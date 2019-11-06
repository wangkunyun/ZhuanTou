package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class MeetingListBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"meid":"6","times":"1569313199","title":"标题啊","content":"您lolX5我快中冷线路图痛苦里去啦8安老子在吉林来咯啦在咯无语咯lol哦咯lol您咯搜嘎你今KKK考虑","uid":"11","addtime":"2019-09-24 16:19:59"},{"meid":"5","times":"1569312293","title":"开会内容","content":"信了lol哦咯lol哦or您肉丸哦哦肉麻诺投资儿女月哦揉胸哦揉胸哦肉OK呀哦skin咯诺累哦erotomanicosmund诺投资osmund","uid":"11","addtime":"2019-09-24 16:04:53"},{"meid":"4","times":"1569312177","title":"小砖3.0概要","content":"今天主要看气质了啊，了啊，了啊，了啊，了啊，了啊，了吗嗯⊙∀⊙！嗯！了，了。，了嘛！！！！！！！！！","uid":"11","addtime":"2019-09-24 16:02:58"}]
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
         * meid : 6
         * times : 1569313199
         * title : 标题啊
         * content : 您lolX5我快中冷线路图痛苦里去啦8安老子在吉林来咯啦在咯无语咯lol哦咯lol您咯搜嘎你今KKK考虑
         * uid : 11
         * addtime : 2019-09-24 16:19:59
         */

        private String meid;
        private String times;
        private String title;
        private String content;
        private String uid;
        private String addtime;

        public String getMeid() {
            return meid;
        }

        public void setMeid(String meid) {
            this.meid = meid;
        }

        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }
    }
}
