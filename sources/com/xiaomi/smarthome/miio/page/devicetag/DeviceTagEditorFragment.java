package com.xiaomi.smarthome.miio.page.devicetag;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import com.xiaomi.smarthome.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DeviceTagEditorFragment extends DeviceTagFragment {

    /* renamed from: a  reason: collision with root package name */
    public static final String f19812a = "hide_soft_input_action";

    public void a(String str) {
        if (this.h != null && (this.h instanceof DeviceTagEditorAdapter)) {
            ((DeviceTagEditorAdapter) this.h).a(str);
        }
    }

    /* access modifiers changed from: protected */
    public DeviceTagAdapter a() {
        if (this.g != null) {
            this.g.setBackgroundColor(getResources().getColor(R.color.list_bg));
        }
        return new DeviceTagEditorAdapter(getActivity(), getTag());
    }

    public Set<String> c() {
        if (this.h == null || !(this.h instanceof DeviceTagEditorAdapter)) {
            return new HashSet();
        }
        return ((DeviceTagEditorAdapter) this.h).h();
    }

    public void a(RecyclerView recyclerView, int i) {
        super.a(recyclerView, i);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(new Intent(f19812a));
    }

    public void a(ArrayList<String> arrayList) {
        if (this.h != null && (this.h instanceof DeviceTagEditorAdapter)) {
            ((DeviceTagEditorAdapter) this.h).a(arrayList);
        }
    }
}
