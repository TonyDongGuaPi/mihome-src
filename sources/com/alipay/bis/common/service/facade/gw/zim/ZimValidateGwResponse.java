package com.alipay.bis.common.service.facade.gw.zim;

import com.alipay.mobile.security.bio.utils.StringUtil;
import com.taobao.weex.el.parse.Operators;
import java.util.Map;

public class ZimValidateGwResponse {
    public Map<String, String> extParams;
    public boolean hasNext = false;
    public String nextProtocol;
    public int productRetCode = 0;
    public String retCodeSub;
    public String retMessageSub;
    public int validationRetCode = 0;

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("ZimValidateGwResponse{validationRetCode=");
        sb.append(this.validationRetCode);
        sb.append(", productRetCode=");
        sb.append(this.productRetCode);
        sb.append(", hasNext=");
        sb.append(this.hasNext);
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
        sb.append(", retCodeSub='");
        sb.append(this.retCodeSub);
        sb.append(Operators.SINGLE_QUOTE);
        sb.append(", retMessageSub='");
        sb.append(this.retMessageSub);
        sb.append(Operators.SINGLE_QUOTE);
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }
}
