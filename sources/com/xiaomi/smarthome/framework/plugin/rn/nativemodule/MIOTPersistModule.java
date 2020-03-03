package com.xiaomi.smarthome.framework.plugin.rn.nativemodule;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ScrollView;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.facebook.GraphRequest;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.taobao.weex.WXGlobalEventReceiver;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.core.entity.Error;
import com.xiaomi.smarthome.download.Downloads;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.plugin.FileUtils;
import com.xiaomi.smarthome.framework.plugin.rn.PluginRNActivity;
import com.xiaomi.smarthome.framework.plugin.rn.RNEventReceiver;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.viewshot.ViewShot;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnCallbackMapUtil;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginFileUtils;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.aspectj.runtime.internal.AroundClosure;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class MIOTPersistModule extends MIOTBaseJavaModule {

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            return MIOTPersistModule.openConnection_aroundBody0((URL) this.state[0]);
        }
    }

    public class AjcClosure3 extends AroundClosure {
        public AjcClosure3(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            return MIOTPersistModule.openConnection_aroundBody2((URL) this.state[0]);
        }
    }

    public class AjcClosure5 extends AroundClosure {
        public AjcClosure5(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            return MIOTPersistModule.openConnection_aroundBody4((URL) this.state[0]);
        }
    }

    public class AjcClosure7 extends AroundClosure {
        public AjcClosure7(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            return MIOTPersistModule.openConnection_aroundBody6((URL) this.state[0]);
        }
    }

    public String getName() {
        return "MIOTFile";
    }

    public MIOTPersistModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("storageBasePath", getFilesPath().getAbsolutePath());
        return hashMap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x004d, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004e, code lost:
        r11 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0051, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0052, code lost:
        r11 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x015d, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x015e, code lost:
        r5 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0161, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0162, code lost:
        r5 = r13;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x01f3 A[SYNTHETIC, Splitter:B:102:0x01f3] */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x01f8 A[Catch:{ Exception -> 0x0200 }] */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x01fd A[Catch:{ Exception -> 0x0200 }] */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x004d A[Catch:{ Throwable -> 0x0051, all -> 0x004d }, ExcHandler: all (th java.lang.Throwable), Splitter:B:4:0x001d] */
    /* JADX WARNING: Removed duplicated region for block: B:122:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x015d A[ExcHandler: all (th java.lang.Throwable), Splitter:B:57:0x0128] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01e4 A[SYNTHETIC, Splitter:B:94:0x01e4] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01e9 A[Catch:{ Exception -> 0x01ef }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void download(java.lang.String r20, java.lang.String r21, java.util.HashMap<java.lang.String, java.lang.Object> r22, java.lang.String r23, int r24, int r25, com.facebook.react.bridge.Callback r26) {
        /*
            r0 = r23
            r1 = r26
            r2 = 3
            r3 = 2
            r4 = 4
            r5 = 0
            r6 = 1
            r7 = 0
            java.net.URL r8 = new java.net.URL     // Catch:{ Throwable -> 0x01b3, all -> 0x01af }
            r9 = r21
            r8.<init>(r9)     // Catch:{ Throwable -> 0x01b3, all -> 0x01af }
            com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor r9 = com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor.b()     // Catch:{ Throwable -> 0x01b3, all -> 0x01af }
            java.net.URLConnection r8 = r9.b(r8)     // Catch:{ Throwable -> 0x01b3, all -> 0x01af }
            java.net.HttpURLConnection r8 = (java.net.HttpURLConnection) r8     // Catch:{ Throwable -> 0x01b3, all -> 0x01af }
            if (r22 == 0) goto L_0x0055
            java.util.Set r9 = r22.entrySet()     // Catch:{ Throwable -> 0x0051, all -> 0x004d }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ Throwable -> 0x0051, all -> 0x004d }
        L_0x0025:
            boolean r10 = r9.hasNext()     // Catch:{ Throwable -> 0x0051, all -> 0x004d }
            if (r10 == 0) goto L_0x0055
            java.lang.Object r10 = r9.next()     // Catch:{ Throwable -> 0x0051, all -> 0x004d }
            java.util.Map$Entry r10 = (java.util.Map.Entry) r10     // Catch:{ Throwable -> 0x0051, all -> 0x004d }
            java.lang.Object r11 = r10.getValue()     // Catch:{ Throwable -> 0x0051, all -> 0x004d }
            if (r11 == 0) goto L_0x0025
            java.lang.Object r10 = r10.getKey()     // Catch:{ Throwable -> 0x0051, all -> 0x004d }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ Throwable -> 0x0051, all -> 0x004d }
            java.lang.String r10 = java.net.URLEncoder.encode(r10)     // Catch:{ Throwable -> 0x0051, all -> 0x004d }
            java.lang.String r11 = r11.toString()     // Catch:{ Throwable -> 0x0051, all -> 0x004d }
            java.lang.String r11 = java.net.URLEncoder.encode(r11)     // Catch:{ Throwable -> 0x0051, all -> 0x004d }
            r8.setRequestProperty(r10, r11)     // Catch:{ Throwable -> 0x0051, all -> 0x004d }
            goto L_0x0025
        L_0x004d:
            r0 = move-exception
            r11 = r5
            goto L_0x01f1
        L_0x0051:
            r0 = move-exception
            r11 = r5
            goto L_0x01b6
        L_0x0055:
            r9 = r24
            r8.setConnectTimeout(r9)     // Catch:{ Throwable -> 0x0051, all -> 0x004d }
            r9 = r25
            r8.setReadTimeout(r9)     // Catch:{ Throwable -> 0x0051, all -> 0x004d }
            r8.connect()     // Catch:{ Throwable -> 0x0051, all -> 0x004d }
            int r9 = r8.getResponseCode()     // Catch:{ Throwable -> 0x0051, all -> 0x004d }
            int r10 = r8.getContentLength()     // Catch:{ Throwable -> 0x0051, all -> 0x004d }
            r11 = 200(0xc8, float:2.8E-43)
            if (r9 == r11) goto L_0x0080
            r12 = 301(0x12d, float:4.22E-43)
            if (r9 == r12) goto L_0x007e
            r12 = 302(0x12e, float:4.23E-43)
            if (r9 == r12) goto L_0x007e
            r12 = 307(0x133, float:4.3E-43)
            if (r9 == r12) goto L_0x007e
            r12 = 308(0x134, float:4.32E-43)
            if (r9 != r12) goto L_0x0080
        L_0x007e:
            r12 = 1
            goto L_0x0081
        L_0x0080:
            r12 = 0
        L_0x0081:
            if (r12 == 0) goto L_0x00c0
            java.lang.String r9 = "Location"
            java.lang.String r9 = r8.getHeaderField(r9)     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            r8.disconnect()     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            java.net.URL r12 = new java.net.URL     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            r12.<init>(r9)     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor r9 = com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor.b()     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            java.net.URLConnection r9 = r9.b(r12)     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            java.net.HttpURLConnection r9 = (java.net.HttpURLConnection) r9     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            r8 = 5000(0x1388, float:7.006E-42)
            r9.setConnectTimeout(r8)     // Catch:{ Throwable -> 0x00b7, all -> 0x00b2 }
            r9.connect()     // Catch:{ Throwable -> 0x00b7, all -> 0x00b2 }
            int r8 = r9.getResponseCode()     // Catch:{ Throwable -> 0x00b7, all -> 0x00b2 }
            int r12 = r9.getContentLength()     // Catch:{ Throwable -> 0x00b7, all -> 0x00b2 }
            r10 = r12
            r19 = r9
            r9 = r8
            r8 = r19
            goto L_0x00c0
        L_0x00b2:
            r0 = move-exception
            r11 = r5
            r8 = r9
            goto L_0x01f1
        L_0x00b7:
            r0 = move-exception
            r11 = r5
            r8 = r9
            goto L_0x01b7
        L_0x00bc:
            r0 = move-exception
            r11 = r5
            goto L_0x01b7
        L_0x00c0:
            com.facebook.react.bridge.WritableMap r12 = com.facebook.react.bridge.Arguments.createMap()     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            if (r9 < r11) goto L_0x0166
            r11 = 300(0x12c, float:4.2E-43)
            if (r9 >= r11) goto L_0x0166
            java.util.Map r11 = r8.getHeaderFields()     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            java.util.Set r11 = r11.entrySet()     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            java.util.Iterator r11 = r11.iterator()     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
        L_0x00d6:
            boolean r13 = r11.hasNext()     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            if (r13 == 0) goto L_0x00fc
            java.lang.Object r13 = r11.next()     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            java.util.Map$Entry r13 = (java.util.Map.Entry) r13     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            java.lang.Object r14 = r13.getKey()     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            java.lang.String r14 = (java.lang.String) r14     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            java.lang.Object r13 = r13.getValue()     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            java.util.List r13 = (java.util.List) r13     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            java.lang.Object r13 = r13.get(r7)     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            java.lang.String r13 = (java.lang.String) r13     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            if (r14 == 0) goto L_0x00d6
            if (r13 == 0) goto L_0x00d6
            r12.putString(r14, r13)     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            goto L_0x00d6
        L_0x00fc:
            java.lang.Object[] r11 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            java.lang.Boolean r13 = java.lang.Boolean.valueOf(r6)     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            r11[r7] = r13     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            r11[r6] = r5     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r7)     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            r11[r3] = r13     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r10)     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            r11[r2] = r13     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            r1.invoke(r11)     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            java.io.BufferedInputStream r11 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            java.io.InputStream r13 = r8.getInputStream()     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            r14 = 8192(0x2000, float:1.14794E-41)
            r11.<init>(r13, r14)     // Catch:{ Throwable -> 0x00bc, all -> 0x004d }
            com.xiaomi.smarthome.framework.plugin.FileUtils.g(r23)     // Catch:{ Throwable -> 0x0164 }
            java.io.FileOutputStream r13 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x0164 }
            r13.<init>(r0)     // Catch:{ Throwable -> 0x0164 }
            byte[] r14 = new byte[r14]     // Catch:{ Throwable -> 0x0161, all -> 0x015d }
            r15 = 0
        L_0x012b:
            int r2 = r11.read(r14)     // Catch:{ Throwable -> 0x015a, all -> 0x015d }
            r3 = -1
            if (r2 == r3) goto L_0x0155
            int r15 = r15 + r2
            java.lang.Object[] r3 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x015a, all -> 0x015d }
            java.lang.Boolean r18 = java.lang.Boolean.valueOf(r6)     // Catch:{ Throwable -> 0x015a, all -> 0x015d }
            r3[r7] = r18     // Catch:{ Throwable -> 0x015a, all -> 0x015d }
            r3[r6] = r5     // Catch:{ Throwable -> 0x015a, all -> 0x015d }
            java.lang.Integer r18 = java.lang.Integer.valueOf(r15)     // Catch:{ Throwable -> 0x015a, all -> 0x015d }
            r17 = 2
            r3[r17] = r18     // Catch:{ Throwable -> 0x015a, all -> 0x015d }
            java.lang.Integer r18 = java.lang.Integer.valueOf(r10)     // Catch:{ Throwable -> 0x015a, all -> 0x015d }
            r16 = 3
            r3[r16] = r18     // Catch:{ Throwable -> 0x015a, all -> 0x015d }
            r1.invoke(r3)     // Catch:{ Throwable -> 0x015a, all -> 0x015d }
            r13.write(r14, r7, r2)     // Catch:{ Throwable -> 0x015a, all -> 0x015d }
            r3 = 2
            goto L_0x012b
        L_0x0155:
            r13.flush()     // Catch:{ Throwable -> 0x015a, all -> 0x015d }
            r5 = r13
            goto L_0x0168
        L_0x015a:
            r0 = move-exception
            r5 = r13
            goto L_0x01b8
        L_0x015d:
            r0 = move-exception
            r5 = r13
            goto L_0x01f1
        L_0x0161:
            r0 = move-exception
            r5 = r13
            goto L_0x01b7
        L_0x0164:
            r0 = move-exception
            goto L_0x01b7
        L_0x0166:
            r11 = r5
            r15 = 0
        L_0x0168:
            com.facebook.react.bridge.WritableMap r2 = com.facebook.react.bridge.Arguments.createMap()     // Catch:{ Throwable -> 0x01ad }
            java.lang.String r3 = "status"
            r2.putInt(r3, r9)     // Catch:{ Throwable -> 0x01ad }
            java.lang.String r3 = "filename"
            r9 = r20
            r2.putString(r3, r9)     // Catch:{ Throwable -> 0x01ad }
            java.lang.String r3 = "path"
            r2.putString(r3, r0)     // Catch:{ Throwable -> 0x01ad }
            java.lang.String r0 = "header"
            r2.putMap(r0, r12)     // Catch:{ Throwable -> 0x01ad }
            java.lang.Object[] r0 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x01ad }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r6)     // Catch:{ Throwable -> 0x01ad }
            r0[r7] = r3     // Catch:{ Throwable -> 0x01ad }
            r0[r6] = r2     // Catch:{ Throwable -> 0x01ad }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r15)     // Catch:{ Throwable -> 0x01ad }
            r3 = 2
            r0[r3] = r2     // Catch:{ Throwable -> 0x01ad }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r10)     // Catch:{ Throwable -> 0x01ad }
            r3 = 3
            r0[r3] = r2     // Catch:{ Throwable -> 0x01ad }
            r1.invoke(r0)     // Catch:{ Throwable -> 0x01ad }
            if (r5 == 0) goto L_0x01a2
            r5.close()     // Catch:{ Exception -> 0x01ef }
        L_0x01a2:
            if (r11 == 0) goto L_0x01a7
            r11.close()     // Catch:{ Exception -> 0x01ef }
        L_0x01a7:
            if (r8 == 0) goto L_0x01ef
        L_0x01a9:
            r8.disconnect()     // Catch:{ Exception -> 0x01ef }
            goto L_0x01ef
        L_0x01ad:
            r0 = move-exception
            goto L_0x01b8
        L_0x01af:
            r0 = move-exception
            r8 = r5
            r11 = r8
            goto L_0x01f1
        L_0x01b3:
            r0 = move-exception
            r8 = r5
            r11 = r8
        L_0x01b6:
            r10 = 0
        L_0x01b7:
            r15 = 0
        L_0x01b8:
            java.lang.String r2 = "download"
            java.lang.String r3 = "fatal"
            android.util.Log.e(r2, r3, r0)     // Catch:{ all -> 0x01f0 }
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch:{ all -> 0x01f0 }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r7)     // Catch:{ all -> 0x01f0 }
            r2[r7] = r3     // Catch:{ all -> 0x01f0 }
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)     // Catch:{ all -> 0x01f0 }
            com.facebook.react.bridge.WritableMap r0 = com.xiaomi.smarthome.framework.plugin.rn.utils.RnCallbackMapUtil.a((java.lang.String) r0)     // Catch:{ all -> 0x01f0 }
            r2[r6] = r0     // Catch:{ all -> 0x01f0 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r15)     // Catch:{ all -> 0x01f0 }
            r3 = 2
            r2[r3] = r0     // Catch:{ all -> 0x01f0 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x01f0 }
            r3 = 3
            r2[r3] = r0     // Catch:{ all -> 0x01f0 }
            r1.invoke(r2)     // Catch:{ all -> 0x01f0 }
            if (r5 == 0) goto L_0x01e7
            r5.close()     // Catch:{ Exception -> 0x01ef }
        L_0x01e7:
            if (r11 == 0) goto L_0x01ec
            r11.close()     // Catch:{ Exception -> 0x01ef }
        L_0x01ec:
            if (r8 == 0) goto L_0x01ef
            goto L_0x01a9
        L_0x01ef:
            return
        L_0x01f0:
            r0 = move-exception
        L_0x01f1:
            if (r5 == 0) goto L_0x01f6
            r5.close()     // Catch:{ Exception -> 0x0200 }
        L_0x01f6:
            if (r11 == 0) goto L_0x01fb
            r11.close()     // Catch:{ Exception -> 0x0200 }
        L_0x01fb:
            if (r8 == 0) goto L_0x0200
            r8.disconnect()     // Catch:{ Exception -> 0x0200 }
        L_0x0200:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTPersistModule.download(java.lang.String, java.lang.String, java.util.HashMap, java.lang.String, int, int, com.facebook.react.bridge.Callback):void");
    }

    static final URLConnection openConnection_aroundBody0(URL url) {
        return url.openConnection();
    }

    static final URLConnection openConnection_aroundBody2(URL url) {
        return url.openConnection();
    }

    private void deleteFile(File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            for (File deleteFile : file.listFiles()) {
                deleteFile(deleteFile);
            }
            file.delete();
            return;
        }
        file.delete();
    }

    @ReactMethod
    public final void deleteFile(String str, Callback callback) {
        if (checkFileNameValidCallBack(str, callback)) {
            try {
                File file = new File(getPathName(str));
                if (file.exists()) {
                    deleteFile(file);
                    callback.invoke(true);
                    return;
                }
                callback.invoke(false, RnCallbackMapUtil.a("file path does not exist :" + str));
            } catch (Throwable th) {
                callback.invoke(false, RnCallbackMapUtil.a(Log.getStackTraceString(th)));
            }
        }
    }

    @ReactMethod
    public void isFileExists(String str, Callback callback) {
        if (checkFileNameValidCallBack(str, callback)) {
            try {
                callback.invoke(true, Boolean.valueOf(new File(getPathName(str)).exists()));
            } catch (Exception e) {
                callback.invoke(false, RnCallbackMapUtil.a(Log.getStackTraceString(e)));
            }
        }
    }

    @Deprecated
    @ReactMethod
    public final void readFileList(Callback callback) {
        try {
            File filesPath = getFilesPath();
            if (filesPath.exists()) {
                String[] list = filesPath.list();
                WritableNativeArray writableNativeArray = new WritableNativeArray();
                for (String putString : list) {
                    WritableNativeMap writableNativeMap = new WritableNativeMap();
                    writableNativeMap.putString("name", putString);
                    writableNativeArray.pushMap(writableNativeMap);
                }
                callback.invoke(true, writableNativeArray);
                return;
            }
            callback.invoke(false, RnCallbackMapUtil.a("path does not exist"));
        } catch (Exception unused) {
            callback.invoke(false);
        }
    }

    @ReactMethod
    public final void writeFile(String str, String str2, Callback callback) {
        if (checkFileNameValidCallBack(str, callback)) {
            String pathName = getPathName(str);
            if (str2 == null) {
                str2 = "";
            }
            writeByteAsyn(pathName, str2.getBytes(), false, callback);
        }
    }

    @ReactMethod
    public final void readFileListFrom(String str, Callback callback) {
        File file;
        if (checkFolderValidCallBack(str, callback)) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    file = new File(getFilesPath() + File.separator + str);
                } else {
                    file = getFilesPath();
                }
                if (file.exists()) {
                    String[] list = file.list();
                    WritableNativeArray writableNativeArray = new WritableNativeArray();
                    for (String putString : list) {
                        WritableNativeMap writableNativeMap = new WritableNativeMap();
                        writableNativeMap.putString("name", putString);
                        writableNativeArray.pushMap(writableNativeMap);
                    }
                    callback.invoke(true, writableNativeArray);
                    return;
                }
                callback.invoke(false, RnCallbackMapUtil.a("path does not exist"));
            } catch (Exception e) {
                callback.invoke(false, RnCallbackMapUtil.a(e.toString()));
            }
        }
    }

    @ReactMethod
    public final void appendFile(String str, String str2, Callback callback) {
        if (checkFileNameValidCallBack(str, callback)) {
            String pathName = getPathName(str);
            if (str2 == null) {
                str2 = "";
            }
            writeByteAsyn(pathName, str2.getBytes(), true, callback);
        }
    }

    @ReactMethod
    public final void writeFileThroughBase64(String str, String str2, Callback callback) {
        if (checkFileNameValidCallBack(str, callback)) {
            if (str2 == null) {
                str2 = "";
            }
            try {
                writeByteAsyn(getPathName(str), Base64.decode(str2, 2), false, callback);
            } catch (Exception e) {
                callback.invoke(false, RnCallbackMapUtil.a(e.toString()));
            }
        }
    }

    @ReactMethod
    public final void appendFileThroughBase64(String str, String str2, Callback callback) {
        if (checkFileNameValidCallBack(str, callback)) {
            if (str2 == null) {
                str2 = "";
            }
            try {
                writeByteAsyn(getPathName(str), Base64.decode(str2, 2), true, callback);
            } catch (Exception e) {
                callback.invoke(false, RnCallbackMapUtil.a(e.toString()));
            }
        }
    }

    @ReactMethod
    public final void readFile(final String str, final Callback callback) {
        if (checkFileNameValidCallBack(str, callback)) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        callback.invoke(true, new String(MIOTPersistModule.this.readByte(MIOTPersistModule.this.getPathName(str))));
                    } catch (Throwable th) {
                        Callback callback = callback;
                        callback.invoke(false, RnCallbackMapUtil.a("fileName: " + str + "  " + Log.getStackTraceString(th)));
                    }
                }
            }).start();
        }
    }

    @ReactMethod
    public final void readFileToHexString(final String str, final Callback callback) {
        if (checkFileNameValidCallBack(str, callback)) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        callback.invoke(true, MIOTUtils.a(MIOTPersistModule.this.readByte(MIOTPersistModule.this.getPathName(str))));
                    } catch (Throwable th) {
                        Callback callback = callback;
                        callback.invoke(false, RnCallbackMapUtil.a("fileName: " + str + "  " + Log.getStackTraceString(th)));
                    }
                }
            }).start();
        }
    }

    @ReactMethod
    public final void readFileToBase64(final String str, final Callback callback) {
        if (checkFileNameValidCallBack(str, callback)) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        callback.invoke(true, Base64.encodeToString(MIOTPersistModule.this.readByte(MIOTPersistModule.this.getPathName(str)), 2));
                    } catch (Throwable th) {
                        Callback callback = callback;
                        callback.invoke(false, RnCallbackMapUtil.a("fileName: " + str + "  " + Log.getStackTraceString(th)));
                    }
                }
            }).start();
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x001c A[SYNTHETIC, Splitter:B:13:0x001c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] readByte(java.lang.String r5) throws java.io.IOException {
        /*
            r4 = this;
            r0 = 0
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch:{ all -> 0x0019 }
            java.lang.String r2 = "r"
            r1.<init>(r5, r2)     // Catch:{ all -> 0x0019 }
            long r2 = r1.length()     // Catch:{ all -> 0x0016 }
            int r5 = (int) r2     // Catch:{ all -> 0x0016 }
            byte[] r5 = new byte[r5]     // Catch:{ all -> 0x0016 }
            r1.read(r5)     // Catch:{ all -> 0x0016 }
            r1.close()     // Catch:{ IOException -> 0x0015 }
        L_0x0015:
            return r5
        L_0x0016:
            r5 = move-exception
            r0 = r1
            goto L_0x001a
        L_0x0019:
            r5 = move-exception
        L_0x001a:
            if (r0 == 0) goto L_0x001f
            r0.close()     // Catch:{ IOException -> 0x001f }
        L_0x001f:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTPersistModule.readByte(java.lang.String):byte[]");
    }

    /* access modifiers changed from: private */
    public String getPathName(String str) {
        if (str == null) {
            return getFilesPath() + File.separator + "defaultname";
        } else if (str.startsWith(File.separator)) {
            return getFilesPath() + str;
        } else {
            return getFilesPath() + File.separator + str;
        }
    }

    private String getUnZipFileAbsPath(String str) {
        if (TextUtils.isEmpty(str)) {
            return getFilesPath().getPath();
        }
        return getFilesPath() + File.separator + str;
    }

    @ReactMethod
    public final void dataLengthOfBase64Data(String str, Callback callback) {
        if (str == null) {
            str = "";
        }
        try {
            callback.invoke(Integer.valueOf(new String(Base64.decode(str, 2)).length()));
        } catch (Throwable th) {
            callback.invoke(0, false, Log.getStackTraceString(th));
        }
    }

    @ReactMethod
    public final void subBase64DataOfBase64Data(String str, int i, int i2, Callback callback) {
        try {
            if (str == null) {
                str = "";
            }
            String str2 = new String(Base64.decode(str, 2));
            if (i < 0 || i >= str2.length() || i2 <= 0 || i + i2 > str2.length()) {
                callback.invoke(false, "data length :" + str2.length() + " require start :" + i + " require end:" + (i + i2));
            }
            callback.invoke(true, Base64.encodeToString(str2.substring(i, i2 + i).getBytes(), 2));
        } catch (Throwable th) {
            callback.invoke(false, Log.getStackTraceString(th));
        }
    }

    @ReactMethod
    public final void uploadFile(ReadableMap readableMap, Callback callback) {
        final String a2 = MIOTUtils.a(readableMap, "uploadUrl");
        final String a3 = MIOTUtils.a(readableMap, "method");
        final ReadableMap f = MIOTUtils.f(readableMap, Downloads.RequestHeaders.e);
        final ReadableMap f2 = MIOTUtils.f(readableMap, GraphRequest.FIELDS_PARAM);
        ReadableArray e = MIOTUtils.e(readableMap, "files");
        if (e != null) {
            final WritableArray createArray = Arguments.createArray();
            for (int i = 0; i < e.size(); i++) {
                ReadableMap map = e.getMap(i);
                String a4 = MIOTUtils.a(map, "path");
                String a5 = MIOTUtils.a(map, "filename");
                if (!checkFolderValidCallBack(a4, callback) || !checkFileNameValidCallBack(a5, callback)) {
                    RnPluginLog.e("filepath or filename is invalid");
                    return;
                }
                if (a5 != null) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putString("filename", a5);
                    if (TextUtils.isEmpty(a4)) {
                        a4 = getPathName(a5);
                    }
                    createMap.putString("filepath", a4);
                    createArray.pushMap(createMap);
                }
            }
            final Callback callback2 = callback;
            new Thread(new Runnable() {
                public void run() {
                    try {
                        String str = a2;
                        String str2 = a3;
                        HashMap<String, Object> hashMap = null;
                        HashMap<String, Object> hashMap2 = f == null ? null : f.toHashMap();
                        if (f2 != null) {
                            hashMap = f2.toHashMap();
                        }
                        MIOTPersistModule.upload(str, str2, hashMap2, hashMap, createArray, callback2);
                    } catch (Exception e2) {
                        callback2.invoke(false, Log.getStackTraceString(e2));
                    }
                }
            }).start();
            return;
        }
        callback.invoke(false, "file path is illegal");
    }

    @ReactMethod
    public final void uploadFileToFDS(ReadableMap readableMap, Callback callback) {
        final String a2 = MIOTUtils.a(readableMap, "uploadUrl");
        final String a3 = MIOTUtils.a(readableMap, "method");
        final ReadableMap f = MIOTUtils.f(readableMap, Downloads.RequestHeaders.e);
        final ReadableMap f2 = MIOTUtils.f(readableMap, GraphRequest.FIELDS_PARAM);
        ReadableArray e = MIOTUtils.e(readableMap, "files");
        if (e != null) {
            final WritableArray createArray = Arguments.createArray();
            for (int i = 0; i < e.size(); i++) {
                ReadableMap map = e.getMap(i);
                String a4 = MIOTUtils.a(map, "path");
                String a5 = MIOTUtils.a(map, "filename");
                if (!checkFolderValidCallBack(a4, callback) || !checkFileNameValidCallBack(a5, callback)) {
                    RnPluginLog.e("filepath or filename is invalid");
                    return;
                }
                if (a5 != null) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putString("filename", a5);
                    if (TextUtils.isEmpty(a4)) {
                        a4 = getPathName(a5);
                    }
                    createMap.putString("filepath", a4);
                    createArray.pushMap(createMap);
                }
            }
            final Callback callback2 = callback;
            Observable.just(0).observeOn(Schedulers.io()).map(new Func1<Integer, Boolean>() {
                /* renamed from: a */
                public Boolean call(Integer num) {
                    try {
                        String str = a2;
                        String str2 = a3;
                        HashMap<String, Object> hashMap = null;
                        HashMap<String, Object> hashMap2 = f == null ? null : f.toHashMap();
                        if (f2 != null) {
                            hashMap = f2.toHashMap();
                        }
                        MIOTPersistModule.uploadToFDS(str, str2, hashMap2, hashMap, createArray, callback2);
                    } catch (Exception e2) {
                        callback2.invoke(false, Log.getStackTraceString(e2));
                    }
                    return true;
                }
            }).subscribe();
            return;
        }
        callback.invoke(false, "file path is illegal");
    }

    @ReactMethod
    public final void downloadFile(final String str, final String str2, final Callback callback) {
        if (checkFolderValidCallBack(str2, callback)) {
            new Thread(new Runnable() {
                public void run() {
                    MIOTPersistModule.download(str2, str, (HashMap<String, Object>) null, MIOTPersistModule.this.getPathName(str2), 60000, 60000, new Callback() {
                        public void invoke(Object... objArr) {
                            if (!objArr[0].booleanValue()) {
                                callback.invoke(objArr);
                            } else if (objArr[1] == null) {
                                WritableMap createMap = Arguments.createMap();
                                createMap.putString("filename", str2);
                                createMap.putString("url", str);
                                createMap.putInt("totalBytesRead", objArr[2].intValue());
                                createMap.putInt("totalBytesExpectedToRead", objArr[3].intValue());
                                createMap.putString(WXGlobalEventReceiver.EVENT_NAME, RNEventReceiver.FILEISDOWNLOADINGEVENTNAME);
                                ((DeviceEventManagerModule.RCTDeviceEventEmitter) MIOTPersistModule.this.getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(RNEventReceiver.FILEISDOWNLOADINGEVENTNAME, createMap);
                            } else {
                                callback.invoke(objArr);
                            }
                        }
                    });
                }
            }).start();
        }
    }

    private void writeByteAsyn(final String str, final byte[] bArr, final boolean z, final Callback callback) {
        Observable.create(new Observable.OnSubscribe<Error>() {
            /* JADX WARNING: Removed duplicated region for block: B:22:0x004c A[SYNTHETIC, Splitter:B:22:0x004c] */
            /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
            /* renamed from: a */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void call(rx.Subscriber<? super com.xiaomi.smarthome.core.entity.Error> r6) {
                /*
                    r5 = this;
                    r0 = 0
                    java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch:{ Throwable -> 0x0034, all -> 0x0031 }
                    java.lang.String r2 = r2     // Catch:{ Throwable -> 0x0034, all -> 0x0031 }
                    java.lang.String r3 = "rw"
                    r1.<init>(r2, r3)     // Catch:{ Throwable -> 0x0034, all -> 0x0031 }
                    boolean r0 = r4     // Catch:{ Throwable -> 0x002f }
                    if (r0 == 0) goto L_0x0016
                    long r2 = r1.length()     // Catch:{ Throwable -> 0x002f }
                    r1.seek(r2)     // Catch:{ Throwable -> 0x002f }
                    goto L_0x001b
                L_0x0016:
                    r2 = 0
                    r1.setLength(r2)     // Catch:{ Throwable -> 0x002f }
                L_0x001b:
                    byte[] r0 = r3     // Catch:{ Throwable -> 0x002f }
                    r1.write(r0)     // Catch:{ Throwable -> 0x002f }
                    com.xiaomi.smarthome.core.entity.Error r0 = new com.xiaomi.smarthome.core.entity.Error     // Catch:{ Throwable -> 0x002f }
                    r2 = 1
                    java.lang.String r3 = ""
                    r0.<init>(r2, r3)     // Catch:{ Throwable -> 0x002f }
                    r6.onNext(r0)     // Catch:{ Throwable -> 0x002f }
                L_0x002b:
                    r1.close()     // Catch:{ IOException -> 0x0048 }
                    goto L_0x0048
                L_0x002f:
                    r0 = move-exception
                    goto L_0x0038
                L_0x0031:
                    r6 = move-exception
                    r1 = r0
                    goto L_0x004a
                L_0x0034:
                    r1 = move-exception
                    r4 = r1
                    r1 = r0
                    r0 = r4
                L_0x0038:
                    com.xiaomi.smarthome.core.entity.Error r2 = new com.xiaomi.smarthome.core.entity.Error     // Catch:{ all -> 0x0049 }
                    r3 = -1
                    java.lang.String r0 = android.util.Log.getStackTraceString(r0)     // Catch:{ all -> 0x0049 }
                    r2.<init>(r3, r0)     // Catch:{ all -> 0x0049 }
                    r6.onNext(r2)     // Catch:{ all -> 0x0049 }
                    if (r1 == 0) goto L_0x0048
                    goto L_0x002b
                L_0x0048:
                    return
                L_0x0049:
                    r6 = move-exception
                L_0x004a:
                    if (r1 == 0) goto L_0x004f
                    r1.close()     // Catch:{ IOException -> 0x004f }
                L_0x004f:
                    throw r6
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTPersistModule.AnonymousClass9.call(rx.Subscriber):void");
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Error>() {
            /* renamed from: a */
            public void call(Error error) {
                if (error.a() == 1) {
                    callback.invoke(true);
                    return;
                }
                callback.invoke(false, RnCallbackMapUtil.a(error.b()));
            }
        }, (Action1<Throwable>) new Action1<Throwable>() {
            /* renamed from: a */
            public void call(Throwable th) {
                callback.invoke(false, RnCallbackMapUtil.a(th.toString()));
            }
        });
    }

    @ReactMethod
    public final void unzipFile(String str, String str2, Callback callback) {
        if (checkFileNameValidCallBack(str, callback) && checkFolderValidCallBack(str2, callback)) {
            try {
                String pathName = getPathName(str);
                if (str.lastIndexOf(46) >= 0) {
                    callback.invoke(Boolean.valueOf(RnPluginFileUtils.a(pathName, getUnZipFileAbsPath(str2))));
                    return;
                }
                callback.invoke(false, RnCallbackMapUtil.a("filename mast contain ‘.’ , for example plugin.zip"));
            } catch (Exception e) {
                callback.invoke(false, RnCallbackMapUtil.a(Log.getStackTraceString(e)));
            }
        }
    }

    @ReactMethod
    public final void ungzYunMiFile(String str, Callback callback) {
        if (checkFileNameValidCallBack(str, callback)) {
            String str2 = null;
            File file = new File(getPathName(str));
            if (!file.exists()) {
                callback.invoke(false, file.getAbsolutePath() + " is not exists");
            } else if (!file.isFile()) {
                callback.invoke(false, file.getAbsolutePath() + " is not a file");
            } else {
                byte[] uncompress = uncompress(file);
                if (uncompress == null) {
                    callback.invoke(false, "ungzYunMiFile error, if you want to see error detail, please use Android Studio LogCat");
                    return;
                }
                try {
                    str2 = new String(Base64.encode(uncompress, 2), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    LogUtil.b(PluginRNActivity.TAG, e.toString());
                }
                callback.invoke(true, str2);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0082 A[SYNTHETIC, Splitter:B:33:0x0082] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0090 A[SYNTHETIC, Splitter:B:38:0x0090] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00a0 A[SYNTHETIC, Splitter:B:44:0x00a0] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ae A[SYNTHETIC, Splitter:B:49:0x00ae] */
    /* JADX WARNING: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    @com.facebook.react.bridge.ReactMethod
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void ungzFile(java.lang.String r10, com.facebook.react.bridge.Callback r11) {
        /*
            r9 = this;
            boolean r0 = r9.checkFileNameValidCallBack(r10, r11)
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            r0 = 0
            r1 = 1
            r2 = 2
            r3 = 0
            java.util.zip.GZIPInputStream r4 = new java.util.zip.GZIPInputStream     // Catch:{ Exception -> 0x0060, all -> 0x005c }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0060, all -> 0x005c }
            java.lang.String r10 = r9.getPathName(r10)     // Catch:{ Exception -> 0x0060, all -> 0x005c }
            r5.<init>(r10)     // Catch:{ Exception -> 0x0060, all -> 0x005c }
            r4.<init>(r5)     // Catch:{ Exception -> 0x0060, all -> 0x005c }
            java.io.ByteArrayOutputStream r10 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0057, all -> 0x0054 }
            r10.<init>()     // Catch:{ Exception -> 0x0057, all -> 0x0054 }
            r0 = 1024(0x400, float:1.435E-42)
            byte[] r5 = new byte[r0]     // Catch:{ Exception -> 0x0052 }
        L_0x0022:
            int r6 = r4.read(r5, r3, r0)     // Catch:{ Exception -> 0x0052 }
            r7 = -1
            if (r6 == r7) goto L_0x002d
            r10.write(r5, r3, r6)     // Catch:{ Exception -> 0x0052 }
            goto L_0x0022
        L_0x002d:
            byte[] r0 = r10.toByteArray()     // Catch:{ Exception -> 0x0052 }
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x0052 }
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x0052 }
            r5[r3] = r6     // Catch:{ Exception -> 0x0052 }
            java.lang.String r0 = android.util.Base64.encodeToString(r0, r2)     // Catch:{ Exception -> 0x0052 }
            r5[r1] = r0     // Catch:{ Exception -> 0x0052 }
            r11.invoke(r5)     // Catch:{ Exception -> 0x0052 }
            r4.close()     // Catch:{ IOException -> 0x0046 }
            goto L_0x004e
        L_0x0046:
            r11 = move-exception
            java.lang.String r11 = r11.toString()
            com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog.b(r11)
        L_0x004e:
            r10.close()     // Catch:{ IOException -> 0x0094 }
            goto L_0x009c
        L_0x0052:
            r0 = move-exception
            goto L_0x0064
        L_0x0054:
            r11 = move-exception
            r10 = r0
            goto L_0x009e
        L_0x0057:
            r10 = move-exception
            r8 = r0
            r0 = r10
            r10 = r8
            goto L_0x0064
        L_0x005c:
            r11 = move-exception
            r10 = r0
            r4 = r10
            goto L_0x009e
        L_0x0060:
            r10 = move-exception
            r4 = r0
            r0 = r10
            r10 = r4
        L_0x0064:
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x009d }
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r3)     // Catch:{ all -> 0x009d }
            r2[r3] = r5     // Catch:{ all -> 0x009d }
            java.lang.String r3 = r0.toString()     // Catch:{ all -> 0x009d }
            com.facebook.react.bridge.WritableMap r3 = com.xiaomi.smarthome.framework.plugin.rn.utils.RnCallbackMapUtil.a((java.lang.String) r3)     // Catch:{ all -> 0x009d }
            r2[r1] = r3     // Catch:{ all -> 0x009d }
            r11.invoke(r2)     // Catch:{ all -> 0x009d }
            java.lang.String r11 = r0.toString()     // Catch:{ all -> 0x009d }
            com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog.b(r11)     // Catch:{ all -> 0x009d }
            if (r4 == 0) goto L_0x008e
            r4.close()     // Catch:{ IOException -> 0x0086 }
            goto L_0x008e
        L_0x0086:
            r11 = move-exception
            java.lang.String r11 = r11.toString()
            com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog.b(r11)
        L_0x008e:
            if (r10 == 0) goto L_0x009c
            r10.close()     // Catch:{ IOException -> 0x0094 }
            goto L_0x009c
        L_0x0094:
            r10 = move-exception
            java.lang.String r10 = r10.toString()
            com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog.b(r10)
        L_0x009c:
            return
        L_0x009d:
            r11 = move-exception
        L_0x009e:
            if (r4 == 0) goto L_0x00ac
            r4.close()     // Catch:{ IOException -> 0x00a4 }
            goto L_0x00ac
        L_0x00a4:
            r0 = move-exception
            java.lang.String r0 = r0.toString()
            com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog.b(r0)
        L_0x00ac:
            if (r10 == 0) goto L_0x00ba
            r10.close()     // Catch:{ IOException -> 0x00b2 }
            goto L_0x00ba
        L_0x00b2:
            r10 = move-exception
            java.lang.String r10 = r10.toString()
            com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog.b(r10)
        L_0x00ba:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTPersistModule.ungzFile(java.lang.String, com.facebook.react.bridge.Callback):void");
    }

    @ReactMethod
    public final void saveImageToPhotosAlbum(String str, Callback callback) {
        try {
            if (checkFileNameValidCallBack(str, callback)) {
                Calendar instance = Calendar.getInstance();
                String format = String.format("PIC_%d%02d%02d_%02d%02d%02d%03d.jpg", new Object[]{Integer.valueOf(instance.get(1)), Integer.valueOf(instance.get(2) + 1), Integer.valueOf(instance.get(5)), Integer.valueOf(instance.get(11)), Integer.valueOf(instance.get(12)), Integer.valueOf(instance.get(13)), Integer.valueOf(instance.get(14))});
                String str2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + File.separator + format;
                FileUtils.a(getPathName(str), str2);
                callback.invoke(true);
                try {
                    Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                    intent.setData(Uri.fromFile(new File(str2)));
                    getCurrentActivity().sendBroadcast(intent);
                } catch (Throwable th) {
                    RnPluginLog.e("saveImageToPhotosAlbum, send ACTION_MEDIA_SCANNER_SCAN_FILE broadcast error:" + Log.getStackTraceString(th));
                }
            }
        } catch (Exception e) {
            callback.invoke(false, RnCallbackMapUtil.a("saveImageToPhotosAlbum error:" + Log.getStackTraceString(e)));
        }
    }

    @ReactMethod
    public final void screenShot(String str, Callback callback) {
        if (checkFileNameValidCallBack(str, callback)) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(IjkMediaMeta.IJKM_KEY_FORMAT, "jpg");
                jSONObject.put("result", ViewShot.Results.TEMP_FILE);
                jSONObject.put("snapshotContentContainer", false);
                jSONObject.put("fileName", str);
                jSONObject.put(Constants.Name.QUALITY, 0.8d);
                captureRef(-1, jSONObject, callback);
            } catch (JSONException e) {
                RnPluginLog.b(e.toString());
                callback.invoke(false, RnCallbackMapUtil.a(e.toString()));
            }
        }
    }

    @ReactMethod
    public final void screenShotInRect(String str, ReadableMap readableMap, Callback callback) {
        ReadableMap readableMap2 = readableMap;
        if (checkFileNameValidCallBack(str, callback)) {
            final double c = MIOTUtils.c(readableMap2, "l");
            final double c2 = MIOTUtils.c(readableMap2, "t");
            final double c3 = MIOTUtils.c(readableMap2, "w");
            final double c4 = MIOTUtils.c(readableMap2, "h");
            View decorView = getCurrentActivity().getWindow().getDecorView();
            final View view = decorView;
            final String str2 = str;
            final Callback callback2 = callback;
            decorView.post(new Runnable() {
                public void run() {
                    try {
                        view.setDrawingCacheEnabled(true);
                        Bitmap drawingCache = view.getDrawingCache();
                        float f2 = MIOTPersistModule.this.getReactApplicationContext().getResources().getDisplayMetrics().density;
                        String access$000 = MIOTPersistModule.this.getPathName(str2);
                        double d2 = (double) f2;
                        double d3 = c;
                        Double.isNaN(d2);
                        int i = (int) ((d3 * d2) + 0.5d);
                        double d4 = c2;
                        Double.isNaN(d2);
                        int i2 = (int) ((d4 * d2) + 0.5d);
                        double d5 = c3;
                        Double.isNaN(d2);
                        int i3 = (int) ((d5 * d2) + 0.5d);
                        double d6 = c4;
                        Double.isNaN(d2);
                        Bitmap.createBitmap(drawingCache, i, i2, i3, (int) ((d2 * d6) + 0.5d)).compress(Bitmap.CompressFormat.JPEG, 90, new BufferedOutputStream(new FileOutputStream(access$000)));
                        view.setDrawingCacheEnabled(false);
                        callback2.invoke(true, access$000);
                    } catch (Exception e2) {
                        callback2.invoke(false, RnCallbackMapUtil.a(Log.getStackTraceString(e2)));
                    }
                }
            });
        }
    }

    @ReactMethod
    public final void amapScreenShot(int i, final String str, final Callback callback) {
        try {
            if (checkFileNameValidCallBack(str, callback)) {
                final MapView findViewById = getCurrentActivity().findViewById(i);
                findViewById.post(new Runnable() {
                    public void run() {
                        findViewById.getMap().getMapScreenShot(new AMap.OnMapScreenShotListener() {
                            public void onMapScreenShot(Bitmap bitmap) {
                                try {
                                    String access$000 = MIOTPersistModule.this.getPathName(str);
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, new BufferedOutputStream(new FileOutputStream(access$000)));
                                    callback.invoke(true, access$000);
                                } catch (Throwable th) {
                                    callback.invoke(false, RnCallbackMapUtil.a(Log.getStackTraceString(th)));
                                }
                            }
                        });
                    }
                });
            }
        } catch (Exception e) {
            callback.invoke(false, RnCallbackMapUtil.a(Log.getStackTraceString(e)));
        }
    }

    @ReactMethod
    public final void longScreenShot(int i, final String str, final Callback callback) {
        try {
            if (checkFileNameValidCallBack(str, callback)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(IjkMediaMeta.IJKM_KEY_FORMAT, "jpg");
                jSONObject.put("result", ViewShot.Results.TEMP_FILE);
                jSONObject.put("snapshotContentContainer", true);
                jSONObject.put("fileName", str);
                jSONObject.put(Constants.Name.QUALITY, 0.8d);
                final View findViewById = getCurrentActivity().findViewById(i);
                if (findViewById == null) {
                    callback.invoke(false, RnCallbackMapUtil.a("can not find view Id, id is " + i));
                } else if (findViewById instanceof ScrollView) {
                    captureRef(i, jSONObject, callback);
                } else {
                    findViewById.post(new Runnable() {
                        public void run() {
                            try {
                                String access$000 = MIOTPersistModule.this.getPathName(str);
                                MIOTUtils.a(findViewById).compress(Bitmap.CompressFormat.JPEG, 90, new BufferedOutputStream(new FileOutputStream(access$000)));
                                callback.invoke(true, access$000);
                            } catch (Exception e) {
                                callback.invoke(false, RnCallbackMapUtil.a(Log.getStackTraceString(e)));
                            }
                        }
                    });
                }
            }
        } catch (Exception e) {
            callback.invoke(false, RnCallbackMapUtil.a(Log.getStackTraceString(e)));
        }
    }

    @ReactMethod
    public final void getRGBAValueFromImageAtPath(String str, ReadableArray readableArray, Callback callback) {
        try {
            if (checkFileNameValidCallBack(str, callback)) {
                Bitmap decodeStream = BitmapFactory.decodeStream(new BufferedInputStream(new FileInputStream(getPathName(str))));
                WritableArray createArray = Arguments.createArray();
                if (readableArray == null || readableArray.size() <= 0) {
                    for (int i = 0; i < decodeStream.getWidth(); i++) {
                        WritableArray createArray2 = Arguments.createArray();
                        for (int i2 = 0; i2 < decodeStream.getHeight(); i2++) {
                            WritableArray createArray3 = Arguments.createArray();
                            int pixel = decodeStream.getPixel(i, i2);
                            createArray3.pushInt(Color.red(pixel));
                            createArray3.pushInt(Color.green(pixel));
                            createArray3.pushInt(Color.blue(pixel));
                            createArray3.pushInt(Color.alpha(pixel));
                            createArray2.pushArray(createArray3);
                        }
                        createArray.pushArray(createArray2);
                    }
                } else {
                    for (int i3 = 0; i3 < readableArray.size(); i3++) {
                        WritableArray createArray4 = Arguments.createArray();
                        int pixel2 = decodeStream.getPixel(MIOTUtils.d(readableArray.getMap(i3), "x"), MIOTUtils.d(readableArray.getMap(i3), Constants.Name.Y));
                        createArray4.pushInt(Color.red(pixel2));
                        createArray4.pushInt(Color.green(pixel2));
                        createArray4.pushInt(Color.blue(pixel2));
                        createArray4.pushInt(Color.alpha(pixel2));
                        createArray.pushArray(createArray4);
                    }
                }
                callback.invoke(true, createArray);
            }
        } catch (Throwable th) {
            callback.invoke(false, Log.getStackTraceString(th));
        }
    }

    /* JADX WARNING: type inference failed for: r22v1 */
    /* JADX WARNING: type inference failed for: r22v2 */
    /* JADX WARNING: Incorrect type for immutable var: ssa=boolean, code=?, for r22v0, types: [boolean] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void captureRef(int r24, org.json.JSONObject r25, com.facebook.react.bridge.Callback r26) {
        /*
            r23 = this;
            r1 = r23
            r0 = r25
            r2 = r26
            com.facebook.react.bridge.ReactApplicationContext r3 = r23.getReactApplicationContext()
            android.content.res.Resources r3 = r3.getResources()
            android.util.DisplayMetrics r3 = r3.getDisplayMetrics()
            java.lang.String r4 = "format"
            java.lang.String r7 = r0.optString(r4)
            java.lang.String r4 = "quality"
            double r9 = r0.optDouble(r4)
            java.lang.String r4 = "result"
            java.lang.String r14 = r0.optString(r4)
            java.lang.String r4 = "snapshotContentContainer"
            boolean r4 = r0.optBoolean(r4)
            java.lang.Boolean r15 = java.lang.Boolean.valueOf(r4)
            java.lang.String r4 = "fileName"
            java.lang.String r4 = r0.optString(r4)
            java.lang.String r5 = "width"
            boolean r5 = r0.has(r5)
            r6 = 0
            if (r5 == 0) goto L_0x0052
            float r5 = r3.density
            double r11 = (double) r5
            java.lang.String r5 = "width"
            double r16 = r0.optDouble(r5)
            java.lang.Double.isNaN(r11)
            double r11 = r11 * r16
            int r5 = (int) r11
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r11 = r5
            goto L_0x0053
        L_0x0052:
            r11 = r6
        L_0x0053:
            java.lang.String r5 = "height"
            boolean r5 = r0.has(r5)
            if (r5 == 0) goto L_0x0070
            float r3 = r3.density
            double r5 = (double) r3
            java.lang.String r3 = "height"
            double r12 = r0.optDouble(r3)
            java.lang.Double.isNaN(r5)
            double r5 = r5 * r12
            int r0 = (int) r5
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r12 = r0
            goto L_0x0071
        L_0x0070:
            r12 = r6
        L_0x0071:
            java.lang.String r0 = "jpg"
            boolean r0 = r0.equals(r7)
            r13 = 1
            r19 = 0
            if (r0 == 0) goto L_0x007e
            r8 = 0
            goto L_0x0094
        L_0x007e:
            java.lang.String r0 = "webm"
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L_0x0088
            r8 = 2
            goto L_0x0094
        L_0x0088:
            java.lang.String r0 = "raw"
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L_0x0093
            r0 = -1
            r8 = -1
            goto L_0x0094
        L_0x0093:
            r8 = 1
        L_0x0094:
            boolean[] r6 = new boolean[r13]
            r6[r19] = r19
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x00dd }
            java.lang.String r4 = r1.getPathName(r4)     // Catch:{ Throwable -> 0x00dd }
            r0.<init>(r4)     // Catch:{ Throwable -> 0x00dd }
            boolean r4 = r0.exists()     // Catch:{ Throwable -> 0x00dd }
            if (r4 == 0) goto L_0x00aa
            r0.delete()     // Catch:{ Throwable -> 0x00dd }
        L_0x00aa:
            android.app.Activity r17 = r23.getCurrentActivity()     // Catch:{ Throwable -> 0x00dd }
            com.facebook.react.bridge.ReactApplicationContext r4 = r23.getReactApplicationContext()     // Catch:{ Throwable -> 0x00dd }
            java.lang.Class<com.facebook.react.uimanager.UIManagerModule> r5 = com.facebook.react.uimanager.UIManagerModule.class
            com.facebook.react.bridge.NativeModule r4 = r4.getNativeModule(r5)     // Catch:{ Throwable -> 0x00dd }
            com.facebook.react.uimanager.UIManagerModule r4 = (com.facebook.react.uimanager.UIManagerModule) r4     // Catch:{ Throwable -> 0x00dd }
            com.xiaomi.smarthome.framework.plugin.rn.nativemodule.viewshot.ViewShot r5 = new com.xiaomi.smarthome.framework.plugin.rn.nativemodule.viewshot.ViewShot     // Catch:{ Throwable -> 0x00dd }
            com.facebook.react.bridge.ReactApplicationContext r16 = r23.getReactApplicationContext()     // Catch:{ Throwable -> 0x00dd }
            com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTPersistModule$13 r3 = new com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTPersistModule$13     // Catch:{ Throwable -> 0x00dd }
            r3.<init>(r6, r2)     // Catch:{ Throwable -> 0x00dd }
            r20 = r5
            r5 = r20
            r21 = r6
            r6 = r24
            r22 = 1
            r13 = r0
            r18 = r3
            r5.<init>(r6, r7, r8, r9, r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ Throwable -> 0x00db }
            r0 = r20
            r4.addUIBlock(r0)     // Catch:{ Throwable -> 0x00db }
            goto L_0x0107
        L_0x00db:
            r0 = move-exception
            goto L_0x00e2
        L_0x00dd:
            r0 = move-exception
            r21 = r6
            r22 = 1
        L_0x00e2:
            boolean r3 = r21[r19]
            if (r3 != 0) goto L_0x0107
            r21[r19] = r22
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x00ff }
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r19)     // Catch:{ Exception -> 0x00ff }
            r3[r19] = r4     // Catch:{ Exception -> 0x00ff }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00ff }
            com.facebook.react.bridge.WritableMap r0 = com.xiaomi.smarthome.framework.plugin.rn.utils.RnCallbackMapUtil.a((java.lang.String) r0)     // Catch:{ Exception -> 0x00ff }
            r3[r22] = r0     // Catch:{ Exception -> 0x00ff }
            r2.invoke(r3)     // Catch:{ Exception -> 0x00ff }
            goto L_0x0107
        L_0x00ff:
            r0 = move-exception
            java.lang.String r0 = r0.toString()
            com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog.b(r0)
        L_0x0107:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTPersistModule.captureRef(int, org.json.JSONObject, com.facebook.react.bridge.Callback):void");
    }

    private boolean checkFolderValidCallBack(String str, Callback callback) {
        if (TextUtils.isEmpty(str) || RnPluginFileUtils.c(getFilesPath().toString(), getPathName(str))) {
            return true;
        }
        callback.invoke(false, str + ", this folder is not valid, cannot contains ...");
        return false;
    }

    static final URLConnection openConnection_aroundBody4(URL url) {
        return url.openConnection();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00ea, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00eb, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00ee, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00ef, code lost:
        r2 = 5;
        r3 = null;
        r8 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x019f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x01a0, code lost:
        r2 = 5;
        r3 = null;
        r17 = null;
        r18 = r14;
        r14 = r13;
        r13 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0298, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0299, code lost:
        r2 = 5;
        r3 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x02f5  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x02fa  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x02ff  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0304  */
    /* JADX WARNING: Removed duplicated region for block: B:127:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ea A[ExcHandler: all (th java.lang.Throwable), Splitter:B:14:0x007c] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x02df  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x02e4  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x02e9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void upload(java.lang.String r19, java.lang.String r20, java.util.HashMap<java.lang.String, java.lang.Object> r21, java.util.HashMap<java.lang.String, java.lang.Object> r22, com.facebook.react.bridge.ReadableArray r23, com.facebook.react.bridge.Callback r24) throws java.lang.Exception {
        /*
            r1 = r24
            java.lang.String r0 = "\r\n"
            java.lang.String r2 = "--"
            java.lang.String r3 = "***miotuploadFile***"
            r9 = 1
            r10 = 0
            java.net.URL r11 = new java.net.URL     // Catch:{ Throwable -> 0x02ab, all -> 0x02a4 }
            r12 = r19
            r11.<init>(r12)     // Catch:{ Throwable -> 0x02ab, all -> 0x02a4 }
            com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor r12 = com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor.b()     // Catch:{ Throwable -> 0x02ab, all -> 0x02a4 }
            java.net.URLConnection r11 = r12.b(r11)     // Catch:{ Throwable -> 0x02ab, all -> 0x02a4 }
            java.net.HttpURLConnection r11 = (java.net.HttpURLConnection) r11     // Catch:{ Throwable -> 0x02ab, all -> 0x02a4 }
            r11.setUseCaches(r10)     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
            r11.setDoOutput(r9)     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
            r11.setDoInput(r9)     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
            r12 = r20
            r11.setRequestMethod(r12)     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
            java.lang.String r12 = "Content-Type"
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
            r13.<init>()     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
            java.lang.String r14 = "multipart/form-data;boundary="
            r13.append(r14)     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
            r13.append(r3)     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
            java.lang.String r13 = r13.toString()     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
            r11.setRequestProperty(r12, r13)     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
            if (r21 == 0) goto L_0x0071
            java.util.Set r12 = r21.entrySet()     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
            java.util.Iterator r12 = r12.iterator()     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
        L_0x0049:
            boolean r13 = r12.hasNext()     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
            if (r13 == 0) goto L_0x0071
            java.lang.Object r13 = r12.next()     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
            java.util.Map$Entry r13 = (java.util.Map.Entry) r13     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
            java.lang.Object r14 = r13.getValue()     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
            if (r14 == 0) goto L_0x0049
            java.lang.Object r13 = r13.getKey()     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
            java.lang.String r13 = (java.lang.String) r13     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
            java.lang.String r13 = java.net.URLEncoder.encode(r13)     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
            java.lang.String r14 = r14.toString()     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
            java.lang.String r14 = java.net.URLEncoder.encode(r14)     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
            r11.setRequestProperty(r13, r14)     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
            goto L_0x0049
        L_0x0071:
            java.io.DataOutputStream r12 = new java.io.DataOutputStream     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
            java.io.OutputStream r13 = r11.getOutputStream()     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
            r12.<init>(r13)     // Catch:{ Throwable -> 0x029f, all -> 0x029c }
            if (r22 == 0) goto L_0x00f4
            java.util.Set r13 = r22.entrySet()     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            java.util.Iterator r13 = r13.iterator()     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
        L_0x0084:
            boolean r14 = r13.hasNext()     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            if (r14 == 0) goto L_0x00f4
            java.lang.Object r14 = r13.next()     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            r15.<init>()     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            r15.append(r2)     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            r15.append(r3)     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            r15.append(r0)     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            java.lang.String r15 = r15.toString()     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            r12.writeBytes(r15)     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            java.lang.Object r15 = r14.getKey()     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            java.lang.String r15 = (java.lang.String) r15     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            java.lang.String r15 = java.net.URLEncoder.encode(r15)     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            java.lang.Object r16 = r14.getValue()     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            if (r16 != 0) goto L_0x00b8
            java.lang.String r14 = ""
            goto L_0x00c4
        L_0x00b8:
            java.lang.Object r14 = r14.getValue()     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            java.lang.String r14 = r14.toString()     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            java.lang.String r14 = java.net.URLEncoder.encode(r14)     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
        L_0x00c4:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            r8.<init>()     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            java.lang.String r4 = "Content-Disposition: form-data; name=\""
            r8.append(r4)     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            r8.append(r15)     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            java.lang.String r4 = "\""
            r8.append(r4)     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            r8.append(r0)     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            java.lang.String r4 = r8.toString()     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            r12.writeBytes(r4)     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            r12.writeBytes(r0)     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            r12.writeBytes(r14)     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            r12.writeBytes(r0)     // Catch:{ Throwable -> 0x00ee, all -> 0x00ea }
            goto L_0x0084
        L_0x00ea:
            r0 = move-exception
            r3 = 0
            goto L_0x02a8
        L_0x00ee:
            r0 = move-exception
            r2 = 5
            r3 = 0
            r8 = 1
            goto L_0x02b1
        L_0x00f4:
            r4 = 0
            r8 = 1
            r13 = 0
            r14 = 0
        L_0x00f8:
            int r15 = r23.size()     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            if (r4 >= r15) goto L_0x01c1
            r15 = r23
            com.facebook.react.bridge.ReadableMap r5 = com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTUtils.a((com.facebook.react.bridge.ReadableArray) r15, (int) r4)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            if (r5 == 0) goto L_0x01bb
            java.lang.String r6 = "filename"
            java.lang.String r6 = com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTUtils.a((com.facebook.react.bridge.ReadableMap) r5, (java.lang.String) r6)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            java.lang.String r9 = "filepath"
            java.lang.String r5 = com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTUtils.a((com.facebook.react.bridge.ReadableMap) r5, (java.lang.String) r9)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            java.io.File r9 = new java.io.File     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r9.<init>(r5)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            java.lang.String r5 = getMimeType(r5)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r7.<init>()     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r7.append(r2)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r7.append(r3)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r7.append(r0)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            java.lang.String r7 = r7.toString()     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r12.writeBytes(r7)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r7.<init>()     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            java.lang.String r10 = "Content-Disposition: form-data; name=\""
            r7.append(r10)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r7.append(r6)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            java.lang.String r10 = "\";filename=\""
            r7.append(r10)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r7.append(r6)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            java.lang.String r6 = "\""
            r7.append(r6)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r7.append(r0)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            java.lang.String r6 = r7.toString()     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r12.writeBytes(r6)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r6.<init>()     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            java.lang.String r7 = "Content-Type: "
            r6.append(r7)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r6.append(r5)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r6.append(r0)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            java.lang.String r5 = r6.toString()     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r12.writeBytes(r5)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r12.writeBytes(r0)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            long r5 = r9.length()     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            int r5 = (int) r5     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            byte[] r5 = new byte[r5]     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r6.<init>(r9)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r7 = 4096(0x1000, float:5.74E-42)
            r9 = 1048576(0x100000, float:1.469368E-39)
            int r7 = java.lang.Math.min(r7, r9)     // Catch:{ Throwable -> 0x01b6, all -> 0x00ea }
            int r9 = r5.length     // Catch:{ Throwable -> 0x01b6, all -> 0x00ea }
            int r7 = java.lang.Math.min(r7, r9)     // Catch:{ Throwable -> 0x01b6, all -> 0x00ea }
            r9 = 0
            int r10 = r6.read(r5, r9, r7)     // Catch:{ Throwable -> 0x01b6, all -> 0x00ea }
            int r14 = r5.length     // Catch:{ Throwable -> 0x01b6, all -> 0x00ea }
            int r13 = r10 + 0
        L_0x018f:
            if (r10 <= 0) goto L_0x01ab
            r12.write(r5, r9, r7)     // Catch:{ Throwable -> 0x019f, all -> 0x00ea }
            int r10 = r6.read(r5, r9, r7)     // Catch:{ Throwable -> 0x019f, all -> 0x00ea }
            r9 = -1
            if (r10 != r9) goto L_0x019d
        L_0x019b:
            r9 = 0
            goto L_0x018f
        L_0x019d:
            int r13 = r13 + r10
            goto L_0x019b
        L_0x019f:
            r0 = move-exception
            r2 = 5
            r3 = 0
            r17 = 0
            r18 = r14
            r14 = r13
            r13 = r18
            goto L_0x02b5
        L_0x01ab:
            r12.writeBytes(r0)     // Catch:{ Throwable -> 0x019f, all -> 0x00ea }
            int r8 = r8 + 1
            r18 = r14
            r14 = r13
            r13 = r18
            goto L_0x01bb
        L_0x01b6:
            r0 = move-exception
            r2 = 5
            r3 = 0
            goto L_0x02b2
        L_0x01bb:
            int r4 = r4 + 1
            r9 = 1
            r10 = 0
            goto L_0x00f8
        L_0x01c1:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r4.<init>()     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r4.append(r2)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r4.append(r3)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r4.append(r2)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r4.append(r0)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            java.lang.String r0 = r4.toString()     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r12.writeBytes(r0)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r12.flush()     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            java.io.InputStream r0 = r11.getInputStream()     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x0298, all -> 0x00ea }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0292, all -> 0x028d }
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x0292, all -> 0x028d }
            r0.<init>(r2)     // Catch:{ Throwable -> 0x0292, all -> 0x028d }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x0292, all -> 0x028d }
            com.facebook.react.bridge.WritableMap r0 = com.facebook.react.bridge.Arguments.createMap()     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            java.util.Map r4 = r11.getHeaderFields()     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            java.util.Set r4 = r4.entrySet()     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
        L_0x01ff:
            boolean r5 = r4.hasNext()     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            if (r5 == 0) goto L_0x0222
            java.lang.Object r5 = r4.next()     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            java.lang.Object r6 = r5.getKey()     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            java.lang.Object r5 = r5.getValue()     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            java.util.List r5 = (java.util.List) r5     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            r7 = 0
            java.lang.Object r5 = r5.get(r7)     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            r0.putString(r6, r5)     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            goto L_0x01ff
        L_0x0222:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            r4.<init>()     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
        L_0x0227:
            java.lang.String r5 = r3.readLine()     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            if (r5 == 0) goto L_0x0236
            r4.append(r5)     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            java.lang.String r5 = "\n"
            r4.append(r5)     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            goto L_0x0227
        L_0x0236:
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            int r5 = r11.getResponseCode()     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            com.facebook.react.bridge.WritableMap r6 = com.facebook.react.bridge.Arguments.createMap()     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            java.lang.String r7 = "header"
            r6.putMap(r7, r0)     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            java.lang.String r0 = "data"
            r6.putString(r0, r4)     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            java.lang.String r0 = "status"
            r6.putInt(r0, r5)     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            r4 = 5
            java.lang.Object[] r0 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            r4 = 1
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r4)     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            r7 = 0
            r0[r7] = r5     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            r0[r4] = r6     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r8)     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            r5 = 2
            r0[r5] = r4     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r13)     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            r5 = 3
            r0[r5] = r4     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r14)     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            r5 = 4
            r0[r5] = r4     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            r1.invoke(r0)     // Catch:{ Throwable -> 0x0288, all -> 0x0283 }
            if (r11 == 0) goto L_0x027b
            r11.disconnect()
        L_0x027b:
            r12.close()
            r2.close()
            goto L_0x02ee
        L_0x0283:
            r0 = move-exception
            r17 = r2
            goto L_0x02f3
        L_0x0288:
            r0 = move-exception
            r17 = r2
            r2 = 5
            goto L_0x02b5
        L_0x028d:
            r0 = move-exception
            r17 = r2
            r3 = 0
            goto L_0x02f3
        L_0x0292:
            r0 = move-exception
            r17 = r2
            r2 = 5
            r3 = 0
            goto L_0x02b5
        L_0x0298:
            r0 = move-exception
            r2 = 5
            r3 = 0
            goto L_0x02b3
        L_0x029c:
            r0 = move-exception
            r3 = 0
            goto L_0x02a7
        L_0x029f:
            r0 = move-exception
            r2 = 5
            r3 = 0
            r8 = 1
            goto L_0x02b0
        L_0x02a4:
            r0 = move-exception
            r3 = 0
            r11 = 0
        L_0x02a7:
            r12 = 0
        L_0x02a8:
            r17 = 0
            goto L_0x02f3
        L_0x02ab:
            r0 = move-exception
            r2 = 5
            r3 = 0
            r8 = 1
            r11 = 0
        L_0x02b0:
            r12 = 0
        L_0x02b1:
            r13 = 0
        L_0x02b2:
            r14 = 0
        L_0x02b3:
            r17 = 0
        L_0x02b5:
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x02f2 }
            r4 = 0
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r4)     // Catch:{ all -> 0x02f2 }
            r2[r4] = r5     // Catch:{ all -> 0x02f2 }
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)     // Catch:{ all -> 0x02f2 }
            r4 = 1
            r2[r4] = r0     // Catch:{ all -> 0x02f2 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x02f2 }
            r4 = 2
            r2[r4] = r0     // Catch:{ all -> 0x02f2 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x02f2 }
            r4 = 3
            r2[r4] = r0     // Catch:{ all -> 0x02f2 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r14)     // Catch:{ all -> 0x02f2 }
            r4 = 4
            r2[r4] = r0     // Catch:{ all -> 0x02f2 }
            r1.invoke(r2)     // Catch:{ all -> 0x02f2 }
            if (r11 == 0) goto L_0x02e2
            r11.disconnect()
        L_0x02e2:
            if (r12 == 0) goto L_0x02e7
            r12.close()
        L_0x02e7:
            if (r17 == 0) goto L_0x02ec
            r17.close()
        L_0x02ec:
            if (r3 == 0) goto L_0x02f1
        L_0x02ee:
            r3.close()
        L_0x02f1:
            return
        L_0x02f2:
            r0 = move-exception
        L_0x02f3:
            if (r11 == 0) goto L_0x02f8
            r11.disconnect()
        L_0x02f8:
            if (r12 == 0) goto L_0x02fd
            r12.close()
        L_0x02fd:
            if (r17 == 0) goto L_0x0302
            r17.close()
        L_0x0302:
            if (r3 == 0) goto L_0x0307
            r3.close()
        L_0x0307:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTPersistModule.upload(java.lang.String, java.lang.String, java.util.HashMap, java.util.HashMap, com.facebook.react.bridge.ReadableArray, com.facebook.react.bridge.Callback):void");
    }

    static final URLConnection openConnection_aroundBody6(URL url) {
        return url.openConnection();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:110:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x009c, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x009f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a0, code lost:
        r6 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x019d, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01dc, code lost:
        r9.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01e1, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01e6, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01f2, code lost:
        r9.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01f7, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01fc, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0201, code lost:
        r12.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:110:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x009c A[Catch:{ Throwable -> 0x009f, all -> 0x009c }, ExcHandler: all (th java.lang.Throwable), Splitter:B:14:0x0063] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x01dc  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x01e1  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x01e6  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01f2  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01f7  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01fc  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0201  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void uploadToFDS(java.lang.String r18, java.lang.String r19, java.util.HashMap<java.lang.String, java.lang.Object> r20, java.util.HashMap<java.lang.String, java.lang.Object> r21, com.facebook.react.bridge.ReadableArray r22, com.facebook.react.bridge.Callback r23) throws java.lang.Exception {
        /*
            r1 = r23
            java.lang.String r0 = "\r\n"
            r4 = 5
            r7 = 1
            r8 = 0
            java.net.URL r9 = new java.net.URL     // Catch:{ Throwable -> 0x01ac, all -> 0x01a6 }
            r10 = r18
            r9.<init>(r10)     // Catch:{ Throwable -> 0x01ac, all -> 0x01a6 }
            com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor r10 = com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor.b()     // Catch:{ Throwable -> 0x01ac, all -> 0x01a6 }
            java.net.URLConnection r9 = r10.b(r9)     // Catch:{ Throwable -> 0x01ac, all -> 0x01a6 }
            java.net.HttpURLConnection r9 = (java.net.HttpURLConnection) r9     // Catch:{ Throwable -> 0x01ac, all -> 0x01a6 }
            r9.setUseCaches(r8)     // Catch:{ Throwable -> 0x01a3, all -> 0x01a1 }
            r9.setDoOutput(r7)     // Catch:{ Throwable -> 0x01a3, all -> 0x01a1 }
            r9.setDoInput(r7)     // Catch:{ Throwable -> 0x01a3, all -> 0x01a1 }
            r10 = r19
            r9.setRequestMethod(r10)     // Catch:{ Throwable -> 0x01a3, all -> 0x01a1 }
            if (r20 == 0) goto L_0x0058
            java.util.Set r10 = r20.entrySet()     // Catch:{ Throwable -> 0x01a3, all -> 0x01a1 }
            java.util.Iterator r10 = r10.iterator()     // Catch:{ Throwable -> 0x01a3, all -> 0x01a1 }
        L_0x0030:
            boolean r11 = r10.hasNext()     // Catch:{ Throwable -> 0x01a3, all -> 0x01a1 }
            if (r11 == 0) goto L_0x0058
            java.lang.Object r11 = r10.next()     // Catch:{ Throwable -> 0x01a3, all -> 0x01a1 }
            java.util.Map$Entry r11 = (java.util.Map.Entry) r11     // Catch:{ Throwable -> 0x01a3, all -> 0x01a1 }
            java.lang.Object r12 = r11.getValue()     // Catch:{ Throwable -> 0x01a3, all -> 0x01a1 }
            if (r12 == 0) goto L_0x0030
            java.lang.Object r11 = r11.getKey()     // Catch:{ Throwable -> 0x01a3, all -> 0x01a1 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ Throwable -> 0x01a3, all -> 0x01a1 }
            java.lang.String r11 = java.net.URLEncoder.encode(r11)     // Catch:{ Throwable -> 0x01a3, all -> 0x01a1 }
            java.lang.String r12 = r12.toString()     // Catch:{ Throwable -> 0x01a3, all -> 0x01a1 }
            java.lang.String r12 = java.net.URLEncoder.encode(r12)     // Catch:{ Throwable -> 0x01a3, all -> 0x01a1 }
            r9.setRequestProperty(r11, r12)     // Catch:{ Throwable -> 0x01a3, all -> 0x01a1 }
            goto L_0x0030
        L_0x0058:
            java.io.DataOutputStream r10 = new java.io.DataOutputStream     // Catch:{ Throwable -> 0x01a3, all -> 0x01a1 }
            java.io.OutputStream r11 = r9.getOutputStream()     // Catch:{ Throwable -> 0x01a3, all -> 0x01a1 }
            r10.<init>(r11)     // Catch:{ Throwable -> 0x01a3, all -> 0x01a1 }
            if (r21 == 0) goto L_0x00a3
            java.util.Set r11 = r21.entrySet()     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            java.util.Iterator r11 = r11.iterator()     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
        L_0x006b:
            boolean r12 = r11.hasNext()     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            if (r12 == 0) goto L_0x00a3
            java.lang.Object r12 = r11.next()     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            java.util.Map$Entry r12 = (java.util.Map.Entry) r12     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            java.lang.Object r13 = r12.getKey()     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            java.lang.String r13 = (java.lang.String) r13     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            java.net.URLEncoder.encode(r13)     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            java.lang.Object r13 = r12.getValue()     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            if (r13 != 0) goto L_0x0089
            java.lang.String r12 = ""
            goto L_0x0095
        L_0x0089:
            java.lang.Object r12 = r12.getValue()     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            java.lang.String r12 = r12.toString()     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            java.lang.String r12 = java.net.URLEncoder.encode(r12)     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
        L_0x0095:
            r10.writeBytes(r12)     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            r10.writeBytes(r0)     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            goto L_0x006b
        L_0x009c:
            r0 = move-exception
            goto L_0x01a9
        L_0x009f:
            r0 = move-exception
            r6 = 1
            goto L_0x01b0
        L_0x00a3:
            r0 = r22
            com.facebook.react.bridge.ReadableMap r0 = com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTUtils.a((com.facebook.react.bridge.ReadableArray) r0, (int) r8)     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            if (r0 == 0) goto L_0x00eb
            java.lang.String r11 = "filepath"
            java.lang.String r0 = com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTUtils.a((com.facebook.react.bridge.ReadableMap) r0, (java.lang.String) r11)     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            java.io.File r11 = new java.io.File     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            r11.<init>(r0)     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            long r12 = r11.length()     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            int r0 = (int) r12     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            byte[] r0 = new byte[r0]     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            java.io.FileInputStream r12 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            r12.<init>(r11)     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            r11 = 4096(0x1000, float:5.74E-42)
            r13 = 1048576(0x100000, float:1.469368E-39)
            int r11 = java.lang.Math.min(r11, r13)     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            int r13 = r0.length     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            int r11 = java.lang.Math.min(r11, r13)     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            int r13 = r12.read(r0, r8, r11)     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            int r14 = r0.length     // Catch:{ Throwable -> 0x009f, all -> 0x009c }
            int r15 = r13 + 0
        L_0x00d6:
            if (r13 <= 0) goto L_0x00e9
            r10.write(r0, r8, r11)     // Catch:{ Throwable -> 0x00e5, all -> 0x009c }
            int r13 = r12.read(r0, r8, r11)     // Catch:{ Throwable -> 0x00e5, all -> 0x009c }
            r6 = -1
            if (r13 != r6) goto L_0x00e3
            goto L_0x00d6
        L_0x00e3:
            int r15 = r15 + r13
            goto L_0x00d6
        L_0x00e5:
            r0 = move-exception
            r6 = 1
            goto L_0x019e
        L_0x00e9:
            r6 = 2
            goto L_0x00ee
        L_0x00eb:
            r6 = 1
            r14 = 0
            r15 = 0
        L_0x00ee:
            r10.flush()     // Catch:{ Throwable -> 0x019d, all -> 0x009c }
            java.io.BufferedInputStream r11 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x019d, all -> 0x009c }
            java.io.InputStream r0 = r9.getInputStream()     // Catch:{ Throwable -> 0x019d, all -> 0x009c }
            r11.<init>(r0)     // Catch:{ Throwable -> 0x019d, all -> 0x009c }
            java.io.BufferedReader r12 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x019b, all -> 0x0199 }
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x019b, all -> 0x0199 }
            r0.<init>(r11)     // Catch:{ Throwable -> 0x019b, all -> 0x0199 }
            r12.<init>(r0)     // Catch:{ Throwable -> 0x019b, all -> 0x0199 }
            com.facebook.react.bridge.WritableMap r0 = com.facebook.react.bridge.Arguments.createMap()     // Catch:{ Throwable -> 0x0197 }
            java.util.Map r13 = r9.getHeaderFields()     // Catch:{ Throwable -> 0x0197 }
            java.util.Set r13 = r13.entrySet()     // Catch:{ Throwable -> 0x0197 }
            java.util.Iterator r13 = r13.iterator()     // Catch:{ Throwable -> 0x0197 }
        L_0x0114:
            boolean r16 = r13.hasNext()     // Catch:{ Throwable -> 0x0197 }
            if (r16 == 0) goto L_0x013a
            java.lang.Object r16 = r13.next()     // Catch:{ Throwable -> 0x0197 }
            java.util.Map$Entry r16 = (java.util.Map.Entry) r16     // Catch:{ Throwable -> 0x0197 }
            java.lang.Object r17 = r16.getKey()     // Catch:{ Throwable -> 0x0197 }
            r2 = r17
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Throwable -> 0x0197 }
            java.lang.Object r16 = r16.getValue()     // Catch:{ Throwable -> 0x0197 }
            r3 = r16
            java.util.List r3 = (java.util.List) r3     // Catch:{ Throwable -> 0x0197 }
            java.lang.Object r3 = r3.get(r8)     // Catch:{ Throwable -> 0x0197 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ Throwable -> 0x0197 }
            r0.putString(r2, r3)     // Catch:{ Throwable -> 0x0197 }
            goto L_0x0114
        L_0x013a:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0197 }
            r2.<init>()     // Catch:{ Throwable -> 0x0197 }
        L_0x013f:
            java.lang.String r3 = r12.readLine()     // Catch:{ Throwable -> 0x0197 }
            if (r3 == 0) goto L_0x014e
            r2.append(r3)     // Catch:{ Throwable -> 0x0197 }
            java.lang.String r3 = "\n"
            r2.append(r3)     // Catch:{ Throwable -> 0x0197 }
            goto L_0x013f
        L_0x014e:
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x0197 }
            int r3 = r9.getResponseCode()     // Catch:{ Throwable -> 0x0197 }
            com.facebook.react.bridge.WritableMap r13 = com.facebook.react.bridge.Arguments.createMap()     // Catch:{ Throwable -> 0x0197 }
            java.lang.String r5 = "header"
            r13.putMap(r5, r0)     // Catch:{ Throwable -> 0x0197 }
            java.lang.String r0 = "data"
            r13.putString(r0, r2)     // Catch:{ Throwable -> 0x0197 }
            java.lang.String r0 = "status"
            r13.putInt(r0, r3)     // Catch:{ Throwable -> 0x0197 }
            java.lang.Object[] r0 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x0197 }
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r7)     // Catch:{ Throwable -> 0x0197 }
            r0[r8] = r2     // Catch:{ Throwable -> 0x0197 }
            r0[r7] = r13     // Catch:{ Throwable -> 0x0197 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r6)     // Catch:{ Throwable -> 0x0197 }
            r3 = 2
            r0[r3] = r2     // Catch:{ Throwable -> 0x0197 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r14)     // Catch:{ Throwable -> 0x0197 }
            r3 = 3
            r0[r3] = r2     // Catch:{ Throwable -> 0x0197 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r15)     // Catch:{ Throwable -> 0x0197 }
            r3 = 4
            r0[r3] = r2     // Catch:{ Throwable -> 0x0197 }
            r1.invoke(r0)     // Catch:{ Throwable -> 0x0197 }
            if (r9 == 0) goto L_0x0190
            r9.disconnect()
        L_0x0190:
            r10.close()
            r11.close()
            goto L_0x01eb
        L_0x0197:
            r0 = move-exception
            goto L_0x01b4
        L_0x0199:
            r0 = move-exception
            goto L_0x01aa
        L_0x019b:
            r0 = move-exception
            goto L_0x019f
        L_0x019d:
            r0 = move-exception
        L_0x019e:
            r11 = 0
        L_0x019f:
            r12 = 0
            goto L_0x01b4
        L_0x01a1:
            r0 = move-exception
            goto L_0x01a8
        L_0x01a3:
            r0 = move-exception
            r6 = 1
            goto L_0x01af
        L_0x01a6:
            r0 = move-exception
            r9 = 0
        L_0x01a8:
            r10 = 0
        L_0x01a9:
            r11 = 0
        L_0x01aa:
            r12 = 0
            goto L_0x01f0
        L_0x01ac:
            r0 = move-exception
            r6 = 1
            r9 = 0
        L_0x01af:
            r10 = 0
        L_0x01b0:
            r11 = 0
            r12 = 0
            r14 = 0
            r15 = 0
        L_0x01b4:
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch:{ all -> 0x01ef }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r8)     // Catch:{ all -> 0x01ef }
            r2[r8] = r3     // Catch:{ all -> 0x01ef }
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)     // Catch:{ all -> 0x01ef }
            r2[r7] = r0     // Catch:{ all -> 0x01ef }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x01ef }
            r3 = 2
            r2[r3] = r0     // Catch:{ all -> 0x01ef }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r14)     // Catch:{ all -> 0x01ef }
            r3 = 3
            r2[r3] = r0     // Catch:{ all -> 0x01ef }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r15)     // Catch:{ all -> 0x01ef }
            r3 = 4
            r2[r3] = r0     // Catch:{ all -> 0x01ef }
            r1.invoke(r2)     // Catch:{ all -> 0x01ef }
            if (r9 == 0) goto L_0x01df
            r9.disconnect()
        L_0x01df:
            if (r10 == 0) goto L_0x01e4
            r10.close()
        L_0x01e4:
            if (r11 == 0) goto L_0x01e9
            r11.close()
        L_0x01e9:
            if (r12 == 0) goto L_0x01ee
        L_0x01eb:
            r12.close()
        L_0x01ee:
            return
        L_0x01ef:
            r0 = move-exception
        L_0x01f0:
            if (r9 == 0) goto L_0x01f5
            r9.disconnect()
        L_0x01f5:
            if (r10 == 0) goto L_0x01fa
            r10.close()
        L_0x01fa:
            if (r11 == 0) goto L_0x01ff
            r11.close()
        L_0x01ff:
            if (r12 == 0) goto L_0x0204
            r12.close()
        L_0x0204:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTPersistModule.uploadToFDS(java.lang.String, java.lang.String, java.util.HashMap, java.util.HashMap, com.facebook.react.bridge.ReadableArray, com.facebook.react.bridge.Callback):void");
    }

    private boolean checkFileNameValidCallBack(String str, Callback callback) {
        if (TextUtils.isEmpty(str)) {
            callback.invoke(false, RnCallbackMapUtil.a("fileName is empty..."));
            return false;
        } else if (RnPluginFileUtils.c(getFilesPath().toString(), getPathName(str))) {
            return true;
        } else {
            callback.invoke(false, RnCallbackMapUtil.a(str + ", this fileName is not valid, cannot contains ..."));
            return false;
        }
    }

    private static String getMimeType(String str) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        if (fileExtensionFromUrl != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0061 A[SYNTHETIC, Splitter:B:30:0x0061] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0075 A[SYNTHETIC, Splitter:B:37:0x0075] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] uncompress(java.io.File r6) {
        /*
            r5 = this;
            r0 = 0
            byte[] r1 = new byte[r0]
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x000d }
            r2.<init>(r6)     // Catch:{ FileNotFoundException -> 0x000d }
            byte[] r6 = r5.getBytes(r2)     // Catch:{ FileNotFoundException -> 0x000d }
            goto L_0x0012
        L_0x000d:
            r6 = move-exception
            r6.printStackTrace()
            r6 = r1
        L_0x0012:
            byte[] r1 = new byte[r0]
            java.util.zip.Inflater r1 = new java.util.zip.Inflater
            r1.<init>()
            r1.reset()
            int r2 = r6.length
            r1.setInput(r6, r0, r2)
            r2 = 0
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0055 }
            int r4 = r6.length     // Catch:{ Exception -> 0x0055 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0055 }
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch:{ Exception -> 0x0050, all -> 0x004d }
        L_0x002b:
            boolean r4 = r1.finished()     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            if (r4 != 0) goto L_0x0039
            int r4 = r1.inflate(r2)     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            r3.write(r2, r0, r4)     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            goto L_0x002b
        L_0x0039:
            byte[] r0 = r3.toByteArray()     // Catch:{ Exception -> 0x0050, all -> 0x004d }
            r3.close()     // Catch:{ IOException -> 0x0041 }
            goto L_0x004b
        L_0x0041:
            r6 = move-exception
            java.lang.String r2 = "rn-plugin"
            java.lang.String r6 = r6.toString()
            com.xiaomi.smarthome.framework.log.LogUtil.b(r2, r6)
        L_0x004b:
            r6 = r0
            goto L_0x006f
        L_0x004d:
            r6 = move-exception
            r2 = r3
            goto L_0x0073
        L_0x0050:
            r0 = move-exception
            r2 = r3
            goto L_0x0056
        L_0x0053:
            r6 = move-exception
            goto L_0x0073
        L_0x0055:
            r0 = move-exception
        L_0x0056:
            java.lang.String r3 = "rn-plugin"
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0053 }
            com.xiaomi.smarthome.framework.log.LogUtil.b(r3, r0)     // Catch:{ all -> 0x0053 }
            if (r2 == 0) goto L_0x006f
            r2.close()     // Catch:{ IOException -> 0x0065 }
            goto L_0x006f
        L_0x0065:
            r0 = move-exception
            java.lang.String r2 = "rn-plugin"
            java.lang.String r0 = r0.toString()
            com.xiaomi.smarthome.framework.log.LogUtil.b(r2, r0)
        L_0x006f:
            r1.end()
            return r6
        L_0x0073:
            if (r2 == 0) goto L_0x0083
            r2.close()     // Catch:{ IOException -> 0x0079 }
            goto L_0x0083
        L_0x0079:
            r0 = move-exception
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "rn-plugin"
            com.xiaomi.smarthome.framework.log.LogUtil.b(r1, r0)
        L_0x0083:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTPersistModule.uncompress(java.io.File):byte[]");
    }

    private byte[] getBytes(FileInputStream fileInputStream) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1000);
            byte[] bArr = new byte[1000];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    byteArrayOutputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (FileNotFoundException e) {
            LogUtil.b(PluginRNActivity.TAG, e.toString());
            return null;
        } catch (IOException e2) {
            LogUtil.b(PluginRNActivity.TAG, e2.toString());
            return null;
        }
    }

    public void onCatalystInstanceDestroy() {
        RnPluginLog.a("MIOTPersistModule  destroy....");
        super.onCatalystInstanceDestroy();
    }
}
