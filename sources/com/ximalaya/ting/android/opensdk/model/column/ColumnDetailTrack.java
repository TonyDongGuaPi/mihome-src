package com.ximalaya.ting.android.opensdk.model.column;

import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import java.util.List;

public class ColumnDetailTrack extends ColumnDetail {
    @SerializedName("column_items")

    /* renamed from: a  reason: collision with root package name */
    private List<Track> f2060a;

    public List<Track> g() {
        return this.f2060a;
    }

    public void a(List<Track> list) {
        this.f2060a = list;
    }

    public String toString() {
        return "ColumnDetailTrack [trackList=" + this.f2060a + Operators.ARRAY_END_STR;
    }
}
