package com.ztkj.wky.zhuantou.Activity.oa.punch;

/**
 * 作者：wky
 * 功能描述：
 * 打卡页面
 */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.google.gson.Gson;
import com.hyphenate.easeui.utils.SharedPreferencesHelper;
import com.kongzue.dialog.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.v3.MessageDialog;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.CompanyPunchInTime;
import com.ztkj.wky.zhuantou.bean.PunchInBean;
import com.ztkj.wky.zhuantou.bean.ThreeAndOneBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PunchInFragment extends Fragment {


    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.tv_SetonWork)
    TextView tvSetonWork;
    @BindView(R.id.onWorkLate)
    TextView onWorkLate;
    @BindView(R.id.tv_PunchOnWork)
    TextView tvPunchOnWork;
    @BindView(R.id.tv_locationOnWork)
    TextView tvLocationOnWork;
    @BindView(R.id.tv_ClickApplyOnWork)
    TextView tvClickApplyOnWork;
    @BindView(R.id.tv_SetOffWork)
    TextView tvSetOffWork;
    @BindView(R.id.offWorkLate)
    TextView offWorkLate;
    @BindView(R.id.tv_PunchOffWork)
    TextView tvPunchOffWork;
    @BindView(R.id.tv_locationOffWork)
    TextView tvLocationOffWork;
    @BindView(R.id.tv_ClickApplyOffWork)
    TextView tvClickApplyOffWork;
    @BindView(R.id.showTvTime)
    TextView showTvTime;
    @BindView(R.id.cl_ClickPunchIn)
    RelativeLayout clClickPunchIn;
    @BindView(R.id.tv_localNow)
    TextView tvLocalNow;
    @BindView(R.id.tv_ClickReLocal)
    TextView tvClickReLocal;
    Unbinder unbinder;
    @BindView(R.id.tv_punchTime)
    TextClock tvPunchTime;
    private String TAG = "PunchInFragment";

    private SharedPreferencesHelper sharedPreferencesHelper, sp_create_team;
    private String uid, token, cid;

    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat simDay = new SimpleDateFormat("yyyy-MM-dd");
    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat simTime = new SimpleDateFormat("HH:mm");
    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private long time, time1000;
    private String s, ss, s12, s0;  //获取每天设置的上下班时间 s是上班时间，ss是下班，单位均为秒
    private String start_time = "0";
    private String end_time = "0";
    private String late = "0";
    private String leave_early = "0";
    private String start_address = "0";
    private String end_address = "0";
    private int num = 0;
    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_punch_in, container, false);
        unbinder = ButterKnife.bind(this, view);
        //获取token和id
        sharedPreferencesHelper = new SharedPreferencesHelper(getContext(), "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        //获取cid
        sp_create_team = new SharedPreferencesHelper(getContext(), "Create_team");
        cid = (String) sp_create_team.getSharedPreference("Create_team_cid", "");


        //===================設置title====================
        layoutTitleTv.setText("打卡");

        //===================获取当前时间戳====================
        time = new Date().getTime();
        time1000 = new Date().getTime() / 1000;

        //======================请求数据=======================
        requestCompanyPunchInTime();//获取公司上下班时间


        //================= 设置位置 ========================
        tvLocalNow.setText(Contents.LOCATION);

        return view;
    }


    private void requestPunchInfo() {
        OkHttpUtils.post().url(Contents.SANHEYIPANDUAN)
                .addParams("token", token)
                .addParams("uid", uid)
                .addParams("cid", cid)
                .addParams("x", String.valueOf(Contents.LONGITUDE))
                .addParams("y", String.valueOf(Contents.LATITUDE))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: 三合一接口判断" + response);

                ThreeAndOneBean threeAndOneBean = new Gson().fromJson(response, ThreeAndOneBean.class);
                ThreeAndOneBean.DataBean data = threeAndOneBean.getData();

//                Log.e(TAG, "onResponse:======考勤范围========= " + data.getAttendance_position());

                if (data.getMorning_attendance().equals("2") && data.getEnd_night().equals("2")) { //上下班都打卡了

                    /**
                     *   这一层是上下班均打卡
                     *  1.所以隐藏打卡按钮
                     *  2.设置后台返回的上下班时间
                     *  3.设置后台返回的上下班地点
                     *  4.判断上班是否迟到，下班是否早退
                     *  5.判断上线班是否外勤
                     *
                     */
                    clClickPunchIn.setVisibility(View.INVISIBLE);//上下班正常的话则隐藏按钮

                    //设置上下班时间
                    String stron = timeStamp2Date(Integer.parseInt(data.getPunchInInfo().getStart_time()), "HH:mm");
                    String stroff = timeStamp2Date(Integer.parseInt(data.getPunchInInfo().getEnd_time()), "HH:mm");
                    tvPunchOnWork.setText("上班打卡 " + stron);
                    tvPunchOffWork.setText("下班打卡 " + stroff);

                    //设置上下班打卡地点
                    tvLocationOnWork.setVisibility(View.VISIBLE);
                    tvLocationOffWork.setVisibility(View.VISIBLE);
                    tvLocationOnWork.setText(data.getPunchInInfo().getStart_address());
                    tvLocationOffWork.setText(data.getPunchInInfo().getEnd_address());

                    //判断是否早上上班迟到
                    if (!data.getPunchInInfo().getLate().equals("0")) {
                        onWorkLate.setVisibility(View.VISIBLE);
                        onWorkLate.setText("迟到");
                        tvClickApplyOnWork.setVisibility(View.VISIBLE);
                    }


                    //判断是否下午下班早退
                    if (!data.getPunchInInfo().getLeave_early().equals("0")) {
                        offWorkLate.setVisibility(View.VISIBLE);
                        offWorkLate.setText("早退");
                        tvClickApplyOffWork.setVisibility(View.VISIBLE);
                    }


                    //判断上班是否显示外勤
                    if (!data.getAttendance_position().equals("2")) {
                        onWorkLate.setVisibility(View.VISIBLE);
                        onWorkLate.setText("外勤");
                    }


                    //判断下班是否显示外勤
                    if (!data.getAttendance_position().equals("2")) {
                        offWorkLate.setVisibility(View.VISIBLE);
                        offWorkLate.setText("外勤");
                    }


                } else if (data.getMorning_attendance().equals("2") && data.getEnd_night().equals("1")) {
                    /**
                     *   这一层是上班打卡了，下班还未打卡
                     *  1.按钮显示下班打卡
                     *  2.上班打卡显示后台返回的时间地点
                     *  3.判断上班是否迟到
                     *  4.判断上班是否外勤外勤
                     *  5.判断下班是否显示外勤打卡
                     *
                     */
                    //按钮显示下班打卡
                    showTvTime.setText("下班打卡");

                    //上班打卡显示后台返回的时间地点
                    String stron = timeStamp2Date(Integer.parseInt(data.getPunchInInfo().getStart_time()), "HH:mm");
                    tvPunchOnWork.setText("上班打卡 " + stron);
                    tvLocationOnWork.setVisibility(View.VISIBLE);
                    tvLocationOnWork.setText(data.getPunchInInfo().getStart_address());

                    //判断上班是否迟到
                    if (!data.getPunchInInfo().getLate().equals("0")) {
                        onWorkLate.setVisibility(View.VISIBLE);
                        onWorkLate.setText("迟到");
                        tvClickApplyOnWork.setVisibility(View.VISIBLE);
                    }

                    //判断上班是否显示外勤
                    if (!data.getAttendance_position().equals("2")) {
                        onWorkLate.setVisibility(View.VISIBLE);
                        onWorkLate.setText("外勤");
                    }

                    //判断下班是否在考勤范围内
                    if (data.getAttendance_position().equals("1")) {
                        showTvTime.setText("外勤打卡");
                        clClickPunchIn.setBackgroundResource(R.mipmap.icon_bg_unnormal);
                    }


                } else if (data.getMorning_attendance().equals("1") && data.getEnd_night().equals("2")) {
                    /**
                     *   这一层是上班未打卡，下班已打卡
                     *  1.直接显示上班缺卡 上班顯示補卡申請
                     *  2.两卡都打完了 隐藏按钮
                     *  3.下班显示后台显示的时间地点
                     *  4.判断下班是否早退
                     *  5.判断下班是否外勤
                     */

                    //上班显示缺卡，
                    onWorkLate.setVisibility(View.VISIBLE);
                    onWorkLate.setText("缺卡");

                    clClickPunchIn.setVisibility(View.GONE);

                    //上班显示补卡申请
                    tvClickApplyOnWork.setVisibility(View.VISIBLE);

                    //判断是否下午下班早退
                    if (!data.getPunchInInfo().getLeave_early().equals("0")) {
                        offWorkLate.setVisibility(View.VISIBLE);
                        offWorkLate.setText("早退");
                        tvClickApplyOffWork.setVisibility(View.VISIBLE);
                        tvClickApplyOnWork.setVisibility(View.VISIBLE);
                    }

                    //判断下班是否显示外勤
                    if (!data.getAttendance_position().equals("2")) {
                        offWorkLate.setVisibility(View.VISIBLE);
                        offWorkLate.setText("外勤");
                    }

                    //设置下班时间
                    String stroff = timeStamp2Date(Integer.parseInt(data.getPunchInInfo().getEnd_time()), "HH:mm");
                    tvPunchOffWork.setText("下班打卡 " + stroff);

                    //设置下班打卡地点
                    tvLocationOffWork.setVisibility(View.VISIBLE);
                    tvLocationOffWork.setText(data.getPunchInInfo().getEnd_address());

                } else if (data.getMorning_attendance().equals("1") && data.getEnd_night().equals("1")) {
                    /**
                     *  这一层上下班都没打卡
                     *  1.判断显示上下班文字
                     *   a. 判断上班是否外频
                     *   b. 当前时间小于9点显示上班打卡
                     *   c. 上班没卡当前时间大于12点，则上班缺卡，显示下班打卡
                     *
                     *   当前时间大于12点     上班缺卡，下班卡还没打卡
                     *                          A.1 上班缺卡显示
                     *                          A.2 上班补卡显示
                     *                          A.3 按钮显示下班打卡或者外勤打卡
                     *
                     *   当前时间小于12点     上班卡还没打
                     *                          A.1 判断当前时间是否小于9点 显示上班打卡或者外频打卡
                     *
                     */
//                    Log.e(TAG, "onResponse: ========s---s2=======" + s);

                    if (time1000 > Integer.parseInt(s12)) { //当前时间大于12点
                        //上班显示缺卡，
                        onWorkLate.setVisibility(View.VISIBLE);
                        onWorkLate.setText("缺卡");

                        //上班显示补卡申请
                        tvClickApplyOnWork.setVisibility(View.VISIBLE);

                        //判断下班是否在考勤范围内
                        if (data.getAttendance_position().equals("1")) {
                            showTvTime.setText("外勤打卡");
                            clClickPunchIn.setBackgroundResource(R.mipmap.icon_bg_unnormal);
                        } else {
                            showTvTime.setText("下班打卡");
                        }


                    } else if (time1000 < Integer.parseInt(s12)) { //当前时间小于12点
                        if (time1000 < Integer.parseInt(s))
                            //判断上班是否在考勤范围内
                            if (data.getAttendance_position().equals("1")) {
                                showTvTime.setText("外勤打卡");
                                clClickPunchIn.setBackgroundResource(R.mipmap.icon_bg_unnormal);
                            } else {
                                showTvTime.setText("上班打卡");
                            }

                    }

                }


            }
        });
    }

    private void requestCompanyPunchInTime() {
        OkHttpUtils.post().url(Contents.COMPANYPUNCHINTIME)
                .addParams("token", token)
                .addParams("cid", cid)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: =======获取公司打卡时间=======" + response);
                CompanyPunchInTime companyPunchInTime = new Gson().fromJson(response, CompanyPunchInTime.class);
                CompanyPunchInTime.DataBean data = companyPunchInTime.getData();
                //获取每天上班的时间戳(秒)
                s = date2TimeStamp(simDay.format(time) + " " + data.getStart_time(), "yyyy-MM-dd HH:mm");

                //获取每天下班的时间戳(秒)
                ss = date2TimeStamp(simDay.format(time) + " " + data.getEnd_time(), "yyyy-MM-dd HH:mm");

                //获取每天中午12点的时间戳(秒)
                s12 = date2TimeStamp(simDay.format(time) + " " + "12:00", "yyyy-MM-dd HH:mm");

                //获取每天凌晨0点的时间戳(秒)
                s0 = date2TimeStamp(simDay.format(time) + " " + "00:00", "yyyy-MM-dd HH:mm");

                //显示上下班时间
                tvSetonWork.setText("上班时间 " + data.getStart_time());
                tvSetOffWork.setText("下班时间 " + data.getEnd_time());
                Contents.WORKON = data.getStart_time();
                Contents.WORKOFF = data.getEnd_time();
                Contents.ADDRESS = data.getAddress();
//                Log.e(TAG, "onResponse: "+s+"===="+ss+"===="+s12+"==="+s0 );
                requestPunchInfo();//三合一接口
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @OnClick({R.id.layout_back, R.id.tv_ClickApplyOnWork, R.id.tv_ClickApplyOffWork, R.id.cl_ClickPunchIn, R.id.tv_ClickReLocal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                Objects.requireNonNull(getActivity()).finish();
                break;
            case R.id.tv_ClickApplyOnWork: //上午的补卡申请
//                requestApplyReissuePunchin();
                intent = new Intent(getActivity(), ApplyReissuePunchinActivity.class);
                String format = simDay.format(new Date().getTime());
                intent.putExtra("time", format);
                intent.putExtra("type", "1");
                startActivity(intent);
                break;
            case R.id.tv_ClickApplyOffWork: //下午的补卡申请
                intent = new Intent(getActivity(), ApplyReissuePunchinActivity.class);
                String format2 = simDay.format(new Date().getTime());
                intent.putExtra("time", format2);
                intent.putExtra("type", "2");
                startActivity(intent);
                break;
            case R.id.cl_ClickPunchIn: //打卡

                requestPunchIn();
                break;
            case R.id.tv_ClickReLocal: //重新定位
                SDKInitializer.initialize(getContext());
                break;
        }
    }


    //打卡
    private void requestPunchIn() {
        time1000 = new Date().getTime() / 1000;
        if (num == 0) {
            if (time1000 < Integer.parseInt(s12)) { //当前时间小于12点 上午打卡
                if (time1000 > Integer.parseInt(s)) { //迟到
                    start_time = s;
                    end_time = "0";
                    late = time1000 + "";
                    leave_early = "0";
                    start_address = Contents.LOCATION;
                    end_address = "0";
                } else { //未迟到
                    start_time = s;
                    end_time = "0";
                    late = "";
                    leave_early = "0";
                    start_address = Contents.LOCATION;
                    end_address = "0";
                }
            } else { //下午打卡
                if (time1000 < Integer.parseInt(ss)) { //早退
                    start_time = "0";
                    end_time = ss;
                    late = "0";
                    leave_early = time1000 + "";
                    start_address = "0";
                    end_address = Contents.LOCATION;
                } else { //未早退
                    start_time = "0";
                    end_time = ss;
                    late = "0";
                    leave_early = "";
                    start_address = "0";
                    end_address = Contents.LOCATION;
                }
            }
        } else {
            MessageDialog.show((AppCompatActivity) Objects.requireNonNull(getContext()), "提示", "当前打卡为早退，是否继续？", "确定", "取消")
                    .setOnOkButtonClickListener(new OnDialogButtonClickListener() {
                        @Override
                        public boolean onClick(BaseDialog baseDialog, View v) {
                            if (time1000 < Integer.parseInt(ss)) { //早退
                                start_time = "0";
                                end_time = ss;
                                late = "0";
                                leave_early = time1000 + "";
                                start_address = "0";
                                end_address = Contents.LOCATION;
                            } else { //未早退
                                start_time = "0";
                                end_time = ss;
                                late = "0";
                                leave_early = "";
                                start_address = "0";
                                end_address = Contents.LOCATION;
                            }
                            return false;
                        }
                    });
        }


        if (showTvTime.getText().equals("外勤打卡")) {
            OkHttpUtils.post().url(Contents.PUNCHIN)
                    .addParams("token", token)
                    .addParams("uid", uid)
                    .addParams("cid", cid)
                    .addParams("x", String.valueOf(Contents.LONGITUDE))
                    .addParams("y", String.valueOf(Contents.LATITUDE))
                    .addParams("start_time", start_time)
                    .addParams("end_time", end_time)
                    .addParams("late", late)
                    .addParams("leave_early", leave_early)
                    .addParams("start_field_address", start_address)
                    .addParams("end_field_address", end_address)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Request request, Exception e) {

                }

                @Override
                public void onResponse(String response) {
                    Log.e(TAG, "onResponse: ======打卡=======" + response);
                    PunchInBean punchInBean = new Gson().fromJson(response, PunchInBean.class);
                    if (punchInBean.getErrno().equals("200")) {
                        num++;
                        requestPunchInfo();
                    }
                }
            });

            return;
        }


        OkHttpUtils.post().url(Contents.PUNCHIN)
                .addParams("token", token)
                .addParams("uid", uid)
                .addParams("cid", cid)
                .addParams("x", String.valueOf(Contents.LONGITUDE))
                .addParams("y", String.valueOf(Contents.LATITUDE))
                .addParams("start_time", start_time)
                .addParams("end_time", end_time)
                .addParams("late", late)
                .addParams("leave_early", leave_early)
                .addParams("start_address", start_address)
                .addParams("end_address", end_address)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
//                Log.e(TAG, "onResponse: =====参数====" + "token++++++" + token + "++++uid+++" + uid + "+++cid++++++" + cid + "x" + Contents.LONGITUDE + "y" + Contents.LATITUDE + "start_time" + start_time + "end_time" + end_time + "late" + late + "leave_early" + leave_early + "start_address" + start_address + "end_address" + end_address);
//                Log.e(TAG, "onResponse: x" + Contents.LONGITUDE);
//                Log.e(TAG, "onResponse: y" + Contents.LATITUDE);
//                Log.e(TAG, "onResponse: start_time" + start_time);
//                Log.e(TAG, "onResponse: end_time" + end_time);
//                Log.e(TAG, "onResponse: late" + late);
//                Log.e(TAG, "onResponse: leave_early" + leave_early);
//                Log.e(TAG, "onResponse: start_address" + start_address);
//                Log.e(TAG, "onResponse: end_address" + end_address);
                Log.e(TAG, "onResponse: ======打卡=======" + response);
                PunchInBean punchInBean = new Gson().fromJson(response, PunchInBean.class);


                if (punchInBean.getErrno().equals("200")) {
                    num++;
                    requestPunchInfo();
                }
            }
        });


    }

    //日期格式字符串转换时间戳
    public static String date2TimeStamp(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String timeStamp2Date(long time, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time * 1000));
    }
}
