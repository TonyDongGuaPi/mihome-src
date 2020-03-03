package com.xiaomi.smarthome.device.bluetooth.receiver;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.xiaomi.smarthome.application.CommonApplication;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbsBluetoothReceiver implements IBluetoothReceiver {

    /* renamed from: a  reason: collision with root package name */
    protected List<String> f15216a = new ArrayList();
    protected Context b = CommonApplication.getAppContext();
    protected Handler c = new Handler(Looper.getMainLooper());

    protected AbsBluetoothReceiver() {
    }

    /* access modifiers changed from: protected */
    public void a(String[] strArr) {
        if (this.f15216a == null) {
            this.f15216a = new ArrayList();
        }
        if (strArr != null && strArr.length > 0) {
            this.f15216a.addAll(Arrays.asList(strArr));
        }
    }

    public List<String> a() {
        return this.f15216a;
    }
}
