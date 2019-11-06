package com.ztkj.wky.zhuantou.messagefra;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.MyAdapterH5;
import com.ztkj.wky.zhuantou.bean.RvBean1;
import com.ztkj.wky.zhuantou.landing.LoginActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;

/**
 * A simple {@link Fragment} subclass.
 */
public class WuYeFragment extends Fragment {


    @BindView(R.id.blank1_rv)
    RecyclerView blank1Rv;
    Unbinder unbinder;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String uid, token;
    private String url = StringUtils.jiekouqianzui + "Article/propertyList";
    private Intent intent;
    private String TAG = "WuYeFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wu_ye, container, false);
        unbinder = ButterKnife.bind(this, view);
        blank1Rv.setHasFixedSize(true);
        blank1Rv.setNestedScrollingEnabled(false);
        sharedPreferencesHelper = new SharedPreferencesHelper(getContext(), "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        if (StringUtils.isEmpty(uid)){
            gi("");
        }else {
            gi(uid);
        }



        return view;
    }

    private void gi(String uid) {

        OkHttpUtils.post()
                .url(url)
                .addParams("onecless", "11")
                .addParams("count", "10")
                .addParams("uid", uid)
                .addParams("page", "1")
                .addParams("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
//                        Log.d("response200", response);
                        Gson gson1 = new Gson();
                        RvBean1 rvBean1 = gson1.fromJson(response, RvBean1.class);

                        if (rvBean1.getErrno().equals("200")) {
                            List<RvBean1.DataBean.ListBean> data = rvBean1.getData().getList();
                            MyAdapterH5 myAdapter = new MyAdapterH5(data, getContext());
                            blank1Rv.setLayoutManager(new LinearLayoutManager(getContext()));
                            blank1Rv.setAdapter(myAdapter);
                        } else if (rvBean1.getErrno().equals("666666")) {
                            Toast.makeText(getContext(), "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(getContext(), Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
                            ActivityManager.getInstance().exit();
                            intent = new Intent(getContext(), LoginActivity.class);
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
