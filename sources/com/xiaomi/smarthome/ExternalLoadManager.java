package com.xiaomi.smarthome;

import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.tencent.tinker.loader.SystemClassLoaderAdder;
import com.tencent.tinker.loader.TinkerDexOptimizer;
import com.tencent.tinker.loader.TinkerTestDexLoad;
import com.xiaomi.smarthome.AppConfigHelper;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.server.internal.plugin.util.Callback;
import com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils;
import com.xiaomi.smarthome.library.crypto.MD5Util;
import com.xiaomi.smarthome.library.http.Error;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.sync.SyncHandler;
import com.xiaomi.smarthome.library.http.util.CommonUtil;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.Response;
import org.json.JSONObject;

public enum ExternalLoadManager {
    instance;
    
    public static final int DOWNED = 2;
    public static final int DOWNING = 1;
    private static final String EXTENSION = ".apk";
    public static final String EXTERNAL_DIR = null;
    public static final int INIT = 0;
    public static final int LOADED = 3;
    public static final int LOAD_ERROR = 4;
    public static final File OPTIMIZE_DIR = null;
    private static final String TAG = "ExternalLoadManager";
    private ArrayList<Callback<Integer, Integer>> downCallback;
    /* access modifiers changed from: private */
    public int mStatus;

    static {
        EXTERNAL_DIR = FileUtils.a(CommonApplication.getAppContext()) + File.separator + "external" + File.separator;
        OPTIMIZE_DIR = CommonApplication.getAppContext().getDir("app_dex", 0);
    }

    public void downExternal(Callback<Integer, Integer> callback) {
        Log.i(TAG, "downExternal start mStatus:" + this.mStatus);
        if (this.mStatus == 0) {
            this.mStatus = 1;
            this.downCallback.add(callback);
            final AtomicBoolean atomicBoolean = new AtomicBoolean();
            new AppConfigHelper(CommonApplication.getAppContext()).a("apk_external", "1", "en", "cardControl/apk_external.json", new AppConfigHelper.OnCacheHandler<JSONObject>() {
                public boolean a(JSONObject jSONObject) throws Exception {
                    boolean parseConfig = ExternalLoadManager.this.parseConfig(jSONObject);
                    atomicBoolean.set(parseConfig);
                    return parseConfig;
                }

                public boolean b(JSONObject jSONObject) throws Exception {
                    return a(jSONObject);
                }
            }, new AppConfigHelper.JsonAsyncHandler() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject, Response response) {
                    boolean z = atomicBoolean.get();
                    boolean parseConfig = ExternalLoadManager.this.parseConfig(jSONObject);
                    if (z) {
                        Log.i(ExternalLoadManager.TAG, "downExternal load cache mStatus:" + ExternalLoadManager.this.mStatus);
                    } else if (parseConfig) {
                        Log.i(ExternalLoadManager.TAG, "downExternal load http mStatus:" + ExternalLoadManager.this.mStatus);
                    } else {
                        Log.i(ExternalLoadManager.TAG, "downExternal nodata");
                        FileUtils.e(ExternalLoadManager.EXTERNAL_DIR);
                        ExternalLoadManager.this.dispatchAllDown(1);
                    }
                }

                public void onFailure(Error error, Exception exc, Response response) {
                    Log.i(ExternalLoadManager.TAG, "downExternal onFailure");
                    if (!atomicBoolean.get()) {
                        ExternalLoadManager.this.dispatchAllDown(0);
                    }
                }
            });
        } else if (this.mStatus == 1) {
            this.downCallback.add(callback);
        } else {
            dispatchCallback(callback, 1);
        }
    }

    private void dispatchCallback(Callback<Integer, Integer> callback, int i) {
        if (callback != null) {
            try {
                callback.call(Integer.valueOf(i));
            } catch (Exception e) {
                Log.e(TAG, Log.getStackTraceString(e));
            }
        }
    }

    public boolean parseConfig(JSONObject jSONObject) {
        JSONObject optJSONObject;
        if (!(jSONObject == null || (optJSONObject = jSONObject.optJSONObject("result")) == null)) {
            String optString = optJSONObject.optString("content");
            if (AppConfigHelper.a(optString)) {
                try {
                    Log.i(TAG, "parseConfig content:" + optString);
                    JSONObject jSONObject2 = new JSONObject(optString);
                    if (jSONObject2.length() > 0) {
                        Iterator<String> keys = jSONObject2.keys();
                        File[] listFiles = new File(EXTERNAL_DIR).listFiles();
                        if (listFiles != null) {
                            for (File file : listFiles) {
                                if (!jSONObject2.has(file.getName().replace(".apk", ""))) {
                                    file.delete();
                                }
                            }
                        }
                        final HashMap hashMap = new HashMap();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            if (!TextUtils.isEmpty(next)) {
                                File file2 = new File(EXTERNAL_DIR + File.separator + next + ".apk");
                                if (!file2.exists() || !next.equalsIgnoreCase(MD5Util.a(file2))) {
                                    hashMap.put(file2, new Pair(jSONObject2.optString(next), next));
                                }
                            }
                        }
                        if (hashMap.size() > 0) {
                            CommonApplication.getGlobalWorkerHandler().post(new Runnable() {
                                public void run() {
                                    try {
                                        ArrayList arrayList = new ArrayList();
                                        for (Map.Entry entry : hashMap.entrySet()) {
                                            final File file = (File) entry.getKey();
                                            Pair pair = (Pair) entry.getValue();
                                            Log.i(ExternalLoadManager.TAG, "down file " + pair);
                                            file.getParentFile().mkdirs();
                                            HttpApi.a(new Request.Builder().a("GET").b((String) pair.first).a(), new SyncHandler<File>() {
                                                /* renamed from: a */
                                                public File b(Response response) throws Exception {
                                                    InputStream byteStream = response.body().byteStream();
                                                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                                                    if (byteStream != null) {
                                                        try {
                                                            byte[] bArr = new byte[102400];
                                                            while (true) {
                                                                int read = byteStream.read(bArr);
                                                                if (read == -1) {
                                                                    break;
                                                                }
                                                                fileOutputStream.write(bArr, 0, read);
                                                            }
                                                        } finally {
                                                            fileOutputStream.flush();
                                                            CommonUtil.a((OutputStream) fileOutputStream);
                                                        }
                                                    }
                                                    return file;
                                                }
                                            });
                                            if (((String) pair.second).equalsIgnoreCase(MD5Util.a(file))) {
                                                arrayList.add(file);
                                            } else {
                                                file.delete();
                                            }
                                        }
                                        TinkerDexOptimizer.a(CommonApplication.getAppContext(), arrayList, ExternalLoadManager.OPTIMIZE_DIR, (TinkerDexOptimizer.ResultCallback) null);
                                        ExternalLoadManager.this.dispatchAllDown(1);
                                    } catch (Exception e) {
                                        Log.e(ExternalLoadManager.TAG, Log.getStackTraceString(e));
                                        ExternalLoadManager.this.dispatchAllDown(0);
                                    }
                                }
                            });
                        } else {
                            dispatchAllDown(1);
                        }
                        return true;
                    }
                } catch (Exception e) {
                    Log.e(TAG, Log.getStackTraceString(e));
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void dispatchAllDown(int i) {
        this.mStatus = i == 1 ? 2 : 0;
        for (int size = this.downCallback.size() - 1; size >= 0; size--) {
            dispatchCallback(this.downCallback.get(size), i);
        }
        this.downCallback.clear();
    }

    public void loadExternal(Callback<Integer, Integer> callback) {
        if (this.mStatus == 3 || this.mStatus == 4) {
            Log.i(TAG, "loadExternal LOADED " + this.mStatus);
            dispatchCallback(callback, this.mStatus);
        } else if (this.mStatus == 2) {
            Log.i(TAG, "loadExternal DOWNED " + this.mStatus);
            install();
            dispatchCallback(callback, this.mStatus);
        } else {
            downExternal(new Callback(callback) {
                private final /* synthetic */ Callback f$1;

                {
                    this.f$1 = r2;
                }

                public final Object call(Object obj) {
                    return ExternalLoadManager.lambda$loadExternal$0(ExternalLoadManager.this, this.f$1, (Integer) obj);
                }
            });
        }
    }

    public void install() {
        File[] listFiles = new File(EXTERNAL_DIR).listFiles();
        if (listFiles != null) {
            List asList = Arrays.asList(listFiles);
            try {
                TinkerTestDexLoad.isPatch = true;
                SystemClassLoaderAdder.a(CommonApplication.getApplication(), (BaseDexClassLoader) getClass().getClassLoader(), OPTIMIZE_DIR, asList, true);
                this.mStatus = getClass().getClassLoader().loadClass("com.amap.api.maps2d.MapView") == null ? 4 : 3;
            } catch (Throwable th) {
                this.mStatus = 4;
                Log.i(TAG, Log.getStackTraceString(th));
            }
        }
    }
}
