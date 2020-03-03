package com.xiaomi.mobilestats.data;

import com.google.protobuf.Internal;
import com.xiaomi.mobilestats.data.ProtoMsg;

final class p implements Internal.EnumLiteMap {
    p() {
    }

    /* renamed from: b */
    public ProtoMsg.StatsMsg.ReportStrategy findValueByNumber(int i) {
        return ProtoMsg.StatsMsg.ReportStrategy.valueOf(i);
    }
}
