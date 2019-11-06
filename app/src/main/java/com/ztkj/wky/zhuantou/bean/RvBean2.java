package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class RvBean2 {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : {"list":[{"tid":"2","t_title":"如何评价《复仇者联盟4》中的 钢铁侠？","t_content":"得劲儿护肤二环附近的日后六个哈让他那几个比特币是他能不能让他 你居然尬黑贵uarhebg","t_degree":"50","cover":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2827330719,2763446606&fm=27&gp=0.jpg"},{"tid":"4","t_title":"如何评价《复仇者联盟4》中的 钢铁侠？","t_content":"得劲儿护肤二环附近的日后六个哈让他那几个比特币是他能不能让他 你居然尬黑贵uarhebg","t_degree":"50","cover":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2827330719,2763446606&fm=27&gp=0.jpg"},{"tid":"1","t_title":"#如何看待高智商罪犯北大学子弑母案嫌疑人","t_content":"assdddddd萨达啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊大手大脚金额的你就恩放假嗯我发你呢教科文","t_degree":"1","cover":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=276693978,4097012019&fm=27&gp=0.jpg"},{"tid":"3","t_title":"#如何看待高智商罪犯北大学子弑母案嫌疑人","t_content":"assdddddd萨达啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊大手大脚金额的你就恩放假嗯我发你呢教科文","t_degree":"1","cover":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=276693978,4097012019&fm=27&gp=0.jpg"}],"count":"4"}
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
         * list : [{"tid":"2","t_title":"如何评价《复仇者联盟4》中的 钢铁侠？","t_content":"得劲儿护肤二环附近的日后六个哈让他那几个比特币是他能不能让他 你居然尬黑贵uarhebg","t_degree":"50","cover":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2827330719,2763446606&fm=27&gp=0.jpg"},{"tid":"4","t_title":"如何评价《复仇者联盟4》中的 钢铁侠？","t_content":"得劲儿护肤二环附近的日后六个哈让他那几个比特币是他能不能让他 你居然尬黑贵uarhebg","t_degree":"50","cover":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2827330719,2763446606&fm=27&gp=0.jpg"},{"tid":"1","t_title":"#如何看待高智商罪犯北大学子弑母案嫌疑人","t_content":"assdddddd萨达啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊大手大脚金额的你就恩放假嗯我发你呢教科文","t_degree":"1","cover":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=276693978,4097012019&fm=27&gp=0.jpg"},{"tid":"3","t_title":"#如何看待高智商罪犯北大学子弑母案嫌疑人","t_content":"assdddddd萨达啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊大手大脚金额的你就恩放假嗯我发你呢教科文","t_degree":"1","cover":"https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=276693978,4097012019&fm=27&gp=0.jpg"}]
         * count : 4
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
             * tid : 2
             * t_title : 如何评价《复仇者联盟4》中的 钢铁侠？
             * t_content : 得劲儿护肤二环附近的日后六个哈让他那几个比特币是他能不能让他 你居然尬黑贵uarhebg
             * t_degree : 50
             * cover : https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2827330719,2763446606&fm=27&gp=0.jpg
             */

            private String tid;
            private String t_title;
            private String t_content;
            private String t_degree;
            private String cover;

            public String getTid() {
                return tid;
            }

            public void setTid(String tid) {
                this.tid = tid;
            }

            public String getT_title() {
                return t_title;
            }

            public void setT_title(String t_title) {
                this.t_title = t_title;
            }

            public String getT_content() {
                return t_content;
            }

            public void setT_content(String t_content) {
                this.t_content = t_content;
            }

            public String getT_degree() {
                return t_degree;
            }

            public void setT_degree(String t_degree) {
                this.t_degree = t_degree;
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
