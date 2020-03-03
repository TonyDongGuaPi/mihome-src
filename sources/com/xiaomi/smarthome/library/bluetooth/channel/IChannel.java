package com.xiaomi.smarthome.library.bluetooth.channel;

import java.util.List;

public interface IChannel {
    void a(int i, byte[] bArr, int i2, ChannelCallback channelCallback);

    void a(List<byte[]> list, ChannelCallback channelCallback);

    void a(byte[] bArr);

    void a(byte[] bArr, int i);

    void a(byte[] bArr, int i, ChannelCallback channelCallback);

    void a(byte[] bArr, ChannelCallback channelCallback, boolean z);

    void f();
}
