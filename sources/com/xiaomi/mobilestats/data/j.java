package com.xiaomi.mobilestats.data;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.xiaomi.mobilestats.data.ProtoMsg;

final class j extends AbstractParser {
    j() {
    }

    /* renamed from: i */
    public ProtoMsg.StatsMsg.EventMsg parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return new ProtoMsg.StatsMsg.EventMsg(codedInputStream, extensionRegistryLite, (a) null);
    }
}
