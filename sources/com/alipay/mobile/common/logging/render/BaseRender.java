package com.alipay.mobile.common.logging.render;

import com.alipay.mobile.common.logging.api.LogContext;

public abstract class BaseRender {
    protected static final String GAP = "$$";
    protected LogContext logContext;

    public BaseRender(LogContext logContext2) {
        this.logContext = logContext2;
    }
}
