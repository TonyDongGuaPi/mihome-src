package com.mibi.common.data;

import android.text.TextUtils;
import com.mibi.common.account.FakeAccountLoader;
import com.mibi.common.exception.NotConnectedException;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.mibi.common.exception.ServiceTokenExpiredException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

public class DomainManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7517a = "DomainManager";
    private static String b = "domainLock";

    public static void a(Session session) throws PaymentException {
        if (session != null) {
            synchronized (session.a(b)) {
                a(session, b(session, (String) null, (String) null));
            }
            return;
        }
        throw new IllegalArgumentException("Session should not be null in updateHostAndServiceId");
    }

    public static void a(Session session, String str, String str2) throws PaymentException {
        if (session != null) {
            synchronized (session.a(b)) {
                a(session, b(session, str, str2));
            }
            return;
        }
        throw new IllegalArgumentException("Session should not be null in updateHostAndServiceId");
    }

    private static DomainParam b(Session session, String str, String str2) throws PaymentException {
        Connection connection;
        if (session.e() instanceof FakeAccountLoader) {
            a(session, new DomainParam(CommonConstants.z, CommonConstants.D));
            connection = ConnectionFactory.a(session.i(), CommonConstants.a(CommonConstants.H));
        } else {
            DomainParam domainParam = (DomainParam) session.m().i(CommonConstants.bi);
            if (domainParam != null && !TextUtils.isEmpty(domainParam.mHost) && !TextUtils.isEmpty(domainParam.mServiceId)) {
                return domainParam;
            }
            a(session, new DomainParam(CommonConstants.z, CommonConstants.D));
            connection = ConnectionFactory.a(CommonConstants.a("domain"), session);
        }
        if (!TextUtils.isEmpty(str)) {
            connection.d().a("order", (Object) str);
        } else if (!TextUtils.isEmpty(str2)) {
            connection.d().a(CommonConstants.aP, (Object) str2);
        }
        try {
            JSONObject e = connection.e();
            try {
                if (e.getInt("errcode") != 1984) {
                    try {
                        String string = e.getString("domain");
                        String string2 = e.getString(CommonConstants.aE);
                        if (Utils.a(string, string2)) {
                            try {
                                new URL(string);
                                return new DomainParam(string, string2);
                            } catch (MalformedURLException e2) {
                                throw new ResultException((Throwable) e2);
                            }
                        } else {
                            throw new ResultException("baseUrl or serviceId should not be empty!");
                        }
                    } catch (JSONException e3) {
                        throw new ResultException("domain content not exists", e3);
                    }
                } else {
                    throw new ServiceTokenExpiredException();
                }
            } catch (JSONException e4) {
                throw new ResultException("error code not exists", e4);
            }
        } catch (NotConnectedException e5) {
            throw e5;
        } catch (PaymentException e6) {
            session.m().a(CommonConstants.bi);
            a(session, new DomainParam(CommonConstants.z, CommonConstants.D));
            throw e6;
        }
    }

    private static void a(Session session, DomainParam domainParam) throws PaymentException {
        if (session.e() instanceof FakeAccountLoader) {
            CommonConstants.a(domainParam.mHost, domainParam.mServiceId);
            return;
        }
        if (!TextUtils.equals(CommonConstants.y, domainParam.mHost) || !TextUtils.equals(CommonConstants.C, domainParam.mServiceId)) {
            CommonConstants.a(domainParam.mHost, domainParam.mServiceId);
            session.b(session.i());
        } else {
            CommonConstants.a(domainParam.mHost, domainParam.mServiceId);
        }
        session.m().a(CommonConstants.bi, (Object) domainParam);
    }

    public static class DomainParam implements Serializable {
        public final String mHost;
        public final String mServiceId;

        public DomainParam(String str, String str2) {
            this.mHost = str;
            this.mServiceId = str2;
        }
    }
}
