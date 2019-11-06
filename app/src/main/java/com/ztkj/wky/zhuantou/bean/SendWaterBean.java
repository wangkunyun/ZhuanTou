package com.ztkj.wky.zhuantou.bean;

import java.util.List;

public class SendWaterBean {


    /**
     * errno : 200
     * errmsg : 获取成功
     * data : {"sname":"保洁","scover":"https://pc.zhuantoukj.com/roombalimg/2019/07/23img_0LZMON3dIgy3NcCCTlwojRmE.jpg","sowing":"https://pc.zhuantoukj.com/roombalimg/2019/07/23img_0LZMON3dIgy3NcCCTlwojRmE.jpg,https://pc.zhuantoukj.com/roombalimg/2019/07/23img_0LZMON3dIgy3NcCCTlwojRmE.jpg,https://pc.zhuantoukj.com/roombalimg/2019/07/23img_0LZMON3dIgy3NcCCTlwojRmE.jpg","sintroduce":"我们不生产水，我们只是大自然的搬运工！！！","address":"密云水库","sphone":"0534-1234567","shopGoods":[{"name":"哈哈","goods":[{"ecid":"5","trade_name":"大海滩","money":"100000","sales_volume":"0","brief_introduction":"1212","image":"https://pc.zhuantoukj.com/roombalimg/2019/09/20img_ABIP9XUfp6tQ8oI1PGS1PvQq.jpg","name":"哈哈"}]},{"name":"所属","goods":[{"ecid":"5","trade_name":"大海滩","money":"100000","sales_volume":"0","brief_introduction":"1212","image":"https://pc.zhuantoukj.com/roombalimg/2019/09/20img_ABIP9XUfp6tQ8oI1PGS1PvQq.jpg","name":"哈哈"},{"ecid":"3","trade_name":"海滩","money":"1000","sales_volume":"0","brief_introduction":"你猜","image":"https://pc.zhuantoukj.com/roombalimg/2019/09/20img_LoedgS7sCC2d0WeHrKMEwnQ2.jpg","name":"所属"},{"ecid":"4","trade_name":"海滩2号","money":"100","sales_volume":"0","brief_introduction":"测","image":"https://pc.zhuantoukj.com/roombalimg/2019/09/20img_UZ5ItsKlfv4AuYLWBZxM5hVk.jpg","name":"所属"}]}]}
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
         * sname : 保洁
         * scover : https://pc.zhuantoukj.com/roombalimg/2019/07/23img_0LZMON3dIgy3NcCCTlwojRmE.jpg
         * sowing : https://pc.zhuantoukj.com/roombalimg/2019/07/23img_0LZMON3dIgy3NcCCTlwojRmE.jpg,https://pc.zhuantoukj.com/roombalimg/2019/07/23img_0LZMON3dIgy3NcCCTlwojRmE.jpg,https://pc.zhuantoukj.com/roombalimg/2019/07/23img_0LZMON3dIgy3NcCCTlwojRmE.jpg
         * sintroduce : 我们不生产水，我们只是大自然的搬运工！！！
         * address : 密云水库
         * sphone : 0534-1234567
         * shopGoods : [{"name":"哈哈","goods":[{"ecid":"5","trade_name":"大海滩","money":"100000","sales_volume":"0","brief_introduction":"1212","image":"https://pc.zhuantoukj.com/roombalimg/2019/09/20img_ABIP9XUfp6tQ8oI1PGS1PvQq.jpg","name":"哈哈"}]},{"name":"所属","goods":[{"ecid":"5","trade_name":"大海滩","money":"100000","sales_volume":"0","brief_introduction":"1212","image":"https://pc.zhuantoukj.com/roombalimg/2019/09/20img_ABIP9XUfp6tQ8oI1PGS1PvQq.jpg","name":"哈哈"},{"ecid":"3","trade_name":"海滩","money":"1000","sales_volume":"0","brief_introduction":"你猜","image":"https://pc.zhuantoukj.com/roombalimg/2019/09/20img_LoedgS7sCC2d0WeHrKMEwnQ2.jpg","name":"所属"},{"ecid":"4","trade_name":"海滩2号","money":"100","sales_volume":"0","brief_introduction":"测","image":"https://pc.zhuantoukj.com/roombalimg/2019/09/20img_UZ5ItsKlfv4AuYLWBZxM5hVk.jpg","name":"所属"}]}]
         */

        private String sname;
        private String scover;
        private String sowing;
        private String sintroduce;
        private String address;
        private String sphone;
        private List<ShopGoodsBean> shopGoods;

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public String getScover() {
            return scover;
        }

        public void setScover(String scover) {
            this.scover = scover;
        }

        public String getSowing() {
            return sowing;
        }

        public void setSowing(String sowing) {
            this.sowing = sowing;
        }

        public String getSintroduce() {
            return sintroduce;
        }

        public void setSintroduce(String sintroduce) {
            this.sintroduce = sintroduce;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getSphone() {
            return sphone;
        }

        public void setSphone(String sphone) {
            this.sphone = sphone;
        }

        public List<ShopGoodsBean> getShopGoods() {
            return shopGoods;
        }

        public void setShopGoods(List<ShopGoodsBean> shopGoods) {
            this.shopGoods = shopGoods;
        }

        public static class ShopGoodsBean {
            /**
             * name : 哈哈
             * goods : [{"ecid":"5","trade_name":"大海滩","money":"100000","sales_volume":"0","brief_introduction":"1212","image":"https://pc.zhuantoukj.com/roombalimg/2019/09/20img_ABIP9XUfp6tQ8oI1PGS1PvQq.jpg","name":"哈哈"}]
             */

            private String name;
            private int type;
            private List<GoodsBean> goods;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<GoodsBean> getGoods() {
                return goods;
            }

            public void setGoods(List<GoodsBean> goods) {
                this.goods = goods;
            }

            public static class GoodsBean {
                /**
                 * ecid : 5
                 * trade_name : 大海滩
                 * money : 100000
                 * sales_volume : 0
                 * brief_introduction : 1212
                 * image : https://pc.zhuantoukj.com/roombalimg/2019/09/20img_ABIP9XUfp6tQ8oI1PGS1PvQq.jpg
                 * name : 哈哈
                 */

                private String ecid;
                private String trade_name;
                private String money;
                private String sales_volume;
                private String brief_introduction;
                private String image;
                private String name;

                public String getEcid() {
                    return ecid;
                }

                public void setEcid(String ecid) {
                    this.ecid = ecid;
                }

                public String getTrade_name() {
                    return trade_name;
                }

                public void setTrade_name(String trade_name) {
                    this.trade_name = trade_name;
                }

                public String getMoney() {
                    return money;
                }

                public void setMoney(String money) {
                    this.money = money;
                }

                public String getSales_volume() {
                    return sales_volume;
                }

                public void setSales_volume(String sales_volume) {
                    this.sales_volume = sales_volume;
                }

                public String getBrief_introduction() {
                    return brief_introduction;
                }

                public void setBrief_introduction(String brief_introduction) {
                    this.brief_introduction = brief_introduction;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}
