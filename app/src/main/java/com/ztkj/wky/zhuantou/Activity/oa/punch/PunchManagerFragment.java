package com.ztkj.wky.zhuantou.Activity.oa.punch;

/**
 * 作者：wky
 * 功能描述：管理人员选项
 */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.CompanyPunchInTime;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PunchManagerFragment extends Fragment {
    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.tv_CheckingPlace)
    TextView tvCheckingPlace;
    @BindView(R.id.clickCheckingPlace)
    RelativeLayout clickCheckingPlace;
    @BindView(R.id.clickCheckingPeople)
    RelativeLayout clickCheckingPeople;
    @BindView(R.id.tv_CheckingTime)
    TextView tvCheckingTime;
    @BindView(R.id.clickCheckingTime)
    RelativeLayout clickCheckingTime;
    @BindView(R.id.tv_CheckingRange)
    TextView tvCheckingRange;
    @BindView(R.id.clickCheckingRange)
    RelativeLayout clickCheckingRange;
    Unbinder unbinder;
    private Intent intent;
    private String TAG = "PunchManagerFragment";
    private CompanyPunchInTime.DataBean data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_punch_manager, container, false);
        unbinder = ButterKnife.bind(this, view);
        layoutTitleTv.setText("考勤设置");


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        requestCompanyPunchInTime();//获取公司上下班时间

    }

    private void requestCompanyPunchInTime() {
        OkHttpUtils.post().url(Contents.COMPANYPUNCHINTIME)
                .addParams("token", SPUtils.getInstance().getString("token"))
                .addParams("cid", SPUtils.getInstance().getString("cid"))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: =======获取公司打卡时间=======" + response);
                CompanyPunchInTime companyPunchInTime = new Gson().fromJson(response, CompanyPunchInTime.class);
                data = companyPunchInTime.getData();
                tvCheckingPlace.setText(data.getAddress());
                tvCheckingTime.setText("早" + data.getStart_time() + "至晚" + data.getEnd_time());
                tvCheckingRange.setText(data.getRadius() + "米");
            }
        });
    }


    @OnClick({R.id.layout_back, R.id.clickCheckingPlace, R.id.clickCheckingPeople, R.id.clickCheckingTime, R.id.clickCheckingRange})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                Objects.requireNonNull(getActivity()).finish();
                break;
            case R.id.clickCheckingPlace:  //考勤地点设置
                intent = new Intent(getActivity(), BaiduMapActivity.class);
                if (!StringUtils.isEmpty(data.getRadius())) {
                    intent.putExtra("radius", data.getRadius());
                    startActivity(intent);
                }
                break;
            case R.id.clickCheckingPeople: //免考勤人员设置
                intent = new Intent(getActivity(), FreeCheckingPeopleActivity.class);
                startActivity(intent);
                break;
            case R.id.clickCheckingTime: //考勤时间设置
                intent = new Intent(getActivity(), CheckingTimeActivity.class);
                startActivity(intent);
                break;
            case R.id.clickCheckingRange: //考勤范围设置.
                intent = new Intent(getActivity(), CheckingRangeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
