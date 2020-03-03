package com.xiaomi.smarthome.messagecenter;

import com.xiaomi.smarthome.messagecenter.AllTypeMsgManager;
import java.util.List;

public interface MessageCenterPageDataInterface<T> {
    void a(long j);

    void a(AllTypeMsgManager.DataloadListener dataloadListener);

    void b(AllTypeMsgManager.DataloadListener dataloadListener);

    void e();

    List<T> f();
}
