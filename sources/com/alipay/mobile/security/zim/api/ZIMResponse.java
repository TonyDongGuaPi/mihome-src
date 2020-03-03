package com.alipay.mobile.security.zim.api;

import com.alipay.mobile.security.bio.utils.StringUtil;
import com.taobao.weex.el.parse.Operators;
import java.util.HashMap;
import java.util.Map;

public class ZIMResponse {
    public String bizData;
    public int code;
    public Map<String, String> extInfo = new HashMap();
    public String msg;
    public String reason;
    public String subCode;

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("ZIMResponse{code=");
        sb.append(this.code);
        sb.append(", subCode=");
        sb.append(this.subCode);
        sb.append(", msg=");
        sb.append(this.msg);
        sb.append(", reason='");
        sb.append(this.reason);
        sb.append(Operators.SINGLE_QUOTE);
        sb.append(", bizData='");
        sb.append(this.bizData);
        sb.append(Operators.SINGLE_QUOTE);
        sb.append(", extInfo.keySet()=");
        if (this.extInfo == null) {
            str = "null";
        } else {
            str = StringUtil.collection2String(this.extInfo.keySet());
        }
        sb.append(str);
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }
}
