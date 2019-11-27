package com.ztkj.wky.zhuantou.Activity.live_shop.order;
/**
 * 作者：wky
 * 功能描述：交易物流
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.Activity.live_shop.ShopDetailActivity;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.JsonBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TradeLogisticsActivity extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.reTrade)
    RecyclerView reTrade;
    @BindView(R.id.click_showMoreMessage)
    TextView clickShowMoreMessage;
    @BindView(R.id.reView)
    RecyclerView reView;
    @BindView(R.id.underColor)
    View underColor;
    @BindView(R.id.toolbar)
    LinearLayout toolbar;
    @BindView(R.id.rl_out)
    RelativeLayout rlOut;
    private String TAG = "activity_trade_logistics";
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_logistics);
        ButterKnife.bind(this);
        layoutTitleTv.setText("交易物流");
        reView.setHasFixedSize(true);
        reView.setNestedScrollingEnabled(false);
        reTrade.setHasFixedSize(true);
        reTrade.setNestedScrollingEnabled(false);
        JsonBean jsonBean = GsonUtil.gsonToBean(Contents.Json, JsonBean.class);
        List<JsonBean.ListBean> list = jsonBean.getList();
//        LiveShopAdapter liveShopAdapter = new LiveShopAdapter(this, list);
        reView.setLayoutManager(new GridLayoutManager(this, 2));
//        reView.setAdapter(liveShopAdapter);
        TradeAdapter tradeAdapter = new TradeAdapter();
        reTrade.setLayoutManager(new LinearLayoutManager(this));
        reTrade.setAdapter(tradeAdapter);
        reTrade.post(new Runnable() {
            @Override
            public void run() {
                height = reTrade.getHeight();
                Log.e(TAG, "run: " + height);
                ViewGroup.LayoutParams layoutParams = underColor.getLayoutParams();
                Log.e(TAG, "onCreate: " + height);
                layoutParams.height = height - 72;
                underColor.setLayoutParams(layoutParams);
            }
        });

    }


    public static void start(Context context) {
        Intent starter = new Intent(context, TradeLogisticsActivity.class);
        context.startActivity(starter);
    }

    @OnClick({R.id.layout_back, R.id.click_showMoreMessage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                Intent intent = new Intent(TradeLogisticsActivity.this, ShopDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.click_showMoreMessage:

                break;
        }
    }

    class TradeAdapter extends RecyclerView.Adapter<TradeAdapter.ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(TradeLogisticsActivity.this).inflate(R.layout.item_layout_trade, viewGroup, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHoder, int i) {

        }

        @Override
        public int getItemCount() {
            return 2;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}
