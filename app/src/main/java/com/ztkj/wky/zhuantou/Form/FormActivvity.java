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

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.superrtc.mediamanager.SRWebSocket;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MvpBase.Iview;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.OriginAdapter;
import com.ztkj.wky.zhuantou.adapter.UnOriginAdapter;
import com.ztkj.wky.zhuantou.base.BaseActivity;
import com.ztkj.wky.zhuantou.base.BaseMvpActivity;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.OriginBean;
import com.ztkj.wky.zhuantou.bean.SuperBean;
import com.ztkj.wky.zhuantou.bean.UnOriginBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.ztkj.wky.zhuantou.base.Contents.getSuperMember;

public class FormActivvity extends BaseActivity implements OriginAdapter.Originlisten {


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
    @BindView(R.id.tv_add_member)
    TextView tvAddMember;
    @BindView(R.id.tv_bumen)
    TextView tvBumen;
    @BindView(R.id.recycle_list_bumen)
    RecyclerView recycleListBumen;
    @BindView(R.id.recycle_list_unconect)
    RecyclerView recycleListUnconect;
    @BindView(R.id.tv_super)
    TextView tvSuper;
    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_super_name)
    TextView tvSuperName;
    String cid;
    OriginBean originBean;
    List<OriginBean.DataBean> beanList;
    UnOriginBean unOriginBean;
    List<UnOriginBean.DataBean> unOrginList;
    UnOriginAdapter unOriginAdapter;
    SuperBean superBean;
    @BindView(R.id.add_child_origin)
    TextView add_child_origin;
    @BindView(R.id.ll_manager_form)
    LinearLayout ll_manager_form;

    public static void start(Context context, String cid) {
        Intent starter = new Intent(context, FormActivvity.class);
        starter.putExtra("cid", cid);
        context.startActivity(starter);
    }

    public static void startNo(Context context) {
        Intent starter = new Intent(context, FormActivvity.class);
        context.startActivity(starter);
    }
    @Override
    public void initView() {
        cid = getIntent().getStringExtra("cid");
        layoutTitleTv.setText("组织架构");
        more.setText("管理");
        if (cid != null) {
            getOrginalList();
            getUnConnectOrgin();
            getSuperMember();

        }
    }

    private void getSuperMember() {
        OkHttpUtils.post().url(Contents.SHOPBASE + getSuperMember)
                .addParams("cid", cid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            superBean = new Gson().fromJson(response, SuperBean.class);
                            Glide.with(FormActivvity.this).load(superBean.getData().getHead()).into(avatar);
                            tvName.setText(superBean.getData().getUsername());
                        }
                    }
                });
    }

    @Override
    public void initListen() {
        originAdapter = new OriginAdapter(FormActivvity.this);
        recycleListBumen.setLayoutManager(new LinearLayoutManager(FormActivvity.this));
        recycleListBumen.setAdapter(originAdapter);
        unOriginAdapter = new UnOriginAdapter(FormActivvity.this);
        recycleListUnconect.setLayoutManager(new LinearLayoutManager(FormActivvity.this));
        recycleListUnconect.setAdapter(unOriginAdapter);
    }

    @Override
    public void iniData() {


    }

    private void getUnConnectOrgin() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.unConnectOrigin)
                .addParams("cid", cid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            unOriginBean = new Gson().fromJson(response, UnOriginBean.class);
                            if (unOriginBean.getData() != null) {
                                unOrginList = unOriginBean.getData();
                                unOriginAdapter.setData(unOrginList);
                            }
                        }
                    }
                });
    }

    OriginAdapter originAdapter;

    private void getOrginalList() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.originalLis)
                .addParams("cid", cid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            originBean = new Gson().fromJson(response, OriginBean.class);
                            if (originBean.getData() != null) {
                                beanList = originBean.getData();
                                originAdapter.setData(beanList);
                            }
                        }
                    }
                });
    }

    @Override
    public int getLayout() {
        return R.layout.form_activity_ayout;
    }


    @OnClick({R.id.layout_back, R.id.tv_add_member, R.id.more,R.id.ll_manager_form,R.id.add_child_origin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.tv_add_member:
                SearchOriginsActivity.start(FormActivvity.this, cid);
                break;
            case R.id.more:
                add_child_origin.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_manager_form:
                AddChildManagerActivity.start(this);
                break;
            case R.id.add_child_origin:
                AddOriginActivity.start(this);
                break;
        }
    }

    @Override
    public void index(int pos) {
        OriginMemberActivity.start(FormActivvity.this, originBean, pos, cid);
    }
}
