package com.xiaomi.mobilestats.data;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.xiaomi.mobilestats.data.ProtoMsg;

final class h extends AbstractParser {
    h() {
    }

    /* renamed from: g */
    public ProtoMsg.StatsMsg.ErrorMsg parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return new ProtoMsg.StatsMsg.ErrorMsg(codedInputStream, extensionRegistryLite, (a) null);
    }
}
