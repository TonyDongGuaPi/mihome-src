package com.xiaomi.qrcode2;

import android.content.Intent;
import android.net.Uri;
import com.xiaomi.zxing.BarcodeFormat;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

final class DecodeFormatManager {

    /* renamed from: a  reason: collision with root package name */
    static final Set<BarcodeFormat> f13000a = EnumSet.of(BarcodeFormat.UPC_A, new BarcodeFormat[]{BarcodeFormat.UPC_E, BarcodeFormat.EAN_13, BarcodeFormat.EAN_8, BarcodeFormat.RSS_14, BarcodeFormat.RSS_EXPANDED});
    static final Set<BarcodeFormat> b = EnumSet.of(BarcodeFormat.CODE_39, BarcodeFormat.CODE_93, BarcodeFormat.CODE_128, BarcodeFormat.ITF, BarcodeFormat.CODABAR);
    static final Set<BarcodeFormat> c = EnumSet.of(BarcodeFormat.QR_CODE);
    static final Set<BarcodeFormat> d = EnumSet.of(BarcodeFormat.DATA_MATRIX);
    static final Set<BarcodeFormat> e = EnumSet.of(BarcodeFormat.AZTEC);
    static final Set<BarcodeFormat> f = EnumSet.of(BarcodeFormat.PDF_417);
    private static final Pattern g = Pattern.compile(",");
    private static final Set<BarcodeFormat> h = EnumSet.copyOf(f13000a);
    private static final Map<String, Set<BarcodeFormat>> i = new HashMap();

    static {
        h.addAll(b);
        i.put("ONE_D_MODE", h);
        i.put("PRODUCT_MODE", f13000a);
        i.put("QR_CODE_MODE", c);
        i.put("DATA_MATRIX_MODE", d);
        i.put("AZTEC_MODE", e);
        i.put("PDF417_MODE", f);
    }

    private DecodeFormatManager() {
    }

    static Set<BarcodeFormat> a(Intent intent) {
        String stringExtra = intent.getStringExtra("SCAN_FORMATS");
        return a(stringExtra != null ? Arrays.asList(g.split(stringExtra)) : null, intent.getStringExtra("SCAN_MODE"));
    }

    static Set<BarcodeFormat> a(Uri uri) {
        List<String> queryParameters = uri.getQueryParameters("SCAN_FORMATS");
        if (!(queryParameters == null || queryParameters.size() != 1 || queryParameters.get(0) == null)) {
            queryParameters = Arrays.asList(g.split(queryParameters.get(0)));
        }
        return a(queryParameters, uri.getQueryParameter("SCAN_MODE"));
    }

    private static Set<BarcodeFormat> a(Iterable<String> iterable, String str) {
        if (iterable != null) {
            EnumSet<E> noneOf = EnumSet.noneOf(BarcodeFormat.class);
            try {
                for (String valueOf : iterable) {
                    noneOf.add(BarcodeFormat.valueOf(valueOf));
                }
                return noneOf;
            } catch (IllegalArgumentException unused) {
            }
        }
        if (str != null) {
            return i.get(str);
        }
        return null;
    }
}
