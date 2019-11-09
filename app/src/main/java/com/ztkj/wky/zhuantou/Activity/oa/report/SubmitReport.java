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

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.SubmitReportAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.SubmitReportBean;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubmitReport extends Fragment {


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
    private List<SubmitReportBean.DataBean> data;
    private String TAG = "SubmitReport";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_submit_report, container, false);
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
                .url(Contents.SUNMITREPORT)
                .addParams("token", token)
                .addParams("uid", uid)
                .addParams("cid", cid)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                SubmitReportBean submitReportBean = new Gson().fromJson(response, SubmitReportBean.class);
                if (submitReportBean.getErrno().equals("200")) {
                    data = submitReportBean.getData();
                    if (data.size() == 0) {
                        noMessage.setVisibility(View.VISIBLE);
                        re.setVisibility(View.GONE);
                    }

                    SubmitReportAdapter submitReportAdapter = new SubmitReportAdapter(getActivity(), data);
                    re.setLayoutManager(new LinearLayoutManager(getActivity()));
                    re.setAdapter(submitReportAdapter);

                } else if (submitReportBean.getErrno().equals("666666")) {
                    Toast.makeText(getActivity(), "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                    JPushInterface.deleteAlias(getActivity(), Integer.parseInt(uid));
                    sharedPreferencesHelper.clear();
                    SPUtils.getInstance().clear();
                    ActivityManager.getInstance().exit();
                    intent = new Intent(getActivity(), NewLoginActivity.class);
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
