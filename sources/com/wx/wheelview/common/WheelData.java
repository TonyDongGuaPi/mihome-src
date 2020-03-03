package com.wx.wheelview.common;

import com.taobao.weex.el.parse.Operators;
import java.io.Serializable;

public class WheelData implements Serializable {
    private static final long serialVersionUID = 1;
    private int id;
    private String name;

    public WheelData() {
    }

    public WheelData(int i, String str) {
        this.id = i;
        this.name = str;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String toString() {
        return "WheelData{" + "id=" + this.id + ", name='" + this.name + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
