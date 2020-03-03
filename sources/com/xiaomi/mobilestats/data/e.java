package com.xiaomi.mobilestats.data;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.xiaomi.mobilestats.data.ProtoMsg;

final class e extends AbstractParser {
    e() {
    }

    /* renamed from: d */
    public ProtoMsg.StatsMsg.ClientResponse parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return new ProtoMsg.StatsMsg.ClientResponse(codedInputStream, extensionRegistryLite, (a) null);
    }
}
