package com.xiaomi.smarthome.mibrain.viewutil.waveview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.xiaomi.smarthome.R;

public class MiBrainAudioRecordView extends RelativeLayout {
    MiBrainCircleWaveView mWaveView;

    public MiBrainAudioRecordView(Context context) {
        super(context);
    }

    public MiBrainAudioRecordView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MiBrainAudioRecordView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mWaveView = (MiBrainCircleWaveView) findViewById(R.id.wave_view);
    }

    public void enterRecordStatus() {
        this.mWaveView.enterRecordStatus();
    }

    public void exitRecordStatus(boolean z) {
        this.mWaveView.exitRecordStatus(z);
    }

    public void receivedAudio(int i) {
        this.mWaveView.addCircle(i);
    }
}
