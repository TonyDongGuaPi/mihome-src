package com.xiaomi.smarthome.shop.mishop;

import com.xiaomi.plugin.Callback;
import com.xiaomi.smarthome.shop.mishop.pojo.GidMap;
import com.xiaomi.smarthome.shop.mishop.pojo.PidMap;
import com.xiaomi.smarthome.shop.mishop.pojo.ProductIdMap;

public class ProductIdMapDataManager {

    /* renamed from: a  reason: collision with root package name */
    private static volatile ProductIdMapDataManager f22177a;
    /* access modifiers changed from: private */
    public ProductIdMap b;
    /* access modifiers changed from: private */
    public long c = 0;

    private ProductIdMapDataManager() {
    }

    public static ProductIdMapDataManager a() {
        if (f22177a == null) {
            synchronized (ProductIdMapDataManager.class) {
                if (f22177a == null) {
                    f22177a = new ProductIdMapDataManager();
                }
            }
        }
        return f22177a;
    }

    public void b() {
        if (System.currentTimeMillis() - this.c >= 300000) {
            this.c = System.currentTimeMillis();
            MiShopConfigApi.a(new Callback<ProductIdMap>() {
                /* renamed from: a */
                public void onCache(ProductIdMap productIdMap) {
                    ProductIdMap unused = ProductIdMapDataManager.this.b = productIdMap;
                }

                /* renamed from: a */
                public void onSuccess(ProductIdMap productIdMap, boolean z) {
                    ProductIdMap unused = ProductIdMapDataManager.this.b = productIdMap;
                }

                public void onFailure(int i, String str) {
                    long unused = ProductIdMapDataManager.this.c = 0;
                }
            });
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0012, code lost:
        r0 = (r0 = r2.b.getGidMap()).getGidMap();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(int r3) {
        /*
            r2 = this;
            com.xiaomi.smarthome.shop.mishop.pojo.ProductIdMap r0 = r2.b
            if (r0 != 0) goto L_0x0007
            java.lang.String r3 = ""
            return r3
        L_0x0007:
            com.xiaomi.smarthome.shop.mishop.pojo.ProductIdMap r0 = r2.b
            com.xiaomi.smarthome.shop.mishop.pojo.GidMap r0 = r0.getGidMap()
            if (r0 != 0) goto L_0x0012
            java.lang.String r3 = ""
            return r3
        L_0x0012:
            android.util.SparseArray r0 = r0.getGidMap()
            if (r0 == 0) goto L_0x0026
            int r1 = r0.indexOfKey(r3)
            if (r1 >= 0) goto L_0x001f
            goto L_0x0026
        L_0x001f:
            java.lang.Object r3 = r0.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            return r3
        L_0x0026:
            java.lang.String r3 = ""
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.shop.mishop.ProductIdMapDataManager.a(int):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0012, code lost:
        r0 = (r0 = r2.b.getPidMap()).getPidMap();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String b(int r3) {
        /*
            r2 = this;
            com.xiaomi.smarthome.shop.mishop.pojo.ProductIdMap r0 = r2.b
            if (r0 != 0) goto L_0x0007
            java.lang.String r3 = ""
            return r3
        L_0x0007:
            com.xiaomi.smarthome.shop.mishop.pojo.ProductIdMap r0 = r2.b
            com.xiaomi.smarthome.shop.mishop.pojo.PidMap r0 = r0.getPidMap()
            if (r0 != 0) goto L_0x0012
            java.lang.String r3 = ""
            return r3
        L_0x0012:
            android.util.SparseArray r0 = r0.getPidMap()
            if (r0 == 0) goto L_0x0026
            int r1 = r0.indexOfKey(r3)
            if (r1 >= 0) goto L_0x001f
            goto L_0x0026
        L_0x001f:
            java.lang.Object r3 = r0.get(r3)
            java.lang.String r3 = (java.lang.String) r3
            return r3
        L_0x0026:
            java.lang.String r3 = ""
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.shop.mishop.ProductIdMapDataManager.b(int):java.lang.String");
    }

    public boolean c() {
        if (this.b == null) {
            return false;
        }
        GidMap gidMap = this.b.getGidMap();
        if (gidMap != null && gidMap.hasData()) {
            return true;
        }
        PidMap pidMap = this.b.getPidMap();
        if (pidMap == null || !pidMap.hasData()) {
            return false;
        }
        return true;
    }
}
