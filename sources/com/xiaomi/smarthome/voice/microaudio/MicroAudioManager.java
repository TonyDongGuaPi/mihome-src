package com.xiaomi.smarthome.voice.microaudio;

import com.xiaomi.smarthome.sip.model.MicroHistoryInfo;
import com.xiaomi.smarthome.voice.microaudio.model.MicroPushMsgInfo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MicroAudioManager {
    private static volatile MicroAudioManager f;

    /* renamed from: a  reason: collision with root package name */
    List<MicroHistoryInfo> f22806a = new ArrayList();
    HashSet<IReceiveMicroPushMsgCallBack> b = new HashSet<>();
    private String c;
    private String d;
    private int e;

    interface IReceiveMicroPushMsgCallBack {
        void a(MicroPushMsgInfo microPushMsgInfo);
    }

    public static MicroAudioManager a() {
        if (f == null) {
            synchronized (MicroAudioManager.class) {
                if (f == null) {
                    f = new MicroAudioManager();
                }
            }
        }
        return f;
    }

    public void a(IReceiveMicroPushMsgCallBack iReceiveMicroPushMsgCallBack) {
        if (iReceiveMicroPushMsgCallBack != null) {
            this.b.add(iReceiveMicroPushMsgCallBack);
        }
    }

    public void b(IReceiveMicroPushMsgCallBack iReceiveMicroPushMsgCallBack) {
        if (iReceiveMicroPushMsgCallBack != null) {
            this.b.remove(iReceiveMicroPushMsgCallBack);
        }
    }

    public void b() {
        this.b.clear();
        f = null;
    }
}
