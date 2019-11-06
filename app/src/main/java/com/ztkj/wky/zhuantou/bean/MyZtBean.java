package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class MyZtBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : {"integral":"5","commodityList":[{"com_id":"1","com_name":"进口水果，超级好吃！","com_cover":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2771201017,181968610&fm=26&gp=0.jpg","com_integral":"10000"},{"com_id":"2","com_name":"进口木瓜，超级丰胸！","com_cover":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3076848760,3503526724&fm=26&gp=0.jpg","com_integral":"10000"},{"com_id":"3","com_name":"超可爱限量版布娃娃！","com_cover":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=813390167,246973185&fm=26&gp=0.jpg","com_integral":"10000"},{"com_id":"4","com_name":"优质鸭梨，贼甜！","com_cover":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1902948552,980620370&fm=26&gp=0.jpg","com_integral":"10000"},{"com_id":"5","com_name":"商品价格标签，防水防磨！","com_cover":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=25886958,366487728&fm=26&gp=0.jpg","com_integral":"10000"},{"com_id":"6","com_name":"2019新款旗袍，穿上贼漂亮！","com_cover":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3241906994,894863366&fm=26&gp=0.jpg","com_integral":"10000"}]}
     */

    private String errno;
    private String errmsg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * integral : 5
         * commodityList : [{"com_id":"1","com_name":"进口水果，超级好吃！","com_cover":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2771201017,181968610&fm=26&gp=0.jpg","com_integral":"10000"},{"com_id":"2","com_name":"进口木瓜，超级丰胸！","com_cover":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3076848760,3503526724&fm=26&gp=0.jpg","com_integral":"10000"},{"com_id":"3","com_name":"超可爱限量版布娃娃！","com_cover":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=813390167,246973185&fm=26&gp=0.jpg","com_integral":"10000"},{"com_id":"4","com_name":"优质鸭梨，贼甜！","com_cover":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1902948552,980620370&fm=26&gp=0.jpg","com_integral":"10000"},{"com_id":"5","com_name":"商品价格标签，防水防磨！","com_cover":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=25886958,366487728&fm=26&gp=0.jpg","com_integral":"10000"},{"com_id":"6","com_name":"2019新款旗袍，穿上贼漂亮！","com_cover":"https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3241906994,894863366&fm=26&gp=0.jpg","com_integral":"10000"}]
         */

        private String integral;
        private List<CommodityListBean> commodityList;

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public List<CommodityListBean> getCommodityList() {
            return commodityList;
        }

        public void setCommodityList(List<CommodityListBean> commodityList) {
            this.commodityList = commodityList;
        }

        public static class CommodityListBean {
            /**
             * com_id : 1
             * com_name : 进口水果，超级好吃！
             * com_cover : https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2771201017,181968610&fm=26&gp=0.jpg
             * com_integral : 10000
             */

            private String com_id;
            private String com_name;
            private String com_cover;
            private String com_integral;

            public String getCom_id() {
                return com_id;
            }

            public void setCom_id(String com_id) {
                this.com_id = com_id;
            }

            public String getCom_name() {
                return com_name;
            }

            public void setCom_name(String com_name) {
                this.com_name = com_name;
            }

            public String getCom_cover() {
                return com_cover;
            }

            public void setCom_cover(String com_cover) {
                this.com_cover = com_cover;
            }

            public String getCom_integral() {
                return com_integral;
            }

            public void setCom_integral(String com_integral) {
                this.com_integral = com_integral;
            }
        }
    }
}
