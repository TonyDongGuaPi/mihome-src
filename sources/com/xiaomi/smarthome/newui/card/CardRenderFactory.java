package com.xiaomi.smarthome.newui.card;

public class CardRenderFactory {

    /* renamed from: a  reason: collision with root package name */
    public static final int f20476a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    public static final int i = 8;
    public static final int j = 9;
    public static final int k = -1;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: com.xiaomi.smarthome.newui.card.CardRender_NotSupport} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: com.xiaomi.smarthome.newui.card.CardRender_NotSupport} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v23, resolved type: com.xiaomi.smarthome.newui.card.CardRender_NotSupport} */
    /* JADX WARNING: type inference failed for: r7v3 */
    /* JADX WARNING: type inference failed for: r1v24, types: [com.xiaomi.smarthome.newui.card.CardRender_1_item] */
    /* JADX WARNING: type inference failed for: r1v25, types: [com.xiaomi.smarthome.newui.card.CardRender_2_item_horizontal2] */
    /* JADX WARNING: type inference failed for: r1v26, types: [com.xiaomi.smarthome.newui.card.CardRender_2_item_horizontal] */
    /* JADX WARNING: type inference failed for: r1v27, types: [com.xiaomi.smarthome.newui.card.CardRender_2_item_title_operation] */
    /* JADX WARNING: type inference failed for: r1v28, types: [com.xiaomi.smarthome.newui.card.CardRender_3_item_horizontal] */
    /* JADX WARNING: type inference failed for: r1v29, types: [com.xiaomi.smarthome.newui.card.CardRender_3_item_vertical] */
    /* JADX WARNING: type inference failed for: r1v30, types: [com.xiaomi.smarthome.newui.card.CardRender_2_item_vertical] */
    /* JADX WARNING: type inference failed for: r1v31, types: [com.xiaomi.smarthome.newui.card.CardRender_3button_status_horizontal] */
    /* JADX WARNING: type inference failed for: r1v32, types: [com.xiaomi.smarthome.newui.card.CardRender_ir] */
    /* JADX WARNING: type inference failed for: r1v33, types: [com.xiaomi.smarthome.newui.card.CardRender_mitv] */
    /* JADX WARNING: type inference failed for: r1v34, types: [com.xiaomi.smarthome.newui.card.CardRender_NotSupport] */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.xiaomi.smarthome.newui.card.BaseCardRender<? extends com.xiaomi.smarthome.newui.card.Card, ? extends com.xiaomi.smarthome.newui.card.Card.CardType, ?> a(com.xiaomi.smarthome.newui.card.Card<? extends com.xiaomi.smarthome.newui.card.Card.CardType> r8, android.view.ViewGroup r9, android.content.Context r10, com.xiaomi.smarthome.device.Device r11) {
        /*
            if (r8 == 0) goto L_0x011c
            java.util.List<T> r0 = r8.c
            int r0 = r0.size()
            if (r0 != 0) goto L_0x000c
            goto L_0x011c
        L_0x000c:
            java.util.ArrayList r0 = r8.a(r11)
            java.util.Iterator r1 = r0.iterator()     // Catch:{ Exception -> 0x00f5 }
        L_0x0014:
            boolean r2 = r1.hasNext()     // Catch:{ Exception -> 0x00f5 }
            if (r2 == 0) goto L_0x0030
            java.lang.Object r2 = r1.next()     // Catch:{ Exception -> 0x00f5 }
            com.xiaomi.smarthome.newui.card.CardItem r2 = (com.xiaomi.smarthome.newui.card.CardItem) r2     // Catch:{ Exception -> 0x00f5 }
            boolean r2 = r2 instanceof com.xiaomi.smarthome.newui.card.carditem.NotSupportCardItem     // Catch:{ Exception -> 0x00f5 }
            if (r2 == 0) goto L_0x0014
            com.xiaomi.smarthome.newui.card.CardRender_NotSupport r7 = new com.xiaomi.smarthome.newui.card.CardRender_NotSupport     // Catch:{ Exception -> 0x00f5 }
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r9
            r5 = r10
            r6 = r11
            r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x00f5 }
            return r7
        L_0x0030:
            boolean r1 = r8 instanceof com.xiaomi.smarthome.newui.card.profile.ProfileCard     // Catch:{ Exception -> 0x00f5 }
            if (r1 == 0) goto L_0x0064
            r1 = r8
            com.xiaomi.smarthome.newui.card.profile.ProfileCard r1 = (com.xiaomi.smarthome.newui.card.profile.ProfileCard) r1     // Catch:{ Exception -> 0x00f5 }
            java.lang.String r1 = r1.e     // Catch:{ Exception -> 0x00f5 }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x00f5 }
            if (r2 != 0) goto L_0x0064
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x00f5 }
            java.lang.String r3 = r11.extra     // Catch:{ Exception -> 0x00f5 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x00f5 }
            java.lang.String r3 = "fw_version"
            java.lang.String r2 = r2.optString(r3)     // Catch:{ Exception -> 0x00f5 }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x00f5 }
            if (r3 != 0) goto L_0x0064
            int r1 = com.xiaomi.smarthome.device.DeviceUtils.a((java.lang.String) r2, (java.lang.String) r1)     // Catch:{ Exception -> 0x00f5 }
            if (r1 >= 0) goto L_0x0064
            com.xiaomi.smarthome.newui.card.CardRender_NotSupport r7 = new com.xiaomi.smarthome.newui.card.CardRender_NotSupport     // Catch:{ Exception -> 0x00f5 }
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r9
            r5 = r10
            r6 = r11
            r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x00f5 }
            return r7
        L_0x0064:
            int r1 = r8.b     // Catch:{ Exception -> 0x00f5 }
            switch(r1) {
                case 0: goto L_0x00df;
                case 1: goto L_0x00d3;
                case 2: goto L_0x00c7;
                case 3: goto L_0x00bb;
                case 4: goto L_0x00ae;
                case 5: goto L_0x00a1;
                case 6: goto L_0x0094;
                case 7: goto L_0x0087;
                case 8: goto L_0x007a;
                case 9: goto L_0x006d;
                default: goto L_0x0069;
            }     // Catch:{ Exception -> 0x00f5 }
        L_0x0069:
            com.xiaomi.smarthome.newui.card.CardRender_NotSupport r7 = new com.xiaomi.smarthome.newui.card.CardRender_NotSupport     // Catch:{ Exception -> 0x00f5 }
            goto L_0x00eb
        L_0x006d:
            com.xiaomi.smarthome.newui.card.CardRender_mitv r7 = new com.xiaomi.smarthome.newui.card.CardRender_mitv     // Catch:{ Exception -> 0x00f5 }
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r9
            r5 = r10
            r6 = r11
            r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x00f5 }
            goto L_0x00fa
        L_0x007a:
            com.xiaomi.smarthome.newui.card.CardRender_ir r7 = new com.xiaomi.smarthome.newui.card.CardRender_ir     // Catch:{ Exception -> 0x00f5 }
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r9
            r5 = r10
            r6 = r11
            r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x00f5 }
            goto L_0x00fa
        L_0x0087:
            com.xiaomi.smarthome.newui.card.CardRender_3button_status_horizontal r7 = new com.xiaomi.smarthome.newui.card.CardRender_3button_status_horizontal     // Catch:{ Exception -> 0x00f5 }
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r9
            r5 = r10
            r6 = r11
            r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x00f5 }
            goto L_0x00fa
        L_0x0094:
            com.xiaomi.smarthome.newui.card.CardRender_2_item_vertical r7 = new com.xiaomi.smarthome.newui.card.CardRender_2_item_vertical     // Catch:{ Exception -> 0x00f5 }
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r9
            r5 = r10
            r6 = r11
            r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x00f5 }
            goto L_0x00fa
        L_0x00a1:
            com.xiaomi.smarthome.newui.card.CardRender_3_item_vertical r7 = new com.xiaomi.smarthome.newui.card.CardRender_3_item_vertical     // Catch:{ Exception -> 0x00f5 }
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r9
            r5 = r10
            r6 = r11
            r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x00f5 }
            goto L_0x00fa
        L_0x00ae:
            com.xiaomi.smarthome.newui.card.CardRender_3_item_horizontal r7 = new com.xiaomi.smarthome.newui.card.CardRender_3_item_horizontal     // Catch:{ Exception -> 0x00f5 }
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r9
            r5 = r10
            r6 = r11
            r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x00f5 }
            goto L_0x00fa
        L_0x00bb:
            com.xiaomi.smarthome.newui.card.CardRender_2_item_title_operation r7 = new com.xiaomi.smarthome.newui.card.CardRender_2_item_title_operation     // Catch:{ Exception -> 0x00f5 }
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r9
            r5 = r10
            r6 = r11
            r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x00f5 }
            goto L_0x00fa
        L_0x00c7:
            com.xiaomi.smarthome.newui.card.CardRender_2_item_horizontal r7 = new com.xiaomi.smarthome.newui.card.CardRender_2_item_horizontal     // Catch:{ Exception -> 0x00f5 }
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r9
            r5 = r10
            r6 = r11
            r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x00f5 }
            goto L_0x00fa
        L_0x00d3:
            com.xiaomi.smarthome.newui.card.CardRender_2_item_horizontal2 r7 = new com.xiaomi.smarthome.newui.card.CardRender_2_item_horizontal2     // Catch:{ Exception -> 0x00f5 }
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r9
            r5 = r10
            r6 = r11
            r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x00f5 }
            goto L_0x00fa
        L_0x00df:
            com.xiaomi.smarthome.newui.card.CardRender_1_item r7 = new com.xiaomi.smarthome.newui.card.CardRender_1_item     // Catch:{ Exception -> 0x00f5 }
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r9
            r5 = r10
            r6 = r11
            r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x00f5 }
            goto L_0x00fa
        L_0x00eb:
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r9
            r5 = r10
            r6 = r11
            r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x00f5 }
            goto L_0x00fa
        L_0x00f5:
            r1 = move-exception
            r1.printStackTrace()
            r7 = 0
        L_0x00fa:
            if (r7 != 0) goto L_0x0107
            com.xiaomi.smarthome.newui.card.CardRender_NotSupport r7 = new com.xiaomi.smarthome.newui.card.CardRender_NotSupport
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r9
            r5 = r10
            r6 = r11
            r1.<init>(r2, r3, r4, r5, r6)
        L_0x0107:
            java.util.Iterator r8 = r0.iterator()
        L_0x010b:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x011b
            java.lang.Object r9 = r8.next()
            com.xiaomi.smarthome.newui.card.CardItem r9 = (com.xiaomi.smarthome.newui.card.CardItem) r9
            r9.a((com.xiaomi.smarthome.newui.card.BaseCardRender) r7)
            goto L_0x010b
        L_0x011b:
            return r7
        L_0x011c:
            com.xiaomi.smarthome.newui.card.CardRender_NotSupport r6 = new com.xiaomi.smarthome.newui.card.CardRender_NotSupport
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r0 = r6
            r1 = r8
            r3 = r9
            r4 = r10
            r5 = r11
            r0.<init>(r1, r2, r3, r4, r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.card.CardRenderFactory.a(com.xiaomi.smarthome.newui.card.Card, android.view.ViewGroup, android.content.Context, com.xiaomi.smarthome.device.Device):com.xiaomi.smarthome.newui.card.BaseCardRender");
    }
}
