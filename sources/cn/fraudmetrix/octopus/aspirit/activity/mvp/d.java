package cn.fraudmetrix.octopus.aspirit.activity.mvp;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import cn.fraudmetrix.octopus.aspirit.R;
import cn.fraudmetrix.octopus.aspirit.activity.mvp.a;
import cn.fraudmetrix.octopus.aspirit.bean.g;
import cn.fraudmetrix.octopus.aspirit.bean.i;
import cn.fraudmetrix.octopus.aspirit.c.b;
import cn.fraudmetrix.octopus.aspirit.c.c;
import cn.fraudmetrix.octopus.aspirit.g.f;
import cn.fraudmetrix.octopus.aspirit.main.OctopusManager;
import cn.fraudmetrix.octopus.aspirit.main.OctopusTaskCallBack;
import cn.fraudmetrix.octopus.aspirit.main.a;
import cn.fraudmetrix.octopus.aspirit.utils.OctopusConstants;
import cn.fraudmetrix.octopus.aspirit.utils.e;
import cn.fraudmetrix.octopus.aspirit.utils.h;
import cn.fraudmetrix.octopus.aspirit.utils.k;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tencent.smtt.sdk.WebView;
import com.unionpay.tsmservice.data.ResultCode;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class d extends cn.fraudmetrix.octopus.aspirit.base.ui.mvp.a<a.b> {

    /* renamed from: a  reason: collision with root package name */
    public JSONArray f613a;
    /* access modifiers changed from: private */
    public String c;
    /* access modifiers changed from: private */
    public String d;
    /* access modifiers changed from: private */
    public String e;
    /* access modifiers changed from: private */
    public cn.fraudmetrix.octopus.aspirit.b.a f;
    /* access modifiers changed from: private */
    public a.C0018a g = new c();
    /* access modifiers changed from: private */
    public b h = new cn.fraudmetrix.octopus.aspirit.c.a();
    /* access modifiers changed from: private */
    public a i = a.None;
    /* access modifiers changed from: private */
    public g j;
    private ScheduledFuture k = null;
    /* access modifiers changed from: private */
    public int l;
    /* access modifiers changed from: private */
    public int m = 0;
    private b.a n = new b.a() {
        public void a() {
            if (d.this.i != a.Complete) {
                d.this.b(d.this.m);
            }
        }

        public void a(int i) {
            ((a.b) d.this.b).a(i, true);
        }

        public void a(int i, f fVar) {
            d.this.f.a(fVar.b(), (String) null);
            ((a.b) d.this.b).a(fVar.b(), fVar.d());
            if (i == 1) {
                ((a.b) d.this.b).b(R.string.octopus_pregress_step3);
            }
        }

        public void a(List<? extends f> list) {
            d dVar;
            int i;
            a unused = d.this.i = a.Complete;
            ((a.b) d.this.b).a(95, true);
            if (d.this.l != 1) {
                ((a.b) d.this.b).a(100, false);
                d.this.b(0);
                return;
            }
            if (list == null || list.size() <= 0) {
                dVar = d.this;
                i = 31;
            } else if (TextUtils.isEmpty(((f) list.get(0)).e())) {
                dVar = d.this;
                i = 24;
            } else {
                ((a.b) d.this.b).b(R.string.octopus_pregress_step4);
                if (list.size() > 1) {
                    StringBuilder sb = new StringBuilder();
                    g z = d.this.j;
                    sb.append(z.stage);
                    sb.append(",upload");
                    z.stage = sb.toString();
                }
                StringBuilder sb2 = new StringBuilder();
                g z2 = d.this.j;
                sb2.append(z2.stage);
                sb2.append(",cookies");
                z2.stage = sb2.toString();
                d.this.g.a(d.this.e, list, (cn.fraudmetrix.octopus.aspirit.a.b) new cn.fraudmetrix.octopus.aspirit.a.b() {
                    /* renamed from: a */
                    public void b(JSONObject jSONObject) {
                        ((a.b) d.this.b).a(98, true);
                        d.this.b(0);
                    }

                    public void a(Throwable th) {
                        ((a.b) d.this.b).b(R.string.octopus_pregress_step10);
                        d.this.b(30);
                    }
                });
                return;
            }
            dVar.b(i);
        }

        public boolean a(f fVar) {
            return d.this.i == a.Run;
        }

        public void b(int i, f fVar) {
            d.this.f.a(fVar.b(), "0", (String) null);
            if (i == 0) {
                ((a.b) d.this.b).i();
                ((a.b) d.this.b).b(R.string.octopus_pregress_step2);
                cn.fraudmetrix.octopus.aspirit.main.a.a().c();
            }
            ((a.b) d.this.b).e();
        }

        public boolean c(int i, f fVar) {
            d.this.f.a(fVar.b(), ResultCode.ERROR_INTERFACE_GET_APP_DETAIL, (String) null);
            if (i == 0) {
                d.this.a(21);
                return false;
            }
            ((a.b) d.this.b).e();
            return true;
        }

        public void d(int i, f fVar) {
            if (!TextUtils.isEmpty(fVar.c())) {
                ((a.b) d.this.b).a("javascript:" + fVar.c(), (String) null);
                ((a.b) d.this.b).k();
            }
        }

        public void e(int i, f fVar) {
            d.this.f.a(fVar.b(), ResultCode.ERROR_INTERFACE_GET_APP_DETAIL, "cancel");
        }
    };
    private Runnable o = new Runnable() {
        public void run() {
            if (d.this.i == a.Run) {
                h.a("timeoutTask");
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        StringBuilder sb = new StringBuilder();
                        g z = d.this.j;
                        sb.append(z.stage);
                        sb.append(",timeout");
                        z.stage = sb.toString();
                        ((a.b) d.this.b).b(R.string.octopus_pregress_step10);
                        d.this.a(22);
                    }
                });
            }
        }
    };

    enum a {
        None,
        Run,
        Cancel,
        Timeout,
        Error,
        Complete
    }

    public d() {
        this.h.a(this.n);
        this.f = c.d();
        this.f.a();
        this.j = c.d().c();
    }

    /* access modifiers changed from: private */
    public void a(cn.fraudmetrix.octopus.aspirit.bean.b bVar) {
        if (bVar != null) {
            StringBuilder sb = new StringBuilder();
            g gVar = this.j;
            sb.append(gVar.stage);
            sb.append(",create");
            gVar.stage = sb.toString();
            this.g.a(((a.b) this.b).l(), this.c, bVar.channel_type, bVar.city_code, new cn.fraudmetrix.octopus.aspirit.a.a<i>() {
                /* renamed from: a */
                public void b(i iVar) {
                    if (TextUtils.isEmpty(iVar.taskId)) {
                        d.this.a(26);
                        return;
                    }
                    d.this.j.task_id = iVar.taskId;
                    d.this.b(iVar.taskId);
                    d.this.l();
                    if (a.f599a.containsKey(d.this.c)) {
                        cn.fraudmetrix.octopus.aspirit.main.a.a().a(k.a(OctopusManager.b().c(), a.f599a.get(d.this.c)));
                    }
                }

                public void a(Throwable th) {
                    d.this.a(20);
                }
            });
        }
    }

    private void a(String str, int i2) {
        try {
            JSONObject b = b(str, i2);
            if (b != null) {
                boolean z = true;
                switch (b.getIntValue("type")) {
                    case 0:
                        if (b.getIntValue("show_mask") <= 0) {
                            z = false;
                        }
                        ((a.b) this.b).a(z, b.getIntValue("show_millis"));
                        return;
                    case 1:
                        int intValue = b.getIntValue(NotificationCompat.CATEGORY_PROGRESS) + new Random().nextInt(5);
                        ((a.b) this.b).i();
                        ((a.b) this.b).a(intValue, true);
                        return;
                    case 2:
                        a(b.getIntValue("code"));
                        return;
                    default:
                        return;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private JSONObject b(String str, int i2) {
        if (!(this.f613a == null || this.f613a.size() == 0)) {
            int i3 = 0;
            while (i3 < this.f613a.size()) {
                try {
                    JSONObject jSONObject = this.f613a.getJSONObject(i3);
                    if (jSONObject != null && jSONObject.containsKey("regex") && !TextUtils.isEmpty(jSONObject.getString("regex")) && jSONObject.containsKey("type") && jSONObject.containsKey("when")) {
                        String string = jSONObject.getString("regex");
                        if ((jSONObject.getIntValue("when") & i2) == i2 && str.matches(string)) {
                            return jSONObject;
                        }
                    }
                    i3++;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void b(int i2) {
        h.a("exitGatherTasks code:" + i2 + " taskid:" + this.e);
        k();
        cn.fraudmetrix.octopus.aspirit.utils.a.a().b();
        c(i2);
        if (i2 == 0) {
            ((a.b) this.b).a(100, false);
            ((a.b) this.b).b(R.string.octopus_pregress_step5);
        }
        OctopusTaskCallBack p = OctopusManager.b().p();
        if (p != null) {
            if (TextUtils.isEmpty(this.e)) {
                this.e = Long.toString(System.currentTimeMillis());
            }
            p.onCallBack(i2, this.e);
        }
        ((a.b) this.b).finish();
    }

    /* access modifiers changed from: private */
    public void b(cn.fraudmetrix.octopus.aspirit.bean.b bVar) {
        if (bVar != null) {
            this.j.channel_info.channel_type = bVar.channel_type;
            this.j.channel_info.channel_code = bVar.channel_code;
            this.j.channel_info.city_code = bVar.city_code;
            this.j.channel_info.detail_type = bVar.detail_type;
            cn.fraudmetrix.octopus.aspirit.main.a.a().b(bVar.channel_type);
        }
    }

    private void c(final int i2) {
        OctopusManager.b().a().schedule(new Runnable() {
            public void run() {
                cn.fraudmetrix.octopus.aspirit.main.a.a().a(i2);
                d.this.f.b();
                c.d().e();
                a.C0022a.a();
                c.b.a();
            }
        }, 300, TimeUnit.MILLISECONDS);
    }

    /* access modifiers changed from: private */
    public void d(String str) {
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split(":");
            if (split.length >= 2) {
                ((a.b) this.b).d("taobaoauth:" + split[1], ((a.b) this.b).getResources().getString(R.string.octopus_onekey));
                return;
            }
        }
        ((a.b) this.b).c(R.string.octopus_onekey);
    }

    private boolean e(String str) {
        if (TextUtils.isEmpty(str) || !str.startsWith(OctopusConstants.C)) {
            return false;
        }
        String[] split = str.split(com.alipay.sdk.sys.a.b);
        if (split.length >= 2 && !TextUtils.isEmpty(split[1])) {
            String[] split2 = split[1].split("=");
            if (split2.length >= 2 && !TextUtils.isEmpty(split2[1])) {
                b(split2[1]);
                c();
                return true;
            }
        }
        a(26);
        return true;
    }

    private void k() {
        if (this.k != null) {
            this.k.cancel(true);
        }
    }

    /* access modifiers changed from: private */
    public void l() {
        if (this.i == a.Run) {
            this.k = OctopusManager.b().a().schedule(this.o, 10, TimeUnit.MINUTES);
        }
    }

    public void a() {
        ((a.b) this.b).j();
        this.j.stage = "config";
        this.g.a(this.c, this.d, (cn.fraudmetrix.octopus.aspirit.b.c<cn.fraudmetrix.octopus.aspirit.bean.b>) new cn.fraudmetrix.octopus.aspirit.b.c<cn.fraudmetrix.octopus.aspirit.bean.b>() {
            /* renamed from: a */
            public void b(cn.fraudmetrix.octopus.aspirit.bean.b bVar) {
                if (bVar == null || TextUtils.isEmpty(bVar.login_url)) {
                    d.this.a(25);
                    return;
                }
                String unused = d.this.d = bVar.channel_type;
                d.this.b(bVar);
                if (d.this.h.a(bVar)) {
                    d.this.f613a = bVar.special_urls;
                    a unused2 = d.this.i = a.Run;
                    int unused3 = d.this.l = bVar.source_type;
                    ((a.b) d.this.b).b(R.string.octopus_pregress_step1);
                    if (bVar.source_type == 1) {
                        d.this.a(bVar);
                    }
                    ((a.b) d.this.b).a(String.format(((a.b) d.this.b).getResources().getString(R.string.octopus_webview_titel), new Object[]{bVar.city_name}));
                    d.this.h.a();
                    return;
                }
                d.this.a(20);
            }

            public void a(Throwable th) {
                h.c("onError" + th.getLocalizedMessage());
                d.this.a(11);
            }
        });
    }

    public void a(int i2) {
        a aVar;
        if (this.i == a.Run || this.i == a.None) {
            if (i2 == 22) {
                aVar = a.Timeout;
            } else if (i2 != 50) {
                aVar = a.Error;
            } else {
                StringBuilder sb = new StringBuilder();
                g gVar = this.j;
                sb.append(gVar.stage);
                sb.append(",cancel");
                gVar.stage = sb.toString();
                aVar = a.Cancel;
            }
            this.i = aVar;
            this.m = i2;
            this.h.b();
        }
    }

    public void a(WebView webView, int i2) {
        this.h.a(webView.getUrl(), i2);
    }

    public void a(WebView webView, int i2, String str, String str2) {
        if (!b()) {
            this.h.a(i2, str, str2);
            cn.fraudmetrix.octopus.aspirit.b.a aVar = this.f;
            aVar.a(str2, i2 + "", str);
        }
    }

    public void a(WebView webView, String str) {
        if (!b()) {
            a(str, 4);
            this.h.a(str);
            this.f.a(str, "0", (String) null);
        }
    }

    public void a(String str) {
        ((a.b) this.b).a(false);
        this.g.a(str, new cn.fraudmetrix.octopus.aspirit.a.b() {
            /* renamed from: a */
            public void b(JSONObject jSONObject) {
                ((a.b) d.this.b).k();
                String string = ((a.b) d.this.b).getResources().getString(R.string.octopus_onekey);
                if (jSONObject.containsKey("data")) {
                    String string2 = jSONObject.getString("data");
                    if (!TextUtils.isEmpty(string2)) {
                        if ("005003".equals(d.this.c)) {
                            d.this.d(string2);
                            return;
                        } else {
                            ((a.b) d.this.b).d(string2, string);
                            return;
                        }
                    }
                }
                ((a.b) d.this.b).a(string);
            }

            public void a(Throwable th) {
                ((a.b) d.this.b).k();
                ((a.b) d.this.b).c(R.string.octopus_onekey);
            }
        });
    }

    public void a(String str, String str2) {
        this.h.b(str, str2);
    }

    public boolean a(Intent intent) {
        if (intent == null || !intent.hasExtra(OctopusConstants.d)) {
            return false;
        }
        this.c = intent.getStringExtra(OctopusConstants.d);
        this.d = intent.getStringExtra(OctopusConstants.h);
        cn.fraudmetrix.octopus.aspirit.main.a.a().a(this.c);
        cn.fraudmetrix.octopus.aspirit.main.a.a().b(this.d);
        cn.fraudmetrix.octopus.aspirit.main.a.a().a(new e(((a.b) this.b).l()));
        return !TextUtils.isEmpty(this.c);
    }

    public void b(WebView webView, String str) {
        a(str, 2);
    }

    public void b(String str) {
        this.e = str;
        cn.fraudmetrix.octopus.aspirit.main.a.a().c(this.e);
    }

    public void b(String str, String str2) {
        this.h.a(str, str2);
        ((a.b) this.b).j();
    }

    public boolean b() {
        return (this.i == a.Run || this.i == a.None) ? false : true;
    }

    public void c() {
        this.h.c();
    }

    public void c(String str) {
        this.j.user_info.account_name = str;
    }

    public boolean c(WebView webView, String str) {
        if (b() || e(str)) {
            return true;
        }
        this.f.a(str, (String) null);
        a(str, 1);
        boolean b = this.h.b(str);
        if (b) {
            this.f.a(str, ResultCode.ERROR_INTERFACE_GET_APP_DETAIL, "error url cancel load");
        }
        return b;
    }

    public void d() {
        super.d();
        k();
    }
}
