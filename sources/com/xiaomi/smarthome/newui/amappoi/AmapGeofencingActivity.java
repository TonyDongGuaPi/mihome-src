package com.xiaomi.smarthome.newui.amappoi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class AmapGeofencingActivity extends BaseActivity implements AdapterView.OnItemClickListener, AMapLocationListener, AMap.OnCameraChangeListener, LocationSource {
    public static final String EXTRA_DATA_ACT_NAME = "extra_data_act_name";
    public static final String EXTRA_DATA_LATITUDE = "extra_data_latitude";
    public static final String EXTRA_DATA_LONGITUDE = "extra_data_longitude";
    public static final String EXTRA_DATA_POID = "extra_data_poid";
    public static final String EXTRA_DATA_RADIUS = "extra_data_radius";
    public static final String EXTRA_DATA_RADIUS_DEGREE = "extra_data_radius_degree";
    public static final String EXTRA_DATA_READABLE_ADDRESS = "extra_data_readable_address";
    public static final String EXTRA_DATA_SUB_TITLE = "extra_data_sub_title";
    public static final String EXTRA_MAX_R = "extra_data_max_radius";
    public static final String EXTRA_MIN_R = "extra_data_min_radius";
    private static final int z = 1;
    private PublishSubject<LatLngEntity> A;

    /* renamed from: a  reason: collision with root package name */
    private int f20429a = 500;
    private int b = 2000;
    private MapView c = null;
    private AMap d;
    /* access modifiers changed from: private */
    public AMapLocation e;
    private ImageView f;
    private LocationBean g;
    private XQProgressDialog h;
    /* access modifiers changed from: private */
    public int i;
    private float j = 15.0f;
    private ImageView k;
    private int l = R.drawable.geofencing_circle;
    private TextView m;
    /* access modifiers changed from: private */
    public GeocodeSearch n;
    /* access modifiers changed from: private */
    public String o = null;
    /* access modifiers changed from: private */
    public String p = null;
    /* access modifiers changed from: private */
    public String q = null;
    /* access modifiers changed from: private */
    public long r;
    private View s;
    /* access modifiers changed from: private */
    public TextView t;
    private TextView u;
    private TextView v;
    private AMapLocationClient w;
    private LocationSource.OnLocationChangedListener x;
    private AMapLocationClientOption y;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_amap_geo_fencing_layout);
        getIntent().getDoubleExtra(EXTRA_DATA_LATITUDE, 0.0d);
        getIntent().getDoubleExtra(EXTRA_DATA_LONGITUDE, 0.0d);
        this.i = (int) getIntent().getFloatExtra(EXTRA_DATA_RADIUS, -1.0f);
        this.q = getIntent().getStringExtra(EXTRA_DATA_RADIUS_DEGREE);
        this.o = getIntent().getStringExtra(EXTRA_DATA_SUB_TITLE);
        this.p = getIntent().getStringExtra(EXTRA_DATA_ACT_NAME);
        this.r = getIntent().getLongExtra(EXTRA_DATA_POID, 0);
        this.f20429a = getIntent().getIntExtra(EXTRA_MIN_R, 500);
        this.b = getIntent().getIntExtra(EXTRA_MAX_R, 2000);
        a(bundle);
        b();
        activate((LocationSource.OnLocationChangedListener) null);
    }

    private void a(Bundle bundle) {
        b(bundle);
        this.f = (ImageView) findViewById(R.id.module_a_3_return_btn);
        this.f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AmapGeofencingActivity.this.setResult(0, (Intent) null);
                AmapGeofencingActivity.this.finish();
            }
        });
        this.u = (TextView) findViewById(R.id.module_a_3_return_title);
        this.u.setText(R.string.select_loc);
        this.v = (TextView) findViewById(R.id.module_a_3_right_text_btn);
        this.v.setText(R.string.confirm);
        this.v.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("longitude", AmapGeofencingActivity.this.e.getLongitude());
                    jSONObject.put("latitude", AmapGeofencingActivity.this.e.getLatitude());
                    jSONObject.put("radius", AmapGeofencingActivity.this.i);
                    if (!TextUtils.isEmpty(AmapGeofencingActivity.this.p)) {
                        jSONObject.put("act_name", AmapGeofencingActivity.this.p);
                    }
                    if (!TextUtils.isEmpty(AmapGeofencingActivity.this.q)) {
                        jSONObject.put("radius_degree", AmapGeofencingActivity.this.q);
                    }
                    intent.putExtra("value", jSONObject.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (!TextUtils.isEmpty(AmapGeofencingActivity.this.o)) {
                    String access$400 = AmapGeofencingActivity.this.o;
                    Object[] objArr = new Object[1];
                    objArr[0] = TextUtils.isEmpty(AmapGeofencingActivity.this.t.getText()) ? "" : AmapGeofencingActivity.this.t.getText();
                    intent.putExtra("key_name", String.format(access$400, objArr));
                }
                intent.putExtra("poi_name", TextUtils.isEmpty(AmapGeofencingActivity.this.t.getText()) ? "" : AmapGeofencingActivity.this.t.getText());
                if (AmapGeofencingActivity.this.r > 0) {
                    intent.putExtra(AmapGeofencingActivity.EXTRA_DATA_POID, AmapGeofencingActivity.this.r);
                }
                AmapGeofencingActivity.this.setResult(-1, intent);
                AmapGeofencingActivity.this.finish();
            }
        });
        this.k = (ImageView) findViewById(R.id.geo_fencing_circle);
        this.m = (TextView) findViewById(R.id.radius);
        findViewById(R.id.my_location_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AmapGeofencingActivity.this.activate((LocationSource.OnLocationChangedListener) null);
            }
        });
        this.s = findViewById(R.id.search_places);
        this.t = (TextView) findViewById(R.id.search_places_tv);
        this.s.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (AmapGeofencingActivity.this.e == null) {
                    ToastUtil.a((int) R.string.area_location_failed);
                    return;
                }
                Intent intent = new Intent(AmapGeofencingActivity.this, AmapPoiActivity.class);
                intent.putExtra("map_location", AmapGeofencingActivity.this.e);
                AmapGeofencingActivity.this.startActivityForResult(intent, 1);
            }
        });
        this.n = new GeocodeSearch(this);
    }

    private void b(Bundle bundle) {
        this.c = findViewById(R.id.mapView);
        this.c.onCreate(bundle);
        this.d = this.c.getMap();
        UiSettings uiSettings = this.d.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setMyLocationButtonEnabled(false);
        this.d.setOnCameraChangeListener(this);
        this.d.setMyLocationEnabled(true);
        this.d.setLocationSource(this);
        Locale I = CoreApi.a().I();
        if (I == null) {
            return;
        }
        if ("zh".equalsIgnoreCase(I.getLanguage())) {
            this.d.setMapLanguage("zh_cn");
        } else {
            this.d.setMapLanguage("en");
        }
    }

    private void a() {
        this.j = this.d.getCameraPosition().zoom;
        this.i = (int) ((this.d.getScalePerPixel() * ((float) DisplayUtils.a(200.0f))) / 2.0f);
        if (this.i > this.b) {
            if (this.l == R.drawable.geofencing_circle) {
                this.l = R.drawable.geofencing_circle_red;
                this.k.setImageResource(this.l);
            }
            this.v.setEnabled(false);
        } else if (this.i < this.f20429a) {
            if (this.l == R.drawable.geofencing_circle) {
                this.l = R.drawable.geofencing_circle_red;
                this.k.setImageResource(this.l);
            }
            this.v.setEnabled(false);
        } else {
            if (this.l == R.drawable.geofencing_circle_red) {
                this.l = R.drawable.geofencing_circle;
                this.k.setImageResource(this.l);
            }
            this.v.setEnabled(true);
        }
        this.m.setText(getResources().getQuantityString(R.plurals.distance_in_meter, this.i, new Object[]{Integer.valueOf(this.i)}));
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 1 && intent != null) {
            LocationBean locationBean = (LocationBean) intent.getParcelableExtra("map_location");
            this.e.setLongitude(locationBean.a());
            this.e.setLatitude(locationBean.b());
            if (locationBean != null) {
                a(locationBean);
            }
        }
    }

    private void a(LocationBean locationBean) {
        this.d.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(locationBean.b(), locationBean.a()), this.j));
        this.t.setText(locationBean.d());
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j2) {
        if (adapterView.getId() != R.id.lv_data) {
            List<LocationBean> a2 = InputTipTask.a((Context) this).a();
            this.d.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(a2.get(i2).b(), a2.get(i2).a()), 15.0f));
        }
    }

    private void b() {
        this.h = new XQProgressDialog(this);
        this.h.setCancelable(false);
        this.h.setMessage(getResources().getString(R.string.loading));
        this.h.show();
        this.h.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
            }
        });
    }

    public void dismissLoadingDialog() {
        if (this.h != null) {
            this.h.dismiss();
        }
    }

    public void onLocationChanged(AMapLocation aMapLocation) {
        this.e = aMapLocation;
        dismissLoadingDialog();
        if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
            this.d.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude()), this.j));
            a();
        }
    }

    public void onCameraChange(CameraPosition cameraPosition) {
        a();
    }

    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        LatLngEntity latLngEntity = new LatLngEntity(cameraPosition.target.latitude, cameraPosition.target.longitude);
        this.e.setLatitude(cameraPosition.target.latitude);
        this.e.setLongitude(cameraPosition.target.longitude);
        a(latLngEntity);
    }

    private void a(LatLngEntity latLngEntity) {
        if (this.A == null) {
            this.A = PublishSubject.create();
            this.A.debounce(800, TimeUnit.MILLISECONDS).switchMap(new Function<LatLngEntity, ObservableSource<RegeocodeAddress>>() {
                /* renamed from: a */
                public ObservableSource<RegeocodeAddress> apply(LatLngEntity latLngEntity) throws Exception {
                    return AmapGeofencingActivity.this.b(latLngEntity);
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableObserver<RegeocodeAddress>() {
                public void onComplete() {
                }

                public void onError(Throwable th) {
                }

                /* renamed from: a */
                public void onNext(RegeocodeAddress regeocodeAddress) {
                    AmapGeofencingActivity.this.a(regeocodeAddress);
                }
            });
        }
        this.A.onNext(latLngEntity);
    }

    /* access modifiers changed from: private */
    public Observable<RegeocodeAddress> b(final LatLngEntity latLngEntity) {
        return Observable.create(new ObservableOnSubscribe<RegeocodeAddress>() {
            public void subscribe(ObservableEmitter<RegeocodeAddress> observableEmitter) throws Exception {
                new ArrayList();
                RegeocodeAddress fromLocation = AmapGeofencingActivity.this.n.getFromLocation(new RegeocodeQuery(new LatLonPoint(latLngEntity.a(), latLngEntity.b()), 200.0f, GeocodeSearch.AMAP));
                if (!AmapGeofencingActivity.this.isValid()) {
                    observableEmitter.onComplete();
                    return;
                }
                observableEmitter.onNext(fromLocation);
                observableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }

    /* access modifiers changed from: private */
    public void a(RegeocodeAddress regeocodeAddress) {
        if (isValid()) {
            this.t.setText(regeocodeAddress.getFormatAddress());
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.c.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.c.onPause();
        this.w.stopLocation();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.c.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.c.onDestroy();
        if (this.w != null) {
            this.w.stopLocation();
            this.w.onDestroy();
        }
        super.onDestroy();
    }

    public void activate(LocationSource.OnLocationChangedListener onLocationChangedListener) {
        if (this.w == null) {
            this.w = new AMapLocationClient(this);
            this.y = new AMapLocationClientOption();
            this.y.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            this.y.setNeedAddress(false);
            this.y.setOnceLocation(true);
        }
        this.w.setLocationListener(this);
        this.w.setLocationOption(this.y);
        this.w.startLocation();
    }

    public void deactivate() {
        if (this.w != null) {
            this.w.stopLocation();
            this.w.onDestroy();
        }
        this.w = null;
    }

    public void onBackPressed() {
        setResult(0, (Intent) null);
        finish();
    }
}
