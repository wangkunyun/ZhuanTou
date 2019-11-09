package com.ztkj.wky.zhuantou.isMy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.FangKeJiLuAdapter;
import com.ztkj.wky.zhuantou.bean.FangKeJiKvBean;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class FangkejilvActivity extends AppCompatActivity {

    @BindView(R.id.geren_goback)
    ImageView gerenGoback;
    @BindView(R.id.fangke_re)
    RecyclerView fangkeRe;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String uid;
    private String token;
    private Intent intent;
    private String url = "https://api.zhuantoukj.com/birck/index.php/Home/Passageway/selectVisitors";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fangkejilv);
        ButterKnife.bind(this);
        sharedPreferencesHelper = new SharedPreferencesHelper(this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        OkHttpUtils.post()
                .url(url)
                .addParams("token", token)
                .addParams("uid", uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        FangKeJiKvBean fangKeJiKvBean = new Gson().fromJson(response, FangKeJiKvBean.class);
                        if (fangKeJiKvBean.getErrno().equals("200")) {
                            List<FangKeJiKvBean.DataBean> data = fangKeJiKvBean.getData();

                            //将非0的数据存取data2
                            List<FangKeJiKvBean.DataBean> data2 = new ArrayList<>();
                            for (int i = 0; i < data.size(); i++) {
                                if (!data.get(i).getName().equals("0")) {
                                    data2.add(data.get(i));
                                }
                            }

                            FangKeJiLuAdapter fangKeJiLuAdapter = new FangKeJiLuAdapter(data2, FangkejilvActivity.this);
                            fangkeRe.setLayoutManager(new LinearLayoutManager(FangkejilvActivity.this));
                            fangkeRe.setAdapter(fangKeJiLuAdapter);

                        } else if (fangKeJiKvBean.getErrno().equals("666666")) {
                            Toast.makeText(FangkejilvActivity.this, "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(FangkejilvActivity.this, Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
                            SPUtils.getInstance().clear();
                            ActivityManager.getInstance().exit();
                            intent = new Intent(FangkejilvActivity.this, NewLoginActivity.class);
                            startActivity(intent);
//                            getActivity().finish();
                        }
                    }
                });


    }

    @OnClick({R.id.geren_goback, R.id.fangke_re})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.geren_goback:
                finish();
                break;
        }
    }
}