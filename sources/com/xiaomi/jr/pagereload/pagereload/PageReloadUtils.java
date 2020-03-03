package com.xiaomi.jr.pagereload.pagereload;

import android.text.TextUtils;
import com.xiaomi.jr.common.utils.Algorithms;
import com.xiaomi.jr.common.utils.Utils;
import com.xiaomi.jr.pagereload.pagereload.IPageReloader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class PageReloadUtils {

    /* renamed from: a  reason: collision with root package name */
    private static List<WeakReference<IPageReloader>> f10992a = new ArrayList();

    public static void a(IPageReloader iPageReloader) {
        Algorithms.a(f10992a, iPageReloader);
    }

    public static void b(IPageReloader iPageReloader) {
        Algorithms.b(f10992a, iPageReloader);
    }

    public static void a(IPageReloader iPageReloader, boolean z, String str, int i) {
        IPageReloader.ReloadOnResumeType reloadOnResumeType;
        Utils.a();
        int size = f10992a.size() - 1;
        while (size >= 0) {
            IPageReloader iPageReloader2 = (IPageReloader) f10992a.get(size).get();
            if (iPageReloader2 != null) {
                if (i == -1 || i == iPageReloader2.d()) {
                    if (!z) {
                        reloadOnResumeType = IPageReloader.ReloadOnResumeType.NOT_RELOAD;
                    } else if (iPageReloader2 == iPageReloader) {
                        reloadOnResumeType = IPageReloader.ReloadOnResumeType.DELAY_RELOAD;
                    } else {
                        reloadOnResumeType = IPageReloader.ReloadOnResumeType.RELOAD;
                    }
                    iPageReloader2.a(reloadOnResumeType);
                    String b = iPageReloader2.b();
                    if (!TextUtils.isEmpty(b) && b.equals(str)) {
                        return;
                    }
                }
                size--;
            } else {
                return;
            }
        }
    }
}
