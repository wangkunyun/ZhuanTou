package com.ztkj.wky.zhuantou.Activity.oa.report;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.SubmitReportBean;
import com.ztkj.wky.zhuantou.landing.LoginActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;

import static com.ztkj.wky.zhuantou.R.drawable.circle_report_orange;

/**
 * A simple {@link Fragment} subclass.
 * 写日志页面 展示日志分类
 */
public class WriteReportFragment extends Fragment {


    @BindView(R.id.recently)
    LinearLayout recently;
    @BindView(R.id.date_report)
    LinearLayout dateReport;
    @BindView(R.id.week_report)
    LinearLayout weekReport;
    @BindView(R.id.month_report)
    LinearLayout monthReport;
    @BindView(R.id.report_work_summary)
    LinearLayout reportWorkSummary;
    Unbinder unbinder;
    @BindView(R.id.circle_real)
    TextView circleReal;
    @BindView(R.id.tv_type_real)
    TextView tvTypeReal;
    @BindView(R.id.tv_real)
    TextView tvReal;
    @BindView(R.id.recentlylTime)
    TextView recentlylTime;
    private Intent intent;

    private SharedPreferencesHelper sharedPreferencesHelper, sp_create_team;
    private String uid;
    private String token;
    private String cid;
    private String type;
    private List<SubmitReportBean.DataBean> data;
    private String TAG = "SubmitReport";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_write_report, container, false);
        unbinder = ButterKnife.bind(this, view);

        //获取token和id
        sharedPreferencesHelper = new SharedPreferencesHelper(getActivity(), "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        //获取cid
        sp_create_team = new SharedPreferencesHelper(getActivity(), "Create_team");
        cid = (String) sp_create_team.getSharedPreference("Create_team_cid", "");

        request_real();


        return view;
    }

    private void request_real() {
        OkHttpUtils.post()
                .url(Contents.SUNMITREPORT)
                .addParams("token", token)
                .addParams("uid", uid)
                .addParams("cid", cid)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                SubmitReportBean submitReportBean = new Gson().fromJson(response, SubmitReportBean.class);
                if (submitReportBean.getErrno().equals("200")) {
                    data = submitReportBean.getData();
                    if (!data.isEmpty()) {
                        type = data.get(0).getType();
                        String addtime = data.get(0).getAddtime();
                        recentlylTime.setText(addtime);
                        if (type.equals("did")) {
                            circleReal.setText("日");
                            tvTypeReal.setText("日报");
                        } else if (type.equals("wid")) {
                            circleReal.setText("周");
                            tvTypeReal.setText("周报");
                        } else if (type.equals("mid")) {
                            circleReal.setText("月");
                            tvTypeReal.setText("月报");
                        } else if (type.equals("tid")) {
                            circleReal.setText("总结");
                            tvTypeReal.setText("总结");
                            circleReal.setBackgroundResource(circle_report_orange);
                        } else {
                            tvReal.setVisibility(View.GONE);
                            circleReal.setVisibility(View.GONE);
                            tvTypeReal.setText("");
                        }
                    } else {
                        recently.setVisibility(View.GONE);
                    }


                } else if (submitReportBean.getErrno().equals("666666")) {
                    Toast.makeText(getActivity(), "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                    JPushInterface.deleteAlias(getActivity(), Integer.parseInt(uid));
                    sharedPreferencesHelper.clear();
                    ActivityManager.getInstance().exit();
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
//                            getActivity().finish();
                } else {

                }
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.recently, R.id.date_report, R.id.week_report, R.id.month_report, R.id.report_work_summary, R.id.report_work_meeting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.recently:
                if (type.equals("did")) {
                    intent = new Intent(getActivity(), WriteReport.class);
                    startActivity(intent);
                } else if (type.equals("wid")) {
                    intent = new Intent(getActivity(), WriteWidReportActivity.class);
                    startActivity(intent);
                } else if (type.equals("mid")) {
                    intent = new Intent(getActivity(), WriteMonReoprtActivity.class);
                    startActivity(intent);
                } else if (type.equals("tid")) {
                    intent = new Intent(getActivity(), WorkSummary.class);
                    startActivity(intent);
                }
                break;
            case R.id.date_report:
                intent = new Intent(getActivity(), WriteReport.class);
                startActivity(intent);
                break;
            case R.id.week_report:
                intent = new Intent(getActivity(), WriteWidReportActivity.class);
                startActivity(intent);
                break;
            case R.id.month_report:
                intent = new Intent(getActivity(), WriteMonReoprtActivity.class);
                startActivity(intent);
                break;
            case R.id.report_work_summary:
                intent = new Intent(getActivity(), WorkSummary.class);
                startActivity(intent);
                break;
            case R.id.report_work_meeting:
                intent = new Intent(getActivity(), MeetingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
