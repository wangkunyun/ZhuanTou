package com.ztkj.wky.zhuantou.landing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.ZcBean;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private String source, realName;

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
        intent2 = getIntent();
        phone = intent2.getStringExtra("phone");
        source = intent2.getStringExtra("source");
        realName = intent2.getStringExtra("realName");
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
                        Gson gson = new Gson();
                        ZcBean zcBean = gson.fromJson(response, ZcBean.class);
                        if (zcBean.getErrno().equals("200")) {
                            intent = new Intent(DxYzActivity.this, PasswordActivity.class);
                            intent.putExtra("phone", phone);
                            intent.putExtra("source", source);
                            intent.putExtra("realName", realName);
                            startActivity(intent);
                            finish();
                        } else {
                            showToast(zcBean.getErrmsg());
                            editSecurityCode.clearEditText();
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
                .addParams("source", source)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {

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
