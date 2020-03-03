package com.xiaomi.mobilestats.data;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.xiaomi.mobilestats.data.ProtoMsg;

final class f extends AbstractParser {
    f() {
    }

    /* renamed from: e */
    public ProtoMsg.StatsMsg.Crash parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return new ProtoMsg.StatsMsg.Crash(codedInputStream, extensionRegistryLite, (a) null);
    }
}
