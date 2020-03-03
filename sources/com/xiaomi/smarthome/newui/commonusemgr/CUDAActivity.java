package com.xiaomi.smarthome.newui.commonusemgr;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.CommonUseDeviceDataManager;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.HomeVirtualDeviceHelper;
import com.xiaomi.smarthome.library.common.widget.CoordinatorTabLayout;
import com.xiaomi.smarthome.library.common.widget.ViewPagerSystem;
import com.xiaomi.smarthome.newui.AddToCommonDialogHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CUDAActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private final int[] f20638a = {R.string.router_name, R.string.tag_category_title, R.string.room_name};
    /* access modifiers changed from: private */
    public List<Fragment> b = new ArrayList();
    private ViewPagerSystem c;
    private CoordinatorTabLayout d;
    /* access modifiers changed from: private */
    public View e;
    /* access modifiers changed from: private */
    public View f;
    /* access modifiers changed from: private */
    public Map<String, Boolean> g = new HashMap();
    /* access modifiers changed from: private */
    public Map<String, Long> h = new HashMap();
    private IDeviceSelectCallback i = new IDeviceSelectCallback() {
        public void a(Fragment fragment, List<Device> list, boolean z) {
            if (list != null) {
                int i = 0;
                if (list.size() == 1) {
                    while (i < list.size()) {
                        Device device = list.get(i);
                        CUDAActivity.this.g.put(device.did, Boolean.valueOf(z));
                        CUDAActivity.this.h.put(device.did, Long.valueOf(z ? System.currentTimeMillis() : -1));
                        i++;
                    }
                } else {
                    while (i < list.size()) {
                        Device device2 = list.get(i);
                        Boolean bool = (Boolean) CUDAActivity.this.g.get(device2.did);
                        Long l = (Long) CUDAActivity.this.h.get(device2.did);
                        long currentTimeMillis = z ? System.currentTimeMillis() : -1;
                        if (!bool.booleanValue() || !z || l == null) {
                            CUDAActivity.this.h.put(device2.did, Long.valueOf(currentTimeMillis));
                        }
                        CUDAActivity.this.g.put(device2.did, Boolean.valueOf(z));
                        i++;
                    }
                }
                for (Fragment fragment2 : CUDAActivity.this.b) {
                    if (fragment2 instanceof BaseCUDFragment) {
                        ((BaseCUDFragment) fragment2).a((Map<String, Boolean>) CUDAActivity.this.g);
                    }
                }
            }
        }
    };

    public interface IDeviceSelectCallback {
        void a(Fragment fragment, List<Device> list, boolean z);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_common_use_device_mana_layout);
        b();
        c();
        SmartHomeDeviceHelper.a().b().e();
    }

    private void a() {
        this.c = (ViewPagerSystem) findViewById(R.id.vp);
        this.c.setOffscreenPageLimit(4);
        this.c.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), this.b, this.f20638a));
        this.c.setPagingEnabled(false);
    }

    private void b() {
        List<Device> d2 = SmartHomeDeviceManager.a().d();
        List<String> C = HomeManager.a().C();
        if (d2 != null && d2.size() > 0) {
            for (int i2 = 0; i2 < d2.size(); i2++) {
                Device device = d2.get(i2);
                if (device != null && (!AddToCommonDialogHelper.a(device) || HomeVirtualDeviceHelper.a(device.model))) {
                    if (C == null || C.isEmpty() || !C.contains(device.did)) {
                        this.g.put(device.did, false);
                        this.h.put(device.did, -1L);
                    } else {
                        int indexOf = C.indexOf(device.did);
                        this.g.put(device.did, true);
                        this.h.put(device.did, Long.valueOf(indexOf == -1 ? (long) i2 : (long) indexOf));
                    }
                }
            }
        }
    }

    private void c() {
        this.f = findViewById(R.id.header_content_tv);
        this.d = (CoordinatorTabLayout) findViewById(R.id.coordinatortablayout);
        this.d.setTitle(getResources().getString(R.string.press_to_add_device)).setBackEnable(true).setupWithViewPager(this.c);
        this.d.setOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            private int b;

            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                float abs = ((float) Math.abs(i)) / ((float) appBarLayout.getTotalScrollRange());
                ImageView imageView = (ImageView) CUDAActivity.this.e.findViewById(R.id.module_a_3_return_btn);
                if (((double) abs) >= 0.99d) {
                    TextView textView = (TextView) CUDAActivity.this.e.findViewById(R.id.module_a_3_return_title);
                    if (textView != null) {
                        textView.setTextColor(CUDAActivity.this.getResources().getColor(R.color.black));
                    }
                    if (this.b != R.drawable.std_tittlebar_main_device_back_black) {
                        this.b = R.drawable.std_tittlebar_main_device_back_black;
                        if (imageView != null) {
                            imageView.setImageResource(R.drawable.std_tittlebar_main_device_back_black);
                        }
                    }
                } else {
                    TextView textView2 = (TextView) CUDAActivity.this.e.findViewById(R.id.module_a_3_return_title);
                    if (textView2 != null) {
                        textView2.setTextColor(CUDAActivity.this.getResources().getColor(R.color.white));
                    }
                    if (this.b != R.drawable.std_tittlebar_main_device_back_white) {
                        this.b = R.drawable.std_tittlebar_main_device_back_white;
                        if (imageView != null) {
                            imageView.setImageResource(R.drawable.std_tittlebar_main_device_back_white);
                        }
                    }
                }
                CUDAActivity.this.f.setAlpha(abs > 0.5f ? 0.0f : 1.0f - (abs * 2.0f));
            }
        });
        d();
        a();
        this.e = findViewById(R.id.title_bar);
        this.e.findViewById(R.id.module_a_3_right_iv_setting_btn).setVisibility(8);
        this.e.findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CUDAActivity.this.e();
                CUDAActivity.this.finish();
            }
        });
        ((TextView) this.e.findViewById(R.id.module_a_3_return_title)).setText(R.string.press_to_add_device);
        this.e.setBackgroundResource(0);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(this.c);
        tabLayout.getTabAt(0).setText(getResources().getText(this.f20638a[0]));
        tabLayout.getTabAt(1).setText(getResources().getText(this.f20638a[1]));
        tabLayout.getTabAt(2).setText(getResources().getText(this.f20638a[2]));
    }

    private void d() {
        this.b = new ArrayList();
        int i2 = 0;
        for (int i3 : this.f20638a) {
            BaseCUDFragment baseCUDFragment = null;
            if (i2 == 0) {
                baseCUDFragment = CUDFragFactory.a(CUDFragFactory.f20644a, this);
            }
            if (i2 == 1) {
                baseCUDFragment = CUDFragFactory.a(CUDFragFactory.b, this);
            }
            if (i2 == 2) {
                baseCUDFragment = CUDFragFactory.a(CUDFragFactory.c, this);
            }
            baseCUDFragment.a(this.g);
            baseCUDFragment.a(this.i);
            this.b.add(baseCUDFragment);
            i2++;
        }
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> b;
        private int[] c;

        public MyPagerAdapter(FragmentManager fragmentManager, List<Fragment> list, int[] iArr) {
            super(fragmentManager);
            this.b = list;
            this.c = iArr;
        }

        public int getCount() {
            return this.b.size();
        }

        public CharSequence getPageTitle(int i) {
            return CUDAActivity.this.getResources().getString(this.c[i]);
        }

        public Fragment getItem(int i) {
            return this.b.get(i);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        e();
    }

    /* access modifiers changed from: private */
    public void e() {
        List<PluginRecord> O;
        if (SmartHomeDeviceManager.a().u() && (O = CoreApi.a().O()) != null && !O.isEmpty() && !this.g.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (Map.Entry next : this.g.entrySet()) {
                if (((Boolean) next.getValue()).booleanValue()) {
                    arrayList.add(next.getKey());
                }
            }
            Collections.sort(arrayList, new Comparator<String>() {
                /* renamed from: a */
                public int compare(String str, String str2) {
                    Long l = (Long) CUDAActivity.this.h.get(str);
                    Long l2 = (Long) CUDAActivity.this.h.get(str2);
                    if (l == null || l2 == null) {
                        return 0;
                    }
                    if (l.longValue() > l2.longValue()) {
                        return 1;
                    }
                    if (l.longValue() < l2.longValue()) {
                        return -1;
                    }
                    return 0;
                }
            });
            a(arrayList);
            CommonUseDeviceDataManager.a().a((List<String>) arrayList);
        }
    }

    private void a(List<String> list) {
        if (IRDeviceUtil.c()) {
            List<String> C = HomeManager.a().C();
            if (C == null || C.size() == 0) {
                list.add(0, CommonUseDeviceDataManager.g);
                return;
            }
            int size = SmartHomeDeviceManager.a().d() == null ? 0 : SmartHomeDeviceManager.a().d().size();
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < C.size(); i2++) {
                String str = C.get(i2);
                if (TextUtils.equals(str, CommonUseDeviceDataManager.g)) {
                    break;
                }
                Long l = this.h.get(str);
                if (l != null && l.longValue() >= 0 && l.longValue() <= ((long) size)) {
                    arrayList.add(str);
                }
            }
            if (list.size() >= arrayList.size()) {
                list.add(arrayList.size(), CommonUseDeviceDataManager.g);
            }
        }
    }
}
