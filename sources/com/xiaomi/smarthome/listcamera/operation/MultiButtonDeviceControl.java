package com.xiaomi.smarthome.listcamera.operation;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.listcamera.AllCameraPage;
import com.xiaomi.smarthome.listcamera.CameraDeviceOpManager;
import com.xiaomi.smarthome.listcamera.adapter.ControlViewHolder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MultiButtonDeviceControl extends DeviceControl {

    /* renamed from: a  reason: collision with root package name */
    public Param f19318a;
    List<RpcButton> e = new ArrayList();

    static class RpcButton {

        /* renamed from: a  reason: collision with root package name */
        HashMap<String, String> f19323a;
        List<Object> b;
        OpRpc c;

        RpcButton() {
        }
    }

    public MultiButtonDeviceControl(JSONObject jSONObject) {
        this.c = jSONObject.optString("prop_name");
        this.f19318a = new Param();
        try {
            if (!this.f19318a.a(new JSONArray(jSONObject.optString("prop_value_type")))) {
                this.f19318a = null;
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            this.f19318a = null;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("state");
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            RpcButton rpcButton = new RpcButton();
            rpcButton.f19323a = new HashMap<>();
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("button_name");
            Iterator<String> keys = optJSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                rpcButton.f19323a.put(next, optJSONObject2.optString(next));
            }
            ArrayList arrayList = new ArrayList();
            if (optJSONObject.has("rpc_params")) {
                JSONArray optJSONArray2 = optJSONObject.optJSONArray("rpc_params");
                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                    arrayList.add(optJSONArray2.opt(i2));
                }
            }
            rpcButton.b = new ArrayList();
            JSONArray optJSONArray3 = optJSONObject.optJSONArray("enable_status");
            for (int i3 = 0; i3 < optJSONArray3.length(); i3++) {
                rpcButton.b.add(optJSONArray3.opt(i3));
            }
            rpcButton.c = new OpRpc(optJSONObject.optString("rpc_method"), arrayList, (Param) null);
            this.e.add(rpcButton);
        }
    }

    public void a(ControlViewHolder controlViewHolder, final MiioDeviceV2 miioDeviceV2, boolean z) {
        Map<String, Object> a2 = CameraDeviceOpManager.a().a(miioDeviceV2.did);
        for (int i = 0; i < this.e.size(); i++) {
            final RpcButton rpcButton = this.e.get(i);
            View e2 = controlViewHolder.e(R.layout.op_button);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DisplayUtils.a(76.0f), DisplayUtils.a(44.0f));
            if (!z || i != this.e.size() - 1) {
                layoutParams.rightMargin = DisplayUtils.a(6.0f);
            }
            e2.setLayoutParams(layoutParams);
            TextView textView = (TextView) e2.findViewById(R.id.button_title);
            textView.setCompoundDrawables((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
            ((TextView) e2.findViewById(R.id.button_desc)).setVisibility(8);
            textView.setText(a((Map<String, String>) rpcButton.f19323a));
            e2.setOnClickListener(new View.OnClickListener() {
                public void onClick(final View view) {
                    view.setEnabled(false);
                    rpcButton.c.a((Object) null, miioDeviceV2, new AsyncCallback<JSONObject, Error>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            Log.e("device_rpc", "rpc success");
                            view.setEnabled(true);
                            if (view.getHandler() != null) {
                                view.getHandler().postDelayed(new Runnable() {
                                    public void run() {
                                        ArrayList arrayList = new ArrayList();
                                        arrayList.add(miioDeviceV2);
                                        CameraDeviceOpManager.a().a((List<Device>) arrayList, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                                            public void onFailure(Error error) {
                                            }

                                            /* renamed from: a */
                                            public void onSuccess(Void voidR) {
                                                Log.e("device_rpc", "update props success");
                                                LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(AllCameraPage.c));
                                            }
                                        });
                                    }
                                }, 1000);
                            }
                        }

                        public void onFailure(Error error) {
                            Log.e("device_rpc", "rpc failed");
                            view.setEnabled(true);
                        }
                    });
                }
            });
            if (a2 == null || !a2.containsKey(this.c)) {
                e2.setEnabled(false);
            } else {
                this.f19318a.a(a2.get(this.c));
                boolean z2 = false;
                for (Object c : rpcButton.b) {
                    if (this.f19318a.c(c)) {
                        z2 = true;
                    }
                }
                if (z2) {
                    e2.setEnabled(true);
                } else {
                    e2.setEnabled(false);
                }
            }
            controlViewHolder.a(e2);
        }
    }
}
