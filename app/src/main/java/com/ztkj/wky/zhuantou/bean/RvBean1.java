package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class RvBean1 {

    /**
     * errno : 200
     * errmsg : success
     * data : {"list":[{"aid":"1","title":"又一起！斯里兰卡部队在餐厅内引爆一个可疑包裹","introduce":"海外网4月24日电据斯里兰卡当地媒体News1st报道，斯里兰卡","cover":"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=4212289692,115634387&fm=173&app=25&f=JPEG?w=218&h=146&s=257059824E339BDC7ECDE09D03009082"},{"aid":"2","title":"又一起！斯里兰卡部队在餐厅内引爆一个可疑包裹","introduce":"海外网4月24日电据斯里兰卡当地媒体News1st报道，斯里兰卡","cover":"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=4212289692,115634387&fm=173&app=25&f=JPEG?w=218&h=146&s=257059824E339BDC7ECDE09D03009082"}],"count":"2"}
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
         * list : [{"aid":"1","title":"又一起！斯里兰卡部队在餐厅内引爆一个可疑包裹","introduce":"海外网4月24日电据斯里兰卡当地媒体News1st报道，斯里兰卡","cover":"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=4212289692,115634387&fm=173&app=25&f=JPEG?w=218&h=146&s=257059824E339BDC7ECDE09D03009082"},{"aid":"2","title":"又一起！斯里兰卡部队在餐厅内引爆一个可疑包裹","introduce":"海外网4月24日电据斯里兰卡当地媒体News1st报道，斯里兰卡","cover":"https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=4212289692,115634387&fm=173&app=25&f=JPEG?w=218&h=146&s=257059824E339BDC7ECDE09D03009082"}]
         * count : 2
         */

        private String count;
        private List<ListBean> list;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * aid : 1
             * title : 又一起！斯里兰卡部队在餐厅内引爆一个可疑包裹
             * introduce : 海外网4月24日电据斯里兰卡当地媒体News1st报道，斯里兰卡
             * cover : https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=4212289692,115634387&fm=173&app=25&f=JPEG?w=218&h=146&s=257059824E339BDC7ECDE09D03009082
             */

            private String aid;
            private String title;
            private String introduce;
            private String cover;
            private String popular;

            public String getPopular() {
                return popular;
            }

            public void setPopular(String popular) {
                this.popular = popular;
            }

            public String getAid() {
                return aid;
            }

            public void setAid(String aid) {
                this.aid = aid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }
        }
    }
}
