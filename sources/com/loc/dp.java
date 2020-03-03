package com.loc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;

final class dp extends BroadcastReceiver {
    dp() {
    }

    public final void onReceive(Context context, Intent intent) {
        try {
            if (!isInitialStickyBroadcast() && intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                String a2 = Cdo.a();
                if (a2 != "None_Network" && !a2.equalsIgnoreCase(Cdo.c)) {
                    dk.a("[BroadcastReceiver.onReceive] - Network state changed");
                    dg.a();
                    ArrayList d = dg.d();
                    dg.a();
                    dg.c();
                    if (Cdo.f6565a && dc.f6556a != null) {
                        dk.a("[BroadcastReceiver.onReceive] - refresh host");
                        dc.f6556a.a(d);
                    }
                }
                String unused = Cdo.c = a2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
