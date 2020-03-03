package com.xiaomi.phonenum;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.xiaomi.phonenum.bean.Error;
import com.xiaomi.phonenum.bean.PhoneNum;
import com.xiaomi.phonenum.bean.Sim;
import com.xiaomi.phonenum.db.PhoneNumDBHelper;
import com.xiaomi.phonenum.obtain.DataProxyParser;
import com.xiaomi.phonenum.obtain.EncryptHttpClient;
import com.xiaomi.phonenum.obtain.HttpProxyParser;
import com.xiaomi.phonenum.obtain.ObtainHandler;
import com.xiaomi.phonenum.obtain.Parser;
import com.xiaomi.phonenum.obtain.PhoneException;
import com.xiaomi.phonenum.obtain.PhoneLevel;
import com.xiaomi.phonenum.obtain.SmsVerifier;
import com.xiaomi.phonenum.phone.PhoneUtil;
import com.xiaomi.phonenum.utils.Logger;
import com.xiaomi.phonenum.utils.LoggerManager;
import java.io.IOException;

public class PhoneNumStore {

    /* renamed from: a  reason: collision with root package name */
    public static final String f12551a = "PhoneNumStore";
    private Context b;
    private ObtainHandler c;
    private PhoneUtil d;
    private Logger e = LoggerManager.a();
    private SmsVerifier f;

    public PhoneNumStore(Context context, String str, PhoneUtil phoneUtil) {
        this.b = context;
        this.d = phoneUtil;
        EncryptHttpClient.HttpFactory httpFactory = new EncryptHttpClient.HttpFactory(this.b);
        this.c = new ObtainHandler(this.b, str, phoneUtil, httpFactory);
        DataProxyParser dataProxyParser = new DataProxyParser(httpFactory);
        dataProxyParser.a(new HttpProxyParser(httpFactory));
        this.c.a((Parser) dataProxyParser);
    }

    public void a(Parser parser) {
        this.c.a(parser);
    }

    public void a(SmsVerifier smsVerifier) {
        this.f = smsVerifier;
    }

    public PhoneNum a(int i, PhoneLevel phoneLevel) throws PhoneException {
        a();
        return c(this.d.a(i), phoneLevel);
    }

    private PhoneNum c(int i, PhoneLevel phoneLevel) throws PhoneException {
        Sim k = this.d.k(i);
        if (k != null) {
            PhoneNum a2 = PhoneNumDBHelper.a(this.b).a(k.f12555a, i);
            if (a2 != null) {
                if (a2.m < phoneLevel.value) {
                    Logger logger = this.e;
                    logger.a(f12551a, "phoneLevel not match " + a2.m + " " + phoneLevel.value);
                    return null;
                } else if (System.currentTimeMillis() - Long.valueOf(a2.h).longValue() > 86400000) {
                    Logger logger2 = this.e;
                    logger2.a(f12551a, "phoneLevel Expired " + System.currentTimeMillis() + " " + Long.valueOf(a2.h));
                    return null;
                }
            }
            return a2;
        }
        this.e.a(f12551a, "SIM_NOT_READY");
        throw new PhoneException(Error.SIM_NOT_READY);
    }

    public PhoneNum a(int i) throws IOException, PhoneException {
        return b(i, PhoneLevel.CACHE);
    }

    public boolean a(PhoneNum phoneNum) {
        if (phoneNum == null || TextUtils.isEmpty(phoneNum.e)) {
            return false;
        }
        return PhoneNumDBHelper.a(this.b).a(phoneNum.e);
    }

    public PhoneNum b(int i, PhoneLevel phoneLevel) throws IOException, PhoneException {
        if (this.d.a("android.permission.READ_PHONE_STATE")) {
            int a2 = this.d.a(i);
            if (phoneLevel.value >= PhoneLevel.SMS_VERIFY.value) {
                return b(a2);
            }
            return d(a2, phoneLevel);
        }
        throw new PhoneException(Error.NO_READ_PHONE_STATE_PERMISSION);
    }

    @Nullable
    private PhoneNum d(int i, PhoneLevel phoneLevel) throws IOException, PhoneException {
        PhoneNum c2 = c(i, phoneLevel);
        if (c2 == null && (c2 = this.c.a(i, phoneLevel)) != null && c2.f12553a == 0) {
            PhoneNumDBHelper.a(this.b).a(c2);
        }
        return c2;
    }

    @Nullable
    private PhoneNum b(int i) throws IOException, PhoneException {
        if (this.f != null) {
            PhoneNum a2 = this.f.a(d(i, PhoneLevel.LINE_NUMBER));
            if (a2 != null && a2.f12553a == 0) {
                PhoneNumDBHelper.a(this.b).a(a2);
            }
            return a2;
        }
        throw new PhoneException(Error.NOT_SUPPORT, "not support sms");
    }

    private void a() throws PhoneException {
        if (!this.d.a("android.permission.READ_PHONE_STATE")) {
            throw new PhoneException(Error.NO_READ_PHONE_STATE_PERMISSION);
        }
    }
}
