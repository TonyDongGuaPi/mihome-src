package com.youpin.weex.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.youpin.weex.app.ui.WeexFragmentForActivity;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

public class WXPageActivity extends AppCompatActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2481a = "WXPageActivity";
    private static List<WeakReference<Activity>> c = new LinkedList();
    private WeexFragmentForActivity b;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_wxpage);
        a();
        b();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        c();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        this.b.a(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return this.b.a(i, keyEvent) || super.onKeyUp(i, keyEvent);
    }

    private void a() {
        this.b = new WeexFragmentForActivity();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("bundleUrl", getIntent().getData().toString());
        this.b.setArguments(bundle);
        beginTransaction.replace(R.id.container, this.b);
        beginTransaction.commit();
    }

    private void b() {
        c.add(new WeakReference(this));
        if (c.size() > 2) {
            WeakReference remove = c.remove(0);
            if (remove.get() != null && !((Activity) remove.get()).isFinishing()) {
                ((Activity) remove.get()).finish();
            }
        }
    }

    private void c() {
        int size = c.size();
        for (int i = 0; i < size; i++) {
            if (c.get(i).get() == this) {
                c.remove(i);
                return;
            }
        }
    }
}
