package com.alipay.mobile.common.logging.api;

public interface ProcessInfo {
    public static final String ALIAS_MAIN = "main";
    public static final String ALIAS_PUSH = "push";
    public static final String ALIAS_TOOLS = "tools";
    public static final String ALIAS_UNKNOWN = "unknown";

    String getMainProcessName();

    String getProcessAlias();

    int getProcessId();

    String getProcessName();

    String getPushProcessName();

    String getToolsProcessName();

    boolean isMainProcess();

    boolean isPushProcess();

    boolean isToolsProcess();
}
