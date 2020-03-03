package com.ximalaya.ting.android.opensdk.model.live.schedule;

import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class ScheduleList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<Schedule> f2093a;

    public List<Schedule> a() {
        return this.f2093a;
    }

    public void a(List<Schedule> list) {
        this.f2093a = list;
    }
}
