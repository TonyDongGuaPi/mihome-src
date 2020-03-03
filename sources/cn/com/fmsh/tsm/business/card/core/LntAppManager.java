package cn.com.fmsh.tsm.business.card.core;

import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.script.exception.FMScriptHandleException;
import cn.com.fmsh.tsm.business.bean.CardAppRecord;
import cn.com.fmsh.tsm.business.card.base.CardManager;
import cn.com.fmsh.tsm.business.constants.Constants;
import cn.com.fmsh.tsm.business.enums.EnumCardAppStatus;
import cn.com.fmsh.tsm.business.enums.EnumTradeType;
import cn.com.fmsh.tsm.business.exception.BusinessException;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LntAppManager implements CardManager {
    private final int BYTE_COUNT_18 = 23;
    private final int MAX_LOG = 10;
    private final byte PERSONALIZE_SUCESS_FLAG = 2;
    private final byte[] aid = {89, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.SIM_SEID, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 83, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG};
    private ApduHandler apduHandler;
    FMLog fmLog = LogFactory.getInstance().getLog();
    private final String logTag = LntAppManager.class.getName();

    public String getMOC() throws BusinessException {
        return null;
    }

    public boolean isLock4Consume() throws BusinessException {
        return false;
    }

    public boolean isLock4Load() throws BusinessException {
        return false;
    }

    public byte[] getAid() {
        return this.aid;
    }

    public void setApduHandler(ApduHandler apduHandler2) {
        this.apduHandler = apduHandler2;
    }

    public int queryBalance() throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.debug(this.logTag, "LNT queryBalance...");
        }
        if (this.apduHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取交通卡余额时，Apdu处理器为空");
            }
            throw new BusinessException("获取交通卡余额时，Apdu处理器为空", BusinessException.ErrorMessage.local_business_apdu_handler_null);
        }
        byte[] bArr = new byte[7];
        bArr[1] = ScriptToolsConst.TagName.CommandMultiple;
        bArr[4] = 2;
        bArr[5] = -35;
        bArr[6] = -15;
        transceive(bArr);
        byte[] bArr2 = new byte[7];
        bArr2[1] = ScriptToolsConst.TagName.CommandMultiple;
        bArr2[4] = 2;
        bArr2[5] = -83;
        bArr2[6] = -13;
        transceive(bArr2);
        byte[] bArr3 = new byte[17];
        bArr3[0] = Byte.MIN_VALUE;
        bArr3[1] = Constants.TagName.ORDER_BRIEF_INFO_LIST;
        bArr3[2] = 3;
        bArr3[3] = 2;
        bArr3[4] = 11;
        bArr3[5] = 1;
        bArr3[16] = 15;
        byte[] transceive = transceive(bArr3);
        if (transceive.length < 9) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取交通卡余额时，APDU响应的数据无效");
            }
            throw new BusinessException("获取交通卡余额时，响应数据无效", BusinessException.ErrorMessage.local_get_app_info_fail);
        }
        return FM_Bytes.bytesToInt(Arrays.copyOf(transceive, 4)) - FM_Bytes.bytesToInt(new byte[]{transceive[6], transceive[7], transceive[8]});
    }

    public List<CardAppRecord> readAppRecords() throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        ArrayList arrayList = new ArrayList();
        if (this.fmLog != null) {
            this.fmLog.debug(this.logTag, "LNT readAppRecords...");
        }
        if (this.apduHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取交易记录时，Apdu处理器为空");
            }
            throw new BusinessException("获取交易记录时，Apdu处理器为空", BusinessException.ErrorMessage.local_business_apdu_handler_null);
        }
        byte[] bArr = new byte[7];
        bArr[1] = ScriptToolsConst.TagName.CommandMultiple;
        bArr[4] = 2;
        bArr[5] = -35;
        bArr[6] = -15;
        transceive(bArr);
        byte[] bArr2 = new byte[7];
        bArr2[1] = ScriptToolsConst.TagName.CommandMultiple;
        bArr2[4] = 2;
        bArr2[5] = -83;
        bArr2[6] = -13;
        transceive(bArr2);
        byte[] bArr3 = new byte[7];
        bArr3[1] = ScriptToolsConst.TagName.CommandMultiple;
        bArr3[4] = 2;
        bArr3[6] = 24;
        transceive(bArr3);
        for (int i = 1; i <= 10; i++) {
            byte[] bArr4 = new byte[5];
            bArr4[1] = Constants.TagName.APP_TYPE;
            bArr4[2] = (byte) i;
            bArr4[3] = 4;
            byte[] transceive = transceive(bArr4);
            if (Arrays.equals(new byte[]{Constants.TagName.PAY_ORDER_ID, -125}, Arrays.copyOfRange(transceive, transceive.length - 2, transceive.length))) {
                break;
            }
            if (transceive.length >= 23) {
                arrayList.add(getAppRecord4bytes(transceive));
            }
        }
        return arrayList;
    }

    public CardAppRecord getAppRecord4bytes(byte[] bArr) {
        CardAppRecord cardAppRecord = new CardAppRecord();
        cardAppRecord.setTradeNo(FM_Bytes.bytesToInt(new byte[]{bArr[0], bArr[1]}));
        cardAppRecord.setAmount(Integer.parseInt(FM_Bytes.bytesToHexString(new byte[]{bArr[5], bArr[6], bArr[7], bArr[8]}), 16));
        cardAppRecord.setTradeType(get18TradeType(bArr[9]));
        cardAppRecord.setTradeDevice(FM_Bytes.bytesToHexString(new byte[]{bArr[10], bArr[11], bArr[12], bArr[13], bArr[14], bArr[15]}));
        cardAppRecord.setTradeDate(FM_Bytes.bytesToHexString(new byte[]{bArr[18], bArr[19]}));
        cardAppRecord.setTradeTime(FM_Bytes.bytesToHexString(new byte[]{bArr[20], bArr[21], bArr[22]}));
        return cardAppRecord;
    }

    public String getFaceID() throws BusinessException {
        return FM_Bytes.bytesToHexString(getAppNo());
    }

    public byte[] getAppNo() throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.debug(this.logTag, "LNT getAppNo...");
        }
        if (this.apduHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "上海交通卡的应用序列号，Apdu处理器为空");
            }
            throw new BusinessException("上海交通卡的应用序列号，Apdu处理器为空", BusinessException.ErrorMessage.local_business_apdu_handler_null);
        }
        byte[] bArr = new byte[5];
        bArr[0] = Constants.TagName.USER_PLATFORM_TYPE;
        bArr[1] = -2;
        byte[] transceive = transceive(bArr);
        if (FM_Bytes.isEnd9000(transceive)) {
            return Arrays.copyOfRange(transceive, 0, 8);
        }
        return null;
    }

    private byte[] transceive(byte[] bArr) throws BusinessException {
        try {
            byte[] transceive = this.apduHandler.transceive(bArr);
            if (transceive != null && transceive.length >= 2) {
                return transceive;
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "Apdu指令执行结果为空");
            }
            if (this.apduHandler != null) {
                this.apduHandler.close();
            }
            throw new BusinessException("Apdu处理器处理结果无效", BusinessException.ErrorMessage.local_business_execute_fail);
        } catch (FMScriptHandleException e) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, "Apdu指令执行出现异常" + Util4Java.getExceptionInfo(e));
            }
            if (this.apduHandler != null) {
                this.apduHandler.close();
            }
            throw new BusinessException("Apdu指令执行出现异常", BusinessException.ErrorMessage.local_business_execute_fail);
        }
    }

    private EnumTradeType get18TradeType(byte b) {
        if (b == 2) {
            return EnumTradeType.recharge;
        }
        if (b == 6) {
            return EnumTradeType.Consumption;
        }
        if (b != 9) {
            return null;
        }
        return EnumTradeType.CompositeConsumption;
    }

    public EnumCardAppStatus getStatus() throws BusinessException {
        EnumCardAppStatus enumCardAppStatus = EnumCardAppStatus.STATUS_INSTALL;
        if (this.apduHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取卡上应用当前状态时，Apdu处理器为空");
            }
            throw new BusinessException("获取卡上应用当前状态时，Apdu处理器为空", BusinessException.ErrorMessage.local_business_apdu_handler_null);
        }
        byte[] bArr = new byte[7];
        bArr[1] = ScriptToolsConst.TagName.CommandMultiple;
        bArr[4] = 2;
        bArr[5] = -35;
        bArr[6] = -15;
        try {
            if (!FM_Bytes.isEnd9000(this.apduHandler.transceive(bArr))) {
                return enumCardAppStatus;
            }
            byte[] bArr2 = new byte[7];
            bArr2[1] = ScriptToolsConst.TagName.CommandMultiple;
            bArr2[4] = 2;
            bArr2[5] = -83;
            bArr2[6] = -13;
            try {
                if (!FM_Bytes.isEnd9000(this.apduHandler.transceive(bArr2))) {
                    return enumCardAppStatus;
                }
                byte[] bArr3 = new byte[5];
                bArr3[1] = 10;
                try {
                    byte[] transceive = this.apduHandler.transceive(bArr3);
                    if (!FM_Bytes.isEnd9000(transceive)) {
                        return enumCardAppStatus;
                    }
                    if (transceive.length < 5) {
                        if (this.fmLog != null) {
                            this.fmLog.error(this.logTag, "获取卡上应用当前状态时，个人化结果指令响应无效");
                        }
                        return enumCardAppStatus;
                    } else if (2 != transceive[2]) {
                        return enumCardAppStatus;
                    } else {
                        EnumCardAppStatus enumCardAppStatus2 = EnumCardAppStatus.STATUS_PERSONALIZED;
                        byte[] bArr4 = new byte[16];
                        bArr4[0] = Byte.MIN_VALUE;
                        bArr4[1] = Constants.TagName.ORDER_BRIEF_INFO_LIST;
                        bArr4[2] = 1;
                        bArr4[3] = 2;
                        bArr4[4] = 11;
                        bArr4[5] = 2;
                        try {
                            if (!FM_Bytes.isEnd9000(this.apduHandler.transceive(bArr4))) {
                                return enumCardAppStatus2;
                            }
                            return EnumCardAppStatus.STATUS_ACTIVATE;
                        } catch (FMScriptHandleException e) {
                            if (this.fmLog != null) {
                                FMLog fMLog = this.fmLog;
                                String str = this.logTag;
                                fMLog.error(str, "判断卡是否开通时，圈存初始化失败:" + Util4Java.getExceptionInfo(e));
                            }
                            return enumCardAppStatus2;
                        }
                    }
                } catch (FMScriptHandleException e2) {
                    if (this.fmLog != null) {
                        FMLog fMLog2 = this.fmLog;
                        String str2 = this.logTag;
                        fMLog2.error(str2, "获取卡上应用当前状态时，读取0015文件失败:" + Util4Java.getExceptionInfo(e2));
                    }
                    return enumCardAppStatus;
                }
            } catch (FMScriptHandleException e3) {
                if (this.fmLog != null) {
                    FMLog fMLog3 = this.fmLog;
                    String str3 = this.logTag;
                    fMLog3.error(str3, "获取卡上应用当前状态时， 选择ADF3失败:" + Util4Java.getExceptionInfo(e3));
                }
                return enumCardAppStatus;
            }
        } catch (FMScriptHandleException e4) {
            if (this.fmLog != null) {
                FMLog fMLog4 = this.fmLog;
                String str4 = this.logTag;
                fMLog4.error(str4, "获取卡上应用当前状态时， 选择DDF1失败:" + Util4Java.getExceptionInfo(e4));
            }
            return enumCardAppStatus;
        }
    }

    public String getTime4Validity() throws BusinessException {
        byte[] bArr = new byte[7];
        bArr[1] = ScriptToolsConst.TagName.CommandMultiple;
        bArr[4] = 2;
        bArr[5] = -35;
        bArr[6] = -15;
        byte[] transceive = transceive(bArr);
        if (!FM_Bytes.isEnd9000(transceive)) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.error(str, "获取卡上应用有效期时，选择应用目录失败：" + FM_Bytes.bytesToHexString(transceive));
            }
            throw new BusinessException("获取卡上应用有效期时，选择应用目录指令处理失败", BusinessException.ErrorMessage.local_business_execute_fail);
        }
        byte[] bArr2 = new byte[5];
        bArr2[1] = -80;
        bArr2[2] = Constants.TagName.PREDEPOSIT_TYPE;
        byte[] transceive2 = transceive(bArr2);
        if (!FM_Bytes.isEnd9000(transceive2)) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.error(str2, "获取卡上应用有效期时，读取15文件失败：" + FM_Bytes.bytesToHexString(transceive2));
            }
            throw new BusinessException("获取卡上应用有效期时，读取15文件失败", BusinessException.ErrorMessage.local_business_execute_fail);
        } else if (transceive2.length < 31) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str3 = this.logTag;
                fMLog3.error(str3, "获取卡上应用有效期时，读取15文件失败：" + FM_Bytes.bytesToHexString(transceive2));
            }
            throw new BusinessException("获取卡上应用有效期时，读取15文件失败", BusinessException.ErrorMessage.local_business_execute_fail);
        } else {
            return FM_Bytes.bytesToHexString(new byte[]{transceive2[27], transceive2[28], transceive2[29], transceive2[30]});
        }
    }
}
