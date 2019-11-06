package com.ztkj.wky.zhuantou.Activity.oa.report;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.ReceiveReportAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.ReceiveReportBean;
import com.ztkj.wky.zhuantou.landing.LoginActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceiveReport extends Fragment {

    @BindView(R.id.re)
    RecyclerView re;
    Unbinder unbinder;
    @BindView(R.id.no_message)
    ImageView noMessage;

    private SharedPreferencesHelper sharedPreferencesHelper, sp_create_team;
    private String uid;
    private String token;
    private String cid;
    private Intent intent;
    //    private List<SubmitReportBean.DataBean> data;
    private String TAG = "ReceiveReport";
    private List<ReceiveReportBean.DataBean> data;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_receive_report, container, false);
        unbinder = ButterKnife.bind(this, view);

        //获取token和id
        sharedPreferencesHelper = new SharedPreferencesHelper(getActivity(), "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        //获取cid
        sp_create_team = new SharedPreferencesHelper(getActivity(), "Create_team");
        cid = (String) sp_create_team.getSharedPreference("Create_team_cid", "");

        request();

        return view;
    }

    private void request() {
        OkHttpUtils.post()
                .url(Contents.RECEIVEREPORT)
                .addParams("token", token)
                .addParams("uid", uid)
                .addParams("cid", cid)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                ReceiveReportBean receiveReportBean = new Gson().fromJson(response, ReceiveReportBean.class);
                if (receiveReportBean.getErrno().equals("200")) {
                    data = receiveReportBean.getData();
                    if (data.size() == 0) {
                        noMessage.setVisibility(View.VISIBLE);
                        re.setVisibility(View.GONE);
                    }

                    ReceiveReportAdapter receiveReportAdapter = new ReceiveReportAdapter(getActivity(), data);
                    re.setLayoutManager(new LinearLayoutManager(getActivity()));
                    re.setAdapter(receiveReportAdapter);

                } else if (receiveReportBean.getErrno().equals("666666")) {
                    Toast.makeText(getActivity(), "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                    JPushInterface.deleteAlias(getActivity(), Integer.parseInt(uid));
                    sharedPreferencesHelper.clear();
                    ActivityManager.getInstance().exit();
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
//                            getActivity().finish();
                } else {

                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
