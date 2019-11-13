package com.ztkj.wky.zhuantou.Activity.live_shop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.ztkj.wky.zhuantou.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopClassActivity extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.bigsearch_edt)
    RelativeLayout bigsearchEdt;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.clickSynthesize)
    TextView clickSynthesize;
    @BindView(R.id.clickSales)
    TextView clickSales;
    @BindView(R.id.clickPrice)
    TextView clickPrice;
    @BindView(R.id.clickFiltrate)
    TextView clickFiltrate;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.shopBanner)
    CardView shopBanner;
    @BindView(R.id.reView)
    RecyclerView reView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_class);
        ButterKnife.bind(this);



    }

    @OnClick({R.id.layout_back, R.id.bigsearch_edt, R.id.more, R.id.clickSynthesize, R.id.clickSales, R.id.clickPrice, R.id.clickFiltrate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.bigsearch_edt:
                break;
            case R.id.more:
                break;
            case R.id.clickSynthesize:
                break;
            case R.id.clickSales:
                break;
            case R.id.clickPrice:
                break;
            case R.id.clickFiltrate:
                break;
        }
    }
}
