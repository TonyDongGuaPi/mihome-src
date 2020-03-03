package com.ximalaya.ting.android.opensdk.model.tag;

import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class TagList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<Tag> f2117a;

    public List<Tag> a() {
        return this.f2117a;
    }

    public void a(List<Tag> list) {
        this.f2117a = list;
    }
}
