package miuipub.app;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;

public abstract class ActionBar extends android.app.ActionBar {

    public interface FragmentViewPagerChangeListener {
        void onPageScrollStateChanged(int i);

        void onPageScrolled(int i, float f, boolean z, boolean z2);

        void onPageSelected(int i);
    }

    public abstract int a(String str, ActionBar.Tab tab, int i, Class<? extends Fragment> cls, Bundle bundle, boolean z);

    public abstract int a(String str, ActionBar.Tab tab, Class<? extends Fragment> cls, Bundle bundle, boolean z);

    public abstract void a(int i, boolean z);

    public abstract void a(Fragment fragment);

    public abstract void a(Context context, FragmentManager fragmentManager);

    public abstract void a(Context context, FragmentManager fragmentManager, boolean z);

    public abstract void a(String str);

    public abstract void a(FragmentViewPagerChangeListener fragmentViewPagerChangeListener);

    public abstract void a(boolean z);

    public abstract void a(boolean z, boolean z2);

    public abstract Fragment b(int i);

    public abstract void b(FragmentViewPagerChangeListener fragmentViewPagerChangeListener);

    public abstract void c(int i);

    public abstract void c(ActionBar.Tab tab);

    public abstract void c(boolean z);

    public abstract void d(int i);

    public abstract void d(boolean z);

    public abstract void e(int i);

    public abstract void e(boolean z);

    public abstract void f(boolean z);

    public abstract boolean f();

    public abstract int g();

    public abstract void h();

    public abstract int i();
}
