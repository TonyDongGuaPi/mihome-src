package com.xiaomi.youpin.hawkeye.timecounter;

import com.xiaomi.youpin.hawkeye.entity.BaseInfo;
import com.xiaomi.youpin.hawkeye.entity.StatType;
import com.xiaomi.youpin.hawkeye.utils.ProcessUtils;
import java.util.List;

public class BlockInfo extends BaseInfo {
    public List<String> blockStack;
    public long blockTime;
    public String processName = ProcessUtils.a();

    public BlockInfo() {
        this.mStatType = StatType.BLOCKINFO;
    }
}
