package com.xiaomi.youpin;

import com.mics.widget.util.MiCSToastManager;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomiyoupin.toast.YPDToast;

/* renamed from: com.xiaomi.youpin.-$$Lambda$MiCSHelper$JCA7wXeHEIDLHceDbek82LUf1p0  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$MiCSHelper$JCA7wXeHEIDLHceDbek82LUf1p0 implements MiCSToastManager.IMiCSToast {
    public static final /* synthetic */ $$Lambda$MiCSHelper$JCA7wXeHEIDLHceDbek82LUf1p0 INSTANCE = new $$Lambda$MiCSHelper$JCA7wXeHEIDLHceDbek82LUf1p0();

    private /* synthetic */ $$Lambda$MiCSHelper$JCA7wXeHEIDLHceDbek82LUf1p0() {
    }

    public final void onToast(CharSequence charSequence, int i) {
        YPDToast.getInstance().toast(SHApplication.getAppContext(), charSequence.toString(), 5, true, i);
    }
}
