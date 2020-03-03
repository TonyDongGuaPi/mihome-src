package com.tencent.smtt.sdk;

import com.tencent.smtt.sdk.TbsReaderView;

class ba implements TbsReaderView.ReaderCallback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TbsReaderView f9155a;

    ba(TbsReaderView tbsReaderView) {
        this.f9155a = tbsReaderView;
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x0189 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCallBackAction(java.lang.Integer r12, java.lang.Object r13, java.lang.Object r14) {
        /*
            r11 = this;
            int r0 = r12.intValue()
            r1 = 5026(0x13a2, float:7.043E-42)
            r2 = 0
            r3 = 5011(0x1393, float:7.022E-42)
            r4 = 1
            r5 = 0
            if (r0 == r1) goto L_0x012f
            r1 = 5030(0x13a6, float:7.049E-42)
            if (r0 == r1) goto L_0x0119
            switch(r0) {
                case 5008: goto L_0x00bd;
                case 5009: goto L_0x0069;
                case 5010: goto L_0x0017;
                default: goto L_0x0014;
            }
        L_0x0014:
            r4 = 0
            goto L_0x0183
        L_0x0017:
            com.tencent.smtt.sdk.TbsReaderView r0 = r11.f9155a
            android.content.Context r0 = r0.f9101a
            boolean r0 = com.tencent.smtt.sdk.a.d.c(r0)
            if (r0 != 0) goto L_0x004d
            java.lang.Integer r12 = java.lang.Integer.valueOf(r3)
            com.tencent.smtt.sdk.TbsReaderView r13 = r11.f9155a
            android.content.Context r13 = r13.f9101a
            r0 = 5022(0x139e, float:7.037E-42)
            java.lang.String r13 = com.tencent.smtt.sdk.TbsReaderView.getResString(r13, r0)
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            java.lang.String r1 = "tip"
            r0.putString(r1, r13)
            java.lang.String r13 = "statistics"
            java.lang.String r1 = "AHNG816"
            r0.putString(r13, r1)
            java.lang.String r13 = "channel_id"
            r1 = 10835(0x2a53, float:1.5183E-41)
            r0.putInt(r13, r1)
            com.tencent.smtt.sdk.TbsReaderView r13 = r11.f9155a
            java.lang.String r1 = "AHNG815"
            goto L_0x00f1
        L_0x004d:
            java.lang.String r0 = ""
            if (r13 == 0) goto L_0x005a
            r2 = r13
            android.os.Bundle r2 = (android.os.Bundle) r2
            java.lang.String r0 = "docpath"
            java.lang.String r0 = r2.getString(r0)
        L_0x005a:
            r6 = r0
            r10 = r2
            com.tencent.smtt.sdk.TbsReaderView r0 = r11.f9155a
            android.content.Context r5 = r0.f9101a
            r7 = 4
            r8 = 0
            java.lang.String r9 = "txt"
            com.tencent.smtt.sdk.QbSdk.startQBForDoc(r5, r6, r7, r8, r9, r10)
            goto L_0x0183
        L_0x0069:
            com.tencent.smtt.sdk.TbsReaderView r0 = r11.f9155a
            android.content.Context r0 = r0.f9101a
            boolean r0 = com.tencent.smtt.sdk.a.d.c(r0)
            if (r0 != 0) goto L_0x009e
            java.lang.Integer r12 = java.lang.Integer.valueOf(r3)
            com.tencent.smtt.sdk.TbsReaderView r13 = r11.f9155a
            android.content.Context r13 = r13.f9101a
            r0 = 5021(0x139d, float:7.036E-42)
            java.lang.String r13 = com.tencent.smtt.sdk.TbsReaderView.getResString(r13, r0)
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            java.lang.String r1 = "tip"
            r0.putString(r1, r13)
            java.lang.String r13 = "statistics"
            java.lang.String r1 = "AHNG808"
            r0.putString(r13, r1)
            java.lang.String r13 = "channel_id"
            r1 = 10833(0x2a51, float:1.518E-41)
            r0.putInt(r13, r1)
            com.tencent.smtt.sdk.TbsReaderView r13 = r11.f9155a
            java.lang.String r1 = "AHNG807"
            goto L_0x00f1
        L_0x009e:
            java.lang.String r0 = ""
            if (r13 == 0) goto L_0x00ab
            r2 = r13
            android.os.Bundle r2 = (android.os.Bundle) r2
            java.lang.String r0 = "docpath"
            java.lang.String r0 = r2.getString(r0)
        L_0x00ab:
            r6 = r0
            r10 = r2
            com.tencent.smtt.sdk.TbsReaderView r0 = r11.f9155a
            android.content.Context r5 = r0.f9101a
            r7 = 4
            r8 = 0
            java.lang.String r9 = ""
            com.tencent.smtt.sdk.QbSdk.startQBForDoc(r5, r6, r7, r8, r9, r10)
            com.tencent.smtt.sdk.TbsReaderView r0 = r11.f9155a
            java.lang.String r1 = "AHNG809"
            goto L_0x0115
        L_0x00bd:
            com.tencent.smtt.sdk.TbsReaderView r0 = r11.f9155a
            android.content.Context r0 = r0.f9101a
            boolean r0 = com.tencent.smtt.sdk.a.d.c(r0)
            if (r0 != 0) goto L_0x00f7
            java.lang.Integer r12 = java.lang.Integer.valueOf(r3)
            com.tencent.smtt.sdk.TbsReaderView r13 = r11.f9155a
            android.content.Context r13 = r13.f9101a
            r0 = 5023(0x139f, float:7.039E-42)
            java.lang.String r13 = com.tencent.smtt.sdk.TbsReaderView.getResString(r13, r0)
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            java.lang.String r1 = "tip"
            r0.putString(r1, r13)
            java.lang.String r13 = "statistics"
            java.lang.String r1 = "AHNG812"
            r0.putString(r13, r1)
            java.lang.String r13 = "channel_id"
            r1 = 10834(0x2a52, float:1.5182E-41)
            r0.putInt(r13, r1)
            com.tencent.smtt.sdk.TbsReaderView r13 = r11.f9155a
            java.lang.String r1 = "AHNG811"
        L_0x00f1:
            r13.userStatistics(r1)
            r13 = r0
            goto L_0x0014
        L_0x00f7:
            java.lang.String r0 = ""
            if (r13 == 0) goto L_0x0104
            r2 = r13
            android.os.Bundle r2 = (android.os.Bundle) r2
            java.lang.String r0 = "docpath"
            java.lang.String r0 = r2.getString(r0)
        L_0x0104:
            r6 = r0
            r10 = r2
            com.tencent.smtt.sdk.TbsReaderView r0 = r11.f9155a
            android.content.Context r5 = r0.f9101a
            r7 = 4
            r8 = 0
            java.lang.String r9 = "pdf"
            com.tencent.smtt.sdk.QbSdk.startQBForDoc(r5, r6, r7, r8, r9, r10)
            com.tencent.smtt.sdk.TbsReaderView r0 = r11.f9155a
            java.lang.String r1 = "AHNG813"
        L_0x0115:
            r0.userStatistics(r1)
            goto L_0x0183
        L_0x0119:
            if (r13 == 0) goto L_0x0183
            r0 = r13
            android.os.Bundle r0 = (android.os.Bundle) r0
            java.lang.String r1 = "name"
            java.lang.String r1 = r0.getString(r1)
            com.tencent.smtt.sdk.TbsReaderView.gReaderPackName = r1
            java.lang.String r1 = "version"
            java.lang.String r0 = r0.getString(r1)
            com.tencent.smtt.sdk.TbsReaderView.gReaderPackVersion = r0
            goto L_0x0183
        L_0x012f:
            com.tencent.smtt.sdk.TbsReaderView r0 = r11.f9155a
            android.content.Context r0 = r0.f9101a
            boolean r0 = com.tencent.smtt.sdk.a.d.c(r0)
            if (r0 != 0) goto L_0x0164
            java.lang.Integer r12 = java.lang.Integer.valueOf(r3)
            com.tencent.smtt.sdk.TbsReaderView r13 = r11.f9155a
            android.content.Context r13 = r13.f9101a
            r0 = 5029(0x13a5, float:7.047E-42)
            java.lang.String r13 = com.tencent.smtt.sdk.TbsReaderView.getResString(r13, r0)
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            java.lang.String r1 = "tip"
            r0.putString(r1, r13)
            java.lang.String r13 = "statistics"
            java.lang.String r1 = "AHNG828"
            r0.putString(r13, r1)
            java.lang.String r13 = "channel_id"
            r1 = 10965(0x2ad5, float:1.5365E-41)
            r0.putInt(r13, r1)
            com.tencent.smtt.sdk.TbsReaderView r13 = r11.f9155a
            java.lang.String r1 = "AHNG827"
            goto L_0x00f1
        L_0x0164:
            java.lang.String r0 = ""
            if (r13 == 0) goto L_0x0171
            r2 = r13
            android.os.Bundle r2 = (android.os.Bundle) r2
            java.lang.String r0 = "docpath"
            java.lang.String r0 = r2.getString(r0)
        L_0x0171:
            r6 = r0
            r10 = r2
            com.tencent.smtt.sdk.TbsReaderView r0 = r11.f9155a
            android.content.Context r5 = r0.f9101a
            r7 = 4
            r8 = 0
            java.lang.String r9 = "doc"
            com.tencent.smtt.sdk.QbSdk.startQBForDoc(r5, r6, r7, r8, r9, r10)
            com.tencent.smtt.sdk.TbsReaderView r0 = r11.f9155a
            java.lang.String r1 = "AHNG829"
            goto L_0x0115
        L_0x0183:
            com.tencent.smtt.sdk.TbsReaderView r0 = r11.f9155a
            com.tencent.smtt.sdk.TbsReaderView$ReaderCallback r0 = r0.d
            if (r0 == 0) goto L_0x0192
            if (r4 != 0) goto L_0x0192
            com.tencent.smtt.sdk.TbsReaderView r0 = r11.f9155a
            com.tencent.smtt.sdk.TbsReaderView$ReaderCallback r0 = r0.d
            r0.onCallBackAction(r12, r13, r14)
        L_0x0192:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.ba.onCallBackAction(java.lang.Integer, java.lang.Object, java.lang.Object):void");
    }
}
