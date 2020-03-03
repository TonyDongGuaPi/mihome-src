package com.miui.tsmclient.util;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import com.alipay.mobile.common.logging.api.LogCategory;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class TSMLocationService {
    private static final long DEFAULT_DURATION_TIME = 20000;
    private static volatile TSMLocationService INSTANCE;
    private static final Location sDefaultLocation = new Location(LogCategory.CATEGORY_NETWORK);
    /* access modifiers changed from: private */
    public Runnable mCancelUpdatesTask = new Runnable() {
        public void run() {
            TSMLocationService.this.cancelUpdates();
        }
    };
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public Location mLocation;
    /* access modifiers changed from: private */
    public List<WeakReference<LocationChangedListener>> mLocationChangedListeners = new ArrayList();
    /* access modifiers changed from: private */
    public LocationListener mLocationListener = new LocationListener() {
        public void onProviderDisabled(String str) {
        }

        public void onProviderEnabled(String str) {
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        public void onLocationChanged(Location location) {
            if (location == null) {
                LogUtils.d("onLocationChanged() called. location is null");
                return;
            }
            Location unused = TSMLocationService.this.mLocation = location;
            LogUtils.d("onLocationChanged() called. longitude is " + TSMLocationService.this.mLocation.getLongitude() + ", latitude is " + TSMLocationService.this.mLocation.getLatitude() + ", accuracy is " + TSMLocationService.this.mLocation.getAccuracy());
            for (WeakReference weakReference : TSMLocationService.this.mLocationChangedListeners) {
                LocationChangedListener locationChangedListener = (LocationChangedListener) weakReference.get();
                if (locationChangedListener != null) {
                    locationChangedListener.onLocationChanged(TSMLocationService.this.mLocation, true);
                }
            }
            TSMLocationService.this.mHandler.removeCallbacks(TSMLocationService.this.mCancelUpdatesTask);
            TSMLocationService.this.cancelUpdates();
        }
    };
    /* access modifiers changed from: private */
    public LocationManager mLocationManager;
    /* access modifiers changed from: private */
    public boolean mOnGoing = false;

    public interface LocationChangedListener {
        void onLocationChanged(Location location, boolean z);
    }

    private class RequestLocationUpdateTask implements Runnable {
        private WeakReference<LocationChangedListener> mLocationChangedListenerRef;

        public RequestLocationUpdateTask(WeakReference<LocationChangedListener> weakReference) {
            this.mLocationChangedListenerRef = weakReference;
        }

        public void run() {
            LocationChangedListener locationChangedListener = (LocationChangedListener) this.mLocationChangedListenerRef.get();
            if (locationChangedListener != null) {
                if (TSMLocationService.this.mLocation != null) {
                    locationChangedListener.onLocationChanged(TSMLocationService.this.mLocation, true);
                    return;
                }
                locationChangedListener.onLocationChanged(TSMLocationService.this.getLastLocation(), false);
                TSMLocationService.this.mLocationChangedListeners.add(this.mLocationChangedListenerRef);
                String access$500 = TSMLocationService.this.getBestProvider();
                if (access$500 != null && !TSMLocationService.this.mOnGoing) {
                    TSMLocationService.this.mLocationManager.requestSingleUpdate(access$500, TSMLocationService.this.mLocationListener, (Looper) null);
                    boolean unused = TSMLocationService.this.mOnGoing = true;
                    LogUtils.d("RequestLocationUpdateTask requestSingleUpdate called");
                }
            }
        }
    }

    private TSMLocationService(Context context) {
        this.mLocationManager = (LocationManager) context.getSystemService("location");
    }

    public static TSMLocationService getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TSMLocationService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TSMLocationService(context);
                }
            }
        }
        return INSTANCE;
    }

    public void requestLocationUpdate(LocationChangedListener locationChangedListener, long j) {
        if (locationChangedListener != null) {
            this.mHandler.post(new RequestLocationUpdateTask(new WeakReference(locationChangedListener)));
            this.mHandler.removeCallbacks(this.mCancelUpdatesTask);
            Handler handler = this.mHandler;
            Runnable runnable = this.mCancelUpdatesTask;
            if (j <= 0) {
                j = DEFAULT_DURATION_TIME;
            }
            handler.postDelayed(runnable, j);
        }
    }

    public Location getLastLocation() {
        if (this.mLocation != null) {
            return this.mLocation;
        }
        String bestProvider = getBestProvider();
        if (bestProvider == null) {
            return sDefaultLocation;
        }
        Location lastKnownLocation = this.mLocationManager.getLastKnownLocation(bestProvider);
        if (lastKnownLocation != null) {
            return lastKnownLocation;
        }
        return sDefaultLocation;
    }

    public boolean isLocationServiceEnabled(Context context) {
        try {
            return Settings.Secure.getInt(context.getContentResolver(), "location_mode") != 0;
        } catch (Settings.SettingNotFoundException e) {
            LogUtils.e("get location mode fail.", e);
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void cancelUpdates() {
        if (this.mOnGoing) {
            this.mOnGoing = false;
            if (this.mLocationChangedListeners != null) {
                this.mLocationChangedListeners.clear();
            }
            if (this.mLocationManager != null) {
                this.mLocationManager.removeUpdates(this.mLocationListener);
                LogUtils.d("cancelUpdates called!");
            }
            this.mLocation = null;
        }
    }

    /* access modifiers changed from: private */
    public String getBestProvider() {
        if (this.mLocationManager.isProviderEnabled(LogCategory.CATEGORY_NETWORK)) {
            return LogCategory.CATEGORY_NETWORK;
        }
        Criteria criteria = new Criteria();
        criteria.setAccuracy(2);
        return this.mLocationManager.getBestProvider(criteria, true);
    }
}
