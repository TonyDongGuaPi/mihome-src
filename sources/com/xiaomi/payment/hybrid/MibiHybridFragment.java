package com.xiaomi.payment.hybrid;

import com.mibi.common.hybrid.Interceptor;
import com.mibi.common.hybrid.MipayHybridFragment;
import com.mibi.common.hybrid.ResultHandler;
import com.xiaomi.payment.hybrid.interceptor.MibiPayInterceptor;
import com.xiaomi.payment.platform.R;
import java.util.ArrayList;
import java.util.List;

public class MibiHybridFragment extends MipayHybridFragment {
    /* access modifiers changed from: protected */
    public List<Interceptor> a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MibiPayInterceptor());
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public List<ResultHandler> b() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MibiPayInterceptor());
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public int g() {
        return R.xml.mibi_hybrid_config;
    }
}
