package com.xiaomi.youpin.youpin_common.ui;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.brentvatne.react.ReactVideoView;
import com.xiaomi.youpin.common.util.TitleBarUtil;
import com.xiaomi.youpin.youpin_common.R;
import com.xiaomi.youpin.youpin_common.ui.NativeExplayerView;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

public class FullVideoViewActivity extends FragmentActivity implements View.OnClickListener, NativeExplayerView.ExoPlayerListener {
    public static final int HIDE_CONTROLS = 110;

    /* renamed from: a  reason: collision with root package name */
    private FrameLayout f23823a;
    private View b;
    private ImageView c;
    private ImageView d;
    private TextView e;
    private ProgressBar f;
    private TextView g;
    private ImageView h;
    private long i;
    private LinearLayout j;
    private View k;
    MainHandler mPlayerHandler = new MainHandler();
    NativeExplayerView nativeExplayerView;

    public static class MainHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        WeakReference<FullVideoViewActivity> f23824a;

        private MainHandler(FullVideoViewActivity fullVideoViewActivity) {
            super(Looper.getMainLooper());
            this.f23824a = new WeakReference<>(fullVideoViewActivity);
        }

        public void handleMessage(Message message) {
            ((FullVideoViewActivity) this.f23824a.get()).handleHideControls();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.yp_video_frame);
        TitleBarUtil.a(getWindow());
        this.k = findViewById(R.id.click_view);
        this.j = (LinearLayout) findViewById(R.id.controller);
        this.h = (ImageView) findViewById(R.id.exit_full);
        this.g = (TextView) findViewById(R.id.total_time);
        this.f = (ProgressBar) findViewById(R.id.progress);
        this.e = (TextView) findViewById(R.id.seek_time);
        this.d = (ImageView) findViewById(R.id.play);
        this.c = (ImageView) findViewById(R.id.center_play);
        this.b = findViewById(R.id.back);
        this.f23823a = (FrameLayout) findViewById(R.id.container);
        TitleBarUtil.a(this.b);
        this.nativeExplayerView = new NativeExplayerView(this);
        this.nativeExplayerView.setExoPlayerListener(this);
        this.f23823a.addView(this.nativeExplayerView);
        this.c.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.k.setOnClickListener(this);
        String stringExtra = getIntent().getStringExtra("url");
        this.i = getIntent().getLongExtra(ReactVideoView.EVENT_PROP_SEEK_TIME, 0);
        this.nativeExplayerView.setSrc(Uri.parse(stringExtra), (String) null, (Map<String, String>) null);
        this.nativeExplayerView.setPausedModifier(false);
        a();
    }

    public void callProgressChanged(double d2, double d3, double d4) {
        ifNeedHandleDefaultControl(d2, d3, d4);
    }

    public void callLoad(double d2, double d3, int i2, int i3, List list, List list2) {
        this.nativeExplayerView.seekTo(this.i);
    }

    /* access modifiers changed from: package-private */
    public void handleHideControls() {
        if (this.nativeExplayerView.isPaused()) {
            updateViewController();
            return;
        }
        this.b.setVisibility(8);
        this.j.setVisibility(8);
    }

    private void a() {
        updateViewController();
        this.mPlayerHandler.removeMessages(110);
        this.mPlayerHandler.sendEmptyMessageDelayed(110, 5000);
    }

    /* access modifiers changed from: package-private */
    public void updateViewController() {
        this.b.setVisibility(0);
        this.h.setImageResource(R.drawable.yp_icon_small_screen);
        boolean isPaused = this.nativeExplayerView.isPaused();
        this.c.setVisibility(isPaused ? 0 : 8);
        this.d.setImageResource(isPaused ? R.drawable.yp_icon_small_play : R.drawable.yp_icon_small_pause);
        this.j.setVisibility(0);
        if (!isPaused && this.j.getVisibility() == 0) {
            this.mPlayerHandler.removeMessages(110);
            this.mPlayerHandler.sendEmptyMessageDelayed(110, 5000);
        }
    }

    /* access modifiers changed from: package-private */
    public void ifNeedHandleDefaultControl(double d2, double d3, double d4) {
        if (this.j != null) {
            int i2 = (int) (d2 / 1000.0d);
            int i3 = (int) (d4 / 1000.0d);
            TextView textView = this.e;
            textView.setText(String.format("%02d", new Object[]{Integer.valueOf(i2 / 60)}) + ":" + String.format("%02d", new Object[]{Integer.valueOf(i2 % 60)}));
            TextView textView2 = this.g;
            textView2.setText(String.format("%02d", new Object[]{Integer.valueOf(i3 / 60)}) + ":" + String.format("%02d", new Object[]{Integer.valueOf(i3 % 60)}));
            this.f.setMax(i3);
            this.f.setProgress(i2);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.back) {
            finish();
        } else if (id == R.id.exit_full) {
            finish();
        } else if (id == R.id.play || id == R.id.center_play) {
            this.nativeExplayerView.setPausedModifier(!this.nativeExplayerView.isPaused());
            updateViewController();
        } else if (id == R.id.click_view) {
            a();
        }
    }
}
