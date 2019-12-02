package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class TradeLogisticsBean {

    /**
     * errno : 200
     * errmsg : 请求成功
     * data : {"message":"ok","nu":"4302557768029","ischeck":"1","com":"yunda","status":"200","data":[{"time":"2019-12-01 08:36:29","context":"[北京主城区公司昌平区宏福服务部]快件已被 您的快件已送达 冠华苑四区东门北侧丰巢智能柜FC0109902(冠华苑四区东门北侧丰巢智能柜) 保管。如有问题请电联业务员：王建华【13693382652】。相逢是缘,如果您对我的服务感到满意,给个五星好不好？【请在评价小件员处给予五星好评】","ftime":"2019-12-01 08:36:29","areaCode":"CN111400000000","areaName":"北京,昌平区","status":"签收"},{"time":"2019-12-01 07:09:36","context":"[北京主城区公司昌平区宏福服务部]进行派件扫描；派送业务员：王建华；联系电话：13693382652","ftime":"2019-12-01 07:09:36","areaCode":"CN111400000000","areaName":"北京,昌平区","status":"派件"},{"time":"2019-12-01 07:02:42","context":"[北京主城区公司昌平区宏福服务部]到达目的地网点，快件很快进行派送","ftime":"2019-12-01 07:02:42","areaCode":"CN111400000000","areaName":"北京,昌平区","status":"派件"},{"time":"2019-11-30 15:18:16","context":"[北京分拨中心]从站点发出，本次转运目的地：北京主城区公司昌平区宏福服务部","ftime":"2019-11-30 15:18:16","areaCode":"CN111400000000","areaName":"北京,昌平区","status":"在途"},{"time":"2019-11-30 14:57:33","context":"[北京分拨中心]在分拨中心进行卸车扫描","ftime":"2019-11-30 14:57:33","areaCode":"CN110000000000","areaName":"北京","status":"在途"},{"time":"2019-11-29 18:29:08","context":"[江苏徐州分拨中心]进行装车扫描，发往：北京分拨中心","ftime":"2019-11-29 18:29:08","areaCode":"CN320300000000","areaName":"江苏,徐州市","status":"在途"},{"time":"2019-11-29 18:26:50","context":"[江苏徐州分拨中心]在分拨中心进行称重扫描","ftime":"2019-11-29 18:26:50","areaCode":"CN320300000000","areaName":"江苏,徐州市","status":"在途"},{"time":"2019-11-29 12:37:22","context":"[江苏丰县公司]进行下级地点扫描，发往：京西地区包","ftime":"2019-11-29 12:37:22","areaCode":null,"areaName":null,"status":"在途"},{"time":"2019-11-29 10:49:57","context":"[江苏丰县公司]进行揽件扫描","ftime":"2019-11-29 10:49:57","areaCode":"CN320321000000","areaName":"江苏,徐州市,丰县","status":"揽收"}],"state":"3","condition":"F00","express":[{"time":"2019-12-01 08:36:29","context":"[北京主城区公司昌平区宏福服务部]快件已被 您的快件已送达 冠华苑四区东门北侧丰巢智能柜FC0109902(冠华苑四区东门北侧丰巢智能柜) 保管。如有问题请电联业务员：王建华【13693382652】。相逢是缘,如果您对我的服务感到满意,给个五星好不好？【请在评价小件员处给予五星好评】","ftime":"2019-12-01 08:36:29","areaCode":"CN111400000000","areaName":"北京,昌平区","status":"签收"},{"time":"2019-12-01 07:09:36","context":"[北京主城区公司昌平区宏福服务部]进行派件扫描；派送业务员：王建华；联系电话：13693382652","ftime":"2019-12-01 07:09:36","areaCode":"CN111400000000","areaName":"北京,昌平区","status":"派件"},{"time":"2019-12-01 07:02:42","context":"[北京主城区公司昌平区宏福服务部]到达目的地网点，快件很快进行派送","ftime":"2019-12-01 07:02:42","areaCode":"CN111400000000","areaName":"北京,昌平区","status":"派件"},{"time":"2019-11-30 15:18:16","context":"[北京分拨中心]从站点发出，本次转运目的地：北京主城区公司昌平区宏福服务部","ftime":"2019-11-30 15:18:16","areaCode":"CN111400000000","areaName":"北京,昌平区","status":"在途"},{"time":"2019-11-30 14:57:33","context":"[北京分拨中心]在分拨中心进行卸车扫描","ftime":"2019-11-30 14:57:33","areaCode":"CN110000000000","areaName":"北京","status":"在途"},{"time":"2019-11-29 18:29:08","context":"[江苏徐州分拨中心]进行装车扫描，发往：北京分拨中心","ftime":"2019-11-29 18:29:08","areaCode":"CN320300000000","areaName":"江苏,徐州市","status":"在途"},{"time":"2019-11-29 18:26:50","context":"[江苏徐州分拨中心]在分拨中心进行称重扫描","ftime":"2019-11-29 18:26:50","areaCode":"CN320300000000","areaName":"江苏,徐州市","status":"在途"},{"time":"2019-11-29 12:37:22","context":"[江苏丰县公司]进行下级地点扫描，发往：京西地区包","ftime":"2019-11-29 12:37:22","areaCode":null,"areaName":null,"status":"在途"},{"time":"2019-11-29 10:49:57","context":"[江苏丰县公司]进行揽件扫描","ftime":"2019-11-29 10:49:57","areaCode":"CN320321000000","areaName":"江苏,徐州市,丰县","status":"揽收"}]}
     */

    private String errno;
    private String errmsg;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * message : ok
         * nu : 4302557768029
         * ischeck : 1
         * com : yunda
         * status : 200
         * data : [{"time":"2019-12-01 08:36:29","context":"[北京主城区公司昌平区宏福服务部]快件已被 您的快件已送达 冠华苑四区东门北侧丰巢智能柜FC0109902(冠华苑四区东门北侧丰巢智能柜) 保管。如有问题请电联业务员：王建华【13693382652】。相逢是缘,如果您对我的服务感到满意,给个五星好不好？【请在评价小件员处给予五星好评】","ftime":"2019-12-01 08:36:29","areaCode":"CN111400000000","areaName":"北京,昌平区","status":"签收"},{"time":"2019-12-01 07:09:36","context":"[北京主城区公司昌平区宏福服务部]进行派件扫描；派送业务员：王建华；联系电话：13693382652","ftime":"2019-12-01 07:09:36","areaCode":"CN111400000000","areaName":"北京,昌平区","status":"派件"},{"time":"2019-12-01 07:02:42","context":"[北京主城区公司昌平区宏福服务部]到达目的地网点，快件很快进行派送","ftime":"2019-12-01 07:02:42","areaCode":"CN111400000000","areaName":"北京,昌平区","status":"派件"},{"time":"2019-11-30 15:18:16","context":"[北京分拨中心]从站点发出，本次转运目的地：北京主城区公司昌平区宏福服务部","ftime":"2019-11-30 15:18:16","areaCode":"CN111400000000","areaName":"北京,昌平区","status":"在途"},{"time":"2019-11-30 14:57:33","context":"[北京分拨中心]在分拨中心进行卸车扫描","ftime":"2019-11-30 14:57:33","areaCode":"CN110000000000","areaName":"北京","status":"在途"},{"time":"2019-11-29 18:29:08","context":"[江苏徐州分拨中心]进行装车扫描，发往：北京分拨中心","ftime":"2019-11-29 18:29:08","areaCode":"CN320300000000","areaName":"江苏,徐州市","status":"在途"},{"time":"2019-11-29 18:26:50","context":"[江苏徐州分拨中心]在分拨中心进行称重扫描","ftime":"2019-11-29 18:26:50","areaCode":"CN320300000000","areaName":"江苏,徐州市","status":"在途"},{"time":"2019-11-29 12:37:22","context":"[江苏丰县公司]进行下级地点扫描，发往：京西地区包","ftime":"2019-11-29 12:37:22","areaCode":null,"areaName":null,"status":"在途"},{"time":"2019-11-29 10:49:57","context":"[江苏丰县公司]进行揽件扫描","ftime":"2019-11-29 10:49:57","areaCode":"CN320321000000","areaName":"江苏,徐州市,丰县","status":"揽收"}]
         * state : 3
         * condition : F00
         * express : [{"time":"2019-12-01 08:36:29","context":"[北京主城区公司昌平区宏福服务部]快件已被 您的快件已送达 冠华苑四区东门北侧丰巢智能柜FC0109902(冠华苑四区东门北侧丰巢智能柜) 保管。如有问题请电联业务员：王建华【13693382652】。相逢是缘,如果您对我的服务感到满意,给个五星好不好？【请在评价小件员处给予五星好评】","ftime":"2019-12-01 08:36:29","areaCode":"CN111400000000","areaName":"北京,昌平区","status":"签收"},{"time":"2019-12-01 07:09:36","context":"[北京主城区公司昌平区宏福服务部]进行派件扫描；派送业务员：王建华；联系电话：13693382652","ftime":"2019-12-01 07:09:36","areaCode":"CN111400000000","areaName":"北京,昌平区","status":"派件"},{"time":"2019-12-01 07:02:42","context":"[北京主城区公司昌平区宏福服务部]到达目的地网点，快件很快进行派送","ftime":"2019-12-01 07:02:42","areaCode":"CN111400000000","areaName":"北京,昌平区","status":"派件"},{"time":"2019-11-30 15:18:16","context":"[北京分拨中心]从站点发出，本次转运目的地：北京主城区公司昌平区宏福服务部","ftime":"2019-11-30 15:18:16","areaCode":"CN111400000000","areaName":"北京,昌平区","status":"在途"},{"time":"2019-11-30 14:57:33","context":"[北京分拨中心]在分拨中心进行卸车扫描","ftime":"2019-11-30 14:57:33","areaCode":"CN110000000000","areaName":"北京","status":"在途"},{"time":"2019-11-29 18:29:08","context":"[江苏徐州分拨中心]进行装车扫描，发往：北京分拨中心","ftime":"2019-11-29 18:29:08","areaCode":"CN320300000000","areaName":"江苏,徐州市","status":"在途"},{"time":"2019-11-29 18:26:50","context":"[江苏徐州分拨中心]在分拨中心进行称重扫描","ftime":"2019-11-29 18:26:50","areaCode":"CN320300000000","areaName":"江苏,徐州市","status":"在途"},{"time":"2019-11-29 12:37:22","context":"[江苏丰县公司]进行下级地点扫描，发往：京西地区包","ftime":"2019-11-29 12:37:22","areaCode":null,"areaName":null,"status":"在途"},{"time":"2019-11-29 10:49:57","context":"[江苏丰县公司]进行揽件扫描","ftime":"2019-11-29 10:49:57","areaCode":"CN320321000000","areaName":"江苏,徐州市,丰县","status":"揽收"}]
         */

        private String message;
        private String nu;
        private String ischeck;
        private String com;
        private String status;
        private String state;
        private String condition;
        private List<DataBean> data;
        private List<ExpressBean> express;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getNu() {
            return nu;
        }

        public void setNu(String nu) {
            this.nu = nu;
        }

        public String getIscheck() {
            return ischeck;
        }

        public void setIscheck(String ischeck) {
            this.ischeck = ischeck;
        }

        public String getCom() {
            return com;
        }

        public void setCom(String com) {
            this.com = com;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public List<ExpressBean> getExpress() {
            return express;
        }

        public void setExpress(List<ExpressBean> express) {
            this.express = express;
        }

        public static class DataBean {
            /**
             * time : 2019-12-01 08:36:29
             * context : [北京主城区公司昌平区宏福服务部]快件已被 您的快件已送达 冠华苑四区东门北侧丰巢智能柜FC0109902(冠华苑四区东门北侧丰巢智能柜) 保管。如有问题请电联业务员：王建华【13693382652】。相逢是缘,如果您对我的服务感到满意,给个五星好不好？【请在评价小件员处给予五星好评】
             * ftime : 2019-12-01 08:36:29
             * areaCode : CN111400000000
             * areaName : 北京,昌平区
             * status : 签收
             */

            private String time;
            private String context;
            private String ftime;
            private String areaCode;
            private String areaName;
            private String status;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }

            public String getFtime() {
                return ftime;
            }

            public void setFtime(String ftime) {
                this.ftime = ftime;
            }

            public String getAreaCode() {
                return areaCode;
            }

            public void setAreaCode(String areaCode) {
                this.areaCode = areaCode;
            }

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }

        public static class ExpressBean {
            /**
             * time : 2019-12-01 08:36:29
             * context : [北京主城区公司昌平区宏福服务部]快件已被 您的快件已送达 冠华苑四区东门北侧丰巢智能柜FC0109902(冠华苑四区东门北侧丰巢智能柜) 保管。如有问题请电联业务员：王建华【13693382652】。相逢是缘,如果您对我的服务感到满意,给个五星好不好？【请在评价小件员处给予五星好评】
             * ftime : 2019-12-01 08:36:29
             * areaCode : CN111400000000
             * areaName : 北京,昌平区
             * status : 签收
             */

            private String time;
            private String context;
            private String ftime;
            private String areaCode;
            private String areaName;
            private String status;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }

            public String getFtime() {
                return ftime;
            }

            public void setFtime(String ftime) {
                this.ftime = ftime;
            }

            public String getAreaCode() {
                return areaCode;
            }

            public void setAreaCode(String areaCode) {
                this.areaCode = areaCode;
            }

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
