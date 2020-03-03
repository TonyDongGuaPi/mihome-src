package com.xiaomi.mishopsdk.io.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.taobao.weex.el.parse.Operators;

public class SimpleApiResponse {
    @JSONField(name = "count")
    public long mCount;
    @JSONField(name = "result")
    public boolean mResult;

    public String toString() {
        return "SimpleApiResponse{mResult=" + this.mResult + ", mCount=" + this.mCount + Operators.BLOCK_END;
    }
}
