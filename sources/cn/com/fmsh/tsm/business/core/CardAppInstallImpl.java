package cn.com.fmsh.tsm.business.core;

import cn.com.fmsh.communication.message.IMessage;
import cn.com.fmsh.communication.message.IMessageHandler;
import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.TLVParse;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.script.ApduHandler;
import cn.com.fmsh.script.ScriptHandler;
import cn.com.fmsh.tsm.business.CardAppInstall;
import cn.com.fmsh.tsm.business.IssuerProcessHandler;
import cn.com.fmsh.tsm.business.bean.CardAppInfo;
import cn.com.fmsh.tsm.business.bean.IssuerPrepareResult;
import cn.com.fmsh.tsm.business.card.CardManagerFactory;
import cn.com.fmsh.tsm.business.card.base.CardManager;
import cn.com.fmsh.tsm.business.constants.Constants;
import cn.com.fmsh.tsm.business.enums.EnumAppManageOperateType;
import cn.com.fmsh.tsm.business.enums.EnumCardAppStatus;
import cn.com.fmsh.tsm.business.enums.EnumCardAppType;
import cn.com.fmsh.tsm.business.enums.EnumCardBusinessOpType;
import cn.com.fmsh.tsm.business.exception.BusinessException;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.util.Arrays;
import java.util.List;

public class CardAppInstallImpl implements CardAppInstall {
    private final int businessQueryStatus = 3;
    private int businessType;
    private final int businessTypeClean = 1;
    private final int businessTypeIssuer = 0;
    private final int businessTypePersionlization = 2;
    private CardBusinessBasic cardBusinessBasic;
    private final byte[] filed40015;
    private FMLog fmLog;
    private boolean isCancel;
    private IssuerProcessHandler issuerProcessHandler;
    private final String logTag;
    private byte[] response4BusinessFinish;

    public CardAppInstallImpl(CardBusinessBasic cardBusinessBasic2) {
        byte[] bArr = new byte[32];
        bArr[30] = Constants.TagName.SYSTEM_VERSION;
        this.filed40015 = bArr;
        this.businessType = 0;
        this.fmLog = null;
        this.logTag = CardAppInstallImpl.class.getName();
        this.cardBusinessBasic = cardBusinessBasic2;
        this.fmLog = LogFactory.getInstance().getLog();
    }

    public void registerIssuerProcessHandler(IssuerProcessHandler issuerProcessHandler2) {
        this.issuerProcessHandler = issuerProcessHandler2;
    }

    /* JADX WARNING: type inference failed for: r5v0 */
    /* JADX WARNING: type inference failed for: r9v3 */
    /* JADX WARNING: type inference failed for: r5v1, types: [boolean] */
    /* JADX WARNING: type inference failed for: r5v4 */
    /* JADX WARNING: type inference failed for: r9v5 */
    /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=?, for r9v4, types: [int, boolean] */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x02a9  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x02d8  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x02da  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x02dd  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x0391  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x03aa  */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x03d5 A[SYNTHETIC, Splitter:B:167:0x03d5] */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x03df A[Catch:{ FMCommunicationMessageException -> 0x03dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x0419  */
    /* JADX WARNING: Removed duplicated region for block: B:182:0x0437  */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x0441 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x01b1  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01ea  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0226  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0245  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean commandHandle(int r17, byte[] r18, byte[] r19, byte[] r20, java.lang.String r21) throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r16 = this;
            r1 = r16
            r0 = r18
            r2 = r19
            r3 = r20
            r4 = r21
            r5 = 0
            r1.isCancel = r5
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r6 = r1.cardBusinessBasic
            if (r6 != 0) goto L_0x004a
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x002f
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r2 = r1.logTag
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = java.lang.String.valueOf(r21)
            r3.<init>(r5)
            java.lang.String r5 = "，业务处理对象为空"
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            r0.warn(r2, r3)
        L_0x002f:
            cn.com.fmsh.tsm.business.exception.BusinessException r0 = new cn.com.fmsh.tsm.business.exception.BusinessException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = java.lang.String.valueOf(r21)
            r2.<init>(r3)
            java.lang.String r3 = "，业务处理器初始化失败"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_init_fail
            r0.<init>(r2, r3)
            throw r0
        L_0x004a:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r6 = r1.cardBusinessBasic
            cn.com.fmsh.communication.message.IMessageHandler r6 = r6.getMessageHandler()
            if (r6 != 0) goto L_0x008b
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x0070
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r2 = r1.logTag
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = java.lang.String.valueOf(r21)
            r3.<init>(r5)
            java.lang.String r5 = "，消息处理器为空，消息配置文件加载失败"
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            r0.warn(r2, r3)
        L_0x0070:
            cn.com.fmsh.tsm.business.exception.BusinessException r0 = new cn.com.fmsh.tsm.business.exception.BusinessException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = java.lang.String.valueOf(r21)
            r2.<init>(r3)
            java.lang.String r3 = "，加载TAG定义配置文件失败"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_load_config_fail
            r0.<init>(r2, r3)
            throw r0
        L_0x008b:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r7 = r1.cardBusinessBasic
            r8 = 8811(0x226b, float:1.2347E-41)
            java.lang.String r7 = r7.getServer4Business(r8)
            if (r7 != 0) goto L_0x00ce
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x00b3
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r2 = r1.logTag
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = java.lang.String.valueOf(r21)
            r3.<init>(r5)
            java.lang.String r5 = "时，获取处理的平台失败"
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            r0.warn(r2, r3)
        L_0x00b3:
            cn.com.fmsh.tsm.business.exception.BusinessException r0 = new cn.com.fmsh.tsm.business.exception.BusinessException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = java.lang.String.valueOf(r21)
            r2.<init>(r3)
            java.lang.String r3 = "时，获取处理的平台失败"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_app_query_server_fail
            r0.<init>(r2, r3)
            throw r0
        L_0x00ce:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r9 = r1.cardBusinessBasic
            r9.businessReady(r4, r7)
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r9 = r1.cardBusinessBasic
            cn.com.fmsh.script.ApduHandler r9 = r9.getApduHandler()
            if (r9 != 0) goto L_0x00f3
            cn.com.fmsh.util.log.FMLog r10 = r1.fmLog
            if (r10 == 0) goto L_0x00e9
            cn.com.fmsh.util.log.FMLog r10 = r1.fmLog
            java.lang.String r11 = r1.logTag
            java.lang.String r12 = "上海交通卡空中发卡时，APDU处理器为空"
            r10.error(r11, r12)
        L_0x00e9:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r10 = r1.cardBusinessBasic
            java.lang.String r11 = "上海交通卡空中发卡时，请先切换卡的访问方式(OMA/NFC)"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r12 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_null
            r10.throwExceptionAndClose((java.lang.String) r11, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r12, (boolean) r5)
        L_0x00f3:
            boolean r10 = r9.isConnect()
            if (r10 == 0) goto L_0x0112
            cn.com.fmsh.util.log.FMLog r9 = r1.fmLog
            if (r9 == 0) goto L_0x0107
            cn.com.fmsh.util.log.FMLog r9 = r1.fmLog
            java.lang.String r10 = r1.logTag
            java.lang.String r11 = "上海交通卡空中发卡时，APDU处理器正忙"
            r9.error(r10, r11)
        L_0x0107:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r9 = r1.cardBusinessBasic
            java.lang.String r10 = "上海交通卡空中发卡时，APDU处理器正忙"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r11 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_busying
            r9.throwExceptionAndClose((java.lang.String) r10, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r11, (boolean) r5)
            goto L_0x0115
        L_0x0112:
            r9.connect()
        L_0x0115:
            r9 = 1
            if (r2 == 0) goto L_0x011b
            int r10 = r2.length
            if (r10 >= r9) goto L_0x011d
        L_0x011b:
            byte[] r2 = new byte[r5]
        L_0x011d:
            byte[] r10 = new byte[r5]
            byte[] r10 = new byte[r5]
            cn.com.fmsh.communication.message.IMessage r8 = r6.createMessage((int) r8)
            r11 = -79
            cn.com.fmsh.communication.message.ITag r11 = r6.createTag((byte) r11)     // Catch:{ FMCommunicationMessageException -> 0x0178 }
            r11.addValue((byte[]) r2)     // Catch:{ FMCommunicationMessageException -> 0x0178 }
            r8.addTag(r11)     // Catch:{ FMCommunicationMessageException -> 0x0178 }
            r2 = -78
            cn.com.fmsh.communication.message.ITag r2 = r6.createTag((byte) r2)     // Catch:{ FMCommunicationMessageException -> 0x0178 }
            r11 = r17
            r2.addValue((int) r11)     // Catch:{ FMCommunicationMessageException -> 0x0178 }
            r8.addTag(r2)     // Catch:{ FMCommunicationMessageException -> 0x0178 }
            r2 = -77
            cn.com.fmsh.communication.message.ITag r2 = r6.createTag((byte) r2)     // Catch:{ FMCommunicationMessageException -> 0x0178 }
            r2.addValue((byte[]) r10)     // Catch:{ FMCommunicationMessageException -> 0x0178 }
            r8.addTag(r2)     // Catch:{ FMCommunicationMessageException -> 0x0178 }
            r2 = 17
            cn.com.fmsh.communication.message.ITag r2 = r6.createTag((byte) r2)     // Catch:{ FMCommunicationMessageException -> 0x0178 }
            r2.addValue((byte[]) r0)     // Catch:{ FMCommunicationMessageException -> 0x0178 }
            r8.addTag(r2)     // Catch:{ FMCommunicationMessageException -> 0x0178 }
            if (r3 == 0) goto L_0x0168
            int r2 = r3.length     // Catch:{ FMCommunicationMessageException -> 0x0178 }
            if (r2 <= 0) goto L_0x0168
            r2 = -76
            cn.com.fmsh.communication.message.ITag r2 = r6.createTag((byte) r2)     // Catch:{ FMCommunicationMessageException -> 0x0178 }
            r2.addValue((byte[]) r0)     // Catch:{ FMCommunicationMessageException -> 0x0178 }
            r8.addTag(r2)     // Catch:{ FMCommunicationMessageException -> 0x0178 }
        L_0x0168:
            byte[] r2 = r8.toBytes()     // Catch:{ FMCommunicationMessageException -> 0x0178 }
            java.io.PrintStream r0 = java.lang.System.out     // Catch:{ FMCommunicationMessageException -> 0x0176 }
            java.lang.String r3 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r2)     // Catch:{ FMCommunicationMessageException -> 0x0176 }
            r0.println(r3)     // Catch:{ FMCommunicationMessageException -> 0x0176 }
            goto L_0x01a2
        L_0x0176:
            r0 = move-exception
            goto L_0x017a
        L_0x0178:
            r0 = move-exception
            r2 = 0
        L_0x017a:
            cn.com.fmsh.util.log.FMLog r3 = r1.fmLog
            if (r3 == 0) goto L_0x0198
            cn.com.fmsh.util.log.FMLog r3 = r1.fmLog
            java.lang.String r8 = r1.logTag
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "上海交通卡空中发卡时，消息处理器出现异常："
            r10.<init>(r11)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r10.append(r0)
            java.lang.String r0 = r10.toString()
            r3.warn(r8, r0)
        L_0x0198:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r3 = "上海交通卡空中发卡时，消息处理器出现异常"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r8 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r0.throwExceptionAndClose((java.lang.String) r3, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r8, (boolean) r9)
        L_0x01a2:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            byte[] r0 = r0.interaction(r2, r4, r9, r7)
            if (r0 == 0) goto L_0x01ad
            int r2 = r0.length
            if (r2 >= r9) goto L_0x01c5
        L_0x01ad:
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            if (r2 == 0) goto L_0x01bb
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            java.lang.String r3 = r1.logTag
            java.lang.String r8 = "平台处理业务请求失败"
            r2.warn(r3, r8)
        L_0x01bb:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r1.cardBusinessBasic
            java.lang.String r3 = "业务处理时，未收到平台响应数据"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r8 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_communication_no_response
            r2.throwExceptionAndClose((java.lang.String) r3, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r8, (boolean) r9)
        L_0x01c5:
            r2 = 2
            byte[] r3 = java.util.Arrays.copyOf(r0, r2)
            int r8 = r0.length
            byte[] r8 = java.util.Arrays.copyOfRange(r0, r2, r8)
            r10 = 0
            r11 = 0
            r13 = 0
            r14 = 0
        L_0x01d3:
            byte[] r15 = cn.com.fmsh.tsm.business.constants.Constants.RespCodeonse4Platform.CARD_REQUEST
            boolean r15 = java.util.Arrays.equals(r15, r3)
            if (r15 != 0) goto L_0x0245
            r1.response4BusinessFinish = r0
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            r0.businessFinish(r9)
            byte[] r0 = cn.com.fmsh.tsm.business.constants.Constants.RespCodeonse4Platform.SUCESS
            boolean r0 = java.util.Arrays.equals(r0, r3)
            if (r0 == 0) goto L_0x0226
            int r0 = r8.length
            r3 = 3
            if (r0 >= r3) goto L_0x01ef
            return r9
        L_0x01ef:
            byte r0 = r8[r5]
            r3 = -75
            if (r3 != r0) goto L_0x0217
            byte r0 = r8[r2]
            if (r0 != 0) goto L_0x01fa
            return r9
        L_0x01fa:
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x0216
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r3 = r1.logTag
            java.lang.String r4 = "业务处理完成，处理结果失败[%X]"
            java.lang.Object[] r6 = new java.lang.Object[r9]
            byte r2 = r8[r2]
            java.lang.Byte r2 = java.lang.Byte.valueOf(r2)
            r6[r5] = r2
            java.lang.String r2 = java.lang.String.format(r4, r6)
            r0.warn(r3, r2)
        L_0x0216:
            return r5
        L_0x0217:
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x0225
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r2 = r1.logTag
            java.lang.String r3 = "业务处理完成，未找到处理结果的TAG"
            r0.warn(r2, r3)
        L_0x0225:
            return r5
        L_0x0226:
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x0244
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r2 = r1.logTag
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r6 = "业务处理失败，响应数据："
            r4.<init>(r6)
            java.lang.String r3 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r3)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            r0.warn(r2, r3)
        L_0x0244:
            return r5
        L_0x0245:
            boolean r3 = r1.isCancel
            if (r3 == 0) goto L_0x0261
            cn.com.fmsh.util.log.FMLog r3 = r1.fmLog
            if (r3 == 0) goto L_0x0257
            cn.com.fmsh.util.log.FMLog r3 = r1.fmLog
            java.lang.String r8 = r1.logTag
            java.lang.String r15 = "业务处理时，接收到取消操作"
            r3.debug(r8, r15)
        L_0x0257:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r1.cardBusinessBasic
            java.lang.String r8 = "业务处理时，接收到取消操作"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r15 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_cancel
            r3.throwExceptionAndClose((java.lang.String) r8, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r15, (boolean) r9)
        L_0x0261:
            cn.com.fmsh.script.bean.ApduRequestList r3 = new cn.com.fmsh.script.bean.ApduRequestList
            r3.<init>()
            cn.com.fmsh.communication.message.IMessage r0 = r6.createMessage((byte[]) r0)     // Catch:{ FMCommunicationMessageException -> 0x02a2 }
            r8 = -96
            cn.com.fmsh.communication.message.ITag r8 = r0.getTag4Id(r8)     // Catch:{ FMCommunicationMessageException -> 0x02a2 }
            r15 = -95
            cn.com.fmsh.communication.message.ITag r15 = r0.getTag4Id(r15)     // Catch:{ FMCommunicationMessageException -> 0x02a0 }
            r12 = -90
            cn.com.fmsh.communication.message.ITag r12 = r0.getTag4Id(r12)     // Catch:{ FMCommunicationMessageException -> 0x029e }
            r2 = -89
            cn.com.fmsh.communication.message.ITag r0 = r0.getTag4Id(r2)     // Catch:{ FMCommunicationMessageException -> 0x029e }
            if (r0 == 0) goto L_0x028d
            byte[] r2 = r0.getBytesVal()     // Catch:{ FMCommunicationMessageException -> 0x029e }
            byte r2 = r2[r5]     // Catch:{ FMCommunicationMessageException -> 0x029e }
            r2 = r2 & 255(0xff, float:3.57E-43)
            r11 = r2
        L_0x028d:
            r2 = 9001(0x2329, float:1.2613E-41)
            cn.com.fmsh.communication.message.IMessage r2 = r6.createMessage((int) r2)     // Catch:{ FMCommunicationMessageException -> 0x029e }
            r2.addTag(r12)     // Catch:{ FMCommunicationMessageException -> 0x029b }
            r2.addTag(r0)     // Catch:{ FMCommunicationMessageException -> 0x029b }
            r14 = r2
            goto L_0x02d5
        L_0x029b:
            r0 = move-exception
            r14 = r2
            goto L_0x02a5
        L_0x029e:
            r0 = move-exception
            goto L_0x02a5
        L_0x02a0:
            r0 = move-exception
            goto L_0x02a4
        L_0x02a2:
            r0 = move-exception
            r8 = 0
        L_0x02a4:
            r15 = 0
        L_0x02a5:
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            if (r2 == 0) goto L_0x02ca
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            java.lang.String r12 = "logTag"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r9 = java.lang.String.valueOf(r21)
            r5.<init>(r9)
            java.lang.String r9 = "解析平台响应数据出现异常:"
            r5.append(r9)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            r2.warn(r12, r0)
        L_0x02ca:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r2 = "解析平台响应数据失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_command_data_invaild
            r9 = 1
            r0.throwExceptionAndClose((java.lang.String) r2, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r9)
        L_0x02d5:
            r12 = r8
            if (r12 == 0) goto L_0x02da
            r13 = 0
            goto L_0x02db
        L_0x02da:
            r12 = 0
        L_0x02db:
            if (r15 == 0) goto L_0x02df
            r12 = r15
            r13 = 1
        L_0x02df:
            r3.fromTag(r12)     // Catch:{ FMCommunicationMessageException -> 0x030f, FMScriptHandleException -> 0x02e3 }
            goto L_0x033a
        L_0x02e3:
            r0 = move-exception
            r2 = r0
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x0303
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r5 = r1.logTag
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "业务处理时，平台响应的数据解析异常"
            r8.<init>(r9)
            java.lang.String r2 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r2)
            r8.append(r2)
            java.lang.String r2 = r8.toString()
            r0.warn(r5, r2)
        L_0x0303:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r2 = "平台响应的数据解析失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_communication_invalid_response
            r8 = 1
            r0.throwExceptionAndClose((java.lang.String) r2, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r8)
            goto L_0x033a
        L_0x030f:
            r0 = move-exception
            r2 = r0
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x032f
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r5 = r1.logTag
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "业务处理时，平台响应的数据解析异常："
            r8.<init>(r9)
            java.lang.String r2 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r2)
            r8.append(r2)
            java.lang.String r2 = r8.toString()
            r0.warn(r5, r2)
        L_0x032f:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r2 = "平台响应的数据解析失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_communication_invalid_response
            r8 = 1
            r0.throwExceptionAndClose((java.lang.String) r2, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r8)
        L_0x033a:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic     // Catch:{ FMScriptHandleException -> 0x0347 }
            cn.com.fmsh.script.ScriptHandler r0 = r0.getScriptHandler()     // Catch:{ FMScriptHandleException -> 0x0347 }
            cn.com.fmsh.script.bean.ApduReponseList r0 = r0.execute((cn.com.fmsh.script.bean.ApduRequestList) r3)     // Catch:{ FMScriptHandleException -> 0x0347 }
            r10 = r0
            r5 = 1
            goto L_0x0385
        L_0x0347:
            r0 = move-exception
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            if (r2 == 0) goto L_0x0366
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            java.lang.String r3 = r1.logTag
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r8 = "业务处理时，脚本执行出现异常:"
            r5.<init>(r8)
            java.lang.String r8 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r5.append(r8)
            java.lang.String r5 = r5.toString()
            r2.warn(r3, r5)
        L_0x0366:
            cn.com.fmsh.script.exception.FMScriptHandleException$ScriptHandleExceptionType r2 = cn.com.fmsh.script.exception.FMScriptHandleException.ScriptHandleExceptionType.STOPED
            cn.com.fmsh.script.exception.FMScriptHandleException$ScriptHandleExceptionType r0 = r0.getType()
            if (r2 != r0) goto L_0x037a
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r2 = "业务处理被取消"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_cancel
            r5 = 1
            r0.throwExceptionAndClose((java.lang.String) r2, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r3, (boolean) r5)
            goto L_0x0385
        L_0x037a:
            r5 = 1
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r2 = "本地执行执行失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception
            r0.throwExceptionAndClose((java.lang.String) r2, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r3, (boolean) r5)
        L_0x0385:
            if (r10 == 0) goto L_0x038d
            int r0 = r10.size()
            if (r0 >= r5) goto L_0x03a6
        L_0x038d:
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x039b
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r2 = r1.logTag
            java.lang.String r3 = "业务处理时，响应结果为空"
            r0.warn(r2, r3)
        L_0x039b:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r2 = "本地执行执行失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception
            r5 = 1
            r0.throwExceptionAndClose((java.lang.String) r2, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r3, (boolean) r5)
        L_0x03a6:
            int r0 = r1.businessType
            if (r0 != 0) goto L_0x03d3
            cn.com.fmsh.tsm.business.enums.EnumIssueProcess r0 = cn.com.fmsh.tsm.business.enums.EnumIssueProcess.instance(r11)
            cn.com.fmsh.tsm.business.IssuerProcessHandler r2 = r1.issuerProcessHandler
            if (r2 == 0) goto L_0x03d3
            if (r0 == 0) goto L_0x03d3
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            if (r2 == 0) goto L_0x03ce
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            java.lang.String r3 = r1.logTag
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r8 = "进度通知,步骤："
            r5.<init>(r8)
            r5.append(r11)
            java.lang.String r5 = r5.toString()
            r2.debug(r3, r5)
        L_0x03ce:
            cn.com.fmsh.tsm.business.IssuerProcessHandler r2 = r1.issuerProcessHandler
            r2.handle(r0)
        L_0x03d3:
            if (r13 != 0) goto L_0x03df
            cn.com.fmsh.communication.message.ITag r0 = r10.toTag4A2()     // Catch:{ FMCommunicationMessageException -> 0x03dd }
            r14.addTag(r0)     // Catch:{ FMCommunicationMessageException -> 0x03dd }
            goto L_0x03e6
        L_0x03dd:
            r0 = move-exception
            goto L_0x03eb
        L_0x03df:
            cn.com.fmsh.communication.message.ITag r0 = r10.toTag4A3()     // Catch:{ FMCommunicationMessageException -> 0x03dd }
            r14.addTag(r0)     // Catch:{ FMCommunicationMessageException -> 0x03dd }
        L_0x03e6:
            byte[] r12 = r14.toBytes()     // Catch:{ FMCommunicationMessageException -> 0x03dd }
            goto L_0x0415
        L_0x03eb:
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            if (r2 == 0) goto L_0x0409
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            java.lang.String r3 = r1.logTag
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r8 = "业务处理时，构造终端响应数据出现异常:"
            r5.<init>(r8)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            r2.warn(r3, r0)
        L_0x0409:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r2 = "构造平台请求数据失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r5 = 1
            r0.throwExceptionAndClose((java.lang.String) r2, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r3, (boolean) r5)
            r12 = 0
        L_0x0415:
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x0433
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r2 = r1.logTag
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = "执行完成 repones:"
            r3.<init>(r5)
            java.lang.String r5 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r12)
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            r0.debug(r2, r3)
        L_0x0433:
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x0441
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r2 = r1.logTag
            java.lang.String r3 = "终端处理完成，发送处理结果给平台..."
            r0.debug(r2, r3)
        L_0x0441:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            r2 = 1
            byte[] r0 = r0.interaction(r12, r4, r2, r7)
            r3 = 2
            byte[] r5 = java.util.Arrays.copyOf(r0, r3)
            int r8 = r0.length
            byte[] r8 = java.util.Arrays.copyOfRange(r0, r3, r8)
            r3 = r5
            r2 = 2
            r5 = 0
            r9 = 1
            goto L_0x01d3
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardAppInstallImpl.commandHandle(int, byte[], byte[], byte[], java.lang.String):boolean");
    }

    /* JADX WARNING: type inference failed for: r5v0 */
    /* JADX WARNING: type inference failed for: r5v1, types: [boolean] */
    /* JADX WARNING: type inference failed for: r5v4 */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0304  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x0332  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0334  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x0337  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x03eb  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x0404  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x042f A[SYNTHETIC, Splitter:B:159:0x042f] */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0439 A[Catch:{ FMCommunicationMessageException -> 0x0437 }] */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x0473  */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x0491  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x049b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean commandHandleVer2(int r17, byte[] r18, byte[] r19, byte[] r20, java.lang.String r21) throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r16 = this;
            r1 = r16
            r0 = r18
            r2 = r19
            r3 = r20
            r4 = r21
            r5 = 0
            r1.isCancel = r5
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r6 = r1.cardBusinessBasic
            if (r6 != 0) goto L_0x004a
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x002f
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r2 = r1.logTag
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = java.lang.String.valueOf(r21)
            r3.<init>(r5)
            java.lang.String r5 = "，业务处理对象为空"
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            r0.warn(r2, r3)
        L_0x002f:
            cn.com.fmsh.tsm.business.exception.BusinessException r0 = new cn.com.fmsh.tsm.business.exception.BusinessException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = java.lang.String.valueOf(r21)
            r2.<init>(r3)
            java.lang.String r3 = "，业务处理器初始化失败"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_init_fail
            r0.<init>(r2, r3)
            throw r0
        L_0x004a:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r6 = r1.cardBusinessBasic
            cn.com.fmsh.communication.message.IMessageHandler r6 = r6.getMessageHandler()
            if (r6 != 0) goto L_0x008b
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x0070
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r2 = r1.logTag
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = java.lang.String.valueOf(r21)
            r3.<init>(r5)
            java.lang.String r5 = "，消息处理器为空，消息配置文件加载失败"
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            r0.warn(r2, r3)
        L_0x0070:
            cn.com.fmsh.tsm.business.exception.BusinessException r0 = new cn.com.fmsh.tsm.business.exception.BusinessException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = java.lang.String.valueOf(r21)
            r2.<init>(r3)
            java.lang.String r3 = "，加载TAG定义配置文件失败"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_load_config_fail
            r0.<init>(r2, r3)
            throw r0
        L_0x008b:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r7 = r1.cardBusinessBasic
            r8 = 8812(0x226c, float:1.2348E-41)
            java.lang.String r7 = r7.getServer4Business(r8)
            if (r7 != 0) goto L_0x00ce
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x00b3
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r2 = r1.logTag
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = java.lang.String.valueOf(r21)
            r3.<init>(r5)
            java.lang.String r5 = "时，获取处理的平台失败"
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            r0.warn(r2, r3)
        L_0x00b3:
            cn.com.fmsh.tsm.business.exception.BusinessException r0 = new cn.com.fmsh.tsm.business.exception.BusinessException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = java.lang.String.valueOf(r21)
            r2.<init>(r3)
            java.lang.String r3 = "时，获取处理的平台失败"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_app_query_server_fail
            r0.<init>(r2, r3)
            throw r0
        L_0x00ce:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r9 = r1.cardBusinessBasic
            r9.businessReady(r4, r7)
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r9 = r1.cardBusinessBasic
            cn.com.fmsh.script.ApduHandler r9 = r9.getApduHandler()
            if (r9 != 0) goto L_0x00f3
            cn.com.fmsh.util.log.FMLog r10 = r1.fmLog
            if (r10 == 0) goto L_0x00e9
            cn.com.fmsh.util.log.FMLog r10 = r1.fmLog
            java.lang.String r11 = r1.logTag
            java.lang.String r12 = "上海交通卡空中发卡时，APDU处理器为空"
            r10.error(r11, r12)
        L_0x00e9:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r10 = r1.cardBusinessBasic
            java.lang.String r11 = "上海交通卡空中发卡时，请先切换卡的访问方式(OMA/NFC)"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r12 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_null
            r10.throwExceptionAndClose((java.lang.String) r11, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r12, (boolean) r5)
        L_0x00f3:
            boolean r10 = r9.isConnect()
            if (r10 == 0) goto L_0x0112
            cn.com.fmsh.util.log.FMLog r9 = r1.fmLog
            if (r9 == 0) goto L_0x0107
            cn.com.fmsh.util.log.FMLog r9 = r1.fmLog
            java.lang.String r10 = r1.logTag
            java.lang.String r11 = "上海交通卡空中发卡时，APDU处理器正忙"
            r9.error(r10, r11)
        L_0x0107:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r9 = r1.cardBusinessBasic
            java.lang.String r10 = "上海交通卡空中发卡时，APDU处理器正忙"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r11 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_busying
            r9.throwExceptionAndClose((java.lang.String) r10, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r11, (boolean) r5)
            goto L_0x0115
        L_0x0112:
            r9.connect()
        L_0x0115:
            r9 = 1
            if (r2 == 0) goto L_0x011b
            int r10 = r2.length
            if (r10 >= r9) goto L_0x011d
        L_0x011b:
            byte[] r2 = new byte[r5]
        L_0x011d:
            byte[] r10 = new byte[r5]
            byte[] r10 = new byte[r5]
            cn.com.fmsh.communication.message.IMessage r11 = r6.createMessage((int) r8)
            r12 = -79
            r13 = -76
            cn.com.fmsh.communication.message.ITag r12 = r6.createTag((byte) r12)     // Catch:{ FMCommunicationMessageException -> 0x016d }
            r12.addValue((byte[]) r2)     // Catch:{ FMCommunicationMessageException -> 0x016d }
            r11.addTag(r12)     // Catch:{ FMCommunicationMessageException -> 0x016d }
            r2 = -78
            cn.com.fmsh.communication.message.ITag r2 = r6.createTag((byte) r2)     // Catch:{ FMCommunicationMessageException -> 0x016d }
            r12 = r17
            r2.addValue((int) r12)     // Catch:{ FMCommunicationMessageException -> 0x016d }
            r11.addTag(r2)     // Catch:{ FMCommunicationMessageException -> 0x016d }
            r2 = -77
            cn.com.fmsh.communication.message.ITag r2 = r6.createTag((byte) r2)     // Catch:{ FMCommunicationMessageException -> 0x016d }
            r2.addValue((byte[]) r10)     // Catch:{ FMCommunicationMessageException -> 0x016d }
            r11.addTag(r2)     // Catch:{ FMCommunicationMessageException -> 0x016d }
            r2 = 17
            cn.com.fmsh.communication.message.ITag r2 = r6.createTag((byte) r2)     // Catch:{ FMCommunicationMessageException -> 0x016d }
            r2.addValue((byte[]) r0)     // Catch:{ FMCommunicationMessageException -> 0x016d }
            r11.addTag(r2)     // Catch:{ FMCommunicationMessageException -> 0x016d }
            if (r3 == 0) goto L_0x0168
            int r2 = r3.length     // Catch:{ FMCommunicationMessageException -> 0x016d }
            if (r2 <= 0) goto L_0x0168
            cn.com.fmsh.communication.message.ITag r2 = r6.createTag((byte) r13)     // Catch:{ FMCommunicationMessageException -> 0x016d }
            r2.addValue((byte[]) r0)     // Catch:{ FMCommunicationMessageException -> 0x016d }
            r11.addTag(r2)     // Catch:{ FMCommunicationMessageException -> 0x016d }
        L_0x0168:
            byte[] r0 = r11.toBytes()     // Catch:{ FMCommunicationMessageException -> 0x016d }
            goto L_0x0197
        L_0x016d:
            r0 = move-exception
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            if (r2 == 0) goto L_0x018c
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            java.lang.String r3 = r1.logTag
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "上海交通卡空中发卡时，消息处理器出现异常："
            r10.<init>(r11)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r10.append(r0)
            java.lang.String r0 = r10.toString()
            r2.warn(r3, r0)
        L_0x018c:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r2 = "上海交通卡空中发卡时，消息处理器出现异常"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r0.throwExceptionAndClose((java.lang.String) r2, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r3, (boolean) r9)
            r0 = 0
        L_0x0197:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r1.cardBusinessBasic
            byte[] r0 = r2.interaction(r0, r4, r9, r7)
            if (r0 == 0) goto L_0x01a2
            int r2 = r0.length
            if (r2 >= r9) goto L_0x01ba
        L_0x01a2:
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            if (r2 == 0) goto L_0x01b0
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            java.lang.String r3 = r1.logTag
            java.lang.String r10 = "平台处理业务请求失败"
            r2.warn(r3, r10)
        L_0x01b0:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r1.cardBusinessBasic
            java.lang.String r3 = "业务处理时，未收到平台响应数据"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r10 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_communication_no_response
            r2.throwExceptionAndClose((java.lang.String) r3, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r10, (boolean) r9)
        L_0x01ba:
            r2 = 2
            byte[] r3 = java.util.Arrays.copyOf(r0, r2)
            r10 = 0
            r11 = 0
            r12 = 0
            r15 = 0
        L_0x01c3:
            byte[] r14 = cn.com.fmsh.tsm.business.constants.Constants.RespCodeonse4Platform.CARD_REQUEST
            boolean r14 = java.util.Arrays.equals(r14, r3)
            if (r14 != 0) goto L_0x029f
            r1.response4BusinessFinish = r0
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r7 = r1.cardBusinessBasic
            r7.businessFinish(r9)
            byte[] r7 = cn.com.fmsh.tsm.business.constants.Constants.RespCodeonse4Platform.SUCESS
            boolean r3 = java.util.Arrays.equals(r7, r3)
            if (r3 == 0) goto L_0x01db
            return r9
        L_0x01db:
            int r3 = r0.length
            if (r3 > r2) goto L_0x0224
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            if (r2 == 0) goto L_0x0203
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            java.lang.String r3 = r1.logTag
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = java.lang.String.valueOf(r21)
            r6.<init>(r7)
            java.lang.String r7 = "，空中发卡平台处理失败，平台响应："
            r6.append(r7)
            java.lang.String r7 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r0)
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r2.warn(r3, r6)
        L_0x0203:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r1.cardBusinessBasic
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = java.lang.String.valueOf(r21)
            r3.<init>(r4)
            java.lang.String r4 = "，空中发卡失败"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r0 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r0)
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r0 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.instance(r0)
            r2.throwExceptionAndClose((java.lang.String) r3, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r0, (boolean) r5)
            goto L_0x029e
        L_0x0224:
            int r3 = r0.length     // Catch:{ FMCommunicationMessageException -> 0x025e }
            byte[] r0 = java.util.Arrays.copyOfRange(r0, r2, r3)     // Catch:{ FMCommunicationMessageException -> 0x025e }
            cn.com.fmsh.communication.message.IMessage r0 = r6.createMessage(r8, r0)     // Catch:{ FMCommunicationMessageException -> 0x025e }
            cn.com.fmsh.communication.message.ITag r0 = r0.getTag4Id(r13)     // Catch:{ FMCommunicationMessageException -> 0x025e }
            if (r0 == 0) goto L_0x029e
            byte[] r0 = r0.getBytesVal()     // Catch:{ FMCommunicationMessageException -> 0x025e }
            r1.response4BusinessFinish = r0     // Catch:{ FMCommunicationMessageException -> 0x025e }
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog     // Catch:{ FMCommunicationMessageException -> 0x025e }
            java.lang.String r2 = r1.logTag     // Catch:{ FMCommunicationMessageException -> 0x025e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ FMCommunicationMessageException -> 0x025e }
            java.lang.String r6 = java.lang.String.valueOf(r21)     // Catch:{ FMCommunicationMessageException -> 0x025e }
            r3.<init>(r6)     // Catch:{ FMCommunicationMessageException -> 0x025e }
            java.lang.String r6 = "发卡失败，第三方平台异常,"
            r3.append(r6)     // Catch:{ FMCommunicationMessageException -> 0x025e }
            java.lang.String r6 = new java.lang.String     // Catch:{ FMCommunicationMessageException -> 0x025e }
            byte[] r7 = r1.response4BusinessFinish     // Catch:{ FMCommunicationMessageException -> 0x025e }
            r6.<init>(r7)     // Catch:{ FMCommunicationMessageException -> 0x025e }
            r3.append(r6)     // Catch:{ FMCommunicationMessageException -> 0x025e }
            java.lang.String r3 = r3.toString()     // Catch:{ FMCommunicationMessageException -> 0x025e }
            r0.warn(r2, r3)     // Catch:{ FMCommunicationMessageException -> 0x025e }
            goto L_0x029e
        L_0x025e:
            r0 = move-exception
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            if (r2 == 0) goto L_0x0284
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            java.lang.String r3 = r1.logTag
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = java.lang.String.valueOf(r21)
            r6.<init>(r7)
            java.lang.String r7 = "，消息处理器出现异常："
            r6.append(r7)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            r2.warn(r3, r0)
        L_0x0284:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = java.lang.String.valueOf(r21)
            r2.<init>(r3)
            java.lang.String r3 = "，消息处理器出现异常"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r0.throwExceptionAndClose((java.lang.String) r2, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r3, (boolean) r5)
        L_0x029e:
            return r5
        L_0x029f:
            boolean r3 = r1.isCancel
            if (r3 == 0) goto L_0x02bb
            cn.com.fmsh.util.log.FMLog r3 = r1.fmLog
            if (r3 == 0) goto L_0x02b1
            cn.com.fmsh.util.log.FMLog r3 = r1.fmLog
            java.lang.String r14 = r1.logTag
            java.lang.String r8 = "业务处理时，接收到取消操作"
            r3.debug(r14, r8)
        L_0x02b1:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r1.cardBusinessBasic
            java.lang.String r8 = "业务处理时，接收到取消操作"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r14 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_cancel
            r3.throwExceptionAndClose((java.lang.String) r8, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r14, (boolean) r9)
        L_0x02bb:
            cn.com.fmsh.script.bean.ApduRequestList r3 = new cn.com.fmsh.script.bean.ApduRequestList
            r3.<init>()
            cn.com.fmsh.communication.message.IMessage r0 = r6.createMessage((byte[]) r0)     // Catch:{ FMCommunicationMessageException -> 0x02fd }
            r8 = -96
            cn.com.fmsh.communication.message.ITag r14 = r0.getTag4Id(r8)     // Catch:{ FMCommunicationMessageException -> 0x02fd }
            r8 = -95
            cn.com.fmsh.communication.message.ITag r8 = r0.getTag4Id(r8)     // Catch:{ FMCommunicationMessageException -> 0x02fa }
            r13 = -90
            cn.com.fmsh.communication.message.ITag r13 = r0.getTag4Id(r13)     // Catch:{ FMCommunicationMessageException -> 0x02f8 }
            r2 = -89
            cn.com.fmsh.communication.message.ITag r0 = r0.getTag4Id(r2)     // Catch:{ FMCommunicationMessageException -> 0x02f8 }
            if (r0 == 0) goto L_0x02e7
            byte[] r2 = r0.getBytesVal()     // Catch:{ FMCommunicationMessageException -> 0x02f8 }
            byte r2 = r2[r5]     // Catch:{ FMCommunicationMessageException -> 0x02f8 }
            r2 = r2 & 255(0xff, float:3.57E-43)
            r11 = r2
        L_0x02e7:
            r2 = 9001(0x2329, float:1.2613E-41)
            cn.com.fmsh.communication.message.IMessage r2 = r6.createMessage((int) r2)     // Catch:{ FMCommunicationMessageException -> 0x02f8 }
            r2.addTag(r13)     // Catch:{ FMCommunicationMessageException -> 0x02f5 }
            r2.addTag(r0)     // Catch:{ FMCommunicationMessageException -> 0x02f5 }
            r12 = r2
            goto L_0x0330
        L_0x02f5:
            r0 = move-exception
            r12 = r2
            goto L_0x0300
        L_0x02f8:
            r0 = move-exception
            goto L_0x0300
        L_0x02fa:
            r0 = move-exception
            r8 = 0
            goto L_0x0300
        L_0x02fd:
            r0 = move-exception
            r8 = 0
            r14 = 0
        L_0x0300:
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            if (r2 == 0) goto L_0x0325
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            java.lang.String r13 = "logTag"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r9 = java.lang.String.valueOf(r21)
            r5.<init>(r9)
            java.lang.String r9 = "解析平台响应数据出现异常:"
            r5.append(r9)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            r2.warn(r13, r0)
        L_0x0325:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r2 = "解析平台响应数据失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_command_data_invaild
            r9 = 1
            r0.throwExceptionAndClose((java.lang.String) r2, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r9)
        L_0x0330:
            if (r14 == 0) goto L_0x0334
            r15 = 0
            goto L_0x0335
        L_0x0334:
            r14 = 0
        L_0x0335:
            if (r8 == 0) goto L_0x0339
            r14 = r8
            r15 = 1
        L_0x0339:
            r3.fromTag(r14)     // Catch:{ FMCommunicationMessageException -> 0x0369, FMScriptHandleException -> 0x033d }
            goto L_0x0394
        L_0x033d:
            r0 = move-exception
            r2 = r0
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x035d
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r5 = r1.logTag
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "业务处理时，平台响应的数据解析异常"
            r8.<init>(r9)
            java.lang.String r2 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r2)
            r8.append(r2)
            java.lang.String r2 = r8.toString()
            r0.warn(r5, r2)
        L_0x035d:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r2 = "平台响应的数据解析失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_communication_invalid_response
            r8 = 1
            r0.throwExceptionAndClose((java.lang.String) r2, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r8)
            goto L_0x0394
        L_0x0369:
            r0 = move-exception
            r2 = r0
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x0389
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r5 = r1.logTag
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "业务处理时，平台响应的数据解析异常："
            r8.<init>(r9)
            java.lang.String r2 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r2)
            r8.append(r2)
            java.lang.String r2 = r8.toString()
            r0.warn(r5, r2)
        L_0x0389:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r2 = "平台响应的数据解析失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_communication_invalid_response
            r8 = 1
            r0.throwExceptionAndClose((java.lang.String) r2, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r8)
        L_0x0394:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic     // Catch:{ FMScriptHandleException -> 0x03a1 }
            cn.com.fmsh.script.ScriptHandler r0 = r0.getScriptHandler()     // Catch:{ FMScriptHandleException -> 0x03a1 }
            cn.com.fmsh.script.bean.ApduReponseList r0 = r0.execute((cn.com.fmsh.script.bean.ApduRequestList) r3)     // Catch:{ FMScriptHandleException -> 0x03a1 }
            r10 = r0
            r5 = 1
            goto L_0x03df
        L_0x03a1:
            r0 = move-exception
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            if (r2 == 0) goto L_0x03c0
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            java.lang.String r3 = r1.logTag
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r8 = "业务处理时，脚本执行出现异常:"
            r5.<init>(r8)
            java.lang.String r8 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r5.append(r8)
            java.lang.String r5 = r5.toString()
            r2.warn(r3, r5)
        L_0x03c0:
            cn.com.fmsh.script.exception.FMScriptHandleException$ScriptHandleExceptionType r2 = cn.com.fmsh.script.exception.FMScriptHandleException.ScriptHandleExceptionType.STOPED
            cn.com.fmsh.script.exception.FMScriptHandleException$ScriptHandleExceptionType r0 = r0.getType()
            if (r2 != r0) goto L_0x03d4
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r2 = "业务处理被取消"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_cancel
            r5 = 1
            r0.throwExceptionAndClose((java.lang.String) r2, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r3, (boolean) r5)
            goto L_0x03df
        L_0x03d4:
            r5 = 1
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r2 = "本地执行执行失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception
            r0.throwExceptionAndClose((java.lang.String) r2, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r3, (boolean) r5)
        L_0x03df:
            if (r10 == 0) goto L_0x03e7
            int r0 = r10.size()
            if (r0 >= r5) goto L_0x0400
        L_0x03e7:
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x03f5
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r2 = r1.logTag
            java.lang.String r3 = "业务处理时，响应结果为空"
            r0.warn(r2, r3)
        L_0x03f5:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r2 = "本地执行执行失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception
            r5 = 1
            r0.throwExceptionAndClose((java.lang.String) r2, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r3, (boolean) r5)
        L_0x0400:
            int r0 = r1.businessType
            if (r0 != 0) goto L_0x042d
            cn.com.fmsh.tsm.business.enums.EnumIssueProcess r0 = cn.com.fmsh.tsm.business.enums.EnumIssueProcess.instance(r11)
            cn.com.fmsh.tsm.business.IssuerProcessHandler r2 = r1.issuerProcessHandler
            if (r2 == 0) goto L_0x042d
            if (r0 == 0) goto L_0x042d
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            if (r2 == 0) goto L_0x0428
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            java.lang.String r3 = r1.logTag
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r8 = "进度通知,步骤："
            r5.<init>(r8)
            r5.append(r11)
            java.lang.String r5 = r5.toString()
            r2.debug(r3, r5)
        L_0x0428:
            cn.com.fmsh.tsm.business.IssuerProcessHandler r2 = r1.issuerProcessHandler
            r2.handle(r0)
        L_0x042d:
            if (r15 != 0) goto L_0x0439
            cn.com.fmsh.communication.message.ITag r0 = r10.toTag4A2()     // Catch:{ FMCommunicationMessageException -> 0x0437 }
            r12.addTag(r0)     // Catch:{ FMCommunicationMessageException -> 0x0437 }
            goto L_0x0440
        L_0x0437:
            r0 = move-exception
            goto L_0x0445
        L_0x0439:
            cn.com.fmsh.communication.message.ITag r0 = r10.toTag4A3()     // Catch:{ FMCommunicationMessageException -> 0x0437 }
            r12.addTag(r0)     // Catch:{ FMCommunicationMessageException -> 0x0437 }
        L_0x0440:
            byte[] r14 = r12.toBytes()     // Catch:{ FMCommunicationMessageException -> 0x0437 }
            goto L_0x046f
        L_0x0445:
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            if (r2 == 0) goto L_0x0463
            cn.com.fmsh.util.log.FMLog r2 = r1.fmLog
            java.lang.String r3 = r1.logTag
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r8 = "业务处理时，构造终端响应数据出现异常:"
            r5.<init>(r8)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            r2.warn(r3, r0)
        L_0x0463:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r2 = "构造平台请求数据失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r5 = 1
            r0.throwExceptionAndClose((java.lang.String) r2, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r3, (boolean) r5)
            r14 = 0
        L_0x046f:
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x048d
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r2 = r1.logTag
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = "执行完成 repones:"
            r3.<init>(r5)
            java.lang.String r5 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r14)
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            r0.debug(r2, r3)
        L_0x048d:
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x049b
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r2 = r1.logTag
            java.lang.String r3 = "终端处理完成，发送处理结果给平台..."
            r0.debug(r2, r3)
        L_0x049b:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            r2 = 1
            byte[] r0 = r0.interaction(r14, r4, r2, r7)
            r3 = 2
            byte[] r5 = java.util.Arrays.copyOf(r0, r3)
            r3 = r5
            r2 = 2
            r5 = 0
            r8 = 8812(0x226c, float:1.2348E-41)
            r9 = 1
            r13 = -76
            goto L_0x01c3
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardAppInstallImpl.commandHandleVer2(int, byte[], byte[], byte[], java.lang.String):boolean");
    }

    public void cancel() {
        this.isCancel = true;
        if (this.cardBusinessBasic != null) {
            ScriptHandler scriptHandler = this.cardBusinessBasic.getScriptHandler();
            if (scriptHandler != null) {
                scriptHandler.cancel();
            }
        } else if (this.fmLog != null) {
            this.fmLog.warn(this.logTag, "业务取消时，业务处理对象为空");
        }
    }

    public EnumCardAppStatus getAppIssuerStatus(EnumCardAppType enumCardAppType) throws BusinessException {
        EnumCardAppStatus enumCardAppStatus;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog == null) {
            this.fmLog.debug(this.logTag, "获取卡的业务流程状态...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取卡的业务流程状态，业务处理对象为空");
            }
            throw new BusinessException("获取卡的业务流程状态，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        ApduHandler apduHandler = this.cardBusinessBasic.getApduHandler();
        if (apduHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "获取卡的业务流程状态时,APDU处理器为空");
            }
            throw new BusinessException("获取卡的业务流程状态时,APDU处理器为空", BusinessException.ErrorMessage.local_business_apdu_handler_null);
        }
        EnumCardAppStatus enumCardAppStatus2 = EnumCardAppStatus.STATUS_UNKNOW;
        if (this.fmLog == null) {
            this.fmLog.debug(this.logTag, "判断链接是否使用...");
        }
        if (apduHandler.isConnect()) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, "获取卡的业务流程状态时，APDU处理器正忙");
            }
            this.cardBusinessBasic.throwExceptionAndClose("获取卡的业务流程状态时，APDU处理器正忙", BusinessException.ErrorMessage.local_business_apdu_handler_busying, false);
        } else {
            apduHandler.connect();
        }
        CardManager cardManager = CardManagerFactory.instance().getCardManager(enumCardAppType);
        if (cardManager == null) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, "获取卡上应用当前状态时，传入的卡上应用类型无效");
            }
            return enumCardAppStatus2;
        }
        if (apduHandler.open(cardManager.getAid())) {
            enumCardAppStatus = EnumCardAppStatus.STATUS_INSTALL;
            cardManager.setApduHandler(apduHandler);
            try {
                enumCardAppStatus = cardManager.getStatus();
            } catch (BusinessException e) {
                if (this.fmLog != null) {
                    FMLog fMLog = this.fmLog;
                    String str = this.logTag;
                    fMLog.error(str, "获取卡上应用当前状态时出现异常:" + Util4Java.getExceptionInfo(e));
                }
                this.cardBusinessBasic.throwExceptionAndClose("获取卡上应用当前状态失败", BusinessException.ErrorMessage.local_business_apdu_handler_null, true);
            }
        } else {
            enumCardAppStatus = EnumCardAppStatus.STATUS_NO_APP;
        }
        if (this.fmLog == null) {
            this.fmLog.debug(this.logTag, "apduHandler.close()...");
        }
        apduHandler.close();
        return enumCardAppStatus;
    }

    public boolean issuer(byte[] bArr, int i, byte[] bArr2, byte[] bArr3) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        this.businessType = 0;
        return commandHandle(i, bArr, bArr2, bArr3, "上海交通卡应用发布");
    }

    public boolean issuerVer2(byte[] bArr, int i, byte[] bArr2, byte[] bArr3) throws BusinessException {
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        this.businessType = 0;
        return commandHandleVer2(i, bArr, bArr2, bArr3, "上海交通卡应用发布");
    }

    public EnumCardAppStatus getAppIssuerStatus4Platform(EnumCardAppType enumCardAppType, String str, byte[] bArr) throws BusinessException {
        byte[] bArr2;
        this.businessType = 1;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "从平台获取当前的空发状态...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str2 = this.logTag;
                fMLog.warn(str2, String.valueOf("从平台获取当前的空发状态") + "时，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("从平台获取当前的空发状态") + "时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str3 = this.logTag;
                fMLog2.warn(str3, String.valueOf("从平台获取当前的空发状态") + "时，消息处理器为空，消息配置文件加载失败");
            }
            throw new BusinessException(String.valueOf("从平台获取当前的空发状态") + "时，加载TAG定义配置文件失败", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.APP_MANAGER);
        if (enumCardAppType != null) {
            try {
                ITag createTag = messageHandler.createTag((byte) 14);
                createTag.addValue(enumCardAppType.getId());
                createMessage.addTag(createTag);
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    FMLog fMLog3 = this.fmLog;
                    String str4 = this.logTag;
                    fMLog3.warn(str4, String.valueOf("从平台获取当前的空发状态") + "时，消息处理器出现异常：" + Util4Java.getExceptionInfo(e));
                }
                CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
                cardBusinessBasic2.throwExceptionAndClose(String.valueOf("从平台获取当前的空发状态") + "时，消息处理器出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr2 = null;
            }
        }
        if (str != null && str.length() > 0) {
            ITag createTag2 = messageHandler.createTag((byte) Constants.TagName.DEVICE_MODEL);
            createTag2.addValue(str);
            createMessage.addTag(createTag2);
        }
        if (bArr != null && bArr.length > 0) {
            ITag createTag3 = messageHandler.createTag((byte) Constants.TagName.SEID);
            createTag3.addValue(bArr);
            createMessage.addTag(createTag3);
        }
        bArr2 = createMessage.toBytes();
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.APP_MANAGER);
        if (server4Business == null) {
            if (this.fmLog != null) {
                FMLog fMLog4 = this.fmLog;
                String str5 = this.logTag;
                fMLog4.warn(str5, String.valueOf("从平台获取当前的空发状态") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("从平台获取当前的空发状态") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        byte[] commandHandle = commandHandle(bArr2, "卡上应用发行状态查询", messageHandler, server4Business);
        if (Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, Arrays.copyOf(commandHandle, 2))) {
            try {
                ITag tag4Id = messageHandler.createMessage(this.response4BusinessFinish).getTag4Id(-76);
                if (tag4Id != null) {
                    return EnumCardAppStatus.instance(FM_Bytes.bytesToInt(tag4Id.getBytesVal()));
                }
            } catch (FMCommunicationMessageException e2) {
                if (this.fmLog != null) {
                    FMLog fMLog5 = this.fmLog;
                    String str6 = this.logTag;
                    fMLog5.warn(str6, String.valueOf("从平台获取当前的空发状态") + "，解析平台响应数据的附加数据域出现异常：" + Util4Java.getExceptionInfo(e2));
                }
                CardBusinessBasic cardBusinessBasic3 = this.cardBusinessBasic;
                cardBusinessBasic3.throwExceptionAndClose(String.valueOf("从平台获取当前的空发状态") + "，解析平台响应数据的附加数据域出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            }
        } else if (commandHandle.length <= 2) {
            if (this.fmLog != null) {
                FMLog fMLog6 = this.fmLog;
                String str7 = this.logTag;
                fMLog6.warn(str7, String.valueOf("从平台获取当前的空发状态") + "，空中发卡平台处理失败，平台响应：" + FM_Bytes.bytesToHexString(commandHandle));
            }
            CardBusinessBasic cardBusinessBasic4 = this.cardBusinessBasic;
            cardBusinessBasic4.throwExceptionAndClose(String.valueOf("从平台获取当前的空发状态") + "，空中发卡失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(commandHandle)), false);
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x0135  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x016e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean issuer(java.lang.String r10, byte r11, byte[] r12, byte[] r13, byte[] r14) throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r9 = this;
            r0 = 1
            r9.businessType = r0
            java.lang.String r1 = "应用发行"
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r9.cardBusinessBasic
            if (r2 != 0) goto L_0x0043
            cn.com.fmsh.util.log.FMLog r10 = r9.fmLog
            if (r10 == 0) goto L_0x0028
            cn.com.fmsh.util.log.FMLog r10 = r9.fmLog
            java.lang.String r11 = r9.logTag
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = java.lang.String.valueOf(r1)
            r12.<init>(r13)
            java.lang.String r13 = "时，业务处理对象为空"
            r12.append(r13)
            java.lang.String r12 = r12.toString()
            r10.warn(r11, r12)
        L_0x0028:
            cn.com.fmsh.tsm.business.exception.BusinessException r10 = new cn.com.fmsh.tsm.business.exception.BusinessException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r12 = java.lang.String.valueOf(r1)
            r11.<init>(r12)
            java.lang.String r12 = "时，业务处理器初始化失败"
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r12 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_init_fail
            r10.<init>(r11, r12)
            throw r10
        L_0x0043:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r9.cardBusinessBasic
            cn.com.fmsh.communication.message.IMessageHandler r2 = r2.getMessageHandler()
            if (r2 != 0) goto L_0x0084
            cn.com.fmsh.util.log.FMLog r10 = r9.fmLog
            if (r10 == 0) goto L_0x0069
            cn.com.fmsh.util.log.FMLog r10 = r9.fmLog
            java.lang.String r11 = r9.logTag
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = java.lang.String.valueOf(r1)
            r12.<init>(r13)
            java.lang.String r13 = "时，消息处理器为空，消息配置文件加载失败"
            r12.append(r13)
            java.lang.String r12 = r12.toString()
            r10.warn(r11, r12)
        L_0x0069:
            cn.com.fmsh.tsm.business.exception.BusinessException r10 = new cn.com.fmsh.tsm.business.exception.BusinessException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r12 = java.lang.String.valueOf(r1)
            r11.<init>(r12)
            java.lang.String r12 = "时，加载TAG定义配置文件失败"
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r12 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_load_config_fail
            r10.<init>(r11, r12)
            throw r10
        L_0x0084:
            r3 = 0
            r4 = 8811(0x226b, float:1.2347E-41)
            cn.com.fmsh.communication.message.IMessage r5 = r2.createMessage((int) r4)
            r6 = -78
            r7 = 0
            cn.com.fmsh.communication.message.ITag r6 = r2.createTag((byte) r6)     // Catch:{ FMCommunicationMessageException -> 0x0103 }
            byte[] r8 = new byte[r0]     // Catch:{ FMCommunicationMessageException -> 0x0103 }
            r8[r7] = r11     // Catch:{ FMCommunicationMessageException -> 0x0103 }
            r6.addValue((byte[]) r8)     // Catch:{ FMCommunicationMessageException -> 0x0103 }
            r5.addTag(r6)     // Catch:{ FMCommunicationMessageException -> 0x0103 }
            if (r13 == 0) goto L_0x00ad
            int r11 = r13.length     // Catch:{ FMCommunicationMessageException -> 0x0103 }
            if (r11 <= 0) goto L_0x00ad
            r11 = -79
            cn.com.fmsh.communication.message.ITag r11 = r2.createTag((byte) r11)     // Catch:{ FMCommunicationMessageException -> 0x0103 }
            r11.addValue((byte[]) r13)     // Catch:{ FMCommunicationMessageException -> 0x0103 }
            r5.addTag(r11)     // Catch:{ FMCommunicationMessageException -> 0x0103 }
        L_0x00ad:
            if (r12 == 0) goto L_0x00be
            int r11 = r12.length     // Catch:{ FMCommunicationMessageException -> 0x0103 }
            if (r11 <= 0) goto L_0x00be
            r11 = -77
            cn.com.fmsh.communication.message.ITag r11 = r2.createTag((byte) r11)     // Catch:{ FMCommunicationMessageException -> 0x0103 }
            r11.addValue((byte[]) r12)     // Catch:{ FMCommunicationMessageException -> 0x0103 }
            r5.addTag(r11)     // Catch:{ FMCommunicationMessageException -> 0x0103 }
        L_0x00be:
            if (r10 == 0) goto L_0x00d2
            int r11 = r10.length()     // Catch:{ FMCommunicationMessageException -> 0x0103 }
            if (r11 <= 0) goto L_0x00d2
            r11 = 104(0x68, float:1.46E-43)
            cn.com.fmsh.communication.message.ITag r11 = r2.createTag((byte) r11)     // Catch:{ FMCommunicationMessageException -> 0x0103 }
            r11.addValue((java.lang.String) r10)     // Catch:{ FMCommunicationMessageException -> 0x0103 }
            r5.addTag(r11)     // Catch:{ FMCommunicationMessageException -> 0x0103 }
        L_0x00d2:
            if (r14 == 0) goto L_0x00e3
            int r10 = r14.length     // Catch:{ FMCommunicationMessageException -> 0x0103 }
            if (r10 <= 0) goto L_0x00e3
            r10 = -76
            cn.com.fmsh.communication.message.ITag r10 = r2.createTag((byte) r10)     // Catch:{ FMCommunicationMessageException -> 0x0103 }
            r10.addValue((byte[]) r14)     // Catch:{ FMCommunicationMessageException -> 0x0103 }
            r5.addTag(r10)     // Catch:{ FMCommunicationMessageException -> 0x0103 }
        L_0x00e3:
            byte[] r10 = r5.toBytes()     // Catch:{ FMCommunicationMessageException -> 0x0103 }
            java.io.PrintStream r11 = java.lang.System.out     // Catch:{ FMCommunicationMessageException -> 0x00ff }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ FMCommunicationMessageException -> 0x00ff }
            java.lang.String r13 = "businessRequest:"
            r12.<init>(r13)     // Catch:{ FMCommunicationMessageException -> 0x00ff }
            java.lang.String r13 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r10)     // Catch:{ FMCommunicationMessageException -> 0x00ff }
            r12.append(r13)     // Catch:{ FMCommunicationMessageException -> 0x00ff }
            java.lang.String r12 = r12.toString()     // Catch:{ FMCommunicationMessageException -> 0x00ff }
            r11.println(r12)     // Catch:{ FMCommunicationMessageException -> 0x00ff }
            goto L_0x012d
        L_0x00ff:
            r11 = move-exception
            r3 = r10
            r10 = r11
            goto L_0x0104
        L_0x0103:
            r10 = move-exception
        L_0x0104:
            cn.com.fmsh.util.log.FMLog r11 = r9.fmLog
            if (r11 == 0) goto L_0x0122
            cn.com.fmsh.util.log.FMLog r11 = r9.fmLog
            java.lang.String r12 = r9.logTag
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r14 = "上海交通卡发卡时，消息处理器出现异常："
            r13.<init>(r14)
            java.lang.String r10 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r10)
            r13.append(r10)
            java.lang.String r10 = r13.toString()
            r11.warn(r12, r10)
        L_0x0122:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r10 = r9.cardBusinessBasic
            java.lang.String r11 = "上海交通卡发卡时，消息处理器出现异常"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r12 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r10.throwExceptionAndClose((java.lang.String) r11, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r12, (boolean) r7)
            r10 = r3
        L_0x012d:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r11 = r9.cardBusinessBasic
            java.lang.String r11 = r11.getServer4Business(r4)
            if (r11 != 0) goto L_0x016e
            cn.com.fmsh.util.log.FMLog r10 = r9.fmLog
            if (r10 == 0) goto L_0x0153
            cn.com.fmsh.util.log.FMLog r10 = r9.fmLog
            java.lang.String r11 = r9.logTag
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = java.lang.String.valueOf(r1)
            r12.<init>(r13)
            java.lang.String r13 = "时，获取处理的平台失败"
            r12.append(r13)
            java.lang.String r12 = r12.toString()
            r10.warn(r11, r12)
        L_0x0153:
            cn.com.fmsh.tsm.business.exception.BusinessException r10 = new cn.com.fmsh.tsm.business.exception.BusinessException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r12 = java.lang.String.valueOf(r1)
            r11.<init>(r12)
            java.lang.String r12 = "时，获取处理的平台失败"
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r12 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_app_query_server_fail
            r10.<init>(r11, r12)
            throw r10
        L_0x016e:
            java.lang.String r12 = "上海交通卡发卡"
            byte[] r10 = r9.commandHandle(r10, r12, r2, r11)
            boolean r10 = cn.com.fmsh.util.FM_Bytes.isEnd9000(r10)
            if (r10 == 0) goto L_0x017c
            return r0
        L_0x017c:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardAppInstallImpl.issuer(java.lang.String, byte, byte[], byte[], byte[]):boolean");
    }

    public boolean issuePrepare(byte[] bArr, String str, byte[] bArr2, byte[] bArr3, String str2, String str3, byte[] bArr4, IssuerPrepareResult issuerPrepareResult) throws BusinessException {
        byte[] bArr5;
        byte[] bArr6 = bArr;
        String str4 = str;
        byte[] bArr7 = bArr2;
        byte[] bArr8 = bArr3;
        String str5 = str2;
        String str6 = str3;
        byte[] bArr9 = bArr4;
        IssuerPrepareResult issuerPrepareResult2 = issuerPrepareResult;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "应用发行准备...");
        }
        this.businessType = 1;
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str7 = this.logTag;
                fMLog.warn(str7, String.valueOf("应用发行准备") + "时，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("应用发行准备") + "时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str8 = this.logTag;
                fMLog2.warn(str8, String.valueOf("应用发行准备") + "时，消息处理器为空，消息配置文件加载失败");
            }
            throw new BusinessException(String.valueOf("应用发行准备") + "时，加载TAG定义配置文件失败", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.APP_ISSUER_PREPARE);
        if (bArr6 != null) {
            try {
                if (bArr6.length > 1) {
                    ITag createTag = messageHandler.createTag((byte) Constants.TagName.SEID);
                    createTag.addValue(bArr6);
                    createMessage.addTag(createTag);
                }
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    FMLog fMLog3 = this.fmLog;
                    String str9 = this.logTag;
                    fMLog3.warn(str9, "应用发行准备时，消息处理器出现异常：" + Util4Java.getExceptionInfo(e));
                }
                this.cardBusinessBasic.throwExceptionAndClose("应用发行准备时，消息处理器出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr5 = null;
            }
        }
        if (str4 != null && str.length() > 0) {
            ITag createTag2 = messageHandler.createTag((byte) Constants.TagName.IMEI);
            createTag2.addValue(str4);
            createMessage.addTag(createTag2);
        }
        if (bArr7 != null && bArr7.length > 1) {
            ITag createTag3 = messageHandler.createTag((byte) Constants.TagName.APP_AID);
            createTag3.addValue(bArr7);
            createMessage.addTag(createTag3);
        }
        if (bArr8 != null && bArr8.length > 1) {
            ITag createTag4 = messageHandler.createTag((byte) Constants.TagName.CPLC);
            createTag4.addValue(bArr8);
            createMessage.addTag(createTag4);
        }
        if (str5 != null && str2.length() > 1) {
            ITag createTag5 = messageHandler.createTag((byte) Constants.TagName.DEVICE_MODEL);
            createTag5.addValue(str5);
            createMessage.addTag(createTag5);
        }
        if (str6 != null && str3.length() > 1) {
            ITag createTag6 = messageHandler.createTag((byte) Constants.TagName.EUID);
            createTag6.addValue(str6);
            createMessage.addTag(createTag6);
        }
        if (bArr9 != null && bArr9.length > 1) {
            ITag createTag7 = messageHandler.createTag((byte) Constants.TagName.PATCH_DATA);
            createTag7.addValue(bArr9);
            createMessage.addTag(createTag7);
        }
        bArr5 = createMessage.toBytes();
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.APP_ISSUER_PREPARE);
        if (server4Business == null) {
            if (this.fmLog != null) {
                FMLog fMLog4 = this.fmLog;
                String str10 = this.logTag;
                fMLog4.warn(str10, String.valueOf("应用发行准备") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("应用发行准备") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        this.cardBusinessBasic.businessReady("应用发行准备", server4Business);
        byte[] interaction = this.cardBusinessBasic.interaction(bArr5, "应用发行准备", false, server4Business);
        if (interaction == null || interaction.length < 1) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "平台处理业务请求失败");
            }
            this.cardBusinessBasic.throwExceptionAndClose("业务处理时，未收到平台响应数据", BusinessException.ErrorMessage.local_communication_no_response, false);
        }
        byte[] bArr10 = new byte[2];
        System.arraycopy(interaction, 0, bArr10, 0, bArr10.length);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, bArr10)) {
            if (this.fmLog != null) {
                FMLog fMLog5 = this.fmLog;
                String str11 = this.logTag;
                fMLog5.error(str11, "应用发行准备，平台处理失败: " + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose("应用发行准备，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(bArr10)), false);
        }
        try {
            IMessage createMessage2 = messageHandler.createMessage(Constants.TradeCode.APP_ISSUER_PREPARE, Arrays.copyOfRange(interaction, 2, interaction.length));
            ITag tag4Id = createMessage2.getTag4Id(-75);
            if (tag4Id != null) {
                byte[] bytesVal = tag4Id.getBytesVal();
                if (bytesVal[0] == 0) {
                    ITag tag4Id2 = createMessage2.getTag4Id(-71);
                    if (tag4Id2 != null) {
                        issuerPrepareResult2.setSir(tag4Id2.getBytesVal());
                    }
                    return true;
                }
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.format("业务处理完成，处理结果失败[%X]", new Object[]{Byte.valueOf(bytesVal[0])}));
                }
                ITag tag4Id3 = createMessage2.getTag4Id(-76);
                if (tag4Id3 != null) {
                    issuerPrepareResult2.setFailDesc(tag4Id3.getBytesVal());
                }
                return false;
            }
        } catch (FMCommunicationMessageException e2) {
            if (this.fmLog != null) {
                FMLog fMLog6 = this.fmLog;
                String str12 = this.logTag;
                fMLog6.error(str12, "上海交通卡空中充值时，解析平台响应出现异常: " + Util4Java.getExceptionInfo(e2));
            }
            this.cardBusinessBasic.throwExceptionAndClose("上海交通卡空中充值时，解析平台响应失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0149  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean issuePrepareResultSearch(byte[] r12, cn.com.fmsh.tsm.business.bean.IssuerPrepareResult r13) throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r11 = this;
            cn.com.fmsh.util.log.FMLog r0 = r11.fmLog
            if (r0 != 0) goto L_0x000e
            cn.com.fmsh.util.log.LogFactory r0 = cn.com.fmsh.util.log.LogFactory.getInstance()
            cn.com.fmsh.util.log.FMLog r0 = r0.getLog()
            r11.fmLog = r0
        L_0x000e:
            cn.com.fmsh.util.log.FMLog r0 = r11.fmLog
            if (r0 == 0) goto L_0x001c
            cn.com.fmsh.util.log.FMLog r0 = r11.fmLog
            java.lang.String r1 = r11.logTag
            java.lang.String r2 = "应用发行准备结果查询..."
            r0.info(r1, r2)
        L_0x001c:
            r0 = 1
            r11.businessType = r0
            java.lang.String r1 = "应用发行准备结果查询"
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r11.cardBusinessBasic
            if (r2 != 0) goto L_0x005f
            cn.com.fmsh.util.log.FMLog r12 = r11.fmLog
            if (r12 == 0) goto L_0x0044
            cn.com.fmsh.util.log.FMLog r12 = r11.fmLog
            java.lang.String r13 = r11.logTag
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = java.lang.String.valueOf(r1)
            r0.<init>(r2)
            java.lang.String r2 = "时，业务处理对象为空"
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r12.warn(r13, r0)
        L_0x0044:
            cn.com.fmsh.tsm.business.exception.BusinessException r12 = new cn.com.fmsh.tsm.business.exception.BusinessException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r0 = java.lang.String.valueOf(r1)
            r13.<init>(r0)
            java.lang.String r0 = "时，业务处理器初始化失败"
            r13.append(r0)
            java.lang.String r13 = r13.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r0 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_init_fail
            r12.<init>(r13, r0)
            throw r12
        L_0x005f:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r11.cardBusinessBasic
            cn.com.fmsh.communication.message.IMessageHandler r2 = r2.getMessageHandler()
            if (r2 != 0) goto L_0x00a0
            cn.com.fmsh.util.log.FMLog r12 = r11.fmLog
            if (r12 == 0) goto L_0x0085
            cn.com.fmsh.util.log.FMLog r12 = r11.fmLog
            java.lang.String r13 = r11.logTag
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = java.lang.String.valueOf(r1)
            r0.<init>(r2)
            java.lang.String r2 = "时，消息处理器为空，消息配置文件加载失败"
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r12.warn(r13, r0)
        L_0x0085:
            cn.com.fmsh.tsm.business.exception.BusinessException r12 = new cn.com.fmsh.tsm.business.exception.BusinessException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r0 = java.lang.String.valueOf(r1)
            r13.<init>(r0)
            java.lang.String r0 = "时，加载TAG定义配置文件失败"
            r13.append(r0)
            java.lang.String r13 = r13.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r0 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_load_config_fail
            r12.<init>(r13, r0)
            throw r12
        L_0x00a0:
            r3 = 0
            r4 = 8831(0x227f, float:1.2375E-41)
            cn.com.fmsh.communication.message.IMessage r5 = r2.createMessage((int) r4)
            r6 = -71
            r7 = 0
            cn.com.fmsh.communication.message.ITag r6 = r2.createTag((byte) r6)     // Catch:{ FMCommunicationMessageException -> 0x00c7 }
            r6.addValue((byte[]) r12)     // Catch:{ FMCommunicationMessageException -> 0x00c7 }
            r5.addTag(r6)     // Catch:{ FMCommunicationMessageException -> 0x00c7 }
            byte[] r12 = r5.toBytes()     // Catch:{ FMCommunicationMessageException -> 0x00c7 }
            java.io.PrintStream r3 = java.lang.System.out     // Catch:{ FMCommunicationMessageException -> 0x00c2 }
            java.lang.String r5 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r12)     // Catch:{ FMCommunicationMessageException -> 0x00c2 }
            r3.println(r5)     // Catch:{ FMCommunicationMessageException -> 0x00c2 }
            goto L_0x0108
        L_0x00c2:
            r3 = move-exception
            r10 = r3
            r3 = r12
            r12 = r10
            goto L_0x00c8
        L_0x00c7:
            r12 = move-exception
        L_0x00c8:
            cn.com.fmsh.util.log.FMLog r5 = r11.fmLog
            if (r5 == 0) goto L_0x00ed
            cn.com.fmsh.util.log.FMLog r5 = r11.fmLog
            java.lang.String r6 = r11.logTag
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = java.lang.String.valueOf(r1)
            r8.<init>(r9)
            java.lang.String r9 = "时，消息处理器出现异常："
            r8.append(r9)
            java.lang.String r12 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r12)
            r8.append(r12)
            java.lang.String r12 = r8.toString()
            r5.warn(r6, r12)
        L_0x00ed:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r12 = r11.cardBusinessBasic
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = java.lang.String.valueOf(r1)
            r5.<init>(r6)
            java.lang.String r6 = "时，消息处理器出现异常"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r12.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r7)
            r12 = r3
        L_0x0108:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r3 = r11.cardBusinessBasic
            java.lang.String r3 = r3.getServer4Business(r4)
            if (r3 != 0) goto L_0x0149
            cn.com.fmsh.util.log.FMLog r12 = r11.fmLog
            if (r12 == 0) goto L_0x012e
            cn.com.fmsh.util.log.FMLog r12 = r11.fmLog
            java.lang.String r13 = r11.logTag
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = java.lang.String.valueOf(r1)
            r0.<init>(r2)
            java.lang.String r2 = "时，获取处理的平台失败"
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r12.warn(r13, r0)
        L_0x012e:
            cn.com.fmsh.tsm.business.exception.BusinessException r12 = new cn.com.fmsh.tsm.business.exception.BusinessException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r0 = java.lang.String.valueOf(r1)
            r13.<init>(r0)
            java.lang.String r0 = "时，获取处理的平台失败"
            r13.append(r0)
            java.lang.String r13 = r13.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r0 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_app_query_server_fail
            r12.<init>(r13, r0)
            throw r12
        L_0x0149:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r5 = r11.cardBusinessBasic
            r5.businessReady(r1, r3)
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r5 = r11.cardBusinessBasic
            byte[] r12 = r5.interaction(r12, r1, r7, r3)
            if (r12 == 0) goto L_0x0159
            int r1 = r12.length
            if (r1 >= r0) goto L_0x0171
        L_0x0159:
            cn.com.fmsh.util.log.FMLog r1 = r11.fmLog
            if (r1 == 0) goto L_0x0167
            cn.com.fmsh.util.log.FMLog r1 = r11.fmLog
            java.lang.String r3 = r11.logTag
            java.lang.String r5 = "平台处理业务请求失败"
            r1.warn(r3, r5)
        L_0x0167:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r1 = r11.cardBusinessBasic
            java.lang.String r3 = "业务处理时，未收到平台响应数据"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r5 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_communication_no_response
            r1.throwExceptionAndClose((java.lang.String) r3, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r5, (boolean) r7)
        L_0x0171:
            r1 = 2
            byte[] r3 = new byte[r1]
            int r5 = r3.length
            java.lang.System.arraycopy(r12, r7, r3, r7, r5)
            byte[] r5 = cn.com.fmsh.tsm.business.constants.Constants.RespCodeonse4Platform.SUCESS
            boolean r5 = java.util.Arrays.equals(r5, r3)
            if (r5 != 0) goto L_0x01ae
            cn.com.fmsh.util.log.FMLog r5 = r11.fmLog
            if (r5 == 0) goto L_0x019e
            cn.com.fmsh.util.log.FMLog r5 = r11.fmLog
            java.lang.String r6 = r11.logTag
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "应用发行准备结果查询时，平台处理失败: "
            r8.<init>(r9)
            java.lang.String r9 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r12)
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r5.error(r6, r8)
        L_0x019e:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r5 = r11.cardBusinessBasic
            java.lang.String r6 = "应用发行准备结果查询时，平台处理失败"
            java.lang.String r3 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r3)
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.instance(r3)
            r5.throwExceptionAndClose((java.lang.String) r6, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r3, (boolean) r7)
        L_0x01ae:
            int r3 = r12.length     // Catch:{ FMCommunicationMessageException -> 0x01f4 }
            byte[] r12 = java.util.Arrays.copyOfRange(r12, r1, r3)     // Catch:{ FMCommunicationMessageException -> 0x01f4 }
            cn.com.fmsh.communication.message.IMessage r12 = r2.createMessage(r4, r12)     // Catch:{ FMCommunicationMessageException -> 0x01f4 }
            r1 = -75
            cn.com.fmsh.communication.message.ITag r1 = r12.getTag4Id(r1)     // Catch:{ FMCommunicationMessageException -> 0x01f4 }
            if (r1 == 0) goto L_0x021d
            byte[] r1 = r1.getBytesVal()     // Catch:{ FMCommunicationMessageException -> 0x01f4 }
            byte r2 = r1[r7]     // Catch:{ FMCommunicationMessageException -> 0x01f4 }
            if (r2 != 0) goto L_0x01c8
            return r0
        L_0x01c8:
            cn.com.fmsh.util.log.FMLog r2 = r11.fmLog     // Catch:{ FMCommunicationMessageException -> 0x01f4 }
            if (r2 == 0) goto L_0x01e4
            cn.com.fmsh.util.log.FMLog r2 = r11.fmLog     // Catch:{ FMCommunicationMessageException -> 0x01f4 }
            java.lang.String r3 = r11.logTag     // Catch:{ FMCommunicationMessageException -> 0x01f4 }
            java.lang.String r4 = "业务处理完成，处理结果失败[%X]"
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ FMCommunicationMessageException -> 0x01f4 }
            byte r1 = r1[r7]     // Catch:{ FMCommunicationMessageException -> 0x01f4 }
            java.lang.Byte r1 = java.lang.Byte.valueOf(r1)     // Catch:{ FMCommunicationMessageException -> 0x01f4 }
            r0[r7] = r1     // Catch:{ FMCommunicationMessageException -> 0x01f4 }
            java.lang.String r0 = java.lang.String.format(r4, r0)     // Catch:{ FMCommunicationMessageException -> 0x01f4 }
            r2.warn(r3, r0)     // Catch:{ FMCommunicationMessageException -> 0x01f4 }
        L_0x01e4:
            r0 = -76
            cn.com.fmsh.communication.message.ITag r12 = r12.getTag4Id(r0)     // Catch:{ FMCommunicationMessageException -> 0x01f4 }
            if (r12 == 0) goto L_0x01f3
            byte[] r12 = r12.getBytesVal()     // Catch:{ FMCommunicationMessageException -> 0x01f4 }
            r13.setFailDesc(r12)     // Catch:{ FMCommunicationMessageException -> 0x01f4 }
        L_0x01f3:
            return r7
        L_0x01f4:
            r12 = move-exception
            cn.com.fmsh.util.log.FMLog r13 = r11.fmLog
            if (r13 == 0) goto L_0x0213
            cn.com.fmsh.util.log.FMLog r13 = r11.fmLog
            java.lang.String r0 = r11.logTag
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "应用发行准备结果查询时，解析平台响应出现异常: "
            r1.<init>(r2)
            java.lang.String r12 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r12)
            r1.append(r12)
            java.lang.String r12 = r1.toString()
            r13.error(r0, r12)
        L_0x0213:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r12 = r11.cardBusinessBasic
            java.lang.String r13 = "应用发行准备结果查询时，解析平台响应失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r0 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r12.throwExceptionAndClose((java.lang.String) r13, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r0, (boolean) r7)
        L_0x021d:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardAppInstallImpl.issuePrepareResultSearch(byte[], cn.com.fmsh.tsm.business.bean.IssuerPrepareResult):boolean");
    }

    /* JADX WARNING: type inference failed for: r6v1 */
    /* JADX WARNING: type inference failed for: r6v4 */
    /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=?, for r6v2, types: [int, boolean] */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x022a  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0255 A[SYNTHETIC, Splitter:B:109:0x0255] */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x025d A[Catch:{ FMCommunicationMessageException -> 0x0269 }] */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0287  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x02a5  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x02af A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0128  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0157  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0159  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x015c  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x015e  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0211  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] commandHandle(byte[] r18, java.lang.String r19, cn.com.fmsh.communication.message.IMessageHandler r20, java.lang.String r21) throws cn.com.fmsh.tsm.business.exception.BusinessException {
        /*
            r17 = this;
            r1 = r17
            r2 = r19
            r3 = r20
            r4 = r21
            r5 = 0
            r1.isCancel = r5
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            if (r0 != 0) goto L_0x0048
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x002d
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r3 = r1.logTag
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = java.lang.String.valueOf(r19)
            r4.<init>(r5)
            java.lang.String r5 = "，业务处理对象为空"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r0.warn(r3, r4)
        L_0x002d:
            cn.com.fmsh.tsm.business.exception.BusinessException r0 = new cn.com.fmsh.tsm.business.exception.BusinessException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r2 = java.lang.String.valueOf(r19)
            r3.<init>(r2)
            java.lang.String r2 = "，业务处理器初始化失败"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r3 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_init_fail
            r0.<init>(r2, r3)
            throw r0
        L_0x0048:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            r0.businessReady(r2, r4)
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            cn.com.fmsh.script.ApduHandler r0 = r0.getApduHandler()
            if (r0 != 0) goto L_0x006d
            cn.com.fmsh.util.log.FMLog r6 = r1.fmLog
            if (r6 == 0) goto L_0x0063
            cn.com.fmsh.util.log.FMLog r6 = r1.fmLog
            java.lang.String r7 = r1.logTag
            java.lang.String r8 = "业务处理时，APDU处理器为空"
            r6.error(r7, r8)
        L_0x0063:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r6 = r1.cardBusinessBasic
            java.lang.String r7 = "上海交通卡空中发卡时，请先切换卡的访问方式(OMA/NFC)"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r8 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_null
            r6.throwExceptionAndClose((java.lang.String) r7, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r8, (boolean) r5)
        L_0x006d:
            boolean r6 = r0.isConnect()
            if (r6 == 0) goto L_0x008c
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x0081
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r6 = r1.logTag
            java.lang.String r7 = "业务处理时，APDU处理器正忙"
            r0.error(r6, r7)
        L_0x0081:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r6 = "上海交通卡空中发卡时，APDU处理器正忙"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r7 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_apdu_handler_busying
            r0.throwExceptionAndClose((java.lang.String) r6, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r7, (boolean) r5)
            goto L_0x008f
        L_0x008c:
            r0.connect()
        L_0x008f:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            r6 = 1
            r7 = r18
            byte[] r0 = r0.interaction(r7, r2, r6, r4)
            if (r0 == 0) goto L_0x009d
            int r7 = r0.length
            if (r7 >= r6) goto L_0x00b5
        L_0x009d:
            cn.com.fmsh.util.log.FMLog r7 = r1.fmLog
            if (r7 == 0) goto L_0x00ab
            cn.com.fmsh.util.log.FMLog r7 = r1.fmLog
            java.lang.String r8 = r1.logTag
            java.lang.String r9 = "平台处理业务请求失败"
            r7.warn(r8, r9)
        L_0x00ab:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r7 = r1.cardBusinessBasic
            java.lang.String r8 = "业务处理时，未收到平台响应数据"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r9 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_communication_no_response
            r7.throwExceptionAndClose((java.lang.String) r8, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r9, (boolean) r6)
        L_0x00b5:
            r7 = 2
            byte[] r8 = java.util.Arrays.copyOf(r0, r7)
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
        L_0x00be:
            byte[] r14 = cn.com.fmsh.tsm.business.constants.Constants.RespCodeonse4Platform.CARD_REQUEST
            boolean r8 = java.util.Arrays.equals(r14, r8)
            if (r8 != 0) goto L_0x00cc
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r2 = r1.cardBusinessBasic
            r2.businessFinish(r6)
            return r0
        L_0x00cc:
            boolean r8 = r1.isCancel
            if (r8 == 0) goto L_0x00da
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r8 = r1.cardBusinessBasic
            java.lang.String r14 = "业务处理时，接收到取消操作"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r15 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_cancel
            r8.throwExceptionAndClose((java.lang.String) r14, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r15, (boolean) r6)
        L_0x00da:
            cn.com.fmsh.script.bean.ApduRequestList r8 = new cn.com.fmsh.script.bean.ApduRequestList
            r8.<init>()
            cn.com.fmsh.communication.message.IMessage r0 = r3.createMessage((byte[]) r0)     // Catch:{ FMCommunicationMessageException -> 0x0121 }
            r14 = -96
            cn.com.fmsh.communication.message.ITag r14 = r0.getTag4Id(r14)     // Catch:{ FMCommunicationMessageException -> 0x0121 }
            r15 = -95
            cn.com.fmsh.communication.message.ITag r15 = r0.getTag4Id(r15)     // Catch:{ FMCommunicationMessageException -> 0x011f }
            r9 = -90
            cn.com.fmsh.communication.message.ITag r9 = r0.getTag4Id(r9)     // Catch:{ FMCommunicationMessageException -> 0x011d }
            r7 = -89
            cn.com.fmsh.communication.message.ITag r0 = r0.getTag4Id(r7)     // Catch:{ FMCommunicationMessageException -> 0x011d }
            if (r0 == 0) goto L_0x010c
            byte[] r7 = new byte[r6]     // Catch:{ FMCommunicationMessageException -> 0x011d }
            byte[] r16 = r0.getBytesVal()     // Catch:{ FMCommunicationMessageException -> 0x011d }
            byte r16 = r16[r5]     // Catch:{ FMCommunicationMessageException -> 0x011d }
            r7[r5] = r16     // Catch:{ FMCommunicationMessageException -> 0x011d }
            int r7 = cn.com.fmsh.util.FM_CN.bcdBytesToInt(r7)     // Catch:{ FMCommunicationMessageException -> 0x011d }
            r11 = r7
        L_0x010c:
            r7 = 9001(0x2329, float:1.2613E-41)
            cn.com.fmsh.communication.message.IMessage r7 = r3.createMessage((int) r7)     // Catch:{ FMCommunicationMessageException -> 0x011d }
            r7.addTag(r9)     // Catch:{ FMCommunicationMessageException -> 0x011a }
            r7.addTag(r0)     // Catch:{ FMCommunicationMessageException -> 0x011a }
            r12 = r7
            goto L_0x0154
        L_0x011a:
            r0 = move-exception
            r12 = r7
            goto L_0x0124
        L_0x011d:
            r0 = move-exception
            goto L_0x0124
        L_0x011f:
            r0 = move-exception
            goto L_0x0123
        L_0x0121:
            r0 = move-exception
            r14 = 0
        L_0x0123:
            r15 = 0
        L_0x0124:
            cn.com.fmsh.util.log.FMLog r7 = r1.fmLog
            if (r7 == 0) goto L_0x0149
            cn.com.fmsh.util.log.FMLog r7 = r1.fmLog
            java.lang.String r9 = "logTag"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = java.lang.String.valueOf(r19)
            r5.<init>(r6)
            java.lang.String r6 = "解析平台响应数据出现异常:"
            r5.append(r6)
            java.lang.String r0 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            r7.warn(r9, r0)
        L_0x0149:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r5 = "解析平台响应数据失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_command_data_invaild
            r7 = 1
            r0.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r7)
        L_0x0154:
            r9 = r14
            if (r9 == 0) goto L_0x0159
            r13 = 0
            goto L_0x015a
        L_0x0159:
            r9 = 0
        L_0x015a:
            if (r15 == 0) goto L_0x015e
            r13 = 1
            goto L_0x015f
        L_0x015e:
            r15 = r9
        L_0x015f:
            r8.fromTag(r15)     // Catch:{ FMCommunicationMessageException -> 0x018f, FMScriptHandleException -> 0x0163 }
            goto L_0x01ba
        L_0x0163:
            r0 = move-exception
            r5 = r0
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x0183
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r6 = r1.logTag
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r9 = "业务处理时，平台响应的数据解析异常"
            r7.<init>(r9)
            java.lang.String r5 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r5)
            r7.append(r5)
            java.lang.String r5 = r7.toString()
            r0.warn(r6, r5)
        L_0x0183:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r5 = "平台响应的数据解析失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_communication_invalid_response
            r7 = 1
            r0.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r7)
            goto L_0x01ba
        L_0x018f:
            r0 = move-exception
            r5 = r0
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x01af
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r6 = r1.logTag
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r9 = "业务处理时，平台响应的数据解析异常："
            r7.<init>(r9)
            java.lang.String r5 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r5)
            r7.append(r5)
            java.lang.String r5 = r7.toString()
            r0.warn(r6, r5)
        L_0x01af:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r5 = "平台响应的数据解析失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_communication_invalid_response
            r7 = 1
            r0.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r7)
        L_0x01ba:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic     // Catch:{ FMScriptHandleException -> 0x01c7 }
            cn.com.fmsh.script.ScriptHandler r0 = r0.getScriptHandler()     // Catch:{ FMScriptHandleException -> 0x01c7 }
            cn.com.fmsh.script.bean.ApduReponseList r0 = r0.execute((cn.com.fmsh.script.bean.ApduRequestList) r8)     // Catch:{ FMScriptHandleException -> 0x01c7 }
            r10 = r0
            r7 = 1
            goto L_0x0205
        L_0x01c7:
            r0 = move-exception
            cn.com.fmsh.util.log.FMLog r5 = r1.fmLog
            if (r5 == 0) goto L_0x01e6
            cn.com.fmsh.util.log.FMLog r5 = r1.fmLog
            java.lang.String r6 = r1.logTag
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "业务处理时，脚本执行出现异常:"
            r7.<init>(r8)
            java.lang.String r8 = cn.com.fmsh.util.Util4Java.getExceptionInfo(r0)
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            r5.warn(r6, r7)
        L_0x01e6:
            cn.com.fmsh.script.exception.FMScriptHandleException$ScriptHandleExceptionType r5 = cn.com.fmsh.script.exception.FMScriptHandleException.ScriptHandleExceptionType.STOPED
            cn.com.fmsh.script.exception.FMScriptHandleException$ScriptHandleExceptionType r0 = r0.getType()
            if (r5 != r0) goto L_0x01fa
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r5 = "业务处理被取消"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_business_cancel
            r7 = 1
            r0.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r7)
            goto L_0x0205
        L_0x01fa:
            r7 = 1
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r5 = "本地执行执行失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception
            r0.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r7)
        L_0x0205:
            if (r10 == 0) goto L_0x020d
            int r0 = r10.size()
            if (r0 >= r7) goto L_0x0226
        L_0x020d:
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x021b
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r5 = r1.logTag
            java.lang.String r6 = "业务处理时，响应结果为空"
            r0.warn(r5, r6)
        L_0x021b:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r5 = "本地执行执行失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_apdu_execute_exception
            r7 = 1
            r0.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r7)
        L_0x0226:
            int r0 = r1.businessType
            if (r0 != 0) goto L_0x0253
            cn.com.fmsh.tsm.business.enums.EnumIssueProcess r0 = cn.com.fmsh.tsm.business.enums.EnumIssueProcess.instance(r11)
            cn.com.fmsh.tsm.business.IssuerProcessHandler r5 = r1.issuerProcessHandler
            if (r5 == 0) goto L_0x0253
            if (r0 == 0) goto L_0x0253
            cn.com.fmsh.util.log.FMLog r5 = r1.fmLog
            if (r5 == 0) goto L_0x024e
            cn.com.fmsh.util.log.FMLog r5 = r1.fmLog
            java.lang.String r6 = r1.logTag
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "进度通知,步骤："
            r7.<init>(r8)
            r7.append(r11)
            java.lang.String r7 = r7.toString()
            r5.debug(r6, r7)
        L_0x024e:
            cn.com.fmsh.tsm.business.IssuerProcessHandler r5 = r1.issuerProcessHandler
            r5.handle(r0)
        L_0x0253:
            if (r13 != 0) goto L_0x025d
            cn.com.fmsh.communication.message.ITag r0 = r10.toTag4A2()     // Catch:{ FMCommunicationMessageException -> 0x0269 }
            r12.addTag(r0)     // Catch:{ FMCommunicationMessageException -> 0x0269 }
            goto L_0x0264
        L_0x025d:
            cn.com.fmsh.communication.message.ITag r0 = r10.toTag4A3()     // Catch:{ FMCommunicationMessageException -> 0x0269 }
            r12.addTag(r0)     // Catch:{ FMCommunicationMessageException -> 0x0269 }
        L_0x0264:
            byte[] r9 = r12.toBytes()     // Catch:{ FMCommunicationMessageException -> 0x0269 }
            goto L_0x0283
        L_0x0269:
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x0277
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r5 = r1.logTag
            java.lang.String r6 = "业务处理时，构造终端响应数据出现异常"
            r0.warn(r5, r6)
        L_0x0277:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            java.lang.String r5 = "构造平台请求数据失败"
            cn.com.fmsh.tsm.business.exception.BusinessException$ErrorMessage r6 = cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage.local_message_message_handle_exception
            r7 = 1
            r0.throwExceptionAndClose((java.lang.String) r5, (cn.com.fmsh.tsm.business.exception.BusinessException.ErrorMessage) r6, (boolean) r7)
            r9 = 0
        L_0x0283:
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x02a1
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r5 = r1.logTag
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "执行完成 repones:"
            r6.<init>(r7)
            java.lang.String r7 = cn.com.fmsh.util.FM_Bytes.bytesToHexString(r9)
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r0.debug(r5, r6)
        L_0x02a1:
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            if (r0 == 0) goto L_0x02af
            cn.com.fmsh.util.log.FMLog r0 = r1.fmLog
            java.lang.String r5 = r1.logTag
            java.lang.String r6 = "终端处理完成，发送处理结果给平台..."
            r0.debug(r5, r6)
        L_0x02af:
            cn.com.fmsh.tsm.business.core.CardBusinessBasic r0 = r1.cardBusinessBasic
            r5 = 1
            byte[] r0 = r0.interaction(r9, r2, r5, r4)
            r6 = 2
            byte[] r8 = java.util.Arrays.copyOf(r0, r6)
            r5 = 0
            r6 = 1
            r7 = 2
            goto L_0x00be
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.tsm.business.core.CardAppInstallImpl.commandHandle(byte[], java.lang.String, cn.com.fmsh.communication.message.IMessageHandler, java.lang.String):byte[]");
    }

    public byte[] deleteAppVer1(byte[] bArr, EnumCardAppType enumCardAppType, byte[] bArr2, String str) throws BusinessException {
        byte[] bArr3;
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "卡上应用删除时，业务处理对象为空");
            }
            throw new BusinessException("卡上应用删除时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "卡上应用删除时，消息处理器为空，消息配置文件加载失败");
            }
            throw new BusinessException("卡上应用删除时，加载TAG定义配置文件失败", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.APP_MANAGER);
        if (bArr != null) {
            try {
                if (bArr.length > 0) {
                    ITag createTag = messageHandler.createTag((byte) Constants.TagName.ACTIVITY_INFO);
                    createTag.addValue(bArr);
                    createMessage.addTag(createTag);
                }
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "卡上应用删除时，消息处理器出现异常：" + Util4Java.getExceptionInfo(e));
                }
                this.cardBusinessBasic.throwExceptionAndClose("卡上应用删除时，消息处理器出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr3 = null;
            }
        }
        if (enumCardAppType != null) {
            ITag createTag2 = messageHandler.createTag((byte) 14);
            createTag2.addValue(enumCardAppType.getId());
            createMessage.addTag(createTag2);
        }
        if (str != null && str.length() > 0) {
            ITag createTag3 = messageHandler.createTag((byte) Constants.TagName.DEVICE_MODEL);
            createTag3.addValue(str);
            createMessage.addTag(createTag3);
        }
        if (bArr2 != null && bArr2.length > 0) {
            ITag createTag4 = messageHandler.createTag((byte) Constants.TagName.SEID);
            createTag4.addValue(bArr2);
            createMessage.addTag(createTag4);
        }
        ITag createTag5 = messageHandler.createTag((byte) Constants.TagName.APP_MANAGE_OPEATE_TYPE);
        createTag5.addValue(EnumAppManageOperateType.APP_DELETE.getId());
        createMessage.addTag(createTag5);
        bArr3 = createMessage.toBytes();
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.APP_MANAGER);
        if (server4Business == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "卡上应用删除时时，获取处理的平台失败");
            }
            throw new BusinessException("卡上应用删除时时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        byte[] commandHandle = commandHandle(bArr3, "卡上应用删除时", messageHandler, server4Business);
        if (Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, Arrays.copyOf(commandHandle, 2))) {
            try {
                ITag tag4Id = messageHandler.createMessage(commandHandle).getTag4Id(-76);
                if (tag4Id != null) {
                    return tag4Id.getBytesVal();
                }
            } catch (FMCommunicationMessageException e2) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "卡上应用删除时，解析平台响应数据的附加数据域出现异常：" + Util4Java.getExceptionInfo(e2));
                }
                this.cardBusinessBasic.throwExceptionAndClose("卡上应用删除时，解析平台响应数据的附加数据域出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            }
        } else if (commandHandle.length <= 2) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "卡上应用删除时，空中发卡平台处理失败，平台响应：" + FM_Bytes.bytesToHexString(commandHandle));
            }
            this.cardBusinessBasic.throwExceptionAndClose("卡上应用删除时，空中发卡失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(commandHandle)), false);
        }
        return null;
    }

    public boolean setApp(byte[] bArr, EnumCardAppType enumCardAppType, byte[] bArr2, String str, EnumAppManageOperateType enumAppManageOperateType) throws BusinessException {
        byte[] bArr3;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "卡上应用状态设置(ver1)...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "卡上应用状态设置时，业务处理对象为空");
            }
            throw new BusinessException("卡上应用状态设置时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "卡上应用状态设置时，消息处理器为空，消息配置文件加载失败");
            }
            throw new BusinessException("卡上应用状态设置时，加载TAG定义配置文件失败", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.APP_MANAGER);
        if (bArr != null) {
            try {
                if (bArr.length > 0) {
                    ITag createTag = messageHandler.createTag((byte) Constants.TagName.ACTIVITY_INFO);
                    createTag.addValue(bArr);
                    createMessage.addTag(createTag);
                }
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "设置卡上应用状态时，消息处理器出现异常：" + Util4Java.getExceptionInfo(e));
                }
                this.cardBusinessBasic.throwExceptionAndClose("设置卡上应用状态时，消息处理器出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr3 = null;
            }
        }
        if (enumCardAppType != null) {
            ITag createTag2 = messageHandler.createTag((byte) 14);
            createTag2.addValue(enumCardAppType.getId());
            createMessage.addTag(createTag2);
        }
        if (str != null && str.length() > 0) {
            ITag createTag3 = messageHandler.createTag((byte) Constants.TagName.DEVICE_MODEL);
            createTag3.addValue(str);
            createMessage.addTag(createTag3);
        }
        if (bArr2 != null && bArr2.length > 0) {
            ITag createTag4 = messageHandler.createTag((byte) Constants.TagName.SEID);
            createTag4.addValue(bArr2);
            createMessage.addTag(createTag4);
        }
        if (enumAppManageOperateType == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "设置卡上应用状态时，没有传入待设置的状态");
            }
            throw new BusinessException("设置卡上应用状态时，没有传入待设置的状态", BusinessException.ErrorMessage.local_business_para_error);
        }
        ITag createTag5 = messageHandler.createTag((byte) Constants.TagName.APP_MANAGE_OPEATE_TYPE);
        createTag5.addValue(enumAppManageOperateType.getId());
        createMessage.addTag(createTag5);
        bArr3 = createMessage.toBytes();
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.APP_MANAGER);
        if (server4Business == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "卡上应用删除时时，获取处理的平台失败");
            }
            throw new BusinessException("卡上应用删除时时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        byte[] commandHandle = commandHandle(bArr3, "设置卡上应用状态时", messageHandler, server4Business);
        if (FM_Bytes.isEnd9000(commandHandle)) {
            return true;
        }
        if (commandHandle.length <= 2) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "设置卡上应用状态时，空中发卡平台处理失败，平台响应：" + FM_Bytes.bytesToHexString(commandHandle));
            }
            this.cardBusinessBasic.throwExceptionAndClose("设置卡上应用状态时，空中发卡失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(commandHandle)), false);
        } else {
            try {
                if (messageHandler.createMessage(Constants.TradeCode.APP_ISSUER_VER2, Arrays.copyOfRange(commandHandle, 2, commandHandle.length)).getTag4Id(-76) != null) {
                    this.fmLog.warn(this.logTag, "设置卡上应用状态时，发卡失败，第三方平台异常," + new String(this.response4BusinessFinish));
                }
            } catch (FMCommunicationMessageException e2) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "设置卡上应用状态时，，平台处理失败，解析第三方异常数据异常：" + Util4Java.getExceptionInfo(e2));
                }
                this.cardBusinessBasic.throwExceptionAndClose("设置卡上应用状态时，，平台处理失败，解析第三方异常数据异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            }
        }
        return false;
    }

    public boolean setAppVer2(byte[] bArr, EnumCardAppType enumCardAppType, byte[] bArr2, String str, EnumAppManageOperateType enumAppManageOperateType) throws BusinessException {
        byte[] bArr3;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "卡上应用状态设置(ver2)...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "卡上应用状态设置时，业务处理对象为空");
            }
            throw new BusinessException("卡上应用状态设置时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "卡上应用状态设置时，消息处理器为空，消息配置文件加载失败");
            }
            throw new BusinessException("卡上应用状态设置时，加载TAG定义配置文件失败", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.APP_MANAGER_VER2);
        if (bArr != null) {
            try {
                if (bArr.length > 0) {
                    ITag createTag = messageHandler.createTag((byte) Constants.TagName.ACTIVITY_INFO);
                    createTag.addValue(bArr);
                    createMessage.addTag(createTag);
                }
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "设置卡上应用状态时，消息处理器出现异常：" + Util4Java.getExceptionInfo(e));
                }
                this.cardBusinessBasic.throwExceptionAndClose("设置卡上应用状态时，消息处理器出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr3 = null;
            }
        }
        if (enumCardAppType != null) {
            ITag createTag2 = messageHandler.createTag((byte) 14);
            createTag2.addValue(enumCardAppType.getId());
            createMessage.addTag(createTag2);
        }
        if (str != null && str.length() > 0) {
            ITag createTag3 = messageHandler.createTag((byte) Constants.TagName.DEVICE_MODEL);
            createTag3.addValue(str);
            createMessage.addTag(createTag3);
        }
        if (bArr2 != null && bArr2.length > 0) {
            ITag createTag4 = messageHandler.createTag((byte) Constants.TagName.SEID);
            createTag4.addValue(bArr2);
            createMessage.addTag(createTag4);
        }
        if (enumAppManageOperateType == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "设置卡上应用状态时，没有传入待设置的状态");
            }
            throw new BusinessException("设置卡上应用状态时，没有传入待设置的状态", BusinessException.ErrorMessage.local_business_para_error);
        }
        ITag createTag5 = messageHandler.createTag((byte) Constants.TagName.APP_MANAGE_OPEATE_TYPE);
        createTag5.addValue(enumAppManageOperateType.getId());
        createMessage.addTag(createTag5);
        bArr3 = createMessage.toBytes();
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.APP_MANAGER_VER2);
        if (server4Business == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "卡上应用删除时时，获取处理的平台失败");
            }
            throw new BusinessException("卡上应用删除时时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        byte[] commandHandle = commandHandle(bArr3, "设置卡上应用状态时", messageHandler, server4Business);
        if (FM_Bytes.isEnd9000(commandHandle)) {
            return true;
        }
        if (commandHandle.length <= 2) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "设置卡上应用状态时，空中发卡平台处理失败，平台响应：" + FM_Bytes.bytesToHexString(commandHandle));
            }
            this.cardBusinessBasic.throwExceptionAndClose("设置卡上应用状态时，空中发卡失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(commandHandle)), false);
        } else {
            try {
                if (messageHandler.createMessage(Constants.TradeCode.APP_ISSUER_VER2, Arrays.copyOfRange(commandHandle, 2, commandHandle.length)).getTag4Id(-76) != null) {
                    this.fmLog.warn(this.logTag, "设置卡上应用状态时，发卡失败，第三方平台异常," + new String(this.response4BusinessFinish));
                }
            } catch (FMCommunicationMessageException e2) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "设置卡上应用状态时，，平台处理失败，解析第三方异常数据异常：" + Util4Java.getExceptionInfo(e2));
                }
                this.cardBusinessBasic.throwExceptionAndClose("设置卡上应用状态时，，平台处理失败，解析第三方异常数据异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            }
        }
        return false;
    }

    public CardAppInfo deleteApp(byte[] bArr, EnumCardAppType enumCardAppType, byte[] bArr2, String str) throws BusinessException {
        byte[] bArr3;
        List<TLVParse.TagEntry> parse;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "卡上应用删除...");
        }
        CardAppInfo cardAppInfo = new CardAppInfo();
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str2 = this.logTag;
                fMLog.warn(str2, String.valueOf("卡上应用删除") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("卡上应用删除") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str3 = this.logTag;
                fMLog2.warn(str3, String.valueOf("卡上应用删除") + "，消息处理器为空，消息配置文件加载失败");
            }
            throw new BusinessException(String.valueOf("卡上应用删除") + "，加载TAG定义配置文件失败", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.APP_MANAGER_VER2);
        if (bArr != null) {
            try {
                if (bArr.length > 0) {
                    ITag createTag = messageHandler.createTag((byte) Constants.TagName.ACTIVITY_INFO);
                    createTag.addValue(bArr);
                    createMessage.addTag(createTag);
                }
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    FMLog fMLog3 = this.fmLog;
                    String str4 = this.logTag;
                    fMLog3.warn(str4, String.valueOf("卡上应用删除") + "，消息处理器出现异常：" + Util4Java.getExceptionInfo(e));
                }
                CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
                cardBusinessBasic2.throwExceptionAndClose(String.valueOf("卡上应用删除") + "，消息处理器出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr3 = null;
            }
        }
        if (enumCardAppType != null) {
            ITag createTag2 = messageHandler.createTag((byte) 14);
            createTag2.addValue(enumCardAppType.getId());
            createMessage.addTag(createTag2);
        }
        if (str != null && str.length() > 0) {
            ITag createTag3 = messageHandler.createTag((byte) Constants.TagName.DEVICE_MODEL);
            createTag3.addValue(str);
            createMessage.addTag(createTag3);
        }
        if (bArr2 != null && bArr2.length > 0) {
            ITag createTag4 = messageHandler.createTag((byte) Constants.TagName.SEID);
            createTag4.addValue(bArr2);
            createMessage.addTag(createTag4);
        }
        ITag createTag5 = messageHandler.createTag((byte) Constants.TagName.APP_MANAGE_OPEATE_TYPE);
        createTag5.addValue(EnumAppManageOperateType.APP_DELETE.getId());
        createMessage.addTag(createTag5);
        bArr3 = createMessage.toBytes();
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.APP_MANAGER_VER2);
        if (server4Business == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "卡上应用删除时时，获取处理的平台失败");
            }
            throw new BusinessException("卡上应用删除时时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        byte[] commandHandle = commandHandle(bArr3, "卡上应用删除", messageHandler, server4Business);
        if (!FM_Bytes.isEnd9000(commandHandle)) {
            if (commandHandle.length <= 2) {
                if (this.fmLog != null) {
                    FMLog fMLog4 = this.fmLog;
                    String str5 = this.logTag;
                    fMLog4.warn(str5, String.valueOf("卡上应用删除") + "，空中发卡平台处理失败，平台响应：" + FM_Bytes.bytesToHexString(commandHandle));
                }
                CardBusinessBasic cardBusinessBasic3 = this.cardBusinessBasic;
                cardBusinessBasic3.throwExceptionAndClose(String.valueOf("卡上应用删除") + "，空中发卡失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(commandHandle)), false);
            } else {
                try {
                    ITag tag4Id = messageHandler.createMessage(Constants.TradeCode.APP_ISSUER_VER2, Arrays.copyOfRange(commandHandle, 2, commandHandle.length)).getTag4Id(-76);
                    if (tag4Id != null) {
                        FMLog fMLog5 = this.fmLog;
                        String str6 = this.logTag;
                        fMLog5.warn(str6, String.valueOf("卡上应用删除") + "发卡失败，第三方平台异常," + tag4Id.getStringVal());
                    }
                } catch (FMCommunicationMessageException e2) {
                    if (this.fmLog != null) {
                        FMLog fMLog6 = this.fmLog;
                        String str7 = this.logTag;
                        fMLog6.warn(str7, String.valueOf("卡上应用删除") + "，平台处理失败，解析第三方异常数据异常：" + Util4Java.getExceptionInfo(e2));
                    }
                    CardBusinessBasic cardBusinessBasic4 = this.cardBusinessBasic;
                    cardBusinessBasic4.throwExceptionAndClose(String.valueOf("卡上应用删除") + "，平台处理失败，解析第三方异常数据异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                }
            }
            if (this.fmLog != null) {
                this.fmLog.info(this.logTag, "卡上应用删除完成");
            }
            return cardAppInfo;
        } else if (commandHandle.length == 2) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, "卡上应用删除完成,平台没有返回删除的应用基本信息");
            }
            return cardAppInfo;
        } else {
            try {
                ITag tag4Id2 = messageHandler.createMessage(commandHandle).getTag4Id(-76);
                if (!(tag4Id2 == null || tag4Id2.getBytesVal() == null || tag4Id2.getBytesVal().length <= 0 || (parse = TLVParse.intance().parse(tag4Id2.getBytesVal(), 1)) == null || parse.size() <= 0)) {
                    for (TLVParse.TagEntry next : parse) {
                        if (next != null) {
                            if (15 == next.getTag()[0]) {
                                cardAppInfo.setCardAppNo(next.getData());
                            }
                            if (40 == next.getTag()[0]) {
                                cardAppInfo.setBalance(Integer.valueOf(FM_Bytes.bytesToInt(next.getData())));
                            }
                            if (95 == next.getTag()[0]) {
                                cardAppInfo.setMoc(FM_Bytes.bytesToHexString(next.getData()));
                            }
                        }
                    }
                }
                return cardAppInfo;
            } catch (FMCommunicationMessageException e3) {
                if (this.fmLog != null) {
                    FMLog fMLog7 = this.fmLog;
                    String str8 = this.logTag;
                    fMLog7.warn(str8, String.valueOf("卡上应用删除") + "，解析平台响应数据的附加数据域出现异常：" + Util4Java.getExceptionInfo(e3));
                }
                CardBusinessBasic cardBusinessBasic5 = this.cardBusinessBasic;
                cardBusinessBasic5.throwExceptionAndClose(String.valueOf("卡上应用删除") + "，解析平台响应数据的附加数据域出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            }
        }
    }

    public boolean appletDownload(EnumCardAppType enumCardAppType, byte[] bArr, String str) throws BusinessException {
        byte[] bArr2;
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str2 = this.logTag;
                fMLog.warn(str2, String.valueOf("应用安装包下载") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("应用安装包下载") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str3 = this.logTag;
                fMLog2.warn(str3, String.valueOf("应用安装包下载") + "，消息处理器为空，消息配置文件加载失败");
            }
            throw new BusinessException(String.valueOf("应用安装包下载") + "，加载TAG定义配置文件失败", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.APPLET_DOWNLOAD);
        if (enumCardAppType != null) {
            try {
                ITag createTag = messageHandler.createTag((byte) 14);
                createTag.addValue(enumCardAppType.getId());
                createMessage.addTag(createTag);
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    FMLog fMLog3 = this.fmLog;
                    String str4 = this.logTag;
                    fMLog3.warn(str4, String.valueOf("应用安装包下载") + "，消息处理器出现异常：" + Util4Java.getExceptionInfo(e));
                }
                CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
                cardBusinessBasic2.throwExceptionAndClose(String.valueOf("应用安装包下载") + "，消息处理器出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr2 = null;
            }
        }
        if (str != null && str.length() > 0) {
            ITag createTag2 = messageHandler.createTag((byte) Constants.TagName.DEVICE_MODEL);
            createTag2.addValue(str);
            createMessage.addTag(createTag2);
        }
        if (bArr != null && bArr.length > 0) {
            ITag createTag3 = messageHandler.createTag((byte) Constants.TagName.SEID);
            createTag3.addValue(bArr);
            createMessage.addTag(createTag3);
        }
        bArr2 = createMessage.toBytes();
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.APPLET_DOWNLOAD);
        if (server4Business == null) {
            if (this.fmLog != null) {
                FMLog fMLog4 = this.fmLog;
                String str5 = this.logTag;
                fMLog4.warn(str5, String.valueOf("应用安装包下载") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("应用安装包下载") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        byte[] commandHandle = commandHandle(bArr2, "应用安装包下载", messageHandler, server4Business);
        if (!FM_Bytes.isEnd9000(commandHandle)) {
            if (commandHandle.length <= 2) {
                if (this.fmLog != null) {
                    FMLog fMLog5 = this.fmLog;
                    String str6 = this.logTag;
                    fMLog5.warn(str6, String.valueOf("应用安装包下载") + "，空中发卡平台处理失败，平台响应：" + FM_Bytes.bytesToHexString(commandHandle));
                }
                CardBusinessBasic cardBusinessBasic3 = this.cardBusinessBasic;
                cardBusinessBasic3.throwExceptionAndClose(String.valueOf("应用安装包下载") + "，空中发卡失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(commandHandle)), false);
            } else {
                try {
                    ITag tag4Id = messageHandler.createMessage(Constants.TradeCode.APP_ISSUER_VER2, Arrays.copyOfRange(commandHandle, 2, commandHandle.length)).getTag4Id(-76);
                    if (tag4Id != null) {
                        FMLog fMLog6 = this.fmLog;
                        String str7 = this.logTag;
                        fMLog6.warn(str7, String.valueOf("应用安装包下载") + "发卡失败，第三方平台异常," + tag4Id.getStringVal());
                    }
                } catch (FMCommunicationMessageException e2) {
                    if (this.fmLog != null) {
                        FMLog fMLog7 = this.fmLog;
                        String str8 = this.logTag;
                        fMLog7.warn(str8, String.valueOf("应用安装包下载") + "，平台处理失败，解析第三方异常数据异常：" + Util4Java.getExceptionInfo(e2));
                    }
                    CardBusinessBasic cardBusinessBasic4 = this.cardBusinessBasic;
                    cardBusinessBasic4.throwExceptionAndClose(String.valueOf("应用安装包下载") + "，平台处理失败，解析第三方异常数据异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                }
            }
            return false;
        } else if (this.fmLog == null) {
            return true;
        } else {
            this.fmLog.debug(this.logTag, "应用安装包处理完成");
            return true;
        }
    }

    public boolean applyBusiness(String str, byte[] bArr, String str2, byte[] bArr2) throws BusinessException {
        String str3;
        byte[] bArr3;
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("应用安装包下载") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("应用安装包下载") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("应用安装包下载") + "，消息处理器为空，消息配置文件加载失败");
            }
            throw new BusinessException(String.valueOf("应用安装包下载") + "，加载TAG定义配置文件失败", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        Configration configration = this.cardBusinessBasic.getConfigration();
        if (configration != null) {
            str3 = configration.getCompanyCode();
        } else {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("应用安装包下载") + "时，Configration 为空");
            }
            str3 = null;
        }
        if (str3 == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("应用安装包下载") + "时，商户编号为空");
            }
            throw new BusinessException(String.valueOf("应用安装包下载") + "时，商户编号为空", BusinessException.ErrorMessage.local_business_para_error);
        }
        IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.APPLET_DOWNLOAD_VER2);
        try {
            ITag createTag = messageHandler.createTag((byte) Constants.TagName.COMPANY_CODE);
            createTag.addValue(str3);
            createMessage.addTag(createTag);
            if (str != null) {
                ITag createTag2 = messageHandler.createTag((byte) Constants.TagName.CP_NO);
                createTag2.addValue(str);
                createMessage.addTag(createTag2);
            }
            if (str2 != null && str2.length() > 0) {
                ITag createTag3 = messageHandler.createTag((byte) Constants.TagName.DEVICE_MODEL);
                createTag3.addValue(str2);
                createMessage.addTag(createTag3);
            }
            if (bArr != null && bArr.length > 0) {
                ITag createTag4 = messageHandler.createTag((byte) Constants.TagName.SEID);
                createTag4.addValue(bArr);
                createMessage.addTag(createTag4);
            }
            if (bArr2 != null && bArr2.length > 0) {
                ITag createTag5 = messageHandler.createTag((byte) Constants.TagName.ACTIVITY_INFO);
                createTag5.addValue(bArr2);
                createMessage.addTag(createTag5);
            }
            bArr3 = createMessage.toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("应用安装包下载") + "，消息处理器出现异常：" + Util4Java.getExceptionInfo(e));
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("应用安装包下载") + "，消息处理器出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            bArr3 = null;
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.APPLET_DOWNLOAD_VER2);
        if (server4Business == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("应用安装包下载") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("应用安装包下载") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        byte[] commandHandle = commandHandle(bArr3, "应用安装包下载", messageHandler, server4Business);
        if (!FM_Bytes.isEnd9000(commandHandle)) {
            if (commandHandle.length <= 2) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("应用安装包下载") + "，空中发卡平台处理失败，平台响应：" + FM_Bytes.bytesToHexString(commandHandle));
                }
                this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("应用安装包下载") + "，空中发卡失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(commandHandle)), false);
            } else {
                try {
                    ITag tag4Id = messageHandler.createMessage(Constants.TradeCode.APP_ISSUER_VER2, Arrays.copyOfRange(commandHandle, 2, commandHandle.length)).getTag4Id(-76);
                    if (tag4Id != null) {
                        this.fmLog.warn(this.logTag, String.valueOf("应用安装包下载") + "发卡失败，第三方平台异常," + tag4Id.getStringVal());
                    }
                } catch (FMCommunicationMessageException e2) {
                    if (this.fmLog != null) {
                        this.fmLog.warn(this.logTag, String.valueOf("应用安装包下载") + "，平台处理失败，解析第三方异常数据异常：" + Util4Java.getExceptionInfo(e2));
                    }
                    this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("应用安装包下载") + "，平台处理失败，解析第三方异常数据异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                }
            }
            return false;
        } else if (this.fmLog == null) {
            return true;
        } else {
            this.fmLog.debug(this.logTag, "应用安装包处理完成");
            return true;
        }
    }

    public byte[] moveApp(byte[] bArr, EnumCardAppType enumCardAppType, byte[] bArr2, String str) throws BusinessException {
        byte[] bArr3;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "卡上应用迁出...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str2 = this.logTag;
                fMLog.warn(str2, String.valueOf("卡上应用迁出") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("卡上应用迁出") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str3 = this.logTag;
                fMLog2.warn(str3, String.valueOf("卡上应用迁出") + "，消息处理器为空，消息配置文件加载失败");
            }
            throw new BusinessException(String.valueOf("卡上应用迁出") + "，加载TAG定义配置文件失败", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.APP_MANAGER_VER2);
        if (bArr != null) {
            try {
                if (bArr.length > 0) {
                    ITag createTag = messageHandler.createTag((byte) Constants.TagName.ACTIVITY_INFO);
                    createTag.addValue(bArr);
                    createMessage.addTag(createTag);
                }
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    FMLog fMLog3 = this.fmLog;
                    String str4 = this.logTag;
                    fMLog3.warn(str4, String.valueOf("卡上应用迁出") + "，消息处理器出现异常：" + Util4Java.getExceptionInfo(e));
                }
                CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
                cardBusinessBasic2.throwExceptionAndClose(String.valueOf("卡上应用迁出") + "，消息处理器出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr3 = null;
            }
        }
        if (enumCardAppType != null) {
            ITag createTag2 = messageHandler.createTag((byte) 14);
            createTag2.addValue(enumCardAppType.getId());
            createMessage.addTag(createTag2);
        }
        if (str != null && str.length() > 0) {
            ITag createTag3 = messageHandler.createTag((byte) Constants.TagName.DEVICE_MODEL);
            createTag3.addValue(str);
            createMessage.addTag(createTag3);
        }
        if (bArr2 != null && bArr2.length > 0) {
            ITag createTag4 = messageHandler.createTag((byte) Constants.TagName.SEID);
            createTag4.addValue(bArr2);
            createMessage.addTag(createTag4);
        }
        ITag createTag5 = messageHandler.createTag((byte) Constants.TagName.APP_MANAGE_OPEATE_TYPE);
        createTag5.addValue(EnumAppManageOperateType.APP_MOVE.getId());
        createMessage.addTag(createTag5);
        bArr3 = createMessage.toBytes();
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.APP_MANAGER_VER2);
        if (server4Business == null) {
            if (this.fmLog != null) {
                FMLog fMLog4 = this.fmLog;
                String str5 = this.logTag;
                fMLog4.warn(str5, String.valueOf("卡上应用迁出") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("卡上应用迁出") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        byte[] commandHandle = commandHandle(bArr3, "卡上应用迁出", messageHandler, server4Business);
        if (Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, Arrays.copyOf(commandHandle, 2))) {
            if (commandHandle.length == 2) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, "应用迁移平台处理完成，未返回迁移认证码");
                }
                return null;
            }
            try {
                ITag tag4Id = messageHandler.createMessage(Constants.TradeCode.APP_MANAGER_VER2, Arrays.copyOfRange(commandHandle, 2, commandHandle.length)).getTag4Id(-76);
                if (tag4Id != null) {
                    return tag4Id.getBytesVal();
                }
            } catch (FMCommunicationMessageException e2) {
                if (this.fmLog != null) {
                    FMLog fMLog5 = this.fmLog;
                    String str6 = this.logTag;
                    fMLog5.warn(str6, String.valueOf("卡上应用迁出") + "，解析平台响应数据的附加数据域出现异常：" + Util4Java.getExceptionInfo(e2));
                }
                CardBusinessBasic cardBusinessBasic3 = this.cardBusinessBasic;
                cardBusinessBasic3.throwExceptionAndClose(String.valueOf("卡上应用迁出") + "，解析平台响应数据的附加数据域出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            }
        } else if (commandHandle.length <= 2) {
            if (this.fmLog != null) {
                FMLog fMLog6 = this.fmLog;
                String str7 = this.logTag;
                fMLog6.warn(str7, String.valueOf("卡上应用迁出") + "，空中发卡平台处理失败，平台响应：" + FM_Bytes.bytesToHexString(commandHandle));
            }
            CardBusinessBasic cardBusinessBasic4 = this.cardBusinessBasic;
            cardBusinessBasic4.throwExceptionAndClose(String.valueOf("卡上应用迁出") + "，空中发卡失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(commandHandle)), false);
        } else {
            try {
                ITag tag4Id2 = messageHandler.createMessage(Constants.TradeCode.APP_MANAGER_VER2, Arrays.copyOfRange(commandHandle, 2, commandHandle.length)).getTag4Id(-76);
                if (tag4Id2 != null) {
                    this.response4BusinessFinish = tag4Id2.getBytesVal();
                    FMLog fMLog7 = this.fmLog;
                    String str8 = this.logTag;
                    fMLog7.warn(str8, String.valueOf("卡上应用迁出") + "发卡失败，第三方平台异常," + new String(this.response4BusinessFinish));
                }
            } catch (FMCommunicationMessageException e3) {
                if (this.fmLog != null) {
                    FMLog fMLog8 = this.fmLog;
                    String str9 = this.logTag;
                    fMLog8.warn(str9, String.valueOf("卡上应用迁出") + "，平台处理失败，解析第三方异常数据异常：" + Util4Java.getExceptionInfo(e3));
                }
                CardBusinessBasic cardBusinessBasic5 = this.cardBusinessBasic;
                cardBusinessBasic5.throwExceptionAndClose(String.valueOf("卡上应用迁出") + "，平台处理失败，解析第三方异常数据异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            }
        }
        return null;
    }

    public boolean personlization(String str) throws BusinessException {
        byte[] bArr;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "卡上应用个人化（运营商）...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str2 = this.logTag;
                fMLog.warn(str2, String.valueOf("卡上应用个人化（运营商）") + "时，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("卡上应用个人化（运营商）") + "时，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str3 = this.logTag;
                fMLog2.warn(str3, String.valueOf("卡上应用个人化（运营商）") + "时，消息处理器为空，消息配置文件加载失败");
            }
            throw new BusinessException(String.valueOf("卡上应用个人化（运营商）") + "时，加载TAG定义配置文件失败", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.PERSONLIZATION);
        if (str != null) {
            try {
                if (str.length() > 0) {
                    ITag createTag = messageHandler.createTag((byte) Constants.TagName.SIM_SEID);
                    createTag.addValue(str);
                    createMessage.addTag(createTag);
                }
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    FMLog fMLog3 = this.fmLog;
                    String str4 = this.logTag;
                    fMLog3.warn(str4, String.valueOf("卡上应用个人化（运营商）") + "时，消息处理器出现异常：" + Util4Java.getExceptionInfo(e));
                }
                CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
                cardBusinessBasic2.throwExceptionAndClose(String.valueOf("卡上应用个人化（运营商）") + "时，消息处理器出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr = null;
            }
        }
        bArr = createMessage.toBytes();
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.APP_MANAGER);
        if (server4Business == null) {
            if (this.fmLog != null) {
                FMLog fMLog4 = this.fmLog;
                String str5 = this.logTag;
                fMLog4.warn(str5, String.valueOf("卡上应用个人化（运营商）") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("卡上应用个人化（运营商）") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        } else if (FM_Bytes.isEnd9000(commandHandle(bArr, "卡上应用个人化（运营商）", messageHandler, server4Business))) {
            return true;
        } else {
            return false;
        }
    }

    public int setCardBusinessStatus(EnumCardBusinessOpType enumCardBusinessOpType, String str, String str2, int i, byte[] bArr, byte[] bArr2, String str3) throws BusinessException {
        byte[] bArr3;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "业务订购/退订...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str4 = this.logTag;
                fMLog.warn(str4, String.valueOf("业务订购/退订") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("业务订购/退订") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str5 = this.logTag;
                fMLog2.warn(str5, String.valueOf("业务订购/退订") + "，消息处理器为空");
            }
            throw new BusinessException(String.valueOf("业务订购/退订") + "，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else if (enumCardBusinessOpType == null) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str6 = this.logTag;
                fMLog3.warn(str6, String.valueOf("业务订购/退订") + "，传入的操作类型无效");
            }
            throw new BusinessException(String.valueOf("业务订购/退订") + "，传入的操作类型无效", BusinessException.ErrorMessage.local_business_para_error);
        } else if (str == null || str.length() < 1) {
            if (this.fmLog != null) {
                FMLog fMLog4 = this.fmLog;
                String str7 = this.logTag;
                fMLog4.warn(str7, String.valueOf("业务订购/退订") + "，传入的手机号码无效");
            }
            throw new BusinessException(String.valueOf("业务订购/退订") + "，传入的手机号码无效", BusinessException.ErrorMessage.local_business_para_error);
        } else if (str2 == null || str2.length() < 1) {
            if (this.fmLog != null) {
                FMLog fMLog5 = this.fmLog;
                String str8 = this.logTag;
                fMLog5.warn(str8, String.valueOf("业务订购/退订") + "，传入的卡上应用序列号无效");
            }
            throw new BusinessException(String.valueOf("业务订购/退订") + "，传入的卡上应用序列号无效", BusinessException.ErrorMessage.local_business_para_error);
        } else {
            String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.BUSINESS_ORDER_SETTING);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    FMLog fMLog6 = this.fmLog;
                    String str9 = this.logTag;
                    fMLog6.warn(str9, String.valueOf("业务订购/退订") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("业务订购/退订") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("业务订购/退订", server4Business);
            IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.BUSINESS_ORDER_SETTING);
            try {
                ITag createTag = messageHandler.createTag((byte) Constants.TagName.BUSINESS_ORDER_OP_TYPE);
                createTag.addValue((int) (byte) enumCardBusinessOpType.getId());
                createMessage.addTag(createTag);
                ITag createTag2 = messageHandler.createTag((byte) 5);
                createTag2.addValue(str);
                createMessage.addTag(createTag2);
                ITag createTag3 = messageHandler.createTag((byte) 15);
                createTag3.addValue(str2);
                createMessage.addTag(createTag3);
                if (i > 0) {
                    ITag createTag4 = messageHandler.createTag((byte) Constants.TagName.CARD_APP_BLANCE);
                    createTag4.addValue(i);
                    createMessage.addTag(createTag4);
                }
                if (bArr != null && bArr.length > 1) {
                    ITag createTag5 = messageHandler.createTag((byte) 22);
                    createTag5.addValue(bArr);
                    createMessage.addTag(createTag5);
                }
                if (bArr2 != null && bArr2.length > 1) {
                    ITag createTag6 = messageHandler.createTag((byte) Constants.TagName.CARD_APP_VERSION);
                    createTag6.addValue(bArr2);
                    createMessage.addTag(createTag6);
                }
                if (str3 != null && str3.length() > 1) {
                    ITag createTag7 = messageHandler.createTag((byte) 88);
                    createTag7.addValue(str3);
                    createMessage.addTag(createTag7);
                }
                bArr3 = createMessage.toBytes();
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    FMLog fMLog7 = this.fmLog;
                    String str10 = this.logTag;
                    fMLog7.warn(str10, String.valueOf("业务订购/退订") + ",构造平台请求数据异常/n" + Util4Java.getExceptionInfo(e));
                }
                CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
                cardBusinessBasic2.throwExceptionAndClose(String.valueOf("业务订购/退订") + ",构造平台请求数据失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr3 = null;
            }
            byte[] commandHandle = commandHandle(bArr3, "业务订购/退订", messageHandler, server4Business);
            this.cardBusinessBasic.businessFinish(true);
            if (FM_Bytes.isEnd9000(commandHandle)) {
                return 0;
            }
            if (this.fmLog != null) {
                FMLog fMLog8 = this.fmLog;
                String str11 = this.logTag;
                fMLog8.warn(str11, String.valueOf("业务订购/退订") + "处理失败，平台响应：" + FM_Bytes.bytesToHexString(commandHandle));
            }
            return FM_Bytes.bytesToInt(commandHandle);
        }
    }

    public int setCardBusinessStatusVer2(EnumCardBusinessOpType enumCardBusinessOpType, String str, String str2, int i, byte[] bArr, byte[] bArr2, String str3, byte[] bArr3) throws BusinessException {
        byte[] bArr4;
        String str4 = str;
        String str5 = str2;
        int i2 = i;
        byte[] bArr5 = bArr;
        byte[] bArr6 = bArr2;
        String str6 = str3;
        byte[] bArr7 = bArr3;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "业务订购/退订...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str7 = this.logTag;
                fMLog.warn(str7, String.valueOf("业务订购/退订") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("业务订购/退订") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str8 = this.logTag;
                fMLog2.warn(str8, String.valueOf("业务订购/退订") + "，消息处理器为空");
            }
            throw new BusinessException(String.valueOf("业务订购/退订") + "，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else if (enumCardBusinessOpType == null) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str9 = this.logTag;
                fMLog3.warn(str9, String.valueOf("业务订购/退订") + "，传入的操作类型无效");
            }
            throw new BusinessException(String.valueOf("业务订购/退订") + "，传入的操作类型无效", BusinessException.ErrorMessage.local_business_para_error);
        } else if (str4 == null || str.length() < 1) {
            if (this.fmLog != null) {
                FMLog fMLog4 = this.fmLog;
                String str10 = this.logTag;
                fMLog4.warn(str10, String.valueOf("业务订购/退订") + "，传入的手机号码无效");
            }
            throw new BusinessException(String.valueOf("业务订购/退订") + "，传入的手机号码无效", BusinessException.ErrorMessage.local_business_para_error);
        } else if (str5 == null || str2.length() < 1) {
            if (this.fmLog != null) {
                FMLog fMLog5 = this.fmLog;
                String str11 = this.logTag;
                fMLog5.warn(str11, String.valueOf("业务订购/退订") + "，传入的卡上应用序列号无效");
            }
            throw new BusinessException(String.valueOf("业务订购/退订") + "，传入的卡上应用序列号无效", BusinessException.ErrorMessage.local_business_para_error);
        } else {
            String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.BUSINESS_ORDER_SETTING);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    FMLog fMLog6 = this.fmLog;
                    String str12 = this.logTag;
                    fMLog6.warn(str12, String.valueOf("业务订购/退订") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("业务订购/退订") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("业务订购/退订", server4Business);
            IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.BUSINESS_ORDER_SETTING_VER2);
            try {
                ITag createTag = messageHandler.createTag((byte) Constants.TagName.BUSINESS_ORDER_OP_TYPE);
                createTag.addValue((int) (byte) enumCardBusinessOpType.getId());
                createMessage.addTag(createTag);
                ITag createTag2 = messageHandler.createTag((byte) 5);
                createTag2.addValue(str4);
                createMessage.addTag(createTag2);
                ITag createTag3 = messageHandler.createTag((byte) 15);
                createTag3.addValue(str5);
                createMessage.addTag(createTag3);
                if (i2 > 0) {
                    ITag createTag4 = messageHandler.createTag((byte) Constants.TagName.CARD_APP_BLANCE);
                    createTag4.addValue(i2);
                    createMessage.addTag(createTag4);
                }
                if (bArr5 != null && bArr5.length > 1) {
                    ITag createTag5 = messageHandler.createTag((byte) 22);
                    createTag5.addValue(bArr5);
                    createMessage.addTag(createTag5);
                }
                if (bArr6 != null && bArr6.length > 1) {
                    ITag createTag6 = messageHandler.createTag((byte) Constants.TagName.CARD_APP_VERSION);
                    createTag6.addValue(bArr6);
                    createMessage.addTag(createTag6);
                }
                if (str6 != null && str3.length() > 1) {
                    ITag createTag7 = messageHandler.createTag((byte) 88);
                    createTag7.addValue(str6);
                    createMessage.addTag(createTag7);
                }
                if (bArr7 != null && bArr7.length > 0) {
                    ITag createTag8 = messageHandler.createTag((byte) Constants.TagName.ACTIVITY_INFO);
                    createTag8.addValue(bArr7);
                    createMessage.addTag(createTag8);
                }
                bArr4 = createMessage.toBytes();
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    FMLog fMLog7 = this.fmLog;
                    String str13 = this.logTag;
                    fMLog7.warn(str13, "用户注册,构造平台请求数据异常/n" + Util4Java.getExceptionInfo(e));
                }
                CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
                cardBusinessBasic2.throwExceptionAndClose(String.valueOf("业务订购/退订") + ",构造平台请求数据失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr4 = null;
            }
            byte[] commandHandle = commandHandle(bArr4, "业务订购/退订", messageHandler, server4Business);
            this.cardBusinessBasic.businessFinish(true);
            if (FM_Bytes.isEnd9000(commandHandle)) {
                return 0;
            }
            if (this.fmLog != null) {
                FMLog fMLog8 = this.fmLog;
                String str14 = this.logTag;
                fMLog8.warn(str14, String.valueOf("业务订购/退订") + "处理失败，平台响应：" + FM_Bytes.bytesToHexString(commandHandle));
            }
            return FM_Bytes.bytesToInt(commandHandle);
        }
    }

    public boolean writeTicket(String str, byte[] bArr) throws BusinessException {
        String str2;
        byte[] bArr2;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "申通写票...");
        }
        if (str == null || str.length() < 1) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("申通写票") + "时，用户信息为空");
            }
            throw new BusinessException(String.valueOf("申通写票") + "时，传入的参数异常", BusinessException.ErrorMessage.local_business_para_error);
        } else if (bArr == null || bArr.length < 1) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("申通写票") + "时，票根信息为空");
            }
            throw new BusinessException(String.valueOf("申通写票") + "时，传入的参数异常", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("申通写票") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("申通写票") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("申通写票") + "，消息处理器为空，消息配置文件加载失败");
                }
                throw new BusinessException(String.valueOf("申通写票") + "，加载TAG定义配置文件失败", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            Configration configration = this.cardBusinessBasic.getConfigration();
            if (configration != null) {
                str2 = configration.getCompanyCode();
            } else {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("申通写票") + "时，Configration 为空");
                }
                str2 = null;
            }
            if (str2 == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("申通写票") + "时，用户所属商户为空");
                }
                throw new BusinessException(String.valueOf("申通写票") + "时，用户所属商户为空", BusinessException.ErrorMessage.local_business_para_error);
            }
            IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.TICKET_MANAGER);
            try {
                ITag createTag = messageHandler.createTag((byte) Constants.TagName.BUSINESS_ORDER_OP_TYPE);
                createTag.addValue(93);
                createMessage.addTag(createTag);
                ITag createTag2 = messageHandler.createTag((byte) 2);
                createTag2.addValue(str);
                createMessage.addTag(createTag2);
                ITag createTag3 = messageHandler.createTag((byte) Constants.TagName.COMPANY_CODE);
                createTag3.addValue(str2);
                createMessage.addTag(createTag3);
                ITag createTag4 = messageHandler.createTag((byte) 17);
                createTag4.addValue(bArr);
                createMessage.addTag(createTag4);
                bArr2 = createMessage.toBytes();
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("申通写票") + "，消息处理器出现异常：" + Util4Java.getExceptionInfo(e));
                }
                this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("申通写票") + "，消息处理器出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr2 = null;
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.TICKET_MANAGER);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("申通写票") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("申通写票") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            byte[] commandHandle = commandHandle(bArr2, "申通写票", messageHandler, server4Business);
            if (FM_Bytes.isEnd9000(commandHandle)) {
                if (this.fmLog != null) {
                    this.fmLog.info(this.logTag, String.valueOf("申通写票") + "时，处理完成");
                }
                return true;
            }
            if (this.fmLog != null) {
                this.fmLog.info(this.logTag, String.valueOf("申通写票") + "时，处理失败,执行结果" + FM_Bytes.bytesToHexString(commandHandle));
            }
            return false;
        }
    }

    public int orderExce(byte[] bArr, byte[] bArr2) throws BusinessException {
        String str;
        byte[] bArr3;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "主订单执行...");
        }
        if (bArr == null || bArr.length < 1) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("主订单执行") + "时，主订单编号为空");
            }
            throw new BusinessException(String.valueOf("主订单执行") + "时，传入的参数异常", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("主订单执行") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("主订单执行") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("主订单执行") + "，消息处理器为空，消息配置文件加载失败");
                }
                throw new BusinessException(String.valueOf("主订单执行") + "，加载TAG定义配置文件失败", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            Configration configration = this.cardBusinessBasic.getConfigration();
            if (configration != null) {
                str = configration.getCompanyCode();
            } else {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("主订单执行") + "时，Configration 为空");
                }
                str = null;
            }
            if (str == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("主订单执行") + "时，用户所属商户为空");
                }
                throw new BusinessException(String.valueOf("主订单执行") + "时，用户所属商户为空", BusinessException.ErrorMessage.local_business_para_error);
            }
            IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.MAIN_ORDER_EXEC);
            try {
                ITag createTag = messageHandler.createTag((byte) Constants.TagName.MAIN_ORDER_ID);
                createTag.addValue(bArr);
                createMessage.addTag(createTag);
                if (bArr2 != null && bArr2.length > 0) {
                    ITag createTag2 = messageHandler.createTag((byte) Constants.TagName.PATCH_DATA);
                    createTag2.addValue(bArr2);
                    createMessage.addTag(createTag2);
                }
                bArr3 = createMessage.toBytes();
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("主订单执行") + "，消息处理器出现异常：" + Util4Java.getExceptionInfo(e));
                }
                this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("主订单执行") + "，消息处理器出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr3 = null;
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.MAIN_ORDER_EXEC);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("主订单执行") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("主订单执行") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            byte[] commandHandle = commandHandle(bArr3, "主订单执行", messageHandler, server4Business);
            if (FM_Bytes.isEnd9000(commandHandle)) {
                if (this.fmLog != null) {
                    this.fmLog.info(this.logTag, String.valueOf("主订单执行") + "时，处理完成");
                }
                return 0;
            } else if (this.fmLog == null) {
                return -1;
            } else {
                this.fmLog.info(this.logTag, String.valueOf("主订单执行") + "时，处理失败,执行结果" + FM_Bytes.bytesToHexString(commandHandle));
                return -1;
            }
        }
    }
}
