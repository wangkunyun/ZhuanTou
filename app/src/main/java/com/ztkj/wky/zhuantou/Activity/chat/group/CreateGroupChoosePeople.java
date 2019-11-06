package com.ztkj.wky.zhuantou.Activity.chat.group;

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

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.CheckBokAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.AddressBookBean;
import com.ztkj.wky.zhuantou.bean.MapBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateGroupChoosePeople extends AppCompatActivity {

    @BindView(R.id.main_cb)
    CheckBox mainCb;
    @BindView(R.id.lv)
    RecyclerView lv;
    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;

    private List<MapBean> list;
    private CheckBokAdapter checkBokAdapter;
    private SharedPreferencesHelper sharedPreferencesHelper, sp_create_team;
    private String token, cid;
    private List<AddressBookBean.DataBean> data;
    private Intent intent;

    private boolean CheckBokState = false;
    private String TAG = "CreateGroupChoosePeople";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group_choose_people);
        ButterKnife.bind(this);

        layoutTitleTv.setText("选择联系人创建");
        //解决滑动冲突
        lv.setHasFixedSize(true);
        lv.setNestedScrollingEnabled(false);
        sharedPreferencesHelper = new SharedPreferencesHelper(this, "anhua");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");

        //获取cid
        sp_create_team = new SharedPreferencesHelper(this, "Create_team");
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
                        AddressBookBean addressBookBean = new Gson().fromJson(response, AddressBookBean.class);
                        if (addressBookBean.getErrno().equals("200")) {
                            data = addressBookBean.getData();
                            //  对lv进行数据绑定
                            list = getLoadData();
                            checkBokAdapter = new CheckBokAdapter(CreateGroupChoosePeople.this, list, mainCb);
                            lv.setLayoutManager(new LinearLayoutManager(CreateGroupChoosePeople.this));
                            lv.setAdapter(checkBokAdapter);
                        }
                    }
                });
    }

    private void setListState(boolean b) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setStatus(b);
        }
        mainCb.setChecked(b);
        checkBokAdapter.notifyDataSetChanged();
    }


    public List<MapBean> getLoadData() {

        List<MapBean> list = new ArrayList<MapBean>();

        for (int i = 0; i < data.size(); i++) {
            MapBean bean = new MapBean(data.get(i).getName(), false, data.get(i).getHead(), data.get(i).getPhone());
            list.add(bean);
        }

        return list;
    }

    @OnClick({R.id.layout_back, R.id.CreateGroup_next, R.id.main_cb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.CreateGroup_next:
                ArrayList<String> intentList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getStatus()) {
                        intentList.add(list.get(i).getPhones());
                    }
                }
                intent = new Intent(CreateGroupChoosePeople.this, SetGroupName.class);
                intent.putExtra("list", intentList.toString());
                Log.e(TAG, "onViewClicked: " + intentList.toString());
                startActivity(intent);
                break;
            case R.id.main_cb:
                ArrayList<Boolean> checkStateList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    checkStateList.add(list.get(i).getStatus());
                }
                if (!CheckBokState) {
                    for (int i = 0; i < list.size(); i++) {
                        if (checkStateList.contains(true)) {
                            setListState(true);
                            CheckBokState = true;
                        } else {
                            setListState(true);
                            CheckBokState = true;
                        }
                    }

                } else {
                    for (int i = 0; i < list.size(); i++) {
                        if (checkStateList.contains(false)) {
                            setListState(true);
                            CheckBokState = true;
                        } else {
                            setListState(false);
                            CheckBokState = false;
                        }
                    }
                }
                break;
        }
    }
}
