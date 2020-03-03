package com.chad.library.adapter.base.util;

import android.util.SparseArray;
import com.chad.library.adapter.base.provider.BaseItemProvider;

public class ProviderDelegate {

    /* renamed from: a  reason: collision with root package name */
    private SparseArray<BaseItemProvider> f5145a = new SparseArray<>();

    public void a(BaseItemProvider baseItemProvider) {
        if (baseItemProvider != null) {
            int a2 = baseItemProvider.a();
            if (this.f5145a.get(a2) == null) {
                this.f5145a.put(a2, baseItemProvider);
                return;
            }
            return;
        }
        throw new ItemProviderException("ItemProvider can not be null");
    }

    public SparseArray<BaseItemProvider> a() {
        return this.f5145a;
    }
}
