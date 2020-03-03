package com.ximalaya.ting.android.opensdk.player.statistic;

import android.content.Context;
import android.text.TextUtils;
import com.ximalaya.ting.android.opensdk.model.PlayableModel;
import com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord;
import com.ximalaya.ting.android.opensdk.model.statistic.RecordModel;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerControl;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerService;
import com.ximalaya.ting.android.opensdk.util.BaseUtil;
import com.ximalaya.ting.android.opensdk.util.Logger;
import java.util.Map;

public class XmStatisticsManager {
    private static volatile XmStatisticsManager e = null;
    private static byte[] f = new byte[0];
    private static final int m = 1;
    private static final int n = 2;
    private static final int o = 3;
    private static final int p = 0;

    /* renamed from: a  reason: collision with root package name */
    public String f2247a;
    public String b;
    public Map<String, String> c;
    int d = 0;
    private IXmPlayStatisticUpload g;
    private IXmUserOneDateListener h;
    private XmPlayRecord i;
    private RecordModel j = null;
    private boolean k = false;
    private boolean l;
    private Context q;
    private long r;
    private long s;
    private boolean t;
    private int u;
    private long v;
    private long w = 0;

    private XmStatisticsManager() {
        e();
    }

    private void e() {
        this.i = new XmPlayRecord();
        XmPlayerControl.f2211a = 0;
    }

    public void a(Context context) {
        this.q = context;
    }

    public static XmStatisticsManager a() {
        if (e == null) {
            synchronized (f) {
                if (e == null) {
                    e = new XmStatisticsManager();
                }
            }
        }
        return e;
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                this.g = (IXmPlayStatisticUpload) Class.forName(str).getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]);
            } catch (Exception unused) {
            }
        }
    }

    public void a(IXmPlayStatisticUpload iXmPlayStatisticUpload) {
        this.g = iXmPlayStatisticUpload;
    }

    public void a(IXmUserOneDateListener iXmUserOneDateListener) {
        this.h = iXmUserOneDateListener;
    }

    public boolean a(int i2, int i3) {
        this.d = i2;
        if (Math.abs(i2 - this.d) > 1200) {
            this.l = true;
        } else {
            this.l = false;
        }
        return this.l;
    }

    public void a(int i2, int i3, int i4) {
        int i5 = i2 != 0 ? (i2 * i3) / 100 : 0;
        if (i4 > 15000 && i4 < i3 - 15000 && i2 > 5) {
            if (i4 >= i5) {
                this.t = true;
                if (this.t && !this.l) {
                    this.u++;
                    this.r = System.currentTimeMillis();
                }
            } else if (i4 < i5 + 3000 && this.t) {
                this.s = System.currentTimeMillis();
                if (this.r != 0) {
                    this.v += this.s - this.r;
                    this.t = false;
                }
            }
        }
    }

    public void a(Track track, boolean z, int i2, String str) {
        if (track != null && this.i != null) {
            this.k = true;
            long currentTimeMillis = System.currentTimeMillis();
            if (track.a() != this.i.r() || (track.a() == this.i.r() && XmPlayerService.getPlayerSrvice() != null && XmPlayerService.getPlayerSrvice().getXmPlayMode() == XmPlayListControl.PlayMode.PLAY_MODEL_SINGLE_LOOP)) {
                this.v = 0;
                this.t = false;
                this.u = 0;
                this.r = 0;
                this.s = 0;
                this.i.d(0);
                XmPlayerControl.f2211a = 0;
                this.i.j(currentTimeMillis);
                if (!TextUtils.isEmpty(str)) {
                    this.i.n(str);
                }
                if ("schedule".equals(track.b()) || "radio".equals(track.b())) {
                    if (track.aw()) {
                        this.i.h(track.a());
                        this.i.c(track.y());
                        this.i.c(false);
                        this.i.d(true);
                    } else {
                        if (BaseUtil.a(track.ax() + "-" + track.ay()) != 0) {
                            this.i.c(true);
                            this.i.h(track.a());
                            Logger.c("StatisticsManager", "object :track" + this.i.r());
                        } else {
                            if ("schedule".equals(track.b())) {
                                this.i.h(track.aF());
                            } else if ("radio".equals(track.b())) {
                                this.i.h(track.a());
                            }
                            this.i.c(false);
                            this.i.d(false);
                        }
                    }
                    Logger.c("StatisticsManager", "object :radio" + this.i.r() + " isPlayTrack:" + this.i.g() + " isActivity:" + this.i.h());
                } else if ("track".equals(track.b()) || PlayableModel.e.equals(track.b())) {
                    this.i.c(true);
                    this.i.h(track.a());
                    this.i.d(1);
                    if (track.ao() != null) {
                        this.i.g(track.ao().d());
                    }
                    Logger.c("StatisticsManager", "object :track" + this.i.r());
                } else if (PlayableModel.d.equals(track.b())) {
                    this.i.c(true);
                    this.i.h(track.a());
                    this.i.p(track.aI());
                    if (track.I() != null) {
                        this.i.q(track.I().a());
                    }
                    this.i.d(4);
                }
                this.i.b(z ^ true ? 1 : 0);
                if (this.g != null && a(track)) {
                    this.i.i(1);
                    this.g.a(this.i);
                } else if (this.g != null && b(track)) {
                    this.i.i(2);
                    this.g.a(this.i);
                } else if (this.g != null && c(track)) {
                    this.i.i(3);
                    this.g.a(this.i);
                }
            }
            if (!this.i.g()) {
                XmPlayerControl.b = System.currentTimeMillis();
            } else {
                XmPlayerControl.b = (long) i2;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x010b A[SYNTHETIC, Splitter:B:40:0x010b] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0134  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0141  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x017f  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0192  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x01db  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0206  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x023a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.ximalaya.ting.android.opensdk.model.track.Track r20, int r21) {
        /*
            r19 = this;
            r0 = r19
            r2 = r21
            if (r20 == 0) goto L_0x026a
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r3 = r0.i
            if (r3 != 0) goto L_0x000c
            goto L_0x026a
        L_0x000c:
            boolean r3 = r0.k
            if (r3 != 0) goto L_0x0011
            return
        L_0x0011:
            r3 = 0
            r0.k = r3
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r4 = r0.i
            boolean r4 = r4 instanceof com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord
            if (r4 != 0) goto L_0x001b
            return
        L_0x001b:
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r4 = r0.i
            boolean r4 = r4.g()
            r5 = 0
            if (r4 == 0) goto L_0x002f
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r4 = r0.i
            long r7 = (long) r2
            r4.f((long) r7)
            r7 = r5
            r9 = r7
            goto L_0x009f
        L_0x002f:
            java.text.SimpleDateFormat r4 = new java.text.SimpleDateFormat
            java.lang.String r7 = "yy:MM:dd:HH:mm"
            java.util.Locale r8 = java.util.Locale.getDefault()
            r4.<init>(r7, r8)
            java.lang.String r7 = r20.ax()
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 != 0) goto L_0x0086
            java.lang.String r7 = r20.ay()
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 == 0) goto L_0x004f
            goto L_0x0086
        L_0x004f:
            java.lang.String r7 = r20.ax()     // Catch:{ ParseException -> 0x007e }
            java.util.Date r7 = r4.parse(r7)     // Catch:{ ParseException -> 0x007e }
            long r7 = r7.getTime()     // Catch:{ ParseException -> 0x007e }
            long r9 = java.lang.System.currentTimeMillis()     // Catch:{ ParseException -> 0x007c }
            java.util.Date r11 = new java.util.Date     // Catch:{ ParseException -> 0x0080 }
            long r12 = java.lang.System.currentTimeMillis()     // Catch:{ ParseException -> 0x0080 }
            r11.<init>(r12)     // Catch:{ ParseException -> 0x0080 }
            java.lang.String r11 = r4.format(r11)     // Catch:{ ParseException -> 0x0080 }
            java.util.Date r4 = r4.parse(r11)     // Catch:{ ParseException -> 0x0080 }
            long r11 = r4.getTime()     // Catch:{ ParseException -> 0x0080 }
            r4 = 0
            long r11 = r11 - r7
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r4 = r0.i     // Catch:{ ParseException -> 0x0080 }
            r4.f((long) r11)     // Catch:{ ParseException -> 0x0080 }
            goto L_0x008d
        L_0x007c:
            r9 = r5
            goto L_0x0080
        L_0x007e:
            r7 = r5
            r9 = r7
        L_0x0080:
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r4 = r0.i
            r4.f((long) r5)
            goto L_0x008d
        L_0x0086:
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r4 = r0.i
            r4.f((long) r5)
            r7 = r5
            r9 = r7
        L_0x008d:
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r4 = r0.i
            long r11 = r20.aE()
            r4.m((long) r11)
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r4 = r0.i
            long r11 = r20.az()
            r4.l((long) r11)
        L_0x009f:
            long r11 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerControl.f2211a
            int r4 = (int) r11
            long r11 = r0.w
            long r13 = (long) r4
            long r11 = r11 + r13
            r0.w = r11
            android.content.Context r11 = r0.q
            java.lang.String r12 = "total_play_sec"
            r15 = 4
            android.content.SharedPreferences r11 = r11.getSharedPreferences(r12, r15)
            java.lang.String r12 = "listenedTimeBeforeAppraised"
            long r15 = r11.getLong(r12, r5)
            r17 = 1000(0x3e8, double:4.94E-321)
            long r5 = r15 * r17
            java.lang.String r12 = "fjsdoifj"
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r15.append(r5)
            java.lang.String r3 = "    "
            r15.append(r3)
            long r1 = r0.w
            r15.append(r1)
            java.lang.String r1 = r15.toString()
            com.ximalaya.ting.android.opensdk.util.Logger.c(r12, r1)
            long r1 = r0.w
            int r3 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r3 <= 0) goto L_0x00f7
            android.content.Context r1 = r0.q
            if (r1 == 0) goto L_0x00f7
            java.lang.String r1 = "is_time_to_appraised"
            r2 = 0
            boolean r1 = r11.getBoolean(r1, r2)
            if (r1 != 0) goto L_0x00f7
            android.content.SharedPreferences$Editor r1 = r11.edit()
            java.lang.String r2 = "is_time_to_appraised"
            r3 = 1
            android.content.SharedPreferences$Editor r1 = r1.putBoolean(r2, r3)
            r1.apply()
        L_0x00f7:
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r1 = r0.i
            r1.d((long) r13)
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r1 = r0.i
            long r2 = java.lang.System.currentTimeMillis()
            r1.o((long) r2)
            r1 = 0
            int r3 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r3 != 0) goto L_0x0127
            java.text.SimpleDateFormat r1 = new java.text.SimpleDateFormat     // Catch:{ Exception -> 0x0123 }
            java.lang.String r2 = "yy:MM:dd:HH:mm"
            java.util.Locale r3 = java.util.Locale.getDefault()     // Catch:{ Exception -> 0x0123 }
            r1.<init>(r2, r3)     // Catch:{ Exception -> 0x0123 }
            java.lang.String r2 = r20.ay()     // Catch:{ Exception -> 0x0123 }
            java.util.Date r1 = r1.parse(r2)     // Catch:{ Exception -> 0x0123 }
            long r9 = r1.getTime()     // Catch:{ Exception -> 0x0123 }
            goto L_0x0127
        L_0x0123:
            long r9 = java.lang.System.currentTimeMillis()
        L_0x0127:
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r1 = r0.i
            r1.i((long) r9)
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r1 = r0.i
            boolean r1 = r1.g()
            if (r1 == 0) goto L_0x0141
            int r1 = r20.W()
            if (r1 == 0) goto L_0x014c
            int r1 = r20.W()
            int r3 = r4 / r1
            goto L_0x014d
        L_0x0141:
            r1 = 0
            long r9 = r9 - r7
            r1 = 0
            int r3 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r3 == 0) goto L_0x014c
            long r13 = r13 / r9
            int r3 = (int) r13
            goto L_0x014d
        L_0x014c:
            r3 = 0
        L_0x014d:
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r1 = r0.i
            long r2 = (long) r3
            long r4 = r20.al()
            long r2 = r2 * r4
            long r2 = r2 / r17
            r1.n((long) r2)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Test statistic track onStopTrack old"
            r1.append(r2)
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r2 = r0.i
            java.lang.String r2 = r2.k()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.ximalaya.ting.android.opensdk.util.Logger.a((java.lang.Object) r1)
            java.lang.String r1 = r20.s()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x0192
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r1 = r0.i
            java.lang.String r2 = r20.s()
            r1.b((java.lang.String) r2)
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r1 = r0.i
            java.lang.String r2 = r20.t()
            r1.c((java.lang.String) r2)
            goto L_0x01ce
        L_0x0192:
            com.ximalaya.ting.android.opensdk.model.album.SubordinatedAlbum r1 = r20.ao()
            if (r1 == 0) goto L_0x01ce
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r1 = r0.i
            com.ximalaya.ting.android.opensdk.model.album.SubordinatedAlbum r2 = r20.ao()
            java.lang.String r2 = r2.a()
            r1.b((java.lang.String) r2)
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r1 = r0.i
            com.ximalaya.ting.android.opensdk.model.album.SubordinatedAlbum r2 = r20.ao()
            java.lang.String r2 = r2.b()
            r1.c((java.lang.String) r2)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Test statistic track onStopTrack new"
            r1.append(r2)
            com.ximalaya.ting.android.opensdk.model.album.SubordinatedAlbum r2 = r20.ao()
            java.lang.String r2 = r2.a()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.ximalaya.ting.android.opensdk.util.Logger.a((java.lang.Object) r1)
        L_0x01ce:
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r1 = r0.i
            int r2 = r20.L()
            r1.e((int) r2)
            com.ximalaya.ting.android.opensdk.model.statistic.RecordModel r1 = r0.j
            if (r1 == 0) goto L_0x01fc
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r1 = r0.i
            com.ximalaya.ting.android.opensdk.model.statistic.RecordModel r2 = r0.j
            int r2 = r2.a()
            r1.g((int) r2)
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r1 = r0.i
            com.ximalaya.ting.android.opensdk.model.statistic.RecordModel r2 = r0.j
            int r2 = r2.b()
            r1.h((int) r2)
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r1 = r0.i
            com.ximalaya.ting.android.opensdk.model.statistic.RecordModel r2 = r0.j
            java.lang.String r2 = r2.c()
            r1.i((java.lang.String) r2)
        L_0x01fc:
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r1 = r0.i
            r2 = 0
            r1.i((int) r2)
            com.ximalaya.ting.android.opensdk.player.statistic.IXmPlayStatisticUpload r1 = r0.g
            if (r1 != 0) goto L_0x020c
            com.ximalaya.ting.android.opensdk.player.statistic.OpenSdkPlayStatisticUpload r1 = com.ximalaya.ting.android.opensdk.player.statistic.OpenSdkPlayStatisticUpload.b()
            r0.g = r1
        L_0x020c:
            java.lang.String r1 = "StatisticsManager"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "stop:position="
            r2.append(r3)
            r3 = r21
            r2.append(r3)
            java.lang.String r4 = "durtion="
            r2.append(r4)
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r4 = r0.i
            float r4 = r4.m()
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            com.ximalaya.ting.android.opensdk.util.Logger.c(r1, r2)
            java.lang.String r1 = r0.b
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto L_0x023e
            java.lang.String r1 = r0.f2247a
            r0.b = r1
        L_0x023e:
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r1 = r0.i
            int r2 = r0.u
            r1.c((int) r2)
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r1 = r0.i
            long r4 = r0.v
            r1.e((long) r4)
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r1 = r0.i
            java.lang.String r2 = r0.b
            r1.o((java.lang.String) r2)
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r1 = r0.i
            r1.f((int) r3)
            r19.g()
            com.ximalaya.ting.android.opensdk.player.statistic.IXmPlayStatisticUpload r1 = r0.g
            com.ximalaya.ting.android.opensdk.model.history.XmPlayRecord r2 = r0.i
            r1.a(r2)
            r19.f()
            r1 = 0
            com.ximalaya.ting.android.opensdk.player.service.XmPlayerControl.f2211a = r1
            return
        L_0x026a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.player.statistic.XmStatisticsManager.a(com.ximalaya.ting.android.opensdk.model.track.Track, int):void");
    }

    private void f() {
        if (!TextUtils.equals(this.f2247a, this.b) && !TextUtils.isEmpty(this.f2247a)) {
            this.b = this.f2247a;
        }
    }

    private void g() {
        if (this.c != null && this.i != null && this.c.containsKey("tid")) {
            this.i.p(this.c.get("tid"));
        }
    }

    public void b() {
        synchronized (f) {
            XmPlayerControl.f2211a = 0;
            XmPlayerControl.b = 0;
            if (this.h != null) {
                this.h.c();
            }
        }
    }

    public void a(RecordModel recordModel) {
        this.j = recordModel;
    }

    private boolean a(Track track) {
        if (track != null) {
            return "track".equals(track.b()) || PlayableModel.e.equals(track.b());
        }
        return false;
    }

    private boolean b(Track track) {
        return track != null && "schedule".equals(track.b());
    }

    private boolean c(Track track) {
        return track != null && "radio".equals(track.b()) && track.aw();
    }

    public void a(boolean z) {
        this.l = z;
    }

    public void a(int i2) {
        if (this.h != null) {
            this.h.a(i2);
        }
    }

    public void a(int i2, boolean z) {
        if (this.h != null) {
            this.h.a(i2, z);
        }
    }

    public void a(int i2, int i3, boolean z) {
        if (this.h != null) {
            this.h.a(i2, i3, z);
        }
    }

    public void c() {
        if (this.h != null) {
            this.h.a();
        }
    }

    public void d() {
        if (this.h != null) {
            this.h.b();
        }
    }
}
