package com.ztkj.wky.zhuantou.Activity.live_shop;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.FileSave;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.adapter.AddressAdapter;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.AddressListBean;
import com.ztkj.wky.zhuantou.bean.AdressUpdateBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAddressActivity extends AppCompatActivity implements View.OnClickListener, AddressAdapter.AdressListen {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.recycler_address)
    RecyclerView recycler_address;
    AddressAdapter addressAdapter;
    @BindView(R.id.btn_add_address)
    Button btn_add_address;
    String uid;
    int type;

    //type 1为商品页 2为设置页面
    public static void start(Context context, int type) {
        Intent starter = new Intent(context, CreateAddressActivity.class);
        starter.putExtra("type", type);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_address);
        ButterKnife.bind(this);
        uid = SPUtils.getInstance().getString("uid");
        type = getIntent().getIntExtra("type", 0);
        layoutBack.setOnClickListener(this);
        btn_add_address.setOnClickListener(this);
        layoutTitleTv.setText("收获地址");
        recycler_address.setLayoutManager(new LinearLayoutManager(this));
        addressAdapter = new AddressAdapter(CreateAddressActivity.this);
        recycler_address.setAdapter(addressAdapter);

    }

    List<AddressListBean.DataBean> list = new ArrayList<>();
    AddressListBean addressListBean;

    private void initData() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.addressList)
                .addParams("sra_user_id", uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            addressListBean = new Gson().fromJson(response, AddressListBean.class);
                            list = addressListBean.getData();
                            if (addressAdapter != null) {
                                addressAdapter.clearData();
                            }
                            addressAdapter.setData(list);
                            addressAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                Intent intent = new Intent();
                setResult(2, intent);
                finish();
                break;
            case R.id.btn_add_address:
                Intent intent1 = new Intent(CreateAddressActivity.this, EditAddressActivity.class);
                startActivityForResult(intent1, 2);
//                EditAddressActivity.start(CreateAddressActivity.this);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        updateBean = (AdressUpdateBean) data.getSerializableExtra("user");
        if (updateBean != null) {
            if (addressAdapter != null) {
                addressAdapter.clearData();
            }
            initData();
        } else {

        }
    }

    AdressUpdateBean updateBean;

    protected void WriteUser(AdressUpdateBean baseUser) {
        FileSave.write(CreateAddressActivity.this, baseUser, "localUser");
    }

    @Override
    public void setDataBean(AddressListBean.DataBean dataBean) {
        updateBean = new AdressUpdateBean();
        updateBean.setAddressId(dataBean.getSra_id());
        updateBean.setUserphone(dataBean.getSra_phone());
        updateBean.setUsername(dataBean.getSra_username());
        updateBean.setUseraddress(dataBean.getSra_address());
        updateBean.setSra_pca_address(dataBean.getSra_pca_address());
        updateBean.setSra_province_city_area(dataBean.getSra_province_city_area());
        WriteUser(updateBean);
        switch (type) {
            case 1:
                Intent intent = new Intent();
                intent.putExtra("user", updateBean);
                setResult(2, intent);
                finish();
                break;
            case 2:
                EditAddressActivity.start(CreateAddressActivity.this);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (uid != null) {
            initData();
        }

    }
}
