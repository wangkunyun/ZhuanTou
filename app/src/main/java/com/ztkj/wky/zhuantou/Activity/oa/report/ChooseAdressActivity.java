package com.ztkj.wky.zhuantou.Activity.oa.report;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.ChooseAddressAdapter;
import com.ztkj.wky.zhuantou.adapter.TeamScaleAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.AddressBookBean;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class ChooseAdressActivity extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.add_friends)
    TextView addFriends;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.re)
    RecyclerView re;

    private SharedPreferencesHelper sharedPreferencesHelper, listApprover_head_uid, sp_create_team;
    private String uid, token, cid;
    private Intent intent, approverIntent;
    private String TAG = "ChooseAdressActivity";

    private List<AddressBookBean.DataBean> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_adress);
        ButterKnife.bind(this);

        sharedPreferencesHelper = new SharedPreferencesHelper(ChooseAdressActivity.this, "anhua");
        listApprover_head_uid = new SharedPreferencesHelper(ChooseAdressActivity.this, "listApprover");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");

        //获取cid
        sp_create_team = new SharedPreferencesHelper(ChooseAdressActivity.this, "Create_team");
        cid = (String) sp_create_team.getSharedPreference("Create_team_cid", "");

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
                            ChooseAddressAdapter chooseAddressAdapter = new ChooseAddressAdapter(data, ChooseAdressActivity.this);
                            chooseAddressAdapter.setOnItemClickListener(new TeamScaleAdapter.OnItemClickListener() {
                                @Override
                                public void onClick(int position) {
                                    listApprover_head_uid.put("approver_head", data.get(position).getHead());
                                    listApprover_head_uid.put("approver_uid", data.get(position).getUid());
                                    finish();
                                }

                                @Override
                                public void onLongClick(int position) {

                                }
                            });
                            re.setLayoutManager(new LinearLayoutManager(ChooseAdressActivity.this));
                            re.setAdapter(chooseAddressAdapter);

                        } else if (addressBookBean.getErrno().equals("666666")) {
                            Toast.makeText(ChooseAdressActivity.this, "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(ChooseAdressActivity.this, Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
                            ActivityManager.getInstance().exit();
                            intent = new Intent(ChooseAdressActivity.this, NewLoginActivity.class);
                            startActivity(intent);
//                            getActivity().finish();
                        } else {

                        }
                    }
                });


    }


    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
