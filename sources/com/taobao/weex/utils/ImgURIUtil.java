package com.taobao.weex.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.res.ResourcesCompat;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class ImgURIUtil {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-2470036482529886632L, "com/taobao/weex/utils/ImgURIUtil", 9);
        $jacocoData = a2;
        return a2;
    }

    public ImgURIUtil() {
        $jacocoInit()[0] = true;
    }

    public static Drawable getDrawableFromLoaclSrc(Context context, Uri uri) {
        boolean[] $jacocoInit = $jacocoInit();
        Resources resources = context.getResources();
        $jacocoInit[1] = true;
        List<String> pathSegments = uri.getPathSegments();
        $jacocoInit[2] = true;
        Drawable drawable = null;
        if (pathSegments.size() != 1) {
            $jacocoInit[3] = true;
            WXLogUtils.e("Local src format is invalid.");
            $jacocoInit[4] = true;
            return null;
        }
        int identifier = resources.getIdentifier(pathSegments.get(0), "drawable", context.getPackageName());
        $jacocoInit[5] = true;
        if (identifier == 0) {
            $jacocoInit[6] = true;
        } else {
            drawable = ResourcesCompat.getDrawable(resources, identifier, (Resources.Theme) null);
            $jacocoInit[7] = true;
        }
        $jacocoInit[8] = true;
        return drawable;
    }
}
