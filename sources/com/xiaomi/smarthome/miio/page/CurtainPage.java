package com.xiaomi.smarthome.miio.page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.miio.device.CurtainDevice;

public class CurtainPage extends MiioPageV2 {

    /* renamed from: a  reason: collision with root package name */
    ImageView f19526a;
    ImageView b;
    ImageView c;
    CurtainDevice d;

    public void a() {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.miio_page_curtain, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.d = (CurtainDevice) this.f;
        this.f19526a = (ImageView) view.findViewById(R.id.remote_control_curation_pause);
        this.f19526a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CurtainPage.this.d.a();
            }
        });
        this.b = (ImageView) view.findViewById(R.id.remote_control_curation_open);
        this.b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CurtainPage.this.d.b();
            }
        });
        this.c = (ImageView) view.findViewById(R.id.remote_control_curation_close);
        this.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CurtainPage.this.d.d();
            }
        });
        a();
    }
}
