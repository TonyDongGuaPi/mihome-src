package com.taobao.weex.ui.component;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.FrameLayout;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.annotation.Component;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.view.WXVideoView;
import com.taobao.weex.utils.WXLogUtils;
import com.xiaomi.mistatistic.sdk.BaseService;
import java.util.HashMap;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

@Component(lazyload = false)
public class WXVideo extends WXComponent<FrameLayout> {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private boolean mAutoPlay;
    private boolean mError;
    boolean mPrepared;
    private boolean mStopped;
    private WXVideoView.Wrapper mWrapper;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-9110656924741992541L, "com/taobao/weex/ui/component/WXVideo", 85);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ boolean access$002(WXVideo wXVideo, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        wXVideo.mError = z;
        $jacocoInit[80] = true;
        return z;
    }

    static /* synthetic */ void access$100(WXVideo wXVideo, String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        wXVideo.notify(str, str2);
        $jacocoInit[81] = true;
    }

    static /* synthetic */ boolean access$200(WXVideo wXVideo) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = wXVideo.mAutoPlay;
        $jacocoInit[82] = true;
        return z;
    }

    static /* synthetic */ boolean access$300(WXVideo wXVideo) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = wXVideo.mStopped;
        $jacocoInit[83] = true;
        return z;
    }

    static /* synthetic */ boolean access$302(WXVideo wXVideo, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        wXVideo.mStopped = z;
        $jacocoInit[84] = true;
        return z;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public WXVideo(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, String str, boolean z, BasicComponentData basicComponentData) {
        this(wXSDKInstance, wXVContainer, z, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXVideo(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, boolean z, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, z, basicComponentData);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
    }

    /* access modifiers changed from: protected */
    public FrameLayout initComponentHostView(@NonNull Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        final WXVideoView.Wrapper wrapper = new WXVideoView.Wrapper(context);
        $jacocoInit[2] = true;
        wrapper.setOnErrorListener(new MediaPlayer.OnErrorListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXVideo this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(8705344165291898200L, "com/taobao/weex/ui/component/WXVideo$1", 10);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                boolean[] $jacocoInit = $jacocoInit();
                if (!WXEnvironment.isApkDebugable()) {
                    $jacocoInit[1] = true;
                } else {
                    $jacocoInit[2] = true;
                    WXLogUtils.d("Video", "onError:" + i);
                    $jacocoInit[3] = true;
                }
                wrapper.getProgressBar().setVisibility(8);
                this.this$0.mPrepared = false;
                $jacocoInit[4] = true;
                WXVideo.access$002(this.this$0, true);
                $jacocoInit[5] = true;
                if (!this.this$0.getEvents().contains("fail")) {
                    $jacocoInit[6] = true;
                } else {
                    $jacocoInit[7] = true;
                    WXVideo.access$100(this.this$0, "fail", "stop");
                    $jacocoInit[8] = true;
                }
                $jacocoInit[9] = true;
                return true;
            }
        });
        $jacocoInit[3] = true;
        wrapper.setOnPreparedListener(new MediaPlayer.OnPreparedListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXVideo this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-3913100121792266747L, "com/taobao/weex/ui/component/WXVideo$2", 16);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void onPrepared(MediaPlayer mediaPlayer) {
                boolean[] $jacocoInit = $jacocoInit();
                if (!WXEnvironment.isApkDebugable()) {
                    $jacocoInit[1] = true;
                } else {
                    $jacocoInit[2] = true;
                    WXLogUtils.d("Video", "onPrepared");
                    $jacocoInit[3] = true;
                }
                wrapper.getProgressBar().setVisibility(8);
                this.this$0.mPrepared = true;
                $jacocoInit[4] = true;
                if (!WXVideo.access$200(this.this$0)) {
                    $jacocoInit[5] = true;
                } else {
                    $jacocoInit[6] = true;
                    wrapper.start();
                    $jacocoInit[7] = true;
                }
                WXVideoView videoView = wrapper.getVideoView();
                $jacocoInit[8] = true;
                videoView.seekTo(5);
                $jacocoInit[9] = true;
                if (wrapper.getMediaController() == null) {
                    $jacocoInit[10] = true;
                } else {
                    $jacocoInit[11] = true;
                    if (!WXVideo.access$300(this.this$0)) {
                        $jacocoInit[12] = true;
                        wrapper.getMediaController().show(3);
                        $jacocoInit[13] = true;
                    } else {
                        wrapper.getMediaController().hide();
                        $jacocoInit[14] = true;
                    }
                }
                WXVideo.access$302(this.this$0, false);
                $jacocoInit[15] = true;
            }
        });
        $jacocoInit[4] = true;
        wrapper.setOnCompletionListener(new MediaPlayer.OnCompletionListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXVideo this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-8011353736722573682L, "com/taobao/weex/ui/component/WXVideo$3", 8);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void onCompletion(MediaPlayer mediaPlayer) {
                boolean[] $jacocoInit = $jacocoInit();
                if (!WXEnvironment.isApkDebugable()) {
                    $jacocoInit[1] = true;
                } else {
                    $jacocoInit[2] = true;
                    WXLogUtils.d("Video", "onCompletion");
                    $jacocoInit[3] = true;
                }
                if (!this.this$0.getEvents().contains(Constants.Event.FINISH)) {
                    $jacocoInit[4] = true;
                } else {
                    $jacocoInit[5] = true;
                    WXVideo.access$100(this.this$0, Constants.Event.FINISH, "stop");
                    $jacocoInit[6] = true;
                }
                $jacocoInit[7] = true;
            }
        });
        $jacocoInit[5] = true;
        wrapper.setOnVideoPauseListener(new WXVideoView.VideoPlayListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXVideo this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-5739354235985607994L, "com/taobao/weex/ui/component/WXVideo$4", 15);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void onPause() {
                boolean[] $jacocoInit = $jacocoInit();
                if (!WXEnvironment.isApkDebugable()) {
                    $jacocoInit[1] = true;
                } else {
                    $jacocoInit[2] = true;
                    WXLogUtils.d("Video", "onPause");
                    $jacocoInit[3] = true;
                }
                if (!this.this$0.getEvents().contains("pause")) {
                    $jacocoInit[4] = true;
                } else {
                    $jacocoInit[5] = true;
                    WXVideo.access$100(this.this$0, "pause", "pause");
                    $jacocoInit[6] = true;
                }
                $jacocoInit[7] = true;
            }

            public void onStart() {
                boolean[] $jacocoInit = $jacocoInit();
                if (!WXEnvironment.isApkDebugable()) {
                    $jacocoInit[8] = true;
                } else {
                    $jacocoInit[9] = true;
                    WXLogUtils.d("Video", "onStart");
                    $jacocoInit[10] = true;
                }
                if (!this.this$0.getEvents().contains("start")) {
                    $jacocoInit[11] = true;
                } else {
                    $jacocoInit[12] = true;
                    WXVideo.access$100(this.this$0, "start", Constants.Value.PLAY);
                    $jacocoInit[13] = true;
                }
                $jacocoInit[14] = true;
            }
        });
        this.mWrapper = wrapper;
        $jacocoInit[6] = true;
        return wrapper;
    }

    private void notify(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        HashMap hashMap = new HashMap(2);
        $jacocoInit[7] = true;
        hashMap.put(Constants.Name.PLAY_STATUS, str2);
        $jacocoInit[8] = true;
        hashMap.put(BaseService.TIME_STAMP, Long.valueOf(System.currentTimeMillis()));
        $jacocoInit[9] = true;
        HashMap hashMap2 = new HashMap();
        $jacocoInit[10] = true;
        HashMap hashMap3 = new HashMap();
        $jacocoInit[11] = true;
        hashMap3.put(Constants.Name.PLAY_STATUS, str2);
        $jacocoInit[12] = true;
        hashMap2.put("attrs", hashMap3);
        $jacocoInit[13] = true;
        WXSDKManager.getInstance().fireEvent(getInstanceId(), getRef(), str, hashMap, hashMap2);
        $jacocoInit[14] = true;
    }

    public void bindData(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        super.bindData(wXComponent);
        $jacocoInit[15] = true;
        addEvent(Constants.Event.APPEAR);
        $jacocoInit[16] = true;
    }

    public void notifyAppearStateChange(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        super.notifyAppearStateChange(str, str2);
        $jacocoInit[17] = true;
        this.mWrapper.createVideoViewIfVisible();
        $jacocoInit[18] = true;
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        super.destroy();
        $jacocoInit[19] = true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean setProperty(java.lang.String r5, java.lang.Object r6) {
        /*
            r4 = this;
            boolean[] r0 = $jacocoInit()
            int r1 = r5.hashCode()
            r2 = 114148(0x1bde4, float:1.59955E-40)
            r3 = 1
            if (r1 == r2) goto L_0x0043
            r2 = 1438608771(0x55bf6d83, float:2.63096338E13)
            if (r1 == r2) goto L_0x0030
            r2 = 1582764102(0x5e571046, float:3.87424082E18)
            if (r1 == r2) goto L_0x001d
            r1 = 20
            r0[r1] = r3
            goto L_0x004f
        L_0x001d:
            java.lang.String r1 = "playStatus"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x002a
            r1 = 25
            r0[r1] = r3
            goto L_0x004f
        L_0x002a:
            r1 = 2
            r2 = 26
            r0[r2] = r3
            goto L_0x0056
        L_0x0030:
            java.lang.String r1 = "autoPlay"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x003d
            r1 = 23
            r0[r1] = r3
            goto L_0x004f
        L_0x003d:
            r1 = 24
            r0[r1] = r3
            r1 = 1
            goto L_0x0056
        L_0x0043:
            java.lang.String r1 = "src"
            boolean r1 = r5.equals(r1)
            if (r1 != 0) goto L_0x0051
            r1 = 21
            r0[r1] = r3
        L_0x004f:
            r1 = -1
            goto L_0x0056
        L_0x0051:
            r1 = 0
            r2 = 22
            r0[r2] = r3
        L_0x0056:
            r2 = 0
            switch(r1) {
                case 0: goto L_0x009d;
                case 1: goto L_0x007e;
                case 2: goto L_0x0063;
                default: goto L_0x005a;
            }
        L_0x005a:
            boolean r5 = super.setProperty(r5, r6)
            r6 = 39
            r0[r6] = r3
            return r5
        L_0x0063:
            java.lang.String r5 = com.taobao.weex.utils.WXUtils.getString(r6, r2)
            if (r5 != 0) goto L_0x006e
            r5 = 35
            r0[r5] = r3
            goto L_0x0079
        L_0x006e:
            r6 = 36
            r0[r6] = r3
            r4.setPlaystatus(r5)
            r5 = 37
            r0[r5] = r3
        L_0x0079:
            r5 = 38
            r0[r5] = r3
            return r3
        L_0x007e:
            java.lang.Boolean r5 = com.taobao.weex.utils.WXUtils.getBoolean(r6, r2)
            if (r5 != 0) goto L_0x0089
            r5 = 31
            r0[r5] = r3
            goto L_0x0098
        L_0x0089:
            r6 = 32
            r0[r6] = r3
            boolean r5 = r5.booleanValue()
            r4.setAutoPlay(r5)
            r5 = 33
            r0[r5] = r3
        L_0x0098:
            r5 = 34
            r0[r5] = r3
            return r3
        L_0x009d:
            java.lang.String r5 = com.taobao.weex.utils.WXUtils.getString(r6, r2)
            if (r5 != 0) goto L_0x00a8
            r5 = 27
            r0[r5] = r3
            goto L_0x00b3
        L_0x00a8:
            r6 = 28
            r0[r6] = r3
            r4.setSrc(r5)
            r5 = 29
            r0[r5] = r3
        L_0x00b3:
            r5 = 30
            r0[r5] = r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.WXVideo.setProperty(java.lang.String, java.lang.Object):boolean");
    }

    @WXComponentProp(name = "src")
    public void setSrc(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[40] = true;
        } else if (getHostView() == null) {
            $jacocoInit[41] = true;
        } else {
            if (TextUtils.isEmpty(str)) {
                $jacocoInit[43] = true;
            } else {
                $jacocoInit[44] = true;
                WXSDKInstance instance = getInstance();
                $jacocoInit[45] = true;
                this.mWrapper.setVideoURI(instance.rewriteUri(Uri.parse(str), "video"));
                $jacocoInit[46] = true;
                this.mWrapper.getProgressBar().setVisibility(0);
                $jacocoInit[47] = true;
            }
            $jacocoInit[48] = true;
            return;
        }
        $jacocoInit[42] = true;
    }

    @WXComponentProp(name = "autoPlay")
    public void setAutoPlay(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mAutoPlay = z;
        if (!z) {
            $jacocoInit[49] = true;
        } else {
            $jacocoInit[50] = true;
            this.mWrapper.createIfNotExist();
            $jacocoInit[51] = true;
            this.mWrapper.start();
            $jacocoInit[52] = true;
        }
        $jacocoInit[53] = true;
    }

    @WXComponentProp(name = "controls")
    public void setControls(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.equals("controls", str)) {
            $jacocoInit[54] = true;
            this.mWrapper.setControls(true);
            $jacocoInit[55] = true;
        } else if (!TextUtils.equals("nocontrols", str)) {
            $jacocoInit[56] = true;
        } else {
            $jacocoInit[57] = true;
            this.mWrapper.setControls(false);
            $jacocoInit[58] = true;
        }
        $jacocoInit[59] = true;
    }

    @WXComponentProp(name = "playStatus")
    public void setPlaystatus(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.mPrepared) {
            $jacocoInit[60] = true;
        } else if (this.mError) {
            $jacocoInit[61] = true;
        } else if (this.mStopped) {
            $jacocoInit[62] = true;
        } else {
            $jacocoInit[63] = true;
            if (str.equals(Constants.Value.PLAY)) {
                $jacocoInit[64] = true;
                this.mWrapper.start();
                $jacocoInit[65] = true;
            } else if (str.equals("pause")) {
                $jacocoInit[66] = true;
                this.mWrapper.pause();
                $jacocoInit[67] = true;
            } else if (!str.equals("stop")) {
                $jacocoInit[68] = true;
            } else {
                $jacocoInit[69] = true;
                this.mWrapper.stopPlayback();
                this.mStopped = true;
                $jacocoInit[70] = true;
            }
            $jacocoInit[78] = true;
        }
        if (this.mError) {
            $jacocoInit[71] = true;
        } else if (!this.mStopped) {
            $jacocoInit[72] = true;
            $jacocoInit[78] = true;
        } else {
            $jacocoInit[73] = true;
        }
        if (!str.equals(Constants.Value.PLAY)) {
            $jacocoInit[74] = true;
        } else {
            this.mError = false;
            $jacocoInit[75] = true;
            this.mWrapper.resume();
            $jacocoInit[76] = true;
            this.mWrapper.getProgressBar().setVisibility(0);
            $jacocoInit[77] = true;
        }
        $jacocoInit[78] = true;
    }
}
