package com.mi.blockcanary;

import android.content.Context;
import com.mi.blockcanary.internal.BlockInfo;

interface BlockInterceptor {
    void a(Context context, BlockInfo blockInfo);
}
