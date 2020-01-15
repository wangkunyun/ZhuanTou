package com.ztkj.wky.zhuantou.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ztkj.wky.zhuantou.MvpBase.Iview;
import com.ztkj.wky.zhuantou.MvpBase.BasePresenter;

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity implements Iview {


    private P mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter == null) {
            mPresenter = createPresenter();
            mPresenter.attachView(this);
        }
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

//    protected abstract T createPresenter();

    protected abstract P createPresenter();
}
