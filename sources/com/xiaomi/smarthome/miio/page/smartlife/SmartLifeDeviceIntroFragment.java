package com.xiaomi.smarthome.miio.page.smartlife;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.shop.utils.ShopLauncher;
import java.util.Map;

public class SmartLifeDeviceIntroFragment extends BaseFragment {

    /* renamed from: a  reason: collision with root package name */
    public static final int f19918a = 1;
    public static final int b = 2;
    private static final int o = 1;
    private XQProgressDialog c;
    private SmartLifeItem d;
    /* access modifiers changed from: private */
    public int e;
    private Map<String, String> f;
    private ImageView g;
    private SimpleDraweeView h;
    private ImageView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private View n;
    private Handler p = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                SmartLifeDeviceIntroFragment.this.b();
            }
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.d = (SmartLifeItem) arguments.getParcelable("data");
            this.e = arguments.getInt("type");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.n == null) {
            this.n = layoutInflater.inflate(R.layout.smart_life_device_intro, (ViewGroup) null);
            a();
        } else if (this.n.getParent() != null) {
            ((ViewGroup) this.n.getParent()).removeView(this.n);
        }
        return this.n;
    }

    public void a(Map<String, String> map) {
        this.f = map;
        if (this.f != null) {
            this.p.sendEmptyMessage(1);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.p.removeCallbacksAndMessages((Object) null);
    }

    private void a() {
        this.g = (ImageView) this.n.findViewById(R.id.loading);
        this.h = (SimpleDraweeView) this.n.findViewById(R.id.bg_iv);
        this.i = (ImageView) this.n.findViewById(R.id.control_device_iv);
        this.j = (TextView) this.n.findViewById(R.id.control_device_name);
        this.k = (TextView) this.n.findViewById(R.id.control_device_desc);
        this.l = (TextView) this.n.findViewById(R.id.timer_ctrl_tv);
        this.m = (TextView) this.n.findViewById(R.id.remote_ctrl_tv);
        if (this.e == 2) {
            this.i.setImageResource(R.drawable.std_lifeline_icon_socket);
            this.j.setText(R.string.smart_life_intro_device_socket);
        } else if (this.e == 1) {
            this.i.setImageResource(R.drawable.smart_life_icon_infrared);
            this.j.setText(R.string.smart_life_intro_device_ultrared);
        }
        this.k.setText(SmartLifeItem.a(getResources(), this.d.f19933a));
        this.h.setImageURI(Uri.parse(SmartLifeItem.c(this.d.f19933a)));
        int[] d2 = SmartLifeItem.d(this.d.f19933a);
        if (d2 != null) {
            if (d2.length == 1) {
                if (d2[0] == R.string.smart_life_intro_tip1) {
                    this.l.setVisibility(0);
                    this.m.setVisibility(8);
                } else if (d2[0] == R.string.smart_life_intro_tip2) {
                    this.l.setVisibility(8);
                    this.m.setVisibility(0);
                }
            } else if (d2.length == 2) {
                this.l.setVisibility(0);
                this.m.setVisibility(0);
            }
        }
        this.n.findViewById(R.id.btn_change).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SmartLifeDeviceIntroFragment.this.e == 2) {
                    ShopLauncher.a(SmartLifeDeviceIntroFragment.this.getActivity(), Uri.parse("https://youpin.mi.com/detail?gid=133&source=" + SHApplication.getAppContext().getPackageName()).toString(), false);
                } else if (SmartLifeDeviceIntroFragment.this.e == 1) {
                    ShopLauncher.a(SmartLifeDeviceIntroFragment.this.getActivity(), Uri.parse("https://youpin.mi.com/detail?gid=103&pid=689&source=" + SHApplication.getAppContext().getPackageName()).toString(), false);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void b() {
        Map<String, String> map = this.f;
        if (map != null && this.d != null) {
            String str = map.get(this.d.f19933a + "");
            if (!TextUtils.isEmpty(str)) {
                this.h.setImageURI(Uri.parse(str));
            }
        }
    }

    public void onResume() {
        super.onResume();
    }
}
