package com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.readapply;


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
import com.ztkj.wky.zhuantou.adapter.AllApplyAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.AllApplyBean;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;

/**
 * A simple {@link Fragment} subclass.
 */
public class HaveApply extends Fragment {


    @BindView(R.id.re)
    RecyclerView re;
    Unbinder unbinder;
    @BindView(R.id.no_message)
    ImageView noMessage;

    private SharedPreferencesHelper sharedPreferencesHelper, sp_create_team, sp_apply;
    private String uid;
    private String token;
    private String cid;
    private Intent intent;
    private String sp_apply_type, sp_apply_isAppover, url;
    private String TAG = "HaveApply";
    private List<AllApplyBean.DataBean> data;
    private String discover;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_have_apply, container, false);
        unbinder = ButterKnife.bind(this, view);
        discover = "2";
        //获取token和id
        sharedPreferencesHelper = new SharedPreferencesHelper(getActivity(), "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        //获取cid
        sp_create_team = new SharedPreferencesHelper(getActivity(), "Create_team");
        cid = (String) sp_create_team.getSharedPreference("Create_team_cid", "");
        //获取type
        sp_apply = new SharedPreferencesHelper(getActivity(), "apply");
        sp_apply_type = (String) sp_apply.getSharedPreference("sp_apply_type", "0");


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //获取type
        sp_apply = new SharedPreferencesHelper(getActivity(), "apply");
        sp_apply_type = (String) sp_apply.getSharedPreference("sp_apply_type", "0");
        sp_apply_isAppover = (String) sp_apply.getSharedPreference("apply_isAppover", "0");
        if (sp_apply_isAppover.equals("0")) {
            url = Contents.HAVEAPPLY;
        } else if (sp_apply_isAppover.equals("1")) {
            url = Contents.HAVEEXAME;
        }
        Log.e(TAG, "onResume: " + sp_apply_type);
        request(url);
    }


    private void request(String url) {
        Log.e(TAG, "request: qingqiuqingiuq" + token + "==" + uid + "==" + cid + "==" + sp_apply_type);
        OkHttpUtils.post()
                .url(url)
                .addParams("token", token)
                .addParams("uid", uid)
                .addParams("cid", cid)
                .addParams("type", sp_apply_type)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: 已审批" + response);
                AllApplyBean allApplyBean = new Gson().fromJson(response, AllApplyBean.class);
                if (allApplyBean.getErrno().equals("200")) {
                    data = allApplyBean.getData();
                    if (data.size() == 0) {
                        noMessage.setVisibility(View.VISIBLE);
                        re.setVisibility(View.GONE);
                    }
                    AllApplyAdapter allApplyAdapter = new AllApplyAdapter(getActivity(), data, discover);
                    re.setLayoutManager(new LinearLayoutManager(getActivity()));
                    re.setAdapter(allApplyAdapter);

                } else if (allApplyBean.getErrno().equals("666666")) {
                    Toast.makeText(getActivity(), "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                    if(!uid.equals("")){
                        JPushInterface.deleteAlias(getActivity(), Integer.parseInt(uid));
                        sharedPreferencesHelper.clear();
                        SPUtils.getInstance().clear();
                        ActivityManager.getInstance().exit();
                        intent = new Intent(getActivity(), NewLoginActivity.class);
                        startActivity(intent);
                    }
                } else {

                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        sp_apply.clear();
    }
}
