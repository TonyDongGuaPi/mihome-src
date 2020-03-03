package com.xiaomi.smarthome.library.common.imagecache;

import android.graphics.Bitmap;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.xiaomi.smarthome.library.common.util.ImageUtils;

public class RoundTransProcessor extends BasePostprocessor {
    public void process(Bitmap bitmap, Bitmap bitmap2) {
        ImageUtils.a(bitmap2, bitmap);
    }
}
