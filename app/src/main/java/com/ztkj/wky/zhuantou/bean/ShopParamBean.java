package com.ztkj.wky.zhuantou.bean;

import java.io.Serializable;
import java.util.List;

public class ShopParamBean implements Serializable {


    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"sp_id":"1","sp_name":"品牌","arr":{"sp_id":"2","sp_name":"劳力士"}},{"sp_id":"3","sp_name":"型号","arr":{"sp_id":"4","sp_name":"RE123123"}}]
     */

    private String errno;
    private String errmsg;
    private List<DataBean> data;

    public String getErrno() {
        return errno;
    }

    public void setErrno(String errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sp_id : 1
         * sp_name : 品牌
         * arr : {"sp_id":"2","sp_name":"劳力士"}
         */

        private String sp_id;
        private String sp_name;
        private ArrBean arr;

        public String getSp_id() {
            return sp_id;
        }

        public void setSp_id(String sp_id) {
            this.sp_id = sp_id;
        }

        public String getSp_name() {
            return sp_name;
        }

        public void setSp_name(String sp_name) {
            this.sp_name = sp_name;
        }

        public ArrBean getArr() {
            return arr;
        }

        public void setArr(ArrBean arr) {
            this.arr = arr;
        }

        public static class ArrBean {
            /**
             * sp_id : 2
             * sp_name : 劳力士
             */

            private String sp_id;
            private String sp_name;

            public String getSp_id() {
                return sp_id;
            }

            public void setSp_id(String sp_id) {
                this.sp_id = sp_id;
            }

            public String getSp_name() {
                return sp_name;
            }

            public void setSp_name(String sp_name) {
                this.sp_name = sp_name;
            }
        }
    }
}
