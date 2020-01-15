package com.ztkj.wky.zhuantou.Form;

import com.ztkj.wky.zhuantou.MvpBase.BasePresenter;

public class FormPresenter extends BasePresenter<FormConstract.FormIview,FormConstract.FormModel> {


    @Override
    public FormConstract.FormModel createModel() {
        return new FormModel();
    }

    public void getData(){
        getModel().requestData();
    }

}
