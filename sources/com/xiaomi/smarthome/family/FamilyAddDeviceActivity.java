package com.xiaomi.smarthome.family;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class FamilyAddDeviceActivity extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public List<Device> f15619a = new ArrayList();
    private DeviceAdapter b;
    /* access modifiers changed from: private */
    public SparseBooleanArray c = new SparseBooleanArray();
    private FamilyItemData d = null;
    private TextView e;
    private ListView f;
    /* access modifiers changed from: private */
    public XQProgressDialog g;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.family_add_device);
        this.d = (FamilyItemData) getIntent().getParcelableExtra(FamilyItemData.f15689a);
        if (this.d == null) {
            finish();
            return;
        }
        b();
        c();
        initTitle();
        this.b = new DeviceAdapter();
        this.f.setAdapter(this.b);
    }

    public void initTitle() {
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FamilyAddDeviceActivity.this.finish();
            }
        });
        this.e.setText(R.string.family_add_device);
    }

    public void onResume() {
        super.onResume();
        a();
    }

    private void a() {
        List<Device> d2 = SmartHomeDeviceManager.a().d();
        this.f15619a.clear();
        for (Device next : d2) {
            if (next.isOwner() && next.canAuth && !next.isSubDevice() && !next.isFamily()) {
                this.f15619a.add(next);
            }
        }
        this.c.clear();
        this.b.notifyDataSetChanged();
    }

    private void b() {
        this.e = (TextView) findViewById(R.id.module_a_3_return_title);
        this.f = (ListView) findViewById(R.id.lv_device_list);
    }

    private void c() {
        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FamilyAddDeviceActivity.this.d();
            }
        });
        this.g = new XQProgressDialog(this);
        this.g.setCancelable(false);
        this.g.setMessage(getString(R.string.family_adding));
    }

    public void onDestroy() {
        super.onDestroy();
        this.g.dismiss();
        this.g = null;
    }

    /* access modifiers changed from: private */
    public void d() {
        if (this.c.size() == 0) {
            ToastUtil.a((Context) this, (int) R.string.family_no_device_selected);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.c.size(); i++) {
            int keyAt = this.c.keyAt(i);
            if (this.c.get(keyAt) && this.f15619a.size() > keyAt) {
                arrayList.add(this.f15619a.get(keyAt).did);
            }
        }
        this.g.show();
        RemoteFamilyApi.a().c(this, this.d.f, (String) arrayList.get(0), new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                if (FamilyAddDeviceActivity.this.g != null) {
                    FamilyAddDeviceActivity.this.g.dismiss();
                    Toast.makeText(FamilyAddDeviceActivity.this, R.string.family_add_device_succeed, 0).show();
                    SmartHomeDeviceManager.a().p();
                    FamilyAddDeviceActivity.this.finish();
                }
            }

            public void onFailure(Error error) {
                if (FamilyAddDeviceActivity.this.g != null) {
                    FamilyAddDeviceActivity.this.g.dismiss();
                    Toast.makeText(FamilyAddDeviceActivity.this, R.string.family_add_device_failed, 0).show();
                }
            }
        });
    }

    class DeviceAdapter extends BaseAdapter {
        public long getItemId(int i) {
            return 0;
        }

        DeviceAdapter() {
        }

        public int getCount() {
            return FamilyAddDeviceActivity.this.f15619a.size();
        }

        public Object getItem(int i) {
            if (i <= 0 || i >= FamilyAddDeviceActivity.this.f15619a.size()) {
                return null;
            }
            return FamilyAddDeviceActivity.this.f15619a.get(i);
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(FamilyAddDeviceActivity.this).inflate(R.layout.message_item_new, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.b = (SimpleDraweeView) view.findViewById(R.id.device_icon);
                viewHolder.c = (TextView) view.findViewById(R.id.right_tv);
                viewHolder.d = (CheckBox) view.findViewById(R.id.ratio_btn);
                viewHolder.e = (TextView) view.findViewById(R.id.device_item);
                viewHolder.f = (TextView) view.findViewById(R.id.device_item_info);
                viewHolder.g = view.findViewById(R.id.arrow);
                viewHolder.f15626a = view.findViewById(R.id.right_fl);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.g.setVisibility(8);
            Device device = (Device) FamilyAddDeviceActivity.this.f15619a.get(i);
            if (device != null) {
                DeviceFactory.b(device.model, viewHolder.b);
                viewHolder.e.setText(device.name);
                viewHolder.f.setText(device.desc);
            }
            viewHolder.d.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!FamilyAddDeviceActivity.this.c.get(i)) {
                        FamilyAddDeviceActivity.this.c.put(i, true);
                    } else {
                        FamilyAddDeviceActivity.this.c.delete(i);
                    }
                }
            });
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ((ViewHolder) view.getTag()).d.performClick();
                }
            });
            if (FamilyAddDeviceActivity.this.c.get(i)) {
                viewHolder.d.setChecked(true);
            } else {
                viewHolder.d.setChecked(false);
            }
            return view;
        }

        class ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            View f15626a;
            SimpleDraweeView b;
            TextView c;
            CheckBox d;
            TextView e;
            TextView f;
            View g;

            ViewHolder() {
            }
        }
    }
}
