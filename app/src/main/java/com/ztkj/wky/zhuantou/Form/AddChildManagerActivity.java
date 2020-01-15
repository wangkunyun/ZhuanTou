package com.ztkj.wky.zhuantou.Form;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class AddChildManagerActivity extends BaseActivity {


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
    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_super_name)
    TextView tvSuperName;
    @BindView(R.id.iv_enter)
    ImageView ivEnter;
    @BindView(R.id.ll_manager_form)
    RelativeLayout llManagerForm;
    @BindView(R.id.add_child_origin)
    TextView addChildOrigin;

    public static void start(Context context) {
        Intent starter = new Intent(context, AddChildManagerActivity.class);
        context.startActivity(starter);
    }


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
        return R.layout.activity_add_child_manager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.layout_back, R.id.iv_enter, R.id.add_child_origin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.iv_enter:
                AddLimitActivity.start(AddChildManagerActivity.this);
                break;
            case R.id.add_child_origin:

                break;
        }
    }
}
