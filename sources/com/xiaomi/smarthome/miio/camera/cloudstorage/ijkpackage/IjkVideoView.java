package com.xiaomi.smarthome.miio.camera.cloudstorage.ijkpackage;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.TextView;
import com.google.android.exoplayer2.C;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.ijkpackage.IRenderView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.TextureMediaPlayer;
import tv.danmaku.ijk.media.player.misc.IMediaDataSource;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;

public class IjkVideoView extends FrameLayout implements MediaController.MediaPlayerControl {
    public static final int RENDER_NONE = 0;
    public static final int RENDER_SURFACE_VIEW = 1;
    public static final int RENDER_TEXTURE_VIEW = 2;
    public static final int STATE_ERROR = -1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_PAUSED = 4;
    public static final int STATE_PLAYBACK_COMPLETED = 5;
    public static final int STATE_PLAYING = 3;
    public static final int STATE_PREPARED = 2;
    public static final int STATE_PREPARING = 1;
    private static final int[] s_allAspectRatio = {0, 1, 2, 4, 5};
    /* access modifiers changed from: private */
    public String TAG = "IjkVideoView";
    public IjkVideoInfo ijkVideoInfo;
    private List<Integer> mAllRenders = new ArrayList();
    /* access modifiers changed from: private */
    public Context mAppContext;
    private IMediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() {
        public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
            int unused = IjkVideoView.this.mCurrentBufferPercentage = i;
        }
    };
    private boolean mCanPause = true;
    private boolean mCanSeekBack = true;
    private boolean mCanSeekForward = true;
    private IMediaPlayer.OnCompletionListener mCompletionListener = new IMediaPlayer.OnCompletionListener() {
        public void onCompletion(IMediaPlayer iMediaPlayer) {
            int unused = IjkVideoView.this.mCurrentState = 5;
            int unused2 = IjkVideoView.this.mTargetState = 5;
            if (IjkVideoView.this.mOnCompletionListener != null) {
                IjkVideoView.this.mOnCompletionListener.onCompletion(IjkVideoView.this.mMediaPlayer);
            }
        }
    };
    private int mCurrentAspectRatio = s_allAspectRatio[0];
    private int mCurrentAspectRatioIndex = 0;
    /* access modifiers changed from: private */
    public int mCurrentBufferPercentage;
    public int mCurrentRender = 0;
    private int mCurrentRenderIndex = 0;
    /* access modifiers changed from: private */
    public int mCurrentState = 0;
    private boolean mEnableBackgroundPlay = false;
    private IMediaPlayer.OnErrorListener mErrorListener = new IMediaPlayer.OnErrorListener() {
        public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
            String access$400 = IjkVideoView.this.TAG;
            Log.d(access$400, "Error: " + i + "," + i2);
            int unused = IjkVideoView.this.mCurrentState = -1;
            int unused2 = IjkVideoView.this.mTargetState = -1;
            if ((IjkVideoView.this.mOnErrorListener == null || !IjkVideoView.this.mOnErrorListener.onError(IjkVideoView.this.mMediaPlayer, i, i2)) && IjkVideoView.this.getWindowToken() != null) {
                IjkVideoView.this.mAppContext.getResources();
            }
            return true;
        }
    };
    private Map<String, String> mHeaders;
    private IMediaPlayer.OnInfoListener mInfoListener = new IMediaPlayer.OnInfoListener() {
        public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
            if (IjkVideoView.this.mOnInfoListener != null) {
                IjkVideoView.this.mOnInfoListener.onInfo(iMediaPlayer, i, i2);
            }
            switch (i) {
                case 3:
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_RENDERING_START:");
                    return true;
                case 700:
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                    return true;
                case 701:
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BUFFERING_START:");
                    return true;
                case 702:
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BUFFERING_END:");
                    return true;
                case 703:
                    String access$400 = IjkVideoView.this.TAG;
                    Log.d(access$400, "MEDIA_INFO_NETWORK_BANDWIDTH: " + i2);
                    return true;
                case 800:
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                    return true;
                case 801:
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                    return true;
                case 802:
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_METADATA_UPDATE:");
                    return true;
                case 901:
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                    return true;
                case 902:
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                    return true;
                case 10001:
                    int unused = IjkVideoView.this.mVideoRotationDegree = i2;
                    String access$4002 = IjkVideoView.this.TAG;
                    Log.d(access$4002, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i2);
                    if (IjkVideoView.this.mRenderView == null) {
                        return true;
                    }
                    IjkVideoView.this.mRenderView.setVideoRotation(i2);
                    return true;
                case 10002:
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
                    return true;
                default:
                    return true;
            }
        }
    };
    /* access modifiers changed from: private */
    public IMediaPlayer mMediaPlayer = null;
    /* access modifiers changed from: private */
    public IMediaPlayer.OnCompletionListener mOnCompletionListener;
    /* access modifiers changed from: private */
    public IMediaPlayer.OnErrorListener mOnErrorListener;
    /* access modifiers changed from: private */
    public IMediaPlayer.OnInfoListener mOnInfoListener;
    /* access modifiers changed from: private */
    public IMediaPlayer.OnPreparedListener mOnPreparedListener;
    /* access modifiers changed from: private */
    public long mPrepareEndTime = 0;
    private long mPrepareStartTime = 0;
    IMediaPlayer.OnPreparedListener mPreparedListener = new IMediaPlayer.OnPreparedListener() {
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            long unused = IjkVideoView.this.mPrepareEndTime = System.currentTimeMillis();
            int unused2 = IjkVideoView.this.mCurrentState = 2;
            if (IjkVideoView.this.mOnPreparedListener != null) {
                IjkVideoView.this.mOnPreparedListener.onPrepared(IjkVideoView.this.mMediaPlayer);
            }
            int unused3 = IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
            int unused4 = IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
            int access$1000 = IjkVideoView.this.mSeekWhenPrepared;
            if (access$1000 != 0) {
                IjkVideoView.this.seekTo(access$1000);
            }
            if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                if (IjkVideoView.this.mTargetState == 3) {
                    IjkVideoView.this.start();
                }
            } else if (IjkVideoView.this.mRenderView != null) {
                IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                if (IjkVideoView.this.ijkVideoInfo != null && IjkVideoView.this.mVideoWidth > 0 && IjkVideoView.this.mVideoHeight > 0) {
                    IjkVideoView.this.ijkVideoInfo.onVideoSizeChanged(iMediaPlayer, IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight, IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                }
                if (IjkVideoView.this.mRenderView.shouldWaitForResize() && (IjkVideoView.this.mSurfaceWidth != IjkVideoView.this.mVideoWidth || IjkVideoView.this.mSurfaceHeight != IjkVideoView.this.mVideoHeight)) {
                    return;
                }
                if (IjkVideoView.this.mTargetState == 3) {
                    IjkVideoView.this.start();
                } else if (!IjkVideoView.this.isPlaying() && access$1000 == 0) {
                    int currentPosition = IjkVideoView.this.getCurrentPosition();
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public IRenderView mRenderView;
    IRenderView.IRenderCallback mSHCallback = new IRenderView.IRenderCallback() {
        public void onSurfaceChanged(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder, int i, int i2, int i3) {
            if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                Log.e(IjkVideoView.this.TAG, "onSurfaceChanged: unmatched render callback\n");
                return;
            }
            int unused = IjkVideoView.this.mSurfaceWidth = i2;
            int unused2 = IjkVideoView.this.mSurfaceHeight = i3;
            boolean z = false;
            boolean z2 = IjkVideoView.this.mTargetState == 3;
            if (!IjkVideoView.this.mRenderView.shouldWaitForResize() || (IjkVideoView.this.mVideoWidth == i2 && IjkVideoView.this.mVideoHeight == i3)) {
                z = true;
            }
            if (IjkVideoView.this.mMediaPlayer != null && z2 && z) {
                if (IjkVideoView.this.mSeekWhenPrepared != 0) {
                    IjkVideoView.this.seekTo(IjkVideoView.this.mSeekWhenPrepared);
                }
                IjkVideoView.this.start();
            }
            if (IjkVideoView.this.ijkVideoInfo != null) {
                IjkVideoView.this.ijkVideoInfo.onSurfaceChanged(i2, i3);
            }
        }

        public void onSurfaceCreated(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder, int i, int i2) {
            if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                Log.e(IjkVideoView.this.TAG, "onSurfaceCreated: unmatched render callback\n");
                return;
            }
            IRenderView.ISurfaceHolder unused = IjkVideoView.this.mSurfaceHolder = iSurfaceHolder;
            if (IjkVideoView.this.mMediaPlayer != null) {
                IjkVideoView.this.bindSurfaceHolder(IjkVideoView.this.mMediaPlayer, iSurfaceHolder);
            } else {
                IjkVideoView.this.openVideo();
            }
            if (IjkVideoView.this.ijkVideoInfo != null) {
                IjkVideoView.this.ijkVideoInfo.onSurfaceCreated(i, i2);
            }
        }

        public void onSurfaceDestroyed(@NonNull IRenderView.ISurfaceHolder iSurfaceHolder) {
            if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                Log.e(IjkVideoView.this.TAG, "onSurfaceDestroyed: unmatched render callback\n");
                return;
            }
            IRenderView.ISurfaceHolder unused = IjkVideoView.this.mSurfaceHolder = null;
            IjkVideoView.this.releaseWithoutStop();
        }
    };
    private IMediaPlayer.OnSeekCompleteListener mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() {
        public void onSeekComplete(IMediaPlayer iMediaPlayer) {
            long unused = IjkVideoView.this.mSeekEndTime = System.currentTimeMillis();
        }
    };
    /* access modifiers changed from: private */
    public long mSeekEndTime = 0;
    private long mSeekStartTime = 0;
    /* access modifiers changed from: private */
    public int mSeekWhenPrepared;
    private Settings mSettings;
    IMediaPlayer.OnVideoSizeChangedListener mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() {
        public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
            int unused = IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
            int unused2 = IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
            int unused3 = IjkVideoView.this.mVideoSarNum = iMediaPlayer.getVideoSarNum();
            int unused4 = IjkVideoView.this.mVideoSarDen = iMediaPlayer.getVideoSarDen();
            String access$400 = IjkVideoView.this.TAG;
            LogUtil.a(access$400, "mVideoWidth:" + IjkVideoView.this.mVideoWidth + " mVideoHeight:" + IjkVideoView.this.mVideoHeight);
            if (IjkVideoView.this.mVideoWidth != 0 && IjkVideoView.this.mVideoHeight != 0) {
                if (IjkVideoView.this.mRenderView != null) {
                    IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                    IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                }
                if (IjkVideoView.this.ijkVideoInfo != null) {
                    IjkVideoView.this.ijkVideoInfo.onVideoSizeChanged(iMediaPlayer, i, i2, i3, i4);
                }
                IjkVideoView.this.requestLayout();
            }
        }
    };
    /* access modifiers changed from: private */
    public int mSurfaceHeight;
    /* access modifiers changed from: private */
    public IRenderView.ISurfaceHolder mSurfaceHolder = null;
    /* access modifiers changed from: private */
    public int mSurfaceWidth;
    /* access modifiers changed from: private */
    public int mTargetState = 0;
    private Uri mUri;
    /* access modifiers changed from: private */
    public int mVideoHeight;
    /* access modifiers changed from: private */
    public int mVideoRotationDegree;
    /* access modifiers changed from: private */
    public int mVideoSarDen;
    /* access modifiers changed from: private */
    public int mVideoSarNum;
    /* access modifiers changed from: private */
    public int mVideoWidth;
    private TextView subtitleDisplay;

    public interface IjkVideoInfo {
        void onPausePlay();

        void onRenderViewClicked(View view);

        void onStartPlay();

        void onStopPlayback();

        void onSurfaceChanged(int i, int i2);

        void onSurfaceCreated(int i, int i2);

        void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4);
    }

    private void attachMediaController() {
    }

    private void initBackground() {
    }

    private void toggleMediaControlsVisibility() {
    }

    public void deselectTrack(int i) {
    }

    public void enterBackground() {
    }

    public int getAudioSessionId() {
        return 0;
    }

    public int getSelectedTrack(int i) {
        return 0;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        return false;
    }

    public void selectTrack(int i) {
    }

    public void setMediaController(IMediaController iMediaController) {
    }

    public void stopBackgroundPlay() {
    }

    public IjkVideoView(Context context) {
        super(context);
        initVideoView(context);
    }

    public IjkVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initVideoView(context);
    }

    public IjkVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initVideoView(context);
    }

    @TargetApi(21)
    public IjkVideoView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        initVideoView(context);
    }

    private void initVideoView(Context context) {
        this.mAppContext = context.getApplicationContext();
        this.mSettings = new Settings(this.mAppContext);
        initBackground();
        initRenders();
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.subtitleDisplay = new TextView(context);
        this.subtitleDisplay.setTextSize(24.0f);
        this.subtitleDisplay.setGravity(17);
        addView(this.subtitleDisplay, new FrameLayout.LayoutParams(-1, -2, 80));
    }

    public IRenderView getRenderView() {
        return this.mRenderView;
    }

    public void setRenderView(IRenderView iRenderView) {
        if (this.mRenderView != null) {
            if (this.mMediaPlayer != null) {
                this.mMediaPlayer.setDisplay((SurfaceHolder) null);
            }
            View view = this.mRenderView.getView();
            this.mRenderView.removeRenderCallback(this.mSHCallback);
            this.mRenderView = null;
            removeView(view);
        }
        if (iRenderView != null) {
            this.mRenderView = iRenderView;
            iRenderView.setAspectRatio(this.mCurrentAspectRatio);
            if (this.mVideoWidth > 0 && this.mVideoHeight > 0) {
                iRenderView.setVideoSize(this.mVideoWidth, this.mVideoHeight);
            }
            if (this.mVideoSarNum > 0 && this.mVideoSarDen > 0) {
                iRenderView.setVideoSampleAspectRatio(this.mVideoSarNum, this.mVideoSarDen);
            }
            View view2 = this.mRenderView.getView();
            if (view2 != null) {
                view2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (IjkVideoView.this.ijkVideoInfo != null) {
                            IjkVideoView.this.ijkVideoInfo.onRenderViewClicked(view);
                        }
                    }
                });
            }
            view2.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 17));
            addView(view2);
            this.mRenderView.addRenderCallback(this.mSHCallback);
            this.mRenderView.setVideoRotation(this.mVideoRotationDegree);
        }
    }

    public void setRender(int i) {
        switch (i) {
            case 0:
                LogUtil.a(this.TAG, "RENDER_NONE");
                setRenderView((IRenderView) null);
                return;
            case 1:
                LogUtil.a(this.TAG, "RENDER_SURFACE_VIEW");
                setRenderView(new SurfaceRenderView(getContext()));
                return;
            case 2:
                LogUtil.a(this.TAG, "RENDER_TEXTURE_VIEW");
                TextureRenderView textureRenderView = new TextureRenderView(getContext());
                if (this.mMediaPlayer != null) {
                    textureRenderView.getSurfaceHolder().bindToMediaPlayer(this.mMediaPlayer);
                    textureRenderView.setVideoSize(this.mMediaPlayer.getVideoWidth(), this.mMediaPlayer.getVideoHeight());
                    textureRenderView.setVideoSampleAspectRatio(this.mMediaPlayer.getVideoSarNum(), this.mMediaPlayer.getVideoSarDen());
                    textureRenderView.setAspectRatio(this.mCurrentAspectRatio);
                }
                setRenderView(textureRenderView);
                return;
            default:
                Log.e(this.TAG, String.format(Locale.getDefault(), "invalid render %d\n", new Object[]{Integer.valueOf(i)}));
                return;
        }
    }

    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    public void setVideoURI(Uri uri) {
        setVideoURI(uri, (Map<String, String>) null);
    }

    public void setVideoURI(Uri uri, Map<String, String> map) {
        this.mUri = uri;
        this.mHeaders = map;
        this.mSeekWhenPrepared = 0;
        openVideo();
        requestLayout();
        invalidate();
    }

    public void stopPlayback() {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.stop();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = 0;
            this.mTargetState = 0;
            ((AudioManager) this.mAppContext.getSystemService("audio")).abandonAudioFocus((AudioManager.OnAudioFocusChangeListener) null);
        }
        if (this.ijkVideoInfo != null) {
            this.ijkVideoInfo.onStopPlayback();
        }
    }

    /* access modifiers changed from: private */
    @TargetApi(23)
    public void openVideo() {
        if (this.mUri != null && this.mSurfaceHolder != null) {
            release(false);
            ((AudioManager) this.mAppContext.getSystemService("audio")).requestAudioFocus((AudioManager.OnAudioFocusChangeListener) null, 3, 1);
            try {
                this.mMediaPlayer = createPlayer(this.mSettings.getPlayer());
                getContext();
                this.mMediaPlayer.setOnPreparedListener(this.mPreparedListener);
                this.mMediaPlayer.setOnVideoSizeChangedListener(this.mSizeChangedListener);
                this.mMediaPlayer.setOnCompletionListener(this.mCompletionListener);
                this.mMediaPlayer.setOnErrorListener(this.mErrorListener);
                this.mMediaPlayer.setOnInfoListener(this.mInfoListener);
                this.mMediaPlayer.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
                this.mMediaPlayer.setOnSeekCompleteListener(this.mSeekCompleteListener);
                this.mMediaPlayer.setVolume(0.0f, 0.0f);
                this.mCurrentBufferPercentage = 0;
                String scheme = this.mUri.getScheme();
                if (Build.VERSION.SDK_INT >= 23 && (TextUtils.isEmpty(scheme) || scheme.equalsIgnoreCase("file"))) {
                    this.mMediaPlayer.setDataSource((IMediaDataSource) new FileMediaDataSource(new File(this.mUri.toString())));
                } else if (Build.VERSION.SDK_INT >= 14) {
                    this.mMediaPlayer.setDataSource(this.mAppContext, this.mUri, this.mHeaders);
                } else {
                    this.mMediaPlayer.setDataSource(this.mUri.toString());
                }
                bindSurfaceHolder(this.mMediaPlayer, this.mSurfaceHolder);
                this.mMediaPlayer.setAudioStreamType(3);
                this.mMediaPlayer.setScreenOnWhilePlaying(true);
                this.mPrepareStartTime = System.currentTimeMillis();
                this.mMediaPlayer.prepareAsync();
                this.mCurrentState = 1;
                attachMediaController();
            } catch (IOException e) {
                String str = this.TAG;
                Log.w(str, "Unable to open content: " + this.mUri, e);
                this.mCurrentState = -1;
                this.mTargetState = -1;
                this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
            } catch (IllegalArgumentException e2) {
                String str2 = this.TAG;
                Log.w(str2, "Unable to open content: " + this.mUri, e2);
                this.mCurrentState = -1;
                this.mTargetState = -1;
                this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
            }
        }
    }

    public void setOnPreparedListener(IMediaPlayer.OnPreparedListener onPreparedListener) {
        this.mOnPreparedListener = onPreparedListener;
    }

    public void setOnCompletionListener(IMediaPlayer.OnCompletionListener onCompletionListener) {
        this.mOnCompletionListener = onCompletionListener;
    }

    public void setOnErrorListener(IMediaPlayer.OnErrorListener onErrorListener) {
        this.mOnErrorListener = onErrorListener;
    }

    public void setOnInfoListener(IMediaPlayer.OnInfoListener onInfoListener) {
        this.mOnInfoListener = onInfoListener;
    }

    /* access modifiers changed from: private */
    public void bindSurfaceHolder(IMediaPlayer iMediaPlayer, IRenderView.ISurfaceHolder iSurfaceHolder) {
        if (iMediaPlayer != null) {
            if (iSurfaceHolder == null) {
                iMediaPlayer.setDisplay((SurfaceHolder) null);
            } else {
                iSurfaceHolder.bindToMediaPlayer(iMediaPlayer);
            }
        }
    }

    public void releaseWithoutStop() {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.setDisplay((SurfaceHolder) null);
        }
    }

    public void release(boolean z) {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.reset();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = 0;
            if (z) {
                this.mTargetState = 0;
            }
            ((AudioManager) this.mAppContext.getSystemService("audio")).abandonAudioFocus((AudioManager.OnAudioFocusChangeListener) null);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = (i == 4 || i == 24 || i == 25 || i == 164 || i == 82 || i == 5 || i == 6) ? false : true;
        if (isInPlaybackState() && z) {
            if (i == 79 || i == 85) {
                if (this.mMediaPlayer.isPlaying()) {
                    pause();
                } else {
                    start();
                }
                return true;
            } else if (i == 126) {
                if (!this.mMediaPlayer.isPlaying()) {
                    start();
                }
                return true;
            } else if (i == 86 || i == 127) {
                if (this.mMediaPlayer.isPlaying()) {
                    pause();
                }
                return true;
            } else {
                toggleMediaControlsVisibility();
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void start() {
        if (isInPlaybackState()) {
            this.mMediaPlayer.start();
            this.mCurrentState = 3;
            setRender(this.mCurrentRender);
        }
        if (this.ijkVideoInfo != null) {
            this.ijkVideoInfo.onStartPlay();
        }
        this.mTargetState = 3;
    }

    public void pause() {
        if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
            this.mCurrentState = 4;
        }
        if (this.ijkVideoInfo != null) {
            this.ijkVideoInfo.onPausePlay();
        }
        this.mTargetState = 4;
    }

    public void suspend() {
        release(false);
    }

    public void resume() {
        openVideo();
    }

    public int getDuration() {
        if (isInPlaybackState()) {
            return (int) this.mMediaPlayer.getDuration();
        }
        return -1;
    }

    public int getCurrentPosition() {
        if (isInPlaybackState()) {
            return (int) this.mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public void seekTo(int i) {
        if (isInPlaybackState()) {
            this.mSeekStartTime = System.currentTimeMillis();
            this.mMediaPlayer.seekTo((long) i);
            this.mSeekWhenPrepared = 0;
            return;
        }
        this.mSeekWhenPrepared = i;
    }

    public boolean isPlaying() {
        return isInPlaybackState() && this.mMediaPlayer.isPlaying();
    }

    public int getBufferPercentage() {
        if (this.mMediaPlayer != null) {
            return this.mCurrentBufferPercentage;
        }
        return 0;
    }

    private boolean isInPlaybackState() {
        return (this.mMediaPlayer == null || this.mCurrentState == -1 || this.mCurrentState == 0 || this.mCurrentState == 1) ? false : true;
    }

    public boolean canPause() {
        return this.mCanPause;
    }

    public boolean canSeekBackward() {
        return this.mCanSeekBack;
    }

    public boolean canSeekForward() {
        return this.mCanSeekForward;
    }

    public int toggleAspectRatio() {
        this.mCurrentAspectRatioIndex++;
        this.mCurrentAspectRatioIndex %= s_allAspectRatio.length;
        this.mCurrentAspectRatio = s_allAspectRatio[this.mCurrentAspectRatioIndex];
        if (this.mRenderView != null) {
            this.mRenderView.setAspectRatio(this.mCurrentAspectRatio);
        }
        return this.mCurrentAspectRatio;
    }

    private void initRenders() {
        this.mAllRenders.clear();
        if (this.mSettings.getEnableSurfaceView()) {
            this.mAllRenders.add(1);
        }
        if (this.mSettings.getEnableTextureView() && Build.VERSION.SDK_INT >= 14) {
            this.mAllRenders.add(2);
        }
        if (this.mSettings.getEnableNoView()) {
            this.mAllRenders.add(0);
        }
        if (this.mAllRenders.isEmpty()) {
            this.mAllRenders.add(2);
        }
        this.mCurrentRender = this.mAllRenders.get(this.mCurrentRenderIndex).intValue();
        setRender(this.mCurrentRender);
    }

    public int toggleRender() {
        this.mCurrentRenderIndex++;
        this.mCurrentRenderIndex %= this.mAllRenders.size();
        this.mCurrentRender = this.mAllRenders.get(this.mCurrentRenderIndex).intValue();
        setRender(this.mCurrentRender);
        return this.mCurrentRender;
    }

    @NonNull
    public static String getRenderText(Context context, int i) {
        switch (i) {
            case 0:
                return context.getString(R.string.VideoView_render_none);
            case 1:
                return context.getString(R.string.VideoView_render_surface_view);
            case 2:
                return context.getString(R.string.VideoView_render_texture_view);
            default:
                return context.getString(R.string.N_A);
        }
    }

    public int togglePlayer() {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.release();
        }
        if (this.mRenderView != null) {
            this.mRenderView.getView().invalidate();
        }
        openVideo();
        return this.mSettings.getPlayer();
    }

    @NonNull
    public static String getPlayerText(Context context, int i) {
        switch (i) {
            case 1:
                return context.getString(R.string.VideoView_player_AndroidMediaPlayer);
            case 2:
                return context.getString(R.string.VideoView_player_IjkMediaPlayer);
            case 3:
                return context.getString(R.string.VideoView_player_IjkExoMediaPlayer);
            default:
                return context.getString(R.string.N_A);
        }
    }

    public IMediaPlayer createPlayer(int i) {
        IjkMediaPlayer ijkMediaPlayer;
        if (this.mUri != null) {
            ijkMediaPlayer = new IjkMediaPlayer();
            IjkMediaPlayer.native_setLogLevel(2);
            if (this.mSettings.getUsingMediaCodec()) {
                ijkMediaPlayer.setOption(4, "mediacodec", 1);
                if (this.mSettings.getUsingMediaCodecAutoRotate()) {
                    ijkMediaPlayer.setOption(4, "mediacodec-auto-rotate", 1);
                } else {
                    ijkMediaPlayer.setOption(4, "mediacodec-auto-rotate", 0);
                }
                if (this.mSettings.getMediaCodecHandleResolutionChange()) {
                    ijkMediaPlayer.setOption(4, "mediacodec-handle-resolution-change", 1);
                } else {
                    ijkMediaPlayer.setOption(4, "mediacodec-handle-resolution-change", 0);
                }
            } else {
                ijkMediaPlayer.setOption(4, "mediacodec", 0);
            }
            if (this.mSettings.getUsingOpenSLES()) {
                ijkMediaPlayer.setOption(4, "opensles", 1);
            } else {
                ijkMediaPlayer.setOption(4, "opensles", 0);
            }
            String pixelFormat = this.mSettings.getPixelFormat();
            if (TextUtils.isEmpty(pixelFormat)) {
                ijkMediaPlayer.setOption(4, "overlay-format", 842225234);
            } else {
                ijkMediaPlayer.setOption(4, "overlay-format", pixelFormat);
            }
            ijkMediaPlayer.setOption(4, "framedrop", 1);
            ijkMediaPlayer.setOption(4, "start-on-prepared", 0);
            ijkMediaPlayer.setOption(1, "http-detect-range-support", 0);
            ijkMediaPlayer.setOption(2, "skip_loop_filter", 48);
        } else {
            ijkMediaPlayer = null;
        }
        return this.mSettings.getEnableDetachedSurfaceTextureView() ? new TextureMediaPlayer(ijkMediaPlayer) : ijkMediaPlayer;
    }

    public boolean isBackgroundPlayEnabled() {
        return this.mEnableBackgroundPlay;
    }

    public void showMediaInfo() {
        if (this.mMediaPlayer != null) {
            MediaPlayerCompat.getSelectedTrack(this.mMediaPlayer, 1);
            MediaPlayerCompat.getSelectedTrack(this.mMediaPlayer, 2);
            MediaPlayerCompat.getSelectedTrack(this.mMediaPlayer, 3);
        }
    }

    private String buildResolution(int i, int i2, int i3, int i4) {
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append(" x ");
        sb.append(i2);
        if (i3 > 1 || i4 > 1) {
            sb.append(Operators.ARRAY_START_STR);
            sb.append(i3);
            sb.append(":");
            sb.append(i4);
            sb.append(Operators.ARRAY_END_STR);
        }
        return sb.toString();
    }

    private String buildTimeMilli(long j) {
        long j2 = j / 1000;
        long j3 = j2 / 3600;
        long j4 = (j2 % 3600) / 60;
        long j5 = j2 % 60;
        if (j <= 0) {
            return "--:--";
        }
        if (j3 >= 100) {
            return String.format(Locale.US, "%d:%02d:%02d", new Object[]{Long.valueOf(j3), Long.valueOf(j4), Long.valueOf(j5)});
        } else if (j3 > 0) {
            return String.format(Locale.US, "%02d:%02d:%02d", new Object[]{Long.valueOf(j3), Long.valueOf(j4), Long.valueOf(j5)});
        } else {
            return String.format(Locale.US, "%02d:%02d", new Object[]{Long.valueOf(j4), Long.valueOf(j5)});
        }
    }

    private String buildTrackType(int i) {
        Context context = getContext();
        switch (i) {
            case 1:
                return context.getString(R.string.TrackType_video);
            case 2:
                return context.getString(R.string.TrackType_audio);
            case 3:
                return context.getString(R.string.TrackType_timedtext);
            case 4:
                return context.getString(R.string.TrackType_subtitle);
            case 5:
                return context.getString(R.string.TrackType_metadata);
            default:
                return context.getString(R.string.TrackType_unknown);
        }
    }

    private String buildLanguage(String str) {
        return TextUtils.isEmpty(str) ? C.LANGUAGE_UNDETERMINED : str;
    }

    public ITrackInfo[] getTrackInfo() {
        if (this.mMediaPlayer == null) {
            return null;
        }
        return this.mMediaPlayer.getTrackInfo();
    }

    public void mute(boolean z) {
        if (this.mMediaPlayer == null) {
            return;
        }
        if (z) {
            this.mMediaPlayer.setVolume(0.0f, 0.0f);
        } else {
            this.mMediaPlayer.setVolume(100.0f, 100.0f);
        }
    }

    public Bitmap snapshot() {
        if (this.mRenderView == null || !(this.mRenderView instanceof TextureRenderView)) {
            return null;
        }
        return ((TextureRenderView) this.mRenderView.getView()).getBitmap();
    }

    public void updateRender() {
        setRender(this.mCurrentRender);
    }

    public int getState() {
        return this.mCurrentState;
    }
}
