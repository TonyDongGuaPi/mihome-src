package com.reactnativecommunity.geolocation;

import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.PromiseImpl;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.SystemClock;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.permissions.PermissionsModule;
import javax.annotation.Nullable;

@SuppressLint({"MissingPermission"})
@ReactModule(name = "RNCGeolocation")
public class GeolocationModule extends ReactContextBaseJavaModule {
    public static final String NAME = "RNCGeolocation";
    private static final float RCT_DEFAULT_LOCATION_ACCURACY = 100.0f;
    private final LocationListener mLocationListener = new LocationListener() {
        public void onProviderDisabled(String str) {
        }

        public void onProviderEnabled(String str) {
        }

        public void onLocationChanged(Location location) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) GeolocationModule.this.getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("geolocationDidChange", GeolocationModule.locationToMap(location));
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
            if (i == 0) {
                GeolocationModule geolocationModule = GeolocationModule.this;
                int i2 = PositionError.POSITION_UNAVAILABLE;
                geolocationModule.emitError(i2, "Provider " + str + " is out of service.");
            } else if (i == 1) {
                GeolocationModule geolocationModule2 = GeolocationModule.this;
                int i3 = PositionError.TIMEOUT;
                geolocationModule2.emitError(i3, "Provider " + str + " is temporarily unavailable.");
            }
        }
    };
    @Nullable
    private String mWatchedProvider;

    public String getName() {
        return NAME;
    }

    public GeolocationModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private static class LocationOptions {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public final long f8697a;
        /* access modifiers changed from: private */
        public final double b;
        /* access modifiers changed from: private */
        public final boolean c;
        /* access modifiers changed from: private */
        public final float d;

        private LocationOptions(long j, double d2, boolean z, float f) {
            this.f8697a = j;
            this.b = d2;
            this.c = z;
            this.d = f;
        }

        /* access modifiers changed from: private */
        public static LocationOptions b(ReadableMap readableMap) {
            return new LocationOptions(readableMap.hasKey("timeout") ? (long) readableMap.getDouble("timeout") : Long.MAX_VALUE, readableMap.hasKey("maximumAge") ? readableMap.getDouble("maximumAge") : Double.POSITIVE_INFINITY, readableMap.hasKey("enableHighAccuracy") && readableMap.getBoolean("enableHighAccuracy"), readableMap.hasKey("distanceFilter") ? (float) readableMap.getDouble("distanceFilter") : GeolocationModule.RCT_DEFAULT_LOCATION_ACCURACY);
        }
    }

    @ReactMethod
    public void getCurrentPosition(final ReadableMap readableMap, final Callback callback, final Callback callback2) {
        if (Build.VERSION.SDK_INT >= 23) {
            PermissionsModule permissionsModule = (PermissionsModule) getReactApplicationContext().getNativeModule(PermissionsModule.class);
            final AnonymousClass2 r4 = new Callback() {
                public void invoke(Object... objArr) {
                    if (objArr[0] == "granted") {
                        GeolocationModule.this.getCurrentLocationData(readableMap, callback, callback2);
                        return;
                    }
                    callback2.invoke(PositionError.buildError(PositionError.PERMISSION_DENIED, "Location permission was not granted."));
                }
            };
            final AnonymousClass3 r5 = new Callback() {
                public void invoke(Object... objArr) {
                    callback2.invoke(PositionError.buildError(PositionError.PERMISSION_DENIED, "Failed to request location permission."));
                }
            };
            final PermissionsModule permissionsModule2 = permissionsModule;
            final ReadableMap readableMap2 = readableMap;
            final Callback callback3 = callback;
            final Callback callback4 = callback2;
            permissionsModule.checkPermission("android.permission.ACCESS_FINE_LOCATION", new PromiseImpl(new Callback() {
                public void invoke(Object... objArr) {
                    if (!objArr[0].booleanValue()) {
                        permissionsModule2.requestPermission("android.permission.ACCESS_FINE_LOCATION", new PromiseImpl(r4, r5));
                    } else {
                        GeolocationModule.this.getCurrentLocationData(readableMap2, callback3, callback4);
                    }
                }
            }, new Callback() {
                public void invoke(Object... objArr) {
                    callback2.invoke(PositionError.buildError(PositionError.PERMISSION_DENIED, "Failed to check location permission."));
                }
            }));
            return;
        }
        getCurrentLocationData(readableMap, callback, callback2);
    }

    public void getCurrentLocationData(ReadableMap readableMap, Callback callback, Callback callback2) {
        LocationOptions a2 = LocationOptions.b(readableMap);
        try {
            LocationManager locationManager = (LocationManager) getReactApplicationContext().getSystemService("location");
            String validProvider = getValidProvider(locationManager, a2.c);
            if (validProvider == null) {
                callback2.invoke(PositionError.buildError(PositionError.POSITION_UNAVAILABLE, "No location provider available."));
                return;
            }
            Location lastKnownLocation = locationManager.getLastKnownLocation(validProvider);
            if (lastKnownLocation == null || ((double) (SystemClock.currentTimeMillis() - lastKnownLocation.getTime())) >= a2.b) {
                new SingleUpdateRequest(locationManager, validProvider, a2.f8697a, callback, callback2).a(lastKnownLocation);
                return;
            }
            callback.invoke(locationToMap(lastKnownLocation));
        } catch (SecurityException e) {
            throwLocationPermissionMissing(e);
        }
    }

    @ReactMethod
    public void startObserving(ReadableMap readableMap) {
        if (!"gps".equals(this.mWatchedProvider)) {
            LocationOptions a2 = LocationOptions.b(readableMap);
            try {
                LocationManager locationManager = (LocationManager) getReactApplicationContext().getSystemService("location");
                String validProvider = getValidProvider(locationManager, a2.c);
                if (validProvider == null) {
                    emitError(PositionError.POSITION_UNAVAILABLE, "No location provider available.");
                    return;
                }
                if (!validProvider.equals(this.mWatchedProvider)) {
                    locationManager.removeUpdates(this.mLocationListener);
                    locationManager.requestLocationUpdates(validProvider, 1000, a2.d, this.mLocationListener);
                }
                this.mWatchedProvider = validProvider;
            } catch (SecurityException e) {
                throwLocationPermissionMissing(e);
            }
        }
    }

    @ReactMethod
    public void stopObserving() {
        ((LocationManager) getReactApplicationContext().getSystemService("location")).removeUpdates(this.mLocationListener);
        this.mWatchedProvider = null;
    }

    @Nullable
    private String getValidProvider(LocationManager locationManager, boolean z) {
        String str = z ? "gps" : LogCategory.CATEGORY_NETWORK;
        if (!locationManager.isProviderEnabled(str)) {
            str = str.equals("gps") ? LogCategory.CATEGORY_NETWORK : "gps";
            if (!locationManager.isProviderEnabled(str)) {
                return null;
            }
        }
        int checkSelfPermission = ContextCompat.checkSelfPermission(getReactApplicationContext(), "android.permission.ACCESS_FINE_LOCATION");
        if (!str.equals("gps") || checkSelfPermission == 0) {
            return str;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static WritableMap locationToMap(Location location) {
        WritableMap createMap = Arguments.createMap();
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putDouble("latitude", location.getLatitude());
        createMap2.putDouble("longitude", location.getLongitude());
        createMap2.putDouble("altitude", location.getAltitude());
        createMap2.putDouble("accuracy", (double) location.getAccuracy());
        createMap2.putDouble("heading", (double) location.getBearing());
        createMap2.putDouble("speed", (double) location.getSpeed());
        createMap.putMap("coords", createMap2);
        createMap.putDouble("timestamp", (double) location.getTime());
        if (Build.VERSION.SDK_INT >= 18) {
            createMap.putBoolean("mocked", location.isFromMockProvider());
        }
        return createMap;
    }

    /* access modifiers changed from: private */
    public void emitError(int i, String str) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("geolocationError", PositionError.buildError(i, str));
    }

    private static void throwLocationPermissionMissing(SecurityException securityException) {
        throw new SecurityException("Looks like the app doesn't have the permission to access location.\nAdd the following line to your app's AndroidManifest.xml:\n<uses-permission android:name=\"android.permission.ACCESS_FINE_LOCATION\" />", securityException);
    }

    private static class SingleUpdateRequest {
        private static final int k = 120000;
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public final Callback f8698a;
        /* access modifiers changed from: private */
        public final Callback b;
        /* access modifiers changed from: private */
        public final LocationManager c;
        private final String d;
        private final long e;
        /* access modifiers changed from: private */
        public Location f;
        /* access modifiers changed from: private */
        public final Handler g;
        /* access modifiers changed from: private */
        public final Runnable h;
        /* access modifiers changed from: private */
        public final LocationListener i;
        /* access modifiers changed from: private */
        public boolean j;

        private SingleUpdateRequest(LocationManager locationManager, String str, long j2, Callback callback, Callback callback2) {
            this.g = new Handler();
            this.h = new Runnable() {
                public void run() {
                    synchronized (SingleUpdateRequest.this) {
                        if (!SingleUpdateRequest.this.j) {
                            SingleUpdateRequest.this.b.invoke(PositionError.buildError(PositionError.TIMEOUT, "Location request timed out"));
                            SingleUpdateRequest.this.c.removeUpdates(SingleUpdateRequest.this.i);
                            FLog.i(ReactConstants.TAG, "LocationModule: Location request timed out");
                            boolean unused = SingleUpdateRequest.this.j = true;
                        }
                    }
                }
            };
            this.i = new LocationListener() {
                public void onProviderDisabled(String str) {
                }

                public void onProviderEnabled(String str) {
                }

                public void onStatusChanged(String str, int i, Bundle bundle) {
                }

                public void onLocationChanged(Location location) {
                    synchronized (SingleUpdateRequest.this) {
                        if (!SingleUpdateRequest.this.j && SingleUpdateRequest.this.a(location, SingleUpdateRequest.this.f)) {
                            SingleUpdateRequest.this.f8698a.invoke(GeolocationModule.locationToMap(location));
                            SingleUpdateRequest.this.g.removeCallbacks(SingleUpdateRequest.this.h);
                            boolean unused = SingleUpdateRequest.this.j = true;
                            SingleUpdateRequest.this.c.removeUpdates(SingleUpdateRequest.this.i);
                        }
                        Location unused2 = SingleUpdateRequest.this.f = location;
                    }
                }
            };
            this.c = locationManager;
            this.d = str;
            this.e = j2;
            this.f8698a = callback;
            this.b = callback2;
        }

        public void a(Location location) {
            this.f = location;
            this.c.requestLocationUpdates(this.d, 100, 1.0f, this.i);
            this.g.postDelayed(this.h, this.e);
        }

        /* access modifiers changed from: private */
        public boolean a(Location location, Location location2) {
            if (location2 == null) {
                return true;
            }
            long time = location.getTime() - location2.getTime();
            boolean z = time > 120000;
            boolean z2 = time < -120000;
            boolean z3 = time > 0;
            if (z) {
                return true;
            }
            if (z2) {
                return false;
            }
            int accuracy = (int) (location.getAccuracy() - location2.getAccuracy());
            boolean z4 = accuracy > 0;
            boolean z5 = accuracy < 0;
            boolean z6 = accuracy > 200;
            boolean a2 = a(location.getProvider(), location2.getProvider());
            if (z5) {
                return true;
            }
            if (z3 && !z4) {
                return true;
            }
            if (!z3 || z6 || !a2) {
                return false;
            }
            return true;
        }

        private boolean a(String str, String str2) {
            if (str == null) {
                return str2 == null;
            }
            return str.equals(str2);
        }
    }
}
