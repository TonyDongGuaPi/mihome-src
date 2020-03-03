package com.xiaomi.smarthome.framework.plugin.rn.viewmanager;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.Circle;
import com.amap.api.maps2d.model.CircleOptions;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.maps2d.model.Polyline;
import com.amap.api.maps2d.model.PolylineOptions;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.MIOTUtils;
import com.xiaomi.smarthome.listcamera.CameraFrameManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;

public class AMapViewManager extends SimpleViewManager {
    private static String REACT_CLASS = "MHMapView";
    private static final String TAG = "AMapViewManager";
    private static final float ZOOMLEVEL_INIT = -1000.0f;
    /* access modifiers changed from: private */
    public ThemedReactContext mReactContext;

    @ReactProp(name = "allowsBackgroundLocationUpdates")
    public void allowsBackgroundLocationUpdates(View view, boolean z) {
    }

    @ReactProp(name = "compassOrigin")
    public void compassOrigin(View view, ReadableMap readableMap) {
    }

    @ReactProp(name = "distanceFilter")
    public void distanceFilter(View view, double d) {
    }

    @ReactProp(name = "headingFilter")
    public void headingFilter(View view, double d) {
    }

    @ReactProp(name = "pausesLocationUpdatesAutomatically")
    public void pausesLocationUpdatesAutomatically(View view, boolean z) {
    }

    @ReactProp(name = "polylines")
    public void polylines(View view, ReadableArray readableArray) {
    }

    @ReactProp(name = "userTrackingMode")
    public void userTrackingMode(View view, String str) {
    }

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public View createViewInstance(ThemedReactContext themedReactContext) {
        this.mReactContext = themedReactContext;
        LocationSource rNMapView = new RNMapView(themedReactContext);
        rNMapView.getMap().getUiSettings().setAllGesturesEnabled(true);
        rNMapView.getMap().setLocationSource(rNMapView);
        return rNMapView;
    }

    @ReactProp(name = "showsUserLocation")
    public void showsUserLocation(View view, boolean z) {
        ((MapView) view).getMap().setMyLocationEnabled(z);
    }

    @ReactProp(name = "trafficEnabled")
    public void trafficEnabled(View view, boolean z) {
        ((MapView) view).getMap().setTrafficEnabled(z);
    }

    @ReactProp(name = "scaleOrigin")
    public void scaleOrigin(View view, int i) {
        ((MapView) view).getMap().getUiSettings().setZoomPosition(i);
    }

    @ReactProp(name = "showsScale")
    public void showsScale(View view, boolean z) {
        ((MapView) view).getMap().getUiSettings().setScaleControlsEnabled(z);
    }

    @ReactProp(name = "showsCompass")
    public void showsCompass(View view, boolean z) {
        ((MapView) view).getMap().getUiSettings().setCompassEnabled(z);
    }

    @ReactProp(name = "zoomEnabled")
    public void zoomEnabled(View view, boolean z) {
        ((MapView) view).getMap().getUiSettings().setZoomControlsEnabled(z);
    }

    @ReactProp(name = "userLocation")
    public void userLocation(View view, ReadableMap readableMap) {
        RNMapView rNMapView = (RNMapView) view;
        if (rNMapView.c != null && readableMap != null) {
            ReadableMap f = MIOTUtils.f(readableMap, "location");
            AMapLocation aMapLocation = new AMapLocation("");
            aMapLocation.setLatitude(f == null ? MIOTUtils.c(readableMap, "latitude") : MIOTUtils.c(f, "latitude"));
            aMapLocation.setLongitude(f == null ? MIOTUtils.c(readableMap, "longitude") : MIOTUtils.c(f, "longitude"));
            rNMapView.c.onLocationChanged(aMapLocation);
        }
    }

    @ReactProp(name = "annotations")
    public void annotations(View view, ReadableArray readableArray) {
        AMapViewManager aMapViewManager = this;
        ReadableArray readableArray2 = readableArray;
        Iterator it = ((RNMapView) view).f.iterator();
        while (it.hasNext()) {
            ((Marker) it.next()).remove();
        }
        if (readableArray2 == null || readableArray.size() == 0) {
            AMapViewManager aMapViewManager2 = aMapViewManager;
            return;
        }
        int i = 0;
        while (i < readableArray.size()) {
            ReadableMap map = readableArray2.getMap(i);
            MIOTUtils.a(map, "id");
            String a2 = MIOTUtils.a(map, "title");
            String a3 = MIOTUtils.a(map, "subtitle");
            boolean b = MIOTUtils.b(map, "canShowCallout");
            String a4 = MIOTUtils.a(MIOTUtils.e(map, "image"));
            double c = MIOTUtils.c(map, ViewProps.Z_INDEX);
            boolean b2 = MIOTUtils.b(map, "lockedToScreen");
            ReadableMap f = MIOTUtils.f(map, "coordinate");
            double c2 = MIOTUtils.c(f, "latitude");
            int i2 = i;
            double c3 = MIOTUtils.c(f, "longitude");
            ReadableMap f2 = MIOTUtils.f(map, "lockedScreenPoint");
            float c4 = (float) MIOTUtils.c(f2, Constants.Name.Y);
            final MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.draggable(!b2);
            markerOptions.anchor((float) MIOTUtils.c(f2, "x"), c4);
            markerOptions.title(a2);
            markerOptions.snippet(a3);
            markerOptions.position(new LatLng(c2, c3));
            markerOptions.zIndex((float) c);
            markerOptions.visible(b);
            final View view2 = view;
            getBitmap(view.getContext(), Uri.parse(a4), new CameraFrameManager.CameraBitmapCallback() {
                public void onBitmapLoaded(Bitmap bitmap, long j) {
                    markerOptions.icon(BitmapDescriptorFactory.fromBitmap(bitmap));
                    ((RNMapView) view2).f.add(view2.getMap().addMarker(markerOptions));
                }
            });
            View view3 = view2;
            readableArray2 = readableArray;
            i = i2 + 1;
            aMapViewManager = this;
        }
        AMapViewManager aMapViewManager3 = aMapViewManager;
    }

    @ReactProp(name = "circles")
    public void circles(View view, ReadableArray readableArray) {
        ReadableArray readableArray2 = readableArray;
        RNMapView rNMapView = (RNMapView) view;
        Iterator it = rNMapView.e.iterator();
        while (it.hasNext()) {
            ((Circle) it.next()).remove();
        }
        if (readableArray2 != null && readableArray.size() != 0) {
            int i = 0;
            while (i < readableArray.size()) {
                ReadableMap map = readableArray2.getMap(i);
                MIOTUtils.a(map, "id");
                double c = MIOTUtils.c(map, "radius");
                double c2 = MIOTUtils.c(map, "lineWidth");
                double c3 = MIOTUtils.c(map, ViewProps.Z_INDEX);
                ReadableMap f = MIOTUtils.f(map, "coordinate");
                double c4 = MIOTUtils.c(f, "latitude");
                double c5 = MIOTUtils.c(f, "longitude");
                CircleOptions circleOptions = new CircleOptions();
                circleOptions.fillColor(getColor(map, "fillColor"));
                circleOptions.strokeColor(getColor(map, "strokeColor"));
                circleOptions.strokeWidth((float) c2);
                circleOptions.radius(c);
                circleOptions.zIndex((float) c3);
                circleOptions.center(new LatLng(c4, c5));
                rNMapView.e.add(((MapView) view).getMap().addCircle(circleOptions));
                i++;
                readableArray2 = readableArray;
            }
        }
    }

    private int getColor(ReadableMap readableMap, String str) {
        ReadableArray e = MIOTUtils.e(readableMap, str);
        double[] dArr = {1.0d, 1.0d, 1.0d, 1.0d};
        if (e != null) {
            for (int i = 0; i < e.size(); i++) {
                dArr[i] = e.getDouble(i);
            }
        }
        return (((int) ((dArr[3] * 255.0d) + 0.5d)) << 24) | (((int) ((dArr[0] * 255.0d) + 0.5d)) << 16) | (((int) ((dArr[1] * 255.0d) + 0.5d)) << 8) | ((int) ((dArr[2] * 255.0d) + 0.5d));
    }

    @ReactProp(name = "multiPolylines")
    public void multiPolylines(View view, ReadableArray readableArray) {
        ReadableArray readableArray2 = readableArray;
        RNMapView rNMapView = (RNMapView) view;
        Iterator it = rNMapView.d.iterator();
        while (it.hasNext()) {
            ((Polyline) it.next()).remove();
        }
        if (readableArray2 != null && readableArray.size() != 0) {
            int i = 0;
            while (i < readableArray.size()) {
                PolylineOptions polylineOptions = new PolylineOptions();
                ReadableMap map = readableArray2.getMap(i);
                MIOTUtils.a(map, "id");
                double c = MIOTUtils.c(map, "renderLineWidth");
                ReadableArray e = MIOTUtils.e(map, "colors");
                double c2 = MIOTUtils.c(map, ViewProps.Z_INDEX);
                ReadableArray e2 = MIOTUtils.e(map, "coordinates");
                if (e2 != null) {
                    int i2 = 0;
                    while (i2 < e2.size()) {
                        ReadableMap map2 = e2.getMap(i2);
                        polylineOptions.add(new LatLng(MIOTUtils.c(map2, "latitude"), MIOTUtils.c(map2, "longitude")));
                        i2++;
                        i = i;
                        ReadableArray readableArray3 = readableArray;
                    }
                }
                int i3 = i;
                if (e != null && e.size() > 0) {
                    polylineOptions.color((int) ((long) e.getDouble(0)));
                }
                polylineOptions.width((float) c);
                polylineOptions.zIndex((float) c2);
                rNMapView.d.add(((MapView) view).getMap().addPolyline(polylineOptions));
                i = i3 + 1;
                readableArray2 = readableArray;
            }
        }
    }

    @ReactProp(name = "userLocationRepresentation")
    public void userLocationRepresentation(final View view, ReadableMap readableMap) {
        final MyLocationStyle myLocationStyle = new MyLocationStyle();
        getBitmap(view.getContext(), Uri.parse(MIOTUtils.a(MIOTUtils.e(readableMap, "image"))), new CameraFrameManager.CameraBitmapCallback() {
            public void onBitmapLoaded(Bitmap bitmap, long j) {
                myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromBitmap(bitmap));
                view.getMap().setMyLocationStyle(myLocationStyle);
            }
        });
    }

    @ReactProp(name = "mapType")
    public void mapType(View view, int i) {
        AMap map = ((MapView) view).getMap();
        int i2 = 1;
        if (i == 1) {
            i2 = 2;
        }
        map.setMapType(i2);
    }

    @ReactProp(name = "language")
    public void mapLanguage(View view, int i) {
        if (i == 1) {
            ((MapView) view).getMap().setMapLanguage("en");
        } else {
            ((MapView) view).getMap().setMapLanguage("zh_cn");
        }
    }

    @ReactProp(name = "logoPosition")
    public void setLogoPosition(View view, int i) {
        switch (i) {
            case 1:
                ((MapView) view).getMap().getUiSettings().setLogoPosition(1);
                return;
            case 2:
                ((MapView) view).getMap().getUiSettings().setLogoPosition(2);
                return;
            default:
                ((MapView) view).getMap().getUiSettings().setLogoPosition(0);
                return;
        }
    }

    @ReactProp(name = "desiredAccuracy")
    public void desiredAccuracy(View view, boolean z) {
        ((RNMapView) view).b.setLocationMode(z ? AMapLocationClientOption.AMapLocationMode.Hight_Accuracy : AMapLocationClientOption.AMapLocationMode.Battery_Saving);
    }

    @ReactProp(name = "zoomLevel")
    public void zoomLevel(View view, double d) {
        if (view instanceof RNMapView) {
            RNMapView rNMapView = (RNMapView) view;
            rNMapView.setZoomLevel((float) Math.min((double) rNMapView.getMap().getMaxZoomLevel(), Math.max((double) rNMapView.getMap().getMinZoomLevel(), d)));
            rNMapView.getMap().animateCamera(CameraUpdateFactory.zoomTo(rNMapView.getZoomLevel()));
            return;
        }
        LogUtil.b(TAG, "zoomLevel exec  view is not instanceof MapView...");
    }

    @ReactProp(name = "centerCoordinate")
    public void centerCoordinate(View view, ReadableMap readableMap) {
        if (readableMap == null) {
            LogUtil.c(TAG, "centerCoordinate exec... centerCoordinate is null...");
        } else if (view instanceof RNMapView) {
            RNMapView rNMapView = (RNMapView) view;
            double c = MIOTUtils.c(readableMap, "latitude");
            double c2 = MIOTUtils.c(readableMap, "longitude");
            float zoomLevel = rNMapView.getZoomLevel();
            LogUtil.c(TAG, "centerCoordinate exec... latitude: " + c + "   longitude: " + c2 + "  zoomLevel: " + zoomLevel);
            rNMapView.getMap().moveCamera(CameraUpdateFactory.newLatLng(new LatLng(c, c2)));
            if (zoomLevel != ZOOMLEVEL_INIT) {
                rNMapView.getMap().moveCamera(CameraUpdateFactory.zoomTo(zoomLevel));
            }
        } else {
            LogUtil.b(TAG, "centerCoordinate exec  view is not instanceof MapView...");
        }
    }

    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder().put("onUpdateUserLocation", MapBuilder.of("registrationName", "onUpdateUserLocation")).put("onSelectAnnotationView", MapBuilder.of("registrationName", "onSelectAnnotationView")).put("onLongPressedAtCoordinate", MapBuilder.of("registrationName", "onLongPressedAtCoordinate")).put("onSingleTappedAtCoordinate", MapBuilder.of("registrationName", "onSingleTappedAtCoordinate")).put("onMapWillZoomByUser", MapBuilder.of("registrationName", "onMapWillZoomByUser")).put("onMapDidZoomByUser", MapBuilder.of("registrationName", "onMapDidZoomByUser")).build();
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext themedReactContext, final View view) {
        AMap map = ((MapView) view).getMap();
        map.setOnMapLongClickListener(new AMap.OnMapLongClickListener() {
            public void onMapLongClick(LatLng latLng) {
                WritableMap createMap = Arguments.createMap();
                createMap.putDouble("longitude", latLng.longitude);
                createMap.putDouble("latitude", latLng.latitude);
                ((RCTEventEmitter) AMapViewManager.this.mReactContext.getJSModule(RCTEventEmitter.class)).receiveEvent(view.getId(), "onLongPressedAtCoordinate", createMap);
            }
        });
        map.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            public void onMyLocationChange(Location location) {
                Address address;
                WritableMap createMap = Arguments.createMap();
                Bundle extras = location.getExtras();
                createMap.putDouble("longitude", location.getLongitude());
                createMap.putDouble("latitude", location.getLatitude());
                createMap.putDouble("horizontalAccuracy", (double) location.getAccuracy());
                if (Build.VERSION.SDK_INT >= 26) {
                    createMap.putDouble("verticalAccuracy", (double) location.getVerticalAccuracyMeters());
                }
                if (!(extras == null || (address = (Address) extras.getParcelable("address")) == null)) {
                    createMap.putString("location", address.getLocality());
                }
                ((RCTEventEmitter) AMapViewManager.this.mReactContext.getJSModule(RCTEventEmitter.class)).receiveEvent(view.getId(), "onUpdateUserLocation", createMap);
            }
        });
        map.setOnMapClickListener(new AMap.OnMapClickListener() {
            public void onMapClick(LatLng latLng) {
                WritableMap createMap = Arguments.createMap();
                createMap.putDouble("longitude", latLng.longitude);
                createMap.putDouble("latitude", latLng.latitude);
                ((RCTEventEmitter) AMapViewManager.this.mReactContext.getJSModule(RCTEventEmitter.class)).receiveEvent(view.getId(), "onSingleTappedAtCoordinate", createMap);
            }
        });
        map.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                ((RCTEventEmitter) AMapViewManager.this.mReactContext.getJSModule(RCTEventEmitter.class)).receiveEvent(view.getId(), "onSelectAnnotationView", Arguments.createMap());
                return false;
            }
        });
        map.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            public void onCameraChange(CameraPosition cameraPosition) {
                WritableMap createMap = Arguments.createMap();
                createMap.putDouble("zoomLevel", (double) cameraPosition.zoom);
                createMap.putBoolean("wasUserAction", true);
                ((RCTEventEmitter) AMapViewManager.this.mReactContext.getJSModule(RCTEventEmitter.class)).receiveEvent(view.getId(), "onMapWillZoomByUser", createMap);
            }

            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                WritableMap createMap = Arguments.createMap();
                createMap.putDouble("zoomLevel", (double) cameraPosition.zoom);
                createMap.putBoolean("wasUserAction", true);
                ((RCTEventEmitter) AMapViewManager.this.mReactContext.getJSModule(RCTEventEmitter.class)).receiveEvent(view.getId(), "onMapDidZoomByUser", createMap);
            }
        });
    }

    public void getBitmap(Context context, Uri uri, final CameraFrameManager.CameraBitmapCallback cameraBitmapCallback) {
        Fresco.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(uri).setProgressiveRenderingEnabled(true).build(), context).subscribe(new BaseBitmapDataSubscriber() {
            public void onNewResultImpl(Bitmap bitmap) {
                cameraBitmapCallback.onBitmapLoaded(bitmap, 0);
            }

            public void onFailureImpl(DataSource dataSource) {
                cameraBitmapCallback.onBitmapLoaded((Bitmap) null, 0);
            }
        }, CallerThreadExecutor.getInstance());
    }

    public static class RNMapView extends MapView implements LocationSource {

        /* renamed from: a  reason: collision with root package name */
        private AMapLocationClient f17519a;
        /* access modifiers changed from: private */
        public AMapLocationClientOption b;
        /* access modifiers changed from: private */
        public LocationSource.OnLocationChangedListener c;
        /* access modifiers changed from: private */
        public ArrayList<Polyline> d = new ArrayList<>();
        /* access modifiers changed from: private */
        public ArrayList<Circle> e = new ArrayList<>();
        /* access modifiers changed from: private */
        public ArrayList<Marker> f = new ArrayList<>();
        private float g = AMapViewManager.ZOOMLEVEL_INIT;

        public RNMapView(Context context) {
            super(context);
        }

        public void activate(LocationSource.OnLocationChangedListener onLocationChangedListener) {
            this.c = onLocationChangedListener;
            if (this.f17519a == null) {
                this.f17519a = new AMapLocationClient(getContext());
                this.b = new AMapLocationClientOption();
                this.f17519a.setLocationListener(new AMapLocationListener() {
                    public void onLocationChanged(AMapLocation aMapLocation) {
                        if (RNMapView.this.c != null && aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                            RNMapView.this.c.onLocationChanged(aMapLocation);
                        }
                    }
                });
                this.b.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
                this.f17519a.setLocationOption(this.b);
                this.f17519a.startLocation();
            }
        }

        public void deactivate() {
            if (this.f17519a != null) {
                this.f17519a.stopLocation();
                this.f17519a.onDestroy();
            }
            this.f17519a = null;
            this.c = null;
        }

        /* access modifiers changed from: protected */
        public void onAttachedToWindow() {
            AMapViewManager.super.onAttachedToWindow();
            onCreate((Bundle) null);
        }

        /* access modifiers changed from: protected */
        public void onDetachedFromWindow() {
            AMapViewManager.super.onDetachedFromWindow();
            onDestroy();
        }

        /* access modifiers changed from: protected */
        public void onWindowVisibilityChanged(int i) {
            AMapViewManager.super.onWindowVisibilityChanged(i);
            if (i == 0) {
                onResume();
            } else {
                onPause();
            }
        }

        public void setZoomLevel(float f2) {
            this.g = f2;
        }

        public float getZoomLevel() {
            return this.g;
        }
    }
}
