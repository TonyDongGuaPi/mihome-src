package com.xiaomi.payment.channel.model;

import android.app.Activity;
import android.os.Bundle;
import com.mibi.common.base.Model;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.xiaomi.payment.data.MibiConstants;

public class TyUnicomMSGChannelModel extends Model implements IGetMSGInfoModel {
    public TyUnicomMSGChannelModel(Session session) {
        super(session);
    }

    public String[] d() {
        return new String[0];
    }

    public void a(SortedParameter sortedParameter, Activity activity, IGetMSGInfoListener iGetMSGInfoListener) {
        String f = sortedParameter.f(MibiConstants.eZ);
        Bundle bundle = new Bundle();
        bundle.putString(MibiConstants.eZ, f);
        bundle.putBoolean(MibiConstants.hj, true);
        iGetMSGInfoListener.a(bundle);
    }
}
