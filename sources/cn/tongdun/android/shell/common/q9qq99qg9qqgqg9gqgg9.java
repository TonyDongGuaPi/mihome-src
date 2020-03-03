package cn.tongdun.android.shell.common;

import android.content.Context;
import cn.tongdun.android.shell.FMAgent;
import cn.tongdun.android.shell.exception.FMException;
import org.apache.commons.compress.archivers.tar.TarConstants;

public class q9qq99qg9qqgqg9gqgg9 {
    private String gqg9qq9gqq9q9q;
    private Context qgg9qgg9999g9g;

    public q9qq99qg9qqgqg9gqgg9(Context context) {
        this.qgg9qgg9999g9g = context;
        this.gqg9qq9gqq9q9q = gqg9qq9gqq9q9q.q9gqqq99999qq(context);
    }

    public void gqg9qq9gqq9q9q(String str, String str2, String str3, String str4) throws FMException {
        if (this.qgg9qgg9999g9g == null) {
            throw new FMException(gqg9qq9gqq9q9q("6e7957784d6950651e310528051978694460407256371f3001350061436d4277536a5f3e193102300564046404", 16));
        } else if (str2 != null || (str != null && (str.equals(FMAgent.ENV_PRODUCTION) || str.equals(FMAgent.ENV_SANDBOX)))) {
            this.gqg9qq9gqq9q9q = gqg9qq9gqq9q9q.q9gqqq99999qq(this.qgg9qgg9999g9g);
            if (str4 != null && !str4.equals(this.gqg9qq9gqq9q9q)) {
                throw new FMException(gqg9qq9gqq9q9q("6e0e4d094d1e46046d2b6f36633075303c", 103) + this.gqg9qq9gqq9q9q + gqg9qq9gqq9q9q("680a342d2a282b7c7c796178683f383d2531232723746d7b61733e", 38) + str4 + gqg9qq9gqq9q9q("145021", 1));
            }
        } else {
            throw new FMException(gqg9qq9gqq9q9q("6e7d50651c", 18) + str + gqg9qq9gqq9q9q("68512d6d2469367f732c653d31783a60711b7a175c15570f0d64067c7e027c1f770e61197c1f7d71326c600a6b064d04461e1c75176d6f107d1f77197a0e020f020f", 125));
        }
    }

    public boolean gqg9qq9gqq9q9q(String str) {
        return str != null || this.gqg9qq9gqq9q9q.equals(this.qgg9qgg9999g9g.getPackageName()) || this.gqg9qq9gqq9q9q.equals(this.qgg9qgg9999g9g.getApplicationInfo().processName);
    }

    public boolean gqg9qq9gqq9q9q(long j, int i) {
        return j == 0 || System.currentTimeMillis() - j >= ((long) i);
    }

    public static String gqg9qq9gqq9q9q(String str, int i) {
        try {
            int length = str.length() / 2;
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 * 2;
                bArr[i2] = (byte) ("0123456789abcdef".indexOf(charArray[i3 + 1]) | ("0123456789abcdef".indexOf(charArray[i3]) << 4));
            }
            byte b = (byte) (i ^ 81);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ TarConstants.R);
            byte b2 = bArr[0];
            int i4 = 1;
            while (i4 < length2) {
                byte b3 = bArr[i4];
                bArr[i4] = (byte) ((b2 ^ bArr[i4]) ^ b);
                i4++;
                b2 = b3;
            }
            return new String(bArr, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
