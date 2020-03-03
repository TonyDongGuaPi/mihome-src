package com.xiaomi.smarthome.library.bluetooth.search;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.smarthome.library.bluetooth.search.classic.BluetoothClassicSearchTask;
import com.xiaomi.smarthome.library.bluetooth.search.le.BluetoothLeSearchTask;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BluetoothSearchRequest {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18530a = 100;
    private static final int b = 17;
    private List<BluetoothSearchTask> c = new ArrayList();
    private BluetoothSearchResponse d;
    private BluetoothSearchTask e;
    private Handler f;

    public void a() {
        if (this.d != null) {
            this.d.a();
        }
        d();
        a(17, 0);
    }

    /* access modifiers changed from: private */
    public void a(int i, int i2) {
        if (this.f == null) {
            this.f = new Handler(Looper.myLooper()) {
                public void handleMessage(Message message) {
                    if (message.what == 17) {
                        BluetoothSearchRequest.this.c();
                    }
                }
            };
        }
        this.f.sendMessageDelayed(this.f.obtainMessage(i), (long) i2);
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.c.size() > 0) {
            this.e = this.c.remove(0);
            this.e.a((BluetoothSearchResponse) new BluetoothSearchTaskResponse(this.e));
            return;
        }
        this.e = null;
        if (this.d != null) {
            this.d.b();
        }
    }

    private class BluetoothSearchTaskResponse implements BluetoothSearchResponse {

        /* renamed from: a  reason: collision with root package name */
        BluetoothSearchTask f18532a;

        BluetoothSearchTaskResponse(BluetoothSearchTask bluetoothSearchTask) {
            this.f18532a = bluetoothSearchTask;
        }

        public void a() {
            BluetoothLog.c(String.format("%s onSearchStarted", new Object[]{this.f18532a.toString()}));
        }

        public void a(BluetoothSearchResult bluetoothSearchResult) {
            BluetoothSearchRequest.this.a(bluetoothSearchResult);
        }

        public void b() {
            BluetoothLog.c(String.format("%s onSearchStopped", new Object[]{this.f18532a.toString()}));
            BluetoothSearchRequest.this.a(17, 100);
        }

        public void c() {
            BluetoothLog.c(String.format("%s onSearchCanceled", new Object[]{this.f18532a.toString()}));
        }
    }

    public void b() {
        if (this.e != null) {
            this.e.f();
            this.e = null;
        }
        this.c.clear();
        if (this.d != null) {
            this.d.c();
        }
        this.d = null;
    }

    private void d() {
        boolean z = false;
        boolean z2 = true;
        boolean z3 = false;
        for (BluetoothSearchTask next : this.c) {
            if (next.d()) {
                if (next.c() == null || next.c().length == 0) {
                    z = true;
                    z2 = false;
                } else {
                    z = true;
                }
            } else if (next.e()) {
                z3 = true;
            } else {
                throw new IllegalArgumentException("unknown search task type!");
            }
        }
        if (z && !z2) {
            e();
        }
        if (z3) {
            f();
        }
    }

    private void e() {
        BluetoothLog.c(String.format("scan for connected le devices", new Object[0]));
        for (BluetoothSearchResult a2 : BluetoothUtils.h()) {
            a(a2);
        }
    }

    private void f() {
        BluetoothLog.c(String.format("scan for bonded classic devices", new Object[0]));
        for (BluetoothSearchResult a2 : BluetoothUtils.j()) {
            a(a2);
        }
    }

    /* access modifiers changed from: private */
    public void a(BluetoothSearchResult bluetoothSearchResult) {
        if (this.d != null) {
            this.d.a(bluetoothSearchResult);
        }
    }

    public void a(List<BluetoothSearchTask> list) {
        this.c.clear();
        this.c.addAll(list);
    }

    public void a(BluetoothSearchResponse bluetoothSearchResponse) {
        this.d = bluetoothSearchResponse;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private List<BluetoothSearchTask> f18533a = new ArrayList();
        private BluetoothSearchResponse b;

        private void a(BluetoothSearchTask bluetoothSearchTask) {
            if (bluetoothSearchTask instanceof BluetoothLeSearchTask) {
                if (BluetoothUtils.a()) {
                    this.f18533a.add(bluetoothSearchTask);
                }
            } else if (bluetoothSearchTask instanceof BluetoothClassicSearchTask) {
                this.f18533a.add(bluetoothSearchTask);
            }
        }

        public Builder a() {
            a(10000, (UUID[]) null);
            return this;
        }

        public Builder a(int i) {
            return a(i, (UUID[]) null);
        }

        public Builder a(int i, UUID[] uuidArr) {
            BluetoothLeSearchTask bluetoothLeSearchTask = new BluetoothLeSearchTask();
            bluetoothLeSearchTask.b(i);
            bluetoothLeSearchTask.a(uuidArr);
            a((BluetoothSearchTask) bluetoothLeSearchTask);
            return this;
        }

        public Builder a(int i, int i2) {
            return a(i, i2, (UUID[]) null);
        }

        public Builder a(int i, int i2, UUID[] uuidArr) {
            for (int i3 = 0; i3 < i2; i3++) {
                a(i, uuidArr);
            }
            return this;
        }

        public Builder b(int i) {
            BluetoothClassicSearchTask bluetoothClassicSearchTask = new BluetoothClassicSearchTask();
            bluetoothClassicSearchTask.b(i);
            a((BluetoothSearchTask) bluetoothClassicSearchTask);
            return this;
        }

        public Builder b(int i, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                b(i);
            }
            return this;
        }

        public Builder a(BluetoothSearchResponse bluetoothSearchResponse) {
            this.b = bluetoothSearchResponse;
            return this;
        }

        public BluetoothSearchRequest b() {
            BluetoothSearchRequest bluetoothSearchRequest = new BluetoothSearchRequest();
            bluetoothSearchRequest.a(this.f18533a);
            bluetoothSearchRequest.a(this.b);
            return bluetoothSearchRequest;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (BluetoothSearchTask bluetoothSearchTask : this.c) {
            sb.append(bluetoothSearchTask.toString() + ", ");
        }
        return sb.toString();
    }
}
