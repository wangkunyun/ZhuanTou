package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class LikeCompanyBean {

    /**
     * errno : 200
     * errmsg : 获取成功
     * data : [{"cname":"与意众合（北京）品牌策划有限公司"},{"cname":"东街一号文化传媒（北京）有限责任公司"},{"cname":"北京中泰盈盛建筑有限公司"},{"cname":"北京华夏国视文化传媒有限公司"},{"cname":"北京卓采方元文化发展有限公司"},{"cname":"北京国合源装饰工程设计有限公司"},{"cname":"北京壹心一艺艺考空间"},{"cname":"北京宏珩教育科技有限责任公司"},{"cname":"北京宜同文化发展有限公司"},{"cname":"北京容达影视文化传媒有限公司"},{"cname":"北京岁月漫长影视有限公司"},{"cname":"北京御宝堂生物科技有限公司"},{"cname":"北京御铭轩建筑设计有限公司"},{"cname":"北京恒溢聚彩影视文化有限公司"},{"cname":"北京新地影视文化传媒有限公司"},{"cname":"北京杉旌国际"},{"cname":"北京杉旌国际文化传播有限公司"},{"cname":"北京欢乐人文化传媒有限公司"},{"cname":"北京欧燃影视文化传媒有限公司"},{"cname":"北京测试公司"},{"cname":"北京游艺幻彩影视科技有限公司"},{"cname":"北京砖头智能科技有限公司"},{"cname":"北京砖头科技有限公司"},{"cname":"北京联盛创美科技有限公司"},{"cname":"北京豪邸沃德科技有限公司"},{"cname":"北京雅迪声电子技术有限公司"},{"cname":"北京魔影量子文化传媒有限公司"},{"cname":"声合汇邦文化（北京）有限公司"},{"cname":"复合拍北京文化传媒有限责任公司"},{"cname":"百川基业（北京）建筑装饰有限公司"},{"cname":"食谱购（北京）科技有限公司"}]
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
         * cname : 与意众合（北京）品牌策划有限公司
         */

        private String cname;

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }
    }
}
