package cn.com.fmsh.nfcos.client.service.xm;

import android.app.Service;
import android.content.Intent;
import android.content.pm.Signature;
import android.nfc.Tag;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import cn.com.fmsh.nfcos.client.service.business.NFCApduHandler;
import cn.com.fmsh.nfcos.client.service.business.OpenMobileApduHandler;
import cn.com.fmsh.nfcos.client.service.business.STTicketInfo;
import cn.com.fmsh.nfcos.client.service.business.STTicketRecord;
import cn.com.fmsh.nfcos.client.service.business.SeServiceHandler;
import cn.com.fmsh.nfcos.client.service.business.ShengtongCardAppManager;
import cn.com.fmsh.nfcos.client.service.business.SocketExceptionHandler4Mi;
import cn.com.fmsh.nfcos.client.service.constants.Constants;
import cn.com.fmsh.nfcos.client.service.enums.BroadCastType;
import cn.com.fmsh.nfcos.client.service.localdata.DBHelper;
import cn.com.fmsh.nfcos.client.service.log.FMLog4Android;
import cn.com.fmsh.nfcos.client.service.xm.CardAppManager;
import cn.com.fmsh.tsm.business.BusinessManager;
import cn.com.fmsh.tsm.business.BusinessManagerFactory;
import cn.com.fmsh.tsm.business.IssuerProcessHandler;
import cn.com.fmsh.tsm.business.bean.Activity;
import cn.com.fmsh.tsm.business.bean.BusinessOrder;
import cn.com.fmsh.tsm.business.bean.CardAppInfo;
import cn.com.fmsh.tsm.business.bean.CardAppRecord;
import cn.com.fmsh.tsm.business.bean.LoginInfo;
import cn.com.fmsh.tsm.business.bean.MainOrder;
import cn.com.fmsh.tsm.business.bean.PayOrder;
import cn.com.fmsh.tsm.business.bean.PreDepositInfo;
import cn.com.fmsh.tsm.business.bean.TicketOperateResult;
import cn.com.fmsh.tsm.business.bean.UserInfo;
import cn.com.fmsh.tsm.business.bean.VersionInfo;
import cn.com.fmsh.tsm.business.card.CardTools;
import cn.com.fmsh.tsm.business.constants.Constants;
import cn.com.fmsh.tsm.business.core.ErrorCodeHandler;
import cn.com.fmsh.tsm.business.enums.EnumAppManageOperateType;
import cn.com.fmsh.tsm.business.enums.EnumBusinessOrderType;
import cn.com.fmsh.tsm.business.enums.EnumCardAppStatus;
import cn.com.fmsh.tsm.business.enums.EnumCardAppType;
import cn.com.fmsh.tsm.business.enums.EnumCardIoType;
import cn.com.fmsh.tsm.business.enums.EnumIssueProcess;
import cn.com.fmsh.tsm.business.enums.EnumOrderStatus;
import cn.com.fmsh.tsm.business.enums.EnumOrderType;
import cn.com.fmsh.tsm.business.enums.EnumTerminalOpType;
import cn.com.fmsh.tsm.business.exception.BusinessException;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.Level;
import cn.com.fmsh.util.log.LogFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.simalliance.openmobileapi.SEService;

public class NfcosService4xm extends Service implements SeServiceHandler {
    static final Signature[] SIGNATURE_MI_WALLET = {new Signature(new byte[]{48, -126, 4, Constants.TagName.ELECTRONIC_LIST, Constants.TagName.PRODUCT_INFO, -10, TarConstants.U, -39})};
    private final int WAIT_OP_SERVICE_COUNT_MAK = 50;
    private final int WAIT_OP_SERVICE_TIME = 100;
    private final String boradcastPermission = "cn.com.fmsh.nfcos.client.service.zhuaweite.permission.notice";
    private final String broadcastActionName = "cn.com.fmsh.nfcos.client.service.huawei.broadcast.action";
    private final int databaseVersion = 1;
    private DBHelper db;
    /* access modifiers changed from: private */
    public ErrorCodeHandler errorCodeHandler;
    /* access modifiers changed from: private */
    public FMLog fmLog = null;
    /* access modifiers changed from: private */
    public String globalUserName = "47906069060842937567";
    /* access modifiers changed from: private */
    public volatile int handlerType = -1;
    /* access modifiers changed from: private */
    public final String logTag = NfcosService4xm.class.getName();
    /* access modifiers changed from: private */
    public BusinessManager manager = null;
    private final String messageKey = "cn.com.fmsh.nfcos.client.service.huawei.broadcast.message.key";
    /* access modifiers changed from: private */
    public OpenMobileApduHandler openMobileApduHandler;
    /* access modifiers changed from: private */
    public volatile boolean openMobileIsRun = false;
    /* access modifiers changed from: private */
    public SEService seService;
    /* access modifiers changed from: private */
    public byte[] securityCode;
    /* access modifiers changed from: private */
    public ShengtongCardAppManager shentongCardManager = null;
    /* access modifiers changed from: private */
    public volatile Map<String, byte[]> unsolvedOrderAndCardNo;

    private class SEServiceCallBack implements SEService.CallBack {
        private SEServiceCallBack() {
        }

        /* synthetic */ SEServiceCallBack(NfcosService4xm nfcosService4xm, SEServiceCallBack sEServiceCallBack) {
            this();
        }

        public void serviceConnected(SEService sEService) {
            Log.d(NfcosService4xm.this.logTag, "open mobile service connected");
            NfcosService4xm.this.openMobileIsRun = true;
        }
    }

    public void onCreate() {
        this.fmLog = new FMLog4Android();
        this.fmLog.setShowLogLevel(Level.DEBUG);
        LogFactory.getInstance().setLog(this.fmLog);
        this.fmLog.info(this.logTag, "nfcos client service create...");
        try {
            this.seService = new SEService(this, new SEServiceCallBack(this, (SEServiceCallBack) null));
            initDatabase();
            this.db = new DBHelper(getBaseContext(), 1);
            byte[] bArr = new byte[32];
            new Random().nextBytes(bArr);
            this.unsolvedOrderAndCardNo = new HashMap();
            this.manager = BusinessManagerFactory.getBusinessManager();
            this.manager.registerLocalDataHandler(this.db);
            this.manager.getCardAppInstall().registerIssuerProcessHandler(new IssuerProcessHandlerImpl());
            this.manager.setTerminalNumber(bArr);
            this.manager.setMobileInfo(new byte[]{2, 1, (byte) EnumTerminalOpType.ANDROID.getId()});
            this.shentongCardManager = ShengtongCardAppManager.getInstance();
            this.shentongCardManager.setDbhelper(this.db);
            this.errorCodeHandler = this.manager.getErrorCodeHandler();
        } catch (SecurityException e) {
            Log.e(this.logTag, "Binding not allowed, uses-permission org.simalliance.openmobileapi.SMARTCARD", e);
            Log.e(this.logTag, Util4Java.getExceptionInfo(e));
        } catch (Exception e2) {
            String str = this.logTag;
            Log.e(str, "Exception: " + Util4Java.getExceptionInfo(e2));
        }
    }

    public SEService seServiceReopen() {
        try {
            if (this.seService == null || !this.seService.isConnected()) {
                this.seService = new SEService(this, new SEServiceCallBack(this, (SEServiceCallBack) null));
            }
            return this.seService;
        } catch (SecurityException unused) {
            Log.e(this.logTag, "Binding not allowed, uses-permission org.simalliance.openmobileapi.SMARTCARD?");
            return null;
        } catch (Exception e) {
            String str = this.logTag;
            Log.e(str, "Exception: " + e.getMessage());
            return null;
        }
    }

    public void sendBroadCast(BroadCastParameter broadCastParameter) {
        Intent intent = new Intent("cn.com.fmsh.nfcos.client.service.huawei.broadcast.action");
        intent.putExtra("cn.com.fmsh.nfcos.client.service.huawei.broadcast.message.key", broadCastParameter);
        sendBroadcast(intent, "cn.com.fmsh.nfcos.client.service.zhuaweite.permission.notice");
    }

    public void serviceConnected(SEService sEService) {
        this.fmLog.info(this.logTag, "seviceConnected()");
    }

    public void onDestroy() {
        super.onDestroy();
        this.fmLog.info(this.logTag, "service onDestroy..........");
        this.openMobileApduHandler = null;
        this.manager = null;
        this.shentongCardManager = null;
        if (this.seService != null && this.seService.isConnected()) {
            this.seService.shutdown();
        }
    }

    public boolean onUnbind(Intent intent) {
        this.fmLog.info(this.logTag, "service onUnbind..........");
        return super.onUnbind(intent);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:25|32|40|41|42|43|44|45|46|63) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:17|18|19|20|62) */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:54|55|56|57|58|59) */
    /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006a */
    /* JADX WARNING: Missing exception handler attribute for start block: B:45:0x00a8 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:57:0x00d1 */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:40:0x008b=Splitter:B:40:0x008b, B:49:0x00ae=Splitter:B:49:0x00ae} */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:45:0x00a8=Splitter:B:45:0x00a8, B:19:0x006a=Splitter:B:19:0x006a} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initDatabase() {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "/data/data/"
            r0.<init>(r1)
            java.lang.String r1 = r6.getPackageName()
            r0.append(r1)
            java.lang.String r1 = "/databases/"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = java.lang.String.valueOf(r0)
            r1.<init>(r2)
            java.lang.String r2 = "fmsh_localData.db"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.io.File r2 = new java.io.File
            r2.<init>(r1)
            boolean r2 = r2.exists()
            if (r2 == 0) goto L_0x003c
            java.lang.String r0 = r6.logTag
            java.lang.String r1 = "database file exists"
            android.util.Log.d(r0, r1)
            return
        L_0x003c:
            java.io.File r2 = new java.io.File
            r2.<init>(r0)
            boolean r0 = r2.exists()
            if (r0 != 0) goto L_0x004a
            r2.mkdir()
        L_0x004a:
            android.content.res.AssetManager r0 = r6.getAssets()
            r2 = 0
            java.lang.String r3 = "fmsh_localData.db"
            java.io.InputStream r0 = r0.open(r3)     // Catch:{ FileNotFoundException -> 0x00ac, IOException -> 0x0089, all -> 0x0086 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0082, IOException -> 0x007e, all -> 0x007a }
            r3.<init>(r1)     // Catch:{ FileNotFoundException -> 0x0082, IOException -> 0x007e, all -> 0x007a }
            r1 = 2048(0x800, float:2.87E-42)
            byte[] r1 = new byte[r1]     // Catch:{ FileNotFoundException -> 0x0078, IOException -> 0x0076, all -> 0x0074 }
        L_0x005e:
            int r2 = r0.read(r1)     // Catch:{ FileNotFoundException -> 0x0078, IOException -> 0x0076, all -> 0x0074 }
            if (r2 > 0) goto L_0x006f
            r3.flush()     // Catch:{ IOException -> 0x006a }
            r3.close()     // Catch:{ IOException -> 0x006a }
        L_0x006a:
            r0.close()     // Catch:{ IOException -> 0x00c9 }
            goto L_0x00c9
        L_0x006f:
            r4 = 0
            r3.write(r1, r4, r2)     // Catch:{ FileNotFoundException -> 0x0078, IOException -> 0x0076, all -> 0x0074 }
            goto L_0x005e
        L_0x0074:
            r1 = move-exception
            goto L_0x007c
        L_0x0076:
            r1 = move-exception
            goto L_0x0080
        L_0x0078:
            r1 = move-exception
            goto L_0x0084
        L_0x007a:
            r1 = move-exception
            r3 = r2
        L_0x007c:
            r2 = r0
            goto L_0x00cb
        L_0x007e:
            r1 = move-exception
            r3 = r2
        L_0x0080:
            r2 = r0
            goto L_0x008b
        L_0x0082:
            r1 = move-exception
            r3 = r2
        L_0x0084:
            r2 = r0
            goto L_0x00ae
        L_0x0086:
            r1 = move-exception
            r3 = r2
            goto L_0x00cb
        L_0x0089:
            r1 = move-exception
            r3 = r2
        L_0x008b:
            java.lang.String r0 = r6.logTag     // Catch:{ all -> 0x00ca }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ca }
            java.lang.String r5 = "Exception: "
            r4.<init>(r5)     // Catch:{ all -> 0x00ca }
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x00ca }
            r4.append(r1)     // Catch:{ all -> 0x00ca }
            java.lang.String r1 = r4.toString()     // Catch:{ all -> 0x00ca }
            android.util.Log.e(r0, r1)     // Catch:{ all -> 0x00ca }
            r3.flush()     // Catch:{ IOException -> 0x00a8 }
        L_0x00a5:
            r3.close()     // Catch:{ IOException -> 0x00a8 }
        L_0x00a8:
            r2.close()     // Catch:{ IOException -> 0x00c9 }
            goto L_0x00c9
        L_0x00ac:
            r1 = move-exception
            r3 = r2
        L_0x00ae:
            java.lang.String r0 = r6.logTag     // Catch:{ all -> 0x00ca }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ca }
            java.lang.String r5 = "Exception: "
            r4.<init>(r5)     // Catch:{ all -> 0x00ca }
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x00ca }
            r4.append(r1)     // Catch:{ all -> 0x00ca }
            java.lang.String r1 = r4.toString()     // Catch:{ all -> 0x00ca }
            android.util.Log.e(r0, r1)     // Catch:{ all -> 0x00ca }
            r3.flush()     // Catch:{ IOException -> 0x00a8 }
            goto L_0x00a5
        L_0x00c9:
            return
        L_0x00ca:
            r1 = move-exception
        L_0x00cb:
            r3.flush()     // Catch:{ IOException -> 0x00d1 }
            r3.close()     // Catch:{ IOException -> 0x00d1 }
        L_0x00d1:
            r2.close()     // Catch:{ IOException -> 0x00d4 }
        L_0x00d4:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.nfcos.client.service.xm.NfcosService4xm.initDatabase():void");
    }

    public IBinder onBind(Intent intent) {
        return new AppManger();
    }

    public class AppManger extends CardAppManager.Stub {
        public AppManger() {
        }

        public int switchMode2OMA(int i, int i2) throws RemoteException {
            byte[] bArr;
            if (NfcosService4xm.this.fmLog != null) {
                NfcosService4xm.this.fmLog.debug(NfcosService4xm.this.logTag, "switch Mode 2 OMA....");
            }
            if (i2 == 1) {
                bArr = Constants.appAid.STPC_AID_EXT;
            } else {
                bArr = i2 == 2 ? Constants.appAid.LINGNAN_PASS_AID : null;
            }
            if (NfcosService4xm.this.openMobileApduHandler == null) {
                NfcosService4xm.this.openMobileApduHandler = new OpenMobileApduHandler(NfcosService4xm.this.seService, NfcosService4xm.this, bArr, i);
            }
            if (NfcosService4xm.this.fmLog != null) {
                NfcosService4xm.this.fmLog.debug(NfcosService4xm.this.logTag, "openMobileApduHandler intance sucess");
            }
            if (NfcosService4xm.this.handlerType != 0) {
                NfcosService4xm.this.handlerType = 0;
            } else if (NfcosService4xm.this.openMobileApduHandler == null) {
                NfcosService4xm.this.fmLog.debug(NfcosService4xm.this.logTag, "openMobileApduHandler is null");
            } else if (Arrays.equals(bArr, NfcosService4xm.this.openMobileApduHandler.getLastOpenAid())) {
                NfcosService4xm.this.fmLog.debug(NfcosService4xm.this.logTag, "aid is same,not change");
                return 0;
            } else {
                NfcosService4xm.this.openMobileApduHandler.setAid(bArr);
            }
            int i3 = 0;
            while (!NfcosService4xm.this.openMobileIsRun) {
                Log.d(NfcosService4xm.this.logTag, "wait open mobile service start");
                i3++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i3 > 50) {
                    return Constants.ErrorCode.OPEN_MOBILE_SERVICE_INVAILD;
                }
            }
            NfcosService4xm.this.manager.setApduHandler(NfcosService4xm.this.openMobileApduHandler);
            NfcosService4xm.this.shentongCardManager.setApduHandler(NfcosService4xm.this.openMobileApduHandler);
            if (NfcosService4xm.this.fmLog != null) {
                NfcosService4xm.this.fmLog.debug(NfcosService4xm.this.logTag, "切换到OMA SUCESS");
            }
            return 0;
        }

        public int switchMode2NFC(Tag tag) throws RemoteException {
            if (NfcosService4xm.this.fmLog != null) {
                NfcosService4xm.this.fmLog.debug(NfcosService4xm.this.logTag, "switch Mode 2 NFC....");
            }
            NfcosService4xm.this.handlerType = 1;
            NFCApduHandler nFCApduHandler = new NFCApduHandler();
            if (nFCApduHandler.setTag(tag)) {
                NfcosService4xm.this.manager.setApduHandler(nFCApduHandler);
                return 0;
            } else if (NfcosService4xm.this.fmLog == null) {
                return Constants.ErrorCode.NFC_TAG_INVAILD;
            } else {
                NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "以NFC访问卡上应用时, APDU处理器设置TAG失败");
                return Constants.ErrorCode.NFC_TAG_INVAILD;
            }
        }

        public int doIssue(byte[] bArr, byte b, byte[] bArr2, byte[] bArr3) throws RemoteException {
            if (bArr == null || bArr.length < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "卡上应用发行时，请传入发卡订单号");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (bArr2 == null || bArr2.length < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "卡上应用发行时，请传入待发卡的SE标识");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                NfcosService4xm.this.manager.setSecurityCode(NfcosService4xm.this.securityCode);
                NfcosService4xm.this.manager.setSocketExceptionHandle(new SocketExceptionHandler4Mi());
                try {
                    if (NfcosService4xm.this.manager.getCardAppInstall().issuerVer2(bArr, 20, bArr2, (byte[]) null)) {
                        return 0;
                    }
                    return 99;
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "卡上应用发行出现异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "卡上应用发行出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            }
        }

        public int cancelIssue(int i) throws RemoteException {
            NfcosService4xm.this.manager.getCardAppInstall().cancel();
            return 0;
        }

        public int getAppIssueStatus(int i, CardAppStatus cardAppStatus) throws RemoteException {
            if (cardAppStatus == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "获取卡上应用状态时，传入的信息载体为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
            EnumCardAppType instance = EnumCardAppType.instance(i);
            if (instance == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "获取卡上应用状态时，传入卡上应用类型无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
            try {
                EnumCardAppStatus appIssuerStatus = NfcosService4xm.this.manager.getCardAppInstall().getAppIssuerStatus(instance);
                if (appIssuerStatus == null) {
                    return 99;
                }
                cardAppStatus.setStatus(appIssuerStatus.getId());
                return 0;
            } catch (BusinessException e) {
                if (NfcosService4xm.this.fmLog != null) {
                    FMLog access$2 = NfcosService4xm.this.fmLog;
                    String access$0 = NfcosService4xm.this.logTag;
                    access$2.warn(access$0, "获取卡上应用状态出现异常:" + Util4Java.getExceptionInfo(e));
                }
                if (e.getErrorMsg() != null) {
                    return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                }
                return 99;
            } catch (Exception e2) {
                if (NfcosService4xm.this.fmLog != null) {
                    FMLog access$22 = NfcosService4xm.this.fmLog;
                    String access$02 = NfcosService4xm.this.logTag;
                    access$22.warn(access$02, "获取卡上应用状态出现异常:" + Util4Java.getExceptionInfo(e2));
                }
                return 99;
            }
        }

        public int getAppIssueStatusByPlatform(int i, byte[] bArr, String str, CardAppStatus cardAppStatus) {
            if (cardAppStatus == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "获取卡上应用发行状态时，数据载体为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
            EnumCardAppType instance = EnumCardAppType.instance(i);
            if (instance == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "获取卡上应用状态时，传入卡上应用类型无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (bArr == null || bArr.length < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "获取卡上应用发行状态时，传入的eSE的标识无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (str == null || str.length() < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "获取卡上应用发行状态时，传入的终端型号规格的表示无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                try {
                    EnumCardAppStatus appIssuerStatus4Platform = NfcosService4xm.this.manager.getCardAppInstall().getAppIssuerStatus4Platform(instance, str, bArr);
                    if (appIssuerStatus4Platform == null) {
                        return 99;
                    }
                    cardAppStatus.setStatus(appIssuerStatus4Platform.getId());
                    return 0;
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "获取卡上应用发行状态出现异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "获取卡上应用发行状态出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            }
        }

        public int login(String str, String str2, LoginInfo loginInfo) throws RemoteException {
            if (str == null || str.length() < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "用户登录时，用户名为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (str2 == null || str2.length() < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "用户登录时，用户密码为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (loginInfo == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "用户登录时，登录结果信息载体为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                try {
                    LoginInfo loginVer2 = NfcosService4xm.this.manager.getCardAppTrade().loginVer2(str, str2);
                    if (loginVer2 == null) {
                        if (NfcosService4xm.this.fmLog != null) {
                            NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "用户登录时，LoginInfo为空");
                        }
                        return 99;
                    }
                    loginInfo.loginFailureCount = loginVer2.getFailureNum();
                    loginInfo.loginResult = loginVer2.getResult();
                    loginInfo.userLockTime = loginVer2.getUserLockTime();
                    if (loginVer2.getResult() != 0) {
                        return 0;
                    }
                    NfcosService4xm.this.globalUserName = str;
                    return 0;
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "用户登录异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "用户登录异常异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            }
        }

        public int queryVersion(VersionInfo versionInfo) throws RemoteException {
            if (versionInfo != null) {
                try {
                    VersionInfo queryVersion = NfcosService4xm.this.manager.getCardAppTrade().queryVersion();
                    if (queryVersion == null) {
                        if (NfcosService4xm.this.fmLog != null) {
                            NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "获取最新终端应用版本时，VersionInfo为空");
                        }
                        return 99;
                    }
                    versionInfo.version = queryVersion.getViersion();
                    versionInfo.isUpdate = queryVersion.isUpdate();
                    versionInfo.url = queryVersion.getUrl();
                    return 0;
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "获取最新终端应用版本异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "获取最新终端应用版本异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            } else if (NfcosService4xm.this.fmLog == null) {
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "获取最新终端应用版本时，获取结果载体为空");
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
        }

        public int getInfo(int i, int i2, CardAppInfo cardAppInfo) throws RemoteException {
            if (cardAppInfo == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "获取交通卡信息，传入的消息载体对象为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
            EnumCardAppType instance = EnumCardAppType.instance(i2);
            if (instance == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "获取卡基本信息时，传入卡的类型无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
            if ((i & 1) != 0) {
                try {
                    byte[] appNo = NfcosService4xm.this.manager.getCardAppTrade().getAppNo(instance);
                    if (appNo == null) {
                        cardAppInfo.cardFaceNo = "";
                    } else if (instance == EnumCardAppType.CARD_APP_TYPE_SH) {
                        cardAppInfo.cardFaceNo = CardTools.getFaceID4UID(Arrays.copyOfRange(appNo, 4, appNo.length));
                    } else {
                        cardAppInfo.cardFaceNo = CardTools.getFaceNo4uidByLnt(appNo);
                    }
                    cardAppInfo.appNo = appNo;
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "获取交通卡卡面号异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                }
            }
            if ((i & 2) != 0) {
                try {
                    cardAppInfo.balance = NfcosService4xm.this.manager.getCardAppTrade().getBalance(instance).intValue();
                } catch (BusinessException e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "获取交通卡余额出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    if (e2.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e2.getErrorMsg().getId());
                    }
                    return 99;
                }
            }
            if ((i & 4) != 0) {
                try {
                    List<CardAppRecord> cardAppRecords = NfcosService4xm.this.manager.getCardAppTrade().getCardAppRecords(instance);
                    if (cardAppRecords != null) {
                        CardAppRecord[] cardAppRecordArr = new CardAppRecord[cardAppRecords.size()];
                        for (int i3 = 0; i3 < cardAppRecords.size(); i3++) {
                            CardAppRecord cardAppRecord = cardAppRecords.get(i3);
                            if (cardAppRecord != null) {
                                CardAppRecord cardAppRecord2 = new CardAppRecord();
                                cardAppRecord2.tradeType = cardAppRecord.getTradeType().getId();
                                cardAppRecord2.tradeDate = cardAppRecord.getTradeDate();
                                cardAppRecord2.tradeTime = cardAppRecord.getTradeTime();
                                cardAppRecord2.amount = cardAppRecord.getAmount();
                                cardAppRecord2.balance = cardAppRecord.getBalance();
                                cardAppRecordArr[i3] = cardAppRecord2;
                            }
                        }
                        cardAppInfo.records = cardAppRecordArr;
                    } else {
                        Log.e(NfcosService4xm.this.logTag, "获取卡上应用交易记录失败");
                    }
                } catch (BusinessException e3) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$23 = NfcosService4xm.this.fmLog;
                        String access$03 = NfcosService4xm.this.logTag;
                        access$23.warn(access$03, "获取交通卡交易记录出现异常:" + Util4Java.getExceptionInfo(e3));
                    }
                    if (e3.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e3.getErrorMsg().getId());
                    }
                    return 99;
                }
            }
            if ((i & 8) != 0) {
                try {
                    if (NfcosService4xm.this.manager.getCardAppTrade().isLock4Consume(instance)) {
                        cardAppInfo.appLock = 1;
                    } else {
                        cardAppInfo.appLock = 0;
                    }
                } catch (BusinessException e4) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$24 = NfcosService4xm.this.fmLog;
                        String access$04 = NfcosService4xm.this.logTag;
                        access$24.warn(access$04, "获取卡上应用状态出现异常:" + Util4Java.getExceptionInfo(e4));
                    }
                    if (e4.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e4.getErrorMsg().getId());
                    }
                    return 99;
                }
            }
            if ((i & 16) != 0) {
                try {
                    cardAppInfo.moc = NfcosService4xm.this.manager.getCardAppTrade().getMOC(instance);
                } catch (BusinessException e5) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$25 = NfcosService4xm.this.fmLog;
                        String access$05 = NfcosService4xm.this.logTag;
                        access$25.warn(access$05, "获取交通卡住建部认证码失败:" + Util4Java.getExceptionInfo(e5));
                    }
                    if (e5.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e5.getErrorMsg().getId());
                    }
                    return 99;
                }
            }
            if ((i & 32) != 0) {
                try {
                    cardAppInfo.time4Validity = NfcosService4xm.this.manager.getCardAppTrade().getTime4Validity(instance);
                } catch (BusinessException e6) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$26 = NfcosService4xm.this.fmLog;
                        String access$06 = NfcosService4xm.this.logTag;
                        access$26.warn(access$06, "获取交通卡有效期失败:" + Util4Java.getExceptionInfo(e6));
                    }
                    if (e6.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e6.getErrorMsg().getId());
                    }
                    return 99;
                }
            }
            if (NfcosService4xm.this.openMobileApduHandler != null) {
                cardAppInfo.aid = NfcosService4xm.this.openMobileApduHandler.getAid();
            }
            return 0;
        }

        public int getInvoiceToken(byte[] bArr, InvoiceToken invoiceToken) throws RemoteException {
            if (invoiceToken == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "获取订单发票申领凭证时，传入的消息载体对象为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (bArr == null || bArr.length < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "获取订单发票申领凭证时，传入订单编号无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                try {
                    invoiceToken.token = NfcosService4xm.this.manager.getCardAppTrade().getInvoiceToken(bArr);
                    return 0;
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "获取发票领取凭证出现异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "获取发票领取凭证出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            }
        }

        public int logout() throws RemoteException {
            try {
                return NfcosService4xm.this.manager.getCardAppTrade().logout();
            } catch (BusinessException e) {
                if (NfcosService4xm.this.fmLog != null) {
                    FMLog access$2 = NfcosService4xm.this.fmLog;
                    String access$0 = NfcosService4xm.this.logTag;
                    access$2.warn(access$0, "上海交通卡充值异常:" + Util4Java.getExceptionInfo(e));
                }
                if (e.getErrorMsg() != null) {
                    return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                }
                return 99;
            }
        }

        public int modifyPassword(String str, String str2) throws RemoteException {
            if (str == null || str.length() < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "用户修改密码时，旧密码为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (str2 == null || str2.length() < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "用户修改密码时，新密码为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                try {
                    return NfcosService4xm.this.manager.getCardAppTrade().modifyPassword(str, str2);
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "用户修改密码异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "用户修改密码异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            }
        }

        public int modifyUserInfo(UserInfo userInfo) throws RemoteException {
            if (userInfo == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "用户信息更新时，用户信息为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (userInfo.username == null || userInfo.username.length() < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "用户信息更新时，用户名无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (userInfo.password == null || userInfo.password.length() < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "用户信息更新时，用户密码无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                UserInfo userInfo2 = new UserInfo();
                userInfo2.setUserName(userInfo.username);
                userInfo2.setPassword(userInfo.password);
                userInfo2.setUserType(2);
                try {
                    return NfcosService4xm.this.manager.getCardAppTrade().modifyUserInfo(userInfo2);
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "用户信息更新出现异常：" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "用户信息更新出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            }
        }

        public int recharge(byte[] bArr, byte[] bArr2) throws RemoteException {
            if (bArr == null || bArr.length < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "上海交通卡充值时，订单编号为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (bArr2 == null || bArr2.length < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "上海交通卡充值时，卡的应用序列号为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                try {
                    if (NfcosService4xm.this.manager.getCardAppTrade().remoteRecharge(bArr, bArr2)) {
                        return 0;
                    }
                    return 99;
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "卡上应用充值充值异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "卡上应用充值充值异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            }
        }

        public int doUnsolvedOrder(byte[] bArr) throws RemoteException {
            if (bArr != null && bArr.length >= 1) {
                try {
                    return NfcosService4xm.this.manager.getCardAppTrade().doUnsolvedOrder(bArr, (byte[]) NfcosService4xm.this.unsolvedOrderAndCardNo.get(FM_Bytes.bytesToHexString(bArr)));
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "处理未决订单异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "处理未决订单异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            } else if (NfcosService4xm.this.fmLog == null) {
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "处理未决订单时，订单编号为空");
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
        }

        public int doRefund(byte[] bArr) throws RemoteException {
            if (bArr != null && bArr.length >= 1) {
                try {
                    return NfcosService4xm.this.manager.getCardAppTrade().doRefund(bArr);
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "处理退款处理异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "处理退款处理异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            } else if (NfcosService4xm.this.fmLog == null) {
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "处理退款时，订单编号为空");
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
        }

        public int register(UserInfo userInfo) throws RemoteException {
            if (userInfo == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "用户注册时，用户信息为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (userInfo.username == null || userInfo.username.length() < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "用户注册时，用户名无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (userInfo.password == null || userInfo.password.length() < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "用户注册时，用户密码无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                UserInfo userInfo2 = new UserInfo();
                userInfo2.setUserName(userInfo.username);
                userInfo2.setPassword(userInfo.password);
                userInfo2.setUserType(2);
                try {
                    return NfcosService4xm.this.manager.getCardAppTrade().registerVer2(userInfo2);
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "用户信息注册时出现异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "用户信息注册时出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            }
        }

        public int setApp(byte[] bArr, int i, byte[] bArr2, String str, int i2) {
            EnumCardAppType instance = EnumCardAppType.instance(i);
            if (instance == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "卡上应用信息设置时，传入卡的类型无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (bArr2 == null || bArr2.length < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "卡上应用信息设置时，传入eSE标识无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (str == null || str.length() < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "卡上应用信息设置时时，传入终端型号无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                EnumAppManageOperateType instance2 = EnumAppManageOperateType.instance(i2);
                if (instance2 == null) {
                    if (NfcosService4xm.this.fmLog != null) {
                        NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "卡上应用信息设置时，传入状态值无效");
                    }
                    return Constants.ErrorCode.AIDL_PARAMETER_NULL;
                } else if (instance2 == EnumAppManageOperateType.APP_LOCK || instance2 == EnumAppManageOperateType.APP_UNLOCK) {
                    try {
                        if (!NfcosService4xm.this.manager.getCardAppInstall().setApp(bArr, instance, bArr2, str, instance2)) {
                            return 99;
                        }
                        return 0;
                    } catch (BusinessException e) {
                        if (NfcosService4xm.this.fmLog != null) {
                            FMLog access$2 = NfcosService4xm.this.fmLog;
                            String access$0 = NfcosService4xm.this.logTag;
                            access$2.warn(access$0, "卡上应用发行出现异常:" + Util4Java.getExceptionInfo(e));
                        }
                        if (e.getErrorMsg() != null) {
                            return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                        }
                        return 99;
                    }
                } else {
                    if (NfcosService4xm.this.fmLog != null) {
                        NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "卡上应用信息设置时，传入状态值无效");
                    }
                    return Constants.ErrorCode.AIDL_PARAMETER_NULL;
                }
            }
        }

        public int queryActivities(int i, List<NfcosActivity> list) {
            boolean z;
            if (list == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "活动信息查询时，传入的消息载体对象为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
            EnumCardAppType instance = EnumCardAppType.instance(i);
            if (instance == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "活动信息查询时，传入卡的类型无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
            try {
                List<Activity> queryActivities = NfcosService4xm.this.manager.getCardAppTrade().queryActivities(instance);
                if (queryActivities == null) {
                    if (NfcosService4xm.this.fmLog != null) {
                        NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "活动信息查询时，查询结果为空");
                    }
                    z = false;
                } else {
                    for (Activity activityClone : queryActivities) {
                        NfcosActivity nfcosActivity = new NfcosActivity();
                        activityClone(nfcosActivity, activityClone);
                        list.add(nfcosActivity);
                    }
                    z = true;
                }
                if (z) {
                    return 0;
                }
                return 99;
            } catch (BusinessException e) {
                if (NfcosService4xm.this.fmLog != null) {
                    FMLog access$2 = NfcosService4xm.this.fmLog;
                    String access$0 = NfcosService4xm.this.logTag;
                    access$2.warn(access$0, "活动信息查询时出现异常:" + Util4Java.getExceptionInfo(e));
                }
                if (e.getErrorMsg() != null) {
                    return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                }
                return 99;
            } catch (Exception e2) {
                if (NfcosService4xm.this.fmLog != null) {
                    FMLog access$22 = NfcosService4xm.this.fmLog;
                    String access$02 = NfcosService4xm.this.logTag;
                    access$22.warn(access$02, "活动信息查询时出现异常:" + Util4Java.getExceptionInfo(e2));
                }
                return 99;
            }
        }

        public int queryMainOrder(byte[] bArr, NfcosMainOrder nfcosMainOrder) {
            if (nfcosMainOrder == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "主订单详细信息查询时，传入的消息载体对象为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
            if (bArr != null) {
                boolean z = true;
                if (bArr.length >= 1) {
                    try {
                        MainOrder queryMainOrder = NfcosService4xm.this.manager.getCardAppTrade().queryMainOrder(bArr);
                        if (queryMainOrder == null) {
                            if (NfcosService4xm.this.fmLog != null) {
                                NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "主订单详细信息查询时，查询结果为空");
                            }
                            z = false;
                        } else {
                            mainOrderClone(nfcosMainOrder, queryMainOrder);
                        }
                        if (z) {
                            return 0;
                        }
                        return 99;
                    } catch (BusinessException e) {
                        if (NfcosService4xm.this.fmLog != null) {
                            FMLog access$2 = NfcosService4xm.this.fmLog;
                            String access$0 = NfcosService4xm.this.logTag;
                            access$2.warn(access$0, "主订单详细信息查询时出现异常:" + Util4Java.getExceptionInfo(e));
                        }
                        if (e.getErrorMsg() != null) {
                            return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                        }
                        return 99;
                    } catch (Exception e2) {
                        if (NfcosService4xm.this.fmLog != null) {
                            FMLog access$22 = NfcosService4xm.this.fmLog;
                            String access$02 = NfcosService4xm.this.logTag;
                            access$22.warn(access$02, "主订单详细信息查询时出现异常:" + Util4Java.getExceptionInfo(e2));
                        }
                        return 99;
                    }
                }
            }
            if (NfcosService4xm.this.fmLog != null) {
                NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "主订单详细信息查询时，传入订单编号无效");
            }
            return Constants.ErrorCode.AIDL_PARAMETER_NULL;
        }

        public int queryPayOrder(byte[] bArr, NfcosPayOrder nfcosPayOrder) {
            if (nfcosPayOrder == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "支付订单详细信息查询时，传入的消息载体对象为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
            if (bArr != null) {
                boolean z = true;
                if (bArr.length >= 1) {
                    try {
                        PayOrder queryPayOrder = NfcosService4xm.this.manager.getCardAppTrade().queryPayOrder(bArr);
                        if (queryPayOrder == null) {
                            if (NfcosService4xm.this.fmLog != null) {
                                NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "支付订单详细信息查询时，查询结果为空");
                            }
                            z = false;
                        } else {
                            payOrderClone(nfcosPayOrder, queryPayOrder);
                        }
                        if (z) {
                            return 0;
                        }
                        return 99;
                    } catch (BusinessException e) {
                        if (NfcosService4xm.this.fmLog != null) {
                            FMLog access$2 = NfcosService4xm.this.fmLog;
                            String access$0 = NfcosService4xm.this.logTag;
                            access$2.warn(access$0, "支付订单详细信息查询时出现异常:" + Util4Java.getExceptionInfo(e));
                        }
                        if (e.getErrorMsg() != null) {
                            return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                        }
                        return 99;
                    } catch (Exception e2) {
                        if (NfcosService4xm.this.fmLog != null) {
                            FMLog access$22 = NfcosService4xm.this.fmLog;
                            String access$02 = NfcosService4xm.this.logTag;
                            access$22.warn(access$02, "支付单详细信息查询时出现异常:" + Util4Java.getExceptionInfo(e2));
                        }
                        return 99;
                    }
                }
            }
            if (NfcosService4xm.this.fmLog != null) {
                NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "支付订单详细信息查询时，传入订单编号无效");
            }
            return Constants.ErrorCode.AIDL_PARAMETER_NULL;
        }

        public int queryBusinessOrders(int i, int i2, int i3, int[] iArr, int i4, byte[] bArr, List<NfcosBusinessOrder> list) {
            ArrayList arrayList;
            boolean z;
            int[] iArr2 = iArr;
            List<NfcosBusinessOrder> list2 = list;
            if (list2 == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "业务订单信息查询时，传入的信息载体对象为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
            EnumCardAppType instance = EnumCardAppType.instance(i3);
            if (instance == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "业务订单查询时，传入卡的类型无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
            if (iArr2 == null || iArr2.length <= 0) {
                arrayList = null;
            } else {
                ArrayList arrayList2 = new ArrayList();
                for (int orderStatus4ID : iArr2) {
                    EnumOrderStatus orderStatus4ID2 = EnumOrderStatus.getOrderStatus4ID(orderStatus4ID);
                    if (orderStatus4ID2 != null) {
                        arrayList2.add(orderStatus4ID2);
                    }
                }
                arrayList = arrayList2;
            }
            EnumBusinessOrderType instance2 = EnumBusinessOrderType.instance(i4);
            if (instance2 == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "业务订单查询时，传入业务订单类型无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (i < 0 || i2 < 0) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "业务订单信息查询时，传入查询范围无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                try {
                    List<BusinessOrder> queryBusinessOrdersVer4 = NfcosService4xm.this.manager.getCardAppTrade().queryBusinessOrdersVer4(i, i2, instance, instance2, arrayList, bArr);
                    if (queryBusinessOrdersVer4 == null) {
                        if (NfcosService4xm.this.fmLog != null) {
                            NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "业务订单信息查询时，查询结果为空");
                        }
                        z = false;
                    } else {
                        for (BusinessOrder next : queryBusinessOrdersVer4) {
                            if (next != null) {
                                NfcosBusinessOrder nfcosBusinessOrder = new NfcosBusinessOrder();
                                businessOrderClone(nfcosBusinessOrder, next);
                                list2.add(nfcosBusinessOrder);
                            }
                        }
                        z = true;
                    }
                    if (z) {
                        return 0;
                    }
                    return 99;
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null) {
                        NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "业务订单信息查询时出现异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "业务订单信息查询时出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            }
        }

        private void businessOrderClone(NfcosBusinessOrder nfcosBusinessOrder, BusinessOrder businessOrder) {
            nfcosBusinessOrder.order = businessOrder.getOrder();
            nfcosBusinessOrder.tradeDate = businessOrder.getTradeDate();
            nfcosBusinessOrder.tradeTime = businessOrder.getTradeTime();
            nfcosBusinessOrder.order = businessOrder.getOrder();
            nfcosBusinessOrder.amount = businessOrder.getAmount();
            if (businessOrder.getTradeState() == null) {
                nfcosBusinessOrder.tradeState = EnumOrderStatus.unknown.getId();
            } else {
                nfcosBusinessOrder.tradeState = businessOrder.getTradeState().getId();
            }
            if (businessOrder.getCardAppType() != EnumCardAppType.CARD_APP_TYPE_SH) {
                nfcosBusinessOrder.faceNo = CardTools.getFaceNo4uidByLnt(businessOrder.getCardNo());
            } else if (businessOrder.getCardNo() == null || businessOrder.getCardNo().length < 4) {
                nfcosBusinessOrder.faceNo = "";
            } else {
                nfcosBusinessOrder.faceNo = CardTools.getFaceID4UID(businessOrder.getCardNo());
            }
            if (businessOrder.getCardIoType() == null) {
                nfcosBusinessOrder.cardIoType = EnumCardIoType.CARD_IO_UNKNOW.getId();
            } else {
                nfcosBusinessOrder.cardIoType = businessOrder.getCardIoType().getId();
            }
            if (businessOrder.getBusinessOrderType() == null) {
                nfcosBusinessOrder.businessOrderType = EnumBusinessOrderType.UNKNOW.getId();
            } else {
                nfcosBusinessOrder.businessOrderType = businessOrder.getBusinessOrderType().getId();
            }
            nfcosBusinessOrder.invoiceStatus = businessOrder.getInvoiceStatus();
            nfcosBusinessOrder.seid = businessOrder.getSeid();
            nfcosBusinessOrder.deviceModel = businessOrder.getDeviceModel();
            nfcosBusinessOrder.mainOrder = businessOrder.getMainOrder();
        }

        private void payOrderClone(NfcosPayOrder nfcosPayOrder, PayOrder payOrder) {
            if (payOrder.getState() == null) {
                nfcosPayOrder.state = EnumOrderStatus.unknown.getId();
            } else {
                nfcosPayOrder.state = payOrder.getState().getId();
            }
            nfcosPayOrder.id = payOrder.getId();
            nfcosPayOrder.date = payOrder.getDate();
            nfcosPayOrder.time = payOrder.getTime();
            nfcosPayOrder.amount = payOrder.getAmount();
            nfcosPayOrder.thirdPayInfo = payOrder.getThirdPayInfo();
            nfcosPayOrder.channel = payOrder.getChannel();
            nfcosPayOrder.mainOrder = payOrder.getMainOrder();
        }

        public int queryBusinessOrder(byte[] bArr, NfcosBusinessOrder nfcosBusinessOrder) throws RemoteException {
            if (nfcosBusinessOrder != null) {
                try {
                    BusinessOrder queryBusinessOrder = NfcosService4xm.this.manager.getCardAppTrade().queryBusinessOrder(bArr);
                    if (queryBusinessOrder == null) {
                        if (NfcosService4xm.this.fmLog != null) {
                            NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "业务订单详细信息查询时，返回结果为空");
                        }
                        return 99;
                    }
                    nfcosBusinessOrder.tradeDate = queryBusinessOrder.getTradeDate();
                    nfcosBusinessOrder.tradeTime = queryBusinessOrder.getTradeTime();
                    nfcosBusinessOrder.order = queryBusinessOrder.getOrder();
                    nfcosBusinessOrder.amount = queryBusinessOrder.getAmount();
                    if (queryBusinessOrder.getTradeState() == null) {
                        nfcosBusinessOrder.tradeState = EnumOrderStatus.unknown.getId();
                    } else {
                        nfcosBusinessOrder.tradeState = queryBusinessOrder.getTradeState().getId();
                    }
                    if (queryBusinessOrder.getCardAppType() != EnumCardAppType.CARD_APP_TYPE_SH) {
                        nfcosBusinessOrder.faceNo = CardTools.getFaceNo4uidByLnt(queryBusinessOrder.getCardNo());
                    } else if (queryBusinessOrder.getCardNo() == null || queryBusinessOrder.getCardNo().length < 4) {
                        nfcosBusinessOrder.faceNo = "";
                    } else {
                        nfcosBusinessOrder.faceNo = CardTools.getFaceID4UID(queryBusinessOrder.getCardNo());
                    }
                    nfcosBusinessOrder.invoiceStatus = queryBusinessOrder.getInvoiceStatus();
                    if (queryBusinessOrder.getCardIoType() == null) {
                        nfcosBusinessOrder.cardIoType = EnumCardIoType.CARD_IO_UNKNOW.getId();
                    } else {
                        nfcosBusinessOrder.cardIoType = queryBusinessOrder.getCardIoType().getId();
                    }
                    if (queryBusinessOrder.getBusinessOrderType() == null) {
                        nfcosBusinessOrder.businessOrderType = EnumBusinessOrderType.UNKNOW.getId();
                        return 0;
                    }
                    nfcosBusinessOrder.businessOrderType = queryBusinessOrder.getBusinessOrderType().getId();
                    return 0;
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "业务订单详细信息查询出现异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "业务订单详细信息查询出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            } else if (NfcosService4xm.this.fmLog == null) {
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "业务订单详细信息查询时，传入的消息载体对象为空");
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
        }

        private void mainOrderClone(NfcosMainOrder nfcosMainOrder, MainOrder mainOrder) {
            nfcosMainOrder.id = mainOrder.getId();
            nfcosMainOrder.amount = mainOrder.getAmount();
            nfcosMainOrder.date = mainOrder.getDate();
            nfcosMainOrder.time = mainOrder.getTime();
            if (mainOrder.getState() != null) {
                nfcosMainOrder.state = mainOrder.getState().getId();
            } else {
                nfcosMainOrder.state = EnumOrderStatus.unknown.getId();
            }
            BusinessOrder[] businessOrders = mainOrder.getBusinessOrders();
            if (businessOrders != null) {
                ArrayList arrayList = new ArrayList();
                for (BusinessOrder businessOrder : businessOrders) {
                    if (businessOrder != null) {
                        NfcosBusinessOrder nfcosBusinessOrder = new NfcosBusinessOrder();
                        businessOrderClone(nfcosBusinessOrder, businessOrder);
                        arrayList.add(nfcosBusinessOrder);
                    }
                }
                nfcosMainOrder.businessOrders = arrayList;
            }
            PayOrder[] payOrders = mainOrder.getPayOrders();
            if (payOrders != null) {
                ArrayList arrayList2 = new ArrayList();
                for (PayOrder payOrder : payOrders) {
                    if (payOrder != null) {
                        NfcosPayOrder nfcosPayOrder = new NfcosPayOrder();
                        payOrderClone(nfcosPayOrder, payOrder);
                        arrayList2.add(nfcosPayOrder);
                    }
                }
                nfcosMainOrder.payOrders = arrayList2;
            }
        }

        private void activityClone(NfcosActivity nfcosActivity, Activity activity) {
            nfcosActivity.name = activity.getName();
            nfcosActivity.code = activity.getCode();
            nfcosActivity.start = activity.getStart();
            nfcosActivity.end = activity.getEnd();
            nfcosActivity.total = activity.getTotal();
            nfcosActivity.remainder = activity.getRemainder();
            nfcosActivity.definition = activity.getDefinition();
            nfcosActivity.payChannel = activity.getPayChannel();
            nfcosActivity.payMin = activity.getPayMin();
        }

        public int applyIssueByProduct(int i, String str, int i2, byte[] bArr, String str2, byte[] bArr2, NfcosMainOrder nfcosMainOrder) throws RemoteException {
            if (nfcosMainOrder == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "发卡订单申请付款时，传入的消息载体对象为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
            EnumCardAppType instance = EnumCardAppType.instance(i);
            if (instance == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "发卡订单申请付款时，传入的卡上应用类型无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
            if (str != null) {
                boolean z = true;
                if (str.length() >= 1) {
                    if (bArr == null || bArr.length < 1) {
                        if (NfcosService4xm.this.fmLog != null) {
                            NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "发卡订单申请付款时，传入SE标识无效");
                        }
                        return Constants.ErrorCode.AIDL_PARAMETER_NULL;
                    } else if (str2 == null || str2.length() < 1) {
                        if (NfcosService4xm.this.fmLog != null) {
                            NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "发卡订单申请付款时，传入终端型号无效");
                        }
                        return Constants.ErrorCode.AIDL_PARAMETER_NULL;
                    } else if (bArr2 == null || bArr2.length < 1) {
                        if (NfcosService4xm.this.fmLog != null) {
                            NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "发卡订单申请付款时，传入活动编码无效");
                        }
                        return Constants.ErrorCode.AIDL_PARAMETER_NULL;
                    } else {
                        byte[] bytes = str.getBytes();
                        byte[] join = FM_Bytes.join(FM_Bytes.join(FM_Bytes.join(FM_Bytes.join(new byte[]{2, 1, (byte) i2}, new byte[]{1, (byte) bytes.length}), bytes), new byte[]{3, (byte) bArr.length}), bArr);
                        byte[] bytes2 = str2.getBytes();
                        try {
                            MainOrder applyAct4Pay = NfcosService4xm.this.manager.getCardAppTrade().applyAct4Pay(bArr2, instance, FM_Bytes.join(FM_Bytes.join(join, new byte[]{5, (byte) bytes2.length}), bytes2));
                            if (applyAct4Pay != null) {
                                mainOrderClone(nfcosMainOrder, applyAct4Pay);
                            } else {
                                if (NfcosService4xm.this.fmLog != null) {
                                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "活动订单申请时，返回结果为空");
                                }
                                z = false;
                            }
                            if (z) {
                                return 0;
                            }
                            return 99;
                        } catch (BusinessException e) {
                            if (NfcosService4xm.this.fmLog != null) {
                                FMLog access$2 = NfcosService4xm.this.fmLog;
                                String access$0 = NfcosService4xm.this.logTag;
                                access$2.warn(access$0, "订单申请付款时出现异常:" + Util4Java.getExceptionInfo(e));
                            }
                            if (e.getErrorMsg() != null) {
                                return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                            }
                            return 99;
                        } catch (Exception e2) {
                            if (NfcosService4xm.this.fmLog != null) {
                                FMLog access$22 = NfcosService4xm.this.fmLog;
                                String access$02 = NfcosService4xm.this.logTag;
                                access$22.warn(access$02, "订单申请付款时出现异常:" + Util4Java.getExceptionInfo(e2));
                            }
                            return 99;
                        }
                    }
                }
            }
            if (NfcosService4xm.this.fmLog != null) {
                NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "发卡订单申请付款时，传入产品编码无效");
            }
            return Constants.ErrorCode.AIDL_PARAMETER_NULL;
        }

        public int applyIssue(int i, int i2, int i3, byte[] bArr, String str, byte[] bArr2, NfcosMainOrder nfcosMainOrder) throws RemoteException {
            if (nfcosMainOrder == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "发卡订单申请付款时，传入的消息载体对象为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
            EnumCardAppType instance = EnumCardAppType.instance(i);
            if (instance == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "发卡订单申请付款时，传入的卡上应用类型无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
            if (bArr != null) {
                boolean z = true;
                if (bArr.length >= 1) {
                    if (str == null || str.length() < 1) {
                        if (NfcosService4xm.this.fmLog != null) {
                            NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "发卡订单申请付款时，传入终端型号无效");
                        }
                        return Constants.ErrorCode.AIDL_PARAMETER_NULL;
                    } else if (bArr2 == null || bArr2.length < 1) {
                        if (NfcosService4xm.this.fmLog != null) {
                            NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "发卡订单申请付款时，传入活动编码无效");
                        }
                        return Constants.ErrorCode.AIDL_PARAMETER_NULL;
                    } else if (i2 < 0) {
                        if (NfcosService4xm.this.fmLog != null) {
                            NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "发卡订单申请付款时，传入的支付金额无效");
                        }
                        return Constants.ErrorCode.AIDL_PARAMETER_NULL;
                    } else {
                        byte[] intToBytes = FM_Bytes.intToBytes(i2, 4);
                        byte[] join = FM_Bytes.join(FM_Bytes.join(FM_Bytes.join(FM_Bytes.join(new byte[]{2, 1, (byte) i3}, new byte[]{1, (byte) intToBytes.length}), intToBytes), new byte[]{3, (byte) bArr.length}), bArr);
                        byte[] bytes = str.getBytes();
                        try {
                            MainOrder applyAct4Pay = NfcosService4xm.this.manager.getCardAppTrade().applyAct4Pay(bArr2, instance, FM_Bytes.join(FM_Bytes.join(join, new byte[]{5, (byte) bytes.length}), bytes));
                            if (applyAct4Pay != null) {
                                mainOrderClone(nfcosMainOrder, applyAct4Pay);
                            } else {
                                if (NfcosService4xm.this.fmLog != null) {
                                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "活动订单申请时，返回结果为空");
                                }
                                z = false;
                            }
                            if (z) {
                                return 0;
                            }
                            return 99;
                        } catch (BusinessException e) {
                            if (NfcosService4xm.this.fmLog != null) {
                                FMLog access$2 = NfcosService4xm.this.fmLog;
                                String access$0 = NfcosService4xm.this.logTag;
                                access$2.warn(access$0, "订单申请付款时出现异常:" + Util4Java.getExceptionInfo(e));
                            }
                            if (e.getErrorMsg() != null) {
                                return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                            }
                            return 99;
                        } catch (Exception e2) {
                            if (NfcosService4xm.this.fmLog != null) {
                                FMLog access$22 = NfcosService4xm.this.fmLog;
                                String access$02 = NfcosService4xm.this.logTag;
                                access$22.warn(access$02, "订单申请付款时出现异常:" + Util4Java.getExceptionInfo(e2));
                            }
                            return 99;
                        }
                    }
                }
            }
            if (NfcosService4xm.this.fmLog != null) {
                NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "发卡订单申请付款时，传入SE标识无效");
            }
            return Constants.ErrorCode.AIDL_PARAMETER_NULL;
        }

        public int doIssueEx(int i, int i2, int i3, byte[] bArr, String str, byte[] bArr2) throws RemoteException {
            EnumCardAppType instance = EnumCardAppType.instance(i);
            if (instance == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "空中发卡时，传入的卡上应用类型无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
            if (bArr != null) {
                boolean z = true;
                if (bArr.length >= 1) {
                    if (str == null || str.length() < 1) {
                        if (NfcosService4xm.this.fmLog != null) {
                            NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "发卡订单申请付款时，传入终端型号无效");
                        }
                        return Constants.ErrorCode.AIDL_PARAMETER_NULL;
                    } else if (bArr2 == null || bArr2.length < 1) {
                        if (NfcosService4xm.this.fmLog != null) {
                            NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "发卡订单申请付款时，传入活动编码无效");
                        }
                        return Constants.ErrorCode.AIDL_PARAMETER_NULL;
                    } else if (i2 < 0) {
                        if (NfcosService4xm.this.fmLog != null) {
                            NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "发卡订单申请付款时，传入的支付金额无效");
                        }
                        return Constants.ErrorCode.AIDL_PARAMETER_NULL;
                    } else {
                        byte[] intToBytes = FM_Bytes.intToBytes(i2, 4);
                        byte[] join = FM_Bytes.join(FM_Bytes.join(FM_Bytes.join(FM_Bytes.join(new byte[]{2, 1, (byte) i3}, new byte[]{1, (byte) intToBytes.length}), intToBytes), new byte[]{3, (byte) bArr.length}), bArr);
                        byte[] bytes = str.getBytes();
                        try {
                            MainOrder applyAct4Pay = NfcosService4xm.this.manager.getCardAppTrade().applyAct4Pay(bArr2, instance, FM_Bytes.join(FM_Bytes.join(join, new byte[]{5, (byte) bytes.length}), bytes));
                            if (applyAct4Pay == null) {
                                if (NfcosService4xm.this.fmLog != null) {
                                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "空中发卡时，平台响应数据解析主订单失败");
                                }
                                return Integer.parseInt(BusinessException.ErrorMessage.business_order_not_exist.getId());
                            } else if (EnumOrderStatus.hasPaid != applyAct4Pay.getState()) {
                                if (NfcosService4xm.this.fmLog != null) {
                                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "空中发卡时，订单未支付");
                                }
                                return Integer.parseInt(BusinessException.ErrorMessage.business_order_apply_no_pay.getId());
                            } else {
                                BusinessOrder businessOrder = null;
                                BusinessOrder businessOrder2 = null;
                                for (BusinessOrder businessOrder3 : applyAct4Pay.getBusinessOrders()) {
                                    if (businessOrder3 != null) {
                                        if (EnumBusinessOrderType.ORDER_TYPE_ISSUE == businessOrder3.getBusinessOrderType()) {
                                            businessOrder = businessOrder3;
                                        }
                                        if (EnumBusinessOrderType.ORDER_TYPE_RECHARGE == businessOrder3.getBusinessOrderType()) {
                                            businessOrder2 = businessOrder3;
                                        }
                                    }
                                }
                                if (businessOrder == null) {
                                    if (NfcosService4xm.this.fmLog != null) {
                                        NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "空中发卡时，没有发卡业务订单");
                                    }
                                    return Integer.parseInt(BusinessException.ErrorMessage.business_order_not_exist.getId());
                                }
                                boolean issuerVer2 = NfcosService4xm.this.manager.getCardAppInstall().issuerVer2(businessOrder.getOrder(), 1, bArr, (byte[]) null);
                                if (NfcosService4xm.this.fmLog != null) {
                                    NfcosService4xm.this.fmLog.debug(NfcosService4xm.this.logTag, "空中发卡结果:" + issuerVer2);
                                }
                                if (!issuerVer2) {
                                    z = false;
                                } else if (businessOrder2 != null) {
                                    z = NfcosService4xm.this.manager.getCardAppTrade().remoteRecharge(businessOrder2.getOrder(), (byte[]) null);
                                    if (NfcosService4xm.this.fmLog != null) {
                                        NfcosService4xm.this.fmLog.debug(NfcosService4xm.this.logTag, "空中发卡完成，充值结果:" + issuerVer2);
                                    }
                                }
                                if (!issuerVer2) {
                                    if (NfcosService4xm.this.fmLog != null) {
                                        NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "空中发卡失败");
                                    }
                                    return Integer.parseInt(BusinessException.ErrorMessage.app_issuer_fail.getId());
                                } else if (z) {
                                    if (NfcosService4xm.this.fmLog != null) {
                                        NfcosService4xm.this.fmLog.debug(NfcosService4xm.this.logTag, "空中发卡完成，充值完成");
                                    }
                                    return 0;
                                } else {
                                    if (NfcosService4xm.this.fmLog != null) {
                                        NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "空中发卡完成，充值失败");
                                    }
                                    return Integer.parseInt(BusinessException.ErrorMessage.trade_fail.getId());
                                }
                            }
                        } catch (BusinessException e) {
                            if (NfcosService4xm.this.fmLog != null) {
                                NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "空中发卡时时出现异常:" + Util4Java.getExceptionInfo(e));
                            }
                            if (e.getErrorMsg() != null) {
                                return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                            }
                            return 99;
                        } catch (Exception e2) {
                            if (NfcosService4xm.this.fmLog != null) {
                                NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "空中发卡时时出现异常:" + Util4Java.getExceptionInfo(e2));
                            }
                            return 99;
                        }
                    }
                }
            }
            if (NfcosService4xm.this.fmLog != null) {
                NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "发卡订单申请付款时，传入SE标识无效");
            }
            return Constants.ErrorCode.AIDL_PARAMETER_NULL;
        }

        public int doIssueExByProduct(int i, String str, int i2, byte[] bArr, String str2, byte[] bArr2) throws RemoteException {
            EnumCardAppType instance = EnumCardAppType.instance(i);
            if (instance == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "空中发卡时，传入的卡上应用类型无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (str == null || str.length() < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "发卡订单申请付款时，传入产品编码无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (bArr == null || bArr.length < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "发卡订单申请付款时，传入SE标识无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (str2 == null || str2.length() < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "发卡订单申请付款时，传入终端型号无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (bArr2 == null || bArr2.length < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "发卡订单申请付款时，传入活动编码无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                byte[] bytes = str.getBytes();
                byte[] join = FM_Bytes.join(FM_Bytes.join(FM_Bytes.join(FM_Bytes.join(new byte[]{2, 1, (byte) i2}, new byte[]{1, (byte) bytes.length}), bytes), new byte[]{3, (byte) bArr.length}), bArr);
                byte[] bytes2 = str2.getBytes();
                try {
                    MainOrder applyAct4Pay = NfcosService4xm.this.manager.getCardAppTrade().applyAct4Pay(bArr2, instance, FM_Bytes.join(FM_Bytes.join(join, new byte[]{5, (byte) bytes2.length}), bytes2));
                    if (applyAct4Pay == null) {
                        if (NfcosService4xm.this.fmLog != null) {
                            NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "空中发卡时，平台响应数据解析主订单失败");
                        }
                        return Integer.parseInt(BusinessException.ErrorMessage.business_order_not_exist.getId());
                    } else if (EnumOrderStatus.hasPaid != applyAct4Pay.getState()) {
                        if (NfcosService4xm.this.fmLog != null) {
                            NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "空中发卡时，订单未支付");
                        }
                        return Integer.parseInt(BusinessException.ErrorMessage.business_order_apply_no_pay.getId());
                    } else {
                        BusinessOrder businessOrder = null;
                        for (BusinessOrder businessOrder2 : applyAct4Pay.getBusinessOrders()) {
                            if (businessOrder2 != null && EnumBusinessOrderType.ORDER_TYPE_ISSUE == businessOrder2.getBusinessOrderType()) {
                                businessOrder = businessOrder2;
                            }
                        }
                        if (businessOrder == null) {
                            if (NfcosService4xm.this.fmLog != null) {
                                NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "空中发卡时，没有发卡业务订单");
                            }
                            return Integer.parseInt(BusinessException.ErrorMessage.business_order_not_exist.getId());
                        } else if (NfcosService4xm.this.manager.getCardAppInstall().issuerVer2(businessOrder.getOrder(), 1, bArr, (byte[]) null)) {
                            return 0;
                        } else {
                            return Integer.parseInt(BusinessException.ErrorMessage.app_issuer_fail.getId());
                        }
                    }
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null) {
                        NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "空中发卡时时出现异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "空中发卡时时出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            }
        }

        public int downloadApplet(int i, byte[] bArr, String str) throws RemoteException {
            EnumCardAppType instance = EnumCardAppType.instance(i);
            if (instance == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "应用安装包下载时，传入的卡上应用类型无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (bArr == null || bArr.length < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "应用安装包下载时，传入SE标识无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (str == null || str.length() < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "应用安装包下载时，传入终端型号无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                try {
                    if (NfcosService4xm.this.manager.getCardAppInstall().appletDownload(instance, bArr, str)) {
                        return 0;
                    }
                    return Integer.parseInt(BusinessException.ErrorMessage.app_issuer_fail.getId());
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "应用安装包下载时出现异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "应用安装包下载时出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            }
        }

        public int deleteApp(byte[] bArr, int i, byte[] bArr2, String str, CardAppInfo cardAppInfo) throws RemoteException {
            if (bArr == null || bArr.length < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "应用删除时，传入应用管理码无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
            EnumCardAppType instance = EnumCardAppType.instance(i);
            if (instance == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "应用删除时，传入的卡上应用类型无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (bArr2 == null || bArr2.length < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "应用删除时，传入SE标识无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (str == null || str.length() < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "应用删除时，传入终端型号无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                try {
                    CardAppInfo deleteApp = NfcosService4xm.this.manager.getCardAppInstall().deleteApp(bArr, instance, bArr2, str);
                    if (deleteApp == null) {
                        return Integer.parseInt(BusinessException.ErrorMessage.app_issuer_fail.getId());
                    }
                    cardAppInfo.appNo = deleteApp.getCardAppNo();
                    cardAppInfo.balance = deleteApp.getBalance().intValue();
                    cardAppInfo.moc = deleteApp.getMoc();
                    return 0;
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "卡上应用删除出现异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "卡上应用删除出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            }
        }

        public int closeApp(byte[] bArr, int i, byte[] bArr2, String str) throws RemoteException {
            if (bArr == null || bArr.length < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "应用关闭时，传入应用管理码无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
            EnumCardAppType instance = EnumCardAppType.instance(i);
            if (instance == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "应用关闭时，传入的卡上应用类型无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (bArr2 == null || bArr2.length < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "应用关闭时，传入SE标识无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (str == null || str.length() < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "应用关闭时，传入终端型号无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                try {
                    if (NfcosService4xm.this.manager.getCardAppInstall().setAppVer2(bArr, instance, bArr2, str, EnumAppManageOperateType.APP_LOCK)) {
                        return 0;
                    }
                    return Integer.parseInt(BusinessException.ErrorMessage.app_issuer_fail.getId());
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "应用关闭出现异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "应用关闭出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            }
        }

        public int openApp(byte[] bArr, int i, byte[] bArr2, String str) throws RemoteException {
            if (bArr == null || bArr.length < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "应用开启时，传入应用管理码无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
            EnumCardAppType instance = EnumCardAppType.instance(i);
            if (instance == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "应用开启时，传入的卡上应用类型无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (bArr2 == null || bArr2.length < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "应用开启时，传入SE标识无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (str == null || str.length() < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "应用开启时，传入终端型号无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                try {
                    if (NfcosService4xm.this.manager.getCardAppInstall().setAppVer2(bArr, instance, bArr2, str, EnumAppManageOperateType.APP_UNLOCK)) {
                        return 0;
                    }
                    return Integer.parseInt(BusinessException.ErrorMessage.app_issuer_fail.getId());
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "应用开启出现异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "应用开启出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            }
        }

        public int moveApp(byte[] bArr, int i, byte[] bArr2, String str, VoucherInfo voucherInfo) throws RemoteException {
            if (voucherInfo == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "应用迁移时，迁移认证码的载体对象为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (bArr == null || bArr.length < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "应用迁移时，传入应用管理码无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                EnumCardAppType instance = EnumCardAppType.instance(i);
                if (instance == null) {
                    if (NfcosService4xm.this.fmLog != null) {
                        NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "应用迁移时，传入的卡上应用类型无效");
                    }
                    return Constants.ErrorCode.AIDL_PARAMETER_NULL;
                } else if (bArr2 == null || bArr2.length < 1) {
                    if (NfcosService4xm.this.fmLog != null) {
                        NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "应用迁移时，传入SE标识无效");
                    }
                    return Constants.ErrorCode.AIDL_PARAMETER_NULL;
                } else if (str == null || str.length() < 1) {
                    if (NfcosService4xm.this.fmLog != null) {
                        NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "应用迁移时，传入终端型号无效");
                    }
                    return Constants.ErrorCode.AIDL_PARAMETER_NULL;
                } else {
                    try {
                        byte[] moveApp = NfcosService4xm.this.manager.getCardAppInstall().moveApp(bArr, instance, bArr2, str);
                        if (moveApp == null || moveApp.length <= 0) {
                            return Integer.parseInt(BusinessException.ErrorMessage.app_issuer_fail.getId());
                        }
                        voucherInfo.token = moveApp;
                        return 0;
                    } catch (BusinessException e) {
                        if (NfcosService4xm.this.fmLog != null) {
                            FMLog access$2 = NfcosService4xm.this.fmLog;
                            String access$0 = NfcosService4xm.this.logTag;
                            access$2.warn(access$0, "应用迁出出现异常:" + Util4Java.getExceptionInfo(e));
                        }
                        if (e.getErrorMsg() != null) {
                            return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                        }
                        return 99;
                    } catch (Exception e2) {
                        if (NfcosService4xm.this.fmLog != null) {
                            FMLog access$22 = NfcosService4xm.this.fmLog;
                            String access$02 = NfcosService4xm.this.logTag;
                            access$22.warn(access$02, "应用迁出出现异常:" + Util4Java.getExceptionInfo(e2));
                        }
                        return 99;
                    }
                }
            }
        }

        public int applyRecharge(int i, int i2, int i3, byte[] bArr, byte[] bArr2, NfcosMainOrder nfcosMainOrder) throws RemoteException {
            if (nfcosMainOrder == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "订单申请付款时，传入的消息载体对象为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
            EnumCardAppType instance = EnumCardAppType.instance(i);
            if (instance == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "订单申请付款时，传入卡的类型无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (i2 < 0) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "订单申请付款时，传入金额无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                boolean z = true;
                byte[] intToBytes = FM_Bytes.intToBytes(i2, 4);
                try {
                    MainOrder applyAct4Pay = NfcosService4xm.this.manager.getCardAppTrade().applyAct4Pay(bArr2, instance, FM_Bytes.join(FM_Bytes.join(FM_Bytes.join(FM_Bytes.join(new byte[]{2, 1, (byte) i3}, new byte[]{1, (byte) intToBytes.length}), intToBytes), new byte[]{3, (byte) bArr.length}), bArr));
                    if (applyAct4Pay != null) {
                        mainOrderClone(nfcosMainOrder, applyAct4Pay);
                    } else {
                        if (NfcosService4xm.this.fmLog != null) {
                            NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "活动订单申请时，返回结果为空");
                        }
                        z = false;
                    }
                    if (z) {
                        return 0;
                    }
                    return 99;
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "订单申请付款时出现异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "订单申请付款时出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            }
        }

        public int queryMainOrders(int i, int i2, int i3, int[] iArr, List<NfcosMainOrder> list) throws RemoteException {
            boolean z;
            if (list == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "订单信息查询时，传入的消息载体对象为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
            EnumCardAppType instance = EnumCardAppType.instance(i3);
            if (instance == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "主订单查询时，传入卡的类型无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (i < 0 || i2 < 0) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "主订单查询时，传入查询范围无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                ArrayList arrayList = null;
                if (iArr != null && iArr.length > 0) {
                    ArrayList arrayList2 = new ArrayList();
                    for (int orderStatus4ID : iArr) {
                        EnumOrderStatus orderStatus4ID2 = EnumOrderStatus.getOrderStatus4ID(orderStatus4ID);
                        if (orderStatus4ID2 != null) {
                            arrayList2.add(orderStatus4ID2);
                        }
                    }
                    arrayList = arrayList2;
                }
                try {
                    List<MainOrder> queryMainOrdersVer4 = NfcosService4xm.this.manager.getCardAppTrade().queryMainOrdersVer4(i, i2, arrayList, instance);
                    if (queryMainOrdersVer4 == null) {
                        if (NfcosService4xm.this.fmLog != null) {
                            NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "订单信息查询时，查询结果为空");
                        }
                        z = false;
                    } else {
                        for (MainOrder mainOrderClone : queryMainOrdersVer4) {
                            NfcosMainOrder nfcosMainOrder = new NfcosMainOrder();
                            mainOrderClone(nfcosMainOrder, mainOrderClone);
                            list.add(nfcosMainOrder);
                        }
                        z = true;
                    }
                    if (z) {
                        return 0;
                    }
                    return 99;
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null) {
                        NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "主订单信息查询时出现异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "主订单信息查询时出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            }
        }

        public int paid4order(byte[] bArr, byte[] bArr2) throws RemoteException {
            if (bArr2 == null || bArr2.length < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "订单支付完成处理时，传入业务活动码无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (bArr == null || bArr.length < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "订单支付完成处理时，订单编号为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                try {
                    return NfcosService4xm.this.manager.getCardAppTrade().setOrderStatus(bArr2, EnumOrderType.MAIN, bArr, EnumOrderStatus.hasPaid);
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "订单支付完成处理异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "订单支付完成处理异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            }
        }

        public int cancelOrder(byte[] bArr, byte[] bArr2) throws RemoteException {
            if (bArr == null || bArr.length < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "订单支付完成处理时，订单编号为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (bArr2 == null || bArr2.length < 1) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "订单支付完成处理时，订单处理活动编码为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                try {
                    return NfcosService4xm.this.manager.getCardAppTrade().setOrderStatus(bArr2, EnumOrderType.MAIN, bArr, EnumOrderStatus.invalid);
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "订单取消处理异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "订单取消处理异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            }
        }

        public int applyRechargeEx(int i, int i2, int i3, byte[] bArr, byte[] bArr2) throws RemoteException {
            EnumCardAppType instance = EnumCardAppType.instance(i);
            if (instance == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "订单申请付款时，传入卡的类型无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (i2 < 0) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "订单申请付款时，传入金额无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                byte[] intToBytes = FM_Bytes.intToBytes(i2, 4);
                try {
                    MainOrder applyAct4Pay = NfcosService4xm.this.manager.getCardAppTrade().applyAct4Pay(bArr2, instance, FM_Bytes.join(FM_Bytes.join(FM_Bytes.join(FM_Bytes.join(new byte[]{2, 1, (byte) i3}, new byte[]{1, (byte) intToBytes.length}), intToBytes), new byte[]{3, (byte) bArr.length}), bArr));
                    if (applyAct4Pay == null) {
                        if (NfcosService4xm.this.fmLog != null) {
                            NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "空中发卡时，平台响应数据解析主订单失败");
                        }
                        return Integer.parseInt(BusinessException.ErrorMessage.business_order_not_exist.getId());
                    } else if (EnumOrderStatus.hasPaid != applyAct4Pay.getState()) {
                        if (NfcosService4xm.this.fmLog != null) {
                            NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "空中发卡时，订单未支付");
                        }
                        return Integer.parseInt(BusinessException.ErrorMessage.business_order_apply_no_pay.getId());
                    } else {
                        BusinessOrder businessOrder = null;
                        for (BusinessOrder businessOrder2 : applyAct4Pay.getBusinessOrders()) {
                            if (businessOrder2 != null && EnumBusinessOrderType.ORDER_TYPE_RECHARGE == businessOrder2.getBusinessOrderType()) {
                                businessOrder = businessOrder2;
                            }
                        }
                        if (businessOrder == null) {
                            if (NfcosService4xm.this.fmLog != null) {
                                NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "空中发卡时，没有发卡业务订单");
                            }
                            return Integer.parseInt(BusinessException.ErrorMessage.business_order_not_exist.getId());
                        } else if (NfcosService4xm.this.manager.getCardAppTrade().remoteRecharge(businessOrder.getOrder(), bArr)) {
                            return 0;
                        } else {
                            return Integer.parseInt(BusinessException.ErrorMessage.trade_fail.getId());
                        }
                    }
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null) {
                        NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "订单申请付款时出现异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "订单申请付款时出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            }
        }

        public int queryPreDeposit(int i, PreDepositInfo preDepositInfo) throws RemoteException {
            boolean z;
            if (preDepositInfo == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "预置金信息查询时，传入的消息载体对象为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
            EnumCardAppType instance = EnumCardAppType.instance(i);
            if (instance == null) {
                if (NfcosService4xm.this.fmLog != null) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "预置金信息查询时，传入卡的类型无效");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
            try {
                PreDepositInfo queryPreDeposit = NfcosService4xm.this.manager.getCardAppTrade().queryPreDeposit(instance);
                if (queryPreDeposit == null) {
                    if (NfcosService4xm.this.fmLog != null) {
                        NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "预置金信息查询时，查询结果为空");
                    }
                    z = false;
                } else {
                    preDepositInfo.total = queryPreDeposit.getTotal();
                    preDepositInfo.blance = queryPreDeposit.getBlance();
                    z = true;
                }
                if (z) {
                    return 0;
                }
                return 99;
            } catch (BusinessException e) {
                if (NfcosService4xm.this.fmLog != null) {
                    FMLog access$2 = NfcosService4xm.this.fmLog;
                    String access$0 = NfcosService4xm.this.logTag;
                    access$2.warn(access$0, "预置金信息查询出现异常:" + Util4Java.getExceptionInfo(e));
                }
                if (e.getErrorMsg() != null) {
                    return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                }
                return 99;
            } catch (Exception e2) {
                if (NfcosService4xm.this.fmLog != null) {
                    FMLog access$22 = NfcosService4xm.this.fmLog;
                    String access$02 = NfcosService4xm.this.logTag;
                    access$22.warn(access$02, "预置金信息查询出现异常:" + Util4Java.getExceptionInfo(e2));
                }
                return 99;
            }
        }

        public int applyBusiness(String str, byte[] bArr, String str2) {
            if (str == null) {
                if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "申通业务申请时，传入的服务提供商编号为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (bArr == null || bArr.length < 1) {
                if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "申通业务申请时， 传入的eSE标识为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (str2 == null || str2.length() < 1) {
                if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "申通业务申请时时， 传入的 终端型号规格为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                try {
                    if (NfcosService4xm.this.manager.getCardAppInstall().applyBusiness(str, bArr, str2, (byte[]) null)) {
                        return 0;
                    }
                    if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                        NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "申通业务申请失败");
                    }
                    return 99;
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "申通业务申请出现异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "申通业务申请出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            }
        }

        public int buyTicket(byte[] bArr, TicketOperateResult ticketOperateResult) {
            if (ticketOperateResult == null) {
                if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "申通业务申请时，传入的消息载体对象为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (NfcosService4xm.this.globalUserName == null) {
                if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "申通业务申请时，传入的消息载体对象为空");
                }
                return Integer.parseInt(BusinessException.ErrorMessage.user_not_login.getId());
            } else if (bArr == null || bArr.length < 1) {
                if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "申通业务申请时， 传入的活动码验证数据为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                try {
                    TicketOperateResult buyTicket = NfcosService4xm.this.manager.getCardAppTrade().buyTicket(NfcosService4xm.this.globalUserName, bArr);
                    if (buyTicket == null) {
                        if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                            NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "申通业务申请时，返回结果为空");
                        }
                        return 99;
                    }
                    ticketOperateResult.ticketStub = buyTicket.getTicketStub();
                    ticketOperateResult.operateResult = buyTicket.getOperateResult();
                    return 0;
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "申通业务申请出现异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "申通业务申请出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            }
        }

        public int writeTicket(byte[] bArr) {
            if (bArr == null) {
                if (NfcosService4xm.this.fmLog == null || !NfcosService4xm.this.fmLog.getShowLogFlag()) {
                    return Constants.ErrorCode.AIDL_PARAMETER_NULL;
                }
                NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "申通写票时，传入的消息载体对象为空");
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (NfcosService4xm.this.globalUserName == null) {
                if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "申通写票时，传入的消息载体对象为空");
                }
                return Integer.parseInt(BusinessException.ErrorMessage.user_not_login.getId());
            } else {
                try {
                    if (NfcosService4xm.this.manager.getCardAppInstall().writeTicket(NfcosService4xm.this.globalUserName, bArr)) {
                        try {
                            int updateStationInfo = NfcosService4xm.this.manager.getCardAppTrade().updateStationInfo();
                            FMLog access$2 = NfcosService4xm.this.fmLog;
                            String access$0 = NfcosService4xm.this.logTag;
                            StringBuilder sb = new StringBuilder("更新在线站名处理:");
                            sb.append(updateStationInfo == 0 ? "成功" : "失败");
                            access$2.info(access$0, sb.toString());
                            return 0;
                        } catch (BusinessException e) {
                            if (NfcosService4xm.this.fmLog == null || !NfcosService4xm.this.fmLog.getShowLogFlag()) {
                                return 0;
                            }
                            FMLog access$22 = NfcosService4xm.this.fmLog;
                            String access$02 = NfcosService4xm.this.logTag;
                            access$22.warn(access$02, "更新在线站名:" + Util4Java.getExceptionInfo(e));
                            return 0;
                        } catch (Exception e2) {
                            if (NfcosService4xm.this.fmLog != null) {
                                FMLog access$23 = NfcosService4xm.this.fmLog;
                                String access$03 = NfcosService4xm.this.logTag;
                                access$23.warn(access$03, "更新在线站名:" + Util4Java.getExceptionInfo(e2));
                            }
                            return 99;
                        }
                    } else {
                        if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                            NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "申通业务申请时，返回结果为空");
                        }
                        return 99;
                    }
                } catch (BusinessException e3) {
                    if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                        FMLog access$24 = NfcosService4xm.this.fmLog;
                        String access$04 = NfcosService4xm.this.logTag;
                        access$24.warn(access$04, "申通写票出现异常:" + Util4Java.getExceptionInfo(e3));
                    }
                    if (e3.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e3.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e4) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$25 = NfcosService4xm.this.fmLog;
                        String access$05 = NfcosService4xm.this.logTag;
                        access$25.warn(access$05, "申通写票出现异常:" + Util4Java.getExceptionInfo(e4));
                    }
                    return 99;
                }
            }
        }

        public int returnTicket(byte[] bArr) {
            if (bArr == null) {
                if (NfcosService4xm.this.fmLog == null || !NfcosService4xm.this.fmLog.getShowLogFlag()) {
                    return Constants.ErrorCode.AIDL_PARAMETER_NULL;
                }
                NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "申通退票时，传入的活动码验证数据为空");
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (NfcosService4xm.this.globalUserName == null) {
                if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "申通业务申请时，传入的消息载体对象为空");
                }
                return Integer.parseInt(BusinessException.ErrorMessage.user_not_login.getId());
            } else {
                try {
                    if (NfcosService4xm.this.manager.getCardAppTrade().returnBusiness(NfcosService4xm.this.globalUserName, bArr)) {
                        return 0;
                    }
                    if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                        NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "申通业务申请时，返回结果为空");
                    }
                    return 99;
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "申通退票出现异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "申通退票出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            }
        }

        public int queryLastOperate(String str, byte[] bArr, TicketOperateResult ticketOperateResult) {
            if (ticketOperateResult == null) {
                if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "查询最后一次业务操作结果时，传入的消息载体对象为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (bArr == null || bArr.length < 1) {
                if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "查询最后一次业务操作结果时， 传入的eSE标识为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (str == null || str.length() < 1) {
                if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "查询最后一次业务操作结果时， 传入的 终端型号规格为空");
                }
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else if (NfcosService4xm.this.globalUserName == null) {
                if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                    NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "查询最后一次业务操作结果时，传入的消息载体对象为空");
                }
                return Integer.parseInt(BusinessException.ErrorMessage.user_not_login.getId());
            } else {
                try {
                    TicketOperateResult queryLastOperate = NfcosService4xm.this.manager.getCardAppTrade().queryLastOperate(NfcosService4xm.this.globalUserName, str, bArr);
                    if (queryLastOperate == null) {
                        if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                            NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "申通业务申请时，返回结果为空");
                        }
                        return 99;
                    }
                    ticketOperateResult.ticketStub = queryLastOperate.getTicketStub();
                    ticketOperateResult.operateResult = queryLastOperate.getOperateResult();
                    return 0;
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "查询最后一次业务操作结果出现异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "查询最后一次业务操作结果出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            }
        }

        public int getMaxSlotNumber() throws RemoteException {
            try {
                return NfcosService4xm.this.shentongCardManager.getMaxSlotNumber();
            } catch (BusinessException e) {
                if (NfcosService4xm.this.fmLog == null || !NfcosService4xm.this.fmLog.getShowLogFlag()) {
                    return 0;
                }
                FMLog access$2 = NfcosService4xm.this.fmLog;
                String access$0 = NfcosService4xm.this.logTag;
                access$2.warn(access$0, "查询支持最大卡槽数:" + Util4Java.getExceptionInfo(e));
                return 0;
            }
        }

        public int getTicketInfoBySlot(int i, STTicketInfo sTTicketInfo) throws RemoteException {
            if (sTTicketInfo != null) {
                try {
                    STTicketInfo ticketInfoBySlot = NfcosService4xm.this.shentongCardManager.getTicketInfoBySlot(i);
                    if (ticketInfoBySlot == null) {
                        if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                            NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "查询卡位上票基本信息时，返回结果为空");
                        }
                        return 99;
                    }
                    sTTicketInfo.inOutFlag = ticketInfoBySlot.getInOutFlag();
                    sTTicketInfo.appNo = ticketInfoBySlot.getAppNo();
                    sTTicketInfo.ticketType = ticketInfoBySlot.getTicketType().getId();
                    sTTicketInfo.startUsageTime = ticketInfoBySlot.getStartUsageTime();
                    sTTicketInfo.validationDate = ticketInfoBySlot.getValidationDate();
                    return 0;
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "查询卡位上票基本信息:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                }
            } else if (NfcosService4xm.this.fmLog == null || !NfcosService4xm.this.fmLog.getShowLogFlag()) {
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "查询卡位上票基本信息，传入的消息载体对象为空");
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
        }

        public int getTicketRecordsBySlot(int i, List<STTicketRecord> list) throws RemoteException {
            if (list != null) {
                try {
                    for (STTicketRecord next : NfcosService4xm.this.shentongCardManager.getTicketRecordsBySlot(i)) {
                        STTicketRecord sTTicketRecord = new STTicketRecord();
                        sTTicketRecord.inOutFlag = next.getInOutFlag();
                        sTTicketRecord.date = next.getDate();
                        sTTicketRecord.time = next.getTime();
                        sTTicketRecord.stationName = next.getStationName();
                        list.add(sTTicketRecord);
                    }
                    return 0;
                } catch (BusinessException e) {
                    if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                        FMLog access$2 = NfcosService4xm.this.fmLog;
                        String access$0 = NfcosService4xm.this.logTag;
                        access$2.warn(access$0, "查询卡位上票交易记录出现异常:" + Util4Java.getExceptionInfo(e));
                    }
                    if (e.getErrorMsg() != null) {
                        return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                    }
                    return 99;
                } catch (Exception e2) {
                    if (NfcosService4xm.this.fmLog != null) {
                        FMLog access$22 = NfcosService4xm.this.fmLog;
                        String access$02 = NfcosService4xm.this.logTag;
                        access$22.warn(access$02, "查询卡位上票交易记录出现异常:" + Util4Java.getExceptionInfo(e2));
                    }
                    return 99;
                }
            } else if (NfcosService4xm.this.fmLog == null || !NfcosService4xm.this.fmLog.getShowLogFlag()) {
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            } else {
                NfcosService4xm.this.fmLog.warn(NfcosService4xm.this.logTag, "查询卡位上票交易记录，传入的消息载体对象为空");
                return Constants.ErrorCode.AIDL_PARAMETER_NULL;
            }
        }

        public int useTheTicket(int i) throws RemoteException {
            try {
                if (NfcosService4xm.this.shentongCardManager.useTheTicket(i) != 0) {
                    return 99;
                }
                return 0;
            } catch (BusinessException e) {
                if (NfcosService4xm.this.fmLog != null && NfcosService4xm.this.fmLog.getShowLogFlag()) {
                    FMLog access$2 = NfcosService4xm.this.fmLog;
                    String access$0 = NfcosService4xm.this.logTag;
                    access$2.warn(access$0, "激活某卡位出现异常:" + Util4Java.getExceptionInfo(e));
                }
                if (e.getErrorMsg() != null) {
                    return NfcosService4xm.this.errorCodeHandler.getCode(e.getErrorMsg().getId());
                }
                return 99;
            } catch (Exception e2) {
                if (NfcosService4xm.this.fmLog != null) {
                    FMLog access$22 = NfcosService4xm.this.fmLog;
                    String access$02 = NfcosService4xm.this.logTag;
                    access$22.warn(access$02, "激活某卡位出现异常:" + Util4Java.getExceptionInfo(e2));
                }
                return 99;
            }
        }
    }

    public class IssuerProcessHandlerImpl implements IssuerProcessHandler {
        public IssuerProcessHandlerImpl() {
        }

        public void handle(EnumIssueProcess enumIssueProcess) {
            BroadCastParameter broadCastParameter = new BroadCastParameter();
            broadCastParameter.broadcastType = BroadCastType.ISSUER_PROCESS.getId();
            broadCastParameter.process = enumIssueProcess.getId();
            NfcosService4xm.this.sendBroadCast(broadCastParameter);
        }
    }
}
