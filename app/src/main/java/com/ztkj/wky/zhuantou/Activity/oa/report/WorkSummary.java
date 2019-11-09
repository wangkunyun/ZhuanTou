package com.ztkj.wky.zhuantou.Activity.oa.report;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.vincent.filepicker.Constant;
import com.vincent.filepicker.activity.NormalFilePickActivity;
import com.vincent.filepicker.filter.entity.NormalFile;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.ShowAdpoverAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.TIDBean;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

/**
 * 工作总结
 */
public class WorkSummary extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.Real_time)
    TextView RealTime;
    @BindView(R.id.tv_startTime)
    TextView tvStartTime;
    @BindView(R.id.click_rl_startTime)
    RelativeLayout clickRlStartTime;
    @BindView(R.id.tv_endTime)
    TextView tvEndTime;
    @BindView(R.id.click_rl_endTime)
    RelativeLayout clickRlEndTime;
    @BindView(R.id.et_writeReport_finishWork)
    EditText etWriteReportFinishWork;
    @BindView(R.id.et_writeReportWorkSummary)
    EditText etWriteReportWorkSummary;
    @BindView(R.id.et_WriteWrite_workPlan)
    EditText etWriteWriteWorkPlan;
    @BindView(R.id.re_write_report_files)
    RecyclerView reWriteReportFiles;
    @BindView(R.id.btnSubmit_writeReport)
    Button btnSubmitWriteReport;
    @BindView(R.id.re_work_summary_approver)
    RecyclerView reWorkSummaryApprover;
    @BindView(R.id.click_rl_addApppover2)
    RelativeLayout clickRlAddApppover2;
    @BindView(R.id.re_work_summary_approver2)
    RecyclerView reWorkSummaryApprover2;
    @BindView(R.id.enterStartTime)
    ImageView enterStartTime;
    @BindView(R.id.clearStartTime)
    ImageView clearStartTime;
    @BindView(R.id.enterEndTime)
    ImageView enterEndTime;
    @BindView(R.id.clearEndTime)
    ImageView clearEndTime;
    @BindView(R.id.click_rl_addfiles)
    RelativeLayout clickRlAddfiles;
    @BindView(R.id.click_rl_addApppover)
    RelativeLayout clickRlAddApppover;

    private ArrayList<NormalFile> list; //文件集合
    private ArrayList<String> ListPathName;//文件名称集合
    private String TAG = "WorkSummary";
    private SharedPreferencesHelper sharedPreferencesHelper, sp_create_team;
    private SharedPreferencesHelper listApprover_head_uid;
    private SharedPreferencesHelper sava_summary;
    private int clickNum = 0;
    private String uid;
    private String token;
    private String cid;
    private String approverTag = "";
    private Intent intent;
    private TIDBean tidBean;
    private String starttime, endtime;
    private String sim_startTime, sim_endTime;
    private String sp_append_head;
    private String sp_append_uid;
    private ArrayList<String> Arr_approver = new ArrayList<>();
    private ArrayList<String> Arr_approver2 = new ArrayList<>();
    private ArrayList<String> Arr_approver_uid = new ArrayList<>();
    private ArrayList<String> Arr_approver_uid2 = new ArrayList<>();
    private SharedPreferencesHelper sp_save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work__summary);
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            Log.e(TAG, "onCreate: 保存的数据保存的数据保存的数据保存的数据" + savedInstanceState.getString("et"));
            etWriteReportFinishWork.setText(savedInstanceState.getString("et"));
        }
        //获取token和id
        sharedPreferencesHelper = new SharedPreferencesHelper(this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        //获取cid
        sp_create_team = new SharedPreferencesHelper(this, "Create_team");
        cid = (String) sp_create_team.getSharedPreference("Create_team_cid", "");
        //拼接的sp
        sava_summary = new SharedPreferencesHelper(WorkSummary.this, "sava_summary");

        sp_save = new SharedPreferencesHelper(this, "sp_save_Summary");
        String tv_1 = (String) sp_save.getSharedPreference("tv1", "");
        String tv_2 = (String) sp_save.getSharedPreference("tv2", "");
        String tv_3 = (String) sp_save.getSharedPreference("tv3", "");
        starttime = (String) sp_save.getSharedPreference("startTime", "");
        endtime = (String) sp_save.getSharedPreference("endTime", "");
        if (!tv_1.isEmpty()) {
            etWriteReportFinishWork.setText(tv_1);
        }
        if (!tv_2.isEmpty()) {
            etWriteReportWorkSummary.setText(tv_2);
        }
        if (!tv_3.isEmpty()) {
            etWriteWriteWorkPlan.setText(tv_3);
        }
        if (!starttime.equals("请选择")) {
            tvStartTime.setText(starttime);
        }
        if (!endtime.equals("请选择")) {
            tvEndTime.setText(endtime);
        }


        //解决滑动冲突
        reWriteReportFiles.setHasFixedSize(true);
        reWriteReportFiles.setNestedScrollingEnabled(false);
        reWorkSummaryApprover.setHasFixedSize(true);
        reWorkSummaryApprover.setNestedScrollingEnabled(false);
        request();

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("et", etWriteReportFinishWork.getText().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: TAG,TAG" + approverTag);
        //从联系人跳过来的sp里的审批人id和
        listApprover_head_uid = new SharedPreferencesHelper(WorkSummary.this, "listApprover");
        String approver_head = (String) listApprover_head_uid.getSharedPreference("approver_head", "");
        String approver_uid = (String) listApprover_head_uid.getSharedPreference("approver_uid", "");
        Log.e(TAG, "onResume: SP,SP" + approver_head + approver_uid);

        if (approverTag.equals("0")) {
            //        Log.e(TAG, "onResume:approver: " + approver_head + "======" + approver_uid);
            //去重并将数据放入集合中  审批人头像
            if (!approver_head.equals("")) {
                if (Arr_approver.size() == 0) {
                    Arr_approver.add(approver_head);
                } else {
                    if (!Arr_approver.contains(approver_head)) {
                        Arr_approver.add(approver_head);
                    }
                }
            }

            //去重并将数据放入集合中  审批人id
            if (!approver_uid.equals("")) {
                if (Arr_approver_uid.size() == 0) {
                    Arr_approver_uid.add(approver_uid);
                } else {
                    if (!Arr_approver_uid.contains(approver_uid)) {
                        Arr_approver_uid.add(approver_uid);
                    }
                }
            }
        } else if (approverTag.equals("1")) {
            //        Log.e(TAG, "onResume:approver: " + approver_head + "======" + approver_uid);
            //去重并将数据放入集合中  审批人头像
            if (!approver_head.equals("")) {
                if (Arr_approver2.size() == 0) {
                    Arr_approver2.add(approver_head);
                } else {
                    if (!Arr_approver2.contains(approver_head)) {
                        Arr_approver2.add(approver_head);
                    }
                }
            }

            //去重并将数据放入集合中  审批人id
            if (!approver_uid.equals("")) {
                if (Arr_approver_uid2.size() == 0) {
                    Arr_approver_uid2.add(approver_uid);
                } else {
                    if (!Arr_approver_uid2.contains(approver_uid)) {
                        Arr_approver_uid2.add(approver_uid);
                    }
                }
            }
        }


        //适配器
        ShowAdpoverAdapter showAdpoverAdapter = new ShowAdpoverAdapter(WorkSummary.this, Arr_approver);

//        ShowPictureAdapter showPictureAdapter = new ShowPictureAdapter(WorkSummary.this, Arr_approver);
        showAdpoverAdapter.notifyDataSetChanged();
        //布局管理器对象 参数1.上下文 2.规定一行显示几列的参数常量
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        //设置RecycleView显示的方向是水平还是垂直 GridLayout.HORIZONTAL水平  GridLayout.VERTICAL默认垂直
        gridLayoutManager.setOrientation(GridLayout.VERTICAL);
        reWorkSummaryApprover.setLayoutManager(gridLayoutManager);
        reWorkSummaryApprover.setAdapter(showAdpoverAdapter);

        //适配器
        ShowAdpoverAdapter showPictureAdapter2 = new ShowAdpoverAdapter(WorkSummary.this, Arr_approver2);
//        ShowPictureAdapter showPictureAdapter2 = new ShowPictureAdapter(WorkSummary.this, Arr_approver2);
        showAdpoverAdapter.notifyDataSetChanged();
        //布局管理器对象 参数1.上下文 2.规定一行显示几列的参数常量
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this, 5);
        //设置RecycleView显示的方向是水平还是垂直 GridLayout.HORIZONTAL水平  GridLayout.VERTICAL默认垂直
        gridLayoutManager.setOrientation(GridLayout.VERTICAL);
        reWorkSummaryApprover2.setLayoutManager(gridLayoutManager2);
        reWorkSummaryApprover2.setAdapter(showPictureAdapter2);

    }

    /**
     * 选择展示文件
     */
    private void show_files() {
        Intent intentfiles = new Intent(WorkSummary.this, NormalFilePickActivity.class);
        intentfiles.putExtra(Constant.MAX_NUMBER, 9);
        intentfiles.putExtra(NormalFilePickActivity.SUFFIX, new String[]{"xlsx", "xls", "doc", "docx", "ppt", "pptx", "pdf"});
        startActivityForResult(intentfiles, Constant.REQUEST_CODE_PICK_FILE);
    }

    /**
     * 处理回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                /**
                 *   获取文件
                 */
                case Constant.REQUEST_CODE_PICK_FILE:
                    list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_FILE);
                    ListPathName = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        String path = list.get(i).getPath();
                        String pathName = path.substring(path.lastIndexOf("/") + 1, path.length());
                        ListPathName.add(pathName);
                    }

                    //适配器
                    ShowFilesAdapter showFilesAdapter = new ShowFilesAdapter(WorkSummary.this, ListPathName);
                    reWriteReportFiles.setLayoutManager(new LinearLayoutManager(WorkSummary.this));
                    reWriteReportFiles.setAdapter(showFilesAdapter);
                    updateFiles(tidBean);
                    break;
            }
        }
    }


    private void request() {
        //生成日报id
        OkHttpUtils.post()
                .url(Contents.SUMMARYID)
                .addParams("token", token)
                .addParams("uid", uid)
                .addParams("cid", cid)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: id返回" + response);
                tidBean = new Gson().fromJson(response, TIDBean.class);
                if (tidBean.getErrno().equals("200")) {


                } else if (tidBean.getErrno().equals("666666")) {
                    Toast.makeText(WorkSummary.this, "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                    JPushInterface.deleteAlias(WorkSummary.this, Integer.parseInt(uid));
                    sharedPreferencesHelper.clear();
                    SPUtils.getInstance().clear();
                    ActivityManager.getInstance().exit();
                    intent = new Intent(WorkSummary.this, NewLoginActivity.class);
                    startActivity(intent);
//                            getActivity().finish();
                } else {

                }
            }
        });
    }


    private void updateFiles(TIDBean didIDBean) {
        //循环上传文件
        for (int i = 0; i < list.size(); i++) {
            String path = list.get(i).getPath();
            File file = new File(path);
            OkHttpUtils.post()
                    .url(Contents.UPDATESUMMARYFILE)
                    .addParams("token", token)
                    .addParams("did", didIDBean.getData().getTid())
                    .addFile("files", path, file)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Request request, Exception e) {
                    Log.e(TAG, "onError: " + e.getMessage());
                }

                @Override
                public void onResponse(String response) {
                    Log.e(TAG, "onResponse: 循环里上传文件" + response);
                }
            });

        }
    }


    @OnClick({R.id.back, R.id.click_rl_startTime, R.id.click_rl_endTime, R.id.btnSubmit_writeReport
            , R.id.click_rl_addfiles, R.id.click_rl_addApppover, R.id.click_rl_addApppover2,
            R.id.clearStartTime, R.id.clearEndTime})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.click_rl_startTime:
                //时间选择器
                TimePickerView pvTime = new TimePickerBuilder(WorkSummary.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        Log.e(TAG, date.toString());
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        //获取汉字格式的时间戳
                        starttime = simpleDateFormat.format(date);
                        //获取英文格式的时间戳并上传给后台
                        tvStartTime.setText(starttime);

                        sim_startTime = String.valueOf(date.getTime() / 1000);


                    }
                })
                        .setCancelText(" ")//取消按钮文字
                        .setSubmitColor(Color.rgb(101, 201, 210))//确定按钮文字颜色
                        .build();
                pvTime.show();

                break;
            case R.id.click_rl_endTime:
                //时间选择器
                TimePickerView pvTime2 = new TimePickerBuilder(WorkSummary.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        Log.e(TAG, date.toString());
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        sim_endTime = String.valueOf(date.getTime() / 1000);

                        //获取汉字格式的时间戳
                        endtime = simpleDateFormat.format(date);
                        //获取英文格式的时间戳并上传给后台
                        tvEndTime.setText(endtime);
                    }
                })
                        .setCancelText(" ")//取消按钮文字
                        .setSubmitColor(Color.rgb(101, 201, 210))//确定按钮文字颜色
                        .build();
                pvTime2.show();

                break;
            case R.id.clearStartTime:
                tvStartTime.setText("");
                break;
            case R.id.clearEndTime:
                tvEndTime.setText("");
                break;
            case R.id.click_rl_addfiles:
                show_files();
                break;
            case R.id.click_rl_addApppover:
                approverTag = "0";
                intent = new Intent(WorkSummary.this, ChooseAdressActivity.class);
                startActivity(intent);
                break;
            case R.id.click_rl_addApppover2:
                approverTag = "1";
                intent = new Intent(WorkSummary.this, ChooseAdressActivity.class);
                startActivity(intent);
                break;
            case R.id.btnSubmit_writeReport:
                listApprover_head_uid.clear();
                if (tvStartTime.getText().toString().equals("请选择") && tvStartTime.getText().toString().equals("请选择")) {
                    Toast.makeText(this, "请输入起止时间", Toast.LENGTH_SHORT).show();
                    return;
                }


                popuinit("是否确认提交", "取消 ", "确定");


                break;
        }
    }


    private void popuinit(String s, String s1, final String s2) {
        View contentView = LayoutInflater.from(WorkSummary.this).inflate(R.layout.pp_telephone, null);
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
        View rootview = LayoutInflater.from(WorkSummary.this).inflate(R.layout.activity_sz, null);

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
                clickNum = clickNum + 1;
                updateWorkSummary();

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

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

    private void updateWorkSummary() {
        String str = getStr(Arr_approver_uid);
        Editable text = etWriteReportFinishWork.getText();
        Editable text1 = etWriteReportWorkSummary.getText();
        Editable text2 = etWriteWriteWorkPlan.getText();
        Log.e(TAG, "WorkSummary: text====" + "-" + text + "-" + text1 + "-" + text2 + "-" + tidBean.getData().getTid() + "==" + tvStartTime.getText().toString() + "==" + tvEndTime.getText().toString());
        Log.e(TAG, "updateWorkSummary: " + sim_startTime + "===" + sim_endTime);
        if (etWriteReportFinishWork.getText().toString().equals("") && etWriteReportWorkSummary.getText().toString().equals("") && etWriteWriteWorkPlan.getText().toString().equals("")) {
            Toast.makeText(WorkSummary.this, "请至少输入一项", Toast.LENGTH_SHORT).show();
        } else {
            OkHttpUtils.post()
                    .url(Contents.SUMMARYMESSAGE)
                    .addParams("token", token)
                    .addParams("tid", tidBean.getData().getTid())
                    .addParams("title", etWriteReportFinishWork.getText().toString())
                    .addParams("content", etWriteReportWorkSummary.getText().toString())
                    .addParams("plan", etWriteWriteWorkPlan.getText().toString())
                    .addParams("start_time", sim_startTime)
                    .addParams("end_time", sim_endTime)
                    .addParams("approver", "," + str)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Request request, Exception e) {

                }

                @Override
                public void onResponse(String response) {
                    sp_save.clear();
                    finish();
                }
            });
        }

    }

    public static String getStr(List<String> strList) {
        String resultStr = "";
        if (strList != null && strList.size() > 0) {
            for (int i = 0; i < strList.size(); i++) {
                resultStr = resultStr + strList.get(i) + ',' + ',';
            }
            resultStr = resultStr.substring(0, resultStr.length() - 1);
        }
        return resultStr;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        listApprover_head_uid.clear();
        if (clickNum == 0) {
            sp_save.put("tv1", etWriteReportFinishWork.getText().toString());
            sp_save.put("tv2", etWriteReportWorkSummary.getText().toString());
            sp_save.put("tv3", etWriteWriteWorkPlan.getText().toString());
            sp_save.put("startTime", starttime);
            sp_save.put("endTime", endtime);

            OkHttpUtils.post()
                    .url("https://api.zhuantoukj.com/birck/index.php/Home/Company/deleteId")
                    .addParams("token", token)
                    .addParams("type", "tid")
                    .addParams("id", tidBean.getData().getTid())
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Request request, Exception e) {

                }

                @Override
                public void onResponse(String response) {

                }
            });

        }
    }

}
