package com.amap.api.services.a;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.a.ac;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IRoutePOISearch;
import com.amap.api.services.routepoisearch.RoutePOISearch;
import com.amap.api.services.routepoisearch.RoutePOISearchQuery;
import com.amap.api.services.routepoisearch.RoutePOISearchResult;

public class bi implements IRoutePOISearch {

    /* renamed from: a  reason: collision with root package name */
    private RoutePOISearchQuery f4321a;
    private Context b;
    /* access modifiers changed from: private */
    public RoutePOISearch.OnRoutePOISearchListener c;
    /* access modifiers changed from: private */
    public Handler d = null;

    public bi(Context context, RoutePOISearchQuery routePOISearchQuery) {
        this.b = context;
        this.f4321a = routePOISearchQuery;
        this.d = ac.a();
    }

    public void setRoutePOISearchListener(RoutePOISearch.OnRoutePOISearchListener onRoutePOISearchListener) {
        this.c = onRoutePOISearchListener;
    }

    public void searchRoutePOIAsyn() {
        as.a().a(new Runnable() {
            public void run() {
                RoutePOISearchResult routePOISearchResult;
                AMapException e;
                ac.j jVar;
                Message obtainMessage = bi.this.d.obtainMessage();
                obtainMessage.arg1 = 14;
                Bundle bundle = new Bundle();
                try {
                    routePOISearchResult = bi.this.searchRoutePOI();
                    try {
                        bundle.putInt("errorCode", 1000);
                        jVar = new ac.j();
                    } catch (AMapException e2) {
                        e = e2;
                        try {
                            bundle.putInt("errorCode", e.getErrorCode());
                            jVar = new ac.j();
                            jVar.b = bi.this.c;
                            jVar.f4286a = routePOISearchResult;
                            obtainMessage.obj = jVar;
                            obtainMessage.setData(bundle);
                            bi.this.d.sendMessage(obtainMessage);
                        } catch (Throwable th) {
                            th = th;
                            ac.j jVar2 = new ac.j();
                            jVar2.b = bi.this.c;
                            jVar2.f4286a = routePOISearchResult;
                            obtainMessage.obj = jVar2;
                            obtainMessage.setData(bundle);
                            bi.this.d.sendMessage(obtainMessage);
                            throw th;
                        }
                    }
                } catch (AMapException e3) {
                    AMapException aMapException = e3;
                    routePOISearchResult = null;
                    e = aMapException;
                    bundle.putInt("errorCode", e.getErrorCode());
                    jVar = new ac.j();
                    jVar.b = bi.this.c;
                    jVar.f4286a = routePOISearchResult;
                    obtainMessage.obj = jVar;
                    obtainMessage.setData(bundle);
                    bi.this.d.sendMessage(obtainMessage);
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    routePOISearchResult = null;
                    th = th3;
                    ac.j jVar22 = new ac.j();
                    jVar22.b = bi.this.c;
                    jVar22.f4286a = routePOISearchResult;
                    obtainMessage.obj = jVar22;
                    obtainMessage.setData(bundle);
                    bi.this.d.sendMessage(obtainMessage);
                    throw th;
                }
                jVar.b = bi.this.c;
                jVar.f4286a = routePOISearchResult;
                obtainMessage.obj = jVar;
                obtainMessage.setData(bundle);
                bi.this.d.sendMessage(obtainMessage);
            }
        });
    }

    public void setQuery(RoutePOISearchQuery routePOISearchQuery) {
        this.f4321a = routePOISearchQuery;
    }

    public RoutePOISearchQuery getQuery() {
        return this.f4321a;
    }

    public RoutePOISearchResult searchRoutePOI() throws AMapException {
        try {
            aa.a(this.b);
            if (a()) {
                return (RoutePOISearchResult) new ap(this.b, this.f4321a.clone()).c();
            }
            throw new AMapException(AMapException.AMAP_CLIENT_INVALID_PARAMETER);
        } catch (AMapException e) {
            s.a(e, "RoutePOISearchCore", "searchRoutePOI");
            throw e;
        }
    }

    private boolean a() {
        if (this.f4321a == null || this.f4321a.getSearchType() == null) {
            return false;
        }
        if (this.f4321a.getFrom() == null && this.f4321a.getTo() == null && this.f4321a.getPolylines() == null) {
            return false;
        }
        return true;
    }
}
