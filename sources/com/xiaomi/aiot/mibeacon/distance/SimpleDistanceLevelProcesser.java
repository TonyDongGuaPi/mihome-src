package com.xiaomi.aiot.mibeacon.distance;

import com.xiaomi.aiot.mibeacon.MiBeacon;

public class SimpleDistanceLevelProcesser implements DistanceLevelProcesser {

    /* renamed from: a  reason: collision with root package name */
    private MiBeacon f9978a;

    public SimpleDistanceLevelProcesser(MiBeacon miBeacon) {
        if (miBeacon != null) {
            this.f9978a = miBeacon;
            return;
        }
        throw new NullPointerException("MiBeacon must not be null.");
    }

    public void a(MiBeacon miBeacon) {
        if (miBeacon != null) {
            this.f9978a = miBeacon;
            return;
        }
        throw new NullPointerException("MiBeacon must not be null.");
    }

    public MiBeacon.DistanceLevel a() {
        if (this.f9978a.g() <= 0.2d) {
            return MiBeacon.DistanceLevel.Immediate;
        }
        if (this.f9978a.g() <= 1.0d) {
            return MiBeacon.DistanceLevel.Near;
        }
        if (this.f9978a.g() <= 5.0d) {
            return MiBeacon.DistanceLevel.Far;
        }
        return MiBeacon.DistanceLevel.Unkonwn;
    }
}
