package com.mics.core.data.business;

import com.google.gson.annotations.SerializedName;

public class ChatParams {
    private Brand brand;
    private String channel;
    @SerializedName("gids")
    private String gid;
    private Goods goods;
    @SerializedName("groupId")
    private String merchantId;
    private Order order;
    private String referer;

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String str) {
        this.channel = str;
    }

    public String getReferer() {
        return this.referer;
    }

    public void setReferer(String str) {
        this.referer = str;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public void setMerchantId(String str) {
        this.merchantId = str;
    }

    public String getGid() {
        return this.gid;
    }

    public void setGid(String str) {
        this.gid = str;
    }

    public Goods getGoods() {
        return this.goods;
    }

    public void setGoods(Goods goods2) {
        this.goods = goods2;
    }

    public Brand getBrand() {
        return this.brand;
    }

    public void setBrand(Brand brand2) {
        this.brand = brand2;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order2) {
        this.order = order2;
    }

    public static class Goods implements Cloneable {
        private String cover;
        private String name;
        private float price;
        private String url;

        public String getCover() {
            return this.cover;
        }

        public void setCover(String str) {
            this.cover = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public float getPrice() {
            return this.price;
        }

        public void setPrice(float f) {
            this.price = f;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        /* access modifiers changed from: protected */
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        public static boolean isNull(Goods goods) {
            if (goods == null) {
                return true;
            }
            return goods.cover == null && goods.name == null && goods.url == null;
        }
    }

    public static class Brand implements Cloneable {
        private String logo;
        private String name;

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getLogo() {
            return this.logo;
        }

        public void setLogo(String str) {
            this.logo = str;
        }

        /* access modifiers changed from: protected */
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public static class Order implements Cloneable {
        private String address;
        private String consignee;
        private String orderId;
        private String status;
        private String tel;
        private String zipcode;

        public String getOrderId() {
            return this.orderId;
        }

        public void setOrderId(String str) {
            this.orderId = str;
        }

        public String getStatus() {
            return this.status;
        }

        public void setStatus(String str) {
            this.status = str;
        }

        public String getAddress() {
            return this.address;
        }

        public void setAddress(String str) {
            this.address = str;
        }

        public String getTel() {
            return this.tel;
        }

        public void setTel(String str) {
            this.tel = str;
        }

        public String getConsignee() {
            return this.consignee;
        }

        public void setConsignee(String str) {
            this.consignee = str;
        }

        public String getZipcode() {
            return this.zipcode;
        }

        public void setZipcode(String str) {
            this.zipcode = str;
        }

        /* access modifiers changed from: protected */
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public Object clone() throws CloneNotSupportedException {
        ChatParams chatParams = (ChatParams) super.clone();
        if (this.goods != null) {
            chatParams.goods = (Goods) this.goods.clone();
        }
        if (this.brand != null) {
            chatParams.brand = (Brand) this.brand.clone();
        }
        if (this.order != null) {
            chatParams.order = (Order) this.order.clone();
        }
        return chatParams;
    }
}
