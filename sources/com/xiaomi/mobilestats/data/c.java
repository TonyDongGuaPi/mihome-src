package com.xiaomi.mobilestats.data;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.xiaomi.mobilestats.data.ProtoMsg;

final class c extends AbstractParser {
    c() {
    }

    /* renamed from: b */
    public ProtoMsg.StatsMsg.Client parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return new ProtoMsg.StatsMsg.Client(codedInputStream, extensionRegistryLite, (a) null);
    }
}
