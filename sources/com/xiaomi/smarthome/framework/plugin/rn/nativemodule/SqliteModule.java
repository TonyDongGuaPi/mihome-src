package com.xiaomi.smarthome.framework.plugin.rn.nativemodule;

import android.content.Context;
import android.content.ContextWrapper;
import com.facebook.common.logging.FLog;
import com.facebook.common.logging.FLogDefaultLoggingDelegate;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NoSuchKeyException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.xiaomi.miot.support.monitor.core.tasks.TaskConfig;
import com.xiaomi.smarthome.framework.plugin.rn.RNRuntime;
import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.pgsqlite.CallbackContext;
import org.pgsqlite.SQLitePlugin;

public class SqliteModule extends SQLitePlugin {
    public SqliteModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        FLogDefaultLoggingDelegate.getInstance().setMinimumLoggingLevel(0);
        FLog.setLoggingDelegate(FLogDefaultLoggingDelegate.getInstance());
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return new ContextWrapper(super.getContext()) {
            public File getDatabasePath(String str) {
                return new File(SqliteModule.this.getDatabasePath(), str);
            }
        };
    }

    private static <T> T simpleProxy(Class<T> cls, InvocationHandler invocationHandler) {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, invocationHandler);
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x008e A[SYNTHETIC, Splitter:B:35:0x008e] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0099 A[SYNTHETIC, Splitter:B:40:0x0099] */
    /* JADX WARNING: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
    @com.facebook.react.bridge.ReactMethod
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void open(final com.facebook.react.bridge.ReadableMap r10, final com.facebook.react.bridge.Callback r11, final com.facebook.react.bridge.Callback r12) {
        /*
            r9 = this;
            java.lang.String r0 = "assetFilename"
            boolean r0 = r10.hasKey(r0)
            if (r0 == 0) goto L_0x00be
            java.io.File r0 = new java.io.File
            java.io.File r1 = r9.getDatabasePath()
            java.lang.String r2 = "name"
            java.lang.String r2 = com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTUtils.a((com.facebook.react.bridge.ReadableMap) r10, (java.lang.String) r2)
            r0.<init>(r1, r2)
            java.lang.String r1 = r0.getAbsolutePath()
            com.xiaomi.smarthome.framework.plugin.FileUtils.g(r1)
            java.lang.String r1 = "assetFilename"
            java.lang.String r3 = com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTUtils.a((com.facebook.react.bridge.ReadableMap) r10, (java.lang.String) r1)
            boolean r1 = r0.exists()
            if (r1 != 0) goto L_0x00be
            java.lang.String r1 = "file://"
            boolean r1 = r3.startsWith(r1)
            if (r1 == 0) goto L_0x00a2
            boolean r1 = com.xiaomi.smarthome.globalsetting.GlobalSetting.j
            r2 = 7
            if (r1 == 0) goto L_0x0040
            java.lang.String r1 = "getDatabasePath"
            java.lang.String r4 = r3.substring(r2)
            android.util.Log.i(r1, r4)
        L_0x0040:
            r1 = 0
            r4 = 0
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x007f }
            java.lang.String r2 = r3.substring(r2)     // Catch:{ Exception -> 0x007f }
            r5.<init>(r2)     // Catch:{ Exception -> 0x007f }
            boolean r2 = r5.exists()     // Catch:{ Exception -> 0x007f }
            if (r2 == 0) goto L_0x0072
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x007f }
            r2.<init>(r5)     // Catch:{ Exception -> 0x007f }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x006f, all -> 0x006c }
            r1.<init>(r0)     // Catch:{ Exception -> 0x006f, all -> 0x006c }
            r0 = 16384(0x4000, float:2.2959E-41)
            byte[] r0 = new byte[r0]     // Catch:{ Exception -> 0x006f, all -> 0x006c }
        L_0x005f:
            int r3 = r2.read(r0)     // Catch:{ Exception -> 0x006f, all -> 0x006c }
            r5 = -1
            if (r3 == r5) goto L_0x006a
            r1.write(r0, r4, r3)     // Catch:{ Exception -> 0x006f, all -> 0x006c }
            goto L_0x005f
        L_0x006a:
            r1 = r2
            goto L_0x0072
        L_0x006c:
            r10 = move-exception
            r1 = r2
            goto L_0x0097
        L_0x006f:
            r10 = move-exception
            r1 = r2
            goto L_0x0080
        L_0x0072:
            if (r1 == 0) goto L_0x00be
            r1.close()     // Catch:{ IOException -> 0x0078 }
            goto L_0x00be
        L_0x0078:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00be
        L_0x007d:
            r10 = move-exception
            goto L_0x0097
        L_0x007f:
            r10 = move-exception
        L_0x0080:
            r11 = 1
            java.lang.Object[] r11 = new java.lang.Object[r11]     // Catch:{ all -> 0x007d }
            java.lang.String r10 = android.util.Log.getStackTraceString(r10)     // Catch:{ all -> 0x007d }
            r11[r4] = r10     // Catch:{ all -> 0x007d }
            r12.invoke(r11)     // Catch:{ all -> 0x007d }
            if (r1 == 0) goto L_0x0096
            r1.close()     // Catch:{ IOException -> 0x0092 }
            goto L_0x0096
        L_0x0092:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0096:
            return
        L_0x0097:
            if (r1 == 0) goto L_0x00a1
            r1.close()     // Catch:{ IOException -> 0x009d }
            goto L_0x00a1
        L_0x009d:
            r11 = move-exception
            r11.printStackTrace()
        L_0x00a1:
            throw r10
        L_0x00a2:
            java.lang.String r1 = "http://"
            boolean r1 = r3.startsWith(r1)
            if (r1 == 0) goto L_0x00be
            java.lang.String r2 = ""
            r4 = 0
            java.lang.String r5 = r0.getAbsolutePath()
            r6 = 15000(0x3a98, float:2.102E-41)
            r7 = 15000(0x3a98, float:2.102E-41)
            com.xiaomi.smarthome.framework.plugin.rn.nativemodule.SqliteModule$2 r8 = new com.xiaomi.smarthome.framework.plugin.rn.nativemodule.SqliteModule$2
            r8.<init>(r10, r11, r12)
            com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTPersistModule.download(r2, r3, r4, r5, r6, r7, r8)
            return
        L_0x00be:
            super.open(r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.nativemodule.SqliteModule.open(com.facebook.react.bridge.ReadableMap, com.facebook.react.bridge.Callback, com.facebook.react.bridge.Callback):void");
    }

    /* access modifiers changed from: private */
    public File getDatabasePath() {
        File file = new File(MIOTBaseJavaModule.getPluginDir(getReactApplicationContext(), RNRuntime.a().e()), TaskConfig.v);
        file.mkdirs();
        return file;
    }

    @ReactMethod
    public void close(ReadableMap readableMap, Callback callback, Callback callback2) {
        super.close(readableMap, callback, callback2);
    }

    @ReactMethod
    public void attach(ReadableMap readableMap, Callback callback, Callback callback2) {
        super.attach(readableMap, callback, callback2);
    }

    @ReactMethod
    public void delete(ReadableMap readableMap, Callback callback, Callback callback2) {
        super.delete(readableMap, callback, callback2);
    }

    @ReactMethod
    public void backgroundExecuteSqlBatch(ReadableMap readableMap, Callback callback, Callback callback2) {
        super.backgroundExecuteSqlBatch(readableMap, callback, callback2);
    }

    @ReactMethod
    public void executeSqlBatch(ReadableMap readableMap, Callback callback, Callback callback2) {
        super.executeSqlBatch(readableMap, callback, callback2);
    }

    @ReactMethod
    public void echoStringValue(ReadableMap readableMap, Callback callback, Callback callback2) {
        super.echoStringValue(readableMap, callback, callback2);
    }

    /* access modifiers changed from: protected */
    public boolean execute(String str, final ReadableMap readableMap, CallbackContext callbackContext) throws Exception {
        return "open".equalsIgnoreCase(str) ? super.execute(str, (ReadableMap) simpleProxy(ReadableMap.class, new InvocationHandler() {
            public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                if (objArr != null && objArr.length == 1 && "getString".equals(method.getName()) && "assetFilename".equals(objArr[0])) {
                    return null;
                }
                try {
                    return method.invoke(readableMap, objArr);
                } catch (Exception unused) {
                    throw new NoSuchKeyException((objArr == null || objArr[0] == null) ? "no args" : objArr[0].toString());
                }
            }
        }), callbackContext) : super.execute(str, readableMap, callbackContext);
    }
}
