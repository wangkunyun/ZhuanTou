package com.ztkj.wky.zhuantou.Activity.live_shop.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.ConfimOrderDetailAdapter;
import com.ztkj.wky.zhuantou.adapter.ResuseWayAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.BaseStatusBean;
import com.ztkj.wky.zhuantou.bean.OrderBean;
import com.ztkj.wky.zhuantou.bean.WxPayBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RefundActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.toolbar)
    LinearLayout toolbar;
    @BindView(R.id.order_pic)
    ImageView orderPic;
    @BindView(R.id.tv_order_name)
    TextView tvOrderName;
    @BindView(R.id.tv_select_reason)
    TextView tvSelectReason;
    @BindView(R.id.go_back)
    ImageView goBack;
    @BindView(R.id.tv_sr_money)
    TextView tv_sr_money;
    @BindView(R.id.btn_save_address)
    Button btnSaveAddress;
    @BindView(R.id.rela_select_address)
    RelativeLayout rela_select_address;
    @BindView(R.id.edi_sr_explain)
    EditText edi_sr_explain;
    @BindView(R.id.iv_refund_one)
    ImageView iv_refund_one;
    @BindView(R.id.iv_refund_two)
    ImageView iv_refund_two;
    @BindView(R.id.iv_refund_three)
    ImageView iv_refund_three;
    OrderBean.DataBean oreder;
    @BindView(R.id.tv_order_size)
    TextView tv_order_size;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.num_shop)
    TextView num_shop;
    @BindView(R.id.recycle_refund)
    RecyclerView recyleListRefund;
    ConfimOrderDetailAdapter confimOrderDetailAdapter;

    public static void start(Context context, OrderBean.DataBean orderbean) {
        Intent starter = new Intent(context, RefundActivity.class);
        starter.putExtra("order", orderbean);
        context.startActivity(starter);
    }

    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund);
        ButterKnife.bind(this);
        layoutTitleTv.setText("申请退款");
        recyleListRefund.setLayoutManager(new LinearLayoutManager(RefundActivity.this));
        confimOrderDetailAdapter = new ConfimOrderDetailAdapter(RefundActivity.this);
        recyleListRefund.setAdapter(confimOrderDetailAdapter);
        oreder = (OrderBean.DataBean) getIntent().getSerializableExtra("order");
        uid = SPUtils.getInstance().getString("uid");
        iv_refund_one.setOnClickListener(this);
        rela_select_address.setOnClickListener(this);
        btnSaveAddress.setOnClickListener(this);
        iv_refund_two.setOnClickListener(this);
        iv_refund_three.setOnClickListener(this);
        if (oreder != null && oreder.getArr().size() > 0) {
            confimOrderDetailAdapter.setData(oreder.getArr(), 1);
            sr_order_id = oreder.getSso_sub_order_number();
            for (int i = 0; i < oreder.getArr().size(); i++) {
                orderPriceDouble += Double.parseDouble(oreder.getArr().get(i).getSog_total_price());
            }

            tv_sr_money.setText(String.valueOf(orderPriceDouble));

        }
        initReason();
    }

    Double orderPriceDouble = 0.0;
    String sr_order_id;

    @OnClick({R.id.layout_back,})
    public void onViewClicked() {
        finish();
    }


    /**
     * 选择展示图片
     */
    private void show_picture() {
        PictureSelector.create(RefundActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }


    private void createRefundId() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.createRefundId)
                .addParams("uid", uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        wxPayBean = new Gson().fromJson(response, WxPayBean.class);
                        if (wxPayBean.getErrno().equals("200")) {
                            if (wxPayBean.getData().getSr_id() != null) {
                                if (oreder != null && oreder.getArr().size() > 0) {
                                    for (int i = 0; i < oreder.getArr().size(); i++) {
                                        createRefundInfo(oreder.getArr().get(i).getSog_id());
                                    }
                                }
                                createRefundCetifi();
                            }
                        } else {
                            ToastUtils.showShort(wxPayBean.getErrmsg());
                        }

                    }
                });
    }

    private void createRefundInfo(String sog_id) {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.addRefundInfo)
                .addParams("sr_id", wxPayBean.getData().getSr_id())
                .addParams("sr_money", String.valueOf(orderPriceDouble))
                .addParams("sr_reason", infoReson)
                .addParams("sr_explain", infodetail)
                .addParams("sog_id", sog_id)
                .addParams("sr_store_id",oreder.getSs_id())
                .addParams("sr_order_id", sr_order_id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }
                    @Override
                    public void onResponse(String response) {
                        BaseStatusBean statusBean = new Gson().fromJson(response, BaseStatusBean.class);
                        if (statusBean.getErrno().equals("200")) {

                        } else {
                            ToastUtils.showShort(statusBean.getErrmsg());
                        }
                    }
                });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rela_select_address:
                popuCoupon();
                break;
            case R.id.btn_save_address:
                infoMoney = tv_sr_money.getText().toString();
                if (infoReson == null || infoMoney == null || oreder == null) {
                    ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                    ToastUtils.showShort("请完善信息");
                    return;
                }
                if (listPath != null && listPath.size() < 1) {
                    ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                    ToastUtils.showShort("请上传凭证");
                    return;
                }
                infodetail = edi_sr_explain.getText().toString();
                if (infodetail == null) {
                    infodetail = "";
                }
                createRefundId();
                break;
            case R.id.iv_refund_one:
                picNum = 1;
                show_picture();
                break;
            case R.id.iv_refund_two:
                picNum = 2;
                show_picture();
                break;
            case R.id.iv_refund_three:
                picNum = 3;
                show_picture();
                break;
        }
    }

    int picNum;
    private List<LocalMedia> selectList;//图片集合
    List<String> listPath = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                /**
                 * 获取图片
                 */
                case PictureConfig.CHOOSE_REQUEST:
                    selectList = PictureSelector.obtainMultipleResult(data);
                    if (selectList != null && selectList.size() > 0) {
                        switch (picNum) {
                            case 1:
                                listPath.add(selectList.get(0).getPath());
                                Glide.with(RefundActivity.this).load(selectList.get(0).getPath()).into(iv_refund_one);
                                iv_refund_two.setVisibility(View.VISIBLE);
                                break;
                            case 2:
                                listPath.add(selectList.get(0).getPath());
                                Glide.with(RefundActivity.this).load(selectList.get(0).getPath()).into(iv_refund_two);
                                iv_refund_three.setVisibility(View.VISIBLE);
                                break;
                            case 3:
                                listPath.add(selectList.get(0).getPath());
                                Glide.with(RefundActivity.this).load(selectList.get(0).getPath()).into(iv_refund_three);
                                break;
                        }
                    }

                    break;

            }
        }


    }

    WxPayBean wxPayBean;


    private void createRefundCetifi() {
        for (int i = 0; i < listPath.size(); i++) {
            OkHttpUtils.post().url(Contents.SHOPBASE + Contents.addRefundCetifi)
                    .addParams("sr_id", wxPayBean.getData().getSr_id())
                    .addFile("files", listPath.get(i), new File(listPath.get(i)))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {

                        }

                        @Override
                        public void onResponse(String response) {
                            BaseStatusBean statusBean = new Gson().fromJson(response, BaseStatusBean.class);
                            if (statusBean.getErrno().equals("200")) {
                                ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                                ToastUtils.showShort("上传成功");
                            } else {
                                ToastUtils.showShort(statusBean.getErrmsg());
                            }
                        }
                    });
        }

    }

    List<String> listInfo = new ArrayList<>();
    String infoReson, infoMoney, infodetail;
    ResuseWayAdapter shopParamAdapter;

    private void popuCoupon() {

        View contentView = LayoutInflater.from(RefundActivity.this).inflate(R.layout.pp_shop_refuse_reason, null);
        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.6f);
        //下面是p里面的东西
        ListView lv = contentView.findViewById(R.id.lv);
        Button pp1_btn = contentView.findViewById(R.id.pp1_btn);
        shopParamAdapter = new ResuseWayAdapter(RefundActivity.this, listInfo);
        lv.setAdapter(shopParamAdapter);
        View rootview = LayoutInflater.from(RefundActivity.this).inflate(R.layout.activity_shop_detail, null);
        setViewDow(contentView, rootview);
        pp1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                infoReson = listInfo.get(i);
                tvSelectReason.setText(infoReson);
                shopParamAdapter = null;
                backgroundAlpha(1f);
                window.dismiss();
            }
        });

    }

    private void initReason() {
        listInfo.add("我不想要了");
        listInfo.add("商品信息填写错误");
        listInfo.add("地址填写错误");
        listInfo.add("其他");
    }

    PopupWindow window;

    private void setViewDow(View view, View rootview) {
        window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(false);
        window.setFocusable(false);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
                window.dismiss();
            }
        });
        window.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }
}
