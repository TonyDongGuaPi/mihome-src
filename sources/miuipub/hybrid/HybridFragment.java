package miuipub.hybrid;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.miuipub.internal.hybrid.HybridManager;
import java.util.HashSet;
import java.util.Set;
import miuipub.app.Fragment;

public class HybridFragment extends Fragment {

    /* renamed from: a  reason: collision with root package name */
    private Set<HybridView> f2943a = new HashSet();

    /* access modifiers changed from: protected */
    public int g() {
        return 0;
    }

    public View b(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return f();
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intent intent;
        super.onViewCreated(view, bundle);
        View findViewById = view.findViewById(R.id.hybrid_view);
        if (findViewById != null && (findViewById instanceof HybridView)) {
            String str = null;
            if (bundle != null) {
                str = bundle.getString("com.miui.sdk.hybrid.extra.URL");
            }
            if (str == null && (intent = getActivity().getIntent()) != null) {
                str = intent.getStringExtra("com.miui.sdk.hybrid.extra.URL");
            }
            a((HybridView) findViewById, g(), str);
        }
    }

    /* access modifiers changed from: protected */
    public View f() {
        return getActivity().getLayoutInflater().inflate(R.layout.hybrid_main, (ViewGroup) null);
    }

    /* access modifiers changed from: protected */
    public final void a(View view) {
        a(view, g());
    }

    /* access modifiers changed from: protected */
    public final void a(View view, int i) {
        a(view, i, (String) null);
    }

    /* access modifiers changed from: protected */
    public final void a(View view, int i, String str) {
        if (view instanceof HybridView) {
            HybridView hybridView = (HybridView) view;
            HybridManager hybridManager = new HybridManager(getActivity(), hybridView);
            hybridView.setHybridManager(hybridManager);
            this.f2943a.add(hybridView);
            hybridManager.a(i, str);
            return;
        }
        throw new IllegalArgumentException("view being registered is not a hybrid view");
    }

    /* access modifiers changed from: protected */
    public final void b(View view) {
        if (view instanceof HybridView) {
            this.f2943a.remove(view);
            return;
        }
        throw new IllegalArgumentException("view being unregistered is not a hybrid view");
    }

    private void a() {
        for (HybridView next : this.f2943a) {
            if (next != null) {
                if (next.getParent() != null) {
                    ((ViewGroup) next.getParent()).removeView(next);
                }
                next.destroy();
            }
        }
        this.f2943a.clear();
    }

    public void onStart() {
        super.onStart();
        for (HybridView hybridManager : this.f2943a) {
            hybridManager.getHybridManager().d();
        }
    }

    public void onResume() {
        super.onResume();
        for (HybridView hybridManager : this.f2943a) {
            hybridManager.getHybridManager().e();
        }
    }

    public void onPause() {
        super.onPause();
        for (HybridView hybridManager : this.f2943a) {
            hybridManager.getHybridManager().f();
        }
    }

    public void onStop() {
        super.onStop();
        for (HybridView hybridManager : this.f2943a) {
            hybridManager.getHybridManager().g();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        for (HybridView hybridManager : this.f2943a) {
            hybridManager.getHybridManager().h();
        }
        a();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        for (HybridView hybridManager : this.f2943a) {
            hybridManager.getHybridManager().a(i, i2, intent);
        }
    }
}
