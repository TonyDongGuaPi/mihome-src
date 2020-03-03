package com.taobao.weex.common;

public enum WXRenderStrategy {
    APPEND_ASYNC("APPEND_ASYNC"),
    APPEND_ONCE("APPEND_ONCE"),
    DATA_RENDER("DATA_RENDER"),
    DATA_RENDER_BINARY("DATA_RENDER_BINARY");
    
    private String flag;

    static {
        boolean[] $jacocoInit;
        $jacocoInit[7] = true;
    }

    private WXRenderStrategy(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.flag = str;
        $jacocoInit[2] = true;
    }

    public String getFlag() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.flag;
        $jacocoInit[3] = true;
        return str;
    }
}
