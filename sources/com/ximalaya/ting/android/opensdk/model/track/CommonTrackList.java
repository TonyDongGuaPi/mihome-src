package com.ximalaya.ting.android.opensdk.model.track;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import com.ximalaya.ting.android.opensdk.model.PlayableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommonTrackList<T extends PlayableModel> extends XimalayaResponse {
    @SerializedName(alternate = {"paid_tracks"}, value = "tracks")

    /* renamed from: a  reason: collision with root package name */
    private List<T> f2121a;
    private Map<String, String> b;
    @SerializedName("total_count")
    private int c;
    @SerializedName("total_page")
    private int d;

    public void a(CommonTrackList commonTrackList) {
        this.b = commonTrackList.b;
        this.c = commonTrackList.c;
        this.d = commonTrackList.d;
        if (this.f2121a != null) {
            this.f2121a.addAll(commonTrackList.f2121a);
        } else {
            this.f2121a = commonTrackList.f2121a;
        }
    }

    public void a(int i, CommonTrackList commonTrackList) {
        this.b = commonTrackList.b;
        this.c = commonTrackList.c;
        this.d = commonTrackList.d;
        if (this.f2121a != null) {
            this.f2121a.addAll(i, commonTrackList.f2121a);
        } else {
            this.f2121a = commonTrackList.f2121a;
        }
    }

    public void b(CommonTrackList commonTrackList) {
        this.b = commonTrackList.b;
        this.c = commonTrackList.c;
        this.d = commonTrackList.d;
        if (this.f2121a != null) {
            this.f2121a.clear();
        } else {
            this.f2121a = new ArrayList();
        }
        this.f2121a.addAll(commonTrackList.f2121a);
    }

    public void c(CommonTrackList commonTrackList) {
        this.b = commonTrackList.b;
        this.c = commonTrackList.c;
        this.d = commonTrackList.d;
    }

    public static CommonTrackList<Track> b() {
        CommonTrackList<Track> commonTrackList = new CommonTrackList<>();
        commonTrackList.a((List<Track>) new ArrayList());
        return commonTrackList;
    }

    public List<T> c() {
        return this.f2121a;
    }

    public void a(List<T> list) {
        this.f2121a = list;
    }

    public Map<String, String> d() {
        return this.b;
    }

    public void a(Map<String, String> map) {
        this.b = map;
    }

    public int e() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public int f() {
        return this.d;
    }

    public void c(int i) {
        this.d = i;
    }

    public String toString() {
        return "CommonTrackList [tracks=" + this.f2121a + ", params=" + this.b + ", totalCount=" + this.c + ", totalPage=" + this.d + Operators.ARRAY_END_STR;
    }
}
