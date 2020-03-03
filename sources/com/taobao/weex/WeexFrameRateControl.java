package com.taobao.weex;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import android.view.Choreographer;
import com.taobao.weex.common.WXErrorCode;
import java.lang.ref.WeakReference;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WeexFrameRateControl {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final long VSYNC_FRAME = 62;
    private final Choreographer mChoreographer;
    private WeakReference<VSyncListener> mListener;
    private final Choreographer.FrameCallback mVSyncFrameCallback;
    private final Runnable runnable;

    public interface VSyncListener {
        void OnVSync();
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(1569734635129244759L, "com/taobao/weex/WeexFrameRateControl", 22);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ WeakReference access$000(WeexFrameRateControl weexFrameRateControl) {
        boolean[] $jacocoInit = $jacocoInit();
        WeakReference<VSyncListener> weakReference = weexFrameRateControl.mListener;
        $jacocoInit[18] = true;
        return weakReference;
    }

    static /* synthetic */ Choreographer.FrameCallback access$100(WeexFrameRateControl weexFrameRateControl) {
        boolean[] $jacocoInit = $jacocoInit();
        Choreographer.FrameCallback frameCallback = weexFrameRateControl.mVSyncFrameCallback;
        $jacocoInit[19] = true;
        return frameCallback;
    }

    static /* synthetic */ Choreographer access$200(WeexFrameRateControl weexFrameRateControl) {
        boolean[] $jacocoInit = $jacocoInit();
        Choreographer choreographer = weexFrameRateControl.mChoreographer;
        $jacocoInit[20] = true;
        return choreographer;
    }

    static /* synthetic */ Runnable access$300(WeexFrameRateControl weexFrameRateControl) {
        boolean[] $jacocoInit = $jacocoInit();
        Runnable runnable2 = weexFrameRateControl.runnable;
        $jacocoInit[21] = true;
        return runnable2;
    }

    public WeexFrameRateControl(VSyncListener vSyncListener) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        this.mListener = new WeakReference<>(vSyncListener);
        if (Build.VERSION.SDK_INT > 15) {
            $jacocoInit[1] = true;
            this.mChoreographer = Choreographer.getInstance();
            $jacocoInit[2] = true;
            this.mVSyncFrameCallback = new Choreographer.FrameCallback(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WeexFrameRateControl this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-1513465741729820560L, "com/taobao/weex/WeexFrameRateControl$1", 12);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    this.this$0 = r3;
                    $jacocoInit[0] = true;
                }

                @SuppressLint({"NewApi"})
                public void doFrame(long j) {
                    boolean[] $jacocoInit = $jacocoInit();
                    if (WeexFrameRateControl.access$000(this.this$0) == null) {
                        $jacocoInit[1] = true;
                    } else {
                        VSyncListener vSyncListener = (VSyncListener) WeexFrameRateControl.access$000(this.this$0).get();
                        if (vSyncListener == null) {
                            $jacocoInit[2] = true;
                        } else {
                            try {
                                $jacocoInit[3] = true;
                                vSyncListener.OnVSync();
                                $jacocoInit[4] = true;
                                WeexFrameRateControl.access$200(this.this$0).postFrameCallback(WeexFrameRateControl.access$100(this.this$0));
                                $jacocoInit[5] = true;
                            } catch (UnsatisfiedLinkError e) {
                                if (!(vSyncListener instanceof WXSDKInstance)) {
                                    $jacocoInit[6] = true;
                                } else {
                                    WXErrorCode wXErrorCode = WXErrorCode.WX_DEGRAD_ERR_INSTANCE_CREATE_FAILED;
                                    $jacocoInit[7] = true;
                                    String errorCode = wXErrorCode.getErrorCode();
                                    $jacocoInit[8] = true;
                                    String stackTraceString = Log.getStackTraceString(e);
                                    $jacocoInit[9] = true;
                                    ((WXSDKInstance) vSyncListener).onRenderError(errorCode, stackTraceString);
                                    $jacocoInit[10] = true;
                                }
                            }
                        }
                    }
                    $jacocoInit[11] = true;
                }
            };
            this.runnable = null;
            $jacocoInit[3] = true;
        } else {
            this.runnable = new Runnable(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WeexFrameRateControl this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-4449448361276631440L, "com/taobao/weex/WeexFrameRateControl$2", 12);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    this.this$0 = r3;
                    $jacocoInit[0] = true;
                }

                public void run() {
                    boolean[] $jacocoInit = $jacocoInit();
                    if (WeexFrameRateControl.access$000(this.this$0) == null) {
                        $jacocoInit[1] = true;
                    } else {
                        VSyncListener vSyncListener = (VSyncListener) WeexFrameRateControl.access$000(this.this$0).get();
                        if (vSyncListener == null) {
                            $jacocoInit[2] = true;
                        } else {
                            try {
                                $jacocoInit[3] = true;
                                vSyncListener.OnVSync();
                                $jacocoInit[4] = true;
                                WXSDKManager.getInstance().getWXRenderManager().postOnUiThread(WeexFrameRateControl.access$300(this.this$0), (long) WeexFrameRateControl.VSYNC_FRAME);
                                $jacocoInit[5] = true;
                            } catch (UnsatisfiedLinkError e) {
                                if (!(vSyncListener instanceof WXSDKInstance)) {
                                    $jacocoInit[6] = true;
                                } else {
                                    WXErrorCode wXErrorCode = WXErrorCode.WX_DEGRAD_ERR_INSTANCE_CREATE_FAILED;
                                    $jacocoInit[7] = true;
                                    String errorCode = wXErrorCode.getErrorCode();
                                    $jacocoInit[8] = true;
                                    String stackTraceString = Log.getStackTraceString(e);
                                    $jacocoInit[9] = true;
                                    ((WXSDKInstance) vSyncListener).onRenderError(errorCode, stackTraceString);
                                    $jacocoInit[10] = true;
                                }
                            }
                        }
                    }
                    $jacocoInit[11] = true;
                }
            };
            this.mChoreographer = null;
            this.mVSyncFrameCallback = null;
            $jacocoInit[4] = true;
        }
        $jacocoInit[5] = true;
    }

    @SuppressLint({"NewApi"})
    public void start() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mChoreographer != null) {
            $jacocoInit[6] = true;
            this.mChoreographer.postFrameCallback(this.mVSyncFrameCallback);
            $jacocoInit[7] = true;
        } else if (this.runnable == null) {
            $jacocoInit[8] = true;
        } else {
            $jacocoInit[9] = true;
            WXSDKManager.getInstance().getWXRenderManager().postOnUiThread(this.runnable, (long) VSYNC_FRAME);
            $jacocoInit[10] = true;
        }
        $jacocoInit[11] = true;
    }

    @SuppressLint({"NewApi"})
    public void stop() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mChoreographer != null) {
            $jacocoInit[12] = true;
            this.mChoreographer.removeFrameCallback(this.mVSyncFrameCallback);
            $jacocoInit[13] = true;
        } else if (this.runnable == null) {
            $jacocoInit[14] = true;
        } else {
            $jacocoInit[15] = true;
            WXSDKManager.getInstance().getWXRenderManager().removeTask(this.runnable);
            $jacocoInit[16] = true;
        }
        $jacocoInit[17] = true;
    }
}
