package com.alipay.bis.common.service.facade.gw.zim;

import com.taobao.weex.el.parse.Operators;

public class ZimValidateJsonGwRequest {
    public String zimData;
    public String zimId;

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("ZimValidateJsonGwRequest{zimId='");
        sb.append(this.zimId);
        sb.append("', data='");
        if (this.zimData == null) {
            str = "null";
        } else {
            str = "[length=" + this.zimData.length() + Operators.ARRAY_END_STR;
        }
        sb.append(str);
        sb.append("'");
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }
}
