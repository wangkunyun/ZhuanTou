package com.ztkj.wky.zhuantou.Activity.oa.report;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.blankj.utilcode.util.SPUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.XtablayoutAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.ReportRedDot;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReadReportFragment extends Fragment {


    @BindView(R.id.xTablayout)
    XTabLayout xTablayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;

    List<Fragment> fragments = new ArrayList<>();
    @BindView(R.id.ReportRedDot)
    TextView ReportRedDot;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read_report, container, false);
        unbinder = ButterKnife.bind(this, view);

        List<String> titles = new ArrayList<>();
        titles.add("全部");
        titles.add("我提交的");
        titles.add("我收到的");
        fragments.add(new AllReport());
        fragments.add(new SubmitReport());
        fragments.add(new ReceiveReport());

        XtablayoutAdapter xtablayoutAdapter = new XtablayoutAdapter(getFragmentManager(), fragments, titles);
        viewPager.setAdapter(xtablayoutAdapter);
        viewPager.setOffscreenPageLimit(3);
        //将TabLayout和ViewPager关联起来。
        xTablayout.setupWithViewPager(viewPager);
        //给TabLayout设置适配器
        xTablayout.setupWithViewPager(viewPager);
        return view;
    }

    @Override
    public void onResume() {
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
                    if( reportRedDot.getData().getNum()!=null){
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
