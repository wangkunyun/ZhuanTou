package com.ztkj.wky.zhuantou.Activity.enterpriseService;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhouwei.library.CustomPopWindow;
import com.google.gson.Gson;
import com.hyphenate.easeui.utils.SharedPreferencesHelper;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.GouWuCheAdapter;
import com.ztkj.wky.zhuantou.adapter.SendWaterShopAdapter;
import com.ztkj.wky.zhuantou.adapter.SendWaterShopListAdapter;
import com.ztkj.wky.zhuantou.bean.GouWuCheBean;
import com.ztkj.wky.zhuantou.bean.SendWaterBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ztkj.wky.zhuantou.base.Contents.SERVERSSHOP;

public class SendWater extends AppCompatActivity {


    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.tv_sendWaterLocal)
    TextView tvSendWaterLocal;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.reShopList)
    RecyclerView reShopList;
    @BindView(R.id.reShop)
    RecyclerView reShop;
    @BindView(R.id.popViewMeasure)
    LinearLayout popViewMeasure;
    @BindView(R.id.click_lin_shopPop)
    LinearLayout clickLinShopPop;
    @BindView(R.id.layout_sendWater)
    RelativeLayout layoutSendWater;

    private String TAG = "SendWater";
    private ArrayList<String> typeList, waterList;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private SharedPreferencesHelper sp_create_team;
    private String uid, token, cid;
    private SendWaterShopListAdapter sendWaterShopListAdapter;
    private SendWaterShopAdapter sendWaterShopAdapter;
    private GouWuCheAdapter gouWuCheAdapter;
    private ArrayList<GouWuCheBean> gouWuCheList;
    private Intent intent;
    private String eid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_water);
        gouWuCheList = new ArrayList<>();
        intent = getIntent();
        eid = intent.getStringExtra("eid");
        initView();
        initEven();
    }

    private void initEven() {
        reShop.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                //判断是当前layoutManager是否为LinearLayoutManager
                // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    //获取第一个可见view的位置
                    int firstItemPosition = linearManager.findFirstVisibleItemPosition();
                    List<SendWaterBean.DataBean.ShopGoodsBean.GoodsBean> rightList = sendWaterShopAdapter.getData();
                    String name = rightList.get(firstItemPosition).getName();
                    List<SendWaterBean.DataBean.ShopGoodsBean> data = sendWaterShopListAdapter.getData();

                    for (int i = 0; i < data.size(); i++) {
                        if (name.equals(data.get(i).getName())) {
                            data.get(i).setType(1);
                            sendWaterShopListAdapter.notifyDataSetChanged();
                        } else {
                            data.get(i).setType(0);
                        }
                    }
                }
            }
        });
        sendWaterShopListAdapter.setOnItemClickListener(new SendWaterShopListAdapter.ClickLinsh() {
            @Override
            public void click(int position, SendWaterBean.DataBean.ShopGoodsBean shopGoodsBean) {
                List<SendWaterBean.DataBean.ShopGoodsBean> data = sendWaterShopListAdapter.getData();
                if (data == null) {
                    return;
                }
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).setType(0);
                }
                data.get(position).setType(1);
                sendWaterShopListAdapter.notifyDataSetChanged();
                List<SendWaterBean.DataBean.ShopGoodsBean.GoodsBean> rightList = sendWaterShopAdapter.getData();
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < rightList.size(); i++) {
                    if (data.get(i).getName().equals(data.get(position).getName())) {
                        list.add(i);
                        return;
                    }
                }
                if (list != null || list.size() != 0) {
                    reShop.scrollToPosition(list.get(0));
                    LinearLayoutManager mLayoutManager =
                            (LinearLayoutManager) reShop.getLayoutManager();
                    mLayoutManager.scrollToPositionWithOffset(position, 0);
                }

            }
        });

        sendWaterShopAdapter.setOnItemClickListener(new SendWaterShopAdapter.ClickLinsh() {
            @Override
            public void click(int position, SendWaterBean.DataBean.ShopGoodsBean.GoodsBean waterList) {
//                Toast.makeText(SendWater.this, "敬请期待", Toast.LENGTH_SHORT).show();
                //购物车逻辑 先注释掉
                GouWuCheBean gouWuCheBean = new GouWuCheBean(waterList.getEcid(),
                        waterList.getName(), waterList.getMoney(),
                        waterList.getImage(), 1);
                if (gouWuCheList.size() == 0) {
                    gouWuCheList.add(gouWuCheBean);
                } else {
                    for (int i = 0; i < gouWuCheList.size(); i++) {
                        if (gouWuCheList.get(i).getEcid().equals(waterList.getEcid())) {
                            int num = gouWuCheList.get(i).getNum() + 1;
                            gouWuCheList.get(i).setNum(num);
                        }
                    }
                }


            }
        });
    }

    private void initView() {
        ButterKnife.bind(this);
        layoutTitleTv.setVisibility(View.GONE);
        sharedPreferencesHelper = new SharedPreferencesHelper(this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");

        sp_create_team = new SharedPreferencesHelper(this, "Create_team");
        cid = (String) sp_create_team.getSharedPreference("Create_team_cid", "");
        //=====================请求数据==企业服务商城商品====================
        requestData();
        //实例化适配器
        //实例化适配器  列表和商品
        sendWaterShopListAdapter = new SendWaterShopListAdapter(this);
        reShopList.setLayoutManager(new LinearLayoutManager(this));
        reShopList.setAdapter(sendWaterShopListAdapter);

        sendWaterShopAdapter = new SendWaterShopAdapter(this);
        reShop.setLayoutManager(new LinearLayoutManager(this));
        reShop.setAdapter(sendWaterShopAdapter);

    }

    private void requestData() {
        Log.e(TAG, "requestData:======token=====eid====== " + token + "=========" + eid);
        OkHttpUtils.post().url(SERVERSSHOP)
                .addParams("token", token)
                .addParams("eid", eid)
                .addParams("types", "1")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: ========企业服务商城商品=======" + response);
                SendWaterBean sendWaterBean = new Gson().fromJson(response, SendWaterBean.class);
                SendWaterBean.DataBean data = sendWaterBean.getData();

                List<SendWaterBean.DataBean.ShopGoodsBean> shopGoods = data.getShopGoods();

                List<SendWaterBean.DataBean.ShopGoodsBean.GoodsBean> goods = new ArrayList<>();
                if (!shopGoods.isEmpty()) {
                    shopGoods.get(0).setType(1);
                    for (int i = 1; i < shopGoods.size(); i++) {
                        shopGoods.get(i).setType(0);
                    }
                    for (int i = 0; i < shopGoods.size(); i++) {
                        List<SendWaterBean.DataBean.ShopGoodsBean.GoodsBean> goods1 = shopGoods.get(i).getGoods();
                        if (goods1 == null) {
                            return;
                        }
                        for (int j = 0; j < goods1.size(); j++) {
                            goods.add(goods1.get(j));
                        }
                    }
                }


                //左边的列表
                sendWaterShopListAdapter.setTypeList(shopGoods);
                //右边的列表
                sendWaterShopAdapter.setWaterList(goods);

            }
        });
    }


    private void handleListView(View contentView) {

    RecyclerView re_popList = contentView.findViewById(R.id.re_popList);
//        gouWuCheAdapter.notifyDataSetChanged();
    gouWuCheAdapter = new GouWuCheAdapter(SendWater.this, gouWuCheList);
        re_popList.setLayoutManager(new LinearLayoutManager(this));
//        re_popList.setAdapter(gouWuCheAdapter);
        gouWuCheAdapter.setOnItemClickListener(new GouWuCheAdapter.ClickAdd() {
        @Override
        public void click(int position, GouWuCheBean gouWuCheList) {

        }
    });
}
    @OnClick({R.id.layout_back, R.id.click_lin_shopPop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.click_lin_shopPop:
                int measuredHeight = popViewMeasure.getMeasuredHeight();
                View contentView = LayoutInflater.from(this).inflate(R.layout.layout_poplist, null);
                //处理popWindow 显示内容
                handleListView(contentView);
                //创建并显示popWindow
                CustomPopWindow mListPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                        .setView(contentView)
                        .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                        .setBgDarkAlpha(0.7f) // 控制亮度
                        .size(ViewGroup.LayoutParams.MATCH_PARENT, 600)//显示大小
                        .create()
//                .showAsDropDown(clickLinShopPop, Gravity.TOP, 0, 0)
                        .showAtLocation(clickLinShopPop, Gravity.BOTTOM, 0, measuredHeight);
                break;
        }
    }
}
