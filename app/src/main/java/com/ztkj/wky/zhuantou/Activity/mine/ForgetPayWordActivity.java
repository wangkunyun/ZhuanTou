package com.ztkj.wky.zhuantou.Activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ztkj.wky.zhuantou.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPayWordActivity extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.SetFingerprint)
    RelativeLayout SetFingerprint;
    @BindView(R.id.BankCardInfo)
    RelativeLayout BankCardInfo;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amend);
        ButterKnife.bind(this);




    }

    @OnClick({R.id.layout_back, R.id.SetFingerprint, R.id.BankCardInfo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.SetFingerprint:
                intent = new Intent(ForgetPayWordActivity.this,ForgetPayWordFingerPrint.class);
                startActivity(intent);
                break;
            case R.id.BankCardInfo:
                intent = new Intent(ForgetPayWordActivity.this,ForgetPayWord2Activity.class);
                startActivity(intent);
                break;
        }
    }
}
