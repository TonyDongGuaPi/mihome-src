package com.xiaomi.mobilestats.data;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.xiaomi.mobilestats.data.ProtoMsg;

final class g extends AbstractParser {
    g() {
    }

    /* renamed from: f */
    public ProtoMsg.StatsMsg.CrashMsg parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return new ProtoMsg.StatsMsg.CrashMsg(codedInputStream, extensionRegistryLite, (a) null);
    }
}
