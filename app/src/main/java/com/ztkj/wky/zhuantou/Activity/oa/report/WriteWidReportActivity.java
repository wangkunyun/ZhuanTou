package com.ztkj.wky.zhuantou.Activity.oa.report;

import android.content.Intent;
import android.os.Bundle;
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

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
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
import com.ztkj.wky.zhuantou.adapter.ShowPictureAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.WidIDBean;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class WriteWidReportActivity extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.tv_writeReport_Realtime)
    TextView tvWriteReportRealtime;
    @BindView(R.id.et_writeReport_finishWork)
    EditText etWriteReportFinishWork;
    @BindView(R.id.et_writeReportworkSummary)
    EditText etWriteReportworkSummary;
    @BindView(R.id.et_WriteWrite_workPlan)
    EditText etWriteWriteWorkPlan;
    @BindView(R.id.write_report_addImg)
    RelativeLayout writeReportAddImg;
    @BindView(R.id.re_write_report_imgs)
    RecyclerView reWriteReportImgs;
    @BindView(R.id.write_report_addFile)
    RelativeLayout writeReportAddFile;
    @BindView(R.id.re_write_report_files)
    RecyclerView reWriteReportFiles;
    @BindView(R.id.write_report_approver)
    RelativeLayout writeReportApprover;
    @BindView(R.id.re_write_report_approver)
    RecyclerView reWriteReportApprover;
    @BindView(R.id.btnSubmit_writeReport)
    Button btnSubmitWriteReport;

    private List<LocalMedia> selectList;//图片集合
    private ArrayList<String> listPictureName; //图片地址集合
    private ArrayList<NormalFile> list; //文件集合
    private ArrayList<String> ListPathName;//文件名称集合
    private String TAG = "WriteReport";
    private SharedPreferencesHelper sharedPreferencesHelper, sp_create_team;
    private int clickNum = 0;
    private String uid;
    private String token;
    private String cid;
    private Intent intent;
    private WidIDBean widIDBean;
    private ArrayList<String> Arr_approver = new ArrayList<>();
    private ArrayList<String> Arr_approver_uid = new ArrayList<>();
    private SharedPreferencesHelper listApprover_head_uid;
    private SharedPreferencesHelper sp_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_wid_report);
        ButterKnife.bind(this);

        //获取token和id
        sharedPreferencesHelper = new SharedPreferencesHelper(this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        //获取cid
        sp_create_team = new SharedPreferencesHelper(this, "Create_team");
        cid = (String) sp_create_team.getSharedPreference("Create_team_cid", "");

        sp_save = new SharedPreferencesHelper(this, "sp_save_WidReport");
        String tv_1 = (String) sp_save.getSharedPreference("tv1", "");
        String tv_2 = (String) sp_save.getSharedPreference("tv2", "");
        String tv_3 = (String) sp_save.getSharedPreference("tv3", "");
        if (!tv_1.isEmpty()) {
            etWriteReportFinishWork.setText(tv_1);
        }
        if (!tv_2.isEmpty()) {
            etWriteReportworkSummary.setText(tv_2);
        }
        if (!tv_3.isEmpty()) {
            etWriteWriteWorkPlan.setText(tv_3);
        }


        //解决滑动冲突
        reWriteReportImgs.setHasFixedSize(true);
        reWriteReportImgs.setNestedScrollingEnabled(false);
        reWriteReportFiles.setHasFixedSize(true);
        reWriteReportFiles.setNestedScrollingEnabled(false);
        reWriteReportApprover.setHasFixedSize(true);
        reWriteReportApprover.setNestedScrollingEnabled(false);
        request();

    }


    @Override
    protected void onResume() {
        super.onResume();

        listApprover_head_uid = new SharedPreferencesHelper(WriteWidReportActivity.this, "listApprover");
        String approver_head = (String) listApprover_head_uid.getSharedPreference("approver_head", "");
        String approver_uid = (String) listApprover_head_uid.getSharedPreference("approver_uid", "");


//        Log.e(TAG, "onResume:approver: " + approver_head + "======" + approver_uid);
        //去重并将数据放入集合中
        if (!approver_head.equals("")) {
            if (Arr_approver.size() == 0) {
                Arr_approver.add(approver_head);
            } else {
                if (!Arr_approver.contains(approver_head)) {
                    Arr_approver.add(approver_head);
                }
            }
        }
        //去重并将数据放入集合中
        if (!approver_uid.equals("")) {
            if (Arr_approver_uid.size() == 0) {
                Arr_approver_uid.add(approver_uid);
            } else {
                if (!Arr_approver_uid.contains(approver_uid)) {
                    Arr_approver_uid.add(approver_uid);
                }
            }
        }
        //实施保存
//        sp_append_head = Arr_approver.toString();
//        sp_append_uid = Arr_approver_uid.toString();
//        sava_summary.put("head", sp_append_head);
//        sava_summary.put("uid", sp_append_uid);


        //适配器
        ShowAdpoverAdapter showAdpoverAdapter = new ShowAdpoverAdapter(WriteWidReportActivity.this, Arr_approver);

//        ShowPictureAdapter showPictureAdapter = new ShowPictureAdapter(WriteWidReportActivity.this, Arr_approver);
        showAdpoverAdapter.notifyDataSetChanged();
        //布局管理器对象 参数1.上下文 2.规定一行显示几列的参数常量
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        //设置RecycleView显示的方向是水平还是垂直 GridLayout.HORIZONTAL水平  GridLayout.VERTICAL默认垂直
        gridLayoutManager.setOrientation(GridLayout.VERTICAL);
        reWriteReportApprover.setLayoutManager(gridLayoutManager);
        reWriteReportApprover.setAdapter(showAdpoverAdapter);

    }

    /**
     * 选择展示图片
     */
    private void show_picture() {
        PictureSelector.create(WriteWidReportActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .forResult(PictureConfig.CHOOSE_REQUEST);


    }

    /**
     * 选择展示文件
     */
    private void show_files() {
        Intent intentfiles = new Intent(WriteWidReportActivity.this, NormalFilePickActivity.class);
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
                 * 获取图片
                 */
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    listPictureName = new ArrayList<>();
                    for (int i = 0; i < selectList.size(); i++) {
                        listPictureName.add(selectList.get(i).getPath());
                    }
//                    ShowAdpoverAdapter showAdpoverAdapter = new ShowAdpoverAdapter(WriteWidReportActivity.this, listPictureName);
                    ShowPictureAdapter showPictureAdapter = new ShowPictureAdapter(WriteWidReportActivity.this, listPictureName);
                    showPictureAdapter.notifyDataSetChanged();
                    //布局管理器对象 参数1.上下文 2.规定一行显示几列的参数常量
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
                    //设置RecycleView显示的方向是水平还是垂直 GridLayout.HORIZONTAL水平  GridLayout.VERTICAL默认垂直
                    gridLayoutManager.setOrientation(GridLayout.VERTICAL);
                    reWriteReportImgs.setLayoutManager(gridLayoutManager);
                    reWriteReportImgs.setAdapter(showPictureAdapter);
                    updatePicture(widIDBean);
                    break;

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
                    ShowFilesAdapter showFilesAdapter = new ShowFilesAdapter(WriteWidReportActivity.this, ListPathName);
                    showFilesAdapter.notifyDataSetChanged();
                    reWriteReportFiles.setLayoutManager(new LinearLayoutManager(WriteWidReportActivity.this));
                    reWriteReportFiles.setAdapter(showFilesAdapter);
                    updateFiles(widIDBean);
                    break;
            }
        }
    }


    @OnClick({R.id.back, R.id.write_report_addImg, R.id.write_report_addFile, R.id.write_report_approver, R.id.btnSubmit_writeReport})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.write_report_addImg:
                show_picture();
                break;
            case R.id.write_report_addFile:
                show_files();
                break;
            case R.id.write_report_approver:
                intent = new Intent(WriteWidReportActivity.this, ChooseAdressActivity.class);
                startActivity(intent);
                break;
            case R.id.btnSubmit_writeReport:
                popuinit("是否确认提交", "取消 ", "确定");

                break;
        }
    }

    private void popuinit(String s, String s1, final String s2) {
        View contentView = LayoutInflater.from(WriteWidReportActivity.this).inflate(R.layout.pp_telephone, null);
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
        View rootview = LayoutInflater.from(WriteWidReportActivity.this).inflate(R.layout.activity_sz, null);

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
                updatereport(widIDBean);

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


    private void request() {
        //生成工作总结id
        OkHttpUtils.post()
                .url(Contents.WEEKID)
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
                widIDBean = new Gson().fromJson(response, WidIDBean.class);
                if (widIDBean.getErrno().equals("200")) {


                } else if (widIDBean.getErrno().equals("666666")) {
                    Toast.makeText(WriteWidReportActivity.this, "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                    JPushInterface.deleteAlias(WriteWidReportActivity.this, Integer.parseInt(uid));
                    sharedPreferencesHelper.clear();
                    SPUtils.getInstance().clear();
                    ActivityManager.getInstance().exit();
                    intent = new Intent(WriteWidReportActivity.this, NewLoginActivity.class);
                    startActivity(intent);
//                            getActivity().finish();
                } else {

                }

            }


        });
    }

    private void updateFiles(WidIDBean widIDBean) {
        //循环上传文件
        for (int i = 0; i < list.size(); i++) {
            String path = list.get(i).getPath();
            File file = new File(path);
            OkHttpUtils.post()
                    .url(Contents.UPDATEWEEKFILE)
                    .addParams("token", token)
                    .addParams("wid", widIDBean.getData().getWid())
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

    private void updatePicture(WidIDBean widIDBean) {
        //循环上传图片
        for (int i = 0; i < selectList.size(); i++) {
            String path = selectList.get(i).getPath();
            File file = new File(path);
            OkHttpUtils.post()
                    .url(Contents.UPDATEWEEKPIC)
                    .addParams("token", token)
                    .addParams("wid", widIDBean.getData().getWid())
                    .addFile("images", path, file)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Request request, Exception e) {

                }

                @Override
                public void onResponse(String response) {
                    Log.e(TAG, "onResponse: 循环里上传图片" + response);
                }
            });
        }
    }

    private void updatereport(WidIDBean widIDBean) {
        String str = getStr(Arr_approver_uid);
        Editable text = etWriteReportFinishWork.getText();
        Editable text1 = etWriteReportworkSummary.getText();
        Editable text2 = etWriteWriteWorkPlan.getText();
        Log.e(TAG, "updatereport: text====" + "-" + text + "-" + text1 + "-" + text2);

        if (etWriteReportFinishWork.getText().toString().equals("") && etWriteReportworkSummary.getText().toString().equals("") && etWriteWriteWorkPlan.getText().toString().equals("")) {
            Toast.makeText(WriteWidReportActivity.this, "请至少输入一项", Toast.LENGTH_SHORT).show();
        } else {
            OkHttpUtils.post()
                    .url(Contents.WEEKMESSAGE)
                    .addParams("token", token)
                    .addParams("uid", uid)
                    .addParams("wid", widIDBean.getData().getWid())
                    .addParams("weekly_completion", etWriteReportFinishWork.getText().toString())
                    .addParams("zhou_summary", etWriteReportworkSummary.getText().toString())
                    .addParams("weekly_plan", etWriteReportFinishWork.getText().toString())
                    .addParams("approver", "," + str)
                    .build().execute(new StringCallback() {
                @Override
                public void onError(Request request, Exception e) {

                }

                @Override
                public void onResponse(String response) {
                    Log.e(TAG, "onResponse: " + response);
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
            sp_save.put("tv2", etWriteReportworkSummary.getText().toString());
            sp_save.put("tv3", etWriteWriteWorkPlan.getText().toString());
            OkHttpUtils.post()
                    .url("https://api.zhuantoukj.com/birck/index.php/Home/Company/deleteId")
                    .addParams("token", token)
                    .addParams("type", "wid")
                    .addParams("id", widIDBean.getData().getWid())
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
