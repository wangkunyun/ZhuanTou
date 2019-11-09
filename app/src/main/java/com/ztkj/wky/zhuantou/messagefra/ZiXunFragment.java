package com.ztkj.wky.zhuantou.messagefra;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.Colorstring;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.FenLeiBean;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;
import com.ztkj.wky.zhuantou.messagefra2.Blank1Fragment;
import com.ztkj.wky.zhuantou.messagefra2.Blank2Fragment;
import com.ztkj.wky.zhuantou.messagefra2.Blank3Fragment;
import com.ztkj.wky.zhuantou.messagefra2.Blank4Fragment;

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
public class ZiXunFragment extends Fragment {

    @BindView(R.id.zixun_btn1)
    RadioButton zixunBtn1;
    @BindView(R.id.zixun_btn2)
    RadioButton zixunBtn2;
    @BindView(R.id.zixun_btn3)
    RadioButton zixunBtn3;
    @BindView(R.id.zixun_btn4)
    RadioButton zixunBtn4;
    @BindView(R.id.zixun_group)
    RadioGroup zixunGroup;
    Unbinder unbinder;
    @BindView(R.id.zixun_fr)
    FrameLayout zixunFr;
    private int position = 0;

    private Blank1Fragment blank1Fragment;
    private Blank2Fragment blank2Fragment;
    private Blank3Fragment blank3Fragment;
    private Blank4Fragment blank4Fragment;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String uid,token;
    private Intent intent;
    private List <RadioButton> listb = new ArrayList<>();

    private String url = StringUtils.jiekouqianzui + "Article/articleClass";
    public ZiXunFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_zi_xun, container, false);
        unbinder = ButterKnife.bind(this, view);
        position = 0;
        setTabSelection(position);
        listb.add(zixunBtn1);
        listb.add(zixunBtn2);
        listb.add(zixunBtn3);
        listb.add(zixunBtn4);
        sharedPreferencesHelper = new SharedPreferencesHelper(getContext(), "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid","");
        token = (String) sharedPreferencesHelper.getSharedPreference("token","");
        gi();
        return view;
    }

    private void gi() {
        OkHttpUtils.post()
                .url(url)
                .addParams("", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }
                    @Override
                    public void onResponse(String response) {
                        Log.d("finezixun",response);
                        Gson gson = new Gson();
                        FenLeiBean fenLeiBean = gson.fromJson(response, FenLeiBean.class);
                        if (fenLeiBean.getErrno().equals("200")){
//                            for (int i = 0; i <listb.size(); i++) {
                                zixunBtn1.setText(fenLeiBean.getData().get(0).getSubordinate().get(0).getCl_name());
                                zixunBtn2.setText(fenLeiBean.getData().get(0).getSubordinate().get(1).getCl_name());
                                zixunBtn3.setText(fenLeiBean.getData().get(0).getSubordinate().get(2).getCl_name());
                                zixunBtn4.setText(fenLeiBean.getData().get(0).getSubordinate().get(3).getCl_name());
//                            }
//                            String cl_name = fenLeiBean.getData().get(0).getCl_name();
                        }else if (fenLeiBean.getErrno().equals("666666")){
                            Toast.makeText(getContext(),"您的账号已在其他手机登录，如非本人操作，请修改密码",Toast.LENGTH_LONG).show();
                            JPushInterface.deleteAlias(getContext(), Integer.parseInt(uid));
                            sharedPreferencesHelper.clear();
                            SPUtils.getInstance().clear();
                            ActivityManager.getInstance().exit();
                            intent = new Intent(getContext(), NewLoginActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }
                    }
                });
    }

    public void setTabSelection(int position) {
        //记录position
        this.position = position;
        //更改底部导航栏按钮状态
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
//        transaction.hide(shouYeFragment).hide(caiFuFragment).hide(jdFragment).hide(woDeFragment).commit();
        transaction = getChildFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                zixunBtn1.setTextColor(Color.parseColor(Colorstring.t3));
                zixunBtn2.setTextColor(Color.parseColor(Colorstring.t6));
                zixunBtn3.setTextColor(Color.parseColor(Colorstring.t6));
                zixunBtn4.setTextColor(Color.parseColor(Colorstring.t6));

                zixunBtn1.setBackgroundResource(R.drawable.alpha);
                zixunBtn2.setBackgroundColor(Color.parseColor(Colorstring.baise));
                zixunBtn3.setBackgroundColor(Color.parseColor(Colorstring.baise));
                zixunBtn4.setBackgroundColor(Color.parseColor(Colorstring.baise));
                zixunBtn1.setChecked(true);

                if (blank1Fragment == null) {
                    blank1Fragment = new Blank1Fragment();
                    transaction.add(R.id.zixun_fr, blank1Fragment);
                } else {
                    transaction.show(blank1Fragment);
                }
                break;
            case 1:
                zixunBtn1.setTextColor(Color.parseColor(Colorstring.t6));
                zixunBtn2.setTextColor(Color.parseColor(Colorstring.t3));
                zixunBtn3.setTextColor(Color.parseColor(Colorstring.t6));
                zixunBtn4.setTextColor(Color.parseColor(Colorstring.t6));

                zixunBtn2.setBackgroundResource(R.drawable.alpha);
                zixunBtn1.setBackgroundColor(Color.parseColor(Colorstring.baise));
                zixunBtn3.setBackgroundColor(Color.parseColor(Colorstring.baise));
                zixunBtn4.setBackgroundColor(Color.parseColor(Colorstring.baise));
                zixunBtn2.setChecked(true);

                if (blank2Fragment == null) {
                    blank2Fragment = new Blank2Fragment();
                    transaction.add(R.id.zixun_fr, blank2Fragment);
                } else {
                    transaction.show(blank2Fragment);
                }

                break;

            case 2:
                zixunBtn1.setTextColor(Color.parseColor(Colorstring.t6));
                zixunBtn2.setTextColor(Color.parseColor(Colorstring.t6));
                zixunBtn3.setTextColor(Color.parseColor(Colorstring.t3));
                zixunBtn4.setTextColor(Color.parseColor(Colorstring.t6));

                zixunBtn3.setBackgroundResource(R.drawable.alpha);
                zixunBtn1.setBackgroundColor(Color.parseColor(Colorstring.baise));
                zixunBtn2.setBackgroundColor(Color.parseColor(Colorstring.baise));
                zixunBtn4.setBackgroundColor(Color.parseColor(Colorstring.baise));
                zixunBtn3.setChecked(true);
                if (blank3Fragment == null) {
                    blank3Fragment = new Blank3Fragment();
                    transaction.add(R.id.zixun_fr, blank3Fragment);
                } else {
                    transaction.show(blank3Fragment);
                }
                break;
            case 3:
                zixunBtn1.setTextColor(Color.parseColor(Colorstring.t6));
                zixunBtn2.setTextColor(Color.parseColor(Colorstring.t6));
                zixunBtn3.setTextColor(Color.parseColor(Colorstring.t6));
                zixunBtn4.setTextColor(Color.parseColor(Colorstring.t3));

                zixunBtn4.setBackgroundResource(R.drawable.alpha);
                zixunBtn1.setBackgroundColor(Color.parseColor(Colorstring.baise));
                zixunBtn2.setBackgroundColor(Color.parseColor(Colorstring.baise));
                zixunBtn3.setBackgroundColor(Color.parseColor(Colorstring.baise));
                zixunBtn4.setChecked(true);

                if (blank4Fragment == null) {
                    blank4Fragment = new Blank4Fragment();
                    transaction.add(R.id.zixun_fr, blank4Fragment);
                } else {
                    transaction.show(blank4Fragment);
                }

                break;
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {

        if (blank1Fragment != null)
            transaction.hide(blank1Fragment);
        if (blank2Fragment != null)
            transaction.hide(blank2Fragment);
        if (blank3Fragment != null)
            transaction.hide(blank3Fragment);
        if (blank4Fragment != null)
            transaction.hide(blank4Fragment);

        transaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.zixun_btn1, R.id.zixun_btn2, R.id.zixun_btn3, R.id.zixun_btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zixun_btn1:
                position = 0;
                setTabSelection(position);
                break;
            case R.id.zixun_btn2:
                position = 1;
                setTabSelection(position);
                break;
            case R.id.zixun_btn3:
                position = 2;
                setTabSelection(position);
                break;
            case R.id.zixun_btn4:
                position = 3;
                setTabSelection(position);
                break;
        }
    }
}
