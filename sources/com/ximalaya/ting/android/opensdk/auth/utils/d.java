package com.ximalaya.ting.android.opensdk.auth.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.DisplayMetrics;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.InputStream;
import java.util.Locale;

public class d {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1845a = "com.ximalaya.ting.android.opensdk.auth.utils.d";
    private static final String b = "drawable";
    private static final String c = "drawable-ldpi";
    private static final String d = "drawable-mdpi";
    private static final String e = "drawable-hdpi";
    private static final String f = "drawable-xhdpi";
    private static final String g = "drawable-xxhdpi";
    private static final String[] h = {g, f, e, d, c, b};

    public static Drawable a(Context context, String str) {
        return a(context, c(context, str), false);
    }

    private static Drawable b(Context context, String str) {
        return a(context, c(context, str), true);
    }

    private static Locale b() {
        Locale locale = Locale.getDefault();
        return (Locale.SIMPLIFIED_CHINESE.equals(locale) || Locale.TRADITIONAL_CHINESE.equals(locale)) ? locale : Locale.ENGLISH;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0090, code lost:
        if (java.lang.Math.abs(r4 - r2) <= java.lang.Math.abs(r4 - r5)) goto L_0x009d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00b0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String c(android.content.Context r8, java.lang.String r9) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            r1 = 0
            if (r0 == 0) goto L_0x000f
            java.lang.String r8 = f1845a
            java.lang.String r9 = "id is NOT correct!"
            com.ximalaya.ting.android.opensdk.auth.utils.Logger.c(r8, r9)
            return r1
        L_0x000f:
            android.content.res.Resources r0 = r8.getResources()
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()
            int r0 = r0.densityDpi
            r2 = 120(0x78, float:1.68E-43)
            if (r0 > r2) goto L_0x0020
            java.lang.String r0 = "drawable-ldpi"
            goto L_0x003d
        L_0x0020:
            r3 = 160(0xa0, float:2.24E-43)
            if (r0 <= r2) goto L_0x0029
            if (r0 > r3) goto L_0x0029
            java.lang.String r0 = "drawable-mdpi"
            goto L_0x003d
        L_0x0029:
            r2 = 240(0xf0, float:3.36E-43)
            if (r0 <= r3) goto L_0x0032
            if (r0 > r2) goto L_0x0032
            java.lang.String r0 = "drawable-hdpi"
            goto L_0x003d
        L_0x0032:
            if (r0 <= r2) goto L_0x003b
            r2 = 320(0x140, float:4.48E-43)
            if (r0 > r2) goto L_0x003b
            java.lang.String r0 = "drawable-xhdpi"
            goto L_0x003d
        L_0x003b:
            java.lang.String r0 = "drawable-xxhdpi"
        L_0x003d:
            java.lang.String r2 = f1845a
            java.lang.String r3 = "find Appropriate path..."
            com.ximalaya.ting.android.opensdk.auth.utils.Logger.a(r2, r3)
            r2 = 0
            r3 = -1
            r4 = -1
            r5 = -1
        L_0x0048:
            java.lang.String[] r6 = h
            int r6 = r6.length
            if (r2 >= r6) goto L_0x0080
            java.lang.String[] r6 = h
            r6 = r6[r2]
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0058
            r4 = r2
        L_0x0058:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String[] r7 = h
            r7 = r7[r2]
            r6.append(r7)
            java.lang.String r7 = "/"
            r6.append(r7)
            r6.append(r9)
            java.lang.String r6 = r6.toString()
            boolean r7 = d(r8, r6)
            if (r7 == 0) goto L_0x007d
            if (r4 != r2) goto L_0x0079
            return r6
        L_0x0079:
            if (r4 < 0) goto L_0x007c
            goto L_0x0081
        L_0x007c:
            r5 = r2
        L_0x007d:
            int r2 = r2 + 1
            goto L_0x0048
        L_0x0080:
            r2 = -1
        L_0x0081:
            if (r5 <= 0) goto L_0x0093
            if (r2 <= 0) goto L_0x0093
            int r8 = r4 - r2
            int r8 = java.lang.Math.abs(r8)
            int r4 = r4 - r5
            int r0 = java.lang.Math.abs(r4)
            if (r8 > r0) goto L_0x0097
            goto L_0x009d
        L_0x0093:
            if (r5 <= 0) goto L_0x0099
            if (r2 >= 0) goto L_0x0099
        L_0x0097:
            r3 = r5
            goto L_0x00a6
        L_0x0099:
            if (r5 >= 0) goto L_0x009f
            if (r2 <= 0) goto L_0x009f
        L_0x009d:
            r3 = r2
            goto L_0x00a6
        L_0x009f:
            java.lang.String r8 = f1845a
            java.lang.String r0 = "Not find the appropriate path for drawable"
            com.ximalaya.ting.android.opensdk.auth.utils.Logger.c(r8, r0)
        L_0x00a6:
            if (r3 >= 0) goto L_0x00b0
            java.lang.String r8 = f1845a
            java.lang.String r9 = "Not find the appropriate path for drawable"
            com.ximalaya.ting.android.opensdk.auth.utils.Logger.c(r8, r9)
            return r1
        L_0x00b0:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String[] r0 = h
            r0 = r0[r3]
            r8.append(r0)
            java.lang.String r0 = "/"
            r8.append(r0)
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.auth.utils.d.c(android.content.Context, java.lang.String):java.lang.String");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: android.graphics.drawable.BitmapDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: android.graphics.drawable.BitmapDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: android.graphics.drawable.BitmapDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: android.graphics.drawable.BitmapDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: android.graphics.drawable.BitmapDrawable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: android.graphics.drawable.BitmapDrawable} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0064 A[SYNTHETIC, Splitter:B:23:0x0064] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006d A[SYNTHETIC, Splitter:B:29:0x006d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.drawable.Drawable a(android.content.Context r8, java.lang.String r9, boolean r10) {
        /*
            android.content.res.AssetManager r0 = r8.getAssets()
            r1 = 0
            java.io.InputStream r9 = r0.open(r9)     // Catch:{ IOException -> 0x005d, all -> 0x005a }
            if (r9 == 0) goto L_0x004f
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeStream(r9)     // Catch:{ IOException -> 0x004d }
            android.content.res.Resources r0 = r8.getResources()     // Catch:{ IOException -> 0x004d }
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()     // Catch:{ IOException -> 0x004d }
            if (r10 == 0) goto L_0x003d
            android.content.res.Resources r10 = r8.getResources()     // Catch:{ IOException -> 0x004d }
            android.content.res.Configuration r10 = r10.getConfiguration()     // Catch:{ IOException -> 0x004d }
            android.content.res.Resources r3 = new android.content.res.Resources     // Catch:{ IOException -> 0x004d }
            android.content.res.AssetManager r8 = r8.getAssets()     // Catch:{ IOException -> 0x004d }
            r3.<init>(r8, r0, r10)     // Catch:{ IOException -> 0x004d }
            android.graphics.drawable.NinePatchDrawable r8 = new android.graphics.drawable.NinePatchDrawable     // Catch:{ IOException -> 0x004d }
            byte[] r5 = r4.getNinePatchChunk()     // Catch:{ IOException -> 0x004d }
            android.graphics.Rect r6 = new android.graphics.Rect     // Catch:{ IOException -> 0x004d }
            r10 = 0
            r6.<init>(r10, r10, r10, r10)     // Catch:{ IOException -> 0x004d }
            r7 = 0
            r2 = r8
            r2.<init>(r3, r4, r5, r6, r7)     // Catch:{ IOException -> 0x004d }
            r1 = r8
            goto L_0x004f
        L_0x003d:
            int r10 = r0.densityDpi     // Catch:{ IOException -> 0x004d }
            r4.setDensity(r10)     // Catch:{ IOException -> 0x004d }
            android.graphics.drawable.BitmapDrawable r10 = new android.graphics.drawable.BitmapDrawable     // Catch:{ IOException -> 0x004d }
            android.content.res.Resources r8 = r8.getResources()     // Catch:{ IOException -> 0x004d }
            r10.<init>(r8, r4)     // Catch:{ IOException -> 0x004d }
            r1 = r10
            goto L_0x004f
        L_0x004d:
            r8 = move-exception
            goto L_0x005f
        L_0x004f:
            if (r9 == 0) goto L_0x0067
            r9.close()     // Catch:{ IOException -> 0x0055 }
            goto L_0x0067
        L_0x0055:
            r8 = move-exception
            r8.printStackTrace()
            goto L_0x0067
        L_0x005a:
            r8 = move-exception
            r9 = r1
            goto L_0x006b
        L_0x005d:
            r8 = move-exception
            r9 = r1
        L_0x005f:
            r8.printStackTrace()     // Catch:{ all -> 0x006a }
            if (r9 == 0) goto L_0x0067
            r9.close()     // Catch:{ IOException -> 0x0055 }
        L_0x0067:
            android.graphics.drawable.Drawable r1 = (android.graphics.drawable.Drawable) r1
            return r1
        L_0x006a:
            r8 = move-exception
        L_0x006b:
            if (r9 == 0) goto L_0x0075
            r9.close()     // Catch:{ IOException -> 0x0071 }
            goto L_0x0075
        L_0x0071:
            r9 = move-exception
            r9.printStackTrace()
        L_0x0075:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.auth.utils.d.a(android.content.Context, java.lang.String, boolean):android.graphics.drawable.Drawable");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:17|16|19|20|(0)|26) */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003b, code lost:
        r5 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005b, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005c, code lost:
        r4.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0066, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0067, code lost:
        r4.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x003d */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0057 A[SYNTHETIC, Splitter:B:22:0x0057] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0062 A[SYNTHETIC, Splitter:B:28:0x0062] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean d(android.content.Context r4, java.lang.String r5) {
        /*
            r0 = 0
            if (r4 == 0) goto L_0x006b
            boolean r1 = android.text.TextUtils.isEmpty(r5)
            if (r1 != 0) goto L_0x006b
            android.content.res.AssetManager r4 = r4.getAssets()
            r1 = 0
            java.io.InputStream r4 = r4.open(r5)     // Catch:{ IOException -> 0x003d }
            java.lang.String r1 = f1845a     // Catch:{ IOException -> 0x0039, all -> 0x0036 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0039, all -> 0x0036 }
            java.lang.String r3 = "file ["
            r2.<init>(r3)     // Catch:{ IOException -> 0x0039, all -> 0x0036 }
            r2.append(r5)     // Catch:{ IOException -> 0x0039, all -> 0x0036 }
            java.lang.String r3 = "] existed"
            r2.append(r3)     // Catch:{ IOException -> 0x0039, all -> 0x0036 }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0039, all -> 0x0036 }
            com.ximalaya.ting.android.opensdk.auth.utils.Logger.a(r1, r2)     // Catch:{ IOException -> 0x0039, all -> 0x0036 }
            if (r4 == 0) goto L_0x0034
            r4.close()     // Catch:{ IOException -> 0x0030 }
            goto L_0x0034
        L_0x0030:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0034:
            r4 = 1
            return r4
        L_0x0036:
            r5 = move-exception
            r1 = r4
            goto L_0x0060
        L_0x0039:
            r1 = r4
            goto L_0x003d
        L_0x003b:
            r5 = move-exception
            goto L_0x0060
        L_0x003d:
            java.lang.String r4 = f1845a     // Catch:{ all -> 0x003b }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x003b }
            java.lang.String r3 = "file ["
            r2.<init>(r3)     // Catch:{ all -> 0x003b }
            r2.append(r5)     // Catch:{ all -> 0x003b }
            java.lang.String r5 = "] NOT existed"
            r2.append(r5)     // Catch:{ all -> 0x003b }
            java.lang.String r5 = r2.toString()     // Catch:{ all -> 0x003b }
            com.ximalaya.ting.android.opensdk.auth.utils.Logger.a(r4, r5)     // Catch:{ all -> 0x003b }
            if (r1 == 0) goto L_0x005f
            r1.close()     // Catch:{ IOException -> 0x005b }
            goto L_0x005f
        L_0x005b:
            r4 = move-exception
            r4.printStackTrace()
        L_0x005f:
            return r0
        L_0x0060:
            if (r1 == 0) goto L_0x006a
            r1.close()     // Catch:{ IOException -> 0x0066 }
            goto L_0x006a
        L_0x0066:
            r4 = move-exception
            r4.printStackTrace()
        L_0x006a:
            throw r5
        L_0x006b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.auth.utils.d.d(android.content.Context, java.lang.String):boolean");
    }

    private static String a(Context context) {
        int i = context.getResources().getDisplayMetrics().densityDpi;
        if (i <= 120) {
            return c;
        }
        if (i > 120 && i <= 160) {
            return d;
        }
        if (i <= 160 || i > 240) {
            return (i <= 240 || i > 320) ? g : f;
        }
        return e;
    }

    private static View a(Context context, String str, ViewGroup viewGroup) throws Exception {
        return LayoutInflater.from(context).inflate(context.getAssets().openXmlResourceParser(str), viewGroup);
    }

    private static Drawable e(Context context, String str) throws Exception {
        InputStream open = context.getAssets().open(str);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        TypedValue typedValue = new TypedValue();
        typedValue.density = displayMetrics.densityDpi;
        Drawable createFromResourceStream = Drawable.createFromResourceStream(context.getResources(), typedValue, open, str);
        open.close();
        return createFromResourceStream;
    }

    public static int a(Context context, int i) {
        double d2 = (double) (((float) i) * context.getResources().getDisplayMetrics().density);
        Double.isNaN(d2);
        return (int) (d2 + 0.5d);
    }

    public static ColorStateList a() {
        return new ColorStateList(new int[][]{new int[]{16842919}, new int[]{16842913}, new int[]{16842908}, StateSet.WILD_CARD}, new int[]{-6710887, -6710887, -6710887, -498622});
    }

    public static StateListDrawable a(Context context, String str, String str2) {
        Drawable drawable;
        Drawable drawable2;
        if (str.indexOf(".9") >= 0) {
            drawable = b(context, str);
        } else {
            drawable = a(context, str);
        }
        if (str2.indexOf(".9") >= 0) {
            drawable2 = b(context, str2);
        } else {
            drawable2 = a(context, str2);
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, drawable2);
        stateListDrawable.addState(new int[]{16842913}, drawable2);
        stateListDrawable.addState(new int[]{16842908}, drawable2);
        stateListDrawable.addState(StateSet.WILD_CARD, drawable);
        return stateListDrawable;
    }

    private static StateListDrawable a(Context context, String str, String str2, String str3) {
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3;
        if (str.indexOf(".9") >= 0) {
            drawable = b(context, str);
        } else {
            drawable = a(context, str);
        }
        if (str3.indexOf(".9") >= 0) {
            drawable2 = b(context, str3);
        } else {
            drawable2 = a(context, str3);
        }
        if (str2.indexOf(".9") >= 0) {
            drawable3 = b(context, str2);
        } else {
            drawable3 = a(context, str2);
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, drawable3);
        stateListDrawable.addState(new int[]{16842913}, drawable3);
        stateListDrawable.addState(new int[]{16842908}, drawable3);
        stateListDrawable.addState(new int[]{16842910}, drawable2);
        stateListDrawable.addState(StateSet.WILD_CARD, drawable);
        return stateListDrawable;
    }

    private static String a(String str, String str2, String str3) {
        Locale locale = Locale.getDefault();
        if (!Locale.SIMPLIFIED_CHINESE.equals(locale) && !Locale.TRADITIONAL_CHINESE.equals(locale)) {
            locale = Locale.ENGLISH;
        }
        if (Locale.SIMPLIFIED_CHINESE.equals(locale)) {
            return str2;
        }
        return Locale.TRADITIONAL_CHINESE.equals(locale) ? str3 : str;
    }
}
