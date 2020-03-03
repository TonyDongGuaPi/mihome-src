package com.xiaomi.smarthome.scene.geofence.manager;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.qti.location.sdk.IZatGeofenceService;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.location.SHLocationManager;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.scene.geofence.manager.model.GeoFenceItem;
import java.util.Map;

public class TestMIUIGFActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21570a = "\r\n";
    TextView btmTextView;
    TextView geoFenceTextView;
    Map<IZatGeofenceService.IZatGeofenceHandle, IZatGeofenceService.IzatGeofence> mHandleData = null;
    Map<String, IZatGeofenceService.IZatGeofenceHandle> mHandleMap = null;

    private void b() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_miui_geofence_test);
        this.btmTextView = (TextView) findViewById(R.id.btm_state_recyclerview);
        this.geoFenceTextView = (TextView) findViewById(R.id.gf_list);
        MIUIGeoFenceManager.b().c();
        a();
        findViewById(R.id.title_bar).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TestMIUIGFActivity.this.startActivity(new Intent(TestMIUIGFActivity.this, SmartFence.class));
            }
        });
        View findViewById = findViewById(R.id.click);
        findViewById.setVisibility(0);
        findViewById.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                final XQProgressDialog xQProgressDialog = new XQProgressDialog(TestMIUIGFActivity.this);
                xQProgressDialog.setCancelable(true);
                xQProgressDialog.show();
                SHLocationManager.a().a((SHLocationManager.LocationCallback) new SHLocationManager.LocationCallback() {
                    public void onGetLatLngSucceed(String str, Location location) {
                        xQProgressDialog.dismiss();
                        GeoFenceItem geoFenceItem = new GeoFenceItem(location.getLatitude(), location.getLongitude(), 100.0d, 3);
                        ServerBean a2 = ServerCompact.a(SHApplication.getAppContext());
                        if (a2 == null) {
                            a2 = new ServerBean("", "CN", "大陆");
                        }
                        MIUIGeoFenceManager.b().a(MIUIGeoFenceHelper.a(CoreApi.a().s(), a2.b), geoFenceItem);
                        TestMIUIGFActivity.this.a();
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void a() {
        StringBuilder sb = new StringBuilder();
        if (!(this.mHandleMap == null || this.mHandleData == null)) {
            for (Map.Entry next : this.mHandleMap.entrySet()) {
                sb.append("id:" + ((String) next.getKey()) + "\r\n");
                IZatGeofenceService.IzatGeofence izatGeofence = this.mHandleData.get(next.getValue());
                if (izatGeofence == null) {
                    sb.append("null!");
                } else {
                    sb.append("lat:" + izatGeofence.c() + ",lng=" + izatGeofence.d() + ",radius=" + izatGeofence.e() + ",Confidence=" + izatGeofence.h().name() + ",DwellTime=" + izatGeofence.i().a() + ",DwellType=" + izatGeofence.i().b() + ",TransitionType=" + izatGeofence.f().name());
                }
                sb.append("\r\n");
                sb.append("\r\n");
            }
        }
        this.geoFenceTextView.setText(sb.toString());
    }
}
