package com.ztkj.wky.zhuantou.Activity.chat.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.ztkj.wky.zhuantou.Activity.oa.ManageCompany;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.AddressBookAdapter;
import com.ztkj.wky.zhuantou.adapter.AddressGroupAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.AddressBookBean;
import com.ztkj.wky.zhuantou.bean.QunListBean;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressTeam extends Fragment {

    @BindView(R.id.company_name)
    TextView companyName;
    @BindView(R.id.btn_manage_company)
    TextView btnManageCompany;
    @BindView(R.id.manage_company)
    LinearLayout manageCompany;
    @BindView(R.id.re_qun)
    RecyclerView reQun;
    @BindView(R.id.re)
    RecyclerView re;
    Unbinder unbinder;

    private SharedPreferencesHelper sharedPreferencesHelper, sp_create_team;
    private String uid, token, cid, team_name, jurisdiction, phone;
    private Intent intent;
    private String TAG = "AddressTeam";

    private List<AddressBookBean.DataBean> data;
    private List<QunListBean.DataBean.StayGroupBean> stayGroup;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_address_team, container, false);
        unbinder = ButterKnife.bind(this, view);

        re.setHasFixedSize(true);
        re.setNestedScrollingEnabled(false);
        reQun.setHasFixedSize(true);
        reQun.setNestedScrollingEnabled(false);

        sharedPreferencesHelper = new SharedPreferencesHelper(getActivity(), "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");
        phone = (String) sharedPreferencesHelper.getSharedPreference("phone", "");

        //获取cid
        sp_create_team = new SharedPreferencesHelper(getActivity(), "Create_team");
        cid = (String) sp_create_team.getSharedPreference("Create_team_cid", "");
        jurisdiction = (String) sp_create_team.getSharedPreference("Create_team_jurisdiction", "");
        team_name = (String) sp_create_team.getSharedPreference("Create_team_name", "");
        if (team_name.equals("0")) {
            companyName.setText("暂无团队");
        } else {
            companyName.setText(team_name);
            if (jurisdiction.equals("1")) {
                btnManageCompany.setVisibility(View.VISIBLE);
            } else {
                manageCompany.setEnabled(false);
            }
        }
        request_addressBook();
        request_qun();
        return view;
    }

    private void request_qun() {
//        Log.e(TAG, "request_qun: Phone" + phone);
        OkHttpUtils.post()
                .url(Contents.LISTQUN)
                .addParams("token", token)
                .addParams("phone", phone)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponseQun: " + response);
                        QunListBean qunListBean = new Gson().fromJson(response, QunListBean.class);
                        if (qunListBean.getErrno().equals("200")) {
                            QunListBean.DataBean data = qunListBean.getData();
                            stayGroup = data.getStayGroup();//这里获取的是我加入的的群
                            List<QunListBean.DataBean.MyGroupBean> myGroup = data.getMyGroup();
                            for (int i = 0; i < myGroup.size(); i++) {

                                QunListBean.DataBean.StayGroupBean bean = new QunListBean.DataBean.StayGroupBean(
                                        myGroup.get(i).getGroupid(),
                                        myGroup.get(i).getImgname(),
                                        myGroup.get(i).getImglogo(),
                                        myGroup.get(i).getImgintroduction(),
                                        myGroup.get(i).getAffiliations_count());
                                stayGroup.add(bean);

                            }

                            AddressGroupAdapter addressGroupAdapter = new AddressGroupAdapter(stayGroup, getActivity());
//                            AddressBookAdapter addressBookAdapter = new AddressBookAdapter(stayGroup, getActivity());
                            reQun.setLayoutManager(new LinearLayoutManager(getActivity()));
                            reQun.setAdapter(addressGroupAdapter);

                        } else if (qunListBean.getErrno().equals("666666")) {
                            Toast.makeText(getActivity(), "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(getActivity(), Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
                            ActivityManager.getInstance().exit();
                            intent = new Intent(getActivity(), NewLoginActivity.class);
                            startActivity(intent);
//                            getActivity().finish();
                        } else {

                        }
                    }
                });
    }

    private void request_addressBook() {

        OkHttpUtils.post()
                .url(Contents.COMPANYBOOK)
                .addParams("token", token)
                .addParams("phone", phone)
                .addParams("cid", cid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        AddressBookBean addressBookBean = new Gson().fromJson(response, AddressBookBean.class);

                        if (addressBookBean.getErrno().equals("200")) {
                            data = addressBookBean.getData();
                            AddressBookAdapter addressBookAdapter = new AddressBookAdapter(data, getActivity());
                            re.setLayoutManager(new LinearLayoutManager(getActivity()));
                            re.setAdapter(addressBookAdapter);

                        } else if (addressBookBean.getErrno().equals("666666")) {
                            Toast.makeText(getActivity(), "您的账号已在其他手机登录，如非本人操作，请修改密码", Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(getActivity(), Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
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

    @OnClick(R.id.btn_manage_company)
    public void onViewClicked() {
        intent = new Intent(getContext(), ManageCompany.class);
        startActivity(intent);
    }
}
