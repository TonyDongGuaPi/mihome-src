package com.alipay.mobile.security.bio.service;

import com.taobao.weex.el.parse.Operators;

public class BioUploadItem {
    public String bisToken;
    public byte[] content;
    public byte[] contentSig;
    public boolean isNeedSendResponse = false;
    public byte[] log;
    public String publicKey;

    public String toString() {
        return "BioUploadItem{, bisToken='" + this.bisToken + Operators.SINGLE_QUOTE + ", isNeedSendResponse=" + this.isNeedSendResponse + Operators.BLOCK_END;
    }
}
