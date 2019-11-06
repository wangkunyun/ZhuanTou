package com.ztkj.wky.zhuantou.Activity.oa.punch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.FreeCheckingPeopleAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.AddressBookBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FreeCheckingPeopleActivity extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.rl_click_addFreeChecking)
    RelativeLayout rlClickAddFreeChecking;
    @BindView(R.id.re)
    RecyclerView re;
    private String TAG = "FreeCheckingPeopleActivity";
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_checking_people);
        ButterKnife.bind(this);
        layoutTitleTv.setText("免考勤人员设置");
        requestFreeAdress();
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestFreeAdress();

    }

    private void requestFreeAdress() {
        OkHttpUtils.post().url(Contents.NOPUNCHINADRESS)
                .addParams("token", SPUtils.getInstance().getString("token"))
                .addParams("cid", SPUtils.getInstance().getString("cid"))
                .addParams("phone", SPUtils.getInstance().getString("phone"))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                AddressBookBean addressBookBean = new Gson().fromJson(response, AddressBookBean.class);
                List<AddressBookBean.DataBean> data = addressBookBean.getData();
                FreeCheckingPeopleAdapter freeCheckingPeopleAdapter
                        = new FreeCheckingPeopleAdapter(FreeCheckingPeopleActivity.this, data);
                freeCheckingPeopleAdapter.notifyDataSetChanged();
                re.setLayoutManager(new LinearLayoutManager(FreeCheckingPeopleActivity.this));
                re.setAdapter(freeCheckingPeopleAdapter);
            }
        });

    }

    @OnClick({R.id.layout_back, R.id.rl_click_addFreeChecking})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.rl_click_addFreeChecking:
                intent = new Intent(FreeCheckingPeopleActivity.this, CheckingPeopleActivity.class);
                startActivity(intent);
                break;
        }
    }
}
