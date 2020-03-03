package com.xiaomi.smarthome.miio.page.devicetag;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.TextView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.listcamera.AllCameraActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class SwitchChildViewHolder extends DeviceTagChildViewHolder {

    /* renamed from: a  reason: collision with root package name */
    public View f19841a;
    /* access modifiers changed from: private */
    public TextView c;
    private TextView d;
    /* access modifiers changed from: private */
    public View e;

    public SwitchChildViewHolder(View view) {
        super(view);
        this.c = (TextView) view.findViewById(R.id.title);
        this.d = (TextView) view.findViewById(R.id.count);
        this.e = view.findViewById(R.id.new_tag);
        this.f19841a = view.findViewById(R.id.divider_item);
    }

    public void a(final DeviceTagAdapter deviceTagAdapter, final DeviceTagGroup deviceTagGroup, int i, int i2) {
        if (deviceTagGroup.w != null && i2 < deviceTagGroup.w.size()) {
            final DeviceTagChild deviceTagChild = deviceTagGroup.w.get(i2);
            int i3 = 0;
            this.f19841a.setVisibility(i2 == deviceTagGroup.w.size() + -1 ? 8 : 0);
            if (deviceTagChild != null) {
                this.c.setText(deviceTagChild.d);
                String str = "";
                if (deviceTagChild.g != null && !deviceTagChild.g.isEmpty()) {
                    str = Operators.BRACKET_START_STR + deviceTagChild.g.size() + Operators.BRACKET_END_STR;
                }
                this.d.setText(str);
                if (this.e != null) {
                    View view = this.e;
                    if (!deviceTagChild.j) {
                        i3 = 4;
                    }
                    view.setVisibility(i3);
                }
                this.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (deviceTagChild.j) {
                            deviceTagChild.j = false;
                            SwitchChildViewHolder.this.e.setVisibility(4);
                            SmartHomeDeviceHelper.a().b().t();
                        }
                        if (deviceTagGroup.t != 0 || !deviceTagChild.d.equals(SHApplication.getAppContext().getString(R.string.permission_name_camera))) {
                            Intent intent = new Intent("device_tag_selected_action");
                            intent.putExtra(DeviceTagInterface.B, deviceTagGroup.t);
                            intent.putExtra("tag_selected_param", true);
                            if (deviceTagGroup.t != 2 || StringUtil.c(deviceTagChild.e)) {
                                intent.putExtra("device_tag_param", deviceTagChild.d);
                            } else {
                                intent.putExtra("device_tag_param", SmartHomeDeviceHelper.a().b().b(deviceTagChild.e, SmartHomeDeviceHelper.a().b().b(deviceTagChild.e)));
                                intent.putExtra("router_bssid_param", deviceTagChild.e);
                            }
                            if (!(deviceTagGroup.t == -1 || deviceTagChild.g == null || deviceTagChild.g.size() <= 0)) {
                                intent.putExtra(DeviceTagInterface.C, deviceTagChild.g.size());
                            }
                            LocalBroadcastManager.getInstance(deviceTagAdapter.f()).sendBroadcast(intent);
                            if (deviceTagAdapter instanceof DeviceTagDrawerAdapter) {
                                ((DeviceTagDrawerAdapter) deviceTagAdapter).a((String) null);
                            }
                            try {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("type", deviceTagGroup.t);
                                jSONObject.put("tag", deviceTagChild.d);
                                CoreApi.a().a(StatType.EVENT, "device_tag_selected", jSONObject.toString(), (String) null, false);
                                if (deviceTagGroup.t == 0) {
                                    StatHelper.e(deviceTagChild.d);
                                } else if (deviceTagGroup.t == 2) {
                                    StatHelper.f(deviceTagChild.d);
                                } else if (deviceTagGroup.t == 4) {
                                    StatHelper.g(deviceTagChild.d);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            SwitchChildViewHolder.this.c.getContext().startActivity(new Intent(SwitchChildViewHolder.this.c.getContext(), AllCameraActivity.class));
                            if (deviceTagAdapter instanceof DeviceTagDrawerAdapter) {
                                ((DeviceTagDrawerAdapter) deviceTagAdapter).a((String) null);
                            }
                        }
                    }
                });
            }
        }
    }
}
