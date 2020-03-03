package org.reactnative.barcodedetector;

import android.util.SparseArray;
import com.google.android.gms.stats.netstats.NetstatsParserPatterns;
import com.mics.core.data.request.SendText;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BarcodeFormatUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final SparseArray<String> f4119a;
    public static final Map<String, Integer> b;
    private static final String c = "UNKNOWN_FORMAT";
    private static final int d = -1;

    static {
        SparseArray<String> sparseArray = new SparseArray<>();
        sparseArray.put(1, "CODE_128");
        sparseArray.put(2, "CODE_39");
        sparseArray.put(4, "CODE_93");
        sparseArray.put(8, "CODABAR");
        sparseArray.put(16, "DATA_MATRIX");
        sparseArray.put(32, "EAN_13");
        sparseArray.put(64, "EAN_8");
        sparseArray.put(128, "ITF");
        sparseArray.put(256, "QR_CODE");
        sparseArray.put(512, "UPC_A");
        sparseArray.put(1024, "UPC_E");
        sparseArray.put(2048, "PDF417");
        sparseArray.put(4096, "AZTEC");
        sparseArray.put(0, NetstatsParserPatterns.TYPE_BOTH_PATTERN);
        sparseArray.put(11, "CALENDAR_EVENT");
        sparseArray.put(1, "CONTACT_INFO");
        sparseArray.put(12, "DRIVER_LICENSE");
        sparseArray.put(2, "EMAIL");
        sparseArray.put(10, "GEO");
        sparseArray.put(3, "ISBN");
        sparseArray.put(4, "PHONE");
        sparseArray.put(5, "PRODUCT");
        sparseArray.put(6, "SMS");
        sparseArray.put(7, SendText.TEXT);
        sparseArray.put(512, "UPC_A");
        sparseArray.put(8, DTransferConstants.f);
        sparseArray.put(9, "WIFI");
        sparseArray.put(-1, "None");
        f4119a = sparseArray;
        HashMap hashMap = new HashMap();
        for (int i = 0; i < sparseArray.size(); i++) {
            hashMap.put(sparseArray.valueAt(i), Integer.valueOf(sparseArray.keyAt(i)));
        }
        b = Collections.unmodifiableMap(hashMap);
    }

    public static String a(int i) {
        return f4119a.get(i, c);
    }

    public static int a(String str) {
        if (b.containsKey(str)) {
            return b.get(str).intValue();
        }
        return -1;
    }
}
