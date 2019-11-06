package com.ztkj.wky.zhuantou.isMy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.Config;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.FangketijiaoBean;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FangKeActivity extends AppCompatActivity {

    @BindView(R.id.geren_goback)
    ImageView gerenGoback;
    @BindView(R.id.fangke_jilv)
    TextView fangkeJilv;
    @BindView(R.id.fangke_phone)
    EditText fangkePhone;
    @BindView(R.id.fangke_date)
    TextView fangkeDate;
    @BindView(R.id.login_tijiao)
    Button loginTijiao;
    private String TAG = "FangKeActivity";
    private String uid;
    private String token;
    private Intent intent;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String time_2;
    private String url = "https://api.zhuantoukj.com/birck/index.php/Home/Passageway/addVisitors";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fang_ke);
        ButterKnife.bind(this);
        //Edittext只能输入数字
        fangkePhone.setInputType(InputType.TYPE_CLASS_PHONE);
        //点击软键盘外部，收起软键盘
        fangkePhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (manager != null)
                        manager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });

        sharedPreferencesHelper = new SharedPreferencesHelper(FangKeActivity.this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
    }

    @OnClick({R.id.geren_goback, R.id.fangke_jilv, R.id.fangke_phone, R.id.fangke_date, R.id.login_tijiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.geren_goback:
                finish();
                break;
            case R.id.fangke_jilv:
                intent = new Intent(FangKeActivity.this, FangkejilvActivity.class);
                startActivity(intent);
                break;
            case R.id.fangke_phone:
                break;
            case R.id.fangke_date:
                //时间选择器
                TimePickerView pvTime = new TimePickerBuilder(FangKeActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                        //获取汉字格式的时间戳
                        String time = simpleDateFormat.format(date);
                        //获取英文格式的时间戳并上传给后台
                        time_2 = simpleDateFormat2.format(date);
                        fangkeDate.setText(time);
                    }
                })
                        .setCancelText(" ")//取消按钮文字
                        .setSubmitColor(Color.rgb(101, 201, 210))//确定按钮文字颜色
                        .build();

                pvTime.show();
                break;
            case R.id.login_tijiao:
                gi();
                break;
        }
    }

    private void gi() {
        if ("".equals(fangkePhone.getText().toString())) {
            Toast.makeText(this, "请输入您的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(fangkeDate.getText().toString())) {
            Toast.makeText(this, "请选择日期", Toast.LENGTH_SHORT).show();
            return;
        }

        OkHttpUtils.post()
                .url(url)
                .addParams("token", token)
                .addParams("phone", fangkePhone.getText().toString())
                .addParams("uid", uid)
                .addParams("time", time_2)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        FangketijiaoBean fangketijiaoBean = new Gson().fromJson(response, FangketijiaoBean.class);
                        if (fangketijiaoBean.getErrno().equals("200")) {
                            popuinit2();

                        }
                    }
                });
    }

    private void popuinit2() {
        View contentView = LayoutInflater.from(FangKeActivity.this).inflate(R.layout.pp_telephone, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.2f);
        //下面是p里面的东西
        TextView ptextView = contentView.findViewById(R.id.ppt_tv1);
        Button pbutton = contentView.findViewById(R.id.ppt_btn1);
        Button pbutton2 = contentView.findViewById(R.id.ppt_btn2);
        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        View rootview = LayoutInflater.from(FangKeActivity.this).inflate(R.layout.activity_sz, null);
        ptextView.setText("访客录入成功，将【小砖来访】分享给他？");
        pbutton.setText("取消");
        pbutton2.setText("确认");
        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                window.dismiss();
                finish();
            }
        });
        pbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IWXAPI wxApi = WXAPIFactory.createWXAPI(FangKeActivity.this, Config.WX_APP_ID, true);
                wxApi.registerApp(Config.WX_APP_ID);
                WXMiniProgramObject miniProgram = new WXMiniProgramObject();
                miniProgram.webpageUrl = Config.WEIXIN_XIAOCHENGXU_PATH;//自定义
                miniProgram.userName = Config.WEIXIN_XIAOCHENGXU_ID;//小程序端提供参数
                miniProgram.path = Config.WEIXIN_XIAOCHENGXU_PATH;//小程序端提供参数

                WXMediaMessage mediaMessage = new WXMediaMessage(miniProgram);
                mediaMessage.title = "小砖来访";//自定义
                mediaMessage.description = "小砖来访";//自定义
                Bitmap bitmap = BitmapFactory.decodeResource(FangKeActivity.this.getResources(), R.mipmap.icon_wx_logo);
                Bitmap sendBitmap = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
//                bitmap.recycle();
                mediaMessage.setThumbImage(sendBitmap);
//                mediaMessage.thumbData = sendBitmap;
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = "";
                req.scene = SendMessageToWX.Req.WXSceneSession;
                req.message = mediaMessage;
                wxApi.sendReq(req);


                backgroundAlpha(1f);
                window.dismiss();
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        window.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }
}
