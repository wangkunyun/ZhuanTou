package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class BannerBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"href":"http://www.baidu.com","banner":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1911150647,1930869014&fm=27&gp=0.jpg"},{"href":"http://www.baidu.com","banner":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1911150647,1930869014&fm=27&gp=0.jpg"},{"href":"http://www.baidu.com","banner":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1911150647,1930869014&fm=27&gp=0.jpg"}]
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
        @Override
        public String toString() {
            return "DataBean{" +
                    "href='" + href + '\'' +
                    ", banner='" + banner + '\'' +
                    '}';
        }

        /**
         * href : http://www.baidu.com
         * banner : https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1911150647,1930869014&fm=27&gp=0.jpg
         */

        private String href;
        private String banner;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }
    }

    @Override
    public String toString() {
        return "BannerBean{" +
                "errno='" + errno + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", data=" + data +
                '}';
    }
}
