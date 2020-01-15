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

public class AddLimitActivity extends BaseActivity {


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
    @BindView(R.id.origin_manager)
    TextView originManager;
    @BindView(R.id.iv_enter)
    ImageView ivEnter;
    @BindView(R.id.origin_manager_name)
    TextView originManagerName;
    @BindView(R.id.rela_manager)
    RelativeLayout relaManager;
    @BindView(R.id.origin_manager_limit)
    TextView originManagerLimit;
    @BindView(R.id.iv_enter_limit)
    ImageView ivEnterLimit;
    @BindView(R.id.origin_manager_name_limit)
    TextView originManagerNameLimit;
    @BindView(R.id.rela_manager_limit)
    RelativeLayout relaManagerLimit;
    @BindView(R.id.add_confirm)
    TextView addConfirm;

    public static void start(Context context) {
        Intent starter = new Intent(context, AddLimitActivity.class);
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
        return R.layout.activity_add_limit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.layout_back, R.id.rela_manager, R.id.rela_manager_limit, R.id.add_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.rela_manager:
                FormActivvity.startNo(this);
                break;
            case R.id.rela_manager_limit:

                break;
            case R.id.add_confirm:

                break;
        }
    }
}
