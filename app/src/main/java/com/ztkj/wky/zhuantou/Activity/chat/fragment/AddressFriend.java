package com.ztkj.wky.zhuantou.Activity.chat.fragment;


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
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.chat.activity.NewFriendList;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.AddressFriendAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.AddressFriendBean;
import com.ztkj.wky.zhuantou.bean.AgreeFriendListBean;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressFriend extends Fragment {


    @BindView(R.id.re)
    RecyclerView re;
    Unbinder unbinder;
    @BindView(R.id.img_newMessage)
    ImageView imgNewMessage;
    @BindView(R.id.tv_connection)
    TextView tvConnection;

    private String TAG = "AddressFriend";
    private String token, phone, uid;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private Intent intent;
    private List<AddressFriendBean.DataBean> data;
    private String intentData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_address_add_friend, container, false);
        unbinder = ButterKnife.bind(this, view);

        sharedPreferencesHelper = new SharedPreferencesHelper(getActivity(), "anhua");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        phone = (String) sharedPreferencesHelper.getSharedPreference("phone", "");


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        request_applyFriend();
        request_addressBook();
    }

    private void request_applyFriend() {
        OkHttpUtils.post().url(Contents.LISTAGREE)
                .addParams("token", token)
                .addParams("phone", phone)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                intentData = response;
                AgreeFriendListBean agreeFriendListBean = new Gson().fromJson(response, AgreeFriendListBean.class);
                List<AgreeFriendListBean.DataBean> data = agreeFriendListBean.getData();
                if (!data.isEmpty()) {
                    imgNewMessage.setVisibility(View.VISIBLE);
                } else {
                    imgNewMessage.setVisibility(View.GONE);
                }
            }
        });
    }

    private void request_addressBook() {

        OkHttpUtils.post()
                .url(Contents.LISTFRIENDS)
                .addParams("token", token)
                .addParams("phone", phone)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse+friend: " + response);
                        AddressFriendBean addressFriendBean = new Gson().fromJson(response, AddressFriendBean.class);

                        if (addressFriendBean.getErrno().equals("200")) {
                            Log.e(TAG, "data+friend: " + response);
                            data = addressFriendBean.getData();
                            AddressFriendAdapter addressFriendAdapter = new AddressFriendAdapter(data, getActivity());
                            re.setLayoutManager(new LinearLayoutManager(getActivity()));
                            re.setAdapter(addressFriendAdapter);
                            if (!data.isEmpty()) {
                                tvConnection.setVisibility(View.GONE);
                            } else {
                                tvConnection.setVisibility(View.VISIBLE);
                            }
                        } else if (addressFriendBean.getErrno().equals("666666")) {
                            Toast.makeText(getActivity(), "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(getActivity(), Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
                            SPUtils.getInstance().clear();
                            ActivityManager.getInstance().exit();
                            intent = new Intent(getActivity(), NewLoginActivity.class);
                            startActivity(intent);
                            getActivity().finish();
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

    @OnClick(R.id.rl_click_NewFriends)
    public void onViewClicked() {
        intent = new Intent(getActivity(), NewFriendList.class);
        startActivity(intent);
    }
}
