package com.xiaomi.mobilestats.data;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.xiaomi.mobilestats.data.ProtoMsg;

final class i extends AbstractParser {
    i() {
    }

    /* renamed from: h */
    public ProtoMsg.StatsMsg.Event parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return new ProtoMsg.StatsMsg.Event(codedInputStream, extensionRegistryLite, (a) null);
    }
}
