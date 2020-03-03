package com.xiaomi.mobilestats.data;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;

final class a implements Descriptors.FileDescriptor.InternalDescriptorAssigner {
    a() {
    }

    public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
        Descriptors.FileDescriptor unused = ProtoMsg.au = fileDescriptor;
        return null;
    }
}
