package com.xiaomi.shop2.plugin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import com.xiaomi.smarthome.constants.AppConstants;
import java.lang.reflect.Constructor;
import java.util.HashMap;

public class PluginLayoutInflaterFactory implements LayoutInflater.Factory {
    private static final String TAG = "PluginLayoutInflaterFactory";
    private static final Class<?>[] mConstructorSignature = {Context.class, AttributeSet.class};
    private final HashMap<String, Constructor<? extends View>> mConstructorMap = new HashMap<>();

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        if (str.equals("view")) {
            str = attributeSet.getAttributeValue((String) null, AppConstants.x);
        }
        if (-1 == str.indexOf(46)) {
            return null;
        }
        return createView(str, context, attributeSet);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0054, code lost:
        r8 = "<unknown>";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0057, code lost:
        r8 = r1.getName();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0069, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006a, code lost:
        r0 = new android.view.InflateException(r8.getPositionDescription() + ": Class not found " + r6);
        r0.initCause(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008a, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x008b, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x008c, code lost:
        r0 = new android.view.InflateException(r8.getPositionDescription() + ": Class is not a View " + r6);
        r0.initCause(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00ac, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00ad, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00ae, code lost:
        r0 = new android.view.InflateException(r8.getPositionDescription() + ": Error inflating class " + r6);
        r0.initCause(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00ce, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0028, code lost:
        r6 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0029, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0069 A[ExcHandler: ClassNotFoundException (r7v3 'e' java.lang.ClassNotFoundException A[CUSTOM_DECLARE]), Splitter:B:1:0x0009] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008b A[ExcHandler: ClassCastException (r7v2 'e' java.lang.ClassCastException A[CUSTOM_DECLARE]), Splitter:B:1:0x0009] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00ad A[ExcHandler: NoSuchMethodException (r7v1 'e' java.lang.NoSuchMethodException A[CUSTOM_DECLARE]), Splitter:B:1:0x0009] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.view.View createView(java.lang.String r6, android.content.Context r7, android.util.AttributeSet r8) {
        /*
            r5 = this;
            java.util.HashMap<java.lang.String, java.lang.reflect.Constructor<? extends android.view.View>> r0 = r5.mConstructorMap
            java.lang.Object r0 = r0.get(r6)
            java.lang.reflect.Constructor r0 = (java.lang.reflect.Constructor) r0
            r1 = 0
            java.lang.ClassLoader r2 = r7.getClassLoader()     // Catch:{ NoSuchMethodException -> 0x00ad, ClassCastException -> 0x008b, ClassNotFoundException -> 0x0069, Exception -> 0x003e }
            if (r0 != 0) goto L_0x002b
            java.lang.Class r0 = r2.loadClass(r6)     // Catch:{ NoSuchMethodException -> 0x00ad, ClassCastException -> 0x008b, ClassNotFoundException -> 0x0069, Exception -> 0x003e }
            java.lang.Class<android.view.View> r2 = android.view.View.class
            java.lang.Class r0 = r0.asSubclass(r2)     // Catch:{ NoSuchMethodException -> 0x00ad, ClassCastException -> 0x008b, ClassNotFoundException -> 0x0069, Exception -> 0x003e }
            java.lang.Class<?>[] r1 = mConstructorSignature     // Catch:{ NoSuchMethodException -> 0x00ad, ClassCastException -> 0x008b, ClassNotFoundException -> 0x0069, Exception -> 0x0028 }
            java.lang.reflect.Constructor r1 = r0.getConstructor(r1)     // Catch:{ NoSuchMethodException -> 0x00ad, ClassCastException -> 0x008b, ClassNotFoundException -> 0x0069, Exception -> 0x0028 }
            java.util.HashMap<java.lang.String, java.lang.reflect.Constructor<? extends android.view.View>> r2 = r5.mConstructorMap     // Catch:{ NoSuchMethodException -> 0x00ad, ClassCastException -> 0x008b, ClassNotFoundException -> 0x0069, Exception -> 0x0028 }
            r2.put(r6, r1)     // Catch:{ NoSuchMethodException -> 0x00ad, ClassCastException -> 0x008b, ClassNotFoundException -> 0x0069, Exception -> 0x0028 }
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x002b
        L_0x0028:
            r6 = move-exception
            r1 = r0
            goto L_0x003f
        L_0x002b:
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ NoSuchMethodException -> 0x00ad, ClassCastException -> 0x008b, ClassNotFoundException -> 0x0069, Exception -> 0x003e }
            r3 = 0
            r2[r3] = r7     // Catch:{ NoSuchMethodException -> 0x00ad, ClassCastException -> 0x008b, ClassNotFoundException -> 0x0069, Exception -> 0x003e }
            r7 = 1
            r2[r7] = r8     // Catch:{ NoSuchMethodException -> 0x00ad, ClassCastException -> 0x008b, ClassNotFoundException -> 0x0069, Exception -> 0x003e }
            r0.setAccessible(r7)     // Catch:{ NoSuchMethodException -> 0x00ad, ClassCastException -> 0x008b, ClassNotFoundException -> 0x0069, Exception -> 0x003e }
            java.lang.Object r7 = r0.newInstance(r2)     // Catch:{ NoSuchMethodException -> 0x00ad, ClassCastException -> 0x008b, ClassNotFoundException -> 0x0069, Exception -> 0x003e }
            android.view.View r7 = (android.view.View) r7     // Catch:{ NoSuchMethodException -> 0x00ad, ClassCastException -> 0x008b, ClassNotFoundException -> 0x0069, Exception -> 0x003e }
            return r7
        L_0x003e:
            r6 = move-exception
        L_0x003f:
            android.view.InflateException r7 = new android.view.InflateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r8 = r8.getPositionDescription()
            r0.append(r8)
            java.lang.String r8 = ": Error inflating class "
            r0.append(r8)
            if (r1 != 0) goto L_0x0057
            java.lang.String r8 = "<unknown>"
            goto L_0x005b
        L_0x0057:
            java.lang.String r8 = r1.getName()
        L_0x005b:
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            r7.<init>(r8)
            r7.initCause(r6)
            throw r7
        L_0x0069:
            r7 = move-exception
            android.view.InflateException r0 = new android.view.InflateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r8 = r8.getPositionDescription()
            r1.append(r8)
            java.lang.String r8 = ": Class not found "
            r1.append(r8)
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            r0.<init>(r6)
            r0.initCause(r7)
            throw r0
        L_0x008b:
            r7 = move-exception
            android.view.InflateException r0 = new android.view.InflateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r8 = r8.getPositionDescription()
            r1.append(r8)
            java.lang.String r8 = ": Class is not a View "
            r1.append(r8)
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            r0.<init>(r6)
            r0.initCause(r7)
            throw r0
        L_0x00ad:
            r7 = move-exception
            android.view.InflateException r0 = new android.view.InflateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r8 = r8.getPositionDescription()
            r1.append(r8)
            java.lang.String r8 = ": Error inflating class "
            r1.append(r8)
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            r0.<init>(r6)
            r0.initCause(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.shop2.plugin.PluginLayoutInflaterFactory.createView(java.lang.String, android.content.Context, android.util.AttributeSet):android.view.View");
    }
}
