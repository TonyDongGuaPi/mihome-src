package com.ximalaya.ting.android.opensdk.model.live.program;

import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class ProgramList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<Program> f2082a;

    public List<Program> a() {
        return this.f2082a;
    }

    public void a(List<Program> list) {
        this.f2082a = list;
    }
}
