package com.alipay.security.mobile.module.http;

import com.alipay.tscenter.biz.rpc.report.general.a;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;

class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ DataReportRequest f1172a;
    final /* synthetic */ b b;

    c(b bVar, DataReportRequest dataReportRequest) {
        this.b = bVar;
        this.f1172a = dataReportRequest;
    }

    public void run() {
        try {
            a a2 = this.b.c;
            DataReportRequest dataReportRequest = this.f1172a;
            DataReportResult unused = b.e = a2.a();
        } catch (Throwable th) {
            DataReportResult unused2 = b.e = new DataReportResult();
            b.e.success = false;
            DataReportResult a3 = b.e;
            a3.resultCode = "static data rpc upload error, " + com.alipay.security.mobile.module.a.a.a(th);
            com.alipay.security.mobile.module.a.a.a(th);
        }
    }
}
