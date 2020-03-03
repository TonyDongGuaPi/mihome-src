package com.alipay.zoloz.toyger.face;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.alipay.zoloz.toyger.ToygerService;
import com.alipay.zoloz.toyger.algorithm.TGDepthFrame;
import com.alipay.zoloz.toyger.algorithm.TGFrame;
import com.alipay.zoloz.toyger.algorithm.Toyger;
import com.alipay.zoloz.toyger.algorithm.ToygerConfig;
import com.alipay.zoloz.toyger.blob.BitmapHelper;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ToygerFaceService extends ToygerService<ToygerFaceCallback, ToygerFaceState, ToygerFaceAttr, ToygerFaceInfo, ToygerFaceAlgorithmConfig> {
    public static final String KEY_TOYGER_DEPTH_FRAME = "toyger_depth_frame";
    public static final String KEY_TOYGER_FRAME = "toyger_frame";
    private static final int QUEUE_LENGTH = 1;
    /* access modifiers changed from: private */
    public static String licenses;
    /* access modifiers changed from: private */
    public static byte[] model;
    private FaceBlobManager blobManager;
    /* access modifiers changed from: private */
    public boolean isMirror = false;
    /* access modifiers changed from: private */
    public final ReentrantLock mColorFrameQueueLock = new ReentrantLock();
    /* access modifiers changed from: private */
    public final BlockingQueue<TGDepthFrame> mDepthFrameQueue = new LinkedBlockingDeque(1);
    /* access modifiers changed from: private */
    public final ReentrantLock mDepthFrameQueueLock = new ReentrantLock();
    /* access modifiers changed from: private */
    public final FrameProcessor mFrameProcessor = new FrameProcessor();
    /* access modifiers changed from: private */
    public final BlockingQueue<List<TGFrame>> mFrameQueue = new LinkedBlockingDeque(1);
    private HandlerThread mProcessThread;
    private Handler mProcessThreadHandler;
    /* access modifiers changed from: private */
    public final AtomicBoolean mRunning = new AtomicBoolean(false);

    private static boolean load(Context context) {
        try {
            InputStream open = context.getAssets().open(ToygerService.ASSET_FACE);
            model = new byte[open.available()];
            open.read(model);
            if (model != null) {
                return true;
            }
            Log.e(ToygerService.TAG, "fail to read model file");
            return false;
        } catch (IOException e) {
            Log.w(ToygerService.TAG, e);
            return false;
        }
    }

    public static boolean preLoad(Context context) {
        if (model != null) {
            Log.i(ToygerService.TAG, "ToygerFaceService.preLoad(): model is already loaded");
            return true;
        }
        boolean load = load(context);
        Log.i(ToygerService.TAG, "ToygerFaceService.preLoad() : bRet=" + load);
        return load;
    }

    public boolean init(Context context, ToygerFaceCallback toygerFaceCallback, String str, String str2, Map<String, Object> map) {
        this.mToygerCallback = toygerFaceCallback;
        if (model == null) {
            load(context);
        }
        if (this.mProcessThread == null) {
            this.mProcessThread = new HandlerThread("ToygerProcessQueue");
            this.mProcessThread.start();
        }
        if (this.mProcessThreadHandler == null) {
            this.mProcessThreadHandler = new Handler(this.mProcessThread.getLooper());
        }
        String str3 = null;
        if (map != null && map.containsKey(ToygerService.KEY_PUBLIC_KEY)) {
            str3 = map.get(ToygerService.KEY_PUBLIC_KEY).toString();
        }
        ToygerFaceBlobConfig from = ToygerFaceBlobConfig.from(str2, str3);
        if (from == null) {
            Log.e(ToygerService.TAG, "fail to parse upload config");
            return false;
        }
        if (map != null && map.containsKey(ToygerService.KEY_IS_MIRROR)) {
            this.isMirror = Boolean.parseBoolean(map.get(ToygerService.KEY_IS_MIRROR).toString());
        }
        try {
            Constructor<?> declaredConstructor = Class.forName(((map == null || !map.containsKey("meta_serializer")) ? 2 : Integer.parseInt(map.get("meta_serializer").toString())) != 2 ? "com.alipay.zoloz.toyger.face.FaceBlobManagerJson" : "com.alipay.zoloz.toyger.face.FaceBlobManagerPb").getDeclaredConstructor(new Class[]{ToygerFaceBlobConfig.class});
            declaredConstructor.setAccessible(true);
            this.blobManager = (FaceBlobManager) declaredConstructor.newInstance(new Object[]{from});
            ToygerFaceAlgorithmConfig from2 = ToygerFaceAlgorithmConfig.from(str);
            if (from2 == null) {
                Log.e(ToygerService.TAG, "fail to parse algorithm config, load default config");
                from2 = new ToygerFaceAlgorithmConfig();
            }
            final ToygerConfig toygerConfig = from2.toToygerConfig();
            String str4 = "";
            if (from.collection != null) {
                String str5 = str4;
                for (int i = 0; i < from.collection.size(); i++) {
                    if (str5.length() > 0) {
                        str5 = str5.concat("#").concat(from.collection.get(i));
                    } else {
                        str5 = from.collection.get(i);
                    }
                }
                str4 = str5;
            }
            toygerConfig.livenessConfig.collection = str4;
            String str6 = "";
            for (int i2 = 0; i2 < from2.liveness_combination.size(); i2++) {
                if (str6.length() > 0) {
                    str6 = str6.concat("#").concat(from2.liveness_combination.get(i2));
                } else {
                    str6 = from2.liveness_combination.get(i2);
                }
            }
            toygerConfig.livenessConfig.livenessCombinations = str6;
            Log.d(ToygerService.TAG, "toygerConfig=" + toygerConfig);
            final ToygerFaceCallback toygerFaceCallback2 = (ToygerFaceCallback) this.mToygerCallback;
            final Context context2 = context;
            this.mProcessThreadHandler.post(new Runnable() {
                public void run() {
                    HashMap hashMap = new HashMap();
                    hashMap.put("StateSignature", ToygerFaceState.class.getName().replace(".", "/"));
                    hashMap.put("AttrSignature", ToygerFaceAttr.class.getName().replace(".", "/"));
                    hashMap.put("FrameSignature", TGFrame.class.getName().replace(".", "/"));
                    boolean init = Toyger.init(context2, ToygerFaceService.model, ToygerFaceService.licenses, context2.getPackageName(), hashMap);
                    Log.e(ToygerService.TAG, "Toyger.init() return : initResult=" + init);
                    byte[] unused = ToygerFaceService.model = null;
                    if (init) {
                        Toyger.config(this, toygerConfig);
                        ToygerFaceService.this.mRunning.set(true);
                        return;
                    }
                    toygerFaceCallback2.onEvent(-4, (Map<String, Object>) null);
                }
            });
            return true;
        } catch (Throwable th) {
            Log.w(ToygerService.TAG, th);
            return false;
        }
    }

    public boolean processImage(List<TGFrame> list, TGDepthFrame tGDepthFrame) {
        ArrayList arrayList = new ArrayList();
        if (this.mFrameQueue.offer(arrayList)) {
            this.mColorFrameQueueLock.lock();
            for (TGFrame deepCopy : list) {
                arrayList.add(deepCopy.deepCopy());
            }
            this.mColorFrameQueueLock.unlock();
        }
        TGDepthFrame tGDepthFrame2 = new TGDepthFrame();
        if (tGDepthFrame != null && (!(tGDepthFrame.data == null && tGDepthFrame.shortBuffer == null) && this.mDepthFrameQueue.offer(tGDepthFrame2))) {
            this.mDepthFrameQueueLock.lock();
            tGDepthFrame2.assign(tGDepthFrame);
            this.mDepthFrameQueueLock.unlock();
        }
        if (this.mProcessThreadHandler == null) {
            Log.i(ToygerService.TAG, "ToygerFaceService.processImage(), processThreadHandler is null, return false");
            return false;
        }
        this.mProcessThreadHandler.post(new Runnable() {
            public void run() {
                ReentrantLock access$600;
                try {
                    if (ToygerFaceService.this.mRunning.get()) {
                        List<TGFrame> list = (List) ToygerFaceService.this.mFrameQueue.poll(1, TimeUnit.SECONDS);
                        TGDepthFrame tGDepthFrame = (TGDepthFrame) ToygerFaceService.this.mDepthFrameQueue.poll();
                        try {
                            ToygerFaceService.this.mColorFrameQueueLock.lock();
                            ToygerFaceService.this.mDepthFrameQueueLock.lock();
                            if (!(list == null || list.size() == 0)) {
                                if (ToygerFaceService.this.isMirror) {
                                    for (TGFrame tGFrame : list) {
                                        tGFrame.data = ToygerFaceService.mirrorYUV420(tGFrame.data, tGFrame.width, tGFrame.height);
                                    }
                                    tGDepthFrame.data = ToygerFaceService.mirrorDepth(tGDepthFrame.data, tGDepthFrame.width, tGDepthFrame.height);
                                }
                                ToygerFaceService.this.mFrameProcessor.initFame((TGFrame) list.get(0), tGDepthFrame);
                                Log.i(ToygerService.TAG, "toyger is processing frame");
                                Toyger.processImage(list, tGDepthFrame, new ToygerFaceAttr());
                            }
                            ToygerFaceService.this.mFrameProcessor.clearFrame();
                            ToygerFaceService.this.mColorFrameQueueLock.unlock();
                            access$600 = ToygerFaceService.this.mDepthFrameQueueLock;
                        } catch (Throwable th) {
                            Log.e(ToygerService.TAG, "Failed to processImage().", th);
                            ToygerFaceService.this.mFrameProcessor.clearFrame();
                            ToygerFaceService.this.mColorFrameQueueLock.unlock();
                            access$600 = ToygerFaceService.this.mDepthFrameQueueLock;
                        }
                        access$600.unlock();
                    }
                } catch (Throwable th2) {
                    Log.e(ToygerService.TAG, "Failed to processImage", th2);
                }
            }
        });
        return true;
    }

    public void reset() {
        Iterator it = this.mFrameQueue.iterator();
        while (it.hasNext()) {
            try {
                this.mColorFrameQueueLock.lock();
                for (TGFrame recycle : (List) it.next()) {
                    recycle.recycle();
                }
                this.mColorFrameQueueLock.unlock();
                it.remove();
            } catch (Throwable th) {
                Log.e(ToygerService.TAG, th.toString());
            }
        }
        Iterator it2 = this.mDepthFrameQueue.iterator();
        while (it2.hasNext()) {
            try {
                this.mDepthFrameQueueLock.lock();
                ((TGDepthFrame) it2.next()).recycle();
                it2.remove();
                this.mDepthFrameQueueLock.unlock();
            } catch (Throwable th2) {
                Log.e(ToygerService.TAG, th2.toString());
            }
        }
        this.mProcessThreadHandler.post(new Runnable() {
            public void run() {
                Toyger.reset();
                Log.i(ToygerService.TAG, "toyger is reset");
            }
        });
    }

    public void release() {
        this.mRunning.set(false);
        reset();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.mProcessThreadHandler.post(new Runnable() {
            public void run() {
                Log.i(ToygerService.TAG, "before releasing toyger");
                Toyger.release();
                countDownLatch.countDown();
                Log.i(ToygerService.TAG, "toyger is released");
            }
        });
        try {
            countDownLatch.await(1, TimeUnit.SECONDS);
            if (this.mProcessThread != null) {
                if (Build.VERSION.SDK_INT >= 18) {
                    this.mProcessThread.quitSafely();
                    Log.i(ToygerService.TAG, "toyger thread quited");
                } else {
                    this.mProcessThread.quit();
                    Log.i(ToygerService.TAG, "toyger thread quited");
                }
            }
            this.mProcessThread = null;
            this.mProcessThreadHandler.removeCallbacksAndMessages((Object) null);
            this.mProcessThreadHandler = null;
        } catch (InterruptedException e) {
            Log.e(ToygerService.TAG, e.toString());
        }
    }

    public void handleStateUpdated(ToygerFaceState toygerFaceState, ToygerFaceAttr toygerFaceAttr) {
        Log.d(ToygerService.TAG, "handleStateUpdated(): state=" + toygerFaceState + ", attr=" + toygerFaceAttr);
        HashMap hashMap = new HashMap();
        hashMap.put(KEY_TOYGER_FRAME, this.mFrameProcessor.getTgFrame());
        hashMap.put(KEY_TOYGER_DEPTH_FRAME, this.mFrameProcessor.getTgDepthFrame());
        ((ToygerFaceCallback) this.mToygerCallback).onStateUpdated(toygerFaceState, toygerFaceAttr, hashMap);
    }

    public void handleInfoReady(TGFrame tGFrame, ToygerFaceAttr toygerFaceAttr) {
        Log.d(ToygerService.TAG, "handleInfoReady(): frame=" + tGFrame + ", attr=" + toygerFaceAttr);
        ((ToygerFaceCallback) this.mToygerCallback).onHighQualityFrame(BitmapHelper.reverseBitmap(BitmapHelper.rotateBitmap(BitmapHelper.bytes2Bitmap(tGFrame.data, tGFrame.width, tGFrame.height, tGFrame.frameMode), tGFrame.rotation), 0), toygerFaceAttr);
    }

    public void handleCaptureCompleted(int i, List<ToygerFaceInfo> list, Map<String, Object> map) {
        Log.d(ToygerService.TAG, "handleCaptureCompleted(): result=" + i + ", infos=" + list + ", extIno=" + map);
        ((ToygerFaceCallback) this.mToygerCallback).onComplete(i, this.blobManager.generateBlob(list, map), this.blobManager.getKey(), this.blobManager.isUTF8());
    }

    public void addMonitorImage(TGFrame tGFrame) {
        this.blobManager.addMonitorImage(tGFrame);
    }

    public Map<String, Object> generateMonitorBlob() {
        byte[] monitorBlob = this.blobManager.getMonitorBlob();
        byte[] key = this.blobManager.getKey();
        HashMap hashMap = new HashMap();
        hashMap.put("content", monitorBlob);
        hashMap.put("key", key);
        hashMap.put(ToygerService.KEY_RES_9_IS_UTF8, Boolean.valueOf(this.blobManager.isUTF8()));
        return hashMap;
    }

    /* access modifiers changed from: private */
    public static byte[] mirrorYUV420(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[(((i * i2) * 3) / 2)];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            for (int i5 = i - 1; i5 >= 0; i5--) {
                bArr2[i3] = bArr[(i4 * i) + i5];
                i3++;
            }
        }
        for (int i6 = i2; i6 < (i2 * 3) / 2; i6++) {
            for (int i7 = i - 2; i7 >= 0; i7 -= 2) {
                int i8 = (i6 * i) + i7;
                bArr2[i3] = bArr[i8];
                int i9 = i3 + 1;
                bArr2[i9] = bArr[i8 + 1];
                i3 = i9 + 1;
            }
        }
        return bArr2;
    }

    /* access modifiers changed from: private */
    public static short[] mirrorDepth(short[] sArr, int i, int i2) {
        short[] sArr2 = new short[(i * i2)];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            for (int i5 = i - 1; i5 >= 0; i5--) {
                sArr2[i3] = sArr[(i4 * i) + i5];
                i3++;
            }
        }
        return sArr2;
    }
}
