package com.xiaomi.smarthome.device.bluetooth.ui;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BleCacheUtils;
import com.xiaomi.smarthome.device.bluetooth.BleDeviceGroup;
import com.xiaomi.smarthome.device.bluetooth.ui.BleMatchListChoiceActivity;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.model.UnduplicateList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BleMatchListChoiceActivity extends BaseActivity {
    public static final String KEY_MODEL = "key_model";

    /* renamed from: a  reason: collision with root package name */
    private ListView f15241a;
    private BindListAdapter b;
    /* access modifiers changed from: private */
    public List<BleDevice> c;
    private BleDeviceGroup d;
    private TextView e;
    private TextView f;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ble_match_list);
        String stringExtra = getIntent().getStringExtra("key_model");
        this.c = new ArrayList();
        this.d = BLEDeviceManager.a(stringExtra);
        if (!(this.d == null || this.d.w() == null)) {
            for (BleDevice next : this.d.w()) {
                if (!this.c.contains(next)) {
                    this.c.add(next);
                }
            }
        }
        Collections.sort(this.c, $$Lambda$BleMatchListChoiceActivity$Es3XB2TzYwbI55iYQq4PDKTTp4k.INSTANCE);
        a();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int a(BleDevice bleDevice, BleDevice bleDevice2) {
        return bleDevice2.rssi - bleDevice.rssi;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    private void a() {
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        if (this.d != null) {
            textView.setText(this.d.s());
        }
        ((ImageView) findViewById(R.id.module_a_3_return_btn)).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                BleMatchListChoiceActivity.this.b(view);
            }
        });
        this.e = (TextView) findViewById(R.id.module_a_3_right_text_btn);
        this.e.setText(R.string.select_all);
        if (this.c.size() > 0) {
            this.e.setVisibility(0);
            this.e.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    BleMatchListChoiceActivity.this.a(view);
                }
            });
        }
        findViewById(R.id.module_a_3_right_btn).setVisibility(8);
        this.f = (TextView) findViewById(R.id.add_batch);
        this.f.setEnabled(false);
        this.f.setOnClickListener(new View.OnClickListener(textView) {
            private final /* synthetic */ TextView f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                BleMatchListChoiceActivity.this.a(this.f$1, view);
            }
        });
        this.f15241a = (ListView) findViewById(R.id.listview);
        this.f15241a.setSelector(new ColorDrawable(0));
        this.b = new BindListAdapter();
        this.f15241a.setAdapter(this.b);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        onBackPressed();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        this.b.a(this.b.b.size() < this.c.size());
        this.b.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(TextView textView, View view) {
        BleMatchListBatchActivity.open(this, new ArrayList(this.b.b), textView.getText().toString());
    }

    public void onBackPressed() {
        finish();
    }

    /* access modifiers changed from: private */
    public void a(int i) {
        try {
            if (i >= this.c.size()) {
                this.e.setText(R.string.unselect_all);
            } else {
                this.e.setText(R.string.select_all);
            }
            if (this.b.b.size() > 0) {
                this.f.setEnabled(true);
            } else {
                this.f.setEnabled(false);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private class BindListAdapter extends BaseAdapter {
        /* access modifiers changed from: private */
        public List<String> b;

        public long getItemId(int i) {
            return (long) i;
        }

        private BindListAdapter() {
            this.b = new UnduplicateList();
        }

        private class ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            SimpleDraweeView f15243a;
            TextView b;
            TextView c;
            View d;
            ImageView e;
            ImageView f;
            ImageView g;

            private ViewHolder() {
            }
        }

        public int getCount() {
            if (BleMatchListChoiceActivity.this.c != null) {
                return BleMatchListChoiceActivity.this.c.size();
            }
            return 0;
        }

        /* renamed from: a */
        public BleDevice getItem(int i) {
            if (i < 0 || i >= getCount()) {
                return null;
            }
            return (BleDevice) BleMatchListChoiceActivity.this.c.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(BleMatchListChoiceActivity.this).inflate(R.layout.ble_bind_item, (ViewGroup) null);
                viewHolder = new ViewHolder();
                viewHolder.f15243a = (SimpleDraweeView) view.findViewById(R.id.icon);
                viewHolder.b = (TextView) view.findViewById(R.id.name);
                viewHolder.c = (TextView) view.findViewById(R.id.mac);
                viewHolder.d = view.findViewById(R.id.content);
                viewHolder.e = (ImageView) view.findViewById(R.id.signal);
                viewHolder.f = (ImageView) view.findViewById(R.id.arrow);
                viewHolder.g = (ImageView) view.findViewById(R.id.check);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            BleDevice a2 = getItem(i);
            viewHolder.b.setText(BleMatchListChoiceActivity.this.a(a2));
            viewHolder.c.setText(a2.mac);
            viewHolder.c.setVisibility(TextUtils.isEmpty(a2.mac) ? 8 : 0);
            viewHolder.f.setVisibility(8);
            viewHolder.e.setVisibility(8);
            viewHolder.g.setVisibility(0);
            viewHolder.d.setOnClickListener(new View.OnClickListener(a2, viewHolder) {
                private final /* synthetic */ BleDevice f$1;
                private final /* synthetic */ BleMatchListChoiceActivity.BindListAdapter.ViewHolder f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(View view) {
                    BleMatchListChoiceActivity.BindListAdapter.this.a(this.f$1, this.f$2, view);
                }
            });
            if (this.b.contains(a2.mac)) {
                viewHolder.g.setBackgroundResource(R.drawable.icon_checkbox_selected_01);
            } else {
                viewHolder.g.setBackgroundResource(R.drawable.icon_checkbox_selected_02);
            }
            DeviceFactory.b(a2.model, viewHolder.f15243a);
            return view;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(BleDevice bleDevice, ViewHolder viewHolder, View view) {
            boolean contains = this.b.contains(bleDevice.mac);
            a(bleDevice.mac, !contains);
            viewHolder.g.setBackgroundResource(!contains ? R.drawable.icon_checkbox_selected_01 : R.drawable.icon_checkbox_selected_02);
        }

        public void a(boolean z) {
            for (int i = 0; i < BleMatchListChoiceActivity.this.c.size(); i++) {
                a(((BleDevice) BleMatchListChoiceActivity.this.c.get(i)).mac, z);
            }
        }

        public void a(String str, boolean z) {
            if (!TextUtils.isEmpty(str)) {
                if (z) {
                    this.b.add(str);
                } else {
                    this.b.remove(str);
                }
                BleMatchListChoiceActivity.this.a(this.b.size());
            }
        }
    }

    /* access modifiers changed from: private */
    public String a(BleDevice bleDevice) {
        String d2 = BleCacheUtils.d(bleDevice.mac);
        return TextUtils.isEmpty(d2) ? BleDeviceGroup.c(bleDevice.model) : d2;
    }
}
