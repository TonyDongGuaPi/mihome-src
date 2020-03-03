package com.imi.fastjson.serializer;

public class MapSerializer implements ObjectSerializer {

    /* renamed from: a  reason: collision with root package name */
    public static MapSerializer f6173a = new MapSerializer();

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00cd A[Catch:{ all -> 0x0103 }] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00d2 A[Catch:{ all -> 0x0103 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.imi.fastjson.serializer.JSONSerializer r18, java.lang.Object r19, java.lang.Object r20, java.lang.reflect.Type r21) throws java.io.IOException {
        /*
            r17 = this;
            r1 = r18
            r0 = r19
            com.imi.fastjson.serializer.SerializeWriter r2 = r18.p()
            if (r0 != 0) goto L_0x000e
            r2.e()
            return
        L_0x000e:
            r3 = r0
            java.util.Map r3 = (java.util.Map) r3
            com.imi.fastjson.serializer.SerializerFeature r4 = com.imi.fastjson.serializer.SerializerFeature.SortField
            boolean r4 = r2.a((com.imi.fastjson.serializer.SerializerFeature) r4)
            if (r4 == 0) goto L_0x0027
            boolean r4 = r3 instanceof java.util.SortedMap
            if (r4 != 0) goto L_0x0027
            boolean r4 = r3 instanceof java.util.LinkedHashMap
            if (r4 != 0) goto L_0x0027
            java.util.TreeMap r4 = new java.util.TreeMap     // Catch:{ Exception -> 0x0027 }
            r4.<init>(r3)     // Catch:{ Exception -> 0x0027 }
            r3 = r4
        L_0x0027:
            boolean r4 = r18.b((java.lang.Object) r19)
            if (r4 == 0) goto L_0x0031
            r18.c(r19)
            return
        L_0x0031:
            com.imi.fastjson.serializer.SerialContext r4 = r18.c()
            r5 = r20
            r1.a((com.imi.fastjson.serializer.SerialContext) r4, (java.lang.Object) r0, (java.lang.Object) r5)
            r5 = 123(0x7b, float:1.72E-43)
            r2.a((char) r5)     // Catch:{ all -> 0x0103 }
            r18.g()     // Catch:{ all -> 0x0103 }
            com.imi.fastjson.serializer.SerializerFeature r5 = com.imi.fastjson.serializer.SerializerFeature.WriteClassName     // Catch:{ all -> 0x0103 }
            boolean r5 = r2.a((com.imi.fastjson.serializer.SerializerFeature) r5)     // Catch:{ all -> 0x0103 }
            r7 = 1
            if (r5 == 0) goto L_0x005d
            java.lang.String r5 = com.imi.fastjson.JSON.DEFAULT_TYPE_KEY     // Catch:{ all -> 0x0103 }
            r2.c(r5)     // Catch:{ all -> 0x0103 }
            java.lang.Class r5 = r19.getClass()     // Catch:{ all -> 0x0103 }
            java.lang.String r5 = r5.getName()     // Catch:{ all -> 0x0103 }
            r2.b((java.lang.String) r5)     // Catch:{ all -> 0x0103 }
            r5 = 0
            goto L_0x005e
        L_0x005d:
            r5 = 1
        L_0x005e:
            java.util.Set r8 = r3.entrySet()     // Catch:{ all -> 0x0103 }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ all -> 0x0103 }
            r9 = 0
            r10 = r9
            r11 = r10
        L_0x0069:
            boolean r12 = r8.hasNext()     // Catch:{ all -> 0x0103 }
            if (r12 == 0) goto L_0x00e6
            java.lang.Object r12 = r8.next()     // Catch:{ all -> 0x0103 }
            java.util.Map$Entry r12 = (java.util.Map.Entry) r12     // Catch:{ all -> 0x0103 }
            java.lang.Object r13 = r12.getValue()     // Catch:{ all -> 0x0103 }
            java.lang.Object r12 = r12.getKey()     // Catch:{ all -> 0x0103 }
            r14 = 44
            if (r12 == 0) goto L_0x0094
            boolean r15 = r12 instanceof java.lang.String     // Catch:{ all -> 0x0103 }
            if (r15 == 0) goto L_0x0086
            goto L_0x0094
        L_0x0086:
            if (r5 != 0) goto L_0x008b
            r2.a((char) r14)     // Catch:{ all -> 0x0103 }
        L_0x008b:
            r1.d(r12)     // Catch:{ all -> 0x0103 }
            r5 = 58
            r2.a((char) r5)     // Catch:{ all -> 0x0103 }
            goto L_0x00cb
        L_0x0094:
            r15 = r12
            java.lang.String r15 = (java.lang.String) r15     // Catch:{ all -> 0x0103 }
            boolean r16 = com.imi.fastjson.serializer.FilterUtils.a(r1, r0, r15)     // Catch:{ all -> 0x0103 }
            if (r16 != 0) goto L_0x009e
            goto L_0x0069
        L_0x009e:
            boolean r16 = com.imi.fastjson.serializer.FilterUtils.c(r1, r0, r15, r13)     // Catch:{ all -> 0x0103 }
            if (r16 != 0) goto L_0x00a5
            goto L_0x0069
        L_0x00a5:
            java.lang.String r15 = com.imi.fastjson.serializer.FilterUtils.b(r1, r0, r15, r13)     // Catch:{ all -> 0x0103 }
            java.lang.Object r13 = com.imi.fastjson.serializer.FilterUtils.a(r1, r0, r15, r13)     // Catch:{ all -> 0x0103 }
            if (r13 != 0) goto L_0x00b8
            com.imi.fastjson.serializer.SerializerFeature r6 = com.imi.fastjson.serializer.SerializerFeature.WriteMapNullValue     // Catch:{ all -> 0x0103 }
            boolean r6 = r1.a((com.imi.fastjson.serializer.SerializerFeature) r6)     // Catch:{ all -> 0x0103 }
            if (r6 != 0) goto L_0x00b8
            goto L_0x0069
        L_0x00b8:
            if (r5 != 0) goto L_0x00bd
            r2.a((char) r14)     // Catch:{ all -> 0x0103 }
        L_0x00bd:
            com.imi.fastjson.serializer.SerializerFeature r5 = com.imi.fastjson.serializer.SerializerFeature.PrettyFormat     // Catch:{ all -> 0x0103 }
            boolean r5 = r2.a((com.imi.fastjson.serializer.SerializerFeature) r5)     // Catch:{ all -> 0x0103 }
            if (r5 == 0) goto L_0x00c8
            r18.i()     // Catch:{ all -> 0x0103 }
        L_0x00c8:
            r2.a((java.lang.String) r15, (boolean) r7)     // Catch:{ all -> 0x0103 }
        L_0x00cb:
            if (r13 != 0) goto L_0x00d2
            r2.e()     // Catch:{ all -> 0x0103 }
        L_0x00d0:
            r5 = 0
            goto L_0x0069
        L_0x00d2:
            java.lang.Class r5 = r13.getClass()     // Catch:{ all -> 0x0103 }
            if (r5 != r10) goto L_0x00dc
            r11.a(r1, r13, r12, r9)     // Catch:{ all -> 0x0103 }
            goto L_0x00d0
        L_0x00dc:
            com.imi.fastjson.serializer.ObjectSerializer r6 = r1.a((java.lang.Class<?>) r5)     // Catch:{ all -> 0x0103 }
            r6.a(r1, r13, r12, r9)     // Catch:{ all -> 0x0103 }
            r10 = r5
            r11 = r6
            goto L_0x00d0
        L_0x00e6:
            r1.a((com.imi.fastjson.serializer.SerialContext) r4)
            r18.h()
            com.imi.fastjson.serializer.SerializerFeature r0 = com.imi.fastjson.serializer.SerializerFeature.PrettyFormat
            boolean r0 = r2.a((com.imi.fastjson.serializer.SerializerFeature) r0)
            if (r0 == 0) goto L_0x00fd
            int r0 = r3.size()
            if (r0 <= 0) goto L_0x00fd
            r18.i()
        L_0x00fd:
            r0 = 125(0x7d, float:1.75E-43)
            r2.a((char) r0)
            return
        L_0x0103:
            r0 = move-exception
            r1.a((com.imi.fastjson.serializer.SerialContext) r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.imi.fastjson.serializer.MapSerializer.a(com.imi.fastjson.serializer.JSONSerializer, java.lang.Object, java.lang.Object, java.lang.reflect.Type):void");
    }
}
