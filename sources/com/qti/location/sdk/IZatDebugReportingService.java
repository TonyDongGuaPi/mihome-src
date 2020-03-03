package com.qti.location.sdk;

import android.os.Bundle;

public interface IZatDebugReportingService {

    public interface IZatDebugReportCallback {
        void a(Bundle bundle);
    }

    Bundle a();

    void a(IZatDebugReportCallback iZatDebugReportCallback) throws IZatIllegalArgumentException;

    void b(IZatDebugReportCallback iZatDebugReportCallback) throws IZatIllegalArgumentException;
}
