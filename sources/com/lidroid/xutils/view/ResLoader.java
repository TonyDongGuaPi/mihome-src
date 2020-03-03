package com.lidroid.xutils.view;

import android.content.Context;
import android.view.animation.AnimationUtils;

public class ResLoader {

    /* renamed from: a  reason: collision with root package name */
    private static /* synthetic */ int[] f6374a;

    /* JADX WARNING: Can't wrap try/catch for region: R(34:3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|(2:34|35)|36|38) */
    /* JADX WARNING: Can't wrap try/catch for region: R(35:3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|38) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0027 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0030 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0039 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0042 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x004b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0055 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x005f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x0069 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x0073 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x007d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:30:0x0087 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:32:0x0091 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x009b */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0015 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x001e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ int[] a() {
        /*
            int[] r0 = f6374a
            if (r0 == 0) goto L_0x0005
            return r0
        L_0x0005:
            com.lidroid.xutils.view.ResType[] r0 = com.lidroid.xutils.view.ResType.values()
            int r0 = r0.length
            int[] r0 = new int[r0]
            com.lidroid.xutils.view.ResType r1 = com.lidroid.xutils.view.ResType.Animation     // Catch:{ NoSuchFieldError -> 0x0015 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0015 }
            r2 = 1
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0015 }
        L_0x0015:
            com.lidroid.xutils.view.ResType r1 = com.lidroid.xutils.view.ResType.Boolean     // Catch:{ NoSuchFieldError -> 0x001e }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001e }
            r2 = 2
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001e }
        L_0x001e:
            com.lidroid.xutils.view.ResType r1 = com.lidroid.xutils.view.ResType.Color     // Catch:{ NoSuchFieldError -> 0x0027 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0027 }
            r2 = 3
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0027 }
        L_0x0027:
            com.lidroid.xutils.view.ResType r1 = com.lidroid.xutils.view.ResType.ColorStateList     // Catch:{ NoSuchFieldError -> 0x0030 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0030 }
            r2 = 4
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0030 }
        L_0x0030:
            com.lidroid.xutils.view.ResType r1 = com.lidroid.xutils.view.ResType.Dimension     // Catch:{ NoSuchFieldError -> 0x0039 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
            r2 = 5
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0039 }
        L_0x0039:
            com.lidroid.xutils.view.ResType r1 = com.lidroid.xutils.view.ResType.DimensionPixelOffset     // Catch:{ NoSuchFieldError -> 0x0042 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0042 }
            r2 = 6
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0042 }
        L_0x0042:
            com.lidroid.xutils.view.ResType r1 = com.lidroid.xutils.view.ResType.DimensionPixelSize     // Catch:{ NoSuchFieldError -> 0x004b }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
            r2 = 7
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
        L_0x004b:
            com.lidroid.xutils.view.ResType r1 = com.lidroid.xutils.view.ResType.Drawable     // Catch:{ NoSuchFieldError -> 0x0055 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0055 }
            r2 = 8
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0055 }
        L_0x0055:
            com.lidroid.xutils.view.ResType r1 = com.lidroid.xutils.view.ResType.IntArray     // Catch:{ NoSuchFieldError -> 0x005f }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005f }
            r2 = 10
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005f }
        L_0x005f:
            com.lidroid.xutils.view.ResType r1 = com.lidroid.xutils.view.ResType.Integer     // Catch:{ NoSuchFieldError -> 0x0069 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0069 }
            r2 = 9
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0069 }
        L_0x0069:
            com.lidroid.xutils.view.ResType r1 = com.lidroid.xutils.view.ResType.Movie     // Catch:{ NoSuchFieldError -> 0x0073 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0073 }
            r2 = 11
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0073 }
        L_0x0073:
            com.lidroid.xutils.view.ResType r1 = com.lidroid.xutils.view.ResType.String     // Catch:{ NoSuchFieldError -> 0x007d }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007d }
            r2 = 12
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007d }
        L_0x007d:
            com.lidroid.xutils.view.ResType r1 = com.lidroid.xutils.view.ResType.StringArray     // Catch:{ NoSuchFieldError -> 0x0087 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0087 }
            r2 = 13
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0087 }
        L_0x0087:
            com.lidroid.xutils.view.ResType r1 = com.lidroid.xutils.view.ResType.Text     // Catch:{ NoSuchFieldError -> 0x0091 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0091 }
            r2 = 14
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0091 }
        L_0x0091:
            com.lidroid.xutils.view.ResType r1 = com.lidroid.xutils.view.ResType.TextArray     // Catch:{ NoSuchFieldError -> 0x009b }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009b }
            r2 = 15
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009b }
        L_0x009b:
            com.lidroid.xutils.view.ResType r1 = com.lidroid.xutils.view.ResType.Xml     // Catch:{ NoSuchFieldError -> 0x00a5 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a5 }
            r2 = 16
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a5 }
        L_0x00a5:
            f6374a = r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.view.ResLoader.a():int[]");
    }

    public static Object a(ResType resType, Context context, int i) {
        if (context == null || i < 1) {
            return null;
        }
        switch (a()[resType.ordinal()]) {
            case 1:
                return AnimationUtils.loadAnimation(context, i);
            case 2:
                return Boolean.valueOf(context.getResources().getBoolean(i));
            case 3:
                return Integer.valueOf(context.getResources().getColor(i));
            case 4:
                return context.getResources().getColorStateList(i);
            case 5:
                return Float.valueOf(context.getResources().getDimension(i));
            case 6:
                return Integer.valueOf(context.getResources().getDimensionPixelOffset(i));
            case 7:
                return Integer.valueOf(context.getResources().getDimensionPixelSize(i));
            case 8:
                return context.getResources().getDrawable(i);
            case 9:
                return Integer.valueOf(context.getResources().getInteger(i));
            case 10:
                return context.getResources().getIntArray(i);
            case 11:
                return context.getResources().getMovie(i);
            case 12:
                return context.getResources().getString(i);
            case 13:
                return context.getResources().getStringArray(i);
            case 14:
                return context.getResources().getText(i);
            case 15:
                return context.getResources().getTextArray(i);
            case 16:
                return context.getResources().getXml(i);
            default:
                return null;
        }
    }
}
