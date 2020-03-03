package com.xiaomi.smarthome.operation.mainpage;

import android.text.TextUtils;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.GridViewData;
import com.xiaomi.smarthome.newui.adapter.DeviceMainGridAdapterV2;
import com.xiaomi.smarthome.operation.BaseOperationProvider;
import com.xiaomi.smarthome.operation.Operation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainPageBannerHelper extends BaseOperationProvider {
    private Operation b;
    private boolean c = false;
    private OnOperationReadyListener d;

    public interface OnOperationReadyListener {
        void onOperationReady(Operation operation);
    }

    public void a(DeviceMainGridAdapterV2 deviceMainGridAdapterV2) {
    }

    /* access modifiers changed from: protected */
    public String b() {
        return "22";
    }

    public boolean m() {
        return c((List<Operation>) Collections.singletonList(this.b));
    }

    public Operation n() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public void a(List<Operation> list) {
        this.b = list.get(0);
        if (this.d != null) {
            this.d.onOperationReady(this.b);
        }
    }

    public void a(OnOperationReadyListener onOperationReadyListener) {
        this.d = onOperationReadyListener;
    }

    public void o() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.b);
        a((List<Operation>) arrayList, true);
        i();
    }

    /* access modifiers changed from: protected */
    public void a() {
        this.b = null;
        this.c = false;
    }

    /* access modifiers changed from: protected */
    public boolean j() {
        return k() && super.j();
    }

    /* access modifiers changed from: protected */
    public boolean b(List<Operation> list) {
        for (Operation operation : list) {
            if (!operation.p) {
                return false;
            }
        }
        if (HomeManager.a().E()) {
            return false;
        }
        List<GridViewData> F = HomeManager.a().F();
        if (F == null) {
            return super.b(list);
        }
        try {
            boolean z = F.size() == 1 && IRDeviceUtil.a(F.get(0).b.did);
            if (!super.b(list) || z) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            return super.b(list);
        }
    }

    public void g() {
        super.g();
        this.b = null;
        this.c = false;
        p();
    }

    /* access modifiers changed from: protected */
    public synchronized void i() {
        super.i();
        this.b = null;
    }

    /* access modifiers changed from: protected */
    public String c() {
        List<GridViewData> F = HomeManager.a().F();
        if (F == null) {
            return "0";
        }
        return String.valueOf(F.size());
    }

    public void p() {
        if (!this.c) {
            this.c = true;
            if (!TextUtils.isEmpty(l())) {
                a(0, TimeUnit.MILLISECONDS);
            } else {
                a(100, TimeUnit.MILLISECONDS);
            }
        }
    }
}
