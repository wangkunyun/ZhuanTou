package com.ztkj.wky.zhuantou.Activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ztkj.wky.zhuantou.R;
import com.ztkj.wky.zhuantou.landing.SecurityCodeView2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetSetPayWord extends AppCompatActivity {

    @BindView(R.id.layout_back)
    ImageView layoutBack;
    @BindView(R.id.layout_title_tv)
    TextView layoutTitleTv;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.sz_toolbar)
    Toolbar szToolbar;
    @BindView(R.id.edit_payWord_security_code)
    SecurityCodeView2 editPayWordSecurityCode;
    @BindView(R.id.PayWord_next)
    Button PayWordNext;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_word);
        ButterKnife.bind(this);
        layoutTitleTv.setText("修改支付密码");
    }

    @OnClick({R.id.layout_back, R.id.PayWord_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.PayWord_next:
                String editContent = editPayWordSecurityCode.getEditContent();
                if (editContent.equals("")) {
                    Toast.makeText(this, "请输入支付密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent = new Intent(this, PayWord2Activity.class);
                intent.putExtra("PayWord", editContent);
                startActivity(intent);
                break;
        }
    }
}
