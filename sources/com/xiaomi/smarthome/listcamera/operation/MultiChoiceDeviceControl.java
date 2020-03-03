package com.xiaomi.smarthome.listcamera.operation;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
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

public class MultiChoiceDeviceControl extends DeviceControl {

    /* renamed from: a  reason: collision with root package name */
    HashMap<String, String> f19324a = new HashMap<>();
    Param e;
    List<OpStatus> f = new ArrayList();
    String g;
    Object h;

    public MultiChoiceDeviceControl(JSONObject jSONObject) {
        this.c = jSONObject.optString("prop_name");
        JSONArray optJSONArray = jSONObject.optJSONArray("state");
        JSONObject optJSONObject = jSONObject.optJSONObject("button_name");
        Iterator<String> keys = optJSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            this.f19324a.put(next, optJSONObject.optString(next));
        }
        String optString = jSONObject.optString("prop_value_type");
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
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
            OpStatus opStatus = new OpStatus();
            opStatus.f19330a = optJSONObject2.opt("prop_value");
            JSONObject optJSONObject3 = optJSONObject2.optJSONObject("desc");
            Iterator<String> keys2 = optJSONObject3.keys();
            while (keys2.hasNext()) {
                String next2 = keys2.next();
                opStatus.d.put(next2, optJSONObject3.optString(next2));
            }
            ArrayList arrayList = new ArrayList();
            if (optJSONObject2.has("rpc_params")) {
                JSONArray optJSONArray2 = optJSONObject2.optJSONArray("rpc_params");
                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                    arrayList.add(optJSONArray2.opt(i2));
                }
            }
            opStatus.b = new OpRpc(optJSONObject2.optString("rpc_method"), arrayList, (Param) null);
            this.f.add(opStatus);
        }
    }

    public void a(ControlViewHolder controlViewHolder, final MiioDeviceV2 miioDeviceV2, boolean z) {
        Map<String, Object> a2 = CameraDeviceOpManager.a().a(miioDeviceV2.did);
        View e2 = controlViewHolder.e(R.layout.op_button);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DisplayUtils.a(76.0f), DisplayUtils.a(44.0f));
        if (!z) {
            layoutParams.rightMargin = DisplayUtils.a(6.0f);
        }
        e2.setLayoutParams(layoutParams);
        TextView textView = (TextView) e2.findViewById(R.id.button_title);
        TextView textView2 = (TextView) e2.findViewById(R.id.button_desc);
        OpStatus opStatus = null;
        if (a2 != null && a2.containsKey(this.c)) {
            this.e.a(a2.get(this.c));
            Iterator<OpStatus> it = this.f.iterator();
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
        e2.setBackgroundResource(R.drawable.default_button_assist);
        textView.setText(a((Map<String, String>) this.f19324a));
        if (opStatus != null) {
            textView2.setText(a((Map<String, String>) opStatus.d));
            e2.setTag(opStatus);
        }
        e2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MultiChoiceDeviceControl.this.a(view.getContext(), view, miioDeviceV2);
            }
        });
        controlViewHolder.a(e2);
        if (a2 == null || !a2.containsKey(this.c)) {
            e2.setEnabled(false);
        } else if (!TextUtils.isEmpty(this.g) && !CameraDeviceOpManager.a().a(miioDeviceV2, this.g, this.h)) {
            e2.setEnabled(false);
        } else if (!miioDeviceV2.isOnline) {
            e2.setEnabled(false);
        } else {
            e2.setEnabled(true);
        }
    }

    /* access modifiers changed from: private */
    public void a(Context context, final View view, final MiioDeviceV2 miioDeviceV2) {
        OpStatus opStatus = (OpStatus) view.getTag();
        if (opStatus != null) {
            String[] strArr = new String[this.f.size()];
            int i = -1;
            for (int i2 = 0; i2 < strArr.length; i2++) {
                strArr[i2] = a((Map<String, String>) this.f.get(i2).d);
                if (opStatus.equals(this.f.get(i2))) {
                    i = i2;
                }
            }
            new MLAlertDialog.Builder(context).a((CharSequence) a((Map<String, String>) this.f19324a)).a((CharSequence[]) strArr, i, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    view.setEnabled(false);
                    final OpStatus opStatus = MultiChoiceDeviceControl.this.f.get(i);
                    opStatus.b.a((Object) null, miioDeviceV2, new AsyncCallback<JSONObject, Error>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            view.setTag(opStatus);
                            CameraDeviceOpManager.a().a(miioDeviceV2.did, MultiChoiceDeviceControl.this.c, opStatus.f19330a);
                            ((TextView) view.findViewById(R.id.button_title)).setText(DeviceControl.a((Map<String, String>) MultiChoiceDeviceControl.this.f19324a));
                            ((TextView) view.findViewById(R.id.button_desc)).setText(DeviceControl.a((Map<String, String>) opStatus.d));
                            view.setEnabled(true);
                        }

                        public void onFailure(Error error) {
                            view.setEnabled(true);
                        }
                    });
                    dialogInterface.dismiss();
                }
            }).a((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).b().show();
        }
    }
}
