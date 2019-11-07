package com.ztkj.wky.zhuantou.Activity.oa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.AddressBookAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.AddressBookBean;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class AddressBook extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.add_friends)
    TextView addFriends;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.company_name)
    TextView companyName;
    @BindView(R.id.btn_manage_company)
    TextView btnManageCompany;
    @BindView(R.id.manage_company)
    LinearLayout manageCompany;
    @BindView(R.id.re)
    RecyclerView re;

    private SharedPreferencesHelper sharedPreferencesHelper, sp_create_team;
    private String uid, token, cid, team_name, jurisdiction;
    private Intent intent;
    private String TAG = "AddressBook";

    private List<AddressBookBean.DataBean> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book);
        ButterKnife.bind(this);

        sharedPreferencesHelper = new SharedPreferencesHelper(AddressBook.this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");

        //获取cid
        sp_create_team = new SharedPreferencesHelper(AddressBook.this, "Create_team");
        cid = (String) sp_create_team.getSharedPreference("Create_team_cid", "");
        jurisdiction = (String) sp_create_team.getSharedPreference("Create_team_jurisdiction", "");
        team_name = (String) sp_create_team.getSharedPreference("Create_team_name", "");
        Log.e(TAG, "onCreate: jurisdiction :" + jurisdiction);
        if (team_name.equals("0")) {
            companyName.setText("暂无团队");
        } else {
            companyName.setText(team_name);
            if (jurisdiction.equals("1")) {
                btnManageCompany.setVisibility(View.VISIBLE);
            } else {
                manageCompany.setEnabled(false);
            }
        }
        request_addressBook();
    }

    private void request_addressBook() {

        OkHttpUtils.post()
                .url(Contents.COMPANYBOOK)
                .addParams("token", token)
                .addParams("cid", cid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        AddressBookBean addressBookBean = new Gson().fromJson(response, AddressBookBean.class);

                        if (addressBookBean.getErrno().equals("200")) {
                            data = addressBookBean.getData();
                            AddressBookAdapter addressBookAdapter = new AddressBookAdapter(data, AddressBook.this);
                            re.setLayoutManager(new LinearLayoutManager(AddressBook.this));
                            re.setAdapter(addressBookAdapter);

                        } else if (addressBookBean.getErrno().equals("666666")) {
                            Toast.makeText(AddressBook.this, "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(AddressBook.this, Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
                            ActivityManager.getInstance().exit();
                            intent = new Intent(AddressBook.this, NewLoginActivity.class);
                            startActivity(intent);
//                            getActivity().finish();
                        } else {

                        }
                    }
                });


    }

    @OnClick({R.id.back, R.id.add_friends, R.id.manage_company})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.add_friends:
                break;
            case R.id.manage_company:
                intent = new Intent(AddressBook.this, ManageCompany.class);
                startActivity(intent);
                break;
        }
    }
}
