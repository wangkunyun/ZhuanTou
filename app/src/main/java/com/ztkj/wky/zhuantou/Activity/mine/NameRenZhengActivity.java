package com.ztkj.wky.zhuantou.Activity.mine;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.MyUtils.GsonUtil;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;
import com.ztkj.wky.zhuantou.bean.IsUpdateIdCardBean;
import com.ztkj.wky.zhuantou.bean.ToastBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NameRenZhengActivity extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_IdCard)
    EditText etIdCard;
    @BindView(R.id.click_updateIdCardPic)
    RelativeLayout clickUpdateIdCardPic;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    private Intent intent;
    private String TAG = "NameRenZhengActivity";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_ren_zheng);
        ButterKnife.bind(this);
        layoutTitleTv.setText("实名认证");

        if (!SPUtils.getInstance().getString("idCardName").isEmpty()) {
            etName.setText(SPUtils.getInstance().getString("idCardName"));
        }
        if (!SPUtils.getInstance().getString("idCardNum").isEmpty()) {
            String idCardNum = SPUtils.getInstance().getString("idCardNum");
            etIdCard.setText(idCardNum.substring(0, 1) + "****************" + idCardNum.substring(17, 18));
        }

        String name_renzheng = SPUtils.getInstance().getString("isrenzheng");
        if (name_renzheng.equals("0")) {
            btnSubmit.setVisibility(View.VISIBLE);
        } else {
            btnSubmit.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.layout_back, R.id.click_updateIdCardPic, R.id.btnSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.click_updateIdCardPic:

                break;
            case R.id.btnSubmit:
//                request();
                if (etName.getText().toString().equals("")) {
                    Toast.makeText(NameRenZhengActivity.this, "请输入您的名称", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (etIdCard.getText().toString().equals("")) {
                    Toast.makeText(NameRenZhengActivity.this, "请输入您的身份证号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent = new Intent(NameRenZhengActivity.this, UpdateIcCard.class);
                intent.putExtra("idCardName", etName.getText().toString());
                intent.putExtra("idCardNum", etIdCard.getText().toString());
                startActivity(intent);
                break;
        }
    }

    private void request() {
        OkHttpUtils.post().url(Contents.ISUPDATEIDCAED)
                .addParams("token", SPUtils.getInstance().getString("token"))
                .addParams("uid", SPUtils.getInstance().getString("uid"))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
//                Log.e(TAG, "onResponse: " + response);
                IsUpdateIdCardBean isUpdateIdCardBean = GsonUtil.gsonToBean(response, IsUpdateIdCardBean.class);
                String type = isUpdateIdCardBean.getData().getType();
                if (type.equals("2")) {
                    Toast.makeText(NameRenZhengActivity.this, "信息填写完整之后方可上传照片", Toast.LENGTH_SHORT).show();
                } else if (type.equals("1")) {
//                    if (etName.getText().toString().equals("")) {
//                        Toast.makeText(NameRenZhengActivity.this, "请输入您的名称", Toast.LENGTH_SHORT).show();
//                        return;
//                    } else {
//                        if (!etName.getText().toString().equals(SPUtils.getInstance().getString("idCardName"))) {
//                            Toast.makeText(NameRenZhengActivity.this, "您输入的名称与上传的身份证片有误", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                    }
//                    if (etIdCard.getText().toString().equals("")) {
//                        Toast.makeText(NameRenZhengActivity.this, "请输入您的身份证号码", Toast.LENGTH_SHORT).show();
//                        return;
//                    } else {
//                        if (!etIdCard.getText().toString().equals(SPUtils.getInstance().getString("idCardNum"))) {
//                            Toast.makeText(NameRenZhengActivity.this, "您输入的身份证号码与上传的身份证片有误", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                    }
                    OkHttpUtils.post().url(Contents.ADDIDCARD)
                            .addParams("token", SPUtils.getInstance().getString("token"))
                            .addParams("uid", SPUtils.getInstance().getString("uid"))
                            .addParams("IDnumber", etIdCard.getText().toString())
                            .addParams("username", etName.getText().toString())
                            .build().execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {

                        }

                        @Override
                        public void onResponse(String response) {
                            Log.e(TAG, "onResponse: " + response);
                            ToastBean toastBean = GsonUtil.gsonToBean(response, ToastBean.class);
                            if (toastBean.getErrno().equals("200")) {
                                Toast.makeText(NameRenZhengActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(NameRenZhengActivity.this, toastBean.getErrmsg(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
    }
}
