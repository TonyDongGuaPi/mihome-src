package cn.com.fmsh.tsm.business;

import cn.com.fmsh.tsm.business.bean.ElectronicAndActivity;
import cn.com.fmsh.tsm.business.bean.ElectronicTakeUp;
import cn.com.fmsh.tsm.business.bean.IdentifyingCode;
import cn.com.fmsh.tsm.business.bean.MainOrder;
import cn.com.fmsh.tsm.business.bean.PromotionMessage;
import cn.com.fmsh.tsm.business.enums.EnumCardIoType;
import cn.com.fmsh.tsm.business.exception.BusinessException;
import java.util.List;

public interface BusinessExtend {
    int applyForElectronicTakeUp(byte[] bArr, byte[] bArr2) throws BusinessException;

    MainOrder applyPromotion(byte[] bArr) throws BusinessException;

    IdentifyingCode obtainIdentifyingCode(int i, String str) throws BusinessException;

    ElectronicAndActivity queryActivity(int i, int i2) throws BusinessException;

    ElectronicTakeUp queryElectronicTakeUp(byte[] bArr, byte[] bArr2) throws BusinessException;

    List<ElectronicTakeUp> queryElectronicTakeUps(byte[] bArr, byte b, int i) throws BusinessException;

    List<ElectronicTakeUp> queryElectronicTakeUpsVer2(byte[] bArr, byte[] bArr2, int i, int i2) throws BusinessException;

    List<PromotionMessage> queryPromotionMessage() throws BusinessException;

    MainOrder useElectronicTakeUp(byte[] bArr, byte[] bArr2, byte[] bArr3, EnumCardIoType enumCardIoType) throws BusinessException;
}
