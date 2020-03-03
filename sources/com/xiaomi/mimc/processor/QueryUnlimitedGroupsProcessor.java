package com.xiaomi.mimc.processor;

import com.xiaomi.mimc.MIMCUser;
import com.xiaomi.mimc.common.Backoff;
import com.xiaomi.mimc.common.HttpUtils;
import com.xiaomi.mimc.json.JSONArray;
import com.xiaomi.mimc.json.JSONObject;
import com.xiaomi.msg.logger.MIMCLog;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class QueryUnlimitedGroupsProcessor extends Thread {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11210a = "QueryUnlimitedGroupsPro";
    private MIMCUser b;
    private boolean c = false;
    private Backoff d = Backoff.a().a(TimeUnit.SECONDS, 1).b(TimeUnit.MINUTES, 1).a(2).a();

    public QueryUnlimitedGroupsProcessor(MIMCUser mIMCUser) {
        this.b = mIMCUser;
    }

    public void run() {
        while (!this.c) {
            if (this.b.m() == null || this.b.m().isEmpty()) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    MIMCLog.c(f11210a, "QueryUnlimitedGroupsProcessor exception:", e);
                }
            } else if (a()) {
                break;
            } else {
                try {
                    this.d.c();
                } catch (InterruptedException e2) {
                    MIMCLog.c(f11210a, "QueryUnlimitedGroupsProcessor queryUnlimitedGroups exception:", e2);
                }
            }
        }
        MIMCLog.b(f11210a, "QueryUnlimitedGroupsProcessor exited.");
    }

    public void a(boolean z) {
        this.c = z;
    }

    public boolean a() {
        String str = this.b.O() + "api/uctopic/topics";
        HashMap hashMap = new HashMap();
        hashMap.put("token", this.b.m());
        hashMap.put("Content-Type", "application/json");
        try {
            String a2 = HttpUtils.a(str, hashMap);
            MIMCLog.b(f11210a, String.format("QueryUnlimitedGroups: body:%s", new Object[]{a2}));
            JSONObject jSONObject = new JSONObject(a2);
            int h = jSONObject.h("code");
            String l = jSONObject.l("message");
            if (h == 200) {
                this.b.D().clear();
                JSONArray i = jSONObject.i("data");
                if (i != null) {
                    for (int i2 = 0; i2 < i.a(); i2++) {
                        this.b.D().add(Long.valueOf(i.l(i2)));
                        MIMCLog.a(f11210a, String.format("QueryUnlimitedGroups member:%s", new Object[]{i.l(i2)}));
                    }
                } else {
                    MIMCLog.b(f11210a, "QueryUnlimitedGroups groups is null.");
                }
                return true;
            }
            MIMCLog.c(f11210a, String.format("QueryUnlimitedGroups code != 200 code:%d message%s", new Object[]{Integer.valueOf(h), l}));
            return false;
        } catch (Exception e) {
            MIMCLog.d(f11210a, "Query unlimited Groups exception:", e);
        }
    }
}
