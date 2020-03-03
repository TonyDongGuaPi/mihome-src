package com.ximalaya.ting.android.opensdk.model.customized;

import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class CustomizedAlbumList extends XimalayaResponse {
    @SerializedName("total_page")

    /* renamed from: a  reason: collision with root package name */
    private int f2067a;
    @SerializedName("total_count")
    private int b;
    @SerializedName("current_page")
    private int c;
    @SerializedName("customized_album_columns")
    private List<CustomizedAlbum> d;

    public int a() {
        return this.f2067a;
    }

    public void a(int i) {
        this.f2067a = i;
    }

    public int b() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public int c() {
        return this.c;
    }

    public void c(int i) {
        this.c = i;
    }

    public List<CustomizedAlbum> d() {
        return this.d;
    }

    public void a(List<CustomizedAlbum> list) {
        this.d = list;
    }
}
