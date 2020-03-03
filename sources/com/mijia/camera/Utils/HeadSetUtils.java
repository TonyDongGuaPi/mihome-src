package com.mijia.camera.Utils;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.media.AudioManager;

public class HeadSetUtils {
    public static boolean a() {
        if (BluetoothAdapter.getDefaultAdapter().getProfileConnectionState(1) == 2) {
            return true;
        }
        return false;
    }

    public static boolean a(Context context) {
        return ((AudioManager) context.getSystemService("audio")).isWiredHeadsetOn();
    }
}
