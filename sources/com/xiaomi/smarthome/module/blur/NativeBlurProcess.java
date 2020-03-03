package com.xiaomi.smarthome.module.blur;

import android.graphics.Bitmap;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class NativeBlurProcess implements BlurProcess {
    /* access modifiers changed from: private */
    public static native void functionToBlur(Bitmap bitmap, int i, int i2, int i3, int i4);

    public Bitmap a(Bitmap bitmap, float f) {
        int i = StackBlurManager.b;
        ArrayList arrayList = new ArrayList(i);
        ArrayList arrayList2 = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            Bitmap bitmap2 = bitmap;
            int i3 = (int) f;
            int i4 = i;
            int i5 = i2;
            arrayList.add(new NativeTask(bitmap2, i3, i4, i5, 1));
            arrayList2.add(new NativeTask(bitmap2, i3, i4, i5, 2));
        }
        try {
            StackBlurManager.c.invokeAll(arrayList);
            try {
                StackBlurManager.c.invokeAll(arrayList2);
                return bitmap;
            } catch (InterruptedException unused) {
                return bitmap;
            }
        } catch (InterruptedException unused2) {
            return bitmap;
        }
    }

    private static class NativeTask implements Callable<Void> {

        /* renamed from: a  reason: collision with root package name */
        private final Bitmap f20153a;
        private final int b;
        private final int c;
        private final int d;
        private final int e;

        public NativeTask(Bitmap bitmap, int i, int i2, int i3, int i4) {
            this.f20153a = bitmap;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
        }

        /* renamed from: a */
        public Void call() throws Exception {
            NativeBlurProcess.functionToBlur(this.f20153a, this.b, this.c, this.d, this.e);
            return null;
        }
    }
}
