package com.mi.global.bbs.ui.youtube.utils;

import android.content.Context;
import android.media.AudioManager;

public class AudioUtil {
    private static AudioManager audioManager;
    private static final Object mSingletonLock = new Object();

    private AudioUtil() {
    }

    private static AudioManager getInstance(Context context) {
        synchronized (mSingletonLock) {
            if (audioManager != null) {
                AudioManager audioManager2 = audioManager;
                return audioManager2;
            }
            if (context != null) {
                audioManager = (AudioManager) context.getSystemService("audio");
            }
            AudioManager audioManager3 = audioManager;
            return audioManager3;
        }
    }

    public static void adjustMusicVolume(Context context, boolean z, boolean z2) {
        getInstance(context).adjustStreamVolume(3, z ? 1 : -1, z2 | true ? 1 : 0);
    }

    public static void playKeyClickSound(Context context, int i) {
        if (i != 0) {
            getInstance(context).playSoundEffect(0, ((float) i) / 100.0f);
        }
    }
}
