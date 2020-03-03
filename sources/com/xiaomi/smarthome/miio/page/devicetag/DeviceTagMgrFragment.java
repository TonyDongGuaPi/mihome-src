package com.xiaomi.smarthome.miio.page.devicetag;

import android.graphics.drawable.NinePatchDrawable;
import android.support.v7.widget.RecyclerView;
import com.xiaomi.smarthome.R;

public class DeviceTagMgrFragment extends DeviceTagFragment {
    /* access modifiers changed from: protected */
    public DeviceTagAdapter a() {
        return new DeviceTagMgrAdapter(getActivity(), getTag());
    }

    /* access modifiers changed from: protected */
    public RecyclerView.Adapter d() {
        return this.k.a(this.l);
    }

    /* access modifiers changed from: protected */
    public void e() {
        this.k.a(this.g);
        this.k.a(true);
        this.k.a((NinePatchDrawable) getContext().getResources().getDrawable(R.drawable.std_list_item_drag_shadow));
        this.g.setBackgroundColor(getResources().getColor(R.color.list_bg));
    }

    public void onPause() {
        super.onPause();
        if (this.h instanceof DeviceTagMgrAdapter) {
            ((DeviceTagMgrAdapter) this.h).h();
        }
    }
}
