package com.xiaomi.smarthome.framework.update;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.framework.update.UpdateManager;
import com.xiaomi.smarthome.framework.update.generator.Generator;
import com.xiaomi.smarthome.framework.update.generator.InterpolationGenerator;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class UpdateItemHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final int f17725a = 945;
    private static final int b = 1200;
    private static UpdateItemHelper c;
    private static AtomicBoolean d = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public Map<String, UpdateItem> e = new HashMap();
    private List<WeakReference<FirmwareUpdateStatusListener>> f = new ArrayList();
    /* access modifiers changed from: private */
    public Handler g = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (UpdateItemHelper.this.h != null) {
                UpdateManager.a().a((List<KeyValuePair>) UpdateItemHelper.this.h, (UpdateManager.CheckAllFirmwareUpdateCallback) new UpdateManager.CheckAllFirmwareUpdateCallback() {
                    public void a() {
                        UpdateItemHelper.this.g.removeMessages(UpdateItemHelper.f17725a);
                        UpdateItemHelper.this.b();
                    }

                    public void a(List<FirmwareUpdateInfo> list) {
                        for (Map.Entry entry : UpdateItemHelper.this.e.entrySet()) {
                            Iterator<FirmwareUpdateInfo> it = list.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    if (it.next().f17724a.equals(entry.getKey())) {
                                        UpdateItemHelper.this.g.sendEmptyMessageDelayed(UpdateItemHelper.f17725a, 1200);
                                        return;
                                    }
                                }
                            }
                        }
                        UpdateItemHelper.this.g.removeMessages(UpdateItemHelper.f17725a);
                    }

                    public void b() {
                        UpdateItemHelper.this.g.removeMessages(UpdateItemHelper.f17725a);
                        UpdateItemHelper.this.b();
                    }
                });
            }
        }
    };
    /* access modifiers changed from: private */
    public List<KeyValuePair> h;

    public interface FirmwareUpdateStatusListener {
        void a(String str);

        void a(String str, int i);

        void b(String str);
    }

    private UpdateItemHelper() {
    }

    public static UpdateItemHelper a() {
        if (d.compareAndSet(false, true)) {
            c = new UpdateItemHelper();
        }
        return c;
    }

    /* access modifiers changed from: private */
    public void a(String str, int i) {
        for (WeakReference<FirmwareUpdateStatusListener> weakReference : this.f) {
            FirmwareUpdateStatusListener firmwareUpdateStatusListener = (FirmwareUpdateStatusListener) weakReference.get();
            if (firmwareUpdateStatusListener != null) {
                if (i == 100) {
                    firmwareUpdateStatusListener.a(str);
                    b(str);
                } else {
                    firmwareUpdateStatusListener.a(str, i);
                }
            }
        }
    }

    private void b(String str) {
        UpdateItem remove = this.e.remove(str);
        if (remove != null) {
            remove.a();
        }
    }

    public void a(FirmwareUpdateStatusListener firmwareUpdateStatusListener) {
        this.f.add(new WeakReference(firmwareUpdateStatusListener));
    }

    public void a(String str, boolean z, int i) {
        UpdateItem updateItem = this.e.get(str);
        if (updateItem == null) {
            if (i <= 100) {
                updateItem = new UpdateItem(str, z);
                this.e.put(str, updateItem);
            } else {
                return;
            }
        }
        updateItem.a(i);
    }

    public void b() {
        if (c != null) {
            this.g.removeCallbacksAndMessages((Object) null);
            d.set(false);
            c = null;
        }
    }

    public void a(String str) {
        for (WeakReference<FirmwareUpdateStatusListener> weakReference : this.f) {
            FirmwareUpdateStatusListener firmwareUpdateStatusListener = (FirmwareUpdateStatusListener) weakReference.get();
            if (firmwareUpdateStatusListener != null) {
                b(str);
                firmwareUpdateStatusListener.b(str);
            }
        }
    }

    public void a(boolean z) {
        if (this.e.isEmpty() || z) {
            b();
        } else {
            d();
        }
    }

    private void d() {
        this.g.sendEmptyMessageDelayed(f17725a, 1200);
    }

    public void c() {
        this.g.removeCallbacksAndMessages((Object) null);
    }

    public void a(List<KeyValuePair> list) {
        this.h = list;
    }

    private class UpdateItem implements Generator.OnProgressUpdateListener {
        private final String b;
        private final Generator c;

        UpdateItem(String str, boolean z) {
            this.b = str;
            this.c = new InterpolationGenerator(str, z);
            this.c.a((Generator.OnProgressUpdateListener) this);
        }

        public void a(int i) {
            this.c.a(i);
        }

        public void b(int i) {
            UpdateItemHelper.this.a(this.b, i);
        }

        public void a() {
            this.c.a();
        }
    }
}
