package com.ximalaya.ting.android.opensdk.model.customized;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class CustomizedTrackList extends XimalayaResponse {
    @SerializedName("total_page")

    /* renamed from: a  reason: collision with root package name */
    private int f2073a;
    @SerializedName("total_count")
    private int b;
    @SerializedName("current_page")
    private int c;
    @SerializedName("customized_track_columns")
    private List<CustomizedTrack> d;

    public int a() {
        return this.f2073a;
    }

    public void a(int i) {
        this.f2073a = i;
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

    public List<CustomizedTrack> d() {
        return this.d;
    }

    public void a(List<CustomizedTrack> list) {
        this.d = list;
    }

    public String toString() {
        return "CustomizedTrackList{customizedTracks=" + this.d + Operators.BLOCK_END;
    }
}