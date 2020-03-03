package com.xiaomi.smarthome.miio.camera.cloudstorage.exopackage;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.google.android.exoplayer2.ControlDispatcher;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackPreparer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ErrorMessageProvider;
import com.google.android.exoplayer2.video.VideoListener;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.plugin.mpk.MJExoPlayerImpl;
import java.util.List;

public class MJExoPlayerViewP extends FrameLayout {
    private static final int SURFACE_TYPE_NONE = 0;
    private static final int SURFACE_TYPE_SURFACE_VIEW = 1;
    private static final int SURFACE_TYPE_TEXTURE_VIEW = 2;
    private final ComponentListener componentListener;
    /* access modifiers changed from: private */
    public final AspectRatioFrameLayout contentFrame;
    private boolean controllerAutoShow;
    /* access modifiers changed from: private */
    public boolean controllerHideDuringAds;
    private boolean controllerHideOnTouch;
    private int controllerShowTimeoutMs;
    @Nullable
    private CharSequence customErrorMessage;
    private Bitmap defaultArtwork;
    @Nullable
    private ErrorMessageProvider<? super ExoPlaybackException> errorMessageProvider;
    /* access modifiers changed from: private */
    public MJExoPlayerImpl.ExoPlayerBridgeListener exoPlayerBridgeListener;
    private boolean keepContentOnPlayerReset;
    private Player player;
    /* access modifiers changed from: private */
    public PlayerViewListener playerViewListener;
    private boolean showBuffering;
    /* access modifiers changed from: private */
    public final View surfaceView;
    /* access modifiers changed from: private */
    public int textureViewRotation;
    private boolean useArtwork;
    private boolean useController;

    public interface PlayerViewListener {
        void onRenderedFirstFrame();

        void onVideoSizeChanged(int i, int i2, int i3, float f);
    }

    private void closeShutter() {
    }

    private void hideArtwork() {
    }

    @SuppressLint({"InlinedApi"})
    private boolean isDpadKey(int i) {
        return i == 19 || i == 270 || i == 22 || i == 271 || i == 20 || i == 269 || i == 21 || i == 268 || i == 23;
    }

    /* access modifiers changed from: private */
    public void updateBuffering() {
    }

    /* access modifiers changed from: private */
    public void updateErrorMessage() {
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return false;
    }

    public boolean dispatchMediaKeyEvent(KeyEvent keyEvent) {
        return false;
    }

    public FrameLayout getOverlayFrameLayout() {
        return null;
    }

    public void hideController() {
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public void setControlDispatcher(@Nullable ControlDispatcher controlDispatcher) {
    }

    public void setControllerHideOnTouch(boolean z) {
    }

    public void setControllerShowTimeoutMs(int i) {
    }

    public void setControllerVisibilityListener(PlayerControlView.VisibilityListener visibilityListener) {
    }

    public void setCustomErrorMessage(@Nullable CharSequence charSequence) {
    }

    public void setExtraAdGroupMarkers(@Nullable long[] jArr, @Nullable boolean[] zArr) {
    }

    public void setFastForwardIncrementMs(int i) {
    }

    public void setPlaybackPreparer(@Nullable PlaybackPreparer playbackPreparer) {
    }

    public void setRepeatToggleModes(int i) {
    }

    public void setRewindIncrementMs(int i) {
    }

    public void setShowMultiWindowTimeBar(boolean z) {
    }

    public void setShowShuffleButton(boolean z) {
    }

    public void setShutterBackgroundColor(int i) {
    }

    public void setUseArtwork(boolean z) {
    }

    public void setUseController(boolean z) {
    }

    public void setExoPlayerBridgeListener(MJExoPlayerImpl.ExoPlayerBridgeListener exoPlayerBridgeListener2) {
        this.exoPlayerBridgeListener = exoPlayerBridgeListener2;
    }

    public MJExoPlayerImpl.ExoPlayerBridgeListener getExoPlayerBridgeListener() {
        return this.exoPlayerBridgeListener;
    }

    public void setPlayerViewListener(PlayerViewListener playerViewListener2) {
        this.playerViewListener = playerViewListener2;
    }

    public PlayerViewListener getPlayerViewListener() {
        return this.playerViewListener;
    }

    public MJExoPlayerViewP(Context context) {
        this(context, (AttributeSet) null);
    }

    public MJExoPlayerViewP(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MJExoPlayerViewP(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        int i2;
        int i3;
        if (isInEditMode()) {
            this.contentFrame = null;
            this.surfaceView = null;
            this.componentListener = null;
            return;
        }
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.PlayerView, 0, 0);
            try {
                obtainStyledAttributes.hasValue(14);
                obtainStyledAttributes.getColor(14, 0);
                obtainStyledAttributes.getResourceId(7, R.layout.exo_player_view);
                obtainStyledAttributes.getBoolean(16, true);
                obtainStyledAttributes.getResourceId(2, 0);
                obtainStyledAttributes.getBoolean(17, true);
                i2 = obtainStyledAttributes.getInt(15, 2);
                i3 = obtainStyledAttributes.getInt(9, 0);
                obtainStyledAttributes.getInt(13, 5000);
                obtainStyledAttributes.getBoolean(5, true);
                obtainStyledAttributes.getBoolean(0, true);
                obtainStyledAttributes.getBoolean(11, false);
                this.keepContentOnPlayerReset = obtainStyledAttributes.getBoolean(6, this.keepContentOnPlayerReset);
                obtainStyledAttributes.getBoolean(4, true);
            } finally {
                obtainStyledAttributes.recycle();
            }
        } else {
            i2 = 2;
            i3 = 0;
        }
        this.componentListener = new ComponentListener();
        setDescendantFocusability(262144);
        this.contentFrame = new AspectRatioFrameLayout(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        addView(this.contentFrame, layoutParams);
        if (this.contentFrame != null) {
            setResizeModeRaw(this.contentFrame, i3);
        }
        if (i2 != 0) {
            ViewGroup.LayoutParams layoutParams2 = new ViewGroup.LayoutParams(-1, -1);
            this.surfaceView = i2 == 2 ? new TextureView(context) : new SurfaceView(context);
            this.surfaceView.setLayoutParams(layoutParams2);
            this.contentFrame.addView(this.surfaceView, 0);
            return;
        }
        this.surfaceView = null;
    }

    public static void switchTargetView(@NonNull Player player2, @Nullable MJExoPlayerViewP mJExoPlayerViewP, @Nullable MJExoPlayerViewP mJExoPlayerViewP2) {
        if (mJExoPlayerViewP != mJExoPlayerViewP2) {
            if (mJExoPlayerViewP2 != null) {
                mJExoPlayerViewP2.setPlayer(player2);
            }
            if (mJExoPlayerViewP != null) {
                mJExoPlayerViewP.setPlayer((Player) null);
            }
        }
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player2) {
        if (this.player != player2) {
            if (this.player != null) {
                this.player.removeListener(this.componentListener);
                Player.VideoComponent videoComponent = this.player.getVideoComponent();
                if (videoComponent != null) {
                    videoComponent.removeVideoListener(this.componentListener);
                    if (this.surfaceView instanceof TextureView) {
                        videoComponent.clearVideoTextureView((TextureView) this.surfaceView);
                    } else if (this.surfaceView instanceof SurfaceView) {
                        videoComponent.clearVideoSurfaceView((SurfaceView) this.surfaceView);
                    }
                }
                Player.TextComponent textComponent = this.player.getTextComponent();
                if (textComponent != null) {
                    textComponent.removeTextOutput(this.componentListener);
                }
            }
            this.player = player2;
            updateBuffering();
            updateErrorMessage();
            updateForCurrentTrackSelections(true);
            if (player2 != null) {
                Player.VideoComponent videoComponent2 = player2.getVideoComponent();
                if (videoComponent2 != null) {
                    if (this.surfaceView instanceof TextureView) {
                        videoComponent2.setVideoTextureView((TextureView) this.surfaceView);
                    } else if (this.surfaceView instanceof SurfaceView) {
                        videoComponent2.setVideoSurfaceView((SurfaceView) this.surfaceView);
                    }
                    videoComponent2.addVideoListener(this.componentListener);
                }
                Player.TextComponent textComponent2 = player2.getTextComponent();
                if (textComponent2 != null) {
                    textComponent2.addTextOutput(this.componentListener);
                }
                player2.addListener(this.componentListener);
                maybeShowController(false);
                return;
            }
            hideController();
        }
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        if (this.surfaceView instanceof SurfaceView) {
            this.surfaceView.setVisibility(i);
        }
    }

    public void setResizeMode(int i) {
        Assertions.checkState(this.contentFrame != null);
        this.contentFrame.setResizeMode(i);
    }

    public int getResizeMode() {
        Assertions.checkState(this.contentFrame != null);
        return this.contentFrame.getResizeMode();
    }

    public boolean getUseArtwork() {
        return this.useArtwork;
    }

    public Bitmap getDefaultArtwork() {
        return this.defaultArtwork;
    }

    public void setDefaultArtwork(Bitmap bitmap) {
        if (this.defaultArtwork != bitmap) {
            this.defaultArtwork = bitmap;
            updateForCurrentTrackSelections(false);
        }
    }

    public boolean getUseController() {
        return this.useController;
    }

    public void setKeepContentOnPlayerReset(boolean z) {
        if (this.keepContentOnPlayerReset != z) {
            this.keepContentOnPlayerReset = z;
            updateForCurrentTrackSelections(false);
        }
    }

    public void setShowBuffering(boolean z) {
        if (this.showBuffering != z) {
            this.showBuffering = z;
            updateBuffering();
        }
    }

    public void setErrorMessageProvider(@Nullable ErrorMessageProvider<? super ExoPlaybackException> errorMessageProvider2) {
        if (this.errorMessageProvider != errorMessageProvider2) {
            this.errorMessageProvider = errorMessageProvider2;
            updateErrorMessage();
        }
    }

    public void showController() {
        showController(shouldShowControllerIndefinitely());
    }

    public int getControllerShowTimeoutMs() {
        return this.controllerShowTimeoutMs;
    }

    public boolean getControllerHideOnTouch() {
        return this.controllerHideOnTouch;
    }

    public boolean getControllerAutoShow() {
        return this.controllerAutoShow;
    }

    public void setControllerAutoShow(boolean z) {
        this.controllerAutoShow = z;
    }

    public void setControllerHideDuringAds(boolean z) {
        this.controllerHideDuringAds = z;
    }

    public void setAspectRatioListener(AspectRatioFrameLayout.AspectRatioListener aspectRatioListener) {
        Assertions.checkState(this.contentFrame != null);
        this.contentFrame.setAspectRatioListener(aspectRatioListener);
    }

    public View getVideoSurfaceView() {
        return this.surfaceView;
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (!this.useController || this.player == null) {
            return false;
        }
        maybeShowController(true);
        return true;
    }

    /* access modifiers changed from: private */
    public void maybeShowController(boolean z) {
        if (isPlayingAd() && !this.controllerHideDuringAds) {
        }
    }

    private boolean shouldShowControllerIndefinitely() {
        if (this.player == null) {
            return true;
        }
        int playbackState = this.player.getPlaybackState();
        if (!this.controllerAutoShow || (playbackState != 1 && playbackState != 4 && this.player.getPlayWhenReady())) {
            return false;
        }
        return true;
    }

    private void showController(boolean z) {
        if (this.useController) {
        }
    }

    /* access modifiers changed from: private */
    public boolean isPlayingAd() {
        return this.player != null && this.player.isPlayingAd() && this.player.getPlayWhenReady();
    }

    /* access modifiers changed from: private */
    public void updateForCurrentTrackSelections(boolean z) {
        if (this.player != null && !this.player.getCurrentTrackGroups().isEmpty()) {
            if (z && !this.keepContentOnPlayerReset) {
                closeShutter();
            }
            TrackSelectionArray currentTrackSelections = this.player.getCurrentTrackSelections();
            int i = 0;
            while (i < currentTrackSelections.length) {
                if (this.player.getRendererType(i) != 2 || currentTrackSelections.get(i) == null) {
                    i++;
                } else {
                    hideArtwork();
                    return;
                }
            }
            closeShutter();
            if (this.useArtwork) {
                for (int i2 = 0; i2 < currentTrackSelections.length; i2++) {
                    TrackSelection trackSelection = currentTrackSelections.get(i2);
                    if (trackSelection != null) {
                        int i3 = 0;
                        while (i3 < trackSelection.length()) {
                            Metadata metadata = trackSelection.getFormat(i3).metadata;
                            if (metadata == null || !setArtworkFromMetadata(metadata)) {
                                i3++;
                            } else {
                                return;
                            }
                        }
                        continue;
                    }
                }
                if (setArtworkFromBitmap(this.defaultArtwork)) {
                    return;
                }
            }
            hideArtwork();
        } else if (!this.keepContentOnPlayerReset) {
            hideArtwork();
            closeShutter();
        }
    }

    private boolean setArtworkFromMetadata(Metadata metadata) {
        for (int i = 0; i < metadata.length(); i++) {
            Metadata.Entry entry = metadata.get(i);
            if (entry instanceof ApicFrame) {
                byte[] bArr = ((ApicFrame) entry).pictureData;
                return setArtworkFromBitmap(BitmapFactory.decodeByteArray(bArr, 0, bArr.length));
            }
        }
        return false;
    }

    private boolean setArtworkFromBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return false;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= 0 || height <= 0) {
            return false;
        }
        if (this.contentFrame == null) {
            return true;
        }
        this.contentFrame.setAspectRatio(((float) width) / ((float) height));
        return true;
    }

    @TargetApi(23)
    private static void configureEditModeLogoV23(Resources resources, ImageView imageView) {
        imageView.setImageDrawable(resources.getDrawable(R.drawable.exo_edit_mode_logo, (Resources.Theme) null));
        imageView.setBackgroundColor(resources.getColor(R.color.exo_edit_mode_background_color, (Resources.Theme) null));
    }

    private static void configureEditModeLogo(Resources resources, ImageView imageView) {
        imageView.setImageDrawable(resources.getDrawable(R.drawable.exo_edit_mode_logo));
        imageView.setBackgroundColor(resources.getColor(R.color.exo_edit_mode_background_color));
    }

    private static void setResizeModeRaw(AspectRatioFrameLayout aspectRatioFrameLayout, int i) {
        aspectRatioFrameLayout.setResizeMode(i);
    }

    /* access modifiers changed from: private */
    public static void applyTextureViewRotation(TextureView textureView, int i) {
        float width = (float) textureView.getWidth();
        float height = (float) textureView.getHeight();
        if (width == 0.0f || height == 0.0f || i == 0) {
            textureView.setTransform((Matrix) null);
            return;
        }
        Matrix matrix = new Matrix();
        float f = width / 2.0f;
        float f2 = height / 2.0f;
        matrix.postRotate((float) i, f, f2);
        RectF rectF = new RectF(0.0f, 0.0f, width, height);
        RectF rectF2 = new RectF();
        matrix.mapRect(rectF2, rectF);
        matrix.postScale(width / rectF2.width(), height / rectF2.height(), f, f2);
        textureView.setTransform(matrix);
    }

    private final class ComponentListener extends Player.DefaultEventListener implements View.OnLayoutChangeListener, TextOutput, VideoListener {
        public void onCues(List<Cue> list) {
        }

        public /* synthetic */ void onSurfaceSizeChanged(int i, int i2) {
            VideoListener.CC.$default$onSurfaceSizeChanged(this, i, i2);
        }

        private ComponentListener() {
        }

        public void onVideoSizeChanged(int i, int i2, int i3, float f) {
            if (MJExoPlayerViewP.this.exoPlayerBridgeListener != null) {
                MJExoPlayerViewP.this.exoPlayerBridgeListener.onVideoSizeChanged(i, i2, i3, f);
            }
            if (MJExoPlayerViewP.this.playerViewListener != null) {
                MJExoPlayerViewP.this.playerViewListener.onVideoSizeChanged(i, i2, i3, f);
            }
            if (MJExoPlayerViewP.this.contentFrame != null) {
                float f2 = (i2 == 0 || i == 0) ? 1.0f : (((float) i) * f) / ((float) i2);
                if (MJExoPlayerViewP.this.surfaceView instanceof TextureView) {
                    if (i3 == 90 || i3 == 270) {
                        f2 = 1.0f / f2;
                    }
                    if (MJExoPlayerViewP.this.textureViewRotation != 0) {
                        MJExoPlayerViewP.this.surfaceView.removeOnLayoutChangeListener(this);
                    }
                    int unused = MJExoPlayerViewP.this.textureViewRotation = i3;
                    if (MJExoPlayerViewP.this.textureViewRotation != 0) {
                        MJExoPlayerViewP.this.surfaceView.addOnLayoutChangeListener(this);
                    }
                    MJExoPlayerViewP.applyTextureViewRotation((TextureView) MJExoPlayerViewP.this.surfaceView, MJExoPlayerViewP.this.textureViewRotation);
                }
                MJExoPlayerViewP.this.contentFrame.setAspectRatio(f2);
            }
        }

        public void onRenderedFirstFrame() {
            if (MJExoPlayerViewP.this.exoPlayerBridgeListener != null) {
                MJExoPlayerViewP.this.exoPlayerBridgeListener.onRenderedFirstFrame();
            }
            if (MJExoPlayerViewP.this.playerViewListener != null) {
                MJExoPlayerViewP.this.playerViewListener.onRenderedFirstFrame();
            }
        }

        public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
            MJExoPlayerViewP.this.updateForCurrentTrackSelections(false);
        }

        public void onPlayerStateChanged(boolean z, int i) {
            MJExoPlayerViewP.this.updateBuffering();
            MJExoPlayerViewP.this.updateErrorMessage();
            if (!MJExoPlayerViewP.this.isPlayingAd() || !MJExoPlayerViewP.this.controllerHideDuringAds) {
                MJExoPlayerViewP.this.maybeShowController(false);
            } else {
                MJExoPlayerViewP.this.hideController();
            }
        }

        public void onPositionDiscontinuity(int i) {
            if (MJExoPlayerViewP.this.isPlayingAd() && MJExoPlayerViewP.this.controllerHideDuringAds) {
                MJExoPlayerViewP.this.hideController();
            }
        }

        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            MJExoPlayerViewP.applyTextureViewRotation((TextureView) view, MJExoPlayerViewP.this.textureViewRotation);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.player.stop();
        this.player.release();
        this.player = null;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }
}
