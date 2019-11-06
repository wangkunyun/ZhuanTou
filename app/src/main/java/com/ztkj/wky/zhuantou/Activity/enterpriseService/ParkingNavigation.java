package com.ztkj.wky.zhuantou.Activity.enterpriseService;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.MyUtils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.bean.AddressBookBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ParkingNavigation extends AppCompatActivity {


    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.icon_enter1)
    ImageView iconEnter1;
    @BindView(R.id.rl_click_parking_rates)
    RelativeLayout rlClickParkingRates;
    @BindView(R.id.re1)
    ImageView re1;
    @BindView(R.id.icon_enter2)
    ImageView iconEnter2;
    @BindView(R.id.rl_click_parking_fool1)
    RelativeLayout rlClickParkingFool1;
    @BindView(R.id.re2)
    LinearLayout re2;
    @BindView(R.id.icon_enter3)
    ImageView iconEnter3;
    @BindView(R.id.rl_click_parking_fool2)
    RelativeLayout rlClickParkingFool2;
    @BindView(R.id.re3)
    LinearLayout re3;
    @BindView(R.id.A)
    RelativeLayout A;
    @BindView(R.id.B)
    RelativeLayout B;
    @BindView(R.id.C)
    RelativeLayout C;
    @BindView(R.id.D)
    RelativeLayout D;
    @BindView(R.id.A1)
    RelativeLayout A1;
    @BindView(R.id.B1)
    RelativeLayout B1;
    @BindView(R.id.C1)
    RelativeLayout C1;
    @BindView(R.id.D1)
    RelativeLayout D1;
    private SharedPreferencesHelper sharedPreferencesHelper, sp_create_team;
    private String uid, token, cid, team_name, jurisdiction, phone;
    private List<AddressBookBean.DataBean> data;
    private boolean clickTag1 = false;
    private boolean clickTag2 = false;
    private boolean clickTag3 = false;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_navigation);
        ButterKnife.bind(this);
        layoutTitleTv.setText("停车导航");


    }

    @OnClick({R.id.layout_back, R.id.rl_click_parking_rates, R.id.rl_click_parking_fool1, R.id.rl_click_parking_fool2, R.id.A, R.id.B, R.id.C, R.id.D, R.id.A1, R.id.B1, R.id.C1, R.id.D1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.rl_click_parking_rates:
                if (clickTag1) {
                    iconEnter1.setImageResource(R.mipmap.icon_oa_enter);
                    re1.setVisibility(View.GONE);
                    clickTag1 = false;
                } else {
                    iconEnter1.setImageResource(R.mipmap.icon_oa_right);
                    re1.setVisibility(View.VISIBLE);
                    clickTag1 = true;
                }
                break;
            case R.id.rl_click_parking_fool1:
                if (clickTag2) {
                    iconEnter2.setImageResource(R.mipmap.icon_oa_enter);
                    re2.setVisibility(View.GONE);
                    clickTag2 = false;
                } else {
                    iconEnter2.setImageResource(R.mipmap.icon_oa_right);
                    re2.setVisibility(View.VISIBLE);
                    clickTag2 = true;
                }
                break;
            case R.id.rl_click_parking_fool2:
                if (clickTag3) {
                    iconEnter3.setImageResource(R.mipmap.icon_oa_enter);
                    re3.setVisibility(View.GONE);
                    clickTag3 = false;
                } else {
                    iconEnter3.setImageResource(R.mipmap.icon_oa_right);
                    re3.setVisibility(View.VISIBLE);
                    clickTag3 = true;
                }
                break;
            case R.id.A:
                intent = new Intent(this, H5ParkingNavigation.class);
                intent.putExtra("title", "地下负一层");
                intent.putExtra("H5Url", "0");
                startActivity(intent);
                break;
            case R.id.B:
                intent = new Intent(this, H5ParkingNavigation.class);
                intent.putExtra("title", "地下负一层");
                intent.putExtra("H5Url", "1");
                startActivity(intent);
                break;
            case R.id.C:
                intent = new Intent(this, H5ParkingNavigation.class);
                intent.putExtra("title", "地下负一层");
                intent.putExtra("H5Url", "2");
                startActivity(intent);
                break;
            case R.id.D:
                intent = new Intent(this, H5ParkingNavigation.class);
                intent.putExtra("title", "地下负一层");
                intent.putExtra("H5Url", "3");
                startActivity(intent);
                break;
            case R.id.A1:
                intent = new Intent(this, H5ParkingNavigation.class);
                intent.putExtra("title", "地下负二层");
                intent.putExtra("H5Url", "0");
                startActivity(intent);
                break;
            case R.id.B1:
                intent = new Intent(this, H5ParkingNavigation.class);
                intent.putExtra("title", "地下负二层");
                intent.putExtra("H5Url", "1");
                startActivity(intent);
                break;
            case R.id.C1:
                intent = new Intent(this, H5ParkingNavigation.class);
                intent.putExtra("title", "地下负二层");
                intent.putExtra("H5Url", "2");
                startActivity(intent);
                break;
            case R.id.D1:
                intent = new Intent(this, H5ParkingNavigation.class);
                intent.putExtra("title", "地下负二层");
                intent.putExtra("H5Url", "3");
                startActivity(intent);
                break;
        }
    }

}