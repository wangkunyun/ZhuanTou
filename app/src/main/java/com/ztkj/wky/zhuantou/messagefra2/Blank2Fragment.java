package com.ztkj.wky.zhuantou.messagefra2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.MyAdapter;
import com.ztkj.wky.zhuantou.bean.FenLeiBean;
import com.ztkj.wky.zhuantou.bean.RvBean1;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;

/**
 * A simple {@link Fragment} subclass.
 */
public class Blank2Fragment extends Fragment {


    //    @BindView(R.id.blank2_rv)
//    RecyclerView blank2Rv;
    Unbinder unbinder;

    public static RecyclerView blank2Rv;

    private String url = StringUtils.jiekouqianzui + "Article/articleClass";
    private String url2 = StringUtils.jiekouqianzui + "Article/articleList";
    private String cl_id;
    private String cl_zhu_id;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String uid, token;
    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank2, container, false);
        unbinder = ButterKnife.bind(this, view);
        blank2Rv = view.findViewById(R.id.blank2_rv);
        blank2Rv.setHasFixedSize(true);
        blank2Rv.setNestedScrollingEnabled(false);
        sharedPreferencesHelper = new SharedPreferencesHelper(getContext(), "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        gi();
        return view;
    }

    private void gi() {
        OkHttpUtils.post()
                .url(url)
                .addParams("", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        final Gson gson = new Gson();
                        Log.d("fine1", response);
                        FenLeiBean fenLeiBean = gson.fromJson(response, FenLeiBean.class);
                        if (fenLeiBean.getErrno().equals("200")) {

                            cl_id = fenLeiBean.getData().get(0).getSubordinate().get(1).getCl_id();
                            cl_zhu_id = fenLeiBean.getData().get(0).getCl_id();

                            OkHttpUtils.post()
                                    .url(url2)
                                    .addParams("", "")
                                    .addParams("onecless", cl_zhu_id)
                                    .addParams("twocless", cl_id)
                                    .addParams("page", "1")
                                    .addParams("count", "10")
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Request request, Exception e) {
                                        }

                                        @Override
                                        public void onResponse(String response) {
                                            Gson gson1 = new Gson();
                                            RvBean1 rvBean1 = gson1.fromJson(response, RvBean1.class);

                                            if (rvBean1.getErrno().equals("200")) {
                                                List<RvBean1.DataBean.ListBean> data = rvBean1.getData().getList();
                                                MyAdapter myAdapter = new MyAdapter(data, getContext());
                                                blank2Rv.setLayoutManager(new LinearLayoutManager(getContext()));
                                                blank2Rv.setAdapter(myAdapter);
                                            } else if (rvBean1.getErrno().equals("666666")) {
                                                Toast.makeText(getContext(), "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                                                JPushInterface.deleteAlias(getContext(), Integer.parseInt(uid));
                                                sharedPreferencesHelper.clear();
                                                SPUtils.getInstance().clear();
                                                ActivityManager.getInstance().exit();
                                                intent = new Intent(getContext(), NewLoginActivity.class);
                                                startActivity(intent);
//                                                getActivity().finish();
                                            }
                                        }
                                    });
                        } else if (fenLeiBean.getErrno().equals("666666")) {
                            Toast.makeText(getContext(), "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(getContext(), Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
                            SPUtils.getInstance().clear();
                            ActivityManager.getInstance().exit();
                            intent = new Intent(getContext(), NewLoginActivity.class);
                            startActivity(intent);
//                                                getActivity().finish();
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
