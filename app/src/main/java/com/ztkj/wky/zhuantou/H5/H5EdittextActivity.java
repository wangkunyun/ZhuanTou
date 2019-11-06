package com.ztkj.wky.zhuantou.H5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.CommentBean;
import com.ztkj.wky.zhuantou.landing.LoginActivity;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class H5EdittextActivity extends AppCompatActivity {

    @BindView(R.id.h5edt_goback)
    ImageView h5edtGoback;
    @BindView(R.id.h5edt_edt)
    EditText h5edtEdt;
    @BindView(R.id.h5edt_btn)
    Button h5edtBtn;
    private Intent intent, intent2;
    private String uid, aid, types, token;
    private String url = StringUtils.jiekouqianzui + "Article/comment";
    private SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5_edittext);
        ButterKnife.bind(this);
        ActivityManager.getInstance().addActivity(this);
        intent2 = getIntent();
        sharedPreferencesHelper = new SharedPreferencesHelper(H5EdittextActivity.this, "anhua");

//        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");

        uid = intent2.getStringExtra("uid");
        aid = intent2.getStringExtra("aid");
        types = intent2.getStringExtra("types");

    }

    @OnClick({R.id.h5edt_goback, R.id.h5edt_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.h5edt_goback:
                finish();
                break;
            case R.id.h5edt_btn:
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(H5EdittextActivity.this, LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                register();
                break;
        }
    }

    private void register() {
        if (h5edtEdt.getText().length() < 11 || h5edtEdt.getText().length() > 150) {
            Toast.makeText(H5EdittextActivity.this, "发表言论必须在10-150字之间", Toast.LENGTH_SHORT).show();
        } else {
            OkHttpUtils.post()
                    .url(url)
                    .addParams("uid", uid)
                    .addParams("aid", aid)
                    .addParams("content", h5edtEdt.getText().toString())
                    .addParams("types", types)
                    .addParams("token", token)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {
                        }

                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            CommentBean geRenBean = gson.fromJson(response, CommentBean.class);
                            if (geRenBean.getErrno().equals("200")) {
                                if (geRenBean.getData().getAddNumber().equals("1")) {
                                    Toast.makeText(H5EdittextActivity.this, geRenBean.getErrmsg(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(H5EdittextActivity.this, "发表成功", Toast.LENGTH_SHORT).show();
                                }
                                finish();
                            } else if (geRenBean.getErrno().equals("666666")) {
                                Toast.makeText(H5EdittextActivity.this, "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                                JPushInterface.deleteAlias(H5EdittextActivity.this, Integer.parseInt(uid));
                                sharedPreferencesHelper.clear();
                                ActivityManager.getInstance().exit();
                                intent = new Intent(H5EdittextActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(H5EdittextActivity.this, gson.toJson(geRenBean.getErrmsg()), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }

}
