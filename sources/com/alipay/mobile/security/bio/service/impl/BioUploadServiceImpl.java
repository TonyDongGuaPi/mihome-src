package com.alipay.mobile.security.bio.service.impl;

import com.alipay.mobile.security.bio.service.BioServiceManager;
import com.alipay.mobile.security.bio.service.BioUploadCallBack;
import com.alipay.mobile.security.bio.service.BioUploadItem;
import com.alipay.mobile.security.bio.service.BioUploadService;

public class BioUploadServiceImpl extends BioUploadService {

    /* renamed from: a  reason: collision with root package name */
    private BioUploadWatchThread f1009a;

    public void addCallBack(BioUploadCallBack bioUploadCallBack) {
        if (this.f1009a != null && bioUploadCallBack != null) {
            this.f1009a.addBioUploadCallBack(bioUploadCallBack);
        }
    }

    public int upload(BioUploadItem bioUploadItem) {
        if (this.f1009a == null) {
            return 0;
        }
        this.f1009a.addBioUploadItem(bioUploadItem);
        return 0;
    }

    public boolean isFulled() {
        if (this.f1009a != null) {
            return this.f1009a.isFulled();
        }
        return false;
    }

    public void onCreate(BioServiceManager bioServiceManager) {
        this.f1009a = new BioUploadWatchThread("BioUploadService", bioServiceManager);
        this.f1009a.start();
    }

    public void clearUp() {
        if (this.f1009a != null && this.f1009a.isEmpty()) {
            this.f1009a.clearUploadItems();
            this.f1009a.clearBioUploadCallBacks();
        }
    }

    public void onDestroy() {
        if (this.f1009a != null && this.f1009a.isEmpty()) {
            this.f1009a.release();
            this.f1009a = null;
        }
    }
}
