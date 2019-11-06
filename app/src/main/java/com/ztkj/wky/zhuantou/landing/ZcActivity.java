package com.ztkj.wky.zhuantou.landing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.ZcBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZcActivity extends AppCompatActivity {

    //    @BindView(R.id.zc_icon1)
//    ImageView zcIcon1;
//    @BindView(R.id.zc_86)
//    TextView zc86;
//    @BindView(R.id.zc_icon2)
//    ImageView zcIcon2;
    @BindView(R.id.zc_edt1)
    EditText zcEdt1;
    @BindView(R.id.zc_cha)
    ImageView zcCha;
    //    @BindView(R.id.login_xiahua1)
//    TextView loginXiahua1;
    @BindView(R.id.zc_btn)
    Button zcBtn;
    @BindView(R.id.zc_goback)
    ImageView zcGoback;
    @BindView(R.id.zc_bc)
    RelativeLayout zcBc;
    @BindView(R.id.zcRealName)
    EditText zcRealName;
    @BindView(R.id.zc_cha2)
    ImageView zcCha2;
    private String urlmsg = StringUtils.jiekouqianzui + "Shortmessage/send_code";
    private Intent intent, intent2;
    private String source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zc);
        ButterKnife.bind(this);
        ActivityManager.getInstance().addActivity(this);
        register();
        intent2 = getIntent();
        source = intent2.getStringExtra("source");

        zcBc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                zcBc.setFocusable(true);
                zcBc.setFocusableInTouchMode(true);
                zcBc.requestFocus();
                return false;
            }
        });
        zcEdt1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                } else {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                }
            }
        });


        zcEdt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                register();
            }

            @Override
            public void afterTextChanged(Editable s) {
                register();
            }
        });
    }

    private void register() {
        String edd = zcEdt1.getText().toString();
        if ("".equals(edd)) {
            zcCha.setEnabled(false);
            zcCha.setAlpha(0f);
        } else {
            zcCha.setEnabled(true);
            zcCha.setAlpha(1f);
        }

        if (!("".equals(edd))) {
            zcBtn.setBackgroundResource(R.drawable.yuanjiaobtn);
            zcBtn.setEnabled(true);
        } else if ("".equals(edd)) {
            zcBtn.setBackgroundResource(R.drawable.yuanjiaobtn2);
            zcBtn.setEnabled(false);
        }
    }

    private void gi() {
        OkHttpUtils.post()
                .url(urlmsg)
                .addParams("phone", zcEdt1.getText().toString())
                .addParams("source", source)
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
                            intent = new Intent();
                            intent.setClass(ZcActivity.this, DxYzActivity.class);
                            intent.putExtra("phone", zcEdt1.getText().toString());
                            intent.putExtra("source", source);
                            intent.putExtra("realName", zcRealName.getText().toString());
                            startActivity(intent);
                            finish();
                        } else {
                            showToast(zcBean.getErrmsg());
                        }
                    }
                });
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

    @OnClick({R.id.zc_goback, R.id.zc_btn, R.id.zc_cha, R.id.zc_cha2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zc_goback:
                finish();
                break;
            case R.id.zc_btn:
                gi();
                break;
            case R.id.zc_cha:
                zcEdt1.setText("");
                break;
            case R.id.zc_cha2:
                zcRealName.setText("");
                break;
        }
    }

}
