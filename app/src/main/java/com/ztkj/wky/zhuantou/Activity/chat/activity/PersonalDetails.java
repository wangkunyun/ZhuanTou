package com.ztkj.wky.zhuantou.Activity.chat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.chat.ECChatAcitivty;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.ToastBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.ztkj.wky.zhuantou.R.drawable.head_portrait;

public class PersonalDetails extends AppCompatActivity {

    @BindView(R.id.imgPersonDetailsBg)
    ImageView imgPersonDetailsBg;
    @BindView(R.id.tvPersonDetailsName)
    TextView tvPersonDetailsName;
    @BindView(R.id.tvPersonDetailsPhone)
    TextView tvPersonDetailsPhone;
    @BindView(R.id.tvPersonDetailsCompanyName)
    TextView tvPersonDetailsCompanyName;
    @BindView(R.id.tvPersonDetailsJob)
    TextView tvPersonDetailsJob;
    @BindView(R.id.btn_PersonDetailsAddFriend)
    Button btnPersonDetailsAddFriend;
    @BindView(R.id.btn_PersonDetailsSendMessage)
    Button btnPersonDetailsSendMessage;
    @BindView(R.id.btn_PersonDetailsSendMessage2)
    Button btnPersonDetailsSendMessage2;
    @BindView(R.id.imgPersonDetailsHead)
    ImageView imgPersonDetailsHead;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.imgPersonDetailsSangedain)
    ImageView imgPersonDetailsSangedain;
    private Intent intent;
    private String name, head, company, phone, job, friendTag, tv_StrAddOrDel;
    private String token, myphone, uid;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String TAG = "PersonalDetails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        ButterKnife.bind(this);

        sharedPreferencesHelper = new SharedPreferencesHelper(this, "anhua");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        myphone = (String) sharedPreferencesHelper.getSharedPreference("phone", "");

        intent = getIntent();
        friendTag = intent.getStringExtra("friendTag");
        name = intent.getStringExtra("name");
        head = intent.getStringExtra("head");
        company = intent.getStringExtra("company");
        phone = intent.getStringExtra("phone");
        job = intent.getStringExtra("job");
        //判断从哪进来，设置按钮的显示
        if (StringUtils.isEmpty(friendTag)) {
            btnPersonDetailsSendMessage2.setVisibility(View.GONE);
            tv_StrAddOrDel = "添加好友";
        } else if (friendTag.equals("1")) { //从团队进来 隐藏两边的发消息和加好友   显示中间的发消息
            btnPersonDetailsAddFriend.setVisibility(View.GONE);
            btnPersonDetailsSendMessage.setVisibility(View.GONE);
            tv_StrAddOrDel = "解除好友关系";
        } else {
            btnPersonDetailsSendMessage2.setVisibility(View.GONE);
            tv_StrAddOrDel = "添加好友";
        }

        tvPersonDetailsName.setText(name);
        tvPersonDetailsCompanyName.setText(company);
        tvPersonDetailsPhone.setText(phone);
        if (job.isEmpty()) {
            tvPersonDetailsJob.setText("暂无");
        } else {
            tvPersonDetailsJob.setText(job);
        }
        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(180);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners)
                .error(head_portrait);
        //设置图片圆角角度
//        RoundedCorners roundedCorners2 = new RoundedCorners(10);
        RequestOptions requestOptions = new RequestOptions();
        RequestOptions options2 = RequestOptions.bitmapTransform(new BlurTransformation(PersonalDetails.this, 23, 4));
        Glide.with(this).load(head)
                .apply(options)
                .into(imgPersonDetailsHead);
        Glide.with(this).load(head)
                .into(imgPersonDetailsBg);


    }

    @OnClick({R.id.btn_PersonDetailsAddFriend, R.id.btn_PersonDetailsSendMessage, R.id.btn_PersonDetailsSendMessage2, R.id.back, R.id.imgPersonDetailsSangedain})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.imgPersonDetailsSangedain://添加好友或者解除关系

                popuinit(view);
                break;
            case R.id.btn_PersonDetailsAddFriend://加好友
//                requestAddFriend(Contents.AddFriend);
                intent = new Intent(PersonalDetails.this, SayHello.class);
                intent.putExtra("phone", phone);
                intent.putExtra("token", token);
                intent.putExtra("myphone", myphone);
                startActivity(intent);
                break;
            case R.id.btn_PersonDetailsSendMessage://不是好友状态下发消息
//                Toast.makeText(this, "敬请期待！！！", Toast.LENGTH_SHORT).show();

                Intent sendIntent = new Intent(PersonalDetails.this, ECChatAcitivty.class);
                sendIntent.putExtra(EaseConstant.EXTRA_USER_ID, phone);
                sendIntent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat);
                startActivity(sendIntent);
                break;
            case R.id.btn_PersonDetailsSendMessage2://是好友状态下发消息
//                Toast.makeText(this, "敬请期待！！！", Toast.LENGTH_SHORT).show();

                Intent sendIntent2 = new Intent(PersonalDetails.this, ECChatAcitivty.class);
                sendIntent2.putExtra(EaseConstant.EXTRA_USER_ID, phone);
                sendIntent2.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat);
                startActivity(sendIntent2);
                break;
        }
    }

    private void requestAddFriend(String url) {
        OkHttpUtils.post().url(url)
                .addParams("token", token)
                .addParams("phone", myphone)
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
                    Toast.makeText(PersonalDetails.this, toastBean.getErrmsg(), Toast.LENGTH_SHORT).show();
//                    //添加好友成功 解除非好友关系 修改UI
//                    btnPersonDetailsAddFriend.setVisibility(View.GONE);
//                    btnPersonDetailsSendMessage.setVisibility(View.GONE);
//                    btnPersonDetailsSendMessage2.setVisibility(View.VISIBLE);
//                    tv_StrAddOrDel = "解除好友关系";
                } else {
                    Toast.makeText(PersonalDetails.this, toastBean.getErrmsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void popuinit(View v) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.pp2, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.7f);
        //下面是p里面的东西
        TextView pa2_tv = contentView.findViewById(R.id.pp2_tv);
        pa2_tv.setText(tv_StrAddOrDel);
        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        pa2_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                window.dismiss();
                //判断从哪进来，设置按钮的显示
                if (StringUtils.isEmpty(friendTag)) {
                    intent = new Intent(PersonalDetails.this, SayHello.class);
                    intent.putExtra("phone", phone);
                    intent.putExtra("token", token);
                    intent.putExtra("myphone", myphone);
                    startActivity(intent);
                } else if (friendTag.equals("1")) { //从团队进来 隐藏两边的发消息和加好友   显示中间的发消息
                    requestAddFriend(Contents.DELFriend);
                } else {
                    requestAddFriend(Contents.AddFriend);
                }
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        window.showAsDropDown(v);
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = PersonalDetails.this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        PersonalDetails.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        PersonalDetails.this.getWindow().setAttributes(lp);
    }


}
