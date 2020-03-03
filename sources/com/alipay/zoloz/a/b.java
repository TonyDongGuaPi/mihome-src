package com.alipay.zoloz.a;

import android.graphics.Rect;
import android.util.Log;
import com.alipay.streammedia.devicesengine.DevicesNativeEngineApi;
import com.alipay.streammedia.devicesengine.camera.FaceRectParams;
import com.alipay.streammedia.devicesengine.camera.ImageParams;
import com.alipay.streammedia.devicesengine.camera.ispAdjustResult;
import tv.danmaku.ijk.media.player.IjkLibLoader;

public class b {

    /* renamed from: a  reason: collision with root package name */
    private DevicesNativeEngineApi f1178a;

    static {
        try {
            DevicesNativeEngineApi.loadLibrariesOnce((IjkLibLoader) null);
        } catch (Throwable th) {
            Log.w("ToygerIsp", th);
        }
    }

    public void a(int i, int i2, int i3, int i4, int i5, float[] fArr, float[][][] fArr2) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            this.f1178a = new DevicesNativeEngineApi();
            ImageParams imageParams = new ImageParams();
            imageParams.orgWidth = i;
            imageParams.orgHeight = i2;
            imageParams.dispWidth = i3;
            imageParams.dispHeight = i4;
            this.f1178a.ispCreate(imageParams, fArr, fArr2, i5);
        } catch (Throwable th) {
            Log.w("ToygerIsp", th);
            this.f1178a = null;
        }
        Log.e("ToygerIsp", "ToygerIsp.init(): mEngineApi=" + this.f1178a + ", cost " + (System.currentTimeMillis() - currentTimeMillis) + " ms.");
    }

    public a a(byte[] bArr, short[] sArr, Rect rect, long j, int i) {
        a aVar;
        if (this.f1178a != null) {
            try {
                FaceRectParams faceRectParams = new FaceRectParams();
                faceRectParams.setX(rect.left);
                faceRectParams.setY(rect.top);
                faceRectParams.setWidth(rect.right - rect.left);
                faceRectParams.setHeight(rect.bottom - rect.top);
                ispAdjustResult ispAdjust = this.f1178a.ispAdjust(bArr, sArr, faceRectParams, j, (long) i);
                aVar = new a(ispAdjust.needSet, ispAdjust.exposureTime, ispAdjust.ISO);
            } catch (Throwable th) {
                Log.w("ToygerIsp", th);
            }
            Log.i("ToygerIsp", "ToygerIsp.adjustIsp(), ispResult=" + aVar);
            return aVar;
        }
        aVar = null;
        Log.i("ToygerIsp", "ToygerIsp.adjustIsp(), ispResult=" + aVar);
        return aVar;
    }

    public void a() {
        if (this.f1178a != null) {
            try {
                this.f1178a.ispDestory();
            } catch (Throwable th) {
                Log.w("ToygerIsp", th);
            }
        }
    }
}
