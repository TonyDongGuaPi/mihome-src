package com.taobao.weex.ui.component.list;

import java.util.regex.Pattern;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class RecyclerTransform {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final String TAG = "RecyclerTransform";
    public static final String TRANSFORM = "transform";
    private static final Pattern transformPattern = Pattern.compile("([a-z]+)\\(([0-9\\.]+),?([0-9\\.]+)?\\)");

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(5931478026264070438L, "com/taobao/weex/ui/component/list/RecyclerTransform", 32);
        $jacocoData = a2;
        return a2;
    }

    public RecyclerTransform() {
        $jacocoInit()[0] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[31] = true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0128, code lost:
        r1[23] = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.support.v7.widget.RecyclerView.ItemDecoration parseTransforms(int r16, java.lang.String r17) {
        /*
            r0 = r17
            boolean[] r1 = $jacocoInit()
            r2 = 1
            if (r0 != 0) goto L_0x000d
            r0 = 0
            r1[r2] = r2
            return r0
        L_0x000d:
            r3 = 2
            r1[r3] = r2
            java.util.regex.Pattern r4 = transformPattern
            java.util.regex.Matcher r4 = r4.matcher(r0)
            r5 = 3
            r1[r5] = r2
            r0 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
        L_0x0020:
            boolean r0 = r4.find()
            if (r0 == 0) goto L_0x015b
            r0 = 4
            r1[r0] = r2
            java.lang.String r7 = r4.group()
            r0 = 5
            r1[r0] = r2
            java.lang.String r0 = r4.group(r2)
            r8 = 6
            r1[r8] = r2     // Catch:{ NumberFormatException -> 0x012d }
            r8 = -1
            int r15 = r0.hashCode()     // Catch:{ NumberFormatException -> 0x012d }
            r6 = -1267206133(0xffffffffb477f80b, float:-2.3093905E-7)
            if (r15 == r6) goto L_0x008d
            r6 = -925180581(0xffffffffc8dadd5b, float:-448234.84)
            if (r15 == r6) goto L_0x007a
            r6 = 109250890(0x683094a, float:4.929037E-35)
            if (r15 == r6) goto L_0x0067
            r6 = 1052832078(0x3ec0f14e, float:0.376841)
            if (r15 == r6) goto L_0x0054
            r0 = 7
            r1[r0] = r2     // Catch:{ NumberFormatException -> 0x012d }
            goto L_0x009f
        L_0x0054:
            java.lang.String r6 = "translate"
            boolean r0 = r0.equals(r6)     // Catch:{ NumberFormatException -> 0x012d }
            if (r0 != 0) goto L_0x0061
            r0 = 10
            r1[r0] = r2     // Catch:{ NumberFormatException -> 0x012d }
            goto L_0x009f
        L_0x0061:
            r0 = 11
            r1[r0] = r2     // Catch:{ NumberFormatException -> 0x012d }
            r8 = 1
            goto L_0x009f
        L_0x0067:
            java.lang.String r6 = "scale"
            boolean r0 = r0.equals(r6)     // Catch:{ NumberFormatException -> 0x012d }
            if (r0 != 0) goto L_0x0074
            r0 = 8
            r1[r0] = r2     // Catch:{ NumberFormatException -> 0x012d }
            goto L_0x009f
        L_0x0074:
            r0 = 9
            r1[r0] = r2     // Catch:{ NumberFormatException -> 0x012d }
            r8 = 0
            goto L_0x009f
        L_0x007a:
            java.lang.String r6 = "rotate"
            boolean r0 = r0.equals(r6)     // Catch:{ NumberFormatException -> 0x012d }
            if (r0 != 0) goto L_0x0087
            r0 = 14
            r1[r0] = r2     // Catch:{ NumberFormatException -> 0x012d }
            goto L_0x009f
        L_0x0087:
            r0 = 15
            r1[r0] = r2     // Catch:{ NumberFormatException -> 0x012d }
            r8 = 3
            goto L_0x009f
        L_0x008d:
            java.lang.String r6 = "opacity"
            boolean r0 = r0.equals(r6)     // Catch:{ NumberFormatException -> 0x012d }
            if (r0 != 0) goto L_0x009a
            r0 = 12
            r1[r0] = r2     // Catch:{ NumberFormatException -> 0x012d }
            goto L_0x009f
        L_0x009a:
            r0 = 13
            r1[r0] = r2     // Catch:{ NumberFormatException -> 0x012d }
            r8 = 2
        L_0x009f:
            switch(r8) {
                case 0: goto L_0x00ee;
                case 1: goto L_0x00cc;
                case 2: goto L_0x00b9;
                case 3: goto L_0x00a6;
                default: goto L_0x00a2;
            }     // Catch:{ NumberFormatException -> 0x012d }
        L_0x00a2:
            java.lang.String r0 = "RecyclerTransform"
            goto L_0x0110
        L_0x00a6:
            java.lang.String r0 = r4.group(r3)     // Catch:{ NumberFormatException -> 0x012d }
            int r6 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x012d }
            r0 = 21
            r1[r0] = r2     // Catch:{ NumberFormatException -> 0x00b5 }
            r12 = r6
            goto L_0x0128
        L_0x00b5:
            r0 = move-exception
            r12 = r6
            goto L_0x012e
        L_0x00b9:
            java.lang.String r0 = r4.group(r3)     // Catch:{ NumberFormatException -> 0x012d }
            float r6 = java.lang.Float.parseFloat(r0)     // Catch:{ NumberFormatException -> 0x012d }
            r0 = 20
            r1[r0] = r2     // Catch:{ NumberFormatException -> 0x00c8 }
            r9 = r6
            goto L_0x0128
        L_0x00c8:
            r0 = move-exception
            r9 = r6
            goto L_0x012e
        L_0x00cc:
            java.lang.String r0 = r4.group(r3)     // Catch:{ NumberFormatException -> 0x012d }
            int r6 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x012d }
            r0 = 18
            r1[r0] = r2     // Catch:{ NumberFormatException -> 0x00eb }
            java.lang.String r0 = r4.group(r5)     // Catch:{ NumberFormatException -> 0x00eb }
            int r8 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x00eb }
            r0 = 19
            r1[r0] = r2     // Catch:{ NumberFormatException -> 0x00e7 }
            r10 = r6
            r11 = r8
            goto L_0x0128
        L_0x00e7:
            r0 = move-exception
            r10 = r6
            r11 = r8
            goto L_0x012e
        L_0x00eb:
            r0 = move-exception
            r10 = r6
            goto L_0x012e
        L_0x00ee:
            java.lang.String r0 = r4.group(r3)     // Catch:{ NumberFormatException -> 0x012d }
            float r6 = java.lang.Float.parseFloat(r0)     // Catch:{ NumberFormatException -> 0x012d }
            r0 = 16
            r1[r0] = r2     // Catch:{ NumberFormatException -> 0x010d }
            java.lang.String r0 = r4.group(r5)     // Catch:{ NumberFormatException -> 0x010d }
            float r8 = java.lang.Float.parseFloat(r0)     // Catch:{ NumberFormatException -> 0x010d }
            r0 = 17
            r1[r0] = r2     // Catch:{ NumberFormatException -> 0x0109 }
            r13 = r6
            r14 = r8
            goto L_0x0128
        L_0x0109:
            r0 = move-exception
            r13 = r6
            r14 = r8
            goto L_0x012e
        L_0x010d:
            r0 = move-exception
            r13 = r6
            goto L_0x012e
        L_0x0110:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x012d }
            r6.<init>()     // Catch:{ NumberFormatException -> 0x012d }
            java.lang.String r8 = "Invaild transform expression:"
            r6.append(r8)     // Catch:{ NumberFormatException -> 0x012d }
            r6.append(r7)     // Catch:{ NumberFormatException -> 0x012d }
            java.lang.String r6 = r6.toString()     // Catch:{ NumberFormatException -> 0x012d }
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r0, (java.lang.String) r6)     // Catch:{ NumberFormatException -> 0x012d }
            r0 = 22
            r1[r0] = r2     // Catch:{ NumberFormatException -> 0x012d }
        L_0x0128:
            r0 = 23
            r1[r0] = r2
            goto L_0x0155
        L_0x012d:
            r0 = move-exception
        L_0x012e:
            r6 = 24
            r1[r6] = r2
            java.lang.String r6 = ""
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r6, (java.lang.Throwable) r0)
            r0 = 25
            r1[r0] = r2
            java.lang.String r0 = "RecyclerTransform"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "Invaild transform expression:"
            r6.append(r8)
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r0, (java.lang.String) r6)
            r0 = 26
            r1[r0] = r2
        L_0x0155:
            r0 = 27
            r1[r0] = r2
            goto L_0x0020
        L_0x015b:
            com.taobao.weex.ui.view.listview.adapter.TransformItemDecoration r0 = new com.taobao.weex.ui.view.listview.adapter.TransformItemDecoration
            r3 = r16
            if (r3 != r2) goto L_0x0167
            r3 = 28
            r1[r3] = r2
            r8 = 1
            goto L_0x016c
        L_0x0167:
            r3 = 29
            r1[r3] = r2
            r8 = 0
        L_0x016c:
            r7 = r0
            r7.<init>(r8, r9, r10, r11, r12, r13, r14)
            r3 = 30
            r1[r3] = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.ui.component.list.RecyclerTransform.parseTransforms(int, java.lang.String):android.support.v7.widget.RecyclerView$ItemDecoration");
    }
}
