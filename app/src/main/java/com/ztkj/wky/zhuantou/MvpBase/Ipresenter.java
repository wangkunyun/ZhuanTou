package com.ztkj.wky.zhuantou.MvpBase;

public interface Ipresenter<V extends Iview> {

    void attachView(V view);

    void detachView();


}
