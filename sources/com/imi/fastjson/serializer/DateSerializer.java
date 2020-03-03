package com.imi.fastjson.serializer;

import com.imi.fastjson.JSON;
import com.imi.fastjson.util.IOUtils;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static final DateSerializer f6151a = new DateSerializer();

    public void a(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        char[] cArr;
        JSONSerializer jSONSerializer2 = jSONSerializer;
        SerializeWriter p = jSONSerializer.p();
        if (obj == null) {
            p.e();
        } else if (!p.a(SerializerFeature.WriteClassName) || obj.getClass() == type) {
            Date date = (Date) obj;
            if (p.a(SerializerFeature.WriteDateUseDateFormat)) {
                DateFormat b = jSONSerializer.b();
                if (b == null) {
                    b = new SimpleDateFormat(JSON.DEFFAULT_DATE_FORMAT);
                }
                p.b(b.format(date));
                return;
            }
            long time = date.getTime();
            if (jSONSerializer2.a(SerializerFeature.UseISO8601DateFormat)) {
                if (jSONSerializer2.a(SerializerFeature.UseSingleQuotes)) {
                    p.append((char) Operators.SINGLE_QUOTE);
                } else {
                    p.append('\"');
                }
                Calendar instance = Calendar.getInstance();
                instance.setTimeInMillis(time);
                int i = instance.get(1);
                int i2 = instance.get(2) + 1;
                int i3 = instance.get(5);
                int i4 = instance.get(11);
                int i5 = instance.get(12);
                int i6 = instance.get(13);
                int i7 = instance.get(14);
                if (i7 != 0) {
                    cArr = "0000-00-00T00:00:00.000".toCharArray();
                    IOUtils.a(i7, 23, cArr);
                    IOUtils.a(i6, 19, cArr);
                    IOUtils.a(i5, 16, cArr);
                    IOUtils.a(i4, 13, cArr);
                    IOUtils.a(i3, 10, cArr);
                    IOUtils.a(i2, 7, cArr);
                    IOUtils.a(i, 4, cArr);
                } else if (i6 == 0 && i5 == 0 && i4 == 0) {
                    cArr = "0000-00-00".toCharArray();
                    IOUtils.a(i3, 10, cArr);
                    IOUtils.a(i2, 7, cArr);
                    IOUtils.a(i, 4, cArr);
                } else {
                    cArr = "0000-00-00T00:00:00".toCharArray();
                    IOUtils.a(i6, 19, cArr);
                    IOUtils.a(i5, 16, cArr);
                    IOUtils.a(i4, 13, cArr);
                    IOUtils.a(i3, 10, cArr);
                    IOUtils.a(i2, 7, cArr);
                    IOUtils.a(i, 4, cArr);
                }
                p.write(cArr);
                if (jSONSerializer2.a(SerializerFeature.UseSingleQuotes)) {
                    p.append((char) Operators.SINGLE_QUOTE);
                } else {
                    p.append('\"');
                }
            } else {
                p.a(time);
            }
        } else if (obj.getClass() == Date.class) {
            p.write("new Date(");
            p.a(((Date) obj).getTime(), (char) Operators.BRACKET_END);
        } else {
            p.a((char) Operators.BLOCK_START);
            p.c(JSON.DEFAULT_TYPE_KEY);
            jSONSerializer2.b(obj.getClass().getName());
            p.a(',');
            p.c("val");
            p.a(((Date) obj).getTime());
            p.a((char) Operators.BLOCK_END);
        }
    }
}
