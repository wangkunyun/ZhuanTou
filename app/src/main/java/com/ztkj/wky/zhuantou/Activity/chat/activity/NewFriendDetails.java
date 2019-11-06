package com.ztkj.wky.zhuantou.Activity.chat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.ToastBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewFriendDetails extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.img_newFriendHead)
    ImageView imgNewFriendHead;
    @BindView(R.id.tv_newFriendName)
    TextView tvNewFriendName;
    @BindView(R.id.rl_click_peopleDetails)
    RelativeLayout rlClickPeopleDetails;
    @BindView(R.id.btn_newFriend_refuse)
    Button btnNewFriendRefuse;
    @BindView(R.id.btn_newFriend_agree)
    Button btnNewFriendAgree;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String token, phone;
    private Intent intent;
    private String friend_phone, head, name, company, job;
    private String TAG = "NewFriendDetails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend_details);
        ButterKnife.bind(this);
        layoutTitleTv.setText("好友申请");
        sharedPreferencesHelper = new SharedPreferencesHelper(this, "anhua");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        phone = (String) sharedPreferencesHelper.getSharedPreference("phone", "");
        intent = getIntent();
        head = intent.getStringExtra("head");
        name = intent.getStringExtra("name");
        friend_phone = intent.getStringExtra("friend_phone");
        company = intent.getStringExtra("company");
        job = intent.getStringExtra("job");

        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(96);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
        Glide.with(this).load(head)
                .apply(options).into(imgNewFriendHead);
        tvNewFriendName.setText(name);
    }

    @OnClick({R.id.layout_back, R.id.rl_click_peopleDetails, R.id.btn_newFriend_refuse, R.id.btn_newFriend_agree})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.rl_click_peopleDetails:
                Intent intentPersonDetails = new Intent(NewFriendDetails.this, PersonalDetails.class);
                intentPersonDetails.putExtra("friendTag", "2");
                intentPersonDetails.putExtra("name", name);
                intentPersonDetails.putExtra("head", head);
                intentPersonDetails.putExtra("phone", friend_phone);
                intentPersonDetails.putExtra("company", company);
                intentPersonDetails.putExtra("job", job);
                startActivity(intentPersonDetails);
                break;
            case R.id.btn_newFriend_refuse:
                requestFriend(Contents.AGREENEWFRIEND, "2");
                break;
            case R.id.btn_newFriend_agree:
                requestFriend(Contents.AGREENEWFRIEND, "1");
                break;
        }
    }

    private void requestFriend(String url, String types) {
        Log.e(TAG, "requestFriend: " + url + "==" + types + "===" + token + "==" + phone + "===" + friend_phone);
        OkHttpUtils.post().url(url)
                .addParams("token", token)
                .addParams("phone", phone)
                .addParams("friend_phone", friend_phone)
                .addParams("types", types)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                ToastBean toastBean = new Gson().fromJson(response, ToastBean.class);
                String errno = toastBean.getErrno();
                if (errno.equals("200")) {
                    Toast.makeText(NewFriendDetails.this, toastBean.getErrmsg(), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(NewFriendDetails.this, toastBean.getErrmsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
