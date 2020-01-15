package com.ztkj.wky.zhuantou.MvpBase;

import com.ztkj.wky.zhuantou.MvpBase.Imodel;
import com.ztkj.wky.zhuantou.MvpBase.Ipresenter;
import com.ztkj.wky.zhuantou.MvpBase.Iview;

public abstract class BasePresenter<V extends Iview, M extends Imodel> implements Ipresenter<V> {

    private V view;
    private M model;


    public BasePresenter() {
        this.model = createModel();
    }


    public abstract M createModel();


    public M getModel() {
        return this.model;
    }


    public V getView() {
        if (this.view != null) {
            return this.view;
        } else {
            return null;
        }

    }

    @Override
    public void attachView(V view) {
        if (view != null) {
            this.view = view;
        } else {
            this.view = null;
        }

    }


    @Override
    public void detachView() {
        this.view = null;
    }
}
