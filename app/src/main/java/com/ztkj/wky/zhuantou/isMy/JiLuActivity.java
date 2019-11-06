package com.ztkj.wky.zhuantou.isMy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.DateUtil;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.MyUtils.WheelView;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.MyAdapter5;
import com.ztkj.wky.zhuantou.bean.DakaBean;
import com.ztkj.wky.zhuantou.landing.LoginActivity;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class JiLuActivity extends AppCompatActivity {

    @BindView(R.id.jilu_goback)
    ImageView jiluGoback;
    @BindView(R.id.jilu_month)
    TextView jiluMonth;
    @BindView(R.id.jilu_headimg)
    ImageView jiluHeadimg;
    @BindView(R.id.jilu_name)
    TextView jiluName;
    @BindView(R.id.jilu_wuyong1)
    TextView jiluWuyong1;
    @BindView(R.id.jilu_wuyong2)
    TextView jiluWuyong2;
    @BindView(R.id.jilu_day)
    TextView jiluDay;
    @BindView(R.id.jilu_time)
    TextView jiluTime;
//    @BindView(R.id.jilu_cf)
//    PullToRefreshLayout n1Cf;
    @BindView(R.id.jilu_rv)
    RecyclerView jiluRv;
    private String url = StringUtils.jiekouqianzui + "User/punchCardRecord";
    //    private Calendar ca = Calendar.getInstance();
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String uid,token;
    private String head;
    private String username;
    //    private int  mYear = ca.get(Calendar.YEAR);
//    private int  mMonth = ca.get(Calendar.MONTH);
//    private int  mDay = ca.get(Calendar.DAY_OF_MONTH);
    ArrayList<String> data = new ArrayList<>();
    private Calendar calendar = Calendar.getInstance();
    private String month1 = calendar.get(Calendar.MONTH) + 1 + "";
    private String month = calendar.get(Calendar.MONTH) + 1 + "";
    private String year = calendar.get(Calendar.YEAR) + "";
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ji_lu);
        ButterKnife.bind(this);
        ActivityManager.getInstance().addActivity(this);
        sharedPreferencesHelper = new SharedPreferencesHelper(JiLuActivity.this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token","");

//        head = (String) sharedPreferencesHelper.getSharedPreference("head", "");
//        username = (String) sharedPreferencesHelper.getSharedPreference("username", "");
        jiluRv.setHasFixedSize(true);
        jiluRv.setNestedScrollingEnabled(false);

        if (month1.length() < 1) {
            month1 = "0" + month1;
        }
//        n1Cf.setCanRefresh(false);
//        n1Cf.setRefreshListener(new BaseRefreshListener() {
//            @Override
//            public void refresh() {
//                gi();
//                n1Cf.finishRefresh();
//            }
//
//            @Override
//            public void loadMore() {
//                gi();
//                n1Cf.finishLoadMore();
//            }
//        });

        gi(month1+"月",month1);

        data.add("1月");
        data.add("2月");
        data.add("3月");
        data.add("4月");
        data.add("5月");
        data.add("6月");
        data.add("7月");
        data.add("8月");
        data.add("9月");
        data.add("10月");
        data.add("11月");
        data.add("12月");

    }


    private void popuinit2(ArrayList<String> mdata, final int mint) {
        View contentView = LayoutInflater.from(JiLuActivity.this).inflate(R.layout.wheel_view, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.2f);
        Button wheele_button = contentView.findViewById(R.id.wheelview_button);
        final WheelView wheelView = contentView.findViewById(R.id.wheelview1);
        wheelView.setData(mdata);
        wheelView.setDefault(mint);
        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        View rootview = LayoutInflater.from(JiLuActivity.this).inflate(R.layout.activity_ji_lu, null);
        wheele_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedText = wheelView.getSelectedText();
                String selectedText2 = wheelView.getSelectedText().replace("月", "");
                if (selectedText2.length() < 1) {
                    selectedText2 = "0" + selectedText2;
                }
                gi(selectedText, selectedText2);

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
        window.showAtLocation(rootview, Gravity.BOTTOM, 0, 70);
    }

    private void gi(final String selectedText, final String selectedText2) {
        Log.d("selectedText", selectedText2);

        int monthLastDay = DateUtil.getMonthLastDay(Integer.parseInt(year), Integer.parseInt(selectedText2));

        OkHttpUtils.post()
                .url(url)
                .addParams("token",token)
                .addParams("uid", uid)
                .addParams("start_time", year + "-" + selectedText2 + "-" + "01")
                .addParams("end_time", year + "-" + selectedText2 + "-" + monthLastDay)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        DakaBean dakaBean = gson.fromJson(response, DakaBean.class);
                        Log.d("refang",response);
                        if ("200".equals(dakaBean.getErrno())){
                            if ("0".equals(dakaBean.getData().getUserInfo().getHead())){
                                jiluHeadimg.setImageResource(R.drawable.head_portrait);
                            }else {
                                Glide.with(JiLuActivity.this).load(dakaBean.getData().getUserInfo().getHead()).into(jiluHeadimg);
                            }
//                        Glide.with(JiLuActivity.this).load(head).into(jiluHeadimg);
                            jiluName.setText(dakaBean.getData().getUserInfo().getName());

                            jiluDay.setText(dakaBean.getData().getUserInfo().getD());
                            jiluTime.setText(dakaBean.getData().getUserInfo().getH());
                            List<DakaBean.DataBean.ListBean> data = dakaBean.getData().getList();
                            MyAdapter5 myAdapter5 = new MyAdapter5(data, JiLuActivity.this);
                            jiluRv.setLayoutManager(new LinearLayoutManager(JiLuActivity.this));
                            jiluRv.setAdapter(myAdapter5);

                            if (Integer.parseInt(month) == Integer.parseInt(selectedText2)) {
                                jiluMonth.setText("本月");
                            } else {
                                jiluMonth.setText(selectedText);
                            }
                        } else if (dakaBean.getErrno().equals("666666")){
                            Toast.makeText(JiLuActivity.this,"您的账号已在其他手机登录，如非本人操作，请修改密码",Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(JiLuActivity.this, Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
                            ActivityManager.getInstance().exit();
                            intent = new Intent(JiLuActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                });
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

    @OnClick({R.id.jilu_goback, R.id.jilu_month})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jilu_goback:
                finish();
                break;
            case R.id.jilu_month:
                popuinit2(data, 2);
                break;
        }
    }
}
