package com.xiaomi.smarthome.listcamera.operation;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.listcamera.adapter.ControlViewHolder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class ClickDeviceControl extends DeviceControl {

    /* renamed from: a  reason: collision with root package name */
    HashMap<String, String> f19315a = new HashMap<>();

    public ClickDeviceControl(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("button_name");
        Iterator<String> keys = optJSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            this.f19315a.put(next, optJSONObject.optString(next));
        }
        ArrayList arrayList = new ArrayList();
        if (jSONObject.has("rpc_params")) {
            JSONArray optJSONArray = jSONObject.optJSONArray("rpc_params");
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(optJSONArray.opt(i));
            }
        }
        this.d = new OpRpc(jSONObject.optString("rpc_method"), arrayList, (Param) null);
    }

    public void a(ControlViewHolder controlViewHolder, final MiioDeviceV2 miioDeviceV2, boolean z) {
        View e = controlViewHolder.e(R.layout.op_button);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DisplayUtils.a(76.0f), DisplayUtils.a(44.0f));
        if (!z) {
            layoutParams.rightMargin = DisplayUtils.a(6.0f);
        }
        e.setLayoutParams(layoutParams);
        TextView textView = (TextView) e.findViewById(R.id.button_title);
        textView.setCompoundDrawables((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        ((TextView) e.findViewById(R.id.button_desc)).setVisibility(8);
        textView.setText(a((Map<String, String>) this.f19315a));
        e.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                view.setEnabled(false);
                ClickDeviceControl.this.d.a((Object) null, miioDeviceV2, new AsyncCallback<JSONObject, Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        Log.e("device_rpc", "rpc success");
                        view.setEnabled(true);
                    }

                    public void onFailure(Error error) {
                        Log.e("device_rpc", "rpc failed");
                        view.setEnabled(true);
                    }
                });
            }
        });
        controlViewHolder.a(e);
        if (miioDeviceV2 == null || !miioDeviceV2.isOnline) {
            e.setEnabled(false);
        } else {
            e.setEnabled(true);
        }
    }
}
