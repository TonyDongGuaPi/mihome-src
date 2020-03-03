package com.ximalaya.ting.android.opensdk.model.announcer;

import com.google.gson.annotations.SerializedName;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import com.ximalaya.ting.android.opensdk.model.album.Announcer;
import java.util.List;

public class AnnouncerList extends XimalayaResponse {
    @SerializedName("total_page")

    /* renamed from: a  reason: collision with root package name */
    private int f2038a;
    @SerializedName("total_count")
    private int b;
    @SerializedName("current_page")
    private int c;
    @SerializedName("vcategory_id")
    private long d;
    @SerializedName("announcers")
    private List<Announcer> e;

    public int a() {
        return this.f2038a;
    }

    public void a(int i) {
        this.f2038a = i;
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

    public long d() {
        return this.d;
    }

    public void a(long j) {
        this.d = j;
    }

    public List<Announcer> e() {
        return this.e;
    }

    public void a(List<Announcer> list) {
        this.e = list;
    }
}
