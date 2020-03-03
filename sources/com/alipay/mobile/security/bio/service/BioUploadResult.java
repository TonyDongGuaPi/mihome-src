package com.alipay.mobile.security.bio.service;

import com.alipay.mobile.security.bio.utils.StringUtil;
import com.taobao.weex.el.parse.Operators;
import java.util.Map;

public class BioUploadResult {
    public Map<String, String> extParams;
    public boolean hasNext = false;
    public String nextProtocol;
    public int productRetCode = 3001;
    public String subCode = "";
    public String subMsg = "";
    public int validationRetCode;

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("BioUploadResult{productRetCode=");
        sb.append(this.productRetCode);
        sb.append(", validationRetCode=");
        sb.append(this.validationRetCode);
        sb.append(", hasNext=");
        sb.append(this.hasNext);
        sb.append(", subCode=");
        sb.append(this.subCode);
        sb.append(", subMsg=");
        sb.append(this.subMsg);
        sb.append(", nextProtocol='");
        sb.append(this.nextProtocol);
        sb.append(Operators.SINGLE_QUOTE);
        sb.append(", extParams=");
        if (this.extParams == null) {
            str = "null";
        } else {
            str = StringUtil.collection2String(this.extParams.keySet());
        }
        sb.append(str);
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }
}
