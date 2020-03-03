package com.alipay.mobile.security.bio.log;

import com.alipay.mobile.common.logging.api.LogCategory;
import com.alipay.mobile.common.logging.impl.BehavorloggerImpl;

public enum BehaviourIdEnum {
    NONE("none"),
    EVENT("event"),
    CLICKED(BehavorloggerImpl.BehavorID.CLICK),
    OPENPAGE(BehavorloggerImpl.BehavorID.OPENPAGE),
    LONGCLICKED(BehavorloggerImpl.BehavorID.LONGCLICK),
    DYNAMICLOADTOCHECK("dynamicLoadToCheck"),
    AUTO_CLICKED("auto_clicked"),
    AUTO_OPENPAGE(BehavorloggerImpl.BehavorID.AUTOOPENPAGE),
    SUBMITED(BehavorloggerImpl.BehavorID.SUBMITE),
    BIZLAUNCHED("bizLaunched"),
    ERROR("error"),
    EXCEPTION(LogCategory.CATEGORY_EXCEPTION),
    SETGESTURE("setGesture"),
    CHECKGESTURE("checkGesture"),
    SLIDED(BehavorloggerImpl.BehavorID.SLIDE),
    MONITOR("monitor"),
    EXECCOMMAND("execCommand"),
    MONITORPERF("monitorPerf");
    
    private String desc;

    private BehaviourIdEnum(String str) {
        this.desc = str;
    }

    public String getDes() {
        return this.desc;
    }

    public static BehaviourIdEnum convert(String str) {
        for (BehaviourIdEnum behaviourIdEnum : values()) {
            if (behaviourIdEnum.desc.equals(str)) {
                return behaviourIdEnum;
            }
        }
        return NONE;
    }
}
