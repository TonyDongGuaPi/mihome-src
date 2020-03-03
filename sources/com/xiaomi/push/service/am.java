package com.xiaomi.push.service;

import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.mimc.common.MIMCConstant;
import com.xiaomi.push.service.XMPushService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class am {

    /* renamed from: a  reason: collision with root package name */
    private static am f12880a;

    /* renamed from: a  reason: collision with other field name */
    private List<a> f280a = new ArrayList();

    /* renamed from: a  reason: collision with other field name */
    private ConcurrentHashMap<String, HashMap<String, b>> f281a = new ConcurrentHashMap<>();

    public interface a {
        void a();
    }

    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private int f12881a = 0;

        /* renamed from: a  reason: collision with other field name */
        public Context f282a;

        /* renamed from: a  reason: collision with other field name */
        IBinder.DeathRecipient f283a = null;

        /* renamed from: a  reason: collision with other field name */
        Messenger f284a;

        /* renamed from: a  reason: collision with other field name */
        private XMPushService.b f285a = new XMPushService.b(this);

        /* renamed from: a  reason: collision with other field name */
        private XMPushService f286a;

        /* renamed from: a  reason: collision with other field name */
        final C0089b f287a = new C0089b();

        /* renamed from: a  reason: collision with other field name */
        c f288a = c.unbind;

        /* renamed from: a  reason: collision with other field name */
        public d f289a;

        /* renamed from: a  reason: collision with other field name */
        public String f290a;

        /* renamed from: a  reason: collision with other field name */
        private List<a> f291a = new ArrayList();

        /* renamed from: a  reason: collision with other field name */
        public boolean f292a;
        c b = null;

        /* renamed from: b  reason: collision with other field name */
        public String f293b;

        /* renamed from: b  reason: collision with other field name */
        private boolean f294b = false;
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;
        public String h;
        public String i;

        public interface a {
            void a(c cVar, c cVar2, int i);
        }

        /* renamed from: com.xiaomi.push.service.am$b$b  reason: collision with other inner class name */
        class C0089b extends XMPushService.i {

            /* renamed from: a  reason: collision with other field name */
            String f295a;
            int b;

            /* renamed from: b  reason: collision with other field name */
            String f296b;
            int c;

            public C0089b() {
                super(0);
            }

            public XMPushService.i a(int i, int i2, String str, String str2) {
                this.b = i;
                this.c = i2;
                this.f296b = str2;
                this.f295a = str;
                return this;
            }

            public String a() {
                return "notify job";
            }

            /* renamed from: a  reason: collision with other method in class */
            public void m306a() {
                if (b.this.a(this.b, this.c, this.f296b)) {
                    b.this.a(this.b, this.c, this.f295a, this.f296b);
                    return;
                }
                com.xiaomi.channel.commonutils.logger.b.b(" ignore notify client :" + b.this.g);
            }
        }

        class c implements IBinder.DeathRecipient {

            /* renamed from: a  reason: collision with root package name */
            final Messenger f12883a;

            /* renamed from: a  reason: collision with other field name */
            final b f297a;

            c(b bVar, Messenger messenger) {
                this.f297a = bVar;
                this.f12883a = messenger;
            }

            public void binderDied() {
                com.xiaomi.channel.commonutils.logger.b.b("peer died, chid = " + this.f297a.g);
                b.a(b.this).a((XMPushService.i) new ao(this, 0), 0);
                if ("9".equals(this.f297a.g) && com.xiaomi.stat.c.c.f23036a.equals(b.a(b.this).getPackageName())) {
                    b.a(b.this).a((XMPushService.i) new ap(this, 0), 60000);
                }
            }
        }

        public b() {
        }

        public b(XMPushService xMPushService) {
            this.f286a = xMPushService;
            a((a) new an(this));
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x0009, code lost:
            r1 = r3.lastIndexOf("/");
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static java.lang.String a(java.lang.String r3) {
            /*
                java.lang.String r0 = ""
                boolean r1 = android.text.TextUtils.isEmpty(r3)
                if (r1 == 0) goto L_0x0009
                return r0
            L_0x0009:
                java.lang.String r1 = "/"
                int r1 = r3.lastIndexOf(r1)
                r2 = -1
                if (r1 == r2) goto L_0x0018
                int r1 = r1 + 1
                java.lang.String r0 = r3.substring(r1)
            L_0x0018:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.am.b.a(java.lang.String):java.lang.String");
        }

        /* access modifiers changed from: private */
        public void a(int i2, int i3, String str, String str2) {
            this.b = this.f288a;
            if (i2 == 2) {
                this.f289a.a(this.f282a, this, i3);
            } else if (i2 == 3) {
                this.f289a.a(this.f282a, this, str2, str);
            } else if (i2 == 1) {
                boolean z = this.f288a == c.binded;
                if (!z && "wait".equals(str2)) {
                    this.f12881a++;
                } else if (z) {
                    this.f12881a = 0;
                    if (this.f284a != null) {
                        try {
                            this.f284a.send(Message.obtain((Handler) null, 16, this.f286a.f231a));
                        } catch (RemoteException unused) {
                        }
                    }
                }
                this.f289a.a(this.f286a, this, z, i3, str);
            }
        }

        /* access modifiers changed from: private */
        public boolean a(int i2, int i3, String str) {
            StringBuilder sb;
            String str2;
            if (this.b == null || !this.f294b) {
                return true;
            }
            if (this.b == this.f288a) {
                sb = new StringBuilder();
                str2 = " status recovered, don't notify client:";
            } else if (this.f284a == null || !this.f294b) {
                sb = new StringBuilder();
                str2 = "peer died, ignore notify ";
            } else {
                com.xiaomi.channel.commonutils.logger.b.b("Peer alive notify status to client:" + this.g);
                return true;
            }
            sb.append(str2);
            sb.append(this.g);
            com.xiaomi.channel.commonutils.logger.b.b(sb.toString());
            return false;
        }

        private boolean b(int i2, int i3, String str) {
            switch (i2) {
                case 1:
                    return this.f288a != c.binded && this.f286a.c() && i3 != 21 && (i3 != 7 || !"wait".equals(str));
                case 2:
                    return this.f286a.c();
                case 3:
                    return !"wait".equals(str);
                default:
                    return false;
            }
        }

        public long a() {
            return (((long) ((Math.random() * 20.0d) - 10.0d)) + ((long) ((this.f12881a + 1) * 15))) * 1000;
        }

        public String a(int i2) {
            switch (i2) {
                case 1:
                    return "OPEN";
                case 2:
                    return "CLOSE";
                case 3:
                    return MIMCConstant.q;
                default:
                    return "unknown";
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a  reason: collision with other method in class */
        public void m305a() {
            try {
                Messenger messenger = this.f284a;
                if (!(messenger == null || this.f283a == null)) {
                    messenger.getBinder().unlinkToDeath(this.f283a, 0);
                }
            } catch (Exception unused) {
            }
            this.b = null;
        }

        /* access modifiers changed from: package-private */
        public void a(Messenger messenger) {
            a();
            if (messenger != null) {
                try {
                    this.f284a = messenger;
                    this.f294b = true;
                    this.f283a = new c(this, messenger);
                    messenger.getBinder().linkToDeath(this.f283a, 0);
                } catch (Exception e2) {
                    com.xiaomi.channel.commonutils.logger.b.b("peer linkToDeath err: " + e2.getMessage());
                    this.f284a = null;
                    this.f294b = false;
                }
            } else {
                com.xiaomi.channel.commonutils.logger.b.b("peer linked with old sdk chid = " + this.g);
            }
        }

        public void a(a aVar) {
            synchronized (this.f291a) {
                this.f291a.add(aVar);
            }
        }

        public void a(c cVar, int i2, int i3, String str, String str2) {
            synchronized (this.f291a) {
                for (a a2 : this.f291a) {
                    a2.a(this.f288a, cVar, i3);
                }
            }
            int i4 = 0;
            if (this.f288a != cVar) {
                com.xiaomi.channel.commonutils.logger.b.a(String.format("update the client %7$s status. %1$s->%2$s %3$s %4$s %5$s %6$s", new Object[]{this.f288a, cVar, a(i2), aq.a(i3), str, str2, this.g}));
                this.f288a = cVar;
            }
            if (this.f289a == null) {
                com.xiaomi.channel.commonutils.logger.b.d("status changed while the client dispatcher is missing");
            } else if (cVar != c.binding) {
                if (this.b != null && this.f294b) {
                    i4 = (this.f284a == null || !this.f294b) ? IMediaPlayer.MEDIA_INFO_MEDIA_ACCURATE_SEEK_COMPLETE : 1000;
                }
                this.f286a.b((XMPushService.i) this.f287a);
                if (b(i2, i3, str2)) {
                    a(i2, i3, str, str2);
                } else {
                    this.f286a.a(this.f287a.a(i2, i3, str, str2), (long) i4);
                }
            }
        }

        public void b(a aVar) {
            synchronized (this.f291a) {
                this.f291a.remove(aVar);
            }
        }
    }

    public enum c {
        unbind,
        binding,
        binded
    }

    private am() {
    }

    public static synchronized am a() {
        am amVar;
        synchronized (am.class) {
            if (f12880a == null) {
                f12880a = new am();
            }
            amVar = f12880a;
        }
        return amVar;
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int indexOf = str.indexOf("@");
        return indexOf > 0 ? str.substring(0, indexOf) : str;
    }

    /* renamed from: a  reason: collision with other method in class */
    public synchronized int m298a() {
        return this.f281a.size();
    }

    public synchronized b a(String str, String str2) {
        HashMap hashMap = this.f281a.get(str);
        if (hashMap == null) {
            return null;
        }
        return (b) hashMap.get(a(str2));
    }

    /* renamed from: a  reason: collision with other method in class */
    public synchronized ArrayList<b> m299a() {
        ArrayList<b> arrayList;
        arrayList = new ArrayList<>();
        for (HashMap<String, b> values : this.f281a.values()) {
            arrayList.addAll(values.values());
        }
        return arrayList;
    }

    /* renamed from: a  reason: collision with other method in class */
    public synchronized Collection<b> m300a(String str) {
        if (!this.f281a.containsKey(str)) {
            return new ArrayList();
        }
        return ((HashMap) this.f281a.get(str).clone()).values();
    }

    /* renamed from: a  reason: collision with other method in class */
    public synchronized List<String> m301a(String str) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (HashMap<String, b> values : this.f281a.values()) {
            for (b bVar : values.values()) {
                if (str.equals(bVar.f290a)) {
                    arrayList.add(bVar.g);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a  reason: collision with other method in class */
    public synchronized void m302a() {
        Iterator it = a().iterator();
        while (it.hasNext()) {
            ((b) it.next()).a();
        }
        this.f281a.clear();
    }

    public synchronized void a(Context context) {
        for (HashMap<String, b> values : this.f281a.values()) {
            for (b a2 : values.values()) {
                a2.a(c.unbind, 1, 3, (String) null, (String) null);
            }
        }
    }

    public synchronized void a(Context context, int i) {
        for (HashMap<String, b> values : this.f281a.values()) {
            for (b a2 : values.values()) {
                a2.a(c.unbind, 2, i, (String) null, (String) null);
            }
        }
    }

    public synchronized void a(a aVar) {
        this.f280a.add(aVar);
    }

    public synchronized void a(b bVar) {
        HashMap hashMap = this.f281a.get(bVar.g);
        if (hashMap == null) {
            hashMap = new HashMap();
            this.f281a.put(bVar.g, hashMap);
        }
        hashMap.put(a(bVar.f293b), bVar);
        for (a a2 : this.f280a) {
            a2.a();
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public synchronized void m303a(String str) {
        HashMap hashMap = this.f281a.get(str);
        if (hashMap != null) {
            for (b a2 : hashMap.values()) {
                a2.a();
            }
            hashMap.clear();
            this.f281a.remove(str);
        }
        for (a a3 : this.f280a) {
            a3.a();
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public synchronized void m304a(String str, String str2) {
        HashMap hashMap = this.f281a.get(str);
        if (hashMap != null) {
            b bVar = (b) hashMap.get(a(str2));
            if (bVar != null) {
                bVar.a();
            }
            hashMap.remove(a(str2));
            if (hashMap.isEmpty()) {
                this.f281a.remove(str);
            }
        }
        for (a a2 : this.f280a) {
            a2.a();
        }
    }

    public synchronized void b() {
        this.f280a.clear();
    }
}
