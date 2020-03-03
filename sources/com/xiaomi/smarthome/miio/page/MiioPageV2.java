package com.xiaomi.smarthome.miio.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.framework.page.DeviceMoreActivity;
import com.xiaomi.smarthome.library.common.util.SmartHomeTitleMoreMenuHelper;

public abstract class MiioPageV2 extends BaseFragment implements Device.StateChangedListener {
    public static final String e = "param_device_did";
    /* access modifiers changed from: protected */
    public MiioDeviceV2 f;
    TextView g;
    ImageView h;
    protected SmartHomeTitleMoreMenuHelper i = new SmartHomeTitleMoreMenuHelper() {
        /* renamed from: o */
        public MiioDeviceV2 c() {
            return MiioPageV2.this.f;
        }

        public TextView a() {
            return MiioPageV2.this.g;
        }

        public ImageView g() {
            return MiioPageV2.this.h;
        }

        public Context b() {
            return MiioPageV2.this.getActivity();
        }

        public void b(int i) {
            if (i == R.string.smarthome_device_auth_release) {
                MiioPageV2.this.e();
            }
        }
    };

    private void b() {
    }

    public abstract void a();

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        View findViewById = view.findViewById(R.id.module_a_3_return_btn);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MiioPageV2.this.e();
                }
            });
        }
        View findViewById2 = view.findViewById(R.id.module_a_3_return_more_more_btn);
        if (findViewById2 != null) {
            if (this.f != null && !this.f.isOwner()) {
                findViewById2.setVisibility(4);
            }
            findViewById2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MiioPageV2.this.d();
                }
            });
        }
        this.g = (TextView) view.findViewById(R.id.module_a_3_return_title);
        this.h = (ImageView) view.findViewById(R.id.module_a_3_return_more_new_btn);
        b();
    }

    /* access modifiers changed from: package-private */
    public void d() {
        Intent intent = new Intent(getActivity(), DeviceMoreActivity.class);
        intent.putExtra("did", this.f.did);
        getActivity().startActivityForResult(intent, 1);
        getActivity().overridePendingTransition(0, 0);
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        if (i2 == 1 && i3 == -1 && intent != null && intent.getIntExtra("result_data", 0) == 1) {
            getActivity().finish();
        }
    }

    public void a(MiioDeviceV2 miioDeviceV2) {
        if (this.f != null) {
            this.f.removeStateChangedListener(this);
        }
        this.f = miioDeviceV2;
        if (this.f != null) {
            this.f.addStateChangedListener(this);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onResume() {
        super.onResume();
        if (this.g != null) {
            this.g.setText(this.f.name);
        }
        this.i.m();
        this.i.l();
    }

    public void onPause() {
        super.onPause();
        this.i.n();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.f != null) {
            this.f.removeStateChangedListener(this);
        }
    }

    public void onStateChanged(Device device) {
        a();
    }

    public void e() {
        getActivity().finish();
    }
}
