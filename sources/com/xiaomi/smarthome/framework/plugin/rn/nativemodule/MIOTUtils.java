package com.xiaomi.smarthome.framework.plugin.rn.nativemodule;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Process;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.LruCache;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.xiaomi.smarthome.core.server.internal.plugin.util.ByteUtils;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MIOTUtils {
    private static int a(char c) {
        int i = c - 'W';
        return i < 0 ? c - '0' : i;
    }

    public static final String a(List<String> list, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        if (list != null) {
            for (String append : list) {
                stringBuffer.append(str);
                stringBuffer.append(append);
            }
        }
        if (stringBuffer.length() == 0) {
            return "";
        }
        return stringBuffer.substring(str.length());
    }

    public static String a(ReadableArray readableArray) {
        if (readableArray == null || readableArray.size() == 0) {
            return null;
        }
        if (readableArray.size() == 1) {
            return readableArray.getMap(0).getString("uri");
        }
        for (int i = 0; i < readableArray.size(); i++) {
            String string = readableArray.getMap(i).getString("uri");
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
        }
        return null;
    }

    public static String a(ReadableMap readableMap, String str) {
        return a(readableMap, str, "");
    }

    public static String a(ReadableMap readableMap, String str, String str2) {
        if (readableMap == null) {
            return str2;
        }
        try {
            return (!readableMap.hasKey(str) || readableMap.isNull(str)) ? str2 : readableMap.getString(str);
        } catch (Exception unused) {
            return str2;
        }
    }

    public static boolean b(ReadableMap readableMap, String str) {
        if (readableMap == null) {
            return false;
        }
        try {
            return readableMap.hasKey(str) && !readableMap.isNull(str) && readableMap.getBoolean(str);
        } catch (Exception unused) {
            return false;
        }
    }

    public static double c(ReadableMap readableMap, String str) {
        return a(readableMap, str, 0.0d);
    }

    public static double a(ReadableMap readableMap, String str, double d) {
        if (readableMap == null) {
            return d;
        }
        try {
            return (!readableMap.hasKey(str) || readableMap.isNull(str)) ? d : readableMap.getDouble(str);
        } catch (Exception unused) {
            return d;
        }
    }

    public static int d(ReadableMap readableMap, String str) {
        return a(readableMap, str, 0);
    }

    public static int a(ReadableMap readableMap, String str, int i) {
        if (readableMap == null) {
            return i;
        }
        try {
            return (!readableMap.hasKey(str) || readableMap.isNull(str)) ? i : readableMap.getInt(str);
        } catch (Exception unused) {
            return i;
        }
    }

    public static ReadableArray e(ReadableMap readableMap, String str) {
        if (readableMap == null) {
            return null;
        }
        try {
            if (!readableMap.hasKey(str) || readableMap.isNull(str)) {
                return null;
            }
            return readableMap.getArray(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static ReadableMap f(ReadableMap readableMap, String str) {
        if (readableMap == null) {
            return null;
        }
        try {
            if (!readableMap.hasKey(str) || readableMap.isNull(str)) {
                return null;
            }
            return readableMap.getMap(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static ReadableMap a(ReadableArray readableArray, int i) {
        if (readableArray == null) {
            return null;
        }
        try {
            if (readableArray.size() <= i || readableArray.isNull(i)) {
                return null;
            }
            return readableArray.getMap(i);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String a(ReadableMap readableMap) {
        if (readableMap == null) {
            return new JSONObject().toString();
        }
        try {
            JSONObject jSONObject = new JSONObject(readableMap.toString());
            if (jSONObject.has("NativeMap")) {
                return jSONObject.getString("NativeMap");
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            RnPluginLog.b(e.toString());
            return new JSONObject().toString();
        }
    }

    public static List<String> b(ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList();
        if (readableArray == null) {
            return arrayList;
        }
        ArrayList<Object> arrayList2 = readableArray.toArrayList();
        int size = arrayList2.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(arrayList2.get(i).toString());
        }
        return arrayList;
    }

    public static String b(ReadableMap readableMap, String str, String str2) {
        if (readableMap == null || TextUtils.isEmpty(str) || !readableMap.hasKey(str)) {
            return str2;
        }
        switch (readableMap.getType(str)) {
            case Boolean:
                return String.valueOf(readableMap.getBoolean(str));
            case Number:
                try {
                    return String.valueOf(readableMap.getInt(str));
                } catch (Exception unused) {
                    return String.valueOf(readableMap.getDouble(str));
                }
            case String:
                return a(readableMap, str);
            case Map:
                return f(readableMap, str).toString();
            case Array:
                return e(readableMap, str).toString();
            default:
                return str2;
        }
    }

    public static Bitmap a(RecyclerView recyclerView) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            return null;
        }
        int itemCount = adapter.getItemCount();
        Paint paint = new Paint();
        LruCache lruCache = new LruCache(((int) (Runtime.getRuntime().maxMemory() / 1024)) / 8);
        int i = 0;
        for (int i2 = 0; i2 < itemCount; i2++) {
            RecyclerView.ViewHolder createViewHolder = adapter.createViewHolder(recyclerView, adapter.getItemViewType(i2));
            adapter.onBindViewHolder(createViewHolder, i2);
            createViewHolder.itemView.measure(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
            createViewHolder.itemView.layout(0, 0, createViewHolder.itemView.getMeasuredWidth(), createViewHolder.itemView.getMeasuredHeight());
            createViewHolder.itemView.setDrawingCacheEnabled(true);
            createViewHolder.itemView.buildDrawingCache();
            Bitmap drawingCache = createViewHolder.itemView.getDrawingCache();
            if (drawingCache != null) {
                lruCache.put(String.valueOf(i2), drawingCache);
            }
            i += createViewHolder.itemView.getMeasuredHeight();
        }
        Bitmap createBitmap = Bitmap.createBitmap(recyclerView.getMeasuredWidth(), i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Drawable background = recyclerView.getBackground();
        if (background instanceof ColorDrawable) {
            canvas.drawColor(((ColorDrawable) background).getColor());
        }
        int i3 = 0;
        for (int i4 = 0; i4 < itemCount; i4++) {
            Bitmap bitmap = (Bitmap) lruCache.get(String.valueOf(i4));
            canvas.drawBitmap(bitmap, 0.0f, (float) i3, paint);
            i3 += bitmap.getHeight();
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap a(View view) {
        if (view instanceof ScrollView) {
            return a((ScrollView) view);
        }
        if (view instanceof ListView) {
            return a((ListView) view);
        }
        if (view instanceof RecyclerView) {
            return a((RecyclerView) view);
        }
        return null;
    }

    public static Bitmap a(ScrollView scrollView) {
        int i = 0;
        for (int i2 = 0; i2 < scrollView.getChildCount(); i2++) {
            i += scrollView.getChildAt(i2).getHeight();
            scrollView.getChildAt(i2).setBackgroundColor(-1);
        }
        Bitmap createBitmap = Bitmap.createBitmap(scrollView.getWidth(), i, Bitmap.Config.RGB_565);
        scrollView.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    public static Bitmap a(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        int count = adapter.getCount();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < count; i2++) {
            View view = adapter.getView(i2, (View) null, listView);
            view.measure(View.MeasureSpec.makeMeasureSpec(listView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
            view.setDrawingCacheEnabled(true);
            view.buildDrawingCache();
            arrayList.add(view.getDrawingCache());
            i += view.getMeasuredHeight();
        }
        Bitmap createBitmap = Bitmap.createBitmap(listView.getMeasuredWidth(), i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        int i3 = 0;
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            Bitmap bitmap = (Bitmap) arrayList.get(i4);
            canvas.drawBitmap(bitmap, 0.0f, (float) i3, paint);
            i3 += bitmap.getHeight();
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static void a(JSONObject jSONObject, WritableMap writableMap) {
        if (jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Object opt = jSONObject.opt(next);
                if (opt instanceof JSONObject) {
                    WritableMap createMap = Arguments.createMap();
                    a((JSONObject) opt, createMap);
                    writableMap.putMap(next, createMap);
                } else if (opt instanceof JSONArray) {
                    WritableArray createArray = Arguments.createArray();
                    a((JSONArray) opt, createArray);
                    writableMap.putArray(next, createArray);
                } else if (opt instanceof Boolean) {
                    writableMap.putBoolean(next, ((Boolean) opt).booleanValue());
                } else if (opt instanceof Integer) {
                    writableMap.putInt(next, ((Integer) opt).intValue());
                } else if (opt instanceof String) {
                    writableMap.putString(next, (String) opt);
                } else if (opt instanceof Number) {
                    writableMap.putDouble(next, ((Number) opt).doubleValue());
                }
            }
        }
    }

    public static void a(JSONArray jSONArray, WritableArray writableArray) {
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                Object opt = jSONArray.opt(i);
                if (opt instanceof JSONObject) {
                    WritableMap createMap = Arguments.createMap();
                    a((JSONObject) opt, createMap);
                    writableArray.pushMap(createMap);
                } else if (opt instanceof JSONArray) {
                    WritableArray createArray = Arguments.createArray();
                    a((JSONArray) opt, createArray);
                    writableArray.pushArray(createArray);
                } else if (opt instanceof Boolean) {
                    writableArray.pushBoolean(((Boolean) opt).booleanValue());
                } else if (opt instanceof Integer) {
                    writableArray.pushInt(((Integer) opt).intValue());
                } else if (opt instanceof String) {
                    writableArray.pushString((String) opt);
                } else if (opt instanceof Number) {
                    writableArray.pushDouble(((Number) opt).doubleValue());
                }
            }
        }
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return ByteUtils.c(bArr);
    }

    public static byte[] a(String str) {
        if (str == null) {
            return new byte[0];
        }
        int length = str.length() >> 1;
        byte[] bArr = new byte[length];
        char[] charArray = str.toLowerCase().toCharArray();
        for (int i = 0; i < length; i++) {
            int i2 = i << 1;
            bArr[i] = (byte) (a(charArray[i2 + 1]) | (a(charArray[i2]) << 4));
        }
        return bArr;
    }

    public static int a(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001f A[SYNTHETIC, Splitter:B:11:0x001f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.graphics.Bitmap r2, int r3) {
        /*
            r0 = 0
            if (r2 == 0) goto L_0x002b
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x001b }
            r1.<init>()     // Catch:{ all -> 0x001b }
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ all -> 0x0019 }
            r2.compress(r0, r3, r1)     // Catch:{ all -> 0x0019 }
            byte[] r2 = r1.toByteArray()     // Catch:{ all -> 0x0019 }
            r3 = 2
            java.lang.String r0 = android.util.Base64.encodeToString(r2, r3)     // Catch:{ all -> 0x0019 }
            r2 = r0
            r0 = r1
            goto L_0x002c
        L_0x0019:
            r2 = move-exception
            goto L_0x001d
        L_0x001b:
            r2 = move-exception
            r1 = r0
        L_0x001d:
            if (r1 == 0) goto L_0x002a
            r1.flush()     // Catch:{ IOException -> 0x0026 }
            r1.close()     // Catch:{ IOException -> 0x0026 }
            goto L_0x002a
        L_0x0026:
            r3 = move-exception
            r3.printStackTrace()
        L_0x002a:
            throw r2
        L_0x002b:
            r2 = r0
        L_0x002c:
            if (r0 == 0) goto L_0x0039
            r0.flush()     // Catch:{ IOException -> 0x0035 }
            r0.close()     // Catch:{ IOException -> 0x0035 }
            goto L_0x0039
        L_0x0035:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0039:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTUtils.a(android.graphics.Bitmap, int):java.lang.String");
    }

    public static String a(Context context) {
        if (context == null) {
            return "";
        }
        int myPid = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return "";
        }
        for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
            if (next.pid == myPid) {
                return next.processName;
            }
        }
        return "";
    }
}
