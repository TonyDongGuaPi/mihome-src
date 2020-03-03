package cn.com.fmsh.tsm.business.card.base;

import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.tsm.business.bean.CardAppRecord;
import cn.com.fmsh.tsm.business.enums.EnumCardAppStatus;
import cn.com.fmsh.tsm.business.exception.BusinessException;
import java.util.List;

public interface CardManager {
    byte[] getAid();

    byte[] getAppNo() throws BusinessException;

    String getFaceID() throws BusinessException;

    String getMOC() throws BusinessException;

    EnumCardAppStatus getStatus() throws BusinessException;

    String getTime4Validity() throws BusinessException;

    boolean isLock4Consume() throws BusinessException;

    boolean isLock4Load() throws BusinessException;

    int queryBalance() throws BusinessException;

    List<CardAppRecord> readAppRecords() throws BusinessException;

    void setApduHandler(ApduHandler apduHandler);
}
