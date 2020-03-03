package com.xiaomi.aiot.mibeacon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.xiaomi.aiot.mibeacon.distance.DistanceCalculator;
import com.xiaomi.aiot.mibeacon.distance.ModelSpecificDistanceCalculator;
import com.xiaomi.aiot.mibeacon.logging.LogManager;
import com.xiaomi.aiot.mibeacon.logging.Loggers;
import com.xiaomi.aiot.mibeacon.scan.ScanHelper;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class BeaconManager {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    protected static volatile BeaconManager f9961a = null;
    @NonNull
    private static final String c = "BeaconManager";
    private static final Object d = new Object();
    private static final Object e = new Object();
    @NonNull
    protected final Set<RangeNotifier> b = new CopyOnWriteArraySet();
    private ScanHelper f;
    private volatile boolean g = false;
    @NonNull
    private final Context h;

    public static void a(boolean z) {
        if (z) {
            LogManager.a(Loggers.b());
            LogManager.a(true);
            return;
        }
        LogManager.a(Loggers.a());
        LogManager.a(false);
    }

    @NonNull
    public Set<RangeNotifier> a() {
        return Collections.unmodifiableSet(this.b);
    }

    public boolean a(@NonNull RangeNotifier rangeNotifier) {
        return this.b.remove(rangeNotifier);
    }

    public void b() {
        this.b.clear();
    }

    public void b(@NonNull RangeNotifier rangeNotifier) {
        if (rangeNotifier != null) {
            this.b.add(rangeNotifier);
        }
    }

    public boolean c(@Nullable RangeNotifier rangeNotifier) {
        synchronized (e) {
            if (this.g) {
                return false;
            }
            if (rangeNotifier != null) {
                this.b.add(rangeNotifier);
            }
            if (this.f == null) {
                this.f = new ScanHelper(this.h);
            }
            boolean a2 = this.f.a();
            this.g = a2;
            return a2;
        }
    }

    public void c() {
        synchronized (e) {
            if (this.g) {
                b();
                this.f.b();
                this.g = false;
            }
        }
    }

    @NonNull
    public static BeaconManager a(@NonNull Context context) {
        BeaconManager beaconManager = f9961a;
        if (beaconManager == null) {
            synchronized (d) {
                beaconManager = f9961a;
                if (beaconManager == null) {
                    beaconManager = new BeaconManager(context);
                    f9961a = beaconManager;
                }
            }
        }
        return beaconManager;
    }

    protected BeaconManager(@NonNull Context context) {
        this.h = context.getApplicationContext();
        MiBeacon.a((DistanceCalculator) new ModelSpecificDistanceCalculator(this.h, ""));
    }
}
