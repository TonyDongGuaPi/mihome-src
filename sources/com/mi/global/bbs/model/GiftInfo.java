package com.mi.global.bbs.model;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;

public class GiftInfo implements Serializable {
    private static final long serialVersionUID = 1;
    public String desc;
    public String name;

    public GiftInfo() {
    }

    public GiftInfo(String str, String str2) {
        this.name = str;
        this.desc = str2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public String toString() {
        return "GiftInfo{name='" + this.name + Operators.SINGLE_QUOTE + ", desc='" + this.desc + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
