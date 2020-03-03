package com.alipay.zoloz.toyger.extservice;

import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import com.alipay.mobile.security.bio.service.BioService;
import com.alipay.mobile.security.bio.service.BioServiceManager;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.zoloz.a.b;
import com.alipay.zoloz.hardware.a.a;
import com.alipay.zoloz.toyger.algorithm.TGDepthFrame;
import com.alipay.zoloz.toyger.algorithm.TGFaceAttr;
import com.alipay.zoloz.toyger.algorithm.TGFaceState;
import com.alipay.zoloz.toyger.algorithm.TGFrame;
import com.alipay.zoloz.toyger.face.FaceBlobManager;
import java.util.concurrent.TimeUnit;

public class ToygerIspService extends BioService {
    private static final String TAG = "ToygerIsp";
    private final long ISP_DELAY = TimeUnit.SECONDS.toMillis(3);
    private long begin = 0;
    private boolean ispRunning = false;
    private boolean mInitialized = false;
    private a mIspService;
    private HandlerThread mIspThread;
    private Handler mIspThreadHandler;
    private b mToygerIsp;

    public void onCreate(BioServiceManager bioServiceManager) {
        super.onCreate(bioServiceManager);
    }

    public void init(int i, int i2, int i3, int i4, int i5) {
        this.mIspService = (a) this.mBioServiceManager.getBioService(a.class);
        if (this.mIspService != null) {
            float[] b = this.mIspService.b();
            float[][][] c = this.mIspService.c();
            this.mIspThread = new HandlerThread("adjustIsp");
            this.mIspThread.start();
            this.mIspThreadHandler = new Handler(this.mIspThread.getLooper());
            this.mToygerIsp = new b();
            this.mToygerIsp.a(i, i2, i3, i4, i5, b, c);
        }
        this.mInitialized = true;
        this.begin = System.currentTimeMillis();
    }

    private boolean validateRegion(RectF rectF) {
        if (rectF != null) {
            return rectF.left >= 0.0f && rectF.top >= 0.0f && rectF.right <= 1.0f && rectF.bottom <= 1.0f;
        }
        return true;
    }

    public void adjustIsp(TGFrame tGFrame, TGDepthFrame tGDepthFrame, TGFaceState tGFaceState, TGFaceAttr tGFaceAttr) {
        RectF rectF;
        if (this.mIspService != null && System.currentTimeMillis() - this.begin >= this.ISP_DELAY) {
            if (tGFaceState.hasFace) {
                rectF = tGFaceAttr.faceRegion;
            } else {
                float f = 60.0f / ((float) tGFrame.width);
                float f2 = 60.0f / ((float) tGFrame.height);
                rectF = new RectF(0.5f - f, 0.5f - f2, f + 0.5f, f2 + 0.5f);
            }
            if (validateRegion(rectF)) {
                Rect convertFaceRegion = FaceBlobManager.convertFaceRegion(rectF, tGFrame.width, tGFrame.height, tGFrame.rotation, false);
                synchronized (this) {
                    if (this.ispRunning) {
                        BioLog.e(TAG, "adjustIsp begin: but ispRunning==true, give up.");
                    } else {
                        this.ispRunning = true;
                        this.mIspThreadHandler.post(new a(this, convertFaceRegion, tGFrame, tGDepthFrame));
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void adjustIsp(Rect rect, byte[] bArr, short[] sArr) {
        int a2 = this.mIspService.a();
        BioLog.w(TAG, "adjustIsp begin: getAEMode()=" + a2);
        if (a2 != 0) {
            this.mIspService.a(0);
        }
        try {
            com.alipay.zoloz.a.a a3 = this.mToygerIsp.a(bArr, sArr, rect, this.mIspService.d(), this.mIspService.e());
            if (a3 != null) {
                BioLog.d(TAG, "result.needSet=" + a3.a() + ", result.exposureTime=" + a3.b() + ", result.ISO=" + a3.c());
                if (a3.a()) {
                    this.mIspService.a(a3.b());
                    this.mIspService.b(a3.c());
                }
            }
        } catch (Throwable th) {
            BioLog.w(TAG, "adjustIsp end.");
            throw th;
        }
        BioLog.w(TAG, "adjustIsp end.");
        synchronized (this) {
            this.ispRunning = false;
        }
    }

    public void onDestroy() {
        if (this.mToygerIsp != null) {
            this.mToygerIsp.a();
        }
        if (this.mIspThread != null) {
            if (Build.VERSION.SDK_INT >= 18) {
                this.mIspThread.quitSafely();
            } else {
                this.mIspThread.quit();
            }
            this.mIspThread = null;
            this.mIspThreadHandler = null;
        }
        this.ispRunning = false;
        this.mIspService = null;
        super.onDestroy();
    }

    public boolean isInitialized() {
        return this.mInitialized;
    }
}
