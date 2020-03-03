package com.xiaomi.mobilestats.data;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.xiaomi.mobilestats.data.ProtoMsg;

final class r extends AbstractParser {
    r() {
    }

    /* renamed from: o */
    public ProtoMsg.StatsMsg.View parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return new ProtoMsg.StatsMsg.View(codedInputStream, extensionRegistryLite, (a) null);
    }
}
