package android.arch.lifecycle;

import java.util.HashMap;

public class ViewModelStore {

    /* renamed from: a  reason: collision with root package name */
    private final HashMap<String, ViewModel> f471a = new HashMap<>();

    /* access modifiers changed from: package-private */
    public final void a(String str, ViewModel viewModel) {
        ViewModel put = this.f471a.put(str, viewModel);
        if (put != null) {
            put.onCleared();
        }
    }

    /* access modifiers changed from: package-private */
    public final ViewModel a(String str) {
        return this.f471a.get(str);
    }

    public final void a() {
        for (ViewModel onCleared : this.f471a.values()) {
            onCleared.onCleared();
        }
        this.f471a.clear();
    }
}
