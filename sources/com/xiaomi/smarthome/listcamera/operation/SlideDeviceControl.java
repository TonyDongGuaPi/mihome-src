package com.xiaomi.smarthome.listcamera.operation;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import com.libra.Color;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.listcamera.CameraDeviceOpManager;
import com.xiaomi.smarthome.listcamera.CameraInfoRefreshManager;
import com.xiaomi.smarthome.listcamera.adapter.ControlViewHolder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SlideDeviceControl extends DeviceControl {

    /* renamed from: a  reason: collision with root package name */
    HashMap<String, String> f19333a = new HashMap<>();
    Param e;
    Param f;
    String g;
    Object h;
    /* access modifiers changed from: private */
    public int i;
    /* access modifiers changed from: private */
    public int j;

    public SlideDeviceControl(JSONObject jSONObject) {
        this.c = jSONObject.optString("prop_name");
        JSONObject optJSONObject = jSONObject.optJSONObject("button_name");
        Iterator<String> keys = optJSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            this.f19333a.put(next, optJSONObject.optString(next));
        }
        if (jSONObject.has("depend_prop")) {
            this.g = jSONObject.optString("depend_prop");
            this.h = jSONObject.opt("depend_value");
        }
        String optString = jSONObject.optString("rpc_method");
        String optString2 = jSONObject.optString("rpc_param_type");
        this.f = new Param();
        try {
            if (!this.f.a(new JSONArray(optString2))) {
                this.f = null;
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        String optString3 = jSONObject.optString("prop_value_type");
        this.e = new Param();
        try {
            if (!this.e.a(new JSONArray(optString3))) {
                this.f = null;
            }
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        this.i = jSONObject.optInt("rpc_param_max");
        this.j = jSONObject.optInt("rpc_param_min");
        this.d = new OpRpc(optString, (List<Object>) null, this.f);
    }

    public void a(ControlViewHolder controlViewHolder, final MiioDeviceV2 miioDeviceV2, boolean z) {
        Object obj;
        Map<String, Object> a2 = CameraDeviceOpManager.a().a(miioDeviceV2.did);
        View e2 = controlViewHolder.e(R.layout.op_button);
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(DisplayUtils.a(76.0f), DisplayUtils.a(44.0f));
        if (!z) {
            marginLayoutParams.rightMargin = DisplayUtils.a(6.0f);
        }
        e2.setLayoutParams(marginLayoutParams);
        TextView textView = (TextView) e2.findViewById(R.id.button_title);
        TextView textView2 = (TextView) e2.findViewById(R.id.button_desc);
        textView.setCompoundDrawables((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        if (!(a2 == null || (obj = a2.get(this.c)) == null)) {
            textView2.setText(obj.toString());
        }
        textView.setText(a((Map<String, String>) this.f19333a));
        e2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SlideDeviceControl.this.a(view, miioDeviceV2);
            }
        });
        controlViewHolder.a(e2);
        if (CameraInfoRefreshManager.a().b() || !CameraInfoRefreshManager.a().c()) {
            e2.setEnabled(false);
        } else if (a2 == null || !a2.containsKey(this.c)) {
            e2.setEnabled(false);
        } else if (!TextUtils.isEmpty(this.g) && !CameraDeviceOpManager.a().a(miioDeviceV2, this.g, this.h)) {
            e2.setEnabled(false);
        } else if (!miioDeviceV2.isOnline) {
            e2.setEnabled(false);
        } else {
            e2.setEnabled(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(View view, final MiioDeviceV2 miioDeviceV2) {
        Map<String, Object> a2 = CameraDeviceOpManager.a().a(miioDeviceV2.did);
        View inflate = LayoutInflater.from(view.getContext()).inflate(R.layout.op_seek_bar, (ViewGroup) null);
        final SeekBar seekBar = (SeekBar) inflate.findViewById(R.id.seek_bar);
        inflate.findViewById(R.id.min_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        inflate.findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                int a2 = SlideDeviceControl.this.j + ((seekBar.getProgress() * (SlideDeviceControl.this.i - SlideDeviceControl.this.j)) / 100);
                if (a2 == 0) {
                    a2 = 1;
                }
                SlideDeviceControl.this.d.a(Integer.valueOf(a2), miioDeviceV2, new AsyncCallback<JSONObject, Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        Log.e("device_rpc", "rpc success");
                    }

                    public void onFailure(Error error) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("rpc failed - ");
                        sb.append(error != null ? error.b() : "");
                        Log.e("device_rpc", sb.toString());
                    }
                });
            }
        });
        seekBar.setProgress(((Integer.valueOf(a2.get(this.c).toString()).intValue() - this.j) * 100) / (this.i - this.j));
        new MLAlertDialog.Builder(view.getContext()).b(inflate).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
            }
        }).d();
    }

    private int a(int i2) {
        if (i2 < 16) {
            return (((i2 * 255) * 6) / 100) | -65536;
        }
        if (i2 < 33) {
            return (((2 - ((i2 * 6) / 100)) * 255) << 16) & Color.k;
        }
        if (i2 < 50) {
            return (((((i2 * 6) / 100) - 2) * 255) << 8) | Color.h;
        }
        if (i2 < 66) {
            return ((4 - ((i2 * 6) / 100)) * 255) & Color.j;
        }
        if (i2 < 83) {
            return (((((i2 * 6) / 100) - 4) * 255) << 16) | Color.g;
        }
        return (((6 - ((i2 * 6) / 100)) * 255) << 8) & -256;
    }
}
