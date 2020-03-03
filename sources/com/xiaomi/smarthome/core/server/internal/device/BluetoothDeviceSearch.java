package com.xiaomi.smarthome.core.server.internal.device;

import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest;
import com.xiaomi.smarthome.core.entity.device.BtDevice;
import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.entity.device.ScanType;
import com.xiaomi.smarthome.core.server.internal.account.AccountManager;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BluetoothApi;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BtConstants;
import com.xiaomi.smarthome.core.server.internal.bluetooth.LocalSearchResponse;
import com.xiaomi.smarthome.core.server.internal.bluetooth.decorator.BluetoothDeviceDecorator;
import com.xiaomi.smarthome.core.server.internal.bluetooth.decorator.BluetoothDeviceDidDecorator;
import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.BluetoothRecognizeResult;
import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.BluetoothRecognizer;
import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.IBluetoothRecognizeResponse;
import com.xiaomi.smarthome.core.server.internal.device.DeviceSearch;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchHelper;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchRequest;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResponse;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResult;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.util.ListUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class BluetoothDeviceSearch extends MiioDeviceSearchBase {
    private static BluetoothDeviceSearch d;

    /* renamed from: a  reason: collision with root package name */
    private HashMap<String, Device> f14498a = new HashMap<>();
    private HashMap<String, Device> c = new HashMap<>();
    private DeviceSearch.DeviceSearchCallback e;
    private volatile boolean f;
    private LocalSearchResponse g;
    private ScheduledExecutorService h = Executors.newSingleThreadScheduledExecutor();
    /* access modifiers changed from: private */
    public ScheduledFuture i;
    private final BluetoothSearchResponse j = new BluetoothSearchResponse() {
        public void a() {
        }

        public void a(BluetoothSearchResult bluetoothSearchResult) {
            BluetoothDeviceSearch.this.a(bluetoothSearchResult);
        }

        public void b() {
            BluetoothDeviceSearch.this.h();
        }

        public void c() {
            BluetoothDeviceSearch.this.i();
        }
    };

    public int b() {
        return 6;
    }

    private BluetoothDeviceSearch() {
    }

    public static BluetoothDeviceSearch a() {
        if (d == null) {
            synchronized (BluetoothDeviceSearch.class) {
                if (d == null) {
                    d = new BluetoothDeviceSearch();
                }
            }
        }
        return d;
    }

    public void a(ScanType scanType, DeviceSearch.DeviceSearchCallback deviceSearchCallback) {
        this.e = deviceSearchCallback;
        j();
    }

    public void a(SearchRequest searchRequest, LocalSearchResponse localSearchResponse) {
        if (BluetoothUtils.b()) {
            if (!this.f) {
                this.f = true;
                k();
                this.g = localSearchResponse;
                a(new SearchTask(searchRequest), 500);
                return;
            }
            BluetoothLog.e("BluetoothDeviceSearch current request execute,please cancel");
        }
    }

    public void c() {
        this.h.execute(new Runnable() {
            public void run() {
                if (BluetoothDeviceSearch.this.i != null && !BluetoothDeviceSearch.this.i.isDone()) {
                    try {
                        BluetoothDeviceSearch.this.i.cancel(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    BluetoothDeviceSearch.this.i();
                }
                BluetoothSearchHelper.b().a((BluetoothSearchRequest) null);
            }
        });
    }

    private void a(SearchTask searchTask, long j2) {
        this.i = this.h.schedule(searchTask, j2, TimeUnit.MILLISECONDS);
    }

    public void a(List<Device> list) {
        ArrayList arrayList = new ArrayList();
        for (Device next : list) {
            if (next.f() == 6) {
                arrayList.add(new BtDevice(next));
            }
        }
        synchronized (this.c) {
            a((ArrayList<BtDevice>) arrayList);
        }
    }

    private void a(ArrayList<BtDevice> arrayList) {
        this.c.clear();
        c((List<BtDevice>) arrayList);
        ArrayList<BtDevice> d2 = BluetoothCache.d();
        HashSet hashSet = new HashSet();
        if (d2 != null && d2.size() > 0) {
            for (BtDevice n : d2) {
                hashSet.add(n.n());
            }
        }
        if (!ListUtils.a(arrayList)) {
            Iterator<BtDevice> it = arrayList.iterator();
            while (it.hasNext()) {
                BtDevice next = it.next();
                String n2 = next.n();
                this.c.put(n2, next);
                BluetoothCache.a(n2, next.m());
                BluetoothCache.e(n2, next.k());
                if (!TextUtils.equals(next.s(), BluetoothCache.o(n2))) {
                    BluetoothCache.i(n2, next.s());
                    BluetoothCache.j(n2, "");
                    BluetoothCache.k(n2, "");
                    BluetoothCache.f(n2, 0);
                    BluetoothCache.a(n2, true);
                }
                BluetoothCache.g(n2, next.l());
                BluetoothCache.m(n2, next.R());
                String str = "";
                try {
                    if (!TextUtils.isEmpty(next.E())) {
                        str = new JSONObject(next.E()).optString("smac", "");
                    }
                    if (!TextUtils.isEmpty(str)) {
                        BluetoothCache.h(n2, str);
                    }
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
                BluetoothCache.a(n2, next.p());
                BluetoothCache.b(n2, next.C());
                if (!TextUtils.isEmpty(next.D())) {
                    BluetoothCache.d(n2, next.D());
                } else {
                    BluetoothCache.d(n2, AccountManager.a().m());
                }
                BluetoothCache.d(n2, 2);
                hashSet.remove(n2);
                if (BluetoothLog.a()) {
                    BluetoothLog.e(String.format("Remote Ble Device: %s", new Object[]{next}));
                }
            }
        }
        if (hashSet.size() > 0) {
            Iterator it2 = hashSet.iterator();
            while (it2.hasNext()) {
                String str2 = (String) it2.next();
                BluetoothMyLogger.e("BluetoothDeviceSearch set device NOT_BOUNDED: " + str2);
                BluetoothCache.d(str2, 0);
            }
        }
    }

    private void c(List<BtDevice> list) {
        for (BtDevice next : BluetoothCache.e()) {
            if (!list.contains(next)) {
                BluetoothApi.a(next.n(), 0);
                BluetoothApi.d(next.n());
            }
        }
    }

    private void b(ArrayList<BtDevice> arrayList) {
        Intent intent = new Intent(BtConstants.i);
        intent.putParcelableArrayListExtra("extra.device", arrayList);
        BluetoothUtils.a(intent);
    }

    /* access modifiers changed from: private */
    public void a(SearchRequest searchRequest) {
        BluetoothSearchHelper.b().a(searchRequest.a(), this.j);
    }

    /* access modifiers changed from: private */
    public void f() {
        ArrayList<BluetoothSearchResult> arrayList = new ArrayList<>();
        arrayList.addAll(BluetoothUtils.h());
        arrayList.addAll(BluetoothUtils.j());
        if (!ListUtils.a(arrayList)) {
            for (BluetoothSearchResult bluetoothSearchResult : arrayList) {
                BluetoothLog.c(String.format("Connected Device: %s", new Object[]{bluetoothSearchResult}));
                BluetoothRecognizeResult a2 = BluetoothRecognizer.a().a(bluetoothSearchResult, 30000);
                BluetoothLog.c(String.format("Connected Device recognize Device: %s, result.model: %s, isBleDevice: %b, isBleDeviceConnected: %b", new Object[]{bluetoothSearchResult, a2.f14269a, Boolean.valueOf(bluetoothSearchResult.b()), Boolean.valueOf(BluetoothUtils.a(bluetoothSearchResult.f()))}));
                if (!TextUtils.isEmpty(a2.f14269a)) {
                    BtDevice btDevice = new BtDevice(bluetoothSearchResult);
                    btDevice.b(a2.f14269a);
                    a(btDevice);
                    if (btDevice.d()) {
                        btDevice.g();
                    }
                    b(btDevice);
                }
            }
        }
    }

    public List<Device> d() {
        ArrayList<Device> arrayList = new ArrayList<>();
        synchronized (this.c) {
            arrayList.addAll(this.c.values());
        }
        synchronized (this.f14498a) {
            for (Map.Entry<String, Device> value : this.f14498a.entrySet()) {
                BtDevice btDevice = (BtDevice) value.getValue();
                if (!arrayList.contains(btDevice)) {
                    arrayList.add(btDevice);
                }
            }
        }
        ArrayList<BtDevice> b = BluetoothCache.b();
        synchronized (this.f14498a) {
            for (BtDevice next : b) {
                if (!arrayList.contains(next)) {
                    arrayList.add(next);
                }
            }
        }
        for (Device device : arrayList) {
            BluetoothDeviceDidDecorator.a().a((BtDevice) device);
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void a(BtDevice btDevice) {
        BluetoothDeviceDecorator.a().a(btDevice);
    }

    /* access modifiers changed from: private */
    public void a(final BluetoothSearchResult bluetoothSearchResult) {
        BluetoothRecognizer.a().a(bluetoothSearchResult, (IBluetoothRecognizeResponse) new IBluetoothRecognizeResponse() {
            public void a(BluetoothRecognizeResult bluetoothRecognizeResult) {
                if (!TextUtils.isEmpty(bluetoothRecognizeResult.f14269a)) {
                    BtDevice btDevice = new BtDevice(bluetoothSearchResult);
                    btDevice.b(bluetoothRecognizeResult.f14269a);
                    btDevice.a(bluetoothRecognizeResult.c);
                    BluetoothDeviceSearch.this.a(btDevice);
                    BluetoothCache.a(bluetoothSearchResult.g(), bluetoothSearchResult.i());
                    BluetoothDeviceSearch.this.b(btDevice);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void g() {
        BluetoothUtils.a(BtConstants.j);
        if (this.g != null) {
            this.g.a();
        }
    }

    /* access modifiers changed from: private */
    public void h() {
        j();
        this.f = false;
        this.i = null;
        BluetoothUtils.a(BtConstants.k);
        if (this.g != null) {
            this.g.b();
        }
        this.g = null;
    }

    /* access modifiers changed from: private */
    public void i() {
        j();
        this.f = false;
        this.i = null;
        BluetoothUtils.a(BtConstants.l);
        if (this.g != null) {
            this.g.c();
        }
        this.g = null;
    }

    /* access modifiers changed from: private */
    public void b(BtDevice btDevice) {
        BluetoothCache.c(btDevice.n(), btDevice.r());
        c(btDevice);
        if (this.g != null) {
            this.g.a(btDevice);
        }
    }

    private void j() {
        if (this.e != null) {
            this.e.a(b(), d());
        }
    }

    private void k() {
        synchronized (this.f14498a) {
            this.f14498a.clear();
        }
    }

    private boolean c(BtDevice btDevice) {
        synchronized (this.f14498a) {
            if (this.f14498a.containsKey(btDevice.n())) {
                return false;
            }
            this.f14498a.put(btDevice.n(), btDevice);
            return true;
        }
    }

    private class SearchTask implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        SearchRequest f14502a;

        public SearchTask(SearchRequest searchRequest) {
            this.f14502a = searchRequest == null ? a() : searchRequest;
        }

        private SearchRequest a() {
            return new SearchRequest.Builder().a(10000).b(10000).a();
        }

        public void run() {
            BluetoothDeviceSearch.this.g();
            BluetoothDeviceSearch.this.f();
            BluetoothDeviceSearch.this.a(this.f14502a);
        }
    }
}
