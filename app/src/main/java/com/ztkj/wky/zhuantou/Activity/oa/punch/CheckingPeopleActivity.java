package com.ztkj.wky.zhuantou.Activity.oa.punch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.CheckBokAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.AddressBookBean;
import com.ztkj.wky.zhuantou.bean.MapBean;
import com.ztkj.wky.zhuantou.bean.ToastBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckingPeopleActivity extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.lv)
    RecyclerView lv;
    @BindView(R.id.main_cb)
    CheckBox mainCb;

    private List<MapBean> list;
    private CheckBokAdapter checkBokAdapter;
    private SharedPreferencesHelper sharedPreferencesHelper, sp_create_team;
    private String token, cid;
    private List<AddressBookBean.DataBean> data;
    private Intent intent;
    private String TAG = "CheckingPeopleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking_people);
        ButterKnife.bind(this);
        layoutTitleTv.setText("免考勤设置");
        more.setVisibility(View.VISIBLE);
        more.setText("确定");

        sharedPreferencesHelper = new SharedPreferencesHelper(this, "anhua");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");

        //获取cid
        sp_create_team = new SharedPreferencesHelper(this, "Create_team");
        cid = (String) sp_create_team.getSharedPreference("Create_team_cid", "");
        request_addressBook();
        request_addressBook();
    }

    private void request_addressBook() {

        OkHttpUtils.post()
                .url(Contents.PUNCHINADRESS)
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
                            //  对lv进行数据绑定
                            list = getLoadData();
                            checkBokAdapter = new CheckBokAdapter(CheckingPeopleActivity.this, list, mainCb);
                            lv.setLayoutManager(new LinearLayoutManager(CheckingPeopleActivity.this));
                            lv.setAdapter(checkBokAdapter);
                        }
                    }
                });
    }

    public List<MapBean> getLoadData() {
        List<MapBean> list = new ArrayList<MapBean>();
        for (int i = 0; i < data.size(); i++) {
            MapBean bean = new MapBean(data.get(i).getName(), false, data.get(i).getHead(), data.get(i).getPhone());
            list.add(bean);
        }

        return list;
    }


    @OnClick({R.id.layout_back, R.id.more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.more:
                ArrayList<String> updateList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getStatus()) {
                        updateList.add(list.get(i).getPhones());
                    }
                }
                String s = updateList.toString();
                String phones = s.substring(1, s.length() - 1).replaceAll(" ", "");
//                Log.e(TAG, "onViewClicked: " + updateList.toString());
//                Log.e(TAG, "onViewClicked: " + SPUtils.getInstance().getString("cid"));
                OkHttpUtils.post().url(Contents.NOPUNCHINPEOPLE)
                        .addParams("phone", SPUtils.getInstance().getString("phone"))//SPUtils.getInstance().getString("phone")
                        .addParams("friend_phone", phones)
                        .addParams("cid", SPUtils.getInstance().getString("cid"))
                        .addParams("type", "1")
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        ToastBean toastBean = GsonUtil.gsonToBean(response, ToastBean.class);
                        if (toastBean.getErrno().equals("200")) {
                            Toast.makeText(CheckingPeopleActivity.this, toastBean.getErrmsg(), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(CheckingPeopleActivity.this, toastBean.getErrmsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (int i = 0; i < list.size(); i++) {
            list.remove(i);
        }
    }
}
