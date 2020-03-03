package com.xiaomi.mobilestats.data;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.xiaomi.mobilestats.data.ProtoMsg;

final class n extends AbstractParser {
    n() {
    }

    /* renamed from: l */
    public ProtoMsg.StatsMsg.ProtoError parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return new ProtoMsg.StatsMsg.ProtoError(codedInputStream, extensionRegistryLite, (a) null);
    }
}
