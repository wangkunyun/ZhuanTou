package com.ztkj.wky.zhuantou.Form;

import com.ztkj.wky.zhuantou.MvpBase.Imodel;
import com.ztkj.wky.zhuantou.MvpBase.Iview;

public interface FormConstract {

     interface FormIview extends Iview {
        void showData();
    }

     interface FormModel extends Imodel {
        void requestData();
    }

}
