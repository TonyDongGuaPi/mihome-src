package com.ximalaya.ting.android.opensdk.model.category;

import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class CategoryList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<Category> f2049a;

    public List<Category> a() {
        return this.f2049a;
    }

    public void a(List<Category> list) {
        this.f2049a = list;
    }
}
