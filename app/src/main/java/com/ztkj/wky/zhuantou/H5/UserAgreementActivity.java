package com.ztkj.wky.zhuantou.H5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.R;

public class UserAgreementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_agreement);
        ActivityManager.getInstance().addActivity(this);


    }
}
