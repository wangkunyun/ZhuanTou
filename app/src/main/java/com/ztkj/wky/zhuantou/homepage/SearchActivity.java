package com.ztkj.wky.zhuantou.homepage;

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
import com.ztkj.wky.zhuantou.adapter.MyAdapter6;
import com.ztkj.wky.zhuantou.adapter.MyAdapter7;
import com.ztkj.wky.zhuantou.bean.SearchBean;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.bigsearch_back)
    ImageView bigsearchBack;
    @BindView(R.id.bigsearch_edt)
    EditText bigsearchEdt;
    @BindView(R.id.bigsearch_rv1)
    RecyclerView bigsearchRv1;
    @BindView(R.id.bigsearch_rv2)
    RecyclerView bigsearchRv2;
    @BindView(R.id.bigsearch_bc)
    LinearLayout bigsearchBc;
    private String uid,token;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private Intent intent;
    private String url = StringUtils.jiekouqianzui+"Article/searchHelp";
    private String searchtv = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        ActivityManager.getInstance().addActivity(this);
        sharedPreferencesHelper = new SharedPreferencesHelper(SearchActivity.this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token","");

        bigsearchBc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                bigsearchBc.setFocusable(true);
                bigsearchBc.setFocusableInTouchMode(true);
                bigsearchBc.requestFocus();
                return false;
            }
        });

        bigsearchEdt.addTextChangedListener(new TextWatcher() {
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
        if ("".equals(bigsearchEdt.getText().toString())){
            searchtv = " ";
        }else {
            searchtv = bigsearchEdt.getText().toString();
        }
        OkHttpUtils.post()
                .url(url)
                .addParams("page","1")
                .addParams("count","1000")
                .addParams("search",searchtv)
                .addParams("types","1")
                .addParams("token",token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Log.d("searchs",response+"  1111"+bigsearchEdt.getText().toString());
                        SearchBean searchBean = gson.fromJson(response, SearchBean.class);
                        if (searchBean.getErrno().equals("200")) {
                            List<SearchBean.DataBean.ListBean> list = searchBean.getData().getList();
                            List<SearchBean.DataBean.EnterpriseBean> enterprise = searchBean.getData().getEnterprise();
                            MyAdapter6 myAdapter6 = new MyAdapter6(enterprise, SearchActivity.this, searchtv);
                            bigsearchRv1.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                            bigsearchRv1.setAdapter(myAdapter6);
                            MyAdapter7 myAdapter7 = new MyAdapter7(list, SearchActivity.this, searchtv);
                            bigsearchRv2.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                            bigsearchRv2.setAdapter(myAdapter7);
                        }else if (searchBean.getErrno().equals("666666")){
                            Toast.makeText(SearchActivity.this,"您的账号已在其他手机登录，如非本人操作，请修改密码",Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(SearchActivity.this, Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
                            ActivityManager.getInstance().exit();
                            intent = new Intent(SearchActivity.this, NewLoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }

    @OnClick({R.id.bigsearch_back, R.id.bigsearch_edt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bigsearch_back:
                finish();
                break;
            case R.id.bigsearch_edt:
                break;
        }
    }
}
