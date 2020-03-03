package com.alipay.mobile.security.bio.workspace;

import com.alipay.mobile.security.bio.utils.StringUtil;
import com.taobao.weex.el.parse.Operators;
import java.util.HashMap;
import java.util.Map;

public class BioFragmentResponse {
    public int errorCode = 500;
    public Map<String, String> ext = new HashMap();
    public boolean isSucess;
    public String resultMessage;
    public String subCode;
    public String subMsg;
    public int suggest;
    public String token;

    public String toString() {
        return "BioFragmentResponse{errorCode=" + this.errorCode + ", subcode=" + this.subCode + ", submsg='" + this.subMsg + Operators.SINGLE_QUOTE + ", suggest=" + this.suggest + ", isSucess=" + this.isSucess + ", token='" + this.token + Operators.SINGLE_QUOTE + ", resultMessage='" + this.resultMessage + Operators.SINGLE_QUOTE + ", ext=" + StringUtil.collection2String(this.ext.keySet()) + Operators.BLOCK_END;
    }
}
