package com.ztkj.wky.zhuantou.n1fra;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.maning.updatelibrary.InstallUtils;
import com.squareup.okhttp.Request;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.enterpriseService.ParkingNavigation;
import com.ztkj.wky.zhuantou.Activity.enterpriseService.TakeOutCodeActivity;
import com.ztkj.wky.zhuantou.MyUtils.Colorstring;
import com.ztkj.wky.zhuantou.MyUtils.HeadRefreshView;
import com.ztkj.wky.zhuantou.MyUtils.LoadMoreView;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.MyAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.BannerBean;
import com.ztkj.wky.zhuantou.bean.EwmBean;
import com.ztkj.wky.zhuantou.bean.FenLeiBean;
import com.ztkj.wky.zhuantou.bean.RvBean1;
import com.ztkj.wky.zhuantou.bean.UpdateBean;
import com.ztkj.wky.zhuantou.bean.WeatherBean;
import com.ztkj.wky.zhuantou.homepage.SearchActivity;
import com.ztkj.wky.zhuantou.isMy.JiLuActivity;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;
import com.ztkj.wky.zhuantou.messagefra.HuaTiFragment;
import com.ztkj.wky.zhuantou.messagefra.WuYeFragment;
import com.ztkj.wky.zhuantou.messagefra.ZiXunFragment;
import com.ztkj.wky.zhuantou.messagefra2.Blank1Fragment;
import com.ztkj.wky.zhuantou.messagefra2.Blank2Fragment;
import com.ztkj.wky.zhuantou.messagefra2.Blank3Fragment;
import com.ztkj.wky.zhuantou.messagefra2.Blank4Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    @BindView(R.id.n1_search)
    ImageView n1Search;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.jd_sy)
    RadioButton jdSy;
    @BindView(R.id.jd_xiahua1)
    TextView jdXiahua1;
    @BindView(R.id.jd_sy2)
    RadioButton jdSy2;
    @BindView(R.id.jd_xiahua2)
    TextView jdXiahua2;
    @BindView(R.id.n1_group)
    RadioGroup n1Group;
    Unbinder unbinder;
    @BindView(R.id.jdfr)
    FrameLayout jdfr;
    @BindView(R.id.n1_cf)
    PullToRefreshLayout n1Cf;
    @BindView(R.id.n1_work_time)
    TextView n1WorkTime;
    @BindView(R.id.n1_work_rl)
    LinearLayout n1WorkRl;
    @BindView(R.id.tv_weather)
    TextView tvWeather;
    //    @BindView(R.id.img_weather)
//    ImageView imgWeather;
    @BindView(R.id.cardview_banner)
    CardView cardviewBanner;
    @BindView(R.id.helloName)
    TextView helloName;
    @BindView(R.id.click_scan)
    ImageView clickScan;
    @BindView(R.id.tv_localCity)
    TextView tvLocalCity;
    @BindView(R.id.jd_sy3)
    RadioButton jdSy3;
    @BindView(R.id.jd_xiahua3)
    TextView jdXiahua3;

    private String url = StringUtils.jiekouqianzui + "Article/banner";
    private String url2 = StringUtils.jiekouqianzui + "User/userInfo";
    private String url3 = StringUtils.jiekouqianzui + "Passageway/passagewayQrCode";
    private String url4 = StringUtils.jiekouqianzui + "Article/articleClass";
    private String url5 = StringUtils.jiekouqianzui + "Article/articleList";
    private SharedPreferencesHelper sharedPreferencesHelper, sp_create_team;
    private String co_id, co_address;
    private int position = 0;
    private List<String> imgs = new ArrayList<>();
    private List<String> strs = new ArrayList<>();
    private HuaTiFragment huaTiFragment;
    private ZiXunFragment ziXunFragment;
    private WuYeFragment wuYeFragment;
    private Intent intent;
    private String uid;
    private String token, realname;
    private String TAG = "HomeFragment";
    public static final int REQUEST_CODE = 111;
    private static final String savePath = "/sdcard/小砖开门/";
    private static final String saveFileName = savePath + "小砖开门.apk";
    private int page = 2;


    public HomeFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_n1, container, false);
        unbinder = ButterKnife.bind(this, view);
        sharedPreferencesHelper = new SharedPreferencesHelper(getContext(), "anhua");
        sp_create_team = new SharedPreferencesHelper(getActivity(), "Create_team");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");


        updateApk();


        gi();
        n1Cf.setHeaderView(new HeadRefreshView(getContext()));
        n1Cf.setFooterView(new LoadMoreView(getContext()));
        n1Cf.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                setTabSelection(position);
                page = 2;
                n1Cf.finishRefresh();
            }

            @Override
            public void loadMore() {
                switch (position) {
                    case 0:
                        articleclass(0, Blank1Fragment.blank1Rv);
                        break;
                    case 1:
                        articleclass(1, Blank2Fragment.blank2Rv);
                        break;
                    case 3:
                        articleclass(2, Blank3Fragment.blank3Rv);
                        break;
                    case 4:
                        articleclass(3, Blank4Fragment.blank4Rv);
                        break;
                }
                n1Cf.finishLoadMore();
            }

            private void articleclass(int i, RecyclerView Rv) {
                OkHttpUtils.post()
                        .url(url4)
                        .addParams("token", token)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {
                            }

                            @Override
                            public void onResponse(String response) {
                                final Gson gson = new Gson();
                                FenLeiBean fenLeiBean = gson.fromJson(response, FenLeiBean.class);
                                if (fenLeiBean.getErrno().equals("200")) {
                                    String cl_id = fenLeiBean.getData().get(0).getSubordinate().get(i).getCl_id();
                                    String cl_zhu_id = fenLeiBean.getData().get(0).getCl_id();

                                    OkHttpUtils.post()
                                            .url(url5)
                                            .addParams("", "")
                                            .addParams("onecless", cl_zhu_id)
                                            .addParams("twocless", cl_id)
                                            .addParams("page", page + "")
                                            .addParams("count", "10")
                                            .build()
                                            .execute(new StringCallback() {
                                                @Override
                                                public void onError(Request request, Exception e) {
                                                }

                                                @Override
                                                public void onResponse(String response) {
                                                    Gson gson1 = new Gson();
                                                    RvBean1 rvBean1 = gson1.fromJson(response, RvBean1.class);

                                                    if (rvBean1.getErrno().equals("200")) {
                                                        page = page + 1;
                                                        List<RvBean1.DataBean.ListBean> data = rvBean1.getData().getList();
                                                        if (data.size() == 0) {
                                                            Toast.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                                                            return;
                                                        }
                                                        MyAdapter myAdapter = new MyAdapter(data, getContext());
                                                        myAdapter.notifyDataSetChanged();
                                                        Rv.setLayoutManager(new LinearLayoutManager(getContext()));
                                                        Rv.setAdapter(myAdapter);
                                                    }
                                                }
                                            });
                                }

                            }
                        });
            }
        });

        position = 0;
        setTabSelection(position);
        return view;
    }


    private void SetHello() {
        realname = (String) sharedPreferencesHelper.getSharedPreference("realname", "");
        Log.e(TAG, "onCreateView: " + realname);
        if (!realname.isEmpty() && realname.length() >= 2) {
            helloName.setText("Hi~" + realname.substring(realname.length() - 2));
        } else {
            helloName.setText("Hi~" + realname);
        }
    }

    private void updateApk() {
        OkHttpUtils.post()
                .url(Contents.UPDATEAPK)
                .addParams("", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.d("finqq", response);
                        Gson gson = new Gson();
                        UpdateBean updateBean = gson.fromJson(response, UpdateBean.class);
                        if ("200".equals(updateBean.getErrno())) {
                            if (Contents.localVersion.equals(updateBean.getData().getAndroid_number())) {
//                                update_popuinit("当前版本已是最新版本", "取消 ", "确定", updateBean.getData().getAndroid_address());
                            } else {
                                update_popuinit("发现已知bug，是否要现在更新？", "取消 ", "立即更新", updateBean.getData().getAndroid_address());
                            }
                        }

                    }
                });
    }

    private void request_weather(String uid) {
        OkHttpUtils.post()
                .url(Contents.WEATHER)
                .addParams("", "")
                .addParams("uid", uid)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
//                Log.e(TAG, "onError: Weather+++:" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: Weather+++:" + response);
                WeatherBean weatherBean = new Gson().fromJson(response, WeatherBean.class);

                if (weatherBean.getErrno().equals("200")) {
                    WeatherBean.DataBean data = weatherBean.getData();
                    String s = "℃";
                    String weather = data.getWeather() + " " + data.getTemperature() + s;
                    tvWeather.setText(weather);
                    sp_create_team.put("Create_team_jurisdiction", weatherBean.getData().getJurisdiction());
                    sp_create_team.put("Create_team_cid", weatherBean.getData().getCid());
                    sp_create_team.put("Create_team_name", weatherBean.getData().getTeam().getTeam_name());
                    sp_create_team.put("Create_team_logo", weatherBean.getData().getTeam().getTeam_logo());
                }
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferencesHelper = new SharedPreferencesHelper(getContext(), "anhua");
        sp_create_team = new SharedPreferencesHelper(getActivity(), "Create_team");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        SetHello();
        if (StringUtils.isEmpty(uid)) {
            request_weather("");
        } else {
            request_weather(uid);
        }

        OkHttpUtils.post()
                .url(url3)
                .addParams("uid", uid)
                .addParams("token", (String) sharedPreferencesHelper.getSharedPreference("token", ""))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        EwmBean ewmBean = gson.fromJson(response, EwmBean.class);
                        Log.d("lue1", response);
                        if (ewmBean.getErrno().equals("200")) {
                            n1WorkTime.setText("今日工作时长已超过" + ewmBean.getData().getDuration() + "%的人");
//                            n1WorkRl.setEnabled(true);
                        } else {
                            n1WorkTime.setText("今日工作时长已超过0%的人");
//                            n1WorkRl.setEnabled(false);
                        }
                    }
                });
    }

    private void gi() {

        OkHttpUtils.post()
                .url(url)
                .addParams("", "")
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

                            banner.setIndicatorGravity(BannerConfig.CENTER);
                            banner.setDelayTime(5000);
                            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                            banner.setImageLoader(new GlideImageLoader());
                            banner.setImages(imgs);
                            banner.start();
                        } else {

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

    public void setTabSelection(int position) {
        //记录position
        this.position = position;
        //更改底部导航栏按钮状态
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
//        transaction.hide(shouYeFragment).hide(caiFuFragment).hide(jdFragment).hide(woDeFragment).commit();
        transaction = getChildFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                jdSy.setTextColor(Color.parseColor(Colorstring.lanse));
                jdSy2.setTextColor(Color.parseColor(Colorstring.t6));
                jdSy3.setTextColor(Color.parseColor(Colorstring.t6));
                TextPaint p = jdSy.getPaint();
                p.setFakeBoldText(true);
                TextPaint p2 = jdSy2.getPaint();
                p2.setFakeBoldText(false);
                TextPaint p3 = jdSy3.getPaint();
                p3.setFakeBoldText(false);
                jdXiahua1.setAlpha(1f);
                jdXiahua2.setAlpha(0f);
                jdXiahua3.setAlpha(0f);
                jdSy.setChecked(true);
//                if (ziXunFragment == null) {
                ziXunFragment = new ZiXunFragment();
                transaction.add(R.id.jdfr, ziXunFragment);
//                } else {
//                    transaction.show(ziXunFragment);
//                }
                break;
            case 1:
                jdSy.setTextColor(Color.parseColor(Colorstring.t6));
                jdSy2.setTextColor(Color.parseColor(Colorstring.lanse));
                jdSy3.setTextColor(Color.parseColor(Colorstring.t6));
                TextPaint p4 = jdSy.getPaint();
                p4.setFakeBoldText(false);
                TextPaint p5 = jdSy2.getPaint();
                p5.setFakeBoldText(true);
                TextPaint p6 = jdSy3.getPaint();
                p6.setFakeBoldText(false);
                jdXiahua1.setAlpha(0f);
                jdXiahua2.setAlpha(1f);
                jdXiahua3.setAlpha(0f);
                jdSy2.setChecked(true);
//                if (huaTiFragment == null) {
                huaTiFragment = new HuaTiFragment();
                transaction.add(R.id.jdfr, huaTiFragment);
//                } else {
//                    transaction.show(huaTiFragment);
//                }
                break;
            case 2:
                jdSy.setTextColor(Color.parseColor(Colorstring.t6));
                jdSy2.setTextColor(Color.parseColor(Colorstring.t6));
                jdSy3.setTextColor(Color.parseColor(Colorstring.lanse));
                TextPaint p7 = jdSy.getPaint();
                p7.setFakeBoldText(false);
                TextPaint p8 = jdSy2.getPaint();
                p8.setFakeBoldText(false);
                TextPaint p9 = jdSy3.getPaint();
                p9.setFakeBoldText(true);
                jdXiahua1.setAlpha(0f);
                jdXiahua2.setAlpha(0f);
                jdXiahua3.setAlpha(1f);
                jdSy3.setChecked(true);
//                if (huaTiFragment == null) {
                wuYeFragment = new WuYeFragment();
                transaction.add(R.id.jdfr, wuYeFragment);
//                } else {
//                    transaction.show(huaTiFragment);
//                }
                break;
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {

        if (huaTiFragment != null)
            transaction.hide(huaTiFragment);
        if (ziXunFragment != null)
            transaction.hide(ziXunFragment);
        if (wuYeFragment != null) {
            transaction.hide(wuYeFragment);
        }
        transaction.commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
//        banner.startPlay();
        banner.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
//        banner.stopPlay();
        banner.stopAutoPlay();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void update_popuinit(String s, String s1, final String s2, final String s3) {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pp_telephone, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.2f);
        //下面是p里面的东西
        TextView ptextView = contentView.findViewById(R.id.ppt_tv1);
        Button pbutton = contentView.findViewById(R.id.ppt_btn1);
        Button pbutton2 = contentView.findViewById(R.id.ppt_btn2);
        ptextView.setText(s);
        pbutton.setText(s1);
        pbutton2.setText(s2);
        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_sz, null);

        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        pbutton2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("https://www.pgyer.com/hjNa");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

                backgroundAlpha(1f);
                window.dismiss();
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        window.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }

    private void getin(final String s2) {
//        安装APK
        /**
         * 安装APK工具类
         * @param activity       上下文
         * @param filePath      文件路径
         * @param callBack      安装界面成功调起的回调
         */
        InstallUtils.installAPK(getActivity(), saveFileName, new InstallUtils.InstallCallBack() {
            @Override
            public void onSuccess() {
                //onSuccess：表示系统的安装界面被打开
                //防止用户取消安装，在这里可以关闭当前应用，以免出现安装被取消
                Toast.makeText(getActivity(), "正在安装程序", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(Exception e) {
                Toast.makeText(getActivity(), "安装失败:" + e.toString(), Toast.LENGTH_SHORT).show();
                InstallUtils.installAPKWithBrower(Objects.requireNonNull(getActivity()), s2);
            }
        });
    }


    //服务 拨打电话
    private void popuinit() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.pp_telephone, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.2f);
        //下面是p里面的东西
        TextView ptextView1 = contentView.findViewById(R.id.ppt_tv1);
        TextView ptextView = contentView.findViewById(R.id.ppt_tv2);
        Button pbutton = contentView.findViewById(R.id.ppt_btn1);
        Button pbutton2 = contentView.findViewById(R.id.ppt_btn2);
        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        View rootview = LayoutInflater.from(getContext()).inflate(R.layout.fragment_n1, null);
        ptextView1.setText("");
        ptextView.setText("“一键服务”将致电给本APP客服，由客服帮您预约各项服务。是否拨打电话 5338-9498");
        pbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        pbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                window.dismiss();
                intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + "53389498");
                intent.setData(data);
                startActivity(intent);
            }
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });

        window.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);
    }

    @OnClick({R.id.n1_search, R.id.jd_sy, R.id.jd_sy2, R.id.jd_sy3, R.id.n1_work_rl, R.id.click_scan, R.id.click_takeOutCode, R.id.click_Parking, R.id.click_ladderControl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.n1_work_rl:
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getContext(), NewLoginActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(getContext(), JiLuActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.n1_search:
                intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.jd_sy:
                position = 0;
                setTabSelection(position);
                break;
            case R.id.jd_sy2:
                position = 1;
                setTabSelection(position);
                break;
            case R.id.jd_sy3:
                position = 2;
                setTabSelection(position);
                break;
            case R.id.click_scan: //扫一扫
                intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.click_takeOutCode:
                intent = new Intent(getActivity(), TakeOutCodeActivity.class);
                startActivity(intent);
                break;
            case R.id.click_Parking:
                intent = new Intent(getContext(), ParkingNavigation.class);
                startActivity(intent);
                break;
            case R.id.click_ladderControl:
                intent = new Intent(getActivity(), TakeOutCodeActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);

                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

    }
}
