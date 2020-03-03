package com.xiaomi.smarthome.mibrain.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.mibrain.viewutil.waveview.AudioRecordWaveView;
import com.xiaomi.smarthome.mibrain.viewutil.waveview.MiBrainRecycleViewLayout2;

public class MiBrainActivityCardStyle_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private MiBrainActivityCardStyle f10589a;

    @UiThread
    public MiBrainActivityCardStyle_ViewBinding(MiBrainActivityCardStyle miBrainActivityCardStyle) {
        this(miBrainActivityCardStyle, miBrainActivityCardStyle.getWindow().getDecorView());
    }

    @UiThread
    public MiBrainActivityCardStyle_ViewBinding(MiBrainActivityCardStyle miBrainActivityCardStyle, View view) {
        this.f10589a = miBrainActivityCardStyle;
        miBrainActivityCardStyle.mMiBrainHintTv = (TextView) Utils.findRequiredViewAsType(view, R.id.mi_brain_speak_hint, "field 'mMiBrainHintTv'", TextView.class);
        miBrainActivityCardStyle.mTipsView = Utils.findRequiredView(view, R.id.mi_brain_tips_view, "field 'mTipsView'");
        miBrainActivityCardStyle.mContentView = Utils.findRequiredView(view, R.id.mi_brain_content_layout, "field 'mContentView'");
        miBrainActivityCardStyle.mMaskView = Utils.findRequiredView(view, R.id.mask, "field 'mMaskView'");
        miBrainActivityCardStyle.mCardContainer = Utils.findRequiredView(view, R.id.content_container, "field 'mCardContainer'");
        miBrainActivityCardStyle.mAudioRecordWaveView = (AudioRecordWaveView) Utils.findRequiredViewAsType(view, R.id.audio_record_wave_view, "field 'mAudioRecordWaveView'", AudioRecordWaveView.class);
        miBrainActivityCardStyle.miBrainRecycleViewLayout = (MiBrainRecycleViewLayout2) Utils.findRequiredViewAsType(view, R.id.mi_brain_recycle_view_layout, "field 'miBrainRecycleViewLayout'", MiBrainRecycleViewLayout2.class);
    }

    @CallSuper
    public void unbind() {
        MiBrainActivityCardStyle miBrainActivityCardStyle = this.f10589a;
        if (miBrainActivityCardStyle != null) {
            this.f10589a = null;
            miBrainActivityCardStyle.mMiBrainHintTv = null;
            miBrainActivityCardStyle.mTipsView = null;
            miBrainActivityCardStyle.mContentView = null;
            miBrainActivityCardStyle.mMaskView = null;
            miBrainActivityCardStyle.mCardContainer = null;
            miBrainActivityCardStyle.mAudioRecordWaveView = null;
            miBrainActivityCardStyle.miBrainRecycleViewLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
