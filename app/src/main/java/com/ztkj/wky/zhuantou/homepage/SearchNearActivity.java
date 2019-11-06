package com.ztkj.wky.zhuantou.homepage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.MyAdapter8;
import com.ztkj.wky.zhuantou.bean.NearSearcchBean;
import com.ztkj.wky.zhuantou.landing.LoginActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class  SearchNearActivity extends AppCompatActivity {

    @BindView(R.id.searchn_back)
    ImageView searchnBack;
    @BindView(R.id.searchn_edt)
    EditText searchnEdt;
    @BindView(R.id.searchn_rv1)
    RecyclerView searchnRv1;
    @BindView(R.id.searchn_bc)
    LinearLayout searchnBc;
    private String url = StringUtils.jiekouqianzui + "Community/communityList";
    private String searchtv = " ";
    private String first;
    private Intent intent,intent2;
    private String uid,token;
    private SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_near);
        ButterKnife.bind(this);
        ActivityManager.getInstance().addActivity(this);
        sharedPreferencesHelper = new SharedPreferencesHelper(SearchNearActivity.this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token","");

        intent2 = getIntent();
        first = intent2.getStringExtra("first");
        register();

        searchnBc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                searchnBc.setFocusable(true);
                searchnBc.setFocusableInTouchMode(true);
                searchnBc.requestFocus();
                return false;
            }
        });
        searchnEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                } else {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                }
            }
        });

        searchnEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                register();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    private void register() {
        if ("".equals(searchnEdt.getText().toString())){
            searchtv = " ";
        }else {
            searchtv = searchnEdt.getText().toString();
        }
        OkHttpUtils.post()
                .url(url)
                .addParams("search",searchtv)
                .addParams("token",token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Log.d("searchs",response+"  1111"+searchtv);
                        NearSearcchBean searchBean = gson.fromJson(response, NearSearcchBean.class);
                        if (searchBean.getErrno().equals("200")) {
                            List<NearSearcchBean.DataBean> enterprise = searchBean.getData();
                            MyAdapter8 myAdapter8 = new MyAdapter8(enterprise, SearchNearActivity.this, first);
                            searchnRv1.setLayoutManager(new LinearLayoutManager(SearchNearActivity.this));
                            searchnRv1.setAdapter(myAdapter8);
                        }else if (searchBean.getErrno().equals("666666")){
                            Toast.makeText(SearchNearActivity.this,"您的账号已在其他手机登录，如非本人操作，请修改密码",Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(SearchNearActivity.this, Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
                            ActivityManager.getInstance().exit();
                            intent = new Intent(SearchNearActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                });
    }


    @OnClick({R.id.searchn_back, R.id.searchn_edt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.searchn_back:
                finish();
                break;
            case R.id.searchn_edt:
//                finish();
                break;
        }
    }

}
