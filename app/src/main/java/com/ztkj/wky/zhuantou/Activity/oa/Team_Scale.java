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

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.TeamScaleAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.Team_ScaleBean;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class Team_Scale extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.re)
    RecyclerView re;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private SharedPreferencesHelper sp;
    private String uid;
    private String token;
    private Intent intent;
    private List<Team_ScaleBean.DataBean> data;
    private String TAG = "Team_Scale";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_scale);
        ButterKnife.bind(this);
        sp = new SharedPreferencesHelper(this, "Create_team");
        sharedPreferencesHelper = new SharedPreferencesHelper(this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        void_request();
    }

    private void void_request() {
        OkHttpUtils.post()
                .url(Contents.TEAMSCALE)
                .addParams("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        Team_ScaleBean team_scaleBean = new Gson().fromJson(response, Team_ScaleBean.class);
                        if (team_scaleBean.getErrno().equals("200")) {
                            data = team_scaleBean.getData();
                            TeamScaleAdapter teamScaleAdapter = new TeamScaleAdapter(data, Team_Scale.this);

                            teamScaleAdapter.setOnItemClickListener(new TeamScaleAdapter.OnItemClickListener() {
                                @Override
                                public void onClick(int position) {
                                    Intent intent = new Intent(Team_Scale.this, Create_Team.class);
                                    sp.put("create_team_scale", data.get(position).getScale_type());
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onLongClick(int position) {

                                }
                            });

                            re.setLayoutManager(new LinearLayoutManager(Team_Scale.this));
                            re.setAdapter(teamScaleAdapter);

                        } else if (team_scaleBean.getErrno().equals("666666")) {
                            Toast.makeText(Team_Scale.this, "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(Team_Scale.this, Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
                            ActivityManager.getInstance().exit();
                            intent = new Intent(Team_Scale.this, NewLoginActivity.class);
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
