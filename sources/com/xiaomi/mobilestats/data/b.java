package com.xiaomi.mobilestats.data;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.xiaomi.mobilestats.data.ProtoMsg;

final class b extends AbstractParser {
    b() {
    }

    /* renamed from: a */
    public ProtoMsg.StatsMsg parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return new ProtoMsg.StatsMsg(codedInputStream, extensionRegistryLite, (a) null);
    }
}
