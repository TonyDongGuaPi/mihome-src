package com.xiaomi.smarthome.listcamera.operation;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.listcamera.CameraDeviceOpManager;
import com.xiaomi.smarthome.listcamera.adapter.ControlViewHolder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ToggleDeviceControl extends DeviceControl {

    /* renamed from: a  reason: collision with root package name */
    List<OpStatus> f19341a = new ArrayList();
    Param e;
    OpStatus f = null;
    String g;
    Object h;

    public ToggleDeviceControl(JSONObject jSONObject) {
        this.c = jSONObject.optString("prop_name");
        String optString = jSONObject.optString("prop_value_type");
        Object opt = jSONObject.opt("green_prop_value");
        this.e = new Param();
        try {
            if (!this.e.a(new JSONArray(optString))) {
                this.e = null;
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            this.e = null;
        }
        if (jSONObject.has("depend_prop")) {
            this.g = jSONObject.optString("depend_prop");
            this.h = jSONObject.opt("depend_value");
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("state");
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            OpStatus opStatus = new OpStatus();
            opStatus.f19330a = optJSONObject.opt("prop_value");
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("button_name");
            Iterator<String> keys = optJSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                opStatus.c.put(next, optJSONObject2.optString(next));
            }
            if (opStatus.f19330a.equals(opt)) {
                this.f = opStatus;
            }
            JSONObject optJSONObject3 = optJSONObject.optJSONObject("desc");
            Iterator<String> keys2 = optJSONObject3.keys();
            while (keys2.hasNext()) {
                String next2 = keys2.next();
                opStatus.d.put(next2, optJSONObject3.optString(next2));
            }
            ArrayList arrayList = new ArrayList();
            if (optJSONObject.has("rpc_params")) {
                JSONArray optJSONArray2 = optJSONObject.optJSONArray("rpc_params");
                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                    arrayList.add(optJSONArray2.opt(i2));
                }
            }
            opStatus.b = new OpRpc(optJSONObject.optString("rpc_method"), arrayList, (Param) null);
            this.f19341a.add(opStatus);
        }
    }

    public boolean a(Object obj, Object obj2) {
        this.e.a(obj);
        return this.e.c(obj2);
    }

    public void a(ControlViewHolder controlViewHolder, final MiioDeviceV2 miioDeviceV2, boolean z) {
        Map<String, Object> a2 = CameraDeviceOpManager.a().a(miioDeviceV2.did);
        View e2 = controlViewHolder.e(R.layout.op_toggle);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DisplayUtils.a(76.0f), DisplayUtils.a(44.0f));
        if (!z) {
            layoutParams.rightMargin = DisplayUtils.a(6.0f);
        }
        e2.setLayoutParams(layoutParams);
        final TextView textView = (TextView) e2.findViewById(R.id.button_title);
        final TextView textView2 = (TextView) e2.findViewById(R.id.button_desc);
        OpStatus opStatus = null;
        if (a2 != null && a2.containsKey(this.c)) {
            this.e.a(a2.get(this.c));
            Iterator<OpStatus> it = this.f19341a.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                OpStatus next = it.next();
                if (this.e.c(next.f19330a)) {
                    opStatus = next;
                    break;
                }
            }
        }
        if (opStatus == null) {
            e2.setEnabled(false);
            textView.setText(a((Map<String, String>) this.f.c));
            textView2.setText(a((Map<String, String>) this.f.d));
            e2.setBackgroundResource(R.drawable.default_button_guide);
        } else {
            e2.setEnabled(true);
            textView.setText(a((Map<String, String>) opStatus.c));
            textView2.setText(a((Map<String, String>) opStatus.d));
            if (opStatus.equals(this.f)) {
                e2.setBackgroundResource(R.drawable.default_button_guide);
            } else {
                e2.setBackgroundResource(R.drawable.default_button_warning);
            }
        }
        e2.setTag(opStatus);
        e2.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                view.setEnabled(false);
                final OpStatus opStatus = (OpStatus) view.getTag();
                if (opStatus != null) {
                    opStatus.b.a((Object) null, miioDeviceV2, new AsyncCallback<JSONObject, Error>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            Log.e("device_rpc", "rpc success");
                            Iterator<OpStatus> it = ToggleDeviceControl.this.f19341a.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                OpStatus next = it.next();
                                if (!next.equals(opStatus)) {
                                    view.setTag(next);
                                    CameraDeviceOpManager.a().a(miioDeviceV2.did, ToggleDeviceControl.this.c, next.f19330a);
                                    break;
                                }
                            }
                            view.postDelayed(new Runnable() {
                                public void run() {
                                    view.setEnabled(true);
                                }
                            }, 1000);
                        }

                        public void onFailure(Error error) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("rpc failed - ");
                            sb.append(error != null ? error.b() : "");
                            Log.e("device_rpc", sb.toString());
                            Iterator<OpStatus> it = ToggleDeviceControl.this.f19341a.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                OpStatus next = it.next();
                                if (next.equals(opStatus)) {
                                    view.setTag(next);
                                    textView.setText(DeviceControl.a((Map<String, String>) next.c));
                                    textView2.setText(DeviceControl.a((Map<String, String>) next.d));
                                    if (next.equals(ToggleDeviceControl.this.f)) {
                                        view.setBackgroundResource(R.drawable.default_button_guide);
                                    } else {
                                        view.setBackgroundResource(R.drawable.default_button_warning);
                                    }
                                }
                            }
                            view.setEnabled(true);
                        }
                    });
                    for (OpStatus next : ToggleDeviceControl.this.f19341a) {
                        if (!next.equals(opStatus)) {
                            textView.setText(DeviceControl.a((Map<String, String>) next.c));
                            textView2.setText(DeviceControl.a((Map<String, String>) next.d));
                            if (next.equals(ToggleDeviceControl.this.f)) {
                                view.setBackgroundResource(R.drawable.default_button_guide);
                                return;
                            } else {
                                view.setBackgroundResource(R.drawable.default_button_warning);
                                return;
                            }
                        }
                    }
                }
            }
        });
        controlViewHolder.a(e2);
        if (a2 == null) {
            e2.setEnabled(false);
        } else if (!TextUtils.isEmpty(this.g) && !CameraDeviceOpManager.a().a(miioDeviceV2, this.g, this.h)) {
            e2.setEnabled(false);
        } else if (!miioDeviceV2.isOnline) {
            e2.setEnabled(false);
        } else {
            e2.setEnabled(true);
        }
    }
}
