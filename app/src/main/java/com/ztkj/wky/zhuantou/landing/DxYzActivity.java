package com.ztkj.wky.zhuantou.landing;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MainActivity;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.DlBean;
import com.ztkj.wky.zhuantou.bean.ZcBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class DxYzActivity extends AppCompatActivity implements SecurityCodeView.InputCompleteListener {

    @BindView(R.id.dx_yz_goback)
    ImageView dxYzGoback;
    @BindView(R.id.dx_yz_icon1)
    ImageView dxYzIcon1;
    @BindView(R.id.edit_security_code)
    SecurityCodeView editSecurityCode;
    @BindView(R.id.dx_yz_tk)
    TextView dxYzTk;
    @BindView(R.id.dx_yz_cxsrts_tv)
    TextView dxYzCxsrtsTv;
    @BindView(R.id.dx_yx_btn)
    Button dxYxBtn;
    private Intent intent;
    private Intent intent2;
    private int countSeconds = 60;//倒计时秒数为60
    private String url = StringUtils.jiekouqianzui + "Shortmessage/Verification";
    private String urlmsg = StringUtils.jiekouqianzui + "Shortmessage/send_code";
    private String phone = "";
    private String TAG = "DxYzActivity";
    private SharedPreferencesHelper sharedPreferencesHelper;
    private SharedPreferencesHelper sp;

    @SuppressLint("HandlerLeak")
    private Handler mCountHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (countSeconds > 0) {
                --countSeconds;
                dxYxBtn.setBackgroundResource(R.drawable.yuanjiaobtn2);
                dxYxBtn.setText("重新发送(" + countSeconds + "s)");
                dxYxBtn.setEnabled(false);
                mCountHandler.sendEmptyMessageDelayed(0, 1000);
            } else {
                countSeconds = 60;
                dxYxBtn.setText("重新发送");
                dxYxBtn.setBackgroundResource(R.drawable.yuanjiaobtn);
                dxYxBtn.setEnabled(true);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dx_yz);
        ActivityManager.getInstance().addActivity(this);
        ButterKnife.bind(this);
        sharedPreferencesHelper = new SharedPreferencesHelper(this, "anhua");
        sp = new SharedPreferencesHelper(this, "Create_team");
        intent2 = getIntent();
        phone = intent2.getStringExtra("phone");
        setListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startCountBack();
    }

    private void setListener() {
        editSecurityCode.setInputCompleteListener(this);
    }

    private void startCountBack() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCountHandler.sendEmptyMessage(0);
            }
        });
    }

    @Override
    public void inputComplete() {
        OkHttpUtils.post()
                .url(url)
                .addParams("code", editSecurityCode.getEditContent())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        Gson gson = new Gson();
                        ZcBean zcBean = gson.fromJson(response, ZcBean.class);
                        if (zcBean.getErrno().equals("200")) {

                            OkHttpUtils.post().url(Contents.CODELOGIN)
                                    .addParams("phone", phone)
                                    .addParams("addtag", JPushInterface.getRegistrationID(DxYzActivity.this))
                                    .build().execute(new StringCallback() {
                                @Override
                                public void onError(Request request, Exception e) {

                                }

                                @Override
                                public void onResponse(String response) {
                                    Log.e("LoginActivity", "onResponse: " + response);
                                    Gson gson = new Gson();
                                    DlBean dlBean = gson.fromJson(response, DlBean.class);
                                    Log.d("log111", response);
                                    if (dlBean.getErrno().equals("200")) {
                                        SPUtils.getInstance().put("token", dlBean.getData().getToken());
                                        SPUtils.getInstance().put("uid", dlBean.getData().getUid());
                                        SPUtils.getInstance().put("realname", dlBean.getData().getUsername());
                                        SPUtils.getInstance().put("phone", dlBean.getData().getPhone());
                                        SPUtils.getInstance().put("cid", dlBean.getData().getCid());
                                        sharedPreferencesHelper.put("uid", dlBean.getData().getUid());
                                        sharedPreferencesHelper.put("username", dlBean.getData().getName());
                                        sharedPreferencesHelper.put("realname", dlBean.getData().getUsername());
                                        sharedPreferencesHelper.put("phone", dlBean.getData().getPhone());
//                                        sharedPreferencesHelper.put("password", loginEdt2.getText().toString().trim());
                                        sharedPreferencesHelper.put("type", dlBean.getData().getType());
                                        sharedPreferencesHelper.put("token", dlBean.getData().getToken());
                                        sharedPreferencesHelper.put("userhead", dlBean.getData().getHead());
                                        sp.put("Create_team_cid", dlBean.getData().getCid());
                                        sp.put("Create_team_name", dlBean.getData().getTeam_name());
//                            Log.e(TAG, "onResponse: "+ sp.getSharedPreference("Create_team_name",""));
                                        sp.put("Create_team_logo", dlBean.getData().getTeam_logo());
                                        sp.put("Create_team_jurisdiction", dlBean.getData().getJurisdiction());


                                        //极光推送
                                        JPushInterface.setAlias(DxYzActivity.this, Integer.parseInt(dlBean.getData().getUid()), dlBean.getData().getUid());
                                        intent = new Intent(DxYzActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        showToast(zcBean.getErrmsg());
                                        editSecurityCode.clearEditText();

                                    }
                                }
                            });

                        }
                    }
                });
    }

    @Override
    public void deleteContent(boolean isDelete) {
    }

    private void gi() {
        OkHttpUtils.post()
                .url(urlmsg)
                .addParams("phone", phone)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        Gson gson = new Gson();
                        ZcBean zcBean = gson.fromJson(response, ZcBean.class);
                        if (zcBean.getErrno().equals("200")) {
                        } else {
                            showToast(zcBean.getErrmsg());
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void showToast(String txt) {
        Toast tast = Toast.makeText(this, txt, Toast.LENGTH_SHORT);
        tast.setGravity(Gravity.CENTER, 0, 0);
        View view = LayoutInflater.from(this).inflate(R.layout.toast_layout, null);
        TextView tvMsg = view.findViewById(R.id.tvMsg);
        tvMsg.setText(txt);
        tast.setView(view);
        tast.show();
    }


    @OnClick({R.id.dx_yz_goback, R.id.dx_yx_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.dx_yz_goback:
                finish();
                break;
            case R.id.dx_yx_btn:
                gi();
                break;
        }
    }
}
