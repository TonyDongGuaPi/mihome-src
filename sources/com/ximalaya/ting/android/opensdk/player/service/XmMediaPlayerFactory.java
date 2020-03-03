package com.ximalaya.ting.android.opensdk.player.service;

import android.content.Context;
import com.ximalaya.ting.android.player.XMediaPlayerWrapper;
import com.ximalaya.ting.android.player.XMediaplayerImpl;

public class XmMediaPlayerFactory {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f2199a = false;

    public static void a(boolean z) {
        f2199a = z;
    }

    public static XMediaplayerImpl a(Context context) {
        return new XMediaPlayerWrapper(context, true, f2199a);
    }
}
