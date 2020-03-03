package com.xiaomi.youpin.youpin_common.widget.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.text.TextRenderer;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.SubtitleView;
import com.google.android.exoplayer2.video.VideoListener;
import java.util.List;

@TargetApi(16)
public final class ExoPlayerView extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private View f23828a;
    /* access modifiers changed from: private */
    public final View b;
    /* access modifiers changed from: private */
    public final SubtitleView c;
    /* access modifiers changed from: private */
    public final AspectRatioFrameLayout d;
    private final ComponentListener e;
    private SimpleExoPlayer f;
    private Context g;
    private ViewGroup.LayoutParams h;
    private boolean i;
    /* access modifiers changed from: private */
    public final Runnable j;

    public ExoPlayerView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ExoPlayerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ExoPlayerView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.i = false;
        this.j = new Runnable() {
            public void run() {
                ExoPlayerView.this.measure(View.MeasureSpec.makeMeasureSpec(ExoPlayerView.this.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ExoPlayerView.this.getHeight(), 1073741824));
                ExoPlayerView.this.layout(ExoPlayerView.this.getLeft(), ExoPlayerView.this.getTop(), ExoPlayerView.this.getRight(), ExoPlayerView.this.getBottom());
            }
        };
        this.g = context;
        setBackgroundColor(ContextCompat.getColor(context, 17170444));
        this.h = new ViewGroup.LayoutParams(-1, -1);
        this.e = new ComponentListener();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        this.d = new AspectRatioFrameLayout(context);
        this.d.setLayoutParams(layoutParams);
        this.b = new View(getContext());
        this.b.setLayoutParams(this.h);
        this.b.setBackgroundColor(ContextCompat.getColor(context, 17170444));
        this.c = new SubtitleView(context);
        this.c.setLayoutParams(this.h);
        this.c.setUserDefaultStyle();
        this.c.setUserDefaultTextSize();
        b();
        this.d.addView(this.b, 1, this.h);
        this.d.addView(this.c, 2, this.h);
        addViewInLayout(this.d, 0, layoutParams);
    }

    private void a() {
        if (this.f23828a instanceof TextureView) {
            this.f.setVideoTextureView((TextureView) this.f23828a);
        } else if (this.f23828a instanceof SurfaceView) {
            this.f.setVideoSurfaceView((SurfaceView) this.f23828a);
        }
    }

    private void b() {
        View textureView = this.i ? new TextureView(this.g) : new SurfaceView(this.g);
        textureView.setLayoutParams(this.h);
        this.f23828a = textureView;
        if (this.d.getChildAt(0) != null) {
            this.d.removeViewAt(0);
        }
        this.d.addView(this.f23828a, 0, this.h);
        if (this.f != null) {
            a();
        }
    }

    public void setPlayer(SimpleExoPlayer simpleExoPlayer) {
        if (this.f != simpleExoPlayer) {
            if (this.f != null) {
                this.f.setTextOutput((TextOutput) null);
                this.f.setVideoListener((SimpleExoPlayer.VideoListener) null);
                this.f.removeListener(this.e);
                this.f.setVideoSurface((Surface) null);
            }
            this.f = simpleExoPlayer;
            this.b.setVisibility(0);
            if (simpleExoPlayer != null) {
                a();
                simpleExoPlayer.setVideoListener(this.e);
                simpleExoPlayer.addListener(this.e);
                simpleExoPlayer.setTextOutput(this.e);
            }
        }
    }

    public void setResizeMode(int i2) {
        if (this.d.getResizeMode() != i2) {
            this.d.setResizeMode(i2);
            post(this.j);
        }
    }

    public View getVideoSurfaceView() {
        return this.f23828a;
    }

    public void setUseTextureView(boolean z) {
        this.i = z;
        b();
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.f != null) {
            TrackSelectionArray currentTrackSelections = this.f.getCurrentTrackSelections();
            int i2 = 0;
            while (i2 < currentTrackSelections.length) {
                if (this.f.getRendererType(i2) != 2 || currentTrackSelections.get(i2) == null) {
                    i2++;
                } else {
                    return;
                }
            }
            this.b.setVisibility(0);
        }
    }

    private final class ComponentListener implements Player.EventListener, SimpleExoPlayer.VideoListener, TextRenderer.Output {
        public void onLoadingChanged(boolean z) {
        }

        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        }

        public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        }

        public void onPlayerStateChanged(boolean z, int i) {
        }

        public void onPositionDiscontinuity(int i) {
        }

        public void onRepeatModeChanged(int i) {
        }

        public void onSeekProcessed() {
        }

        public void onShuffleModeEnabledChanged(boolean z) {
        }

        public /* synthetic */ void onSurfaceSizeChanged(int i, int i2) {
            VideoListener.CC.$default$onSurfaceSizeChanged(this, i, i2);
        }

        public void onTimelineChanged(Timeline timeline, Object obj, int i) {
        }

        private ComponentListener() {
        }

        public void onCues(List<Cue> list) {
            ExoPlayerView.this.c.onCues(list);
        }

        public void onVideoSizeChanged(int i, int i2, int i3, float f) {
            boolean z = ExoPlayerView.this.d.getAspectRatio() == 0.0f;
            ExoPlayerView.this.d.setAspectRatio(i2 == 0 ? 1.0f : (((float) i) * f) / ((float) i2));
            if (z) {
                ExoPlayerView.this.post(ExoPlayerView.this.j);
            }
        }

        public void onRenderedFirstFrame() {
            ExoPlayerView.this.b.setVisibility(4);
        }

        public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
            ExoPlayerView.this.c();
        }
    }

    public void setShutterBackgroundColor(int i2) {
        if (this.b != null) {
            this.b.setBackgroundColor(i2);
        }
    }
}
