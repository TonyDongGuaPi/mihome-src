package com.mipay.common.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.mipay.common.data.d;
import java.util.ArrayList;
import java.util.Iterator;

public class l extends Fragment {
    static final int d = 100000;
    static final int e = 100001;
    static final int f = 999999;

    /* renamed from: a  reason: collision with root package name */
    private ArrayList<j> f8118a;
    int b = 0;
    Bundle c;
    private h g;
    private StepActivity h;

    private void a(boolean z) {
        if (!z) {
            onPause();
            onStop();
        } else {
            onStart();
            onResume();
        }
        doVisibilityChanged(z);
    }

    /* access modifiers changed from: package-private */
    public void a(h hVar) {
        if (isResumed()) {
            doJumpBackResult(hVar.f8116a, hVar.b);
        } else {
            this.g = hVar;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(j jVar) {
        if (this.f8118a == null) {
            this.f8118a = new ArrayList<>();
        }
        this.f8118a.add(jVar);
    }

    /* access modifiers changed from: protected */
    public void a(Class<? extends l> cls, Bundle bundle) {
        a(cls, bundle, -1, (String) null, (Class<? extends StepActivity>) null);
    }

    /* access modifiers changed from: protected */
    public void a(Class<? extends l> cls, Bundle bundle, int i, String str) {
        a(cls, bundle, i, str, (Class<? extends StepActivity>) null);
    }

    /* access modifiers changed from: protected */
    public void a(Class<? extends l> cls, Bundle bundle, int i, String str, Class<? extends StepActivity> cls2) {
        this.h.a(this, cls, bundle, i, str, cls2);
    }

    /* access modifiers changed from: protected */
    public void a(Class<? extends l> cls, Bundle bundle, String str, Class<? extends StepActivity> cls2) {
        a(cls, bundle, -1, str, cls2);
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        this.h.a(this, str);
    }

    public void doActivityCreated(Bundle bundle) {
    }

    public void doActivityResult(int i, int i2, Intent intent) {
    }

    /* access modifiers changed from: protected */
    public void doAttach(Activity activity) {
    }

    public void doBackPressed() {
        finish();
    }

    public void doCreate(Bundle bundle) {
    }

    public void doDestroy() {
    }

    public void doDestroyView() {
    }

    public void doDetach() {
    }

    public void doFragmentResult(int i, int i2, Bundle bundle) {
    }

    public View doInflateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }

    public void doJumpBackResult(int i, Bundle bundle) {
    }

    public void doNewActivityIntent(Intent intent) {
    }

    public boolean doOptionsItemSelected(MenuItem menuItem) {
        return false;
    }

    public void doPause() {
    }

    public void doResume() {
    }

    public void doSaveInstanceState(Bundle bundle) {
    }

    public void doStart() {
    }

    public void doStop() {
    }

    public void doVisibilityChanged(boolean z) {
    }

    public void finish() {
        this.h.a(this);
    }

    public void finish(String str) {
        finish(str, true);
    }

    public void finish(String str, boolean z) {
        this.h.a(str, z);
    }

    public int getResultCode() {
        return this.b;
    }

    public Bundle getResultData() {
        return this.c;
    }

    public final void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        doActivityCreated(bundle);
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 100001 || i2 == f) {
            Iterator it = intent.getParcelableArrayListExtra(d.KEY_FRAGMENT_RESULT).iterator();
            while (it.hasNext()) {
                j jVar = (j) it.next();
                Intent intent2 = null;
                if (jVar.d != null) {
                    intent2 = new Intent();
                    intent2.putExtras(jVar.d);
                }
                doActivityResult(i, jVar.c, intent2);
            }
            if (i2 == f) {
                this.h.f8111a.a((h) intent.getParcelableExtra(d.KEY_JUMP_BACK_RESULT), intent.getBooleanExtra(d.KEY_JUMP_BACK_CONTINUE, true));
                return;
            }
            return;
        }
        doActivityResult(i, i2, intent);
    }

    public final void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.h = (StepActivity) getActivity();
            doAttach(activity);
        } catch (ClassCastException unused) {
            throw new ClassCastException(getActivity().toString() + " must be a StepActivity");
        }
    }

    public final void onBackPressed() {
        doBackPressed();
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        doCreate(bundle);
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return doInflateView(layoutInflater, viewGroup, bundle);
    }

    public final void onDestroy() {
        super.onDestroy();
        doDestroy();
    }

    public final void onDestroyView() {
        super.onDestroyView();
        doDestroyView();
    }

    public final void onDetach() {
        super.onDetach();
        doDetach();
    }

    public final void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        a(!z);
    }

    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        return doOptionsItemSelected(menuItem);
    }

    public final void onPause() {
        super.onPause();
        doPause();
    }

    public final void onResume() {
        if (this.f8118a != null) {
            Iterator<j> it = this.f8118a.iterator();
            while (it.hasNext()) {
                j next = it.next();
                doFragmentResult(next.b, next.c, next.d);
            }
            this.f8118a = null;
        }
        if (this.g != null) {
            doJumpBackResult(this.g.f8116a, this.g.b);
            this.g = null;
        }
        super.onResume();
        doResume();
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        doSaveInstanceState(bundle);
    }

    public final void onStart() {
        super.onStart();
        doStart();
    }

    public final void onStop() {
        super.onStop();
        doStop();
    }

    public final void setResult(int i) {
        this.b = i;
        this.c = null;
    }

    public final void setResult(int i, Bundle bundle) {
        this.b = i;
        this.c = bundle;
    }
}
