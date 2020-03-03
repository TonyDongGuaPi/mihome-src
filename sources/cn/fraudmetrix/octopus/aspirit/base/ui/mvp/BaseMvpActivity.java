package cn.fraudmetrix.octopus.aspirit.base.ui.mvp;

import android.content.Context;
import cn.fraudmetrix.octopus.aspirit.base.ui.BaseActivity;
import cn.fraudmetrix.octopus.aspirit.base.ui.mvp.a;

public abstract class BaseMvpActivity<T extends a> extends BaseActivity implements b {
    /* access modifiers changed from: protected */
    public T m;

    public Context l() {
        return this;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.m != null) {
            this.m.d();
        }
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        if (this.m != null) {
            this.m.j();
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        if (this.m != null) {
            this.m.i();
        }
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        if (this.m != null) {
            this.m.g();
        }
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        if (this.m != null) {
            this.m.h();
        }
        super.onStop();
    }
}
