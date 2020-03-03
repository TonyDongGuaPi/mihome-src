package cn.com.fmsh.nfcos.client.service.business;

import cn.com.fmsh.nfcos.client.service.enums.EnumTicketType;
import cn.com.fmsh.nfcos.client.service.localdata.DBHelper;
import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.script.exception.FMScriptHandleException;
import cn.com.fmsh.tsm.business.constants.Constants;
import cn.com.fmsh.tsm.business.exception.BusinessException;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.util.ArrayList;
import java.util.List;

public class ShengtongCardAppManager {
    private static final int MIN_SOLT = 1;
    private static final byte[] aid = {Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.ORDER_BRIEF_INFO_LIST, 77, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BACK_INFO_TYPE, 48, 50, 64};
    private static final byte[] cis;
    private static ShengtongCardAppManager instance;
    private static final byte[] pfAid;
    private ApduHandler apduHandler;
    private DBHelper dbhelper = null;
    FMLog fmLog = LogFactory.getInstance().getLog();
    private final String logTag = ShengtongCardAppManager.class.getName();

    public void setDbhelper(DBHelper dBHelper) {
        this.dbhelper = dBHelper;
    }

    public static ShengtongCardAppManager getInstance() {
        if (instance == null) {
            instance = new ShengtongCardAppManager();
        }
        return instance;
    }

    private ShengtongCardAppManager() {
    }

    public void setApduHandler(ApduHandler apduHandler2) {
        if (apduHandler2 == null) {
            this.fmLog.warn(this.logTag, "setApduHandler, apduHandler is null");
        }
        this.apduHandler = apduHandler2;
    }

    public int useTheTicket(int i) throws BusinessException {
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "shentong useTheTicket...");
        }
        byte[] aIDBySlot = getAIDBySlot(i);
        String str = "激活申通票（卡槽:" + i + "）时，";
        if (this.apduHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf(str) + "Apdu处理器为空");
            }
            throw new BusinessException(String.valueOf(str) + "Apdu处理器为空", BusinessException.ErrorMessage.local_business_apdu_handler_null);
        } else if (this.apduHandler.isConnect()) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf(str) + "Apdu处理器正在忙");
            }
            throw new BusinessException(String.valueOf(str) + "Apdu处理器正在忙", BusinessException.ErrorMessage.local_business_apdu_handler_busying);
        } else {
            try {
                if (!this.apduHandler.open(cis)) {
                    if (this.fmLog != null) {
                        this.fmLog.warn(this.logTag, String.valueOf(str) + "选择CIS失败");
                    }
                    throw new BusinessException(String.valueOf(str) + "选择CIS失败", BusinessException.ErrorMessage.local_apdu_reponse_invalid);
                }
                byte[] bArr = {Byte.MIN_VALUE, -16, 1, 1, 11, Constants.TagName.CP_NO, 9};
                byte[] transceive = this.apduHandler.transceive(FM_Bytes.concatArrays(bArr, aIDBySlot));
                if (this.fmLog != null) {
                    this.fmLog.info(this.logTag, String.valueOf(str) + "激活AID结果:" + FM_Bytes.bytesToHexString(transceive));
                }
                if (FM_Bytes.isEnd(transceive, new byte[]{Constants.TagName.PAY_ORDER, 48}) && transceive.length >= 25) {
                    byte[] copyOfRange = FM_Bytes.copyOfRange(transceive, 17, 26);
                    if (this.fmLog != null) {
                        this.fmLog.info(this.logTag, String.valueOf(str) + "去激活AID:" + FM_Bytes.bytesToHexString(copyOfRange));
                    }
                    byte[] bArr2 = new byte[7];
                    bArr2[0] = Byte.MIN_VALUE;
                    bArr2[1] = -16;
                    bArr2[2] = 1;
                    bArr2[4] = 11;
                    bArr2[5] = Constants.TagName.CP_NO;
                    bArr2[6] = 9;
                    byte[] transceive2 = this.apduHandler.transceive(FM_Bytes.concatArrays(bArr2, copyOfRange));
                    if (this.fmLog != null) {
                        this.fmLog.info(this.logTag, String.valueOf(str) + "去激活结果:" + FM_Bytes.bytesToHexString(transceive2));
                        FMLog fMLog = this.fmLog;
                        String str2 = this.logTag;
                        StringBuilder sb = new StringBuilder(String.valueOf(str));
                        sb.append("判断结果,isEnd9000:");
                        sb.append(FM_Bytes.isEnd9000(transceive2));
                        sb.append(";isEnd:");
                        byte[] bArr3 = new byte[2];
                        bArr3[0] = Constants.TagName.SYSTEM_VERSION;
                        sb.append(FM_Bytes.isEnd(transceive2, bArr3));
                        fMLog.info(str2, sb.toString());
                    }
                    if (!FM_Bytes.isEnd9000(transceive2)) {
                        if (this.fmLog != null) {
                            this.fmLog.warn(this.logTag, String.valueOf(str) + "去激活指令失败");
                        }
                        throw new BusinessException(String.valueOf(str) + "去激活指令失败", BusinessException.ErrorMessage.local_apdu_reponse_invalid);
                    }
                    byte[] transceive3 = this.apduHandler.transceive(FM_Bytes.concatArrays(bArr, aIDBySlot));
                    if (this.fmLog != null) {
                        this.fmLog.info(this.logTag, String.valueOf(str) + "再次激活结果:" + FM_Bytes.bytesToHexString(transceive3));
                        FMLog fMLog2 = this.fmLog;
                        String str3 = this.logTag;
                        StringBuilder sb2 = new StringBuilder(String.valueOf(str));
                        sb2.append("判断结果,isEnd9000:");
                        sb2.append(FM_Bytes.isEnd9000(transceive3));
                        sb2.append(";isEnd:");
                        byte[] bArr4 = new byte[2];
                        bArr4[0] = Constants.TagName.SYSTEM_VERSION;
                        sb2.append(FM_Bytes.isEnd(transceive3, bArr4));
                        fMLog2.info(str3, sb2.toString());
                    }
                    if (!FM_Bytes.isEnd9000(transceive3)) {
                        if (this.fmLog != null) {
                            this.fmLog.warn(this.logTag, String.valueOf(str) + "再激活指令失败");
                        }
                        throw new BusinessException(String.valueOf(str) + "再激活指令失败", BusinessException.ErrorMessage.local_apdu_reponse_invalid);
                    }
                } else if (!FM_Bytes.isEnd9000(transceive)) {
                    if (this.fmLog != null) {
                        this.fmLog.warn(this.logTag, String.valueOf(str) + "激活指令失败");
                    }
                    throw new BusinessException(String.valueOf(str) + "激活指令失败", BusinessException.ErrorMessage.local_apdu_reponse_invalid);
                }
                byte length = (byte) (pfAid.length & 255);
                byte[] transceive4 = this.apduHandler.transceive(FM_Bytes.concatArrays(new byte[]{Byte.MIN_VALUE, -16, 1, 1, (byte) (length + 2), Constants.TagName.CP_NO, length}, pfAid));
                if (this.fmLog != null) {
                    this.fmLog.info(this.logTag, String.valueOf(str) + "激活伪浦发结果:" + FM_Bytes.bytesToHexString(transceive4));
                    FMLog fMLog3 = this.fmLog;
                    String str4 = this.logTag;
                    StringBuilder sb3 = new StringBuilder(String.valueOf(str));
                    sb3.append("判断结果,isEnd9000:");
                    sb3.append(FM_Bytes.isEnd9000(transceive4));
                    sb3.append(";isEnd:");
                    byte[] bArr5 = new byte[2];
                    bArr5[0] = Constants.TagName.SYSTEM_VERSION;
                    sb3.append(FM_Bytes.isEnd(transceive4, bArr5));
                    fMLog3.info(str4, sb3.toString());
                }
                if (!FM_Bytes.isEnd9000(transceive4)) {
                    if (this.fmLog != null) {
                        this.fmLog.warn(this.logTag, String.valueOf(str) + "激活浦发应用失败");
                    }
                    throw new BusinessException(String.valueOf(str) + "激活浦发应用失败", BusinessException.ErrorMessage.local_apdu_reponse_invalid);
                }
                this.apduHandler.close();
                return 0;
            } catch (FMScriptHandleException e) {
                e.printStackTrace();
                throw new BusinessException(String.valueOf(str) + "Apdu处理器执行失败", BusinessException.ErrorMessage.local_business_execute_fail);
            } catch (Throwable th) {
                Throwable th2 = th;
                this.apduHandler.close();
                throw th2;
            }
        }
    }

    public STTicketInfo getTicketInfoBySlot(int i) throws BusinessException {
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "shentong getTicketInfoBySlot...");
        }
        byte[] aIDBySlot = getAIDBySlot(i);
        String str = "获取申通票基本信息（卡槽:" + i + "）时，";
        if (this.apduHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf(str) + "Apdu处理器为空");
            }
            throw new BusinessException(String.valueOf(str) + "Apdu处理器为空", BusinessException.ErrorMessage.local_business_apdu_handler_null);
        } else if (this.apduHandler.isConnect()) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf(str) + "Apdu处理器正在忙");
            }
            throw new BusinessException(String.valueOf(str) + "Apdu处理器正在忙", BusinessException.ErrorMessage.local_business_apdu_handler_busying);
        } else {
            try {
                if (!this.apduHandler.open(aIDBySlot)) {
                    if (this.fmLog != null) {
                        this.fmLog.warn(this.logTag, String.valueOf(str) + "选择AID失败");
                    }
                    throw new BusinessException(String.valueOf(str) + "选择AID失败", BusinessException.ErrorMessage.local_apdu_reponse_invalid);
                }
                byte[] bArr = new byte[7];
                int i2 = 1;
                bArr[1] = ScriptToolsConst.TagName.CommandMultiple;
                bArr[4] = 2;
                bArr[5] = -35;
                bArr[6] = -15;
                if (!FM_Bytes.isEnd9000(this.apduHandler.transceive(bArr))) {
                    if (this.fmLog != null) {
                        this.fmLog.warn(this.logTag, String.valueOf(str) + "选择DDF1失败");
                    }
                    throw new BusinessException(String.valueOf(str) + "选择DDF1失败", BusinessException.ErrorMessage.local_apdu_reponse_invalid);
                }
                byte[] transceive = this.apduHandler.transceive(new byte[]{Byte.MIN_VALUE, Constants.TagName.ORDER_TRADE_STATUSES, -1, 3, 25});
                if (FM_Bytes.isEnd9000(transceive)) {
                    if (transceive.length >= 25) {
                        STTicketInfo sTTicketInfo = new STTicketInfo();
                        sTTicketInfo.setAppNo(FM_Bytes.copyOfRange(transceive, 0, 10));
                        EnumTicketType enumTicketType = EnumTicketType.NONE;
                        if (transceive[16] == 72) {
                            enumTicketType = EnumTicketType.TICKET_TYPE_THRESS_DAYS;
                        } else if (transceive[16] == 24) {
                            enumTicketType = EnumTicketType.TICKET_TYPE_ONE_DAY;
                        }
                        sTTicketInfo.setTicketType(enumTicketType);
                        sTTicketInfo.setValidationDate(FM_Bytes.bytesToHexString(FM_Bytes.copyOfRange(transceive, 10, 14)));
                        sTTicketInfo.setStartUsageTime(FM_Bytes.bytesToHexString(FM_Bytes.copyOfRange(transceive, 17, 24)));
                        byte b = (byte) (transceive[24] & Constants.TagName.STATION_ENAME);
                        if (b == 64) {
                            i2 = 0;
                        } else if (b != Byte.MIN_VALUE) {
                            i2 = -1;
                        }
                        sTTicketInfo.setInOutFlag(i2);
                        this.apduHandler.close();
                        return sTTicketInfo;
                    }
                }
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf(str) + "获取基本信息指令失败");
                }
                throw new BusinessException(String.valueOf(str) + "获取基本信息指令失败", BusinessException.ErrorMessage.local_apdu_reponse_invalid);
            } catch (FMScriptHandleException e) {
                e.printStackTrace();
                throw new BusinessException(String.valueOf(str) + "Apdu处理器执行失败", BusinessException.ErrorMessage.local_business_execute_fail);
            } catch (Throwable th) {
                this.apduHandler.close();
                throw th;
            }
        }
    }

    public List<STTicketRecord> getTicketRecordsBySlot(int i) throws BusinessException {
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "shentong getTicketRecordsBySlot...");
        }
        byte[] aIDBySlot = getAIDBySlot(i);
        String str = "获取申通票交易记录（卡槽:" + i + "）时，";
        if (this.apduHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf(str) + "Apdu处理器为空");
            }
            throw new BusinessException(String.valueOf(str) + "Apdu处理器为空", BusinessException.ErrorMessage.local_business_apdu_handler_null);
        } else if (this.apduHandler.isConnect()) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf(str) + "Apdu处理器正在忙");
            }
            throw new BusinessException(String.valueOf(str) + "Apdu处理器正在忙", BusinessException.ErrorMessage.local_business_apdu_handler_busying);
        } else {
            try {
                if (!this.apduHandler.open(aIDBySlot)) {
                    if (this.fmLog != null) {
                        this.fmLog.warn(this.logTag, String.valueOf(str) + "选择AID失败");
                    }
                    throw new BusinessException(String.valueOf(str) + "选择AID失败", BusinessException.ErrorMessage.local_apdu_reponse_invalid);
                }
                byte[] bArr = new byte[7];
                bArr[1] = ScriptToolsConst.TagName.CommandMultiple;
                bArr[4] = 2;
                bArr[5] = -35;
                bArr[6] = -15;
                if (!FM_Bytes.isEnd9000(this.apduHandler.transceive(bArr))) {
                    if (this.fmLog != null) {
                        this.fmLog.warn(this.logTag, String.valueOf(str) + "选择DDF1失败");
                    }
                    throw new BusinessException(String.valueOf(str) + "选择DDF1失败", BusinessException.ErrorMessage.local_apdu_reponse_invalid);
                }
                byte[] bArr2 = new byte[5];
                bArr2[1] = Constants.TagName.APP_TYPE;
                bArr2[3] = Constants.TagName.USER_PLATFORM_TYPE;
                ArrayList<STTicketRecord> arrayList = new ArrayList<>();
                for (int i2 = 1; i2 <= 10; i2++) {
                    bArr2[2] = (byte) (i2 & 255);
                    byte[] transceive = this.apduHandler.transceive(bArr2);
                    if (FM_Bytes.isEnd9000(transceive) && transceive.length >= 23) {
                        STTicketRecord sTTicketRecord = new STTicketRecord();
                        sTTicketRecord.setDate(FM_Bytes.bytesToHexString(FM_Bytes.copyOfRange(transceive, 16, 20)));
                        sTTicketRecord.setTime(FM_Bytes.bytesToHexString(FM_Bytes.copyOfRange(transceive, 20, 23)));
                        sTTicketRecord.setInOutFlag(-1);
                        arrayList.add(sTTicketRecord);
                    } else if (!FM_Bytes.isEnd(transceive, new byte[]{Constants.TagName.PAY_ORDER_ID, -125})) {
                        if (this.fmLog != null) {
                            this.fmLog.warn(this.logTag, String.valueOf(str) + "获取交易记录" + i2 + "#失败");
                        }
                        throw new BusinessException(String.valueOf(str) + "获取交易记录" + i2 + "#失败", BusinessException.ErrorMessage.local_apdu_reponse_invalid);
                    }
                }
                ApduHandler apduHandler2 = this.apduHandler;
                byte[] bArr3 = new byte[5];
                bArr3[1] = Constants.TagName.APP_TYPE;
                bArr3[2] = 1;
                bArr3[3] = -52;
                byte[] transceive2 = apduHandler2.transceive(bArr3);
                if (FM_Bytes.isEnd9000(transceive2)) {
                    if (transceive2.length >= 26) {
                        String bytesToHexString = FM_Bytes.bytesToHexString(FM_Bytes.copyOfRange(transceive2, 4, 11));
                        String stationName = getStationName(FM_Bytes.bytesToHexString(FM_Bytes.copyOfRange(transceive2, 11, 15)).substring(0, 4));
                        String bytesToHexString2 = FM_Bytes.bytesToHexString(FM_Bytes.copyOfRange(transceive2, 15, 22));
                        String stationName2 = getStationName(FM_Bytes.bytesToHexString(FM_Bytes.copyOfRange(transceive2, 22, 26)).substring(0, 4));
                        for (STTicketRecord sTTicketRecord2 : arrayList) {
                            if (bytesToHexString.equals(String.valueOf(sTTicketRecord2.getDate()) + sTTicketRecord2.getTime())) {
                                sTTicketRecord2.setInOutFlag(0);
                                sTTicketRecord2.setStationName(stationName);
                            }
                            if (bytesToHexString2.equals(String.valueOf(sTTicketRecord2.getDate()) + sTTicketRecord2.getTime())) {
                                sTTicketRecord2.setInOutFlag(1);
                                sTTicketRecord2.setStationName(stationName2);
                            }
                        }
                        this.apduHandler.close();
                        return arrayList;
                    }
                }
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf(str) + "获取进出站信息失败");
                }
                throw new BusinessException(String.valueOf(str) + "获取进出站信息失败", BusinessException.ErrorMessage.local_apdu_reponse_invalid);
            } catch (FMScriptHandleException e) {
                e.printStackTrace();
                throw new BusinessException(String.valueOf(str) + "Apdu处理器执行失败", BusinessException.ErrorMessage.local_business_execute_fail);
            } catch (Throwable th) {
                this.apduHandler.close();
                throw th;
            }
        }
    }

    private String getStationName(String str) {
        if (this.dbhelper != null) {
            return this.dbhelper.getStation(str);
        }
        this.fmLog.info(this.logTag, "shentong local db helper no setted");
        return null;
    }

    public int getMaxSlotNumber() throws BusinessException {
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "shentong getMaxSlotNumber...");
        }
        if (this.apduHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("获取申通票支持卡槽个数") + "Apdu处理器为空");
            }
            throw new BusinessException(String.valueOf("获取申通票支持卡槽个数") + "Apdu处理器为空", BusinessException.ErrorMessage.local_business_apdu_handler_null);
        } else if (this.apduHandler.isConnect()) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.warn(str2, String.valueOf("获取申通票支持卡槽个数") + "Apdu处理器正在忙");
            }
            throw new BusinessException(String.valueOf("获取申通票支持卡槽个数") + "Apdu处理器正在忙", BusinessException.ErrorMessage.local_business_apdu_handler_busying);
        } else {
            try {
                if (this.apduHandler.open(pfAid)) {
                    ApduHandler apduHandler2 = this.apduHandler;
                    byte[] bArr = new byte[7];
                    bArr[0] = Byte.MIN_VALUE;
                    bArr[1] = Constants.TagName.PATCH_DATA;
                    bArr[3] = -88;
                    bArr[4] = 2;
                    bArr[5] = 1;
                    bArr[6] = Framer.ENTER_FRAME_PREFIX;
                    byte[] transceive = apduHandler2.transceive(bArr);
                    if (FM_Bytes.isEnd9000(transceive) && transceive.length >= 64 && transceive[19] == -112) {
                        byte b = transceive[63] & 255;
                        FMLog fMLog3 = this.fmLog;
                        String str3 = this.logTag;
                        fMLog3.info(str3, "支持最大卡槽:" + b);
                        if (this.apduHandler.open(getAIDBySlot(b))) {
                            this.apduHandler.close();
                            return b;
                        }
                    }
                }
                this.apduHandler.close();
                return 0;
            } catch (FMScriptHandleException e) {
                e.printStackTrace();
                throw new BusinessException(String.valueOf("获取申通票支持卡槽个数") + "Apdu处理器执行失败", BusinessException.ErrorMessage.local_business_execute_fail);
            } catch (Throwable th) {
                this.apduHandler.close();
                throw th;
            }
        }
    }

    static {
        byte[] bArr = new byte[16];
        bArr[0] = ScriptToolsConst.TagName.CommandSingle;
        bArr[3] = 3;
        bArr[4] = 51;
        bArr[5] = 1;
        bArr[6] = 1;
        bArr[7] = 1;
        bArr[8] = -1;
        bArr[9] = 32;
        bArr[11] = -1;
        bArr[12] = -1;
        bArr[13] = -1;
        bArr[14] = -1;
        pfAid = bArr;
        byte[] bArr2 = new byte[9];
        bArr2[0] = ScriptToolsConst.TagName.CommandSingle;
        bArr2[3] = 1;
        bArr2[4] = Constants.TagName.TERMINAL_BACK_MAIN_ID;
        bArr2[5] = Constants.TagName.TERMINAL_BACK_INFO_TYPE;
        bArr2[6] = Constants.TagName.TERMINAL_BACK_QUESTION_FLAG;
        bArr2[7] = 83;
        cis = bArr2;
    }

    private byte[] getAIDBySlot(int i) throws BusinessException {
        if (i >= 1) {
            byte[] bArr = (byte[]) aid.clone();
            bArr[aid.length - 1] = (byte) (i + 64);
            return bArr;
        }
        throw new BusinessException("卡槽参数输入错误,只支持大于等于1", BusinessException.ErrorMessage.local_business_para_error);
    }
}
