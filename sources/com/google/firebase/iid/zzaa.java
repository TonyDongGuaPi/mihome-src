package com.google.firebase.iid;

import android.os.Bundle;
import com.xiaomi.smarthome.library.bluetooth.channel.packet.Packet;

final class zzaa extends zzab<Void> {
    zzaa(int i, int i2, Bundle bundle) {
        super(i, 2, bundle);
    }

    /* access modifiers changed from: package-private */
    public final void zzb(Bundle bundle) {
        if (bundle.getBoolean(Packet.w, false)) {
            finish(null);
        } else {
            zza(new zzac(4, "Invalid response to one way request"));
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzw() {
        return true;
    }
}
