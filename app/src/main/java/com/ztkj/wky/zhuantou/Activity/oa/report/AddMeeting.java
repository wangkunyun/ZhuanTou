package com.ztkj.wky.zhuantou.Activity.oa.report;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMeeting extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.etMeetingTitle)
    EditText etMeetingTitle;
    @BindView(R.id.clickClear)
    ImageView clickClear;
    @BindView(R.id.etMeetingContents)
    EditText etMeetingContents;
    @BindView(R.id.btnMeetingSave)
    Button btnMeetingSave;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String uid;
    private String token;
    private String TAG = "AddMeeting";
    private Intent intent;
    private String meid;
    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        ButterKnife.bind(this);
        layoutTitleTv.setText("会议记录");


        intent = getIntent();
        tag = intent.getStringExtra("tag");
        if (tag.equals("1")) {
            more.setVisibility(View.VISIBLE);
            more.setText("删除");
            btnMeetingSave.setText("修改");
            meid = intent.getStringExtra("meid");
            String title = intent.getStringExtra("title");
            String content = intent.getStringExtra("content");
            etMeetingTitle.setText(title);
            etMeetingContents.setText(content);
        }
        //获取token和id
        sharedPreferencesHelper = new SharedPreferencesHelper(this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
    }

    @OnClick({R.id.layout_back, R.id.clickClear, R.id.more, R.id.btnMeetingSave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.clickClear:
                etMeetingTitle.setText("");
                break;
            case R.id.more:
                popuinit2("会议记录删除以后将无法恢复，是否确认继续删除", "取消 ", "确定");

                break;
            case R.id.btnMeetingSave:
                if (tag.equals("1")) {
                    updateMeeting();
                } else {
                    addMeeting();
                }
                break;

        }
    }

    private void updateMeeting() {
        long time1000 = new Date().getTime() / 1000;
        OkHttpUtils.post().url(Contents.UPDATEMEETING)
                .addParams("token", token)
                .addParams("meid", meid)
                .addParams("times", time1000 + "")
                .addParams("title", etMeetingTitle.getText().toString())
                .addParams("content", etMeetingContents.getText().toString())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: ======修改会议记录=========" + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.get("errno").equals("200")) {
                        Toast.makeText(AddMeeting.this, "修改成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void delMeeting() {
        OkHttpUtils.post().url(Contents.DELMEETING)
                .addParams("token", token)
                .addParams("meid", meid)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: ======删除会议记录=========" + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.get("errno").equals("200")) {
                        Toast.makeText(AddMeeting.this, "删除成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void addMeeting() {
        long time1000 = new Date().getTime() / 1000;
        OkHttpUtils.post().url(Contents.ADDMEETING)
                .addParams("token", token)
                .addParams("uid", uid)
                .addParams("times", time1000 + "")
                .addParams("title", etMeetingTitle.getText().toString())
                .addParams("content", etMeetingContents.getText().toString())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: ======添加会议记录=========" + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.get("errno").equals("200")) {
                        Toast.makeText(AddMeeting.this, "添加成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void popuinit2(String s, String s1, final String s2) {
        View contentView = LayoutInflater.from(AddMeeting.this).inflate(R.layout.pp_telephone, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.2f);
        //下面是p里面的东西
        TextView ptextView = contentView.findViewById(R.id.ppt_tv1);
        Button pbutton = contentView.findViewById(R.id.ppt_btn1);
        Button pbutton2 = contentView.findViewById(R.id.ppt_btn2);
        ptextView.setText(s);
        pbutton.setText(s1);
        pbutton2.setText(s2);
        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        View rootview = LayoutInflater.from(AddMeeting.this).inflate(R.layout.activity_sz, null);

        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        pbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                delMeeting();

                backgroundAlpha(1f);
                window.dismiss();
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        window.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = AddMeeting.this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        AddMeeting.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        AddMeeting.this.getWindow().setAttributes(lp);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        more.setVisibility(View.GONE);
    }
}
