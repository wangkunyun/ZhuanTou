package com.ztkj.wky.zhuantou.Activity.chat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.ToastBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SayHello extends AppCompatActivity {

    @BindView(R.id.finish)
    TextView finish;
    @BindView(R.id.etSayHello)
    EditText etSayHello;
    @BindView(R.id.sendRequest)
    Button sendRequest;
    private Intent intent;
    private String TAG = "SayHello";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_say_hello);
        ButterKnife.bind(this);
        intent = getIntent();
        Log.e(TAG, "requestAddFriend: " + etSayHello.getText().toString());
    }

    @OnClick({R.id.finish, R.id.sendRequest})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finish:
                finish();
                break;
            case R.id.sendRequest:
                requestAddFriend();
                break;
        }
    }

    private void requestAddFriend() {

        String token = intent.getStringExtra("token");
        String phone = intent.getStringExtra("phone");
        String myphone = intent.getStringExtra("myphone");
        Log.e(TAG, "requestAddFriend: 00" + token + phone + myphone);
        OkHttpUtils.post().url(Contents.AddFriend)
                .addParams("token", token)
                .addParams("phone", myphone)
                .addParams("sayHello", etSayHello.getText().toString())
                .addParams("friend_phone", phone)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                ToastBean toastBean = new Gson().fromJson(response, ToastBean.class);
                if (toastBean.getErrno().equals("200")) {
                    Toast.makeText(SayHello.this, toastBean.getErrmsg(), Toast.LENGTH_SHORT).show();
                    finish();
//                    //添加好友成功 解除非好友关系 修改UI
//                    btnPersonDetailsAddFriend.setVisibility(View.GONE);
//                    btnPersonDetailsSendMessage.setVisibility(View.GONE);
//                    btnPersonDetailsSendMessage2.setVisibility(View.VISIBLE);
//                    tv_StrAddOrDel = "解除好友关系";
                } else {
                    Toast.makeText(SayHello.this, toastBean.getErrmsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
