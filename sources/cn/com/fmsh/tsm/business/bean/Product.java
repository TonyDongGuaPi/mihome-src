package cn.com.fmsh.tsm.business.bean;

import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;

public class Product {
    private String city;
    private String code;
    private String id;
    private String name;
    private int price;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int i) {
        this.price = i;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public static Product fromTag(ITag iTag) throws FMCommunicationMessageException {
        ITag[] itemTags;
        if (iTag == null || (itemTags = iTag.getItemTags()) == null || itemTags.length < 1) {
            return null;
        }
        Product product = new Product();
        for (ITag iTag2 : itemTags) {
            byte id2 = iTag2.getId();
            if (id2 == 103) {
                product.id = iTag2.getStringVal();
            } else if (id2 != 124) {
                switch (id2) {
                    case -103:
                        product.code = iTag2.getStringVal();
                        break;
                    case -102:
                        product.name = iTag2.getStringVal();
                        break;
                    case -101:
                        product.city = iTag2.getStringVal();
                        break;
                }
            } else {
                product.price = iTag2.getIntVal();
            }
        }
        return product;
    }
}
