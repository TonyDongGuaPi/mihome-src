package com.xiaomi.smarthome.device.choosedevice;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfoStatus;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.ResetDevicePage;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.widget.HeaderGridView;
import java.util.ArrayList;
import java.util.List;

public class ScanQRCodeDeviceActivity extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public ChooseDeviceAdapter f15357a;
    private HeaderGridView b;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_scan_qrcode_device_list);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ScanQRCodeDeviceActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.choose_device_title);
        this.b = (HeaderGridView) findViewById(R.id.scan_qrcode_device_list);
        this.f15357a = new ChooseDeviceAdapter();
        this.b.addHeaderView(LayoutInflater.from(this).inflate(R.layout.space_scan_qrcode_device_list, this.b, false));
        this.b.setAdapter((ListAdapter) this.f15357a);
        SHApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                ScanQRCodeDeviceActivity.this.a();
            }
        });
    }

    /* access modifiers changed from: private */
    public void a() {
        final ArrayList arrayList = new ArrayList();
        List<PluginRecord> O = CoreApi.a().O();
        if (O != null) {
            arrayList.addAll(O);
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (((PluginRecord) arrayList.get(size)).c().e() != 5) {
                    arrayList.remove(size);
                } else {
                    int f = ((PluginRecord) arrayList.get(size)).c().f();
                    if (f != 3 && f != 4 && f != 1) {
                        arrayList.remove(size);
                    } else if (!(((PluginRecord) arrayList.get(size)).c().v() == PluginDeviceInfoStatus.RELEASE || ((PluginRecord) arrayList.get(size)).c().v() == PluginDeviceInfoStatus.WHITE_LIST)) {
                        arrayList.remove(size);
                    }
                }
            }
            this.mHandler.post(new Runnable() {
                public void run() {
                    ScanQRCodeDeviceActivity.this.f15357a.a((List<PluginRecord>) arrayList);
                    ScanQRCodeDeviceActivity.this.f15357a.notifyDataSetChanged();
                }
            });
        }
    }

    private class ChooseDeviceAdapter extends BaseAdapter {
        private List<PluginRecord> b;

        private boolean a(char c) {
            return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        private ChooseDeviceAdapter() {
            this.b = new ArrayList();
        }

        public void a(List<PluginRecord> list) {
            this.b = list;
        }

        public int getCount() {
            return this.b.size();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(ScanQRCodeDeviceActivity.this.getContext()).inflate(R.layout.choose_device_grid_item, viewGroup, false);
            }
            final PluginRecord pluginRecord = this.b.get(i);
            TextView textView = (TextView) view.findViewById(R.id.name);
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.image);
            ((ImageView) view.findViewById(R.id.image_new_tag)).setVisibility(8);
            String p = pluginRecord.p();
            if (p.length() <= 6) {
                textView.setText(p);
            } else {
                StringBuilder sb = new StringBuilder(p.substring(0, 6));
                StringBuilder sb2 = new StringBuilder(p.substring(6, p.length()));
                if (a(sb.charAt(sb.length() - 1)) && a(sb2.charAt(0))) {
                    while (sb2.length() > 0 && a(sb2.charAt(0))) {
                        sb.append(sb2.charAt(0));
                        sb2.deleteCharAt(0);
                    }
                }
                if (sb2.toString().trim().length() > 6) {
                    sb2.delete(6, sb2.length());
                    sb2.append("...");
                }
                textView.setText(sb.toString().trim() + "\n" + sb2.toString().trim());
            }
            DeviceFactory.b(pluginRecord.o(), simpleDraweeView);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(ScanQRCodeDeviceActivity.this, ResetDevicePage.class);
                    intent.putExtra("model", pluginRecord.o());
                    intent.addFlags(536870912);
                    intent.addFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                    ScanQRCodeDeviceActivity.this.startActivity(intent);
                    ScanQRCodeDeviceActivity.this.finish();
                }
            });
            return view;
        }
    }
}
