package com.xiaomi.smarthome.miio.camera.face.model;

import com.google.gson.JsonElement;
import com.taobao.weex.el.parse.Operators;

public class CommonResult {
    public int code;
    public JsonElement data;
    public String description;
    public String result;

    public String toString() {
        return "CommonResult{result='" + this.result + Operators.SINGLE_QUOTE + ", code=" + this.code + ", data=" + this.data + ", description='" + this.description + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
