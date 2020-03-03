package com.xiaomi.smarthome.miio.page.deviceshare;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.navigate.NavigateUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.library.common.widget.FragmentPagerAdapter;
import com.xiaomi.smarthome.library.common.widget.TabPageIndicator;
import com.xiaomi.smarthome.library.common.widget.ViewPager;
import com.xiaomi.smarthome.library.common.widget.ViewPagerWithSwipeEnable;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.List;

public class ShareDeviceInfoActivity extends BaseActivity {
    public static final String PARAM_KEY_REF_FROM_APP = "param_key_ref_from_app";
    public static final String PARAM_KEY_USERID = "user_id";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public PagerAdapter f19767a;
    /* access modifiers changed from: private */
    public ViewPagerWithSwipeEnable b;
    private TabPageIndicator c;
    /* access modifiers changed from: private */
    public Intent d;
    private int e = 0;
    private TextView f;
    private TextView g;
    private TextView h;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.d = getIntent();
        String stringExtra = this.d.getStringExtra("user_id");
        if (TextUtils.isEmpty(stringExtra) || !stringExtra.equalsIgnoreCase(CoreApi.a().s())) {
            NavigateUtil.a(this);
            finish();
            return;
        }
        setContentView(R.layout.activity_share_device_layout);
        TitleBarUtil.a(TitleBarUtil.a(), findViewById(R.id.title_bar_choose_device));
        initViews();
        if (TitleBarUtil.f11582a) {
            View findViewById = findViewById(R.id.top_delete_bar);
            int a2 = TitleBarUtil.a();
            findViewById.getLayoutParams().height += a2;
            findViewById.setPadding(0, findViewById.getPaddingTop() + a2, 0, 0);
            findViewById.setLayoutParams(findViewById.getLayoutParams());
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: package-private */
    public void initViews() {
        this.f19767a = new PagerAdapter(getSupportFragmentManager());
        this.b = (ViewPagerWithSwipeEnable) findViewById(R.id.pager);
        this.b.setAdapter(this.f19767a);
        this.b.setOffscreenPageLimit(2);
        this.c = (TabPageIndicator) findViewById(R.id.indicator);
        this.c.setViewPager(this.b);
        this.c.setVisibility(8);
        this.c.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
                if (i == 0) {
                }
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                if (i == 0) {
                    ShareDeviceInfoActivity.this.a();
                } else {
                    ShareDeviceInfoActivity.this.b();
                }
                BaseFragment baseFragment = (BaseFragment) ShareDeviceInfoActivity.this.f19767a.a(i);
                if (baseFragment != null) {
                    baseFragment.onPageSelected();
                }
                if (i == 1) {
                    STAT.d.aC();
                }
            }
        });
        this.f = (TextView) findViewById(R.id.module_a_3_return_title);
        this.f.setText(R.string.miio_setting_share);
        this.g = (TextView) findViewById(R.id.module_a_3_return_title_left);
        this.g.setText(R.string.sh_share_2);
        this.g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareDeviceInfoActivity.this.b.setCurrentItem(0);
            }
        });
        this.h = (TextView) findViewById(R.id.module_a_3_return_title_right);
        this.h.setText(R.string.accept);
        this.h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareDeviceInfoActivity.this.b.setCurrentItem(1);
            }
        });
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareDeviceInfoActivity.this.finish();
                STAT.d.aA();
            }
        });
        a();
    }

    public void setViewPagerSwipe(boolean z) {
        this.b.setSwipeEnabled(z);
    }

    /* access modifiers changed from: private */
    public void a() {
        this.g.setTextColor(Color.parseColor("#ff32BAC0"));
        this.h.setTextColor(getResources().getColor(R.color.class_D));
    }

    /* access modifiers changed from: private */
    public void b() {
        this.g.setTextColor(getResources().getColor(R.color.class_D));
        this.h.setTextColor(Color.parseColor("#ff32BAC0"));
    }

    public void setCurrentPage(int i) {
        this.b.setCurrentItem(i, true);
    }

    public class PagerAdapter extends FragmentPagerAdapter {
        private List<BaseFragment> d = new ArrayList();

        public PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            ShareDeviceInfoFragment shareDeviceInfoFragment = new ShareDeviceInfoFragment();
            shareDeviceInfoFragment.setArguments(ShareDeviceInfoActivity.this.d.getExtras());
            this.d.add(shareDeviceInfoFragment);
            ReceiveShareListFragment receiveShareListFragment = new ReceiveShareListFragment();
            receiveShareListFragment.setArguments(ShareDeviceInfoActivity.this.d.getExtras());
            this.d.add(receiveShareListFragment);
        }

        public Fragment a(int i) {
            if (i < 0 || i >= this.d.size()) {
                return null;
            }
            return this.d.get(i);
        }

        public int a() {
            return this.d.size();
        }
    }

    public void onBackPressed() {
        this.b.setSwipeEnabled(true);
        BaseFragment baseFragment = (BaseFragment) this.f19767a.a(this.b.getCurrentItem());
        if (baseFragment == null || !baseFragment.onBackPressed()) {
            super.onBackPressed();
        }
    }
}
