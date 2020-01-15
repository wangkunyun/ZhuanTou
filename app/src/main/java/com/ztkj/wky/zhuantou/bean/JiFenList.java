package com.ztkj.wky.zhuantou.bean;

import java.io.Serializable;
import java.util.List;

public class JiFenList implements Serializable {


    /**
     * errno : 200
     * errmsg : 请求成功
     * data : [{"gid":"22","com_id":"24","gname":"电视机台灯","integral":"1100","addtime":"2019年12月30日 11:13","com_cover":"https://api.zhuantoukj.com/birck/Public/jifen/6.jpg"},{"gid":"21","com_id":"24","gname":"电视机台灯","integral":"1100","addtime":"2019年12月27日 13:34","com_cover":"https://api.zhuantoukj.com/birck/Public/jifen/6.jpg"},{"gid":"20","com_id":"24","gname":"电视机台灯","integral":"1100","addtime":"2019年12月27日 13:33","com_cover":"https://api.zhuantoukj.com/birck/Public/jifen/6.jpg"},{"gid":"19","com_id":"24","gname":"电视机台灯","integral":"1100","addtime":"2019年12月27日 13:32","com_cover":"https://api.zhuantoukj.com/birck/Public/jifen/6.jpg"},{"gid":"18","com_id":"24","gname":"电视机台灯","integral":"1100","addtime":"2019年12月27日 10:14","com_cover":"https://api.zhuantoukj.com/birck/Public/jifen/6.jpg"},{"gid":"17","com_id":"24","gname":"电视机台灯","integral":"1100","addtime":"2019年12月27日 10:10","com_cover":"https://api.zhuantoukj.com/birck/Public/jifen/6.jpg"},{"gid":"16","com_id":"24","gname":"电视机台灯","integral":"1100","addtime":"2019年12月27日 10:05","com_cover":"https://api.zhuantoukj.com/birck/Public/jifen/6.jpg"},{"gid":"15","com_id":"24","gname":"电视机台灯","integral":"1100","addtime":"2019年12月27日 09:57","com_cover":"https://api.zhuantoukj.com/birck/Public/jifen/6.jpg"},{"gid":"14","com_id":"24","gname":"电视机台灯","integral":"1100","addtime":"2019年12月27日 09:51","com_cover":"https://api.zhuantoukj.com/birck/Public/jifen/6.jpg"},{"gid":"13","com_id":"24","gname":"电视机台灯","integral":"1100","addtime":"2019年12月27日 09:48","com_cover":"https://api.zhuantoukj.com/birck/Public/jifen/6.jpg"},{"gid":"12","com_id":"24","gname":"电视机台灯","integral":"1100","addtime":"2019年12月27日 09:45","com_cover":"https://api.zhuantoukj.com/birck/Public/jifen/6.jpg"},{"gid":"11","com_id":"24","gname":"电视机台灯","integral":"1100","addtime":"2019年12月27日 09:39","com_cover":"https://api.zhuantoukj.com/birck/Public/jifen/6.jpg"},{"gid":"10","com_id":"24","gname":"电视机台灯","integral":"1100","addtime":"2019年12月27日 09:34","com_cover":"https://api.zhuantoukj.com/birck/Public/jifen/6.jpg"},{"gid":"9","com_id":"24","gname":"电视机台灯","integral":"1100","addtime":"2019年12月27日 09:27","com_cover":"https://api.zhuantoukj.com/birck/Public/jifen/6.jpg"},{"gid":"8","com_id":"24","gname":"电视机台灯","integral":"1100","addtime":"2019年12月27日 09:25","com_cover":"https://api.zhuantoukj.com/birck/Public/jifen/6.jpg"},{"gid":"7","com_id":"24","gname":"电视机台灯","integral":"1100","addtime":"2019年12月26日 18:01","com_cover":"https://api.zhuantoukj.com/birck/Public/jifen/6.jpg"},{"gid":"6","com_id":"24","gname":"电视机台灯","integral":"1100","addtime":"2019年12月26日 17:46","com_cover":"https://api.zhuantoukj.com/birck/Public/jifen/6.jpg"},{"gid":"5","com_id":"25","gname":"1800mAh直升机夹子风扇","integral":"1800","addtime":"2019年12月26日 15:48","com_cover":"https://api.zhuantoukj.com/birck/Public/jifen/7.jpg"},{"gid":"4","com_id":"25","gname":"1800mAh直升机夹子风扇","integral":"1800","addtime":"2019年12月26日 13:39","com_cover":"https://api.zhuantoukj.com/birck/Public/jifen/7.jpg"},{"gid":"3","com_id":"24","gname":"电视机台灯","integral":"1100","addtime":"2019年12月26日 13:37","com_cover":"https://api.zhuantoukj.com/birck/Public/jifen/6.jpg"}]
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

    public static class DataBean implements Serializable{
        /**
         * gid : 22
         * com_id : 24
         * gname : 电视机台灯
         * integral : 1100
         * addtime : 2019年12月30日 11:13
         * com_cover : https://api.zhuantoukj.com/birck/Public/jifen/6.jpg
         */

        private String gid;
        private String com_id;
        private String gname;
        private String integral;
        private String addtime;
        private String com_cover;

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getCom_id() {
            return com_id;
        }

        public void setCom_id(String com_id) {
            this.com_id = com_id;
        }

        public String getGname() {
            return gname;
        }

        public void setGname(String gname) {
            this.gname = gname;
        }

        public String getIntegral() {
            return integral;
        }

        public void setIntegral(String integral) {
            this.integral = integral;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getCom_cover() {
            return com_cover;
        }

        public void setCom_cover(String com_cover) {
            this.com_cover = com_cover;
        }
    }
}
