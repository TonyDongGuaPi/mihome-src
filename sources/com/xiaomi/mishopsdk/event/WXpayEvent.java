package com.xiaomi.mishopsdk.event;

import com.taobao.weex.el.parse.Operators;

public class WXpayEvent {
    public int code;
    public int type;

    public String toString() {
        return "WxPayEvent{type=" + this.type + ", code=" + this.code + Operators.BLOCK_END;
    }
}
