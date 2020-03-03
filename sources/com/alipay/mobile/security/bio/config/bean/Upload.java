package com.alipay.mobile.security.bio.config.bean;

import com.alipay.mobile.security.bio.common.record.MetaRecord;
import com.alipay.mobile.security.bio.utils.StringUtil;
import com.daimajia.easing.BuildConfig;
import com.taobao.weex.el.parse.Operators;

public class Upload {

    /* renamed from: a  reason: collision with root package name */
    private int f990a = 10;
    private String b = BuildConfig.VERSION_NAME;
    private float c = 0.8f;
    private String d = MetaRecord.DEFAULT_LOG_CLASSIFIERS;
    private String[] e;

    public String getLog_classifier() {
        return this.d;
    }

    public void setLog_classifier(String str) {
        this.d = str;
    }

    public String[] getCollection() {
        return this.e;
    }

    public void setCollection(String[] strArr) {
        this.e = strArr;
    }

    public int getMinquality() {
        return this.f990a;
    }

    public void setMinquality(int i) {
        this.f990a = i;
    }

    public String getMode() {
        return this.b;
    }

    public void setMode(String str) {
        this.b = str;
    }

    public float getUpload_compress_rate() {
        return this.c;
    }

    public void setUpload_compress_rate(float f) {
        this.c = f;
    }

    public String getLogClassifier() {
        return this.d;
    }

    public void setLogClassifier(String str) {
        this.d = str;
    }

    public String toString() {
        return "Upload{minquality=" + this.f990a + ", mode='" + this.b + Operators.SINGLE_QUOTE + ", upload_compress_rate=" + this.c + ", log_classifier='" + this.d + Operators.SINGLE_QUOTE + ", collection='" + StringUtil.array2String(this.e) + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
