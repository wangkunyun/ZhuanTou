package com.ztkj.wky.zhuantou.landing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
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
import com.ztkj.wky.zhuantou.MainActivity;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.DlBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class PasswordActivity extends AppCompatActivity {

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
    @BindView(R.id.ps_bc)
    RelativeLayout psBc;
    private String url = StringUtils.jiekouqianzui + "Login/register";
    private String url2 = StringUtils.jiekouqianzui + "Login/retrievePassword";
    private String finurl;
    private Intent intent, intent2;
    private String phone;
    private String source, realName;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private SharedPreferencesHelper sp;
    private String regid;
    private List<String> listz1 = new ArrayList<>();
    private List<String> listz2 = new ArrayList<>();
    private List<String> listz3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        ButterKnife.bind(this);
        ActivityManager.getInstance().addActivity(this);
        intent2 = getIntent();
        phone = intent2.getStringExtra("phone");
        source = intent2.getStringExtra("source");
        realName = intent2.getStringExtra("realName");
        if (source.equals("1")) {
            finurl = url;
        } else if (source.equals("2")) {
            finurl = url2;
        }
        psBc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                psBc.setFocusable(true);
                psBc.setFocusableInTouchMode(true);
                psBc.requestFocus();
                return false;
            }
        });
        psEdt1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                } else {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                }
            }
        });

        sharedPreferencesHelper = new SharedPreferencesHelper(PasswordActivity.this, "anhua");
        sp = new SharedPreferencesHelper(PasswordActivity.this, "Create_team");
        regid = (String) sharedPreferencesHelper.getSharedPreference("regId", "");
        TransformationMethod method = PasswordTransformationMethod.getInstance();
        psEdt1.setTransformationMethod(method);
        psEdt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                register2();
            }

            @Override
            public void afterTextChanged(Editable s) {
                register2();
            }
        });
        register2();
    }

    private void register() {
        if (!(StringUtils.isPassword(psEdt1.getText().toString())) && !("".equals(psEdt1.getText().toString()))) {
            showToast("密码必须为8-18位字母+数字");
        } else {
            gi();
        }
    }

    private void register2() {
        String edd = psEdt1.getText().toString();
        if ("".equals(edd)) {
            psCha.setEnabled(false);
            psCha.setAlpha(0f);
        } else {
            psCha.setEnabled(true);
            psCha.setAlpha(1f);
        }

        if (!("".equals(edd))) {
            psBtn.setBackgroundResource(R.drawable.yuanjiaobtn);
            psBtn.setEnabled(true);
        } else if ("".equals(edd)) {
            psBtn.setBackgroundResource(R.drawable.yuanjiaobtn2);
            psBtn.setEnabled(false);
        }
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

    private void gi() {
        OkHttpUtils.post()
                .url(finurl)
                .addParams("phone", phone)
                .addParams("pwd", psEdt1.getText().toString())
                .addParams("username", realName)
                .addParams("addtag", JPushInterface.getRegistrationID(this))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.d("rapq", response);
                        Gson gson = new Gson();
                        DlBean dlBean = gson.fromJson(response, DlBean.class);
                        if (dlBean.getErrno().equals("200")) {
                            sharedPreferencesHelper.put("uid", dlBean.getData().getUid());
                            sharedPreferencesHelper.put("username", dlBean.getData().getName());
                            sharedPreferencesHelper.put("realname", dlBean.getData().getUsername());
                            sharedPreferencesHelper.put("phone", dlBean.getData().getPhone());
                            sharedPreferencesHelper.put("type", dlBean.getData().getType());
                            sharedPreferencesHelper.put("token", dlBean.getData().getToken());
                            sharedPreferencesHelper.put("zuijin1", listz1);
                            sharedPreferencesHelper.put("zuijin2", listz2);
                            sharedPreferencesHelper.put("zuijin3", listz3);

                            sharedPreferencesHelper.put("userhead", dlBean.getData().getHead());

                            sp.put("Create_team_cid", dlBean.getData().getCid());
                            sp.put("Create_team_name", dlBean.getData().getTeam_name());
                            sp.put("Create_team_logo", dlBean.getData().getTeam_logo());
                            sp.put("Create_team_jurisdiction", dlBean.getData().getJurisdiction());


                            JPushInterface.setAlias(PasswordActivity.this, Integer.parseInt(dlBean.getData().getUid()), dlBean.getData().getUid());
//                            JPushInterface.deleteAlias(AqZxActivity.this, Integer.parseInt(id));
                            ActivityManager.getInstance().exit();
                            intent = new Intent(PasswordActivity.this, MainActivity.class);
                            startActivity(intent);
//                            finish();
                        } else {
                            showToast(dlBean.getErrmsg());
                        }
                    }
                });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @OnClick({R.id.ps_goback, R.id.ps_btn, R.id.ps_cha})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ps_goback:
                finish();
                break;
            case R.id.ps_btn:
                register();
                break;
            case R.id.ps_cha:
                psEdt1.setText("");
                break;
        }
    }
}
