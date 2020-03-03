package com.unionpay.mobile.android.widgets;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.widgets.u;
import java.math.BigDecimal;

public class bd extends aa implements u.a {
    private static final String c = "bd";
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;

    public class a implements InputFilter {
        private u b = null;
        private BigDecimal c = null;
        private BigDecimal d = null;

        public a(u uVar, BigDecimal bigDecimal, BigDecimal bigDecimal2) {
            this.b = uVar;
            this.c = bigDecimal;
            this.d = bigDecimal2;
            if (this.c.compareTo(BigDecimal.ZERO) == 1) {
                c(this.c.toString());
            }
        }

        private static int a(String str) {
            if (str == null || !b(str)) {
                return 0;
            }
            return (str.length() - str.indexOf(".")) - 1;
        }

        private CharSequence a(String str, boolean z, boolean z2) {
            String bigDecimal = this.d.toString();
            String bigDecimal2 = this.c.toString();
            try {
                BigDecimal bigDecimal3 = new BigDecimal(str);
                bigDecimal3.setScale(2, 6);
                if (z && z2 && bigDecimal3.compareTo(this.c) == -1) {
                    c(bigDecimal2);
                }
                if (bigDecimal3.compareTo(this.d) != 1) {
                    return null;
                }
                c(bigDecimal);
                return null;
            } catch (Exception unused) {
                return "";
            }
        }

        private static boolean b(String str) {
            return str != null && str.contains(".");
        }

        private void c(String str) {
            this.b.c(str);
            Selection.setSelection(this.b.c(), this.b.b().length());
        }

        public final CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            boolean z = true;
            boolean z2 = false;
            if (charSequence == null || !charSequence.equals("")) {
                int i5 = i2 - i;
                boolean b2 = b(spanned.toString());
                if (1 != i5) {
                    boolean b3 = b(charSequence.toString());
                    if (b3 && b2) {
                        return "";
                    }
                    if (b3 && (a(charSequence.toString()) > 2 || spanned.toString().length() != i4)) {
                        return "";
                    }
                } else if (!b2 && spanned.length() == 1 && spanned.charAt(0) == '0') {
                    return ".";
                } else {
                    if ('0' == charSequence.charAt(0) && i3 == 0 && i4 == 0 && spanned.length() != 0) {
                        return "";
                    }
                    if ('.' == charSequence.charAt(0)) {
                        if (spanned.length() == 0) {
                            return "";
                        }
                        if (spanned.length() != 0 && spanned.length() - i3 > 2) {
                            return "";
                        }
                    }
                    if (b2 && i3 > spanned.toString().indexOf(".") && a(spanned.toString()) > 2 - i5) {
                        return "";
                    }
                }
                String str = spanned.subSequence(0, i3).toString() + charSequence.subSequence(i, i2).toString() + spanned.subSequence(i4, spanned.length());
                if (a(str) != 2) {
                    z = false;
                }
                return a(str, false, z);
            }
            String str2 = spanned.subSequence(0, i3).toString() + charSequence.subSequence(i, i2).toString() + spanned.subSequence(i4, spanned.length());
            if (i4 - i3 != 1) {
                z2 = true;
            }
            return a(str2, true, z2);
        }
    }

    /* JADX WARNING: type inference failed for: r4v1, types: [java.math.BigDecimal, java.lang.String] */
    /* JADX WARNING: Can't wrap try/catch for region: R(13:0|1|2|4|5|6|7|8|9|13|(1:17)|18|19) */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:11|12) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0066, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r0 = new java.math.BigDecimal(3.4028234663852886E38d);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00b2, code lost:
        r4.setScale(2, 6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00b5, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00b6, code lost:
        r4.setScale(2, 6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00b9, code lost:
        throw r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0052, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0068 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0054 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public bd(android.content.Context r4, int r5, org.json.JSONObject r6, java.lang.String r7) {
        /*
            r3 = this;
            r3.<init>(r4, r5, r6, r7)
            r4 = 0
            r3.o = r4
            r3.p = r4
            r3.q = r4
            r3.r = r4
            r3.s = r4
            java.lang.String r5 = "point"
            java.lang.String r5 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r6, (java.lang.String) r5)
            r3.r = r5
            java.lang.String r5 = "max_use_point"
            java.lang.String r5 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r6, (java.lang.String) r5)
            r3.q = r5
            java.lang.String r5 = "min_use_point"
            java.lang.String r5 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r6, (java.lang.String) r5)
            r3.p = r5
            java.lang.String r5 = "ratio"
            java.lang.String r5 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r6, (java.lang.String) r5)
            r3.s = r5
            java.lang.String r5 = "ordr_amnt"
            java.lang.String r5 = com.unionpay.mobile.android.utils.j.a((org.json.JSONObject) r6, (java.lang.String) r5)
            r3.o = r5
            com.unionpay.mobile.android.widgets.u r5 = r3.b
            r6 = 8194(0x2002, float:1.1482E-41)
            r5.a((int) r6)
            com.unionpay.mobile.android.widgets.u r5 = r3.b
            java.lang.String r6 = "0123456789."
            android.text.method.DigitsKeyListener r6 = android.text.method.DigitsKeyListener.getInstance(r6)
            r5.a((android.text.InputFilter) r6)
            r5 = 6
            r6 = 2
            java.math.BigDecimal r7 = new java.math.BigDecimal     // Catch:{ Exception -> 0x0054 }
            java.lang.String r0 = r3.p     // Catch:{ Exception -> 0x0054 }
            r7.<init>(r0)     // Catch:{ Exception -> 0x0054 }
            goto L_0x0056
        L_0x0052:
            r7 = move-exception
            goto L_0x00b6
        L_0x0054:
            java.math.BigDecimal r7 = java.math.BigDecimal.ZERO     // Catch:{ all -> 0x0052 }
        L_0x0056:
            java.math.BigDecimal r7 = r7.setScale(r6, r5)
            java.math.BigDecimal r0 = new java.math.BigDecimal     // Catch:{ Exception -> 0x0068 }
            java.lang.String r1 = r3.q     // Catch:{ Exception -> 0x0068 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x0068 }
        L_0x0061:
            java.math.BigDecimal r5 = r0.setScale(r6, r5)
            goto L_0x0073
        L_0x0066:
            r7 = move-exception
            goto L_0x00b2
        L_0x0068:
            java.math.BigDecimal r0 = new java.math.BigDecimal     // Catch:{ all -> 0x0066 }
            r1 = 5183643170566569984(0x47efffffe0000000, double:3.4028234663852886E38)
            r0.<init>(r1)     // Catch:{ all -> 0x0066 }
            goto L_0x0061
        L_0x0073:
            com.unionpay.mobile.android.widgets.u r0 = r3.b
            com.unionpay.mobile.android.widgets.bd$a r1 = new com.unionpay.mobile.android.widgets.bd$a
            com.unionpay.mobile.android.widgets.u r2 = r3.b
            r1.<init>(r2, r7, r5)
            r0.a((android.text.InputFilter) r1)
            r3.a(r4, r4)
            java.lang.String r4 = r3.q()
            if (r4 == 0) goto L_0x0092
            java.lang.String r4 = r3.q()
            int r4 = r4.length()
            if (r4 != 0) goto L_0x00ac
        L_0x0092:
            r3.u()
            com.unionpay.mobile.android.languages.c r4 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r4 = r4.ay
            java.lang.Object[] r5 = new java.lang.Object[r6]
            r6 = 0
            java.lang.String r7 = r3.r
            r5[r6] = r7
            r6 = 1
            java.lang.String r7 = r3.s
            r5[r6] = r7
            java.lang.String r4 = java.lang.String.format(r4, r5)
            r3.c(r4)
        L_0x00ac:
            com.unionpay.mobile.android.widgets.u r4 = r3.b
            r4.a((com.unionpay.mobile.android.widgets.u.a) r3)
            return
        L_0x00b2:
            r4.setScale(r6, r5)
            throw r7
        L_0x00b6:
            r4.setScale(r6, r5)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.widgets.bd.<init>(android.content.Context, int, org.json.JSONObject, java.lang.String):void");
    }

    private void a(String str, String str2) {
        SpannableString spannableString;
        if (p() == null || p().length() == 0) {
            t();
            if (str == null) {
                String format = String.format(c.bD.aw, new Object[]{this.q});
                spannableString = new SpannableString(format);
                spannableString.setSpan(new ForegroundColorSpan(-15958150), 0, format.length(), 0);
            } else {
                String format2 = String.format(c.bD.aw + str, new Object[]{this.q, str2});
                SpannableString spannableString2 = new SpannableString(format2);
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(-15958150);
                int length = format2.length();
                spannableString2.setSpan(foregroundColorSpan, 0, length - (str2 + "元").length(), 0);
                ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(-44462);
                int length2 = format2.length();
                spannableString2.setSpan(foregroundColorSpan2, length2 - (str2 + "元").length(), format2.length(), 0);
                spannableString = spannableString2;
            }
            v();
            a((CharSequence) spannableString, TextView.BufferType.SPANNABLE);
        }
    }

    public final String a() {
        String a2 = super.a();
        if (!(a2 == null || a2.length() == 0)) {
            try {
                BigDecimal bigDecimal = new BigDecimal(a2);
                bigDecimal.setScale(2, 6);
                if (bigDecimal.compareTo(BigDecimal.ZERO) == 1) {
                    return bigDecimal.toString();
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public final void a(Editable editable) {
        super.a(editable);
        if (p() == null || p().length() == 0) {
            try {
                a(c.bD.ax, new BigDecimal(this.o).setScale(2, 6).subtract(new BigDecimal(a()).setScale(2, 6).multiply(new BigDecimal(this.s).setScale(2, 6)).setScale(2, 6)).toString());
            } catch (Exception unused) {
                a((String) null, (String) null);
            }
        }
    }

    public final void a(boolean z) {
        if (z) {
            a((View) this.l);
        }
    }

    public final boolean b() {
        return true;
    }

    public final boolean c() {
        return true;
    }

    public final String h() {
        String a2 = a();
        if (a2 == null || a2.length() == 0) {
            return null;
        }
        return super.h();
    }
}
