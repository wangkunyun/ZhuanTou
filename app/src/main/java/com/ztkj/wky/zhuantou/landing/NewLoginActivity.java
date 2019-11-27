package com.ztkj.wky.zhuantou.landing;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.H5.UserInstructionsActivity;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.MPermissionUtils;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.ZcBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewLoginActivity extends AppCompatActivity {

    @BindView(R.id.ps_goback)
    ImageView psGoback;
    @BindView(R.id.ps_icon1)
    ImageView psIcon1;
    @BindView(R.id.ps_edt1)
    EditText psEdt1;
    @BindView(R.id.ps_cha)
    ImageView psCha;
    @BindView(R.id.ps_xiahua1)
    TextView psXiahua1;
    @BindView(R.id.ps_btn)
    Button psBtn;
    @BindView(R.id.login_userxy)
    TextView loginUserxy;
    @BindView(R.id.login_xy)
    LinearLayout loginXy;
    private String ed_zh;//存储账号
    private String ed_mm;//存储密码
    private Intent intent;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private SharedPreferencesHelper sp;


    public static void start(Context context) {
        Intent starter = new Intent(context, NewLoginActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);
        ButterKnife.bind(this);

        ActivityManager.getInstance().addActivity(this);
        MPermissionUtils.requestPermissionsResult(this, 1,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.VIBRATE,
                        Manifest.permission.WAKE_LOCK,
                        Manifest.permission.SYSTEM_ALERT_WINDOW,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CALL_PHONE,
                },

                //这个是悬浮窗设置 但是小米手机设置悬浮窗权限的时候要求必须要在设置里面打开
                new MPermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                    }

                    @Override
                    public void onPermissionDenied() {
                    }
                });


        sharedPreferencesHelper = new SharedPreferencesHelper(NewLoginActivity.this, "anhua");
        sp = new SharedPreferencesHelper(NewLoginActivity.this, "Create_team");

    }


    @OnClick({R.id.ps_goback, R.id.ps_btn, R.id.login_xy, R.id.ps_cha})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ps_goback:
                finish();
                break;
            case R.id.ps_btn:

                if (StringUtils.isEmpty(psEdt1.getText().toString())) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                gi();

                break;
            case R.id.login_xy:
                intent = new Intent();
                intent.setClass(NewLoginActivity.this, UserInstructionsActivity.class);
                intent.putExtra("urlmsg", "https://api.zhuantoukj.com/image/xieyi.html");
                intent.putExtra("title", "用户协议");
                startActivity(intent);
                break;
            case R.id.ps_cha:
                psEdt1.setText("");
                break;
        }
    }

    private void gi() {
        OkHttpUtils.post()
                .url(Contents.SENDCODE)
                .addParams("phone", psEdt1.getText().toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.d("resduan", response);
                        Gson gson = new Gson();
                        ZcBean zcBean = gson.fromJson(response, ZcBean.class);
                        if (zcBean.getErrno().equals("200")) {
                            intent = new Intent(NewLoginActivity.this, DxYzActivity.class);
                            intent.putExtra("phone", psEdt1.getText().toString());
                            startActivity(intent);
                        } else {

                       }
                    }
                });
    }

}
