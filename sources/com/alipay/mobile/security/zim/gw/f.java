package com.alipay.mobile.security.zim.gw;

import com.alipay.bis.common.service.facade.gw.zim.ZimDispatchJsonGwFacade;
import com.alipay.bis.common.service.facade.gw.zim.ZimValidateGwResponse;
import com.alipay.bis.common.service.facade.gw.zim.ZimValidateJsonGwRequest;
import com.alipay.mobile.security.bio.api.BioResponse;
import com.alipay.mobile.security.bio.service.BioServiceManager;
import com.alipay.mobile.security.bio.service.local.rpc.BioRPCService;
import com.alipay.mobile.security.bio.utils.BioLog;
import java.util.HashMap;
import java.util.Map;

class f implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ZimValidateJsonGwRequest f1056a;
    final /* synthetic */ BioResponse b;
    final /* synthetic */ JsonGwService c;

    f(JsonGwService jsonGwService, ZimValidateJsonGwRequest zimValidateJsonGwRequest, BioResponse bioResponse) {
        this.c = jsonGwService;
        this.f1056a = zimValidateJsonGwRequest;
        this.b = bioResponse;
    }

    public void run() {
        ZimValidateGwResponse zimValidateGwResponse;
        Map<String, String> ext;
        BioLog.d("" + this.f1056a);
        try {
            zimValidateGwResponse = ((ZimDispatchJsonGwFacade) ((BioRPCService) BioServiceManager.getCurrentInstance().getBioService(BioRPCService.class)).getRpcProxy(ZimDispatchJsonGwFacade.class)).validate(this.f1056a);
        } catch (Throwable th) {
            BioLog.w(th);
            zimValidateGwResponse = null;
        }
        if (zimValidateGwResponse == null) {
            zimValidateGwResponse = new ZimValidateGwResponse();
            zimValidateGwResponse.productRetCode = 1001;
            zimValidateGwResponse.validationRetCode = 1001;
        }
        BioLog.d("" + zimValidateGwResponse);
        if (!(this.b == null || (ext = this.b.getExt()) == null || ext.isEmpty())) {
            if (zimValidateGwResponse.extParams == null) {
                zimValidateGwResponse.extParams = new HashMap();
            }
            zimValidateGwResponse.extParams.putAll(ext);
        }
        if (this.c.mMainHandler != null) {
            this.c.mMainHandler.post(new g(this, zimValidateGwResponse));
        }
    }
}
