package com.miuipub.internal.app;

import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.miuipub.internal.app.ActionBarImpl;
import com.miuipub.internal.widget.ActionBarContainer;
import com.miuipub.internal.widget.ActionBarOverlayLayout;
import java.util.ArrayList;
import java.util.Iterator;
import miuipub.app.ActionBar;
import miuipub.v6.R;
import miuipub.view.ViewPager;

public class ActionBarViewPagerController {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public ActionBarImpl f8225a;
    /* access modifiers changed from: private */
    public ViewPager b;
    /* access modifiers changed from: private */
    public DynamicFragmentPagerAdapter c;
    /* access modifiers changed from: private */
    public ArrayList<ActionBar.FragmentViewPagerChangeListener> d;
    private ActionBar.TabListener e = new ActionBar.TabListener() {
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        }

        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        }

        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            int a2 = ActionBarViewPagerController.this.c.a();
            for (int i = 0; i < a2; i++) {
                if (ActionBarViewPagerController.this.c.b(i) == tab) {
                    ActionBarViewPagerController.this.b.setCurrentItem(i, true);
                    return;
                }
            }
        }
    };
    private ActionMenuChangeAnimatorObject f;
    private ObjectAnimator g;

    ActionBarViewPagerController(ActionBarImpl actionBarImpl, FragmentManager fragmentManager, boolean z) {
        this.f8225a = actionBarImpl;
        ActionBarOverlayLayout b2 = this.f8225a.b();
        Context context = b2.getContext();
        View findViewById = b2.findViewById(R.id.v6_view_pager);
        if (findViewById instanceof ViewPager) {
            this.b = (ViewPager) findViewById;
        } else {
            this.b = new ViewPager(context);
            this.b.setId(R.id.v6_view_pager);
            ((ViewGroup) b2.findViewById(16908290)).addView(this.b);
        }
        this.c = new DynamicFragmentPagerAdapter(context, fragmentManager, this.b);
        this.b.setInternalPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void a(int i, float f, int i2) {
                boolean a2 = ActionBarViewPagerController.this.c.a(i);
                int i3 = i + 1;
                boolean z = i3 < ActionBarViewPagerController.this.c.a() && ActionBarViewPagerController.this.c.a(i3);
                if (ActionBarViewPagerController.this.d != null) {
                    Iterator it = ActionBarViewPagerController.this.d.iterator();
                    while (it.hasNext()) {
                        ((ActionBar.FragmentViewPagerChangeListener) it.next()).onPageScrolled(i, f, a2, z);
                    }
                }
            }

            public void a(int i) {
                ActionBarViewPagerController.this.f8225a.setSelectedNavigationItem(i);
                ActionBarViewPagerController.this.c.b(ActionBarViewPagerController.this.b, i, ActionBarViewPagerController.this.c.a(i, true));
                if (ActionBarViewPagerController.this.d != null) {
                    Iterator it = ActionBarViewPagerController.this.d.iterator();
                    while (it.hasNext()) {
                        ((ActionBar.FragmentViewPagerChangeListener) it.next()).onPageSelected(i);
                    }
                }
            }

            public void b(int i) {
                if (ActionBarViewPagerController.this.d != null) {
                    Iterator it = ActionBarViewPagerController.this.d.iterator();
                    while (it.hasNext()) {
                        ((ActionBar.FragmentViewPagerChangeListener) it.next()).onPageScrollStateChanged(i);
                    }
                }
            }
        });
        if (z) {
            a((ActionBar.FragmentViewPagerChangeListener) new ViewPagerScrollEffect(this.b, this.c));
        }
    }

    /* access modifiers changed from: package-private */
    public int a(String str, ActionBar.Tab tab, Class<? extends Fragment> cls, Bundle bundle, boolean z) {
        ((ActionBarImpl.TabImpl) tab).a(this.e);
        this.f8225a.a(tab);
        return this.c.a(str, cls, bundle, tab, z);
    }

    /* access modifiers changed from: package-private */
    public int a(String str, ActionBar.Tab tab, int i, Class<? extends Fragment> cls, Bundle bundle, boolean z) {
        ((ActionBarImpl.TabImpl) tab).a(this.e);
        this.f8225a.a(tab, i);
        return this.c.a(str, i, cls, bundle, tab, z);
    }

    /* access modifiers changed from: package-private */
    public void a(int i) {
        this.c.c(i);
        this.f8225a.a(i);
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        int a2 = this.c.a(str);
        if (a2 >= 0) {
            a(a2);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(ActionBar.Tab tab) {
        this.f8225a.b(tab);
        this.c.a(tab);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.f8225a.c();
        this.c.b();
    }

    /* access modifiers changed from: package-private */
    public Fragment b(int i) {
        return this.c.a(i, true);
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.c.a();
    }

    /* access modifiers changed from: package-private */
    public void a(Fragment fragment) {
        int a2 = this.c.a(fragment);
        if (a2 >= 0) {
            this.f8225a.a(a2);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i, boolean z) {
        this.c.b(i, z);
        if (i == this.b.getCurrentItem()) {
            if (this.f == null) {
                this.f = new ActionMenuChangeAnimatorObject();
                this.g = ObjectAnimator.ofFloat(this.f, "Value", new float[]{0.0f, 1.0f});
                this.g.setDuration((long) this.b.getContext().getResources().getInteger(17694720));
            }
            this.f.a(i, z);
            this.g.start();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(ActionBar.FragmentViewPagerChangeListener fragmentViewPagerChangeListener) {
        if (this.d == null) {
            this.d = new ArrayList<>();
        }
        this.d.add(fragmentViewPagerChangeListener);
    }

    /* access modifiers changed from: package-private */
    public void b(ActionBar.FragmentViewPagerChangeListener fragmentViewPagerChangeListener) {
        if (this.d != null) {
            this.d.remove(fragmentViewPagerChangeListener);
        }
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return this.b.getOffscreenPageLimit();
    }

    /* access modifiers changed from: package-private */
    public void c(int i) {
        this.b.setOffscreenPageLimit(i);
    }

    class ActionMenuChangeAnimatorObject {
        private int b;
        private boolean c;

        ActionMenuChangeAnimatorObject() {
        }

        /* access modifiers changed from: package-private */
        public void a(int i, boolean z) {
            this.b = i;
            this.c = z;
        }

        public void a(float f) {
            if (ActionBarViewPagerController.this.d != null) {
                Iterator it = ActionBarViewPagerController.this.d.iterator();
                while (it.hasNext()) {
                    ActionBar.FragmentViewPagerChangeListener fragmentViewPagerChangeListener = (ActionBar.FragmentViewPagerChangeListener) it.next();
                    if (fragmentViewPagerChangeListener instanceof ActionBarContainer) {
                        fragmentViewPagerChangeListener.onPageScrolled(this.b, 1.0f - f, this.c, !this.c);
                    }
                }
            }
        }
    }
}
