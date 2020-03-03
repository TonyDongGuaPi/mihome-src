package cn.fraudmetrix.octopus.aspirit.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import cn.fraudmetrix.octopus.aspirit.utils.h;

public abstract class BaseActivity extends AppCompatActivity implements a {
    public void a(int i, int i2) {
        Toast.makeText(this, i, i2).show();
    }

    public void a(@Nullable CharSequence charSequence) {
        a(charSequence, 1);
    }

    public void a(@Nullable CharSequence charSequence, int i) {
        Toast.makeText(this, charSequence, i).show();
    }

    public void c() {
    }

    public void c(int i) {
        a(i, 1);
    }

    public void d() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        h.b(toString() + " onCreate start");
        super.onCreate(bundle);
        setContentView(a());
        b();
        c();
        d();
        h.b(toString() + " onCreate end");
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }
}
