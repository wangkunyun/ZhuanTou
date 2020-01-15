package com.ztkj.wky.zhuantou.Form;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddOriginActivity extends BaseActivity {


    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.toolbar)
    LinearLayout toolbar;
    @BindView(R.id.origin_name)
    TextView originName;
    @BindView(R.id.edi_add_origin)
    EditText ediAddOrigin;
    @BindView(R.id.name)
    RelativeLayout name;
    @BindView(R.id.origin_manager)
    TextView originManager;
    @BindView(R.id.iv_enter)
    ImageView ivEnter;
    @BindView(R.id.origin_manager_name)
    TextView originManagerName;
    @BindView(R.id.add_complete)
    TextView addComplete;

    @Override
    public void initView() {

    }

    @Override
    public void initListen() {

    }

    @Override
    public void iniData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_add_origin;
    }



    public static void start(Context context) {
        Intent starter = new Intent(context, AddOriginActivity.class);
        context.startActivity(starter);
    }

    @OnClick({R.id.layout_back, R.id.iv_enter, R.id.add_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.iv_enter:
                FormActivvity.startNo(this);
                break;
            case R.id.add_complete:


                break;
        }
    }
}
