package com.xiaomi.miot.support.monitor;

import android.graphics.Bitmap;
import java.util.ArrayList;

public class MonitorJNI {
    static native void getAllWeakBitmaps(ArrayList<Bitmap> arrayList);

    static native void startHookBitmap();
}
