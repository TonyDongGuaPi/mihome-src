package com.alipay.mobile.security.bio.model.inspector.impl;

import android.content.Context;
import com.alipay.mobile.security.bio.exception.BioObjectNotInitialException;
import com.alipay.mobile.security.bio.model.inspector.Inspector;

public class InspectorImpl implements Inspector {

    /* renamed from: a  reason: collision with root package name */
    Context f994a;

    public int checkEnvironment() {
        return 0;
    }

    public InspectorImpl(Context context) {
        if (context == null) {
            this.f994a = context;
            return;
        }
        throw new BioObjectNotInitialException("Context");
    }
}
