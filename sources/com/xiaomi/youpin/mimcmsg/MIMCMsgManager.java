package com.xiaomi.youpin.mimcmsg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.xiaomi.mimc.MIMCMessageHandler;
import com.xiaomi.mimc.MIMCOnlineStatusListener;
import com.xiaomi.mimc.MIMCRtsCallHandler;
import com.xiaomi.mimc.MIMCTokenFetcher;
import com.xiaomi.mimc.MIMCUser;
import com.xiaomi.mimc.common.MIMCConstant;
import com.xiaomi.mimc.data.LaunchedResponse;
import com.xiaomi.mimc.data.RtsChannelType;
import com.xiaomi.mimc.data.RtsDataType;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.mimcmsg.api.MIMCApi;
import java.io.File;

public class MIMCMsgManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23633a = "MIMC";
    public static final String b = "com.xiaomi.youpin.action.on_logout";
    public static final String c = "com.xiaomi.youpin.action.on_login";
    public static final String d = "MIMCMessageUpdateNotification";
    public static final String e = "MIMCRecentContactListUpdateNotification";
    public static long f = (d() ? 2882303761517534264L : 2882303761517532944L);
    private static volatile MIMCMsgManager g;
    /* access modifiers changed from: private */
    public String h = "";
    /* access modifiers changed from: private */
    public MIMCUser i;
    /* access modifiers changed from: private */
    public MIMCConstant.OnlineStatus j;
    private MessageHandler k;
    private BroadcastReceiver l;

    private MIMCMsgManager() {
        if (this.l == null) {
            this.l = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (intent.getAction().equals("com.xiaomi.youpin.action.on_login")) {
                        MIMCMsgManager.this.c();
                    } else if (intent.getAction().equals("com.xiaomi.youpin.action.on_logout")) {
                        String unused = MIMCMsgManager.this.h = "";
                        if (MIMCMsgManager.this.i != null) {
                            MIMCMsgManager.this.i.logout();
                            MIMCMsgManager.this.i.f();
                            MIMCUser unused2 = MIMCMsgManager.this.i = null;
                        }
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.xiaomi.youpin.action.on_login");
            intentFilter.addAction("com.xiaomi.youpin.action.on_logout");
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.l, intentFilter);
        }
    }

    public static MIMCMsgManager a() {
        if (g == null) {
            synchronized (MIMCMsgManager.class) {
                if (g == null) {
                    g = new MIMCMsgManager();
                }
            }
        }
        return g;
    }

    public MIMCUser b() {
        return this.i;
    }

    public MIMCUser a(String str) {
        String str2 = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (TextUtils.equals(str, this.h) && this.i != null) {
            return this.i;
        }
        this.h = str;
        if (this.i != null) {
            this.i.logout();
            this.i.f();
        }
        File externalFilesDir = XmPluginHostApi.instance().context().getExternalFilesDir((String) null);
        long j2 = f;
        if (externalFilesDir != null) {
            str2 = externalFilesDir.getAbsolutePath();
        }
        this.i = MIMCUser.a(j2, str, str2);
        if (this.i != null) {
            if (this.k == null) {
                this.k = new MessageHandler();
            }
            this.i.a((MIMCMessageHandler) this.k);
            this.i.a((MIMCTokenFetcher) new TokenFetcher());
            this.i.a((MIMCRtsCallHandler) new RTSHandler());
            this.i.a((MIMCOnlineStatusListener) new OnlineStatusListener());
        }
        return this.i;
    }

    public void c() {
        MIMCUser a2;
        if (!TextUtils.isEmpty(XmPluginHostApi.instance().getAccountId()) && (a2 = a(XmPluginHostApi.instance().getAccountId())) != null && !a2.p()) {
            a2.a();
            LogUtils.d("MIMC", "登录中....");
        }
    }

    class OnlineStatusListener implements MIMCOnlineStatusListener {
        OnlineStatusListener() {
        }

        public void a(MIMCConstant.OnlineStatus onlineStatus, String str, String str2, String str3) {
            MIMCConstant.OnlineStatus unused = MIMCMsgManager.this.j = onlineStatus;
            boolean z = onlineStatus == MIMCConstant.OnlineStatus.ONLINE;
            LogUtils.d("MIMC", "是否在线 :   " + z);
        }
    }

    class TokenFetcher implements MIMCTokenFetcher {
        TokenFetcher() {
        }

        public String a() throws Exception {
            return MIMCApi.a();
        }
    }

    class RTSHandler implements MIMCRtsCallHandler {
        public LaunchedResponse a(String str, String str2, long j, byte[] bArr) {
            return null;
        }

        public void a(long j, int i, Object obj) {
        }

        public void a(long j, String str) {
        }

        public void a(long j, String str, String str2, byte[] bArr, RtsDataType rtsDataType, RtsChannelType rtsChannelType) {
        }

        public void a(long j, boolean z, String str) {
        }

        public void b(long j, int i, Object obj) {
        }

        RTSHandler() {
        }
    }

    public static boolean d() {
        return GlobalSetting.w && GlobalSetting.C;
    }
}
