package com.brentvatne.react;

import android.annotation.SuppressLint;
import android.content.res.AssetFileDescriptor;
import android.graphics.Matrix;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.CookieManager;
import android.widget.MediaController;
import com.android.vending.expansion.zipfile.APKExpansionSupport;
import com.android.vending.expansion.zipfile.ZipResourceFile;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.yqritc.scalablevideoview.ScalableType;
import com.yqritc.scalablevideoview.ScalableVideoView;
import com.yqritc.scalablevideoview.ScaleManager;
import com.yqritc.scalablevideoview.Size;
import java.io.IOException;
import java.util.HashMap;

@SuppressLint({"ViewConstructor"})
public class ReactVideoView extends ScalableVideoView implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener, MediaController.MediaPlayerControl, LifecycleEventListener {
    public static final String EVENT_PROP_CURRENT_TIME = "currentTime";
    public static final String EVENT_PROP_DURATION = "duration";
    public static final String EVENT_PROP_ERROR = "error";
    public static final String EVENT_PROP_EXTRA = "extra";
    public static final String EVENT_PROP_FAST_FORWARD = "canPlayFastForward";
    public static final String EVENT_PROP_HEIGHT = "height";
    public static final String EVENT_PROP_NATURALSIZE = "naturalSize";
    public static final String EVENT_PROP_ORIENTATION = "orientation";
    public static final String EVENT_PROP_PLAYABLE_DURATION = "playableDuration";
    public static final String EVENT_PROP_REVERSE = "canPlayReverse";
    public static final String EVENT_PROP_SEEKABLE_DURATION = "seekableDuration";
    public static final String EVENT_PROP_SEEK_TIME = "seekTime";
    public static final String EVENT_PROP_SLOW_FORWARD = "canPlaySlowForward";
    public static final String EVENT_PROP_SLOW_REVERSE = "canPlaySlowReverse";
    public static final String EVENT_PROP_STEP_BACKWARD = "canStepBackward";
    public static final String EVENT_PROP_STEP_FORWARD = "canStepForward";
    public static final String EVENT_PROP_WHAT = "what";
    public static final String EVENT_PROP_WIDTH = "width";
    /* access modifiers changed from: private */
    public int A = 0;
    /* access modifiers changed from: private */
    public boolean B = false;
    private boolean C = false;

    /* renamed from: a  reason: collision with root package name */
    private ThemedReactContext f4802a;
    /* access modifiers changed from: private */
    public RCTEventEmitter b;
    /* access modifiers changed from: private */
    public Handler c = new Handler();
    /* access modifiers changed from: private */
    public Runnable d = null;
    private Handler e = new Handler();
    /* access modifiers changed from: private */
    public MediaController f;
    private String g = null;
    private String h = "mp4";
    private boolean i = false;
    private boolean j = false;
    private ScalableType k = ScalableType.LEFT_TOP;
    private boolean l = false;
    /* access modifiers changed from: private */
    public boolean m = false;
    private boolean n = false;
    private float o = 1.0f;
    /* access modifiers changed from: private */
    public float p = 250.0f;
    private float q = 1.0f;
    private float r = 1.0f;
    private boolean s = false;
    /* access modifiers changed from: private */
    public boolean t = false;
    private boolean u = false;
    private long v = 0;
    private int w = 0;
    private int x = 0;
    /* access modifiers changed from: private */
    public boolean y = false;
    /* access modifiers changed from: private */
    public int z = 0;

    public boolean canPause() {
        return true;
    }

    public boolean canSeekBackward() {
        return true;
    }

    public boolean canSeekForward() {
        return true;
    }

    public int getAudioSessionId() {
        return 0;
    }

    public int getBufferPercentage() {
        return 0;
    }

    public void onHostDestroy() {
    }

    public enum Events {
        EVENT_LOAD_START("onVideoLoadStart"),
        EVENT_LOAD("onVideoLoad"),
        EVENT_ERROR("onVideoError"),
        EVENT_PROGRESS("onVideoProgress"),
        EVENT_SEEK("onVideoSeek"),
        EVENT_END("onVideoEnd"),
        EVENT_STALLED("onPlaybackStalled"),
        EVENT_RESUME("onPlaybackResume"),
        EVENT_READY_FOR_DISPLAY("onReadyForDisplay");
        
        private final String mName;

        private Events(String str) {
            this.mName = str;
        }

        public String toString() {
            return this.mName;
        }
    }

    public ReactVideoView(ThemedReactContext themedReactContext) {
        super(themedReactContext);
        this.f4802a = themedReactContext;
        this.b = (RCTEventEmitter) themedReactContext.getJSModule(RCTEventEmitter.class);
        themedReactContext.addLifecycleEventListener(this);
        a();
        setSurfaceTextureListener(this);
        this.d = new Runnable() {
            public void run() {
                if (ReactVideoView.this.y && !ReactVideoView.this.B && !ReactVideoView.this.m) {
                    WritableMap createMap = Arguments.createMap();
                    double currentPosition = (double) ReactVideoView.this.mMediaPlayer.getCurrentPosition();
                    Double.isNaN(currentPosition);
                    createMap.putDouble(ReactVideoView.EVENT_PROP_CURRENT_TIME, currentPosition / 1000.0d);
                    double access$400 = (double) ReactVideoView.this.A;
                    Double.isNaN(access$400);
                    createMap.putDouble(ReactVideoView.EVENT_PROP_PLAYABLE_DURATION, access$400 / 1000.0d);
                    double access$500 = (double) ReactVideoView.this.z;
                    Double.isNaN(access$500);
                    createMap.putDouble(ReactVideoView.EVENT_PROP_SEEKABLE_DURATION, access$500 / 1000.0d);
                    ReactVideoView.this.b.receiveEvent(ReactVideoView.this.getId(), Events.EVENT_PROGRESS.toString(), createMap);
                    ReactVideoView.this.c.postDelayed(ReactVideoView.this.d, (long) Math.round(ReactVideoView.this.p));
                }
            }
        };
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.C) {
            b();
            this.f.show();
        }
        return super.onTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"DrawAllocation"})
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        Matrix a2;
        super.onLayout(z2, i2, i3, i4, i5);
        if (z2 && this.y) {
            int videoWidth = getVideoWidth();
            int videoHeight = getVideoHeight();
            if (videoWidth != 0 && videoHeight != 0 && (a2 = new ScaleManager(new Size(getWidth(), getHeight()), new Size(videoWidth, videoHeight)).a(this.mScalableType)) != null) {
                setTransform(a2);
            }
        }
    }

    private void a() {
        if (this.mMediaPlayer == null) {
            this.y = false;
            this.mMediaPlayer = new MediaPlayer();
            this.mMediaPlayer.setScreenOnWhilePlaying(true);
            this.mMediaPlayer.setOnVideoSizeChangedListener(this);
            this.mMediaPlayer.setOnErrorListener(this);
            this.mMediaPlayer.setOnPreparedListener(this);
            this.mMediaPlayer.setOnBufferingUpdateListener(this);
            this.mMediaPlayer.setOnCompletionListener(this);
            this.mMediaPlayer.setOnInfoListener(this);
            this.mMediaPlayer.setOnSeekCompleteListener(this);
        }
    }

    private void b() {
        if (this.f == null) {
            this.f = new MediaController(getContext());
        }
    }

    public void cleanupMediaPlayerResources() {
        if (this.f != null) {
            this.f.hide();
        }
        if (this.mMediaPlayer != null) {
            this.y = false;
            release();
        }
    }

    public void setSrc(String str, String str2, boolean z2, boolean z3) {
        setSrc(str, str2, z2, z3, 0, 0);
    }

    public void setSrc(String str, String str2, boolean z2, boolean z3, int i2, int i3) {
        this.g = str;
        this.h = str2;
        this.i = z2;
        this.j = z3;
        this.w = i2;
        this.x = i3;
        this.y = false;
        this.z = 0;
        this.A = 0;
        a();
        this.mMediaPlayer.reset();
        if (z2) {
            try {
                String cookie = CookieManager.getInstance().getCookie(Uri.parse(str).buildUpon().build().toString());
                HashMap hashMap = new HashMap();
                if (cookie != null) {
                    hashMap.put("Cookie", cookie);
                }
                setDataSource(str);
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        } else if (!z3) {
            AssetFileDescriptor assetFileDescriptor = null;
            if (this.w > 0) {
                try {
                    ZipResourceFile b2 = APKExpansionSupport.b(this.f4802a, this.w, this.x);
                    assetFileDescriptor = b2.b(str.replace(".mp4", "") + ".mp4");
                } catch (IOException e3) {
                    e3.printStackTrace();
                } catch (NullPointerException e4) {
                    e4.printStackTrace();
                }
            }
            if (assetFileDescriptor == null) {
                int identifier = this.f4802a.getResources().getIdentifier(str, "drawable", this.f4802a.getPackageName());
                if (identifier == 0) {
                    identifier = this.f4802a.getResources().getIdentifier(str, ShareConstants.V, this.f4802a.getPackageName());
                }
                setRawData(identifier);
            } else {
                setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            }
        } else if (str.startsWith("content://")) {
            setDataSource(this.f4802a, Uri.parse(str));
        } else {
            setDataSource(str);
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putString("uri", str);
        createMap.putString("type", str2);
        createMap.putBoolean(ReactVideoViewManager.PROP_SRC_IS_NETWORK, z2);
        if (this.w > 0) {
            createMap.putInt(ReactVideoViewManager.PROP_SRC_MAINVER, this.w);
            if (this.x > 0) {
                createMap.putInt(ReactVideoViewManager.PROP_SRC_PATCHVER, this.x);
            }
        }
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putMap("src", createMap);
        this.b.receiveEvent(getId(), Events.EVENT_LOAD_START.toString(), createMap2);
        this.B = false;
        try {
            prepareAsync(this);
        } catch (Exception e5) {
            e5.printStackTrace();
        }
    }

    public void setResizeModeModifier(ScalableType scalableType) {
        this.k = scalableType;
        if (this.y) {
            setScalableType(scalableType);
            invalidate();
        }
    }

    public void setRepeatModifier(boolean z2) {
        this.l = z2;
        if (this.y) {
            setLooping(z2);
        }
    }

    public void setPausedModifier(boolean z2) {
        this.m = z2;
        if (!this.u) {
            this.t = this.m;
            this.u = true;
        }
        if (this.y) {
            if (this.m) {
                if (this.mMediaPlayer.isPlaying()) {
                    pause();
                }
            } else if (!this.mMediaPlayer.isPlaying()) {
                start();
                if (this.q != this.r) {
                    setRateModifier(this.q);
                }
                this.c.post(this.d);
            }
        }
    }

    public void setMutedModifier(boolean z2) {
        this.n = z2;
        if (this.y) {
            if (this.n) {
                setVolume(0.0f, 0.0f);
            } else {
                setVolume(this.o, this.o);
            }
        }
    }

    public void setVolumeModifier(float f2) {
        this.o = f2;
        setMutedModifier(this.n);
    }

    public void setProgressUpdateInterval(float f2) {
        this.p = f2;
    }

    public void setRateModifier(float f2) {
        this.q = f2;
        if (!this.y) {
            return;
        }
        if (Build.VERSION.SDK_INT < 23) {
            Log.e(ReactVideoViewManager.REACT_CLASS, "Setting playback rate is not yet supported on Android versions below 6.0");
        } else if (!this.m) {
            this.mMediaPlayer.setPlaybackParams(this.mMediaPlayer.getPlaybackParams().setSpeed(f2));
            this.r = f2;
        }
    }

    public void applyModifiers() {
        setResizeModeModifier(this.k);
        setRepeatModifier(this.l);
        setPausedModifier(this.m);
        setMutedModifier(this.n);
        setProgressUpdateInterval(this.p);
        setRateModifier(this.q);
    }

    public void setPlayInBackground(boolean z2) {
        this.s = z2;
    }

    public void setControls(boolean z2) {
        this.C = z2;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        this.y = true;
        this.z = mediaPlayer.getDuration();
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("width", mediaPlayer.getVideoWidth());
        createMap.putInt("height", mediaPlayer.getVideoHeight());
        if (mediaPlayer.getVideoWidth() > mediaPlayer.getVideoHeight()) {
            createMap.putString("orientation", "landscape");
        } else {
            createMap.putString("orientation", "portrait");
        }
        WritableMap createMap2 = Arguments.createMap();
        double d2 = (double) this.z;
        Double.isNaN(d2);
        createMap2.putDouble("duration", d2 / 1000.0d);
        double currentPosition = (double) mediaPlayer.getCurrentPosition();
        Double.isNaN(currentPosition);
        createMap2.putDouble(EVENT_PROP_CURRENT_TIME, currentPosition / 1000.0d);
        createMap2.putMap(EVENT_PROP_NATURALSIZE, createMap);
        createMap2.putBoolean(EVENT_PROP_FAST_FORWARD, true);
        createMap2.putBoolean(EVENT_PROP_SLOW_FORWARD, true);
        createMap2.putBoolean(EVENT_PROP_SLOW_REVERSE, true);
        createMap2.putBoolean(EVENT_PROP_REVERSE, true);
        createMap2.putBoolean(EVENT_PROP_FAST_FORWARD, true);
        createMap2.putBoolean(EVENT_PROP_STEP_BACKWARD, true);
        createMap2.putBoolean(EVENT_PROP_STEP_FORWARD, true);
        this.b.receiveEvent(getId(), Events.EVENT_LOAD.toString(), createMap2);
        applyModifiers();
        if (this.C) {
            b();
            this.f.setMediaPlayer(this);
            this.f.setAnchorView(this);
            this.e.post(new Runnable() {
                public void run() {
                    ReactVideoView.this.f.setEnabled(true);
                    ReactVideoView.this.f.show();
                }
            });
        }
    }

    public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt(EVENT_PROP_WHAT, i2);
        createMap.putInt("extra", i3);
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putMap("error", createMap);
        this.b.receiveEvent(getId(), Events.EVENT_ERROR.toString(), createMap2);
        return true;
    }

    public boolean onInfo(MediaPlayer mediaPlayer, int i2, int i3) {
        if (i2 != 3) {
            switch (i2) {
                case 701:
                    this.b.receiveEvent(getId(), Events.EVENT_STALLED.toString(), Arguments.createMap());
                    return false;
                case 702:
                    this.b.receiveEvent(getId(), Events.EVENT_RESUME.toString(), Arguments.createMap());
                    return false;
                default:
                    return false;
            }
        } else {
            this.b.receiveEvent(getId(), Events.EVENT_READY_FOR_DISPLAY.toString(), Arguments.createMap());
            return false;
        }
    }

    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i2) {
        double d2 = (double) (this.z * i2);
        Double.isNaN(d2);
        this.A = (int) Math.round(d2 / 100.0d);
    }

    public void onSeekComplete(MediaPlayer mediaPlayer) {
        WritableMap createMap = Arguments.createMap();
        double currentPosition = (double) getCurrentPosition();
        Double.isNaN(currentPosition);
        createMap.putDouble(EVENT_PROP_CURRENT_TIME, currentPosition / 1000.0d);
        double d2 = (double) this.v;
        Double.isNaN(d2);
        createMap.putDouble(EVENT_PROP_SEEK_TIME, d2 / 1000.0d);
        this.b.receiveEvent(getId(), Events.EVENT_SEEK.toString(), createMap);
        this.v = 0;
    }

    public void seekTo(int i2) {
        if (this.y) {
            this.v = (long) i2;
            super.seekTo(i2);
            if (this.B && this.z != 0 && i2 < this.z) {
                this.B = false;
            }
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        this.B = true;
        this.b.receiveEvent(getId(), Events.EVENT_END.toString(), (WritableMap) null);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.y = false;
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.w > 0) {
            setSrc(this.g, this.h, this.i, this.j, this.w, this.x);
            return;
        }
        setSrc(this.g, this.h, this.i, this.j);
    }

    public void onHostPause() {
        if (this.mMediaPlayer != null && !this.s) {
            this.t = this.m;
            setPausedModifier(true);
        }
    }

    public void onHostResume() {
        if (this.mMediaPlayer != null && !this.s) {
            new Handler().post(new Runnable() {
                public void run() {
                    ReactVideoView.this.setPausedModifier(ReactVideoView.this.t);
                }
            });
        }
    }
}
