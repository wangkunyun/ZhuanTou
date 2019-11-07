package com.ztkj.wky.zhuantou.homepage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.AllServiceBean;
import com.ztkj.wky.zhuantou.bean.AllServiceBean2;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class AllServiceActivity extends AppCompatActivity {

    @BindView(R.id.allser_img)
    ImageView allserImg;
    @BindView(R.id.allser_goback)
    ImageView allserGoback;
    @BindView(R.id.allser_tittle)
    TextView allserTittle;
    @BindView(R.id.allser_tv1)
    TextView allserTv1;
    @BindView(R.id.allser_tv2)
    TextView allserTv2;
    @BindView(R.id.allser_phone)
    ImageView allserPhone;
    @BindView(R.id.allser_dingimg)
    ImageView allserDingimg;
    @BindView(R.id.allser_tv3)
    TextView allserTv3;

    private String url = StringUtils.jiekouqianzui + "Article/enterpriseServiceInfo";
    private String url2 = StringUtils.jiekouqianzui + "Community/businessInfo";
    private Intent intent, intent2;
    private String eid;
    private String ase;
    private String sphone = "";
    private String uid, token;
    private String TAG = "AllServiceActivity";
    private SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_service);
        ButterKnife.bind(this);
        ActivityManager.getInstance().addActivity(this);
        sharedPreferencesHelper = new SharedPreferencesHelper(AllServiceActivity.this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        intent2 = getIntent();
        eid = intent2.getStringExtra("eid");
        ase = intent2.getStringExtra("ase");
        Log.e(TAG, "onCreate: " + eid + "+" + ase);
        if (ase.equals("0")) {
            gi();
        } else {
            gi2();
        }

    }

    private void gi2() {
        OkHttpUtils.post()
                .url(url2)
                .addParams("bu_id", eid)
                .addParams("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        AllServiceBean2 allServiceBean = gson.fromJson(response, AllServiceBean2.class);
                        if (allServiceBean.getErrno().equals("200")) {
                            sphone = allServiceBean.getData().getBu_phone();
                            Glide.with(AllServiceActivity.this).load(allServiceBean.getData().getBu_image()).into(allserImg);
                            allserTv1.setText(allServiceBean.getData().getBu_name());
                            allserTv2.setText(allServiceBean.getData().getKey_word());
                            allserTv3.setText(allServiceBean.getData().getBu_address());
                        } else if (allServiceBean.getErrno().equals("666666")) {
                            Toast.makeText(AllServiceActivity.this, "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(AllServiceActivity.this, Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
                            ActivityManager.getInstance().exit();
                            intent = new Intent(AllServiceActivity.this, NewLoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }

    private void gi() {
        OkHttpUtils.post()
                .url(url)
                .addParams("eid", eid)
                .addParams("types", "1")
                .addParams("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e("AllServiceActivity", "onResponse: " + response);
                        Gson gson = new Gson();
                        AllServiceBean allServiceBean = gson.fromJson(response, AllServiceBean.class);
                        if (allServiceBean.getErrno().equals("200")) {
                            sphone = allServiceBean.getData().getSphone();
                            Glide.with(AllServiceActivity.this).load(allServiceBean.getData().getScover()).into(allserImg);
                            allserTv1.setText(allServiceBean.getData().getSname());
                            allserTv2.setText(allServiceBean.getData().getSintroduce());
                            allserTv3.setText(allServiceBean.getData().getProvince()
                                    + allServiceBean.getData().getCity()
                                    + allServiceBean.getData().getArea()
                                    + allServiceBean.getData().getAddress());
                        } else if (allServiceBean.getErrno().equals("666666")) {
                            Toast.makeText(AllServiceActivity.this, "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(AllServiceActivity.this, Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
                            ActivityManager.getInstance().exit();
                            intent = new Intent(AllServiceActivity.this, NewLoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }

    private void popuinit(final String s) {
        View contentView = LayoutInflater.from(AllServiceActivity.this).inflate(R.layout.pp_telephone, null);
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
        View rootview = LayoutInflater.from(AllServiceActivity.this).inflate(R.layout.fragment_n3, null);
        ptextView.setText(s);
        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        pbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                window.dismiss();
                intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + s);
                intent.setData(data);
                startActivity(intent);
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {//通过对小区企业住户的服务从而达到智慧城市
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

    @OnClick({R.id.allser_goback, R.id.allser_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.allser_goback:
                finish();
                break;
            case R.id.allser_phone:
                popuinit(sphone);
                break;
        }
    }
}
