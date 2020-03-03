package com.xiaomi.phonenum.obtain;

import com.xiaomi.phonenum.bean.PhoneNum;
import java.io.IOException;

public interface SmsVerifier {
    PhoneNum a(PhoneNum phoneNum) throws IOException;
}
