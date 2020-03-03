package com.xiaomi.mimc;

import java.util.List;

public interface MIMCMessageHandler {
    void a(MIMCGroupMessage mIMCGroupMessage);

    void a(MIMCMessage mIMCMessage);

    void a(MIMCServerAck mIMCServerAck);

    void a(List<MIMCMessage> list);

    void b(MIMCGroupMessage mIMCGroupMessage);

    void b(List<MIMCGroupMessage> list);

    void c(List<MIMCGroupMessage> list);
}
