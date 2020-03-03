package com.xiaomi.payment.entry;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.mipay.core.runtime.ExtensionExecutable;

public interface IEntry extends ExtensionExecutable {

    /* renamed from: a  reason: collision with root package name */
    public static final String f12296a = "result";
    public static final String b = "arguments";

    public interface ContextEnterInterface {
        Context a();

        void a(Intent intent, int i);
    }

    String a();

    void a(ContextEnterInterface contextEnterInterface, Bundle bundle, int i);

    boolean a(Context context);
}
