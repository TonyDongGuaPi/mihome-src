package com.taobao.weex.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;
import com.taobao.weex.ui.view.gesture.WXGesture;
import com.taobao.weex.ui.view.gesture.WXGestureObservable;
import com.taobao.weex.utils.WXResourceUtils;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXVideoView extends VideoView implements WXGestureObservable {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private VideoPlayListener mVideoPauseListener;
    private WXGesture wxGesture;

    public interface VideoPlayListener {
        void onPause();

        void onStart();
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-5696741019089788051L, "com/taobao/weex/ui/view/WXVideoView", 16);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXVideoView(Context context) {
        super(context);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public void registerGestureListener(WXGesture wXGesture) {
        boolean[] $jacocoInit = $jacocoInit();
        this.wxGesture = wXGesture;
        $jacocoInit[1] = true;
    }

    public WXGesture getGestureListener() {
        boolean[] $jacocoInit = $jacocoInit();
        WXGesture wXGesture = this.wxGesture;
        $jacocoInit[2] = true;
        return wXGesture;
    }

    public void setOnVideoPauseListener(VideoPlayListener videoPlayListener) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mVideoPauseListener = videoPlayListener;
        $jacocoInit[3] = true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        if (this.wxGesture == null) {
            $jacocoInit[4] = true;
        } else {
            $jacocoInit[5] = true;
            onTouchEvent |= this.wxGesture.onTouch(this, motionEvent);
            $jacocoInit[6] = true;
        }
        $jacocoInit[7] = true;
        return onTouchEvent;
    }

    public void start() {
        boolean[] $jacocoInit = $jacocoInit();
        super.start();
        if (this.mVideoPauseListener == null) {
            $jacocoInit[8] = true;
        } else {
            $jacocoInit[9] = true;
            this.mVideoPauseListener.onStart();
            $jacocoInit[10] = true;
        }
        $jacocoInit[11] = true;
    }

    public void pause() {
        boolean[] $jacocoInit = $jacocoInit();
        super.pause();
        if (this.mVideoPauseListener == null) {
            $jacocoInit[12] = true;
        } else {
            $jacocoInit[13] = true;
            this.mVideoPauseListener.onPause();
            $jacocoInit[14] = true;
        }
        $jacocoInit[15] = true;
    }

    public static class Wrapper extends FrameLayout implements ViewTreeObserver.OnGlobalLayoutListener {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private boolean mControls = true;
        private MediaController mMediaController;
        private MediaPlayer.OnCompletionListener mOnCompletionListener;
        private MediaPlayer.OnErrorListener mOnErrorListener;
        private MediaPlayer.OnPreparedListener mOnPreparedListener;
        private ProgressBar mProgressBar;
        private Uri mUri;
        private VideoPlayListener mVideoPlayListener;
        private WXVideoView mVideoView;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-3765808994665069268L, "com/taobao/weex/ui/view/WXVideoView$Wrapper", 95);
            $jacocoData = a2;
            return a2;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Wrapper(Context context) {
            super(context);
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[0] = true;
            init(context);
            $jacocoInit[1] = true;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Wrapper(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[2] = true;
            init(context);
            $jacocoInit[3] = true;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Wrapper(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[4] = true;
            init(context);
            $jacocoInit[5] = true;
        }

        private void init(Context context) {
            boolean[] $jacocoInit = $jacocoInit();
            setBackgroundColor(WXResourceUtils.getColor("#ee000000"));
            $jacocoInit[6] = true;
            this.mProgressBar = new ProgressBar(context);
            $jacocoInit[7] = true;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            $jacocoInit[8] = true;
            this.mProgressBar.setLayoutParams(layoutParams);
            layoutParams.gravity = 17;
            $jacocoInit[9] = true;
            addView(this.mProgressBar);
            $jacocoInit[10] = true;
            getViewTreeObserver().addOnGlobalLayoutListener(this);
            $jacocoInit[11] = true;
        }

        public ProgressBar getProgressBar() {
            boolean[] $jacocoInit = $jacocoInit();
            ProgressBar progressBar = this.mProgressBar;
            $jacocoInit[12] = true;
            return progressBar;
        }

        @Nullable
        public WXVideoView getVideoView() {
            boolean[] $jacocoInit = $jacocoInit();
            WXVideoView wXVideoView = this.mVideoView;
            $jacocoInit[13] = true;
            return wXVideoView;
        }

        @NonNull
        public WXVideoView createIfNotExist() {
            boolean[] $jacocoInit = $jacocoInit();
            if (this.mVideoView != null) {
                $jacocoInit[14] = true;
            } else {
                $jacocoInit[15] = true;
                createVideoView();
                $jacocoInit[16] = true;
            }
            WXVideoView wXVideoView = this.mVideoView;
            $jacocoInit[17] = true;
            return wXVideoView;
        }

        @Nullable
        public MediaController getMediaController() {
            boolean[] $jacocoInit = $jacocoInit();
            MediaController mediaController = this.mMediaController;
            $jacocoInit[18] = true;
            return mediaController;
        }

        public void setVideoURI(Uri uri) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mUri = uri;
            if (this.mVideoView == null) {
                $jacocoInit[19] = true;
            } else {
                $jacocoInit[20] = true;
                this.mVideoView.setVideoURI(uri);
                $jacocoInit[21] = true;
            }
            $jacocoInit[22] = true;
        }

        public void start() {
            boolean[] $jacocoInit = $jacocoInit();
            if (this.mVideoView == null) {
                $jacocoInit[23] = true;
            } else {
                $jacocoInit[24] = true;
                this.mVideoView.start();
                $jacocoInit[25] = true;
            }
            $jacocoInit[26] = true;
        }

        public void pause() {
            boolean[] $jacocoInit = $jacocoInit();
            if (this.mVideoView == null) {
                $jacocoInit[27] = true;
            } else {
                $jacocoInit[28] = true;
                this.mVideoView.pause();
                $jacocoInit[29] = true;
            }
            $jacocoInit[30] = true;
        }

        public void stopPlayback() {
            boolean[] $jacocoInit = $jacocoInit();
            if (this.mVideoView == null) {
                $jacocoInit[31] = true;
            } else {
                $jacocoInit[32] = true;
                this.mVideoView.stopPlayback();
                $jacocoInit[33] = true;
            }
            $jacocoInit[34] = true;
        }

        public void resume() {
            boolean[] $jacocoInit = $jacocoInit();
            if (this.mVideoView == null) {
                $jacocoInit[35] = true;
            } else {
                $jacocoInit[36] = true;
                this.mVideoView.resume();
                $jacocoInit[37] = true;
            }
            $jacocoInit[38] = true;
        }

        public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mOnErrorListener = onErrorListener;
            if (this.mVideoView == null) {
                $jacocoInit[39] = true;
            } else {
                $jacocoInit[40] = true;
                this.mVideoView.setOnErrorListener(onErrorListener);
                $jacocoInit[41] = true;
            }
            $jacocoInit[42] = true;
        }

        public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mOnPreparedListener = onPreparedListener;
            if (this.mVideoView == null) {
                $jacocoInit[43] = true;
            } else {
                $jacocoInit[44] = true;
                this.mVideoView.setOnPreparedListener(onPreparedListener);
                $jacocoInit[45] = true;
            }
            $jacocoInit[46] = true;
        }

        public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mOnCompletionListener = onCompletionListener;
            if (this.mVideoView == null) {
                $jacocoInit[47] = true;
            } else {
                $jacocoInit[48] = true;
                this.mVideoView.setOnCompletionListener(onCompletionListener);
                $jacocoInit[49] = true;
            }
            $jacocoInit[50] = true;
        }

        public void setOnVideoPauseListener(VideoPlayListener videoPlayListener) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mVideoPlayListener = videoPlayListener;
            if (this.mVideoView == null) {
                $jacocoInit[51] = true;
            } else {
                $jacocoInit[52] = true;
                this.mVideoView.setOnVideoPauseListener(videoPlayListener);
                $jacocoInit[53] = true;
            }
            $jacocoInit[54] = true;
        }

        public void setControls(boolean z) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mControls = z;
            if (this.mVideoView == null) {
                $jacocoInit[55] = true;
            } else if (this.mMediaController == null) {
                $jacocoInit[56] = true;
            } else if (!this.mControls) {
                $jacocoInit[57] = true;
                this.mMediaController.setVisibility(8);
                $jacocoInit[58] = true;
            } else {
                this.mMediaController.setVisibility(0);
                $jacocoInit[59] = true;
            }
            $jacocoInit[60] = true;
        }

        private synchronized void createVideoView() {
            boolean[] $jacocoInit = $jacocoInit();
            if (this.mVideoView != null) {
                $jacocoInit[61] = true;
                return;
            }
            Context context = getContext();
            $jacocoInit[62] = true;
            WXVideoView wXVideoView = new WXVideoView(context);
            $jacocoInit[63] = true;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            layoutParams.gravity = 17;
            $jacocoInit[64] = true;
            wXVideoView.setLayoutParams(layoutParams);
            $jacocoInit[65] = true;
            addView(wXVideoView, 0);
            $jacocoInit[66] = true;
            wXVideoView.setOnErrorListener(this.mOnErrorListener);
            $jacocoInit[67] = true;
            wXVideoView.setOnPreparedListener(this.mOnPreparedListener);
            $jacocoInit[68] = true;
            wXVideoView.setOnCompletionListener(this.mOnCompletionListener);
            $jacocoInit[69] = true;
            wXVideoView.setOnVideoPauseListener(this.mVideoPlayListener);
            $jacocoInit[70] = true;
            MediaController mediaController = new MediaController(context);
            $jacocoInit[71] = true;
            mediaController.setAnchorView(this);
            $jacocoInit[72] = true;
            wXVideoView.setMediaController(mediaController);
            $jacocoInit[73] = true;
            mediaController.setMediaPlayer(wXVideoView);
            if (!this.mControls) {
                $jacocoInit[74] = true;
                mediaController.setVisibility(8);
                $jacocoInit[75] = true;
            } else {
                mediaController.setVisibility(0);
                $jacocoInit[76] = true;
            }
            this.mMediaController = mediaController;
            this.mVideoView = wXVideoView;
            if (this.mUri == null) {
                $jacocoInit[77] = true;
            } else {
                $jacocoInit[78] = true;
                setVideoURI(this.mUri);
                $jacocoInit[79] = true;
            }
            $jacocoInit[80] = true;
        }

        @SuppressLint({"NewApi"})
        private void removeSelfFromViewTreeObserver() {
            boolean[] $jacocoInit = $jacocoInit();
            if (Build.VERSION.SDK_INT >= 16) {
                $jacocoInit[81] = true;
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                $jacocoInit[82] = true;
            } else {
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
                $jacocoInit[83] = true;
            }
            $jacocoInit[84] = true;
        }

        public boolean createVideoViewIfVisible() {
            boolean[] $jacocoInit = $jacocoInit();
            Rect rect = new Rect();
            if (this.mVideoView != null) {
                $jacocoInit[85] = true;
                return true;
            }
            if (!getGlobalVisibleRect(rect)) {
                $jacocoInit[86] = true;
            } else if (rect.isEmpty()) {
                $jacocoInit[87] = true;
            } else {
                $jacocoInit[88] = true;
                createVideoView();
                $jacocoInit[89] = true;
                return true;
            }
            $jacocoInit[90] = true;
            return false;
        }

        public void onGlobalLayout() {
            boolean[] $jacocoInit = $jacocoInit();
            if (!createVideoViewIfVisible()) {
                $jacocoInit[91] = true;
            } else {
                $jacocoInit[92] = true;
                removeSelfFromViewTreeObserver();
                $jacocoInit[93] = true;
            }
            $jacocoInit[94] = true;
        }
    }
}
