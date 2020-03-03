package com.alipay.deviceid.module.x;

import com.alipay.deviceid.module.rpc.mrpc.core.HttpException;
import com.alipay.deviceid.module.rpc.mrpc.core.RpcException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

public final class aw extends as {
    private au g;

    public aw(au auVar, Method method, int i, String str, byte[] bArr, boolean z) {
        super(method, i, str, bArr, "application/x-www-form-urlencoded", z);
        this.g = auVar;
    }

    public final Object a() {
        az azVar = new az(this.g.a());
        azVar.b = this.b;
        azVar.c = this.e;
        azVar.e = this.f;
        azVar.a("id", String.valueOf(this.d));
        azVar.a("operationType", this.c);
        azVar.a("gzip", String.valueOf(this.g.d()));
        azVar.a((Header) new BasicHeader("uuid", UUID.randomUUID().toString()));
        List<Header> list = this.g.c().b;
        if (list != null && !list.isEmpty()) {
            for (Header a2 : list) {
                azVar.a(a2);
            }
        }
        StringBuilder sb = new StringBuilder("threadid = ");
        sb.append(Thread.currentThread().getId());
        sb.append("; ");
        sb.append(azVar.toString());
        try {
            bf bfVar = this.g.b().a(azVar).get();
            if (bfVar != null) {
                return bfVar.a();
            }
            throw new RpcException((Integer) 9, "response is null");
        } catch (InterruptedException e) {
            throw new RpcException(13, "", e);
        } catch (ExecutionException e2) {
            Throwable cause = e2.getCause();
            if (cause == null || !(cause instanceof HttpException)) {
                throw new RpcException(9, "", e2);
            }
            HttpException httpException = (HttpException) cause;
            int code = httpException.getCode();
            switch (code) {
                case 1:
                    code = 2;
                    break;
                case 2:
                    code = 3;
                    break;
                case 3:
                    code = 4;
                    break;
                case 4:
                    code = 5;
                    break;
                case 5:
                    code = 6;
                    break;
                case 6:
                    code = 7;
                    break;
                case 7:
                    code = 8;
                    break;
                case 8:
                    code = 15;
                    break;
                case 9:
                    code = 16;
                    break;
            }
            throw new RpcException(Integer.valueOf(code), httpException.getMsg());
        } catch (CancellationException e3) {
            throw new RpcException(13, "", e3);
        }
    }
}
