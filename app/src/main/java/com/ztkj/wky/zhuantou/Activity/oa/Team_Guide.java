package com.ztkj.wky.zhuantou.Activity.oa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.TeamGuideAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.Team_GuideBean;
import com.ztkj.wky.zhuantou.landing.LoginActivity;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class Team_Guide extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.re)
    RecyclerView re;

    private String TAG = "Team_Guide";
    private SharedPreferencesHelper sharedPreferencesHelper;
    private SharedPreferencesHelper sp;
    private String uid;
    private String token;
    private Intent intent;
    private List<Team_GuideBean.DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_guide);
        ButterKnife.bind(this);

        sp = new SharedPreferencesHelper(this, "Create_team");
        sharedPreferencesHelper = new SharedPreferencesHelper(this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        void_request();
    }

    private void void_request() {
        OkHttpUtils.post()
                .url(Contents.GRTGUIDE)
                .addParams("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        Team_GuideBean team_guideBean = new Gson().fromJson(response, Team_GuideBean.class);
                        if (team_guideBean.getErrno().equals("200")) {
                            data = team_guideBean.getData();
//                            TeamScaleAdapter teamScaleAdapter = new TeamScaleAdapter(data, Team_Guide.this);
                            TeamGuideAdapter teamGuideAdapter = new TeamGuideAdapter(data, Team_Guide.this);

                            teamGuideAdapter.setOnItemClickListener(new TeamGuideAdapter.OnItemClickListener() {
                                @Override
                                public void onClick(int position) {
                                    Intent intent = new Intent(Team_Guide.this, Create_Team.class);
                                    sp.put("create_team_guide", data.get(position).getTitle());
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onLongClick(int position) {

                                }
                            });

                            re.setLayoutManager(new LinearLayoutManager(Team_Guide.this));
                            re.setAdapter(teamGuideAdapter);

                        } else if (team_guideBean.getErrno().equals("666666")) {
                            Toast.makeText(Team_Guide.this, "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(Team_Guide.this, Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
                            ActivityManager.getInstance().exit();
                            intent = new Intent(Team_Guide.this, LoginActivity.class);
                            startActivity(intent);
//                            getActivity().finish();
                        } else {

                        }
                    }
                });
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
