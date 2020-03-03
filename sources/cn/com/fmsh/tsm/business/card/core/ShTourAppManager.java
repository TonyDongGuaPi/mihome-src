package cn.com.fmsh.tsm.business.card.core;

import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.script.exception.FMScriptHandleException;
import cn.com.fmsh.tsm.business.bean.CardAppRecord;
import cn.com.fmsh.tsm.business.card.CardTools;
import cn.com.fmsh.tsm.business.card.base.CardManager;
import cn.com.fmsh.tsm.business.constants.Constants;
import cn.com.fmsh.tsm.business.enums.EnumTradeType;
import cn.com.fmsh.tsm.business.exception.BusinessException;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.miui.tsmclient.util.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ShTourAppManager implements CardManager {
    private final int BYTE_COUNT_18;
    private final byte LOCK;
    private final int MAX_LOG;
    private final byte[] aid;
    private ApduHandler apduHandler;
    FMLog fmLog = LogFactory.getInstance().getLog();
    private final String logTag = ShTourAppManager.class.getName();

    public ShTourAppManager() {
        byte[] bArr = new byte[9];
        bArr[0] = ScriptToolsConst.TagName.CommandSingle;
        bArr[4] = 3;
        bArr[5] = Constants.TagName.ACTIVITY_TOTAL;
        bArr[6] = Constants.TagName.PRODUCT_INFO;
        bArr[7] = 7;
        bArr[8] = 1;
        this.aid = bArr;
        this.MAX_LOG = 10;
        this.BYTE_COUNT_18 = 23;
        this.LOCK = 0;
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
            this.fmLog.info(this.logTag, "获取上海旅游卡余额 ...");
        }
        if (this.apduHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取上海旅游卡余额，Apdu处理器为空");
            }
            throw new BusinessException("获取上海旅游卡余额时，Apdu处理器为空", BusinessException.ErrorMessage.local_business_apdu_handler_null);
        }
        byte[] bArr = new byte[7];
        bArr[1] = ScriptToolsConst.TagName.CommandMultiple;
        bArr[4] = 2;
        bArr[5] = Constants.TagName.CARD_APP_ACTIVATION_STATUS;
        bArr[6] = 1;
        transceive(bArr);
        byte[] bArr2 = new byte[17];
        bArr2[0] = Byte.MIN_VALUE;
        bArr2[1] = Constants.TagName.ORDER_BRIEF_INFO_LIST;
        bArr2[2] = 3;
        bArr2[3] = 2;
        bArr2[4] = 11;
        bArr2[5] = 1;
        bArr2[16] = 15;
        byte[] transceive = transceive(bArr2);
        if (transceive.length < 9) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取上海旅游卡余额时，APDU响应的数据无效");
            }
            throw new BusinessException("获取上海旅游卡余额时，响应数据无效", BusinessException.ErrorMessage.local_get_app_info_fail);
        }
        return FM_Bytes.bytesToInt(Arrays.copyOf(transceive, 4)) - FM_Bytes.bytesToInt(new byte[]{transceive[6], transceive[7], transceive[8]});
    }

    public List<CardAppRecord> readAppRecords() throws BusinessException {
        ArrayList arrayList = new ArrayList();
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "获取上海旅游卡交易记录 ...");
        }
        if (this.apduHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("获取上海旅游卡交易记录") + "时，Apdu处理器为空");
            }
            throw new BusinessException(String.valueOf("获取上海旅游卡交易记录") + "时，Apdu处理器为空", BusinessException.ErrorMessage.local_business_apdu_handler_null);
        }
        byte[] bArr = new byte[7];
        bArr[1] = ScriptToolsConst.TagName.CommandMultiple;
        bArr[4] = 2;
        bArr[5] = Constants.TagName.CARD_APP_ACTIVATION_STATUS;
        bArr[6] = 1;
        transceive(bArr);
        HashMap hashMap = new HashMap();
        byte[] bArr2 = new byte[7];
        bArr2[1] = ScriptToolsConst.TagName.CommandMultiple;
        bArr2[4] = 2;
        bArr2[6] = 7;
        transceive(bArr2);
        byte[] bArr3 = new byte[5];
        for (int i = 1; i <= 10; i++) {
            byte[] bArr4 = new byte[5];
            bArr4[1] = Constants.TagName.APP_TYPE;
            bArr4[2] = (byte) i;
            bArr4[3] = 4;
            byte[] transceive = transceive(bArr4);
            if (Arrays.equals(new byte[]{Constants.TagName.PAY_ORDER_ID, -125}, Arrays.copyOfRange(transceive, transceive.length - 2, transceive.length))) {
                break;
            }
            if (transceive.length >= 16) {
                String dateTime4File07 = getDateTime4File07(Arrays.copyOfRange(transceive, 10, 15));
                EnumTradeType enumTradeType = get07TradeType(transceive[0]);
                if (!(dateTime4File07 == null || enumTradeType == null)) {
                    hashMap.put(dateTime4File07, enumTradeType);
                }
            }
        }
        byte[] bArr5 = new byte[7];
        bArr5[1] = ScriptToolsConst.TagName.CommandMultiple;
        bArr5[4] = 2;
        bArr5[6] = 24;
        transceive(bArr5);
        for (int i2 = 1; i2 <= 10; i2++) {
            byte[] bArr6 = new byte[5];
            bArr6[1] = Constants.TagName.APP_TYPE;
            bArr6[2] = (byte) i2;
            bArr6[3] = 4;
            byte[] transceive2 = transceive(bArr6);
            if (Arrays.equals(new byte[]{Constants.TagName.PAY_ORDER_ID, -125}, Arrays.copyOfRange(transceive2, transceive2.length - 2, transceive2.length))) {
                break;
            }
            if (transceive2.length >= 23) {
                arrayList.add(getAppRecord4bytes(transceive2, hashMap));
            }
        }
        return arrayList;
    }

    public CardAppRecord getAppRecord4bytes(byte[] bArr, Map<String, EnumTradeType> map) {
        CardAppRecord cardAppRecord = new CardAppRecord();
        cardAppRecord.setTradeNo(FM_Bytes.bytesToInt(new byte[]{bArr[0], bArr[1]}));
        byte[] bArr2 = {bArr[16], bArr[17], bArr[18], bArr[19]};
        cardAppRecord.setTradeDate(FM_Bytes.bytesToHexString(bArr2));
        byte[] bArr3 = {bArr[20], bArr[21], bArr[22]};
        cardAppRecord.setTradeTime(FM_Bytes.bytesToHexString(bArr3));
        EnumTradeType enumTradeType = map.get(String.valueOf(FM_Bytes.bytesToHexString(bArr2)) + FM_Bytes.bytesToHexString(bArr3));
        cardAppRecord.setAmount(Integer.parseInt(FM_Bytes.bytesToHexString(new byte[]{bArr[5], bArr[6], bArr[7], bArr[8]}), 16));
        cardAppRecord.setBalance(FM_Bytes.bytesToInt(new byte[]{bArr[2], bArr[3], bArr[4]}));
        cardAppRecord.setOriTradeType(bArr[12]);
        cardAppRecord.setOriTradeType(bArr[9]);
        if (enumTradeType != null) {
            cardAppRecord.setTradeType(enumTradeType);
        } else {
            cardAppRecord.setTradeType(get18TradeType(bArr[12], bArr[9]));
        }
        cardAppRecord.setTradeDevice(FM_Bytes.bytesToHexString(new byte[]{bArr[10], bArr[11], bArr[12], bArr[13], bArr[14], bArr[15]}));
        return cardAppRecord;
    }

    public String getFaceID() throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "获取上海旅游卡卡面号 ...");
        }
        if (this.apduHandler != null) {
            return CardTools.getFaceID4UID(Arrays.copyOfRange(getAppNo(), 4, 8));
        }
        if (this.fmLog != null) {
            FMLog fMLog = this.fmLog;
            String str = this.logTag;
            fMLog.warn(str, String.valueOf("获取上海旅游卡卡面号") + "时，Apdu处理器为空");
        }
        throw new BusinessException(String.valueOf("获取上海旅游卡卡面号") + "时，Apdu处理器为空", BusinessException.ErrorMessage.local_business_apdu_handler_null);
    }

    public byte[] getAppNo() throws BusinessException {
        byte[] bArr = new byte[8];
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "获取上海旅游卡应用序列号 ...");
        }
        if (this.apduHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("获取上海旅游卡应用序列号 ") + "，Apdu处理器为空");
            }
            throw new BusinessException(String.valueOf("获取上海旅游卡应用序列号 ") + "，Apdu处理器为空", BusinessException.ErrorMessage.local_business_apdu_handler_null);
        }
        byte[] bArr2 = new byte[7];
        bArr2[1] = ScriptToolsConst.TagName.CommandMultiple;
        bArr2[4] = 2;
        bArr2[5] = Constants.TagName.CARD_APP_ACTIVATION_STATUS;
        bArr2[6] = 1;
        byte[] transceive = transceive(bArr2);
        if (!FM_Bytes.isEnd9000(transceive)) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.warn(str2, String.valueOf("获取上海旅游卡应用序列号 ") + "，选择ADF执行异常:" + FM_Bytes.bytesToHexString(transceive));
            }
            throw new BusinessException(String.valueOf("获取上海旅游卡应用序列号 ") + "，选择ADF执行异常", BusinessException.ErrorMessage.local_message_apdu_execute_exception);
        } else if (transceive.length >= 42) {
            System.arraycopy(transceive, 34, bArr, 0, bArr.length);
            return bArr;
        } else {
            byte[] bArr3 = new byte[5];
            bArr3[1] = -80;
            bArr3[2] = Constants.TagName.PREDEPOSIT_TYPE;
            byte[] transceive2 = transceive(bArr3);
            if (!FM_Bytes.isEnd9000(transceive2)) {
                if (this.fmLog != null) {
                    FMLog fMLog3 = this.fmLog;
                    String str3 = this.logTag;
                    fMLog3.warn(str3, String.valueOf("获取上海旅游卡应用序列号 ") + "，选择15文件响应异常:" + FM_Bytes.bytesToHexString(transceive2));
                }
                throw new BusinessException(String.valueOf("获取上海旅游卡应用序列号 ") + "，选择15文件响应异常", BusinessException.ErrorMessage.local_message_apdu_execute_exception);
            } else if (transceive2.length < 20) {
                return null;
            } else {
                System.arraycopy(transceive2, 12, bArr, 0, bArr.length);
                return bArr;
            }
        }
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

    private EnumTradeType get18TradeType(byte b, byte b2) {
        EnumTradeType enumTradeType;
        switch (b) {
            case -125:
                enumTradeType = EnumTradeType.recharge;
                break;
            case 1:
                enumTradeType = EnumTradeType.subwayConsumption;
                break;
            case 5:
                enumTradeType = EnumTradeType.subwayConsumption;
                break;
            case 9:
                enumTradeType = EnumTradeType.bus;
                break;
            case 17:
                enumTradeType = EnumTradeType.subwayConsumption;
                break;
            case 20:
                enumTradeType = EnumTradeType.subwayUpdate;
                break;
            case 32:
                enumTradeType = EnumTradeType.maglev;
                break;
            case 34:
                enumTradeType = EnumTradeType.recharge;
                break;
            case 49:
                enumTradeType = EnumTradeType.ferry;
                break;
            case 65:
                enumTradeType = EnumTradeType.taxi;
                break;
            case 81:
                enumTradeType = EnumTradeType.expressway;
                break;
            case 82:
                enumTradeType = EnumTradeType.park;
                break;
            case 99:
                enumTradeType = EnumTradeType.gasStation;
                break;
            default:
                enumTradeType = null;
                break;
        }
        if (enumTradeType != null) {
            return enumTradeType;
        }
        if (b2 != 2) {
            return EnumTradeType.elseTrade;
        }
        return EnumTradeType.recharge;
    }

    private EnumTradeType get07TradeType(byte b) {
        switch (b) {
            case -125:
                return EnumTradeType.recharge;
            case -107:
                return EnumTradeType.onlineRecharge;
            case -103:
                return EnumTradeType.onlineRecharge;
            case 1:
                return EnumTradeType.bus;
            case 3:
                return EnumTradeType.onlineRecharge;
            case 5:
                return EnumTradeType.bus;
            case 6:
                return EnumTradeType.subwayConsumption;
            case 7:
                return EnumTradeType.subwayConsumption;
            case 9:
                return EnumTradeType.bus;
            case 17:
                return EnumTradeType.subwayConsumption;
            case 20:
                return EnumTradeType.subwayUpdate;
            case 32:
                return EnumTradeType.maglev;
            case 34:
                return EnumTradeType.recharge;
            case 49:
                return EnumTradeType.ferry;
            case 52:
                return EnumTradeType.subwayConsumption;
            case 65:
                return EnumTradeType.taxi;
            case 66:
                return EnumTradeType.taxi;
            case 81:
                return EnumTradeType.expressway;
            case 82:
                return EnumTradeType.park;
            case 99:
                return EnumTradeType.gasStation;
            case 104:
                return EnumTradeType.onlineConsumption;
            default:
                return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x0132  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public cn.com.fmsh.tsm.business.enums.EnumCardAppStatus getStatus() throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r17 = this;
            r1 = r17
            cn.com.fmsh.tsm.business.enums.EnumCardAppStatus r2 = cn.com.fmsh.tsm.business.enums.EnumCardAppStatus.STATUS_INSTALL
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x0012
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r3 = r1.logTag
            java.lang.String r4 = "获取上海旅游卡当前的状态..."
            r0.info(r3, r4)
        L_0x0012:
            java.lang.String r3 = "获取上海旅游卡当前的状态"
            cn.com.fmsh.script.ApduHandler r0 = r1.apduHandler
            if (r0 != 0) goto L_0x0052
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x0037
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r2 = r1.logTag
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = java.lang.String.valueOf(r3)
            r4.<init>(r5)
            java.lang.String r5 = "时，Apdu处理器为空"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r0.warn(r2, r4)
        L_0x0037:
            cn.com.fmsh.tsm.business.exception.BusinessException r0 = new cn.com.fmsh.tsm.business.exception.BusinessException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r2.<init>(r3)
            java.lang.String r3 = "时，Apdu处理器为空"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_null
            r0.<init>(r2, r3)
            throw r0
        L_0x0052:
            r4 = 5
            byte[] r0 = new byte[r4]
            r5 = -80
            r6 = 1
            r0[r6] = r5
            r5 = -107(0xffffffffffffff95, float:NaN)
            r7 = 2
            r0[r7] = r5
            cn.com.fmsh.script.ApduHandler r5 = r1.apduHandler     // Catch:{ FMScriptHandleException -> 0x01d2 }
            byte[] r5 = r5.transceive(r0)     // Catch:{ FMScriptHandleException -> 0x01d2 }
            boolean r0 = cn.com.fmsh.util.FM_Bytes.isEnd9000(r5)
            if (r0 != 0) goto L_0x006c
            return r2
        L_0x006c:
            r0 = 32
            byte[] r0 = new byte[r0]
            r8 = 30
            r9 = -112(0xffffffffffffff90, float:NaN)
            r0[r8] = r9
            boolean r0 = java.util.Arrays.equals(r0, r5)
            if (r0 != 0) goto L_0x01d1
            cn.com.fmsh.tsm.business.enums.EnumCardAppStatus r2 = cn.com.fmsh.tsm.business.enums.EnumCardAppStatus.STATUS_PERSONALIZED
            java.util.Random r0 = new java.util.Random
            r0.<init>()
            r8 = 8
            byte[] r9 = new byte[r8]
            r0.nextBytes(r9)
            byte[] r0 = new byte[r4]
            r10 = 10
            r0[r6] = r10
            r10 = -125(0xffffffffffffff83, float:NaN)
            r0[r7] = r10
            r10 = 3
            r11 = 4
            r0[r10] = r11
            r0[r11] = r8
            byte[] r0 = cn.com.fmsh.util.FM_Bytes.join(r0, r9)
            r9 = 0
            cn.com.fmsh.script.ApduHandler r12 = r1.apduHandler     // Catch:{ FMScriptHandleException -> 0x00a8 }
            byte[] r0 = r12.transceive(r0)     // Catch:{ FMScriptHandleException -> 0x00a8 }
            r5 = r0
            r0 = 0
            goto L_0x00cf
        L_0x00a8:
            r0 = move-exception
            cn.com.fmsh.util.log.FMLog r12 = r1.fmLog
            if (r12 == 0) goto L_0x00ce
            cn.com.fmsh.util.log.FMLog r12 = r1.fmLog
            java.lang.String r13 = r1.logTag
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            java.lang.String r15 = java.lang.String.valueOf(r3)
            r14.<init>(r15)
            java.lang.String r15 = "时，异常:"
            r14.append(r15)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r14.append(r0)
            java.lang.String r0 = r14.toString()
            r12.error(r13, r0)
        L_0x00ce:
            r0 = 1
        L_0x00cf:
            if (r0 == 0) goto L_0x00d3
        L_0x00d1:
            r0 = 1
            goto L_0x0130
        L_0x00d3:
            boolean r0 = cn.com.fmsh.util.FM_Bytes.isEnd9000(r5)
            if (r0 != 0) goto L_0x00da
            goto L_0x00d1
        L_0x00da:
            int r0 = r5.length
            if (r0 >= r4) goto L_0x00de
            goto L_0x00d1
        L_0x00de:
            byte[] r0 = java.util.Arrays.copyOfRange(r5, r6, r4)
            java.lang.String r0 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r0)
            long r12 = java.lang.System.currentTimeMillis()
            java.text.SimpleDateFormat r14 = new java.text.SimpleDateFormat
            java.lang.String r15 = "yyyyMMdd"
            r14.<init>(r15)
            java.util.Date r14 = r14.parse(r0)     // Catch:{ ParseException -> 0x00f9 }
            r16 = r14
            goto L_0x011c
        L_0x00f9:
            cn.com.fmsh.util.log.FMLog r14 = r1.fmLog
            if (r14 == 0) goto L_0x011a
            cn.com.fmsh.util.log.FMLog r14 = r1.fmLog
            java.lang.String r15 = r1.logTag
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r4 = java.lang.String.valueOf(r3)
            r8.<init>(r4)
            java.lang.String r4 = "时，获取的日期格式异常:"
            r8.append(r4)
            r8.append(r0)
            java.lang.String r0 = r8.toString()
            r14.warn(r15, r0)
        L_0x011a:
            r16 = 0
        L_0x011c:
            if (r16 != 0) goto L_0x011f
            goto L_0x00d1
        L_0x011f:
            byte r0 = r5[r9]
            if (r0 == 0) goto L_0x012f
            long r4 = r16.getTime()
            int r0 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r0 >= 0) goto L_0x012c
            goto L_0x012f
        L_0x012c:
            cn.com.fmsh.tsm.business.enums.EnumCardAppStatus r0 = cn.com.fmsh.tsm.business.enums.EnumCardAppStatus.STATUS_ACTIVATE
            return r0
        L_0x012f:
            r0 = 0
        L_0x0130:
            if (r0 == 0) goto L_0x01d0
            r0 = 16
            byte[] r0 = new byte[r0]
            r4 = -128(0xffffffffffffff80, float:NaN)
            r0[r9] = r4
            r4 = 80
            r0[r6] = r4
            r0[r10] = r7
            r4 = 11
            r0[r11] = r4
            r4 = 5
            r0[r4] = r6
            r4 = 7
            r5 = 8
            r0[r5] = r4
            r4 = 9
            r5 = -48
            r0[r4] = r5
            cn.com.fmsh.script.ApduHandler r4 = r1.apduHandler     // Catch:{ FMScriptHandleException -> 0x01a9 }
            byte[] r0 = r4.transceive(r0)     // Catch:{ FMScriptHandleException -> 0x01a9 }
            boolean r0 = cn.com.fmsh.util.FM_Bytes.isEnd9000(r0)
            if (r0 != 0) goto L_0x015f
            return r2
        L_0x015f:
            java.lang.String r0 = "805200000B2014072815153492F981B2"
            byte[] r0 = cn.com.fmsh.util.FM_Bytes.hexStringToBytes(r0)
            cn.com.fmsh.script.ApduHandler r4 = r1.apduHandler     // Catch:{ FMScriptHandleException -> 0x0182 }
            byte[] r0 = r4.transceive(r0)     // Catch:{ FMScriptHandleException -> 0x0182 }
            byte[] r3 = new byte[r7]
            r3 = {-109, 2} // fill-array
            boolean r3 = cn.com.fmsh.util.FM_Bytes.isEnd(r0, r3)
            if (r3 == 0) goto L_0x0179
            cn.com.fmsh.tsm.business.enums.EnumCardAppStatus r0 = cn.com.fmsh.tsm.business.enums.EnumCardAppStatus.STATUS_ACTIVATE
            return r0
        L_0x0179:
            boolean r0 = cn.com.fmsh.util.FM_Bytes.isEnd9000(r0)
            if (r0 == 0) goto L_0x01d0
            cn.com.fmsh.tsm.business.enums.EnumCardAppStatus r0 = cn.com.fmsh.tsm.business.enums.EnumCardAppStatus.STATUS_ACTIVATE
            return r0
        L_0x0182:
            r0 = move-exception
            cn.com.fmsh.util.log.FMLog r4 = r1.fmLog
            if (r4 == 0) goto L_0x01a8
            cn.com.fmsh.util.log.FMLog r4 = r1.fmLog
            java.lang.String r5 = r1.logTag
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r6.<init>(r3)
            java.lang.String r3 = "时，圈存初始化失败:"
            r6.append(r3)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            r4.error(r5, r0)
        L_0x01a8:
            return r2
        L_0x01a9:
            r0 = move-exception
            cn.com.fmsh.util.log.FMLog r4 = r1.fmLog
            if (r4 == 0) goto L_0x01cf
            cn.com.fmsh.util.log.FMLog r4 = r1.fmLog
            java.lang.String r5 = r1.logTag
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r6.<init>(r3)
            java.lang.String r3 = "时，圈存初始化失败:"
            r6.append(r3)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            r4.error(r5, r0)
        L_0x01cf:
            return r2
        L_0x01d0:
            return r2
        L_0x01d1:
            return r2
        L_0x01d2:
            r0 = move-exception
            cn.com.fmsh.util.log.FMLog r4 = r1.fmLog
            if (r4 == 0) goto L_0x01f8
            cn.com.fmsh.util.log.FMLog r4 = r1.fmLog
            java.lang.String r5 = r1.logTag
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r3 = java.lang.String.valueOf(r3)
            r6.<init>(r3)
            java.lang.String r3 = "时，读取0015文件失败:"
            r6.append(r3)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            r4.error(r5, r0)
        L_0x01f8:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.card.core.ShTourAppManager.getStatus():cn.com.fmsh.tsm.business.enums.EnumCardAppStatus");
    }

    public String getDateTime4File07(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        int bytesToInt = FM_Bytes.bytesToInt(new byte[]{bArr[0], bArr[1]}) >> 4;
        stringBuffer.append(String.valueOf(bytesToInt));
        System.out.println("yearValue:" + bytesToInt);
        String valueOf = String.valueOf(bArr[1] & 15);
        if (valueOf.length() == 1) {
            valueOf = "0" + valueOf;
        }
        stringBuffer.append(valueOf);
        String valueOf2 = String.valueOf((bArr[2] & 248) >> 3);
        if (valueOf2.length() == 1) {
            valueOf2 = "0" + valueOf2;
        }
        stringBuffer.append(valueOf2);
        String valueOf3 = String.valueOf(FM_Bytes.bytesToInt(new byte[]{(byte) (bArr[2] & 7), (byte) (bArr[3] & Constants.TagName.STATION_ENAME)}) >> 6);
        if (valueOf3.length() == 1) {
            valueOf3 = "0" + valueOf3;
        }
        stringBuffer.append(valueOf3);
        String valueOf4 = String.valueOf((byte) (bArr[3] & Constants.TagName.CARD_APP_ACTIVATION_STATUS));
        if (valueOf4.length() == 1) {
            valueOf4 = "0" + valueOf4;
        }
        stringBuffer.append(valueOf4);
        String valueOf5 = String.valueOf((bArr[4] & FileDownloadStatus.l) >> 2);
        if (valueOf5.length() == 1) {
            valueOf5 = "0" + valueOf5;
        }
        stringBuffer.append(valueOf5);
        return stringBuffer.toString();
    }

    public boolean isLock4Load() throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.apduHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取圈存功能锁定状态时，Apdu处理器为空");
            }
            throw new BusinessException("获取上海旅游卡余额时，Apdu处理器为空", BusinessException.ErrorMessage.local_business_apdu_handler_null);
        }
        byte[] bArr = new byte[8];
        new Random().nextBytes(bArr);
        byte[] bArr2 = new byte[5];
        bArr2[1] = 10;
        bArr2[2] = -125;
        bArr2[3] = 4;
        bArr2[4] = 8;
        byte[] transceive = transceive(FM_Bytes.join(bArr2, bArr));
        if (!FM_Bytes.isEnd9000(transceive)) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取圈存功能锁定状态时，APDU指令返回结果失败");
            }
            throw new BusinessException("获取圈存功能锁定状态时，APDU指令处理失败", BusinessException.ErrorMessage.local_business_execute_fail);
        } else if (transceive.length < 5) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取圈存功能锁定状态时，APDU指令处理失败");
            }
            throw new BusinessException("获取圈存功能锁定状态时，APDU指令处理失败", BusinessException.ErrorMessage.local_business_execute_fail);
        } else {
            String bytesToHexString = FM_Bytes.bytesToHexString(Arrays.copyOfRange(transceive, 1, 5));
            long currentTimeMillis = System.currentTimeMillis();
            try {
                Date parse = new SimpleDateFormat(StringUtils.SOURCE_DATE_FORMAT).parse(bytesToHexString);
                if (transceive[0] == 0 || parse.getTime() < currentTimeMillis) {
                    return true;
                }
                return false;
            } catch (ParseException unused) {
                if (this.fmLog != null) {
                    FMLog fMLog = this.fmLog;
                    String str = this.logTag;
                    fMLog.warn(str, "获取消费功能锁定状态时，获取的日期格式异常:" + bytesToHexString);
                }
                throw new BusinessException("获取圈存功能锁定状态时，APDU指令处理失败", BusinessException.ErrorMessage.local_business_execute_fail);
            }
        }
    }

    public boolean isLock4Consume() throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.apduHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取圈存功能锁定状态时，Apdu处理器为空");
            }
            throw new BusinessException("获取上海旅游卡余额时，Apdu处理器为空", BusinessException.ErrorMessage.local_business_apdu_handler_null);
        }
        byte[] bArr = new byte[8];
        new Random().nextBytes(bArr);
        byte[] bArr2 = new byte[5];
        bArr2[1] = 10;
        bArr2[2] = Constants.TagName.TERMINAL_BACK_INFO_TYPE;
        bArr2[3] = 4;
        bArr2[4] = 8;
        byte[] transceive = transceive(FM_Bytes.join(bArr2, bArr));
        if (!FM_Bytes.isEnd9000(transceive)) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取消费功能锁定状态时，APDU指令返回结果失败");
            }
            throw new BusinessException("获取圈存功能锁定状态时，APDU指令处理失败", BusinessException.ErrorMessage.local_business_execute_fail);
        } else if (transceive.length < 5) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取消费功能锁定状态时，APDU指令处理失败");
            }
            throw new BusinessException("获取圈存功能锁定状态时，APDU指令处理失败", BusinessException.ErrorMessage.local_business_execute_fail);
        } else {
            String bytesToHexString = FM_Bytes.bytesToHexString(Arrays.copyOfRange(transceive, 1, 5));
            long currentTimeMillis = System.currentTimeMillis();
            try {
                Date parse = new SimpleDateFormat(StringUtils.SOURCE_DATE_FORMAT).parse(bytesToHexString);
                if (transceive[0] == 0 || parse.getTime() < currentTimeMillis) {
                    return true;
                }
                return false;
            } catch (ParseException unused) {
                if (this.fmLog != null) {
                    FMLog fMLog = this.fmLog;
                    String str = this.logTag;
                    fMLog.warn(str, "获取消费功能锁定状态时，获取的日期格式异常:" + bytesToHexString);
                }
                throw new BusinessException("获取圈存功能锁定状态时，APDU指令处理失败", BusinessException.ErrorMessage.local_business_execute_fail);
            }
        }
    }

    public String getMOC() throws BusinessException {
        byte[] bArr = new byte[5];
        bArr[0] = Byte.MIN_VALUE;
        bArr[1] = -54;
        bArr[4] = 9;
        byte[] transceive = transceive(bArr);
        if (FM_Bytes.isEnd9000(transceive)) {
            return FM_Bytes.bytesToHexString(Arrays.copyOf(transceive, transceive.length - 2));
        }
        if (this.fmLog != null) {
            FMLog fMLog = this.fmLog;
            String str = this.logTag;
            fMLog.warn(str, "获取住建部认证码时，APDU指令返回结果失败，reponse：" + FM_Bytes.bytesToHexString(transceive));
        }
        throw new BusinessException("获取圈存功能锁定状态时，APDU指令处理失败", BusinessException.ErrorMessage.local_business_execute_fail);
    }

    public String getTime4Validity() throws BusinessException {
        byte[] bArr = new byte[7];
        bArr[1] = ScriptToolsConst.TagName.CommandMultiple;
        bArr[4] = 2;
        bArr[5] = Constants.TagName.CARD_APP_ACTIVATION_STATUS;
        bArr[6] = 1;
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
        } else if (transceive2.length < 29) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str3 = this.logTag;
                fMLog3.error(str3, "获取卡上应用有效期时，读取15文件失败：" + FM_Bytes.bytesToHexString(transceive2));
            }
            throw new BusinessException("获取卡上应用有效期时，读取15文件失败", BusinessException.ErrorMessage.local_business_execute_fail);
        } else {
            return FM_Bytes.bytesToHexString(new byte[]{transceive2[24], transceive2[25], transceive2[26], transceive2[27]});
        }
    }
}
