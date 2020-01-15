package com.ztkj.wky.zhuantou.MvpBase;

public interface Iview {

    //显示loading
    void showLoading();

    //隐藏loading
    void hideLoading();

    //显示吐司
    void showError(String msg);
}
