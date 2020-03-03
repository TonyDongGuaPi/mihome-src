package com.xiaomi.mobilestats.data;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.xiaomi.mobilestats.data.ProtoMsg;

final class q extends AbstractParser {
    q() {
    }

    /* renamed from: n */
    public ProtoMsg.StatsMsg.Response parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return new ProtoMsg.StatsMsg.Response(codedInputStream, extensionRegistryLite, (a) null);
    }
}
