package com.alipay.mobile.common.logging.impl;

import com.alipay.mobile.common.logging.api.LogCategory;
import com.alipay.mobile.common.logging.api.LogEvent;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.fastvideo.IOUtils;

public class TraceLogEvent extends LogEvent {

    /* renamed from: a  reason: collision with root package name */
    private static StringBuffer f964a = new StringBuffer();
    private static final long serialVersionUID = 1;

    public TraceLogEvent(String str, LogEvent.Level level, String str2) {
        f964a.setLength(0);
        this.category = LogCategory.CATEGORY_APPLOG;
        this.tag = str;
        this.level = level;
        this.timeStamp = System.currentTimeMillis();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.timeStamp);
        stringBuffer.append(' ');
        stringBuffer.append(level);
        stringBuffer.append(IOUtils.f15883a);
        stringBuffer.append(str);
        stringBuffer.append(Operators.CONDITION_IF_MIDDLE);
        stringBuffer.append(Operators.ARRAY_START);
        stringBuffer.append(Thread.currentThread().getName());
        stringBuffer.append("] ");
        stringBuffer.append(str2);
        this.message = stringBuffer.toString();
    }
}
