package com.tencent.wxop.stat.a;

import android.content.Context;
import com.tencent.wxop.stat.StatGameUser;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.q;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import org.json.JSONObject;

public class g extends e {

    /* renamed from: a  reason: collision with root package name */
    private StatGameUser f9277a = null;

    public g(Context context, int i, StatGameUser statGameUser, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.f9277a = statGameUser.clone();
    }

    public f a() {
        return f.MTA_GAME_USER;
    }

    public boolean a(JSONObject jSONObject) {
        if (this.f9277a == null) {
            return false;
        }
        q.a(jSONObject, "wod", this.f9277a.a());
        q.a(jSONObject, ApiConst.j, this.f9277a.b());
        q.a(jSONObject, "lev", this.f9277a.c());
        return true;
    }
}
