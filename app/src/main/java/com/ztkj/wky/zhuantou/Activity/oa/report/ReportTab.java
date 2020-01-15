package com.ztkj.wky.zhuantou.Activity.oa.report;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.hjm.bottomtabbar.BottomTabBar;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.ReportRedDot;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReportTab extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;
    @BindView(R.id.ReportRedDot)
    TextView ReportRedDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);

        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(80, 80)
                .setFontSize(10)
                .addTabItem("写日志", R.mipmap.icon_wrt_select, WriteReportFragment.class)
                .addTabItem("看日志", R.mipmap.icon_look_sel, ReadReportFragment.class)
                .setTabBarBackgroundColor(Color.WHITE)
                .isShowDivider(true);
    }


    @Override
    protected void onResume() {
        super.onResume();
        requestDot();
    }

    private void requestDot() {
        //请求日志小红点
        OkHttpUtils.post()
                .url(Contents.REPORTREDDOT)
                .addParams("token", SPUtils.getInstance().getString("token"))
                .addParams("uid", SPUtils.getInstance().getString("uid"))
                .addParams("cid", SPUtils.getInstance().getString("cid"))
                .addParams("type", "0")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                ReportRedDot reportRedDot = GsonUtil.gsonToBean(response, com.ztkj.wky.zhuantou.bean.ReportRedDot.class);
                if (reportRedDot.getErrno().equals("200")) {
                    if(reportRedDot.getData().getNum()!=null){
                        Contents.reportReddotNum = Integer.parseInt(reportRedDot.getData().getNum());
                        if (Contents.reportReddotNum != 0) {
                            ReportRedDot.setVisibility(View.VISIBLE);
                        } else {
                            ReportRedDot.setVisibility(View.GONE);
                        }
                    }
                }


            }
        });
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
