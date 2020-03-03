package com.xiaomi.youpin.youpin_common.widget.video.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class AudioBecomingNoisyReceiver extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    private final Context f23835a;
    private BecomingNoisyListener b = BecomingNoisyListener.f23836a;

    public AudioBecomingNoisyReceiver(Context context) {
        this.f23835a = context.getApplicationContext();
    }

    public void onReceive(Context context, Intent intent) {
        if ("android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
            this.b.onAudioBecomingNoisy();
        }
    }

    public void setListener(BecomingNoisyListener becomingNoisyListener) {
        this.b = becomingNoisyListener;
        this.f23835a.registerReceiver(this, new IntentFilter("android.media.AUDIO_BECOMING_NOISY"));
    }

    public void removeListener() {
        this.b = BecomingNoisyListener.f23836a;
        try {
            this.f23835a.unregisterReceiver(this);
        } catch (Exception unused) {
        }
    }
}
