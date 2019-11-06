package com.ztkj.wky.zhuantou.Activity.oa.punch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.base.Contents;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckingRangeActivity extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    private String radius = "0";
    private String TAG = "CheckingRangeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking_range);
        ButterKnife.bind(this);
        layoutTitleTv.setText("设置打卡范围");
//        more.setVisibility(View.VISIBLE);
//        more.setText("确定");

    }


    @OnClick({R.id.layout_back, R.id.range_100, R.id.range_200, R.id.range_300, R.id.range_400, R.id.range_500, R.id.range_1000, R.id.range_1500, R.id.range_2000, R.id.range_3000})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.range_100:
                radius = "100";
                request();
                break;
            case R.id.range_200:
                radius = "200";
                request();
                break;
            case R.id.range_300:
                radius = "300";
                request();
                break;
            case R.id.range_400:
                radius = "400";
                request();
                break;
            case R.id.range_500:
                radius = "500";
                request();
                break;
            case R.id.range_1000:
                radius = "1000";
                request();
                break;
            case R.id.range_1500:
                radius = "1500";
                request();
                break;
            case R.id.range_2000:
                radius = "2000";
                request();
                break;
            case R.id.range_3000:
                radius = "3000";
                request();
                break;
        }
    }

    private void request() {
        if (radius.equals("0")) {
            Toast.makeText(this, "请选择", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpUtils.post().url(Contents.AMENDPUNCHININFO)
                .addParams("token", SPUtils.getInstance().getString("token"))
                .addParams("phone", "13460720766")//SPUtils.getInstance().getString("phone")
                .addParams("cid", SPUtils.getInstance().getString("cid"))
                .addParams("radius", radius)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.get("errno").equals("200")) {
                        Toast.makeText(CheckingRangeActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
