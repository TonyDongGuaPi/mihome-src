package cn.com.fmsh.tsm.business;

import cn.com.fmsh.tsm.business.bean.CardAppInfo;
import cn.com.fmsh.tsm.business.bean.IssuerPrepareResult;
import cn.com.fmsh.tsm.business.enums.EnumAppManageOperateType;
import cn.com.fmsh.tsm.business.enums.EnumCardAppStatus;
import cn.com.fmsh.tsm.business.enums.EnumCardAppType;
import cn.com.fmsh.tsm.business.enums.EnumCardBusinessOpType;
import cn.com.fmsh.tsm.business.exception.BusinessException;

public interface CardAppInstall {
    boolean appletDownload(EnumCardAppType enumCardAppType, byte[] bArr, String str) throws BusinessException;

    boolean applyBusiness(String str, byte[] bArr, String str2, byte[] bArr2) throws BusinessException;

    void cancel();

    CardAppInfo deleteApp(byte[] bArr, EnumCardAppType enumCardAppType, byte[] bArr2, String str) throws BusinessException;

    byte[] deleteAppVer1(byte[] bArr, EnumCardAppType enumCardAppType, byte[] bArr2, String str) throws BusinessException;

    EnumCardAppStatus getAppIssuerStatus(EnumCardAppType enumCardAppType) throws BusinessException;

    EnumCardAppStatus getAppIssuerStatus4Platform(EnumCardAppType enumCardAppType, String str, byte[] bArr) throws BusinessException;

    boolean issuePrepare(byte[] bArr, String str, byte[] bArr2, byte[] bArr3, String str2, String str3, byte[] bArr4, IssuerPrepareResult issuerPrepareResult) throws BusinessException;

    boolean issuePrepareResultSearch(byte[] bArr, IssuerPrepareResult issuerPrepareResult) throws BusinessException;

    boolean issuer(byte[] bArr, int i, byte[] bArr2, byte[] bArr3) throws BusinessException;

    boolean issuerVer2(byte[] bArr, int i, byte[] bArr2, byte[] bArr3) throws BusinessException;

    byte[] moveApp(byte[] bArr, EnumCardAppType enumCardAppType, byte[] bArr2, String str) throws BusinessException;

    int orderExce(byte[] bArr, byte[] bArr2) throws BusinessException;

    boolean personlization(String str) throws BusinessException;

    void registerIssuerProcessHandler(IssuerProcessHandler issuerProcessHandler);

    boolean setApp(byte[] bArr, EnumCardAppType enumCardAppType, byte[] bArr2, String str, EnumAppManageOperateType enumAppManageOperateType) throws BusinessException;

    boolean setAppVer2(byte[] bArr, EnumCardAppType enumCardAppType, byte[] bArr2, String str, EnumAppManageOperateType enumAppManageOperateType) throws BusinessException;

    int setCardBusinessStatus(EnumCardBusinessOpType enumCardBusinessOpType, String str, String str2, int i, byte[] bArr, byte[] bArr2, String str3) throws BusinessException;

    int setCardBusinessStatusVer2(EnumCardBusinessOpType enumCardBusinessOpType, String str, String str2, int i, byte[] bArr, byte[] bArr2, String str3, byte[] bArr3) throws BusinessException;

    boolean writeTicket(String str, byte[] bArr) throws BusinessException;
}
