package miuipub.date;

import android.content.res.Resources;
import com.miuipub.internal.util.PackageConstants;
import java.util.Locale;
import miuipub.util.SoftReferenceSingleton;
import miuipub.v6.R;

public class CalendarFormatSymbols {

    /* renamed from: a  reason: collision with root package name */
    private static final SoftReferenceSingleton<CalendarFormatSymbols> f2928a = new SoftReferenceSingleton<CalendarFormatSymbols>() {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public CalendarFormatSymbols createInstance() {
            return new CalendarFormatSymbols();
        }
    };
    private Resources b = PackageConstants.a().getResources();

    public static CalendarFormatSymbols a() {
        return f2928a.get();
    }

    public Locale b() {
        return Locale.getDefault();
    }

    public String[] c() {
        return this.b.getStringArray(R.array.v6_solar_terms);
    }

    public String[] d() {
        return this.b.getStringArray(R.array.v6_chinese_days);
    }

    public String[] e() {
        return this.b.getStringArray(R.array.v6_detailed_am_pms);
    }

    public String[] f() {
        return this.b.getStringArray(R.array.v6_am_pms);
    }

    public String[] g() {
        return this.b.getStringArray(R.array.v6_chinese_digits);
    }

    public String[] h() {
        return this.b.getStringArray(R.array.v6_chinese_leap_months);
    }

    public String[] i() {
        return this.b.getStringArray(R.array.v6_chinese_months);
    }

    public String[] j() {
        return this.b.getStringArray(R.array.v6_earthly_branches);
    }

    public String[] k() {
        return this.b.getStringArray(R.array.v6_months_short);
    }

    public String[] l() {
        return this.b.getStringArray(R.array.v6_months_shortest);
    }

    public String[] m() {
        return this.b.getStringArray(R.array.v6_months);
    }

    public String[] n() {
        return this.b.getStringArray(R.array.v6_heavenly_stems);
    }

    public String[] o() {
        return this.b.getStringArray(R.array.v6_chinese_symbol_animals);
    }

    public String[] p() {
        return this.b.getStringArray(R.array.v6_eras);
    }

    public String[] q() {
        return this.b.getStringArray(R.array.v6_week_days_short);
    }

    public String[] r() {
        return this.b.getStringArray(R.array.v6_week_days_shortest);
    }

    public String[] s() {
        return this.b.getStringArray(R.array.v6_week_days);
    }
}
