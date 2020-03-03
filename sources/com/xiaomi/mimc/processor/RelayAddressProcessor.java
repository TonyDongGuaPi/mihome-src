package com.xiaomi.mimc.processor;

import com.xiaomi.mimc.MIMCUser;
import com.xiaomi.mimc.common.MIMCConstant;
import com.xiaomi.mimc.common.MIMCUtils;
import com.xiaomi.mimc.common.ResolverClient;
import com.xiaomi.mimc.json.JSONArray;
import com.xiaomi.msg.logger.MIMCLog;
import java.util.HashMap;
import java.util.Map;

public class RelayAddressProcessor extends Thread {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11211a = "RelayAddressProcessor";
    private MIMCUser b;

    public RelayAddressProcessor(MIMCUser mIMCUser) {
        this.b = mIMCUser;
    }

    public void run() {
        if (MIMCUtils.c(this.b.Q())) {
            MIMCLog.c(f11211a, "Relay domain is empty");
            return;
        }
        try {
            HashMap<String, JSONArray> a2 = new ResolverClient().a(this.b.N(), this.b.Q());
            if (a2 == null) {
                MIMCLog.c(f11211a, "getIpByResolver rangeAddresses is null");
                return;
            }
            for (Map.Entry next : a2.entrySet()) {
                if (((String) next.getKey()).equals(this.b.Q())) {
                    String jSONArray = ((JSONArray) next.getValue()).toString();
                    this.b.i(jSONArray);
                    MIMCUtils.a(this.b.ac(), this.b.T(), MIMCConstant.ag, jSONArray);
                    MIMCLog.b(f11211a, String.format("Get relay address from resolver, rangeAddress:%s", new Object[]{jSONArray}));
                }
            }
        } catch (Exception e) {
            MIMCLog.d(f11211a, "Exception:", e);
        }
    }
}
