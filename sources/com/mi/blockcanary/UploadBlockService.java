package com.mi.blockcanary;

import android.content.Context;
import com.mi.blockcanary.internal.BlockInfo;
import com.mi.mistatistic.sdk.MiStatInterface;
import com.xiaomi.youpin.hawkeye.entity.StatType;

public class UploadBlockService implements BlockInterceptor {

    /* renamed from: a  reason: collision with root package name */
    private String f6751a;

    public void a(Context context, BlockInfo blockInfo) {
        a(blockInfo);
    }

    private void a(BlockInfo blockInfo) {
        try {
            if (!blockInfo.g().contains("android.os.MessageQueue.nativePollOnce(Native Method)")) {
                this.f6751a = blockInfo.c().toString();
                MiStatInterface.b("blockEvent", "blockPage", "blockLabel", StatType.BLOCKINFO, this.f6751a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
