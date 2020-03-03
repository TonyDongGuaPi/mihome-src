package cn.com.fmsh.tsm.business.core;

import cn.com.fmsh.communication.message.IMessage;
import cn.com.fmsh.communication.message.IMessageHandler;
import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.tsm.business.CardAppTrade;
import cn.com.fmsh.tsm.business.LocalDataHandler;
import cn.com.fmsh.tsm.business.bean.Activity;
import cn.com.fmsh.tsm.business.bean.BusinessOrder;
import cn.com.fmsh.tsm.business.bean.CardBusinessStatus;
import cn.com.fmsh.tsm.business.bean.InvoiceToken;
import cn.com.fmsh.tsm.business.bean.LoginInfo;
import cn.com.fmsh.tsm.business.bean.MainOrder;
import cn.com.fmsh.tsm.business.bean.Notice;
import cn.com.fmsh.tsm.business.bean.PasswordPrompt;
import cn.com.fmsh.tsm.business.bean.PayOrder;
import cn.com.fmsh.tsm.business.bean.PreDepositInfo;
import cn.com.fmsh.tsm.business.bean.Product;
import cn.com.fmsh.tsm.business.bean.StationInfo;
import cn.com.fmsh.tsm.business.bean.TerminalBackInfo;
import cn.com.fmsh.tsm.business.bean.TicketOperateResult;
import cn.com.fmsh.tsm.business.bean.UserInfo;
import cn.com.fmsh.tsm.business.bean.VersionInfo;
import cn.com.fmsh.tsm.business.constants.Constants;
import cn.com.fmsh.tsm.business.enums.EnumAppActivationStatus;
import cn.com.fmsh.tsm.business.enums.EnumBusinessOrderStatus;
import cn.com.fmsh.tsm.business.enums.EnumBusinessOrderType;
import cn.com.fmsh.tsm.business.enums.EnumCardAppType;
import cn.com.fmsh.tsm.business.enums.EnumCardIoType;
import cn.com.fmsh.tsm.business.enums.EnumOrderStatus;
import cn.com.fmsh.tsm.business.enums.EnumOrderType;
import cn.com.fmsh.tsm.business.enums.EnumResultsSortType;
import cn.com.fmsh.tsm.business.exception.BusinessException;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import com.xiaomi.smarthome.auth.AuthCode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardAppTradeImpl implements CardAppTrade {
    private CardBusinessBasic cardBusinessBasic;
    FMLog fmLog = null;
    private final String logTag = CardAppTradeImpl.class.getName();
    private String userName;

    public CardAppTradeImpl(CardBusinessBasic cardBusinessBasic2) {
        this.cardBusinessBasic = cardBusinessBasic2;
        this.fmLog = LogFactory.getInstance().getLog();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0098, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00ea, code lost:
        if (r6.fmLog != null) goto L_0x00ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00ec, code lost:
        r6.fmLog.error(r6.logTag, "获取卡上信息时出现异常");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00f6, code lost:
        r6.cardBusinessBasic.throwExceptionAndClose("获取卡上信息时出现异常", cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0102, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0105, code lost:
        throw r7;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:50:0x00e8 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public cn.com.fmsh.tsm.business.bean.CardAppInfo getCardAppInfo(int r7, cn.com.fmsh.tsm.business.enums.EnumCardAppType r8) throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r6 = this;
            cn.com.fmsh.util.log.FMLog r0 = r6.fmLog
            if (r0 != 0) goto L_0x000e
            cn.com.fmsh.util.log.LogFactory r0 = cn.com.fmsh.util.log.LogFactory.getInstance()
            cn.com.fmsh.util.log.FMLog r0 = r0.getLog()
            r6.fmLog = r0
        L_0x000e:
            cn.com.fmsh.util.log.FMLog r0 = r6.fmLog
            if (r0 == 0) goto L_0x001b
            cn.com.fmsh.util.log.FMLog r0 = r6.fmLog
            java.lang.String r1 = r6.logTag
            java.lang.String r2 = "STPC card info..."
            r0.info(r1, r2)
        L_0x001b:
            cn.com.fmsh.tsm.business.bean.CardAppInfo r0 = new cn.com.fmsh.tsm.business.bean.CardAppInfo
            r0.<init>()
            java.lang.String r1 = "获取卡的余额和交易记录"
            r0.setTitle(r1)
            cn.com.fmsh.tsm.business.card.CardManagerFactory r1 = cn.com.fmsh.tsm.business.card.CardManagerFactory.instance()
            cn.com.fmsh.tsm.business.card.base.CardManager r8 = r1.getCardManager(r8)
            if (r8 != 0) goto L_0x003f
            cn.com.fmsh.util.log.FMLog r7 = r6.fmLog
            if (r7 == 0) goto L_0x003e
            cn.com.fmsh.util.log.FMLog r7 = r6.fmLog
            java.lang.String r8 = r6.logTag
            java.lang.String r1 = "获取卡的余额和交易记录时，获取指定卡类型的处理器失败"
            r7.warn(r8, r1)
        L_0x003e:
            return r0
        L_0x003f:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r1 = r6.cardBusinessBasic
            cn.com.fmsh.script.ApduHandler r1 = r1.getApduHandler()
            r2 = 0
            if (r1 != 0) goto L_0x0060
            cn.com.fmsh.util.log.FMLog r3 = r6.fmLog
            if (r3 == 0) goto L_0x0056
            cn.com.fmsh.util.log.FMLog r3 = r6.fmLog
            java.lang.String r4 = r6.logTag
            java.lang.String r5 = "获取卡的余额和交易记录，APDU处理器为空"
            r3.warn(r4, r5)
        L_0x0056:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r6.cardBusinessBasic
            java.lang.String r4 = "获取卡面号时，请先切换卡的访问方式(OMA/NFC)"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_null
            r3.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r2)
        L_0x0060:
            boolean r3 = r1.isConnect()
            if (r3 == 0) goto L_0x007f
            cn.com.fmsh.util.log.FMLog r3 = r6.fmLog
            if (r3 == 0) goto L_0x0074
            cn.com.fmsh.util.log.FMLog r3 = r6.fmLog
            java.lang.String r4 = r6.logTag
            java.lang.String r5 = "获取卡的余额和交易记录时，APDU处理器正忙"
            r3.warn(r4, r5)
        L_0x0074:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r6.cardBusinessBasic
            java.lang.String r4 = "获取卡的余额和交易记录时，APDU处理器正忙"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_busying
            r3.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r2)
            goto L_0x0082
        L_0x007f:
            r1.connect()
        L_0x0082:
            r8.setApduHandler(r1)
            r2 = r7 & 1
            if (r2 == 0) goto L_0x009a
            java.lang.String r2 = r8.getFaceID()     // Catch:{ BusinessException -> 0x00e8 }
            r0.setFaceId(r2)     // Catch:{ BusinessException -> 0x00e8 }
            byte[] r2 = r8.getAppNo()     // Catch:{ BusinessException -> 0x00e8 }
            r0.setCardAppNo(r2)     // Catch:{ BusinessException -> 0x00e8 }
            goto L_0x009a
        L_0x0098:
            r7 = move-exception
            goto L_0x0102
        L_0x009a:
            r2 = r7 & 2
            if (r2 == 0) goto L_0x00a9
            int r2 = r8.queryBalance()     // Catch:{ BusinessException -> 0x00e8 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ BusinessException -> 0x00e8 }
            r0.setBalance(r2)     // Catch:{ BusinessException -> 0x00e8 }
        L_0x00a9:
            r2 = r7 & 4
            if (r2 == 0) goto L_0x00c6
            java.util.List r2 = r8.readAppRecords()     // Catch:{ BusinessException -> 0x00e8 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ BusinessException -> 0x00e8 }
        L_0x00b5:
            boolean r3 = r2.hasNext()     // Catch:{ BusinessException -> 0x00e8 }
            if (r3 != 0) goto L_0x00bc
            goto L_0x00c6
        L_0x00bc:
            java.lang.Object r3 = r2.next()     // Catch:{ BusinessException -> 0x00e8 }
            cn.com.fmsh.tsm.business.bean.CardAppRecord r3 = (cn.com.fmsh.tsm.business.bean.CardAppRecord) r3     // Catch:{ BusinessException -> 0x00e8 }
            r0.addRecord((cn.com.fmsh.tsm.business.bean.CardAppRecord) r3)     // Catch:{ BusinessException -> 0x00e8 }
            goto L_0x00b5
        L_0x00c6:
            r2 = r7 & 8
            if (r2 == 0) goto L_0x00d1
            boolean r2 = r8.isLock4Consume()     // Catch:{ BusinessException -> 0x00e8 }
            r0.setAppClose(r2)     // Catch:{ BusinessException -> 0x00e8 }
        L_0x00d1:
            r2 = r7 & 16
            if (r2 == 0) goto L_0x00dc
            java.lang.String r2 = r8.getMOC()     // Catch:{ BusinessException -> 0x00e8 }
            r0.setMoc(r2)     // Catch:{ BusinessException -> 0x00e8 }
        L_0x00dc:
            r7 = r7 & 32
            if (r7 == 0) goto L_0x0106
            java.lang.String r7 = r8.getTime4Validity()     // Catch:{ BusinessException -> 0x00e8 }
            r0.setTime4Validity(r7)     // Catch:{ BusinessException -> 0x00e8 }
            goto L_0x0106
        L_0x00e8:
            cn.com.fmsh.util.log.FMLog r7 = r6.fmLog     // Catch:{ all -> 0x0098 }
            if (r7 == 0) goto L_0x00f6
            cn.com.fmsh.util.log.FMLog r7 = r6.fmLog     // Catch:{ all -> 0x0098 }
            java.lang.String r8 = r6.logTag     // Catch:{ all -> 0x0098 }
            java.lang.String r2 = "获取卡上信息时出现异常"
            r7.error(r8, r2)     // Catch:{ all -> 0x0098 }
        L_0x00f6:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r7 = r6.cardBusinessBasic     // Catch:{ all -> 0x0098 }
            java.lang.String r8 = "获取卡上信息时出现异常"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r2 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception     // Catch:{ all -> 0x0098 }
            r3 = 1
            r7.throwExceptionAndClose((java.lang.String) r8, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r2, (boolean) r3)     // Catch:{ all -> 0x0098 }
            goto L_0x0106
        L_0x0102:
            r1.close()
            throw r7
        L_0x0106:
            r1.close()
            cn.com.fmsh.util.log.FMLog r7 = r6.fmLog
            if (r7 == 0) goto L_0x0117
            cn.com.fmsh.util.log.FMLog r7 = r6.fmLog
            java.lang.String r8 = r6.logTag
            java.lang.String r1 = "获取卡的余额和交易记录完成"
            r7.info(r8, r1)
        L_0x0117:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardAppTradeImpl.getCardAppInfo(int, cn.com.fmsh.tsm.business.enums.EnumCardAppType):cn.com.fmsh.tsm.business.bean.CardAppInfo");
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:35|36|(1:38)|39|40|43) */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a0, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a4, code lost:
        if (r7.fmLog != null) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a6, code lost:
        r7.fmLog.error(r7.logTag, "获取卡面号时出现异常");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b0, code lost:
        r7.cardBusinessBasic.throwExceptionAndClose("获取卡面号时出现异常", cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00ba, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00bf, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00c2, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00a2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getFaceID(cn.com.fmsh.tsm.business.enums.EnumCardAppType r8) throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r7 = this;
            cn.com.fmsh.util.log.FMLog r0 = r7.fmLog
            if (r0 != 0) goto L_0x000e
            cn.com.fmsh.util.log.LogFactory r0 = cn.com.fmsh.util.log.LogFactory.getInstance()
            cn.com.fmsh.util.log.FMLog r0 = r0.getLog()
            r7.fmLog = r0
        L_0x000e:
            cn.com.fmsh.util.log.FMLog r0 = r7.fmLog
            if (r0 == 0) goto L_0x001c
            cn.com.fmsh.util.log.FMLog r0 = r7.fmLog
            java.lang.String r1 = r7.logTag
            java.lang.String r2 = "获取交通卡的卡面号..."
            r0.info(r1, r2)
        L_0x001c:
            cn.com.fmsh.tsm.business.card.CardManagerFactory r0 = cn.com.fmsh.tsm.business.card.CardManagerFactory.instance()
            cn.com.fmsh.tsm.business.card.base.CardManager r8 = r0.getCardManager(r8)
            r0 = 0
            if (r8 != 0) goto L_0x0036
            cn.com.fmsh.util.log.FMLog r8 = r7.fmLog
            if (r8 == 0) goto L_0x0035
            cn.com.fmsh.util.log.FMLog r8 = r7.fmLog
            java.lang.String r1 = r7.logTag
            java.lang.String r2 = "获取卡面号时，获取指定卡类型的处理器失败"
            r8.warn(r1, r2)
        L_0x0035:
            return r0
        L_0x0036:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r1 = r7.cardBusinessBasic
            cn.com.fmsh.script.ApduHandler r1 = r1.getApduHandler()
            r2 = 0
            if (r1 != 0) goto L_0x0057
            cn.com.fmsh.util.log.FMLog r3 = r7.fmLog
            if (r3 == 0) goto L_0x004d
            cn.com.fmsh.util.log.FMLog r3 = r7.fmLog
            java.lang.String r4 = r7.logTag
            java.lang.String r5 = "获取卡面号时，APDU处理器为空"
            r3.error(r4, r5)
        L_0x004d:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r7.cardBusinessBasic
            java.lang.String r4 = "获取卡面号时，请先切换卡的访问方式(OMA/NFC)"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_null
            r3.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r2)
        L_0x0057:
            boolean r3 = r1.isConnect()
            r4 = 1
            if (r3 == 0) goto L_0x0077
            cn.com.fmsh.util.log.FMLog r3 = r7.fmLog
            if (r3 == 0) goto L_0x006c
            cn.com.fmsh.util.log.FMLog r3 = r7.fmLog
            java.lang.String r5 = r7.logTag
            java.lang.String r6 = "获取卡面号时，APDU处理器正忙"
            r3.error(r5, r6)
        L_0x006c:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r7.cardBusinessBasic
            java.lang.String r5 = "获取卡面号时，APDU处理器正忙"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_busying
            r3.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r2)
            goto L_0x0095
        L_0x0077:
            boolean r2 = r1.connect()
            if (r2 != 0) goto L_0x0095
            cn.com.fmsh.util.log.FMLog r2 = r7.fmLog
            if (r2 == 0) goto L_0x008b
            cn.com.fmsh.util.log.FMLog r2 = r7.fmLog
            java.lang.String r3 = r7.logTag
            java.lang.String r5 = "获取卡面号时，连接卡失败"
            r2.error(r3, r5)
        L_0x008b:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r7.cardBusinessBasic
            java.lang.String r3 = "获取卡面号时，连接卡失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception
            r2.throwExceptionAndClose((java.lang.String) r3, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r4)
        L_0x0095:
            r8.setApduHandler(r1)
            java.lang.String r8 = r8.getFaceID()     // Catch:{ BusinessException -> 0x00a2 }
            r1.close()
            goto L_0x00be
        L_0x00a0:
            r8 = move-exception
            goto L_0x00bf
        L_0x00a2:
            cn.com.fmsh.util.log.FMLog r8 = r7.fmLog     // Catch:{ all -> 0x00a0 }
            if (r8 == 0) goto L_0x00b0
            cn.com.fmsh.util.log.FMLog r8 = r7.fmLog     // Catch:{ all -> 0x00a0 }
            java.lang.String r2 = r7.logTag     // Catch:{ all -> 0x00a0 }
            java.lang.String r3 = "获取卡面号时出现异常"
            r8.error(r2, r3)     // Catch:{ all -> 0x00a0 }
        L_0x00b0:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r8 = r7.cardBusinessBasic     // Catch:{ all -> 0x00a0 }
            java.lang.String r2 = "获取卡面号时出现异常"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception     // Catch:{ all -> 0x00a0 }
            r8.throwExceptionAndClose((java.lang.String) r2, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r3, (boolean) r4)     // Catch:{ all -> 0x00a0 }
            r1.close()
            r8 = r0
        L_0x00be:
            return r8
        L_0x00bf:
            r1.close()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardAppTradeImpl.getFaceID(cn.com.fmsh.tsm.business.enums.EnumCardAppType):java.lang.String");
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:35|36|(1:38)|39|40|43) */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a0, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a4, code lost:
        if (r7.fmLog != null) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a6, code lost:
        r7.fmLog.error(r7.logTag, "获取应用序列号时出现异常");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b0, code lost:
        r7.cardBusinessBasic.throwExceptionAndClose("获取应用序列号时出现异常", cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00ba, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00bf, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00c2, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00a2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getAppNo(cn.com.fmsh.tsm.business.enums.EnumCardAppType r8) throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r7 = this;
            cn.com.fmsh.util.log.FMLog r0 = r7.fmLog
            if (r0 != 0) goto L_0x000e
            cn.com.fmsh.util.log.LogFactory r0 = cn.com.fmsh.util.log.LogFactory.getInstance()
            cn.com.fmsh.util.log.FMLog r0 = r0.getLog()
            r7.fmLog = r0
        L_0x000e:
            cn.com.fmsh.util.log.FMLog r0 = r7.fmLog
            if (r0 == 0) goto L_0x001c
            cn.com.fmsh.util.log.FMLog r0 = r7.fmLog
            java.lang.String r1 = r7.logTag
            java.lang.String r2 = "获取应用序列号..."
            r0.info(r1, r2)
        L_0x001c:
            cn.com.fmsh.tsm.business.card.CardManagerFactory r0 = cn.com.fmsh.tsm.business.card.CardManagerFactory.instance()
            cn.com.fmsh.tsm.business.card.base.CardManager r8 = r0.getCardManager(r8)
            r0 = 0
            if (r8 != 0) goto L_0x0036
            cn.com.fmsh.util.log.FMLog r8 = r7.fmLog
            if (r8 == 0) goto L_0x0035
            cn.com.fmsh.util.log.FMLog r8 = r7.fmLog
            java.lang.String r1 = r7.logTag
            java.lang.String r2 = "获取应用序列号时，获取指定卡类型的处理器失败"
            r8.warn(r1, r2)
        L_0x0035:
            return r0
        L_0x0036:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r1 = r7.cardBusinessBasic
            cn.com.fmsh.script.ApduHandler r1 = r1.getApduHandler()
            r2 = 0
            if (r1 != 0) goto L_0x0057
            cn.com.fmsh.util.log.FMLog r3 = r7.fmLog
            if (r3 == 0) goto L_0x004d
            cn.com.fmsh.util.log.FMLog r3 = r7.fmLog
            java.lang.String r4 = r7.logTag
            java.lang.String r5 = "获取应用序列号时，APDU处理器为空"
            r3.error(r4, r5)
        L_0x004d:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r7.cardBusinessBasic
            java.lang.String r4 = "获取应用序列号时，请先切换卡的访问方式(OMA/NFC)"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_null
            r3.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r2)
        L_0x0057:
            boolean r3 = r1.isConnect()
            r4 = 1
            if (r3 == 0) goto L_0x0077
            cn.com.fmsh.util.log.FMLog r3 = r7.fmLog
            if (r3 == 0) goto L_0x006c
            cn.com.fmsh.util.log.FMLog r3 = r7.fmLog
            java.lang.String r5 = r7.logTag
            java.lang.String r6 = "获取应用序列号时，APDU处理器正忙"
            r3.error(r5, r6)
        L_0x006c:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r7.cardBusinessBasic
            java.lang.String r5 = "获取应用序列号时，APDU处理器正忙"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_busying
            r3.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r2)
            goto L_0x0095
        L_0x0077:
            boolean r2 = r1.connect()
            if (r2 != 0) goto L_0x0095
            cn.com.fmsh.util.log.FMLog r2 = r7.fmLog
            if (r2 == 0) goto L_0x008b
            cn.com.fmsh.util.log.FMLog r2 = r7.fmLog
            java.lang.String r3 = r7.logTag
            java.lang.String r5 = "获取应用序列号时，连接卡失败"
            r2.error(r3, r5)
        L_0x008b:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r7.cardBusinessBasic
            java.lang.String r3 = "获取应用序列号时，连接卡失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception
            r2.throwExceptionAndClose((java.lang.String) r3, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r4)
        L_0x0095:
            r8.setApduHandler(r1)
            byte[] r8 = r8.getAppNo()     // Catch:{ BusinessException -> 0x00a2 }
            r1.close()
            goto L_0x00be
        L_0x00a0:
            r8 = move-exception
            goto L_0x00bf
        L_0x00a2:
            cn.com.fmsh.util.log.FMLog r8 = r7.fmLog     // Catch:{ all -> 0x00a0 }
            if (r8 == 0) goto L_0x00b0
            cn.com.fmsh.util.log.FMLog r8 = r7.fmLog     // Catch:{ all -> 0x00a0 }
            java.lang.String r2 = r7.logTag     // Catch:{ all -> 0x00a0 }
            java.lang.String r3 = "获取应用序列号时出现异常"
            r8.error(r2, r3)     // Catch:{ all -> 0x00a0 }
        L_0x00b0:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r8 = r7.cardBusinessBasic     // Catch:{ all -> 0x00a0 }
            java.lang.String r2 = "获取应用序列号时出现异常"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception     // Catch:{ all -> 0x00a0 }
            r8.throwExceptionAndClose((java.lang.String) r2, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r3, (boolean) r4)     // Catch:{ all -> 0x00a0 }
            r1.close()
            r8 = r0
        L_0x00be:
            return r8
        L_0x00bf:
            r1.close()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardAppTradeImpl.getAppNo(cn.com.fmsh.tsm.business.enums.EnumCardAppType):byte[]");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00f3, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00f7, code lost:
        if (r8.fmLog != null) goto L_0x00f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00f9, code lost:
        r3 = r8.fmLog;
        r5 = r8.logTag;
        r3.error(r5, java.lang.String.valueOf("获取卡的类型时") + "出现异常");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0113, code lost:
        r3 = r8.cardBusinessBasic;
        r3.throwExceptionAndClose(java.lang.String.valueOf("获取卡的类型时") + "出现异常", cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0131, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0134, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00f5 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public cn.com.fmsh.tsm.business.enums.EnumCardAppType getCardAppType() throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r8 = this;
            cn.com.fmsh.util.log.FMLog r0 = r8.fmLog
            if (r0 != 0) goto L_0x000e
            cn.com.fmsh.util.log.LogFactory r0 = cn.com.fmsh.util.log.LogFactory.getInstance()
            cn.com.fmsh.util.log.FMLog r0 = r0.getLog()
            r8.fmLog = r0
        L_0x000e:
            cn.com.fmsh.util.log.FMLog r0 = r8.fmLog
            if (r0 == 0) goto L_0x001c
            cn.com.fmsh.util.log.FMLog r0 = r8.fmLog
            java.lang.String r1 = r8.logTag
            java.lang.String r2 = "获取卡的类型..."
            r0.info(r1, r2)
        L_0x001c:
            java.lang.String r0 = "获取卡的类型时"
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r1 = r8.cardBusinessBasic
            cn.com.fmsh.script.ApduHandler r1 = r1.getApduHandler()
            r2 = 0
            if (r1 != 0) goto L_0x0060
            cn.com.fmsh.util.log.FMLog r3 = r8.fmLog
            if (r3 == 0) goto L_0x0046
            cn.com.fmsh.util.log.FMLog r3 = r8.fmLog
            java.lang.String r4 = r8.logTag
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = java.lang.String.valueOf(r0)
            r5.<init>(r6)
            java.lang.String r6 = "，APDU处理器为空"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r3.error(r4, r5)
        L_0x0046:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r8.cardBusinessBasic
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = java.lang.String.valueOf(r0)
            r4.<init>(r5)
            java.lang.String r5 = "，请先切换卡的访问方式(OMA/NFC)"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_null
            r3.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r2)
        L_0x0060:
            boolean r3 = r1.isConnect()
            r4 = 1
            if (r3 == 0) goto L_0x00a0
            cn.com.fmsh.util.log.FMLog r3 = r8.fmLog
            if (r3 == 0) goto L_0x0085
            cn.com.fmsh.util.log.FMLog r3 = r8.fmLog
            java.lang.String r5 = r8.logTag
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = java.lang.String.valueOf(r0)
            r6.<init>(r7)
            java.lang.String r7 = "，APDU处理器正忙"
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r3.error(r5, r6)
        L_0x0085:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r8.cardBusinessBasic
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = java.lang.String.valueOf(r0)
            r5.<init>(r6)
            java.lang.String r6 = "，APDU处理器正忙"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_busying
            r3.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r2)
            goto L_0x00de
        L_0x00a0:
            boolean r2 = r1.connect()
            if (r2 != 0) goto L_0x00de
            cn.com.fmsh.util.log.FMLog r2 = r8.fmLog
            if (r2 == 0) goto L_0x00c4
            cn.com.fmsh.util.log.FMLog r2 = r8.fmLog
            java.lang.String r3 = r8.logTag
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = java.lang.String.valueOf(r0)
            r5.<init>(r6)
            java.lang.String r6 = "，连接卡失败"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r2.error(r3, r5)
        L_0x00c4:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r8.cardBusinessBasic
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = java.lang.String.valueOf(r0)
            r3.<init>(r5)
            java.lang.String r5 = "，连接卡失败"
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception
            r2.throwExceptionAndClose((java.lang.String) r3, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r4)
        L_0x00de:
            cn.com.fmsh.tsm.business.card.CardManagerFactory r2 = cn.com.fmsh.tsm.business.card.CardManagerFactory.instance()
            r2.setApduHandler(r1)
            r2 = 0
            cn.com.fmsh.tsm.business.card.CardManagerFactory r3 = cn.com.fmsh.tsm.business.card.CardManagerFactory.instance()     // Catch:{ BusinessException -> 0x00f5 }
            cn.com.fmsh.tsm.business.enums.EnumCardAppType r3 = r3.getCardAppType()     // Catch:{ BusinessException -> 0x00f5 }
            r1.close()
            r2 = r3
            goto L_0x0130
        L_0x00f3:
            r0 = move-exception
            goto L_0x0131
        L_0x00f5:
            cn.com.fmsh.util.log.FMLog r3 = r8.fmLog     // Catch:{ all -> 0x00f3 }
            if (r3 == 0) goto L_0x0113
            cn.com.fmsh.util.log.FMLog r3 = r8.fmLog     // Catch:{ all -> 0x00f3 }
            java.lang.String r5 = r8.logTag     // Catch:{ all -> 0x00f3 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f3 }
            java.lang.String r7 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x00f3 }
            r6.<init>(r7)     // Catch:{ all -> 0x00f3 }
            java.lang.String r7 = "出现异常"
            r6.append(r7)     // Catch:{ all -> 0x00f3 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00f3 }
            r3.error(r5, r6)     // Catch:{ all -> 0x00f3 }
        L_0x0113:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r8.cardBusinessBasic     // Catch:{ all -> 0x00f3 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f3 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x00f3 }
            r5.<init>(r0)     // Catch:{ all -> 0x00f3 }
            java.lang.String r0 = "出现异常"
            r5.append(r0)     // Catch:{ all -> 0x00f3 }
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x00f3 }
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception     // Catch:{ all -> 0x00f3 }
            r3.throwExceptionAndClose((java.lang.String) r0, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r4)     // Catch:{ all -> 0x00f3 }
            r1.close()
        L_0x0130:
            return r2
        L_0x0131:
            r1.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardAppTradeImpl.getCardAppType():cn.com.fmsh.tsm.business.enums.EnumCardAppType");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00f3, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00f7, code lost:
        if (r8.fmLog != null) goto L_0x00f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00f9, code lost:
        r3 = r8.fmLog;
        r5 = r8.logTag;
        r3.error(r5, java.lang.String.valueOf("获取卡的类型时") + "出现异常");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0113, code lost:
        r3 = r8.cardBusinessBasic;
        r3.throwExceptionAndClose(java.lang.String.valueOf("获取卡的类型时") + "出现异常", cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0131, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0134, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00f5 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<cn.com.fmsh.tsm.business.enums.EnumCardAppType> getCardAppTypes() throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r8 = this;
            cn.com.fmsh.util.log.FMLog r0 = r8.fmLog
            if (r0 != 0) goto L_0x000e
            cn.com.fmsh.util.log.LogFactory r0 = cn.com.fmsh.util.log.LogFactory.getInstance()
            cn.com.fmsh.util.log.FMLog r0 = r0.getLog()
            r8.fmLog = r0
        L_0x000e:
            cn.com.fmsh.util.log.FMLog r0 = r8.fmLog
            if (r0 == 0) goto L_0x001c
            cn.com.fmsh.util.log.FMLog r0 = r8.fmLog
            java.lang.String r1 = r8.logTag
            java.lang.String r2 = "获取卡的类型..."
            r0.info(r1, r2)
        L_0x001c:
            java.lang.String r0 = "获取卡的类型时"
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r1 = r8.cardBusinessBasic
            cn.com.fmsh.script.ApduHandler r1 = r1.getApduHandler()
            r2 = 0
            if (r1 != 0) goto L_0x0060
            cn.com.fmsh.util.log.FMLog r3 = r8.fmLog
            if (r3 == 0) goto L_0x0046
            cn.com.fmsh.util.log.FMLog r3 = r8.fmLog
            java.lang.String r4 = r8.logTag
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = java.lang.String.valueOf(r0)
            r5.<init>(r6)
            java.lang.String r6 = "，APDU处理器为空"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r3.error(r4, r5)
        L_0x0046:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r8.cardBusinessBasic
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = java.lang.String.valueOf(r0)
            r4.<init>(r5)
            java.lang.String r5 = "，请先切换卡的访问方式(OMA/NFC)"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_null
            r3.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r2)
        L_0x0060:
            boolean r3 = r1.isConnect()
            r4 = 1
            if (r3 == 0) goto L_0x00a0
            cn.com.fmsh.util.log.FMLog r3 = r8.fmLog
            if (r3 == 0) goto L_0x0085
            cn.com.fmsh.util.log.FMLog r3 = r8.fmLog
            java.lang.String r5 = r8.logTag
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = java.lang.String.valueOf(r0)
            r6.<init>(r7)
            java.lang.String r7 = "，APDU处理器正忙"
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r3.error(r5, r6)
        L_0x0085:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r8.cardBusinessBasic
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = java.lang.String.valueOf(r0)
            r5.<init>(r6)
            java.lang.String r6 = "，APDU处理器正忙"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_busying
            r3.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r2)
            goto L_0x00de
        L_0x00a0:
            boolean r2 = r1.connect()
            if (r2 != 0) goto L_0x00de
            cn.com.fmsh.util.log.FMLog r2 = r8.fmLog
            if (r2 == 0) goto L_0x00c4
            cn.com.fmsh.util.log.FMLog r2 = r8.fmLog
            java.lang.String r3 = r8.logTag
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = java.lang.String.valueOf(r0)
            r5.<init>(r6)
            java.lang.String r6 = "，连接卡失败"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r2.error(r3, r5)
        L_0x00c4:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r8.cardBusinessBasic
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = java.lang.String.valueOf(r0)
            r3.<init>(r5)
            java.lang.String r5 = "，连接卡失败"
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception
            r2.throwExceptionAndClose((java.lang.String) r3, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r4)
        L_0x00de:
            cn.com.fmsh.tsm.business.card.CardManagerFactory r2 = cn.com.fmsh.tsm.business.card.CardManagerFactory.instance()
            r2.setApduHandler(r1)
            r2 = 0
            cn.com.fmsh.tsm.business.card.CardManagerFactory r3 = cn.com.fmsh.tsm.business.card.CardManagerFactory.instance()     // Catch:{ BusinessException -> 0x00f5 }
            java.util.List r3 = r3.getCardAppTypes()     // Catch:{ BusinessException -> 0x00f5 }
            r1.close()
            r2 = r3
            goto L_0x0130
        L_0x00f3:
            r0 = move-exception
            goto L_0x0131
        L_0x00f5:
            cn.com.fmsh.util.log.FMLog r3 = r8.fmLog     // Catch:{ all -> 0x00f3 }
            if (r3 == 0) goto L_0x0113
            cn.com.fmsh.util.log.FMLog r3 = r8.fmLog     // Catch:{ all -> 0x00f3 }
            java.lang.String r5 = r8.logTag     // Catch:{ all -> 0x00f3 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f3 }
            java.lang.String r7 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x00f3 }
            r6.<init>(r7)     // Catch:{ all -> 0x00f3 }
            java.lang.String r7 = "出现异常"
            r6.append(r7)     // Catch:{ all -> 0x00f3 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x00f3 }
            r3.error(r5, r6)     // Catch:{ all -> 0x00f3 }
        L_0x0113:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r8.cardBusinessBasic     // Catch:{ all -> 0x00f3 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f3 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x00f3 }
            r5.<init>(r0)     // Catch:{ all -> 0x00f3 }
            java.lang.String r0 = "出现异常"
            r5.append(r0)     // Catch:{ all -> 0x00f3 }
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x00f3 }
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception     // Catch:{ all -> 0x00f3 }
            r3.throwExceptionAndClose((java.lang.String) r0, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r4)     // Catch:{ all -> 0x00f3 }
            r1.close()
        L_0x0130:
            return r2
        L_0x0131:
            r1.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardAppTradeImpl.getCardAppTypes():java.util.List");
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:35|36|(1:38)|39|40|43) */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a0, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a4, code lost:
        if (r7.fmLog != null) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a6, code lost:
        r7.fmLog.error(r7.logTag, "获取住建部认证码时时出现异常");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b0, code lost:
        r7.cardBusinessBasic.throwExceptionAndClose("获取住建部认证码时出现异常", cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00ba, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00bf, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00c2, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00a2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getMOC(cn.com.fmsh.tsm.business.enums.EnumCardAppType r8) throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r7 = this;
            cn.com.fmsh.util.log.FMLog r0 = r7.fmLog
            if (r0 != 0) goto L_0x000e
            cn.com.fmsh.util.log.LogFactory r0 = cn.com.fmsh.util.log.LogFactory.getInstance()
            cn.com.fmsh.util.log.FMLog r0 = r0.getLog()
            r7.fmLog = r0
        L_0x000e:
            cn.com.fmsh.util.log.FMLog r0 = r7.fmLog
            if (r0 == 0) goto L_0x001c
            cn.com.fmsh.util.log.FMLog r0 = r7.fmLog
            java.lang.String r1 = r7.logTag
            java.lang.String r2 = "获取交通卡住建部认证码..."
            r0.info(r1, r2)
        L_0x001c:
            cn.com.fmsh.tsm.business.card.CardManagerFactory r0 = cn.com.fmsh.tsm.business.card.CardManagerFactory.instance()
            cn.com.fmsh.tsm.business.card.base.CardManager r8 = r0.getCardManager(r8)
            r0 = 0
            if (r8 != 0) goto L_0x0036
            cn.com.fmsh.util.log.FMLog r8 = r7.fmLog
            if (r8 == 0) goto L_0x0035
            cn.com.fmsh.util.log.FMLog r8 = r7.fmLog
            java.lang.String r1 = r7.logTag
            java.lang.String r2 = "获取交通卡住建部认证码时，获取指定卡类型的处理器失败"
            r8.warn(r1, r2)
        L_0x0035:
            return r0
        L_0x0036:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r1 = r7.cardBusinessBasic
            cn.com.fmsh.script.ApduHandler r1 = r1.getApduHandler()
            r2 = 0
            if (r1 != 0) goto L_0x0057
            cn.com.fmsh.util.log.FMLog r3 = r7.fmLog
            if (r3 == 0) goto L_0x004d
            cn.com.fmsh.util.log.FMLog r3 = r7.fmLog
            java.lang.String r4 = r7.logTag
            java.lang.String r5 = "获取住建部认证码时，APDU处理器为空"
            r3.error(r4, r5)
        L_0x004d:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r7.cardBusinessBasic
            java.lang.String r4 = "获取住建部认证码时，请先切换卡的访问方式(OMA/NFC)"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_null
            r3.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r2)
        L_0x0057:
            boolean r3 = r1.isConnect()
            r4 = 1
            if (r3 == 0) goto L_0x0077
            cn.com.fmsh.util.log.FMLog r3 = r7.fmLog
            if (r3 == 0) goto L_0x006c
            cn.com.fmsh.util.log.FMLog r3 = r7.fmLog
            java.lang.String r5 = r7.logTag
            java.lang.String r6 = "获取住建部认证码时，APDU处理器正忙"
            r3.error(r5, r6)
        L_0x006c:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r7.cardBusinessBasic
            java.lang.String r5 = "获取住建部认证码时，APDU处理器正忙"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_busying
            r3.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r2)
            goto L_0x0095
        L_0x0077:
            boolean r2 = r1.connect()
            if (r2 != 0) goto L_0x0095
            cn.com.fmsh.util.log.FMLog r2 = r7.fmLog
            if (r2 == 0) goto L_0x008b
            cn.com.fmsh.util.log.FMLog r2 = r7.fmLog
            java.lang.String r3 = r7.logTag
            java.lang.String r5 = "获取住建部认证码时，连接卡失败"
            r2.error(r3, r5)
        L_0x008b:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r7.cardBusinessBasic
            java.lang.String r3 = "获取住建部认证码时，连接卡失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception
            r2.throwExceptionAndClose((java.lang.String) r3, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r4)
        L_0x0095:
            r8.setApduHandler(r1)
            java.lang.String r8 = r8.getMOC()     // Catch:{ BusinessException -> 0x00a2 }
            r1.close()
            goto L_0x00be
        L_0x00a0:
            r8 = move-exception
            goto L_0x00bf
        L_0x00a2:
            cn.com.fmsh.util.log.FMLog r8 = r7.fmLog     // Catch:{ all -> 0x00a0 }
            if (r8 == 0) goto L_0x00b0
            cn.com.fmsh.util.log.FMLog r8 = r7.fmLog     // Catch:{ all -> 0x00a0 }
            java.lang.String r2 = r7.logTag     // Catch:{ all -> 0x00a0 }
            java.lang.String r3 = "获取住建部认证码时时出现异常"
            r8.error(r2, r3)     // Catch:{ all -> 0x00a0 }
        L_0x00b0:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r8 = r7.cardBusinessBasic     // Catch:{ all -> 0x00a0 }
            java.lang.String r2 = "获取住建部认证码时出现异常"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception     // Catch:{ all -> 0x00a0 }
            r8.throwExceptionAndClose((java.lang.String) r2, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r3, (boolean) r4)     // Catch:{ all -> 0x00a0 }
            r1.close()
            r8 = r0
        L_0x00be:
            return r8
        L_0x00bf:
            r1.close()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardAppTradeImpl.getMOC(cn.com.fmsh.tsm.business.enums.EnumCardAppType):java.lang.String");
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0117, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x011b, code lost:
        if (r9.fmLog != null) goto L_0x011d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x011d, code lost:
        r10 = r9.fmLog;
        r3 = r9.logTag;
        r10.error(r3, java.lang.String.valueOf("获取交通卡的余额") + "时出现异常");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0137, code lost:
        r10 = r9.cardBusinessBasic;
        r10.throwExceptionAndClose(java.lang.String.valueOf("获取交通卡的余额") + "时出现异常", cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0151, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0156, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0159, code lost:
        throw r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0119 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Integer getBalance(cn.com.fmsh.tsm.business.enums.EnumCardAppType r10) throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r9 = this;
            cn.com.fmsh.util.log.FMLog r0 = r9.fmLog
            if (r0 != 0) goto L_0x000e
            cn.com.fmsh.util.log.LogFactory r0 = cn.com.fmsh.util.log.LogFactory.getInstance()
            cn.com.fmsh.util.log.FMLog r0 = r0.getLog()
            r9.fmLog = r0
        L_0x000e:
            cn.com.fmsh.util.log.FMLog r0 = r9.fmLog
            if (r0 == 0) goto L_0x001c
            cn.com.fmsh.util.log.FMLog r0 = r9.fmLog
            java.lang.String r1 = r9.logTag
            java.lang.String r2 = "获取交通卡的余额..."
            r0.info(r1, r2)
        L_0x001c:
            java.lang.String r0 = "获取交通卡的余额"
            cn.com.fmsh.tsm.business.card.CardManagerFactory r1 = cn.com.fmsh.tsm.business.card.CardManagerFactory.instance()
            cn.com.fmsh.tsm.business.card.base.CardManager r10 = r1.getCardManager(r10)
            r1 = 0
            if (r10 != 0) goto L_0x0049
            cn.com.fmsh.util.log.FMLog r10 = r9.fmLog
            if (r10 == 0) goto L_0x0048
            cn.com.fmsh.util.log.FMLog r10 = r9.fmLog
            java.lang.String r2 = r9.logTag
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r3.<init>(r0)
            java.lang.String r0 = "，获取指定卡类型的处理器失败"
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r10.warn(r2, r0)
        L_0x0048:
            return r1
        L_0x0049:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r9.cardBusinessBasic
            cn.com.fmsh.script.ApduHandler r2 = r2.getApduHandler()
            r3 = 0
            if (r2 != 0) goto L_0x008a
            cn.com.fmsh.util.log.FMLog r4 = r9.fmLog
            if (r4 == 0) goto L_0x0070
            cn.com.fmsh.util.log.FMLog r4 = r9.fmLog
            java.lang.String r5 = r9.logTag
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = java.lang.String.valueOf(r0)
            r6.<init>(r7)
            java.lang.String r7 = "时，APDU处理器为空"
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r4.error(r5, r6)
        L_0x0070:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r4 = r9.cardBusinessBasic
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = java.lang.String.valueOf(r0)
            r5.<init>(r6)
            java.lang.String r6 = "时，请先切换卡的访问方式(OMA/NFC)"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_null
            r4.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r3)
        L_0x008a:
            boolean r4 = r2.isConnect()
            r5 = 1
            if (r4 == 0) goto L_0x00ca
            cn.com.fmsh.util.log.FMLog r4 = r9.fmLog
            if (r4 == 0) goto L_0x00af
            cn.com.fmsh.util.log.FMLog r4 = r9.fmLog
            java.lang.String r6 = r9.logTag
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = java.lang.String.valueOf(r0)
            r7.<init>(r8)
            java.lang.String r8 = "时，APDU处理器正忙"
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            r4.error(r6, r7)
        L_0x00af:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r4 = r9.cardBusinessBasic
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = java.lang.String.valueOf(r0)
            r6.<init>(r7)
            java.lang.String r7 = "时，APDU处理器正忙"
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r7 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_busying
            r4.throwExceptionAndClose((java.lang.String) r6, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r7, (boolean) r3)
            goto L_0x0108
        L_0x00ca:
            boolean r3 = r2.connect()
            if (r3 != 0) goto L_0x0108
            cn.com.fmsh.util.log.FMLog r3 = r9.fmLog
            if (r3 == 0) goto L_0x00ee
            cn.com.fmsh.util.log.FMLog r3 = r9.fmLog
            java.lang.String r4 = r9.logTag
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = java.lang.String.valueOf(r0)
            r6.<init>(r7)
            java.lang.String r7 = "，连接卡失败"
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r3.error(r4, r6)
        L_0x00ee:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r9.cardBusinessBasic
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r6 = java.lang.String.valueOf(r0)
            r4.<init>(r6)
            java.lang.String r6 = "时，连接卡失败"
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception
            r3.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r5)
        L_0x0108:
            r10.setApduHandler(r2)
            int r10 = r10.queryBalance()     // Catch:{ BusinessException -> 0x0119 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ BusinessException -> 0x0119 }
            r2.close()
            goto L_0x0155
        L_0x0117:
            r10 = move-exception
            goto L_0x0156
        L_0x0119:
            cn.com.fmsh.util.log.FMLog r10 = r9.fmLog     // Catch:{ all -> 0x0117 }
            if (r10 == 0) goto L_0x0137
            cn.com.fmsh.util.log.FMLog r10 = r9.fmLog     // Catch:{ all -> 0x0117 }
            java.lang.String r3 = r9.logTag     // Catch:{ all -> 0x0117 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0117 }
            java.lang.String r6 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0117 }
            r4.<init>(r6)     // Catch:{ all -> 0x0117 }
            java.lang.String r6 = "时出现异常"
            r4.append(r6)     // Catch:{ all -> 0x0117 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0117 }
            r10.error(r3, r4)     // Catch:{ all -> 0x0117 }
        L_0x0137:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r10 = r9.cardBusinessBasic     // Catch:{ all -> 0x0117 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0117 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0117 }
            r3.<init>(r0)     // Catch:{ all -> 0x0117 }
            java.lang.String r0 = "时出现异常"
            r3.append(r0)     // Catch:{ all -> 0x0117 }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x0117 }
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception     // Catch:{ all -> 0x0117 }
            r10.throwExceptionAndClose((java.lang.String) r0, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r3, (boolean) r5)     // Catch:{ all -> 0x0117 }
            r2.close()
            r10 = r1
        L_0x0155:
            return r10
        L_0x0156:
            r2.close()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardAppTradeImpl.getBalance(cn.com.fmsh.tsm.business.enums.EnumCardAppType):java.lang.Integer");
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0113, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0117, code lost:
        if (r9.fmLog != null) goto L_0x0119;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0119, code lost:
        r10 = r9.fmLog;
        r3 = r9.logTag;
        r10.error(r3, java.lang.String.valueOf("获取卡的交易记录") + "时出现异常");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0133, code lost:
        r10 = r9.cardBusinessBasic;
        r10.throwExceptionAndClose(java.lang.String.valueOf("获取卡的交易记录") + "时出现异常", cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x014d, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0152, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0155, code lost:
        throw r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0115 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<cn.com.fmsh.tsm.business.bean.CardAppRecord> getCardAppRecords(cn.com.fmsh.tsm.business.enums.EnumCardAppType r10) throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r9 = this;
            cn.com.fmsh.util.log.FMLog r0 = r9.fmLog
            if (r0 != 0) goto L_0x000e
            cn.com.fmsh.util.log.LogFactory r0 = cn.com.fmsh.util.log.LogFactory.getInstance()
            cn.com.fmsh.util.log.FMLog r0 = r0.getLog()
            r9.fmLog = r0
        L_0x000e:
            cn.com.fmsh.util.log.FMLog r0 = r9.fmLog
            if (r0 == 0) goto L_0x001c
            cn.com.fmsh.util.log.FMLog r0 = r9.fmLog
            java.lang.String r1 = r9.logTag
            java.lang.String r2 = "获取卡的交易记录..."
            r0.info(r1, r2)
        L_0x001c:
            java.lang.String r0 = "获取卡的交易记录"
            cn.com.fmsh.tsm.business.card.CardManagerFactory r1 = cn.com.fmsh.tsm.business.card.CardManagerFactory.instance()
            cn.com.fmsh.tsm.business.card.base.CardManager r10 = r1.getCardManager(r10)
            r1 = 0
            if (r10 != 0) goto L_0x0049
            cn.com.fmsh.util.log.FMLog r10 = r9.fmLog
            if (r10 == 0) goto L_0x0048
            cn.com.fmsh.util.log.FMLog r10 = r9.fmLog
            java.lang.String r2 = r9.logTag
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r3.<init>(r0)
            java.lang.String r0 = "，获取指定卡类型的处理器失败"
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r10.warn(r2, r0)
        L_0x0048:
            return r1
        L_0x0049:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r9.cardBusinessBasic
            cn.com.fmsh.script.ApduHandler r2 = r2.getApduHandler()
            r3 = 0
            if (r2 != 0) goto L_0x008a
            cn.com.fmsh.util.log.FMLog r4 = r9.fmLog
            if (r4 == 0) goto L_0x0070
            cn.com.fmsh.util.log.FMLog r4 = r9.fmLog
            java.lang.String r5 = r9.logTag
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = java.lang.String.valueOf(r0)
            r6.<init>(r7)
            java.lang.String r7 = "时，APDU处理器为空"
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r4.error(r5, r6)
        L_0x0070:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r4 = r9.cardBusinessBasic
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = java.lang.String.valueOf(r0)
            r5.<init>(r6)
            java.lang.String r6 = "时，请先切换卡的访问方式(OMA/NFC)"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_null
            r4.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r3)
        L_0x008a:
            boolean r4 = r2.isConnect()
            r5 = 1
            if (r4 == 0) goto L_0x00ca
            cn.com.fmsh.util.log.FMLog r4 = r9.fmLog
            if (r4 == 0) goto L_0x00af
            cn.com.fmsh.util.log.FMLog r4 = r9.fmLog
            java.lang.String r6 = r9.logTag
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = java.lang.String.valueOf(r0)
            r7.<init>(r8)
            java.lang.String r8 = "时，APDU处理器正忙"
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            r4.error(r6, r7)
        L_0x00af:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r4 = r9.cardBusinessBasic
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = java.lang.String.valueOf(r0)
            r6.<init>(r7)
            java.lang.String r7 = "时，APDU处理器正忙"
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r7 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_busying
            r4.throwExceptionAndClose((java.lang.String) r6, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r7, (boolean) r3)
            goto L_0x0108
        L_0x00ca:
            boolean r3 = r2.connect()
            if (r3 != 0) goto L_0x0108
            cn.com.fmsh.util.log.FMLog r3 = r9.fmLog
            if (r3 == 0) goto L_0x00ee
            cn.com.fmsh.util.log.FMLog r3 = r9.fmLog
            java.lang.String r4 = r9.logTag
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = java.lang.String.valueOf(r0)
            r6.<init>(r7)
            java.lang.String r7 = "时，连接卡失败"
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r3.error(r4, r6)
        L_0x00ee:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r9.cardBusinessBasic
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r6 = java.lang.String.valueOf(r0)
            r4.<init>(r6)
            java.lang.String r6 = "时，连接卡失败"
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception
            r3.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r5)
        L_0x0108:
            r10.setApduHandler(r2)
            java.util.List r10 = r10.readAppRecords()     // Catch:{ BusinessException -> 0x0115 }
            r2.close()
            goto L_0x0151
        L_0x0113:
            r10 = move-exception
            goto L_0x0152
        L_0x0115:
            cn.com.fmsh.util.log.FMLog r10 = r9.fmLog     // Catch:{ all -> 0x0113 }
            if (r10 == 0) goto L_0x0133
            cn.com.fmsh.util.log.FMLog r10 = r9.fmLog     // Catch:{ all -> 0x0113 }
            java.lang.String r3 = r9.logTag     // Catch:{ all -> 0x0113 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0113 }
            java.lang.String r6 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0113 }
            r4.<init>(r6)     // Catch:{ all -> 0x0113 }
            java.lang.String r6 = "时出现异常"
            r4.append(r6)     // Catch:{ all -> 0x0113 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0113 }
            r10.error(r3, r4)     // Catch:{ all -> 0x0113 }
        L_0x0133:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r10 = r9.cardBusinessBasic     // Catch:{ all -> 0x0113 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0113 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0113 }
            r3.<init>(r0)     // Catch:{ all -> 0x0113 }
            java.lang.String r0 = "时出现异常"
            r3.append(r0)     // Catch:{ all -> 0x0113 }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x0113 }
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception     // Catch:{ all -> 0x0113 }
            r10.throwExceptionAndClose((java.lang.String) r0, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r3, (boolean) r5)     // Catch:{ all -> 0x0113 }
            r2.close()
            r10 = r1
        L_0x0151:
            return r10
        L_0x0152:
            r2.close()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardAppTradeImpl.getCardAppRecords(cn.com.fmsh.tsm.business.enums.EnumCardAppType):java.util.List");
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x012b, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x012f, code lost:
        if (r8.fmLog != null) goto L_0x0131;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0131, code lost:
        r9 = r8.fmLog;
        r3 = r8.logTag;
        r9.error(r3, java.lang.String.valueOf("获取交通卡圈存功能锁定状态") + "时出现异常");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x014b, code lost:
        r9 = r8.cardBusinessBasic;
        r9.throwExceptionAndClose(java.lang.String.valueOf("获取交通卡圈存功能锁定状态") + "时出现异常", cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0165, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x016a, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x016d, code lost:
        throw r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        return false;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x012d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isLock4Load(cn.com.fmsh.tsm.business.enums.EnumCardAppType r9) throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r8 = this;
            cn.com.fmsh.util.log.FMLog r0 = r8.fmLog
            if (r0 != 0) goto L_0x000e
            cn.com.fmsh.util.log.LogFactory r0 = cn.com.fmsh.util.log.LogFactory.getInstance()
            cn.com.fmsh.util.log.FMLog r0 = r0.getLog()
            r8.fmLog = r0
        L_0x000e:
            cn.com.fmsh.util.log.FMLog r0 = r8.fmLog
            if (r0 == 0) goto L_0x001c
            cn.com.fmsh.util.log.FMLog r0 = r8.fmLog
            java.lang.String r1 = r8.logTag
            java.lang.String r2 = "获取交通卡圈存功能锁定状态..."
            r0.info(r1, r2)
        L_0x001c:
            java.lang.String r0 = "获取交通卡圈存功能锁定状态"
            cn.com.fmsh.tsm.business.card.CardManagerFactory r1 = cn.com.fmsh.tsm.business.card.CardManagerFactory.instance()
            cn.com.fmsh.tsm.business.card.base.CardManager r9 = r1.getCardManager(r9)
            r1 = 0
            if (r9 != 0) goto L_0x0062
            cn.com.fmsh.util.log.FMLog r2 = r8.fmLog
            if (r2 == 0) goto L_0x0048
            cn.com.fmsh.util.log.FMLog r2 = r8.fmLog
            java.lang.String r3 = r8.logTag
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = java.lang.String.valueOf(r0)
            r4.<init>(r5)
            java.lang.String r5 = "时，传如的卡类型无效"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r2.warn(r3, r4)
        L_0x0048:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r8.cardBusinessBasic
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = java.lang.String.valueOf(r0)
            r3.<init>(r4)
            java.lang.String r4 = "时，请先切换卡的访问方式(OMA/NFC)"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r4 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_no_card_app_type
            r2.throwExceptionAndClose((java.lang.String) r3, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r4, (boolean) r1)
        L_0x0062:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r8.cardBusinessBasic
            cn.com.fmsh.script.ApduHandler r2 = r2.getApduHandler()
            if (r2 != 0) goto L_0x00a2
            cn.com.fmsh.util.log.FMLog r3 = r8.fmLog
            if (r3 == 0) goto L_0x0088
            cn.com.fmsh.util.log.FMLog r3 = r8.fmLog
            java.lang.String r4 = r8.logTag
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = java.lang.String.valueOf(r0)
            r5.<init>(r6)
            java.lang.String r6 = "时，APDU处理器为空"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r3.error(r4, r5)
        L_0x0088:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r8.cardBusinessBasic
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = java.lang.String.valueOf(r0)
            r4.<init>(r5)
            java.lang.String r5 = "时，请先切换卡的访问方式(OMA/NFC)"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_null
            r3.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r1)
        L_0x00a2:
            boolean r3 = r2.isConnect()
            r4 = 1
            if (r3 == 0) goto L_0x00e2
            cn.com.fmsh.util.log.FMLog r3 = r8.fmLog
            if (r3 == 0) goto L_0x00c7
            cn.com.fmsh.util.log.FMLog r3 = r8.fmLog
            java.lang.String r5 = r8.logTag
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = java.lang.String.valueOf(r0)
            r6.<init>(r7)
            java.lang.String r7 = "时，APDU处理器正忙"
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r3.error(r5, r6)
        L_0x00c7:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r8.cardBusinessBasic
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = java.lang.String.valueOf(r0)
            r5.<init>(r6)
            java.lang.String r6 = "时，APDU处理器正忙"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_busying
            r3.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r1)
            goto L_0x0120
        L_0x00e2:
            boolean r3 = r2.connect()
            if (r3 != 0) goto L_0x0120
            cn.com.fmsh.util.log.FMLog r3 = r8.fmLog
            if (r3 == 0) goto L_0x0106
            cn.com.fmsh.util.log.FMLog r3 = r8.fmLog
            java.lang.String r5 = r8.logTag
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = java.lang.String.valueOf(r0)
            r6.<init>(r7)
            java.lang.String r7 = "时，连接卡失败"
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r3.error(r5, r6)
        L_0x0106:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r8.cardBusinessBasic
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = java.lang.String.valueOf(r0)
            r5.<init>(r6)
            java.lang.String r6 = "时，连接卡失败"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception
            r3.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r4)
        L_0x0120:
            r9.setApduHandler(r2)
            boolean r9 = r9.isLock4Load()     // Catch:{ BusinessException -> 0x012d }
            r2.close()
            goto L_0x0169
        L_0x012b:
            r9 = move-exception
            goto L_0x016a
        L_0x012d:
            cn.com.fmsh.util.log.FMLog r9 = r8.fmLog     // Catch:{ all -> 0x012b }
            if (r9 == 0) goto L_0x014b
            cn.com.fmsh.util.log.FMLog r9 = r8.fmLog     // Catch:{ all -> 0x012b }
            java.lang.String r3 = r8.logTag     // Catch:{ all -> 0x012b }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x012b }
            java.lang.String r6 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x012b }
            r5.<init>(r6)     // Catch:{ all -> 0x012b }
            java.lang.String r6 = "时出现异常"
            r5.append(r6)     // Catch:{ all -> 0x012b }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x012b }
            r9.error(r3, r5)     // Catch:{ all -> 0x012b }
        L_0x014b:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r9 = r8.cardBusinessBasic     // Catch:{ all -> 0x012b }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x012b }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x012b }
            r3.<init>(r0)     // Catch:{ all -> 0x012b }
            java.lang.String r0 = "时出现异常"
            r3.append(r0)     // Catch:{ all -> 0x012b }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x012b }
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception     // Catch:{ all -> 0x012b }
            r9.throwExceptionAndClose((java.lang.String) r0, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r3, (boolean) r4)     // Catch:{ all -> 0x012b }
            r2.close()
            r9 = 0
        L_0x0169:
            return r9
        L_0x016a:
            r2.close()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardAppTradeImpl.isLock4Load(cn.com.fmsh.tsm.business.enums.EnumCardAppType):boolean");
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x012b, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x012f, code lost:
        if (r8.fmLog != null) goto L_0x0131;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0131, code lost:
        r9 = r8.fmLog;
        r3 = r8.logTag;
        r9.error(r3, java.lang.String.valueOf("获取交通卡圈存功能锁定状态") + "时出现异常");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x014b, code lost:
        r9 = r8.cardBusinessBasic;
        r9.throwExceptionAndClose(java.lang.String.valueOf("获取交通卡圈存功能锁定状态") + "时出现异常", cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0165, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x016a, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x016d, code lost:
        throw r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        return false;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x012d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isLock4Consume(cn.com.fmsh.tsm.business.enums.EnumCardAppType r9) throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r8 = this;
            cn.com.fmsh.util.log.FMLog r0 = r8.fmLog
            if (r0 != 0) goto L_0x000e
            cn.com.fmsh.util.log.LogFactory r0 = cn.com.fmsh.util.log.LogFactory.getInstance()
            cn.com.fmsh.util.log.FMLog r0 = r0.getLog()
            r8.fmLog = r0
        L_0x000e:
            cn.com.fmsh.util.log.FMLog r0 = r8.fmLog
            if (r0 == 0) goto L_0x001c
            cn.com.fmsh.util.log.FMLog r0 = r8.fmLog
            java.lang.String r1 = r8.logTag
            java.lang.String r2 = "获取交通卡消费功能是锁定状态..."
            r0.info(r1, r2)
        L_0x001c:
            java.lang.String r0 = "获取交通卡圈存功能锁定状态"
            cn.com.fmsh.tsm.business.card.CardManagerFactory r1 = cn.com.fmsh.tsm.business.card.CardManagerFactory.instance()
            cn.com.fmsh.tsm.business.card.base.CardManager r9 = r1.getCardManager(r9)
            r1 = 0
            if (r9 != 0) goto L_0x0062
            cn.com.fmsh.util.log.FMLog r2 = r8.fmLog
            if (r2 == 0) goto L_0x0048
            cn.com.fmsh.util.log.FMLog r2 = r8.fmLog
            java.lang.String r3 = r8.logTag
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = java.lang.String.valueOf(r0)
            r4.<init>(r5)
            java.lang.String r5 = "时，传如的卡类型无效"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r2.error(r3, r4)
        L_0x0048:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r8.cardBusinessBasic
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = java.lang.String.valueOf(r0)
            r3.<init>(r4)
            java.lang.String r4 = "时，请先切换卡的访问方式(OMA/NFC)"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r4 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_no_card_app_type
            r2.throwExceptionAndClose((java.lang.String) r3, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r4, (boolean) r1)
        L_0x0062:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r8.cardBusinessBasic
            cn.com.fmsh.script.ApduHandler r2 = r2.getApduHandler()
            if (r2 != 0) goto L_0x00a2
            cn.com.fmsh.util.log.FMLog r3 = r8.fmLog
            if (r3 == 0) goto L_0x0088
            cn.com.fmsh.util.log.FMLog r3 = r8.fmLog
            java.lang.String r4 = r8.logTag
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = java.lang.String.valueOf(r0)
            r5.<init>(r6)
            java.lang.String r6 = "时，APDU处理器为空"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r3.error(r4, r5)
        L_0x0088:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r8.cardBusinessBasic
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = java.lang.String.valueOf(r0)
            r4.<init>(r5)
            java.lang.String r5 = "时，请先切换卡的访问方式(OMA/NFC)"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_null
            r3.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r1)
        L_0x00a2:
            boolean r3 = r2.isConnect()
            r4 = 1
            if (r3 == 0) goto L_0x00e2
            cn.com.fmsh.util.log.FMLog r3 = r8.fmLog
            if (r3 == 0) goto L_0x00c7
            cn.com.fmsh.util.log.FMLog r3 = r8.fmLog
            java.lang.String r5 = r8.logTag
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = java.lang.String.valueOf(r0)
            r6.<init>(r7)
            java.lang.String r7 = "时，APDU处理器正忙"
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r3.error(r5, r6)
        L_0x00c7:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r8.cardBusinessBasic
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = java.lang.String.valueOf(r0)
            r5.<init>(r6)
            java.lang.String r6 = "时，APDU处理器正忙"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_busying
            r3.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r1)
            goto L_0x0120
        L_0x00e2:
            boolean r3 = r2.connect()
            if (r3 != 0) goto L_0x0120
            cn.com.fmsh.util.log.FMLog r3 = r8.fmLog
            if (r3 == 0) goto L_0x0106
            cn.com.fmsh.util.log.FMLog r3 = r8.fmLog
            java.lang.String r5 = r8.logTag
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = java.lang.String.valueOf(r0)
            r6.<init>(r7)
            java.lang.String r7 = "时，APDU处理器连接卡失败"
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r3.error(r5, r6)
        L_0x0106:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r8.cardBusinessBasic
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = java.lang.String.valueOf(r0)
            r5.<init>(r6)
            java.lang.String r6 = "时，APDU处理器连接卡失败"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception
            r3.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r4)
        L_0x0120:
            r9.setApduHandler(r2)
            boolean r9 = r9.isLock4Consume()     // Catch:{ BusinessException -> 0x012d }
            r2.close()
            goto L_0x0169
        L_0x012b:
            r9 = move-exception
            goto L_0x016a
        L_0x012d:
            cn.com.fmsh.util.log.FMLog r9 = r8.fmLog     // Catch:{ all -> 0x012b }
            if (r9 == 0) goto L_0x014b
            cn.com.fmsh.util.log.FMLog r9 = r8.fmLog     // Catch:{ all -> 0x012b }
            java.lang.String r3 = r8.logTag     // Catch:{ all -> 0x012b }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x012b }
            java.lang.String r6 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x012b }
            r5.<init>(r6)     // Catch:{ all -> 0x012b }
            java.lang.String r6 = "时出现异常"
            r5.append(r6)     // Catch:{ all -> 0x012b }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x012b }
            r9.error(r3, r5)     // Catch:{ all -> 0x012b }
        L_0x014b:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r9 = r8.cardBusinessBasic     // Catch:{ all -> 0x012b }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x012b }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x012b }
            r3.<init>(r0)     // Catch:{ all -> 0x012b }
            java.lang.String r0 = "时出现异常"
            r3.append(r0)     // Catch:{ all -> 0x012b }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x012b }
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception     // Catch:{ all -> 0x012b }
            r9.throwExceptionAndClose((java.lang.String) r0, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r3, (boolean) r4)     // Catch:{ all -> 0x012b }
            r2.close()
            r9 = 0
        L_0x0169:
            return r9
        L_0x016a:
            r2.close()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardAppTradeImpl.isLock4Consume(cn.com.fmsh.tsm.business.enums.EnumCardAppType):boolean");
    }

    public MainOrder apply4Pay(int i, int i2, byte[] bArr, EnumCardAppType enumCardAppType) throws BusinessException {
        byte b;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.debug(this.logTag, "订单申请...");
        }
        if (i < 0 || i2 < 0 || enumCardAppType == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("订单申请") + "时，传入的参数异常");
            }
            throw new BusinessException(String.valueOf("订单申请") + "时，传入的参数异常", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.warn(str2, String.valueOf("订单申请") + "时，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("订单申请") + "时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            ApduHandler apduHandler = this.cardBusinessBasic.getApduHandler();
            if (apduHandler == null) {
                if (this.fmLog != null) {
                    FMLog fMLog3 = this.fmLog;
                    String str3 = this.logTag;
                    fMLog3.error(str3, String.valueOf("订单申请") + "时，APDU处理器为空");
                }
                CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
                cardBusinessBasic2.throwExceptionAndClose(String.valueOf("订单申请") + "时，请先切换卡的访问方式(OMA/NFC)", BusinessException.ErrorMessage.local_business_apdu_handler_null, false);
            }
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    FMLog fMLog4 = this.fmLog;
                    String str4 = this.logTag;
                    fMLog4.warn(str4, String.valueOf("订单申请") + "时，消息处理器为空");
                }
                throw new BusinessException(String.valueOf("订单申请") + "时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            Configration configration = this.cardBusinessBasic.getConfigration();
            if (configration != null) {
                byte orderSource = configration.getOrderSource();
                if (orderSource == 0 && this.fmLog != null) {
                    FMLog fMLog5 = this.fmLog;
                    String str5 = this.logTag;
                    fMLog5.warn(str5, String.valueOf("订单申请") + "时，订单来源在配置文件中未定义");
                }
                b = orderSource;
            } else {
                if (this.fmLog != null) {
                    FMLog fMLog6 = this.fmLog;
                    String str6 = this.logTag;
                    fMLog6.warn(str6, String.valueOf("订单申请") + "时，未找到配置文件");
                }
                b = 0;
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.APPLY_ORDER_VER2);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    FMLog fMLog7 = this.fmLog;
                    String str7 = this.logTag;
                    fMLog7.warn(str7, String.valueOf("订单申请") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("订单申请") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("订单申请", server4Business);
            MainOrder apply4PayHandle = apply4PayHandle(b, i, i2, enumCardAppType.getId(), bArr, messageHandler, apduHandler, server4Business);
            this.cardBusinessBasic.businessFinish(false);
            return apply4PayHandle;
        }
    }

    private MainOrder apply4PayHandle(int i, int i2, int i3, int i4, byte[] bArr, IMessageHandler iMessageHandler, ApduHandler apduHandler, String str) throws BusinessException {
        byte[] bArr2;
        IMessage createMessage = iMessageHandler.createMessage((int) Constants.TradeCode.APPLY_ORDER_VER2);
        try {
            ITag createTag = iMessageHandler.createTag((byte) 30);
            createTag.addValue(i);
            createMessage.addTag(createTag);
            ITag createTag2 = iMessageHandler.createTag((byte) 13);
            createTag2.addValue(i3);
            createMessage.addTag(createTag2);
            ITag createTag3 = iMessageHandler.createTag((byte) 14);
            createTag3.addValue(i4);
            createMessage.addTag(createTag3);
            if (bArr != null && bArr.length > 0) {
                ITag createTag4 = iMessageHandler.createTag((byte) 15);
                createTag4.addValue(FM_Bytes.bytesToHexString(bArr));
                createMessage.addTag(createTag4);
            }
            ITag createTag5 = iMessageHandler.createTag((byte) 16);
            createTag5.addValue(FM_Bytes.intToBytes(i2, 4));
            createMessage.addTag(createTag5);
            ITag createTag6 = iMessageHandler.createTag((byte) Constants.TagName.CARD_FORM);
            if (ApduHandler.ApduHandlerType.NFC == apduHandler.getApduHandlerType()) {
                createTag6.addValue(EnumCardIoType.CARD_IO_TYPE_OUT.getId());
            } else if (ApduHandler.ApduHandlerType.BLUETOOTH == apduHandler.getApduHandlerType()) {
                createTag6.addValue(EnumCardIoType.CARD_IO_TYPE_OUT.getId());
            } else {
                createTag6.addValue(EnumCardIoType.CARD_IO_TYPE_IN.getId());
            }
            createMessage.addTag(createTag6);
            bArr2 = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str2 = this.logTag;
                fMLog.warn(str2, String.valueOf("订单申请") + "时，消息处理器出现异常：" + Util4Java.getExceptionInfo(e));
            }
            CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
            cardBusinessBasic2.throwExceptionAndClose(String.valueOf("订单申请") + "时，消息处理器出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr2 = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr2, "订单申请", false, str);
        byte[] bArr3 = new byte[2];
        System.arraycopy(interaction, 0, bArr3, 0, bArr3.length);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, bArr3)) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str3 = this.logTag;
                fMLog2.error(str3, String.valueOf("订单申请") + "时，平台处理失败: " + FM_Bytes.bytesToHexString(interaction));
            }
            CardBusinessBasic cardBusinessBasic3 = this.cardBusinessBasic;
            cardBusinessBasic3.throwExceptionAndClose(String.valueOf("订单申请") + "时，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(bArr3)), false);
        }
        try {
            ITag tag4Id = iMessageHandler.createMessage(Constants.TradeCode.APPLY_ORDER_VER2, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(96);
            if (tag4Id != null) {
                return MainOrder.fromTag(tag4Id);
            }
        } catch (FMCommunicationMessageException e2) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str4 = this.logTag;
                fMLog3.error(str4, String.valueOf("订单申请") + "时，解析平台响应出现异常: " + Util4Java.getExceptionInfo(e2));
            }
            CardBusinessBasic cardBusinessBasic4 = this.cardBusinessBasic;
            cardBusinessBasic4.throwExceptionAndClose(String.valueOf("订单申请") + "时，解析平台响应失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
        }
        return null;
    }

    public MainOrder applyAct4Pay(byte[] bArr, EnumCardAppType enumCardAppType, byte[] bArr2) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "活动订单申请...");
        }
        if (bArr == null || bArr.length < 1) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("活动订单申请") + "时，未传入活动编码");
            }
            throw new BusinessException(String.valueOf("活动订单申请") + "时，传入的参数异常", BusinessException.ErrorMessage.local_business_para_error);
        } else if (bArr2 == null || bArr2.length < 1) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.warn(str2, String.valueOf("活动订单申请") + "时，未传入活动附加数据");
            }
            throw new BusinessException(String.valueOf("活动订单申请") + "时，传入的参数异常", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str3 = this.logTag;
                fMLog3.warn(str3, String.valueOf("活动订单申请") + "时，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("活动订单申请") + "时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    FMLog fMLog4 = this.fmLog;
                    String str4 = this.logTag;
                    fMLog4.warn(str4, String.valueOf("活动订单申请") + "时，消息处理器为空");
                }
                throw new BusinessException(String.valueOf("活动订单申请") + "时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            Configration configration = this.cardBusinessBasic.getConfigration();
            if (configration == null) {
                if (this.fmLog != null) {
                    FMLog fMLog5 = this.fmLog;
                    String str5 = this.logTag;
                    fMLog5.warn(str5, String.valueOf("活动订单申请") + "时，配置信息对象为空");
                }
                throw new BusinessException(String.valueOf("活动订单申请") + "时，配置信息对象为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            byte orderSource = configration.getOrderSource();
            if (orderSource == 0 && this.fmLog != null) {
                FMLog fMLog6 = this.fmLog;
                String str6 = this.logTag;
                fMLog6.warn(str6, String.valueOf("活动订单申请") + "时，订单来源在配置文件中未定义");
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(1142);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    FMLog fMLog7 = this.fmLog;
                    String str7 = this.logTag;
                    fMLog7.warn(str7, String.valueOf("活动订单申请") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("活动订单申请") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("活动订单申请", server4Business);
            MainOrder applyAct4PayHandle = applyAct4PayHandle(orderSource, enumCardAppType.getId(), bArr, bArr2, messageHandler, server4Business);
            this.cardBusinessBasic.businessFinish(false);
            return applyAct4PayHandle;
        }
    }

    private MainOrder applyAct4PayHandle(int i, int i2, byte[] bArr, byte[] bArr2, IMessageHandler iMessageHandler, String str) throws BusinessException {
        byte[] bArr3;
        IMessage createMessage = iMessageHandler.createMessage(1142);
        try {
            ITag createTag = iMessageHandler.createTag((byte) 30);
            createTag.addValue(i);
            createMessage.addTag(createTag);
            ITag createTag2 = iMessageHandler.createTag((byte) 14);
            createTag2.addValue(i2);
            createMessage.addTag(createTag2);
            if (bArr != null && bArr.length > 0) {
                ITag createTag3 = iMessageHandler.createTag((byte) Constants.TagName.ACTIVITY_INFO);
                createTag3.addValue(bArr);
                createMessage.addTag(createTag3);
            }
            ITag createTag4 = iMessageHandler.createTag((byte) Constants.TagName.PATCH_DATA);
            createTag4.addValue(bArr2);
            createMessage.addTag(createTag4);
            bArr3 = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str2 = this.logTag;
                fMLog.warn(str2, String.valueOf("活动订单申请") + "时，消息处理器出现异常：" + Util4Java.getExceptionInfo(e));
            }
            CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
            cardBusinessBasic2.throwExceptionAndClose(String.valueOf("活动订单申请") + "时，消息处理器出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr3 = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr3, "活动订单申请", false, str);
        byte[] bArr4 = new byte[2];
        System.arraycopy(interaction, 0, bArr4, 0, bArr4.length);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, bArr4)) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str3 = this.logTag;
                fMLog2.error(str3, String.valueOf("活动订单申请") + "时，平台处理失败: " + FM_Bytes.bytesToHexString(interaction));
            }
            CardBusinessBasic cardBusinessBasic3 = this.cardBusinessBasic;
            cardBusinessBasic3.throwExceptionAndClose(String.valueOf("活动订单申请") + "时，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(bArr4)), false);
        }
        try {
            ITag tag4Id = iMessageHandler.createMessage(Constants.TradeCode.APPLY_ORDER_VER2, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(96);
            if (tag4Id != null) {
                return MainOrder.fromTag(tag4Id);
            }
        } catch (FMCommunicationMessageException e2) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str4 = this.logTag;
                fMLog3.error(str4, String.valueOf("活动订单申请") + "时，解析平台响应出现异常: " + Util4Java.getExceptionInfo(e2));
            }
            CardBusinessBasic cardBusinessBasic4 = this.cardBusinessBasic;
            cardBusinessBasic4.throwExceptionAndClose(String.valueOf("活动订单申请") + "时，解析平台响应失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
        }
        return null;
    }

    public boolean remoteRecharge(byte[] bArr, byte[] bArr2) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "卡上应用圈存...");
        }
        if (bArr == null || bArr.length < 1) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("卡上应用圈存") + "时，传入的订单编号为空");
            }
            throw new BusinessException(String.valueOf("卡上应用圈存") + "时，传入的订单编号为空", BusinessException.ErrorMessage.local_business_para_error);
        } else if (bArr2 == null || bArr2.length < 1) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.warn(str2, String.valueOf("卡上应用圈存") + "时，传入的卡应用序列号为空");
            }
            throw new BusinessException(String.valueOf("卡上应用圈存") + "时，传入的卡应用序列号为空", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str3 = this.logTag;
                fMLog3.warn(str3, String.valueOf("卡上应用圈存") + "时，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("卡上应用圈存") + "时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    FMLog fMLog4 = this.fmLog;
                    String str4 = this.logTag;
                    fMLog4.warn(str4, String.valueOf("卡上应用圈存") + "时，消息处理器为空");
                }
                throw new BusinessException(String.valueOf("卡上应用圈存") + "时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(3011);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    FMLog fMLog5 = this.fmLog;
                    String str5 = this.logTag;
                    fMLog5.warn(str5, String.valueOf("卡上应用圈存") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("卡上应用圈存") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("卡上应用圈存", server4Business);
            return remoteRechargeVer2(bArr, bArr2, messageHandler, server4Business);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:80:0x0243  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0269  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x029f A[Catch:{ FMCommunicationMessageException -> 0x02f6, FMScriptHandleException -> 0x02cc }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean remoteRechargeVer2(byte[] r17, byte[] r18, cn.com.fmsh.communication.message.IMessageHandler r19, java.lang.String r20) throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r16 = this;
            r1 = r16
            r0 = r18
            r2 = r19
            r3 = r20
            java.lang.String r4 = "卡上应用圈存"
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r5 = r1.cardBusinessBasic
            cn.com.fmsh.script.ApduHandler r5 = r5.getApduHandler()
            r6 = 0
            if (r5 != 0) goto L_0x004c
            cn.com.fmsh.util.log.FMLog r7 = r1.fmLog
            if (r7 == 0) goto L_0x0032
            cn.com.fmsh.util.log.FMLog r7 = r1.fmLog
            java.lang.String r8 = r1.logTag
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = java.lang.String.valueOf(r4)
            r9.<init>(r10)
            java.lang.String r10 = "时，APDU处理器为空"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.error(r8, r9)
        L_0x0032:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r7 = r1.cardBusinessBasic
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = java.lang.String.valueOf(r4)
            r8.<init>(r9)
            java.lang.String r9 = "时，请先切换卡的访问方式(OMA/NFC)"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r9 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_null
            r7.throwExceptionAndClose((java.lang.String) r8, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r9, (boolean) r6)
        L_0x004c:
            boolean r7 = r5.isConnect()
            r8 = 1
            if (r7 == 0) goto L_0x008c
            cn.com.fmsh.util.log.FMLog r7 = r1.fmLog
            if (r7 == 0) goto L_0x0071
            cn.com.fmsh.util.log.FMLog r7 = r1.fmLog
            java.lang.String r9 = r1.logTag
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = java.lang.String.valueOf(r4)
            r10.<init>(r11)
            java.lang.String r11 = "时，APDU处理器正忙"
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            r7.error(r9, r10)
        L_0x0071:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r7 = r1.cardBusinessBasic
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = java.lang.String.valueOf(r4)
            r9.<init>(r10)
            java.lang.String r10 = "时，APDU处理器正忙"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r10 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_busying
            r7.throwExceptionAndClose((java.lang.String) r9, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r10, (boolean) r6)
            goto L_0x00ca
        L_0x008c:
            boolean r7 = r5.connect()
            if (r7 != 0) goto L_0x00ca
            cn.com.fmsh.util.log.FMLog r7 = r1.fmLog
            if (r7 == 0) goto L_0x00b0
            cn.com.fmsh.util.log.FMLog r7 = r1.fmLog
            java.lang.String r9 = r1.logTag
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = java.lang.String.valueOf(r4)
            r10.<init>(r11)
            java.lang.String r11 = "时，连接卡失败"
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            r7.error(r9, r10)
        L_0x00b0:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r7 = r1.cardBusinessBasic
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = java.lang.String.valueOf(r4)
            r9.<init>(r10)
            java.lang.String r10 = "时，连接卡失败"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r10 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception
            r7.throwExceptionAndClose((java.lang.String) r9, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r10, (boolean) r8)
        L_0x00ca:
            r7 = 3011(0xbc3, float:4.22E-42)
            cn.com.fmsh.communication.message.IMessage r7 = r2.createMessage((int) r7)
            r9 = 17
            r10 = 0
            cn.com.fmsh.communication.message.ITag r9 = r2.createTag((byte) r9)     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            r11 = r17
            r9.addValue((byte[]) r11)     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            r7.addTag(r9)     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            r9 = 15
            cn.com.fmsh.communication.message.ITag r9 = r2.createTag((byte) r9)     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            if (r0 == 0) goto L_0x00ea
            int r11 = r0.length     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            if (r11 >= r8) goto L_0x00ee
        L_0x00ea:
            r0 = 8
            byte[] r0 = new byte[r0]     // Catch:{ FMCommunicationMessageException -> 0x0133 }
        L_0x00ee:
            java.lang.String r0 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r0)     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            r9.addValue((java.lang.String) r0)     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            r7.addTag(r9)     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            r0 = 47
            cn.com.fmsh.communication.message.ITag r0 = r2.createTag((byte) r0)     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            cn.com.fmsh.script.ApduHandler$ApduHandlerType r9 = cn.com.fmsh.script.ApduHandler.ApduHandlerType.NFC     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            cn.com.fmsh.script.ApduHandler$ApduHandlerType r11 = r5.getApduHandlerType()     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            if (r9 != r11) goto L_0x0110
            cn.com.fmsh.tsm.business.enums.EnumCardIoType r5 = cn.com.fmsh.tsm.business.enums.EnumCardIoType.CARD_IO_TYPE_OUT     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            int r5 = r5.getId()     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            r0.addValue((int) r5)     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            goto L_0x012b
        L_0x0110:
            cn.com.fmsh.script.ApduHandler$ApduHandlerType r9 = cn.com.fmsh.script.ApduHandler.ApduHandlerType.BLUETOOTH     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            cn.com.fmsh.script.ApduHandler$ApduHandlerType r5 = r5.getApduHandlerType()     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            if (r9 != r5) goto L_0x0122
            cn.com.fmsh.tsm.business.enums.EnumCardIoType r5 = cn.com.fmsh.tsm.business.enums.EnumCardIoType.CARD_IO_TYPE_OUT     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            int r5 = r5.getId()     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            r0.addValue((int) r5)     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            goto L_0x012b
        L_0x0122:
            cn.com.fmsh.tsm.business.enums.EnumCardIoType r5 = cn.com.fmsh.tsm.business.enums.EnumCardIoType.CARD_IO_TYPE_IN     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            int r5 = r5.getId()     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            r0.addValue((int) r5)     // Catch:{ FMCommunicationMessageException -> 0x0133 }
        L_0x012b:
            r7.addTag(r0)     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            byte[] r0 = r7.toBytes()     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            goto L_0x0174
        L_0x0133:
            r0 = move-exception
            cn.com.fmsh.util.log.FMLog r5 = r1.fmLog
            if (r5 == 0) goto L_0x0159
            cn.com.fmsh.util.log.FMLog r5 = r1.fmLog
            java.lang.String r7 = r1.logTag
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r11 = java.lang.String.valueOf(r4)
            r9.<init>(r11)
            java.lang.String r11 = "时，构造平台请求数据异常："
            r9.append(r11)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r9.append(r0)
            java.lang.String r0 = r9.toString()
            r5.warn(r7, r0)
        L_0x0159:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r7 = java.lang.String.valueOf(r4)
            r5.<init>(r7)
            java.lang.String r7 = "时，构造平台请求数据失败"
            r5.append(r7)
            java.lang.String r5 = r5.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r7 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r0.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r7, (boolean) r8)
            r0 = r10
        L_0x0174:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r5 = r1.cardBusinessBasic
            byte[] r5 = r5.interaction(r0, r4, r8, r3)
            r7 = 2
            byte[] r9 = java.util.Arrays.copyOf(r5, r7)
            r14 = r0
            r11 = r10
            r12 = r11
            r13 = r12
        L_0x0183:
            byte[] r0 = cn.com.fmsh.tsm.business.constants.Constants.RespCodeonse4Platform.CARD_REQUEST
            boolean r0 = java.util.Arrays.equals(r0, r9)
            if (r0 != 0) goto L_0x021d
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            r0.businessFinish(r8)
            byte[] r0 = cn.com.fmsh.tsm.business.constants.Constants.RespCodeonse4Platform.SUCESS
            boolean r0 = java.util.Arrays.equals(r0, r9)
            if (r0 == 0) goto L_0x0199
            return r8
        L_0x0199:
            byte[] r0 = cn.com.fmsh.tsm.business.constants.Constants.RespCodeonse4Platform.CARD_REQUEST
            boolean r0 = java.util.Arrays.equals(r0, r9)
            if (r0 != 0) goto L_0x021c
            if (r10 == 0) goto L_0x01d7
            cn.com.fmsh.script.bean.ApduRequest[] r0 = r10.getApduRequests()
            int r2 = r0.length
            r3 = 0
        L_0x01a9:
            if (r3 < r2) goto L_0x01ac
            goto L_0x01d7
        L_0x01ac:
            r5 = r0[r3]
            if (r5 == 0) goto L_0x01d4
            byte[] r7 = r5.getApdu()
            if (r7 == 0) goto L_0x01d4
            byte[] r7 = r5.getApdu()
            int r7 = r7.length
            byte[] r10 = cn.com.fmsh.tsm.business.constants.Constants.Command.UPDATE_VALID_DATE
            int r10 = r10.length
            if (r7 < r10) goto L_0x01d4
            byte[] r5 = r5.getApdu()
            byte[] r7 = cn.com.fmsh.tsm.business.constants.Constants.Command.UPDATE_VALID_DATE
            int r7 = r7.length
            byte[] r5 = java.util.Arrays.copyOf(r5, r7)
            byte[] r7 = cn.com.fmsh.tsm.business.constants.Constants.Command.UPDATE_VALID_DATE
            boolean r5 = java.util.Arrays.equals(r7, r5)
            if (r5 == 0) goto L_0x01d4
            return r8
        L_0x01d4:
            int r3 = r3 + 1
            goto L_0x01a9
        L_0x01d7:
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x01fc
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r2 = r1.logTag
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = java.lang.String.valueOf(r4)
            r3.<init>(r5)
            java.lang.String r5 = "时，(圈存初始化)平台响应错误响应码: "
            r3.append(r5)
            java.lang.String r5 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r9)
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            r0.error(r2, r3)
        L_0x01fc:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = java.lang.String.valueOf(r4)
            r2.<init>(r3)
            java.lang.String r3 = "时，平台处理失败"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r9)
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.instance(r3)
            r0.throwExceptionAndClose((java.lang.String) r2, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r3, (boolean) r6)
        L_0x021c:
            return r8
        L_0x021d:
            cn.com.fmsh.communication.message.IMessage r0 = r2.createMessage((byte[]) r5)     // Catch:{ FMCommunicationMessageException -> 0x023e }
            r5 = -90
            cn.com.fmsh.communication.message.ITag r5 = r0.getTag4Id(r5)     // Catch:{ FMCommunicationMessageException -> 0x023e }
            r9 = -89
            cn.com.fmsh.communication.message.ITag r9 = r0.getTag4Id(r9)     // Catch:{ FMCommunicationMessageException -> 0x023b }
            r10 = -95
            cn.com.fmsh.communication.message.ITag r0 = r0.getTag4Id(r10)     // Catch:{ FMCommunicationMessageException -> 0x0237 }
            r11 = r0
            r12 = r5
            r13 = r9
            goto L_0x0267
        L_0x0237:
            r0 = move-exception
            r12 = r5
            r13 = r9
            goto L_0x023f
        L_0x023b:
            r0 = move-exception
            r12 = r5
            goto L_0x023f
        L_0x023e:
            r0 = move-exception
        L_0x023f:
            cn.com.fmsh.util.log.FMLog r5 = r1.fmLog
            if (r5 == 0) goto L_0x025d
            cn.com.fmsh.util.log.FMLog r5 = r1.fmLog
            java.lang.String r9 = r1.logTag
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r15 = "卡上应用圈，解析平台响应出现异常: "
            r10.<init>(r15)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r10.append(r0)
            java.lang.String r0 = r10.toString()
            r5.error(r9, r0)
        L_0x025d:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r5 = "卡上应用圈存，解析平台响应失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r9 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r0.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r9, (boolean) r8)
        L_0x0267:
            if (r11 != 0) goto L_0x0281
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x0277
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r5 = r1.logTag
            java.lang.String r9 = "卡上应用圈存，平台响应数据无执行脚本（A1）"
            r0.error(r5, r9)
        L_0x0277:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r5 = "卡上应用圈存，平台响应数据无执行脚本"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r9 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r0.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r9, (boolean) r8)
        L_0x0281:
            cn.com.fmsh.script.bean.ApduRequestList r10 = new cn.com.fmsh.script.bean.ApduRequestList
            r10.<init>()
            r10.fromTag(r11)     // Catch:{ FMCommunicationMessageException -> 0x02f6, FMScriptHandleException -> 0x02cc }
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic     // Catch:{ FMCommunicationMessageException -> 0x02f6, FMScriptHandleException -> 0x02cc }
            cn.com.fmsh.script.ScriptHandler r0 = r0.getScriptHandler()     // Catch:{ FMCommunicationMessageException -> 0x02f6, FMScriptHandleException -> 0x02cc }
            cn.com.fmsh.script.bean.ApduReponseList r0 = r0.execute((cn.com.fmsh.script.bean.ApduRequestList) r10)     // Catch:{ FMCommunicationMessageException -> 0x02f6, FMScriptHandleException -> 0x02cc }
            if (r0 == 0) goto L_0x029b
            int r5 = r0.size()     // Catch:{ FMCommunicationMessageException -> 0x02f6, FMScriptHandleException -> 0x02cc }
            if (r5 >= r8) goto L_0x02b3
        L_0x029b:
            cn.com.fmsh.util.log.FMLog r5 = r1.fmLog     // Catch:{ FMCommunicationMessageException -> 0x02f6, FMScriptHandleException -> 0x02cc }
            if (r5 == 0) goto L_0x02a9
            cn.com.fmsh.util.log.FMLog r5 = r1.fmLog     // Catch:{ FMCommunicationMessageException -> 0x02f6, FMScriptHandleException -> 0x02cc }
            java.lang.String r9 = r1.logTag     // Catch:{ FMCommunicationMessageException -> 0x02f6, FMScriptHandleException -> 0x02cc }
            java.lang.String r15 = "卡上应用圈存，脚本执行结果为空"
            r5.error(r9, r15)     // Catch:{ FMCommunicationMessageException -> 0x02f6, FMScriptHandleException -> 0x02cc }
        L_0x02a9:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r5 = r1.cardBusinessBasic     // Catch:{ FMCommunicationMessageException -> 0x02f6, FMScriptHandleException -> 0x02cc }
            java.lang.String r9 = "卡上应用圈存，脚本执行失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r15 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception     // Catch:{ FMCommunicationMessageException -> 0x02f6, FMScriptHandleException -> 0x02cc }
            r5.throwExceptionAndClose((java.lang.String) r9, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r15, (boolean) r8)     // Catch:{ FMCommunicationMessageException -> 0x02f6, FMScriptHandleException -> 0x02cc }
        L_0x02b3:
            r5 = 9001(0x2329, float:1.2613E-41)
            cn.com.fmsh.communication.message.IMessage r5 = r2.createMessage((int) r5)     // Catch:{ FMCommunicationMessageException -> 0x02f6, FMScriptHandleException -> 0x02cc }
            r5.addTag(r12)     // Catch:{ FMCommunicationMessageException -> 0x02f6, FMScriptHandleException -> 0x02cc }
            r5.addTag(r13)     // Catch:{ FMCommunicationMessageException -> 0x02f6, FMScriptHandleException -> 0x02cc }
            cn.com.fmsh.communication.message.ITag r0 = r0.toTag4A3()     // Catch:{ FMCommunicationMessageException -> 0x02f6, FMScriptHandleException -> 0x02cc }
            r5.addTag(r0)     // Catch:{ FMCommunicationMessageException -> 0x02f6, FMScriptHandleException -> 0x02cc }
            byte[] r0 = r5.toBytes()     // Catch:{ FMCommunicationMessageException -> 0x02f6, FMScriptHandleException -> 0x02cc }
            r14 = r0
            goto L_0x031f
        L_0x02cc:
            r0 = move-exception
            cn.com.fmsh.util.log.FMLog r5 = r1.fmLog
            if (r5 == 0) goto L_0x02eb
            cn.com.fmsh.util.log.FMLog r5 = r1.fmLog
            java.lang.String r9 = r1.logTag
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            java.lang.String r6 = "卡上应用圈存，脚本执行出现异常："
            r15.<init>(r6)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r15.append(r0)
            java.lang.String r0 = r15.toString()
            r5.error(r9, r0)
        L_0x02eb:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r5 = "卡上应用圈存，脚本执行失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception
            r0.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r8)
            goto L_0x031f
        L_0x02f6:
            r0 = move-exception
            cn.com.fmsh.util.log.FMLog r5 = r1.fmLog
            if (r5 == 0) goto L_0x0315
            cn.com.fmsh.util.log.FMLog r5 = r1.fmLog
            java.lang.String r6 = r1.logTag
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r15 = "卡上应用圈存，平台响应数据解析出现异常: "
            r9.<init>(r15)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r9.append(r0)
            java.lang.String r0 = r9.toString()
            r5.error(r6, r0)
        L_0x0315:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r5 = "卡上应用圈存，平台响应数据解析失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r0.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r8)
        L_0x031f:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r5 = "卡上应用圈存时"
            byte[] r5 = r0.interaction(r14, r5, r8, r3)
            byte[] r9 = java.util.Arrays.copyOf(r5, r7)
            r6 = 0
            goto L_0x0183
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardAppTradeImpl.remoteRechargeVer2(byte[], byte[], cn.com.fmsh.communication.message.IMessageHandler, java.lang.String):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:196:0x0541, code lost:
        r1.cardBusinessBasic.businessFinish(true);
        r0 = r5;
     */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x034f  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0376  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x03a2 A[Catch:{ FMCommunicationMessageException -> 0x0406, FMScriptHandleException -> 0x03dc }] */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x03b0 A[Catch:{ FMCommunicationMessageException -> 0x0406, FMScriptHandleException -> 0x03dc }] */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x0445  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x044b  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x01f8  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x021f  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0257 A[Catch:{ FMCommunicationMessageException -> 0x02c5, FMScriptHandleException -> 0x0282 }] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0303  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean remoteRecharge(byte[] r17, byte[] r18, cn.com.fmsh.communication.message.IMessageHandler r19, java.lang.String r20) throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r16 = this;
            r1 = r16
            r0 = r18
            r2 = r19
            r3 = r20
            java.lang.String r4 = "卡上应用圈存"
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r5 = r1.cardBusinessBasic
            cn.com.fmsh.script.ApduHandler r5 = r5.getApduHandler()
            r6 = 0
            if (r5 != 0) goto L_0x004c
            cn.com.fmsh.util.log.FMLog r7 = r1.fmLog
            if (r7 == 0) goto L_0x0032
            cn.com.fmsh.util.log.FMLog r7 = r1.fmLog
            java.lang.String r8 = r1.logTag
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = java.lang.String.valueOf(r4)
            r9.<init>(r10)
            java.lang.String r10 = "时，APDU处理器为空"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r7.error(r8, r9)
        L_0x0032:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r7 = r1.cardBusinessBasic
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = java.lang.String.valueOf(r4)
            r8.<init>(r9)
            java.lang.String r9 = "时，请先切换卡的访问方式(OMA/NFC)"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r9 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_null
            r7.throwExceptionAndClose((java.lang.String) r8, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r9, (boolean) r6)
        L_0x004c:
            boolean r7 = r5.isConnect()
            r8 = 1
            if (r7 == 0) goto L_0x008c
            cn.com.fmsh.util.log.FMLog r7 = r1.fmLog
            if (r7 == 0) goto L_0x0071
            cn.com.fmsh.util.log.FMLog r7 = r1.fmLog
            java.lang.String r9 = r1.logTag
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = java.lang.String.valueOf(r4)
            r10.<init>(r11)
            java.lang.String r11 = "时，APDU处理器正忙"
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            r7.error(r9, r10)
        L_0x0071:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r7 = r1.cardBusinessBasic
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = java.lang.String.valueOf(r4)
            r9.<init>(r10)
            java.lang.String r10 = "时，APDU处理器正忙"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r10 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_busying
            r7.throwExceptionAndClose((java.lang.String) r9, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r10, (boolean) r6)
            goto L_0x00ca
        L_0x008c:
            boolean r7 = r5.connect()
            if (r7 != 0) goto L_0x00ca
            cn.com.fmsh.util.log.FMLog r7 = r1.fmLog
            if (r7 == 0) goto L_0x00b0
            cn.com.fmsh.util.log.FMLog r7 = r1.fmLog
            java.lang.String r9 = r1.logTag
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = java.lang.String.valueOf(r4)
            r10.<init>(r11)
            java.lang.String r11 = "时，连接卡失败"
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            r7.error(r9, r10)
        L_0x00b0:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r7 = r1.cardBusinessBasic
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = java.lang.String.valueOf(r4)
            r9.<init>(r10)
            java.lang.String r10 = "时，连接卡失败"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r10 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception
            r7.throwExceptionAndClose((java.lang.String) r9, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r10, (boolean) r8)
        L_0x00ca:
            r7 = 3011(0xbc3, float:4.22E-42)
            cn.com.fmsh.communication.message.IMessage r7 = r2.createMessage((int) r7)
            r9 = 17
            cn.com.fmsh.communication.message.ITag r9 = r2.createTag((byte) r9)     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            r11 = r17
            r9.addValue((byte[]) r11)     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            r7.addTag(r9)     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            r9 = 15
            cn.com.fmsh.communication.message.ITag r9 = r2.createTag((byte) r9)     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            if (r0 == 0) goto L_0x00e9
            int r11 = r0.length     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            if (r11 >= r8) goto L_0x00ed
        L_0x00e9:
            r0 = 8
            byte[] r0 = new byte[r0]     // Catch:{ FMCommunicationMessageException -> 0x0133 }
        L_0x00ed:
            java.lang.String r0 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r0)     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            r9.addValue((java.lang.String) r0)     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            r7.addTag(r9)     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            r0 = 47
            cn.com.fmsh.communication.message.ITag r0 = r2.createTag((byte) r0)     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            cn.com.fmsh.script.ApduHandler$ApduHandlerType r9 = cn.com.fmsh.script.ApduHandler.ApduHandlerType.NFC     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            cn.com.fmsh.script.ApduHandler$ApduHandlerType r11 = r5.getApduHandlerType()     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            if (r9 != r11) goto L_0x010f
            cn.com.fmsh.tsm.business.enums.EnumCardIoType r5 = cn.com.fmsh.tsm.business.enums.EnumCardIoType.CARD_IO_TYPE_OUT     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            int r5 = r5.getId()     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            r0.addValue((int) r5)     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            goto L_0x012a
        L_0x010f:
            cn.com.fmsh.script.ApduHandler$ApduHandlerType r9 = cn.com.fmsh.script.ApduHandler.ApduHandlerType.BLUETOOTH     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            cn.com.fmsh.script.ApduHandler$ApduHandlerType r5 = r5.getApduHandlerType()     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            if (r9 != r5) goto L_0x0121
            cn.com.fmsh.tsm.business.enums.EnumCardIoType r5 = cn.com.fmsh.tsm.business.enums.EnumCardIoType.CARD_IO_TYPE_OUT     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            int r5 = r5.getId()     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            r0.addValue((int) r5)     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            goto L_0x012a
        L_0x0121:
            cn.com.fmsh.tsm.business.enums.EnumCardIoType r5 = cn.com.fmsh.tsm.business.enums.EnumCardIoType.CARD_IO_TYPE_IN     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            int r5 = r5.getId()     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            r0.addValue((int) r5)     // Catch:{ FMCommunicationMessageException -> 0x0133 }
        L_0x012a:
            r7.addTag(r0)     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            byte[] r0 = r7.toBytes()     // Catch:{ FMCommunicationMessageException -> 0x0133 }
            r5 = r0
            goto L_0x0174
        L_0x0133:
            r0 = move-exception
            cn.com.fmsh.util.log.FMLog r5 = r1.fmLog
            if (r5 == 0) goto L_0x0159
            cn.com.fmsh.util.log.FMLog r5 = r1.fmLog
            java.lang.String r7 = r1.logTag
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r11 = java.lang.String.valueOf(r4)
            r9.<init>(r11)
            java.lang.String r11 = "时，构造平台请求数据异常："
            r9.append(r11)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r9.append(r0)
            java.lang.String r0 = r9.toString()
            r5.warn(r7, r0)
        L_0x0159:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r7 = java.lang.String.valueOf(r4)
            r5.<init>(r7)
            java.lang.String r7 = "时，构造平台请求数据失败"
            r5.append(r7)
            java.lang.String r5 = r5.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r7 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r0.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r7, (boolean) r8)
            r5 = 0
        L_0x0174:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            byte[] r0 = r0.interaction(r5, r4, r8, r3)
            r7 = 2
            byte[] r9 = java.util.Arrays.copyOf(r0, r7)
            byte[] r11 = cn.com.fmsh.tsm.business.constants.Constants.RespCodeonse4Platform.SUCESS
            boolean r11 = java.util.Arrays.equals(r11, r9)
            if (r11 == 0) goto L_0x0188
            return r8
        L_0x0188:
            byte[] r11 = cn.com.fmsh.tsm.business.constants.Constants.RespCodeonse4Platform.CARD_REQUEST
            boolean r11 = java.util.Arrays.equals(r11, r9)
            if (r11 != 0) goto L_0x01d5
            cn.com.fmsh.util.log.FMLog r11 = r1.fmLog
            if (r11 == 0) goto L_0x01b5
            cn.com.fmsh.util.log.FMLog r11 = r1.fmLog
            java.lang.String r12 = r1.logTag
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r14 = java.lang.String.valueOf(r4)
            r13.<init>(r14)
            java.lang.String r14 = "时，(圈存初始化)平台响应错误响应码: "
            r13.append(r14)
            java.lang.String r14 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r9)
            r13.append(r14)
            java.lang.String r13 = r13.toString()
            r11.error(r12, r13)
        L_0x01b5:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r11 = r1.cardBusinessBasic
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r12.<init>(r4)
            java.lang.String r4 = "时，平台处理失败"
            r12.append(r4)
            java.lang.String r4 = r12.toString()
            java.lang.String r9 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r9)
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r9 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.instance(r9)
            r11.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r9, (boolean) r8)
        L_0x01d5:
            r4 = -95
            r9 = -89
            r11 = -90
            cn.com.fmsh.communication.message.IMessage r0 = r2.createMessage((byte[]) r0)     // Catch:{ FMCommunicationMessageException -> 0x01f1 }
            cn.com.fmsh.communication.message.ITag r12 = r0.getTag4Id(r11)     // Catch:{ FMCommunicationMessageException -> 0x01f1 }
            cn.com.fmsh.communication.message.ITag r13 = r0.getTag4Id(r9)     // Catch:{ FMCommunicationMessageException -> 0x01ef }
            cn.com.fmsh.communication.message.ITag r0 = r0.getTag4Id(r4)     // Catch:{ FMCommunicationMessageException -> 0x01ed }
            r6 = r0
            goto L_0x021d
        L_0x01ed:
            r0 = move-exception
            goto L_0x01f4
        L_0x01ef:
            r0 = move-exception
            goto L_0x01f3
        L_0x01f1:
            r0 = move-exception
            r12 = 0
        L_0x01f3:
            r13 = 0
        L_0x01f4:
            cn.com.fmsh.util.log.FMLog r14 = r1.fmLog
            if (r14 == 0) goto L_0x0212
            cn.com.fmsh.util.log.FMLog r14 = r1.fmLog
            java.lang.String r15 = r1.logTag
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r6 = "卡上应用圈存初始化，解析平台响应出现异常: "
            r10.<init>(r6)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r10.append(r0)
            java.lang.String r0 = r10.toString()
            r14.error(r15, r0)
        L_0x0212:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r6 = "卡上应用圈存初始化，解析平台响应失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r10 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r0.throwExceptionAndClose((java.lang.String) r6, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r10, (boolean) r8)
            r6 = 0
        L_0x021d:
            if (r6 != 0) goto L_0x0237
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x022d
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r10 = r1.logTag
            java.lang.String r14 = "卡上应用圈存初始化，平台响应数据无执行脚本（A1）"
            r0.error(r10, r14)
        L_0x022d:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r10 = "卡上应用圈存初始化，平台响应数据无执行脚本"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r14 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r0.throwExceptionAndClose((java.lang.String) r10, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r14, (boolean) r8)
        L_0x0237:
            cn.com.fmsh.script.bean.ApduRequestList r0 = new cn.com.fmsh.script.bean.ApduRequestList
            r0.<init>()
            r10 = 9001(0x2329, float:1.2613E-41)
            r0.fromTag(r6)     // Catch:{ FMCommunicationMessageException -> 0x02c5, FMScriptHandleException -> 0x0282 }
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r14 = r1.cardBusinessBasic     // Catch:{ FMCommunicationMessageException -> 0x02c5, FMScriptHandleException -> 0x0282 }
            cn.com.fmsh.script.ScriptHandler r14 = r14.getScriptHandler()     // Catch:{ FMCommunicationMessageException -> 0x02c5, FMScriptHandleException -> 0x0282 }
            cn.com.fmsh.script.bean.ApduReponseList r0 = r14.execute((cn.com.fmsh.script.bean.ApduRequestList) r0)     // Catch:{ FMCommunicationMessageException -> 0x02c5, FMScriptHandleException -> 0x0282 }
            if (r0 == 0) goto L_0x0253
            int r14 = r0.size()     // Catch:{ FMCommunicationMessageException -> 0x02c5, FMScriptHandleException -> 0x0282 }
            if (r14 >= r8) goto L_0x026b
        L_0x0253:
            cn.com.fmsh.util.log.FMLog r14 = r1.fmLog     // Catch:{ FMCommunicationMessageException -> 0x02c5, FMScriptHandleException -> 0x0282 }
            if (r14 == 0) goto L_0x0261
            cn.com.fmsh.util.log.FMLog r14 = r1.fmLog     // Catch:{ FMCommunicationMessageException -> 0x02c5, FMScriptHandleException -> 0x0282 }
            java.lang.String r15 = r1.logTag     // Catch:{ FMCommunicationMessageException -> 0x02c5, FMScriptHandleException -> 0x0282 }
            java.lang.String r4 = "卡上应用圈存初始化，脚本执行结果为空"
            r14.error(r15, r4)     // Catch:{ FMCommunicationMessageException -> 0x02c5, FMScriptHandleException -> 0x0282 }
        L_0x0261:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r4 = r1.cardBusinessBasic     // Catch:{ FMCommunicationMessageException -> 0x02c5, FMScriptHandleException -> 0x0282 }
            java.lang.String r14 = "卡上应用圈存初始化，脚本执行失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r15 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception     // Catch:{ FMCommunicationMessageException -> 0x02c5, FMScriptHandleException -> 0x0282 }
            r4.throwExceptionAndClose((java.lang.String) r14, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r15, (boolean) r8)     // Catch:{ FMCommunicationMessageException -> 0x02c5, FMScriptHandleException -> 0x0282 }
        L_0x026b:
            cn.com.fmsh.communication.message.IMessage r4 = r2.createMessage((int) r10)     // Catch:{ FMCommunicationMessageException -> 0x02c5, FMScriptHandleException -> 0x0282 }
            r4.addTag(r12)     // Catch:{ FMCommunicationMessageException -> 0x02c5, FMScriptHandleException -> 0x0282 }
            r4.addTag(r13)     // Catch:{ FMCommunicationMessageException -> 0x02c5, FMScriptHandleException -> 0x0282 }
            cn.com.fmsh.communication.message.ITag r0 = r0.toTag4A3()     // Catch:{ FMCommunicationMessageException -> 0x02c5, FMScriptHandleException -> 0x0282 }
            r4.addTag(r0)     // Catch:{ FMCommunicationMessageException -> 0x02c5, FMScriptHandleException -> 0x0282 }
            byte[] r0 = r4.toBytes()     // Catch:{ FMCommunicationMessageException -> 0x02c5, FMScriptHandleException -> 0x0282 }
            r5 = r0
            goto L_0x02ee
        L_0x0282:
            r0 = move-exception
            cn.com.fmsh.util.log.FMLog r4 = r1.fmLog
            if (r4 == 0) goto L_0x02a1
            cn.com.fmsh.util.log.FMLog r4 = r1.fmLog
            java.lang.String r14 = r1.logTag
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            java.lang.String r10 = "卡上应用圈存初始化，脚本执行出现异常："
            r15.<init>(r10)
            java.lang.String r10 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r15.append(r10)
            java.lang.String r10 = r15.toString()
            r4.error(r14, r10)
        L_0x02a1:
            cn.com.fmsh.script.exception.FMScriptHandleException$ScriptHandleExceptionType r4 = r0.getType()
            if (r4 == 0) goto L_0x02ba
            cn.com.fmsh.script.exception.FMScriptHandleException$ScriptHandleExceptionType r4 = cn.com.fmsh.script.exception.FMScriptHandleException.ScriptHandleExceptionType.OPENMOBILE_TRANSMIT_EXCEPTION
            cn.com.fmsh.script.exception.FMScriptHandleException$ScriptHandleExceptionType r0 = r0.getType()
            if (r4 != r0) goto L_0x02ba
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r4 = "卡上应用圈存初始化，脚本执行失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r10 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_open_mobile_exception
            r0.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r10, (boolean) r8)
            goto L_0x02ee
        L_0x02ba:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r4 = "卡上应用圈存初始化，脚本执行失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r10 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception
            r0.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r10, (boolean) r8)
            goto L_0x02ee
        L_0x02c5:
            r0 = move-exception
            cn.com.fmsh.util.log.FMLog r4 = r1.fmLog
            if (r4 == 0) goto L_0x02e4
            cn.com.fmsh.util.log.FMLog r4 = r1.fmLog
            java.lang.String r10 = r1.logTag
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            java.lang.String r15 = "卡上应用圈存初始化，平台响应数据解析出现异常: "
            r14.<init>(r15)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r14.append(r0)
            java.lang.String r0 = r14.toString()
            r4.error(r10, r0)
        L_0x02e4:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r4 = "卡上应用圈存初始化，平台响应数据解析失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r10 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r0.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r10, (boolean) r8)
        L_0x02ee:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r4 = "卡上应用圈存初始化"
            byte[] r0 = r0.interaction(r5, r4, r8, r3)
            byte[] r4 = java.util.Arrays.copyOf(r0, r7)
            byte[] r10 = cn.com.fmsh.tsm.business.constants.Constants.RespCodeonse4Platform.CARD_REQUEST
            boolean r10 = java.util.Arrays.equals(r10, r4)
            if (r10 != 0) goto L_0x0331
            cn.com.fmsh.util.log.FMLog r10 = r1.fmLog
            if (r10 == 0) goto L_0x0321
            cn.com.fmsh.util.log.FMLog r10 = r1.fmLog
            java.lang.String r14 = r1.logTag
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            java.lang.String r7 = "卡上应用圈存初始化，(圈存)平台响应错误响应码: "
            r15.<init>(r7)
            java.lang.String r7 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r4)
            r15.append(r7)
            java.lang.String r7 = r15.toString()
            r10.error(r14, r7)
        L_0x0321:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r7 = r1.cardBusinessBasic
            java.lang.String r10 = "卡上应用圈存初始化，平台处理失败"
            java.lang.String r4 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r4)
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r4 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.instance(r4)
            r7.throwExceptionAndClose((java.lang.String) r10, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r4, (boolean) r8)
        L_0x0331:
            cn.com.fmsh.communication.message.IMessage r0 = r2.createMessage((byte[]) r0)     // Catch:{ FMCommunicationMessageException -> 0x0348 }
            cn.com.fmsh.communication.message.ITag r4 = r0.getTag4Id(r11)     // Catch:{ FMCommunicationMessageException -> 0x0348 }
            cn.com.fmsh.communication.message.ITag r7 = r0.getTag4Id(r9)     // Catch:{ FMCommunicationMessageException -> 0x0346 }
            r10 = -95
            cn.com.fmsh.communication.message.ITag r0 = r0.getTag4Id(r10)     // Catch:{ FMCommunicationMessageException -> 0x0344 }
            goto L_0x0374
        L_0x0344:
            r0 = move-exception
            goto L_0x034b
        L_0x0346:
            r0 = move-exception
            goto L_0x034a
        L_0x0348:
            r0 = move-exception
            r4 = r12
        L_0x034a:
            r7 = r13
        L_0x034b:
            cn.com.fmsh.util.log.FMLog r10 = r1.fmLog
            if (r10 == 0) goto L_0x0369
            cn.com.fmsh.util.log.FMLog r10 = r1.fmLog
            java.lang.String r12 = r1.logTag
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r14 = "卡上应用圈存时，解析平台响应出现异常: "
            r13.<init>(r14)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r13.append(r0)
            java.lang.String r0 = r13.toString()
            r10.error(r12, r0)
        L_0x0369:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r10 = "卡上应用圈存时，平台响应解析失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r12 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r0.throwExceptionAndClose((java.lang.String) r10, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r12, (boolean) r8)
            r0 = r6
        L_0x0374:
            if (r0 != 0) goto L_0x038e
            cn.com.fmsh.util.log.FMLog r6 = r1.fmLog
            if (r6 == 0) goto L_0x0384
            cn.com.fmsh.util.log.FMLog r6 = r1.fmLog
            java.lang.String r10 = r1.logTag
            java.lang.String r12 = "卡上应用圈存时，平台响应数据无执行脚本（A1）"
            r6.error(r10, r12)
        L_0x0384:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r6 = r1.cardBusinessBasic
            java.lang.String r10 = "卡上应用圈存时，平台响应数据无执行脚本"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r12 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r6.throwExceptionAndClose((java.lang.String) r10, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r12, (boolean) r8)
        L_0x038e:
            cn.com.fmsh.script.bean.ApduRequestList r6 = new cn.com.fmsh.script.bean.ApduRequestList
            r6.<init>()
            r6.fromTag(r0)     // Catch:{ FMCommunicationMessageException -> 0x0406, FMScriptHandleException -> 0x03dc }
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic     // Catch:{ FMCommunicationMessageException -> 0x0406, FMScriptHandleException -> 0x03dc }
            cn.com.fmsh.script.ScriptHandler r0 = r0.getScriptHandler()     // Catch:{ FMCommunicationMessageException -> 0x0406, FMScriptHandleException -> 0x03dc }
            cn.com.fmsh.script.bean.ApduReponseList r0 = r0.execute((cn.com.fmsh.script.bean.ApduRequestList) r6)     // Catch:{ FMCommunicationMessageException -> 0x0406, FMScriptHandleException -> 0x03dc }
            if (r0 == 0) goto L_0x03ac
            int r6 = r0.size()     // Catch:{ FMCommunicationMessageException -> 0x0406, FMScriptHandleException -> 0x03dc }
            if (r6 >= r8) goto L_0x03a9
            goto L_0x03ac
        L_0x03a9:
            r6 = 9001(0x2329, float:1.2613E-41)
            goto L_0x03c5
        L_0x03ac:
            cn.com.fmsh.util.log.FMLog r6 = r1.fmLog     // Catch:{ FMCommunicationMessageException -> 0x0406, FMScriptHandleException -> 0x03dc }
            if (r6 == 0) goto L_0x03ba
            cn.com.fmsh.util.log.FMLog r6 = r1.fmLog     // Catch:{ FMCommunicationMessageException -> 0x0406, FMScriptHandleException -> 0x03dc }
            java.lang.String r10 = r1.logTag     // Catch:{ FMCommunicationMessageException -> 0x0406, FMScriptHandleException -> 0x03dc }
            java.lang.String r12 = "卡上应用圈存时，脚本执行结果为空"
            r6.error(r10, r12)     // Catch:{ FMCommunicationMessageException -> 0x0406, FMScriptHandleException -> 0x03dc }
        L_0x03ba:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r6 = r1.cardBusinessBasic     // Catch:{ FMCommunicationMessageException -> 0x0406, FMScriptHandleException -> 0x03dc }
            java.lang.String r10 = "卡上应用圈存时，脚本执行出现异常"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r12 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception     // Catch:{ FMCommunicationMessageException -> 0x0406, FMScriptHandleException -> 0x03dc }
            r6.throwExceptionAndClose((java.lang.String) r10, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r12, (boolean) r8)     // Catch:{ FMCommunicationMessageException -> 0x0406, FMScriptHandleException -> 0x03dc }
            goto L_0x03a9
        L_0x03c5:
            cn.com.fmsh.communication.message.IMessage r10 = r2.createMessage((int) r6)     // Catch:{ FMCommunicationMessageException -> 0x0406, FMScriptHandleException -> 0x03dc }
            r10.addTag(r4)     // Catch:{ FMCommunicationMessageException -> 0x0406, FMScriptHandleException -> 0x03dc }
            r10.addTag(r7)     // Catch:{ FMCommunicationMessageException -> 0x0406, FMScriptHandleException -> 0x03dc }
            cn.com.fmsh.communication.message.ITag r0 = r0.toTag4A3()     // Catch:{ FMCommunicationMessageException -> 0x0406, FMScriptHandleException -> 0x03dc }
            r10.addTag(r0)     // Catch:{ FMCommunicationMessageException -> 0x0406, FMScriptHandleException -> 0x03dc }
            byte[] r0 = r10.toBytes()     // Catch:{ FMCommunicationMessageException -> 0x0406, FMScriptHandleException -> 0x03dc }
            r5 = r0
            goto L_0x042f
        L_0x03dc:
            r0 = move-exception
            cn.com.fmsh.util.log.FMLog r4 = r1.fmLog
            if (r4 == 0) goto L_0x03fb
            cn.com.fmsh.util.log.FMLog r4 = r1.fmLog
            java.lang.String r6 = r1.logTag
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r10 = "卡上应用圈存时，脚本执行出现异常："
            r7.<init>(r10)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r7.append(r0)
            java.lang.String r0 = r7.toString()
            r4.error(r6, r0)
        L_0x03fb:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r4 = "上卡上应用圈存时，脚本执行出现异常"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception
            r0.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r8)
            goto L_0x042f
        L_0x0406:
            r0 = move-exception
            cn.com.fmsh.util.log.FMLog r4 = r1.fmLog
            if (r4 == 0) goto L_0x0425
            cn.com.fmsh.util.log.FMLog r4 = r1.fmLog
            java.lang.String r6 = r1.logTag
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r10 = "卡上应用圈存时，平台响应数据解析出现异常: "
            r7.<init>(r10)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r7.append(r0)
            java.lang.String r0 = r7.toString()
            r4.error(r6, r0)
        L_0x0425:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r4 = "卡上应用圈存时，平台响应数据解析出现异常"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r0.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r8)
        L_0x042f:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r4 = "卡上应用圈存时"
            byte[] r0 = r0.interaction(r5, r4, r8, r3)
            r4 = 2
            byte[] r6 = java.util.Arrays.copyOf(r0, r4)
            byte[] r4 = cn.com.fmsh.tsm.business.constants.Constants.RespCodeonse4Platform.SUCESS
            boolean r4 = java.util.Arrays.equals(r4, r6)
            if (r4 == 0) goto L_0x044b
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            r0.businessFinish(r8)
            return r8
        L_0x044b:
            byte[] r4 = cn.com.fmsh.tsm.business.constants.Constants.RespCodeonse4Platform.CARD_REQUEST
            boolean r4 = java.util.Arrays.equals(r4, r6)
            if (r4 != 0) goto L_0x0478
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x0471
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r2 = r1.logTag
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "卡上应用圈存失败，平台响应错误响应码: "
            r3.<init>(r4)
            java.lang.String r4 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r6)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.error(r2, r3)
        L_0x0471:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            r0.businessFinish(r8)
            r2 = 0
            return r2
        L_0x0478:
            cn.com.fmsh.util.log.FMLog r4 = r1.fmLog
            if (r4 == 0) goto L_0x0486
            cn.com.fmsh.util.log.FMLog r4 = r1.fmLog
            java.lang.String r6 = r1.logTag
            java.lang.String r7 = "卡上应用圈存完成"
            r4.debug(r6, r7)
        L_0x0486:
            cn.com.fmsh.communication.message.IMessage r4 = r2.createMessage((byte[]) r0)     // Catch:{ FMCommunicationMessageException -> 0x05b6 }
            cn.com.fmsh.communication.message.ITag r0 = r4.getTag4Id(r11)     // Catch:{ FMCommunicationMessageException -> 0x05b6 }
            cn.com.fmsh.communication.message.ITag r6 = r4.getTag4Id(r9)     // Catch:{ FMCommunicationMessageException -> 0x05b6 }
            r7 = -95
            cn.com.fmsh.communication.message.ITag r7 = r4.getTag4Id(r7)     // Catch:{ FMCommunicationMessageException -> 0x05b6 }
            if (r7 != 0) goto L_0x04ae
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x04a8
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r2 = r1.logTag
            java.lang.String r3 = "卡上应用圈存完成,更新有效期，平台响应数据无执行脚本（A1）"
            r0.error(r2, r3)
        L_0x04a8:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            r0.businessFinish(r8)
            return r8
        L_0x04ae:
            cn.com.fmsh.script.bean.ApduRequestList r9 = new cn.com.fmsh.script.bean.ApduRequestList
            r9.<init>()
            r9.fromTag(r7)     // Catch:{ FMCommunicationMessageException -> 0x0522, FMScriptHandleException -> 0x0502 }
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r7 = r1.cardBusinessBasic     // Catch:{ FMCommunicationMessageException -> 0x0522, FMScriptHandleException -> 0x0502 }
            cn.com.fmsh.script.ScriptHandler r7 = r7.getScriptHandler()     // Catch:{ FMCommunicationMessageException -> 0x0522, FMScriptHandleException -> 0x0502 }
            cn.com.fmsh.script.bean.ApduReponseList r7 = r7.execute((cn.com.fmsh.script.bean.ApduRequestList) r9)     // Catch:{ FMCommunicationMessageException -> 0x0522, FMScriptHandleException -> 0x0502 }
            if (r7 == 0) goto L_0x04e6
            int r9 = r7.size()     // Catch:{ FMCommunicationMessageException -> 0x0522, FMScriptHandleException -> 0x0502 }
            if (r9 >= r8) goto L_0x04c9
            goto L_0x04e6
        L_0x04c9:
            r9 = 9001(0x2329, float:1.2613E-41)
            cn.com.fmsh.communication.message.IMessage r2 = r2.createMessage((int) r9)     // Catch:{ FMCommunicationMessageException -> 0x0522, FMScriptHandleException -> 0x0502 }
            r2.addTag(r0)     // Catch:{ FMCommunicationMessageException -> 0x0522, FMScriptHandleException -> 0x0502 }
            r2.addTag(r6)     // Catch:{ FMCommunicationMessageException -> 0x0522, FMScriptHandleException -> 0x0502 }
            cn.com.fmsh.communication.message.ITag r0 = r7.toTag4A3()     // Catch:{ FMCommunicationMessageException -> 0x0522, FMScriptHandleException -> 0x0502 }
            r2.addTag(r0)     // Catch:{ FMCommunicationMessageException -> 0x0522, FMScriptHandleException -> 0x0502 }
            byte[] r0 = r2.toBytes()     // Catch:{ FMCommunicationMessageException -> 0x0522, FMScriptHandleException -> 0x0502 }
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r1.cardBusinessBasic
            r2.businessFinish(r8)
            goto L_0x0547
        L_0x04e6:
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog     // Catch:{ FMCommunicationMessageException -> 0x0522, FMScriptHandleException -> 0x0502 }
            if (r0 == 0) goto L_0x04f4
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog     // Catch:{ FMCommunicationMessageException -> 0x0522, FMScriptHandleException -> 0x0502 }
            java.lang.String r2 = r1.logTag     // Catch:{ FMCommunicationMessageException -> 0x0522, FMScriptHandleException -> 0x0502 }
            java.lang.String r6 = "卡上应用圈存完成,更新有效期时，脚本执行结果为空"
            r0.error(r2, r6)     // Catch:{ FMCommunicationMessageException -> 0x0522, FMScriptHandleException -> 0x0502 }
        L_0x04f4:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic     // Catch:{ FMCommunicationMessageException -> 0x0522, FMScriptHandleException -> 0x0502 }
            r0.businessFinish(r8)     // Catch:{ FMCommunicationMessageException -> 0x0522, FMScriptHandleException -> 0x0502 }
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            r0.businessFinish(r8)
            return r8
        L_0x04ff:
            r0 = move-exception
            goto L_0x05b0
        L_0x0502:
            r0 = move-exception
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog     // Catch:{ all -> 0x04ff }
            if (r2 == 0) goto L_0x0541
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog     // Catch:{ all -> 0x04ff }
            java.lang.String r6 = r1.logTag     // Catch:{ all -> 0x04ff }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x04ff }
            java.lang.String r9 = "卡上应用圈存完成，更新有效期时，脚本执行出现异常："
            r7.<init>(r9)     // Catch:{ all -> 0x04ff }
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)     // Catch:{ all -> 0x04ff }
            r7.append(r0)     // Catch:{ all -> 0x04ff }
            java.lang.String r0 = r7.toString()     // Catch:{ all -> 0x04ff }
            r2.error(r6, r0)     // Catch:{ all -> 0x04ff }
            goto L_0x0541
        L_0x0522:
            r0 = move-exception
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog     // Catch:{ all -> 0x04ff }
            if (r2 == 0) goto L_0x0541
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog     // Catch:{ all -> 0x04ff }
            java.lang.String r6 = r1.logTag     // Catch:{ all -> 0x04ff }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x04ff }
            java.lang.String r9 = "卡上应用圈存完成，更新有效期时，平台响应数据解析出现异常: "
            r7.<init>(r9)     // Catch:{ all -> 0x04ff }
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)     // Catch:{ all -> 0x04ff }
            r7.append(r0)     // Catch:{ all -> 0x04ff }
            java.lang.String r0 = r7.toString()     // Catch:{ all -> 0x04ff }
            r2.error(r6, r0)     // Catch:{ all -> 0x04ff }
        L_0x0541:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            r0.businessFinish(r8)
            r0 = r5
        L_0x0547:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r1.cardBusinessBasic
            java.lang.String r5 = "卡上应用圈存完成,更新有效期"
            r6 = 0
            byte[] r0 = r2.interaction(r0, r5, r6, r3)
            r2 = 2
            byte[] r0 = java.util.Arrays.copyOf(r0, r2)
            byte[] r2 = cn.com.fmsh.tsm.business.constants.Constants.RespCodeonse4Platform.SUCESS
            boolean r2 = java.util.Arrays.equals(r2, r0)
            if (r2 != 0) goto L_0x05a1
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            if (r2 == 0) goto L_0x057c
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            java.lang.String r3 = r1.logTag
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "卡上应用圈存完成,更新有效期，平台响应错误响应码: "
            r5.<init>(r6)
            java.lang.String r6 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r0)
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r2.warn(r3, r5)
        L_0x057c:
            java.lang.String r0 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r0)
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r0 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.instance(r0)
            if (r0 != 0) goto L_0x0588
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r0 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.trade_fail
        L_0x0588:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r1.cardBusinessBasic
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r4)
            java.lang.String r4 = "时,构造平台请求数据出现失败"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r4 = 0
            r2.throwExceptionAndClose((java.lang.String) r3, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r0, (boolean) r4)
            goto L_0x05af
        L_0x05a1:
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x05af
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r2 = r1.logTag
            java.lang.String r3 = "卡上应用圈存完成"
            r0.debug(r2, r3)
        L_0x05af:
            return r8
        L_0x05b0:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r1.cardBusinessBasic
            r2.businessFinish(r8)
            throw r0
        L_0x05b6:
            r0 = move-exception
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            if (r2 == 0) goto L_0x05d5
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            java.lang.String r3 = r1.logTag
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "卡上应用圈存完成,更新有效期，解析平台响应出现异常: "
            r4.<init>(r5)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            r2.error(r3, r0)
        L_0x05d5:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r2 = "卡上应用圈存完成,更新有效期，解析平台响应出现异常"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r0.throwExceptionAndClose((java.lang.String) r2, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r3, (boolean) r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardAppTradeImpl.remoteRecharge(byte[], byte[], cn.com.fmsh.communication.message.IMessageHandler, java.lang.String):boolean");
    }

    public List<MainOrder> queryMainOrders(int i, int i2, EnumOrderStatus enumOrderStatus, EnumCardAppType enumCardAppType) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "主订单信息查询...");
        }
        if (enumOrderStatus == null) {
            enumOrderStatus = EnumOrderStatus.unknown;
        }
        EnumOrderStatus enumOrderStatus2 = enumOrderStatus;
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("主订单信息查询") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("主订单信息查询") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.warn(str2, String.valueOf("主订单信息查询") + "，消息处理器为空");
            }
            throw new BusinessException(String.valueOf("主订单信息查询") + "，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_ORDERS_VER2);
        if (server4Business == null) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str3 = this.logTag;
                fMLog3.warn(str3, String.valueOf("主订单信息查询") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("主订单信息查询") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        this.cardBusinessBasic.businessReady("主订单信息查询", server4Business);
        List<MainOrder> queryMainOrdersHandle = queryMainOrdersHandle(enumOrderStatus2, i2 + i, enumCardAppType, messageHandler, server4Business);
        this.cardBusinessBasic.businessFinish(false);
        return queryMainOrdersHandle;
    }

    private List<MainOrder> queryMainOrdersHandle(EnumOrderStatus enumOrderStatus, int i, EnumCardAppType enumCardAppType, IMessageHandler iMessageHandler, String str) throws BusinessException {
        byte[] bArr;
        try {
            IMessage createMessage = iMessageHandler.createMessage((int) Constants.TradeCode.QUERY_ORDERS_VER2);
            ITag createTag = iMessageHandler.createTag((byte) Constants.TagName.QUERY_RECORD_COUNT);
            createTag.addValue(i);
            createMessage.addTag(createTag);
            ITag createTag2 = iMessageHandler.createTag((byte) 14);
            createTag2.addValue(enumCardAppType.getId());
            createMessage.addTag(createTag2);
            ITag createTag3 = iMessageHandler.createTag((byte) Constants.TagName.ORDER_TYPE);
            createTag3.addValue(EnumOrderType.MAIN.getId());
            createMessage.addTag(createTag3);
            if (enumOrderStatus != null) {
                ITag createTag4 = iMessageHandler.createTag((byte) 21);
                createTag4.addValue(enumOrderStatus.getId());
                createMessage.addTag(createTag4);
            }
            bArr = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("主订单信息查询") + "时,构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("主订单信息查询") + "时,构造平台请求数据出现失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr, "主订单信息查询", false, str);
        byte[] copyOf = Arrays.copyOf(interaction, 2);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, copyOf)) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, String.valueOf("主订单信息查询") + "时，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("主订单信息查询") + "时，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(copyOf)), true);
        }
        ArrayList arrayList = new ArrayList();
        try {
            ITag tag4Id = iMessageHandler.createMessage(Constants.TradeCode.QUERY_ORDERS_VER2, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(97);
            if (tag4Id == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("主订单信息查询") + "时，平台响应数据没有包含标签[61],即平台没有主订单记录");
                }
                return arrayList;
            }
            ITag[] itemTags = tag4Id.getItemTags();
            if (itemTags != null) {
                if (itemTags.length >= 1) {
                    for (ITag fromTag : itemTags) {
                        arrayList.add(MainOrder.fromTag(fromTag));
                    }
                    return arrayList;
                }
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("主订单信息查询") + "时，平台响应数据没有包含标签[60] Tag,即平台没有订单记录");
            }
            return arrayList;
        } catch (FMCommunicationMessageException unused) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("主订单信息查询") + "时，解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("主订单信息查询") + "时，解析平台响应数据失败", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
        }
    }

    public List<MainOrder> queryMainOrdersVer4(int i, int i2, List<EnumOrderStatus> list, EnumCardAppType enumCardAppType) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "主订单信息查询(Ver4)...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("主订单信息查询") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("主订单信息查询") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.warn(str2, String.valueOf("主订单信息查询") + "，消息处理器为空");
            }
            throw new BusinessException(String.valueOf("主订单信息查询") + "，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_ORDERS_VER4);
        if (server4Business == null) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str3 = this.logTag;
                fMLog3.warn(str3, String.valueOf("主订单信息查询") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("主订单信息查询") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        this.cardBusinessBasic.businessReady("主订单信息查询", server4Business);
        List<MainOrder> queryMainOrdersHandleVer4 = queryMainOrdersHandleVer4(list, i2 + i, enumCardAppType, messageHandler, server4Business);
        this.cardBusinessBasic.businessFinish(false);
        return queryMainOrdersHandleVer4;
    }

    private List<MainOrder> queryMainOrdersHandleVer4(List<EnumOrderStatus> list, int i, EnumCardAppType enumCardAppType, IMessageHandler iMessageHandler, String str) throws BusinessException {
        byte[] bArr;
        try {
            IMessage createMessage = iMessageHandler.createMessage((int) Constants.TradeCode.QUERY_ORDERS_VER4);
            ITag createTag = iMessageHandler.createTag((byte) Constants.TagName.QUERY_RECORD_COUNT);
            createTag.addValue(i);
            createMessage.addTag(createTag);
            ITag createTag2 = iMessageHandler.createTag((byte) 14);
            createTag2.addValue(enumCardAppType.getId());
            createMessage.addTag(createTag2);
            ITag createTag3 = iMessageHandler.createTag((byte) Constants.TagName.ORDER_TYPE);
            createTag3.addValue(EnumOrderType.MAIN.getId());
            createMessage.addTag(createTag3);
            if (list != null && list.size() > 0) {
                byte[] bArr2 = new byte[list.size()];
                for (int i2 = 0; i2 < list.size(); i2++) {
                    EnumOrderStatus enumOrderStatus = list.get(i2);
                    if (enumOrderStatus != null) {
                        bArr2[i2] = (byte) enumOrderStatus.getId();
                    }
                }
                ITag createTag4 = iMessageHandler.createTag((byte) Constants.TagName.ORDER_TRADE_STATUSES);
                createTag4.addValue(bArr2);
                createMessage.addTag(createTag4);
            }
            bArr = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("主订单信息查询") + "时,构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("主订单信息查询") + "时,构造平台请求数据出现失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr, "主订单信息查询", false, str);
        byte[] copyOf = Arrays.copyOf(interaction, 2);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, copyOf)) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, String.valueOf("主订单信息查询") + "时,平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose("主订单记录查询时，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(copyOf)), true);
        }
        ArrayList arrayList = new ArrayList();
        try {
            ITag tag4Id = iMessageHandler.createMessage(Constants.TradeCode.QUERY_ORDERS_VER2, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(97);
            if (tag4Id == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "主订单记录查询时,平台响应数据没有包含标签[61],即平台没有主订单记录");
                }
                return arrayList;
            }
            ITag[] itemTags = tag4Id.getItemTags();
            if (itemTags != null) {
                if (itemTags.length >= 1) {
                    for (ITag fromTag : itemTags) {
                        arrayList.add(MainOrder.fromTag(fromTag));
                    }
                    return arrayList;
                }
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "主订单记录查询时,平台响应数据没有包含标签[60] Tag,即平台没有订单记录");
            }
            return arrayList;
        } catch (FMCommunicationMessageException unused) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "主订单记录查询时，解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose("主订单记录查询时，解析平台响应数据失败", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
        }
    }

    public MainOrder queryMainOrder(byte[] bArr) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "主订单详细查询...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("主订单信息查询") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("主订单信息查询") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.warn(str2, String.valueOf("主订单信息查询") + "，消息处理器为空");
            }
            throw new BusinessException(String.valueOf("主订单信息查询") + "，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_ORDER_VER2);
        if (server4Business == null) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str3 = this.logTag;
                fMLog3.warn(str3, String.valueOf("主订单信息查询") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("主订单信息查询") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        this.cardBusinessBasic.businessReady("主订单信息查询", server4Business);
        MainOrder queryMainOrderHandle = queryMainOrderHandle(bArr, messageHandler, server4Business);
        this.cardBusinessBasic.businessFinish(false);
        return queryMainOrderHandle;
    }

    private MainOrder queryMainOrderHandle(byte[] bArr, IMessageHandler iMessageHandler, String str) throws BusinessException {
        byte[] bArr2;
        try {
            IMessage createMessage = iMessageHandler.createMessage((int) Constants.TradeCode.QUERY_ORDER_VER2);
            ITag createTag = iMessageHandler.createTag((byte) Constants.TagName.MAIN_ORDER_ID);
            createTag.addValue(bArr);
            createMessage.addTag(createTag);
            ITag createTag2 = iMessageHandler.createTag((byte) Constants.TagName.ORDER_TYPE);
            createTag2.addValue(EnumOrderType.MAIN.getId());
            createMessage.addTag(createTag2);
            bArr2 = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "主订单信息查询时,构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
            }
            this.cardBusinessBasic.throwExceptionAndClose("主订单信息查查询时,构造平台请求数据出现失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr2 = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr2, "主订单信息查询时", false, str);
        byte[] copyOf = Arrays.copyOf(interaction, 2);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, copyOf)) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, "主订单信息查询时，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose("主订单信息查询时，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(copyOf)), true);
        }
        try {
            ITag tag4Id = iMessageHandler.createMessage(Constants.TradeCode.QUERY_ORDERS_VER2, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(96);
            if (tag4Id != null) {
                return MainOrder.fromTag(tag4Id);
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "主订单信息查询时,平台响应数据没有包含标签[61],即平台没有主订单记录");
            }
            return null;
        } catch (FMCommunicationMessageException unused) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "主订单信息查询时，解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose("主订单信息查询时，解析平台响应数据失败", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
            return null;
        }
    }

    public List<BusinessOrder> queryBusinessOrders(int i, int i2, EnumCardAppType enumCardAppType, EnumBusinessOrderType enumBusinessOrderType, EnumOrderStatus enumOrderStatus) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "业务订单查询...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "业务订单查询，业务处理对象为空");
            }
            throw new BusinessException("业务订单查询，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "业务订单查询，消息处理器为空");
            }
            throw new BusinessException("业务订单查询，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_ORDERS_VER2);
        if (server4Business == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("业务订单查询") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("业务订单查询") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        this.cardBusinessBasic.businessReady("业务订单查询", server4Business);
        List<BusinessOrder> queryBusinessOrdersHandle = queryBusinessOrdersHandle(enumOrderStatus, i + i2, enumCardAppType, enumBusinessOrderType, messageHandler, server4Business);
        this.cardBusinessBasic.businessFinish(false);
        return queryBusinessOrdersHandle;
    }

    private List<BusinessOrder> queryBusinessOrdersHandle(EnumOrderStatus enumOrderStatus, int i, EnumCardAppType enumCardAppType, EnumBusinessOrderType enumBusinessOrderType, IMessageHandler iMessageHandler, String str) throws BusinessException {
        byte[] bArr;
        BusinessOrder fromTag;
        try {
            IMessage createMessage = iMessageHandler.createMessage((int) Constants.TradeCode.QUERY_ORDERS_VER2);
            if (enumOrderStatus != null) {
                ITag createTag = iMessageHandler.createTag((byte) 21);
                createTag.addValue(enumOrderStatus.getId());
                createMessage.addTag(createTag);
            }
            ITag createTag2 = iMessageHandler.createTag((byte) Constants.TagName.QUERY_RECORD_COUNT);
            createTag2.addValue(i);
            createMessage.addTag(createTag2);
            if (enumCardAppType != null) {
                ITag createTag3 = iMessageHandler.createTag((byte) 14);
                createTag3.addValue(enumCardAppType.getId());
                createMessage.addTag(createTag3);
            }
            ITag createTag4 = iMessageHandler.createTag((byte) Constants.TagName.ORDER_TYPE);
            createTag4.addValue(EnumOrderType.BUSINESS.getId());
            createMessage.addTag(createTag4);
            if (enumBusinessOrderType != null) {
                ITag createTag5 = iMessageHandler.createTag((byte) Constants.TagName.BUSINESS_ORDER_TYPE);
                createTag5.addValue(enumBusinessOrderType.getId());
                createMessage.addTag(createTag5);
            }
            bArr = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "业务订单查询时,构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
            }
            this.cardBusinessBasic.throwExceptionAndClose("业务订单查询时,构造平台请求数据出现失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr, "业务订单查询时", false, str);
        byte[] copyOf = Arrays.copyOf(interaction, 2);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, copyOf)) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, "业务订单查询时，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose("业务订单查询时，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(copyOf)), true);
        }
        ArrayList arrayList = new ArrayList();
        try {
            ITag tag4Id = iMessageHandler.createMessage(Constants.TradeCode.QUERY_ORDERS_VER2, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(27);
            if (tag4Id == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "业务订单查询时,平台没有业务订单记录");
                }
                return arrayList;
            }
            ITag[] itemTags = tag4Id.getItemTags();
            if (itemTags != null) {
                if (itemTags.length >= 1) {
                    for (ITag iTag : itemTags) {
                        if (!(iTag == null || (fromTag = BusinessOrder.fromTag(iTag)) == null)) {
                            arrayList.add(fromTag);
                        }
                    }
                    return arrayList;
                }
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "业务订单查询时,平台业务订到集合为空");
            }
            return arrayList;
        } catch (FMCommunicationMessageException unused) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "业务订单查询时，解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose("业务订单查询时，解析平台响应数据失败", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
        }
    }

    public List<BusinessOrder> queryBusinessOrdersVer3(int i, int i2, EnumCardAppType enumCardAppType, EnumBusinessOrderType enumBusinessOrderType, EnumOrderStatus enumOrderStatus, byte[] bArr) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "业务订单查询(Ver3)...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "业务订单查询，业务处理对象为空");
            }
            throw new BusinessException("业务订单查询，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "业务订单查询，消息处理器为空");
            }
            throw new BusinessException("业务订单查询，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_ORDERS_VER3);
        if (server4Business == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("业务订单查询") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("业务订单查询") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        this.cardBusinessBasic.businessReady("业务订单查询", server4Business);
        List<BusinessOrder> queryBusinessOrdersHandleVer3 = queryBusinessOrdersHandleVer3(enumOrderStatus, i + i2, enumCardAppType, enumBusinessOrderType, messageHandler, bArr, server4Business);
        this.cardBusinessBasic.businessFinish(false);
        return queryBusinessOrdersHandleVer3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0092 A[Catch:{ FMCommunicationMessageException -> 0x00a3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x012a A[Catch:{ FMCommunicationMessageException -> 0x0167 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0139 A[Catch:{ FMCommunicationMessageException -> 0x0167 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<cn.com.fmsh.tsm.business.bean.BusinessOrder> queryBusinessOrdersHandleVer3(cn.com.fmsh.tsm.business.enums.EnumOrderStatus r5, int r6, cn.com.fmsh.tsm.business.enums.EnumCardAppType r7, cn.com.fmsh.tsm.business.enums.EnumBusinessOrderType r8, cn.com.fmsh.communication.message.IMessageHandler r9, byte[] r10, java.lang.String r11) throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r4 = this;
            r0 = 1133(0x46d, float:1.588E-42)
            r1 = 0
            r2 = 0
            cn.com.fmsh.communication.message.IMessage r0 = r9.createMessage((int) r0)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            r3 = 21
            if (r5 == 0) goto L_0x001b
            cn.com.fmsh.communication.message.ITag r3 = r9.createTag((byte) r3)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            int r5 = r5.getId()     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            r3.addValue((int) r5)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            r0.addTag(r3)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            goto L_0x002b
        L_0x001b:
            cn.com.fmsh.communication.message.ITag r5 = r9.createTag((byte) r3)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            cn.com.fmsh.tsm.business.enums.EnumOrderStatus r3 = cn.com.fmsh.tsm.business.enums.EnumOrderStatus.unknown     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            int r3 = r3.getId()     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            r5.addValue((int) r3)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            r0.addTag(r5)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
        L_0x002b:
            r5 = 38
            cn.com.fmsh.communication.message.ITag r5 = r9.createTag((byte) r5)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            r5.addValue((int) r6)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            r0.addTag(r5)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            if (r7 == 0) goto L_0x0049
            r5 = 14
            cn.com.fmsh.communication.message.ITag r5 = r9.createTag((byte) r5)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            int r6 = r7.getId()     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            r5.addValue((int) r6)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            r0.addTag(r5)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
        L_0x0049:
            r5 = 101(0x65, float:1.42E-43)
            cn.com.fmsh.communication.message.ITag r5 = r9.createTag((byte) r5)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            cn.com.fmsh.tsm.business.enums.EnumOrderType r6 = cn.com.fmsh.tsm.business.enums.EnumOrderType.BUSINESS     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            int r6 = r6.getId()     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            r5.addValue((int) r6)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            r0.addTag(r5)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            if (r8 == 0) goto L_0x008f
            r5 = 72
            cn.com.fmsh.communication.message.ITag r5 = r9.createTag((byte) r5)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            int r6 = r8.getId()     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            r5.addValue((int) r6)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            r0.addTag(r5)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            if (r10 == 0) goto L_0x008f
            int r5 = r10.length     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            if (r5 <= 0) goto L_0x008f
            cn.com.fmsh.tsm.business.enums.EnumBusinessOrderType r5 = cn.com.fmsh.tsm.business.enums.EnumBusinessOrderType.ORDER_TYPE_ISSUE     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            if (r8 != r5) goto L_0x0080
            r5 = -79
            cn.com.fmsh.communication.message.ITag r5 = r9.createTag((byte) r5)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            r5.addValue((byte[]) r10)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            goto L_0x0081
        L_0x0080:
            r5 = r1
        L_0x0081:
            cn.com.fmsh.tsm.business.enums.EnumBusinessOrderType r6 = cn.com.fmsh.tsm.business.enums.EnumBusinessOrderType.ORDER_TYPE_RECHARGE     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            if (r8 != r6) goto L_0x0090
            r5 = 15
            cn.com.fmsh.communication.message.ITag r5 = r9.createTag((byte) r5)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            r5.addValue((byte[]) r10)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            goto L_0x0090
        L_0x008f:
            r5 = r1
        L_0x0090:
            if (r5 == 0) goto L_0x009e
            r6 = -76
            cn.com.fmsh.communication.message.ITag r6 = r9.createTag((byte) r6)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            r6.addValue((cn.com.fmsh.communication.message.ITag) r5)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            r0.addTag(r6)     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
        L_0x009e:
            byte[] r5 = r0.toBytes()     // Catch:{ FMCommunicationMessageException -> 0x00a3 }
            goto L_0x00cd
        L_0x00a3:
            r5 = move-exception
            cn.com.fmsh.util.log.FMLog r6 = r4.fmLog
            if (r6 == 0) goto L_0x00c2
            cn.com.fmsh.util.log.FMLog r6 = r4.fmLog
            java.lang.String r7 = r4.logTag
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r10 = "业务订单查询时,构造平台请求数据出现异常："
            r8.<init>(r10)
            java.lang.String r5 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r5)
            r8.append(r5)
            java.lang.String r5 = r8.toString()
            r6.warn(r7, r5)
        L_0x00c2:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r5 = r4.cardBusinessBasic
            java.lang.String r6 = "业务订单查询时,构造平台请求数据出现失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r7 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r5.throwExceptionAndClose((java.lang.String) r6, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r7, (boolean) r2)
            r5 = r1
        L_0x00cd:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r6 = r4.cardBusinessBasic
            java.lang.String r7 = "业务订单查询时"
            byte[] r5 = r6.interaction(r5, r7, r2, r11)
            r6 = 2
            byte[] r7 = java.util.Arrays.copyOf(r5, r6)
            byte[] r8 = cn.com.fmsh.tsm.business.constants.Constants.RespCodeonse4Platform.SUCESS
            boolean r8 = java.util.Arrays.equals(r8, r7)
            r10 = 1
            if (r8 != 0) goto L_0x0112
            cn.com.fmsh.util.log.FMLog r8 = r4.fmLog
            if (r8 == 0) goto L_0x0102
            cn.com.fmsh.util.log.FMLog r8 = r4.fmLog
            java.lang.String r11 = r4.logTag
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "业务订单查询时，平台响应错误响应码: "
            r0.<init>(r1)
            java.lang.String r1 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r5)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r8.error(r11, r0)
        L_0x0102:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r8 = r4.cardBusinessBasic
            java.lang.String r11 = "业务订单查询时，平台处理失败"
            java.lang.String r7 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r7)
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r7 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.instance(r7)
            r8.throwExceptionAndClose((java.lang.String) r11, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r7, (boolean) r10)
        L_0x0112:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r8 = 1132(0x46c, float:1.586E-42)
            int r11 = r5.length     // Catch:{ FMCommunicationMessageException -> 0x0167 }
            byte[] r6 = java.util.Arrays.copyOfRange(r5, r6, r11)     // Catch:{ FMCommunicationMessageException -> 0x0167 }
            cn.com.fmsh.communication.message.IMessage r6 = r9.createMessage(r8, r6)     // Catch:{ FMCommunicationMessageException -> 0x0167 }
            r8 = 27
            cn.com.fmsh.communication.message.ITag r6 = r6.getTag4Id(r8)     // Catch:{ FMCommunicationMessageException -> 0x0167 }
            if (r6 != 0) goto L_0x0139
            cn.com.fmsh.util.log.FMLog r6 = r4.fmLog     // Catch:{ FMCommunicationMessageException -> 0x0167 }
            if (r6 == 0) goto L_0x0138
            cn.com.fmsh.util.log.FMLog r6 = r4.fmLog     // Catch:{ FMCommunicationMessageException -> 0x0167 }
            java.lang.String r8 = r4.logTag     // Catch:{ FMCommunicationMessageException -> 0x0167 }
            java.lang.String r9 = "业务订单查询时,平台没有业务订单记录"
            r6.warn(r8, r9)     // Catch:{ FMCommunicationMessageException -> 0x0167 }
        L_0x0138:
            return r7
        L_0x0139:
            cn.com.fmsh.communication.message.ITag[] r6 = r6.getItemTags()     // Catch:{ FMCommunicationMessageException -> 0x0167 }
            if (r6 == 0) goto L_0x0158
            int r8 = r6.length     // Catch:{ FMCommunicationMessageException -> 0x0167 }
            if (r8 >= r10) goto L_0x0143
            goto L_0x0158
        L_0x0143:
            int r8 = r6.length     // Catch:{ FMCommunicationMessageException -> 0x0167 }
            r9 = 0
        L_0x0145:
            if (r9 < r8) goto L_0x0148
            goto L_0x018f
        L_0x0148:
            r10 = r6[r9]     // Catch:{ FMCommunicationMessageException -> 0x0167 }
            if (r10 == 0) goto L_0x0155
            cn.com.fmsh.tsm.business.bean.BusinessOrder r10 = cn.com.fmsh.tsm.business.bean.BusinessOrder.fromTag(r10)     // Catch:{ FMCommunicationMessageException -> 0x0167 }
            if (r10 == 0) goto L_0x0155
            r7.add(r10)     // Catch:{ FMCommunicationMessageException -> 0x0167 }
        L_0x0155:
            int r9 = r9 + 1
            goto L_0x0145
        L_0x0158:
            cn.com.fmsh.util.log.FMLog r6 = r4.fmLog     // Catch:{ FMCommunicationMessageException -> 0x0167 }
            if (r6 == 0) goto L_0x0166
            cn.com.fmsh.util.log.FMLog r6 = r4.fmLog     // Catch:{ FMCommunicationMessageException -> 0x0167 }
            java.lang.String r8 = r4.logTag     // Catch:{ FMCommunicationMessageException -> 0x0167 }
            java.lang.String r9 = "业务订单查询时,平台业务订到集合为空"
            r6.warn(r8, r9)     // Catch:{ FMCommunicationMessageException -> 0x0167 }
        L_0x0166:
            return r7
        L_0x0167:
            cn.com.fmsh.util.log.FMLog r6 = r4.fmLog
            if (r6 == 0) goto L_0x0185
            cn.com.fmsh.util.log.FMLog r6 = r4.fmLog
            java.lang.String r8 = r4.logTag
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "业务订单查询时，解析平台响应数据异常："
            r9.<init>(r10)
            java.lang.String r5 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r5)
            r9.append(r5)
            java.lang.String r5 = r9.toString()
            r6.warn(r8, r5)
        L_0x0185:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r5 = r4.cardBusinessBasic
            java.lang.String r6 = "业务订单查询时，解析平台响应数据失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r8 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_command_data_invaild
            r5.throwExceptionAndClose((java.lang.String) r6, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r8, (boolean) r2)
        L_0x018f:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardAppTradeImpl.queryBusinessOrdersHandleVer3(cn.com.fmsh.tsm.business.enums.EnumOrderStatus, int, cn.com.fmsh.tsm.business.enums.EnumCardAppType, cn.com.fmsh.tsm.business.enums.EnumBusinessOrderType, cn.com.fmsh.communication.message.IMessageHandler, byte[], java.lang.String):java.util.List");
    }

    public List<BusinessOrder> queryBusinessOrdersVer4(int i, int i2, EnumCardAppType enumCardAppType, EnumBusinessOrderType enumBusinessOrderType, List<EnumOrderStatus> list, byte[] bArr) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "业务订单查询(Ver4)...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "业务订单查询，业务处理对象为空");
            }
            throw new BusinessException("业务订单查询，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "业务订单查询，消息处理器为空");
            }
            throw new BusinessException("业务订单查询，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_ORDERS_VER4);
        if (server4Business == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("业务订单查询") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("业务订单查询") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        this.cardBusinessBasic.businessReady("业务订单查询", server4Business);
        List<BusinessOrder> queryBusinessOrdersHandleVer4 = queryBusinessOrdersHandleVer4(list, i + i2, enumCardAppType, enumBusinessOrderType, messageHandler, bArr, server4Business);
        this.cardBusinessBasic.businessFinish(false);
        return queryBusinessOrdersHandleVer4;
    }

    private List<BusinessOrder> queryBusinessOrdersHandleVer4(List<EnumOrderStatus> list, int i, EnumCardAppType enumCardAppType, EnumBusinessOrderType enumBusinessOrderType, IMessageHandler iMessageHandler, byte[] bArr, String str) throws BusinessException {
        byte[] bArr2;
        BusinessOrder fromTag;
        try {
            IMessage createMessage = iMessageHandler.createMessage((int) Constants.TradeCode.QUERY_ORDERS_VER4);
            if (list != null && list.size() > 0) {
                byte[] bArr3 = new byte[list.size()];
                for (int i2 = 0; i2 < list.size(); i2++) {
                    EnumOrderStatus enumOrderStatus = list.get(i2);
                    if (enumOrderStatus != null) {
                        bArr3[i2] = (byte) enumOrderStatus.getId();
                    }
                }
                ITag createTag = iMessageHandler.createTag((byte) Constants.TagName.ORDER_TRADE_STATUSES);
                createTag.addValue(bArr3);
                createMessage.addTag(createTag);
            }
            ITag createTag2 = iMessageHandler.createTag((byte) Constants.TagName.QUERY_RECORD_COUNT);
            createTag2.addValue(i);
            createMessage.addTag(createTag2);
            if (enumCardAppType != null) {
                ITag createTag3 = iMessageHandler.createTag((byte) 14);
                createTag3.addValue(enumCardAppType.getId());
                createMessage.addTag(createTag3);
            }
            ITag createTag4 = iMessageHandler.createTag((byte) Constants.TagName.ORDER_TYPE);
            createTag4.addValue(EnumOrderType.BUSINESS.getId());
            createMessage.addTag(createTag4);
            if (enumBusinessOrderType != null) {
                ITag createTag5 = iMessageHandler.createTag((byte) Constants.TagName.BUSINESS_ORDER_TYPE);
                createTag5.addValue(enumBusinessOrderType.getId());
                createMessage.addTag(createTag5);
            }
            if (bArr != null && bArr.length > 0) {
                ITag createTag6 = iMessageHandler.createTag((byte) Constants.TagName.PATCH_DATA);
                createTag6.addValue(bArr);
                createMessage.addTag(createTag6);
            }
            bArr2 = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "业务订单查询时,构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
            }
            this.cardBusinessBasic.throwExceptionAndClose("业务订单查询时,构造平台请求数据出现失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr2 = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr2, "业务订单查询时", false, str);
        byte[] copyOf = Arrays.copyOf(interaction, 2);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, copyOf)) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, "业务订单查询时，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose("业务订单查询时，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(copyOf)), true);
        }
        ArrayList arrayList = new ArrayList();
        try {
            ITag tag4Id = iMessageHandler.createMessage(Constants.TradeCode.QUERY_ORDERS_VER2, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(27);
            if (tag4Id == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "业务订单查询时,平台没有业务订单记录");
                }
                return arrayList;
            }
            ITag[] itemTags = tag4Id.getItemTags();
            if (itemTags != null) {
                if (itemTags.length >= 1) {
                    for (ITag iTag : itemTags) {
                        if (!(iTag == null || (fromTag = BusinessOrder.fromTag(iTag)) == null)) {
                            arrayList.add(fromTag);
                        }
                    }
                    return arrayList;
                }
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "业务订单查询时,平台业务订到集合为空");
            }
            return arrayList;
        } catch (FMCommunicationMessageException unused) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "业务订单查询时，解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose("业务订单查询时，解析平台响应数据失败", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
        }
    }

    public BusinessOrder queryBusinessOrder(byte[] bArr) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            FMLog fMLog = this.fmLog;
            String str = this.logTag;
            fMLog.info(str, "业务订单[" + FM_Bytes.bytesToHexString(bArr) + "]详细信息查询...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "业务订单详细查询时，业务处理对象为空");
            }
            throw new BusinessException("业务订单详细查询时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "业务订单详细查询时，消息处理器为空");
            }
            throw new BusinessException("业务订单查询时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_ORDER_VER2);
        if (server4Business == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.warn(str2, String.valueOf("业务订单详细查询时") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("业务订单详细查询时") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        this.cardBusinessBasic.businessReady("业务订单详细查询时", server4Business);
        BusinessOrder queryBusinessOrderhandle = queryBusinessOrderhandle(bArr, messageHandler, server4Business);
        this.cardBusinessBasic.businessFinish(false);
        return queryBusinessOrderhandle;
    }

    private BusinessOrder queryBusinessOrderhandle(byte[] bArr, IMessageHandler iMessageHandler, String str) throws BusinessException {
        byte[] bArr2;
        IMessage createMessage = iMessageHandler.createMessage((int) Constants.TradeCode.QUERY_ORDER_VER2);
        try {
            ITag createTag = iMessageHandler.createTag((byte) 17);
            createTag.addValue(bArr);
            createMessage.addTag(createTag);
            ITag createTag2 = iMessageHandler.createTag((byte) Constants.TagName.ORDER_TYPE);
            createTag2.addValue(EnumOrderType.BUSINESS.getId());
            createMessage.addTag(createTag2);
            bArr2 = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "业务订单详细查询时，构造平台请求数据出现异常:" + Util4Java.getExceptionInfo(e));
            }
            this.cardBusinessBasic.throwExceptionAndClose("业务订单详细查询时，构造平台请求数据失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr2 = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr2, "业务订单详细查询时", false, str);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, Arrays.copyOf(interaction, 2))) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, "业务订单详细查询时，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose("业务订单详细查询时，平台处理失败", BusinessException.ErrorMessage.local_message_platform_business_handle_fail, true);
        }
        try {
            ITag tag4Id = iMessageHandler.createMessage(Constants.TradeCode.QUERY_ORDER_VER2, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(26);
            if (tag4Id == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "单笔订单查询时,平台响应数据没有包含1A Tag");
                }
                this.cardBusinessBasic.throwExceptionAndClose("单笔订单查询时,平台响应数据无效", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
            }
            return BusinessOrder.fromTag(tag4Id);
        } catch (FMCommunicationMessageException unused) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "单条记录查询时，解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose("单条记录查询时，解析平台响应数据失败", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
            return null;
        }
    }

    public List<PayOrder> queryPayOrders(int i, int i2, EnumCardAppType enumCardAppType) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.debug(this.logTag, "支付订单查询...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "支付订单查询，业务处理对象为空");
            }
            throw new BusinessException("支付订单查询，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "支付订单查询，消息处理器为空");
            }
            throw new BusinessException("支付订单查询，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_ORDERS_VER2);
        if (server4Business == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("支付订单查询") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("支付订单查询") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        this.cardBusinessBasic.businessReady("支付订单查询", server4Business);
        List<PayOrder> queryPayOrdersHandle = queryPayOrdersHandle(EnumOrderStatus.unknown.getId(), i + i2, enumCardAppType, messageHandler, server4Business);
        this.cardBusinessBasic.businessFinish(false);
        return queryPayOrdersHandle;
    }

    private List<PayOrder> queryPayOrdersHandle(int i, int i2, EnumCardAppType enumCardAppType, IMessageHandler iMessageHandler, String str) throws BusinessException {
        byte[] bArr;
        PayOrder fromTag;
        try {
            IMessage createMessage = iMessageHandler.createMessage((int) Constants.TradeCode.QUERY_ORDERS_VER2);
            ITag createTag = iMessageHandler.createTag((byte) Constants.TagName.ORDER_TRADE_STATUSES);
            createTag.addValue(i);
            createMessage.addTag(createTag);
            ITag createTag2 = iMessageHandler.createTag((byte) Constants.TagName.QUERY_RECORD_COUNT);
            createTag2.addValue(i2);
            createMessage.addTag(createTag2);
            if (enumCardAppType != null) {
                ITag createTag3 = iMessageHandler.createTag((byte) 14);
                createTag3.addValue(enumCardAppType.getId());
                createMessage.addTag(createTag3);
            }
            ITag createTag4 = iMessageHandler.createTag((byte) Constants.TagName.ORDER_TYPE);
            createTag4.addValue(EnumOrderType.PAY.getId());
            createMessage.addTag(createTag4);
            bArr = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "支付订单查询时,构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
            }
            this.cardBusinessBasic.throwExceptionAndClose("支付订单查询时,构造平台请求数据出现失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr, "支付订单查询时", false, str);
        byte[] copyOf = Arrays.copyOf(interaction, 2);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, copyOf)) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, "支付订单查询时，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose("支付订单查询时，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(copyOf)), true);
        }
        ArrayList arrayList = new ArrayList();
        try {
            ITag tag4Id = iMessageHandler.createMessage(Constants.TradeCode.QUERY_ORDERS_VER2, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(100);
            if (tag4Id == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "支付订单查询时,平台没有支付订单记录");
                }
                return arrayList;
            }
            ITag[] itemTags = tag4Id.getItemTags();
            if (itemTags != null) {
                if (itemTags.length >= 1) {
                    for (ITag iTag : itemTags) {
                        if (!(iTag == null || (fromTag = PayOrder.fromTag(iTag)) == null)) {
                            arrayList.add(fromTag);
                        }
                    }
                    return arrayList;
                }
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "支付订单查询时,平台响应为空");
            }
            return arrayList;
        } catch (FMCommunicationMessageException unused) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "支付订单查询时，解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose("支付订单查询时，解析平台响应数据失败", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
        }
    }

    public List<PayOrder> queryPayOrdersVer4(int i, int i2, EnumCardAppType enumCardAppType) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "支付订单查询...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "支付订单查询，业务处理对象为空");
            }
            throw new BusinessException("支付订单查询，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "支付订单查询，消息处理器为空");
            }
            throw new BusinessException("支付订单查询，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_ORDERS_VER4);
        if (server4Business == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("支付订单查询") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("支付订单查询") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        this.cardBusinessBasic.businessReady("支付订单查询", server4Business);
        List<PayOrder> queryPayOrdersHandleVer4 = queryPayOrdersHandleVer4((EnumOrderStatus) null, i + i2, enumCardAppType, messageHandler, server4Business);
        this.cardBusinessBasic.businessFinish(false);
        return queryPayOrdersHandleVer4;
    }

    private List<PayOrder> queryPayOrdersHandleVer4(EnumOrderStatus enumOrderStatus, int i, EnumCardAppType enumCardAppType, IMessageHandler iMessageHandler, String str) throws BusinessException {
        byte[] bArr;
        PayOrder fromTag;
        try {
            IMessage createMessage = iMessageHandler.createMessage((int) Constants.TradeCode.QUERY_ORDERS_VER4);
            if (enumOrderStatus != null) {
                ITag createTag = iMessageHandler.createTag((byte) Constants.TagName.ORDER_TRADE_STATUSES);
                createTag.addValue(new byte[]{(byte) enumOrderStatus.getId()});
                createMessage.addTag(createTag);
            }
            ITag createTag2 = iMessageHandler.createTag((byte) Constants.TagName.QUERY_RECORD_COUNT);
            createTag2.addValue(i);
            createMessage.addTag(createTag2);
            if (enumCardAppType != null) {
                ITag createTag3 = iMessageHandler.createTag((byte) 14);
                createTag3.addValue(enumCardAppType.getId());
                createMessage.addTag(createTag3);
            }
            ITag createTag4 = iMessageHandler.createTag((byte) Constants.TagName.ORDER_TYPE);
            createTag4.addValue(EnumOrderType.PAY.getId());
            createMessage.addTag(createTag4);
            bArr = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "支付订单查询时,构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
            }
            this.cardBusinessBasic.throwExceptionAndClose("支付订单查询时,构造平台请求数据出现失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr, "支付订单查询时", false, str);
        byte[] copyOf = Arrays.copyOf(interaction, 2);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, copyOf)) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, "支付订单查询时，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose("支付订单查询时，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(copyOf)), true);
        }
        ArrayList arrayList = new ArrayList();
        try {
            ITag tag4Id = iMessageHandler.createMessage(Constants.TradeCode.QUERY_ORDERS_VER2, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(100);
            if (tag4Id == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "支付订单查询时,平台没有支付订单记录");
                }
                return arrayList;
            }
            ITag[] itemTags = tag4Id.getItemTags();
            if (itemTags != null) {
                if (itemTags.length >= 1) {
                    for (ITag iTag : itemTags) {
                        if (!(iTag == null || (fromTag = PayOrder.fromTag(iTag)) == null)) {
                            arrayList.add(fromTag);
                        }
                    }
                    return arrayList;
                }
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "支付订单查询时,平台响应为空");
            }
            return arrayList;
        } catch (FMCommunicationMessageException unused) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "支付订单查询时，解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose("支付订单查询时，解析平台响应数据失败", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
        }
    }

    public PayOrder queryPayOrder(byte[] bArr) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            FMLog fMLog = this.fmLog;
            String str = this.logTag;
            fMLog.info(str, "支付订单[" + FM_Bytes.bytesToHexString(bArr) + "]详细信息查询...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "支付订单详细查询时，业务处理对象为空");
            }
            throw new BusinessException("支付订单详细查询时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "支付订单详细查询时，消息处理器为空");
            }
            throw new BusinessException("支付订单查询时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_ORDER_VER2);
        if (server4Business == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.warn(str2, String.valueOf("支付订单详细查询时") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("支付订单详细查询时") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        this.cardBusinessBasic.businessReady("支付订单详细查询时", server4Business);
        PayOrder queryPayOrderhandle = queryPayOrderhandle(bArr, messageHandler, server4Business);
        this.cardBusinessBasic.businessFinish(false);
        return queryPayOrderhandle;
    }

    private PayOrder queryPayOrderhandle(byte[] bArr, IMessageHandler iMessageHandler, String str) throws BusinessException {
        byte[] bArr2;
        IMessage createMessage = iMessageHandler.createMessage((int) Constants.TradeCode.QUERY_ORDER_VER2);
        try {
            ITag createTag = iMessageHandler.createTag((byte) Constants.TagName.PAY_ORDER_ID);
            createTag.addValue(bArr);
            createMessage.addTag(createTag);
            ITag createTag2 = iMessageHandler.createTag((byte) Constants.TagName.ORDER_TYPE);
            createTag2.addValue(EnumOrderType.PAY.getId());
            createMessage.addTag(createTag2);
            bArr2 = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "支付订单详细查询时，构造平台请求数据出现异常:" + Util4Java.getExceptionInfo(e));
            }
            this.cardBusinessBasic.throwExceptionAndClose("支付订单详细查询时，构造平台请求数据失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr2 = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr2, "支付订单详细查询时", false, str);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, Arrays.copyOf(interaction, 2))) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, "支付订单详细查询时，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose("支付订单详细查询时，平台处理失败", BusinessException.ErrorMessage.local_message_platform_business_handle_fail, true);
        }
        try {
            ITag tag4Id = iMessageHandler.createMessage(Constants.TradeCode.QUERY_ORDER_VER2, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(99);
            if (tag4Id == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "单笔订单查询时,平台响应数据没有包含63 Tag");
                }
                this.cardBusinessBasic.throwExceptionAndClose("单笔订单查询时,平台响应数据无效", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
            }
            return PayOrder.fromTag(tag4Id);
        } catch (FMCommunicationMessageException unused) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "单条记录查询时，解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose("单条记录查询时，解析平台响应数据失败", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
            return null;
        }
    }

    public BusinessOrder queryOrder(byte[] bArr) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.debug(this.logTag, "业务订单详细信息查询...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "单笔订单查询时，业务处理对象为空");
            }
            throw new BusinessException("单笔订单查询时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "单笔订单查询时，消息处理器为空");
            }
            throw new BusinessException("单笔订单查询时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_ORDER);
        if (server4Business == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("单笔订单查询") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("单笔订单查询") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        this.cardBusinessBasic.businessReady("单笔订单查询", server4Business);
        BusinessOrder queryOrderhandle = queryOrderhandle(bArr, messageHandler, server4Business);
        this.cardBusinessBasic.businessFinish(false);
        return queryOrderhandle;
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:0x0169  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private cn.com.fmsh.tsm.business.bean.BusinessOrder queryOrderhandle(byte[] r9, cn.com.fmsh.communication.message.IMessageHandler r10, java.lang.String r11) throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r8 = this;
            r0 = 1121(0x461, float:1.571E-42)
            cn.com.fmsh.communication.message.IMessage r1 = r10.createMessage((int) r0)
            r2 = 17
            r3 = 0
            r4 = 0
            cn.com.fmsh.communication.message.ITag r2 = r10.createTag((byte) r2)     // Catch:{ FMCommunicationMessageException -> 0x0019 }
            r2.addValue((byte[]) r9)     // Catch:{ FMCommunicationMessageException -> 0x0019 }
            r1.addTag(r2)     // Catch:{ FMCommunicationMessageException -> 0x0019 }
            byte[] r9 = r1.toBytes()     // Catch:{ FMCommunicationMessageException -> 0x0019 }
            goto L_0x0043
        L_0x0019:
            r9 = move-exception
            cn.com.fmsh.util.log.FMLog r1 = r8.fmLog
            if (r1 == 0) goto L_0x0038
            cn.com.fmsh.util.log.FMLog r1 = r8.fmLog
            java.lang.String r2 = r8.logTag
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "单笔订单查询时，构造平台请求数据出现异常:"
            r5.<init>(r6)
            java.lang.String r9 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r9)
            r5.append(r9)
            java.lang.String r9 = r5.toString()
            r1.warn(r2, r9)
        L_0x0038:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r9 = r8.cardBusinessBasic
            java.lang.String r1 = "单笔订单查询时，构造平台请求数据失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r2 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r9.throwExceptionAndClose((java.lang.String) r1, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r2, (boolean) r4)
            r9 = r3
        L_0x0043:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r1 = r8.cardBusinessBasic
            java.lang.String r2 = "单笔订单查询时"
            byte[] r9 = r1.interaction(r9, r2, r4, r11)
            byte[] r11 = cn.com.fmsh.tsm.business.constants.Constants.RespCodeonse4Platform.SUCESS
            r1 = 2
            byte[] r2 = java.util.Arrays.copyOf(r9, r1)
            boolean r11 = java.util.Arrays.equals(r11, r2)
            r2 = 1
            if (r11 != 0) goto L_0x0082
            cn.com.fmsh.util.log.FMLog r11 = r8.fmLog
            if (r11 == 0) goto L_0x0078
            cn.com.fmsh.util.log.FMLog r11 = r8.fmLog
            java.lang.String r5 = r8.logTag
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "单笔订单查询时，平台响应错误响应码: "
            r6.<init>(r7)
            java.lang.String r7 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r9)
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r11.error(r5, r6)
        L_0x0078:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r11 = r8.cardBusinessBasic
            java.lang.String r5 = "单笔订单查询，平台处理失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_platform_business_handle_fail
            r11.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r2)
        L_0x0082:
            int r11 = r9.length     // Catch:{ FMCommunicationMessageException -> 0x0164 }
            byte[] r11 = java.util.Arrays.copyOfRange(r9, r1, r11)     // Catch:{ FMCommunicationMessageException -> 0x0164 }
            cn.com.fmsh.communication.message.IMessage r10 = r10.createMessage(r0, r11)     // Catch:{ FMCommunicationMessageException -> 0x0164 }
            r11 = 26
            cn.com.fmsh.communication.message.ITag r10 = r10.getTag4Id(r11)     // Catch:{ FMCommunicationMessageException -> 0x0164 }
            if (r10 != 0) goto L_0x00ab
            cn.com.fmsh.util.log.FMLog r11 = r8.fmLog     // Catch:{ FMCommunicationMessageException -> 0x0164 }
            if (r11 == 0) goto L_0x00a1
            cn.com.fmsh.util.log.FMLog r11 = r8.fmLog     // Catch:{ FMCommunicationMessageException -> 0x0164 }
            java.lang.String r0 = r8.logTag     // Catch:{ FMCommunicationMessageException -> 0x0164 }
            java.lang.String r1 = "单笔订单查询时,平台响应数据没有包含1A Tag"
            r11.warn(r0, r1)     // Catch:{ FMCommunicationMessageException -> 0x0164 }
        L_0x00a1:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r11 = r8.cardBusinessBasic     // Catch:{ FMCommunicationMessageException -> 0x0164 }
            java.lang.String r0 = "单笔订单查询时,平台响应数据无效"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r1 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_command_data_invaild     // Catch:{ FMCommunicationMessageException -> 0x0164 }
            r11.throwExceptionAndClose((java.lang.String) r0, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r1, (boolean) r4)     // Catch:{ FMCommunicationMessageException -> 0x0164 }
        L_0x00ab:
            cn.com.fmsh.communication.message.ITag[] r10 = r10.getItemTags()     // Catch:{ FMCommunicationMessageException -> 0x0164 }
            if (r10 == 0) goto L_0x00b4
            int r11 = r10.length     // Catch:{ FMCommunicationMessageException -> 0x0164 }
            if (r11 >= r2) goto L_0x00cc
        L_0x00b4:
            cn.com.fmsh.util.log.FMLog r11 = r8.fmLog     // Catch:{ FMCommunicationMessageException -> 0x0164 }
            if (r11 == 0) goto L_0x00c2
            cn.com.fmsh.util.log.FMLog r11 = r8.fmLog     // Catch:{ FMCommunicationMessageException -> 0x0164 }
            java.lang.String r0 = r8.logTag     // Catch:{ FMCommunicationMessageException -> 0x0164 }
            java.lang.String r1 = "单笔订单查询时,平台响应数据A1 Tag无数据"
            r11.warn(r0, r1)     // Catch:{ FMCommunicationMessageException -> 0x0164 }
        L_0x00c2:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r11 = r8.cardBusinessBasic     // Catch:{ FMCommunicationMessageException -> 0x0164 }
            java.lang.String r0 = "单笔订单查询时,平台响应数据无效"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r1 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_command_data_invaild     // Catch:{ FMCommunicationMessageException -> 0x0164 }
            r11.throwExceptionAndClose((java.lang.String) r0, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r1, (boolean) r4)     // Catch:{ FMCommunicationMessageException -> 0x0164 }
        L_0x00cc:
            cn.com.fmsh.tsm.business.bean.BusinessOrder r11 = new cn.com.fmsh.tsm.business.bean.BusinessOrder     // Catch:{ FMCommunicationMessageException -> 0x0164 }
            r11.<init>()     // Catch:{ FMCommunicationMessageException -> 0x0164 }
            int r0 = r10.length     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            r1 = 0
        L_0x00d3:
            if (r1 < r0) goto L_0x00d7
            goto L_0x018d
        L_0x00d7:
            r2 = r10[r1]     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            if (r2 != 0) goto L_0x00dd
            goto L_0x0160
        L_0x00dd:
            byte r3 = r2.getId()     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            r5 = 13
            if (r3 == r5) goto L_0x0159
            r5 = 47
            if (r3 == r5) goto L_0x014d
            switch(r3) {
                case 15: goto L_0x0141;
                case 16: goto L_0x0135;
                case 17: goto L_0x012d;
                default: goto L_0x00ec;
            }     // Catch:{ FMCommunicationMessageException -> 0x0165 }
        L_0x00ec:
            switch(r3) {
                case 19: goto L_0x0125;
                case 20: goto L_0x011d;
                case 21: goto L_0x0111;
                case 22: goto L_0x0105;
                case 23: goto L_0x00f9;
                case 24: goto L_0x00f1;
                default: goto L_0x00ef;
            }     // Catch:{ FMCommunicationMessageException -> 0x0165 }
        L_0x00ef:
            goto L_0x0160
        L_0x00f1:
            int r2 = r2.getIntVal()     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            r11.setInvoiceStatus(r2)     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            goto L_0x0160
        L_0x00f9:
            java.lang.String r2 = r2.getStringVal()     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            byte[] r2 = cn.com.fmsh.util.FM_Bytes.hexStringToBytes(r2)     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            r11.setTerminalNo(r2)     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            goto L_0x0160
        L_0x0105:
            byte[] r2 = r2.getBytesVal()     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            int r2 = cn.com.fmsh.util.FM_Bytes.bytesToInt(r2)     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            r11.setSerialNo(r2)     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            goto L_0x0160
        L_0x0111:
            int r2 = r2.getIntVal()     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            cn.com.fmsh.tsm.business.enums.EnumOrderStatus r2 = cn.com.fmsh.tsm.business.enums.EnumOrderStatus.getOrderStatus4ID(r2)     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            r11.setTradeState(r2)     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            goto L_0x0160
        L_0x011d:
            java.lang.String r2 = r2.getStringVal()     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            r11.setTradeTime(r2)     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            goto L_0x0160
        L_0x0125:
            java.lang.String r2 = r2.getStringVal()     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            r11.setTradeDate(r2)     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            goto L_0x0160
        L_0x012d:
            byte[] r2 = r2.getBytesVal()     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            r11.setOrder(r2)     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            goto L_0x0160
        L_0x0135:
            byte[] r2 = r2.getBytesVal()     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            int r2 = cn.com.fmsh.util.FM_Bytes.bytesToInt(r2)     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            r11.setAmount(r2)     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            goto L_0x0160
        L_0x0141:
            java.lang.String r2 = r2.getStringVal()     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            byte[] r2 = cn.com.fmsh.util.FM_Bytes.hexStringToBytes(r2)     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            r11.setCardNo(r2)     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            goto L_0x0160
        L_0x014d:
            int r2 = r2.getIntVal()     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            cn.com.fmsh.tsm.business.enums.EnumCardIoType r2 = cn.com.fmsh.tsm.business.enums.EnumCardIoType.getCardIoType(r2)     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            r11.setCardIoType(r2)     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            goto L_0x0160
        L_0x0159:
            int r2 = r2.getIntVal()     // Catch:{ FMCommunicationMessageException -> 0x0165 }
            r11.setPayChannel(r2)     // Catch:{ FMCommunicationMessageException -> 0x0165 }
        L_0x0160:
            int r1 = r1 + 1
            goto L_0x00d3
        L_0x0164:
            r11 = r3
        L_0x0165:
            cn.com.fmsh.util.log.FMLog r10 = r8.fmLog
            if (r10 == 0) goto L_0x0183
            cn.com.fmsh.util.log.FMLog r10 = r8.fmLog
            java.lang.String r0 = r8.logTag
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "单条记录查询时，解析平台响应数据异常："
            r1.<init>(r2)
            java.lang.String r9 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r9)
            r1.append(r9)
            java.lang.String r9 = r1.toString()
            r10.warn(r0, r9)
        L_0x0183:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r9 = r8.cardBusinessBasic
            java.lang.String r10 = "单条记录查询时，解析平台响应数据失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r0 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_command_data_invaild
            r9.throwExceptionAndClose((java.lang.String) r10, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r0, (boolean) r4)
        L_0x018d:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardAppTradeImpl.queryOrderhandle(byte[], cn.com.fmsh.communication.message.IMessageHandler, java.lang.String):cn.com.fmsh.tsm.business.bean.BusinessOrder");
    }

    public List<BusinessOrder> queryUnsolvedOrder(EnumCardAppType enumCardAppType) throws BusinessException {
        if (this.cardBusinessBasic == null) {
            if (this.fmLog == null) {
                this.fmLog = LogFactory.getInstance().getLog();
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "未决交易查询时，业务处理对象为空");
            }
            throw new BusinessException("未决交易查询时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "未决交易查询时，消息处理器为空");
            }
            throw new BusinessException("未决交易查询时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_ORDERS_VER2);
        if (server4Business == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf(" 未决交易查询") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf(" 未决交易查询") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        this.cardBusinessBasic.businessReady(" 未决交易查询", server4Business);
        EnumCardAppType enumCardAppType2 = enumCardAppType;
        IMessageHandler iMessageHandler = messageHandler;
        String str2 = server4Business;
        List<BusinessOrder> queryBusinessOrdersHandle = queryBusinessOrdersHandle(EnumOrderStatus.unsettled, 10, enumCardAppType2, EnumBusinessOrderType.ORDER_TYPE_RECHARGE, iMessageHandler, str2);
        List<BusinessOrder> queryBusinessOrdersHandle2 = queryBusinessOrdersHandle(EnumOrderStatus.hasPaid, 10, enumCardAppType2, EnumBusinessOrderType.ORDER_TYPE_RECHARGE, iMessageHandler, str2);
        List<BusinessOrder> queryBusinessOrdersHandle3 = queryBusinessOrdersHandle(EnumOrderStatus.failure, 10, enumCardAppType2, EnumBusinessOrderType.ORDER_TYPE_RECHARGE, iMessageHandler, str2);
        for (BusinessOrder add : queryBusinessOrdersHandle2) {
            queryBusinessOrdersHandle.add(add);
        }
        for (BusinessOrder add2 : queryBusinessOrdersHandle3) {
            queryBusinessOrdersHandle.add(add2);
        }
        this.cardBusinessBasic.businessFinish(false);
        return queryBusinessOrdersHandle;
    }

    public List<BusinessOrder> queryConfirmDoubtOrder(EnumCardAppType enumCardAppType) throws BusinessException {
        if (this.cardBusinessBasic == null) {
            if (this.fmLog == null) {
                this.fmLog = LogFactory.getInstance().getLog();
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "确认可疑订单查询，业务处理对象为空");
            }
            throw new BusinessException("确认可疑订单查询，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "确认可疑订单查询，消息处理器为空");
            }
            throw new BusinessException("确认可疑订单查询，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_ORDERS_VER2);
        if (server4Business == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf(" 确认可疑订单查询") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf(" 确认可疑订单查询") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        this.cardBusinessBasic.businessReady(" 确认可疑订单查询", server4Business);
        List<BusinessOrder> queryBusinessOrdersHandle = queryBusinessOrdersHandle(EnumOrderStatus.dubious, 10, enumCardAppType, EnumBusinessOrderType.ORDER_TYPE_RECHARGE, messageHandler, server4Business);
        this.cardBusinessBasic.businessFinish(false);
        return queryBusinessOrdersHandle;
    }

    /* JADX WARNING: Removed duplicated region for block: B:126:0x02af  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x02c5 A[SYNTHETIC, Splitter:B:129:0x02c5] */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x02cd A[Catch:{ FMCommunicationMessageException -> 0x02d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x02f6  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x0314  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x031e A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x01dc  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x020a  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x020c  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x020f  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0211  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int doUnsolvedOrder(byte[] r17, byte[] r18) throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r16 = this;
            r1 = r16
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x000f
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r2 = r1.logTag
            java.lang.String r3 = "doUnsolvedOrder start...."
            r0.warn(r2, r3)
        L_0x000f:
            java.lang.String r2 = "处理未决交易"
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 != 0) goto L_0x0020
            cn.com.fmsh.util.log.LogFactory r0 = cn.com.fmsh.util.log.LogFactory.getInstance()
            cn.com.fmsh.util.log.FMLog r0 = r0.getLog()
            r1.fmLog = r0
        L_0x0020:
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x002e
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r3 = r1.logTag
            java.lang.String r4 = "处理未决交易..."
            r0.info(r3, r4)
        L_0x002e:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            if (r0 != 0) goto L_0x004b
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x0040
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r2 = r1.logTag
            java.lang.String r3 = "处理未决交易时，业务处理对象为空"
            r0.warn(r2, r3)
        L_0x0040:
            cn.com.fmsh.tsm.business.exception.BusinessException r0 = new cn.com.fmsh.tsm.business.exception.BusinessException
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r2 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_init_fail
            java.lang.String r3 = "处理未决交易时，业务处理器初始化失败"
            r0.<init>(r3, r2)
            throw r0
        L_0x004b:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            cn.com.fmsh.communication.message.IMessageHandler r3 = r0.getMessageHandler()
            if (r3 != 0) goto L_0x006c
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x0061
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r2 = r1.logTag
            java.lang.String r3 = "处理未决交易时，消息处理器为空"
            r0.warn(r2, r3)
        L_0x0061:
            cn.com.fmsh.tsm.business.exception.BusinessException r0 = new cn.com.fmsh.tsm.business.exception.BusinessException
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r2 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_load_config_fail
            java.lang.String r3 = "处理未决交易时，消息处理器为空"
            r0.<init>(r3, r2)
            throw r0
        L_0x006c:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            cn.com.fmsh.script.ApduHandler r0 = r0.getApduHandler()
            if (r0 != 0) goto L_0x008d
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x0082
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r2 = r1.logTag
            java.lang.String r3 = "处理未决交易时，apdu指令处理器为空"
            r0.warn(r2, r3)
        L_0x0082:
            cn.com.fmsh.tsm.business.exception.BusinessException r0 = new cn.com.fmsh.tsm.business.exception.BusinessException
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r2 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_null
            java.lang.String r3 = "上海交通卡空中充值时，请先切换卡的访问方式(OMA/NFC)"
            r0.<init>(r3, r2)
            throw r0
        L_0x008d:
            boolean r4 = r0.isConnect()
            r5 = 0
            r6 = 1
            if (r4 == 0) goto L_0x00ae
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x00a3
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r4 = r1.logTag
            java.lang.String r7 = "处理未决交易时，APDU处理器正忙"
            r0.error(r4, r7)
        L_0x00a3:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r4 = "处理未决交易时，APDU处理器正忙"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r7 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_busying
            r0.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r7, (boolean) r5)
            goto L_0x00cc
        L_0x00ae:
            boolean r0 = r0.connect()
            if (r0 != 0) goto L_0x00cc
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x00c2
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r4 = r1.logTag
            java.lang.String r7 = "处理未决交易时，连接卡失败"
            r0.error(r4, r7)
        L_0x00c2:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r4 = "处理未决交易时，连接卡失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r7 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception
            r0.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r7, (boolean) r6)
        L_0x00cc:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            r4 = 3021(0xbcd, float:4.233E-42)
            java.lang.String r7 = r0.getServer4Business(r4)
            if (r7 != 0) goto L_0x010f
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x00f4
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r3 = r1.logTag
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = java.lang.String.valueOf(r2)
            r4.<init>(r5)
            java.lang.String r5 = "时，获取处理的平台失败"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r0.warn(r3, r4)
        L_0x00f4:
            cn.com.fmsh.tsm.business.exception.BusinessException r0 = new cn.com.fmsh.tsm.business.exception.BusinessException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r3.<init>(r2)
            java.lang.String r2 = "时，获取处理的平台失败"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_app_query_server_fail
            r0.<init>(r2, r3)
            throw r0
        L_0x010f:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            r0.businessReady(r2, r7)
            cn.com.fmsh.communication.message.IMessage r0 = r3.createMessage((int) r4)
            r4 = 17
            cn.com.fmsh.communication.message.ITag r4 = r3.createTag((byte) r4)     // Catch:{ FMCommunicationMessageException -> 0x012b }
            r9 = r17
            r4.addValue((byte[]) r9)     // Catch:{ FMCommunicationMessageException -> 0x012b }
            r0.addTag(r4)     // Catch:{ FMCommunicationMessageException -> 0x012b }
            byte[] r0 = r0.toBytes()     // Catch:{ FMCommunicationMessageException -> 0x012b }
            goto L_0x0155
        L_0x012b:
            r0 = move-exception
            cn.com.fmsh.util.log.FMLog r4 = r1.fmLog
            if (r4 == 0) goto L_0x014a
            cn.com.fmsh.util.log.FMLog r4 = r1.fmLog
            java.lang.String r9 = r1.logTag
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "未决交易处理,构造平台请求数据异常："
            r10.<init>(r11)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r10.append(r0)
            java.lang.String r0 = r10.toString()
            r4.warn(r9, r0)
        L_0x014a:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r4 = "未决交易处理,构造平台请求数据失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r9 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r0.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r9, (boolean) r6)
            r0 = 0
        L_0x0155:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r4 = r1.cardBusinessBasic
            java.lang.String r9 = "未决交易处理"
            byte[] r0 = r4.interaction(r0, r9, r6, r7)
            r4 = 2
            byte[] r9 = java.util.Arrays.copyOf(r0, r4)
            r10 = 0
            r11 = 0
            r12 = 0
        L_0x0166:
            byte[] r13 = cn.com.fmsh.tsm.business.constants.Constants.RespCodeonse4Platform.CARD_REQUEST
            boolean r13 = java.util.Arrays.equals(r13, r9)
            if (r13 != 0) goto L_0x019f
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r1.cardBusinessBasic
            r2.businessFinish(r6)
            byte[] r2 = cn.com.fmsh.tsm.business.constants.Constants.RespCodeonse4Platform.SUCESS
            boolean r2 = java.util.Arrays.equals(r2, r9)
            if (r2 != 0) goto L_0x019e
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            if (r2 == 0) goto L_0x0199
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            java.lang.String r3 = r1.logTag
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "未决交易处理，平台响应错误响应码: "
            r4.<init>(r5)
            java.lang.String r0 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r0)
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            r2.warn(r3, r0)
        L_0x0199:
            int r0 = cn.com.fmsh.util.FM_CN.bcdBytesToInt(r9)
            return r0
        L_0x019e:
            return r5
        L_0x019f:
            cn.com.fmsh.script.bean.ApduRequestList r9 = new cn.com.fmsh.script.bean.ApduRequestList
            r9.<init>()
            cn.com.fmsh.communication.message.IMessage r0 = r3.createMessage((byte[]) r0)     // Catch:{ FMCommunicationMessageException -> 0x01d5 }
            r13 = -96
            cn.com.fmsh.communication.message.ITag r13 = r0.getTag4Id(r13)     // Catch:{ FMCommunicationMessageException -> 0x01d5 }
            r14 = -95
            cn.com.fmsh.communication.message.ITag r14 = r0.getTag4Id(r14)     // Catch:{ FMCommunicationMessageException -> 0x01d3 }
            r15 = -90
            cn.com.fmsh.communication.message.ITag r15 = r0.getTag4Id(r15)     // Catch:{ FMCommunicationMessageException -> 0x01d1 }
            r5 = -89
            cn.com.fmsh.communication.message.ITag r0 = r0.getTag4Id(r5)     // Catch:{ FMCommunicationMessageException -> 0x01d1 }
            r5 = 9001(0x2329, float:1.2613E-41)
            cn.com.fmsh.communication.message.IMessage r5 = r3.createMessage((int) r5)     // Catch:{ FMCommunicationMessageException -> 0x01d1 }
            r5.addTag(r15)     // Catch:{ FMCommunicationMessageException -> 0x01ce }
            r5.addTag(r0)     // Catch:{ FMCommunicationMessageException -> 0x01ce }
            r12 = r5
            goto L_0x0207
        L_0x01ce:
            r0 = move-exception
            r12 = r5
            goto L_0x01d8
        L_0x01d1:
            r0 = move-exception
            goto L_0x01d8
        L_0x01d3:
            r0 = move-exception
            goto L_0x01d7
        L_0x01d5:
            r0 = move-exception
            r13 = 0
        L_0x01d7:
            r14 = 0
        L_0x01d8:
            cn.com.fmsh.util.log.FMLog r5 = r1.fmLog
            if (r5 == 0) goto L_0x01fd
            cn.com.fmsh.util.log.FMLog r5 = r1.fmLog
            java.lang.String r15 = "logTag"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r4 = java.lang.String.valueOf(r2)
            r8.<init>(r4)
            java.lang.String r4 = "解析平台响应数据出现异常:"
            r8.append(r4)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r8.append(r0)
            java.lang.String r0 = r8.toString()
            r5.warn(r15, r0)
        L_0x01fd:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r4 = "解析平台响应数据失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_command_data_invaild
            r0.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r6)
        L_0x0207:
            r8 = r13
            if (r8 == 0) goto L_0x020c
            r11 = 0
            goto L_0x020d
        L_0x020c:
            r8 = 0
        L_0x020d:
            if (r14 == 0) goto L_0x0211
            r11 = 1
            goto L_0x0212
        L_0x0211:
            r14 = r8
        L_0x0212:
            r9.fromTag(r14)     // Catch:{ FMCommunicationMessageException -> 0x0241, FMScriptHandleException -> 0x0216 }
            goto L_0x026b
        L_0x0216:
            r0 = move-exception
            r4 = r0
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x0236
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r5 = r1.logTag
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r13 = "业务处理时，平台响应的数据解析异常"
            r8.<init>(r13)
            java.lang.String r4 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r4)
            r8.append(r4)
            java.lang.String r4 = r8.toString()
            r0.warn(r5, r4)
        L_0x0236:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r4 = "平台响应的数据解析失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_communication_invalid_response
            r0.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r6)
            goto L_0x026b
        L_0x0241:
            r0 = move-exception
            r4 = r0
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x0261
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r5 = r1.logTag
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r13 = "业务处理时，平台响应的数据解析异常："
            r8.<init>(r13)
            java.lang.String r4 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r4)
            r8.append(r4)
            java.lang.String r4 = r8.toString()
            r0.warn(r5, r4)
        L_0x0261:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r4 = "平台响应的数据解析失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_communication_invalid_response
            r0.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r6)
        L_0x026b:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic     // Catch:{ FMScriptHandleException -> 0x0277 }
            cn.com.fmsh.script.ScriptHandler r0 = r0.getScriptHandler()     // Catch:{ FMScriptHandleException -> 0x0277 }
            cn.com.fmsh.script.bean.ApduReponseList r0 = r0.execute((cn.com.fmsh.script.bean.ApduRequestList) r9)     // Catch:{ FMScriptHandleException -> 0x0277 }
            r10 = r0
            goto L_0x02a3
        L_0x0277:
            r0 = move-exception
            cn.com.fmsh.util.log.FMLog r4 = r1.fmLog
            if (r4 == 0) goto L_0x0286
            cn.com.fmsh.util.log.FMLog r4 = r1.fmLog
            java.lang.String r5 = r1.logTag
            java.lang.String r8 = "业务处理时，脚本执行出现异常"
            r4.warn(r5, r8)
        L_0x0286:
            cn.com.fmsh.script.exception.FMScriptHandleException$ScriptHandleExceptionType r4 = cn.com.fmsh.script.exception.FMScriptHandleException.ScriptHandleExceptionType.STOPED
            cn.com.fmsh.script.exception.FMScriptHandleException$ScriptHandleExceptionType r0 = r0.getType()
            if (r4 != r0) goto L_0x0299
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r4 = "业务处理被取消"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_cancel
            r0.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r6)
            goto L_0x02a3
        L_0x0299:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r4 = "本地执行执行失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception
            r0.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r6)
        L_0x02a3:
            if (r10 == 0) goto L_0x02ab
            int r0 = r10.size()
            if (r0 >= r6) goto L_0x02c3
        L_0x02ab:
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x02b9
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r4 = r1.logTag
            java.lang.String r5 = "业务处理时，响应结果为空"
            r0.warn(r4, r5)
        L_0x02b9:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r4 = "本地执行执行失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception
            r0.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r6)
        L_0x02c3:
            if (r11 != 0) goto L_0x02cd
            cn.com.fmsh.communication.message.ITag r0 = r10.toTag4A2()     // Catch:{ FMCommunicationMessageException -> 0x02d9 }
            r12.addTag(r0)     // Catch:{ FMCommunicationMessageException -> 0x02d9 }
            goto L_0x02d4
        L_0x02cd:
            cn.com.fmsh.communication.message.ITag r0 = r10.toTag4A3()     // Catch:{ FMCommunicationMessageException -> 0x02d9 }
            r12.addTag(r0)     // Catch:{ FMCommunicationMessageException -> 0x02d9 }
        L_0x02d4:
            byte[] r8 = r12.toBytes()     // Catch:{ FMCommunicationMessageException -> 0x02d9 }
            goto L_0x02f2
        L_0x02d9:
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x02e7
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r4 = r1.logTag
            java.lang.String r5 = "业务处理时，构造终端响应数据出现异常"
            r0.warn(r4, r5)
        L_0x02e7:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r4 = "构造平台请求数据失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r0.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r6)
            r8 = 0
        L_0x02f2:
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x0310
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r4 = r1.logTag
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r9 = "执行完成 repones:"
            r5.<init>(r9)
            java.lang.String r9 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r8)
            r5.append(r9)
            java.lang.String r5 = r5.toString()
            r0.debug(r4, r5)
        L_0x0310:
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x031e
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r4 = r1.logTag
            java.lang.String r5 = "终端处理完成，发送处理结果给平台..."
            r0.debug(r4, r5)
        L_0x031e:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            byte[] r0 = r0.interaction(r8, r2, r6, r7)
            r4 = 2
            byte[] r9 = java.util.Arrays.copyOf(r0, r4)
            r5 = 0
            goto L_0x0166
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardAppTradeImpl.doUnsolvedOrder(byte[], byte[]):int");
    }

    public int doRefund(byte[] bArr) throws BusinessException {
        byte[] bArr2;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "退款申请...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog == null) {
                this.fmLog = LogFactory.getInstance().getLog();
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "退款申请时，业务处理对象为空");
            }
            throw new BusinessException("退款申请时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "退款申请时，消息处理器为空");
            }
            throw new BusinessException("退款申请时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.REFUND_VER2);
        if (server4Business == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("退款申请") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("退款申请") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        this.cardBusinessBasic.businessReady("退款申请", server4Business);
        IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.REFUND_VER2);
        try {
            ITag createTag = messageHandler.createTag((byte) Constants.TagName.MAIN_ORDER_ID);
            createTag.addValue(bArr);
            createMessage.addTag(createTag);
            bArr2 = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "退款申请时,构造平台请求数据异常:" + Util4Java.getExceptionInfo(e));
            }
            this.cardBusinessBasic.throwExceptionAndClose("退款申请时,构造平台请求数据失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr2 = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr2, "退款申请", false, server4Business);
        byte[] copyOf = Arrays.copyOf(interaction, 2);
        this.cardBusinessBasic.businessFinish(false);
        if (Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, copyOf)) {
            return 0;
        }
        if (this.fmLog != null) {
            this.fmLog.error(this.logTag, "退款申请时，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
        }
        return FM_CN.bcdBytesToInt(copyOf);
    }

    public int register(UserInfo userInfo) throws BusinessException {
        byte[] bArr;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "用户注册...");
        }
        if (userInfo == null) {
            throw new BusinessException("用户注册时，传入的用户信息为空", BusinessException.ErrorMessage.local_business_para_error);
        } else if (userInfo.getUserName() == null || userInfo.getUserName().length() < 1) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "用户注册时，传入的用户账号为空");
            }
            throw new BusinessException("用户注册时，传入的用户账号为空", BusinessException.ErrorMessage.local_business_para_error);
        } else if (userInfo.getPassword() == null || userInfo.getPassword().length() < 1) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "用户注册时，传入的用户密码为空");
            }
            throw new BusinessException("用户注册时，传入的用户密码为空", BusinessException.ErrorMessage.local_business_para_error);
        } else if (userInfo.getPassword().length() > 32) {
            throw new BusinessException("用户注册时，用户密钥长度必须小于32", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "用户注册时，业务处理对象为空");
            }
            throw new BusinessException("用户注册时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户注册时，消息处理器为空");
                }
                throw new BusinessException("处理未决交易时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(1001);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("用户注册") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("用户注册") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("用户注册", server4Business);
            IMessage createMessage = messageHandler.createMessage(1001);
            try {
                ITag createTag = messageHandler.createTag((byte) 1);
                createTag.addValue(userInfo.getUserType());
                createMessage.addTag(createTag);
                ITag createTag2 = messageHandler.createTag((byte) 2);
                createTag2.addValue(userInfo.getUserName());
                createMessage.addTag(createTag2);
                ITag createTag3 = messageHandler.createTag((byte) 3);
                createTag3.addValue(userInfo.getPassword());
                createMessage.addTag(createTag3);
                if (userInfo.getMail() != null && userInfo.getMail().length() > 1) {
                    ITag createTag4 = messageHandler.createTag((byte) 4);
                    createTag4.addValue(userInfo.getMail());
                    createMessage.addTag(createTag4);
                }
                if (userInfo.getPhone() != null && userInfo.getPhone().length() > 1) {
                    ITag createTag5 = messageHandler.createTag((byte) 5);
                    createTag5.addValue(userInfo.getPhone());
                    createMessage.addTag(createTag5);
                }
                if (userInfo.getRealName() != null && userInfo.getRealName().length() > 1) {
                    ITag createTag6 = messageHandler.createTag((byte) 6);
                    createTag6.addValue(userInfo.getRealName());
                    createMessage.addTag(createTag6);
                }
                if (userInfo.getCertType() != -1) {
                    ITag createTag7 = messageHandler.createTag((byte) 7);
                    createTag7.addValue(userInfo.getCertType());
                    createMessage.addTag(createTag7);
                }
                if (userInfo.getCertNo() != null && userInfo.getCertNo().length() > 1) {
                    ITag createTag8 = messageHandler.createTag((byte) 8);
                    createTag8.addValue(userInfo.getCertNo());
                    createMessage.addTag(createTag8);
                }
                bArr = createMessage.toBytes();
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户注册,构造平台请求数据异常:" + Util4Java.getExceptionInfo(e));
                }
                this.cardBusinessBasic.throwExceptionAndClose("用户注册,构造平台请求数据失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr = null;
            }
            byte[] interaction = this.cardBusinessBasic.interaction(bArr, "用户注册", false, server4Business);
            byte[] copyOf = Arrays.copyOf(interaction, 2);
            this.cardBusinessBasic.businessFinish(false);
            if (Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, copyOf)) {
                return 0;
            }
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, "用户注册，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
            }
            return FM_CN.bcdBytesToInt(copyOf);
        }
    }

    public int terminalInfoBack(TerminalBackInfo terminalBackInfo) throws BusinessException {
        byte[] bArr;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "终端信息反馈...");
        }
        if (terminalBackInfo == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("终端信息反馈") + "，传入的参数异常");
            }
            throw new BusinessException(String.valueOf("终端信息反馈") + "，传入的参数异常", BusinessException.ErrorMessage.local_business_para_error);
        } else if (terminalBackInfo.getTitle() == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.warn(str2, String.valueOf("终端信息反馈") + "，传入的参数异常");
            }
            throw new BusinessException(String.valueOf("终端信息反馈") + "，传入的参数异常", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog == null) {
                this.fmLog = LogFactory.getInstance().getLog();
            }
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str3 = this.logTag;
                fMLog3.warn(str3, String.valueOf("终端信息反馈") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("终端信息反馈") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    FMLog fMLog4 = this.fmLog;
                    String str4 = this.logTag;
                    fMLog4.warn(str4, String.valueOf("终端信息反馈") + "，消息处理器为空");
                }
                throw new BusinessException(String.valueOf("终端信息反馈") + "，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(4001);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    FMLog fMLog5 = this.fmLog;
                    String str5 = this.logTag;
                    fMLog5.warn(str5, String.valueOf("终端信息反馈") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("终端信息反馈") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("终端信息反馈", server4Business);
            IMessage createMessage = messageHandler.createMessage(4001);
            try {
                ITag createTag = messageHandler.createTag((byte) Constants.TagName.TERMINAL_BACK_CONTENT);
                createTag.addValue(terminalBackInfo.getTitle());
                createMessage.addTag(createTag);
                ITag createTag2 = messageHandler.createTag((byte) Constants.TagName.TERMINAL_BACK_INFO_TYPE);
                createTag2.addValue(terminalBackInfo.getInfoType().getId());
                createMessage.addTag(createTag2);
                if (terminalBackInfo.getOsVersion() != null && terminalBackInfo.getOsVersion().length() > 0) {
                    ITag createTag3 = messageHandler.createTag((byte) Constants.TagName.TERMINAL_OS_VERSION);
                    createTag3.addValue(terminalBackInfo.getOsVersion());
                    createMessage.addTag(createTag3);
                }
                if (terminalBackInfo.getModelNumber() != null && terminalBackInfo.getModelNumber().length() > 1) {
                    ITag createTag4 = messageHandler.createTag((byte) Constants.TagName.TERMINAL_MODEL_NUMBER);
                    createTag4.addValue(terminalBackInfo.getModelNumber());
                    createMessage.addTag(createTag4);
                }
                if (terminalBackInfo.getBasebandVersion() != null && terminalBackInfo.getBasebandVersion().length() > 1) {
                    ITag createTag5 = messageHandler.createTag((byte) Constants.TagName.TERMINAL_BASEBAND_VERSION);
                    createTag5.addValue(terminalBackInfo.getBasebandVersion());
                    createMessage.addTag(createTag5);
                }
                if (terminalBackInfo.getAppVersion() != null && terminalBackInfo.getAppVersion().length() > 1) {
                    ITag createTag6 = messageHandler.createTag((byte) Constants.TagName.SYSTEM_NEW_VERSION);
                    createTag6.addValue(terminalBackInfo.getAppVersion());
                    createMessage.addTag(createTag6);
                }
                if (terminalBackInfo.getId() != null && terminalBackInfo.getId().length > 1) {
                    ITag createTag7 = messageHandler.createTag((byte) Constants.TagName.TERMINAL_BACK_MAIN_ID);
                    createTag7.addValue(terminalBackInfo.getId());
                    createMessage.addTag(createTag7);
                }
                bArr = createMessage.toBytes();
            } catch (FMCommunicationMessageException unused) {
                if (this.fmLog != null) {
                    FMLog fMLog6 = this.fmLog;
                    String str6 = this.logTag;
                    fMLog6.warn(str6, String.valueOf("终端信息反馈") + ",构造平台请求数据异常");
                }
                CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
                cardBusinessBasic2.throwExceptionAndClose(String.valueOf("终端信息反馈") + ",构造平台请求数据失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr = null;
            }
            byte[] interaction = this.cardBusinessBasic.interaction(bArr, "终端信息反馈", false, server4Business);
            byte[] copyOf = Arrays.copyOf(interaction, 2);
            this.cardBusinessBasic.businessFinish(false);
            if (Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, copyOf)) {
                return 0;
            }
            if (this.fmLog != null) {
                FMLog fMLog7 = this.fmLog;
                String str7 = this.logTag;
                fMLog7.error(str7, String.valueOf("终端信息反馈") + ",，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
            }
            return FM_CN.bcdBytesToInt(copyOf);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x01c6  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x020a A[SYNTHETIC, Splitter:B:59:0x020a] */
    /* JADX WARNING: Removed duplicated region for block: B:76:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] terminalInfoReport(int r12, byte[] r13) throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r11 = this;
            java.lang.String r0 = "终端信息上报"
            cn.com.fmsh.util.log.FMLog r1 = r11.fmLog
            if (r1 != 0) goto L_0x0011
            cn.com.fmsh.util.log.LogFactory r1 = cn.com.fmsh.util.log.LogFactory.getInstance()
            cn.com.fmsh.util.log.FMLog r1 = r1.getLog()
            r11.fmLog = r1
        L_0x0011:
            cn.com.fmsh.util.log.FMLog r1 = r11.fmLog
            if (r1 == 0) goto L_0x001f
            cn.com.fmsh.util.log.FMLog r1 = r11.fmLog
            java.lang.String r2 = r11.logTag
            java.lang.String r3 = "终端信息上报..."
            r1.info(r2, r3)
        L_0x001f:
            if (r12 > 0) goto L_0x005a
            cn.com.fmsh.util.log.FMLog r12 = r11.fmLog
            if (r12 == 0) goto L_0x003f
            cn.com.fmsh.util.log.FMLog r12 = r11.fmLog
            java.lang.String r13 = r11.logTag
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = java.lang.String.valueOf(r0)
            r1.<init>(r2)
            java.lang.String r2 = "，传入的参数异常"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r12.warn(r13, r1)
        L_0x003f:
            cn.com.fmsh.tsm.business.exception.BusinessException r12 = new cn.com.fmsh.tsm.business.exception.BusinessException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r13.<init>(r0)
            java.lang.String r0 = "，传入的参数异常"
            r13.append(r0)
            java.lang.String r13 = r13.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r0 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_para_error
            r12.<init>(r13, r0)
            throw r12
        L_0x005a:
            if (r13 == 0) goto L_0x0260
            int r1 = r13.length
            r2 = 1
            if (r1 >= r2) goto L_0x0062
            goto L_0x0260
        L_0x0062:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r1 = r11.cardBusinessBasic
            if (r1 != 0) goto L_0x00ad
            cn.com.fmsh.util.log.FMLog r12 = r11.fmLog
            if (r12 != 0) goto L_0x0074
            cn.com.fmsh.util.log.LogFactory r12 = cn.com.fmsh.util.log.LogFactory.getInstance()
            cn.com.fmsh.util.log.FMLog r12 = r12.getLog()
            r11.fmLog = r12
        L_0x0074:
            cn.com.fmsh.util.log.FMLog r12 = r11.fmLog
            if (r12 == 0) goto L_0x0092
            cn.com.fmsh.util.log.FMLog r12 = r11.fmLog
            java.lang.String r13 = r11.logTag
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = java.lang.String.valueOf(r0)
            r1.<init>(r2)
            java.lang.String r2 = "，业务处理对象为空"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r12.warn(r13, r1)
        L_0x0092:
            cn.com.fmsh.tsm.business.exception.BusinessException r12 = new cn.com.fmsh.tsm.business.exception.BusinessException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r13.<init>(r0)
            java.lang.String r0 = "，业务处理器初始化失败"
            r13.append(r0)
            java.lang.String r13 = r13.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r0 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_init_fail
            r12.<init>(r13, r0)
            throw r12
        L_0x00ad:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r1 = r11.cardBusinessBasic
            cn.com.fmsh.communication.message.IMessageHandler r1 = r1.getMessageHandler()
            if (r1 != 0) goto L_0x00ee
            cn.com.fmsh.util.log.FMLog r12 = r11.fmLog
            if (r12 == 0) goto L_0x00d3
            cn.com.fmsh.util.log.FMLog r12 = r11.fmLog
            java.lang.String r13 = r11.logTag
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = java.lang.String.valueOf(r0)
            r1.<init>(r2)
            java.lang.String r2 = "，消息处理器为空"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r12.warn(r13, r1)
        L_0x00d3:
            cn.com.fmsh.tsm.business.exception.BusinessException r12 = new cn.com.fmsh.tsm.business.exception.BusinessException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r13.<init>(r0)
            java.lang.String r0 = "，消息处理器为空"
            r13.append(r0)
            java.lang.String r13 = r13.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r0 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_load_config_fail
            r12.<init>(r13, r0)
            throw r12
        L_0x00ee:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r11.cardBusinessBasic
            r4 = 4001(0xfa1, float:5.607E-42)
            java.lang.String r3 = r3.getServer4Business(r4)
            if (r3 != 0) goto L_0x0131
            cn.com.fmsh.util.log.FMLog r12 = r11.fmLog
            if (r12 == 0) goto L_0x0116
            cn.com.fmsh.util.log.FMLog r12 = r11.fmLog
            java.lang.String r13 = r11.logTag
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = java.lang.String.valueOf(r0)
            r1.<init>(r2)
            java.lang.String r2 = "时，获取处理的平台失败"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r12.warn(r13, r1)
        L_0x0116:
            cn.com.fmsh.tsm.business.exception.BusinessException r12 = new cn.com.fmsh.tsm.business.exception.BusinessException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r13.<init>(r0)
            java.lang.String r0 = "时，获取处理的平台失败"
            r13.append(r0)
            java.lang.String r13 = r13.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r0 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_app_query_server_fail
            r12.<init>(r13, r0)
            throw r12
        L_0x0131:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r4 = r11.cardBusinessBasic
            r4.businessReady(r0, r3)
            r4 = 0
            r5 = 4031(0xfbf, float:5.649E-42)
            cn.com.fmsh.communication.message.IMessage r6 = r1.createMessage((int) r5)
            r7 = 93
            r8 = -76
            r9 = 0
            cn.com.fmsh.communication.message.ITag r7 = r1.createTag((byte) r7)     // Catch:{ FMCommunicationMessageException -> 0x0174 }
            r7.addValue((int) r12)     // Catch:{ FMCommunicationMessageException -> 0x0174 }
            r6.addTag(r7)     // Catch:{ FMCommunicationMessageException -> 0x0174 }
            cn.com.fmsh.communication.message.ITag r12 = r1.createTag((byte) r8)     // Catch:{ FMCommunicationMessageException -> 0x0174 }
            r12.addValue((byte[]) r13)     // Catch:{ FMCommunicationMessageException -> 0x0174 }
            r6.addTag(r12)     // Catch:{ FMCommunicationMessageException -> 0x0174 }
            byte[] r12 = r6.toBytes()     // Catch:{ FMCommunicationMessageException -> 0x0174 }
            java.io.PrintStream r13 = java.lang.System.out     // Catch:{ FMCommunicationMessageException -> 0x0172 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ FMCommunicationMessageException -> 0x0172 }
            java.lang.String r6 = "request:"
            r4.<init>(r6)     // Catch:{ FMCommunicationMessageException -> 0x0172 }
            java.lang.String r6 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r12)     // Catch:{ FMCommunicationMessageException -> 0x0172 }
            r4.append(r6)     // Catch:{ FMCommunicationMessageException -> 0x0172 }
            java.lang.String r4 = r4.toString()     // Catch:{ FMCommunicationMessageException -> 0x0172 }
            r13.println(r4)     // Catch:{ FMCommunicationMessageException -> 0x0172 }
            goto L_0x01b3
        L_0x0172:
            r13 = move-exception
            goto L_0x0176
        L_0x0174:
            r13 = move-exception
            r12 = r4
        L_0x0176:
            cn.com.fmsh.util.log.FMLog r4 = r11.fmLog
            if (r4 == 0) goto L_0x019a
            cn.com.fmsh.util.log.FMLog r4 = r11.fmLog
            java.lang.String r6 = r11.logTag
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r10 = java.lang.String.valueOf(r0)
            r7.<init>(r10)
            java.lang.String r10 = ",构造平台请求数据异常:"
            r7.append(r10)
            java.lang.String r13 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r13)
            r7.append(r13)
            java.lang.String r13 = r7.toString()
            r4.warn(r6, r13)
        L_0x019a:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r13 = r11.cardBusinessBasic
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r6 = java.lang.String.valueOf(r0)
            r4.<init>(r6)
            java.lang.String r6 = ",构造平台请求数据失败"
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r13.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r9)
        L_0x01b3:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r13 = r11.cardBusinessBasic
            byte[] r12 = r13.interaction(r12, r0, r9, r3)
            byte[] r13 = cn.com.fmsh.tsm.business.constants.Constants.RespCodeonse4Platform.SUCESS
            r3 = 2
            byte[] r4 = java.util.Arrays.copyOf(r12, r3)
            boolean r13 = java.util.Arrays.equals(r13, r4)
            if (r13 != 0) goto L_0x0205
            cn.com.fmsh.util.log.FMLog r13 = r11.fmLog
            if (r13 == 0) goto L_0x01eb
            cn.com.fmsh.util.log.FMLog r13 = r11.fmLog
            java.lang.String r4 = r11.logTag
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = java.lang.String.valueOf(r0)
            r6.<init>(r7)
            java.lang.String r7 = "，平台响应错误响应码: "
            r6.append(r7)
            java.lang.String r7 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r12)
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r13.error(r4, r6)
        L_0x01eb:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r13 = r11.cardBusinessBasic
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r6 = java.lang.String.valueOf(r0)
            r4.<init>(r6)
            java.lang.String r6 = "，平台处理失败"
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_platform_business_handle_fail
            r13.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r2)
        L_0x0205:
            byte[] r13 = new byte[r2]
            int r2 = r12.length
            if (r2 <= r3) goto L_0x025f
            int r2 = r12.length     // Catch:{ FMCommunicationMessageException -> 0x0220 }
            byte[] r2 = java.util.Arrays.copyOfRange(r12, r3, r2)     // Catch:{ FMCommunicationMessageException -> 0x0220 }
            cn.com.fmsh.communication.message.IMessage r1 = r1.createMessage(r5, r2)     // Catch:{ FMCommunicationMessageException -> 0x0220 }
            cn.com.fmsh.communication.message.ITag r1 = r1.getTag4Id(r8)     // Catch:{ FMCommunicationMessageException -> 0x0220 }
            if (r1 != 0) goto L_0x021a
            return r13
        L_0x021a:
            byte[] r1 = r1.getBytesVal()     // Catch:{ FMCommunicationMessageException -> 0x0220 }
            r13 = r1
            goto L_0x025f
        L_0x0220:
            cn.com.fmsh.util.log.FMLog r1 = r11.fmLog
            if (r1 == 0) goto L_0x0245
            cn.com.fmsh.util.log.FMLog r1 = r11.fmLog
            java.lang.String r2 = r11.logTag
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = java.lang.String.valueOf(r0)
            r3.<init>(r4)
            java.lang.String r4 = "，解析平台响应数据异常："
            r3.append(r4)
            java.lang.String r12 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r12)
            r3.append(r12)
            java.lang.String r12 = r3.toString()
            r1.warn(r2, r12)
        L_0x0245:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r12 = r11.cardBusinessBasic
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r1.<init>(r0)
            java.lang.String r0 = "，解析平台响应数据失败"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r1 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_command_data_invaild
            r12.throwExceptionAndClose((java.lang.String) r0, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r1, (boolean) r9)
        L_0x025f:
            return r13
        L_0x0260:
            cn.com.fmsh.util.log.FMLog r12 = r11.fmLog
            if (r12 == 0) goto L_0x027e
            cn.com.fmsh.util.log.FMLog r12 = r11.fmLog
            java.lang.String r13 = r11.logTag
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = java.lang.String.valueOf(r0)
            r1.<init>(r2)
            java.lang.String r2 = "，传入的参数异常"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r12.warn(r13, r1)
        L_0x027e:
            cn.com.fmsh.tsm.business.exception.BusinessException r12 = new cn.com.fmsh.tsm.business.exception.BusinessException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r13.<init>(r0)
            java.lang.String r0 = "，传入的参数异常"
            r13.append(r0)
            java.lang.String r13 = r13.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r0 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_para_error
            r12.<init>(r13, r0)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardAppTradeImpl.terminalInfoReport(int, byte[]):byte[]");
    }

    public List<TerminalBackInfo> queryTerminalInfoBack(byte[] bArr, byte[] bArr2, int i, EnumResultsSortType enumResultsSortType) throws BusinessException {
        byte[] bArr3;
        TerminalBackInfo fromTag;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "反馈信息查询");
        }
        if (i < 0) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("反馈信息查询") + "时，查询条数参数不合法");
            }
            throw new BusinessException(String.valueOf("反馈信息查询") + "时，查询条数参数不合法", BusinessException.ErrorMessage.local_business_para_error);
        } else if (enumResultsSortType == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("反馈信息查询") + "时，查询结果排序方式为空");
            }
            throw new BusinessException(String.valueOf("反馈信息查询") + "时，查询结果排序方式为空", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog == null) {
                this.fmLog = LogFactory.getInstance().getLog();
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("反馈信息查询") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("反馈信息查询") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("反馈信息查询") + "，消息处理器为空");
                }
                throw new BusinessException(String.valueOf("反馈信息查询") + "，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(4001);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("反馈信息查询") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("反馈信息查询") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("反馈信息查询", server4Business);
            IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.QUERY_TERMINAL_BACK);
            if (bArr != null) {
                try {
                    ITag createTag = messageHandler.createTag((byte) Constants.TagName.TERMINAL_BACK_MAIN_ID);
                    createTag.addValue(bArr);
                    createMessage.addTag(createTag);
                } catch (FMCommunicationMessageException e) {
                    if (this.fmLog != null) {
                        this.fmLog.warn(this.logTag, String.valueOf("反馈信息查询") + ",构造平台请求数据异常:" + Util4Java.getExceptionInfo(e));
                    }
                    this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("反馈信息查询") + ",构造平台请求数据失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                    bArr3 = null;
                }
            }
            if (bArr2 != null) {
                ITag createTag2 = messageHandler.createTag((byte) Constants.TagName.TERMINAL_BACK_CHILDREN_ID);
                createTag2.addValue(bArr2);
                createMessage.addTag(createTag2);
            }
            ITag createTag3 = messageHandler.createTag((byte) Constants.TagName.QUERY_RECORD_COUNT);
            createTag3.addValue(i);
            createMessage.addTag(createTag3);
            ITag createTag4 = messageHandler.createTag((byte) Constants.TagName.QUERY_DATA_SORT_TYPE);
            createTag4.addValue(new byte[]{(byte) enumResultsSortType.getId()});
            createMessage.addTag(createTag4);
            bArr3 = createMessage.toBytes();
            byte[] interaction = this.cardBusinessBasic.interaction(bArr3, "反馈信息查询", false, server4Business);
            this.cardBusinessBasic.businessFinish(false);
            ArrayList arrayList = new ArrayList();
            try {
                ITag tag4Id = messageHandler.createMessage(Constants.TradeCode.QUERY_TERMINAL_BACK, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(84);
                if (tag4Id == null) {
                    if (this.fmLog != null) {
                        this.fmLog.debug(this.logTag, String.valueOf("反馈信息查询") + ",平台响应的活动信息集合为空");
                    }
                    return arrayList;
                }
                ITag[] itemTags = tag4Id.getItemTags();
                if (itemTags != null) {
                    if (itemTags.length >= 1) {
                        for (ITag iTag : itemTags) {
                            if (!(iTag == null || (fromTag = TerminalBackInfo.fromTag(iTag)) == null)) {
                                arrayList.add(fromTag);
                            }
                        }
                        return arrayList;
                    }
                }
                if (this.fmLog != null) {
                    this.fmLog.debug(this.logTag, String.valueOf("反馈信息查询") + ",平台响应的活动信息集合为空");
                }
                return arrayList;
            } catch (FMCommunicationMessageException unused) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("反馈信息查询") + "，解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
                }
                this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("反馈信息查询") + ",解析平台响应数据失败", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
            }
        }
    }

    public int deleteTerminalInfoBack(byte[] bArr) throws BusinessException {
        byte[] bArr2;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "删除终端反馈信息");
        }
        if (bArr == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("删除终端反馈信息") + "时,终端反馈信息标识为空");
            }
            throw new BusinessException(String.valueOf("删除终端反馈信息") + "时，终端反馈信息标识为空", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog == null) {
                this.fmLog = LogFactory.getInstance().getLog();
            }
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.warn(str2, String.valueOf("删除终端反馈信息") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("删除终端反馈信息") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    FMLog fMLog3 = this.fmLog;
                    String str3 = this.logTag;
                    fMLog3.warn(str3, String.valueOf("删除终端反馈信息") + "，消息处理器为空");
                }
                throw new BusinessException(String.valueOf("删除终端反馈信息") + "，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.REFUND_VER2);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    FMLog fMLog4 = this.fmLog;
                    String str4 = this.logTag;
                    fMLog4.warn(str4, String.valueOf("删除终端反馈信息") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("删除终端反馈信息") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("删除终端反馈信息", server4Business);
            IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.DELETE_TERMINAL_BACK);
            try {
                ITag createTag = messageHandler.createTag((byte) Constants.TagName.TERMINAL_BACK_MAIN_ID);
                createTag.addValue(bArr);
                createMessage.addTag(createTag);
                bArr2 = createMessage.toBytes();
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    FMLog fMLog5 = this.fmLog;
                    String str5 = this.logTag;
                    fMLog5.warn(str5, String.valueOf("删除终端反馈信息") + "时,构造平台请求数据异常:" + Util4Java.getExceptionInfo(e));
                }
                CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
                cardBusinessBasic2.throwExceptionAndClose(String.valueOf("删除终端反馈信息") + ",构造平台请求数据失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr2 = null;
            }
            byte[] interaction = this.cardBusinessBasic.interaction(bArr2, "删除终端反馈信息", false, server4Business);
            byte[] copyOf = Arrays.copyOf(interaction, 2);
            this.cardBusinessBasic.businessFinish(false);
            if (Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, copyOf)) {
                return 0;
            }
            if (this.fmLog != null) {
                FMLog fMLog6 = this.fmLog;
                String str6 = this.logTag;
                fMLog6.error(str6, String.valueOf("删除终端反馈信息") + "时，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
            }
            return FM_CN.bcdBytesToInt(copyOf);
        }
    }

    public int registerVer2(UserInfo userInfo) throws BusinessException {
        String str;
        byte[] bArr;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "用户注册（Ver2）...");
        }
        if (userInfo == null) {
            throw new BusinessException("用户注册时，传入的用户信息为空", BusinessException.ErrorMessage.local_business_para_error);
        } else if (userInfo.getUserName() == null || userInfo.getUserName().length() < 1) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "用户注册时，传入的用户账号为空");
            }
            throw new BusinessException("用户注册时，传入的用户账号为空", BusinessException.ErrorMessage.local_business_para_error);
        } else if (userInfo.getPassword() == null || userInfo.getPassword().length() < 1) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "用户注册时，传入的用户密码为空");
            }
            throw new BusinessException("用户注册时，传入的用户密码为空", BusinessException.ErrorMessage.local_business_para_error);
        } else if (userInfo.getPassword().length() > 32) {
            throw new BusinessException("用户注册时，用户密钥长度必须小于32", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog == null) {
                this.fmLog = LogFactory.getInstance().getLog();
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "用户注册时，业务处理对象为空");
            }
            throw new BusinessException("用户注册时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            Configration configration = this.cardBusinessBasic.getConfigration();
            if (configration != null) {
                str = configration.getCompanyCode();
            } else {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户注册时，Configration 为空");
                }
                str = null;
            }
            if (str == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户注册时，配置文件中未包含厂商编码");
                }
                throw new BusinessException("用户注册时，用户所属商户为空", BusinessException.ErrorMessage.local_business_para_error);
            }
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户注册时，消息处理器为空");
                }
                throw new BusinessException("用户注册时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(1002);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("用户注册") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("用户注册") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("用户注册", server4Business);
            IMessage createMessage = messageHandler.createMessage(1002);
            try {
                ITag createTag = messageHandler.createTag((byte) 1);
                createTag.addValue(userInfo.getUserType());
                createMessage.addTag(createTag);
                ITag createTag2 = messageHandler.createTag((byte) 2);
                createTag2.addValue(userInfo.getUserName());
                createMessage.addTag(createTag2);
                ITag createTag3 = messageHandler.createTag((byte) 3);
                createTag3.addValue(userInfo.getPassword());
                createMessage.addTag(createTag3);
                if (userInfo.getMail() != null && userInfo.getMail().length() > 1) {
                    ITag createTag4 = messageHandler.createTag((byte) 4);
                    createTag4.addValue(userInfo.getMail());
                    createMessage.addTag(createTag4);
                }
                if (userInfo.getPhone() != null && userInfo.getPhone().length() > 1) {
                    ITag createTag5 = messageHandler.createTag((byte) 5);
                    createTag5.addValue(userInfo.getPhone());
                    createMessage.addTag(createTag5);
                }
                if (userInfo.getRealName() != null && userInfo.getRealName().length() > 1) {
                    ITag createTag6 = messageHandler.createTag((byte) 6);
                    createTag6.addValue(userInfo.getRealName());
                    createMessage.addTag(createTag6);
                }
                if (userInfo.getCertType() != -1) {
                    ITag createTag7 = messageHandler.createTag((byte) 7);
                    createTag7.addValue(userInfo.getCertType());
                    createMessage.addTag(createTag7);
                }
                if (userInfo.getCertNo() != null && userInfo.getCertNo().length() > 1) {
                    ITag createTag8 = messageHandler.createTag((byte) 8);
                    createTag8.addValue(userInfo.getCertNo());
                    createMessage.addTag(createTag8);
                }
                ITag createTag9 = messageHandler.createTag((byte) Constants.TagName.COMPANY_CODE);
                createTag9.addValue(str);
                createMessage.addTag(createTag9);
                bArr = createMessage.toBytes();
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户注册,构造平台请求数据异常:" + Util4Java.getExceptionInfo(e));
                }
                this.cardBusinessBasic.throwExceptionAndClose("用户注册,构造平台请求数据失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr = null;
            }
            byte[] interaction = this.cardBusinessBasic.interaction(bArr, "用户注册", false, server4Business);
            byte[] copyOf = Arrays.copyOf(interaction, 2);
            this.cardBusinessBasic.businessFinish(false);
            if (Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, copyOf)) {
                return 0;
            }
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, "用户注册，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
            }
            return FM_CN.bcdBytesToInt(copyOf);
        }
    }

    public int registerVer3(UserInfo userInfo) throws BusinessException {
        String str;
        byte[] bArr;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "用户注册（Ver3）...");
        }
        if (userInfo == null) {
            throw new BusinessException("用户注册时，传入的用户信息为空", BusinessException.ErrorMessage.local_business_para_error);
        } else if (userInfo.getUserName() == null || userInfo.getUserName().length() < 1) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "用户注册时，传入的用户账号为空");
            }
            throw new BusinessException("用户注册时，传入的用户账号为空", BusinessException.ErrorMessage.local_business_para_error);
        } else if (userInfo.getPassword() == null || userInfo.getPassword().length() < 1) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "用户注册时，传入的用户密码为空");
            }
            throw new BusinessException("用户注册时，传入的用户密码为空", BusinessException.ErrorMessage.local_business_para_error);
        } else if (userInfo.getPassword().length() > 32) {
            throw new BusinessException("用户注册时，用户密钥长度必须小于32", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog == null) {
                this.fmLog = LogFactory.getInstance().getLog();
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "用户注册时，业务处理对象为空");
            }
            throw new BusinessException("用户注册时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            Configration configration = this.cardBusinessBasic.getConfigration();
            if (configration != null) {
                str = configration.getCompanyCode();
            } else {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户注册时，Configration 为空");
                }
                str = null;
            }
            if (str == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户注册时，配置文件中未包含厂商编码");
                }
                throw new BusinessException("用户注册时，用户所属商户为空", BusinessException.ErrorMessage.local_business_para_error);
            }
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户注册时，消息处理器为空");
                }
                throw new BusinessException("用户注册时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(1002);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("用户注册") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("用户注册") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("用户注册", server4Business);
            IMessage createMessage = messageHandler.createMessage(1003);
            try {
                ITag createTag = messageHandler.createTag((byte) 1);
                createTag.addValue(userInfo.getUserType());
                createMessage.addTag(createTag);
                ITag createTag2 = messageHandler.createTag((byte) 2);
                createTag2.addValue(userInfo.getUserName());
                createMessage.addTag(createTag2);
                ITag createTag3 = messageHandler.createTag((byte) 3);
                createTag3.addValue(userInfo.getPassword());
                createMessage.addTag(createTag3);
                if (userInfo.getMail() != null && userInfo.getMail().length() > 1) {
                    ITag createTag4 = messageHandler.createTag((byte) 4);
                    createTag4.addValue(userInfo.getMail());
                    createMessage.addTag(createTag4);
                }
                if (userInfo.getPhone() != null && userInfo.getPhone().length() > 1) {
                    ITag createTag5 = messageHandler.createTag((byte) 5);
                    createTag5.addValue(userInfo.getPhone());
                    createMessage.addTag(createTag5);
                }
                if (userInfo.getRealName() != null && userInfo.getRealName().length() > 1) {
                    ITag createTag6 = messageHandler.createTag((byte) 6);
                    createTag6.addValue(userInfo.getRealName());
                    createMessage.addTag(createTag6);
                }
                if (userInfo.getCertType() != -1) {
                    ITag createTag7 = messageHandler.createTag((byte) 7);
                    createTag7.addValue(userInfo.getCertType());
                    createMessage.addTag(createTag7);
                }
                if (userInfo.getCertNo() != null && userInfo.getCertNo().length() > 1) {
                    ITag createTag8 = messageHandler.createTag((byte) 8);
                    createTag8.addValue(userInfo.getCertNo());
                    createMessage.addTag(createTag8);
                }
                if (userInfo.getVerificationCodeNo() != null && userInfo.getVerificationCodeNo().length > 1) {
                    ITag createTag9 = messageHandler.createTag((byte) 64);
                    createTag9.addValue(userInfo.getVerificationCodeNo());
                    createMessage.addTag(createTag9);
                }
                if (userInfo.getVerificationCode() != null && userInfo.getVerificationCode().length() > 1) {
                    ITag createTag10 = messageHandler.createTag((byte) 12);
                    createTag10.addValue(userInfo.getVerificationCode());
                    createMessage.addTag(createTag10);
                }
                ITag createTag11 = messageHandler.createTag((byte) Constants.TagName.COMPANY_CODE);
                createTag11.addValue(str);
                createMessage.addTag(createTag11);
                bArr = createMessage.toBytes();
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户注册,构造平台请求数据异常:" + Util4Java.getExceptionInfo(e));
                }
                this.cardBusinessBasic.throwExceptionAndClose("用户注册,构造平台请求数据失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr = null;
            }
            byte[] interaction = this.cardBusinessBasic.interaction(bArr, "用户注册", false, server4Business);
            byte[] copyOf = Arrays.copyOf(interaction, 2);
            this.cardBusinessBasic.businessFinish(false);
            if (Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, copyOf)) {
                return 0;
            }
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, "用户注册，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
            }
            return FM_CN.bcdBytesToInt(copyOf);
        }
    }

    public LoginInfo login(String str, String str2) throws BusinessException {
        byte[] bArr;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "用户登录...");
        }
        if (str == null || "".equals(str) || str2 == null || "".equals(str2)) {
            throw new BusinessException("用户登录时，传入的参数异常", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "用户登录时，业务处理对象为空");
            }
            throw new BusinessException("用户登录时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户登录时，消息处理器为空");
                }
                throw new BusinessException("用户登录时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(1021);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("用户登录") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("用户登录") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("用户登录", server4Business);
            IMessage createMessage = messageHandler.createMessage(1021);
            try {
                ITag createTag = messageHandler.createTag((byte) 2);
                createTag.addValue(str);
                createMessage.addTag(createTag);
                ITag createTag2 = messageHandler.createTag((byte) 3);
                createTag2.addValue(str2);
                createMessage.addTag(createTag2);
                bArr = createMessage.toBytes();
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户登录时，构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
                }
                this.cardBusinessBasic.throwExceptionAndClose("用户登录时，构造平台请求数据出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr = null;
            }
            byte[] interaction = this.cardBusinessBasic.interaction(bArr, "用户登录", false, server4Business);
            byte[] bArr2 = new byte[2];
            System.arraycopy(interaction, 0, bArr2, 0, bArr2.length);
            LoginInfo loginInfo = new LoginInfo();
            if (interaction.length != 2) {
                try {
                    IMessage createMessage2 = messageHandler.createMessage(1021, Arrays.copyOfRange(interaction, 2, interaction.length));
                    loginInfo.setResult(Util4Java.String2Int(FM_CN.bcdBytesToString(bArr2), 1001));
                    ITag tag4Id = createMessage2.getTag4Id(36);
                    if (tag4Id != null) {
                        loginInfo.setFailureNum(tag4Id.getIntVal());
                    }
                    ITag tag4Id2 = createMessage2.getTag4Id(43);
                    if (tag4Id2 != null) {
                        loginInfo.setUserLockTime(tag4Id2.getIntVal());
                    }
                } catch (FMCommunicationMessageException e2) {
                    if (this.fmLog != null) {
                        this.fmLog.warn(this.logTag, "用户登录时，解析平台响应数据出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                }
            } else if (Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, bArr2)) {
                this.userName = str;
                loginInfo.setResult(0);
            } else {
                loginInfo.setResult(FM_CN.bcdBytesToInt(bArr2));
            }
            this.cardBusinessBasic.businessFinish(false);
            return loginInfo;
        }
    }

    public LoginInfo loginVer2(String str, String str2) throws BusinessException {
        String str3;
        byte[] bArr;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "用户登录（Ver2）...");
        }
        if (str == null || "".equals(str) || str2 == null || "".equals(str2)) {
            throw new BusinessException("用户登录时，传入的用户名或者密码为空", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "用户登录时，业务处理对象为空");
            }
            throw new BusinessException("用户登录时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户登录时，消息处理器为空");
                }
                throw new BusinessException("用户登录时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            Configration configration = this.cardBusinessBasic.getConfigration();
            if (configration != null) {
                str3 = configration.getCompanyCode();
            } else {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户登录时，Configration 为空");
                }
                str3 = null;
            }
            if (str3 == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户登录时，用户所属商户为空");
                }
                throw new BusinessException("用户登录时，用户所属商户为空", BusinessException.ErrorMessage.local_business_para_error);
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(1022);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("用户登录") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("用户登录") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("用户登录", server4Business);
            IMessage createMessage = messageHandler.createMessage(1022);
            try {
                ITag createTag = messageHandler.createTag((byte) 2);
                createTag.addValue(str);
                createMessage.addTag(createTag);
                ITag createTag2 = messageHandler.createTag((byte) 3);
                createTag2.addValue(str2);
                createMessage.addTag(createTag2);
                ITag createTag3 = messageHandler.createTag((byte) Constants.TagName.COMPANY_CODE);
                createTag3.addValue(str3);
                createMessage.addTag(createTag3);
                bArr = createMessage.toBytes();
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户登录时，构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
                }
                this.cardBusinessBasic.throwExceptionAndClose("用户登录时，构造平台请求数据出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr = null;
            }
            byte[] interaction = this.cardBusinessBasic.interaction(bArr, "用户登录", false, server4Business);
            byte[] bArr2 = new byte[2];
            System.arraycopy(interaction, 0, bArr2, 0, bArr2.length);
            LoginInfo loginInfo = new LoginInfo();
            if (interaction.length != 2) {
                try {
                    IMessage createMessage2 = messageHandler.createMessage(1021, Arrays.copyOfRange(interaction, 2, interaction.length));
                    loginInfo.setResult(Util4Java.String2Int(FM_CN.bcdBytesToString(bArr2), 1001));
                    ITag tag4Id = createMessage2.getTag4Id(36);
                    if (tag4Id != null) {
                        loginInfo.setFailureNum(tag4Id.getIntVal());
                    }
                    ITag tag4Id2 = createMessage2.getTag4Id(43);
                    if (tag4Id2 != null) {
                        loginInfo.setUserLockTime(tag4Id2.getIntVal());
                    }
                } catch (FMCommunicationMessageException e2) {
                    if (this.fmLog != null) {
                        this.fmLog.warn(this.logTag, "用户登录时，解析平台响应数据出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                }
            } else if (Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, bArr2)) {
                this.userName = str;
                loginInfo.setResult(0);
            } else {
                loginInfo.setResult(FM_CN.bcdBytesToInt(bArr2));
            }
            this.cardBusinessBasic.businessFinish(false);
            return loginInfo;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:0x0195  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x01ac  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public cn.com.fmsh.tsm.business.bean.LoginInfo loginVer3(java.lang.String r9, java.lang.String r10, cn.com.fmsh.tsm.business.enums.EnumUserPlatformType r11, java.lang.String r12) throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r8 = this;
            java.lang.String r0 = "用户登录"
            cn.com.fmsh.util.log.FMLog r1 = r8.fmLog
            if (r1 != 0) goto L_0x0011
            cn.com.fmsh.util.log.LogFactory r1 = cn.com.fmsh.util.log.LogFactory.getInstance()
            cn.com.fmsh.util.log.FMLog r1 = r1.getLog()
            r8.fmLog = r1
        L_0x0011:
            cn.com.fmsh.util.log.FMLog r1 = r8.fmLog
            if (r1 == 0) goto L_0x001f
            cn.com.fmsh.util.log.FMLog r1 = r8.fmLog
            java.lang.String r2 = r8.logTag
            java.lang.String r3 = "用户登录（Ver3）..."
            r1.info(r2, r3)
        L_0x001f:
            if (r9 == 0) goto L_0x022e
            java.lang.String r1 = ""
            boolean r1 = r1.equals(r9)
            if (r1 != 0) goto L_0x022e
            if (r10 == 0) goto L_0x022e
            java.lang.String r1 = ""
            boolean r1 = r1.equals(r10)
            if (r1 != 0) goto L_0x022e
            if (r11 == 0) goto L_0x0223
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r1 = r8.cardBusinessBasic
            if (r1 != 0) goto L_0x0052
            cn.com.fmsh.util.log.FMLog r9 = r8.fmLog
            if (r9 == 0) goto L_0x0047
            cn.com.fmsh.util.log.FMLog r9 = r8.fmLog
            java.lang.String r10 = r8.logTag
            java.lang.String r11 = "用户登录时，业务处理对象为空"
            r9.warn(r10, r11)
        L_0x0047:
            cn.com.fmsh.tsm.business.exception.BusinessException r9 = new cn.com.fmsh.tsm.business.exception.BusinessException
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r10 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_init_fail
            java.lang.String r11 = "用户登录时，业务处理器初始化失败"
            r9.<init>(r11, r10)
            throw r9
        L_0x0052:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r1 = r8.cardBusinessBasic
            cn.com.fmsh.communication.message.IMessageHandler r1 = r1.getMessageHandler()
            if (r1 != 0) goto L_0x0073
            cn.com.fmsh.util.log.FMLog r9 = r8.fmLog
            if (r9 == 0) goto L_0x0068
            cn.com.fmsh.util.log.FMLog r9 = r8.fmLog
            java.lang.String r10 = r8.logTag
            java.lang.String r11 = "用户登录时，消息处理器为空"
            r9.warn(r10, r11)
        L_0x0068:
            cn.com.fmsh.tsm.business.exception.BusinessException r9 = new cn.com.fmsh.tsm.business.exception.BusinessException
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r10 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_load_config_fail
            java.lang.String r11 = "用户登录时，消息处理器为空"
            r9.<init>(r11, r10)
            throw r9
        L_0x0073:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r8.cardBusinessBasic
            cn.com.fmsh.tsm.business.core.Configration r2 = r2.getConfigration()
            r3 = 0
            if (r2 == 0) goto L_0x0081
            java.lang.String r2 = r2.getCompanyCode()
            goto L_0x0090
        L_0x0081:
            cn.com.fmsh.util.log.FMLog r2 = r8.fmLog
            if (r2 == 0) goto L_0x008f
            cn.com.fmsh.util.log.FMLog r2 = r8.fmLog
            java.lang.String r4 = r8.logTag
            java.lang.String r5 = "用户登录时，Configration 为空"
            r2.warn(r4, r5)
        L_0x008f:
            r2 = r3
        L_0x0090:
            if (r2 != 0) goto L_0x00ab
            cn.com.fmsh.util.log.FMLog r9 = r8.fmLog
            if (r9 == 0) goto L_0x00a0
            cn.com.fmsh.util.log.FMLog r9 = r8.fmLog
            java.lang.String r10 = r8.logTag
            java.lang.String r11 = "用户登录时，用户所属商户为空"
            r9.warn(r10, r11)
        L_0x00a0:
            cn.com.fmsh.tsm.business.exception.BusinessException r9 = new cn.com.fmsh.tsm.business.exception.BusinessException
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r10 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_para_error
            java.lang.String r11 = "用户登录时，用户所属商户为空"
            r9.<init>(r11, r10)
            throw r9
        L_0x00ab:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r4 = r8.cardBusinessBasic
            r5 = 1023(0x3ff, float:1.434E-42)
            java.lang.String r4 = r4.getServer4Business(r5)
            if (r4 != 0) goto L_0x00ee
            cn.com.fmsh.util.log.FMLog r9 = r8.fmLog
            if (r9 == 0) goto L_0x00d3
            cn.com.fmsh.util.log.FMLog r9 = r8.fmLog
            java.lang.String r10 = r8.logTag
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r12 = java.lang.String.valueOf(r0)
            r11.<init>(r12)
            java.lang.String r12 = "时，获取处理的平台失败"
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            r9.warn(r10, r11)
        L_0x00d3:
            cn.com.fmsh.tsm.business.exception.BusinessException r9 = new cn.com.fmsh.tsm.business.exception.BusinessException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = java.lang.String.valueOf(r0)
            r10.<init>(r11)
            java.lang.String r11 = "时，获取处理的平台失败"
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r11 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_app_query_server_fail
            r9.<init>(r10, r11)
            throw r9
        L_0x00ee:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r6 = r8.cardBusinessBasic
            r6.businessReady(r0, r4)
            cn.com.fmsh.communication.message.IMessage r0 = r1.createMessage((int) r5)
            r5 = 2
            r6 = 0
            cn.com.fmsh.communication.message.ITag r7 = r1.createTag((byte) r5)     // Catch:{ FMCommunicationMessageException -> 0x0154 }
            r7.addValue((java.lang.String) r9)     // Catch:{ FMCommunicationMessageException -> 0x0154 }
            r0.addTag(r7)     // Catch:{ FMCommunicationMessageException -> 0x0154 }
            r7 = 3
            cn.com.fmsh.communication.message.ITag r7 = r1.createTag((byte) r7)     // Catch:{ FMCommunicationMessageException -> 0x0154 }
            r7.addValue((java.lang.String) r10)     // Catch:{ FMCommunicationMessageException -> 0x0154 }
            r0.addTag(r7)     // Catch:{ FMCommunicationMessageException -> 0x0154 }
            r10 = -119(0xffffffffffffff89, float:NaN)
            cn.com.fmsh.communication.message.ITag r10 = r1.createTag((byte) r10)     // Catch:{ FMCommunicationMessageException -> 0x0154 }
            r10.addValue((java.lang.String) r2)     // Catch:{ FMCommunicationMessageException -> 0x0154 }
            r0.addTag(r10)     // Catch:{ FMCommunicationMessageException -> 0x0154 }
            r10 = -60
            cn.com.fmsh.communication.message.ITag r10 = r1.createTag((byte) r10)     // Catch:{ FMCommunicationMessageException -> 0x0154 }
            int r11 = r11.getId()     // Catch:{ FMCommunicationMessageException -> 0x0154 }
            r10.addValue((int) r11)     // Catch:{ FMCommunicationMessageException -> 0x0154 }
            r0.addTag(r10)     // Catch:{ FMCommunicationMessageException -> 0x0154 }
            r10 = -59
            cn.com.fmsh.communication.message.ITag r10 = r1.createTag((byte) r10)     // Catch:{ FMCommunicationMessageException -> 0x0154 }
            r10.addValue((java.lang.String) r12)     // Catch:{ FMCommunicationMessageException -> 0x0154 }
            r0.addTag(r10)     // Catch:{ FMCommunicationMessageException -> 0x0154 }
            byte[] r10 = r0.toBytes()     // Catch:{ FMCommunicationMessageException -> 0x0154 }
            java.io.PrintStream r11 = java.lang.System.out     // Catch:{ FMCommunicationMessageException -> 0x0152 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ FMCommunicationMessageException -> 0x0152 }
            java.lang.String r0 = "request:"
            r12.<init>(r0)     // Catch:{ FMCommunicationMessageException -> 0x0152 }
            java.lang.String r0 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r10)     // Catch:{ FMCommunicationMessageException -> 0x0152 }
            r12.append(r0)     // Catch:{ FMCommunicationMessageException -> 0x0152 }
            java.lang.String r12 = r12.toString()     // Catch:{ FMCommunicationMessageException -> 0x0152 }
            r11.println(r12)     // Catch:{ FMCommunicationMessageException -> 0x0152 }
            goto L_0x017e
        L_0x0152:
            r11 = move-exception
            goto L_0x0156
        L_0x0154:
            r11 = move-exception
            r10 = r3
        L_0x0156:
            cn.com.fmsh.util.log.FMLog r12 = r8.fmLog
            if (r12 == 0) goto L_0x0174
            cn.com.fmsh.util.log.FMLog r12 = r8.fmLog
            java.lang.String r0 = r8.logTag
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "用户登录时，构造平台请求数据出现异常："
            r2.<init>(r3)
            java.lang.String r11 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r11)
            r2.append(r11)
            java.lang.String r11 = r2.toString()
            r12.warn(r0, r11)
        L_0x0174:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r11 = r8.cardBusinessBasic
            java.lang.String r12 = "用户登录时，构造平台请求数据出现异常"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r0 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r11.throwExceptionAndClose((java.lang.String) r12, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r0, (boolean) r6)
        L_0x017e:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r11 = r8.cardBusinessBasic
            java.lang.String r12 = "用户登录"
            byte[] r10 = r11.interaction(r10, r12, r6, r4)
            byte[] r11 = new byte[r5]
            int r12 = r11.length
            java.lang.System.arraycopy(r10, r6, r11, r6, r12)
            cn.com.fmsh.tsm.business.bean.LoginInfo r12 = new cn.com.fmsh.tsm.business.bean.LoginInfo
            r12.<init>()
            int r0 = r10.length
            if (r0 != r5) goto L_0x01ac
            byte[] r10 = cn.com.fmsh.tsm.business.constants.Constants.RespCodeonse4Platform.SUCESS
            boolean r10 = java.util.Arrays.equals(r10, r11)
            if (r10 == 0) goto L_0x01a4
            r8.userName = r9
            r12.setResult(r6)
            goto L_0x021d
        L_0x01a4:
            int r9 = cn.com.fmsh.util.FM_CN.bcdBytesToInt(r11)
            r12.setResult(r9)
            goto L_0x021d
        L_0x01ac:
            r9 = 1021(0x3fd, float:1.431E-42)
            int r0 = r10.length     // Catch:{ FMCommunicationMessageException -> 0x01fe }
            byte[] r10 = java.util.Arrays.copyOfRange(r10, r5, r0)     // Catch:{ FMCommunicationMessageException -> 0x01fe }
            cn.com.fmsh.communication.message.IMessage r9 = r1.createMessage(r9, r10)     // Catch:{ FMCommunicationMessageException -> 0x01fe }
            byte[] r10 = cn.com.fmsh.tsm.business.constants.Constants.RespCodeonse4Platform.SUCESS     // Catch:{ FMCommunicationMessageException -> 0x01fe }
            boolean r10 = java.util.Arrays.equals(r10, r11)     // Catch:{ FMCommunicationMessageException -> 0x01fe }
            if (r10 == 0) goto L_0x01c3
            r12.setResult(r6)     // Catch:{ FMCommunicationMessageException -> 0x01fe }
            goto L_0x01d0
        L_0x01c3:
            java.lang.String r10 = cn.com.fmsh.util.FM_CN.bcdBytesToString(r11)     // Catch:{ FMCommunicationMessageException -> 0x01fe }
            r11 = 1001(0x3e9, float:1.403E-42)
            int r10 = cn.com.fmsh.util.Util4Java.String2Int(r10, r11)     // Catch:{ FMCommunicationMessageException -> 0x01fe }
            r12.setResult(r10)     // Catch:{ FMCommunicationMessageException -> 0x01fe }
        L_0x01d0:
            r10 = 36
            cn.com.fmsh.communication.message.ITag r10 = r9.getTag4Id(r10)     // Catch:{ FMCommunicationMessageException -> 0x01fe }
            if (r10 == 0) goto L_0x01df
            int r10 = r10.getIntVal()     // Catch:{ FMCommunicationMessageException -> 0x01fe }
            r12.setFailureNum(r10)     // Catch:{ FMCommunicationMessageException -> 0x01fe }
        L_0x01df:
            r10 = 43
            cn.com.fmsh.communication.message.ITag r10 = r9.getTag4Id(r10)     // Catch:{ FMCommunicationMessageException -> 0x01fe }
            if (r10 == 0) goto L_0x01ee
            int r10 = r10.getIntVal()     // Catch:{ FMCommunicationMessageException -> 0x01fe }
            r12.setUserLockTime(r10)     // Catch:{ FMCommunicationMessageException -> 0x01fe }
        L_0x01ee:
            r10 = -76
            cn.com.fmsh.communication.message.ITag r9 = r9.getTag4Id(r10)     // Catch:{ FMCommunicationMessageException -> 0x01fe }
            if (r9 == 0) goto L_0x021d
            byte[] r9 = r9.getBytesVal()     // Catch:{ FMCommunicationMessageException -> 0x01fe }
            r12.setPatchData(r9)     // Catch:{ FMCommunicationMessageException -> 0x01fe }
            goto L_0x021d
        L_0x01fe:
            r9 = move-exception
            cn.com.fmsh.util.log.FMLog r10 = r8.fmLog
            if (r10 == 0) goto L_0x021d
            cn.com.fmsh.util.log.FMLog r10 = r8.fmLog
            java.lang.String r11 = r8.logTag
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "用户登录时，解析平台响应数据出现异常:"
            r0.<init>(r1)
            java.lang.String r9 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r9)
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            r10.warn(r11, r9)
        L_0x021d:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r9 = r8.cardBusinessBasic
            r9.businessFinish(r6)
            return r12
        L_0x0223:
            cn.com.fmsh.tsm.business.exception.BusinessException r9 = new cn.com.fmsh.tsm.business.exception.BusinessException
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r10 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_para_error
            java.lang.String r11 = "用户登录时，传入的用户所属平台类型为空"
            r9.<init>(r11, r10)
            throw r9
        L_0x022e:
            cn.com.fmsh.tsm.business.exception.BusinessException r9 = new cn.com.fmsh.tsm.business.exception.BusinessException
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r10 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_para_error
            java.lang.String r11 = "用户登录时，传入的用户名或者密码为空"
            r9.<init>(r11, r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardAppTradeImpl.loginVer3(java.lang.String, java.lang.String, cn.com.fmsh.tsm.business.enums.EnumUserPlatformType, java.lang.String):cn.com.fmsh.tsm.business.bean.LoginInfo");
    }

    public int logout() throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "用户登出...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "用户退出时，业务处理对象为空");
            }
            throw new BusinessException("用户退出时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        if (this.cardBusinessBasic.getApduHandler() != null) {
            this.cardBusinessBasic.getApduHandler().close();
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(1022);
        if (server4Business == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "用户退出时，获取平台信息失败");
            }
            throw new BusinessException("用户退出时，获取平台信息失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        } else if (this.cardBusinessBasic.closeSessionHandle(server4Business)) {
            return 0;
        } else {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "用户退出时处理失败");
            }
            this.cardBusinessBasic.disconnect(server4Business);
            return -1;
        }
    }

    public int modifyPassword(String str, String str2) throws BusinessException {
        byte[] bArr;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "用户密码修改...");
        }
        if (str == null || str.length() < 1 || str2 == null || str2.length() < 1) {
            throw new BusinessException("用户密码修改时，传入的参数异常", BusinessException.ErrorMessage.local_business_para_error);
        } else if (str.length() > 32) {
            throw new BusinessException("用户密码修改时，旧密码长度必须小于32", BusinessException.ErrorMessage.local_business_para_error);
        } else if (str2.length() > 32) {
            throw new BusinessException("用户密码修改时，新密码长度必须小于32", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "用户密码修改时，业务处理对象为空");
            }
            throw new BusinessException("用户密码修改时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户密码修改时，消息处理器为空");
                }
                throw new BusinessException("用户密码修改时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(1031);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("用户密码修改") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("用户密码修改") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("用户密码修改", server4Business);
            IMessage createMessage = messageHandler.createMessage(1031);
            try {
                ITag createTag = messageHandler.createTag((byte) 2);
                createTag.addValue(this.userName);
                createMessage.addTag(createTag);
                ITag createTag2 = messageHandler.createTag((byte) 9);
                ITag createTag3 = messageHandler.createTag((byte) 3);
                createTag3.addValue(str);
                createTag2.addValue(createTag3);
                ITag createTag4 = messageHandler.createTag((byte) 3);
                createTag4.addValue(str2);
                createTag2.addValue(createTag4);
                createMessage.addTag(createTag2);
                bArr = createMessage.toBytes();
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户密码修改时，构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
                }
                this.cardBusinessBasic.throwExceptionAndClose("用户密码修改时，构造平台请求数据出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr = null;
            }
            byte[] interaction = this.cardBusinessBasic.interaction(bArr, "用户密码修改", false, server4Business);
            byte[] bArr2 = new byte[2];
            System.arraycopy(interaction, 0, bArr2, 0, bArr2.length);
            this.cardBusinessBasic.businessFinish(false);
            if (Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, bArr2)) {
                return 0;
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "用户密码修改,平台处理失败，:" + FM_Bytes.bytesToHexString(interaction));
            }
            return FM_CN.bcdBytesToInt(bArr2);
        }
    }

    public PasswordPrompt retrievePassword(String str) throws BusinessException {
        byte[] bArr;
        PasswordPrompt passwordPrompt;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "用户密码找回...");
        }
        if (str == null || str.length() < 1) {
            throw new BusinessException(String.valueOf("用户密码找回") + "时，传入的参数异常", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("用户密码找回") + "时，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("用户密码找回") + "时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("用户密码找回") + "时，消息处理器为空");
                }
                throw new BusinessException(String.valueOf("用户密码找回") + "时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(1041);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("用户密码找回") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("用户密码找回") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("用户密码找回", server4Business);
            IMessage createMessage = messageHandler.createMessage(1041);
            try {
                ITag createTag = messageHandler.createTag((byte) 2);
                createTag.addValue(str);
                createMessage.addTag(createTag);
                bArr = createMessage.toBytes();
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("用户密码找回") + "时，构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
                }
                this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("用户密码找回") + "时，构造平台请求数据出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr = null;
            }
            byte[] interaction = this.cardBusinessBasic.interaction(bArr, "用户密码找回", false, server4Business);
            byte[] bArr2 = new byte[2];
            System.arraycopy(interaction, 0, bArr2, 0, bArr2.length);
            if (Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, bArr2)) {
                try {
                    ITag tag4Id = messageHandler.createMessage(1041, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(10);
                    if (tag4Id != null) {
                        passwordPrompt = PasswordPrompt.fromTag(tag4Id);
                        this.cardBusinessBasic.businessFinish(false);
                        return passwordPrompt;
                    }
                } catch (FMCommunicationMessageException e2) {
                    if (this.fmLog != null) {
                        this.fmLog.warn(this.logTag, "用户登录时，解析平台响应数据出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                }
            } else {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("用户密码找回") + "时，处理失败：" + FM_Bytes.bytesToHexString(interaction));
                }
                this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("用户密码找回") + "时，处理失败，平台响应结果: + FM_Bytes.bytesToHexString(response)", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            }
            passwordPrompt = null;
            this.cardBusinessBasic.businessFinish(false);
            return passwordPrompt;
        }
    }

    public PasswordPrompt retrievePasswordVer2(String str, byte[] bArr, String str2) throws BusinessException {
        String str3;
        byte[] bArr2;
        PasswordPrompt passwordPrompt;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "用户密码找回...");
        }
        if (str == null || str.length() < 1) {
            throw new BusinessException(String.valueOf("用户密码找回") + "时，传入的参数异常", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("用户密码找回") + "时，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("用户密码找回") + "时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("用户密码找回") + "时，消息处理器为空");
                }
                throw new BusinessException(String.valueOf("用户密码找回") + "时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(1041);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("用户密码找回") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("用户密码找回") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            Configration configration = this.cardBusinessBasic.getConfigration();
            if (configration != null) {
                str3 = configration.getCompanyCode();
            } else {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户找回密码时，Configration 为空");
                }
                str3 = null;
            }
            if (str3 == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户找回密码时，用户所属商户为空");
                }
                throw new BusinessException("用户登录时，用户所属商户为空", BusinessException.ErrorMessage.local_business_para_error);
            }
            this.cardBusinessBasic.businessReady("用户密码找回", server4Business);
            IMessage createMessage = messageHandler.createMessage(1043);
            try {
                ITag createTag = messageHandler.createTag((byte) 2);
                createTag.addValue(str);
                createMessage.addTag(createTag);
                ITag createTag2 = messageHandler.createTag((byte) Constants.TagName.COMPANY_CODE);
                createTag2.addValue(str3);
                createMessage.addTag(createTag2);
                if (str2 != null && str2.length() > 0) {
                    ITag createTag3 = messageHandler.createTag((byte) 12);
                    createTag3.addValue(str2);
                    createMessage.addTag(createTag3);
                }
                if (bArr != null && bArr.length > 0) {
                    ITag createTag4 = messageHandler.createTag((byte) 64);
                    createTag4.addValue(bArr);
                    createMessage.addTag(createTag4);
                }
                bArr2 = createMessage.toBytes();
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("用户密码找回") + "时，构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
                }
                this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("用户密码找回") + "时，构造平台请求数据出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr2 = null;
            }
            byte[] interaction = this.cardBusinessBasic.interaction(bArr2, "用户密码找回", false, server4Business);
            byte[] bArr3 = new byte[2];
            System.arraycopy(interaction, 0, bArr3, 0, bArr3.length);
            if (Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, bArr3)) {
                try {
                    ITag tag4Id = messageHandler.createMessage(1041, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(10);
                    if (tag4Id != null) {
                        passwordPrompt = PasswordPrompt.fromTag(tag4Id);
                        this.cardBusinessBasic.businessFinish(false);
                        return passwordPrompt;
                    }
                } catch (FMCommunicationMessageException e2) {
                    if (this.fmLog != null) {
                        this.fmLog.warn(this.logTag, "用户登录时，解析平台响应数据出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                }
            } else {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("用户密码找回") + "时，处理失败：" + FM_Bytes.bytesToHexString(interaction));
                }
                this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("用户密码找回") + "时，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(bArr3)), false);
            }
            passwordPrompt = null;
            this.cardBusinessBasic.businessFinish(false);
            return passwordPrompt;
        }
    }

    public UserInfo queryUserInfo(String str) throws BusinessException {
        byte[] bArr;
        if (str == null || "".equals(str)) {
            throw new BusinessException("用户信息查询时，传入的参数异常", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog == null) {
                this.fmLog = LogFactory.getInstance().getLog();
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "用户信息查询时，业务处理对象为空");
            }
            throw new BusinessException("用户信息查询时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户信息查询时，消息处理器为空");
                }
                throw new BusinessException("用户信息查询时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(1051);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("用户信息查询") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("用户信息查询") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("用户信息查询", server4Business);
            IMessage createMessage = messageHandler.createMessage(1051);
            try {
                ITag createTag = messageHandler.createTag((byte) 2);
                createTag.addValue(str);
                createMessage.addTag(createTag);
                bArr = createMessage.toBytes();
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户信息查询时，构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
                }
                this.cardBusinessBasic.throwExceptionAndClose("用户信息查询时时，构造平台请求数据出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr = null;
            }
            byte[] interaction = this.cardBusinessBasic.interaction(bArr, "用户信息查询", false, server4Business);
            byte[] bArr2 = new byte[2];
            System.arraycopy(interaction, 0, bArr2, 0, bArr2.length);
            if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, bArr2)) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户信息查询时，平台处理失败：" + FM_Bytes.bytesToHexString(interaction));
                }
                this.cardBusinessBasic.throwExceptionAndClose("用户信息查询时，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(bArr2)), false);
            }
            UserInfo userInfo = new UserInfo();
            try {
                IMessage createMessage2 = messageHandler.createMessage(1051, Arrays.copyOfRange(interaction, 2, interaction.length));
                ITag tag4Id = createMessage2.getTag4Id(1);
                if (tag4Id != null) {
                    userInfo.setUserType(tag4Id.getIntVal());
                }
                ITag tag4Id2 = createMessage2.getTag4Id(4);
                if (tag4Id2 != null) {
                    userInfo.setMail(tag4Id2.getStringVal());
                }
                ITag tag4Id3 = createMessage2.getTag4Id(5);
                if (tag4Id3 != null) {
                    userInfo.setPhone(tag4Id3.getStringVal());
                }
                ITag tag4Id4 = createMessage2.getTag4Id(6);
                if (tag4Id4 != null) {
                    userInfo.setRealName(tag4Id4.getStringVal());
                }
                ITag tag4Id5 = createMessage2.getTag4Id(7);
                if (tag4Id5 != null) {
                    userInfo.setCertType(tag4Id5.getIntVal());
                }
                ITag tag4Id6 = createMessage2.getTag4Id(8);
                if (tag4Id6 != null) {
                    userInfo.setCertNo(tag4Id6.getStringVal());
                }
            } catch (FMCommunicationMessageException e2) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户信息查询时，解析平台响应数据异常：" + Util4Java.getExceptionInfo(e2));
                }
                this.cardBusinessBasic.throwExceptionAndClose("用户信息查询时，终端接收到无效的平台响应数据", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            }
            this.cardBusinessBasic.businessFinish(false);
            return userInfo;
        }
    }

    public UserInfo queryUserInfoVer2(String str) throws BusinessException {
        String str2;
        byte[] bArr;
        if (str == null || "".equals(str)) {
            throw new BusinessException("用户信息查询时，传入的参数异常", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog == null) {
                this.fmLog = LogFactory.getInstance().getLog();
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "用户信息查询时，业务处理对象为空");
            }
            throw new BusinessException("用户信息查询时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            Configration configration = this.cardBusinessBasic.getConfigration();
            if (configration != null) {
                str2 = configration.getCompanyCode();
            } else {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户注册时，Configration 为空");
                }
                str2 = null;
            }
            if (str2 == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户注册时，配置文件中未包含厂商编码");
                }
                throw new BusinessException("用户注册时，用户所属商户为空", BusinessException.ErrorMessage.local_business_para_error);
            }
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户信息查询时，消息处理器为空");
                }
                throw new BusinessException("用户信息查询时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(1052);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("用户信息查询") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("用户信息查询") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("用户信息查询", server4Business);
            IMessage createMessage = messageHandler.createMessage(1052);
            try {
                ITag createTag = messageHandler.createTag((byte) 2);
                createTag.addValue(str);
                createMessage.addTag(createTag);
                ITag createTag2 = messageHandler.createTag((byte) Constants.TagName.COMPANY_CODE);
                createTag2.addValue(str2);
                createMessage.addTag(createTag2);
                bArr = createMessage.toBytes();
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户信息查询时，构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
                }
                this.cardBusinessBasic.throwExceptionAndClose("用户信息查询时时，构造平台请求数据出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr = null;
            }
            byte[] interaction = this.cardBusinessBasic.interaction(bArr, "用户信息查询", false, server4Business);
            byte[] bArr2 = new byte[2];
            System.arraycopy(interaction, 0, bArr2, 0, bArr2.length);
            if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, bArr2)) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户信息查询时，平台处理失败：" + FM_Bytes.bytesToHexString(interaction));
                }
                this.cardBusinessBasic.throwExceptionAndClose("用户信息查询时，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(bArr2)), false);
            }
            UserInfo userInfo = new UserInfo();
            try {
                IMessage createMessage2 = messageHandler.createMessage(1051, Arrays.copyOfRange(interaction, 2, interaction.length));
                ITag tag4Id = createMessage2.getTag4Id(1);
                if (tag4Id != null) {
                    userInfo.setUserType(tag4Id.getIntVal());
                }
                ITag tag4Id2 = createMessage2.getTag4Id(4);
                if (tag4Id2 != null) {
                    userInfo.setMail(tag4Id2.getStringVal());
                }
                ITag tag4Id3 = createMessage2.getTag4Id(5);
                if (tag4Id3 != null) {
                    userInfo.setPhone(tag4Id3.getStringVal());
                }
                ITag tag4Id4 = createMessage2.getTag4Id(6);
                if (tag4Id4 != null) {
                    userInfo.setRealName(tag4Id4.getStringVal());
                }
                ITag tag4Id5 = createMessage2.getTag4Id(7);
                if (tag4Id5 != null) {
                    userInfo.setCertType(tag4Id5.getIntVal());
                }
                ITag tag4Id6 = createMessage2.getTag4Id(8);
                if (tag4Id6 != null) {
                    userInfo.setCertNo(tag4Id6.getStringVal());
                }
            } catch (FMCommunicationMessageException e2) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户信息查询时，解析平台响应数据异常：" + Util4Java.getExceptionInfo(e2));
                }
                this.cardBusinessBasic.throwExceptionAndClose("用户信息查询时，终端接收到无效的平台响应数据", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            }
            this.cardBusinessBasic.businessFinish(false);
            return userInfo;
        }
    }

    public int modifyUserInfo(UserInfo userInfo) throws BusinessException {
        byte[] bArr;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "用户信息修改...");
        }
        if (userInfo == null) {
            throw new BusinessException("用户信息修改时，传入的参数异常", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "用户信息修改时，业务处理对象为空");
            }
            throw new BusinessException("用户信息修改时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户信息修改时，消息处理器为空");
                }
                throw new BusinessException("用户信息修改时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(1011);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("用户信息修改") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("用户信息修改") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("用户信息修改", server4Business);
            IMessage createMessage = messageHandler.createMessage(1011);
            try {
                ITag createTag = messageHandler.createTag((byte) 1);
                createTag.addValue((int) (byte) userInfo.getUserType());
                createMessage.addTag(createTag);
                ITag createTag2 = messageHandler.createTag((byte) 2);
                createTag2.addValue(userInfo.getUserName());
                createMessage.addTag(createTag2);
                ITag createTag3 = messageHandler.createTag((byte) 3);
                createTag3.addValue(userInfo.getPassword());
                createMessage.addTag(createTag3);
                if (userInfo.getMail() != null && userInfo.getMail().length() > 1) {
                    ITag createTag4 = messageHandler.createTag((byte) 4);
                    createTag4.addValue(userInfo.getMail());
                    createMessage.addTag(createTag4);
                }
                if (userInfo.getPhone() != null && userInfo.getPhone().length() > 1) {
                    ITag createTag5 = messageHandler.createTag((byte) 5);
                    createTag5.addValue(userInfo.getPhone());
                    createMessage.addTag(createTag5);
                }
                if (userInfo.getRealName() != null && userInfo.getRealName().length() > 1) {
                    ITag createTag6 = messageHandler.createTag((byte) 6);
                    createTag6.addValue(userInfo.getRealName());
                    createMessage.addTag(createTag6);
                }
                if (userInfo.getCertType() != -1) {
                    ITag createTag7 = messageHandler.createTag((byte) 7);
                    createTag7.addValue(userInfo.getCertType());
                    createMessage.addTag(createTag7);
                }
                if (userInfo.getCertNo() != null && userInfo.getCertNo().length() > 1) {
                    ITag createTag8 = messageHandler.createTag((byte) 8);
                    createTag8.addValue(userInfo.getCertNo());
                    createMessage.addTag(createTag8);
                }
                bArr = createMessage.toBytes();
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "用户信息修改时，构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
                }
                this.cardBusinessBasic.throwExceptionAndClose("用户信息修改时，构造平台请求数据出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr = null;
            }
            byte[] interaction = this.cardBusinessBasic.interaction(bArr, "用户信息修改", false, server4Business);
            byte[] bArr2 = new byte[2];
            System.arraycopy(interaction, 0, bArr2, 0, bArr2.length);
            if (Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, bArr2)) {
                return 0;
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "用户信息修改:" + FM_Bytes.bytesToHexString(interaction));
            }
            return FM_CN.bcdBytesToInt(bArr2);
        }
    }

    public int modifyUserInfoVer2(UserInfo userInfo) throws BusinessException {
        byte[] bArr;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "用户信息修改...");
        }
        if (userInfo == null) {
            throw new BusinessException(String.valueOf("用户信息修改") + "时，传入的参数异常", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("用户信息修改") + "时，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("用户信息修改") + "时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    FMLog fMLog2 = this.fmLog;
                    String str2 = this.logTag;
                    fMLog2.warn(str2, String.valueOf("用户信息修改") + "时，消息处理器为空");
                }
                throw new BusinessException(String.valueOf("用户信息修改") + "时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(1011);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    FMLog fMLog3 = this.fmLog;
                    String str3 = this.logTag;
                    fMLog3.warn(str3, String.valueOf("用户信息修改") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("用户信息修改") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("用户信息修改", server4Business);
            IMessage createMessage = messageHandler.createMessage(1012);
            try {
                ITag createTag = messageHandler.createTag((byte) 1);
                createTag.addValue((int) (byte) userInfo.getUserType());
                createMessage.addTag(createTag);
                ITag createTag2 = messageHandler.createTag((byte) 2);
                createTag2.addValue(userInfo.getUserName());
                createMessage.addTag(createTag2);
                ITag createTag3 = messageHandler.createTag((byte) 3);
                createTag3.addValue(userInfo.getPassword());
                createMessage.addTag(createTag3);
                if (userInfo.getMail() != null && userInfo.getMail().length() > 1) {
                    ITag createTag4 = messageHandler.createTag((byte) 4);
                    createTag4.addValue(userInfo.getMail());
                    createMessage.addTag(createTag4);
                }
                if (userInfo.getPhone() != null && userInfo.getPhone().length() > 1) {
                    ITag createTag5 = messageHandler.createTag((byte) 5);
                    createTag5.addValue(userInfo.getPhone());
                    createMessage.addTag(createTag5);
                }
                if (userInfo.getRealName() != null && userInfo.getRealName().length() > 1) {
                    ITag createTag6 = messageHandler.createTag((byte) 6);
                    createTag6.addValue(userInfo.getRealName());
                    createMessage.addTag(createTag6);
                }
                if (userInfo.getCertType() != -1) {
                    ITag createTag7 = messageHandler.createTag((byte) 7);
                    createTag7.addValue(userInfo.getCertType());
                    createMessage.addTag(createTag7);
                }
                if (userInfo.getCertNo() != null && userInfo.getCertNo().length() > 1) {
                    ITag createTag8 = messageHandler.createTag((byte) 8);
                    createTag8.addValue(userInfo.getCertNo());
                    createMessage.addTag(createTag8);
                }
                bArr = createMessage.toBytes();
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    FMLog fMLog4 = this.fmLog;
                    String str4 = this.logTag;
                    fMLog4.warn(str4, String.valueOf("用户信息修改") + "时，构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
                }
                CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
                cardBusinessBasic2.throwExceptionAndClose(String.valueOf("用户信息修改") + "时，构造平台请求数据出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr = null;
            }
            byte[] interaction = this.cardBusinessBasic.interaction(bArr, "用户信息修改", false, server4Business);
            byte[] bArr2 = new byte[2];
            System.arraycopy(interaction, 0, bArr2, 0, bArr2.length);
            if (Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, bArr2)) {
                return 0;
            }
            if (this.fmLog != null) {
                FMLog fMLog5 = this.fmLog;
                String str5 = this.logTag;
                fMLog5.warn(str5, String.valueOf("用户信息修改") + ":" + FM_Bytes.bytesToHexString(interaction));
            }
            return FM_CN.bcdBytesToInt(bArr2);
        }
    }

    public String getInvoiceToken(byte[] bArr) throws BusinessException {
        byte[] bArr2;
        String str;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "获取订单发票领取凭证...");
        }
        if (bArr == null || bArr.length < 1) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取订单发票领取凭证，传入的订单编号为空");
            }
            throw new BusinessException("获取订单发票领取凭证，传入的订单编号为空", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取订单发票领取凭证，业务处理对象为空");
            }
            throw new BusinessException("获取订单发票领取凭证，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "获取订单发票领取凭证，消息处理器为空");
                }
                throw new BusinessException("获取订单发票领取凭证，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.GET_INVOICE_VER2);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("获取订单发票领取凭证") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("获取订单发票领取凭证") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("获取订单发票领取凭证", server4Business);
            IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.GET_INVOICE_VER2);
            try {
                ITag createTag = messageHandler.createTag((byte) Constants.TagName.MAIN_ORDER_ID);
                createTag.addValue(bArr);
                createMessage.addTag(createTag);
                bArr2 = createMessage.toBytes();
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "获取订单发票领取凭证，构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
                }
                this.cardBusinessBasic.throwExceptionAndClose("获取订单发票领取凭证，构造平台请求数据出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr2 = null;
            }
            byte[] interaction = this.cardBusinessBasic.interaction(bArr2, "获取订单发票领取凭证", false, server4Business);
            if (interaction == null || interaction.length < 2) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "获取订单发票领取凭证，终端接收到无效的平台响应数据");
                }
                this.cardBusinessBasic.throwExceptionAndClose("获取订单发票领取凭证，终端接收到无效的平台响应数据", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
            }
            byte[] bArr3 = new byte[2];
            System.arraycopy(interaction, 0, bArr3, 0, bArr3.length);
            if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, bArr3)) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "获取订单发票领取凭证,平台处理失败:" + FM_Bytes.bytesToHexString(interaction));
                }
                this.cardBusinessBasic.throwExceptionAndClose("获取订单发票领取凭证，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(bArr3)), false);
            }
            try {
                ITag tag4Id = messageHandler.createMessage(Constants.TradeCode.GET_INVOICE_VER2, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(66);
                if (tag4Id != null) {
                    str = FM_Bytes.bytesToHexString(tag4Id.getBytesVal());
                    this.cardBusinessBasic.businessFinish(false);
                    return str;
                }
            } catch (FMCommunicationMessageException unused) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "获取订单发票领取凭证，解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
                }
                this.cardBusinessBasic.throwExceptionAndClose("获取订单发票领取凭证，终端接收到无效的平台响应数据", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
            }
            str = null;
            this.cardBusinessBasic.businessFinish(false);
            return str;
        }
    }

    public List<InvoiceToken> getInvoiceTokenVer3() throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "获取订单发票领取凭证集合...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取订单发票领取凭证集合，业务处理对象为空");
            }
            throw new BusinessException("获取订单发票领取凭证集合，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取订单发票领取凭证集合，消息处理器为空");
            }
            throw new BusinessException("获取订单发票领取凭证集合，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.GET_INVOICE_VER2);
        if (server4Business == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("获取订单发票领取凭证集合") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("获取订单发票领取凭证集合") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        this.cardBusinessBasic.businessReady("获取订单发票领取凭证集合", server4Business);
        byte[] bArr = null;
        try {
            bArr = messageHandler.createMessage((int) Constants.TradeCode.GET_INVOICE_VER3).toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("获取订单发票领取凭证集合") + "时，，构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("获取订单发票领取凭证集合") + "时，构造平台请求数据出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr, "获取订单发票领取凭证集合", false, server4Business);
        if (interaction == null || interaction.length < 2) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("获取订单发票领取凭证集合") + "时，终端接收到无效的平台响应数据");
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("获取订单发票领取凭证集合") + "时，终端接收到无效的平台响应数据", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
        }
        byte[] bArr2 = new byte[2];
        System.arraycopy(interaction, 0, bArr2, 0, bArr2.length);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, bArr2)) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("获取订单发票领取凭证集合") + "时，平台处理失败:" + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("获取订单发票领取凭证集合") + "时，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(bArr2)), false);
        }
        ArrayList arrayList = new ArrayList();
        try {
            ITag tag4Id = messageHandler.createMessage(Constants.TradeCode.GET_INVOICE_VER3, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(-98);
            if (tag4Id == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("获取订单发票领取凭证集合") + "时，平台响应数据没有包含标签[9E],即平台没有领取发票信息对象集合记录");
                }
                return arrayList;
            }
            ITag[] itemTags = tag4Id.getItemTags();
            if (itemTags != null) {
                if (itemTags.length >= 1) {
                    for (ITag fromTag : itemTags) {
                        arrayList.add(InvoiceToken.fromTag(fromTag));
                    }
                    this.cardBusinessBasic.businessFinish(false);
                    return arrayList;
                }
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("获取订单发票领取凭证集合") + "时，平台响应数据没有包含标签[9D] Tag,即平台没有领取发票信息对象记录");
            }
            return arrayList;
        } catch (FMCommunicationMessageException unused) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取订单发票领取凭证，解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose("获取订单发票领取凭证，终端接收到无效的平台响应数据", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
        }
    }

    public List<Notice> getNotices(int i) throws BusinessException {
        byte[] bArr;
        ITag[] itemTags;
        if (this.cardBusinessBasic == null) {
            if (this.fmLog == null) {
                this.fmLog = LogFactory.getInstance().getLog();
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取平台通知时，业务处理对象为空");
            }
            throw new BusinessException("获取平台通知时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取平台通知时，消息处理器为空");
            }
            throw new BusinessException("获取平台通知时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_NOTICE);
        if (server4Business == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("获取平台通知") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("获取平台通知") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        this.cardBusinessBasic.businessReady("获取平台通知", server4Business);
        IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.QUERY_NOTICE);
        try {
            ITag createTag = messageHandler.createTag((byte) 49);
            createTag.addValue(FM_Bytes.intToBytes(i, 8));
            createMessage.addTag(createTag);
            bArr = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取平台通知时，构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
            }
            this.cardBusinessBasic.throwExceptionAndClose("获取平台通知时，构造平台请求数据出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr, "获取平台通知", false, server4Business);
        if (interaction == null || interaction.length < 2) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取平台通知时，终端接收到无效的平台响应数据");
            }
            this.cardBusinessBasic.throwExceptionAndClose("获取平台通知时，终端接收到无效的平台响应数据", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
        }
        byte[] bArr2 = new byte[2];
        System.arraycopy(interaction, 0, bArr2, 0, bArr2.length);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, bArr2)) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取平台通知,平台处理失败:" + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose("获取平台通知，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(bArr2)), false);
        }
        ArrayList arrayList = new ArrayList();
        try {
            ITag tag4Id = messageHandler.createMessage(Constants.TradeCode.QUERY_NOTICE, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(AuthCode.l);
            if (!(tag4Id == null || (itemTags = tag4Id.getItemTags()) == null)) {
                for (ITag iTag : itemTags) {
                    if (iTag != null) {
                        Notice notice = new Notice();
                        if (iTag.getId() == -110) {
                            ITag[] itemTags2 = iTag.getItemTags();
                            notice.setType(Notice.NOTICE_TXT);
                            for (ITag iTag2 : itemTags2) {
                                switch (iTag2.getId()) {
                                    case 49:
                                        notice.setNo(FM_Bytes.bytesToInt(iTag2.getBytesVal()));
                                        break;
                                    case 50:
                                        notice.setTitle(iTag2.getStringVal());
                                        break;
                                    case 52:
                                        notice.setContent(iTag2.getStringVal());
                                        break;
                                    case 54:
                                        notice.setStartDate(iTag2.getStringVal());
                                        break;
                                    case 55:
                                        notice.setEndDate(iTag2.getStringVal());
                                        break;
                                }
                            }
                        } else if (iTag.getId() == -108) {
                            ITag[] itemTags3 = iTag.getItemTags();
                            notice.setType(Notice.NOTICE_UNSOLVED);
                            for (ITag iTag3 : itemTags3) {
                                if (iTag3 != null) {
                                    if (iTag3.getId() == 17) {
                                        notice.setOrder(iTag3.getBytesVal());
                                    }
                                }
                            }
                        }
                        arrayList.add(notice);
                    }
                }
            }
        } catch (FMCommunicationMessageException unused) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取平台通知时，解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose("获取平台通知时，终端接收到无效的平台响应数据", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
        }
        this.cardBusinessBasic.businessFinish(false);
        return arrayList;
    }

    public VersionInfo queryVersion() throws BusinessException {
        if (this.cardBusinessBasic == null) {
            if (this.fmLog == null) {
                this.fmLog = LogFactory.getInstance().getLog();
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取最新客户端版本信息，业务处理对象为空");
            }
            throw new BusinessException("获取最新客户端版本信息时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取最新客户端版本信息，消息处理器为空");
            }
            throw new BusinessException("获取最新客户端版本信息，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_VERSION);
        if (server4Business == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("获取最新客户端版本信息") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("获取最新客户端版本信息") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        this.cardBusinessBasic.businessReady("获取最新客户端版本信息", server4Business);
        byte[] bArr = null;
        try {
            bArr = messageHandler.createMessage((int) Constants.TradeCode.QUERY_VERSION).toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取最新客户端版本信息，构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
            }
            this.cardBusinessBasic.throwExceptionAndClose("获取最新客户端版本信息，构造平台请求数据出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr, "获取最新客户端版本信息", false, server4Business);
        byte[] bArr2 = new byte[2];
        System.arraycopy(interaction, 0, bArr2, 0, bArr2.length);
        VersionInfo versionInfo = new VersionInfo();
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, bArr2)) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取最新客户端版本信息,平台处理失败:" + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose("获取最新客户端版本信息，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(bArr2)), false);
        }
        try {
            for (ITag iTag : messageHandler.createMessage(Constants.TradeCode.QUERY_VERSION, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(AuthCode.m).getItemTags()) {
                if (iTag != null) {
                    byte id = iTag.getId();
                    if (id != 28) {
                        switch (id) {
                            case 44:
                                versionInfo.setViersion(iTag.getStringVal());
                                break;
                            case 45:
                                boolean z = true;
                                if (iTag.getIntVal() != 1) {
                                    z = false;
                                }
                                versionInfo.setUpdate(z);
                                break;
                        }
                    } else {
                        versionInfo.setUrl(iTag.getStringVal());
                    }
                }
            }
        } catch (FMCommunicationMessageException unused) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取最新客户端版本信息，解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose("获取最新客户端版本信息，终端接收到无效的平台响应数据", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
        }
        this.cardBusinessBasic.businessFinish(false);
        return versionInfo;
    }

    public boolean isRun4plateform() throws BusinessException {
        byte b;
        byte[] bArr;
        if (this.cardBusinessBasic == null) {
            if (this.fmLog == null) {
                this.fmLog = LogFactory.getInstance().getLog();
            }
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("系统启用状态查询") + "时，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("系统启用状态查询") + "时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.warn(str2, String.valueOf("系统启用状态查询") + "时，消息处理器为空");
            }
            throw new BusinessException(String.valueOf("系统启用状态查询") + "时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        Configration configration = this.cardBusinessBasic.getConfigration();
        if (configration != null) {
            b = configration.getOrderSource();
            if (b == 0 && this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str3 = this.logTag;
                fMLog3.warn(str3, String.valueOf("系统启用状态查询") + "时，订单来源在配置文件中未定义");
            }
        } else {
            if (this.fmLog != null) {
                FMLog fMLog4 = this.fmLog;
                String str4 = this.logTag;
                fMLog4.warn(str4, String.valueOf("系统启用状态查询") + "时，未找到配置文件");
            }
            b = 0;
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_VERSION);
        if (server4Business == null) {
            if (this.fmLog != null) {
                FMLog fMLog5 = this.fmLog;
                String str5 = this.logTag;
                fMLog5.warn(str5, String.valueOf("系统启用状态查询") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("系统启用状态查询") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        this.cardBusinessBasic.businessReady("系统启用状态查询", server4Business);
        IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.CHECK_SERVER);
        try {
            ITag createTag = messageHandler.createTag((byte) 30);
            createTag.addValue((int) b);
            createMessage.addTag(createTag);
            bArr = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                FMLog fMLog6 = this.fmLog;
                String str6 = this.logTag;
                fMLog6.warn(str6, String.valueOf("系统启用状态查询") + "时，构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
            }
            CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
            cardBusinessBasic2.throwExceptionAndClose(String.valueOf("系统启用状态查询") + "时，构造平台请求数据出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr, "系统启用状态查询", false, server4Business);
        byte[] bArr2 = new byte[2];
        System.arraycopy(interaction, 0, bArr2, 0, bArr2.length);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, bArr2)) {
            if (this.fmLog != null) {
                FMLog fMLog7 = this.fmLog;
                String str7 = this.logTag;
                fMLog7.warn(str7, String.valueOf("系统启用状态查询") + "时，平台处理失败:" + FM_Bytes.bytesToHexString(interaction));
            }
            return false;
        }
        this.cardBusinessBasic.businessFinish(false);
        return true;
    }

    public List<Activity> queryActivities(EnumCardAppType enumCardAppType) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "活动信息查询...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("活动信息查询") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("活动信息查询") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.warn(str2, String.valueOf("活动信息查询") + "，消息处理器为空");
            }
            throw new BusinessException(String.valueOf("活动信息查询") + "，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else if (enumCardAppType == null) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str3 = this.logTag;
                fMLog3.warn(str3, String.valueOf("活动信息查询") + "，没有传入卡上应用的类型");
            }
            throw new BusinessException(String.valueOf("活动信息查询") + "，没有传入卡上应用的类型", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else {
            String str4 = null;
            Configration configration = this.cardBusinessBasic.getConfigration();
            if (configration != null) {
                str4 = configration.getCompanyCode();
            } else if (this.fmLog != null) {
                FMLog fMLog4 = this.fmLog;
                String str5 = this.logTag;
                fMLog4.warn(str5, String.valueOf("活动信息查询") + "，Configration 为空");
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_ACTIVITIES);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    FMLog fMLog5 = this.fmLog;
                    String str6 = this.logTag;
                    fMLog5.warn(str6, String.valueOf("活动信息查询") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("活动信息查询") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("活动信息查询", server4Business);
            List<Activity> queryActivitiesHandle = queryActivitiesHandle(enumCardAppType, str4, messageHandler, server4Business);
            this.cardBusinessBasic.businessFinish(false);
            return queryActivitiesHandle;
        }
    }

    private List<Activity> queryActivitiesHandle(EnumCardAppType enumCardAppType, String str, IMessageHandler iMessageHandler, String str2) throws BusinessException {
        byte[] bArr;
        Activity fromTag;
        try {
            IMessage createMessage = iMessageHandler.createMessage((int) Constants.TradeCode.QUERY_ACTIVITIES);
            ITag createTag = iMessageHandler.createTag((byte) 14);
            createTag.addValue(enumCardAppType.getId());
            createMessage.addTag(createTag);
            ITag createTag2 = iMessageHandler.createTag((byte) Constants.TagName.COMPANY_CODE);
            createTag2.addValue(str);
            createMessage.addTag(createTag2);
            bArr = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("活动信息查询") + ",构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("活动信息查询") + ",构造平台请求数据出现失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr, "活动信息查询", false, str2);
        byte[] copyOf = Arrays.copyOf(interaction, 2);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, copyOf)) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, String.valueOf("活动信息查询") + "，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("活动信息查询") + "，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(copyOf)), true);
        }
        ArrayList arrayList = new ArrayList();
        try {
            ITag tag4Id = iMessageHandler.createMessage(Constants.TradeCode.QUERY_ORDERS_VER2, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(-128);
            if (tag4Id == null) {
                if (this.fmLog != null) {
                    this.fmLog.debug(this.logTag, String.valueOf("活动信息查询") + ",平台响应的活动信息集合为空");
                }
                return arrayList;
            }
            ITag[] itemTags = tag4Id.getItemTags();
            if (itemTags != null) {
                if (itemTags.length >= 1) {
                    for (ITag iTag : itemTags) {
                        if (!(iTag == null || (fromTag = Activity.fromTag(iTag)) == null)) {
                            arrayList.add(fromTag);
                        }
                    }
                    return arrayList;
                }
            }
            if (this.fmLog != null) {
                this.fmLog.debug(this.logTag, String.valueOf("活动信息查询") + ",平台响应的活动信息集合为空");
            }
            return arrayList;
        } catch (FMCommunicationMessageException unused) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("活动信息查询") + "，解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("活动信息查询") + ",解析平台响应数据失败", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
        }
    }

    public PreDepositInfo queryPreDeposit(EnumCardAppType enumCardAppType) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "预置金信息查询...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("预置金信息查询") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("预置金信息查询") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.warn(str2, String.valueOf("预置金信息查询") + "，消息处理器为空");
            }
            throw new BusinessException(String.valueOf("预置金信息查询") + "，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else if (enumCardAppType == null) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str3 = this.logTag;
                fMLog3.warn(str3, String.valueOf("预置金信息查询") + "，没有传入卡上应用的类型");
            }
            throw new BusinessException(String.valueOf("预置金信息查询") + "，没有传入卡上应用的类型", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else {
            String str4 = null;
            Configration configration = this.cardBusinessBasic.getConfigration();
            if (configration != null) {
                str4 = configration.getCompanyCode();
            } else if (this.fmLog != null) {
                FMLog fMLog4 = this.fmLog;
                String str5 = this.logTag;
                fMLog4.warn(str5, String.valueOf("预置金信息查询") + "，Configration 为空");
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_PREDEPOSIT);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    FMLog fMLog5 = this.fmLog;
                    String str6 = this.logTag;
                    fMLog5.warn(str6, String.valueOf("预置金信息查询") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("预置金信息查询") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("预置金信息查询", server4Business);
            PreDepositInfo queryPreDepositHandle = queryPreDepositHandle(enumCardAppType, str4, messageHandler, server4Business);
            this.cardBusinessBasic.businessFinish(false);
            return queryPreDepositHandle;
        }
    }

    private PreDepositInfo queryPreDepositHandle(EnumCardAppType enumCardAppType, String str, IMessageHandler iMessageHandler, String str2) throws BusinessException {
        byte[] bArr;
        PreDepositInfo preDepositInfo = new PreDepositInfo();
        try {
            IMessage createMessage = iMessageHandler.createMessage((int) Constants.TradeCode.QUERY_PREDEPOSIT);
            ITag createTag = iMessageHandler.createTag((byte) 14);
            createTag.addValue(enumCardAppType.getId());
            createMessage.addTag(createTag);
            ITag createTag2 = iMessageHandler.createTag((byte) Constants.TagName.COMPANY_CODE);
            createTag2.addValue(str);
            createMessage.addTag(createTag2);
            bArr = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str3 = this.logTag;
                fMLog.warn(str3, String.valueOf("预置金信息查询") + ",构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
            }
            CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
            cardBusinessBasic2.throwExceptionAndClose(String.valueOf("预置金信息查询") + ",构造平台请求数据出现失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr, "预置金信息查询", false, str2);
        byte[] copyOf = Arrays.copyOf(interaction, 2);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, copyOf)) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str4 = this.logTag;
                fMLog2.error(str4, String.valueOf("预置金信息查询") + "，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
            }
            CardBusinessBasic cardBusinessBasic3 = this.cardBusinessBasic;
            cardBusinessBasic3.throwExceptionAndClose(String.valueOf("预置金信息查询") + "，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(copyOf)), true);
        }
        try {
            IMessage createMessage2 = iMessageHandler.createMessage(Constants.TradeCode.QUERY_PREDEPOSIT, Arrays.copyOfRange(interaction, 2, interaction.length));
            ITag tag4Id = createMessage2.getTag4Id(90);
            if (tag4Id != null) {
                preDepositInfo.setTotal(FM_Bytes.bytesToInt(tag4Id.getBytesVal()));
            } else if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str5 = this.logTag;
                fMLog3.warn(str5, String.valueOf("预置金信息查询") + ",时，未包含预置金总额");
            }
            ITag tag4Id2 = createMessage2.getTag4Id(91);
            if (tag4Id2 != null) {
                preDepositInfo.setBlance(FM_Bytes.bytesToInt(tag4Id2.getBytesVal()));
            } else if (this.fmLog != null) {
                FMLog fMLog4 = this.fmLog;
                String str6 = this.logTag;
                fMLog4.warn(str6, String.valueOf("预置金信息查询") + ",时，未包含预置金余额");
            }
        } catch (FMCommunicationMessageException unused) {
            if (this.fmLog != null) {
                FMLog fMLog5 = this.fmLog;
                String str7 = this.logTag;
                fMLog5.warn(str7, String.valueOf("预置金信息查询") + "，解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
            }
            CardBusinessBasic cardBusinessBasic4 = this.cardBusinessBasic;
            cardBusinessBasic4.throwExceptionAndClose(String.valueOf("预置金信息查询") + ",解析平台响应数据失败", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
        }
        return preDepositInfo;
    }

    public List<PreDepositInfo> queryPreDepositVer2(EnumCardAppType enumCardAppType) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "预置金信息查询...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("预置金信息查询") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("预置金信息查询") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.warn(str2, String.valueOf("预置金信息查询") + "，消息处理器为空");
            }
            throw new BusinessException(String.valueOf("预置金信息查询") + "，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else if (enumCardAppType == null) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str3 = this.logTag;
                fMLog3.warn(str3, String.valueOf("预置金信息查询") + "，没有传入卡上应用的类型");
            }
            throw new BusinessException(String.valueOf("预置金信息查询") + "，没有传入卡上应用的类型", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else {
            String str4 = null;
            Configration configration = this.cardBusinessBasic.getConfigration();
            if (configration != null) {
                str4 = configration.getCompanyCode();
            } else if (this.fmLog != null) {
                FMLog fMLog4 = this.fmLog;
                String str5 = this.logTag;
                fMLog4.warn(str5, String.valueOf("预置金信息查询") + "，Configration 为空");
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_PREDEPOSIT_VER2);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    FMLog fMLog5 = this.fmLog;
                    String str6 = this.logTag;
                    fMLog5.warn(str6, String.valueOf("预置金信息查询") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("预置金信息查询") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("预置金信息查询", server4Business);
            List<PreDepositInfo> queryPreDepositVer2Handle = queryPreDepositVer2Handle(enumCardAppType, str4, messageHandler, server4Business);
            this.cardBusinessBasic.businessFinish(false);
            return queryPreDepositVer2Handle;
        }
    }

    private List<PreDepositInfo> queryPreDepositVer2Handle(EnumCardAppType enumCardAppType, String str, IMessageHandler iMessageHandler, String str2) throws BusinessException {
        byte[] bArr;
        PreDepositInfo fromTag;
        try {
            IMessage createMessage = iMessageHandler.createMessage((int) Constants.TradeCode.QUERY_PREDEPOSIT_VER2);
            ITag createTag = iMessageHandler.createTag((byte) 14);
            createTag.addValue(enumCardAppType.getId());
            createMessage.addTag(createTag);
            ITag createTag2 = iMessageHandler.createTag((byte) Constants.TagName.COMPANY_CODE);
            createTag2.addValue(str);
            createMessage.addTag(createTag2);
            bArr = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("预置金信息查询") + ",构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("预置金信息查询") + ",构造平台请求数据出现失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr, "预置金信息查询", false, str2);
        byte[] copyOf = Arrays.copyOf(interaction, 2);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, copyOf)) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, String.valueOf("预置金信息查询") + "，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("预置金信息查询") + "，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(copyOf)), true);
        }
        ArrayList arrayList = new ArrayList();
        try {
            ITag tag4Id = iMessageHandler.createMessage(Constants.TradeCode.QUERY_PREDEPOSIT_VER2, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(-105);
            if (tag4Id == null) {
                if (this.fmLog != null) {
                    this.fmLog.debug(this.logTag, String.valueOf("预置金信息查询") + ",没有对应的额度信息");
                }
                return arrayList;
            }
            ITag[] itemTags = tag4Id.getItemTags();
            if (itemTags != null) {
                if (itemTags.length >= 1) {
                    for (ITag iTag : itemTags) {
                        if (!(iTag == null || (fromTag = PreDepositInfo.fromTag(iTag)) == null)) {
                            arrayList.add(fromTag);
                        }
                    }
                    return arrayList;
                }
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("预置金信息查询") + ",额度信息集合为空");
            }
            return arrayList;
        } catch (FMCommunicationMessageException unused) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("预置金信息查询") + "，解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("预置金信息查询") + ",解析平台响应数据失败", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
        }
    }

    public CardBusinessStatus queryCardBusinessStatus(String str) throws BusinessException {
        byte[] bArr;
        EnumAppActivationStatus activationStatus4ID;
        EnumBusinessOrderStatus businessOrderStatus4ID;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "获取卡上应用的状态...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog == null) {
                this.fmLog = LogFactory.getInstance().getLog();
            }
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str2 = this.logTag;
                fMLog.warn(str2, String.valueOf("获取卡上应用的状态") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("获取卡上应用的状态") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str3 = this.logTag;
                fMLog2.warn(str3, String.valueOf("获取卡上应用的状态") + "，消息处理器为空");
            }
            throw new BusinessException(String.valueOf("获取卡上应用的状态") + "，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS);
        if (server4Business == null) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str4 = this.logTag;
                fMLog3.warn(str4, String.valueOf("获取卡上应用的状态") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("获取卡上应用的状态") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        this.cardBusinessBasic.businessReady("获取卡上应用的状态", server4Business);
        IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS);
        try {
            ITag createTag = messageHandler.createTag((byte) 15);
            createTag.addValue(str);
            createMessage.addTag(createTag);
            bArr = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                FMLog fMLog4 = this.fmLog;
                String str5 = this.logTag;
                fMLog4.warn(str5, String.valueOf("获取卡上应用的状态") + "，构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
            }
            CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
            cardBusinessBasic2.throwExceptionAndClose(String.valueOf("获取卡上应用的状态") + "，构造平台请求数据出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr, "获取卡上应用的状态", false, server4Business);
        byte[] bArr2 = new byte[2];
        System.arraycopy(interaction, 0, bArr2, 0, bArr2.length);
        CardBusinessStatus cardBusinessStatus = new CardBusinessStatus();
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, bArr2)) {
            if (this.fmLog != null) {
                FMLog fMLog5 = this.fmLog;
                String str6 = this.logTag;
                fMLog5.warn(str6, String.valueOf("获取卡上应用的状态") + ",平台处理失败:" + FM_Bytes.bytesToHexString(interaction));
            }
            CardBusinessBasic cardBusinessBasic3 = this.cardBusinessBasic;
            cardBusinessBasic3.throwExceptionAndClose(String.valueOf("获取卡上应用的状态") + "，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(bArr2)), false);
        }
        try {
            IMessage createMessage2 = messageHandler.createMessage(Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS, Arrays.copyOfRange(interaction, 2, interaction.length));
            ITag tag4Id = createMessage2.getTag4Id(62);
            if (!(tag4Id == null || (businessOrderStatus4ID = EnumBusinessOrderStatus.getBusinessOrderStatus4ID(tag4Id.getBytesVal()[0])) == null)) {
                cardBusinessStatus.setBusinessOrderStatus(businessOrderStatus4ID);
            }
            ITag tag4Id2 = createMessage2.getTag4Id(63);
            if (!(tag4Id2 == null || (activationStatus4ID = EnumAppActivationStatus.getActivationStatus4ID(tag4Id2.getBytesVal()[0])) == null)) {
                cardBusinessStatus.setActivationStatus(activationStatus4ID);
            }
        } catch (FMCommunicationMessageException unused) {
            if (this.fmLog != null) {
                FMLog fMLog6 = this.fmLog;
                String str7 = this.logTag;
                fMLog6.warn(str7, "获取最新客户端版本信息，解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose("获取最新客户端版本信息，终端接收到无效的平台响应数据", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
        }
        this.cardBusinessBasic.businessFinish(false);
        return cardBusinessStatus;
    }

    public int setOrderStatus(byte[] bArr, EnumOrderType enumOrderType, byte[] bArr2, EnumOrderStatus enumOrderStatus) throws BusinessException {
        byte[] bArr3;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "订单状态设置...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "订单状态设置时，业务处理对象为空");
            }
            throw new BusinessException("订单状态设置时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "订单状态设置时，消息处理器为空");
            }
            throw new BusinessException("订单状态设置时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.SET_ORDER_STATUS);
        if (server4Business == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("订单状态设置") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("订单状态设置") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        this.cardBusinessBasic.businessReady("订单状态设置", server4Business);
        IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.SET_ORDER_STATUS);
        if (bArr != null) {
            try {
                if (bArr.length > 0) {
                    ITag createTag = messageHandler.createTag((byte) Constants.TagName.ACTIVITY_INFO);
                    createTag.addValue(bArr);
                    createMessage.addTag(createTag);
                }
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "订单状态设置时,构造平台请求数据异常:" + Util4Java.getExceptionInfo(e));
                }
                this.cardBusinessBasic.throwExceptionAndClose("订单状态设置时,构造平台请求数据失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr3 = null;
            }
        }
        if (enumOrderType != null) {
            ITag createTag2 = messageHandler.createTag((byte) Constants.TagName.ORDER_TYPE);
            createTag2.addValue(enumOrderType.getId());
            createMessage.addTag(createTag2);
            if (bArr2 != null && bArr2.length > 0) {
                if (EnumOrderType.MAIN == enumOrderType) {
                    createTag2 = messageHandler.createTag((byte) Constants.TagName.MAIN_ORDER_ID);
                }
                if (EnumOrderType.BUSINESS == enumOrderType) {
                    createTag2 = messageHandler.createTag((byte) 17);
                }
                if (EnumOrderType.PAY == enumOrderType) {
                    createTag2 = messageHandler.createTag((byte) Constants.TagName.PAY_ORDER_ID);
                }
                createTag2.addValue(bArr2);
                createMessage.addTag(createTag2);
            }
            if (enumOrderStatus != null) {
                ITag createTag3 = messageHandler.createTag((byte) 21);
                createTag3.addValue(enumOrderStatus.getId());
                createMessage.addTag(createTag3);
            }
        }
        bArr3 = createMessage.toBytes();
        byte[] interaction = this.cardBusinessBasic.interaction(bArr3, "订单状态设置", false, server4Business);
        byte[] copyOf = Arrays.copyOf(interaction, 2);
        this.cardBusinessBasic.businessFinish(false);
        if (Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, copyOf)) {
            return 0;
        }
        if (this.fmLog != null) {
            this.fmLog.error(this.logTag, "订单状态设置时，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
        }
        return FM_CN.bcdBytesToInt(copyOf);
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0092, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0096, code lost:
        if (r7.fmLog != null) goto L_0x0098;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0098, code lost:
        r7.fmLog.error(r7.logTag, "获取卡上应用有效期时出现异常");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a2, code lost:
        r7.cardBusinessBasic.throwExceptionAndClose("获取卡上应用有效期时出现异常", cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00ac, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00b1, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b4, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:32:0x0094 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getTime4Validity(cn.com.fmsh.tsm.business.enums.EnumCardAppType r8) throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r7 = this;
            cn.com.fmsh.util.log.FMLog r0 = r7.fmLog
            if (r0 != 0) goto L_0x000e
            cn.com.fmsh.util.log.LogFactory r0 = cn.com.fmsh.util.log.LogFactory.getInstance()
            cn.com.fmsh.util.log.FMLog r0 = r0.getLog()
            r7.fmLog = r0
        L_0x000e:
            cn.com.fmsh.util.log.FMLog r0 = r7.fmLog
            if (r0 == 0) goto L_0x001c
            cn.com.fmsh.util.log.FMLog r0 = r7.fmLog
            java.lang.String r1 = r7.logTag
            java.lang.String r2 = "获取卡的有效期..."
            r0.info(r1, r2)
        L_0x001c:
            cn.com.fmsh.tsm.business.card.CardManagerFactory r0 = cn.com.fmsh.tsm.business.card.CardManagerFactory.instance()
            cn.com.fmsh.tsm.business.card.base.CardManager r8 = r0.getCardManager(r8)
            r0 = 0
            if (r8 != 0) goto L_0x0028
            return r0
        L_0x0028:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r1 = r7.cardBusinessBasic
            cn.com.fmsh.script.ApduHandler r1 = r1.getApduHandler()
            r2 = 0
            if (r1 != 0) goto L_0x0049
            cn.com.fmsh.util.log.FMLog r3 = r7.fmLog
            if (r3 == 0) goto L_0x003f
            cn.com.fmsh.util.log.FMLog r3 = r7.fmLog
            java.lang.String r4 = r7.logTag
            java.lang.String r5 = "获取卡上应用有效期时，APDU处理器为空"
            r3.error(r4, r5)
        L_0x003f:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r7.cardBusinessBasic
            java.lang.String r4 = "获取卡上应用有效期时，请先切换卡的访问方式(OMA/NFC)"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_null
            r3.throwExceptionAndClose((java.lang.String) r4, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r2)
        L_0x0049:
            boolean r3 = r1.isConnect()
            r4 = 1
            if (r3 == 0) goto L_0x0069
            cn.com.fmsh.util.log.FMLog r3 = r7.fmLog
            if (r3 == 0) goto L_0x005e
            cn.com.fmsh.util.log.FMLog r3 = r7.fmLog
            java.lang.String r5 = r7.logTag
            java.lang.String r6 = "获取卡上应用有效期时，APDU处理器正忙"
            r3.error(r5, r6)
        L_0x005e:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r7.cardBusinessBasic
            java.lang.String r5 = "获取卡上应用有效期时，APDU处理器正忙"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_busying
            r3.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r2)
            goto L_0x0087
        L_0x0069:
            boolean r2 = r1.connect()
            if (r2 != 0) goto L_0x0087
            cn.com.fmsh.util.log.FMLog r2 = r7.fmLog
            if (r2 == 0) goto L_0x007d
            cn.com.fmsh.util.log.FMLog r2 = r7.fmLog
            java.lang.String r3 = r7.logTag
            java.lang.String r5 = "获取卡上应用有效期时，连接卡失败"
            r2.error(r3, r5)
        L_0x007d:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r7.cardBusinessBasic
            java.lang.String r3 = "获取卡上应用有效期时，连接卡失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception
            r2.throwExceptionAndClose((java.lang.String) r3, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r4)
        L_0x0087:
            r8.setApduHandler(r1)
            java.lang.String r8 = r8.getTime4Validity()     // Catch:{ BusinessException -> 0x0094 }
            r1.close()
            goto L_0x00b0
        L_0x0092:
            r8 = move-exception
            goto L_0x00b1
        L_0x0094:
            cn.com.fmsh.util.log.FMLog r8 = r7.fmLog     // Catch:{ all -> 0x0092 }
            if (r8 == 0) goto L_0x00a2
            cn.com.fmsh.util.log.FMLog r8 = r7.fmLog     // Catch:{ all -> 0x0092 }
            java.lang.String r2 = r7.logTag     // Catch:{ all -> 0x0092 }
            java.lang.String r3 = "获取卡上应用有效期时出现异常"
            r8.error(r2, r3)     // Catch:{ all -> 0x0092 }
        L_0x00a2:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r8 = r7.cardBusinessBasic     // Catch:{ all -> 0x0092 }
            java.lang.String r2 = "获取卡上应用有效期时出现异常"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception     // Catch:{ all -> 0x0092 }
            r8.throwExceptionAndClose((java.lang.String) r2, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r3, (boolean) r4)     // Catch:{ all -> 0x0092 }
            r1.close()
            r8 = r0
        L_0x00b0:
            return r8
        L_0x00b1:
            r1.close()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardAppTradeImpl.getTime4Validity(cn.com.fmsh.tsm.business.enums.EnumCardAppType):java.lang.String");
    }

    public TicketOperateResult buyTicket(String str, byte[] bArr) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            FMLog fMLog = this.fmLog;
            String str2 = this.logTag;
            fMLog.info(str2, String.valueOf("申通业务申请") + "...");
        }
        if (str == null || str.length() < 1) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str3 = this.logTag;
                fMLog2.warn(str3, String.valueOf("申通业务申请") + "时，用户信息为空");
            }
            throw new BusinessException(String.valueOf("申通业务申请") + "时，传入的参数异常", BusinessException.ErrorMessage.local_business_para_error);
        } else if (bArr == null || bArr.length < 1) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str4 = this.logTag;
                fMLog3.warn(str4, String.valueOf("申通业务申请") + "时，活动验证数据为空");
            }
            throw new BusinessException(String.valueOf("申通业务申请") + "时，传入的参数异常", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog4 = this.fmLog;
                String str5 = this.logTag;
                fMLog4.warn(str5, String.valueOf("申通业务申请") + "时，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("申通业务申请") + "时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    FMLog fMLog5 = this.fmLog;
                    String str6 = this.logTag;
                    fMLog5.warn(str6, String.valueOf("申通业务申请") + "时，消息处理器为空");
                }
                throw new BusinessException(String.valueOf("申通业务申请") + "时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            String str7 = null;
            Configration configration = this.cardBusinessBasic.getConfigration();
            if (configration != null) {
                str7 = configration.getCompanyCode();
            } else if (this.fmLog != null) {
                FMLog fMLog6 = this.fmLog;
                String str8 = this.logTag;
                fMLog6.warn(str8, String.valueOf("申通业务申请") + "时，Configration 为空");
            }
            String str9 = str7;
            if (str9 == null) {
                if (this.fmLog != null) {
                    FMLog fMLog7 = this.fmLog;
                    String str10 = this.logTag;
                    fMLog7.warn(str10, String.valueOf("申通业务申请") + "时，用户所属商户为空");
                }
                throw new BusinessException(String.valueOf("申通业务申请") + "时，用户所属商户为空", BusinessException.ErrorMessage.local_business_para_error);
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.TICKET_MANAGER);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    FMLog fMLog8 = this.fmLog;
                    String str11 = this.logTag;
                    fMLog8.warn(str11, String.valueOf("申通业务申请") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("申通业务申请") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("申通业务申请", server4Business);
            TicketOperateResult applyBusinessHandle = applyBusinessHandle(str, str9, bArr, messageHandler, server4Business);
            this.cardBusinessBasic.businessFinish(false);
            return applyBusinessHandle;
        }
    }

    private TicketOperateResult applyBusinessHandle(String str, String str2, byte[] bArr, IMessageHandler iMessageHandler, String str3) throws BusinessException {
        byte[] bArr2;
        IMessage createMessage = iMessageHandler.createMessage((int) Constants.TradeCode.TICKET_MANAGER);
        try {
            ITag createTag = iMessageHandler.createTag((byte) 2);
            createTag.addValue(str);
            createMessage.addTag(createTag);
            ITag createTag2 = iMessageHandler.createTag((byte) Constants.TagName.COMPANY_CODE);
            createTag2.addValue(str2);
            createMessage.addTag(createTag2);
            ITag createTag3 = iMessageHandler.createTag((byte) Constants.TagName.BUSINESS_ORDER_OP_TYPE);
            createTag3.addValue(91);
            createMessage.addTag(createTag3);
            ITag createTag4 = iMessageHandler.createTag((byte) Constants.TagName.ACTIVITY_INFO);
            createTag4.addValue(bArr);
            createMessage.addTag(createTag4);
            bArr2 = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str4 = this.logTag;
                fMLog.warn(str4, String.valueOf("申通业务申请") + "时，消息处理器出现异常：" + Util4Java.getExceptionInfo(e));
            }
            CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
            cardBusinessBasic2.throwExceptionAndClose(String.valueOf("申通业务申请") + "时，消息处理器出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr2 = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr2, "申通业务申请", false, str3);
        byte[] bArr3 = new byte[2];
        System.arraycopy(interaction, 0, bArr3, 0, bArr3.length);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, bArr3)) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str5 = this.logTag;
                fMLog2.error(str5, String.valueOf("申通业务申请") + "时，平台处理失败: " + FM_Bytes.bytesToHexString(interaction));
            }
            CardBusinessBasic cardBusinessBasic3 = this.cardBusinessBasic;
            cardBusinessBasic3.throwExceptionAndClose(String.valueOf("申通业务申请") + "时，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(bArr3)), false);
        }
        TicketOperateResult ticketOperateResult = new TicketOperateResult();
        try {
            ITag tag4Id = iMessageHandler.createMessage(Constants.TradeCode.TICKET_MANAGER, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(17);
            if (tag4Id != null) {
                ticketOperateResult.setTicketStub(tag4Id.getBytesVal());
            }
        } catch (FMCommunicationMessageException e2) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str6 = this.logTag;
                fMLog3.error(str6, String.valueOf("申通业务申请") + "时，解析平台响应出现异常: " + Util4Java.getExceptionInfo(e2));
            }
            CardBusinessBasic cardBusinessBasic4 = this.cardBusinessBasic;
            cardBusinessBasic4.throwExceptionAndClose(String.valueOf("申通业务申请") + "时，解析平台响应失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
        }
        return ticketOperateResult;
    }

    public boolean returnBusiness(String str, byte[] bArr) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            FMLog fMLog = this.fmLog;
            String str2 = this.logTag;
            fMLog.info(str2, String.valueOf("申通退票处理") + "...");
        }
        if (str == null || str.length() < 1) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str3 = this.logTag;
                fMLog2.warn(str3, String.valueOf("申通退票处理") + "时，用户信息为空");
            }
            throw new BusinessException(String.valueOf("申通退票处理") + "时，传入的参数异常", BusinessException.ErrorMessage.local_business_para_error);
        } else if (bArr == null || bArr.length < 1) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str4 = this.logTag;
                fMLog3.warn(str4, String.valueOf("申通退票处理") + "时，活动验证数据为空");
            }
            throw new BusinessException(String.valueOf("申通退票处理") + "时，传入的参数异常", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog4 = this.fmLog;
                String str5 = this.logTag;
                fMLog4.warn(str5, String.valueOf("申通退票处理") + "时，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("申通退票处理") + "时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    FMLog fMLog5 = this.fmLog;
                    String str6 = this.logTag;
                    fMLog5.warn(str6, String.valueOf("申通退票处理") + "时，消息处理器为空");
                }
                throw new BusinessException(String.valueOf("申通退票处理") + "时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            String str7 = null;
            Configration configration = this.cardBusinessBasic.getConfigration();
            if (configration != null) {
                str7 = configration.getCompanyCode();
            } else if (this.fmLog != null) {
                FMLog fMLog6 = this.fmLog;
                String str8 = this.logTag;
                fMLog6.warn(str8, String.valueOf("申通退票处理") + "时，Configration 为空");
            }
            String str9 = str7;
            if (str9 == null) {
                if (this.fmLog != null) {
                    FMLog fMLog7 = this.fmLog;
                    String str10 = this.logTag;
                    fMLog7.warn(str10, String.valueOf("申通退票处理") + "时，用户所属商户为空");
                }
                throw new BusinessException(String.valueOf("申通退票处理") + "时，用户所属商户为空", BusinessException.ErrorMessage.local_business_para_error);
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.TICKET_MANAGER);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    FMLog fMLog8 = this.fmLog;
                    String str11 = this.logTag;
                    fMLog8.warn(str11, String.valueOf("申通退票处理") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("申通退票处理") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("申通退票处理", server4Business);
            boolean returnBusinessHandle = returnBusinessHandle(str, str9, bArr, messageHandler, server4Business);
            this.cardBusinessBasic.businessFinish(false);
            return returnBusinessHandle;
        }
    }

    private boolean returnBusinessHandle(String str, String str2, byte[] bArr, IMessageHandler iMessageHandler, String str3) throws BusinessException {
        byte[] bArr2;
        IMessage createMessage = iMessageHandler.createMessage((int) Constants.TradeCode.TICKET_MANAGER);
        try {
            ITag createTag = iMessageHandler.createTag((byte) 2);
            createTag.addValue(str);
            createMessage.addTag(createTag);
            ITag createTag2 = iMessageHandler.createTag((byte) Constants.TagName.COMPANY_CODE);
            createTag2.addValue(str2);
            createMessage.addTag(createTag2);
            ITag createTag3 = iMessageHandler.createTag((byte) Constants.TagName.BUSINESS_ORDER_OP_TYPE);
            createTag3.addValue(92);
            createMessage.addTag(createTag3);
            ITag createTag4 = iMessageHandler.createTag((byte) Constants.TagName.ACTIVITY_INFO);
            createTag4.addValue(bArr);
            createMessage.addTag(createTag4);
            bArr2 = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str4 = this.logTag;
                fMLog.warn(str4, String.valueOf("申通退票处理") + "时，消息处理器出现异常：" + Util4Java.getExceptionInfo(e));
            }
            CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
            cardBusinessBasic2.throwExceptionAndClose(String.valueOf("申通退票处理") + "时，消息处理器出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr2 = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr2, "申通退票处理", false, str3);
        byte[] bArr3 = new byte[2];
        System.arraycopy(interaction, 0, bArr3, 0, bArr3.length);
        if (Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, bArr3)) {
            return true;
        }
        if (this.fmLog != null) {
            FMLog fMLog2 = this.fmLog;
            String str5 = this.logTag;
            fMLog2.error(str5, String.valueOf("申通退票处理") + "时，平台处理失败: " + FM_Bytes.bytesToHexString(interaction));
        }
        return false;
    }

    public TicketOperateResult queryLastOperate(String str, String str2, byte[] bArr) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            FMLog fMLog = this.fmLog;
            String str3 = this.logTag;
            fMLog.info(str3, String.valueOf("申通业务申请") + "...");
        }
        if (str == null || str.length() < 1) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str4 = this.logTag;
                fMLog2.warn(str4, String.valueOf("申通业务申请") + "时，用户信息为空");
            }
            throw new BusinessException(String.valueOf("申通业务申请") + "时，传入的参数异常", BusinessException.ErrorMessage.local_business_para_error);
        } else if (str2 == null || str2.length() < 1) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str5 = this.logTag;
                fMLog3.warn(str5, String.valueOf("申通业务申请") + "时，终端型号规格为空");
            }
            throw new BusinessException(String.valueOf("申通业务申请") + "时，传入的参数异常", BusinessException.ErrorMessage.local_business_para_error);
        } else if (bArr == null || bArr.length < 1) {
            if (this.fmLog != null) {
                FMLog fMLog4 = this.fmLog;
                String str6 = this.logTag;
                fMLog4.warn(str6, String.valueOf("申通业务申请") + "时，eSE标识为空");
            }
            throw new BusinessException(String.valueOf("申通业务申请") + "时，传入的参数异常", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog5 = this.fmLog;
                String str7 = this.logTag;
                fMLog5.warn(str7, String.valueOf("申通业务申请") + "时，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("申通业务申请") + "时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    FMLog fMLog6 = this.fmLog;
                    String str8 = this.logTag;
                    fMLog6.warn(str8, String.valueOf("申通业务申请") + "时，消息处理器为空");
                }
                throw new BusinessException(String.valueOf("申通业务申请") + "时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            String str9 = null;
            Configration configration = this.cardBusinessBasic.getConfigration();
            if (configration != null) {
                str9 = configration.getCompanyCode();
            } else if (this.fmLog != null) {
                FMLog fMLog7 = this.fmLog;
                String str10 = this.logTag;
                fMLog7.warn(str10, String.valueOf("申通业务申请") + "时，Configration 为空");
            }
            String str11 = str9;
            if (str11 == null) {
                if (this.fmLog != null) {
                    FMLog fMLog8 = this.fmLog;
                    String str12 = this.logTag;
                    fMLog8.warn(str12, String.valueOf("申通业务申请") + "时，用户所属商户为空");
                }
                throw new BusinessException(String.valueOf("申通业务申请") + "时，用户所属商户为空", BusinessException.ErrorMessage.local_business_para_error);
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.TICKET_MANAGER);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    FMLog fMLog9 = this.fmLog;
                    String str13 = this.logTag;
                    fMLog9.warn(str13, String.valueOf("申通业务申请") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("申通业务申请") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("申通业务申请", server4Business);
            TicketOperateResult queryLastOperateHandle = queryLastOperateHandle(str, str11, str2, bArr, messageHandler, server4Business);
            this.cardBusinessBasic.businessFinish(false);
            return queryLastOperateHandle;
        }
    }

    private TicketOperateResult queryLastOperateHandle(String str, String str2, String str3, byte[] bArr, IMessageHandler iMessageHandler, String str4) throws BusinessException {
        byte[] bArr2;
        IMessage createMessage = iMessageHandler.createMessage((int) Constants.TradeCode.TICKET_MANAGER);
        try {
            ITag createTag = iMessageHandler.createTag((byte) 2);
            createTag.addValue(str);
            createMessage.addTag(createTag);
            ITag createTag2 = iMessageHandler.createTag((byte) Constants.TagName.COMPANY_CODE);
            createTag2.addValue(str2);
            createMessage.addTag(createTag2);
            ITag createTag3 = iMessageHandler.createTag((byte) Constants.TagName.BUSINESS_ORDER_OP_TYPE);
            createTag3.addValue(94);
            createMessage.addTag(createTag3);
            ITag createTag4 = iMessageHandler.createTag((byte) Constants.TagName.SEID);
            createTag4.addValue(bArr);
            createMessage.addTag(createTag4);
            ITag createTag5 = iMessageHandler.createTag((byte) Constants.TagName.DEVICE_MODEL);
            createTag5.addValue(str3);
            createMessage.addTag(createTag5);
            bArr2 = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str5 = this.logTag;
                fMLog.warn(str5, String.valueOf("申通业务申请") + "时，消息处理器出现异常：" + Util4Java.getExceptionInfo(e));
            }
            CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
            cardBusinessBasic2.throwExceptionAndClose(String.valueOf("申通业务申请") + "时，消息处理器出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr2 = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr2, "申通业务申请", false, str4);
        byte[] bArr3 = new byte[2];
        System.arraycopy(interaction, 0, bArr3, 0, bArr3.length);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, bArr3)) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str6 = this.logTag;
                fMLog2.error(str6, String.valueOf("申通业务申请") + "时，平台处理失败: " + FM_Bytes.bytesToHexString(interaction));
            }
            CardBusinessBasic cardBusinessBasic3 = this.cardBusinessBasic;
            cardBusinessBasic3.throwExceptionAndClose(String.valueOf("申通业务申请") + "时，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(bArr3)), false);
        }
        TicketOperateResult ticketOperateResult = new TicketOperateResult();
        try {
            IMessage createMessage2 = iMessageHandler.createMessage(Constants.TradeCode.TICKET_MANAGER, Arrays.copyOfRange(interaction, 2, interaction.length));
            ITag tag4Id = createMessage2.getTag4Id(21);
            if (tag4Id != null) {
                ticketOperateResult.setOperateResult(tag4Id.getIntVal());
            } else {
                if (this.fmLog != null) {
                    FMLog fMLog3 = this.fmLog;
                    String str7 = this.logTag;
                    fMLog3.error(str7, String.valueOf("申通业务申请") + "时，平台响应未包含票根的Tag");
                }
                CardBusinessBasic cardBusinessBasic4 = this.cardBusinessBasic;
                cardBusinessBasic4.throwExceptionAndClose(String.valueOf("申通业务申请") + "时，平台处理失败", BusinessException.ErrorMessage.local_message_platform_business_handle_fail, false);
            }
            ITag tag4Id2 = createMessage2.getTag4Id(17);
            if (tag4Id2 != null) {
                ticketOperateResult.setTicketStub(tag4Id2.getBytesVal());
            } else {
                if (this.fmLog != null) {
                    FMLog fMLog4 = this.fmLog;
                    String str8 = this.logTag;
                    fMLog4.error(str8, String.valueOf("申通业务申请") + "时，平台响应未包含状态的Tag");
                }
                CardBusinessBasic cardBusinessBasic5 = this.cardBusinessBasic;
                cardBusinessBasic5.throwExceptionAndClose(String.valueOf("申通业务申请") + "时，平台处理失败", BusinessException.ErrorMessage.local_message_platform_business_handle_fail, false);
            }
        } catch (FMCommunicationMessageException e2) {
            if (this.fmLog != null) {
                FMLog fMLog5 = this.fmLog;
                String str9 = this.logTag;
                fMLog5.error(str9, String.valueOf("申通业务申请") + "时，解析平台响应出现异常: " + Util4Java.getExceptionInfo(e2));
            }
            CardBusinessBasic cardBusinessBasic6 = this.cardBusinessBasic;
            cardBusinessBasic6.throwExceptionAndClose(String.valueOf("申通业务申请") + "时，解析平台响应失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
        }
        return ticketOperateResult;
    }

    public List<Product> queryProducts(String str, EnumCardAppType enumCardAppType, byte[] bArr) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "产品信息检索...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str2 = this.logTag;
                fMLog.warn(str2, String.valueOf("产品信息检索") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("产品信息检索") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str3 = this.logTag;
                fMLog2.warn(str3, String.valueOf("产品信息检索") + "，消息处理器为空");
            }
            throw new BusinessException(String.valueOf("产品信息检索") + "，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_ORDERS_VER2);
        if (server4Business == null) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str4 = this.logTag;
                fMLog3.warn(str4, String.valueOf("产品信息检索") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("产品信息检索") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        this.cardBusinessBasic.businessReady("产品信息检索", server4Business);
        List<Product> queryProductsHandle = queryProductsHandle(str, enumCardAppType, bArr, messageHandler, server4Business);
        this.cardBusinessBasic.businessFinish(false);
        return queryProductsHandle;
    }

    private List<Product> queryProductsHandle(String str, EnumCardAppType enumCardAppType, byte[] bArr, IMessageHandler iMessageHandler, String str2) throws BusinessException {
        byte[] bArr2;
        Product fromTag;
        try {
            IMessage createMessage = iMessageHandler.createMessage(5011);
            if (str != null && str.length() > 0) {
                ITag createTag = iMessageHandler.createTag((byte) Constants.TagName.DEVICE_MODEL);
                createTag.addValue(str);
                createMessage.addTag(createTag);
            }
            if (enumCardAppType != null) {
                ITag createTag2 = iMessageHandler.createTag((byte) 14);
                createTag2.addValue(enumCardAppType.getId());
                createMessage.addTag(createTag2);
            }
            if (bArr != null && bArr.length > 0) {
                ITag createTag3 = iMessageHandler.createTag((byte) Constants.TagName.PATCH_DATA);
                createTag3.addValue(bArr);
                createMessage.addTag(createTag3);
            }
            bArr2 = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("产品信息检索") + ",构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("产品信息检索") + ",,构造平台请求数据出现失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr2 = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr2, "产品信息检索", false, str2);
        byte[] copyOf = Arrays.copyOf(interaction, 2);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, copyOf)) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, String.valueOf("产品信息检索") + "，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("产品信息检索") + "，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(copyOf)), true);
        }
        ArrayList arrayList = new ArrayList();
        try {
            ITag tag4Id = iMessageHandler.createMessage(5011, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(-100);
            if (tag4Id == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("产品信息检索") + "，平台没有记录");
                }
                return arrayList;
            }
            ITag[] itemTags = tag4Id.getItemTags();
            if (itemTags != null) {
                if (itemTags.length >= 1) {
                    for (ITag iTag : itemTags) {
                        if (!(iTag == null || (fromTag = Product.fromTag(iTag)) == null)) {
                            arrayList.add(fromTag);
                        }
                    }
                    return arrayList;
                }
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("产品信息检索") + "，平台业务订到集合为空");
            }
            return arrayList;
        } catch (FMCommunicationMessageException unused) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("产品信息检索") + "，解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("产品信息检索") + "，解析平台响应数据失败", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
        }
    }

    public Product queryProduct(String str) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (str == null || str.length() < 1) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str2 = this.logTag;
                fMLog.warn(str2, String.valueOf("产品详细信息查询") + "，产品标识为空");
            }
            throw new BusinessException(String.valueOf("产品详细信息查询") + "，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str3 = this.logTag;
                fMLog2.warn(str3, String.valueOf("产品详细信息查询") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("产品详细信息查询") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    FMLog fMLog3 = this.fmLog;
                    String str4 = this.logTag;
                    fMLog3.warn(str4, String.valueOf("产品详细信息查询") + "，消息处理器为空");
                }
                throw new BusinessException("业务订单查询时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_ORDER_VER2);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    FMLog fMLog4 = this.fmLog;
                    String str5 = this.logTag;
                    fMLog4.warn(str5, String.valueOf("产品详细信息查询") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("产品详细信息查询") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("产品详细信息查询", server4Business);
            Product queryProductHandle = queryProductHandle(str, messageHandler, server4Business);
            this.cardBusinessBasic.businessFinish(false);
            return queryProductHandle;
        }
    }

    private Product queryProductHandle(String str, IMessageHandler iMessageHandler, String str2) throws BusinessException {
        byte[] bArr;
        IMessage createMessage = iMessageHandler.createMessage(5021);
        try {
            ITag createTag = iMessageHandler.createTag((byte) 103);
            createTag.addValue(str);
            createMessage.addTag(createTag);
            bArr = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "业务订单详细查询时，构造平台请求数据出现异常:" + Util4Java.getExceptionInfo(e));
            }
            this.cardBusinessBasic.throwExceptionAndClose("业务订单详细查询时，构造平台请求数据失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr, "业务订单详细查询时", false, str2);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, Arrays.copyOf(interaction, 2))) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, String.valueOf("产品详细信息查询") + "，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("产品详细信息查询") + "，平台处理失败", BusinessException.ErrorMessage.local_message_platform_business_handle_fail, true);
        }
        try {
            ITag tag4Id = iMessageHandler.createMessage(5021, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(-104);
            if (tag4Id == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("产品详细信息查询") + "，平台响应数据没有包含记录信息");
                }
                this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("产品详细信息查询") + "，,平台响应数据无效", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
            }
            return Product.fromTag(tag4Id);
        } catch (FMCommunicationMessageException unused) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("产品详细信息查询") + "，解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("产品详细信息查询") + "，解析平台响应数据失败", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
            return null;
        }
    }

    public int updateStationInfo() throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("地铁站点信息更新") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("地铁站点信息更新") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.warn(str2, String.valueOf("地铁站点信息更新") + "，消息处理器为空");
            }
            throw new BusinessException("业务订单查询时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        LocalDataHandler localDataHandler = this.cardBusinessBasic.getLocalDataHandler();
        if (localDataHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str3 = this.logTag;
                fMLog3.warn(str3, String.valueOf("地铁站点信息更新") + "，本地数据处理器为空");
            }
            throw new BusinessException("业务订单查询时，本地数据处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_ORDER_VER2);
        if (server4Business == null) {
            if (this.fmLog != null) {
                FMLog fMLog4 = this.fmLog;
                String str4 = this.logTag;
                fMLog4.warn(str4, String.valueOf("地铁站点信息更新") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("地铁站点信息更新") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        int updateStationInfoHandle = updateStationInfoHandle(messageHandler, server4Business, localDataHandler);
        this.cardBusinessBasic.businessReady("地铁站点信息更新", server4Business);
        this.cardBusinessBasic.businessFinish(false);
        return updateStationInfoHandle;
    }

    private int updateStationInfoHandle(IMessageHandler iMessageHandler, String str, LocalDataHandler localDataHandler) throws BusinessException {
        byte[] bArr;
        StationInfo fromTag;
        String version4StationInfo = localDataHandler.getVersion4StationInfo();
        if (version4StationInfo == null) {
            version4StationInfo = "0";
        }
        IMessage createMessage = iMessageHandler.createMessage((int) Constants.TradeCode.STATION_INFO_UPDATE);
        try {
            ITag createTag = iMessageHandler.createTag((byte) Constants.TagName.STATION_CONFIG_VERSION);
            createTag.addValue(version4StationInfo);
            createMessage.addTag(createTag);
            bArr = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("地铁站点信息更新") + "，构造平台请求数据出现异常:" + Util4Java.getExceptionInfo(e));
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("地铁站点信息更新") + "，，构造平台请求数据失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr, "地铁站点信息更新", false, str);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, Arrays.copyOf(interaction, 2))) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, String.valueOf("地铁站点信息更新") + "，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("地铁站点信息更新") + "，平台处理失败", BusinessException.ErrorMessage.local_message_platform_business_handle_fail, true);
        }
        try {
            IMessage createMessage2 = iMessageHandler.createMessage(Constants.TradeCode.STATION_INFO_UPDATE, Arrays.copyOfRange(interaction, 2, interaction.length));
            ITag tag4Id = createMessage2.getTag4Id(-68);
            if (tag4Id == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("地铁站点信息更新") + "，平台响应数据没有包含地铁站配置信息版本");
                }
                this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("地铁站点信息更新") + ",平台响应数据无效", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
            }
            String stringVal = tag4Id.getStringVal();
            ITag tag4Id2 = createMessage2.getTag4Id(-66);
            if (tag4Id2 == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("地铁站点信息更新") + "，平台响应数据没有包含地铁站信息集合");
                }
                this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("地铁站点信息更新") + ",平台响应数据无效", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
            }
            ArrayList arrayList = new ArrayList();
            ITag[] itemTags = tag4Id2.getItemTags();
            if (itemTags != null && itemTags.length > 0) {
                for (ITag iTag : itemTags) {
                    if (!(iTag == null || (fromTag = StationInfo.fromTag(iTag)) == null)) {
                        arrayList.add(fromTag);
                    }
                }
            }
            return localDataHandler.updateStationInfo(stringVal, arrayList);
        } catch (FMCommunicationMessageException unused) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("地铁站点信息更新") + "，解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("地铁站点信息更新") + "，解析平台响应数据失败", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
            return 0;
        }
    }

    public byte[] rentBusinessHandle(int i, byte[] bArr) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("短租卡业务操作") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("短租卡业务操作") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.warn(str2, String.valueOf("短租卡业务操作") + "，消息处理器为空");
            }
            throw new BusinessException("业务订单查询时，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_ORDER_VER2);
        if (server4Business == null) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str3 = this.logTag;
                fMLog3.warn(str3, String.valueOf("短租卡业务操作") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("短租卡业务操作") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        byte[] rentBusinessHandleHandle = rentBusinessHandleHandle(messageHandler, server4Business, i, bArr);
        this.cardBusinessBasic.businessReady("短租卡业务操作", server4Business);
        this.cardBusinessBasic.businessFinish(false);
        return rentBusinessHandleHandle;
    }

    private byte[] rentBusinessHandleHandle(IMessageHandler iMessageHandler, String str, int i, byte[] bArr) throws BusinessException {
        byte[] bArr2;
        IMessage createMessage = iMessageHandler.createMessage(3101);
        try {
            ITag createTag = iMessageHandler.createTag((byte) Constants.TagName.RENT_HANDLE_TYPE);
            createTag.addValue(i);
            createMessage.addTag(createTag);
            if (bArr != null) {
                ITag createTag2 = iMessageHandler.createTag((byte) Constants.TagName.RENT_HANDLE_DATD);
                createTag2.addValue(bArr);
                createMessage.addTag(createTag2);
            }
            bArr2 = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str2 = this.logTag;
                fMLog.warn(str2, String.valueOf("短租卡业务操作") + "，构造平台请求数据出现异常:" + Util4Java.getExceptionInfo(e));
            }
            CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
            cardBusinessBasic2.throwExceptionAndClose(String.valueOf("短租卡业务操作") + "，，构造平台请求数据失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr2 = null;
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr2, "短租卡业务操作", false, str);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, Arrays.copyOf(interaction, 2))) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str3 = this.logTag;
                fMLog2.error(str3, String.valueOf("短租卡业务操作") + "，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
            }
            CardBusinessBasic cardBusinessBasic3 = this.cardBusinessBasic;
            cardBusinessBasic3.throwExceptionAndClose(String.valueOf("短租卡业务操作") + "，平台处理失败", BusinessException.ErrorMessage.local_message_platform_business_handle_fail, true);
        }
        byte[] bArr3 = new byte[1];
        try {
            ITag tag4Id = iMessageHandler.createMessage(3101, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(-61);
            if (tag4Id != null) {
                return tag4Id.getBytesVal();
            }
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str4 = this.logTag;
                fMLog3.warn(str4, String.valueOf("短租卡业务操作") + "，平台响应无数据");
            }
            return bArr3;
        } catch (FMCommunicationMessageException unused) {
            if (this.fmLog != null) {
                FMLog fMLog4 = this.fmLog;
                String str5 = this.logTag;
                fMLog4.warn(str5, String.valueOf("短租卡业务操作") + "，解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
            }
            CardBusinessBasic cardBusinessBasic4 = this.cardBusinessBasic;
            cardBusinessBasic4.throwExceptionAndClose(String.valueOf("短租卡业务操作") + "，解析平台响应数据失败", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
            return bArr3;
        }
    }
}
