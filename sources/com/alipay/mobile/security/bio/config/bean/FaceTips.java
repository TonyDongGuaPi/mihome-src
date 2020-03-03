package com.alipay.mobile.security.bio.config.bean;

import com.taobao.weex.el.parse.Operators;

public class FaceTips {

    /* renamed from: a  reason: collision with root package name */
    private String f986a;
    private String b;
    public String bottomText = "";
    private String c;
    private String d = "";
    private String e = "";
    private AlertConfig f = new AlertConfig();
    private AlertConfig g = new AlertConfig();
    private AlertConfig h = new AlertConfig();
    private AlertConfig i = new AlertConfig();
    private AlertConfig j = new AlertConfig();
    private AlertConfig k = new AlertConfig();
    private AlertConfig l = new AlertConfig();
    private AlertConfig m = new AlertConfig();
    private AlertConfig n = new AlertConfig();
    private AlertConfig o = new AlertConfig();
    private AlertConfig p = new AlertConfig();
    public String sceneText = "";
    public String topText = "";
    public String topText_angle = "";
    public String topText_blink = "";
    public String topText_blur = "";
    public String topText_integrity = "";
    public String topText_light = "";
    public String topText_max_rectwidth = "";
    public String topText_noface = "";
    public String topText_openness = "";
    public String topText_pitch = "";
    public String topText_quality = "";
    public String topText_rectwidth = "";
    public String topText_stay = "";
    public String topText_yaw = "";

    public FaceTips() {
        this.f.setReturnCode(102);
        this.g.setReturnCode(105);
        this.h.setReturnCode(205);
        this.i.setReturnCode(100);
        this.j.setReturnCode(202);
        this.k.setReturnCode(203);
        this.l.setReturnCode(208);
        this.m.setReturnCode(209);
        this.n.setReturnCode(207);
        this.o.setReturnCode(202);
        this.p.setReturnCode(210);
    }

    public void setNoFaceText(String str) {
        this.f986a = str;
    }

    public String getNoFaceText() {
        return this.f986a;
    }

    public void setNoBlinkText(String str) {
        this.b = str;
    }

    public String getNoBlinkText() {
        return this.b;
    }

    public void setAdjustPoseText(String str) {
        this.c = str;
    }

    public String getAdjustPoseText() {
        return this.c;
    }

    public AlertConfig getUnsurpportAlert() {
        return this.f;
    }

    public void setUnsurpportAlert(AlertConfig alertConfig) {
        this.f = alertConfig;
    }

    public AlertConfig getSystemVersionErrorAlert() {
        return this.g;
    }

    public void setSystemVersionErrorAlert(AlertConfig alertConfig) {
        this.g = alertConfig;
    }

    public AlertConfig getSystemErrorAlert() {
        return this.h;
    }

    public void setSystemErrorAlert(AlertConfig alertConfig) {
        this.h = alertConfig;
    }

    public AlertConfig getCameraNoPermissionAlert() {
        return this.i;
    }

    public void setCameraNoPermissionAlert(AlertConfig alertConfig) {
        this.i = alertConfig;
    }

    public AlertConfig getExitAlert() {
        return this.j;
    }

    public void setExitAlert(AlertConfig alertConfig) {
        this.j = alertConfig;
    }

    public AlertConfig getTimeoutAlert() {
        return this.k;
    }

    public void setTimeoutAlert(AlertConfig alertConfig) {
        this.k = alertConfig;
    }

    public AlertConfig getFailAlert() {
        return this.l;
    }

    public void setFailAlert(AlertConfig alertConfig) {
        this.l = alertConfig;
    }

    public AlertConfig getLimitAlert() {
        return this.m;
    }

    public void setLimitAlert(AlertConfig alertConfig) {
        this.m = alertConfig;
    }

    public AlertConfig getNetworkErrorAlert() {
        return this.n;
    }

    public void setNetworkErrorAlert(AlertConfig alertConfig) {
        this.n = alertConfig;
    }

    public AlertConfig getInterruptAlert() {
        return this.o;
    }

    public void setInterruptAlert(AlertConfig alertConfig) {
        this.o = alertConfig;
    }

    public AlertConfig getAuthorizationAlert() {
        return this.p;
    }

    public void setAuthorizationAlert(AlertConfig alertConfig) {
        this.p = alertConfig;
    }

    public String getBrandTip() {
        return this.d;
    }

    public void setBrandTip(String str) {
        this.d = str;
    }

    public String getStopScanTip() {
        return this.e;
    }

    public void setStopScanTip(String str) {
        this.e = str;
    }

    public String toString() {
        return "FaceTips{noFaceText='" + this.f986a + Operators.SINGLE_QUOTE + ", noBlinkText='" + this.b + Operators.SINGLE_QUOTE + ", adjustPoseText='" + this.c + Operators.SINGLE_QUOTE + ", brandTip='" + this.d + Operators.SINGLE_QUOTE + ", stopScanTip='" + this.e + Operators.SINGLE_QUOTE + ", unsurpportAlert=" + this.f + ", systemVersionErrorAlert=" + this.g + ", systemErrorAlert=" + this.h + ", cameraNoPermissionAlert=" + this.i + ", exitAlert=" + this.j + ", timeoutAlert=" + this.k + ", failAlert=" + this.l + ", limitAlert=" + this.m + ", networkErrorAlert=" + this.n + ", interruptAlert=" + this.o + Operators.BLOCK_END;
    }
}
