package cn.fraudmetrix.octopus.aspirit.g;

import cn.fraudmetrix.octopus.aspirit.utils.a;

public class g extends b {
    public g(String str, String str2, String str3, String str4, String str5, int i) {
        super(str, str2, str3, str4, str5, i);
    }

    public String e() {
        String e = super.e();
        return e + "; " + a.a().a("https://passport.suning.com/ids/login");
    }
}
