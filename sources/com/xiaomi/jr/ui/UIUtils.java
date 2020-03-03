package com.xiaomi.jr.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.common.util.UriUtil;
import com.xiaomi.jr.common.utils.Constants;
import com.xiaomi.jr.common.utils.Utils;

public class UIUtils {
    public static int a(int i, int i2, float f) {
        float f2 = 1.0f - f;
        return Color.rgb((int) ((((float) Color.red(i2)) * f) + (((float) Color.red(i)) * f2)), (int) ((((float) Color.green(i2)) * f) + (((float) Color.green(i)) * f2)), (int) ((((float) Color.blue(i2)) * f) + (((float) Color.blue(i)) * f2)));
    }

    public static Bitmap a(Bitmap bitmap, int i, int i2, int i3) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        RectF rectF = new RectF(0.0f, 0.0f, (float) i, (float) i2);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(-16777216, PorterDuff.Mode.CLEAR);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(-16777216);
        paint.setAntiAlias(true);
        float f = (float) i3;
        canvas.drawRoundRect(rectF, f, f, paint);
        Paint paint2 = new Paint();
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, (Rect) null, rectF, paint2);
        bitmap.recycle();
        return createBitmap;
    }

    public static int a(Context context, String str) {
        Uri b = b(context, str);
        if (!TextUtils.equals(b.getScheme(), UriUtil.QUALIFIED_RESOURCE_SCHEME)) {
            return 0;
        }
        return Utils.a(context, b);
    }

    private static Uri b(Context context, String str) {
        Uri parse = Uri.parse(str);
        if (!TextUtils.equals(parse.getScheme(), Constants.h) || !TextUtils.equals(parse.getAuthority(), "image")) {
            return parse;
        }
        Uri.Builder authority = parse.buildUpon().scheme(UriUtil.QUALIFIED_RESOURCE_SCHEME).authority(context.getPackageName());
        return authority.path("drawable" + parse.getPath()).build();
    }
}
