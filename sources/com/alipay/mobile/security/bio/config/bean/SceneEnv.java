package com.alipay.mobile.security.bio.config.bean;

import com.taobao.weex.el.parse.Operators;

public class SceneEnv {

    /* renamed from: a  reason: collision with root package name */
    private String f988a = "";
    private String b = "normal";

    public void setSceneCode(String str) {
        this.f988a = str;
    }

    public String getSceneCode() {
        return this.f988a;
    }

    public void setSceneType(String str) {
        this.b = str;
    }

    public String getSceneType() {
        return this.b;
    }

    public String toString() {
        return "SceneEnv{sceneCode='" + this.f988a + Operators.SINGLE_QUOTE + ", sceneType='" + this.b + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
