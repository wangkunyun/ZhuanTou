package com.ztkj.wky.zhuantou.Activity.live_shop.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.ResuseWayAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RefundActivity extends AppCompatActivity implements View.OnClickListener {


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

    @BindView(R.id.btn_save_address)
    Button btnSaveAddress;
    @BindView(R.id.rela_select_address)
    RelativeLayout rela_select_address;

    public static void start(Context context) {
        Intent starter = new Intent(context, RefundActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund);
        ButterKnife.bind(this);
        rela_select_address.setOnClickListener(this);
    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rela_select_address:
                popuCoupon();
                break;
        }
    }

    private void popuCoupon() {
        View contentView = LayoutInflater.from(RefundActivity.this).inflate(R.layout.pp_shop_refuse_reason, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.6f);
        //下面是p里面的东西
        ListView lv = contentView.findViewById(R.id.lv);
        Button pp1_btn = contentView.findViewById(R.id.pp1_btn);
        ResuseWayAdapter shopParamAdapter = new ResuseWayAdapter(RefundActivity.this);
        lv.setAdapter(shopParamAdapter);
        View rootview = LayoutInflater.from(RefundActivity.this).inflate(R.layout.activity_shop_detail, null);
        setViewDow(contentView, rootview);
        pp1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });

    }


    PopupWindow window;

    private void setViewDow(View view, View rootview) {
        window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        window.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }
}
