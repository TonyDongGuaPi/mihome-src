package com.xiaomi.miot.support.monitor.config;

import com.xiaomi.miot.support.monitor.config.model.BaseConfigInfo;
import com.xiaomi.miot.support.monitor.config.model.BlockConfigInfo;
import com.xiaomi.miot.support.monitor.config.model.LeakConfigInfo;
import com.xiaomi.miot.support.monitor.config.model.MemConfigInfo;
import com.xiaomi.miot.support.monitor.report.IReport;
import com.xiaomi.miot.support.monitor.report.SafeNullReporter;

public class MiotMonitorConfig {
    private static final String k = "MiotMonitorConfig";

    /* renamed from: a  reason: collision with root package name */
    public IReport f1475a = new SafeNullReporter();
    public LeakConfigInfo b = new LeakConfigInfo();
    public BlockConfigInfo c = new BlockConfigInfo();
    public BaseConfigInfo d = new BaseConfigInfo();
    public BaseConfigInfo e = new BaseConfigInfo();
    public BaseConfigInfo f = new BaseConfigInfo();
    public MemConfigInfo g = new MemConfigInfo();
    public BaseConfigInfo h = new BaseConfigInfo();
    public boolean i = true;
    public boolean j = false;
}
