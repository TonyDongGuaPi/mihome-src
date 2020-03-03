package com.xiaomi.smarthome.listcamera;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.widget.DragSortController;
import com.xiaomi.smarthome.library.common.widget.DragSortListView;
import com.xiaomi.smarthome.listcamera.CameraGroupManager;
import com.xiaomi.smarthome.listcamera.adapter.CameraControlAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeviceControlChooseActivity extends BaseActivity implements View.OnClickListener, DragSortListView.DropListener {

    /* renamed from: a  reason: collision with root package name */
    private DragSortListView f19270a;
    private DragSortController b;
    private CameraControlAdapter c;
    private ListView d;
    private CameraControlAdapter e;
    private CameraGroupManager.GroupInfo f;
    /* access modifiers changed from: private */
    public List<Device> g = new ArrayList();
    private List<Device> h = new ArrayList();
    private View i;
    private View j;
    private View k;
    /* access modifiers changed from: private */
    public String l;
    private TextView m;
    private int n;
    private int o;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_edit_client_all_column);
        this.f19270a = (DragSortListView) findViewById(R.id.list_display);
        this.c = new CameraControlAdapter(this, true, R.drawable.client_all_column_remove);
        this.f19270a.setAdapter((ListAdapter) this.c);
        this.b = new DragSortController(this.f19270a);
        this.b.c((int) R.id.img_drag_handle);
        this.b.b(false);
        this.b.a(true);
        this.b.a(0);
        this.f19270a.setDragEnabled(true);
        this.f19270a.setFloatViewManager(this.b);
        this.f19270a.setOnTouchListener(this.b);
        this.f19270a.setDropListener(this);
        this.d = (ListView) findViewById(R.id.list_hidden);
        this.e = new CameraControlAdapter(this, false, R.drawable.client_all_column_add);
        this.d.setAdapter(this.e);
        this.i = findViewById(R.id.column_sort_hint_text);
        this.j = findViewById(R.id.column_display_hint);
        this.k = findViewById(R.id.column_hidden_hint);
        this.n = DisplayUtils.a((Context) this).y / 3;
        this.o = (this.n / DisplayUtils.a(52.0f)) + 1;
        this.n = this.o * DisplayUtils.a(52.0f);
        a();
        updateColumnData();
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.about_columns);
        this.m = (TextView) findViewById(R.id.module_a_3_right_text_btn);
        this.m.setText(R.string.common_finish);
        findViewById(R.id.module_a_3_right_text_btn).setVisibility(0);
        findViewById(R.id.module_a_3_right_text_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CameraGroupManager.a().a(DeviceControlChooseActivity.this.l, DeviceControlChooseActivity.this.g, new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        CameraInfoRefreshManager.a().g();
                        DeviceControlChooseActivity.this.finish();
                    }

                    public void onFailure(Error error) {
                        DeviceControlChooseActivity.this.finish();
                    }
                });
            }
        });
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    public void addControl(Device device) {
        this.h.remove(device);
        this.g.add(device);
        updateColumnData();
    }

    public void removeControl(Device device) {
        this.g.remove(device);
        this.h.add(device);
        updateColumnData();
    }

    private void a() {
        this.l = getIntent().getStringExtra("did");
        this.f = CameraGroupManager.a().a(this.l);
        if (this.f == null) {
            finish();
        } else {
            initDevice(getAllSupportDevice());
        }
    }

    /* access modifiers changed from: package-private */
    public void initDevice(List<Device> list) {
        HashMap hashMap = new HashMap();
        ArrayList<Device> arrayList = new ArrayList<>();
        for (CameraGroupManager.GroupInfo.DeviceControlInfo next : this.f.e) {
            hashMap.put(next.f19241a, next);
        }
        for (Device next2 : list) {
            if (hashMap.containsKey(next2.did)) {
                arrayList.add(next2);
            } else {
                this.h.add(next2);
            }
        }
        HashMap hashMap2 = new HashMap();
        for (Device device : arrayList) {
            hashMap2.put(device.did, device);
        }
        for (CameraGroupManager.GroupInfo.DeviceControlInfo next3 : this.f.e) {
            if (SmartHomeDeviceManager.a().n(next3.f19241a) != null) {
                this.g.add(hashMap2.get(next3.f19241a));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public List<Device> getAllSupportDevice() {
        ArrayList arrayList = new ArrayList();
        for (Device next : SmartHomeDeviceManager.a().d()) {
            if (next != null && CameraDeviceOpManager.a().a(next)) {
                arrayList.add(next);
            }
        }
        for (Device next2 : SmartHomeDeviceManager.a().k()) {
            if (next2 != null && CameraDeviceOpManager.a().a(next2)) {
                arrayList.add(next2);
            }
        }
        return arrayList;
    }

    public void updateColumnData() {
        if (this.g == null || this.g.isEmpty()) {
            this.f19270a.setVisibility(8);
            this.i.setVisibility(8);
            this.j.setVisibility(0);
        } else {
            this.f19270a.setVisibility(0);
            this.i.setVisibility(8);
            this.j.setVisibility(8);
            ViewGroup.LayoutParams layoutParams = this.f19270a.getLayoutParams();
            if (this.g.size() > this.o) {
                if (layoutParams.height == -2) {
                    layoutParams.height = this.n;
                    this.f19270a.requestLayout();
                }
            } else if (layoutParams.height != -2) {
                layoutParams.height = -2;
                this.f19270a.requestLayout();
            }
        }
        this.c.a(this.g);
        this.f19270a.setSelection(this.f19270a.getBottom());
        if (this.h == null || this.h.isEmpty()) {
            this.d.setVisibility(8);
            this.k.setVisibility(0);
        } else {
            this.d.setVisibility(0);
            this.k.setVisibility(8);
        }
        this.e.a(this.h);
    }

    public void drop(int i2, int i3) {
        if (i2 != i3) {
            this.g.add(i3, this.g.remove(i2));
            this.c.a(this.g);
        }
    }

    public void onClick(View view) {
        finish();
    }
}
