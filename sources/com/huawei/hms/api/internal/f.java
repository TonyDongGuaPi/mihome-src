package com.huawei.hms.api.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.ResponseHeader;
import com.huawei.hms.core.aidl.b;
import com.huawei.hms.core.aidl.d;
import com.huawei.hms.support.api.transport.DatagramTransport;
import com.huawei.hms.support.log.a;

public class f extends d.a {

    /* renamed from: a  reason: collision with root package name */
    private final Class<? extends IMessageEntity> f5860a;
    private final DatagramTransport.a b;

    public f(Class<? extends IMessageEntity> cls, DatagramTransport.a aVar) {
        this.f5860a = cls;
        this.b = aVar;
    }

    public void a(b bVar) throws RemoteException {
        if (bVar == null || TextUtils.isEmpty(bVar.f5870a)) {
            a.d("IPCCallback", "In call, URI cannot be empty.");
            throw new RemoteException();
        }
        com.huawei.hms.core.aidl.f a2 = com.huawei.hms.core.aidl.a.a(bVar.c());
        ResponseHeader responseHeader = new ResponseHeader();
        a2.a(bVar.b, (IMessageEntity) responseHeader);
        IMessageEntity iMessageEntity = null;
        if (bVar.b() > 0 && (iMessageEntity = a()) != null) {
            a2.a(bVar.a(), iMessageEntity);
        }
        this.b.a(responseHeader.getStatusCode(), iMessageEntity);
    }

    /* access modifiers changed from: protected */
    public IMessageEntity a() {
        if (this.f5860a == null) {
            return null;
        }
        try {
            return (IMessageEntity) this.f5860a.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            a.d("IPCCallback", "In newResponseInstance, instancing exception." + e.getMessage());
            return null;
        }
    }
}
