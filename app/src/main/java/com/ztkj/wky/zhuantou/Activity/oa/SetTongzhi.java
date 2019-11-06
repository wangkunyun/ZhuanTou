package com.ztkj.wky.zhuantou.Activity.oa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.GetCompanyAnnBean;
import com.ztkj.wky.zhuantou.bean.ToastBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetTongzhi extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.etGroupName)
    EditText etGroupName;
    @BindView(R.id.btnFinish_Tongzhi)
    Button btnFinishTongzhi;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private SharedPreferencesHelper sp_create_team;
    private String uid, token, cid, Create_team_jurisdiction;
    private String TAG = "SetTongzhi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_tongzhi);
        ButterKnife.bind(this);

        sharedPreferencesHelper = new SharedPreferencesHelper(this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");

        sp_create_team = new SharedPreferencesHelper(this, "Create_team");
        cid = (String) sp_create_team.getSharedPreference("Create_team_cid", "");
        Create_team_jurisdiction = (String) sp_create_team.getSharedPreference("Create_team_jurisdiction", "");
//        Log.e(TAG, "onCreate:=========Create_team_jurisdiction========= " + Create_team_jurisdiction);
        //1是管理员
        if (Create_team_jurisdiction.equals("1")) {
            more.setVisibility(View.VISIBLE);
            btnFinishTongzhi.setVisibility(View.VISIBLE);
        }

        OkHttpUtils.post().url(Contents.GETCOMPANYANNOUNCEMENT)
                .addParams("token", token)
                .addParams("cid", cid)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
//                Log.e(TAG, "onResponse:gettongzhi " + response);
                GetCompanyAnnBean getCompanyAnnBean = new Gson().fromJson(response, GetCompanyAnnBean.class);
                String notice = getCompanyAnnBean.getData().getNotice();
                etGroupName.setText(notice);
            }
        });

    }

    @OnClick({R.id.layout_back, R.id.more, R.id.btnFinish_Tongzhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.more:
                etGroupName.setText("");
                break;
            case R.id.btnFinish_Tongzhi:
                if (etGroupName.getText().toString().isEmpty()) {
                    Toast.makeText(this, "请输入至少输入5个字", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (etGroupName.getText().toString().length() < 5) {
                    Toast.makeText(this, "请输入至少输入5个字", Toast.LENGTH_SHORT).show();
                    return;
                }
                OkHttpUtils.post().url(Contents.COMPANYANNOUNCEMENT)
                        .addParams("token", token)
                        .addParams("cid", cid)
                        .addParams("notice", etGroupName.getText().toString())
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        ToastBean toastBean = new Gson().fromJson(response, ToastBean.class);
                        if (toastBean.getErrno().equals("200")) {
                            Toast.makeText(SetTongzhi.this, toastBean.getErrmsg(), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(SetTongzhi.this, toastBean.getErrmsg(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                break;
        }
    }
}
