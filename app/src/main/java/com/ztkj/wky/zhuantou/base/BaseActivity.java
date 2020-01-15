package com.ztkj.wky.zhuantou.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {


    private Unbinder unbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        if (unbinder == null) {
            unbinder = ButterKnife.bind(this);
        }
        initView();
        initListen();
        iniData();
    }

    public void startActivity(Class<?> tClass, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, tClass);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        startActivity(intent);
    }

    public void startForResulet(Class<?> t, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, t);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    public abstract void initView();

    public abstract void initListen();

    public abstract void iniData();

    public abstract int getLayout();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
