package com.ztkj.wky.zhuantou.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SearchOriginBean implements Serializable {


    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"uid":"18","username":"王晓秋","phone":"18701165198","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-10-24/5db15d548c007.png","department_id":"0"},{"uid":"13","username":"郑伟","phone":"18618189090","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-08-01/5d42ba32f011d.png","department_id":"0"},{"uid":"11","username":"王坤云","phone":"15721695007","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-09-05/5d70cef697b8e.png","department_id":"0"},{"uid":"393","username":"刘晓彤","phone":"17011100007","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-12-31/5e0abf9b0a4b4.png","department_id":"0"},{"uid":"46","username":"陈天凯","phone":"18640195188","head":"0","department_id":"0"},{"uid":"412","username":"王靖雯","phone":"18600995711","head":"0","department_id":"0"},{"uid":"162","username":"吕松松","phone":"13460720766","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-12-19/5dfb269a6aec5.png","department_id":"1"},{"uid":"168","username":"王坤云2","phone":"13681413195","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-11-07/5dc3a93c3fb53.png","department_id":"0"},{"uid":"240","username":"曲义","phone":"15584339464","head":"0","department_id":"2"},{"uid":"238","username":"李美欣","phone":"18295780704","head":"0","department_id":"0"},{"uid":"369","username":"海荣","phone":"15501030414","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-11-12/5dca7e256b93b.png","department_id":"0"},{"uid":"415","username":"刘晓彤","phone":"13019705987","head":"https://api.zhuantoukj.com/birck/Public/heard/2019-11-09/5dc65ee0198f9.png","department_id":"0"},{"uid":"471","username":"冀永金","phone":"18552911994","head":"0","department_id":"0"},{"uid":"479","username":"张甫省","phone":"18811746451","head":"0","department_id":"0"},{"uid":"1","username":"王鹏杰","phone":"15313947598","head":"0","department_id":"0"}]
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
         * uid : 18
         * username : 王晓秋
         * phone : 18701165198
         * head : https://api.zhuantoukj.com/birck/Public/heard/2019-10-24/5db15d548c007.png
         * department_id : 0
         */

        private String uid;
        private String username;
        private String phone;
        private String head;
        private String department_id;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getDepartment_id() {
            return department_id;
        }

        public void setDepartment_id(String department_id) {
            this.department_id = department_id;
        }
    }
}
