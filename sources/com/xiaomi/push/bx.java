package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import miuipub.reflect.Field;

public class bx extends bz {
    public bx(String str, String str2, String[] strArr, String str3) {
        super(str, str2, strArr, str3);
    }

    public static bx a(Context context, String str, int i) {
        b.b("delete  messages when db size is too bigger");
        String a2 = cd.a(context).a(str);
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("rowDataId in (select ");
        sb.append("rowDataId from " + a2);
        sb.append(" order by createTimeStamp asc");
        sb.append(" limit ?)");
        return new bx(str, sb.toString(), new String[]{String.valueOf(i)}, "a job build to delete history message");
    }

    private void a(long j) {
        if (this.d != null && this.d.length > 0) {
            this.d[0] = String.valueOf(j);
        }
    }

    public void a(Context context, Object obj) {
        if (obj instanceof Long) {
            long longValue = ((Long) obj).longValue();
            long a2 = cj.a(c());
            long j = bv.b;
            if (a2 > j) {
                double d = (double) (a2 - j);
                Double.isNaN(d);
                double d2 = (double) j;
                Double.isNaN(d2);
                double d3 = (double) longValue;
                Double.isNaN(d3);
                long j2 = (long) (((d * 1.2d) / d2) * d3);
                a(j2);
                br a3 = br.a(context);
                a3.a("begin delete " + j2 + "noUpload messages , because db size is " + a2 + Field.b);
                super.a(context, obj);
                return;
            }
            b.b("db size is suitable");
        }
    }
}
