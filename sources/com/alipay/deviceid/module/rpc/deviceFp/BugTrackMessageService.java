package com.alipay.deviceid.module.rpc.deviceFp;

import com.alipay.deviceid.module.x.aq;

public interface BugTrackMessageService {
    @aq(a = "alipay.security.errorLog.collect")
    String logCollect(String str);
}
