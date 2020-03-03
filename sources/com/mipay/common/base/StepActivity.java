package com.mipay.common.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import com.mipay.common.data.d;
import java.util.ArrayList;
import java.util.Iterator;
import junit.framework.Assert;

public class StepActivity extends Activity {
    static final int b = 100000;
    static final int c = 100001;
    static final int d = 999999;
    private static final String e = "StepActivity";
    private static final String k = "fragment_info";

    /* renamed from: a  reason: collision with root package name */
    f f8111a;
    private Class<? extends l> f;
    private Bundle g;
    private String h;
    private int i;
    private String j;

    private void b(Bundle bundle) {
        if (bundle != null) {
            if (d.DEBUG) {
                Log.v(e, "restoring fragment stack");
            }
            this.f8111a = (f) bundle.getParcelable(k);
            this.f8111a.b(getFragmentManager());
        } else {
            if (d.DEBUG) {
                Log.v(e, "building fragment stack");
            }
            this.f8111a = new f();
        }
        this.f8111a.a(this);
    }

    /* access modifiers changed from: protected */
    public void a() {
    }

    /* access modifiers changed from: protected */
    public void a(int i2, int i3, Intent intent) {
    }

    /* access modifiers changed from: protected */
    public void a(int i2, int i3, Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void a(int i2, Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void a(Intent intent) {
    }

    /* access modifiers changed from: protected */
    public void a(Bundle bundle) {
    }

    /* access modifiers changed from: package-private */
    public void a(l lVar) {
        this.f8111a.a(lVar);
    }

    /* access modifiers changed from: package-private */
    public void a(l lVar, Class<? extends l> cls, Bundle bundle, int i2, String str, Class<? extends StepActivity> cls2) {
        Assert.assertNotNull(cls);
        String tag = lVar == null ? null : lVar.getTag();
        if (cls2 != null) {
            Intent intent = new Intent(this, cls2);
            intent.putExtra("fragment", cls.getName());
            intent.putExtra(d.KEY_FRAGMENT_ARGUMENTS, bundle);
            intent.putExtra(d.KEY_FRAGMENT_RESULT_WHO, tag);
            intent.putExtra(d.KEY_FRAGMENT_REQUEST_CODE, i2);
            intent.putExtra(d.KEY_FRAGMENT_FLAG, str);
            startActivityForResult(intent, 100000);
            return;
        }
        this.f8111a.a(cls, bundle, i2, tag, false, str);
    }

    /* access modifiers changed from: package-private */
    public void a(l lVar, String str) {
        this.f8111a.a(lVar, str);
    }

    /* access modifiers changed from: protected */
    public void a(Class<? extends l> cls, Bundle bundle, String str) {
        this.f = cls;
        this.g = bundle;
        this.j = str;
    }

    /* access modifiers changed from: package-private */
    public void a(String str, boolean z) {
        this.f8111a.a(str, z);
    }

    /* access modifiers changed from: package-private */
    public void a(ArrayList<j> arrayList) {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(d.KEY_FRAGMENT_RESULT, arrayList);
        setResult(100001, intent);
        finish();
    }

    /* access modifiers changed from: package-private */
    public void a(ArrayList<j> arrayList, h hVar, boolean z) {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(d.KEY_FRAGMENT_RESULT, arrayList);
        intent.putExtra(d.KEY_JUMP_BACK_RESULT, hVar);
        intent.putExtra(d.KEY_JUMP_BACK_CONTINUE, z);
        setResult(d, intent);
        finish();
    }

    /* access modifiers changed from: protected */
    public boolean a(MenuItem menuItem) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void b() {
    }

    /* access modifiers changed from: protected */
    public void c() {
    }

    /* access modifiers changed from: protected */
    public void c(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void d() {
    }

    /* access modifiers changed from: protected */
    public void d(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void e() {
    }

    /* access modifiers changed from: protected */
    public void e(Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void f() {
        finish();
    }

    /* access modifiers changed from: protected */
    public final void onActivityResult(int i2, int i3, Intent intent) {
        Fragment findFragmentByTag;
        super.onActivityResult(i2, i3, intent);
        boolean z = false;
        boolean z2 = i2 == 100000;
        if (i3 == 100001 || i3 == d) {
            z = true;
        }
        if (z2) {
            if (z) {
                Iterator it = intent.getParcelableArrayListExtra(d.KEY_FRAGMENT_RESULT).iterator();
                while (it.hasNext()) {
                    j jVar = (j) it.next();
                    String str = jVar.f8117a;
                    if (TextUtils.isEmpty(str) || (findFragmentByTag = getFragmentManager().findFragmentByTag(str)) == null) {
                        a(jVar.b, jVar.c, jVar.d);
                    } else {
                        ((l) findFragmentByTag).doFragmentResult(jVar.b, jVar.c, jVar.d);
                    }
                }
                if (i3 != d) {
                    return;
                }
            } else {
                return;
            }
        } else if (z) {
            Iterator it2 = intent.getParcelableArrayListExtra(d.KEY_FRAGMENT_RESULT).iterator();
            while (it2.hasNext()) {
                j jVar2 = (j) it2.next();
                Intent intent2 = null;
                if (jVar2.d != null) {
                    intent2 = new Intent();
                    intent2.putExtras(jVar2.d);
                }
                a(i2, jVar2.c, intent2);
            }
            if (i3 != d) {
                return;
            }
        } else {
            a(i2, i3, intent);
            return;
        }
        this.f8111a.a((h) intent.getParcelableExtra(d.KEY_JUMP_BACK_RESULT), intent.getBooleanExtra(d.KEY_JUMP_BACK_CONTINUE, true));
    }

    public final void onBackPressed() {
        if (!this.f8111a.a()) {
            this.f8111a.d();
        } else {
            f();
        }
    }

    /* access modifiers changed from: protected */
    public final void onCreate(Bundle bundle) {
        c(bundle);
        if (isFinishing()) {
            super.onCreate(bundle);
            return;
        }
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("fragment");
        if (TextUtils.isEmpty(stringExtra)) {
            this.f = null;
        } else {
            try {
                this.f = Class.forName(stringExtra);
            } catch (Exception unused) {
                throw new IllegalStateException("fragment class to load has error");
            }
        }
        this.g = intent.getBundleExtra(d.KEY_FRAGMENT_ARGUMENTS);
        this.h = intent.getStringExtra(d.KEY_FRAGMENT_RESULT_WHO);
        this.i = intent.getIntExtra(d.KEY_FRAGMENT_REQUEST_CODE, -1);
        this.j = intent.getStringExtra(d.KEY_FRAGMENT_FLAG);
        super.onCreate(bundle);
        b(bundle);
        a(bundle);
        if (bundle == null && this.f != null) {
            this.f8111a.a(this.f, this.g, this.i, this.h, true, this.j);
        }
    }

    /* access modifiers changed from: protected */
    public final void onDestroy() {
        c();
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (!this.f8111a.a()) {
            ((l) getFragmentManager().findFragmentByTag(this.f8111a.b().f8114a)).doNewActivityIntent(intent);
            return;
        }
        a(intent);
    }

    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        return !this.f8111a.a() ? this.f8111a.a(menuItem) : a(menuItem);
    }

    /* access modifiers changed from: protected */
    public final void onPause() {
        e();
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public final void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        e(bundle);
    }

    /* access modifiers changed from: protected */
    public final void onResume() {
        super.onResume();
        d();
    }

    /* access modifiers changed from: protected */
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (d.DEBUG) {
            Log.v(e, "saving fragment stack");
        }
        this.f8111a.a(getFragmentManager());
        bundle.putParcelable(k, this.f8111a);
        d(bundle);
    }

    /* access modifiers changed from: protected */
    public final void onStart() {
        super.onStart();
        a();
    }

    /* access modifiers changed from: protected */
    public final void onStop() {
        b();
        super.onStop();
    }

    public void startFragment(Class<? extends l> cls, Bundle bundle, String str, Class<? extends StepActivity> cls2) {
        Assert.assertNotNull(cls2);
        a((l) null, cls, bundle, -1, str, cls2);
    }
}
