package cn.fraudmetrix.octopus.aspirit.activity.mvp;

import android.content.Context;
import android.support.annotation.NonNull;
import cn.fraudmetrix.octopus.aspirit.b.c;
import cn.fraudmetrix.octopus.aspirit.bean.i;
import cn.fraudmetrix.octopus.aspirit.g.f;
import java.util.HashMap;
import java.util.List;

public interface a {

    /* renamed from: a  reason: collision with root package name */
    public static final HashMap<String, String> f599a = new HashMap<String, String>() {
        {
            put("005011", "com.jingdong.app.mall");
            put("005003", "com.taobao.taobao");
            put("005004", "com.eg.android.AlipayGphone");
        }
    };

    /* renamed from: cn.fraudmetrix.octopus.aspirit.activity.mvp.a$a  reason: collision with other inner class name */
    public interface C0018a {
        void a(@NonNull Context context, @NonNull String str, String str2, String str3, cn.fraudmetrix.octopus.aspirit.a.a<i> aVar);

        void a(@NonNull String str, cn.fraudmetrix.octopus.aspirit.a.b bVar);

        void a(@NonNull String str, String str2, c<cn.fraudmetrix.octopus.aspirit.bean.b> cVar);

        void a(@NonNull String str, @NonNull List<? extends f> list, cn.fraudmetrix.octopus.aspirit.a.b bVar);
    }

    public interface b extends cn.fraudmetrix.octopus.aspirit.base.ui.mvp.b {
        void a(int i);

        void a(int i, boolean z);

        void a(String str);

        void a(String str, String str2);

        void a(boolean z);

        void a(boolean z, int i);

        void b(int i);

        void b(String str);

        void b(String str, String str2);

        void c(String str);

        void c(String str, String str2);

        void d(String str);

        void d(String str, String str2);

        void e();

        String f();

        cn.fraudmetrix.octopus.aspirit.f.a g();

        void h();

        void i();
    }
}
