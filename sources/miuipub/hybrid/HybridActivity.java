package miuipub.hybrid;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import com.miuipub.internal.hybrid.HybridManager;
import java.util.HashSet;
import java.util.Set;
import miuipub.app.Activity;

public class HybridActivity extends Activity {
    public static final String EXTRA_URL = "com.miui.sdk.hybrid.extra.URL";

    /* renamed from: a  reason: collision with root package name */
    private Set<HybridView> f2934a = new HashSet();

    /* access modifiers changed from: protected */
    public int getConfigResId() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Intent intent;
        super.onCreate(bundle);
        setContentView(getContentView());
        View findViewById = findViewById(R.id.hybrid_view);
        if (findViewById != null && (findViewById instanceof HybridView)) {
            String str = null;
            if (bundle != null) {
                str = bundle.getString("com.miui.sdk.hybrid.extra.URL");
            }
            if (str == null && (intent = getIntent()) != null) {
                str = intent.getStringExtra("com.miui.sdk.hybrid.extra.URL");
            }
            registerHybridView((HybridView) findViewById, getConfigResId(), str);
        }
    }

    /* access modifiers changed from: protected */
    public View getContentView() {
        return getLayoutInflater().inflate(R.layout.hybrid_main, (ViewGroup) null);
    }

    /* access modifiers changed from: protected */
    public final void registerHybridView(View view) {
        registerHybridView(view, getConfigResId());
    }

    /* access modifiers changed from: protected */
    public final void registerHybridView(View view, int i) {
        registerHybridView(view, i, (String) null);
    }

    /* access modifiers changed from: protected */
    public final void registerHybridView(View view, int i, String str) {
        if (view instanceof HybridView) {
            HybridView hybridView = (HybridView) view;
            HybridManager hybridManager = new HybridManager(this, hybridView);
            hybridView.setHybridManager(hybridManager);
            this.f2934a.add(hybridView);
            hybridManager.a(i, str);
            return;
        }
        throw new IllegalArgumentException("view being registered is not a hybrid view");
    }

    /* access modifiers changed from: protected */
    public final void unregisterHybridView(View view) {
        if (view instanceof HybridView) {
            this.f2934a.remove(view);
            return;
        }
        throw new IllegalArgumentException("view being unregistered is not a hybrid view");
    }

    private void a() {
        for (HybridView next : this.f2934a) {
            if (next != null) {
                if (next.getParent() != null) {
                    ((ViewGroup) next.getParent()).removeView(next);
                }
                next.destroy();
            }
        }
        this.f2934a.clear();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        for (HybridView hybridManager : this.f2934a) {
            hybridManager.getHybridManager().d();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        for (HybridView hybridManager : this.f2934a) {
            hybridManager.getHybridManager().e();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        for (HybridView hybridManager : this.f2934a) {
            hybridManager.getHybridManager().f();
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        for (HybridView hybridManager : this.f2934a) {
            hybridManager.getHybridManager().g();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        for (HybridView hybridManager : this.f2934a) {
            hybridManager.getHybridManager().h();
        }
        a();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        for (HybridView hybridManager : this.f2934a) {
            hybridManager.getHybridManager().a(i, i2, intent);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            for (HybridView next : this.f2934a) {
                if (next.canGoBack() && !next.getHybridManager().a()) {
                    next.goBack();
                    return true;
                }
            }
        }
        return super.onKeyDown(i, keyEvent);
    }
}
