package com.xiaomi.ai;

import android.media.AudioTrack;

public interface TtsListener {
    void a();

    void a(AudioTrack audioTrack);

    void a(PCMInfo pCMInfo);

    void a(SpeechError speechError);

    void a(String str);

    void a(boolean z);

    void b();
}
