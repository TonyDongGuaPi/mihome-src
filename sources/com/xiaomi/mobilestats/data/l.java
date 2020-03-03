package com.xiaomi.mobilestats.data;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.xiaomi.mobilestats.data.ProtoMsg;

final class l extends AbstractParser {
    l() {
    }

    /* renamed from: j */
    public ProtoMsg.StatsMsg.Page parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return new ProtoMsg.StatsMsg.Page(codedInputStream, extensionRegistryLite, (a) null);
    }
}
