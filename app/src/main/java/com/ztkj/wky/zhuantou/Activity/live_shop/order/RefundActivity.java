package com.ztkj.wky.zhuantou.Activity.live_shop.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RefundActivity extends AppCompatActivity {


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
    @BindView(R.id.order_pic)
    ImageView orderPic;
    @BindView(R.id.tv_order_name)
    TextView tvOrderName;
    @BindView(R.id.tv_select_reason)
    TextView tvSelectReason;
    @BindView(R.id.go_back)
    ImageView goBack;
    @BindView(R.id.rela_select_address)
    RelativeLayout relaSelectAddress;
    @BindView(R.id.btn_save_address)
    Button btnSaveAddress;

    public static void start(Context context) {
        Intent starter = new Intent(context, RefundActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }
}
