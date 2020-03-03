package com.xiaomi.mimc;

import com.xiaomi.mimc.data.ChannelUser;
import com.xiaomi.mimc.data.RtsDataType;
import java.util.List;

public interface MIMCRtsChannelHandler {
    void a(long j, int i, Object obj);

    void a(long j, long j2, String str, boolean z, String str2, byte[] bArr);

    void a(long j, String str, String str2);

    void a(long j, String str, String str2, boolean z, String str3);

    void a(long j, String str, String str2, boolean z, String str3, byte[] bArr, List<ChannelUser> list);

    void a(long j, String str, String str2, byte[] bArr, RtsDataType rtsDataType);

    void b(long j, int i, Object obj);

    void b(long j, String str, String str2);
}
