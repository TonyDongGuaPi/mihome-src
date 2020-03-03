package com.mibi.common.base;

import android.content.Context;
import com.mibi.common.data.Session;
import java.util.UUID;
import junit.framework.Assert;

public abstract class Model implements IModel {

    /* renamed from: a  reason: collision with root package name */
    private String f7461a = UUID.randomUUID().toString();
    private Context b;
    private Session c;

    public Model(Session session) {
        Assert.assertNotNull(session);
        this.b = session.i();
        this.c = session;
    }

    public String a() {
        return this.f7461a;
    }

    public Context l_() {
        if (this.b != null) {
            return this.b;
        }
        throw new IllegalStateException("Do not call this before onInit()");
    }

    public Session c() {
        if (this.b != null) {
            return this.c;
        }
        throw new IllegalStateException("Do not call this before onInit()");
    }
}
