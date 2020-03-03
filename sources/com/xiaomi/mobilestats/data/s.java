package com.xiaomi.mobilestats.data;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.xiaomi.mobilestats.data.ProtoMsg;

final class s extends AbstractParser {
    s() {
    }

    /* renamed from: p */
    public ProtoMsg.StatsMsg.ViewMsg parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return new ProtoMsg.StatsMsg.ViewMsg(codedInputStream, extensionRegistryLite, (a) null);
    }
}
