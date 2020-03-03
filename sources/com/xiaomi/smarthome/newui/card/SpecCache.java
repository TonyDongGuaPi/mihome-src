package com.xiaomi.smarthome.newui.card;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager;
import com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils;
import com.xiaomi.smarthome.core.server.internal.plugin.util.ParcelUtils;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.api.spec.instance.SpecDevice;
import com.xiaomi.smarthome.device.api.spec.instance.SpecService;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.library.common.util.IOUtils;
import com.xiaomi.smarthome.library.crypto.MD5Util;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.sync.SyncHandler;
import com.xiaomi.smarthome.newui.card.spec.SpecUtils;
import com.xiaomi.smarthome.newui.card.spec.instance.SpecDeviceCodec;
import com.xiaomi.smarthome.newui.utils.NumberUtils;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SpecCache {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f20526a = ("preview".equals(GlobalDynamicSettingManager.a().f()) ? "http://pv.miot-spec.srv/miot-spec-v2/instance" : "http://miot-spec.org/miot-spec-v2/instance");
    /* access modifiers changed from: private */
    public static final String b = ("preview".equals(GlobalDynamicSettingManager.a().f()) ? "http://pv.miot-spec.srv/instance/v2/multiLanguage" : "http://miot-spec.org/instance/v2/multiLanguage");
    private static final int c = 50;
    private static final String e = "spec_support_cache";
    private static final String g = "spec_urns.parcel";
    private HashMap<String, SpecDevice> d = new HashMap<>();
    private Map<String, Map<String, Map<String, String>>> f = new HashMap();
    private ExecutorService h = new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue(), $$Lambda$SpecCache$xzM8rgb5BQYxS3dM6_dCGJInhg.INSTANCE);

    /* access modifiers changed from: private */
    public static /* synthetic */ Thread b(Runnable runnable) {
        Thread thread = new Thread(runnable, "SpecCache_Executor");
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        if (thread.getPriority() != 10) {
            thread.setPriority(10);
        }
        return thread;
    }

    public SpecCache() {
        File a2 = a(f20526a);
        File a3 = a(b);
        boolean mkdirs = a2.mkdirs();
        boolean mkdirs2 = a3.mkdirs();
        this.h.execute(new Runnable() {
            public final void run() {
                SpecCache.this.c();
            }
        });
        LogUtilGrey.a("mijia-card", "getSpecFilePath create language:" + mkdirs2 + " instance:" + mkdirs);
    }

    public void a(List<Device> list, Runnable runnable) {
        this.h.execute(new Runnable(list, runnable) {
            private final /* synthetic */ List f$1;
            private final /* synthetic */ Runnable f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                SpecCache.this.b(this.f$1, this.f$2);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(List list, Runnable runnable) {
        if (list != null) {
            HashSet hashSet = new HashSet();
            HashSet hashSet2 = new HashSet();
            HashSet hashSet3 = new HashSet();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String str = ((Device) it.next()).specUrn;
                if (!TextUtils.isEmpty(str)) {
                    hashSet.add(str);
                    if (this.d.get(str) == null) {
                        hashSet2.add(str);
                    }
                    if (this.f.get(str) == null) {
                        hashSet3.add(str);
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            Iterator it2 = hashSet2.iterator();
            int i = 0;
            while (it2.hasNext()) {
                arrayList.add((String) it2.next());
                if (arrayList.size() == 50) {
                    i += Math.max(0, a(f20526a, (Collection<String>) arrayList));
                    arrayList = new ArrayList();
                }
            }
            if (arrayList.size() > 0) {
                i += Math.max(0, a(f20526a, (Collection<String>) arrayList));
            }
            ArrayList arrayList2 = new ArrayList();
            Iterator it3 = hashSet3.iterator();
            int i2 = 0;
            while (it3.hasNext()) {
                arrayList2.add((String) it3.next());
                if (arrayList2.size() == 50) {
                    i2 += Math.max(0, a(b, (Collection<String>) arrayList2));
                    arrayList2 = new ArrayList();
                }
            }
            if (arrayList2.size() > 0) {
                i2 += Math.max(0, a(b, (Collection<String>) arrayList2));
            }
            Log.i("mijia-card", "onDeviceChange load instance size:" + i + " language size:" + i2);
            if (runnable != null) {
                runnable.run();
            }
            Parcel obtain = Parcel.obtain();
            obtain.writeInt(hashSet.size());
            Iterator it4 = hashSet.iterator();
            while (it4.hasNext()) {
                obtain.writeString((String) it4.next());
            }
            FileUtils.a(a(g), obtain.marshall());
            obtain.recycle();
        } else if (runnable != null) {
            runnable.run();
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        Parcel b2;
        HashSet hashSet = new HashSet();
        File a2 = a(g);
        if (!a2.exists() || (b2 = FileUtils.b(a2)) == null) {
            LogUtilGrey.a("mijia-card", "loadSpecInstanceLanguage no cache urns");
            return;
        }
        int readInt = b2.readInt();
        for (int i = 0; i < readInt; i++) {
            String readString = b2.readString();
            if (!TextUtils.isEmpty(readString)) {
                hashSet.add(readString);
            }
        }
        a((HashSet<String>) hashSet);
        b((HashSet<String>) hashSet);
    }

    private void a(HashSet<String> hashSet) {
        Parcel b2;
        long currentTimeMillis = System.currentTimeMillis();
        Iterator<String> it = hashSet.iterator();
        while (it.hasNext()) {
            File a2 = a(f20526a, it.next());
            if (a2.exists() && (b2 = FileUtils.b(a2)) != null) {
                try {
                    b2.readLong();
                    SpecDevice specDevice = (SpecDevice) b2.readParcelable(SpecDevice.class.getClassLoader());
                    this.d.put(specDevice.getType(), specDevice);
                } catch (Exception e2) {
                    boolean delete = a2.delete();
                    LogUtilGrey.a("mijia-card", Log.getStackTraceString(e2) + " delete:" + delete);
                }
            }
        }
        LogUtil.c("mijia-card", "readInstance cache spend time:" + (System.currentTimeMillis() - currentTimeMillis) + "  data size:" + this.d.size());
    }

    private void b(HashSet<String> hashSet) {
        Parcel b2;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            Iterator<String> it = hashSet.iterator();
            while (it.hasNext()) {
                File a2 = a(b, it.next());
                if (a2.exists() && (b2 = FileUtils.b(a2)) != null) {
                    try {
                        b2.readLong();
                        String readString = b2.readString();
                        Map a3 = ParcelUtils.a(b2, SpecDevice.class.getClassLoader(), $$Lambda$SpecCache$1alRQPhqaUOh_6BEUTT3HYqHEqE.INSTANCE);
                        if (a3 != null) {
                            if (a3.size() == 0) {
                                this.f.put(readString, Collections.emptyMap());
                            } else {
                                this.f.put(readString, a3);
                            }
                        }
                    } catch (Exception e2) {
                        boolean delete = a2.delete();
                        LogUtilGrey.a("mijia-card", Log.getStackTraceString(e2) + " delete:" + delete);
                    }
                }
            }
        } catch (Exception e3) {
            LogUtilGrey.a("mijia-card", Log.getStackTraceString(e3));
        }
        LogUtil.c("mijia-card", "readLanguage cache spend time:" + (System.currentTimeMillis() - currentTimeMillis) + "  data size:" + this.f.size());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Map a(Void voidR) throws Exception {
        return new ArrayMap();
    }

    /* access modifiers changed from: private */
    public void a(String str, SpecDevice specDevice, long j) {
        try {
            Map<Integer, SpecService> services = specDevice.getServices();
            if (services != null) {
                if (services.size() != 0) {
                    this.d.put(specDevice.getType(), specDevice);
                    Parcel obtain = Parcel.obtain();
                    obtain.writeLong(j);
                    obtain.writeParcelable(specDevice, 0);
                    FileUtils.a(a(f20526a, specDevice.getType()), obtain.marshall());
                    obtain.recycle();
                    IOUtils.a((OutputStream) null);
                }
            }
            LogUtilGrey.a("mijia-card", "writeInstance specDevice is null str:" + str);
        } catch (Throwable th) {
            IOUtils.a((OutputStream) null);
            throw th;
        }
        IOUtils.a((OutputStream) null);
    }

    /* access modifiers changed from: private */
    public void a(String str, Map<String, Map<String, String>> map, long j) {
        if (map == null) {
            try {
                LogUtilGrey.a("mijia-card", "writeLanguage stringMap is null str:" + str);
            } catch (Throwable th) {
                LogUtilGrey.a("mijia-card", Log.getStackTraceString(th));
            }
        } else {
            this.f.put(str, map);
            Parcel obtain = Parcel.obtain();
            obtain.writeLong(j);
            obtain.writeString(str);
            obtain.writeMap(map);
            FileUtils.a(a(b, str), obtain.marshall());
            obtain.recycle();
        }
        IOUtils.a((OutputStream) null);
    }

    private File a(String str, String str2) {
        return new File(a(str), MD5Util.a(str2));
    }

    @NonNull
    private File a(String str) {
        return new File(CommonApplication.getAppContext().getFilesDir() + File.separator + e + File.separator + MD5Util.a(str));
    }

    /* access modifiers changed from: private */
    public Map<String, Map<String, String>> a(JSONObject jSONObject) {
        String str;
        String str2;
        if (jSONObject == null) {
            return Collections.emptyMap();
        }
        ArrayMap arrayMap = new ArrayMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            JSONObject optJSONObject = jSONObject.optJSONObject(next);
            if (optJSONObject != null) {
                Iterator<String> keys2 = optJSONObject.keys();
                while (keys2.hasNext()) {
                    String next2 = keys2.next();
                    String[] split = next2.split(":");
                    if (split.length > 1) {
                        int a2 = NumberUtils.a((Object) split[1], 0);
                        if (split.length > 3) {
                            int a3 = NumberUtils.a((Object) split[3], 0);
                            if (split.length > 5) {
                                try {
                                    str2 = String.valueOf(Long.valueOf(split[5]));
                                } catch (Exception unused) {
                                    str2 = split[5];
                                }
                                str = SpecUtils.a(split[2]) + a2 + "." + a3 + "." + str2;
                            } else {
                                str = SpecUtils.a(split[2]) + a2 + "." + a3;
                            }
                        } else {
                            str = String.valueOf(a2);
                        }
                        Map map = (Map) arrayMap.get(str);
                        if (map == null) {
                            map = new ArrayMap();
                            arrayMap.put(str, map);
                        }
                        map.put(next, optJSONObject.optString(next2));
                    }
                }
            }
        }
        return arrayMap;
    }

    public Map<String, Map<String, String>> a(Device device) {
        String d2 = DeviceUtils.d(device);
        Map<String, Map<String, String>> map = this.f.get(d2);
        if (!TextUtils.isEmpty(d2) && map == null) {
            this.h.execute(new Runnable(d2, device) {
                private final /* synthetic */ String f$1;
                private final /* synthetic */ Device f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    SpecCache.this.b(this.f$1, this.f$2);
                }
            });
        }
        return map;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(String str, Device device) {
        HashSet hashSet = new HashSet();
        hashSet.add(str);
        if (a(b, (Collection<String>) hashSet) >= 0) {
            MiotSpecCardManager.f().a(device.did, (String) null, (Object) null);
        }
    }

    public SpecDevice b(Device device) {
        String d2 = DeviceUtils.d(device);
        SpecDevice specDevice = this.d.get(d2);
        if (!TextUtils.isEmpty(d2) && specDevice == null) {
            this.h.execute(new Runnable(d2, device) {
                private final /* synthetic */ String f$1;
                private final /* synthetic */ Device f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    SpecCache.this.a(this.f$1, this.f$2);
                }
            });
        }
        return specDevice;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(String str, Device device) {
        HashSet hashSet = new HashSet();
        hashSet.add(str);
        if (a(f20526a, (Collection<String>) hashSet) >= 0) {
            MiotSpecCardManager.f().a(device.did, (String) null, (Object) null);
        }
    }

    private int a(final String str, final Collection<String> collection) {
        try {
            final JSONArray jSONArray = new JSONArray();
            if (f20526a.equals(str)) {
                for (String next : collection) {
                    if (this.d.get(next) == null) {
                        jSONArray.put(next);
                    }
                }
            } else if (b.equals(str)) {
                for (String next2 : collection) {
                    if (this.f.get(next2) == null) {
                        jSONArray.put(next2 + "," + 0);
                    }
                }
            }
            Request a2 = new Request.Builder().a("POST").b(str).a((RequestBody) new RequestBody() {
                public MediaType contentType() {
                    return MediaType.parse("application/json");
                }

                public void writeTo(BufferedSink bufferedSink) throws IOException {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("urns", jSONArray);
                        bufferedSink.writeString(jSONObject.toString(), Charset.forName("UTF-8"));
                    } catch (JSONException e) {
                        LogUtilGrey.a("mijia-card", Log.getStackTraceString(e));
                    }
                }
            }).a();
            if (jSONArray.length() != 0) {
                return ((Integer) HttpApi.a(a2, new SyncHandler<Integer>() {
                    /* renamed from: a */
                    public Integer b(Response response) throws Exception {
                        ResponseBody body = response.body();
                        if (body == null || !response.isSuccessful()) {
                            LogUtil.b("mijia-card", "SpecCache.loadSpecItemConfig processResponse code:" + response.code() + " noData url:" + str + "?urn=" + collection);
                            return -1;
                        }
                        int i = 0;
                        JSONObject jSONObject = new JSONObject(body.string());
                        if (SpecCache.f20526a.equals(str)) {
                            for (String str : collection) {
                                JSONObject optJSONObject = jSONObject.optJSONObject(str);
                                if (optJSONObject == null || optJSONObject.length() <= 0) {
                                    LogUtilGrey.a("SpecCache", "urn:" + str + " no instance data");
                                } else {
                                    SpecCache.this.a(str, SpecDeviceCodec.a(optJSONObject), optJSONObject.optLong("timestamp"));
                                    i++;
                                }
                            }
                        } else if (SpecCache.b.equals(str)) {
                            for (String str2 : collection) {
                                JSONObject optJSONObject2 = jSONObject.optJSONObject(str2);
                                if (optJSONObject2 == null || optJSONObject2.length() <= 0) {
                                    SpecCache.this.a(str2, (Map<String, Map<String, String>>) Collections.emptyMap(), -1);
                                } else {
                                    SpecCache.this.a(str2, (Map<String, Map<String, String>>) SpecCache.this.a(optJSONObject2.optJSONObject("data")), optJSONObject2.optLong("timestamp"));
                                    i++;
                                }
                            }
                        }
                        LogUtil.c("mijia-card", "SpecCache.loadSpecItemConfig url:" + str + "?urn=" + collection);
                        return Integer.valueOf(i);
                    }
                })).intValue();
            }
            LogUtil.c("mijia-card", "SpecCache.loadSpecItemConfig cached url:" + str + "?urn=" + collection);
            return 0;
        } catch (Exception e2) {
            LogUtil.b("mijia-card", "SpecCache.loadSpecItemConfig :" + Log.getStackTraceString(e2));
            return -1;
        }
    }

    public void a(Runnable runnable) {
        this.h.execute(runnable);
    }
}
