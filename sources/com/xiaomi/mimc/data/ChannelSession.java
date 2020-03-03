package com.xiaomi.mimc.data;

import com.xiaomi.mimc.proto.RtsSignal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChannelSession extends RtsSession {

    /* renamed from: a  reason: collision with root package name */
    private String f11182a;
    private Set<RtsSignal.UserInfo> b;
    private byte[] c;

    public ChannelSession(RtsSignal.CallType callType, long j, String str, RtsSignal.UserInfo userInfo, long j2) {
        this(callType, j, str, userInfo, j2, (byte[]) null);
    }

    public ChannelSession(RtsSignal.CallType callType, long j, String str, RtsSignal.UserInfo userInfo, long j2, byte[] bArr) {
        super(callType, j, j2);
        this.b = new HashSet();
        this.f11182a = str;
        if (userInfo != null) {
            this.b.add(userInfo);
        }
        this.c = bArr;
    }

    public void a(RtsSignal.UserInfo userInfo) {
        if (userInfo != null) {
            this.b.add(userInfo);
        }
    }

    public void a(List<RtsSignal.UserInfo> list) {
        if (list != null) {
            this.b.addAll(list);
        }
    }

    public void a() {
        this.b.clear();
    }

    public void b(RtsSignal.UserInfo userInfo) {
        this.b.remove(userInfo);
    }

    public String b() {
        return this.f11182a;
    }

    public void a(byte[] bArr) {
        this.c = bArr;
    }

    public byte[] c() {
        return this.c;
    }

    public Set<RtsSignal.UserInfo> d() {
        return this.b;
    }
}
