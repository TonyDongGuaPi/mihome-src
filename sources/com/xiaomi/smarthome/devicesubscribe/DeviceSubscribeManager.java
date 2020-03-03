package com.xiaomi.smarthome.devicesubscribe;

import android.text.TextUtils;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.device.api.DevicelibApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.library.crypto.MD5Util;
import com.xiaomi.smarthome.library.crypto.SHA1Util;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class DeviceSubscribeManager {

    /* renamed from: a  reason: collision with root package name */
    private static DeviceSubscribeManager f15541a;
    /* access modifiers changed from: private */
    public static Object b = new Object();
    private Random c = new Random();
    private Map<String, SubscribeCallback> d = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public Map<String, SubscribeCallback> e = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public Map<String, Runnable> f = new ConcurrentHashMap();

    public static DeviceSubscribeManager a() {
        if (f15541a == null) {
            synchronized (b) {
                if (f15541a == null) {
                    f15541a = new DeviceSubscribeManager();
                }
            }
        }
        return f15541a;
    }

    public void a(String str, int i, List<String> list, int i2, final SubscribeCallback subscribeCallback) {
        if (subscribeCallback != null) {
            if (!CoreApi.a().q()) {
                subscribeCallback.a(new Error(-1, "not logged in"));
            } else if (i2 <= 0) {
                subscribeCallback.a(new Error(-1, "expire <=0"));
            } else {
                int i3 = i2 > 3 ? 3 : i2;
                final String c2 = c();
                Runnable runnable = this.f.get(c2);
                if (runnable == null) {
                    synchronized (b) {
                        runnable = this.f.get(c2);
                        if (runnable == null) {
                            runnable = new Runnable() {
                                public void run() {
                                    DeviceSubscribeManager.this.b(c2);
                                    DeviceSubscribeManager.this.f.remove(c2);
                                }
                            };
                            this.f.put(c2, runnable);
                        }
                    }
                }
                CommonApplication.getGlobalWorkerHandler().removeCallbacks(runnable);
                CommonApplication.getGlobalWorkerHandler().postDelayed(runnable, ((long) i3) * 1000);
                this.d.put(c2, subscribeCallback);
                DevicelibApi.subscribeDeviceV2(CommonApplication.getAppContext(), str, i, list, c2, i3, new AsyncCallback<Boolean, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Boolean bool) {
                        if (bool.booleanValue()) {
                            subscribeCallback.a(c2);
                        } else {
                            subscribeCallback.a(new Error(-1, ""));
                        }
                    }

                    public void onFailure(Error error) {
                        subscribeCallback.a(error);
                    }
                });
            }
        }
    }

    public void a(String str, int i, List<String> list, final String str2, final UnSubscribeCallback unSubscribeCallback) {
        DevicelibApi.unsubscribeDeviceV2(CommonApplication.getAppContext(), str, i, list, str2, new AsyncCallback<Boolean, Error>() {
            /* renamed from: a */
            public void onSuccess(Boolean bool) {
                if (bool.booleanValue()) {
                    DeviceSubscribeManager.this.a(str2);
                    if (unSubscribeCallback != null) {
                        unSubscribeCallback.a();
                    }
                } else if (unSubscribeCallback != null) {
                    unSubscribeCallback.a(new Error(-1, ""));
                }
            }

            public void onFailure(Error error) {
                if (unSubscribeCallback != null) {
                    unSubscribeCallback.a(new Error(-1, ""));
                }
            }
        });
    }

    public void a(JSONObject jSONObject, String str, final int i, final SubscribeCallback subscribeCallback) {
        if (subscribeCallback != null) {
            if (!CoreApi.a().q()) {
                subscribeCallback.a(new Error(-1, "not logged in"));
            } else if (i <= 0) {
                subscribeCallback.a(new Error(-1, "expire <=0"));
            } else {
                if (i > 180) {
                    i = 180;
                }
                DevicelibApi.subscribeDeviceBatchV2(CommonApplication.getAppContext(), jSONObject, str, i, new AsyncCallback<String, Error>() {
                    /* renamed from: a */
                    public void onSuccess(final String str) {
                        if (!TextUtils.isEmpty(str)) {
                            Runnable runnable = (Runnable) DeviceSubscribeManager.this.f.get(str);
                            if (runnable == null) {
                                synchronized (DeviceSubscribeManager.b) {
                                    runnable = (Runnable) DeviceSubscribeManager.this.f.get(str);
                                    if (runnable == null) {
                                        runnable = new Runnable() {
                                            public void run() {
                                                DeviceSubscribeManager.this.b(str);
                                                DeviceSubscribeManager.this.f.remove(str);
                                            }
                                        };
                                        DeviceSubscribeManager.this.f.put(str, runnable);
                                    }
                                }
                            }
                            CommonApplication.getGlobalWorkerHandler().removeCallbacks(runnable);
                            CommonApplication.getGlobalWorkerHandler().postDelayed(runnable, ((long) i) * 1000);
                            DeviceSubscribeManager.this.e.put(str, subscribeCallback);
                            if (subscribeCallback != null) {
                                subscribeCallback.a(str);
                            }
                        } else if (subscribeCallback != null) {
                            subscribeCallback.a(new Error(-1, "subId return null"));
                        }
                    }

                    public void onFailure(Error error) {
                        if (subscribeCallback != null) {
                            subscribeCallback.a(error);
                        }
                    }
                });
            }
        }
    }

    public void a(final String str, final UnSubscribeCallback unSubscribeCallback) {
        DevicelibApi.unsubscribeDeviceBatchV2(CommonApplication.getAppContext(), str, new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                DeviceSubscribeManager.this.b(str);
                if (unSubscribeCallback != null) {
                    unSubscribeCallback.a();
                }
            }

            public void onFailure(Error error) {
                if (unSubscribeCallback != null) {
                    unSubscribeCallback.a(new Error(-1, ""));
                }
            }
        });
    }

    private String c() {
        boolean g = ServerCompact.g(CommonApplication.getAppContext());
        String str = SystemApi.a().a(CommonApplication.getAppContext(), g) + CoreApi.a().s() + this.c.nextInt() + System.currentTimeMillis();
        return g ? SHA1Util.b(str) : MD5Util.a(str);
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.d.remove(str);
            this.f.remove(str);
        }
    }

    public void a(String str, String str2, String str3, JSONArray jSONArray) {
        SubscribeCallback subscribeCallback;
        if (!TextUtils.isEmpty(str) && (subscribeCallback = this.d.get(str)) != null) {
            subscribeCallback.a(str2, str3, jSONArray);
        }
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.e.remove(str);
        }
    }

    public void b(String str, String str2, String str3, JSONArray jSONArray) {
        SubscribeCallback subscribeCallback;
        if (!TextUtils.isEmpty(str) && (subscribeCallback = this.e.get(str)) != null) {
            subscribeCallback.a(str2, str3, jSONArray);
        }
    }
}
