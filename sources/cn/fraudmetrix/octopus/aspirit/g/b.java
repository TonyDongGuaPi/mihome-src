package cn.fraudmetrix.octopus.aspirit.g;

import android.text.TextUtils;
import android.util.Patterns;
import cn.fraudmetrix.octopus.aspirit.g.f;
import cn.fraudmetrix.octopus.aspirit.utils.a;
import com.alipay.sdk.util.i;
import java.util.ArrayList;
import java.util.List;

public abstract class b extends a {
    protected String i;
    private boolean j = false;
    private List<String> k = new ArrayList();

    public b(String str, String str2, String str3, String str4, String str5, int i2) {
        super(str, str2, str4, str5, i2);
        this.i = str3;
    }

    private void f(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(c(str));
        for (String c : this.k) {
            String c2 = c(c);
            if (!TextUtils.isEmpty(c2) && stringBuffer.indexOf(c2) == -1) {
                if (stringBuffer.length() > 0 && !stringBuffer.toString().endsWith("; ") && !stringBuffer.toString().endsWith(i.b) && c2.startsWith(i.b)) {
                    stringBuffer.append("; ");
                }
                stringBuffer.append(c2);
            }
        }
        a(str, stringBuffer.toString().replace("; ; ", "; ").replace(";;", i.b));
    }

    private boolean g(String str) {
        try {
            return Patterns.WEB_URL.matcher(str).matches() && str.matches(this.i);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public f.a a(String str, int i2) {
        if (j() != f.a.LoadUrl || this.j || i2 < 100 || !this.f642a.equals(str)) {
            return super.a(str, i2);
        }
        this.j = true;
        return f.a.InjectionJs;
    }

    /* access modifiers changed from: protected */
    public String c(String str) {
        return a.a().a(str);
    }

    public f.a d(String str) {
        if (j() != f.a.LoadUrl) {
            if (j() == f.a.WaitLogin || j() == f.a.LoginCompleteLoad) {
                if (g(str)) {
                    this.k.add(str);
                    f(str);
                    a(f.a.Complete);
                } else {
                    this.f642a = str;
                }
            } else if (j() == f.a.Complete) {
                return f.a.None;
            }
            return j();
        }
        a(f.a.WaitLogin);
        return f.a.InjectionJs;
    }

    public boolean e(String str) {
        if (g(str)) {
            a(f.a.LoginCompleteLoad);
        }
        if (j() == f.a.WaitLogin) {
            this.k.add(str);
        }
        return j() == f.a.Complete;
    }

    public void f() {
        this.k.clear();
        this.j = false;
        super.f();
    }
}
