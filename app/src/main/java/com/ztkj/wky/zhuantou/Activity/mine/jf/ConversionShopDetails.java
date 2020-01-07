package com.ztkj.wky.zhuantou.Activity.mine.jf;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.Activity.live_shop.CreateAddressActivity;
import com.ztkj.wky.zhuantou.MyUtils.FileSave;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.AdressUpdateBean;
import com.ztkj.wky.zhuantou.bean.ComInfoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConversionShopDetails extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.toolbar)
    LinearLayout toolbar;
    @BindView(R.id.img_conversionPic)
    ImageView imgConversionPic;
    @BindView(R.id.tv_conversionTitle)
    TextView tvConversionTitle;
    @BindView(R.id.tv_conversionPrice)
    TextView tvConversionPrice;
    @BindView(R.id.tv_conversionIntroduce)
    TextView tvConversionIntroduce;
    @BindView(R.id.btn_conversion)
    Button btnConversion;
    private TextView receive_name, receive_phone, receive_address, tv_chooseAddress;
    private String com_id;
    private String TAG = "ConversionShopDetails";
    private String username, userphone, useraddress;
    private List<ComInfoBean.DataBean.CommodityCategoryListBean> commodity_category_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion_shop_details);
        ButterKnife.bind(this);
        layoutTitleTv.setText("我的砖头");
        com_id = getIntent().getStringExtra("com_id");


        request();
    }


    private void request() {
        OkHttpUtils.post().url(Contents.commodityInfo)
                .addParams("com_id", com_id)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "onResponse: " + response);
                ComInfoBean comInfoBean = GsonUtil.gsonToBean(response, ComInfoBean.class);
                if (comInfoBean.getErrno().equals("200")) {
                    ComInfoBean.DataBean data = comInfoBean.getData();
                    Glide.with(ConversionShopDetails.this).load(data.getCommodity_info().getCom_cover()).into(imgConversionPic);
                    tvConversionTitle.setText(data.getCommodity_info().getCom_name());
                    tvConversionPrice.setText(data.getCommodity_info().getCom_integral() + "砖头");
                    tvConversionIntroduce.setText(data.getCommodity_info().getCom_details());
                    commodity_category_list = data.getCommodity_category_list();
                    if (commodity_category_list.size() != 0) {
                        for (int i = 0; i < commodity_category_list.size(); i++) {
                            commodity_category_list.get(i).setSelect("false");
                        }
                        commodity_category_list.get(0).setSelect("true");
                    }
                }


            }
        });
    }

    public static void start(Context context, String com_id) {
        Intent starter = new Intent(context, ConversionShopDetails.class);
        starter.putExtra("com_id", com_id);
        context.startActivity(starter);
    }

    @OnClick({R.id.layout_back, R.id.btn_conversion})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                break;
            case R.id.btn_conversion:
                popuChoose();
                break;
        }
    }


    private void popuChoose() {
        View contentView = LayoutInflater.from(ConversionShopDetails.this).inflate(R.layout.pp_conversion, null);
        View rootview = LayoutInflater.from(ConversionShopDetails.this).inflate(R.layout.activity_conversion_shop_details, null);

        tv_chooseAddress = contentView.findViewById(R.id.tv_chooseAddress);
        AdressUpdateBean adressUpdateBean = (AdressUpdateBean) FileSave.read(this, "localUser");
        if (adressUpdateBean != null) {
            username = adressUpdateBean.getUsername();
            userphone = adressUpdateBean.getUserphone();
            useraddress = adressUpdateBean.getUseraddress();
            tv_chooseAddress.setVisibility(View.GONE);
        } else {
            tv_chooseAddress.setVisibility(View.VISIBLE);
        }

        receive_name = contentView.findViewById(R.id.receive_name);
        receive_phone = contentView.findViewById(R.id.receive_phone);
        receive_address = contentView.findViewById(R.id.receive_address);
        RelativeLayout rl_click_address = contentView.findViewById(R.id.rl_click_address);
        RecyclerView reViewAddress = contentView.findViewById(R.id.reViewAddress);
        Button btn_conversion = contentView.findViewById(R.id.btn_conversion);

        receive_name.setText(username);
        receive_phone.setText(userphone);
        receive_address.setText(useraddress);
        reViewAddress.setLayoutManager(new GridLayoutManager(ConversionShopDetails.this, 5));
        reViewAddress.setAdapter(new Adapter());

        //选择收货地址的点击事件
        rl_click_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConversionShopDetails.this, CreateAddressActivity.class);
                intent.putExtra("type", 1);
                startActivityForResult(intent, 2);
            }
        });


        //设置popuwindow是在父布局的哪个地方显示
        backgroundAlpha(0.6f);
        setViewDow(contentView, rootview);
    }

    private void setViewDow(View view, View rootview) {
        PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        window.setOutsideTouchable(true);
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

    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(ConversionShopDetails.this).inflate(R.layout.item_layout_conversion, viewGroup, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.tv_sku.setText(commodity_category_list.get(i).getName());
            if (commodity_category_list.get(i).getSelect().equals("true")) {
                viewHolder.rl_bg.setBackgroundResource(R.drawable.yuanjiaobtn_8dp_green2);
            } else {
                viewHolder.rl_bg.setBackgroundResource(R.drawable.yuanjiaobtn_8dp_grey);
            }

            viewHolder.rl_bg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    commodity_category_list.get(i).setSelect("true");
                }
            });
        }

        @Override
        public int getItemCount() {
            return commodity_category_list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView tv_sku;
            private RelativeLayout rl_bg;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_sku = itemView.findViewById(R.id.item_sku);
                rl_bg = itemView.findViewById(R.id.rl_bg);
            }
        }
    }
}
