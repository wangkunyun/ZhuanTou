package com.ztkj.wky.zhuantou.Activity.chat.group;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetGroupName extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.etGroupName)
    EditText etGroupName;
    @BindView(R.id.btn_FinishCreate)
    Button btnFinishCreate;
    private Intent intent;
    private String TAG = "SetGroupName";
    private String phones;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String token, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_group_name);
        ButterKnife.bind(this);

        intent = getIntent();
        String list = intent.getStringExtra("list");
        phones = list.substring(1, list.length() - 1).replaceAll(" ", "");

        sharedPreferencesHelper = new SharedPreferencesHelper(this, "anhua");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        phone = (String) sharedPreferencesHelper.getSharedPreference("phone", "");


    }

    @OnClick({R.id.layout_back, R.id.btn_FinishCreate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.btn_FinishCreate:
                if (etGroupName.getText().toString().equals("")) {
                    Toast.makeText(this, "请输入群名称", Toast.LENGTH_SHORT).show();
                    return;
                }
                OkHttpUtils.post().url(Contents.CREATEGROUP)
                        .addParams("token", token)
                        .addParams("imgname", etGroupName.getText().toString())
                        .addParams("imgintroduction", "")
                        .addParams("publics", "2")
                        .addParams("membersonly", "2")
                        .addParams("allowinvites", "2")
                        .addParams("maxusers", "500")
                        .addParams("members", phones)
                        .addParams("owner", phone)
                        .addParams("notice", "")
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String errno = (String) jsonObject.get("errno");
                            if (errno.equals("200")) {
                                Toast.makeText(SetGroupName.this, "创建成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
        }
    }
}
