package com.tmall.wireless.vaf.framework;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.tmall.wireless.vaf.virtualview.ViewFactory;
import com.tmall.wireless.vaf.virtualview.core.Layout;
import com.tmall.wireless.vaf.virtualview.core.ViewBase;
import com.tmall.wireless.vaf.virtualview.core.ViewCache;
import com.tmall.wireless.vaf.virtualview.view.image.VirtualImage;
import java.util.LinkedList;
import java.util.List;

public class ViewManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9362a = "ViewManager_TMTEST";
    private ViewFactory b = new ViewFactory();
    private ArrayMap<String, List<ViewBase>> c = new ArrayMap<>();
    private SparseArray<ViewBase> d = new SparseArray<>();
    private VafContext e;

    public void a(VafContext vafContext) {
        this.e = vafContext;
        this.b.a(vafContext);
    }

    public ViewFactory a() {
        return this.b;
    }

    public boolean a(Context context) {
        return this.b.a(context);
    }

    public int a(String str) {
        return this.b.a(str);
    }

    public int a(byte[] bArr) {
        return this.b.a(bArr);
    }

    public int a(byte[] bArr, boolean z) {
        return this.b.a(bArr, z);
    }

    public ViewBase a(int i) {
        return this.d.get(i);
    }

    public void b() {
        for (int i = 0; i < this.c.size(); i++) {
            List valueAt = this.c.valueAt(i);
            if (valueAt != null) {
                for (int i2 = 0; i2 < valueAt.size(); i2++) {
                    ViewBase viewBase = (ViewBase) valueAt.get(i2);
                    viewBase.d();
                    ViewCache l = viewBase.l();
                    if (l != null) {
                        l.d();
                    }
                }
                valueAt.clear();
            }
        }
        this.c.clear();
        this.c = null;
        this.b.a();
        this.d.clear();
        this.d = null;
    }

    public ViewBase c() {
        VirtualImage virtualImage = new VirtualImage(this.e, new ViewCache());
        virtualImage.a(new Layout.Params());
        return virtualImage;
    }

    public int b(String str) {
        return this.b.c(str);
    }

    public ViewBase c(String str) {
        List list = this.c.get(str);
        if (list != null && list.size() != 0) {
            return (ViewBase) list.remove(0);
        }
        ViewBase a2 = this.b.a(str, this.d);
        if (a2 != null) {
            if (a2.D()) {
                this.e.n().a(a2);
            }
            a2.b(str);
        } else {
            Log.e(f9362a, "new view failed type:" + str);
        }
        return a2;
    }

    public void a(ViewBase viewBase) {
        if (viewBase != null) {
            String z = viewBase.z();
            if (!TextUtils.isEmpty(z)) {
                viewBase.e();
                List list = this.c.get(z);
                if (list == null) {
                    list = new LinkedList();
                    this.c.put(z, list);
                }
                list.add(viewBase);
                return;
            }
            Log.e(f9362a, "recycle type invalidate:" + z);
            RuntimeException runtimeException = new RuntimeException("here");
            runtimeException.fillInStackTrace();
            Log.w(f9362a, "Called: " + this, runtimeException);
        }
    }
}
