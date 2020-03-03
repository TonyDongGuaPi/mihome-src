package com.mibi.common.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.mibi.common.R;
import com.mibi.common.component.SimpleDialogFragment;
import com.mibi.common.data.Client;
import com.mibi.common.data.CommonConstants;
import com.mibi.common.data.MemoryStorage;
import com.mibi.common.data.PermissionUtils;
import com.mibi.common.data.Session;
import com.mibi.common.data.Utils;
import java.util.UUID;
import miuipub.app.ActionBar;

public abstract class BaseFragment extends DecoratableFragment implements FragmentCompat.OnRequestPermissionsResultCallback, IPresenterFactory, IView {
    private static final int B = 1;
    private static final String t = "BaseFragment";
    private static final String v = "SAVE_VIEW_UUID";
    private static final String w = "SAVE_PRESENTER_DATA";
    private boolean A;

    /* renamed from: a  reason: collision with root package name */
    protected BaseActivity f7451a;
    protected Session b;
    boolean c = false;
    boolean d = false;
    boolean e = false;
    boolean f = true;
    CharSequence g;
    boolean h = true;
    boolean i = true;
    String j;
    String k;
    int l;
    boolean m = false;
    View.OnClickListener n;
    private String u;
    private IPresenterFactory x;
    private final TaskHolder y = new TaskHolder();
    private boolean z;

    /* access modifiers changed from: protected */
    public void A() {
    }

    /* access modifiers changed from: protected */
    public void a(MemoryStorage memoryStorage) {
    }

    public IPresenter onCreatePresenter() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String w() {
        return "";
    }

    /* access modifiers changed from: protected */
    public String x() {
        return "";
    }

    public void a(Activity activity) {
        super.a(activity);
        try {
            this.f7451a = (BaseActivity) getActivity();
            this.c = this.f7451a.isInDialog();
        } catch (ClassCastException e2) {
            Log.d("TAG", "BaseFragment should use within BaseActivity");
            throw e2;
        }
    }

    public void a(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" doCreate");
        sb.append(", saveInstance is null:");
        sb.append(String.valueOf(bundle == null));
        Log.d(t, sb.toString());
        if (bundle == null) {
            this.u = UUID.randomUUID().toString();
        } else {
            this.u = bundle.getString(v);
        }
        this.b = ((BaseActivity) getActivity()).getSession();
        if (((BaseActivity) getActivity()).hasSession()) {
            b_(getArguments());
            a(this.b.m());
        }
        super.a(bundle);
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.f = bundle.getBoolean(CommonConstants.at, true);
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        b();
        this.y.c();
        IPresenter H_ = H_();
        if (H_ != null) {
            Bundle bundle2 = null;
            if (bundle != null) {
                bundle2 = bundle.getBundle(w);
            }
            H_.a(getActivity(), this.b, getArguments(), bundle2);
        }
    }

    public final void a(IPresenterFactory iPresenterFactory) {
        this.x = iPresenterFactory;
    }

    public final IPresenter H_() {
        IPresenter iPresenter = this.f7451a.mPresenters.get(this.u);
        if (iPresenter != null) {
            return iPresenter;
        }
        if (this.x != null) {
            iPresenter = this.x.onCreatePresenter();
        }
        if (iPresenter == null) {
            iPresenter = onCreatePresenter();
        }
        if (iPresenter != null) {
            this.f7451a.mPresenters.put(this.u, iPresenter);
        }
        return iPresenter;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        if (!this.d) {
            e();
        }
        this.d = true;
        this.e = false;
    }

    /* access modifiers changed from: package-private */
    public void z_() {
        if (this.d && this.e) {
            e();
        }
        if (this.e) {
            this.f7451a.forceUpdateActionBar();
        }
        this.e = false;
    }

    /* access modifiers changed from: protected */
    public final void d() {
        if (this.d) {
            e();
        }
        this.f7451a.forceUpdateActionBar();
        this.e = false;
    }

    /* access modifiers changed from: protected */
    public void e() {
        ActionBar T = T();
        if (T != null) {
            if (!this.f) {
                T.hide();
                return;
            }
            T.show();
            if (!this.c || !Utils.b()) {
                L();
            } else {
                K();
            }
        }
    }

    private void K() {
        ActionBar T = T();
        if (T != null) {
            if (T.getCustomView() == null) {
                T.setCustomView(R.layout.mibi_custom_action_bar);
                T.setDisplayOptions(16);
            }
            CharSequence f2 = f();
            if (TextUtils.isEmpty(f2)) {
                f2 = this.f7451a.getApplicationLabel();
            }
            ((TextView) T.getCustomView().findViewById(R.id.title)).setText(f2);
            String str = this.j;
            if (TextUtils.isEmpty(str)) {
                str = getString(R.string.mibi_cancel);
            }
            Button button = (Button) T.getCustomView().findViewById(R.id.buttonLeft);
            button.setText(str);
            button.setEnabled(this.i);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BaseFragment.this.y();
                }
            });
            boolean z2 = !TextUtils.isEmpty(this.k);
            Button button2 = (Button) T.getCustomView().findViewById(R.id.buttonRight);
            if (z2) {
                button2.setVisibility(0);
                if (!TextUtils.isEmpty(this.k)) {
                    button2.setText(this.k);
                }
                button2.setOnClickListener(this.n);
                return;
            }
            button2.setVisibility(8);
        }
    }

    private void L() {
        ActionBar T = T();
        if (T != null) {
            CharSequence charSequence = this.g;
            if (TextUtils.isEmpty(charSequence)) {
                charSequence = this.f7451a.getApplicationLabel();
            }
            T.setTitle(charSequence);
            boolean z2 = this.i;
            T.setHomeButtonEnabled(z2);
            T.setDisplayHomeAsUpEnabled(z2);
            int i2 = 0;
            if (!z2) {
                T.setDisplayOptions(0, 7);
            } else {
                T.setDisplayOptions(4, 7);
            }
            if (this.l != 0) {
                T.setDisplayShowCustomEnabled(true);
                T.setCustomView(R.layout.mibi_custom_action_bar_extra_button);
                View findViewById = T.getCustomView().findViewById(R.id.extra);
                View findViewById2 = T.getCustomView().findViewById(R.id.bubble);
                if (this.l != 0) {
                    findViewById.setBackgroundResource(this.l);
                    if (!this.m) {
                        i2 = 8;
                    }
                    findViewById2.setVisibility(i2);
                }
                findViewById.setOnClickListener(this.n);
                return;
            }
            T.setDisplayShowCustomEnabled(false);
        }
    }

    /* access modifiers changed from: protected */
    public final void a(int i2) {
        a((CharSequence) getString(i2));
    }

    /* access modifiers changed from: protected */
    public final void a(CharSequence charSequence) {
        if (!TextUtils.equals(this.g, charSequence)) {
            this.e = true;
        }
        this.g = charSequence;
        z_();
    }

    /* access modifiers changed from: protected */
    public final CharSequence f() {
        return this.g;
    }

    /* access modifiers changed from: protected */
    public final void b(int i2) {
        a(getString(i2));
    }

    /* access modifiers changed from: protected */
    public final void a(String str) {
        if (!TextUtils.equals(this.j, str)) {
            this.e = true;
        }
        this.j = str;
        z_();
    }

    /* access modifiers changed from: protected */
    public final void a(String str, int i2, View.OnClickListener onClickListener) {
        if (!TextUtils.equals(this.k, str)) {
            this.e = true;
        }
        if (this.l != i2) {
            this.e = true;
        }
        if (this.n != onClickListener) {
            this.e = true;
        }
        this.k = str;
        this.l = i2;
        this.n = onClickListener;
        z_();
    }

    /* access modifiers changed from: protected */
    public final void a(boolean z2) {
        if (this.l != 0) {
            if (this.m != z2) {
                this.e = true;
            }
            this.m = z2;
            z_();
        }
    }

    /* access modifiers changed from: protected */
    public final void b(boolean z2) {
        if (!(this.h == z2 && this.i == z2)) {
            this.e = true;
        }
        this.h = z2;
        this.i = z2;
        z_();
    }

    /* access modifiers changed from: protected */
    public final boolean g() {
        return this.h;
    }

    /* access modifiers changed from: protected */
    public final void c_(boolean z2) {
        if (this.i != z2) {
            this.e = true;
        }
        this.i = z2;
        z_();
    }

    /* access modifiers changed from: protected */
    public final boolean h() {
        return this.i;
    }

    /* access modifiers changed from: protected */
    public final void d(boolean z2) {
        if (this.f != z2) {
            this.e = true;
        }
        this.f = z2;
        z_();
    }

    /* access modifiers changed from: protected */
    public final boolean i() {
        return this.f;
    }

    public void d(Bundle bundle) {
        super.d(bundle);
        bundle.putString(v, this.u);
        IPresenter H_ = H_();
        if (H_ != null) {
            Bundle bundle2 = new Bundle();
            H_.a(bundle2);
            bundle.putBundle(w, bundle2);
        }
    }

    /* access modifiers changed from: protected */
    public boolean A_() {
        return this.A;
    }

    public void k() {
        super.k();
        this.A = false;
    }

    public void l() {
        super.l();
        this.A = true;
    }

    public void m() {
        super.m();
        this.f7451a.forceUpdateActionBar();
        if (!this.z) {
            this.z = true;
            this.y.d();
        }
        IPresenter H_ = H_();
        if (H_ != null) {
            H_.a((IView) this);
        }
    }

    public void n() {
        super.n();
        if (this.z) {
            this.z = false;
            Activity activity = getActivity();
            if (!Client.c()) {
                this.y.e();
            } else if (activity != null && activity.isChangingConfigurations()) {
                this.y.f();
            }
        }
        IPresenter H_ = H_();
        if (H_ != null) {
            H_.a();
        }
    }

    public void o() {
        super.o();
        Log.d(t, getClass().getSimpleName() + " doDestroy");
        this.y.g();
        IPresenter H_ = H_();
        if (H_ != null) {
            H_.c();
            this.f7451a.mPresenters.remove(this.u);
        }
    }

    public final Session p() {
        return this.b;
    }

    public final void a(Session session) {
        this.b = session;
    }

    public final TaskManager q() {
        return this.y;
    }

    /* access modifiers changed from: protected */
    public void r() {
        this.y.a();
    }

    /* access modifiers changed from: protected */
    public void s() {
        this.y.b();
    }

    /* access modifiers changed from: protected */
    public void t() {
        v();
    }

    /* access modifiers changed from: protected */
    public void u() {
        v();
    }

    /* access modifiers changed from: protected */
    public void v() {
        if (isAdded()) {
            View view = getView();
            View findFocus = view.findFocus();
            if (findFocus == null || (!(findFocus instanceof EditText) && !findFocus.onCheckIsTextEditor())) {
                Utils.a((Context) getActivity(), view);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void a(String str, String str2, String str3, DialogInterface.OnClickListener onClickListener) {
        if (!A_()) {
            SimpleDialogFragment.SimpleDialogFragmentBuilder simpleDialogFragmentBuilder = new SimpleDialogFragment.SimpleDialogFragmentBuilder(1);
            simpleDialogFragmentBuilder.b(str);
            simpleDialogFragmentBuilder.a(str2);
            SimpleDialogFragment a2 = simpleDialogFragmentBuilder.a();
            a2.a(str3, onClickListener);
            a2.setCancelable(true);
            a2.show(getFragmentManager(), "simpleDialog");
        }
    }

    public void y() {
        if (this.h) {
            super.y();
        }
    }

    public boolean a(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.a(menuItem);
        }
        y();
        return true;
    }

    public final boolean z() {
        return this.c;
    }

    /* access modifiers changed from: protected */
    public void a(String... strArr) {
        String[] b2 = PermissionUtils.b(getActivity(), strArr);
        if (b2 != null) {
            FragmentCompat.requestPermissions(this, b2, 1);
        } else {
            A();
        }
    }

    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        if (strArr == null || iArr == null) {
            Log.d(t, "invalid permission result");
            return;
        }
        if (CommonConstants.b) {
            for (int i3 = 0; i3 < strArr.length; i3++) {
                Log.d(t, "permission result: " + strArr[i3] + " " + iArr[i3]);
            }
        }
        if (PermissionUtils.a(iArr)) {
            A();
        } else {
            B();
        }
    }

    /* access modifiers changed from: protected */
    public void B() {
        Log.d(t, "user not granted permissions");
    }

    public void a(int i2, int i3, Intent intent) {
        super.a(i2, i3, intent);
        if (H_() != null) {
            if (intent == null) {
                intent = new Intent();
            }
            H_().a(i2, i3, intent.getExtras());
        }
    }

    public void a(int i2, int i3, Bundle bundle) {
        super.a(i2, i3, bundle);
        if (H_() != null) {
            H_().a(i2, i3, bundle);
        }
    }

    public void a(int i2, Bundle bundle) {
        super.a(i2, bundle);
        if (H_() != null) {
            H_().a(-1000, i2, bundle);
        }
    }
}
