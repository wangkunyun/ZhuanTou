package com.ztkj.wky.zhuantou.landing;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
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
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.H5.UserInstructionsActivity;
import com.ztkj.wky.zhuantou.MainActivity;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.MPermissionUtils;
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

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_icon1)
    ImageView loginIcon1;
    @BindView(R.id.login_edt1)
    EditText loginEdt1;
    @BindView(R.id.login_cha)
    ImageView loginCha;
    @BindView(R.id.login_icon3)
    ImageView loginIcon3;
    @BindView(R.id.login_edt2)
    EditText loginEdt2;
    @BindView(R.id.login_cha2)
    ImageView loginCha2;
    @BindView(R.id.login_dlzc)
    TextView loginDlzc;
    @BindView(R.id.login_wjmm)
    TextView loginWjmm;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.login_userxy)
    TextView loginUserxy;
    @BindView(R.id.login_xy)
    LinearLayout loginXy;
    @BindView(R.id.login_bc)
    RelativeLayout loginBc;
    private String ed_zh;//存储账号
    private String ed_mm;//存储密码
    private Intent intent;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private SharedPreferencesHelper sp;
    private String regid;
    private String url = StringUtils.jiekouqianzui + "Login/signIn";
    private List<String> listz1 = new ArrayList<>();
    private List<String> listz2 = new ArrayList<>();
    private List<String> listz3 = new ArrayList<>();
    private String token;
    private String TAG = "LoginActivity";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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


        sharedPreferencesHelper = new SharedPreferencesHelper(LoginActivity.this, "anhua");
        sp = new SharedPreferencesHelper(LoginActivity.this, "Create_team");

        regid = (String) sharedPreferencesHelper.getSharedPreference("regId", "");

        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");


        register();
        register2();

        loginBc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                loginBc.setFocusable(true);
                loginBc.setFocusableInTouchMode(true);
                loginBc.requestFocus();
                return false;
            }
        });
        loginEdt1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                } else {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                }
            }
        });
        loginEdt2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                } else {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                }
            }
        });

        TransformationMethod method = PasswordTransformationMethod.getInstance();
        loginEdt2.setTransformationMethod(method);
        loginEdt1.addTextChangedListener(new TextWatcher() {
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
        loginEdt2.addTextChangedListener(new TextWatcher() {
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

    }

    private void register() {
        ed_zh = loginEdt1.getText().toString();
        ed_mm = loginEdt2.getText().toString();
        if ("".equals(ed_zh)) {
            loginCha.setEnabled(false);
            loginCha.setAlpha(0f);
        } else {
            loginCha.setEnabled(true);
            loginCha.setAlpha(1f);
        }
        if (!("".equals(ed_zh)) && !("".equals(ed_mm))) {
            loginBtn.setBackgroundResource(R.drawable.yuanjiaobtn);
            loginBtn.setEnabled(true);
        } else if (("".equals(ed_zh)) || ("".equals(ed_mm))) {
            loginBtn.setBackgroundResource(R.drawable.yuanjiaobtn2);
            loginBtn.setEnabled(false);
        }
    }

    private void register2() {
        ed_zh = loginEdt1.getText().toString();
        ed_mm = loginEdt2.getText().toString();
        if ("".equals(ed_mm)) {
            loginCha2.setEnabled(false);
            loginCha2.setAlpha(0f);
        } else {
            loginCha2.setEnabled(true);
            loginCha2.setAlpha(1f);
        }
        if (!("".equals(ed_zh)) && !("".equals(ed_mm))) {
            loginBtn.setBackgroundResource(R.drawable.yuanjiaobtn);
            loginBtn.setEnabled(true);
        } else if (("".equals(ed_zh)) || ("".equals(ed_mm))) {
            loginBtn.setBackgroundResource(R.drawable.yuanjiaobtn2);
            loginBtn.setEnabled(false);
        }
    }

    private void gi() {
        OkHttpUtils.post()
                .url(url)
                .addParams("phone", loginEdt1.getText().toString())
                .addParams("pwd", loginEdt2.getText().toString())
                .addParams("addtag", JPushInterface.getRegistrationID(this))
                .build()
                .execute(new StringCallback() {
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
                            sharedPreferencesHelper.put("password", loginEdt2.getText().toString().trim());
                            sharedPreferencesHelper.put("type", dlBean.getData().getType());
                            sharedPreferencesHelper.put("token", dlBean.getData().getToken());

                            sharedPreferencesHelper.put("userhead", dlBean.getData().getHead());
                            sharedPreferencesHelper.put("zuijin1", listz1);
                            sharedPreferencesHelper.put("zuijin2", listz2);
                            sharedPreferencesHelper.put("zuijin3", listz3);
                            sp.put("Create_team_cid", dlBean.getData().getCid());
                            sp.put("Create_team_name", dlBean.getData().getTeam_name());
//                            Log.e(TAG, "onResponse: "+ sp.getSharedPreference("Create_team_name",""));
                            sp.put("Create_team_logo", dlBean.getData().getTeam_logo());
                            sp.put("Create_team_jurisdiction", dlBean.getData().getJurisdiction());



                            //极光推送
                            JPushInterface.setAlias(LoginActivity.this, Integer.parseInt(dlBean.getData().getUid()), dlBean.getData().getUid());
                            intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            popuinit(dlBean.getErrmsg());
                        }
                    }
                });

    }

    private void popuinit(String s) {
        View contentView = LayoutInflater.from(LoginActivity.this).inflate(R.layout.mypp_1, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.2f);
        //下面是p里面的东西
        TextView ptextView = contentView.findViewById(R.id.mypp1_tv1);
        Button pbutton = contentView.findViewById(R.id.mypp1_btn);
        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        View rootview = LayoutInflater.from(LoginActivity.this).inflate(R.layout.activity_login, null);
        ptextView.setText(s);
        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    private long firstPressedTime;

    // System.currentTimeMillis() 当前系统的时间
//    @Override
//    public void onBackPressed() {
//        if (System.currentTimeMillis() - firstPressedTime < 2000) {
////            super.onBackPressed();
//            //必须要将这个页面设为栈顶 不然无效 无法全面退出页面
//            finish();
//            System.exit(0);
//        } else {
//            Toast.makeText(LoginActivity.this, "再按一次退出", Toast.LENGTH_LONG).show();
//            firstPressedTime = System.currentTimeMillis();
//        }
//    }

    @OnClick({R.id.login_dlzc, R.id.login_wjmm, R.id.login_xy, R.id.login_btn, R.id.login_userxy, R.id.login_cha, R.id.login_cha2, R.id.login_goback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_goback:
                finish();
                break;
            case R.id.login_dlzc:
                intent = new Intent();
                intent.setClass(LoginActivity.this, ZcActivity.class);
                intent.putExtra("source", "1");
                startActivity(intent);
                break;
            case R.id.login_wjmm:
                intent = new Intent();
                intent.setClass(LoginActivity.this, ZcActivity.class);
                intent.putExtra("source", "2");
                startActivity(intent);
                break;
            case R.id.login_btn:
                gi();
                break;
            case R.id.login_userxy:
                intent = new Intent();
                intent.setClass(LoginActivity.this, UserInstructionsActivity.class);
                intent.putExtra("urlmsg", "https://api.zhuantoukj.com/detial/xieyi.html");
                intent.putExtra("title", "用户协议");
                startActivity(intent);
                break;
            case R.id.login_cha:
                loginEdt1.setText("");
                break;
            case R.id.login_cha2:
                loginEdt2.setText("");
                break;
            case R.id.login_xy:
                intent = new Intent();
                intent.setClass(LoginActivity.this, UserInstructionsActivity.class);
                intent.putExtra("urlmsg", "https://api.zhuantoukj.com/image/xieyi.html");
                intent.putExtra("title", "用户协议");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}