package com.ztkj.wky.zhuantou.Activity.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.BillListAdapter;
import com.ztkj.wky.zhuantou.bean.BillListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ztkj.wky.zhuantou.base.Contents.BILLLIST;

public class BillList extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.reBill)
    RecyclerView reBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_list);
        ButterKnife.bind(this);
        layoutTitleTv.setText("我的账单");

        request();
    }

    private void request() {
        OkHttpUtils.post().url(BILLLIST)
                .addParams("token", SPUtils.getInstance().getString("token"))
                .addParams("uid", SPUtils.getInstance().getString("uid"))
                .addParams("page", "1")
                .addParams("count", "10000")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                BillListBean billListBean = GsonUtil.gsonToBean(response, BillListBean.class);
                List<BillListBean.DataBean> data = billListBean.getData();
                BillListAdapter billListAdapter = new BillListAdapter(BillList.this, data);
                reBill.setLayoutManager(new LinearLayoutManager(BillList.this));
                reBill.setAdapter(billListAdapter);
            }
        });

    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }
}
