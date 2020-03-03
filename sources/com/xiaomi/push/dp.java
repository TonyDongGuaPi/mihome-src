package com.xiaomi.push;

import android.util.Log;
import com.xiaomi.push.al;

class dp extends al.b {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Cdo f12698a;

    dp(Cdo doVar) {
        this.f12698a = doVar;
    }

    public void b() {
        if (!Cdo.g.isEmpty()) {
            try {
                if (!aa.d()) {
                    Log.w(this.f12698a.d, "SDCard is unavailable.");
                } else {
                    this.f12698a.b();
                }
            } catch (Exception e) {
                Log.e(this.f12698a.d, "", e);
            }
        }
    }
}
