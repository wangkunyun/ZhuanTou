package com.ztkj.wky.zhuantou.landing;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.hyphenate.easeui.utils.SharedPreferencesHelper;
import com.ztkj.wky.zhuantou.MainActivity;
import com.ztkj.wky.zhuantou.MyUtils.ActivityManager;
import com.ztkj.wky.zhuantou.MyUtils.MPermissionUtils;
import com.ztkj.wky.zhuantou.MyUtils.StatusBarUtil;
import com.ztkj.wky.zhuantou.R;

public class SplashActivity extends AppCompatActivity {
    private SharedPreferencesHelper sharedPreferencesHelper;
    private Boolean aBooleanid;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StatusBarUtil.setStatusBarMode(SplashActivity.this, true, R.color.white);
        ActivityManager.getInstance().addActivity(this);
        MPermissionUtils.requestPermissionsResult(this, 1,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.VIBRATE,
                        Manifest.permission.WAKE_LOCK,
                        Manifest.permission.SYSTEM_ALERT_WINDOW,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CALL_PHONE},
                //这个是悬浮窗设置 但是小米手机设置悬浮窗权限的时候要求必须要在设置里面打开
                new MPermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                    }

                    @Override
                    public void onPermissionDenied() {
                    }
                });

        sharedPreferencesHelper = new SharedPreferencesHelper(SplashActivity.this, "anhua");
        aBooleanid = sharedPreferencesHelper.contain("uid");

        final int time = 2500;    //设置等待时间，单位为毫秒
        final Handler handler = new Handler();

        if (aBooleanid) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, time);
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, time);

        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        MPermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
}
