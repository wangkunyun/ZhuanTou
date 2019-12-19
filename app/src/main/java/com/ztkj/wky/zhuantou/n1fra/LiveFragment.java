package com.ztkj.wky.zhuantou.n1fra;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.squareup.okhttp.Request;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.enterpriseService.ParkingNavigation;
import com.ztkj.wky.zhuantou.H5.FDReadActivity;
import com.ztkj.wky.zhuantou.MyUtils.Colorstring;
import com.ztkj.wky.zhuantou.MyUtils.HeadRefreshView;
import com.ztkj.wky.zhuantou.MyUtils.LoadMoreView;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.MyAdapter9;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.BannerBean;
import com.ztkj.wky.zhuantou.bean.NearBean;
import com.ztkj.wky.zhuantou.bean.ToastBean;
import com.ztkj.wky.zhuantou.homepage.SearchActivity;
import com.ztkj.wky.zhuantou.homepage.SearchNearActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 、附近商家
 * <p>
 * A simple {@link Fragment} subclass.
 */
public class LiveFragment extends Fragment {
    @BindView(R.id.n5_banner)
    Banner n5_banner;
    @BindView(R.id.near_dingweiimg)
    ImageView nearDingweiimg;
    @BindView(R.id.near_title)
    TextView nearTitle;
    @BindView(R.id.n5_sales_volume)
    TextView n5SalesVolume;
    @BindView(R.id.n5_distance)
    TextView n5Distance;
    @BindView(R.id.near_rl)
    RelativeLayout nearRl;
    @BindView(R.id.near_rv)
    RecyclerView nearRv;
    Unbinder unbinder;
    private Intent intent, intent2;
    private String co_id, co_address;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String uid, token;
    private String banner_url = StringUtils.jiekouqianzui + "Article/banner";
    private String url = StringUtils.jiekouqianzui + "Community/businessList";
    private String first = "1";
    private List<String> imgs = new ArrayList<>();
    private List<String> strs = new ArrayList<>();
    private PullToRefreshLayout n1Cf;

    private String order_by; //根据id或者name排序
    private String order_type; //正序或者倒叙排序 正旭：ASC  倒叙： DESC
    private String TAG = "LiveFragment";

    public LiveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_n5, container, false);
        unbinder = ButterKnife.bind(this, view);
        n1Cf = view.findViewById(R.id.n1_cf);
        sharedPreferencesHelper = new SharedPreferencesHelper(getContext(), "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");

        nearRv.setHasFixedSize(true);
        nearRv.setNestedScrollingEnabled(false);

        n1Cf.setHeaderView(new HeadRefreshView(getContext()));
        n1Cf.setFooterView(new LoadMoreView(getContext()));
        n1Cf.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                n1Cf.finishRefresh();
            }

            @Override
            public void loadMore() {
                n1Cf.finishLoadMore();
            }
        });

        //设置默认排序方式
        order_by = "bu_id";
        order_type = "ASC";
        /**
         * //下拉刷新代码
         * //        n5Cf.setHeaderView(new HeadRefreshView(getContext()));
         * //        n5Cf.setFooterView(new LoadMoreView(getContext()));
         * //        n5Cf.setRefreshListener(new BaseRefreshListener() {
         * //            @Override
         * //            public void refresh() {
         * //                gi();
         * //                n5Cf.finishRefresh();
         * //            }
         * //
         * //            @Override
         * //            public void loadMore() {
         * //                gi();
         * //                n5Cf.finishLoadMore();
         * //            }
         * //        });
         */
        Banner_gi();//轮播

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferencesHelper = new SharedPreferencesHelper(getContext(), "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        if (!(sharedPreferencesHelper.contain("co_id"))) {
            sharedPreferencesHelper.put("co_id", "1");
            sharedPreferencesHelper.put("co_address", "世界侨商中心");
        }
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        co_id = (String) sharedPreferencesHelper.getSharedPreference("co_id", "");
        co_address = (String) sharedPreferencesHelper.getSharedPreference("co_address", "");
        nearTitle.setText(co_address);


        gi();
    }


    private void Banner_gi() {

        OkHttpUtils.post()
                .url(banner_url)
                .addParams("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Log.d("repres", response);
                        BannerBean bannerBean = gson.fromJson(response, BannerBean.class);

                        if (bannerBean.getErrno().equals("200")) {
                            List<BannerBean.DataBean> data = bannerBean.getData();
                            for (int i = 0; i < data.size(); i++) {
                                imgs.add(data.get(i).getBanner());
                                strs.add(data.get(i).getHref());
                            }

                            n5_banner.setIndicatorGravity(BannerConfig.CENTER);
                            n5_banner.setDelayTime(5000);
                            n5_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                            n5_banner.setImageLoader(new GlideImageLoader());
                            n5_banner.setImages(imgs);
                            n5_banner.start();
                        }
                    }
                });


    }

    private void gi() {
        OkHttpUtils.post()
                .url(url)
                .addParams("co_id", co_id)
                .addParams("token", token)
                .addParams("order_by", order_by)
                .addParams("order_type", order_type)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        NearBean nearBean = gson.fromJson(response, NearBean.class);
                        if (nearBean.getErrno().equals("200")) {
                            List<NearBean.DataBean> enterprise = nearBean.getData();
                            MyAdapter9 myAdapter9 = new MyAdapter9(enterprise, getContext());
                            nearRv.setLayoutManager(new LinearLayoutManager(getContext()));
                            nearRv.setAdapter(myAdapter9);
                        }
                    }
                });

    }

    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

    @OnClick({R.id.live_search, R.id.near_rl, R.id.n5_sales_volume, R.id.n5_distance, R.id.tv_click1, R.id.tv_click2, R.id.tv_click3, R.id.tv_click4, R.id.tv_click5, R.id.tv_click6, R.id.tv_click7, R.id.tv_click8})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.live_search:
                intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.near_rl:
                intent = new Intent(getContext(), SearchNearActivity.class);
                intent.putExtra("first", first);
                startActivity(intent);
                break;
            case R.id.n5_sales_volume:
                n5SalesVolume.setTextColor(Color.parseColor(Colorstring.t5));
                n5Distance.setTextColor(Color.parseColor(Colorstring.t6));
                order_by = "bu_id";
                order_type = "ASC";
                gi();
                break;
            case R.id.n5_distance:
                n5Distance.setTextColor(Color.parseColor(Colorstring.t5));
                n5SalesVolume.setTextColor(Color.parseColor(Colorstring.t6));
                order_by = "bu_name";
                order_type = "DESC";
                gi();
                break;
            case R.id.tv_click1:
                intent = new Intent(getContext(), FDReadActivity.class);
                intent.putExtra("h5Title", "樊登读书");
                intent.putExtra("h5Url", Contents.FDUrl);
//                startActivity(intent);
                clickUrl("1", intent);
                break;
            case R.id.tv_click2:
                intent = new Intent(getContext(), FDReadActivity.class);
                intent.putExtra("h5Title", "城乡特产");
                intent.putExtra("h5Url", Contents.CXSMKUrl);
                clickUrl("2", intent);
//                startActivity(intent);
                break;
            case R.id.tv_click3:
                Toast.makeText(getContext(), "敬请期待！！！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_click4: //停车导航
                intent = new Intent(getContext(), ParkingNavigation.class);
                startActivity(intent);
//                Toast.makeText(getContext(), "敬请期待！！！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_click5:
                Toast.makeText(getContext(), "敬请期待！！！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_click6:
                intent = new Intent(getContext(), FDReadActivity.class);
                intent.putExtra("h5Title", "车享家");
                intent.putExtra("h5Url", Contents.CARENJOYHOMEUrl);
                clickUrl("3", intent);
//                startActivity(intent);
                break;
            case R.id.tv_click7:
                intent = new Intent(getContext(), FDReadActivity.class);
                intent.putExtra("h5Title", "新租赁");
                intent.putExtra("h5Url", Contents.NEWLEASEUrl);
                clickUrl("4", intent);
//                startActivity(intent);
                break;
            case R.id.tv_click8:
                intent = new Intent(getContext(), FDReadActivity.class);
                intent.putExtra("h5Title", "积分商城");
                intent.putExtra("h5Url", Contents.JFSHOPUrl);
                clickUrl("5", intent);
//                startActivity(intent);
                break;

        }
    }

    private void clickUrl(String type, Intent intentUrl) {
        OkHttpUtils.post().url(Contents.CLICKYOUQINGLIANJIE)
                .addParams("", "")
                .addParams("types", type)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse:=======友情链接===== " + response);
                ToastBean toastBean = new Gson().fromJson(response, ToastBean.class);
                if (toastBean.getErrno().equals("200")) {
                    startActivity(intentUrl);
                }
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
//        n5_banner.startPlay();
        n5_banner.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
//        n5_banner.stopPlay();
        n5_banner.stopAutoPlay();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
