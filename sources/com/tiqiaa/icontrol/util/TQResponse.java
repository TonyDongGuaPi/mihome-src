package com.tiqiaa.icontrol.util;

import com.imi.fastjson.JSON;
import com.imi.fastjson.TypeReference;
import com.imi.fastjson.annotation.JSONField;
import com.imi.fastjson.annotation.JSONType;
import com.imi.fastjson.parser.Feature;
import com.tiqiaa.common.IJsonable;

@JSONType(orders = {"errcode", "data"})
public class TQResponse implements IJsonable {
    @JSONField(name = "data")
    Object data;
    @JSONField(name = "errcode")
    int errcode;

    public int getErrcode() {
        return this.errcode;
    }

    public void setErrcode(int i) {
        this.errcode = i;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object obj) {
        this.data = obj;
    }

    public <T> T getData(Class<T> cls) {
        if (this.data == null || this.data.equals("")) {
            return null;
        }
        try {
            return JSON.parseObject(this.data.toString(), cls);
        } catch (Exception e) {
            LogUtil.printException(e);
            return null;
        }
    }

    public <T> T getData(TypeReference<T> typeReference) {
        if (this.data == null || this.data.equals("")) {
            return null;
        }
        try {
            return JSON.parseObject(this.data.toString(), typeReference, new Feature[0]);
        } catch (Exception e) {
            LogUtil.printException(e);
            return null;
        }
    }
}
