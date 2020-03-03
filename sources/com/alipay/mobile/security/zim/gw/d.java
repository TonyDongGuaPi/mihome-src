package com.alipay.mobile.security.zim.gw;

import com.alipay.bis.common.service.facade.gw.zim.ZimDispatchJsonGwFacade;
import com.alipay.bis.common.service.facade.gw.zim.ZimInitGwRequest;
import com.alipay.bis.common.service.facade.gw.zim.ZimInitGwResponse;
import com.alipay.mobile.security.bio.service.BioServiceManager;
import com.alipay.mobile.security.bio.service.local.rpc.BioRPCService;
import com.alipay.mobile.security.bio.service.local.rpc.IRpcException;
import com.alipay.mobile.security.bio.utils.BioLog;

class d implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ZimInitGwRequest f1054a;
    final /* synthetic */ JsonGwService b;

    d(JsonGwService jsonGwService, ZimInitGwRequest zimInitGwRequest) {
        this.b = jsonGwService;
        this.f1054a = zimInitGwRequest;
    }

    public void run() {
        ZimInitGwResponse zimInitGwResponse;
        BioLog.d("" + this.f1054a);
        ZimDispatchJsonGwFacade zimDispatchJsonGwFacade = (ZimDispatchJsonGwFacade) ((BioRPCService) BioServiceManager.getCurrentInstance().getBioService(BioRPCService.class)).getRpcProxy(ZimDispatchJsonGwFacade.class);
        try {
            if (this.f1054a.zimId.contains("_bis")) {
                zimInitGwResponse = zimDispatchJsonGwFacade.init(this.f1054a);
            } else {
                zimInitGwResponse = zimDispatchJsonGwFacade.initStandard(this.f1054a);
            }
        } catch (Throwable th) {
            BioLog.w(th);
            ZimInitGwResponse zimInitGwResponse2 = new ZimInitGwResponse();
            if (th instanceof IRpcException) {
                zimInitGwResponse2.retCode = 2002;
            } else {
                zimInitGwResponse2.retCode = 200;
            }
            zimInitGwResponse = zimInitGwResponse2;
        }
        if (zimInitGwResponse == null) {
            zimInitGwResponse = new ZimInitGwResponse();
            zimInitGwResponse.retCode = 200;
        }
        BioLog.d("" + zimInitGwResponse);
        if (this.b.mMainHandler != null) {
            this.b.mMainHandler.post(new e(this, zimInitGwResponse));
        }
    }
}
