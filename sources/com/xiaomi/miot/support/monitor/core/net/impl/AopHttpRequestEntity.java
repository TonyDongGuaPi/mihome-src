package com.xiaomi.miot.support.monitor.core.net.impl;

import com.xiaomi.miot.support.monitor.MiotMonitorManager;
import com.xiaomi.miot.support.monitor.core.net.NetInfo;
import com.xiaomi.miot.support.monitor.utils.AndroidUtils;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.HttpEntity;

public class AopHttpRequestEntity extends AopHttpEntity {
    private static final String c = "AopHttpRequestEntity";

    public AopHttpRequestEntity(HttpEntity httpEntity, NetInfo netInfo) {
        super(httpEntity, netInfo);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        try {
            AopOutputStream aopOutputStream = new AopOutputStream(outputStream);
            aopOutputStream.a(this);
            this.f11468a.writeTo(aopOutputStream);
            this.b.setSendBytes(aopOutputStream.b());
            this.b.setStartTime(System.currentTimeMillis());
            this.b.back_type = AndroidUtils.a(MiotMonitorManager.a().h()) ? 1 : 2;
        } catch (IOException e) {
            throw e;
        }
    }

    public void b(long j) {
        this.b.setSendBytes(j);
        this.b.end();
    }

    public void d(long j) {
        this.b.setSendBytes(j);
        this.b.end();
    }

    public void a(long j) {
        this.b.setSendBytes(j);
        this.b.end();
    }

    public void c(long j) {
        this.b.setSendBytes(j);
        this.b.end();
    }
}
