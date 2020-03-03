package com.xiaomi.smarthome.homeroom;

import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.api.UserApi;
import com.xiaomi.smarthome.framework.api.UserConfig;
import com.xiaomi.smarthome.framework.api.UserConfigApi;
import com.xiaomi.smarthome.miio.Miio;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;

public class UserConfigKeyManager {

    /* renamed from: a  reason: collision with root package name */
    private static UserConfigKeyManager f18219a;
    /* access modifiers changed from: private */
    public AtomicBoolean b = new AtomicBoolean(false);
    private final List<UserConfigCallbackWrapper> c = Collections.synchronizedList(new ArrayList());

    private class UserConfigCallbackWrapper {

        /* renamed from: a  reason: collision with root package name */
        int f18223a;
        List<Integer> b;
        AsyncCallback<Map<Integer, UserConfig.UserConfigData>, Error> c;

        private UserConfigCallbackWrapper(int i, List<Integer> list, AsyncCallback<Map<Integer, UserConfig.UserConfigData>, Error> asyncCallback) {
            this.f18223a = i;
            this.b = list;
            this.c = asyncCallback;
        }
    }

    private UserConfigKeyManager() {
    }

    public static UserConfigKeyManager a() {
        if (f18219a == null) {
            synchronized (UserConfigKeyManager.class) {
                if (f18219a == null) {
                    f18219a = new UserConfigKeyManager();
                }
            }
        }
        return f18219a;
    }

    public void a(int i, List<Integer> list, AsyncCallback<Map<Integer, UserConfig.UserConfigData>, Error> asyncCallback) {
        if (list != null && list.size() != 0 && asyncCallback != null) {
            synchronized (this.c) {
                for (UserConfigCallbackWrapper userConfigCallbackWrapper : this.c) {
                    if (userConfigCallbackWrapper.c == asyncCallback) {
                        return;
                    }
                }
                this.c.add(new UserConfigCallbackWrapper(i, list, asyncCallback));
            }
        }
    }

    public void a(AsyncCallback<Map<Integer, UserConfig.UserConfigData>, Error> asyncCallback) {
        if (asyncCallback != null) {
            synchronized (this.c) {
                Iterator<UserConfigCallbackWrapper> it = this.c.iterator();
                while (it.hasNext()) {
                    if (it.next().c == asyncCallback) {
                        it.remove();
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(int i, Map<Integer, UserConfig.UserConfigData> map) {
        synchronized (this.c) {
            for (UserConfigCallbackWrapper next : this.c) {
                if (next.f18223a == i) {
                    HashMap hashMap = new HashMap();
                    if (map != null && map.size() > 0) {
                        for (Integer intValue : next.b) {
                            int intValue2 = intValue.intValue();
                            UserConfig.UserConfigData userConfigData = map.get(Integer.valueOf(intValue2));
                            if (userConfigData != null) {
                                hashMap.put(Integer.valueOf(intValue2), userConfigData);
                            }
                        }
                    }
                    next.c.onSuccess(hashMap);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(int i, Error error) {
        synchronized (this.c) {
            for (UserConfigCallbackWrapper next : this.c) {
                if (next.f18223a == i) {
                    next.c.onFailure(error);
                }
            }
        }
    }

    public void b() {
        if (!this.b.getAndSet(true)) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            synchronized (this.c) {
                for (UserConfigCallbackWrapper next : this.c) {
                    if (next.f18223a == 7) {
                        for (Integer intValue : next.b) {
                            int intValue2 = intValue.intValue();
                            if (!arrayList.contains(Integer.valueOf(intValue2))) {
                                arrayList.add(Integer.valueOf(intValue2));
                            }
                        }
                    }
                }
            }
            if (arrayList.size() == 0) {
                Miio.g("UserConfigKeyManager geUserConfigKeyAsyncComponentId7 keys is null");
                this.b.set(false);
                return;
            }
            JSONArray jSONArray = new JSONArray();
            for (Integer put : arrayList) {
                jSONArray.put(put);
            }
            UserConfigApi.a().a(SHApplication.getAppContext(), 7, jSONArray, (AsyncCallback<Map<Integer, UserConfig.UserConfigData>, Error>) new AsyncCallback<Map<Integer, UserConfig.UserConfigData>, Error>() {
                /* renamed from: a */
                public void onSuccess(Map<Integer, UserConfig.UserConfigData> map) {
                    UserConfigKeyManager.this.b.set(false);
                    UserConfigKeyManager.this.a(7, map);
                }

                public void onFailure(Error error) {
                    UserConfigKeyManager.this.b.set(false);
                    UserConfigKeyManager.this.a(7, error);
                }
            });
        }
    }

    public void a(final int i, final int i2, final AsyncCallback<String, Error> asyncCallback) {
        if (asyncCallback != null) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(i2);
            UserConfigApi.a().a(SHApplication.getAppContext(), i, jSONArray, (AsyncCallback<Map<Integer, UserConfig.UserConfigData>, Error>) new AsyncCallback<Map<Integer, UserConfig.UserConfigData>, Error>() {
                /* renamed from: a */
                public void onSuccess(Map<Integer, UserConfig.UserConfigData> map) {
                    if (map == null || map.get(Integer.valueOf(i2)) == null) {
                        asyncCallback.onSuccess("");
                        return;
                    }
                    final int[] iArr = new int[2];
                    UserApi.a(map.get(Integer.valueOf(i2)), iArr);
                    JSONArray jSONArray = new JSONArray();
                    if (iArr[1] > 0 && iArr[0] > 0) {
                        for (int i = iArr[0]; i < iArr[0] + iArr[1]; i++) {
                            jSONArray.put(i);
                        }
                    }
                    UserConfigApi.a().a(SHApplication.getAppContext(), i, jSONArray, (AsyncCallback<Map<Integer, UserConfig.UserConfigData>, Error>) new AsyncCallback<Map<Integer, UserConfig.UserConfigData>, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Map<Integer, UserConfig.UserConfigData> map) {
                            if (map == null || map.size() <= 0) {
                                asyncCallback.onSuccess("");
                                return;
                            }
                            asyncCallback.onSuccess(UserApi.a(iArr[0], iArr[1], map));
                        }

                        public void onFailure(Error error) {
                            asyncCallback.onFailure(error);
                        }
                    });
                }

                public void onFailure(Error error) {
                    asyncCallback.onFailure(error);
                }
            });
        }
    }
}
