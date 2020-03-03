package com.xiaomi.miot.support.monitor.core.net.impl;

import com.xiaomi.miot.support.monitor.core.net.NetInfo;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.HttpEntity;

public class AopHttpResponseEntity extends AopHttpEntity {
    private static final String c = "AopHttpResponseEntity";

    public AopHttpResponseEntity(HttpEntity httpEntity, NetInfo netInfo) {
        super(httpEntity, netInfo);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        try {
            AopOutputStream aopOutputStream = new AopOutputStream(outputStream);
            aopOutputStream.a(this);
            this.f11468a.writeTo(aopOutputStream);
        } catch (IOException e) {
            throw e;
        }
    }

    public void b(long j) {
        this.b.setReceivedBytes(j);
        this.b.end();
    }

    public void d(long j) {
        this.b.setReceivedBytes(j);
        this.b.end();
    }

    public void a(long j) {
        this.b.setReceivedBytes(j);
        this.b.end();
    }

    public void c(long j) {
        this.b.setReceivedBytes(j);
        this.b.end();
    }
}
