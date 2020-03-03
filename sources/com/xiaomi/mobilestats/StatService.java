package com.xiaomi.mobilestats;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import com.xiaomi.mobilestats.common.AESUtils;
import com.xiaomi.mobilestats.common.CommonUtil;
import com.xiaomi.mobilestats.common.DeviceUtil;
import com.xiaomi.mobilestats.common.EncodeProtoUtil;
import com.xiaomi.mobilestats.common.HostManager;
import com.xiaomi.mobilestats.common.NetType;
import com.xiaomi.mobilestats.common.NetworkUtil;
import com.xiaomi.mobilestats.common.StrUtil;
import com.xiaomi.mobilestats.controller.LogController;
import com.xiaomi.mobilestats.data.BasicStoreTools;
import com.xiaomi.mobilestats.data.DataCore;
import com.xiaomi.mobilestats.data.ProtoMsg;
import com.xiaomi.mobilestats.data.SendStrategyEnum;
import com.xiaomi.mobilestats.data.WriteFileThread;
import com.xiaomi.mobilestats.object.GSMCell;
import com.xiaomi.mobilestats.object.Msg;
import com.xiaomi.mobilestats.object.PageItem;
import com.ximalaya.ting.android.opensdk.player.statistic.OpenSdkPlayStatisticUpload;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class StatService {
    public static final int MSG_TIMER = 4096;

    /* renamed from: a  reason: collision with root package name */
    private static boolean f12075a = true;
    private static WeakHashMap b = new WeakHashMap();
    private static HashMap c = new HashMap();
    private static HandlerThread d = new HandlerThread("XMAgent");
    private static StatService e = new StatService();
    public static Handler handler = new a(d.getLooper());
    public static Context sApplicationContext;

    private StatService() {
        if (d != null) {
            d.start();
        }
    }

    private static void a(int i, PageItem pageItem) {
        if (pageItem != null && b != null) {
            if (b.containsKey(Integer.valueOf(i))) {
                b.remove(Integer.valueOf(i));
            }
            b.put(Integer.valueOf(i), pageItem);
        }
    }

    /* access modifiers changed from: private */
    public static void a(Context context) {
        if (f12075a && context != null) {
            PageItem pageItem = new PageItem();
            pageItem.startTime = System.currentTimeMillis();
            a(context.hashCode(), pageItem);
        }
    }

    /* access modifiers changed from: private */
    public static void a(Context context, int i, String str, HashMap hashMap) {
        if (f12075a && context != null) {
            PageItem pageItem = new PageItem();
            pageItem.pageName = str;
            pageItem.startTime = System.currentTimeMillis();
            if (hashMap != null) {
                pageItem.map = hashMap;
            }
            a(i, pageItem);
        }
    }

    private static void a(Context context, Msg msg) {
        byte[] desEncrypt;
        ProtoMsg.StatsMsg.ClientResponse parseFrom;
        if (msg != null && context != null) {
            byte[] responseBytes = msg.getResponseBytes();
            byte[] bArr = new byte[8];
            byte[] bArr2 = new byte[16];
            if (responseBytes != null && responseBytes.length > 24) {
                byte[] bArr3 = new byte[(responseBytes.length - 24)];
                try {
                    System.arraycopy(responseBytes, 0, bArr, 0, 8);
                    System.arraycopy(responseBytes, 8, bArr2, 0, 16);
                    System.arraycopy(responseBytes, 24, bArr3, 0, bArr3.length);
                    if (bArr2.length == 16 && bArr3.length > 0 && (desEncrypt = AESUtils.desEncrypt(bArr3, AESUtils.ENCODE_KEY, bArr2)) != null && (parseFrom = ProtoMsg.StatsMsg.ClientResponse.parseFrom(desEncrypt)) != null) {
                        ProtoMsg.StatsMsg.ReportStrategy strategy = parseFrom.getStrategy();
                        int interval = parseFrom.getInterval();
                        boolean isWifi = parseFrom.getIsWifi();
                        BasicStoreTools.getInstance().setCRKey(context, parseFrom.getKey());
                        if (strategy != null) {
                            SendStrategyEnum sendStrategyEnum = SendStrategyEnum.APP_START;
                            if (strategy.equals(ProtoMsg.StatsMsg.ReportStrategy.REAL_TIME)) {
                                sendStrategyEnum = SendStrategyEnum.REAL_TIME;
                            } else if (strategy.equals(ProtoMsg.StatsMsg.ReportStrategy.APP_START)) {
                                sendStrategyEnum = SendStrategyEnum.APP_START;
                            } else if (strategy.equals(ProtoMsg.StatsMsg.ReportStrategy.ONCE_PER_DAY)) {
                                sendStrategyEnum = SendStrategyEnum.ONCE_A_DAY;
                            } else if (strategy.equals(ProtoMsg.StatsMsg.ReportStrategy.SET_TIME_INTERVAL)) {
                                sendStrategyEnum = SendStrategyEnum.SET_TIME_INTERVAL;
                            }
                            LogController.geInstance().setSendStrategy(context, sendStrategyEnum, (long) interval, isWifi);
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static void a(Context context, Exception exc, HashMap hashMap) {
        ProtoMsg.StatsMsg.ErrorMsg extensibleErrorProtoInfo;
        Msg postProtoInfo;
        if (f12075a && context != null && (extensibleErrorProtoInfo = EncodeProtoUtil.getExtensibleErrorProtoInfo(context, exc, hashMap)) != null) {
            if (!c(context) || ((LogController.isOnlyWifi && !CommonUtil.isWiFiActive(context)) || ((postProtoInfo = NetworkUtil.postProtoInfo(11, StrUtil.encodeBytesData(extensibleErrorProtoInfo.toByteArray()))) != null && !postProtoInfo.isFlag()))) {
                saveProtoInfoToFile(context, "error", extensibleErrorProtoInfo.toByteArray());
            }
        }
    }

    /* access modifiers changed from: private */
    public static void a(Context context, String str, String str2) {
        ProtoMsg.StatsMsg.EventMsg eventProtoInfo;
        Msg postProtoInfo;
        if (f12075a && context != null && (eventProtoInfo = EncodeProtoUtil.getEventProtoInfo(context, str, str2, (HashMap) null)) != null) {
            if (!c(context) || ((LogController.isOnlyWifi && !CommonUtil.isWiFiActive(context)) || ((postProtoInfo = NetworkUtil.postProtoInfo(7, StrUtil.encodeBytesData(eventProtoInfo.toByteArray()))) != null && !postProtoInfo.isFlag()))) {
                saveProtoInfoToFile(context, "event", eventProtoInfo.toByteArray());
            }
        }
    }

    /* access modifiers changed from: private */
    public static void a(Context context, String str, String str2, HashMap hashMap) {
        ProtoMsg.StatsMsg.EventMsg eventProtoInfo;
        Msg postProtoInfo;
        if (f12075a && context != null && (eventProtoInfo = EncodeProtoUtil.getEventProtoInfo(context, str, str2, hashMap)) != null) {
            if (!c(context) || ((LogController.isOnlyWifi && !CommonUtil.isWiFiActive(context)) || ((postProtoInfo = NetworkUtil.postProtoInfo(7, StrUtil.encodeBytesData(eventProtoInfo.toByteArray()))) != null && !postProtoInfo.isFlag()))) {
                saveProtoInfoToFile(context, "event", eventProtoInfo.toByteArray());
            }
        }
    }

    /* access modifiers changed from: private */
    public static void a(Context context, boolean z, String str, HashMap hashMap, String str2) {
        ProtoMsg.StatsMsg.ClientMsg clientDataInfo;
        if (f12075a && context != null && (clientDataInfo = EncodeProtoUtil.getClientDataInfo(context, z, str, hashMap, str2)) != null) {
            Msg postProtoInfo = NetworkUtil.postProtoInfo(9, StrUtil.encodeBytesData(clientDataInfo.toByteArray()));
            if (postProtoInfo == null || !postProtoInfo.isFlag()) {
                ProtoMsg.StatsMsg.ClientMsg b2 = b(context, z, str, hashMap, str2);
                if (b2 != null) {
                    saveProtoInfoToFile(context, OpenSdkPlayStatisticUpload.r, b2.toByteArray());
                } else {
                    saveProtoInfoToFile(context, OpenSdkPlayStatisticUpload.r, clientDataInfo.toByteArray());
                }
            } else {
                a(context, postProtoInfo);
            }
        }
    }

    private static void a(ProtoMsg.StatsMsg.Page.Builder builder, HashMap hashMap) {
        if (hashMap != null && !hashMap.isEmpty()) {
            for (Map.Entry entry : hashMap.entrySet()) {
                ProtoMsg.StatsMsg.ProtoMap.Builder newBuilder = ProtoMsg.StatsMsg.ProtoMap.newBuilder();
                newBuilder.setKey((String) entry.getKey());
                newBuilder.setValue((String) entry.getValue());
                builder.addPageMap(newBuilder);
            }
        }
    }

    private static ProtoMsg.StatsMsg.ClientMsg b(Context context, boolean z, String str, HashMap hashMap, String str2) {
        if (hashMap == null) {
            hashMap = new HashMap();
        }
        hashMap.put("isSavedClient", "true");
        return EncodeProtoUtil.getClientDataInfo(context, z, str, hashMap, str2);
    }

    /* access modifiers changed from: private */
    public static void b(Context context) {
        PageItem pageItem;
        Msg postProtoInfo;
        if (f12075a && context != null && b != null && context != null && b.containsKey(Integer.valueOf(context.hashCode())) && (pageItem = (PageItem) b.get(Integer.valueOf(context.hashCode()))) != null) {
            pageItem.endTime = System.currentTimeMillis();
            pageItem.duration = System.currentTimeMillis() - pageItem.endTime;
            ProtoMsg.StatsMsg.PageMsg pageProtoInfo = getPageProtoInfo(context, pageItem, context.getClass().getName(), (HashMap) null);
            if (pageProtoInfo != null) {
                if (!c(context) || ((LogController.isOnlyWifi && !CommonUtil.isWiFiActive(context)) || ((postProtoInfo = NetworkUtil.postProtoInfo(5, StrUtil.encodeBytesData(pageProtoInfo.toByteArray()))) != null && !postProtoInfo.isFlag()))) {
                    saveProtoInfoToFile(context, "page", pageProtoInfo.toByteArray());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static void b(Context context, int i, String str, HashMap hashMap) {
        PageItem pageItem;
        Msg postProtoInfo;
        if (f12075a && context != null && b != null && b.containsKey(Integer.valueOf(i)) && (pageItem = (PageItem) b.remove(Integer.valueOf(i))) != null) {
            pageItem.endTime = System.currentTimeMillis();
            pageItem.duration = System.currentTimeMillis() - pageItem.startTime;
            if (hashMap != null) {
                pageItem.map = hashMap;
            }
            ProtoMsg.StatsMsg.PageMsg pageProtoInfo = getPageProtoInfo(context, pageItem, str, hashMap);
            if (pageProtoInfo != null) {
                if (!c(context) || ((LogController.isOnlyWifi && !CommonUtil.isWiFiActive(context)) || ((postProtoInfo = NetworkUtil.postProtoInfo(5, StrUtil.encodeBytesData(pageProtoInfo.toByteArray()))) != null && !postProtoInfo.isFlag()))) {
                    saveProtoInfoToFile(context, "page", pageProtoInfo.toByteArray());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static void b(Context context, String str, String str2, HashMap hashMap) {
        ProtoMsg.StatsMsg.EventMsg eventProtoInfo;
        if (f12075a && context != null && (eventProtoInfo = EncodeProtoUtil.getEventProtoInfo(context, str, str2, hashMap)) != null) {
            NetworkUtil.postProtoInfo(7, StrUtil.encodeBytesData(eventProtoInfo.toByteArray()));
        }
    }

    /* access modifiers changed from: private */
    public static void c(Context context, String str, String str2, HashMap hashMap) {
        ProtoMsg.StatsMsg.ViewMsg viewProtoInfo;
        if (f12075a && context != null && (viewProtoInfo = EncodeProtoUtil.getViewProtoInfo(context, str, str2, hashMap)) != null) {
            NetworkUtil.postProtoInfo(17, StrUtil.encodeBytesData(viewProtoInfo.toByteArray()));
        }
    }

    private static boolean c(Context context) {
        return LogController.geInstance().sendStragegy.equals(SendStrategyEnum.REAL_TIME) && CommonUtil.isNetworkAvailable(context) && !NetType.isNet2G_DOWN(context);
    }

    public static StatService getInstance() {
        return e;
    }

    public static ProtoMsg.StatsMsg.PageMsg getPageProtoInfo(Context context, PageItem pageItem, String str, HashMap hashMap) {
        String str2;
        if (pageItem != null) {
            b.remove(Integer.valueOf(pageItem.hashCode()));
            try {
                String str3 = TextUtils.isEmpty(pageItem.pageName) ? "" : pageItem.pageName;
                ProtoMsg.StatsMsg.PageMsg.Builder newBuilder = ProtoMsg.StatsMsg.PageMsg.newBuilder();
                ProtoMsg.StatsMsg.Page.Builder newBuilder2 = ProtoMsg.StatsMsg.Page.newBuilder();
                ProtoMsg.StatsMsg.Page.Builder deviceId = newBuilder2.setType("page").setUserId(DataCore.getUserId(context)).setSessionId(DataCore.getVMIMEI(context) + "").setDeviceId(DeviceUtil.getInstance().getUniqueId(context));
                if (TextUtils.isEmpty(str)) {
                    str = str3;
                }
                deviceId.setPageId(str).setStartMills(pageItem.startTime).setEndMills(pageItem.endTime).setDuration(pageItem.duration).setPlatform("android").setAppKey(DataCore.getAppkey(context)).setAppChannel(DataCore.getAppChannel(context)).setPackageName(DataCore.getPackageName(context)).setVersionCode(DataCore.getAppVersionCode(context)).setVersionName(DataCore.getAppVersionName(context) + "").setSdkVerson(DataCore.getSDKVersion() + "").setOsVersion(DataCore.getOSSysVersion() + "").setDeviceName(CommonUtil.getDeviceName());
                try {
                    GSMCell cellInfo = CommonUtil.getCellInfo(context);
                    newBuilder2.setMccMnc(cellInfo != null ? cellInfo.MCCMNC : "");
                    if (cellInfo != null) {
                        str2 = cellInfo.CID + "";
                    } else {
                        str2 = "";
                    }
                    newBuilder2.setCellId(str2);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                a(newBuilder2, getInstance().getInitMap());
                a(newBuilder2, pageItem.map);
                a(newBuilder2, hashMap);
                newBuilder.addPage(newBuilder2);
                return newBuilder.build();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        return null;
    }

    public static void initExtraMap(HashMap hashMap) {
        if (f12075a && hashMap != null) {
            for (Map.Entry entry : hashMap.entrySet()) {
                c.put((String) entry.getKey(), (String) entry.getValue());
            }
        }
    }

    public static void initService(Application application) {
        if (application != null) {
            sApplicationContext = application.getApplicationContext();
            int sendStrategy = BasicStoreTools.getInstance().getSendStrategy(sApplicationContext);
            long sendStrategyTime = BasicStoreTools.getInstance().getSendStrategyTime(sApplicationContext);
            boolean onlyWifi = BasicStoreTools.getInstance().getOnlyWifi(sApplicationContext);
            if (sendStrategy < 0 || sendStrategy >= SendStrategyEnum.values().length) {
                LogController.geInstance().setSendStrategy(sApplicationContext, SendStrategyEnum.REAL_TIME, 0, false);
                return;
            }
            LogController.geInstance().setSendStrategy(sApplicationContext, SendStrategyEnum.values()[sendStrategy], sendStrategyTime, onlyWifi);
        }
    }

    public static void initService(Context context) {
        if (context != null) {
            sApplicationContext = context.getApplicationContext();
            int sendStrategy = BasicStoreTools.getInstance().getSendStrategy(sApplicationContext);
            long sendStrategyTime = BasicStoreTools.getInstance().getSendStrategyTime(sApplicationContext);
            boolean onlyWifi = BasicStoreTools.getInstance().getOnlyWifi(sApplicationContext);
            if (sendStrategy < 0 || sendStrategy >= SendStrategyEnum.values().length) {
                LogController.geInstance().setSendStrategy(sApplicationContext, SendStrategyEnum.REAL_TIME, 0, false);
                return;
            }
            LogController.geInstance().setSendStrategy(sApplicationContext, SendStrategyEnum.values()[sendStrategy], sendStrategyTime, onlyWifi);
        }
    }

    public static void initService(Context context, SendStrategyEnum sendStrategyEnum, int i, boolean z) {
        if (context != null) {
            sApplicationContext = context.getApplicationContext();
            LogController.geInstance().setSendStrategy(sApplicationContext, sendStrategyEnum, (long) i, z);
        }
    }

    public static void onError(Context context, Exception exc, HashMap hashMap) {
        if (context != null) {
            runOnHandlerThread(new b(context, exc, hashMap));
        }
    }

    public static void onEvent(Context context, String str, String str2) {
        if (context != null) {
            runOnHandlerThread(new c(context, str, str2));
        }
    }

    public static void onEvent(Context context, String str, String str2, HashMap hashMap) {
        if (context != null) {
            runOnHandlerThread(new k(context, str, str2, hashMap));
        }
    }

    @SuppressLint({"NewApi"})
    public static synchronized void onFragmentPause(Fragment fragment, HashMap hashMap) {
        synchronized (StatService.class) {
            onPageEnd(fragment.getActivity(), fragment, fragment.getClass().getName(), hashMap);
        }
    }

    @SuppressLint({"NewApi"})
    public static synchronized void onFragmentResume(Fragment fragment, HashMap hashMap) {
        synchronized (StatService.class) {
            onPageStart(fragment.getActivity(), fragment, fragment.getClass().getName(), hashMap);
        }
    }

    public static synchronized void onPageEnd(Context context, Object obj, String str) {
        synchronized (StatService.class) {
            if (context != null) {
                onPageEnd(context, obj, str, (HashMap) null);
            }
        }
    }

    public static synchronized void onPageEnd(Context context, Object obj, String str, HashMap hashMap) {
        synchronized (StatService.class) {
            if (context != null) {
                runOnHandlerThread(new d(context, obj, str, hashMap));
            }
        }
    }

    public static synchronized void onPageStart(Context context, Object obj, String str) {
        synchronized (StatService.class) {
            if (context != null) {
                runOnHandlerThread(new e(context, obj, str, (HashMap) null));
            }
        }
    }

    public static synchronized void onPageStart(Context context, Object obj, String str, HashMap hashMap) {
        synchronized (StatService.class) {
            if (context != null) {
                runOnHandlerThread(new e(context, obj, str, hashMap));
            }
        }
    }

    public static synchronized void onPause(Context context) {
        synchronized (StatService.class) {
            if (context != null) {
                runOnHandlerThread(new f(context));
            }
        }
    }

    public static void onPostClientInfo(Context context, boolean z, String str, HashMap hashMap, String str2) {
        if (context != null) {
            runOnHandlerThread(new g(context, z, str, hashMap, str2));
        }
    }

    public static void onPostEvent(Context context, String str, String str2, HashMap hashMap) {
        if (context != null) {
            runOnHandlerThread(new h(context, str, str2, hashMap));
        }
    }

    public static synchronized void onResume(Context context) {
        synchronized (StatService.class) {
            if (context != null) {
                runOnHandlerThread(new l(context));
            }
        }
    }

    public static void onView(Context context, String str, String str2, HashMap hashMap) {
        if (context != null) {
            runOnHandlerThread(new i(context, str, str2, hashMap));
        }
    }

    public static void runOnHandlerThread(Runnable runnable) {
        if (f12075a && runnable != null) {
            handler.post(runnable);
        }
    }

    public static void saveProtoInfoToFile(Context context, String str, byte[] bArr) {
        if (context != null && bArr != null && bArr.length > 0) {
            byte[] encrpt = AESUtils.encrpt(bArr);
            String saveInfoToLog = LogController.saveInfoToLog(context, str, encrpt);
            if (!StrUtil.isEmpty(saveInfoToLog) && handler != null) {
                handler.post(new WriteFileThread(context, saveInfoToLog, encrpt));
            }
        }
    }

    public static void setAppChannel(Context context, String str) {
        DataCore.getMoblieInfo().appChannel = str;
        BasicStoreTools.getInstance().setAppChannel(context, str);
    }

    public static void setAppKey(Context context, String str) {
        DataCore.getMoblieInfo().appKey = str;
        BasicStoreTools.getInstance().setAppKey(context, str);
    }

    public static void setDebugOn(boolean z, String str) {
        HostManager.setDebugOn(z, str);
    }

    public static void setEnable(boolean z) {
        f12075a = z;
        if (!f12075a) {
            LogController.deleteAllFloder();
        }
    }

    public static void setExceptionOn(Context context, boolean z) {
        if (z && context != null) {
            runOnHandlerThread(new j(context));
        }
    }

    public static void setLogSenderDelayed(int i) {
        LogController.geInstance().setSendDelayedTime(i);
    }

    public static void updateAllLogData() {
        Message obtainMessage = handler.obtainMessage();
        if (obtainMessage != null) {
            obtainMessage.what = 4096;
            obtainMessage.sendToTarget();
        }
    }

    public HashMap getInitMap() {
        return c;
    }
}
