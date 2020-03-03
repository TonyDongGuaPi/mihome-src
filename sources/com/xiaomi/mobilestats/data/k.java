package com.xiaomi.mobilestats.data;

import com.google.protobuf.Internal;
import com.xiaomi.mobilestats.data.ProtoMsg;

final class k implements Internal.EnumLiteMap {
    k() {
    }

    /* renamed from: a */
    public ProtoMsg.StatsMsg.NetworkType findValueByNumber(int i) {
        return ProtoMsg.StatsMsg.NetworkType.valueOf(i);
    }
}
