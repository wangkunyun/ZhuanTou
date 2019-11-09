package com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.apply.ApplyEvection;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.apply.ApplyGoOut;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.apply.ApplyLeave;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.apply.ApplyOutWork;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.apply.ApplyOverTime;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.apply.ApplyPayFor;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.apply.ApplyReimbursement;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.apply.ApplySeal;
import com.ztkj.wky.zhuantou.Activity.oa.examineAndapprove.apply.ApplyUseCar;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.ApplyRecenlyAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.AllApplyBean;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;

/**
 * A simple {@link Fragment} subclass.
 */
public class WriteApplyForFragment extends Fragment {


    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.ReRecently)
    RecyclerView ReRecently;
    @BindView(R.id.click_apply_leave)
    RelativeLayout clickApplyLeave;
    @BindView(R.id.click_apply_overtime)
    RelativeLayout clickApplyOvertime;
    @BindView(R.id.click_apply_evection)
    RelativeLayout clickApplyEvection;
    @BindView(R.id.click_apply_outworker)
    RelativeLayout clickApplyOutworker;
    @BindView(R.id.click_apply_goout)
    RelativeLayout clickApplyGoout;
    @BindView(R.id.click_apply_payfor)
    RelativeLayout clickApplyPayfor;
    @BindView(R.id.click_apply_reimbursement)
    LinearLayout clickApplyReimbursement;
    @BindView(R.id.click_apply_usercar)
    RelativeLayout clickApplyUsercar;
    private String TAG = "WriteApplyForFragment";
    private SharedPreferencesHelper sharedPreferencesHelper, sp_create_team, sp_apply;
    private String uid;
    private String token;
    private String cid;
    private ArrayList<String> recently;
    private int SizeAdapter;


    Unbinder unbinder;

    private Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_write_apply_for, container, false);
        unbinder = ButterKnife.bind(this, view);
        //获取token和id
        sharedPreferencesHelper = new SharedPreferencesHelper(getActivity(), "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        //获取cid
        sp_create_team = new SharedPreferencesHelper(getActivity(), "Create_team");
        cid = (String) sp_create_team.getSharedPreference("Create_team_cid", "");
        intent = new Intent();

        requestRecntly();


        return view;
    }

    private void requestRecntly() {
        OkHttpUtils.post()
                .url(Contents.ALLAPPLY)
                .addParams("token", token)
                .addParams("uid", uid)
                .addParams("cid", cid)
                .addParams("type", "0")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                AllApplyBean allApplyBean = new Gson().fromJson(response, AllApplyBean.class);
                if (allApplyBean.getErrno().equals("200")) {
                    if (!(allApplyBean.getData().size() == 0)) {
                        List<AllApplyBean.DataBean> data = allApplyBean.getData();
                        recently = new ArrayList<>();

                        for (int i = 0; i < data.size(); i++) {
                            if (!recently.contains(data.get(i).getType())) {
                                recently.add(data.get(i).getType());
                            }
                        }
                        if (recently.size() > 5) {
                            SizeAdapter = 5;
                        } else {
                            SizeAdapter = recently.size();
                        }
                        Log.e(TAG, "onResponse: " + recently.size());
                        Log.e(TAG, "onResponse: " + recently);
                        ApplyRecenlyAdapter applyRecenlyAdapter = new ApplyRecenlyAdapter(getActivity(), recently, SizeAdapter);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 5);
                        //设置RecycleView显示的方向是水平还是垂直 GridLayout.HORIZONTAL水平  GridLayout.VERTICAL默认垂直
                        gridLayoutManager.setOrientation(GridLayout.VERTICAL);
                        ReRecently.setLayoutManager(gridLayoutManager);
                        ReRecently.setAdapter(applyRecenlyAdapter);
                    }

                } else if (allApplyBean.getErrno().equals("666666")) {
                    Toast.makeText(getActivity(), "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                    JPushInterface.deleteAlias(getActivity(), Integer.parseInt(uid));
                    sharedPreferencesHelper.clear();
                    SPUtils.getInstance().clear();
                    ActivityManager.getInstance().exit();
                    intent = new Intent(getActivity(), NewLoginActivity.class);
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

    @OnClick({R.id.layout_back, R.id.click_apply_leave, R.id.click_apply_overtime, R.id.click_apply_evection, R.id.click_apply_outworker, R.id.click_apply_goout, R.id.click_apply_payfor, R.id.click_apply_reimbursement, R.id.click_apply_usercar, R.id.click_apply_seal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back: //返回
                getActivity().finish();
                break;
            case R.id.click_apply_leave://请假
                intent.setClass(getContext(), ApplyLeave.class);
                startActivity(intent);
                break;
            case R.id.click_apply_overtime://加班
                intent.setClass(getContext(), ApplyOverTime.class);
                startActivity(intent);
                break;
            case R.id.click_apply_evection://出差
                intent.setClass(getContext(), ApplyEvection.class);
                startActivity(intent);
                break;
            case R.id.click_apply_outworker://外勤
                intent.setClass(getContext(), ApplyOutWork.class);
                startActivity(intent);
                break;
            case R.id.click_apply_goout://外出
                intent.setClass(getContext(), ApplyGoOut.class);
                startActivity(intent);
                break;
            case R.id.click_apply_payfor://费用报销
                intent.setClass(getContext(), ApplyReimbursement.class);
                startActivity(intent);
                break;
            case R.id.click_apply_reimbursement://付款申请
                intent.setClass(getContext(), ApplyPayFor.class);
                startActivity(intent);
                break;
            case R.id.click_apply_usercar://用车申请
                intent.setClass(getContext(), ApplyUseCar.class);
                startActivity(intent);
                break;
            case R.id.click_apply_seal://用印申请
                intent.setClass(getContext(), ApplySeal.class);
                startActivity(intent);
                break;
        }
    }
}
