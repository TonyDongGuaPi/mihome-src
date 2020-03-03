package com.xiaomi.qrcode;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.ResultPointCallback;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

final class DecodeThread extends Thread {

    /* renamed from: a  reason: collision with root package name */
    public static final String f12960a = "barcode_bitmap";
    public static final String b = "barcode_scaled_factor";
    private final ScanBarcodeActivity c;
    private final Map<DecodeHintType, Object> d = new EnumMap(DecodeHintType.class);
    private Handler e;
    private final CountDownLatch f = new CountDownLatch(1);

    DecodeThread(ScanBarcodeActivity scanBarcodeActivity, Collection<BarcodeFormat> collection, Map<DecodeHintType, ?> map, String str, ResultPointCallback resultPointCallback) {
        this.c = scanBarcodeActivity;
        if (map != null) {
            this.d.putAll(map);
        }
        this.d.put(DecodeHintType.POSSIBLE_FORMATS, collection);
        if (str != null) {
            this.d.put(DecodeHintType.CHARACTER_SET, str);
        }
        this.d.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK, resultPointCallback);
        Log.i("DecodeThread", "Hints: " + this.d);
    }

    /* access modifiers changed from: package-private */
    public Handler a() {
        try {
            this.f.await();
        } catch (InterruptedException unused) {
        }
        return this.e;
    }

    public void run() {
        Looper.prepare();
        this.e = new DecodeHandler(this.c, this.d);
        this.f.countDown();
        Looper.loop();
    }
}
