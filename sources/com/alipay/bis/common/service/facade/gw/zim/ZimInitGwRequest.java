package com.alipay.bis.common.service.facade.gw.zim;

import com.taobao.weex.el.parse.Operators;

public class ZimInitGwRequest {
    public String bizData;
    public String channel;
    public String merchant;
    public String metaInfo;
    public String produceNode;
    public String productName;
    public String zimId;

    public String toString() {
        return "ZimInitGwRequest{zimId='" + this.zimId + Operators.SINGLE_QUOTE + ", channel='" + this.channel + Operators.SINGLE_QUOTE + ", merchant='" + this.merchant + Operators.SINGLE_QUOTE + ", productName='" + this.productName + Operators.SINGLE_QUOTE + ", produceNode='" + this.produceNode + Operators.SINGLE_QUOTE + ", bizData='" + this.bizData + Operators.SINGLE_QUOTE + ", metaInfo='" + this.metaInfo + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
