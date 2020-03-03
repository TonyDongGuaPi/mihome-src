package com.alipay.mobile.security.bio.service.impl;

import com.alipay.mobile.security.bio.exception.BioIllegalArgumentException;
import com.alipay.mobile.security.bio.service.BioServiceManager;
import com.alipay.mobile.security.bio.service.BioUploadItem;
import com.alipay.mobile.security.bio.service.BioUploadResult;
import com.alipay.mobile.security.bio.service.BioUploadServiceCore;

public abstract class BioUploadGW<Request> {
    public static final int BASE64_FLAGS = 10;

    /* renamed from: a  reason: collision with root package name */
    protected BioServiceManager f1008a;

    public abstract BioUploadResult upload(BioUploadItem bioUploadItem);

    public BioUploadGW(BioServiceManager bioServiceManager) {
        if (bioServiceManager != null) {
            this.f1008a = bioServiceManager;
            return;
        }
        throw new BioIllegalArgumentException("BioServiceManager can't be null");
    }

    /* access modifiers changed from: package-private */
    public BioUploadResult a(Request request, boolean z) {
        return ((BioUploadServiceCore) this.f1008a.getBioService(BioUploadServiceCore.class)).upload(request, z);
    }
}
