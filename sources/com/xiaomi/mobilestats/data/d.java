package com.xiaomi.mobilestats.data;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.xiaomi.mobilestats.data.ProtoMsg;

final class d extends AbstractParser {
    d() {
    }

    /* renamed from: c */
    public ProtoMsg.StatsMsg.ClientMsg parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return new ProtoMsg.StatsMsg.ClientMsg(codedInputStream, extensionRegistryLite, (a) null);
    }
}
