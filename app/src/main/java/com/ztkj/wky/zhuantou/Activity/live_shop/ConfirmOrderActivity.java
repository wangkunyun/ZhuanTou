package com.ztkj.wky.zhuantou.Activity.live_shop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.ConfimOrderAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfirmOrderActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.confirm_list)
    RecyclerView confirm_list;
    ConfimOrderAdapter confimOrderAdapter;
    @BindView(R.id.rela_address)
    LinearLayout rela_address;
    @BindView(R.id.view_divider)
    View view_divider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        ButterKnife.bind(this);
        layoutTitleTv.setText("确认订单");
        view_divider.setVisibility(View.GONE);
        layoutBack.setOnClickListener(this);
        confimOrderAdapter = new ConfimOrderAdapter(ConfirmOrderActivity.this);
        confirm_list.setLayoutManager(new LinearLayoutManager(ConfirmOrderActivity.this));
        confirm_list.setAdapter(confimOrderAdapter);
        confirm_list.setHasFixedSize(true);
        confirm_list.setNestedScrollingEnabled(false);
        rela_address.setOnClickListener(this);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ConfirmOrderActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.rela_address:
                CreateAddressActivity.start(ConfirmOrderActivity.this);
                break;
        }
    }
}
