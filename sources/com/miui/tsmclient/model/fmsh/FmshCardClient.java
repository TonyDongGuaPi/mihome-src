package com.miui.tsmclient.model.fmsh;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import cn.com.fmsh.nfcos.client.service.xm.CardAppInfo;
import cn.com.fmsh.nfcos.client.service.xm.CardAppManager;
import cn.com.fmsh.nfcos.client.service.xm.LoginInfo;
import cn.com.fmsh.nfcos.client.service.xm.NfcosBusinessOrder;
import cn.com.fmsh.nfcos.client.service.xm.NfcosMainOrder;
import cn.com.fmsh.nfcos.client.service.xm.UserInfo;
import cn.com.fmsh.nfcos.client.service.xm.VoucherInfo;
import cn.com.fmsh.tsm.business.enums.EnumBusinessOrderType;
import cn.com.fmsh.tsm.business.enums.EnumCardAppType;
import cn.com.fmsh.tsm.business.enums.EnumCardIoType;
import cn.com.fmsh.tsm.business.enums.EnumOrderStatus;
import com.miui.tsmclient.account.TSMAccountManager;
import com.miui.tsmclient.entity.ActionToken;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.entity.FmshCardInfo;
import com.miui.tsmclient.entity.FmshUserInfo;
import com.miui.tsmclient.model.BaseCardClient;
import com.miui.tsmclient.model.BaseResponse;
import com.miui.tsmclient.net.AuthApiException;
import com.miui.tsmclient.net.TSMAuthContants;
import com.miui.tsmclient.util.Constants;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.ResUtils;
import com.miui.tsmclient.util.SysUtils;
import com.tsmclient.smartcard.Coder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class FmshCardClient extends BaseCardClient {
    public static final int GET_INFO_REQUEST_TYPE_CARD_NO = 1;
    public static final int GET_INFO_REQUEST_TYPE_OVERAGE = 2;
    public static final int GET_INFO_REQUEST_TYPE_PURCHASE_RECORD = 4;
    protected static final byte ISSUE_HANDLE_FLAG_LNT = 3;
    protected static final byte ISSUE_HANDLE_FLAG_SPTC = 1;
    protected static final int OMA_CHANNEL_TYPE_ESE = 1;
    private static final int PAGE_SIZE_QUERY_ORDERS = 10;
    private static final String TAG = "FmshCardClient";
    private static final Semaphore sSemaphore = new Semaphore(1);
    protected TSMAccountManager mAccountManager;
    private CardInfo mCardInfo = new CardInfo(CardInfo.CARD_TYPE_DUMMY);

    public FmshCardClient() {
        this.mBussinessType = TSMAuthContants.BusinessType.sptc.toString();
        this.mAccountManager = new TSMAccountManager();
    }

    /* access modifiers changed from: protected */
    public boolean login(CardAppManager cardAppManager, Context context, int i) {
        FmshUserInfo fmshUserInfo = getFmshUserInfo(context, cardAppManager, i);
        if (fmshUserInfo == null || TextUtils.isEmpty(fmshUserInfo.getUserName()) || TextUtils.isEmpty(fmshUserInfo.getPassword())) {
            return false;
        }
        String userName = fmshUserInfo.getUserName();
        String password = fmshUserInfo.getPassword();
        LoginInfo loginInfo = new LoginInfo();
        if (this.mAccountManager.loadAccountInfo(context) == null) {
            return false;
        }
        try {
            int login = cardAppManager.login(userName, password, loginInfo);
            LogUtils.d("login api execute result is: " + login);
            if (loginInfo.loginResult == 1000) {
                LogUtils.d("User name：" + userName + " has not been registered!, so registered an new account!");
                UserInfo userInfo = new UserInfo();
                userInfo.password = password;
                userInfo.username = userName;
                if (cardAppManager.register(userInfo) == 0) {
                    LogUtils.d("account " + userName + " registered success! Then login...");
                    login = cardAppManager.login(userName, password, loginInfo);
                }
                LogUtils.d("Account:" + userName + " login result:" + loginInfo.loginResult);
            }
            LogUtils.d("login result is: " + loginInfo.loginResult);
            if (login == 0 && loginInfo.loginResult == 0) {
                return true;
            }
            return false;
        } catch (RemoteException e) {
            LogUtils.e("failed to login sptc account", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public BaseResponse applyIssue(Context context, int i, int i2, int i3, byte[] bArr, String str, String str2) {
        if (context == null) {
            LogUtils.e("applyIssue called, but the param is invalid!");
            return new BaseResponse(1, new Object[0]);
        }
        final NfcosMainOrder nfcosMainOrder = new NfcosMainOrder();
        final Context context2 = context;
        final int i4 = i;
        final int i5 = i2;
        final int i6 = i3;
        final byte[] bArr2 = bArr;
        final String str3 = str;
        final String str4 = str2;
        return executeTask(new FmshAppTask(context) {
            /* access modifiers changed from: protected */
            public BaseResponse doInBackground() {
                LogUtils.d("applyIssue() called!");
                CardAppManager service = getService();
                if (service != null) {
                    try {
                        if (!FmshCardClient.this.login(service, context2, i4)) {
                            return new BaseResponse(5, ResUtils.getString(context2, "service_unavailable"), new Object[0]);
                        }
                        int applyIssue = service.applyIssue(i4, i5, i6, bArr2, str3, Coder.hexStringToBytes(str4), nfcosMainOrder);
                        if (applyIssue == 1103) {
                            applyIssue = 1000;
                        } else if (applyIssue == 1041) {
                            applyIssue = 205;
                        }
                        return new BaseResponse(applyIssue, nfcosMainOrder);
                    } catch (RemoteException e) {
                        LogUtils.e("exception occurred when apply4OrderEx() running", e);
                    }
                }
                return new BaseResponse(3, ResUtils.getString(context2, "service_unavailable"), new Object[0]);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public BaseResponse applyIssueByProduct(Context context, int i, String str, int i2, byte[] bArr, String str2, String str3) {
        if (context == null) {
            LogUtils.e("applyIssueByProduct called, but the param is invalid!");
            return new BaseResponse(1, new Object[0]);
        }
        final NfcosMainOrder nfcosMainOrder = new NfcosMainOrder();
        final Context context2 = context;
        final int i3 = i;
        final String str4 = str;
        final int i4 = i2;
        final byte[] bArr2 = bArr;
        final String str5 = str2;
        final String str6 = str3;
        return executeTask(new FmshAppTask(context) {
            /* access modifiers changed from: protected */
            public BaseResponse doInBackground() {
                LogUtils.d("applyIssueByProduct() called!");
                CardAppManager service = getService();
                if (service != null) {
                    try {
                        if (!FmshCardClient.this.login(service, context2, i3)) {
                            return new BaseResponse(5, ResUtils.getString(context2, "service_unavailable"), new Object[0]);
                        }
                        int applyIssueByProduct = service.applyIssueByProduct(i3, str4, i4, bArr2, str5, Coder.hexStringToBytes(str6), nfcosMainOrder);
                        if (applyIssueByProduct == 1103) {
                            applyIssueByProduct = 1000;
                        } else if (applyIssueByProduct == 1041) {
                            applyIssueByProduct = 205;
                        }
                        return new BaseResponse(applyIssueByProduct, nfcosMainOrder);
                    } catch (RemoteException e) {
                        LogUtils.e("exception occurred when apply4OrderEx() running", e);
                    }
                }
                return new BaseResponse(3, ResUtils.getString(context2, "service_unavailable"), new Object[0]);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public BaseResponse doIssueEx(Context context, int i, int i2, String str, byte[] bArr, ActionToken actionToken, int i3, Bundle bundle) {
        if (context == null || actionToken == null) {
            LogUtils.e("doIssue called, but the param is invalid!");
            return new BaseResponse(1, new Object[0]);
        }
        final int i4 = i;
        final Context context2 = context;
        final int i5 = i3;
        final int i6 = i2;
        final byte[] bArr2 = bArr;
        final String str2 = str;
        final ActionToken actionToken2 = actionToken;
        return executeTask(new FmshAppTask(context) {
            /* access modifiers changed from: protected */
            public BaseResponse doInBackground() {
                LogUtils.d("doIssue() called!");
                CardAppManager service = getService();
                if (service != null) {
                    try {
                        service.switchMode2OMA(1, i4);
                        if (FmshCardClient.this.login(service, context2, i4)) {
                            int doIssueEx = service.doIssueEx(i4, i5, i6, bArr2, str2, Coder.hexStringToBytes(actionToken2.mToken));
                            if (doIssueEx != 1104) {
                                if (doIssueEx != 0) {
                                    return new BaseResponse(doIssueEx, new Object[0]);
                                }
                            }
                            return new BaseResponse(0, new Object[0]);
                        }
                    } catch (RemoteException e) {
                        LogUtils.e("exception occurred when doIssue() running", e);
                    }
                }
                return new BaseResponse(3, ResUtils.getString(context2, "service_unavailable"), new Object[0]);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public BaseResponse doIssue(Context context, int i, byte[] bArr, byte b, byte[] bArr2, byte[] bArr3, Bundle bundle) {
        if (context == null) {
            LogUtils.e("doIssue called, but the param is invalid!");
            return new BaseResponse(1, new Object[0]);
        }
        final int i2 = i;
        final Context context2 = context;
        final byte[] bArr4 = bArr;
        final byte b2 = b;
        final byte[] bArr5 = bArr2;
        final byte[] bArr6 = bArr3;
        return executeTask(new FmshAppTask(context) {
            /* access modifiers changed from: protected */
            public BaseResponse doInBackground() {
                LogUtils.d("doIssue() called!");
                CardAppManager service = getService();
                if (service != null) {
                    try {
                        service.switchMode2OMA(1, i2);
                        if (FmshCardClient.this.login(service, context2, i2)) {
                            int doIssue = service.doIssue(bArr4, b2, bArr5, bArr6);
                            if (doIssue != 1104) {
                                if (doIssue != 0) {
                                    return new BaseResponse(doIssue, new Object[0]);
                                }
                            }
                            return new BaseResponse(0, new Object[0]);
                        }
                    } catch (RemoteException e) {
                        LogUtils.e("exception occurred when doIssue() running", e);
                    }
                }
                return new BaseResponse(3, ResUtils.getString(context2, "service_unavailable"), new Object[0]);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public BaseResponse downloadApplet(Context context, int i, byte[] bArr, String str) {
        if (context == null) {
            LogUtils.e("downloadApplet called, but the param is invalid!");
            return new BaseResponse(1, new Object[0]);
        }
        final int i2 = i;
        final Context context2 = context;
        final byte[] bArr2 = bArr;
        final String str2 = str;
        return executeTask(new FmshAppTask(context) {
            /* access modifiers changed from: protected */
            public BaseResponse doInBackground() {
                LogUtils.d("downloadApplet() called!");
                CardAppManager service = getService();
                if (service != null) {
                    try {
                        service.switchMode2OMA(1, i2);
                        if (FmshCardClient.this.login(service, context2, i2)) {
                            return new BaseResponse(service.downloadApplet(i2, bArr2, str2), new Object[0]);
                        }
                    } catch (RemoteException e) {
                        LogUtils.e("exception occurred when doIssue() running", e);
                    }
                }
                return new BaseResponse(3, ResUtils.getString(context2, "service_unavailable"), new Object[0]);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public BaseResponse cancelIssue(final Context context, int i, final int i2) {
        if (context != null) {
            return executeTask(new FmshAppTask(context) {
                /* access modifiers changed from: protected */
                public BaseResponse doInBackground() {
                    LogUtils.d("cancelIssue() called!");
                    CardAppManager service = getService();
                    if (service != null) {
                        try {
                            return new BaseResponse(service.cancelIssue(i2), new Object[0]);
                        } catch (RemoteException e) {
                            LogUtils.e("exception occurred when cancelIssue() running", e);
                        }
                    }
                    return new BaseResponse(3, ResUtils.getString(context, "service_unavailable"), new Object[0]);
                }
            });
        }
        LogUtils.e("cancelIssue called, but the param  is invalid, context = null, stop: " + i2);
        return new BaseResponse(1, new Object[0]);
    }

    /* access modifiers changed from: package-private */
    public BaseResponse applyRecharge(Context context, int i, int i2, ActionToken actionToken, byte[] bArr, Tag tag) {
        if (context == null) {
            LogUtils.e("applyRecharge called, but the param is invalid! context = null!");
            return new BaseResponse(1, new Object[0]);
        }
        final NfcosMainOrder nfcosMainOrder = new NfcosMainOrder();
        final Tag tag2 = tag;
        final int i3 = i;
        final Context context2 = context;
        final ActionToken actionToken2 = actionToken;
        final int i4 = i2;
        final byte[] bArr2 = bArr;
        AnonymousClass7 r1 = new FmshAppTask(context) {
            /* access modifiers changed from: protected */
            public BaseResponse doInBackground() {
                LogUtils.d("applyRecharge() called!");
                CardAppManager service = getService();
                if (service != null) {
                    try {
                        if (tag2 != null) {
                            service.switchMode2NFC(tag2);
                        } else {
                            service.switchMode2OMA(1, i3);
                        }
                        if (!FmshCardClient.this.login(service, context2, i3)) {
                            return new BaseResponse(5, ResUtils.getString(context2, "service_unavailable"), new Object[0]);
                        }
                        int applyRecharge = service.applyRecharge(i3, actionToken2.mRechargeAmount, i4, bArr2, (byte[]) null, nfcosMainOrder);
                        if (applyRecharge == 1035 || applyRecharge == 1031) {
                            LogUtils.d("card has not activated!");
                            if (FmshCardClient.this.handleCardNotActivate(context2, service, (NfcosBusinessOrder) null, i3)) {
                                applyRecharge = service.applyRecharge(i3, actionToken2.mRechargeAmount, i4, bArr2, (byte[]) null, nfcosMainOrder);
                            }
                        }
                        return new BaseResponse(applyRecharge, nfcosMainOrder);
                    } catch (RemoteException e) {
                        LogUtils.e("exception occurred when applyRecharge() running", e);
                    }
                }
                return new BaseResponse(3, ResUtils.getString(context2, "service_unavailable"), new Object[0]);
            }
        };
        lock();
        try {
            BaseResponse executeTask = executeTask(r1);
            unLock();
            return executeTask;
        } catch (Throwable th) {
            Throwable th2 = th;
            unLock();
            throw th2;
        }
    }

    private NfcosBusinessOrder getIssueOrder(CardAppManager cardAppManager, int i, byte[] bArr) throws RemoteException {
        ArrayList arrayList = new ArrayList();
        cardAppManager.queryBusinessOrders(0, 10, i, new int[]{EnumOrderStatus.hasPaid.getId()}, EnumBusinessOrderType.ORDER_TYPE_ISSUE.getId(), bArr, arrayList);
        if (!arrayList.isEmpty()) {
            return (NfcosBusinessOrder) arrayList.get(0);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public boolean handleCardNotActivate(Context context, CardAppManager cardAppManager, NfcosBusinessOrder nfcosBusinessOrder, int i) throws RemoteException {
        byte[] bArr;
        byte b;
        try {
            bArr = getSeid(context);
        } catch (IOException e) {
            LogUtils.e("get seid failed!", e);
            bArr = null;
        }
        if (bArr == null) {
            return false;
        }
        if (nfcosBusinessOrder == null || nfcosBusinessOrder.businessOrderType != EnumBusinessOrderType.ORDER_TYPE_ISSUE.getId()) {
            nfcosBusinessOrder = getIssueOrder(cardAppManager, i, bArr);
        }
        if (nfcosBusinessOrder != null) {
            if (EnumCardAppType.CARD_APP_TYPE_SH.getId() == i) {
                b = 1;
            } else {
                b = EnumCardAppType.CARD_APP_TYPE_LNT.getId() == i ? (byte) 3 : 0;
            }
            if (bArr != null) {
                int doIssue = cardAppManager.doIssue(nfcosBusinessOrder.order, b, bArr, (byte[]) null);
                LogUtils.d("continue do issue,result:" + doIssue);
                if (doIssue == 0) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public BaseResponse queryIssueOrder(final Context context, final int i) {
        if (context != null) {
            return executeTask(new FmshAppTask(context) {
                /* access modifiers changed from: protected */
                public BaseResponse doInBackground() {
                    LogUtils.d("queryIssueOrder() called!");
                    CardAppManager service = getService();
                    if (service != null) {
                        try {
                            ArrayList arrayList = new ArrayList();
                            return new BaseResponse(service.queryBusinessOrders(0, 10, i, new int[]{EnumOrderStatus.success.getId()}, EnumBusinessOrderType.ORDER_TYPE_ISSUE.getId(), FmshCardClient.this.getSeid(context), arrayList), arrayList);
                        } catch (RemoteException e) {
                            LogUtils.e("RemoteException occurred when queryOrder() running", e);
                        } catch (IOException e2) {
                            LogUtils.e("IOException occurred when queryOrder() running", e2);
                        }
                    }
                    return new BaseResponse(3, ResUtils.getString(context, "service_unavailable"), new Object[0]);
                }
            });
        }
        LogUtils.e("queryIssueOrder called, but the param is invalid! context = null");
        return new BaseResponse(1, new Object[0]);
    }

    /* access modifiers changed from: package-private */
    public BaseResponse recharge(Context context, int i, ActionToken actionToken, byte[] bArr, int i2, Tag tag) {
        if (context == null || actionToken == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("recharge called, but the param is invalid! context == null:");
            sb.append(context == null);
            sb.append(",order == null:");
            sb.append(actionToken == null);
            LogUtils.e(sb.toString());
            return new BaseResponse(1, new Object[0]);
        }
        final Tag tag2 = tag;
        final int i3 = i;
        final byte[] bArr2 = bArr;
        final ActionToken actionToken2 = actionToken;
        final int i4 = i2;
        final Context context2 = context;
        AnonymousClass9 r1 = new FmshAppTask(context) {
            /* access modifiers changed from: protected */
            /* JADX WARNING: Removed duplicated region for block: B:18:0x0042 A[Catch:{ RemoteException -> 0x00bb }] */
            /* JADX WARNING: Removed duplicated region for block: B:19:0x0044 A[Catch:{ RemoteException -> 0x00bb }] */
            /* JADX WARNING: Removed duplicated region for block: B:29:0x0095 A[Catch:{ RemoteException -> 0x00bb }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.miui.tsmclient.model.BaseResponse doInBackground() {
                /*
                    r9 = this;
                    java.lang.String r0 = "recharge() called!"
                    com.miui.tsmclient.util.LogUtils.d(r0)
                    cn.com.fmsh.nfcos.client.service.xm.CardAppManager r0 = r9.getService()
                    r7 = 0
                    if (r0 == 0) goto L_0x00c1
                    android.nfc.Tag r1 = r4     // Catch:{ RemoteException -> 0x00bb }
                    r2 = 1
                    if (r1 == 0) goto L_0x0017
                    android.nfc.Tag r1 = r4     // Catch:{ RemoteException -> 0x00bb }
                    r0.switchMode2NFC(r1)     // Catch:{ RemoteException -> 0x00bb }
                    goto L_0x001c
                L_0x0017:
                    int r1 = r5     // Catch:{ RemoteException -> 0x00bb }
                    r0.switchMode2OMA(r2, r1)     // Catch:{ RemoteException -> 0x00bb }
                L_0x001c:
                    byte[] r1 = r6     // Catch:{ RemoteException -> 0x00bb }
                    if (r1 == 0) goto L_0x0026
                    int r3 = r1.length     // Catch:{ RemoteException -> 0x00bb }
                    if (r3 != 0) goto L_0x0024
                    goto L_0x0026
                L_0x0024:
                    r8 = r1
                    goto L_0x0033
                L_0x0026:
                    cn.com.fmsh.nfcos.client.service.xm.CardAppInfo r1 = new cn.com.fmsh.nfcos.client.service.xm.CardAppInfo     // Catch:{ RemoteException -> 0x00bb }
                    r1.<init>()     // Catch:{ RemoteException -> 0x00bb }
                    int r3 = r5     // Catch:{ RemoteException -> 0x00bb }
                    r0.getInfo(r2, r3, r1)     // Catch:{ RemoteException -> 0x00bb }
                    byte[] r1 = r1.appNo     // Catch:{ RemoteException -> 0x00bb }
                    goto L_0x0024
                L_0x0033:
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ RemoteException -> 0x00bb }
                    r1.<init>()     // Catch:{ RemoteException -> 0x00bb }
                    java.lang.String r2 = "before applyRechargeEx, cardAppNo is "
                    r1.append(r2)     // Catch:{ RemoteException -> 0x00bb }
                    if (r8 == 0) goto L_0x0044
                    int r2 = r8.length     // Catch:{ RemoteException -> 0x00bb }
                    if (r2 <= 0) goto L_0x0044
                    r2 = r8
                    goto L_0x0046
                L_0x0044:
                    java.lang.String r2 = "null"
                L_0x0046:
                    r1.append(r2)     // Catch:{ RemoteException -> 0x00bb }
                    java.lang.String r1 = r1.toString()     // Catch:{ RemoteException -> 0x00bb }
                    com.miui.tsmclient.util.LogUtils.d(r1)     // Catch:{ RemoteException -> 0x00bb }
                    int r2 = r5     // Catch:{ RemoteException -> 0x00bb }
                    com.miui.tsmclient.entity.ActionToken r1 = r7     // Catch:{ RemoteException -> 0x00bb }
                    int r3 = r1.mRechargeAmount     // Catch:{ RemoteException -> 0x00bb }
                    int r4 = r8     // Catch:{ RemoteException -> 0x00bb }
                    com.miui.tsmclient.entity.ActionToken r1 = r7     // Catch:{ RemoteException -> 0x00bb }
                    java.lang.String r1 = r1.mToken     // Catch:{ RemoteException -> 0x00bb }
                    byte[] r6 = com.tsmclient.smartcard.Coder.hexStringToBytes(r1)     // Catch:{ RemoteException -> 0x00bb }
                    r1 = r0
                    r5 = r8
                    int r1 = r1.applyRechargeEx(r2, r3, r4, r5, r6)     // Catch:{ RemoteException -> 0x00bb }
                    r2 = 1035(0x40b, float:1.45E-42)
                    if (r1 == r2) goto L_0x006e
                    r2 = 1031(0x407, float:1.445E-42)
                    if (r1 != r2) goto L_0x0091
                L_0x006e:
                    com.miui.tsmclient.model.fmsh.FmshCardClient r2 = com.miui.tsmclient.model.fmsh.FmshCardClient.this     // Catch:{ RemoteException -> 0x00bb }
                    android.content.Context r3 = r9     // Catch:{ RemoteException -> 0x00bb }
                    r4 = 0
                    int r5 = r5     // Catch:{ RemoteException -> 0x00bb }
                    boolean r2 = r2.handleCardNotActivate(r3, r0, r4, r5)     // Catch:{ RemoteException -> 0x00bb }
                    if (r2 == 0) goto L_0x0091
                    int r2 = r5     // Catch:{ RemoteException -> 0x00bb }
                    com.miui.tsmclient.entity.ActionToken r1 = r7     // Catch:{ RemoteException -> 0x00bb }
                    int r3 = r1.mRechargeAmount     // Catch:{ RemoteException -> 0x00bb }
                    int r4 = r8     // Catch:{ RemoteException -> 0x00bb }
                    com.miui.tsmclient.entity.ActionToken r1 = r7     // Catch:{ RemoteException -> 0x00bb }
                    java.lang.String r1 = r1.mToken     // Catch:{ RemoteException -> 0x00bb }
                    byte[] r6 = com.tsmclient.smartcard.Coder.hexStringToBytes(r1)     // Catch:{ RemoteException -> 0x00bb }
                    r1 = r0
                    r5 = r8
                    int r1 = r1.applyRechargeEx(r2, r3, r4, r5, r6)     // Catch:{ RemoteException -> 0x00bb }
                L_0x0091:
                    r0 = 1104(0x450, float:1.547E-42)
                    if (r1 == r0) goto L_0x00b3
                    if (r1 != 0) goto L_0x0098
                    goto L_0x00b3
                L_0x0098:
                    r0 = 1001(0x3e9, float:1.403E-42)
                    if (r8 == 0) goto L_0x009f
                    int r2 = r8.length     // Catch:{ RemoteException -> 0x00bb }
                    if (r2 != 0) goto L_0x00ab
                L_0x009f:
                    r2 = 9910(0x26b6, float:1.3887E-41)
                    if (r1 != r2) goto L_0x00ab
                    com.miui.tsmclient.model.BaseResponse r1 = new com.miui.tsmclient.model.BaseResponse     // Catch:{ RemoteException -> 0x00bb }
                    java.lang.Object[] r2 = new java.lang.Object[r7]     // Catch:{ RemoteException -> 0x00bb }
                    r1.<init>(r0, r2)     // Catch:{ RemoteException -> 0x00bb }
                    return r1
                L_0x00ab:
                    com.miui.tsmclient.model.BaseResponse r1 = new com.miui.tsmclient.model.BaseResponse     // Catch:{ RemoteException -> 0x00bb }
                    java.lang.Object[] r2 = new java.lang.Object[r7]     // Catch:{ RemoteException -> 0x00bb }
                    r1.<init>(r0, r2)     // Catch:{ RemoteException -> 0x00bb }
                    return r1
                L_0x00b3:
                    com.miui.tsmclient.model.BaseResponse r0 = new com.miui.tsmclient.model.BaseResponse     // Catch:{ RemoteException -> 0x00bb }
                    java.lang.Object[] r1 = new java.lang.Object[r7]     // Catch:{ RemoteException -> 0x00bb }
                    r0.<init>(r7, r1)     // Catch:{ RemoteException -> 0x00bb }
                    return r0
                L_0x00bb:
                    r0 = move-exception
                    java.lang.String r1 = "exception occurred when recharge() running"
                    com.miui.tsmclient.util.LogUtils.e(r1, r0)
                L_0x00c1:
                    com.miui.tsmclient.model.BaseResponse r0 = new com.miui.tsmclient.model.BaseResponse
                    r1 = 3
                    android.content.Context r2 = r9
                    java.lang.String r3 = "service_unavailable"
                    java.lang.String r2 = com.miui.tsmclient.util.ResUtils.getString(r2, r3)
                    java.lang.Object[] r3 = new java.lang.Object[r7]
                    r0.<init>(r1, r2, r3)
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclient.model.fmsh.FmshCardClient.AnonymousClass9.doInBackground():com.miui.tsmclient.model.BaseResponse");
            }
        };
        lock();
        try {
            BaseResponse executeTask = executeTask(r1);
            unLock();
            return executeTask;
        } catch (Throwable th) {
            Throwable th2 = th;
            unLock();
            throw th2;
        }
    }

    /* access modifiers changed from: package-private */
    public BaseResponse queryUnsolvedOrder(Context context, int i, Tag tag) {
        if (context == null) {
            LogUtils.e("queryUnsolvedOrder called, but the param is invalid! context is null!");
            return new BaseResponse(1, new Object[0]);
        }
        final ArrayList arrayList = new ArrayList();
        final Context context2 = context;
        final int i2 = i;
        final Tag tag2 = tag;
        return executeTask(new FmshAppTask(context) {
            /* access modifiers changed from: protected */
            public BaseResponse doInBackground() {
                LogUtils.d("queryUnsolvedOrder() called!");
                CardAppManager service = getService();
                if (service != null) {
                    try {
                        ArrayList arrayList = new ArrayList();
                        if (FmshCardClient.this.login(service, context2, i2)) {
                            int[] iArr = {EnumOrderStatus.hasPaid.getId(), EnumOrderStatus.failure.getId(), EnumOrderStatus.unsettled.getId(), EnumOrderStatus.dubious.getId()};
                            int queryBusinessOrders = service.queryBusinessOrders(0, 10, i2, iArr, EnumBusinessOrderType.UNKNOW.getId(), FmshCardClient.this.getSeid(context2), arrayList);
                            CardAppInfo cardAppInfo = new CardAppInfo();
                            service.getInfo(i2, 1, cardAppInfo);
                            if (cardAppInfo.appNo != null) {
                                ArrayList arrayList2 = new ArrayList();
                                queryBusinessOrders = service.queryBusinessOrders(0, 10, i2, iArr, EnumBusinessOrderType.UNKNOW.getId(), cardAppInfo.appNo, arrayList2);
                                arrayList.addAll(arrayList2);
                            }
                            if (!arrayList.isEmpty()) {
                                for (NfcosBusinessOrder nfcosBusinessOrder : arrayList) {
                                    if (FmshCardClient.this.isSameCardIOType(tag2, nfcosBusinessOrder)) {
                                        if (nfcosBusinessOrder.businessOrderType == EnumBusinessOrderType.ORDER_TYPE_ISSUE.getId() || nfcosBusinessOrder.businessOrderType == EnumBusinessOrderType.ORDER_TYPE_RECHARGE.getId()) {
                                            arrayList.add(nfcosBusinessOrder);
                                        }
                                    }
                                }
                            }
                            return new BaseResponse(queryBusinessOrders, arrayList);
                        }
                    } catch (RemoteException e) {
                        LogUtils.e("exception occurred when queryUnsolvedOrder() running", e);
                    } catch (IOException e2) {
                        LogUtils.e("IOException occurred when queryUnsolvedOrder() running", e2);
                    }
                }
                return new BaseResponse(3, ResUtils.getString(context2, "service_unavailable"), new Object[0]);
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean isSameCardIOType(Tag tag, NfcosBusinessOrder nfcosBusinessOrder) {
        return (tag != null && nfcosBusinessOrder.cardIoType == EnumCardIoType.CARD_IO_TYPE_OUT.getId()) || (tag == null && nfcosBusinessOrder.cardIoType == EnumCardIoType.CARD_IO_TYPE_IN.getId());
    }

    /* access modifiers changed from: private */
    public int handleUnsolvedOrder(Context context, CardAppManager cardAppManager, int i, NfcosBusinessOrder nfcosBusinessOrder, FmshCardInfo fmshCardInfo) throws RemoteException {
        int i2 = -2;
        if (nfcosBusinessOrder.businessOrderType != EnumBusinessOrderType.ORDER_TYPE_ISSUE.getId()) {
            byte[] bArr = fmshCardInfo.mAppNo;
            if (nfcosBusinessOrder.tradeState == EnumOrderStatus.hasPaid.getId() || nfcosBusinessOrder.tradeState == EnumOrderStatus.failure.getId()) {
                LogUtils.d("order has applyRecharge success,so recharge directly");
                if (bArr == null || bArr.length == 0) {
                    CardAppInfo cardAppInfo = new CardAppInfo();
                    cardAppManager.getInfo(1, i, cardAppInfo);
                    bArr = cardAppInfo.appNo;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("before recharge, cardAppNo is ");
                sb.append((bArr == null || bArr.length <= 0) ? "null" : bArr);
                LogUtils.d(sb.toString());
                i2 = cardAppManager.recharge(nfcosBusinessOrder.order, bArr);
                if (i2 == 1035 || i2 == 1031) {
                    LogUtils.d("card has not activated!");
                    if (handleCardNotActivate(context, cardAppManager, nfcosBusinessOrder, i)) {
                        i2 = cardAppManager.recharge(nfcosBusinessOrder.order, bArr);
                    }
                }
            } else if (nfcosBusinessOrder.tradeState == EnumOrderStatus.unsettled.getId()) {
                LogUtils.d("order status is unkown!");
                i2 = cardAppManager.doUnsolvedOrder(nfcosBusinessOrder.order);
                if (i2 == 0) {
                    NfcosBusinessOrder nfcosBusinessOrder2 = new NfcosBusinessOrder();
                    cardAppManager.queryBusinessOrder(nfcosBusinessOrder.order, nfcosBusinessOrder2);
                    LogUtils.d("doUnsolvedOrder success,now the order state is:" + nfcosBusinessOrder2.tradeState);
                    handleUnsolvedOrder(context, cardAppManager, i, nfcosBusinessOrder2, fmshCardInfo);
                }
            }
            if (i2 == 1104) {
                i2 = 0;
            }
            if ((bArr == null || bArr.length == 0) && i2 == 9910) {
                return 1001;
            }
            return i2;
        } else if (handleCardNotActivate(context, cardAppManager, nfcosBusinessOrder, i)) {
            return 0;
        } else {
            return -2;
        }
    }

    /* access modifiers changed from: package-private */
    public BaseResponse handleUnsolvedOrder(Context context, int i, FmshCardInfo fmshCardInfo, Tag tag, NfcosBusinessOrder nfcosBusinessOrder) {
        if (context == null || nfcosBusinessOrder == null) {
            LogUtils.e("handleUnsolvedOrder called, but the param is invalid!");
            return new BaseResponse(1, new Object[0]);
        }
        final Tag tag2 = tag;
        final int i2 = i;
        final Context context2 = context;
        final NfcosBusinessOrder nfcosBusinessOrder2 = nfcosBusinessOrder;
        final FmshCardInfo fmshCardInfo2 = fmshCardInfo;
        return executeTask(new FmshAppTask(context) {
            /* access modifiers changed from: protected */
            public BaseResponse doInBackground() {
                LogUtils.d("handleUnsolvedOrder() called!");
                CardAppManager service = getService();
                if (service != null) {
                    try {
                        if (tag2 != null) {
                            service.switchMode2NFC(tag2);
                        } else {
                            service.switchMode2OMA(1, i2);
                        }
                        if (FmshCardClient.this.login(service, context2, i2)) {
                            FmshCardClient.this.lock();
                            int access$300 = FmshCardClient.this.handleUnsolvedOrder(context2, service, i2, nfcosBusinessOrder2, fmshCardInfo2);
                            FmshCardClient.this.unLock();
                            if (access$300 == 1030) {
                                access$300 = 2002;
                            }
                            return new BaseResponse(access$300, nfcosBusinessOrder2);
                        }
                    } catch (RemoteException e) {
                        LogUtils.e("exception occurred when handleUnsolvedOrder() running", e);
                    } catch (Throwable th) {
                        FmshCardClient.this.unLock();
                        throw th;
                    }
                }
                return new BaseResponse(3, ResUtils.getString(context2, "service_unavailable"), new Object[0]);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public BaseResponse getInfo(Context context, int i, int i2) {
        if (context == null || i2 < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("getInfo called, but the param is invalid! context == null:");
            sb.append(context == null);
            sb.append(",type:");
            sb.append(i2);
            LogUtils.e(sb.toString());
            return new BaseResponse(1, new Object[0]);
        }
        final CardAppInfo cardAppInfo = new CardAppInfo();
        final int i3 = i;
        final int i4 = i2;
        final Context context2 = context;
        AnonymousClass12 r0 = new FmshAppTask(context) {
            /* access modifiers changed from: protected */
            public BaseResponse doInBackground() {
                LogUtils.d("getInfo() called!");
                CardAppManager service = getService();
                if (service != null) {
                    try {
                        service.switchMode2OMA(1, i3);
                        return new BaseResponse(service.getInfo(i4, i3, cardAppInfo), cardAppInfo);
                    } catch (RemoteException e) {
                        LogUtils.e("exception occurred when getInfo() running", e);
                    }
                }
                return new BaseResponse(3, ResUtils.getString(context2, "service_unavailable"), new Object[0]);
            }
        };
        lock();
        try {
            return executeTask(r0);
        } finally {
            unLock();
        }
    }

    /* access modifiers changed from: package-private */
    public BaseResponse moveApp(Context context, byte[] bArr, int i, byte[] bArr2, String str, FmshCardInfo fmshCardInfo, Bundle bundle) {
        if (context == null) {
            LogUtils.e("moveApp called, but the param is invalid!");
            return new BaseResponse(1, new Object[0]);
        }
        final Context context2 = context;
        final int i2 = i;
        final byte[] bArr3 = bArr;
        final byte[] bArr4 = bArr2;
        final String str2 = str;
        final FmshCardInfo fmshCardInfo2 = fmshCardInfo;
        final Bundle bundle2 = bundle;
        AnonymousClass13 r1 = new FmshAppTask(context) {
            /* access modifiers changed from: protected */
            public BaseResponse doInBackground() {
                LogUtils.d("moveApp() called!");
                CardAppManager service = getService();
                if (service != null) {
                    try {
                        if (FmshCardClient.this.login(service, context2, i2)) {
                            service.switchMode2OMA(1, i2);
                            VoucherInfo voucherInfo = new VoucherInfo();
                            int moveApp = service.moveApp(bArr3, i2, bArr4, str2, voucherInfo);
                            if (moveApp == 0) {
                                SysUtils.clearCardCache(context2, fmshCardInfo2);
                            }
                            LogUtils.d("fmsh moveApp called! resultCode:" + moveApp + ", token:" + Coder.bytesToHexString(voucherInfo.token));
                            return FmshCardClient.this.uploadTransferOutResult(context2, fmshCardInfo2, bundle2, new BaseResponse(moveApp, new Object[0]));
                        }
                    } catch (RemoteException e) {
                        LogUtils.e("exception occurred when moveApp() running", e);
                    }
                }
                return new BaseResponse(3, ResUtils.getString(context2, "service_unavailable"), new Object[0]);
            }
        };
        lock();
        try {
            BaseResponse executeTask = executeTask(r1);
            unLock();
            return executeTask;
        } catch (Throwable th) {
            Throwable th2 = th;
            unLock();
            throw th2;
        }
    }

    /* access modifiers changed from: private */
    public BaseResponse uploadTransferOutResult(Context context, CardInfo cardInfo, Bundle bundle, BaseResponse baseResponse) {
        return (bundle == null || !bundle.getBoolean(Constants.EXTRAS_KEY_WITHDRAW, false) || baseResponse.mResultCode != 0) ? baseResponse : this.mTSMAuthManager.uploadTransferOutResult(context, cardInfo);
    }

    static int getCardAppType(String str) {
        if (CardInfo.SPTC_TYPE_SET.contains(str)) {
            return EnumCardAppType.CARD_APP_TYPE_SH.getId();
        }
        return 0;
    }

    public byte[] getSeid(Context context) throws IOException {
        try {
            String cplc = this.mCardInfo.getTerminal().getCPLC();
            StringBuilder sb = new StringBuilder();
            sb.append(Coder.encodeMD5(Coder.hexStringToBytes(cplc)).substring(0, 20));
            sb.append(cplc.substring(0, 4));
            sb.append(cplc.substring(20, 36));
            LogUtils.d("seid:" + sb.toString());
            return Coder.hexStringToBytes(sb.toString());
        } catch (InterruptedException unused) {
            throw new IOException("getSeid is interrupted");
        }
    }

    private FmshUserInfo getFmshUserInfo(Context context, CardAppManager cardAppManager, int i) {
        try {
            return this.mTSMAuthManager.getFmshUserInfo(context.getApplicationContext(), this.mBussinessType, getAppNo(cardAppManager, i));
        } catch (AuthApiException e) {
            LogUtils.e("AuthApiException occurred when get FmshUserInfo, code = " + e.mErrorCode, e);
            return null;
        } catch (RemoteException e2) {
            LogUtils.e("RemoteException occurred when get FmshUserInfo", e2);
            return null;
        }
    }

    private String getAppNo(CardAppManager cardAppManager, int i) throws RemoteException {
        CardAppInfo cardAppInfo = new CardAppInfo();
        if (TextUtils.equals(TSMAuthContants.BusinessType.sptc.toString(), this.mBussinessType)) {
            cardAppManager.switchMode2OMA(1, i);
            cardAppManager.getInfo(1, i, cardAppInfo);
        }
        return Coder.bytesToHexString(cardAppInfo.appNo);
    }

    /* access modifiers changed from: private */
    public void lock() {
        LogUtils.d("Thread id: " + Thread.currentThread().getId() + " is Acquiring a permit");
        sSemaphore.acquireUninterruptibly();
        LogUtils.d("Acquire success!");
    }

    /* access modifiers changed from: private */
    public void unLock() {
        LogUtils.d("Thread id: " + Thread.currentThread().getId() + " is release a permit");
        sSemaphore.release();
    }
}
