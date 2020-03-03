package com.xiaomi.youpin.hawkeye.memory;

import com.xiaomi.youpin.hawkeye.entity.BaseInfo;

public class MemoryRecordInfo extends BaseInfo {
    public int dalvikPss;
    public int nativePss;
    public int otherPss;
    public String processName;
    public int totalPss;
}
