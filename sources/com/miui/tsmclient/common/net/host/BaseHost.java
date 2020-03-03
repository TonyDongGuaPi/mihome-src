package com.miui.tsmclient.common.net.host;

import com.miui.tsmclient.common.net.NetConstants;
import com.miui.tsmclient.util.EnvironmentConfig;

public abstract class BaseHost extends Host {
    public String getAuthType() {
        return EnvironmentConfig.isLoginAuth() ? "login" : NetConstants.AUTH_TYPE_OAUTH;
    }
}
