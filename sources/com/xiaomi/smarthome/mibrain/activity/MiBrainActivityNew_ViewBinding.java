package com.xiaomi.smarthome.mibrain.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.mibrain.viewutil.MiBrainExecutingView;
import com.xiaomi.smarthome.mibrain.viewutil.waveview.MiBrainAudioRecordView;
import com.xiaomi.smarthome.mibrain.viewutil.waveview.MiBrainRecycleViewLayout;
import com.xiaomi.smarthome.mibrain.viewutil.waveview.MiBrainWaveView;

public class MiBrainActivityNew_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private MiBrainActivityNew f10614a;

    @UiThread
    public MiBrainActivityNew_ViewBinding(MiBrainActivityNew miBrainActivityNew) {
        this(miBrainActivityNew, miBrainActivityNew.getWindow().getDecorView());
    }

    @UiThread
    public MiBrainActivityNew_ViewBinding(MiBrainActivityNew miBrainActivityNew, View view) {
        this.f10614a = miBrainActivityNew;
        miBrainActivityNew.mBrainContentResultRl = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.mi_brain_content_rl, "field 'mBrainContentResultRl'", RelativeLayout.class);
        miBrainActivityNew.mBrainContentResultIV = (ImageView) Utils.findRequiredViewAsType(view, R.id.mi_brain_content_executing_result_iv, "field 'mBrainContentResultIV'", ImageView.class);
        miBrainActivityNew.mBrainContentResultUpTV = (TextView) Utils.findRequiredViewAsType(view, R.id.mi_brain_content_result_up_tv, "field 'mBrainContentResultUpTV'", TextView.class);
        miBrainActivityNew.mBrainContentResultDownTV = (TextView) Utils.findRequiredViewAsType(view, R.id.mi_brain_content_result_down_tv, "field 'mBrainContentResultDownTV'", TextView.class);
        miBrainActivityNew.mBrainSettingWhiteTV = (TextView) Utils.findRequiredViewAsType(view, R.id.setting_tv_white, "field 'mBrainSettingWhiteTV'", TextView.class);
        miBrainActivityNew.mHelpIV = (ImageView) Utils.findRequiredViewAsType(view, R.id.mi_brain_help_iv, "field 'mHelpIV'", ImageView.class);
        miBrainActivityNew.mCloseIV = (ImageView) Utils.findRequiredViewAsType(view, R.id.mi_brain_close_iv, "field 'mCloseIV'", ImageView.class);
        miBrainActivityNew.miBrainWaveView = (MiBrainWaveView) Utils.findRequiredViewAsType(view, R.id.mi_brain_wave_view, "field 'miBrainWaveView'", MiBrainWaveView.class);
        miBrainActivityNew.miBrainAudioRecordView = (MiBrainAudioRecordView) Utils.findRequiredViewAsType(view, R.id.mi_brain_ring_view, "field 'miBrainAudioRecordView'", MiBrainAudioRecordView.class);
        miBrainActivityNew.miBrainListeningRl = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.mi_brain_wave_view_listening_rl, "field 'miBrainListeningRl'", RelativeLayout.class);
        miBrainActivityNew.miBrainNoListeningIV = (ImageView) Utils.findRequiredViewAsType(view, R.id.mi_brain_wave_view_normal_iv, "field 'miBrainNoListeningIV'", ImageView.class);
        miBrainActivityNew.miBrainControlView = Utils.findRequiredView(view, R.id.mi_brain_voice_control_view, "field 'miBrainControlView'");
        miBrainActivityNew.miBrainRecycleViewLayout = (MiBrainRecycleViewLayout) Utils.findRequiredViewAsType(view, R.id.mi_brain_recycle_view_layout, "field 'miBrainRecycleViewLayout'", MiBrainRecycleViewLayout.class);
        miBrainActivityNew.miBrainExecutingView = (MiBrainExecutingView) Utils.findRequiredViewAsType(view, R.id.mi_brain_executing_view, "field 'miBrainExecutingView'", MiBrainExecutingView.class);
        miBrainActivityNew.mErrorDescTV = (TextView) Utils.findRequiredViewAsType(view, R.id.mi_brain_error_desc_tv, "field 'mErrorDescTV'", TextView.class);
        miBrainActivityNew.mHelpLL = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.mi_brain_help_rl, "field 'mHelpLL'", RelativeLayout.class);
        miBrainActivityNew.mHelpListView = (ListView) Utils.findRequiredViewAsType(view, R.id.mi_brain_help_lv, "field 'mHelpListView'", ListView.class);
        miBrainActivityNew.mTipsLL = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.mi_brain_tips_content, "field 'mTipsLL'", LinearLayout.class);
        miBrainActivityNew.mMoreIV = (ImageView) Utils.findRequiredViewAsType(view, R.id.more_voice_iv, "field 'mMoreIV'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        MiBrainActivityNew miBrainActivityNew = this.f10614a;
        if (miBrainActivityNew != null) {
            this.f10614a = null;
            miBrainActivityNew.mBrainContentResultRl = null;
            miBrainActivityNew.mBrainContentResultIV = null;
            miBrainActivityNew.mBrainContentResultUpTV = null;
            miBrainActivityNew.mBrainContentResultDownTV = null;
            miBrainActivityNew.mBrainSettingWhiteTV = null;
            miBrainActivityNew.mHelpIV = null;
            miBrainActivityNew.mCloseIV = null;
            miBrainActivityNew.miBrainWaveView = null;
            miBrainActivityNew.miBrainAudioRecordView = null;
            miBrainActivityNew.miBrainListeningRl = null;
            miBrainActivityNew.miBrainNoListeningIV = null;
            miBrainActivityNew.miBrainControlView = null;
            miBrainActivityNew.miBrainRecycleViewLayout = null;
            miBrainActivityNew.miBrainExecutingView = null;
            miBrainActivityNew.mErrorDescTV = null;
            miBrainActivityNew.mHelpLL = null;
            miBrainActivityNew.mHelpListView = null;
            miBrainActivityNew.mTipsLL = null;
            miBrainActivityNew.mMoreIV = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
