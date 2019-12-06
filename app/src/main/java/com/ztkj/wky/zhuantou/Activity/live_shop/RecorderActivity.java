package com.ztkj.wky.zhuantou.Activity.live_shop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.RecorderAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.BaseStatusBean;
import com.ztkj.wky.zhuantou.bean.RecorderBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecorderActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    String uid;
    int page = 1;
    RecorderBean recorderBean;
    List<RecorderBean.DataBean> list;
    RecorderAdapter recorderAdapter;
    @BindView(R.id.recycle_recorder)
    RecyclerView recycle_recorder;
    @BindView(R.id.relaEmpty)
    RelativeLayout relaEmpty;
    @BindView(R.id.empty)
    ImageView empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);
        ButterKnife.bind(this);
        uid = SPUtils.getInstance().getString("uid");
        empty.setImageDrawable(getResources().getDrawable(R.mipmap.empty_img_stp));
        layoutTitleTv.setText("浏览记录");
        more.setText("清空");
        recorderBean = (RecorderBean) getIntent().getSerializableExtra("recorderBean");
        more.setVisibility(View.VISIBLE);
        layoutBack.setOnClickListener(this);
        more.setOnClickListener(this);
        recorderAdapter = new RecorderAdapter(RecorderActivity.this);
        recycle_recorder.setLayoutManager(new LinearLayoutManager(RecorderActivity.this));
        recycle_recorder.setAdapter(recorderAdapter);
        initdata();
    }

    public static void start(Context context, RecorderBean recorderBean) {
        Intent starter = new Intent(context, RecorderActivity.class);
        starter.putExtra("recorderBean", recorderBean);
        context.startActivity(starter);
    }

    private void initdata() {
        if (recorderBean != null) {
            if (recorderBean.getData().size() > 0) {
                isHidden(false);
                recorderAdapter.setData(recorderBean.getData());
                recorderAdapter.notifyDataSetChanged();
            } else {
                isHidden(true);
            }
        }

    }


    private void isHidden(boolean isHidden) {
        if (isHidden) {
            recycle_recorder.setVisibility(View.GONE);
            relaEmpty.setVisibility(View.VISIBLE);
        } else {
            recycle_recorder.setVisibility(View.VISIBLE);
            relaEmpty.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.more:
                recorderAdapter.clearData();
                deleteRecorder();
                isHidden(true);
                break;
        }
    }

    private void deleteRecorder() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.deleteRecoder)
                .addParams("sf_user_id", uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        BaseStatusBean statusBean=new Gson().fromJson(response,BaseStatusBean.class);
                        if(!statusBean.getErrno().equals("200")){
                            ToastUtils.showShort(statusBean.getErrmsg());
                        }
                    }
                });
    }
}