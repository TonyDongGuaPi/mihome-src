package com.xiaomi.smarthome.newui.widget;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.library.common.widget.FragmentPagerAdapter;
import com.xiaomi.smarthome.library.common.widget.PagerAdapter;
import com.xiaomi.smarthome.library.common.widget.ViewPager;
import java.util.ArrayList;
import java.util.Iterator;

public class LinearViewPager extends FrameLayout {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public FragmentPagerAdapter f20869a;
    private int b = -1;
    /* access modifiers changed from: private */
    public FragmentTransaction c;
    private ViewPager.OnPageChangeListener d;
    private final ArrayList<ItemInfo> e = new ArrayList<>();

    public int getCurrentItem() {
        return this.b;
    }

    private class ItemInfo {

        /* renamed from: a  reason: collision with root package name */
        Fragment f20871a;
        int b;

        private ItemInfo() {
        }
    }

    public LinearViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setAdapter(FragmentPagerAdapter fragmentPagerAdapter) {
        if (this.f20869a != null) {
            this.f20869a.a((PagerAdapter.DataSetObserver) null);
            this.f20869a.a((View) this);
            for (int i = 0; i < this.e.size(); i++) {
                this.f20869a.a(this, i, this.e.get(i).f20871a);
            }
            this.f20869a.b(this);
            removeAllViews();
            scrollTo(0, 0);
        }
        this.f20869a = fragmentPagerAdapter;
    }

    public void setCurrentItem(int i) {
        ItemInfo itemInfo;
        if (this.b != i) {
            boolean z = false;
            if (!ServerCompact.e(SHApplication.getAppContext()) && this.b == -1) {
                z = true;
            }
            this.b = i;
            Iterator<ItemInfo> it = this.e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    itemInfo = null;
                    break;
                }
                itemInfo = it.next();
                if (itemInfo.b == i) {
                    break;
                }
            }
            if (itemInfo != null) {
                a(this.f20869a.c(), itemInfo.f20871a, i);
                return;
            }
            Fragment a2 = a(this.f20869a.c(), i, z);
            ItemInfo itemInfo2 = new ItemInfo();
            itemInfo2.b = i;
            itemInfo2.f20871a = a2;
            this.e.add(itemInfo2);
        }
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.d = onPageChangeListener;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:0|(1:2)|(1:4)(1:5)|6|7|8|9|10) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0046 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.support.v4.app.Fragment a(android.support.v4.app.FragmentManager r3, int r4, boolean r5) {
        /*
            r2 = this;
            android.support.v4.app.FragmentTransaction r0 = r3.beginTransaction()
            r2.c = r0
            int r0 = r2.getId()
            java.lang.String r0 = a(r0, r4)
            android.support.v4.app.Fragment r3 = r3.findFragmentByTag(r0)
            r2.a()
            if (r5 == 0) goto L_0x0021
            com.xiaomi.smarthome.newui.widget.LinearViewPager$1 r5 = new com.xiaomi.smarthome.newui.widget.LinearViewPager$1
            r5.<init>()
            r0 = 1500(0x5dc, double:7.41E-321)
            r2.postDelayed(r5, r0)
        L_0x0021:
            if (r3 == 0) goto L_0x0029
            android.support.v4.app.FragmentTransaction r4 = r2.c
            r4.show(r3)
            goto L_0x0040
        L_0x0029:
            com.xiaomi.smarthome.library.common.widget.FragmentPagerAdapter r3 = r2.f20869a
            android.support.v4.app.Fragment r3 = r3.a((int) r4)
            android.support.v4.app.FragmentTransaction r5 = r2.c
            int r0 = r2.getId()
            int r1 = r2.getId()
            java.lang.String r4 = a(r1, r4)
            r5.add(r0, r3, r4)
        L_0x0040:
            android.support.v4.app.FragmentTransaction r4 = r2.c     // Catch:{ Exception -> 0x0046 }
            r4.commitNowAllowingStateLoss()     // Catch:{ Exception -> 0x0046 }
            goto L_0x0050
        L_0x0046:
            android.support.v4.app.FragmentTransaction r4 = r2.c     // Catch:{ Exception -> 0x0050 }
            r4.show(r3)     // Catch:{ Exception -> 0x0050 }
            android.support.v4.app.FragmentTransaction r4 = r2.c     // Catch:{ Exception -> 0x0050 }
            r4.commitNowAllowingStateLoss()     // Catch:{ Exception -> 0x0050 }
        L_0x0050:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.widget.LinearViewPager.a(android.support.v4.app.FragmentManager, int, boolean):android.support.v4.app.Fragment");
    }

    private void a(FragmentManager fragmentManager, Fragment fragment, int i) {
        this.c = fragmentManager.beginTransaction();
        a();
        if (fragment != null) {
            this.c.show(fragment);
        } else {
            this.c.add(getId(), this.f20869a.a(i), a(getId(), i));
        }
        this.c.commitNowAllowingStateLoss();
    }

    private void a() {
        Iterator<ItemInfo> it = this.e.iterator();
        while (it.hasNext()) {
            ItemInfo next = it.next();
            if (next.f20871a.isAdded()) {
                this.c.hide(next.f20871a);
            }
        }
    }

    /* access modifiers changed from: private */
    public static String a(int i, int i2) {
        return "android:switcher:" + i + ":" + i2;
    }

    public PagerAdapter getAdapter() {
        return this.f20869a;
    }
}
