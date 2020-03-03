package com.alipay.deviceid.module.x;

import com.xiaomi.verificationsdk.internal.Constants;

public final class cf {
    public static String a(ce ceVar) {
        ci ciVar = ceVar.f905a;
        ck ckVar = ceVar.c;
        cj cjVar = ceVar.b;
        cq cqVar = ceVar.g;
        cn cnVar = ceVar.e;
        cp cpVar = ceVar.f;
        cm cmVar = ceVar.d;
        ciVar.b = cnVar;
        ciVar.c.put("sensor", ciVar.b);
        cqVar.c = cpVar;
        cqVar.b.put("ua", cqVar.c);
        cmVar.c = ciVar;
        cmVar.d = ckVar;
        cmVar.e = cjVar;
        cmVar.f = cqVar;
        cmVar.b.put("dev", cmVar.c);
        cmVar.b.put("loc", cmVar.d);
        cmVar.b.put(Constants.d, cmVar.e);
        cmVar.b.put("usr", cmVar.f);
        return cmVar.a().toString();
    }
}
