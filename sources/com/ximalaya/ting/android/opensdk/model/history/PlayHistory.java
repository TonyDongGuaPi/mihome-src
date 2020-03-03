package com.ximalaya.ting.android.opensdk.model.history;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import org.json.JSONObject;

public class PlayHistory {
    @SerializedName("content_type")

    /* renamed from: a  reason: collision with root package name */
    private int f2076a;
    private PlayHistoryAlbum b;
    private PlayHistoryRadio c;
    private int d;
    private long e;
    private long f;

    public int a() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }

    public long b() {
        return this.e;
    }

    public void a(long j) {
        this.e = j;
    }

    public long c() {
        return this.f;
    }

    public void b(long j) {
        this.f = j;
    }

    public PlayHistory(JSONObject jSONObject, Gson gson) {
        if (jSONObject != null) {
            this.f2076a = jSONObject.optInt("content_type");
            if (this.f2076a == 1) {
                this.b = (PlayHistoryAlbum) gson.fromJson(jSONObject.toString(), PlayHistoryAlbum.class);
            } else if (this.f2076a == 2) {
                this.c = (PlayHistoryRadio) gson.fromJson(jSONObject.toString(), PlayHistoryRadio.class);
            }
            this.d = jSONObject.optInt("break_second");
            this.e = jSONObject.optLong("play_begin_at");
            this.f = jSONObject.optLong("play_end_at");
        }
    }

    public int d() {
        return this.f2076a;
    }

    public void b(int i) {
        this.f2076a = i;
    }

    public PlayHistoryAlbum e() {
        return this.b;
    }

    public void a(PlayHistoryAlbum playHistoryAlbum) {
        this.b = playHistoryAlbum;
    }

    public PlayHistoryRadio f() {
        return this.c;
    }

    public void a(PlayHistoryRadio playHistoryRadio) {
        this.c = playHistoryRadio;
    }
}
