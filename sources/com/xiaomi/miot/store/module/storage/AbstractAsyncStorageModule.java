package com.xiaomi.miot.store.module.storage;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.modules.common.ModuleDataCleaner;
import com.xiaomi.youpin.common.cache.ICache;
import java.util.Iterator;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AbstractAsyncStorageModule extends ReactContextBaseJavaModule implements ModuleDataCleaner.Cleanable {
    volatile ICache<String> mCache;
    private boolean mShuttingDown = false;

    /* access modifiers changed from: package-private */
    public abstract ICache<String> createCache();

    public AbstractAsyncStorageModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public void initialize() {
        super.initialize();
        this.mShuttingDown = false;
    }

    public void onCatalystInstanceDestroy() {
        this.mShuttingDown = true;
    }

    public void clearSensitiveData() {
        if (this.mCache != null) {
            this.mCache.b();
        }
    }

    @ReactMethod
    public void multiGet(final ReadableArray readableArray, final Callback callback) {
        if (readableArray == null) {
            callback.invoke(getInvalidKeyError((String) null), null);
            return;
        }
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) {
            /* access modifiers changed from: protected */
            public void doInBackgroundGuarded(Void... voidArr) {
                if (!AbstractAsyncStorageModule.this.ensureDatabase()) {
                    callback.invoke(AbstractAsyncStorageModule.getDBError((String) null), null);
                    return;
                }
                WritableArray createArray = Arguments.createArray();
                int i = 0;
                while (i < readableArray.size()) {
                    try {
                        String string = readableArray.getString(i);
                        String d = AbstractAsyncStorageModule.this.mCache.d(string);
                        WritableArray createArray2 = Arguments.createArray();
                        createArray2.pushString(string);
                        if (d == null) {
                            createArray2.pushNull();
                        } else {
                            createArray2.pushString(d);
                        }
                        createArray.pushArray(createArray2);
                        i++;
                    } catch (Exception unused) {
                    }
                }
                callback.invoke(null, createArray);
            }
        }.execute(new Void[0]);
    }

    @ReactMethod
    public void multiSet(final ReadableArray readableArray, final Callback callback) {
        if (readableArray.size() == 0) {
            callback.invoke(getInvalidKeyError((String) null));
            return;
        }
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) {
            /* access modifiers changed from: protected */
            public void doInBackgroundGuarded(Void... voidArr) {
                WritableMap writableMap = null;
                if (!AbstractAsyncStorageModule.this.ensureDatabase()) {
                    callback.invoke(AbstractAsyncStorageModule.getDBError((String) null));
                    return;
                }
                int i = 0;
                while (i < readableArray.size()) {
                    try {
                        if (readableArray.getArray(i).size() != 2) {
                            AbstractAsyncStorageModule.getInvalidValueError((String) null);
                            return;
                        } else if (readableArray.getArray(i).getString(0) == null) {
                            AbstractAsyncStorageModule.getInvalidKeyError((String) null);
                            return;
                        } else if (readableArray.getArray(i).getString(1) == null) {
                            AbstractAsyncStorageModule.getInvalidValueError((String) null);
                            return;
                        } else {
                            AbstractAsyncStorageModule.this.mCache.a(readableArray.getArray(i).getString(0), readableArray.getArray(i).getString(1));
                            i++;
                        }
                    } catch (Exception e) {
                        FLog.w(ReactConstants.TAG, e.getMessage(), (Throwable) e);
                        writableMap = AbstractAsyncStorageModule.getError((String) null, e.getMessage());
                    }
                }
                AbstractAsyncStorageModule.this.mCache.d();
                if (writableMap != null) {
                    callback.invoke(writableMap);
                    return;
                }
                callback.invoke(new Object[0]);
            }
        }.execute(new Void[0]);
    }

    @ReactMethod
    public void multiRemove(final ReadableArray readableArray, final Callback callback) {
        if (readableArray.size() == 0) {
            callback.invoke(getInvalidKeyError((String) null));
            return;
        }
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) {
            /* access modifiers changed from: protected */
            public void doInBackgroundGuarded(Void... voidArr) {
                WritableMap writableMap = null;
                if (!AbstractAsyncStorageModule.this.ensureDatabase()) {
                    callback.invoke(AbstractAsyncStorageModule.getDBError((String) null));
                    return;
                }
                int i = 0;
                while (i < readableArray.size()) {
                    try {
                        AbstractAsyncStorageModule.this.mCache.c(readableArray.getString(i));
                        i++;
                    } catch (Exception e) {
                        FLog.w(ReactConstants.TAG, e.getMessage(), (Throwable) e);
                        writableMap = AbstractAsyncStorageModule.getError((String) null, e.getMessage());
                    }
                }
                AbstractAsyncStorageModule.this.mCache.d();
                if (writableMap != null) {
                    callback.invoke(writableMap);
                    return;
                }
                callback.invoke(new Object[0]);
            }
        }.execute(new Void[0]);
    }

    @ReactMethod
    public void multiMerge(final ReadableArray readableArray, final Callback callback) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) {
            /* access modifiers changed from: protected */
            public void doInBackgroundGuarded(Void... voidArr) {
                WritableMap writableMap = null;
                if (!AbstractAsyncStorageModule.this.ensureDatabase()) {
                    callback.invoke(AbstractAsyncStorageModule.getDBError((String) null));
                    return;
                }
                int i = 0;
                while (i < readableArray.size()) {
                    try {
                        if (readableArray.getArray(i).size() != 2) {
                            AbstractAsyncStorageModule.getInvalidValueError((String) null);
                            return;
                        } else if (readableArray.getArray(i).getString(0) == null) {
                            AbstractAsyncStorageModule.getInvalidKeyError((String) null);
                            return;
                        } else if (readableArray.getArray(i).getString(1) == null) {
                            AbstractAsyncStorageModule.getInvalidValueError((String) null);
                            return;
                        } else {
                            String string = readableArray.getArray(i).getString(0);
                            AbstractAsyncStorageModule.this.mCache.a(string, AbstractAsyncStorageModule.mergeImpl(AbstractAsyncStorageModule.this.mCache.d(string), readableArray.getArray(i).getString(1)));
                            AbstractAsyncStorageModule.this.mCache.d();
                            i++;
                        }
                    } catch (Exception e) {
                        FLog.w(ReactConstants.TAG, e.getMessage(), (Throwable) e);
                        writableMap = AbstractAsyncStorageModule.getError((String) null, e.getMessage());
                    }
                }
                if (writableMap != null) {
                    callback.invoke(writableMap);
                    return;
                }
                callback.invoke(new Object[0]);
            }
        }.execute(new Void[0]);
    }

    @ReactMethod
    public void clear(final Callback callback) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) {
            /* access modifiers changed from: protected */
            public void doInBackgroundGuarded(Void... voidArr) {
                if (AbstractAsyncStorageModule.this.ensureDatabase()) {
                    callback.invoke(AbstractAsyncStorageModule.getDBError((String) null));
                    return;
                }
                try {
                    AbstractAsyncStorageModule.this.mCache.b();
                    callback.invoke(new Object[0]);
                } catch (Exception e) {
                    FLog.w(ReactConstants.TAG, e.getMessage(), (Throwable) e);
                    callback.invoke(AbstractAsyncStorageModule.getError((String) null, e.getMessage()));
                }
            }
        }.execute(new Void[0]);
    }

    @ReactMethod
    public void getAllKeys(final Callback callback) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) {
            /* access modifiers changed from: protected */
            public void doInBackgroundGuarded(Void... voidArr) {
                if (!AbstractAsyncStorageModule.this.ensureDatabase()) {
                    callback.invoke(AbstractAsyncStorageModule.getDBError((String) null), null);
                    return;
                }
                WritableArray createArray = Arguments.createArray();
                try {
                    for (String pushString : AbstractAsyncStorageModule.this.mCache.a()) {
                        createArray.pushString(pushString);
                    }
                    callback.invoke(null, createArray);
                } catch (Exception e) {
                    FLog.w(ReactConstants.TAG, e.getMessage(), (Throwable) e);
                    callback.invoke(AbstractAsyncStorageModule.getError((String) null, e.getMessage()), null);
                }
            }
        }.execute(new Void[0]);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001d, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001f, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean ensureDatabase() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.mShuttingDown     // Catch:{ all -> 0x0020 }
            r1 = 0
            if (r0 != 0) goto L_0x001e
            com.facebook.react.bridge.ReactApplicationContext r0 = r2.getReactApplicationContext()     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x000d
            goto L_0x001e
        L_0x000d:
            com.xiaomi.youpin.common.cache.ICache<java.lang.String> r0 = r2.mCache     // Catch:{ all -> 0x0020 }
            if (r0 != 0) goto L_0x0017
            com.xiaomi.youpin.common.cache.ICache r0 = r2.createCache()     // Catch:{ all -> 0x0020 }
            r2.mCache = r0     // Catch:{ all -> 0x0020 }
        L_0x0017:
            com.xiaomi.youpin.common.cache.ICache<java.lang.String> r0 = r2.mCache     // Catch:{ all -> 0x0020 }
            if (r0 == 0) goto L_0x001c
            r1 = 1
        L_0x001c:
            monitor-exit(r2)
            return r1
        L_0x001e:
            monitor-exit(r2)
            return r1
        L_0x0020:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.miot.store.module.storage.AbstractAsyncStorageModule.ensureDatabase():boolean");
    }

    static String mergeImpl(String str, String str2) throws JSONException {
        if (str == null) {
            return str2;
        }
        JSONObject jSONObject = new JSONObject(str);
        deepMergeInto(jSONObject, new JSONObject(str2));
        return jSONObject.toString();
    }

    private static void deepMergeInto(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        Iterator<String> keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            JSONObject optJSONObject = jSONObject2.optJSONObject(next);
            JSONObject optJSONObject2 = jSONObject.optJSONObject(next);
            if (optJSONObject == null || optJSONObject2 == null) {
                jSONObject.put(next, jSONObject2.get(next));
            } else {
                deepMergeInto(optJSONObject2, optJSONObject);
                jSONObject.put(next, optJSONObject2);
            }
        }
    }

    static WritableMap getError(@Nullable String str, String str2) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("message", str2);
        if (str != null) {
            createMap.putString("key", str);
        }
        return createMap;
    }

    static WritableMap getInvalidKeyError(@Nullable String str) {
        return getError(str, "Invalid key");
    }

    static WritableMap getInvalidValueError(@Nullable String str) {
        return getError(str, "Invalid Value");
    }

    static WritableMap getDBError(@Nullable String str) {
        return getError(str, "Database Error");
    }
}
