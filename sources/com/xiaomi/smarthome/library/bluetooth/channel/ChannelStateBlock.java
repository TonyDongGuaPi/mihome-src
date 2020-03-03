package com.xiaomi.smarthome.library.bluetooth.channel;

public class ChannelStateBlock {

    /* renamed from: a  reason: collision with root package name */
    public ChannelState f18489a;
    public ChannelEvent b;
    public IChannelStateHandler c;

    public ChannelStateBlock(ChannelState channelState, ChannelEvent channelEvent, IChannelStateHandler iChannelStateHandler) {
        this.f18489a = channelState;
        this.b = channelEvent;
        this.c = iChannelStateHandler;
    }
}
