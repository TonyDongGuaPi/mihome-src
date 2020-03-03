package com.xiaomi.mobilestats.data;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.xiaomi.mobilestats.data.ProtoMsg;

final class o extends AbstractParser {
    o() {
    }

    /* renamed from: m */
    public ProtoMsg.StatsMsg.ProtoMap parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return new ProtoMsg.StatsMsg.ProtoMap(codedInputStream, extensionRegistryLite, (a) null);
    }
}
