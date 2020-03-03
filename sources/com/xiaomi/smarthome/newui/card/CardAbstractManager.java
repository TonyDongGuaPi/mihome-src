package com.xiaomi.smarthome.newui.card;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.mi.global.bbs.manager.Region;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.server.internal.plugin.util.Callback;
import com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils;
import com.xiaomi.smarthome.core.server.internal.plugin.util.ParcelUtils;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.devicesubscribe.DevicePropSubscriber;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.plugin.ZipFileUtils;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class CardAbstractManager implements ZipFileUtils.TranslateName {
    private static final String d = "card_icon_translated";

    /* renamed from: a  reason: collision with root package name */
    protected Map<String, Map<String, Object>> f20467a = new ConcurrentHashMap();
    protected Map<String, Map<String, Long>> b = new ConcurrentHashMap();
    private LinkedList<OnStateChangedListener> c = new LinkedList<>();
    /* access modifiers changed from: private */
    public AtomicBoolean e = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public String f = "";

    /* access modifiers changed from: protected */
    public abstract File c();

    public Map<String, Object> b(String str) {
        return this.f20467a.get(str);
    }

    public Map<String, Object> c(String str) {
        return this.f20467a.remove(str);
    }

    /* access modifiers changed from: protected */
    public void a() {
        Parcel b2;
        File c2 = c();
        if (c2 == null || (b2 = FileUtils.b(c2)) == null) {
            LogUtil.b("mijia-card", "loadPropsFromLocal file:" + c2);
            return;
        }
        try {
            ParcelUtils.a(b2, CardAbstractManager.class.getClassLoader(), new Callback<Void, Map>() {

                /* renamed from: a  reason: collision with root package name */
                int f20468a;

                /* renamed from: a */
                public Map call(Void voidR) throws Exception {
                    this.f20468a++;
                    if (this.f20468a == 1) {
                        return CardAbstractManager.this.f20467a;
                    }
                    return Collections.synchronizedMap(new ArrayMap());
                }
            });
            ParcelUtils.a(b2, CardAbstractManager.class.getClassLoader(), new Callback<Void, Map>() {

                /* renamed from: a  reason: collision with root package name */
                int f20469a;

                /* renamed from: a */
                public Map call(Void voidR) throws Exception {
                    this.f20469a++;
                    if (this.f20469a == 1) {
                        return CardAbstractManager.this.b;
                    }
                    return Collections.synchronizedMap(new ArrayMap());
                }
            });
            LogUtil.c("mijia-card", "loadPropsFromLocal refresh propSize:" + this.f20467a.size() + " timeSize:" + this.b.size() + " file:" + c2);
        } catch (Exception e2) {
            LogUtil.b("mijia-card", "loadPropsFromLocal error " + Log.getStackTraceString(e2));
        }
    }

    /* access modifiers changed from: protected */
    public void b() {
        File c2 = c();
        if (c2 == null) {
            LogUtil.c("mijia-card", "savePropsCache cacheFile is null");
            return;
        }
        Parcel obtain = Parcel.obtain();
        ParcelUtils.a(obtain, (Map) this.f20467a);
        ParcelUtils.a(obtain, (Map) this.b);
        FileUtils.a(c2, obtain.marshall());
        obtain.recycle();
        LogUtil.c("mijia-card", "savePropsCache size:" + this.f20467a.size() + " file:" + c2);
    }

    public Object a(String str, String str2, Object obj) {
        if (TextUtils.isEmpty(str2)) {
            b(str, str2, obj);
            return null;
        }
        Map map = this.f20467a.get(str);
        if (map == null) {
            Map synchronizedMap = Collections.synchronizedMap(new ArrayMap());
            this.f20467a.put(str, synchronizedMap);
            synchronizedMap.put(str2, obj);
            b(str, str2, obj);
            return null;
        }
        Object obj2 = map.get(str2);
        if (obj2 == null || !obj2.equals(obj)) {
            map.put(str2, obj);
            b(str, str2, obj);
        }
        return obj2;
    }

    private void b(String str, String str2, Object obj) {
        for (int size = this.c.size() - 1; size >= 0; size--) {
            this.c.get(size).onStateChanged(str, str2, obj);
        }
    }

    public long a(String str, String str2, long j) {
        Map map = this.b.get(str);
        if (map == null) {
            Map synchronizedMap = Collections.synchronizedMap(new ArrayMap());
            this.b.put(str, synchronizedMap);
            synchronizedMap.put(str2, Long.valueOf(j));
            return 0;
        }
        Long l = (Long) map.put(str2, Long.valueOf(j));
        if (l == null) {
            return 0;
        }
        return l.longValue();
    }

    public void a(OnStateChangedListener onStateChangedListener) {
        if (onStateChangedListener != null && !this.c.contains(onStateChangedListener)) {
            this.c.add(onStateChangedListener);
        }
    }

    public void b(OnStateChangedListener onStateChangedListener) {
        this.c.remove(onStateChangedListener);
    }

    public Map<String, Long> d(String str) {
        return this.b.get(str);
    }

    public static class CardDeviceSubscriberInterface implements DevicePropSubscriber.DeviceSubscriberInterface {

        /* renamed from: a  reason: collision with root package name */
        private final WeakReference<? extends CardAbstractManager> f20472a;

        public CardDeviceSubscriberInterface(CardAbstractManager cardAbstractManager) {
            this.f20472a = new WeakReference<>(cardAbstractManager);
        }

        public List<String> a(String str) {
            Map<String, Object> b;
            CardAbstractManager cardAbstractManager = (CardAbstractManager) this.f20472a.get();
            if (cardAbstractManager == null || (b = cardAbstractManager.b(str)) == null) {
                return null;
            }
            return new ArrayList(b.keySet());
        }

        public void a(String str, JSONArray jSONArray) {
            CardAbstractManager cardAbstractManager = (CardAbstractManager) this.f20472a.get();
            if (cardAbstractManager != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    if (optJSONObject != null && !optJSONObject.isNull("key")) {
                        Object opt = optJSONObject.opt("value");
                        String optString = optJSONObject.optString("key");
                        long optLong = optJSONObject.optLong("time", 0);
                        try {
                            if (!optString.startsWith(Device.EVENT_PREFIX) && (opt instanceof JSONArray) && ((JSONArray) opt).length() > 0) {
                                opt = ((JSONArray) opt).get(0);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        cardAbstractManager.a(str, optString, opt);
                        cardAbstractManager.a(str, optString, optLong);
                    }
                }
            }
        }

        public JSONArray b(String str) {
            Map<String, Object> b;
            JSONArray jSONArray = new JSONArray();
            CardAbstractManager cardAbstractManager = (CardAbstractManager) this.f20472a.get();
            if (cardAbstractManager == null || (b = cardAbstractManager.b(str)) == null) {
                return jSONArray;
            }
            for (Map.Entry next : b.entrySet()) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put((String) next.getKey(), next.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                jSONArray.put(jSONObject);
            }
            return jSONArray;
        }
    }

    /* access modifiers changed from: private */
    @NonNull
    public String e(String str) {
        return FileUtils.a(CommonApplication.getAppContext()) + File.separator + d + File.separator + str.hashCode() + File.separator;
    }

    public String d() {
        return this.f;
    }

    /* access modifiers changed from: private */
    @SuppressLint({"StaticFieldLeak"})
    /* renamed from: b */
    public void c(JSONObject jSONObject) {
        if (!this.e.getAndSet(true)) {
            final String a2 = a(jSONObject);
            LogUtilGrey.a("mijia-card", "MiotSpecCardManager.downloadAndUnZipCardIconRes url:" + a2 + " version:" + a2.hashCode());
            String str = FileUtils.a(CommonApplication.getAppContext()) + File.separator + d + File.separator + "temp" + File.separator + a2.hashCode();
            FileUtils.k(str);
            FileUtils.d(str + "iconZip");
            final File file = new File(str + "iconZip");
            Observable.create(new ObservableOnSubscribe(a2, file) {
                private final /* synthetic */ String f$1;
                private final /* synthetic */ File f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void subscribe(ObservableEmitter observableEmitter) {
                    CardAbstractManager.this.a(this.f$1, this.f$2, observableEmitter);
                }
            }).observeOn(Schedulers.io()).subscribe(new Observer<Boolean>() {
                public void onComplete() {
                }

                public void onSubscribe(Disposable disposable) {
                }

                /* renamed from: a */
                public void onNext(Boolean bool) {
                    try {
                        Thread.currentThread().setName("miotspeccard");
                        String a2 = CardAbstractManager.this.e(a2);
                        File file = new File(a2);
                        if (!file.exists()) {
                            if (!file.exists()) {
                                file.mkdirs();
                            }
                            if (ZipFileUtils.a(file.getAbsolutePath(), file.getAbsolutePath(), (ZipFileUtils.TranslateName) CardAbstractManager.this)) {
                                String unused = CardAbstractManager.this.f = a2;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } catch (Throwable th) {
                        CardAbstractManager.this.e.set(false);
                        throw th;
                    }
                    CardAbstractManager.this.e.set(false);
                }

                public void onError(Throwable th) {
                    CardAbstractManager.this.e.set(false);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(String str, File file, final ObservableEmitter observableEmitter) throws Exception {
        AsyncTaskUtils.a(new NetworkUtils.DownloadTask(CommonApplication.getAppContext(), str, file, new NetworkUtils.DownloadTask.DownloadListener() {
            public void a() {
                observableEmitter.onNext(true);
            }

            public void b() {
                observableEmitter.onError((Throwable) null);
            }
        }), new Object[0]);
    }

    public void a(Handler handler, JSONObject jSONObject) {
        String[] list;
        String e2 = e(a(jSONObject));
        File file = new File(e2);
        if (!file.exists() || (list = file.list()) == null || list.length == 0) {
            handler.post(new Runnable(jSONObject) {
                private final /* synthetic */ JSONObject f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    CardAbstractManager.this.c(this.f$1);
                }
            });
        } else {
            this.f = e2;
        }
    }

    /* access modifiers changed from: protected */
    public String a(JSONObject jSONObject) {
        String str;
        ServerBean F = CoreApi.a().F();
        if (ServerCompact.c(F) || ServerCompact.l(F)) {
            str = jSONObject.optString("cn");
        } else if (ServerCompact.k(F)) {
            str = jSONObject.optString("sg");
        } else if (ServerCompact.i(F)) {
            str = jSONObject.optString("us");
        } else if (ServerCompact.d(F)) {
            str = jSONObject.optString("de");
        } else if (ServerCompact.j(F)) {
            str = jSONObject.optString(Region.RU);
        } else {
            str = ServerCompact.g(F) ? jSONObject.optString("in") : null;
        }
        if (TextUtils.isEmpty(str)) {
            str = jSONObject.optString("cn");
        }
        return str == null ? "" : str;
    }

    public String a(String str) {
        return CardItemUtils.a(str);
    }

    public void e() {
        LogUtilGrey.a("mijia-card", "dataReadyRefresh");
        LocalBroadcastManager.getInstance(CommonApplication.getAppContext()).sendBroadcast(new Intent("com.smarthome.refresh_list_view").putExtra(SmartHomeDeviceManager.c, true));
    }
}
