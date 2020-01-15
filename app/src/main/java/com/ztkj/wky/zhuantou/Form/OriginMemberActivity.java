package com.ztkj.wky.zhuantou.Form;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.OriginAdapter;
import com.ztkj.wky.zhuantou.adapter.UnOriginAdapter;
import com.ztkj.wky.zhuantou.adapter.UnOriginMemberAdapter;
import com.ztkj.wky.zhuantou.base.BaseActivity;
import com.ztkj.wky.zhuantou.bean.OriginBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OriginMemberActivity extends BaseActivity {


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
    @BindView(R.id.tv_add_menber)
    TextView tvAddMenber;
    @BindView(R.id.recyclelist)
    RecyclerView recyclelist;
    OriginBean originBean;
    List<OriginBean.DataBean.DepartmentLiseBean> beanList;
    UnOriginMemberAdapter originAdapter;
    int index;
    String cid;

    public static void start(Context context, OriginBean originBean, int index, String cid) {
        Intent starter = new Intent(context, OriginMemberActivity.class);
        starter.putExtra("origin", originBean);
        starter.putExtra("index", index);
        starter.putExtra("cid", cid);
        context.startActivity(starter);
    }

    @Override
    public void initView() {
        cid = getIntent().getStringExtra("cid");
        originBean = (OriginBean) getIntent().getSerializableExtra("origin");
        index = getIntent().getIntExtra("index", 0);
        layoutTitleTv.setText(originBean.getData().get(index).getDepartment_name());
        more.setText("设置");
        originAdapter = new UnOriginMemberAdapter(OriginMemberActivity.this);
        recyclelist.setLayoutManager(new LinearLayoutManager(OriginMemberActivity.this));
        recyclelist.setAdapter(originAdapter);
        beanList = originBean.getData().get(index).getDepartment_lise();
        originAdapter.setDepartMember(beanList);

    }

    @Override
    public void initListen() {

    }

    @Override
    public void iniData() {


    }

    @Override
    public int getLayout() {
        return R.layout.activity_origin_member;
    }


    @OnClick({R.id.layout_back, R.id.tv_add_menber})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.tv_add_menber:
                SearchOriginsActivity.start(this, cid);
                break;
        }
    }
}
