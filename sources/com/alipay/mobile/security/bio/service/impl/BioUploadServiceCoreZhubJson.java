package com.alipay.mobile.security.bio.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.bis.common.service.facade.gw.model.upload.BisJsonUploadGwRequest;
import com.alipay.bis.common.service.facade.gw.zim.ZimDispatchJsonGwFacade;
import com.alipay.bis.common.service.facade.gw.zim.ZimValidateGwResponse;
import com.alipay.bis.common.service.facade.gw.zim.ZimValidateJsonGwRequest;
import com.alipay.mobile.security.bio.constants.CodeConstants;
import com.alipay.mobile.security.bio.service.BioUploadResult;
import com.alipay.mobile.security.bio.service.local.rpc.BioRPCService;
import com.alipay.mobile.security.bio.service.local.rpc.IRpcException;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.mobile.security.zim.gw.b;

public class BioUploadServiceCoreZhubJson extends b<BisJsonUploadGwRequest> {
    public BioUploadResult doUpload(BisJsonUploadGwRequest bisJsonUploadGwRequest) {
        BioUploadResult bioUploadResult = new BioUploadResult();
        try {
            ZimValidateJsonGwRequest zimValidateJsonGwRequest = new ZimValidateJsonGwRequest();
            zimValidateJsonGwRequest.zimId = this.f1053a;
            zimValidateJsonGwRequest.zimData = JSON.toJSONString(bisJsonUploadGwRequest);
            BioLog.w("upload(): request= " + bisJsonUploadGwRequest);
            ZimValidateGwResponse validateStandard = ((ZimDispatchJsonGwFacade) ((BioRPCService) this.mBioServiceManager.getBioService(BioRPCService.class)).getRpcProxy(ZimDispatchJsonGwFacade.class)).validateStandard(zimValidateJsonGwRequest);
            BioLog.w("upload(): response= " + validateStandard);
            if (validateStandard == null) {
                bioUploadResult.validationRetCode = 1001;
                bioUploadResult.productRetCode = 3002;
                bioUploadResult.subCode = CodeConstants.SERVER_PARAM_ERROR;
                bioUploadResult.subMsg = CodeConstants.getMessage(CodeConstants.SERVER_PARAM_ERROR);
            } else {
                bioUploadResult.productRetCode = validateStandard.productRetCode;
                bioUploadResult.validationRetCode = validateStandard.validationRetCode;
                bioUploadResult.hasNext = validateStandard.hasNext;
                bioUploadResult.nextProtocol = validateStandard.nextProtocol;
                bioUploadResult.extParams = validateStandard.extParams;
                bioUploadResult.subCode = validateStandard.retCodeSub;
                bioUploadResult.subMsg = validateStandard.retMessageSub;
            }
        } catch (Exception e) {
            BioLog.w((Throwable) e);
            if (e instanceof IRpcException) {
                int code = ((IRpcException) e).getCode();
                if (code == 4001 || code == 5 || code == 16 || code == 2) {
                    bioUploadResult.validationRetCode = 3001;
                    bioUploadResult.productRetCode = 3002;
                    bioUploadResult.subCode = CodeConstants.NETWORK_ERROR;
                    bioUploadResult.subMsg = CodeConstants.getMessage(CodeConstants.NETWORK_ERROR);
                } else {
                    bioUploadResult.validationRetCode = 1001;
                    bioUploadResult.productRetCode = 3002;
                    bioUploadResult.subCode = CodeConstants.SERVER_PARAM_ERROR;
                    bioUploadResult.subMsg = CodeConstants.getMessage(CodeConstants.SERVER_PARAM_ERROR);
                }
            } else {
                bioUploadResult.validationRetCode = 1001;
                bioUploadResult.productRetCode = 3002;
                bioUploadResult.subCode = CodeConstants.SERVER_PARAM_ERROR;
                bioUploadResult.subMsg = CodeConstants.getMessage(CodeConstants.SERVER_PARAM_ERROR);
            }
        }
        return bioUploadResult;
    }
}
