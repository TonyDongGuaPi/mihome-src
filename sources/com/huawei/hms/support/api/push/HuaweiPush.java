package com.huawei.hms.support.api.push;

import com.huawei.hms.api.Api;

public class HuaweiPush {
    public static final HuaweiPushApi HuaweiPushApi = new HuaweiPushApiImp();
    public static final Api<Api.ApiOptions.NoOptions> PUSH_API = new Api<>("HuaweiPush.API");
}
