package com.ximalaya.ting.android.opensdk.model.metadata;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Attributes extends ChildAttributes {
    @SerializedName("child_metadatas")

    /* renamed from: a  reason: collision with root package name */
    private List<ChildMetadata> f2094a;

    public List<ChildMetadata> a() {
        return this.f2094a;
    }

    public void a(List<ChildMetadata> list) {
        this.f2094a = list;
    }
}
