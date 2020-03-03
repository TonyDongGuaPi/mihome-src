package com.p2p.audio;

import android.content.Context;
import android.media.AudioManager;
import com.debug.SDKLog;

public class AudioFoucsTool {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8507a = "Huang";
    /* access modifiers changed from: private */
    public static AudioManager b;
    private static AudioManager.OnAudioFocusChangeListener c = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int i) {
            if (AudioFoucsTool.b == null) {
                SDKLog.c(AudioFoucsTool.f8507a, "afChangeListener audioManager is null ");
            }
            switch (i) {
                case -3:
                    SDKLog.c(AudioFoucsTool.f8507a, "afChangeListener: LOSS");
                    AudioFoucsTool.b.abandonAudioFocus(this);
                    return;
                case -2:
                    SDKLog.c(AudioFoucsTool.f8507a, "afChangeListener: AUDIOFOCUS_LOSS_TRANSIENT");
                    AudioFoucsTool.b.abandonAudioFocus(this);
                    return;
                case -1:
                    SDKLog.c(AudioFoucsTool.f8507a, "afChangeListener: AUDIOFOCUS_LOSS");
                    AudioFoucsTool.b.abandonAudioFocus(this);
                    return;
                case 1:
                    SDKLog.c(AudioFoucsTool.f8507a, "afChangeListener: AUDIOFOCUS_GAIN");
                    return;
                case 2:
                    SDKLog.c(AudioFoucsTool.f8507a, "afChangeListener: AUDIOFOCUS_GAIN_TRANSIENT");
                    return;
                case 3:
                    SDKLog.c(AudioFoucsTool.f8507a, "afChangeListener: GAIN");
                    return;
                case 4:
                    SDKLog.c(AudioFoucsTool.f8507a, "afChangeListener: AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE");
                    return;
                default:
                    SDKLog.c(AudioFoucsTool.f8507a, "afChangeListener: DEFAULT");
                    AudioFoucsTool.b.abandonAudioFocus(this);
                    return;
            }
        }
    };

    public static void a(Context context, String str) {
        if (context == null) {
            SDKLog.d(f8507a, "request: applicationContext is null");
            return;
        }
        b = (AudioManager) context.getSystemService("audio");
        b.requestAudioFocus(c, 0, 4);
        SDKLog.b(f8507a, "request: " + str);
    }

    public static void b(Context context, String str) {
        if (context == null) {
            SDKLog.d(f8507a, "abandon: applicationContext is null");
            return;
        }
        try {
            if (b == null) {
                b = (AudioManager) context.getSystemService("audio");
            }
            if (b != null) {
                b.abandonAudioFocus(c);
            }
            SDKLog.b(f8507a, "abandon: " + str);
        } catch (Exception e) {
            e.printStackTrace();
            SDKLog.d(f8507a, "abandon exception=" + e.getMessage());
        }
    }
}
