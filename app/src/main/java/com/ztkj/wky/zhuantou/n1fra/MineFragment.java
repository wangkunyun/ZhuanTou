package com.ztkj.wky.zhuantou.n1fra;


import android.annotation.SuppressLint;
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
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.live_shop.CollectShopActivity;
import com.ztkj.wky.zhuantou.Activity.live_shop.RecorderActivity;
import com.ztkj.wky.zhuantou.Activity.live_shop.order.OrderTabActivity;
import com.ztkj.wky.zhuantou.Activity.mine.MyWallet;
import com.ztkj.wky.zhuantou.Activity.mine.NotificationActivity;
import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MyUtils.StringUtils;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.CollecShopBean;
import com.ztkj.wky.zhuantou.bean.GetUserMessageBean;
import com.ztkj.wky.zhuantou.bean.RecorderBean;
import com.ztkj.wky.zhuantou.isMy.GeRenActivity;
import com.ztkj.wky.zhuantou.isMy.JiFenActivity;
import com.ztkj.wky.zhuantou.isMy.SzActivity;
import com.ztkj.wky.zhuantou.landing.NewLoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {


    //    @BindView(R.id.n3_headImg)
//    ImageView n3HeadImg;
//    @BindView(R.id.n3_name)
//    TextView n3Name;
//    @BindView(R.id.n3_kdimg)
//    ImageView n3Kdimg;
//    @BindView(R.id.n3_grzl)
//    RelativeLayout n3Grzl;
//    @BindView(R.id.n3_jifen)
//    TextView n3Jifen;
//    @BindView(R.id.n3_wdjf)
//    RelativeLayout n3Wdjf;
    //    @BindView(R.id.n3_tab1)
//    RelativeLayout n3Tab1;
//    @BindView(R.id.n3_tab2)
//    RelativeLayout n3Tab2;
//    @BindView(R.id.n3_tab3)
//    RelativeLayout n3Tab3;
    Unbinder unbinder;
    @BindView(R.id.clickMineScan)
    ImageView clickMineScan;
    @BindView(R.id.clickMineTongzhi)
    TextView clickMineTongzhi;
    @BindView(R.id.tvMinePersonName)
    TextView tvMinePersonName;
    @BindView(R.id.tvMineCompanyName)
    TextView tvMineCompanyName;
    @BindView(R.id.imgMinePersonHead)
    ImageView imgMinePersonHead;
    @BindView(R.id.clickMineGeren)
    RelativeLayout clickMineGeren;
    @BindView(R.id.clickImgSign)
    ImageView clickImgSign;
    @BindView(R.id.tvMineJF)
    TextView tvMineJF;
    @BindView(R.id.tvMineCollect)
    TextView tvMineCollect;
    @BindView(R.id.clickCollect)
    RelativeLayout clickCollect;
    @BindView(R.id.tvMineBrowsingHis)
    TextView tvMineBrowsingHis;
    @BindView(R.id.clickBrowsingHis)
    RelativeLayout clickBrowsingHis;
    @BindView(R.id.tvMineIntegral)
    TextView tvMineIntegral;
    @BindView(R.id.clickJF)
    RelativeLayout clickJF;
    @BindView(R.id.clickAllOrder)
    ImageView clickAllOrder;
    @BindView(R.id.clickWaitPay)
    ImageView clickWaitPay;
    @BindView(R.id.clickWaitSend)
    ImageView clickWaitSend;
    @BindView(R.id.clickWaitReceive)
    ImageView clickWaitReceive;
    @BindView(R.id.click_mine_wallet)
    RelativeLayout clickMineWallet;
    @BindView(R.id.n3_tab3)
    RelativeLayout n3Tab3;
    @BindView(R.id.click_mine_face)
    RelativeLayout clickMineFace;
    @BindView(R.id.click_mine_parking)
    RelativeLayout clickMineParking;
    @BindView(R.id.n3_tab2)
    RelativeLayout n3Tab2;

    private Intent intent;
    private SharedPreferencesHelper sharedPreferencesHelper;
    private String uid, token;
    private String url = StringUtils.jiekouqianzui + "User/userInfo";
    private String TAG = "fragment_n3";
    public static final int REQUEST_CODE = 111;

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
            initReorder();
            initCollectData();
        } else {
            gi("");
        }

    }

    RecorderBean recorderBean;
    int page = 1;
    CollecShopBean collecShopBean;

    private void initCollectData() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.getCollectList)
                .addParams("uid", uid)
                .addParams("page", String.valueOf(page))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        collecShopBean = new Gson().fromJson(response, CollecShopBean.class);
                        if (collecShopBean != null) {
                            if (collecShopBean.getErrno().equals("200")) {
                                if (collecShopBean.getData() != null) {
                                    int num = collecShopBean.getData().size();
                                    tvMineCollect.setText(String.valueOf(num));
                                } else {
                                    tvMineCollect.setText("0");
                                }

                            }
                        }
                    }
                });
    }

    private void initReorder() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.trajectoryList)
                .addParams("sf_user_id", uid)
                .addParams("page", String.valueOf(page))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            recorderBean = new Gson().fromJson(response, RecorderBean.class);
                            if (recorderBean.getErrno().equals("200")) {
                                if (recorderBean.getData() != null) {
                                    int num = recorderBean.getData().size();
                                    tvMineBrowsingHis.setText(String.valueOf(num));
                                } else {
                                    tvMineBrowsingHis.setText("0");
                                }

                            } else {
                                ToastUtils.showShort(recorderBean.getErrmsg());
                            }
                        }
                    }
                });
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

                    @SuppressLint("SetTextI18n")
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
                                tvMinePersonName.setText("暂无昵称");
                            } else {
                                tvMinePersonName.setText(getUserMessageBean.getData().getUsername());
                            }
                            if ("0".equals(getUserMessageBean.getData().getHead())) {
                                imgMinePersonHead.setImageResource(R.drawable.head_portrait);
                            } else {
                                //设置图片圆角角度
                                RoundedCorners roundedCorners = new RoundedCorners(96);
                                RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
                                Glide.with(getContext()).load(getUserMessageBean.getData().getHead())
                                        .apply(options)
                                        .into(imgMinePersonHead);
                            }
//                            tvMineJF.setText(getUserMessageBean.getData().getIntegral());

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


    @OnClick({R.id.clickMineScan, R.id.clickMineTongzhi, R.id.clickMineGeren, R.id.clickImgSign, R.id.clickCollect, R.id.clickBrowsingHis, R.id.clickJF, R.id.clickAllOrder, R.id.clickWaitPay, R.id.clickWaitSend, R.id.clickWaitReceive, R.id.click_mine_wallet, R.id.n3_tab3, R.id.click_mine_face, R.id.click_mine_parking, R.id.n3_tab2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.clickMineScan:
                intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.clickMineTongzhi: //通知
                NotificationActivity.start(getActivity());
                break;
            case R.id.clickMineGeren:
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), NewLoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent(getContext(), GeRenActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.clickImgSign:
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), NewLoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent(getContext(), JiFenActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.clickCollect: //收藏
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), NewLoginActivity.class);
                    startActivity(intent);
                    return;
                } else {
                    if (getActivity() != null) {
                        CollectShopActivity.start(getActivity());
                    }

                }
                break;
            case R.id.clickBrowsingHis: //浏览记录
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), NewLoginActivity.class);
                    startActivity(intent);
                    return;
                } else {
                    if (getActivity() != null) {
                        if (recorderBean != null) {
                            RecorderActivity.start(getActivity(), recorderBean);
                        }
                    }
                }
                break;
            case R.id.clickJF:
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), NewLoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent(getContext(), JiFenActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.clickAllOrder:
                OrderTabActivity.start(getActivity(), 0);
                break;
            case R.id.clickWaitPay:
                OrderTabActivity.start(getActivity(), 1);
                break;
            case R.id.clickWaitSend:
                OrderTabActivity.start(getActivity(), 2);
                break;
            case R.id.clickWaitReceive:
                OrderTabActivity.start(getActivity(), 3);
                break;
            case R.id.click_mine_wallet:
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), NewLoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent(getActivity(), MyWallet.class);
                startActivity(intent);
                break;
            case R.id.n3_tab3:
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), NewLoginActivity.class);
                    startActivity(intent);
                    return;
                }
                popuinit();
                break;
            case R.id.click_mine_face:
                Toast.makeText(getActivity(), "敬请期待", Toast.LENGTH_SHORT).show();
                break;
            case R.id.click_mine_parking:
                // if (StringUtils.isEmpty(uid)) {
//                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
//                    intent = new Intent(getActivity(), NewLoginActivity.class);
//                    startActivity(intent);
//                    return;
//                }
//                intent = new Intent(getContext(), ParkActivity.class);
//                getActivity().startActivity(intent);
                Toast.makeText(getActivity(), "敬请期待！！！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.n3_tab2:
                if (StringUtils.isEmpty(uid)) {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), NewLoginActivity.class);
                    startActivity(intent);
                    return;
                }
                intent = new Intent(getContext(), SzActivity.class);
                getActivity().startActivity(intent);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);

                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

    }

}
