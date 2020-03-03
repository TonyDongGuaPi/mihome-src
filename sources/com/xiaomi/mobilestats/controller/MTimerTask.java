package com.xiaomi.mobilestats.controller;

import android.os.Message;
import com.xiaomi.mobilestats.StatService;
import java.util.TimerTask;

public class MTimerTask extends TimerTask {
    public void run() {
        if (StatService.handler != null) {
            Message obtainMessage = StatService.handler.obtainMessage();
            obtainMessage.what = 4096;
            obtainMessage.sendToTarget();
        }
    }
}
