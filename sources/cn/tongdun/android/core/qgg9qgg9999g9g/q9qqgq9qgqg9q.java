package cn.tongdun.android.core.qgg9qgg9999g9g;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

class q9qqgq9qgqg9q implements LocationListener {
    boolean gqg9qq9gqq9q9q;

    public void onProviderEnabled(String str) {
    }

    private q9qqgq9qgqg9q() {
        this.gqg9qq9gqq9q9q = false;
    }

    /* synthetic */ q9qqgq9qgqg9q(qq99gqggggq qq99gqggggq) {
        this();
    }

    public void onLocationChanged(Location location) {
        if (location.getLatitude() != 0.0d || location.getLongitude() != 0.0d) {
            boolean z = this.gqg9qq9gqq9q9q;
            this.gqg9qq9gqq9q9q = true;
        }
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
        switch (i) {
            case 0:
            case 1:
                this.gqg9qq9gqq9q9q = false;
                return;
            default:
                return;
        }
    }

    public void onProviderDisabled(String str) {
        this.gqg9qq9gqq9q9q = false;
    }
}
