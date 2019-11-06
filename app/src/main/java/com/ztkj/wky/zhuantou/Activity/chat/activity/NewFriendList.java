package com.ztkj.wky.zhuantou.Activity.chat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.NewFriendAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.AgreeFriendListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewFriendList extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.re)
    RecyclerView re;
    private Intent intent;
    private String token, phone, uid;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String TAG = "NewFriendList";
    private List<AgreeFriendListBean.DataBean> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend_list);
        ButterKnife.bind(this);
        layoutTitleTv.setText("新的朋友");

        sharedPreferencesHelper = new SharedPreferencesHelper(this, "anhua");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        phone = (String) sharedPreferencesHelper.getSharedPreference("phone", "");


        request_applyFriend();
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
                AgreeFriendListBean agreeFriendListBean = new Gson().fromJson(response, AgreeFriendListBean.class);
                data = agreeFriendListBean.getData();
                NewFriendAdapter newFriendAdapter = new NewFriendAdapter(NewFriendList.this, data);
                re.setLayoutManager(new LinearLayoutManager(NewFriendList.this));
                re.setAdapter(newFriendAdapter);
            }
        });
    }


    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }
}
