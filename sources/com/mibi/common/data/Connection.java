package com.mibi.common.data;

import com.mibi.common.exception.PaymentException;
import java.net.URL;
import org.json.JSONObject;

public interface Connection {
    JSONObject a();

    void a(boolean z);

    String b();

    void b(boolean z);

    int c();

    SortedParameter d();

    JSONObject e() throws PaymentException;

    String f() throws PaymentException;

    URL g();
}
