package com.ztkj.wky.zhuantou.Activity.chat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.SearchFriendBean;
import com.ztkj.wky.zhuantou.landing.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class SearchFriend extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.etFriendNum)
    EditText etFriendNum;
    @BindView(R.id.btn_Search)
    TextView btnSearch;
    @BindView(R.id.img_SearchHead)
    ImageView imgSearchHead;
    @BindView(R.id.tv_SearchName)
    TextView tvSearchName;
    @BindView(R.id.btn_Lin)
    LinearLayout btnLin;
    @BindView(R.id.tv_SearchNoFriend)
    TextView tvSearchNoFriend;

    private String token, phone, uid;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String TAG = "SearchFriend";
    private Intent intent, intentPerson;
    private SearchFriendBean.DataBean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friend);
        ButterKnife.bind(this);
        etFriendNum.setInputType(InputType.TYPE_CLASS_PHONE);//电话
        sharedPreferencesHelper = new SharedPreferencesHelper(SearchFriend.this, "anhua");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        phone = (String) sharedPreferencesHelper.getSharedPreference("phone", "");

    }

    @OnClick({R.id.back, R.id.btn_Search, R.id.btn_Lin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_Search:
                request();
                break;
            case R.id.btn_Lin:
                intentPerson = new Intent(SearchFriend.this, PersonalDetails.class);
                intentPerson.putExtra("friendTag", data.getTypes());
                intentPerson.putExtra("name", data.getNickname());
                intentPerson.putExtra("head", data.getHead());
                intentPerson.putExtra("phone", data.getPhone());
                intentPerson.putExtra("company", data.getCname());
                intentPerson.putExtra("job", data.getPosition());
                startActivity(intentPerson);
                break;
        }
    }

    private void request() {
        if (etFriendNum.getText().toString().equals("")) {
            Toast.makeText(this, "请输入查找人手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpUtils.post()
                .url(Contents.SEARCHFRIEND)
                .addParams("token", token)
                .addParams("phone", etFriendNum.getText().toString().trim())
                .addParams("myPhone", phone)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                SearchFriendBean searchFriendBean = new Gson().fromJson(response, SearchFriendBean.class);

                if (searchFriendBean.getErrno().equals("200")) {
                    data = searchFriendBean.getData();
                    btnLin.setVisibility(View.VISIBLE);
                    tvSearchNoFriend.setVisibility(View.GONE);
                    //设置图片圆角角度
                    RoundedCorners roundedCorners = new RoundedCorners(180);
                    RequestOptions options = RequestOptions.bitmapTransform(roundedCorners)
                            .error(R.drawable.head_portrait);
                    Glide.with(SearchFriend.this).load(data.getHead())
                            .apply(options)
                            .into(imgSearchHead);

                    tvSearchName.setText(data.getNickname());

                } else if (searchFriendBean.getErrno().equals("666666")) {
                    Toast.makeText(SearchFriend.this, "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                    JPushInterface.deleteAlias(SearchFriend.this, Integer.parseInt(uid));
                    sharedPreferencesHelper.clear();
                    ActivityManager.getInstance().exit();
                    intent = new Intent(SearchFriend.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    btnLin.setVisibility(View.GONE);
                    tvSearchNoFriend.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        btnLin.setVisibility(View.GONE);
    }
}
