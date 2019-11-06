package com.ztkj.wky.zhuantou.isMy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ztkj.wky.zhuantou.H5.UserInstructionsActivity;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.Colorstring;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.MyAdapter4;
import com.ztkj.wky.zhuantou.bean.JiFenBean;
import com.ztkj.wky.zhuantou.bean.MyZtBean;
import com.ztkj.wky.zhuantou.landing.LoginActivity;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class JiFenActivity extends AppCompatActivity {

    @BindView(R.id.zc_goback)
    ImageView zcGoback;
    @BindView(R.id.jifen_gyzt)
    TextView jifenGyzt;
    @BindView(R.id.jifen_ztsl)
    TextView jifenZtsl;
    @BindView(R.id.jifen_qiandao)
    Button jifenQiandao;
    @BindView(R.id.jifen_tv1)
    TextView jifenTv1;
    @BindView(R.id.jifen_rv)
    RecyclerView jifenRv;
//    @BindView(R.id.jifen_cf)
//    PullToRefreshLayout jifenCf;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private Intent intent;
    private String reach;
    private String uid,token;
    private String url = StringUtils.jiekouqianzui+"Commodity/commodityList";
    private String url2 = StringUtils.jiekouqianzui+"User/record";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ji_fen);
        ButterKnife.bind(this);
        ActivityManager.getInstance().addActivity(this);
        sharedPreferencesHelper = new SharedPreferencesHelper(JiFenActivity.this, "anhua");
        reach = (String) sharedPreferencesHelper.getSharedPreference("reach","");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid","");
        token = (String) sharedPreferencesHelper.getSharedPreference("token","");
        register();
        gi();
    }

    private void register() {
        if (reach.equals("0")){
            jifenQiandao.setEnabled(true);
            jifenQiandao.setText("签到领积分");
            jifenQiandao.setTextColor(Color.parseColor(Colorstring.baise));
        }else if (reach.equals("1")){
            jifenQiandao.setEnabled(false);
            jifenQiandao.setText("已签到");
            jifenQiandao.setTextColor(Color.parseColor("#FFC4A3"));
        }
    }

    private void gi() {
        OkHttpUtils.post()
                .url(url)
                .addParams("uid",uid)
                .addParams("token",token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();

                        Log.d("fuckckck",response);
                        MyZtBean  myZtBean = gson.fromJson(response,MyZtBean.class);

                        if (myZtBean.getErrno().equals("200")){
                            jifenZtsl.setText(myZtBean.getData().getIntegral());

                            List<MyZtBean.DataBean.CommodityListBean> data = myZtBean.getData().getCommodityList();
                            MyAdapter4 myAdapter4 = new MyAdapter4(data,JiFenActivity.this);
                            jifenRv.setLayoutManager(new GridLayoutManager(JiFenActivity.this,2));
                            jifenRv.setAdapter(myAdapter4);
                        } else if (myZtBean.getErrno().equals("666666")){
                            Toast.makeText(JiFenActivity.this,"您的账号已在其他手机登录，如非本人操作，请修改密码",Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(JiFenActivity.this, Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
                            ActivityManager.getInstance().exit();
                            intent = new Intent(JiFenActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }

    private void gi2() {
        OkHttpUtils.post()
                .url(url2)
                .addParams("uid",uid)
                .addParams("token",token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }
                    @Override
                    public void onResponse(String response) {
                        Log.d("jifenll",response);
                        Gson gson = new Gson();

                        JiFenBean  zcBean = gson.fromJson(response,JiFenBean.class);

                        if ("200".equals(zcBean.getErrno())){
                            jifenQiandao.setEnabled(false);
                            jifenQiandao.setText("已签到");
                            jifenQiandao.setTextColor(Color.parseColor("#FFC4A3"));
                            jifenZtsl.setText(zcBean.getData().getIntegral());
                        }else if (zcBean.getErrno().equals("666666")){
                            Toast.makeText(JiFenActivity.this,"您的账号已在其他手机登录，如非本人操作，请修改密码",Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(JiFenActivity.this, Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
                            ActivityManager.getInstance().exit();
                            intent = new Intent(JiFenActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(JiFenActivity.this,gson.toJson(zcBean.getErrmsg()),Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    @OnClick({R.id.zc_goback, R.id.jifen_gyzt, R.id.jifen_qiandao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zc_goback:
                finish();
                break;
            case R.id.jifen_gyzt:
                intent = new Intent();
                intent.setClass(JiFenActivity.this, UserInstructionsActivity.class);
                intent.putExtra("urlmsg","https://api.zhuantoukj.com/image/guanyu.html");
                intent.putExtra("title","关于砖头");
                startActivity(intent);
                break;
            case R.id.jifen_qiandao:
                gi2();
                break;
        }
    }


}
