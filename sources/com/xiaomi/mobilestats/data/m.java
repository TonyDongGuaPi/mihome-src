package com.xiaomi.mobilestats.data;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.xiaomi.mobilestats.data.ProtoMsg;

final class m extends AbstractParser {
    m() {
    }

    /* renamed from: k */
    public ProtoMsg.StatsMsg.PageMsg parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return new ProtoMsg.StatsMsg.PageMsg(codedInputStream, extensionRegistryLite, (a) null);
    }
}
