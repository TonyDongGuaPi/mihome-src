package com.alipay.bis.common.service.facade.gw.zim;

import com.alipay.mobile.security.bio.utils.StringUtil;
import com.taobao.weex.el.parse.Operators;
import java.util.Map;

public class ZimInitGwResponse {
    public Map<String, String> extParams;
    public String message;
    public String protocol;
    public int retCode = 0;
    public String retCodeSub;
    public String retMessageSub;
    public String zimId;

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("ZimInitGwResponse{retCode=");
        sb.append(this.retCode);
        sb.append(", message='");
        sb.append(this.message);
        sb.append(Operators.SINGLE_QUOTE);
        sb.append(", zimId='");
        sb.append(this.zimId);
        sb.append(Operators.SINGLE_QUOTE);
        sb.append(", protocol='");
        sb.append(this.protocol);
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
