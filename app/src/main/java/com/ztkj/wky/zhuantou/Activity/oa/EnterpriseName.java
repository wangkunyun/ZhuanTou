package com.ztkj.wky.zhuantou.Activity.oa;
/***
 * 企业名称
 *
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.StringUtils;
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


public class EnterpriseName extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.save)
    TextView save;
    @BindView(R.id.et_enterprise_name)
    EditText etEnterpriseName;
    @BindView(R.id.clear_name)
    ImageView clearName;
    @BindView(R.id.team_scale)
    RelativeLayout teamScale;
    private String TAG = "EnterpriseName";
    private String token;
    private String cid;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private SharedPreferencesHelper sp_create_team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_name);
        ButterKnife.bind(this);
        //获取token和id
        sharedPreferencesHelper = new SharedPreferencesHelper(this, "anhua");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        //获取cid
        sp_create_team = new SharedPreferencesHelper(this, "Create_team");
        cid = (String) sp_create_team.getSharedPreference("Create_team_cid", "");
    }

    @OnClick({R.id.back, R.id.save, R.id.clear_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.save:
                if (etEnterpriseName.getText().toString().isEmpty()) {
                    Toast.makeText(this, "请输入团队名称", Toast.LENGTH_SHORT).show();
                    return;
                }
                updateName();
                break;
            case R.id.clear_name:
                etEnterpriseName.setText("");
                break;
        }
    }

    private void updateName() {
        OkHttpUtils.post()
                .url(Contents.CREATETEAM)
                .addParams("team_name", etEnterpriseName.getText().toString())
                .addParams("token", token)
                .addParams("cid", cid)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String errno = (String) jsonObject.get("errno");
                    Log.e(TAG, "onResponse: " + errno);
                    if (StringUtils.equals(errno, "200")) {
                        sp_create_team.put("Create_team_name", etEnterpriseName.getText().toString());
                        Toast.makeText(EnterpriseName.this, "修改成功", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
