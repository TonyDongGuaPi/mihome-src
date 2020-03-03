package com.alipay.mobile.security.zim.gw;

import cn.com.fmsh.communication.message.constants.Constants;
import com.alipay.mobile.security.bio.service.BioUploadResult;
import com.alipay.mobile.security.bio.service.BioUploadServiceCore;
import com.alipay.mobile.security.zim.a.a;
import com.alipay.mobile.security.zim.a.d;
import java.util.HashMap;
import java.util.Map;

public abstract class b<Request> extends BioUploadServiceCore<Request> {

    /* renamed from: a  reason: collision with root package name */
    protected String f1053a;

    /* access modifiers changed from: protected */
    public abstract BioUploadResult doUpload(Request request);

    public void setZimId(String str) {
        this.f1053a = str;
    }

    public BioUploadResult upload(Request request, boolean z) {
        if (z) {
            a.a().b(a.g);
        }
        BioUploadResult doUpload = doUpload(request);
        if (z) {
            a(doUpload);
        }
        return doUpload;
    }

    private void a(BioUploadResult bioUploadResult) {
        HashMap hashMap = new HashMap();
        if (bioUploadResult != null) {
            int i = bioUploadResult.validationRetCode;
            if (i == 100 || i == 1000) {
                hashMap.put("result", d.f1046a);
            } else {
                hashMap.put("result", d.b);
            }
            hashMap.put("message", "");
            hashMap.put(Constants.XMLNode.XMLMessage.MESSAGE_RET_CODE, "" + bioUploadResult.validationRetCode);
            hashMap.put("subCode", bioUploadResult.subCode);
            hashMap.put("subMsg", bioUploadResult.subMsg);
        } else {
            hashMap.put("result", d.b);
            hashMap.put("message", "0");
            hashMap.put(Constants.XMLNode.XMLMessage.MESSAGE_RET_CODE, "0");
            hashMap.put("subCode", "");
            hashMap.put("subMsg", "");
        }
        a.a().a(a.h, (Map<String, String>) hashMap);
    }
}
