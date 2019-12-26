package com.ztkj.wky.zhuantou.Activity.mine.jf;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConversionShopDetails extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.toolbar)
    LinearLayout toolbar;
    @BindView(R.id.img_conversionPic)
    ImageView imgConversionPic;
    @BindView(R.id.tv_conversionTitle)
    TextView tvConversionTitle;
    @BindView(R.id.tv_conversionPrice)
    TextView tvConversionPrice;
    @BindView(R.id.tv_conversionIntroduce)
    TextView tvConversionIntroduce;
    @BindView(R.id.btn_conversion)
    Button btnConversion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_shop_details);
        ButterKnife.bind(this);
        layoutTitleTv.setText("我的砖头");

    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ConversionShopDetails.class);
        context.startActivity(starter);
    }

    @OnClick({R.id.layout_back, R.id.btn_conversion})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                break;
            case R.id.btn_conversion:
                popuChoose();
                break;
        }
    }

    private void popuChoose() {
        View contentView = LayoutInflater.from(ConversionShopDetails.this).inflate(R.layout.pp_conversion, null);
        View rootview = LayoutInflater.from(ConversionShopDetails.this).inflate(R.layout.activity_conversion_shop_details, null);

        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.6f);
        setViewDow(contentView, rootview);
    }

    private void setViewDow(View view, View rootview) {
        PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
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
