package com.amap.api.services.a;

import android.content.Context;
import android.os.Handler;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.district.DistrictResult;
import com.amap.api.services.district.DistrictSearch;
import com.amap.api.services.district.DistrictSearchQuery;
import com.amap.api.services.interfaces.IDistrictSearch;
import java.util.HashMap;

public class bd implements IDistrictSearch {
    private static HashMap<Integer, DistrictResult> f;

    /* renamed from: a  reason: collision with root package name */
    private Context f4306a;
    /* access modifiers changed from: private */
    public DistrictSearchQuery b;
    /* access modifiers changed from: private */
    public DistrictSearch.OnDistrictSearchListener c;
    private DistrictSearchQuery d;
    private int e;
    /* access modifiers changed from: private */
    public Handler g = ac.a();

    public bd(Context context) {
        this.f4306a = context.getApplicationContext();
    }

    private void a(DistrictResult districtResult) {
        f = new HashMap<>();
        if (this.b != null && districtResult != null && this.e > 0 && this.e > this.b.getPageNum()) {
            f.put(Integer.valueOf(this.b.getPageNum()), districtResult);
        }
    }

    public DistrictSearchQuery getQuery() {
        return this.b;
    }

    public void setQuery(DistrictSearchQuery districtSearchQuery) {
        this.b = districtSearchQuery;
    }

    private boolean a() {
        return this.b != null;
    }

    /* access modifiers changed from: protected */
    public DistrictResult a(int i) throws AMapException {
        if (b(i)) {
            return f.get(Integer.valueOf(i));
        }
        throw new AMapException(AMapException.AMAP_CLIENT_INVALID_PARAMETER);
    }

    private boolean b(int i) {
        return i < this.e && i >= 0;
    }

    public DistrictResult searchDistrict() throws AMapException {
        DistrictResult districtResult;
        try {
            DistrictResult districtResult2 = new DistrictResult();
            aa.a(this.f4306a);
            if (!a()) {
                this.b = new DistrictSearchQuery();
            }
            districtResult2.setQuery(this.b.clone());
            if (!this.b.weakEquals(this.d)) {
                this.e = 0;
                this.d = this.b.clone();
                if (f != null) {
                    f.clear();
                }
            }
            if (this.e == 0) {
                districtResult = (DistrictResult) new u(this.f4306a, this.b.clone()).c();
                if (districtResult == null) {
                    return districtResult;
                }
                this.e = districtResult.getPageCount();
                a(districtResult);
            } else {
                districtResult = a(this.b.getPageNum());
                if (districtResult == null) {
                    districtResult = (DistrictResult) new u(this.f4306a, this.b.clone()).c();
                    if (this.b != null) {
                        if (districtResult != null) {
                            if (this.e > 0 && this.e > this.b.getPageNum()) {
                                f.put(Integer.valueOf(this.b.getPageNum()), districtResult);
                            }
                        }
                    }
                    return districtResult;
                }
            }
            return districtResult;
        } catch (AMapException e2) {
            s.a(e2, "DistrictSearch", "searchDistrict");
            throw e2;
        }
    }

    public void searchDistrictAsyn() {
        try {
            as.a().a(new Runnable() {
                /* JADX WARNING: Removed duplicated region for block: B:29:0x00cd  */
                /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r7 = this;
                        com.amap.api.services.a.ac r0 = com.amap.api.services.a.ac.a()
                        android.os.Message r0 = r0.obtainMessage()
                        com.amap.api.services.district.DistrictResult r1 = new com.amap.api.services.district.DistrictResult
                        r1.<init>()
                        com.amap.api.services.a.bd r2 = com.amap.api.services.a.bd.this
                        com.amap.api.services.district.DistrictSearchQuery r2 = r2.b
                        r1.setQuery(r2)
                        r2 = 4
                        com.amap.api.services.a.bd r3 = com.amap.api.services.a.bd.this     // Catch:{ AMapException -> 0x0085, Throwable -> 0x005a, all -> 0x0055 }
                        com.amap.api.services.district.DistrictResult r3 = r3.searchDistrict()     // Catch:{ AMapException -> 0x0085, Throwable -> 0x005a, all -> 0x0055 }
                        if (r3 == 0) goto L_0x002c
                        com.amap.api.services.core.AMapException r1 = new com.amap.api.services.core.AMapException     // Catch:{ AMapException -> 0x002a, Throwable -> 0x0028 }
                        r1.<init>()     // Catch:{ AMapException -> 0x002a, Throwable -> 0x0028 }
                        r3.setAMapException(r1)     // Catch:{ AMapException -> 0x002a, Throwable -> 0x0028 }
                        goto L_0x002c
                    L_0x0028:
                        r1 = move-exception
                        goto L_0x005e
                    L_0x002a:
                        r1 = move-exception
                        goto L_0x0089
                    L_0x002c:
                        r0.arg1 = r2
                        com.amap.api.services.a.bd r1 = com.amap.api.services.a.bd.this
                        com.amap.api.services.district.DistrictSearch$OnDistrictSearchListener r1 = r1.c
                        r0.obj = r1
                        android.os.Bundle r1 = new android.os.Bundle
                        r1.<init>()
                        java.lang.String r2 = "result"
                        r1.putParcelable(r2, r3)
                        r0.setData(r1)
                        com.amap.api.services.a.bd r1 = com.amap.api.services.a.bd.this
                        android.os.Handler r1 = r1.g
                        if (r1 == 0) goto L_0x00ac
                    L_0x004b:
                        com.amap.api.services.a.bd r1 = com.amap.api.services.a.bd.this
                        android.os.Handler r1 = r1.g
                        r1.sendMessage(r0)
                        goto L_0x00ac
                    L_0x0055:
                        r3 = move-exception
                        r6 = r3
                        r3 = r1
                        r1 = r6
                        goto L_0x00ae
                    L_0x005a:
                        r3 = move-exception
                        r6 = r3
                        r3 = r1
                        r1 = r6
                    L_0x005e:
                        java.lang.String r4 = "DistrictSearch"
                        java.lang.String r5 = "searchDistrictAnsyThrowable"
                        com.amap.api.services.a.s.a(r1, r4, r5)     // Catch:{ all -> 0x00ad }
                        r0.arg1 = r2
                        com.amap.api.services.a.bd r1 = com.amap.api.services.a.bd.this
                        com.amap.api.services.district.DistrictSearch$OnDistrictSearchListener r1 = r1.c
                        r0.obj = r1
                        android.os.Bundle r1 = new android.os.Bundle
                        r1.<init>()
                        java.lang.String r2 = "result"
                        r1.putParcelable(r2, r3)
                        r0.setData(r1)
                        com.amap.api.services.a.bd r1 = com.amap.api.services.a.bd.this
                        android.os.Handler r1 = r1.g
                        if (r1 == 0) goto L_0x00ac
                        goto L_0x004b
                    L_0x0085:
                        r3 = move-exception
                        r6 = r3
                        r3 = r1
                        r1 = r6
                    L_0x0089:
                        r3.setAMapException(r1)     // Catch:{ all -> 0x00ad }
                        r0.arg1 = r2
                        com.amap.api.services.a.bd r1 = com.amap.api.services.a.bd.this
                        com.amap.api.services.district.DistrictSearch$OnDistrictSearchListener r1 = r1.c
                        r0.obj = r1
                        android.os.Bundle r1 = new android.os.Bundle
                        r1.<init>()
                        java.lang.String r2 = "result"
                        r1.putParcelable(r2, r3)
                        r0.setData(r1)
                        com.amap.api.services.a.bd r1 = com.amap.api.services.a.bd.this
                        android.os.Handler r1 = r1.g
                        if (r1 == 0) goto L_0x00ac
                        goto L_0x004b
                    L_0x00ac:
                        return
                    L_0x00ad:
                        r1 = move-exception
                    L_0x00ae:
                        r0.arg1 = r2
                        com.amap.api.services.a.bd r2 = com.amap.api.services.a.bd.this
                        com.amap.api.services.district.DistrictSearch$OnDistrictSearchListener r2 = r2.c
                        r0.obj = r2
                        android.os.Bundle r2 = new android.os.Bundle
                        r2.<init>()
                        java.lang.String r4 = "result"
                        r2.putParcelable(r4, r3)
                        r0.setData(r2)
                        com.amap.api.services.a.bd r2 = com.amap.api.services.a.bd.this
                        android.os.Handler r2 = r2.g
                        if (r2 == 0) goto L_0x00d6
                        com.amap.api.services.a.bd r2 = com.amap.api.services.a.bd.this
                        android.os.Handler r2 = r2.g
                        r2.sendMessage(r0)
                    L_0x00d6:
                        throw r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.bd.AnonymousClass1.run():void");
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void searchDistrictAnsy() {
        searchDistrictAsyn();
    }

    public void setOnDistrictSearchListener(DistrictSearch.OnDistrictSearchListener onDistrictSearchListener) {
        this.c = onDistrictSearchListener;
    }
}
