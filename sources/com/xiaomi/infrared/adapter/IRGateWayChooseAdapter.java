package com.xiaomi.infrared.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.library.common.widget.SettingsItemView;
import java.util.List;

public class IRGateWayChooseAdapter extends BaseListAdapter<DeviceStat> {
    private static final int c = 16;
    private static final int d = 30;
    private static final String e = "IRGateWayChooseAdapter";
    /* access modifiers changed from: private */
    public AdapterOnClickListener f;

    public interface AdapterOnClickListener {
        void onClick(View view, DeviceStat deviceStat);
    }

    public int a(int i) {
        return R.layout.item_gateway_choose;
    }

    public IRGateWayChooseAdapter(Context context, List<DeviceStat> list, AdapterOnClickListener adapterOnClickListener) {
        super(context, list);
        this.f = adapterOnClickListener;
    }

    public void a(ViewHolder viewHolder, DeviceStat deviceStat, final int i) {
        SettingsItemView settingsItemView = (SettingsItemView) viewHolder.a((int) R.id.ir_device_item);
        if (!deviceStat.isOnline) {
            settingsItemView.setTitle(deviceStat.name + this.f10217a.getResources().getString(R.string.ir_device_offline));
            settingsItemView.setEnabled(false);
            settingsItemView.setAlpha(0.8f);
        } else {
            settingsItemView.setTitle(deviceStat.name);
        }
        Log.d(e, "convert: share : " + deviceStat.ownerName + " name " + deviceStat.name);
        if (!a(deviceStat).booleanValue()) {
            settingsItemView.setTitle(deviceStat.name + this.f10217a.getResources().getString(R.string.ir_device_share));
            settingsItemView.setEnabled(false);
            settingsItemView.setAlpha(0.8f);
        }
        settingsItemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                IRGateWayChooseAdapter.this.f.onClick(view, (DeviceStat) IRGateWayChooseAdapter.this.getItem(i));
            }
        });
    }

    public Boolean a(DeviceStat deviceStat) {
        return Boolean.valueOf(((deviceStat.permitLevel & 30) & 16) != 0);
    }
}
