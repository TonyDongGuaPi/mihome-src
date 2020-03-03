package com.xiaomi.pluginbase;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import java.lang.reflect.Constructor;
import java.util.HashMap;

public class LayoutInflaterFactory implements LayoutInflater.Factory2 {
    private final Object[] mConstructorArgs = new Object[2];
    private final Class<?>[] mConstructorSignature = {Context.class, AttributeSet.class};
    private final HashMap<String, Constructor<? extends View>> sConstructorMap = new HashMap<>();

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: java.lang.Class} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v8, resolved type: android.content.Context} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v12, resolved type: java.lang.Class} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v15, resolved type: java.lang.Class} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v16, resolved type: android.content.Context} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v17, resolved type: java.lang.Class} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v18, resolved type: android.content.Context} */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0049, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004f, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0051, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0053, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006d, code lost:
        r7 = e;
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0083, code lost:
        r8 = "<unknown>";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0086, code lost:
        r8 = r8.getName();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0098, code lost:
        r2 = new android.view.InflateException(r9.getPositionDescription() + ": Class not found " + r7);
        r2.initCause(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b8, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00b9, code lost:
        r2 = new android.view.InflateException(r9.getPositionDescription() + ": Class is not a View " + r7);
        r2.initCause(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00d9, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00da, code lost:
        r2 = new android.view.InflateException(r9.getPositionDescription() + ": Error inflating class " + r7);
        r2.initCause(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00fa, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00fb, code lost:
        r5.mConstructorArgs[0] = r6;
        r5.mConstructorArgs[1] = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0103, code lost:
        throw r7;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x004f A[Catch:{ NoSuchMethodException -> 0x0053, ClassCastException -> 0x0051, ClassNotFoundException -> 0x004f, Exception -> 0x006d, all -> 0x0049 }, ExcHandler: ClassNotFoundException (r8v11 'e' java.lang.ClassNotFoundException A[CUSTOM_DECLARE, Catch:{ NoSuchMethodException -> 0x0053, ClassCastException -> 0x0051, ClassNotFoundException -> 0x004f, Exception -> 0x006d, all -> 0x0049 }]), Splitter:B:8:0x002f] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0051 A[Catch:{ NoSuchMethodException -> 0x0053, ClassCastException -> 0x0051, ClassNotFoundException -> 0x004f, Exception -> 0x006d, all -> 0x0049 }, ExcHandler: ClassCastException (r8v10 'e' java.lang.ClassCastException A[CUSTOM_DECLARE, Catch:{ NoSuchMethodException -> 0x0053, ClassCastException -> 0x0051, ClassNotFoundException -> 0x004f, Exception -> 0x006d, all -> 0x0049 }]), Splitter:B:8:0x002f] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0053 A[Catch:{ NoSuchMethodException -> 0x0053, ClassCastException -> 0x0051, ClassNotFoundException -> 0x004f, Exception -> 0x006d, all -> 0x0049 }, ExcHandler: NoSuchMethodException (r8v9 'e' java.lang.NoSuchMethodException A[CUSTOM_DECLARE, Catch:{ NoSuchMethodException -> 0x0053, ClassCastException -> 0x0051, ClassNotFoundException -> 0x004f, Exception -> 0x006d, all -> 0x0049 }]), Splitter:B:8:0x002f] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0083 A[Catch:{ NoSuchMethodException -> 0x0053, ClassCastException -> 0x0051, ClassNotFoundException -> 0x004f, Exception -> 0x006d, all -> 0x0049 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0086 A[Catch:{ NoSuchMethodException -> 0x0053, ClassCastException -> 0x0051, ClassNotFoundException -> 0x004f, Exception -> 0x006d, all -> 0x0049 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View onCreateView(android.view.View r6, java.lang.String r7, android.content.Context r8, android.util.AttributeSet r9) {
        /*
            r5 = this;
            java.lang.String r6 = "view"
            boolean r6 = r7.equals(r6)
            r0 = 0
            if (r6 == 0) goto L_0x000f
            java.lang.String r6 = "class"
            java.lang.String r7 = r9.getAttributeValue(r0, r6)
        L_0x000f:
            r6 = -1
            r1 = 46
            int r1 = r7.indexOf(r1)
            if (r6 != r1) goto L_0x0019
            return r0
        L_0x0019:
            java.lang.Object[] r6 = r5.mConstructorArgs
            r1 = 0
            r6 = r6[r1]
            android.content.Context r6 = (android.content.Context) r6
            java.lang.Object[] r2 = r5.mConstructorArgs
            r2[r1] = r8
            java.util.HashMap<java.lang.String, java.lang.reflect.Constructor<? extends android.view.View>> r2 = r5.sConstructorMap
            java.lang.Object r2 = r2.get(r7)
            java.lang.reflect.Constructor r2 = (java.lang.reflect.Constructor) r2
            r3 = 1
            if (r2 != 0) goto L_0x0056
            java.lang.ClassLoader r8 = r8.getClassLoader()     // Catch:{ NoSuchMethodException -> 0x0053, ClassCastException -> 0x0051, ClassNotFoundException -> 0x004f, Exception -> 0x004c }
            java.lang.Class r8 = r8.loadClass(r7)     // Catch:{ NoSuchMethodException -> 0x0053, ClassCastException -> 0x0051, ClassNotFoundException -> 0x004f, Exception -> 0x004c }
            java.lang.Class<android.view.View> r2 = android.view.View.class
            java.lang.Class r8 = r8.asSubclass(r2)     // Catch:{ NoSuchMethodException -> 0x0053, ClassCastException -> 0x0051, ClassNotFoundException -> 0x004f, Exception -> 0x004c }
            java.lang.Class<?>[] r2 = r5.mConstructorSignature     // Catch:{ NoSuchMethodException -> 0x0053, ClassCastException -> 0x0051, ClassNotFoundException -> 0x004f, Exception -> 0x006d }
            java.lang.reflect.Constructor r2 = r8.getConstructor(r2)     // Catch:{ NoSuchMethodException -> 0x0053, ClassCastException -> 0x0051, ClassNotFoundException -> 0x004f, Exception -> 0x006d }
            java.util.HashMap<java.lang.String, java.lang.reflect.Constructor<? extends android.view.View>> r4 = r5.sConstructorMap     // Catch:{ NoSuchMethodException -> 0x0053, ClassCastException -> 0x0051, ClassNotFoundException -> 0x004f, Exception -> 0x006d }
            r4.put(r7, r2)     // Catch:{ NoSuchMethodException -> 0x0053, ClassCastException -> 0x0051, ClassNotFoundException -> 0x004f, Exception -> 0x006d }
            goto L_0x0057
        L_0x0049:
            r7 = move-exception
            goto L_0x00fb
        L_0x004c:
            r7 = move-exception
            r8 = r0
            goto L_0x006e
        L_0x004f:
            r8 = move-exception
            goto L_0x0098
        L_0x0051:
            r8 = move-exception
            goto L_0x00b9
        L_0x0053:
            r8 = move-exception
            goto L_0x00da
        L_0x0056:
            r8 = r0
        L_0x0057:
            java.lang.Object[] r4 = r5.mConstructorArgs     // Catch:{ NoSuchMethodException -> 0x0053, ClassCastException -> 0x0051, ClassNotFoundException -> 0x004f, Exception -> 0x006d }
            r4[r3] = r9     // Catch:{ NoSuchMethodException -> 0x0053, ClassCastException -> 0x0051, ClassNotFoundException -> 0x004f, Exception -> 0x006d }
            r2.setAccessible(r3)     // Catch:{ NoSuchMethodException -> 0x0053, ClassCastException -> 0x0051, ClassNotFoundException -> 0x004f, Exception -> 0x006d }
            java.lang.Object r2 = r2.newInstance(r4)     // Catch:{ NoSuchMethodException -> 0x0053, ClassCastException -> 0x0051, ClassNotFoundException -> 0x004f, Exception -> 0x006d }
            android.view.View r2 = (android.view.View) r2     // Catch:{ NoSuchMethodException -> 0x0053, ClassCastException -> 0x0051, ClassNotFoundException -> 0x004f, Exception -> 0x006d }
            java.lang.Object[] r7 = r5.mConstructorArgs
            r7[r1] = r6
            java.lang.Object[] r6 = r5.mConstructorArgs
            r6[r3] = r0
            return r2
        L_0x006d:
            r7 = move-exception
        L_0x006e:
            android.view.InflateException r2 = new android.view.InflateException     // Catch:{ all -> 0x0049 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0049 }
            r4.<init>()     // Catch:{ all -> 0x0049 }
            java.lang.String r9 = r9.getPositionDescription()     // Catch:{ all -> 0x0049 }
            r4.append(r9)     // Catch:{ all -> 0x0049 }
            java.lang.String r9 = ": Error inflating class "
            r4.append(r9)     // Catch:{ all -> 0x0049 }
            if (r8 != 0) goto L_0x0086
            java.lang.String r8 = "<unknown>"
            goto L_0x008a
        L_0x0086:
            java.lang.String r8 = r8.getName()     // Catch:{ all -> 0x0049 }
        L_0x008a:
            r4.append(r8)     // Catch:{ all -> 0x0049 }
            java.lang.String r8 = r4.toString()     // Catch:{ all -> 0x0049 }
            r2.<init>(r8)     // Catch:{ all -> 0x0049 }
            r2.initCause(r7)     // Catch:{ all -> 0x0049 }
            throw r2     // Catch:{ all -> 0x0049 }
        L_0x0098:
            android.view.InflateException r2 = new android.view.InflateException     // Catch:{ all -> 0x0049 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0049 }
            r4.<init>()     // Catch:{ all -> 0x0049 }
            java.lang.String r9 = r9.getPositionDescription()     // Catch:{ all -> 0x0049 }
            r4.append(r9)     // Catch:{ all -> 0x0049 }
            java.lang.String r9 = ": Class not found "
            r4.append(r9)     // Catch:{ all -> 0x0049 }
            r4.append(r7)     // Catch:{ all -> 0x0049 }
            java.lang.String r7 = r4.toString()     // Catch:{ all -> 0x0049 }
            r2.<init>(r7)     // Catch:{ all -> 0x0049 }
            r2.initCause(r8)     // Catch:{ all -> 0x0049 }
            throw r2     // Catch:{ all -> 0x0049 }
        L_0x00b9:
            android.view.InflateException r2 = new android.view.InflateException     // Catch:{ all -> 0x0049 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0049 }
            r4.<init>()     // Catch:{ all -> 0x0049 }
            java.lang.String r9 = r9.getPositionDescription()     // Catch:{ all -> 0x0049 }
            r4.append(r9)     // Catch:{ all -> 0x0049 }
            java.lang.String r9 = ": Class is not a View "
            r4.append(r9)     // Catch:{ all -> 0x0049 }
            r4.append(r7)     // Catch:{ all -> 0x0049 }
            java.lang.String r7 = r4.toString()     // Catch:{ all -> 0x0049 }
            r2.<init>(r7)     // Catch:{ all -> 0x0049 }
            r2.initCause(r8)     // Catch:{ all -> 0x0049 }
            throw r2     // Catch:{ all -> 0x0049 }
        L_0x00da:
            android.view.InflateException r2 = new android.view.InflateException     // Catch:{ all -> 0x0049 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0049 }
            r4.<init>()     // Catch:{ all -> 0x0049 }
            java.lang.String r9 = r9.getPositionDescription()     // Catch:{ all -> 0x0049 }
            r4.append(r9)     // Catch:{ all -> 0x0049 }
            java.lang.String r9 = ": Error inflating class "
            r4.append(r9)     // Catch:{ all -> 0x0049 }
            r4.append(r7)     // Catch:{ all -> 0x0049 }
            java.lang.String r7 = r4.toString()     // Catch:{ all -> 0x0049 }
            r2.<init>(r7)     // Catch:{ all -> 0x0049 }
            r2.initCause(r8)     // Catch:{ all -> 0x0049 }
            throw r2     // Catch:{ all -> 0x0049 }
        L_0x00fb:
            java.lang.Object[] r8 = r5.mConstructorArgs
            r8[r1] = r6
            java.lang.Object[] r6 = r5.mConstructorArgs
            r6[r3] = r0
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.pluginbase.LayoutInflaterFactory.onCreateView(android.view.View, java.lang.String, android.content.Context, android.util.AttributeSet):android.view.View");
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView((View) null, str, context, attributeSet);
    }
}
