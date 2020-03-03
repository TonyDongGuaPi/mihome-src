package com.alipay.mobile.common.logging.impl;

import com.alipay.mobile.common.logging.api.LogCategory;
import com.alipay.mobile.common.logging.api.LogContext;
import com.alipay.mobile.common.logging.api.LogEvent;
import com.alipay.mobile.common.logging.api.behavor.Behavor;
import com.alipay.mobile.common.logging.api.behavor.BehavorLogger;
import com.alipay.mobile.common.logging.render.BehavorRender;

public class BehavorloggerImpl implements BehavorLogger {

    /* renamed from: a  reason: collision with root package name */
    private LogContext f961a;
    private BehavorRender b;

    public class BehavorID {
        public static final String AUTOCLICK = "auto_click";
        public static final String AUTOOPENPAGE = "auto_openPage";
        public static final String CLICK = "clicked";
        public static final String EVENT = "event";
        public static final String LONGCLICK = "longClicked";
        public static final String OPENPAGE = "openPage";
        public static final String SLIDE = "slided";
        public static final String SUBMITE = "submited";

        public BehavorID() {
        }
    }

    public BehavorloggerImpl(LogContext logContext) {
        this.f961a = logContext;
        this.b = new BehavorRender(logContext);
    }

    public void click(Behavor behavor) {
        this.f961a.appendLogEvent(new LogEvent(LogCategory.CATEGORY_USERBEHAVOR, BehavorLogger.class.getSimpleName(), LogEvent.Level.INFO, this.b.render(BehavorID.CLICK, behavor)));
    }

    public void openPage(Behavor behavor) {
        this.f961a.appendLogEvent(new LogEvent(LogCategory.CATEGORY_USERBEHAVOR, BehavorLogger.class.getSimpleName(), LogEvent.Level.INFO, this.b.render(BehavorID.OPENPAGE, behavor)));
    }

    public void longClick(Behavor behavor) {
        event(BehavorID.LONGCLICK, behavor);
    }

    public void submit(Behavor behavor) {
        event(BehavorID.SUBMITE, behavor);
    }

    public void slide(Behavor behavor) {
        event(BehavorID.SLIDE, behavor);
    }

    public void autoOpenPage(Behavor behavor) {
        this.f961a.appendLogEvent(new LogEvent(LogCategory.CATEGORY_AUTOUSERBEHAVOR, BehavorLogger.class.getSimpleName(), LogEvent.Level.INFO, this.b.render(BehavorID.AUTOOPENPAGE, behavor)));
    }

    public void autoClick(Behavor behavor) {
        this.f961a.appendLogEvent(new LogEvent(LogCategory.CATEGORY_AUTOUSERBEHAVOR, BehavorLogger.class.getSimpleName(), LogEvent.Level.INFO, this.b.render(BehavorID.AUTOCLICK, behavor)));
    }

    public void event(String str, Behavor behavor) {
        this.f961a.appendLogEvent(new LogEvent(LogCategory.CATEGORY_USERBEHAVOR, BehavorLogger.class.getSimpleName(), LogEvent.Level.INFO, this.b.render(str, behavor)));
    }
}
