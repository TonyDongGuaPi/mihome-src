package com.xiaomi.mimc;

import com.xiaomi.mimc.data.LaunchedResponse;
import com.xiaomi.mimc.data.RtsChannelType;
import com.xiaomi.mimc.data.RtsDataType;

public interface MIMCRtsCallHandler {
    LaunchedResponse a(String str, String str2, long j, byte[] bArr);

    void a(long j, int i, Object obj);

    void a(long j, String str);

    void a(long j, String str, String str2, byte[] bArr, RtsDataType rtsDataType, RtsChannelType rtsChannelType);

    void a(long j, boolean z, String str);

    void b(long j, int i, Object obj);
}
