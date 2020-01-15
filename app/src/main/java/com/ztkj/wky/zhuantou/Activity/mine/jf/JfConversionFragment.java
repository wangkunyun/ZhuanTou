package com.ztkj.wky.zhuantou.Activity.mine.jf;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.H5.UserInstructionsActivity;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.Colorstring;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.MyAdapter4;
import com.ztkj.wky.zhuantou.bean.JiFenBean;
import com.ztkj.wky.zhuantou.bean.MyZtBean;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;

public class JfConversionFragment extends Fragment {


    @BindView(R.id.zc_goback)
    ImageView zcGoback;
    @BindView(R.id.jifen_gyzt)
    TextView jifenGyzt;
    @BindView(R.id.jifen_ztsl)
    TextView jifenZtsl;
    @BindView(R.id.jifen_qiandao)
    Button jifenQiandao;
    @BindView(R.id.jifen_tv1)
    TextView jifenTv1;
    @BindView(R.id.jifen_rv)
    RecyclerView jifenRv;
    Unbinder unbinder;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private Intent intent;
    private String reach;
    private String uid, token;
    private String url = StringUtils.jiekouqianzui + "Commodity/commodityList";
    private String url2 = StringUtils.jiekouqianzui + "User/record";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jf_conversion, container, false);
        unbinder = ButterKnife.bind(this, view);
        jifenRv.setHasFixedSize(true);
        jifenRv.setNestedScrollingEnabled(false);
        ActivityManager.getInstance().addActivity(getActivity());
        sharedPreferencesHelper = new SharedPreferencesHelper(getActivity(), "anhua");
        reach = (String) sharedPreferencesHelper.getSharedPreference("reach", "");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        register();
        gi();

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void register() {
        if (reach.equals("0")) {
            jifenQiandao.setEnabled(true);
            jifenQiandao.setText("签到领积分");
            jifenQiandao.setTextColor(Color.parseColor(Colorstring.baise));
        } else if (reach.equals("1")) {
            jifenQiandao.setEnabled(false);
            jifenQiandao.setText("已签到");
            jifenQiandao.setTextColor(Color.parseColor("#FFC4A3"));
        }
    }

    private void gi() {
        OkHttpUtils.post()
                .url(url)
                .addParams("uid", uid)
                .addParams("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();

                        Log.d("fuckckck", response);
                        MyZtBean myZtBean = gson.fromJson(response, MyZtBean.class);

                        if (myZtBean.getErrno().equals("200")) {
                            jifenZtsl.setText(myZtBean.getData().getIntegral());

                            List<MyZtBean.DataBean.CommodityListBean> data = myZtBean.getData().getCommodityList();
                            MyAdapter4 myAdapter4 = new MyAdapter4(data, getActivity());
                            jifenRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                            jifenRv.setAdapter(myAdapter4);
                        } else if (myZtBean.getErrno().equals("666666")) {
                            Toast.makeText(getActivity(), "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(getActivity(), Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
                            SPUtils.getInstance().clear();
                            ActivityManager.getInstance().exit();
                            intent = new Intent(getActivity(), NewLoginActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }
                    }
                });
    }

    private void gi2() {
        OkHttpUtils.post()
                .url(url2)
                .addParams("uid", uid)
                .addParams("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.d("jifenll", response);
                        Gson gson = new Gson();
                        JiFenBean zcBean = gson.fromJson(response, JiFenBean.class);
                        if ("200".equals(zcBean.getErrno())) {
                            jifenQiandao.setEnabled(false);
                            jifenQiandao.setText("已签到");
                            jifenQiandao.setTextColor(Color.parseColor("#FFC4A3"));
                            jifenZtsl.setText(zcBean.getData().getIntegral());
                        } else if (zcBean.getErrno().equals("666666")) {
                            Toast.makeText(getActivity(), "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(getActivity(), Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
                            SPUtils.getInstance().clear();
                            ActivityManager.getInstance().exit();
                            intent = new Intent(getActivity(), NewLoginActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        } else {
                            Toast.makeText(getActivity(), gson.toJson(zcBean.getErrmsg()), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


    @OnClick({R.id.zc_goback, R.id.jifen_gyzt, R.id.jifen_ztsl, R.id.jifen_qiandao, R.id.jifen_tv1, R.id.jifen_rv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zc_goback:
                getActivity().finish();
                break;
            case R.id.jifen_gyzt:
                intent = new Intent();
                intent.setClass(getActivity(), UserInstructionsActivity.class);
                intent.putExtra("urlmsg", "https://api.zhuantoukj.com/image/guanyu.html");
                intent.putExtra("title", "关于砖头");
                startActivity(intent);
                break;
            case R.id.jifen_qiandao:
                gi2();
                break;
        }
    }
}
