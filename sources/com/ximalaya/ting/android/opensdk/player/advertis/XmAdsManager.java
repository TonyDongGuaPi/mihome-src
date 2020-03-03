package com.ximalaya.ting.android.opensdk.player.advertis;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.advertis.Advertis;
import com.ximalaya.ting.android.opensdk.model.advertis.AdvertisList;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerService;
import com.ximalaya.ting.android.opensdk.player.statistic.OpenSdkPlayStatisticUpload;
import com.ximalaya.ting.android.opensdk.util.DigestUtils;
import com.ximalaya.ting.android.opensdk.util.FileUtilBase;
import com.ximalaya.ting.android.opensdk.util.Logger;
import com.ximalaya.ting.android.opensdk.util.MyAsyncTask;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class XmAdsManager {

    /* renamed from: a  reason: collision with root package name */
    public static final int f2167a = 20000;
    public static boolean b = false;
    private static final int c = 20;
    private static final int d = 5000;
    private static volatile XmAdsManager e;
    private static byte[] f = new byte[0];
    private IXmAdsStatusListener g;
    private Context h;
    /* access modifiers changed from: private */
    public List<String> i = new CopyOnWriteArrayList();
    /* access modifiers changed from: private */
    public MiniPlayer j;
    /* access modifiers changed from: private */
    public boolean k = false;
    /* access modifiers changed from: private */
    public TaskWrapper l;
    /* access modifiers changed from: private */
    public IXmAdsDataHandle m;
    private boolean n = false;
    private String o;
    private long p = -1;
    private AdvertisList q;
    private long r;
    private Map<String, List<IDataCallBack<String>>> s = new ConcurrentHashMap();

    public interface IPlayStartCallBack {
        boolean a();
    }

    public interface PlayAdsCallback {
        void a(boolean z);
    }

    public static XmAdsManager a(Context context) {
        if (e == null) {
            synchronized (f) {
                if (e == null) {
                    e = new XmAdsManager(context);
                    b(context);
                }
            }
        }
        return e;
    }

    private static void b(Context context) {
        String str;
        if (context != null) {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                str = Environment.getExternalStorageDirectory().getPath() + "/Android/data/" + context.getPackageName() + "/files/ads";
            } else {
                str = context.getFilesDir().getPath() + "/ads";
            }
            try {
                FileUtilBase.b(new File(str));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(String str) {
        Logger.a("XmAdsManager ==  2 " + str);
        if (!TextUtils.isEmpty(str)) {
            try {
                this.m = (IXmAdsDataHandle) Class.forName(str).getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]);
                Logger.a("XmAdsManager ==  3 " + this.m);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public MiniPlayer a() {
        return this.j;
    }

    public void b() {
        if (this.j != null) {
            this.j.m();
        }
        if (this.l != null) {
            this.l.b = true;
        }
        this.k = false;
        this.j = null;
        this.l = null;
        this.g = null;
        if (this.m != null) {
            this.m.a();
        }
    }

    public boolean c() {
        return this.k && !d();
    }

    public boolean d() {
        return this.j != null && this.j.i();
    }

    public void e() {
        if (this.j != null) {
            Logger.a("playAd 0:" + System.currentTimeMillis());
            this.j.k();
            Advertis o2 = this.j.o();
            if (o2 != null) {
                o2.e((long) this.j.d());
                if (this.g != null) {
                    this.g.a(o2, 0);
                }
            }
        }
    }

    public void f() {
        if (this.j != null) {
            this.j.l();
        }
    }

    public boolean g() {
        return this.k;
    }

    public void a(IXmAdsStatusListener iXmAdsStatusListener) {
        this.g = iXmAdsStatusListener;
    }

    public boolean h() {
        if (this.l == null || this.l.c == null || this.l.c.a() == null || this.l.c.a().size() <= 0 || this.l.c.a().get(0) == null || 11 != this.l.c.a().get(0).L()) {
            return false;
        }
        return true;
    }

    private boolean a(Advertis advertis) {
        return advertis != null && advertis.ao() && !TextUtils.isEmpty(advertis.l());
    }

    public class TaskWrapper {

        /* renamed from: a  reason: collision with root package name */
        public Track f2177a;
        public boolean b;
        public AdvertisList c;
        public PlayAdsCallback d;
        public PlayAdsCallback e;
        public IPlayStartCallBack f;
        public int g = 0;

        public TaskWrapper() {
        }
    }

    public void a(Track track, int i2, PlayAdsCallback playAdsCallback, boolean z) {
        if (!z) {
            i();
            this.k = true;
        }
        TaskWrapper taskWrapper = new TaskWrapper();
        taskWrapper.f2177a = track;
        taskWrapper.d = playAdsCallback;
        this.l = taskWrapper;
        this.n = z;
        a(this.l, i2, z);
    }

    public synchronized void i() {
        if (this.l != null) {
            this.l.b = true;
            this.l.d = null;
            this.l.e = null;
            this.l.f = null;
            this.l.g = 0;
            if (XmPlayerService.getPlayerSrvice() != null) {
                XmPlayerService.getPlayerSrvice().setPlayStartCallback((IPlayStartCallBack) null);
            }
        }
        if (this.j != null) {
            this.j.m();
        }
        this.k = false;
    }

    private XmAdsManager(Context context) {
        this.h = context.getApplicationContext();
        m();
    }

    private void m() {
        File file = new File(FileUtilBase.a(this.h, ""));
        if (!file.exists()) {
            file.mkdirs();
        }
        String[] list = file.list();
        if (list != null && list.length != 0) {
            this.i.clear();
            this.i.addAll(Arrays.asList(list));
        }
    }

    /* access modifiers changed from: private */
    public boolean b(String str) {
        File file = new File(FileUtilBase.a(this.h, str));
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    /* access modifiers changed from: private */
    public String c(String str) {
        Context context = this.h;
        return FileUtilBase.a(context, DigestUtils.b(str) + d(str));
    }

    private synchronized void a(final TaskWrapper taskWrapper, int i2, boolean z) {
        if (this.g != null && !z) {
            this.g.a();
        }
        Logger.a("getAdsInfoAndPlay 0:" + System.currentTimeMillis());
        if (this.m == null) {
            this.k = false;
            a(true, taskWrapper, true);
            Logger.a("getAdsInfoAndPlay 1:" + System.currentTimeMillis());
            return;
        }
        Logger.a("getAdsInfoAndPlay 2:" + System.currentTimeMillis());
        if (taskWrapper.f2177a.a() != this.p || this.q == null || System.currentTimeMillis() - this.r >= 40000) {
            HashMap hashMap = new HashMap();
            if ("track".equals(taskWrapper.f2177a.b())) {
                hashMap.put("trackId", "" + taskWrapper.f2177a.a());
            } else {
                hashMap.put(OpenSdkPlayStatisticUpload.c, "" + taskWrapper.f2177a.aF());
                hashMap.put("scheduleId", "" + taskWrapper.f2177a.a());
            }
            if (XmPlayerService.getPlayerSrvice() != null) {
                hashMap.put("mode", XmPlayerService.getPlayerSrvice().isOnlineResource() ? "0" : "1");
            } else {
                hashMap.put("mode", "0");
            }
            hashMap.put("playMethod", String.valueOf(i2));
            hashMap.put(Advertis.T, z + "");
            this.m.a(this.o);
            Logger.a("getAdsInfoAndPlay 4:" + System.currentTimeMillis());
            this.o = this.m.a(taskWrapper.f2177a, hashMap, new IDataCallBack<AdvertisList>() {
                public void a(AdvertisList advertisList) {
                    Logger.a("getAdsInfoAndPlay 5:" + System.currentTimeMillis());
                    XmAdsManager.this.a(advertisList, taskWrapper);
                }

                public void a(int i, String str) {
                    Logger.a("getAdsInfoAndPlay 6:" + System.currentTimeMillis());
                    if (taskWrapper == XmAdsManager.this.l) {
                        boolean unused = XmAdsManager.this.k = false;
                    }
                    boolean unused2 = XmAdsManager.this.a(true, taskWrapper, true);
                }
            });
            return;
        }
        a(this.q, taskWrapper);
        this.q = null;
        this.p = -1;
        Logger.a("getAdsInfoAndPlay 3:" + System.currentTimeMillis());
    }

    /* access modifiers changed from: private */
    public void a(AdvertisList advertisList, TaskWrapper taskWrapper) {
        boolean a2 = a(taskWrapper.b, taskWrapper, false);
        Logger.a("dataReceiver 1:" + System.currentTimeMillis());
        if (a2) {
            Logger.a("dataReceiver 2:" + System.currentTimeMillis());
            return;
        }
        Logger.a("dataReceiver 3:" + System.currentTimeMillis());
        if (this.g != null) {
            this.g.a(advertisList);
        }
        if (advertisList == null || advertisList.b() != 0 || advertisList.a() == null || advertisList.a().size() == 0 || advertisList.a().get(0) == null) {
            this.k = false;
            a(true, taskWrapper, true);
            Logger.a("dataReceiver 4:" + System.currentTimeMillis());
            return;
        }
        taskWrapper.c = advertisList;
        if (!advertisList.a().get(0).R()) {
            a(taskWrapper);
            if (!b && advertisList.a().get(0).L() == 11) {
                j();
            }
        } else {
            if (this.g != null) {
                this.g.d();
            }
            this.k = false;
        }
        Logger.a("dataReceiver 5:" + System.currentTimeMillis());
        this.m.a(taskWrapper);
    }

    public void a(boolean z) {
        if (this.l != null) {
            if (this.l.d != null) {
                this.l.d.a(z);
            }
            i();
            if (this.g != null) {
                this.g.d();
            }
        }
    }

    public void j() {
        if (this.l != null) {
            if (this.l.e != null) {
                this.l.e.a(true);
            }
            this.k = false;
            if (b(this.l) < 0) {
                i();
            } else {
                this.l.d = this.l.e;
                this.l.e = null;
            }
            if (this.g != null) {
                this.g.d();
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized boolean a(boolean z, TaskWrapper taskWrapper, boolean z2) {
        Logger.a("exitPlayAds cancel:" + z + " task:" + taskWrapper.f2177a.toString() + " result:" + z2 + "  time:" + System.currentTimeMillis());
        if (!z) {
            return false;
        }
        if (taskWrapper != null && taskWrapper == this.l) {
            if (this.g != null) {
                Logger.a("exitPlayAds 0");
                this.g.d();
            }
            if (taskWrapper.d != null) {
                Logger.a("exitPlayAds 1");
                taskWrapper.d.a(z2);
            }
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x009d A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(final com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager.TaskWrapper r14) {
        /*
            r13 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "downloadAndPlayAds 0:"
            r0.append(r1)
            long r1 = java.lang.System.currentTimeMillis()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.ximalaya.ting.android.opensdk.util.Logger.a((java.lang.String) r0)
            boolean r0 = r14.b
            r1 = 0
            boolean r0 = r13.a((boolean) r0, (com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager.TaskWrapper) r14, (boolean) r1)
            if (r0 == 0) goto L_0x003a
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r0 = "downloadAndPlayAds 1:"
            r14.append(r0)
            long r0 = java.lang.System.currentTimeMillis()
            r14.append(r0)
            java.lang.String r14 = r14.toString()
            com.ximalaya.ting.android.opensdk.util.Logger.a((java.lang.String) r14)
            return
        L_0x003a:
            int r0 = r13.b((com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager.TaskWrapper) r14)
            com.ximalaya.ting.android.opensdk.model.advertis.AdvertisList r2 = r14.c
            java.util.List r2 = r2.a()
            java.lang.Object r2 = r2.get(r1)
            com.ximalaya.ting.android.opensdk.model.advertis.Advertis r2 = (com.ximalaya.ting.android.opensdk.model.advertis.Advertis) r2
            java.lang.String r2 = r2.l()
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            r8 = 1
            r2 = r2 ^ r8
            if (r0 <= 0) goto L_0x006c
            com.ximalaya.ting.android.opensdk.model.advertis.AdvertisList r2 = r14.c
            java.util.List r2 = r2.a()
            java.lang.Object r2 = r2.get(r1)
            com.ximalaya.ting.android.opensdk.model.advertis.Advertis r2 = (com.ximalaya.ting.android.opensdk.model.advertis.Advertis) r2
            java.lang.String r2 = r2.l()
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            r2 = r2 ^ r8
            goto L_0x0071
        L_0x006c:
            if (r0 != 0) goto L_0x0071
            r9 = 0
            r10 = 0
            goto L_0x0073
        L_0x0071:
            r10 = r2
            r9 = 1
        L_0x0073:
            if (r0 < 0) goto L_0x00d2
            com.ximalaya.ting.android.opensdk.model.advertis.AdvertisList r2 = r14.c
            java.util.List r2 = r2.a()
            java.lang.Object r2 = r2.get(r0)
            com.ximalaya.ting.android.opensdk.model.advertis.Advertis r2 = (com.ximalaya.ting.android.opensdk.model.advertis.Advertis) r2
            if (r2 == 0) goto L_0x00d2
            java.lang.String r11 = r2.l()
            java.lang.String r2 = r13.c((java.lang.String) r11)
            com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager$2 r3 = new com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager$2
            r3.<init>(r14, r2, r0)
            r14.f = r3
            java.io.File r3 = new java.io.File
            r3.<init>(r2)
            boolean r2 = r3.exists()
            if (r2 == 0) goto L_0x00b8
            if (r0 == 0) goto L_0x00a3
            if (r9 == 0) goto L_0x00d2
            if (r10 != 0) goto L_0x00d2
        L_0x00a3:
            com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r0 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.getPlayerSrvice()
            if (r0 == 0) goto L_0x00b2
            com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r0 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.getPlayerSrvice()
            com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager$IPlayStartCallBack r2 = r14.f
            r0.setPlayStartCallback(r2)
        L_0x00b2:
            r13.k = r1
            r13.a((boolean) r8, (com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager.TaskWrapper) r14, (boolean) r8)
            return
        L_0x00b8:
            com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager$3 r12 = new com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager$3
            r2 = r12
            r3 = r13
            r4 = r0
            r5 = r9
            r6 = r10
            r7 = r14
            r2.<init>(r4, r5, r6, r7)
            com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager$4 r2 = new com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager$4
            r2.<init>(r11, r12)
            com.ximalaya.ting.android.opensdk.util.MyAsyncTask.a((java.lang.Runnable) r2)
            if (r0 == 0) goto L_0x00d1
            if (r9 == 0) goto L_0x00d2
            if (r10 != 0) goto L_0x00d2
        L_0x00d1:
            return
        L_0x00d2:
            if (r9 == 0) goto L_0x014b
            com.ximalaya.ting.android.opensdk.model.advertis.AdvertisList r2 = r14.c
            java.util.List r2 = r2.a()
            java.lang.Object r2 = r2.get(r1)
            com.ximalaya.ting.android.opensdk.model.advertis.Advertis r2 = (com.ximalaya.ting.android.opensdk.model.advertis.Advertis) r2
            int r3 = r2.L()
            r4 = 11
            if (r3 != r4) goto L_0x00f4
            java.lang.String r2 = r2.an()
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L_0x00f4
            r2 = 1
            goto L_0x00f5
        L_0x00f4:
            r2 = 0
        L_0x00f5:
            if (r2 == 0) goto L_0x00fe
            com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager$PlayAdsCallback r3 = r14.d
            r14.e = r3
            r3 = 0
            r14.d = r3
        L_0x00fe:
            if (r10 != 0) goto L_0x0131
            if (r2 != 0) goto L_0x0131
            r13.k = r1
            if (r0 < 0) goto L_0x0115
            com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r0 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.getPlayerSrvice()
            if (r0 == 0) goto L_0x0115
            com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r0 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.getPlayerSrvice()
            com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager$IPlayStartCallBack r1 = r14.f
            r0.setPlayStartCallback(r1)
        L_0x0115:
            r13.a((boolean) r8, (com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager.TaskWrapper) r14, (boolean) r8)
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r0 = "downloadAndPlayAds 2:"
            r14.append(r0)
            long r0 = java.lang.System.currentTimeMillis()
            r14.append(r0)
            java.lang.String r14 = r14.toString()
            com.ximalaya.ting.android.opensdk.util.Logger.a((java.lang.String) r14)
            return
        L_0x0131:
            if (r10 == 0) goto L_0x0138
            if (r2 != 0) goto L_0x0138
            r13.a((com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager.TaskWrapper) r14, (int) r0)
        L_0x0138:
            if (r2 == 0) goto L_0x014b
            if (r0 < 0) goto L_0x014b
            com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r0 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.getPlayerSrvice()
            if (r0 == 0) goto L_0x014b
            com.ximalaya.ting.android.opensdk.player.service.XmPlayerService r0 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerService.getPlayerSrvice()
            com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager$IPlayStartCallBack r14 = r14.f
            r0.setPlayStartCallback(r14)
        L_0x014b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager.a(com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager$TaskWrapper):void");
    }

    private int b(TaskWrapper taskWrapper) {
        if (taskWrapper == null || taskWrapper.c == null || taskWrapper.c.a() == null || taskWrapper.c.a().size() == 0) {
            return -1;
        }
        List<Advertis> a2 = taskWrapper.c.a();
        for (int i2 = 0; i2 < a2.size(); i2++) {
            Advertis advertis = a2.get(i2);
            if (advertis != null && advertis.ao() && !TextUtils.isEmpty(advertis.l())) {
                return i2;
            }
        }
        return -1;
    }

    private static String d(String str) {
        if (TextUtils.isEmpty(str) || !str.contains(".")) {
            return null;
        }
        return str.substring(str.lastIndexOf("."), str.length());
    }

    private void a(final TaskWrapper taskWrapper, final boolean z, final int i2) {
        Logger.a("playAdsInternal 0:" + System.currentTimeMillis());
        if (a(taskWrapper.b, taskWrapper, false)) {
            if (this.g != null) {
                this.g.c();
            }
            Logger.a("playAdsInternal 1:" + System.currentTimeMillis());
            return;
        }
        if (this.j != null) {
            this.j.m();
        } else {
            this.j = new MiniPlayer();
        }
        this.j.a((MediaPlayer.OnCompletionListener) new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                Logger.a("playAd CompletionListener:" + System.currentTimeMillis());
                if (taskWrapper == XmAdsManager.this.l) {
                    XmAdsManager.this.j.a((MediaPlayer.OnCompletionListener) null);
                }
                if (!z && i2 >= 0 && XmPlayerService.getPlayerSrvice() != null) {
                    XmPlayerService.getPlayerSrvice().setPlayStartCallback(taskWrapper.f);
                }
                boolean unused = XmAdsManager.this.k = false;
                boolean unused2 = XmAdsManager.this.a(true, taskWrapper, true);
            }
        });
        Advertis advertis = taskWrapper.c.a().get(0);
        if (z && taskWrapper.c.a().size() > i2) {
            advertis.g(taskWrapper.c.a().get(i2).l());
            advertis.k(true);
        }
        String a2 = FileUtilBase.a(this.h, DigestUtils.b(advertis.l()) + d(advertis.l()));
        if (new File(a2).exists()) {
            XmPlayerService playerSrvice = XmPlayerService.getPlayerSrvice();
            if (playerSrvice == null || !playerSrvice.isLossAudioFocus()) {
                try {
                    this.j.a(a2, advertis);
                    Logger.a("playAdsInternal 3:" + System.currentTimeMillis());
                    e();
                } catch (Exception e2) {
                    Logger.a("playAdsInternal 4:" + System.currentTimeMillis());
                    e2.printStackTrace();
                    if (this.g != null) {
                        this.g.a(0, 0);
                    }
                    this.k = false;
                    a(true, taskWrapper, true);
                }
            } else {
                playerSrvice.setLossAudioFocus(false);
                this.k = false;
                a(true, taskWrapper, true);
                Logger.a("playAdsInternal 2:" + System.currentTimeMillis());
            }
        } else {
            this.k = false;
            a(true, taskWrapper, true);
            Logger.a("playAdsInternal 5:" + System.currentTimeMillis());
        }
    }

    private void a(final TaskWrapper taskWrapper, final int i2) {
        if (a(taskWrapper.b, taskWrapper, false)) {
            Logger.a("downloadAdsFile 0:" + System.currentTimeMillis());
            return;
        }
        if (this.g != null) {
            this.g.b();
        }
        MyAsyncTask.a((Runnable) new Runnable() {
            public void run() {
                Logger.a("downloadAdsFile doInBackground 1:" + System.currentTimeMillis());
                if (!taskWrapper.b) {
                    String l = taskWrapper.c.a().get(0).l();
                    String b2 = XmAdsManager.this.c(l);
                    if (new File(b2).exists()) {
                        XmAdsManager.this.a(taskWrapper, b2, false, i2);
                        return;
                    }
                    XmAdsManager.this.a(l, (IDataCallBack<String>) new IDataCallBack<String>() {
                        public void a(@Nullable String str) {
                            XmAdsManager.this.a(taskWrapper, str, false, i2);
                        }

                        public void a(int i, String str) {
                            XmAdsManager.this.a(taskWrapper, (String) null, false, i2);
                        }
                    });
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str, IDataCallBack<String> iDataCallBack) {
        List list = this.s.get(str);
        if (list == null || list.size() <= 0) {
            CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            copyOnWriteArrayList.add(iDataCallBack);
            this.s.put(str, copyOnWriteArrayList);
            a(str, c(str), (TaskWrapper) null);
            return;
        }
        list.add(iDataCallBack);
    }

    /* access modifiers changed from: private */
    public void a(TaskWrapper taskWrapper, String str, boolean z, int i2) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            b(taskWrapper, str, z, i2);
            return;
        }
        final TaskWrapper taskWrapper2 = taskWrapper;
        final String str2 = str;
        final boolean z2 = z;
        final int i3 = i2;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                XmAdsManager.this.b(taskWrapper2, str2, z2, i3);
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(TaskWrapper taskWrapper, String str, boolean z, int i2) {
        Logger.a("downloadAdsFile onPostExecute 0:" + System.currentTimeMillis());
        if (a(taskWrapper.b, taskWrapper, false)) {
            Logger.a("downloadAdsFile onPostExecute 1:" + System.currentTimeMillis());
            if (this.g != null) {
                this.g.c();
            }
        } else if (TextUtils.isEmpty(str)) {
            this.k = false;
            Logger.a("downloadAdsFile onPostExecute 2:" + System.currentTimeMillis());
            a(true, taskWrapper, true);
            if (this.g != null) {
                this.g.c();
            }
        } else {
            if (this.i.size() > 20) {
                b(this.i.remove(0));
            }
            this.i.add(str);
            Logger.a("downloadAdsFile onPostExecute 3:" + System.currentTimeMillis());
            a(taskWrapper, z, i2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00b1, code lost:
        r5.flush();
        r9 = r6.s.get(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00bc, code lost:
        if (r9 == null) goto L_0x00d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00c2, code lost:
        if (r9.size() <= 0) goto L_0x00d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00c4, code lost:
        r9 = r9.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00cc, code lost:
        if (r9.hasNext() == false) goto L_0x00d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00ce, code lost:
        ((com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack) r9.next()).a(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00d8, code lost:
        r6.s.remove(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0131, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0132, code lost:
        r7.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:?, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0107 A[Catch:{ all -> 0x00e7 }, LOOP:4: B:59:0x0101->B:61:0x0107, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x012d A[SYNTHETIC, Splitter:B:64:0x012d] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0138 A[SYNTHETIC, Splitter:B:69:0x0138] */
    /* JADX WARNING: Removed duplicated region for block: B:83:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r7, java.lang.String r8, @android.support.annotation.Nullable com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager.TaskWrapper r9) {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
            android.content.Context r2 = r6.h     // Catch:{ Exception -> 0x00e9 }
            java.lang.String r2 = com.ximalaya.ting.android.opensdk.util.FileUtilBase.c(r2, r7)     // Catch:{ Exception -> 0x00e9 }
            com.ximalaya.ting.android.opensdk.httputil.Config r3 = com.ximalaya.ting.android.opensdk.httputil.HttpUrlUtil.f1992a     // Catch:{ Exception -> 0x00e9 }
            java.lang.String r4 = "GET"
            com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager$8 r5 = new com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager$8     // Catch:{ Exception -> 0x00e9 }
            r5.<init>()     // Catch:{ Exception -> 0x00e9 }
            java.net.HttpURLConnection r2 = com.ximalaya.ting.android.opensdk.httputil.HttpUrlUtil.a(r2, r3, r4, r5)     // Catch:{ Exception -> 0x00e9 }
            r2.connect()     // Catch:{ Exception -> 0x00e9 }
            int r3 = r2.getResponseCode()     // Catch:{ Exception -> 0x00e9 }
            r4 = 200(0xc8, float:2.8E-43)
            if (r3 == r4) goto L_0x005b
            java.util.Map<java.lang.String, java.util.List<com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack<java.lang.String>>> r8 = r6.s     // Catch:{ Exception -> 0x00e9 }
            java.lang.Object r8 = r8.get(r7)     // Catch:{ Exception -> 0x00e9 }
            java.util.List r8 = (java.util.List) r8     // Catch:{ Exception -> 0x00e9 }
            if (r8 == 0) goto L_0x0055
            int r9 = r8.size()     // Catch:{ Exception -> 0x00e9 }
            if (r9 <= 0) goto L_0x0055
            java.util.Iterator r8 = r8.iterator()     // Catch:{ Exception -> 0x00e9 }
        L_0x0034:
            boolean r9 = r8.hasNext()     // Catch:{ Exception -> 0x00e9 }
            if (r9 == 0) goto L_0x0055
            java.lang.Object r9 = r8.next()     // Catch:{ Exception -> 0x00e9 }
            com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack r9 = (com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack) r9     // Catch:{ Exception -> 0x00e9 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00e9 }
            r2.<init>()     // Catch:{ Exception -> 0x00e9 }
            java.lang.String r4 = "resCode == "
            r2.append(r4)     // Catch:{ Exception -> 0x00e9 }
            r2.append(r3)     // Catch:{ Exception -> 0x00e9 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x00e9 }
            r9.a(r0, r2)     // Catch:{ Exception -> 0x00e9 }
            goto L_0x0034
        L_0x0055:
            java.util.Map<java.lang.String, java.util.List<com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack<java.lang.String>>> r8 = r6.s     // Catch:{ Exception -> 0x00e9 }
            r8.remove(r7)     // Catch:{ Exception -> 0x00e9 }
            return
        L_0x005b:
            java.io.InputStream r2 = r2.getInputStream()     // Catch:{ Exception -> 0x00e9 }
            r3 = 8192(0x2000, float:1.14794E-41)
            byte[] r3 = new byte[r3]     // Catch:{ Exception -> 0x00e9 }
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x00e9 }
            r4.<init>(r8)     // Catch:{ Exception -> 0x00e9 }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x00e9 }
            r5.<init>(r4, r0)     // Catch:{ Exception -> 0x00e9 }
        L_0x006d:
            int r1 = r2.read(r3)     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
            if (r1 <= 0) goto L_0x00b1
            if (r9 == 0) goto L_0x00ad
            boolean r4 = r9.b     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
            if (r4 == 0) goto L_0x00ad
            java.util.Map<java.lang.String, java.util.List<com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack<java.lang.String>>> r8 = r6.s     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
            java.lang.Object r8 = r8.get(r7)     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
            java.util.List r8 = (java.util.List) r8     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
            if (r8 == 0) goto L_0x009f
            int r9 = r8.size()     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
            if (r9 <= 0) goto L_0x009f
            java.util.Iterator r8 = r8.iterator()     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
        L_0x008d:
            boolean r9 = r8.hasNext()     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
            if (r9 == 0) goto L_0x009f
            java.lang.Object r9 = r8.next()     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
            com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack r9 = (com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack) r9     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
            java.lang.String r1 = "已取消"
            r9.a(r0, r1)     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
            goto L_0x008d
        L_0x009f:
            java.util.Map<java.lang.String, java.util.List<com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack<java.lang.String>>> r8 = r6.s     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
            r8.remove(r7)     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
            r5.close()     // Catch:{ IOException -> 0x00a8 }
            goto L_0x00ac
        L_0x00a8:
            r7 = move-exception
            r7.printStackTrace()
        L_0x00ac:
            return
        L_0x00ad:
            r5.write(r3, r0, r1)     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
            goto L_0x006d
        L_0x00b1:
            r5.flush()     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
            java.util.Map<java.lang.String, java.util.List<com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack<java.lang.String>>> r9 = r6.s     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
            java.lang.Object r9 = r9.get(r7)     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
            java.util.List r9 = (java.util.List) r9     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
            if (r9 == 0) goto L_0x00d8
            int r1 = r9.size()     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
            if (r1 <= 0) goto L_0x00d8
            java.util.Iterator r9 = r9.iterator()     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
        L_0x00c8:
            boolean r1 = r9.hasNext()     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
            if (r1 == 0) goto L_0x00d8
            java.lang.Object r1 = r9.next()     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
            com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack r1 = (com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack) r1     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
            r1.a(r8)     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
            goto L_0x00c8
        L_0x00d8:
            java.util.Map<java.lang.String, java.util.List<com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack<java.lang.String>>> r8 = r6.s     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
            r8.remove(r7)     // Catch:{ Exception -> 0x00e4, all -> 0x00e1 }
            r5.close()     // Catch:{ IOException -> 0x0131 }
            goto L_0x0135
        L_0x00e1:
            r7 = move-exception
            r1 = r5
            goto L_0x0136
        L_0x00e4:
            r8 = move-exception
            r1 = r5
            goto L_0x00ea
        L_0x00e7:
            r7 = move-exception
            goto L_0x0136
        L_0x00e9:
            r8 = move-exception
        L_0x00ea:
            r8.printStackTrace()     // Catch:{ all -> 0x00e7 }
            java.util.Map<java.lang.String, java.util.List<com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack<java.lang.String>>> r9 = r6.s     // Catch:{ all -> 0x00e7 }
            java.lang.Object r9 = r9.get(r7)     // Catch:{ all -> 0x00e7 }
            java.util.List r9 = (java.util.List) r9     // Catch:{ all -> 0x00e7 }
            if (r9 == 0) goto L_0x0126
            int r2 = r9.size()     // Catch:{ all -> 0x00e7 }
            if (r2 <= 0) goto L_0x0126
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x00e7 }
        L_0x0101:
            boolean r2 = r9.hasNext()     // Catch:{ all -> 0x00e7 }
            if (r2 == 0) goto L_0x0126
            java.lang.Object r2 = r9.next()     // Catch:{ all -> 0x00e7 }
            com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack r2 = (com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack) r2     // Catch:{ all -> 0x00e7 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e7 }
            r3.<init>()     // Catch:{ all -> 0x00e7 }
            java.lang.String r4 = "错误了=="
            r3.append(r4)     // Catch:{ all -> 0x00e7 }
            java.lang.String r4 = r8.getMessage()     // Catch:{ all -> 0x00e7 }
            r3.append(r4)     // Catch:{ all -> 0x00e7 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00e7 }
            r2.a(r0, r3)     // Catch:{ all -> 0x00e7 }
            goto L_0x0101
        L_0x0126:
            java.util.Map<java.lang.String, java.util.List<com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack<java.lang.String>>> r8 = r6.s     // Catch:{ all -> 0x00e7 }
            r8.remove(r7)     // Catch:{ all -> 0x00e7 }
            if (r1 == 0) goto L_0x0135
            r1.close()     // Catch:{ IOException -> 0x0131 }
            goto L_0x0135
        L_0x0131:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0135:
            return
        L_0x0136:
            if (r1 == 0) goto L_0x0140
            r1.close()     // Catch:{ IOException -> 0x013c }
            goto L_0x0140
        L_0x013c:
            r8 = move-exception
            r8.printStackTrace()
        L_0x0140:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager.a(java.lang.String, java.lang.String, com.ximalaya.ting.android.opensdk.player.advertis.XmAdsManager$TaskWrapper):void");
    }

    public int k() {
        if (this.j != null) {
            return this.j.g();
        }
        return 0;
    }

    public boolean l() {
        return this.n;
    }

    public void a(int i2, int i3) {
        if (this.m != null) {
            this.m.a(i2, i3);
        }
    }
}
