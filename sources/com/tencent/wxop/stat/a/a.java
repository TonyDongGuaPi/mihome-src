package com.tencent.wxop.stat.a;

import android.content.Context;
import com.tencent.wxop.stat.StatAccount;
import com.tencent.wxop.stat.StatSpecifyReportedInfo;
import com.tencent.wxop.stat.common.q;
import com.xiaomi.accountsdk.account.AccountIntent;
import org.json.JSONObject;

public class a extends e {

    /* renamed from: a  reason: collision with root package name */
    private StatAccount f9271a = null;

    public a(Context context, int i, StatAccount statAccount, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        super(context, i, statSpecifyReportedInfo);
        this.f9271a = statAccount;
    }

    public f a() {
        return f.ADDITION;
    }

    public boolean a(JSONObject jSONObject) {
        q.a(jSONObject, AccountIntent.QQ_SNS_TYPE, this.f9271a.b());
        jSONObject.put("acc", this.f9271a.a());
        return true;
    }
}
