package com.miui.tsmclient.model.mitsm;

import android.content.Context;
import android.os.Bundle;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.model.BaseResponse;

public class AsyncMiTSMCardOperation extends MiTSMCardOperation {
    protected AsyncMiTSMClient mAsyncMiTSMCardClient = new AsyncMiTSMClient(this.mMiTSMCardClient);

    public BaseResponse issue(Context context, CardInfo cardInfo, Bundle bundle) {
        return this.mAsyncMiTSMCardClient.issue(context, cardInfo, bundle);
    }
}
