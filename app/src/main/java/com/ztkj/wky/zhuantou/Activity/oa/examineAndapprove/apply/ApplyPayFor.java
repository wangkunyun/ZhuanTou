package com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.apply;

/**
 * 作者：wky
 * 功能描述：
 * 费用申请
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.oa.report.ChooseAdressActivity;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.ShowAdpoverAdapter;
import com.ztkj.wky.zhuantou.base.Contents;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApplyPayFor extends AppCompatActivity {

    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.reimbursementAmount)
    EditText reimbursementAmount;
    @BindView(R.id.dt_reimbursementDetails)
    EditText dtReimbursementDetails;
    @BindView(R.id.re_approver)
    RecyclerView reApprover;
    @BindView(R.id.re_approver2)
    RecyclerView reApprover2;
    @BindView(R.id.btnSubmit_leave)
    Button btnSubmitLeave;
    @BindView(R.id.et_reimbursementType)
    EditText etReimbursementType;


    private SharedPreferencesHelper sharedPreferencesHelper, sp_create_team, sp_save;
    private SharedPreferencesHelper listApprover_head_uid;
    private String approverTag = "";
    private String TAG = "ApplyPayFor";
    private Intent intent;
    private String uid;
    private String token;
    private String cid;
    private int clickNum = 0;
    private ArrayList<String> Arr_approver = new ArrayList<>();
    private ArrayList<String> Arr_approver2 = new ArrayList<>();
    private ArrayList<String> Arr_approver_uid = new ArrayList<>();
    private ArrayList<String> Arr_approver_uid2 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_pay_for);
        ButterKnife.bind(this);

        layoutTitleTv.setText("报销申请");
        intent = new Intent();

        //获取token和id
        sharedPreferencesHelper = new SharedPreferencesHelper(this, "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        //获取cid
        sp_create_team = new SharedPreferencesHelper(this, "Create_team");
        cid = (String) sp_create_team.getSharedPreference("Create_team_cid", "");

        //实时保存
        sp_save = new SharedPreferencesHelper(this, "sp_save_PayFor");
        String tv_1 = (String) sp_save.getSharedPreference("tv1", "");
        String tv_2 = (String) sp_save.getSharedPreference("tv2", "");
        String tv_3 = (String) sp_save.getSharedPreference("tv3", "");
        if (!tv_1.isEmpty()) {
            reimbursementAmount.setText(tv_1);
        }
        if (!tv_2.isEmpty()) {
            etReimbursementType.setText(tv_2);
        }
        if (!tv_3.isEmpty()) {
            dtReimbursementDetails.setText(tv_3);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: TAG,TAG" + approverTag);
        //从联系人跳过来的sp里的审批人id和
        listApprover_head_uid = new SharedPreferencesHelper(ApplyPayFor.this, "listApprover");
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
                    //审批人只能是一个人，所以不用做去重，当集合不等于0时给提示
//                    if (!Arr_approver.contains(approver_head)) {
//                        Arr_approver.add(approver_head);
//                    }
                    Toast.makeText(this, "只能有一个审批人", Toast.LENGTH_SHORT).show();
                }
            }

            //去重并将数据放入集合中  审批人id
            if (!approver_uid.equals("")) {
                if (Arr_approver_uid.size() == 0) {
                    Arr_approver_uid.add(approver_uid);
                } else {
//                    if (!Arr_approver_uid.contains(approver_uid)) {
//                        Arr_approver_uid.add(approver_uid);
//                    }
                }
            }
        } else if (approverTag.equals("1")) {
            //        Log.e(TAG, "onResume:approver: " + approver_head + "======" + approver_uid);
            //去重并将数据放入集合中  审批人头像
            if (!Arr_approver.isEmpty()) { //如果审批人集合不为空，为空则提示添加审批人


                if (!approver_head.equals(Arr_approver.get(0))) { //如果抄送人不包含审批人,包含则提示审批人不能是抄送人
                    if (!approver_head.equals("")) { //如果选中联系人不为空并且集合
                        if (Arr_approver2.size() == 0) {
                            Arr_approver2.add(approver_head);
                        } else {
                            if (!Arr_approver2.contains(approver_head)) {
                                Arr_approver2.add(approver_head);
                            }
                        }
                    }
                } else {
                    Toast.makeText(this, "审批人不能为抄送人", Toast.LENGTH_SHORT).show();
                }


            } else {
                Toast.makeText(this, "请添加审批人", Toast.LENGTH_SHORT).show();
            }


            //去重并将数据放入集合中  审批人id
            //去重并将数据放入集合中  审批人头像
            if (!Arr_approver_uid.isEmpty()) { //如果审批人集合不为空，为空则提示添加审批人
                if (!approver_uid.equals(Arr_approver_uid.get(0))) { //如果抄送人不包含审批人,包含则提示审批人不能是抄送人
                    if (!approver_uid.equals("")) { //如果选中联系人不为空并且集合
                        if (Arr_approver_uid2.size() == 0) {
                            Arr_approver_uid2.add(approver_uid);
                        } else {
                            if (!Arr_approver_uid2.contains(approver_uid)) {
                                Arr_approver_uid2.add(approver_uid);
                            }
                        }
                    }
                }
            }
        }


        //适配器
        ShowAdpoverAdapter showAdpoverAdapter = new ShowAdpoverAdapter(ApplyPayFor.this, Arr_approver);

//        ShowPictureAdapter showPictureAdapter = new ShowPictureAdapter(WorkSummary.this, Arr_approver);
        showAdpoverAdapter.notifyDataSetChanged();
        //布局管理器对象 参数1.上下文 2.规定一行显示几列的参数常量
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        //设置RecycleView显示的方向是水平还是垂直 GridLayout.HORIZONTAL水平  GridLayout.VERTICAL默认垂直
        gridLayoutManager.setOrientation(GridLayout.VERTICAL);
        reApprover.setLayoutManager(gridLayoutManager);
        reApprover.setAdapter(showAdpoverAdapter);

        //适配器
        ShowAdpoverAdapter showPictureAdapter2 = new ShowAdpoverAdapter(ApplyPayFor.this, Arr_approver2);

//        ShowPictureAdapter showPictureAdapter2 = new ShowPictureAdapter(WorkSummary.this, Arr_approver2);
        showAdpoverAdapter.notifyDataSetChanged();
        //布局管理器对象 参数1.上下文 2.规定一行显示几列的参数常量
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this, 5);
        //设置RecycleView显示的方向是水平还是垂直 GridLayout.HORIZONTAL水平  GridLayout.VERTICAL默认垂直
        gridLayoutManager.setOrientation(GridLayout.VERTICAL);
        reApprover2.setLayoutManager(gridLayoutManager2);
        reApprover2.setAdapter(showPictureAdapter2);

    }


    @OnClick({R.id.layout_back, R.id.click_rl_reimbursementType, R.id.click_rl_addApppover, R.id.click_rl_addApppover2, R.id.btnSubmit_leave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.click_rl_addApppover:
                approverTag = "0";
                intent = new Intent(ApplyPayFor.this, ChooseAdressActivity.class);
                startActivity(intent);
                break;
            case R.id.click_rl_addApppover2:
                approverTag = "1";
                intent = new Intent(ApplyPayFor.this, ChooseAdressActivity.class);
                startActivity(intent);
                break;
            case R.id.btnSubmit_leave:
                updateRequest();
                break;
        }
    }

    private void updateRequest() {

        String str1 = getStr(Arr_approver_uid);
        String str2 = getStr(Arr_approver_uid2);
        if (reimbursementAmount.getText().toString().isEmpty()) {
            Toast.makeText(this, "请输入报销金额", Toast.LENGTH_SHORT).show();
            return;
        }
        if (etReimbursementType.getText().toString().isEmpty()) {
            Toast.makeText(this, "请输入报销类型", Toast.LENGTH_SHORT).show();
            return;
        }
        if (dtReimbursementDetails.getText().toString().isEmpty()) {
            Toast.makeText(this, "请输入报销说明", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Arr_approver.isEmpty()) {
            Toast.makeText(this, "请选择审批人", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Arr_approver2.isEmpty()) {
            Toast.makeText(this, "请选择抄送人", Toast.LENGTH_SHORT).show();
            return;
        }

        // Log.e(TAG, "updateRequest: " + "==" + token + "==" + uid + "==" + cid + "==" + updatestartTime + "==" + updateendTime + "==" + l_time + "==" + etReasonForLeave.getText().toString());
        OkHttpUtils.post().url(Contents.REIMBURSEMENT)
                .addParams("token", token)
                .addParams("uid", uid)
                .addParams("cid", cid)
                .addParams("total_money", reimbursementAmount.getText().toString())
                .addParams("category", etReimbursementType.getText().toString())
                .addParams("detailed", dtReimbursementDetails.getText().toString())
                .addParams("approver", Arr_approver_uid.get(0))
                .addParams("copier", "," + str2)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.e(TAG, "onResponse: " + jsonObject.get("errno"));
                    if (jsonObject.get("errno").equals("200")) {
                        clickNum = clickNum + 1;
                        sp_save.clear();
                        Toast.makeText(ApplyPayFor.this, "提交成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


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
            sp_save.put("tv1", reimbursementAmount.getText().toString());
            sp_save.put("tv2", etReimbursementType.getText().toString());
            sp_save.put("tv3", dtReimbursementDetails.getText().toString());
        }
    }

}
