package com.xiaomi.smarthome.miio.page.devicetag;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.core.CoreApi;
import org.json.JSONException;
import org.json.JSONObject;

public class AllGroupViewHolder extends DeviceTagGroupViewHolder {

    /* renamed from: a  reason: collision with root package name */
    private TextView f19792a;

    public AllGroupViewHolder(View view) {
        super(view);
        this.f19792a = (TextView) view.findViewById(R.id.title);
    }

    public void a(final DeviceTagAdapter deviceTagAdapter, final DeviceTagGroup deviceTagGroup, int i) {
        this.f19792a.setText(deviceTagGroup.u);
        this.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("device_tag_selected_action");
                intent.putExtra(DeviceTagInterface.B, deviceTagGroup.t);
                intent.putExtra("tag_selected_param", true);
                LocalBroadcastManager.getInstance(deviceTagAdapter.f()).sendBroadcast(intent);
                if (deviceTagAdapter instanceof DeviceTagDrawerAdapter) {
                    ((DeviceTagDrawerAdapter) deviceTagAdapter).a((String) null);
                }
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("type", deviceTagGroup.t);
                    jSONObject.put("tag", SHApplication.getAppContext().getString(R.string.tag_all_devices));
                    CoreApi.a().a(StatType.EVENT, "device_tag_selected", jSONObject.toString(), (String) null, false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
