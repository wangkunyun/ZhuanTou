package com.ztkj.wky.zhuantou.Activity.live_shop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GetJsonDataUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.AddressBean;
import com.ztkj.wky.zhuantou.bean.AdressUpdateBean;
import com.ztkj.wky.zhuantou.bean.JsonBean;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.qqtheme.framework.picker.OptionPicker;

public class EditAddressActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.btn_save_address)
    Button btnSaveAddress;
    @BindView(R.id.rela_select_address)
    RelativeLayout rela_select_address;
    private List<AddressBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    @BindView(R.id.tv_address)
    TextView tv_address;
    private static boolean isLoaded = false;
    int type;

    public static void start(Context context) {
        Intent starter = new Intent(context, EditAddressActivity.class);
        context.startActivity(starter);
    }

    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        ButterKnife.bind(this);
        uid = SPUtils.getInstance().getString("uid");
        type = getIntent().getIntExtra("type", 0);
        layoutBack.setOnClickListener(this);
        btnSaveAddress.setOnClickListener(this);
        rela_select_address.setOnClickListener(this);
        layoutTitleTv.setText("编辑收获地址");
        initJsonData();

    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了

                        Toast.makeText(EditAddressActivity.this, "Begin Parse Data", Toast.LENGTH_SHORT).show();
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 子线程中解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
                    Toast.makeText(EditAddressActivity.this, "Parse Succeed", Toast.LENGTH_SHORT).show();
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
                    Toast.makeText(EditAddressActivity.this, "Parse Failed", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private void showPickerView() {// 弹出选择器
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1).getPickerViewText() : "";

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";

                String opt3tx = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3) : "";

                String tx = opt1tx + opt2tx + opt3tx;
                tv_address.setText(tx);
                Toast.makeText(EditAddressActivity.this, tx, Toast.LENGTH_SHORT).show();
            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }


    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<AddressBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String cityName = jsonBean.get(i).getCityList().get(c).getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表
                city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(cityList);

            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }


    public ArrayList<AddressBean> parseData(String result) {//Gson 解析
        ArrayList<AddressBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                AddressBean entity = gson.fromJson(data.optJSONObject(i).toString(), AddressBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    String nameUser, phoneUser, addreUser, addressDetailUser;
    @BindView(R.id.edi_name)
    EditText edi_name;
    @BindView(R.id.edi_phone)
    EditText edi_phone;
    @BindView(R.id.address_detial)
    EditText address_detial;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                Intent intent = new Intent();
                setResult(1, intent);
                finish();
                break;
            case R.id.btn_save_address:
                if (uid != null) {
                    nameUser = edi_name.getText().toString();
                    phoneUser = edi_phone.getText().toString();
                    addreUser = tv_address.getText().toString();
                    addressDetailUser = address_detial.getText().toString();
                    if (TextUtils.isEmpty(nameUser)) {
                        ToastUtils.showShort("请填写姓名");
                        return;
                    }
                    if (TextUtils.isEmpty(phoneUser)) {
                        ToastUtils.showShort("请填写手机号");
                        return;
                    }
                    if (TextUtils.isEmpty(addreUser)) {
                        ToastUtils.showShort("请填写地址");
                        return;
                    }
                    if (TextUtils.isEmpty(addressDetailUser)) {
                        ToastUtils.showShort("请完善详细信息");
                        return;
                    }
                    allAddress = addreUser + addressDetailUser;
                    SaveAddress();
                }


                break;
            case R.id.rela_select_address:
                showPickerView();
                break;
        }
    }

    String allAddress;

    private void SaveAddress() {
        OkHttpUtils.post().url(Contents.SHOPBASE + Contents.addAddress)
                .addParams("sra_username", nameUser)
                .addParams("sra_address", allAddress)
                .addParams("sra_phone", phoneUser)
                .addParams("sra_user_id", uid)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtils.showShort(e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            AdressUpdateBean address = new AdressUpdateBean();
                            address.setUsername(nameUser);
                            address.setUserphone(phoneUser);
                            address.setUseraddress(allAddress);
                            Intent intent = new Intent();
                            intent.putExtra("user", address);
                            setResult(1, intent);
                            finish();
                        }

                    }
                });


    }

}
