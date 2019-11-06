package com.ztkj.wky.zhuantou.n1fra;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.mine.MyWallet;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.GetUserMessageBean;
import com.ztkj.wky.zhuantou.isMy.GeRenActivity;
import com.ztkj.wky.zhuantou.isMy.JiFenActivity;
import com.ztkj.wky.zhuantou.isMy.JiLuActivity;
import com.ztkj.wky.zhuantou.isMy.SzActivity;
import com.ztkj.wky.zhuantou.landing.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class N3Fragment extends Fragment {


    @BindView(R.id.n3_headImg)
    ImageView n3HeadImg;
    @BindView(R.id.n3_name)
    TextView n3Name;
    @BindView(R.id.n3_kdimg)
    ImageView n3Kdimg;
    @BindView(R.id.n3_grzl)
    RelativeLayout n3Grzl;
    @BindView(R.id.n3_jifen)
    TextView n3Jifen;
    @BindView(R.id.n3_wdjf)
    RelativeLayout n3Wdjf;
    @BindView(R.id.n3_tab1)
    RelativeLayout n3Tab1;
    @BindView(R.id.n3_tab2)
    RelativeLayout n3Tab2;
    @BindView(R.id.n3_tab3)
    RelativeLayout n3Tab3;
    Unbinder unbinder;

    private Intent intent;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String uid, token;
    private String url = StringUtils.jiekouqianzui + "User/userInfo";
    private String TAG = "fragment_n3";

    public N3Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_n3, container, false);
        unbinder = ButterKnife.bind(this, view);

        sharedPreferencesHelper = new SharedPreferencesHelper(getContext(), "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferencesHelper = new SharedPreferencesHelper(getContext(), "anhua");
        uid = (String) sharedPreferencesHelper.getSharedPreference("uid", "");
        token = (String) sharedPreferencesHelper.getSharedPreference("token", "");

        if (!StringUtils.isEmpty(uid)) {
            gi(token);
        } else {
            gi("");
        }

    }

    private void gi(String token) {
        Log.e(TAG, "gi: " + uid);
        OkHttpUtils.post()
                .url(url)
                .addParams("uid", uid)
                .addParams("token", token)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse: " + response);
                        Gson gson = new Gson();
                        GetUserMessageBean getUserMessageBean = gson.fromJson(response, GetUserMessageBean.class);
                        if (getUserMessageBean.getErrno().equals("200")) {
                            sharedPreferencesHelper.put("username", getUserMessageBean.getData().getName());
                            sharedPreferencesHelper.put("integral", getUserMessageBean.getData().getIntegral());
                            sharedPreferencesHelper.put("sex", getUserMessageBean.getData().getSex());
                            sharedPreferencesHelper.put("address", getUserMessageBean.getData().getAddress());
                            sharedPreferencesHelper.put("head", getUserMessageBean.getData().getHead());
                            sharedPreferencesHelper.put("jifen", getUserMessageBean.getData().getIntegral());
                            sharedPreferencesHelper.put("reach", getUserMessageBean.getData().getReach());
                            SPUtils.getInstance().put("isrenzheng", getUserMessageBean.getData().getReal_name());
                            SPUtils.getInstance().put("realname", getUserMessageBean.getData().getName());
                            SPUtils.getInstance().put("balance", getUserMessageBean.getData().getBalance());
                            if ("0".equals(getUserMessageBean.getData().getUsername())) {
                                n3Name.setText("暂无昵称");
                            } else {
                                n3Name.setText(getUserMessageBean.getData().getUsername());
                            }
                            if ("0".equals(getUserMessageBean.getData().getHead())) {
                                n3HeadImg.setImageResource(R.drawable.head_portrait);
                            } else {
                                Glide.with(getContext()).load(getUserMessageBean.getData().getHead()).into(n3HeadImg);
                            }
                            n3Jifen.setText(getUserMessageBean.getData().getIntegral());

                        }
                    }
                });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        token = "";
        unbinder.unbind();
    }

    @OnClick({R.id.n3_grzl, R.id.n3_wdjf, R.id.n3_tab1, R.id.n3_tab2, R.id.n3_tab3, R.id.click_mine_face, R.id.click_mine_parking, R.id.click_mine_wallet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.n3_grzl:
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent(getContext(), GeRenActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.n3_wdjf:
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent(getContext(), JiFenActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.n3_tab1:
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent(getContext(), JiLuActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.n3_tab2:
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent(getContext(), SzActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.n3_tab3:
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                popuinit();
                break;
            case R.id.click_mine_face:
                Toast.makeText(getActivity(), "敬请期待！！！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.click_mine_wallet:
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent(getActivity(), MyWallet.class);
                startActivity(intent);
                break;
            case R.id.click_mine_parking: //停车缴费
//                if (StringUtils.isEmpty(uid)) {
//                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
//                    intent = new Intent(getActivity(), LoginActivity.class);
//                    startActivity(intent);
//                    return;
//                }
//                intent = new Intent(getContext(), ParkActivity.class);
//                getActivity().startActivity(intent);
                Toast.makeText(getActivity(), "敬请期待！！！", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    private void popuinit() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.pp_telephone, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.2f);
        //下面是p里面的东西
//        TextView ptextView = contentView.findViewById(R.id.ppt_tv1);
        Button pbutton = contentView.findViewById(R.id.ppt_btn1);
        Button pbutton2 = contentView.findViewById(R.id.ppt_btn2);
        final PopupWindow window = new PopupWindow(contentView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        View rootview = LayoutInflater.from(getContext()).inflate(R.layout.fragment_n3, null);
//        ptextView.setText("5338-9498");
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
                backgroundAlpha(1f);
                window.dismiss();
                intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + "53389498");
                intent.setData(data);
                startActivity(intent);
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
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);
    }

}
