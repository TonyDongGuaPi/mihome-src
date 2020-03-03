package com.xiaomi.youpin.youpin_common.widget.video;

import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.ui.TimeBar;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import com.xiaomi.youpin.common.util.ConvertUtils;
import com.xiaomi.youpin.youpin_common.R;
import java.util.Formatter;
import java.util.Locale;

public class FullScreenHelper implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    public static final int f23831a = 110;
    volatile boolean b = false;
    /* access modifiers changed from: private */
    public BaseExoplayerView c;
    private boolean d = false;
    private View e = null;
    private TextView f;
    private TimeBar g;
    /* access modifiers changed from: private */
    public TextView h;
    private View i;
    private ImageView j;
    private ImageView k;
    private ImageView l;
    private ImageView m;
    private View n;
    /* access modifiers changed from: private */
    public boolean o;
    private ComponentListener p;
    /* access modifiers changed from: private */
    public StringBuilder q;
    /* access modifiers changed from: private */
    public Formatter r;
    /* access modifiers changed from: private */
    public Player s;
    private Timeline.Window t;
    private Timeline.Period u;
    private boolean v = true;

    public FullScreenHelper(BaseExoplayerView baseExoplayerView) {
        this.c = baseExoplayerView;
    }

    public void a(View view) {
        if (!this.b && this.e != null) {
            this.b = true;
            this.c.removeView(this.e);
            a(true, 1);
            ((ViewGroup) view).addView(this.e);
            this.e.setFocusable(true);
            this.e.setFocusableInTouchMode(true);
            this.e.requestFocus();
            this.e.setOnKeyListener(new View.OnKeyListener() {
                public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                    return FullScreenHelper.this.a(view, i, keyEvent);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean a(View view, int i2, KeyEvent keyEvent) {
        if (i2 != 4) {
            return false;
        }
        this.i.performClick();
        return true;
    }

    public void a(boolean z, int i2) {
        if (this.n != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.n.getLayoutParams();
            if (!z) {
                layoutParams.setMargins(0, 0, 0, 0);
            } else if (i2 == 1) {
                layoutParams.setMargins(0, 0, 0, ConvertUtils.b(250.0f));
            } else if (i2 == 2) {
                int b2 = ConvertUtils.b(20.0f);
                layoutParams.setMargins(b2, 0, b2, 0);
            }
            this.n.setLayoutParams(layoutParams);
            this.n.requestLayout();
        }
    }

    public void b(View view) {
        if (this.b && this.e != null) {
            this.b = false;
            ((ViewGroup) view).removeView(this.e);
            this.c.addView(this.e, 0, new FrameLayout.LayoutParams(-1, -1));
            this.e.measure(View.MeasureSpec.makeMeasureSpec(this.c.getWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(this.c.getHeight(), Integer.MIN_VALUE));
            this.e.layout(this.e.getLeft(), this.e.getTop(), this.e.getRight(), this.e.getBottom());
            this.l.requestLayout();
            a(false, 1);
            this.e.setFocusable(false);
            this.e.setFocusableInTouchMode(false);
            this.e.setOnKeyListener((View.OnKeyListener) null);
        }
    }

    public void a(ExoPlayerView exoPlayerView) {
        this.e = LayoutInflater.from(this.c.getContext()).inflate(R.layout.yp_video_frame, this.c, false);
        this.j = (ImageView) this.e.findViewById(R.id.exit_full);
        this.f = (TextView) this.e.findViewById(R.id.total_time);
        this.g = (TimeBar) this.e.findViewById(R.id.progress);
        this.h = (TextView) this.e.findViewById(R.id.seek_time);
        this.k = (ImageView) this.e.findViewById(R.id.play);
        this.i = this.e.findViewById(R.id.back);
        this.m = (ImageView) this.e.findViewById(R.id.mute_img);
        this.l = (ImageView) this.e.findViewById(R.id.center_play);
        this.n = this.e.findViewById(R.id.controller);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        ((FrameLayout) this.e.findViewById(R.id.container)).addView(exoPlayerView, layoutParams);
        this.l.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.e.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                FullScreenHelper.this.c(view);
            }
        });
        this.u = new Timeline.Period();
        this.t = new Timeline.Window();
        this.q = new StringBuilder();
        this.r = new Formatter(this.q, Locale.getDefault());
        this.p = new ComponentListener();
        if (this.g != null) {
            this.g.removeListener(this.p);
            this.g.addListener(this.p);
        }
        this.c.addView(this.e, 0, layoutParams);
        b();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        d();
    }

    private void d() {
        int i2 = 0;
        this.i.setVisibility((!this.b || this.i.getVisibility() == 0) ? 8 : 0);
        View view = this.n;
        if (!this.d || this.n.getVisibility() == 0) {
            i2 = 8;
        }
        view.setVisibility(i2);
        this.c.mPlayerHandler.removeMessages(110);
        this.c.mPlayerHandler.sendEmptyMessageDelayed(110, 5000);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (this.c.isPaused()) {
            b();
            return;
        }
        this.i.setVisibility(8);
        this.n.setVisibility(8);
    }

    /* access modifiers changed from: package-private */
    public void b() {
        if (this.d) {
            int i2 = 8;
            this.i.setVisibility(this.b ? 0 : 8);
            this.j.setImageResource(this.b ? R.drawable.yp_icon_small_screen : R.drawable.yp_icon_full_screen);
            boolean isPaused = this.c.isPaused();
            this.l.setVisibility((!isPaused || (!this.b && !this.d)) ? 4 : 0);
            this.k.setImageResource(isPaused ? R.drawable.yp_icon_small_play : R.drawable.yp_icon_small_pause);
            View view = this.n;
            if (this.b || this.d) {
                i2 = 0;
            }
            view.setVisibility(i2);
            this.m.setImageResource(this.c.isMuted() ? R.drawable.yp_icon_mute_screen : R.drawable.yp_icon_voice_screen);
            if (!isPaused && this.n.getVisibility() == 0) {
                this.c.mPlayerHandler.removeMessages(110);
                this.c.mPlayerHandler.sendEmptyMessageDelayed(110, 5000);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(long j2, long j3, long j4) {
        if (this.d && this.n != null) {
            this.h.setText(Util.getStringForTime(this.q, this.r, j2));
            this.f.setText(Util.getStringForTime(this.q, this.r, j4));
            this.g.setPosition(j2);
            this.g.setDuration(j4);
        }
    }

    public void a(boolean z) {
        this.d = z;
        b();
    }

    public void b(boolean z) {
        if (this.g != null) {
            this.g.setEnabled(z);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.back) {
            this.c.setFullscreen(false);
        } else if (id == R.id.exit_full) {
            this.c.setFullscreen(!this.b);
        } else if (id == R.id.play || id == R.id.center_play) {
            e();
        } else if (id == R.id.mute_img) {
            this.c.setMutedModifier(!this.c.isMuted());
            b();
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        boolean isPaused = this.c.isPaused();
        if (isPaused && this.c.resetVideoIfNeed()) {
            a(0);
            this.h.setText(Util.getStringForTime(this.q, this.r, 0));
        }
        this.c.setPausedModifier(!isPaused);
        b();
    }

    private final class ComponentListener implements TimeBar.OnScrubListener {
        private ComponentListener() {
        }

        public void onScrubStart(TimeBar timeBar, long j) {
            boolean unused = FullScreenHelper.this.o = true;
            if (FullScreenHelper.this.c != null) {
                FullScreenHelper.this.c.callOnDrag("start", j);
            }
        }

        public void onScrubMove(TimeBar timeBar, long j) {
            if (FullScreenHelper.this.h != null) {
                FullScreenHelper.this.h.setText(Util.getStringForTime(FullScreenHelper.this.q, FullScreenHelper.this.r, j));
            }
        }

        public void onScrubStop(TimeBar timeBar, long j, boolean z) {
            boolean unused = FullScreenHelper.this.o = false;
            if (!z && FullScreenHelper.this.s != null) {
                FullScreenHelper.this.a(j);
                if (FullScreenHelper.this.c != null) {
                    FullScreenHelper.this.c.callOnDrag("end", j);
                    if (FullScreenHelper.this.c.isPaused()) {
                        FullScreenHelper.this.e();
                    }
                }
            }
        }
    }

    public void a(long j2) {
        if (this.s != null) {
            this.s.seekTo(j2);
            f();
        }
    }

    private void f() {
        long j2;
        long j3;
        long j4 = 0;
        if (this.s != null) {
            if (this.t.durationUs != C.TIME_UNSET) {
                j4 = C.usToMs(this.t.durationUs);
            }
            j3 = this.s.getContentPosition();
            j2 = this.s.getContentBufferedPosition();
        } else {
            j3 = 0;
            j2 = 0;
        }
        if (this.f != null) {
            this.f.setText(Util.getStringForTime(this.q, this.r, j4));
        }
        if (this.h != null && !this.o) {
            this.h.setText(Util.getStringForTime(this.q, this.r, j3));
        }
        if (this.g != null) {
            this.g.setPosition(j3);
            this.g.setBufferedPosition(j2);
            this.g.setDuration(j4);
        }
    }

    public Player c() {
        return this.s;
    }

    public void a(@Nullable Player player) {
        boolean z = false;
        Assertions.checkState(Looper.myLooper() == Looper.getMainLooper());
        if (player == null || player.getApplicationLooper() == Looper.getMainLooper()) {
            z = true;
        }
        Assertions.checkArgument(z);
        if (this.s != player) {
            this.s = player;
        }
    }
}
