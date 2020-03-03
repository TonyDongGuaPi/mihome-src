package com.xiaomi.smarthome.listcamera.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.listcamera.adapter.CameraLargeAdapter;

public class ControlViewHolder extends CameraLargeAdapter.ChildViewHolder {
    public View b;
    public TextView c;
    public TextView d;
    private LayoutInflater e;
    private LinearLayout f;

    public ControlViewHolder(View view) {
        super(view, CameraLargeAdapter.ViewType.CHILD_OPERATION);
        this.e = LayoutInflater.from(view.getContext());
        this.f = (LinearLayout) view.findViewById(R.id.deivce_control_container);
        this.b = view.findViewById(R.id.device_info_container);
        this.c = (TextView) view.findViewById(R.id.button_title);
        this.d = (TextView) view.findViewById(R.id.button_desc);
    }

    public View e(int i) {
        if (this.e != null) {
            return this.e.inflate(i, (ViewGroup) null);
        }
        return null;
    }

    public void l() {
        if (this.f != null) {
            this.f.removeViews(1, this.f.getChildCount() - 1);
        }
    }

    public void a(View view) {
        if (view != null) {
            this.f.addView(view);
        }
    }
}
