package com.ztkj.wky.zhuantou.Activity.mine;
/**
 * 作者：wky
 * 功能描述：
 *  绑定车牌号
 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.ToastBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindingCarNum extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.et_CarNum)
    EditText etCarNum;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding_car_num);
        ButterKnife.bind(this);
        layoutTitleTv.setText("填写车牌号");
    }

    @OnClick({R.id.layout_back, R.id.btnSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.btnSubmit:
                request();
                break;
        }
    }

    private void request() {
        if (etCarNum.getText().toString().isEmpty()) {
            Toast.makeText(this, "请输入车牌号", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpUtils.post().url(Contents.BINDINGCAENUM)
                .addParams("token", SPUtils.getInstance().getString("token"))
                .addParams("uid", SPUtils.getInstance().getString("uid"))
                .addParams("license_plate", etCarNum.getText().toString())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                ToastBean toastBean = GsonUtil.gsonToBean(response, ToastBean.class);
                if (toastBean.getErrno().equals("200")) {
                    Toast.makeText(BindingCarNum.this, toastBean.getErrmsg(), Toast.LENGTH_SHORT).show();
                    finish();
                }
                Toast.makeText(BindingCarNum.this, toastBean.getErrmsg(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
