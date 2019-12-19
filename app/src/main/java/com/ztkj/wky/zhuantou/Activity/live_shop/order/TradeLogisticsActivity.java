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

import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.TradeLogisticsBean;

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
    private String TAG = "TradeLogisticsActivity";
    private int height;
    private List<TradeLogisticsBean.DataBeanX.DataBean> Tradedata;
    private int TradeSize = 2;
    private boolean isClick = true;

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
        reView.setLayoutManager(new GridLayoutManager(this, 2));
        request();


//        TradeAdapter tradeAdapter = new TradeAdapter();
//        reTrade.setLayoutManager(new LinearLayoutManager(TradeLogisticsActivity.this));
//        reTrade.setAdapter(tradeAdapter);
    }

    private void request() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.getLogistics)
                .addParams("sso_courier_number", "4302557768029")
                .addParams("sso_express_number", "yunda")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                TradeLogisticsBean tradeLogisticsBean = GsonUtil.gsonToBean(response, TradeLogisticsBean.class);
                if (tradeLogisticsBean.getErrno().equals("200")) {
                    TradeLogisticsBean.DataBeanX data = tradeLogisticsBean.getData();
                    Tradedata = data.getData();
                    TradeAdapter tradeAdapter = new TradeAdapter();
                    reTrade.setLayoutManager(new LinearLayoutManager(TradeLogisticsActivity.this));
                    reTrade.setAdapter(tradeAdapter);
                    //设置高度
                    reTrade.post(new Runnable() {
                        @Override
                        public void run() {
                            height = reTrade.getHeight();
                            Log.e(TAG, "run: " + height);
                            ViewGroup.LayoutParams layoutParams = underColor.getLayoutParams();
                            Log.e(TAG, "onCreate: " + height);
                            layoutParams.height = height - 85;
                            underColor.setLayoutParams(layoutParams);
                        }
                    });
                }
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
                finish();
                break;
            case R.id.click_showMoreMessage:
                if (isClick) {
                    TradeSize = Tradedata.size();
                    isClick = false;
                    request();
                } else {
                    TradeSize = 2;
                    isClick = true;
                    request();
                }
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
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.tv_trade.setText(Tradedata.get(i).getContext());
            if (i != 0) {
                viewHolder.tv_bgStatic.setBackgroundResource(R.drawable.bg_trade_grey);
            }
        }

        @Override
        public int getItemCount() {
            return TradeSize;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView tv_trade, tv_bgStatic;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_trade = itemView.findViewById(R.id.tv_trade);
                tv_bgStatic = itemView.findViewById(R.id.tv_bgStatic);
            }
        }
    }
}
