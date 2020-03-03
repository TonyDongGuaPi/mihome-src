package com.alipay.mobile.security.bio.service.impl;

import android.util.Base64;
import com.alipay.bis.common.service.facade.gw.model.upload.BisJsonUploadGwRequest;
import com.alipay.mobile.security.bio.service.BioServiceManager;
import com.alipay.mobile.security.bio.service.BioStoreParameter;
import com.alipay.mobile.security.bio.service.BioStoreResult;
import com.alipay.mobile.security.bio.service.BioStoreService;
import com.alipay.mobile.security.bio.service.BioUploadItem;
import com.alipay.mobile.security.bio.service.BioUploadResult;
import com.alipay.mobile.security.bio.utils.StringUtil;

public class BioUploadJsonGWImpl extends BioUploadGW<BisJsonUploadGwRequest> {
    public BioUploadJsonGWImpl(BioServiceManager bioServiceManager) {
        super(bioServiceManager);
    }

    public BioUploadResult upload(BioUploadItem bioUploadItem) {
        if (StringUtil.isNullorEmpty(bioUploadItem.publicKey)) {
            return b(bioUploadItem);
        }
        return a(bioUploadItem);
    }

    private BioUploadResult a(BioUploadItem bioUploadItem) {
        BioStoreService bioStoreService = (BioStoreService) this.f1008a.getBioService(BioStoreService.class);
        byte[] random = bioStoreService.getRandom();
        BioStoreParameter bioStoreParameter = new BioStoreParameter();
        bioStoreParameter.content = bioUploadItem.log;
        bioStoreParameter.publicKey = bioUploadItem.publicKey;
        bioStoreParameter.random = random;
        BioStoreResult encryptWithRandom = bioStoreService.encryptWithRandom(bioStoreParameter);
        BisJsonUploadGwRequest bisJsonUploadGwRequest = new BisJsonUploadGwRequest();
        bisJsonUploadGwRequest.bisToken = bioUploadItem.bisToken;
        if (encryptWithRandom != null) {
            bisJsonUploadGwRequest.behavLog = Base64.encodeToString(encryptWithRandom.encodeContent, 10);
            bisJsonUploadGwRequest.behavLogSig = Base64.encodeToString(encryptWithRandom.encodeSeed, 10);
        }
        if (bioUploadItem.content == null) {
            return a(bisJsonUploadGwRequest, bioUploadItem.isNeedSendResponse);
        }
        bisJsonUploadGwRequest.content = new String(bioUploadItem.content);
        bisJsonUploadGwRequest.contentSig = Base64.encodeToString(bioUploadItem.contentSig, 10);
        return a(bisJsonUploadGwRequest, bioUploadItem.isNeedSendResponse);
    }

    private BioUploadResult b(BioUploadItem bioUploadItem) {
        BisJsonUploadGwRequest bisJsonUploadGwRequest = new BisJsonUploadGwRequest();
        bisJsonUploadGwRequest.bisToken = bioUploadItem.bisToken;
        bisJsonUploadGwRequest.behavLogSig = "";
        bisJsonUploadGwRequest.contentSig = "";
        bisJsonUploadGwRequest.content = new String(bioUploadItem.content);
        bisJsonUploadGwRequest.behavLog = Base64.encodeToString(bioUploadItem.log, 10);
        return a(bisJsonUploadGwRequest, bioUploadItem.isNeedSendResponse);
    }
}
