package com.swmansion.rnscreens;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.modules.core.ChoreographerCompat;
import com.facebook.react.modules.core.ReactChoreographer;
import com.swmansion.rnscreens.ScreenFragment;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ScreenContainer<T extends ScreenFragment> extends ViewGroup {

    /* renamed from: a  reason: collision with root package name */
    private final Set<ScreenFragment> f8947a = new HashSet();
    @Nullable
    private FragmentManager b;
    @Nullable
    private FragmentTransaction c;
    private boolean d;
    private boolean e;
    /* access modifiers changed from: private */
    public boolean f = false;
    private final ChoreographerCompat.FrameCallback g = new ChoreographerCompat.FrameCallback() {
        public void doFrame(long j) {
            ScreenContainer.this.b();
        }
    };
    private final Runnable h = new Runnable() {
        public void run() {
            boolean unused = ScreenContainer.this.f = false;
            ScreenContainer.this.measure(View.MeasureSpec.makeMeasureSpec(ScreenContainer.this.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ScreenContainer.this.getHeight(), 1073741824));
            ScreenContainer.this.layout(ScreenContainer.this.getLeft(), ScreenContainer.this.getTop(), ScreenContainer.this.getRight(), ScreenContainer.this.getBottom());
        }
    };
    protected final ArrayList<T> mScreenFragments = new ArrayList<>();

    public ScreenContainer(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            getChildAt(i5).layout(0, 0, getWidth(), getHeight());
        }
    }

    public void requestLayout() {
        super.requestLayout();
        if (!this.f) {
            this.f = true;
            post(this.h);
        }
    }

    /* access modifiers changed from: protected */
    public void markUpdated() {
        if (!this.d) {
            this.d = true;
            ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, this.g);
        }
    }

    /* access modifiers changed from: protected */
    public void notifyChildUpdate() {
        markUpdated();
    }

    /* access modifiers changed from: protected */
    public T adapt(Screen screen) {
        return new ScreenFragment(screen);
    }

    /* access modifiers changed from: protected */
    public void addScreen(Screen screen, int i) {
        ScreenFragment adapt = adapt(screen);
        screen.setFragment(adapt);
        this.mScreenFragments.add(i, adapt);
        screen.setContainer(this);
        markUpdated();
    }

    /* access modifiers changed from: protected */
    public void removeScreenAt(int i) {
        ((ScreenFragment) this.mScreenFragments.get(i)).a().setContainer((ScreenContainer) null);
        this.mScreenFragments.remove(i);
        markUpdated();
    }

    /* access modifiers changed from: protected */
    public int getScreenCount() {
        return this.mScreenFragments.size();
    }

    /* access modifiers changed from: protected */
    public Screen getScreenAt(int i) {
        return ((ScreenFragment) this.mScreenFragments.get(i)).a();
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0043  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.support.v4.app.FragmentManager a() {
        /*
            r3 = this;
            r0 = r3
        L_0x0001:
            boolean r1 = r0 instanceof com.facebook.react.ReactRootView
            if (r1 != 0) goto L_0x0014
            boolean r2 = r0 instanceof com.swmansion.rnscreens.Screen
            if (r2 != 0) goto L_0x0014
            android.view.ViewParent r2 = r0.getParent()
            if (r2 == 0) goto L_0x0014
            android.view.ViewParent r0 = r0.getParent()
            goto L_0x0001
        L_0x0014:
            boolean r2 = r0 instanceof com.swmansion.rnscreens.Screen
            if (r2 == 0) goto L_0x0023
            com.swmansion.rnscreens.Screen r0 = (com.swmansion.rnscreens.Screen) r0
            android.support.v4.app.Fragment r0 = r0.getFragment()
            android.support.v4.app.FragmentManager r0 = r0.getChildFragmentManager()
            return r0
        L_0x0023:
            if (r1 == 0) goto L_0x004b
            com.facebook.react.ReactRootView r0 = (com.facebook.react.ReactRootView) r0
            android.content.Context r0 = r0.getContext()
        L_0x002b:
            boolean r1 = r0 instanceof android.support.v4.app.FragmentActivity
            if (r1 != 0) goto L_0x003a
            boolean r2 = r0 instanceof android.content.ContextWrapper
            if (r2 == 0) goto L_0x003a
            android.content.ContextWrapper r0 = (android.content.ContextWrapper) r0
            android.content.Context r0 = r0.getBaseContext()
            goto L_0x002b
        L_0x003a:
            if (r1 == 0) goto L_0x0043
            android.support.v4.app.FragmentActivity r0 = (android.support.v4.app.FragmentActivity) r0
            android.support.v4.app.FragmentManager r0 = r0.getSupportFragmentManager()
            return r0
        L_0x0043:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "In order to use RNScreens components your app's activity need to extend ReactFragmentActivity or ReactCompatActivity"
            r0.<init>(r1)
            throw r0
        L_0x004b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "ScreenContainer is not attached under ReactRootView"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.ScreenContainer.a():android.support.v4.app.FragmentManager");
    }

    /* access modifiers changed from: protected */
    public final FragmentManager getFragmentManager() {
        if (this.b == null) {
            this.b = a();
        }
        return this.b;
    }

    /* access modifiers changed from: protected */
    public FragmentTransaction getOrCreateTransaction() {
        if (this.c == null) {
            this.c = getFragmentManager().beginTransaction();
            this.c.setReorderingAllowed(true);
        }
        return this.c;
    }

    /* access modifiers changed from: protected */
    public void tryCommitTransaction() {
        if (this.c != null) {
            this.c.commitAllowingStateLoss();
            this.c = null;
        }
    }

    private void a(ScreenFragment screenFragment) {
        getOrCreateTransaction().add(getId(), (Fragment) screenFragment);
        this.f8947a.add(screenFragment);
    }

    private void b(ScreenFragment screenFragment) {
        FragmentTransaction orCreateTransaction = getOrCreateTransaction();
        orCreateTransaction.remove(screenFragment);
        orCreateTransaction.add(getId(), (Fragment) screenFragment);
    }

    private void c(ScreenFragment screenFragment) {
        getOrCreateTransaction().remove(screenFragment);
        this.f8947a.remove(screenFragment);
    }

    /* access modifiers changed from: protected */
    public boolean isScreenActive(ScreenFragment screenFragment) {
        return screenFragment.a().isActive();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.e = true;
        b();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.e = false;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            getChildAt(i3).measure(i, i2);
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        if (this.d && this.e) {
            this.d = false;
            onUpdate();
        }
    }

    /* access modifiers changed from: protected */
    public void onUpdate() {
        HashSet hashSet = new HashSet(this.f8947a);
        int size = this.mScreenFragments.size();
        for (int i = 0; i < size; i++) {
            ScreenFragment screenFragment = (ScreenFragment) this.mScreenFragments.get(i);
            if (!isScreenActive(screenFragment) && this.f8947a.contains(screenFragment)) {
                c(screenFragment);
            }
            hashSet.remove(screenFragment);
        }
        if (!hashSet.isEmpty()) {
            Object[] array = hashSet.toArray();
            for (Object obj : array) {
                c((ScreenFragment) obj);
            }
        }
        int size2 = this.mScreenFragments.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size2; i3++) {
            if (isScreenActive((ScreenFragment) this.mScreenFragments.get(i3))) {
                i2++;
            }
        }
        boolean z = i2 > 1;
        int size3 = this.mScreenFragments.size();
        boolean z2 = false;
        for (int i4 = 0; i4 < size3; i4++) {
            ScreenFragment screenFragment2 = (ScreenFragment) this.mScreenFragments.get(i4);
            boolean isScreenActive = isScreenActive(screenFragment2);
            if (isScreenActive && !this.f8947a.contains(screenFragment2)) {
                a(screenFragment2);
                z2 = true;
            } else if (isScreenActive && z2) {
                b(screenFragment2);
            }
            screenFragment2.a().setTransitioning(z);
        }
        tryCommitTransaction();
    }
}
