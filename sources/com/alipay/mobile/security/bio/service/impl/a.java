package com.alipay.mobile.security.bio.service.impl;

import com.alipay.mobile.security.bio.service.BioExtService;
import com.alipay.mobile.security.bio.service.BioService;
import com.alipay.mobile.security.bio.utils.BioLog;
import java.util.Collection;

class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ BioServiceManagerImpl f1011a;

    a(BioServiceManagerImpl bioServiceManagerImpl) {
        this.f1011a = bioServiceManagerImpl;
    }

    public void run() {
        Collection<BioService> values;
        synchronized (this.f1011a.d) {
            values = this.f1011a.d.values();
        }
        for (BioService bioService : values) {
            if (bioService instanceof BioExtService) {
                BioExtService bioExtService = (BioExtService) bioService;
                if (!bioExtService.isPreparing()) {
                    BioLog.i("loadingResource:" + bioExtService.getClass().getName());
                    bioExtService.loadingResource();
                }
            }
        }
        boolean unused = BioServiceManagerImpl.h = false;
    }
}
