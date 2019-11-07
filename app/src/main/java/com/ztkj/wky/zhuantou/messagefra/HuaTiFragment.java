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
import com.ztkj.wky.zhuantou.adapter.MyAdapter2;
import com.ztkj.wky.zhuantou.bean.RvBean2;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;

/**
 * A simple {@link Fragment} subclass.
 */
public class HuaTiFragment extends Fragment {


    @BindView(R.id.huati_rv)
    RecyclerView huatiRv;
    Unbinder unbinder;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String uid,token;
    private String url = StringUtils.jiekouqianzui+"Article/informationList";
    private Intent intent;
    public HuaTiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hua_ti, container, false);
        unbinder = ButterKnife.bind(this, view);
        huatiRv.setHasFixedSize(true);
        huatiRv.setNestedScrollingEnabled(false);
        sharedPreferencesHelper = new SharedPreferencesHelper(getContext(), "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid","");
        token = (String) sharedPreferencesHelper.getSharedPreference("token","");
        gi();
        return view;
    }

    private void gi() {

        OkHttpUtils.post()
                .url(url)
                .addParams("page","1")
                .addParams("count","1000")
                .addParams("","")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        RvBean2 rvBean2 = gson.fromJson(response, RvBean2.class);
                        if (rvBean2.getErrno().equals("200")){
                            List<RvBean2.DataBean.ListBean> data = rvBean2.getData().getList();
                            MyAdapter2 myAdapter2 = new MyAdapter2(data,getContext());
                            huatiRv.setLayoutManager(new LinearLayoutManager(getContext()));
                            huatiRv.setAdapter(myAdapter2);
                        } else if (rvBean2.getErrno().equals("666666")){
                            Toast.makeText(getContext(),"您的账号已在其他手机登录，如非本人操作，请修改密码",Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(getContext(), Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
                            ActivityManager.getInstance().exit();
                            intent = new Intent(getContext(), NewLoginActivity.class);
                            startActivity(intent);
                            getActivity().finish();

//                            Intent intent = new Intent(getContext(), NewLoginActivity.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                            Bundle bundle = new Bundle();
//                            bundle.putBoolean(Constant.INTENT_LOGIN_AGAIN,true);
//                            bundle.putString(Constant.INTENT_LOGIN_MESSAGE,message);
//                            intent.putExtras(bundle);
//                            startActivity(intent);

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
