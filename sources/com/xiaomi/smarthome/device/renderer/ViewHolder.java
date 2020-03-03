package com.xiaomi.smarthome.device.renderer;

import android.animation.ValueAnimator;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.renderer.PluginDownloadingRecord;
import com.xiaomi.smarthome.library.common.widget.PieProgressBar;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ViewHolder {
    private static final Interpolator e = new DecelerateInterpolator();
    private static final float f = 85.0f;

    /* renamed from: a  reason: collision with root package name */
    View f15412a;
    @BindView(2131427602)
    TextView addBtn;
    @BindView(2131429757)
    SimpleDraweeView avatar;
    Button b;
    PieProgressBar c;
    @BindView(2131428355)
    CheckBox ckbEdit;
    TextView d;
    private Map<String, ValueAnimator> g = new ConcurrentHashMap();
    @BindView(2131430623)
    ImageView mLLLoading;
    @BindView(2131430628)
    View mLLLoadingContainer;
    @BindView(2131428842)
    ImageView mStatusImage;
    @BindView(2131428843)
    SwitchButton mSwitchBtn;
    @BindView(2131428844)
    View mSwitchBtnLayout;
    @BindView(2131431126)
    TextView name;
    @BindView(2131431130)
    TextView nameStatus;
    @BindView(2131428826)
    View progress_bar;

    ViewHolder(View view) {
        ButterKnife.bind((Object) this, view);
        if (Build.VERSION.SDK_INT >= 17) {
            view.setLayoutDirection(0);
            this.ckbEdit.setLayoutDirection(0);
            this.avatar.setLayoutDirection(0);
            this.name.setLayoutDirection(0);
            this.nameStatus.setLayoutDirection(0);
        }
    }

    public void a(String str, PluginDownloadingRecord pluginDownloadingRecord) {
        if (pluginDownloadingRecord != null) {
            if (this.f15412a == null) {
                this.f15412a = ((ViewStub) ((ViewGroup) this.addBtn.getParent().getParent()).findViewById(R.id.plugin_container_stub)).inflate();
                this.b = (Button) this.f15412a.findViewById(R.id.update_btn);
                this.c = (PieProgressBar) this.f15412a.findViewById(R.id.update_progress);
                this.d = (TextView) this.f15412a.findViewById(R.id.update_percent);
            }
            this.f15412a.setVisibility(0);
            this.c.setPercentView(this.d);
            if (pluginDownloadingRecord.b == PluginDownloadingRecord.Status.PENDING) {
                this.b.setVisibility(8);
                this.b.setText(this.f15412a.getContext().getString(R.string.device_list_plugin_download_immediately));
                this.c.setVisibility(8);
                this.d.setVisibility(8);
            } else if (pluginDownloadingRecord.b == PluginDownloadingRecord.Status.DOWNLOADING) {
                Log.d("DOWN_PROG", "->" + pluginDownloadingRecord.c);
                if (!this.g.containsKey(str)) {
                    this.b.setVisibility(8);
                    this.c.setVisibility(0);
                    this.d.setVisibility(0);
                    this.c.setPercent(pluginDownloadingRecord.c * f);
                    if (pluginDownloadingRecord.c >= 0.99f) {
                        a(str, pluginDownloadingRecord.e);
                    }
                }
            } else if (pluginDownloadingRecord.b == PluginDownloadingRecord.Status.DOWNLOADING_FAILURE) {
                this.b.setVisibility(8);
                this.b.setText(this.f15412a.getContext().getString(R.string.device_list_plugin_download_failure));
                this.c.setVisibility(8);
                this.d.setVisibility(8);
            }
        }
    }

    public void a(String str, PluginInstallingRecord pluginInstallingRecord) {
        if (pluginInstallingRecord != null) {
            a(a(str, 0));
        }
    }

    /* access modifiers changed from: private */
    public void a(float f2) {
        Log.d("FAKE_PROG", "->" + f2);
        if (this.f15412a == null) {
            this.f15412a = ((ViewStub) ((ViewGroup) this.addBtn.getParent().getParent()).findViewById(R.id.plugin_container_stub)).inflate();
            this.b = (Button) this.f15412a.findViewById(R.id.update_btn);
            this.c = (PieProgressBar) this.f15412a.findViewById(R.id.update_progress);
            this.d = (TextView) this.f15412a.findViewById(R.id.update_percent);
            return;
        }
        this.f15412a.setVisibility(0);
        this.c.setPercentView(this.d);
        this.c.setVisibility(0);
        this.c.setPercent(f2 * 100.0f);
        this.b.setVisibility(8);
        this.d.setVisibility(0);
    }

    public void a(String str) {
        ValueAnimator remove;
        if (this.f15412a != null) {
            this.f15412a.setVisibility(8);
        }
        if (!TextUtils.isEmpty(str) && (remove = this.g.remove(str)) != null) {
            remove.cancel();
        }
    }

    public float a(String str, long j) {
        ValueAnimator valueAnimator = this.g.get(str);
        if (valueAnimator == null) {
            synchronized (this) {
                valueAnimator = this.g.get(str);
                double min = (double) Math.min(1.0f, ((float) (System.currentTimeMillis() - j)) / 4000.0f);
                Double.isNaN(min);
                double d2 = (min * 0.14d) + 0.85d;
                if (valueAnimator == null) {
                    ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{(float) d2, 0.99f});
                    ofFloat.setDuration(4000);
                    ofFloat.setInterpolator(e);
                    ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            ViewHolder.this.a(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        }
                    });
                    ofFloat.start();
                    this.g.put(str, ofFloat);
                    Log.d("FAKE_PROG", "start");
                    valueAnimator = ofFloat;
                }
            }
        }
        return ((Float) valueAnimator.getAnimatedValue()).floatValue();
    }

    public void b(String str) {
        ValueAnimator valueAnimator = this.g.get(str);
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.g.remove(valueAnimator);
        }
    }

    public void a() {
        for (ValueAnimator cancel : this.g.values()) {
            cancel.cancel();
        }
        this.g.clear();
    }
}
