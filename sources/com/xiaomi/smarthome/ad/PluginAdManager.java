package com.xiaomi.smarthome.ad;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.RemoteException;
import com.xiaomi.smarthome.ad.api.AdPosition;
import com.xiaomi.smarthome.ad.api.AdType;
import com.xiaomi.smarthome.ad.api.Advertisement;
import com.xiaomi.smarthome.ad.api.IAdCallback;
import com.xiaomi.smarthome.ad.api.PluginAdApi;
import com.xiaomi.smarthome.ad.view.PluginAdUtil;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PluginAdManager {

    /* renamed from: a  reason: collision with root package name */
    private static volatile PluginAdManager f13631a = null;
    private static final String b = "com.xiaomi.popAd";
    private static final String c = "com.xiaomi.bottomFlowAd";
    private String d = "";
    private Map<String, IAdCallback> e = new HashMap();
    private IAdCallback f;
    private IAdCallback g;
    private List<AdPosition> h = new ArrayList();
    private PluginAdCloseCache i;
    private HandlerThread j;
    private Handler k;

    private PluginAdManager(Context context) {
        this.i = PluginAdCloseCache.a(context);
        this.j = new HandlerThread("plugin_id");
        this.j.start();
        this.k = new Handler(this.j.getLooper());
    }

    public static PluginAdManager a(Context context) {
        if (f13631a == null) {
            synchronized (PluginAdManager.class) {
                if (f13631a == null) {
                    f13631a = new PluginAdManager(context);
                }
            }
        }
        return f13631a;
    }

    public void a(String str, String str2) {
        this.d = str;
        a(str);
        final PluginRecord d2 = CoreApi.a().d(str);
        if (d2 != null) {
            this.k.post(new Runnable() {
                public void run() {
                    PluginAdApi.a().a(SHApplication.getAppContext(), d2.d(), (AsyncCallback<List<AdPosition>, Error>) new AsyncCallback<List<AdPosition>, Error>() {
                        public void onFailure(Error error) {
                        }

                        /* renamed from: a */
                        public void onSuccess(List<AdPosition> list) {
                            try {
                                PluginAdManager.this.a(list);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(List<AdPosition> list) throws RemoteException {
        if (!list.isEmpty()) {
            this.h.addAll(list);
            c();
            for (AdPosition next : list) {
                if (this.e.containsKey(next.a())) {
                    this.e.get(next.a()).onQueryAdSuccess(next);
                    this.e.remove(next.a());
                }
            }
        }
    }

    private void c() throws RemoteException {
        if (this.f != null && this.g != null) {
            AdPosition adPosition = null;
            AdPosition adPosition2 = null;
            for (AdPosition next : this.h) {
                if (next.b().size() > 0 && AdType.c.equals(next.b().get(0).d())) {
                    adPosition2 = next;
                } else if (next.b().size() > 0 && AdType.f13637a.equals(next.b().get(0).d())) {
                    adPosition = next;
                }
            }
            if (adPosition != null && a(adPosition) && adPosition2 != null && a(adPosition2)) {
                if (PluginAdUtil.a(adPosition).h() >= PluginAdUtil.a(adPosition2).h()) {
                    this.f.onQueryAdSuccess(adPosition);
                } else {
                    this.g.onQueryAdSuccess(adPosition2);
                }
            } else if (adPosition != null && a(adPosition)) {
                this.f.onQueryAdSuccess(adPosition);
            } else if (adPosition2 != null && a(adPosition2)) {
                this.g.onQueryAdSuccess(adPosition2);
            }
            this.f = null;
            this.g = null;
        }
    }

    public void a(String str, String str2, IAdCallback iAdCallback) throws RemoteException {
        if (b.equals(str2)) {
            this.f = iAdCallback;
        } else if (c.equals(str2)) {
            this.g = iAdCallback;
        } else if (str.equals(this.d)) {
            if (this.h.isEmpty()) {
                this.e.put(str2, iAdCallback);
            } else {
                a(str2, iAdCallback);
            }
        }
    }

    public void a(Advertisement advertisement) {
        this.i.b(advertisement);
        PluginAdApi.a().a(this.d);
    }

    public void a() {
        PluginAdApi.a().b(this.d);
    }

    public void b(Advertisement advertisement) {
        this.i.a(advertisement);
        PluginAdApi.a().d(this.d);
    }

    public void b() {
        PluginAdApi.a().c(this.d);
    }

    public void a(String str) {
        if (this.d.equals(str)) {
            this.h.clear();
            this.e.clear();
        }
    }

    private void a(String str, IAdCallback iAdCallback) throws RemoteException {
        for (AdPosition next : this.h) {
            if (str.equals(next.a()) && a(next)) {
                iAdCallback.onQueryAdSuccess(next);
                return;
            }
        }
        iAdCallback.onQueryAdFail();
    }

    private boolean a(AdPosition adPosition) {
        if (adPosition == null || adPosition.b().size() == 0) {
            return false;
        }
        Advertisement a2 = PluginAdUtil.a(adPosition);
        if (this.i.a(a2.a()) && this.i.b(a2.a())) {
            return false;
        }
        return true;
    }
}
