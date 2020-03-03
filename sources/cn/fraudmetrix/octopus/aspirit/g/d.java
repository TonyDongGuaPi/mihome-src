package cn.fraudmetrix.octopus.aspirit.g;

import android.text.TextUtils;
import cn.fraudmetrix.octopus.aspirit.g.f;

public class d extends a {
    public d(String str, String str2, String str3, String str4, int i) {
        super(str, str2, str3, str4, i);
        if (TextUtils.isEmpty(str4)) {
            this.c = "window.bridge.passHtmlContent('" + str2 + "',document.getElementsByTagName('html')[0].innerHTML);";
        }
    }

    public f.a d(String str) {
        if (this.f642a.equals(str)) {
            a(f.a.GatherData);
            return f.a.InjectionJs;
        }
        a(f.a.Error);
        return j();
    }

    public boolean e(String str) {
        this.f642a.equals(str);
        return j() == f.a.Error || j() == f.a.Complete;
    }
}
