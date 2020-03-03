package org.apache.commons.lang;

import android.support.media.ExifInterface;
import com.facebook.appevents.UserDataStore;
import com.miui.tsmclient.net.TSMAuthContants;
import com.unionpay.tsmservice.data.Constant;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.bluetooth.XmBluetoothRecord;
import com.xiaomi.smarthome.framework.statistic.StatUtil;
import com.xiaomi.stat.a.j;
import com.xiaomi.stat.d;
import com.xiaomi.youpin.share.ShareRecordConstant;
import com.ximalaya.ting.android.opensdk.auth.constants.XmlyAuthErrorNoConstants;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class Entities {

    /* renamed from: a  reason: collision with root package name */
    static final String[][] f3361a = {new String[]{"nbsp", "160"}, new String[]{"iexcl", "161"}, new String[]{"cent", "162"}, new String[]{"pound", Constants.Plugin.PLUGINID_MISHOPSTAT}, new String[]{"curren", "164"}, new String[]{"yen", "165"}, new String[]{"brvbar", "166"}, new String[]{"sect", "167"}, new String[]{"uml", "168"}, new String[]{ShareRecordConstant.j, "169"}, new String[]{"ordf", "170"}, new String[]{"laquo", "171"}, new String[]{"not", "172"}, new String[]{"shy", "173"}, new String[]{"reg", "174"}, new String[]{"macr", "175"}, new String[]{"deg", "176"}, new String[]{"plusmn", "177"}, new String[]{"sup2", "178"}, new String[]{"sup3", "179"}, new String[]{"acute", "180"}, new String[]{StatUtil.g, "181"}, new String[]{"para", "182"}, new String[]{"middot", "183"}, new String[]{"cedil", "184"}, new String[]{"sup1", "185"}, new String[]{"ordm", "186"}, new String[]{"raquo", "187"}, new String[]{"frac14", "188"}, new String[]{"frac12", "189"}, new String[]{"frac34", "190"}, new String[]{"iquest", "191"}, new String[]{"Agrave", "192"}, new String[]{"Aacute", "193"}, new String[]{"Acirc", "194"}, new String[]{"Atilde", "195"}, new String[]{"Auml", "196"}, new String[]{"Aring", "197"}, new String[]{"AElig", "198"}, new String[]{"Ccedil", "199"}, new String[]{"Egrave", XmlyAuthErrorNoConstants.g}, new String[]{"Eacute", XmlyAuthErrorNoConstants.h}, new String[]{"Ecirc", XmlyAuthErrorNoConstants.i}, new String[]{"Euml", XmlyAuthErrorNoConstants.j}, new String[]{"Igrave", XmlyAuthErrorNoConstants.k}, new String[]{"Iacute", XmlyAuthErrorNoConstants.l}, new String[]{"Icirc", XmlyAuthErrorNoConstants.m}, new String[]{"Iuml", XmlyAuthErrorNoConstants.n}, new String[]{"ETH", XmlyAuthErrorNoConstants.p}, new String[]{"Ntilde", XmlyAuthErrorNoConstants.q}, new String[]{"Ograve", XmlyAuthErrorNoConstants.e}, new String[]{"Oacute", XmlyAuthErrorNoConstants.f}, new String[]{"Ocirc", XmlyAuthErrorNoConstants.o}, new String[]{"Otilde", "213"}, new String[]{"Ouml", "214"}, new String[]{"times", "215"}, new String[]{"Oslash", "216"}, new String[]{"Ugrave", "217"}, new String[]{"Uacute", "218"}, new String[]{"Ucirc", "219"}, new String[]{"Uuml", "220"}, new String[]{"Yacute", "221"}, new String[]{"THORN", "222"}, new String[]{"szlig", "223"}, new String[]{"agrave", "224"}, new String[]{"aacute", "225"}, new String[]{"acirc", "226"}, new String[]{"atilde", "227"}, new String[]{"auml", "228"}, new String[]{"aring", "229"}, new String[]{"aelig", "230"}, new String[]{"ccedil", "231"}, new String[]{"egrave", "232"}, new String[]{"eacute", "233"}, new String[]{"ecirc", "234"}, new String[]{"euml", "235"}, new String[]{"igrave", "236"}, new String[]{"iacute", "237"}, new String[]{"icirc", "238"}, new String[]{"iuml", "239"}, new String[]{"eth", "240"}, new String[]{"ntilde", "241"}, new String[]{"ograve", "242"}, new String[]{"oacute", "243"}, new String[]{"ocirc", "244"}, new String[]{"otilde", "245"}, new String[]{"ouml", "246"}, new String[]{"divide", "247"}, new String[]{"oslash", "248"}, new String[]{"ugrave", "249"}, new String[]{"uacute", "250"}, new String[]{"ucirc", "251"}, new String[]{"uuml", "252"}, new String[]{"yacute", "253"}, new String[]{"thorn", "254"}, new String[]{"yuml", "255"}};
    static final String[][] b = {new String[]{"fnof", "402"}, new String[]{"Alpha", "913"}, new String[]{"Beta", "914"}, new String[]{ExifInterface.TAG_GAMMA, "915"}, new String[]{"Delta", "916"}, new String[]{"Epsilon", "917"}, new String[]{"Zeta", "918"}, new String[]{"Eta", "919"}, new String[]{"Theta", "920"}, new String[]{"Iota", "921"}, new String[]{"Kappa", "922"}, new String[]{"Lambda", "923"}, new String[]{"Mu", "924"}, new String[]{"Nu", "925"}, new String[]{"Xi", "926"}, new String[]{"Omicron", "927"}, new String[]{"Pi", "928"}, new String[]{"Rho", "929"}, new String[]{"Sigma", "931"}, new String[]{"Tau", "932"}, new String[]{"Upsilon", "933"}, new String[]{"Phi", "934"}, new String[]{"Chi", "935"}, new String[]{"Psi", "936"}, new String[]{"Omega", "937"}, new String[]{"alpha", "945"}, new String[]{"beta", "946"}, new String[]{"gamma", "947"}, new String[]{com.xiaomi.jr.verification.Constants.h, "948"}, new String[]{"epsilon", "949"}, new String[]{"zeta", "950"}, new String[]{"eta", "951"}, new String[]{"theta", "952"}, new String[]{"iota", "953"}, new String[]{"kappa", "954"}, new String[]{"lambda", "955"}, new String[]{"mu", "956"}, new String[]{"nu", "957"}, new String[]{"xi", "958"}, new String[]{"omicron", "959"}, new String[]{"pi", "960"}, new String[]{"rho", "961"}, new String[]{"sigmaf", "962"}, new String[]{"sigma", "963"}, new String[]{"tau", "964"}, new String[]{"upsilon", "965"}, new String[]{"phi", "966"}, new String[]{"chi", "967"}, new String[]{"psi", "968"}, new String[]{"omega", "969"}, new String[]{"thetasym", "977"}, new String[]{"upsih", "978"}, new String[]{"piv", "982"}, new String[]{"bull", "8226"}, new String[]{"hellip", "8230"}, new String[]{"prime", "8242"}, new String[]{"Prime", "8243"}, new String[]{"oline", "8254"}, new String[]{"frasl", "8260"}, new String[]{"weierp", "8472"}, new String[]{"image", "8465"}, new String[]{"real", "8476"}, new String[]{"trade", "8482"}, new String[]{"alefsym", "8501"}, new String[]{"larr", "8592"}, new String[]{"uarr", "8593"}, new String[]{"rarr", "8594"}, new String[]{"darr", "8595"}, new String[]{"harr", "8596"}, new String[]{"crarr", "8629"}, new String[]{"lArr", "8656"}, new String[]{"uArr", "8657"}, new String[]{"rArr", "8658"}, new String[]{"dArr", "8659"}, new String[]{"hArr", "8660"}, new String[]{"forall", "8704"}, new String[]{"part", "8706"}, new String[]{"exist", "8707"}, new String[]{"empty", "8709"}, new String[]{"nabla", "8711"}, new String[]{"isin", "8712"}, new String[]{"notin", "8713"}, new String[]{"ni", "8715"}, new String[]{"prod", "8719"}, new String[]{"sum", "8721"}, new String[]{"minus", "8722"}, new String[]{"lowast", "8727"}, new String[]{"radic", "8730"}, new String[]{XmBluetoothRecord.TYPE_PROP, "8733"}, new String[]{"infin", "8734"}, new String[]{"ang", "8736"}, new String[]{"and", "8743"}, new String[]{"or", "8744"}, new String[]{"cap", "8745"}, new String[]{"cup", "8746"}, new String[]{"int", "8747"}, new String[]{"there4", "8756"}, new String[]{"sim", "8764"}, new String[]{"cong", "8773"}, new String[]{"asymp", "8776"}, new String[]{"ne", "8800"}, new String[]{"equiv", "8801"}, new String[]{"le", "8804"}, new String[]{UserDataStore.GENDER, "8805"}, new String[]{j.i, "8834"}, new String[]{"sup", "8835"}, new String[]{"sube", "8838"}, new String[]{"supe", "8839"}, new String[]{"oplus", "8853"}, new String[]{"otimes", "8855"}, new String[]{"perp", "8869"}, new String[]{"sdot", "8901"}, new String[]{"lceil", "8968"}, new String[]{"rceil", "8969"}, new String[]{"lfloor", "8970"}, new String[]{"rfloor", "8971"}, new String[]{TSMAuthContants.PARAM_LANGUAGE, "9001"}, new String[]{"rang", "9002"}, new String[]{"loz", "9674"}, new String[]{"spades", "9824"}, new String[]{"clubs", "9827"}, new String[]{"hearts", "9829"}, new String[]{"diams", "9830"}, new String[]{"OElig", "338"}, new String[]{"oelig", "339"}, new String[]{"Scaron", "352"}, new String[]{"scaron", "353"}, new String[]{"Yuml", "376"}, new String[]{"circ", "710"}, new String[]{"tilde", "732"}, new String[]{"ensp", "8194"}, new String[]{"emsp", "8195"}, new String[]{"thinsp", "8201"}, new String[]{"zwnj", "8204"}, new String[]{"zwj", "8205"}, new String[]{"lrm", "8206"}, new String[]{"rlm", "8207"}, new String[]{"ndash", "8211"}, new String[]{"mdash", "8212"}, new String[]{"lsquo", "8216"}, new String[]{"rsquo", "8217"}, new String[]{"sbquo", "8218"}, new String[]{"ldquo", "8220"}, new String[]{"rdquo", "8221"}, new String[]{"bdquo", "8222"}, new String[]{"dagger", "8224"}, new String[]{"Dagger", "8225"}, new String[]{"permil", "8240"}, new String[]{"lsaquo", "8249"}, new String[]{"rsaquo", "8250"}, new String[]{"euro", "8364"}};
    public static final Entities c;
    public static final Entities d;
    public static final Entities e;
    private static final String[][] f = {new String[]{"quot", "34"}, new String[]{"amp", "38"}, new String[]{d.T, Constant.TRANS_TYPE_LOAD}, new String[]{"gt", "62"}};
    private static final String[][] g = {new String[]{"apos", "39"}};
    private final EntityMap h;

    interface EntityMap {
        int a(String str);

        void a(String str, int i);

        String b(int i);
    }

    static {
        Entities entities = new Entities();
        entities.a(f);
        entities.a(g);
        c = entities;
        Entities entities2 = new Entities();
        entities2.a(f);
        entities2.a(f3361a);
        d = entities2;
        Entities entities3 = new Entities();
        a(entities3);
        e = entities3;
    }

    static void a(Entities entities) {
        entities.a(f);
        entities.a(f3361a);
        entities.a(b);
    }

    static class PrimitiveEntityMap implements EntityMap {

        /* renamed from: a  reason: collision with root package name */
        private final Map f3365a = new HashMap();
        private final IntHashMap b = new IntHashMap();

        PrimitiveEntityMap() {
        }

        public void a(String str, int i) {
            this.f3365a.put(str, new Integer(i));
            this.b.a(i, str);
        }

        public String b(int i) {
            return (String) this.b.b(i);
        }

        public int a(String str) {
            Object obj = this.f3365a.get(str);
            if (obj == null) {
                return -1;
            }
            return ((Integer) obj).intValue();
        }
    }

    static abstract class MapIntMap implements EntityMap {

        /* renamed from: a  reason: collision with root package name */
        protected final Map f3364a;
        protected final Map b;

        MapIntMap(Map map, Map map2) {
            this.f3364a = map;
            this.b = map2;
        }

        public void a(String str, int i) {
            this.f3364a.put(str, new Integer(i));
            this.b.put(new Integer(i), str);
        }

        public String b(int i) {
            return (String) this.b.get(new Integer(i));
        }

        public int a(String str) {
            Object obj = this.f3364a.get(str);
            if (obj == null) {
                return -1;
            }
            return ((Integer) obj).intValue();
        }
    }

    static class HashEntityMap extends MapIntMap {
        public HashEntityMap() {
            super(new HashMap(), new HashMap());
        }
    }

    static class TreeEntityMap extends MapIntMap {
        public TreeEntityMap() {
            super(new TreeMap(), new TreeMap());
        }
    }

    static class LookupEntityMap extends PrimitiveEntityMap {
        private static final int b = 256;

        /* renamed from: a  reason: collision with root package name */
        private String[] f3363a;

        LookupEntityMap() {
        }

        public String b(int i) {
            if (i < 256) {
                return a()[i];
            }
            return super.b(i);
        }

        private String[] a() {
            if (this.f3363a == null) {
                b();
            }
            return this.f3363a;
        }

        private void b() {
            this.f3363a = new String[256];
            for (int i = 0; i < 256; i++) {
                this.f3363a[i] = super.b(i);
            }
        }
    }

    static class ArrayEntityMap implements EntityMap {

        /* renamed from: a  reason: collision with root package name */
        protected final int f3362a;
        protected int b;
        protected String[] c;
        protected int[] d;

        public ArrayEntityMap() {
            this.b = 0;
            this.f3362a = 100;
            this.c = new String[this.f3362a];
            this.d = new int[this.f3362a];
        }

        public ArrayEntityMap(int i) {
            this.b = 0;
            this.f3362a = i;
            this.c = new String[i];
            this.d = new int[i];
        }

        public void a(String str, int i) {
            a(this.b + 1);
            this.c[this.b] = str;
            this.d[this.b] = i;
            this.b++;
        }

        /* access modifiers changed from: protected */
        public void a(int i) {
            if (i > this.c.length) {
                int max = Math.max(i, this.b + this.f3362a);
                String[] strArr = new String[max];
                System.arraycopy(this.c, 0, strArr, 0, this.b);
                this.c = strArr;
                int[] iArr = new int[max];
                System.arraycopy(this.d, 0, iArr, 0, this.b);
                this.d = iArr;
            }
        }

        public String b(int i) {
            for (int i2 = 0; i2 < this.b; i2++) {
                if (this.d[i2] == i) {
                    return this.c[i2];
                }
            }
            return null;
        }

        public int a(String str) {
            for (int i = 0; i < this.b; i++) {
                if (this.c[i].equals(str)) {
                    return this.d[i];
                }
            }
            return -1;
        }
    }

    static class BinaryEntityMap extends ArrayEntityMap {
        public BinaryEntityMap() {
        }

        public BinaryEntityMap(int i) {
            super(i);
        }

        private int c(int i) {
            int i2 = this.b - 1;
            int i3 = 0;
            while (i3 <= i2) {
                int i4 = (i3 + i2) >>> 1;
                int i5 = this.d[i4];
                if (i5 < i) {
                    i3 = i4 + 1;
                } else if (i5 <= i) {
                    return i4;
                } else {
                    i2 = i4 - 1;
                }
            }
            return -(i3 + 1);
        }

        public void a(String str, int i) {
            a(this.b + 1);
            int c = c(i);
            if (c <= 0) {
                int i2 = -(c + 1);
                int i3 = i2 + 1;
                System.arraycopy(this.d, i2, this.d, i3, this.b - i2);
                this.d[i2] = i;
                System.arraycopy(this.c, i2, this.c, i3, this.b - i2);
                this.c[i2] = str;
                this.b++;
            }
        }

        public String b(int i) {
            int c = c(i);
            if (c < 0) {
                return null;
            }
            return this.c[c];
        }
    }

    public Entities() {
        this.h = new LookupEntityMap();
    }

    Entities(EntityMap entityMap) {
        this.h = entityMap;
    }

    public void a(String[][] strArr) {
        for (int i = 0; i < strArr.length; i++) {
            a(strArr[i][0], Integer.parseInt(strArr[i][1]));
        }
    }

    public void a(String str, int i) {
        this.h.a(str, i);
    }

    public String a(int i) {
        return this.h.b(i);
    }

    public int a(String str) {
        return this.h.a(str);
    }

    public String b(String str) {
        StringWriter d2 = d(str);
        try {
            a((Writer) d2, str);
            return d2.toString();
        } catch (IOException e2) {
            throw new UnhandledException(e2);
        }
    }

    public void a(Writer writer, String str) throws IOException {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            String a2 = a((int) charAt);
            if (a2 != null) {
                writer.write(38);
                writer.write(a2);
                writer.write(59);
            } else if (charAt > 127) {
                writer.write("&#");
                writer.write(Integer.toString(charAt, 10));
                writer.write(59);
            } else {
                writer.write(charAt);
            }
        }
    }

    public String c(String str) {
        int indexOf = str.indexOf(38);
        if (indexOf < 0) {
            return str;
        }
        StringWriter d2 = d(str);
        try {
            a(d2, str, indexOf);
            return d2.toString();
        } catch (IOException e2) {
            throw new UnhandledException(e2);
        }
    }

    private StringWriter d(String str) {
        double length = (double) str.length();
        double length2 = (double) str.length();
        Double.isNaN(length2);
        Double.isNaN(length);
        return new StringWriter((int) (length + (length2 * 0.1d)));
    }

    public void b(Writer writer, String str) throws IOException {
        int indexOf = str.indexOf(38);
        if (indexOf < 0) {
            writer.write(str);
        } else {
            a(writer, str, indexOf);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0067, code lost:
        if (r2 > 65535) goto L_0x006f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.io.Writer r11, java.lang.String r12, int r13) throws java.io.IOException {
        /*
            r10 = this;
            r0 = 0
            r11.write(r12, r0, r13)
            int r1 = r12.length()
        L_0x0008:
            if (r13 >= r1) goto L_0x0086
            char r2 = r12.charAt(r13)
            r3 = 38
            r4 = 1
            if (r2 != r3) goto L_0x0081
            int r5 = r13 + 1
            r6 = 59
            int r7 = r12.indexOf(r6, r5)
            r8 = -1
            if (r7 != r8) goto L_0x0022
            r11.write(r2)
            goto L_0x0084
        L_0x0022:
            int r9 = r12.indexOf(r3, r5)
            if (r9 == r8) goto L_0x002e
            if (r9 >= r7) goto L_0x002e
            r11.write(r2)
            goto L_0x0084
        L_0x002e:
            java.lang.String r13 = r12.substring(r5, r7)
            int r2 = r13.length()
            if (r2 <= 0) goto L_0x006f
            char r5 = r13.charAt(r0)
            r9 = 35
            if (r5 != r9) goto L_0x006a
            if (r2 <= r4) goto L_0x006f
            char r2 = r13.charAt(r4)
            r5 = 88
            if (r2 == r5) goto L_0x0059
            r5 = 120(0x78, float:1.68E-43)
            if (r2 == r5) goto L_0x0059
            java.lang.String r2 = r13.substring(r4)     // Catch:{ NumberFormatException -> 0x006f }
            r5 = 10
            int r2 = java.lang.Integer.parseInt(r2, r5)     // Catch:{ NumberFormatException -> 0x006f }
            goto L_0x0064
        L_0x0059:
            r2 = 2
            java.lang.String r2 = r13.substring(r2)     // Catch:{ NumberFormatException -> 0x006f }
            r5 = 16
            int r2 = java.lang.Integer.parseInt(r2, r5)     // Catch:{ NumberFormatException -> 0x006f }
        L_0x0064:
            r5 = 65535(0xffff, float:9.1834E-41)
            if (r2 <= r5) goto L_0x0070
            goto L_0x006f
        L_0x006a:
            int r2 = r10.a((java.lang.String) r13)
            goto L_0x0070
        L_0x006f:
            r2 = -1
        L_0x0070:
            if (r2 != r8) goto L_0x007c
            r11.write(r3)
            r11.write(r13)
            r11.write(r6)
            goto L_0x007f
        L_0x007c:
            r11.write(r2)
        L_0x007f:
            r13 = r7
            goto L_0x0084
        L_0x0081:
            r11.write(r2)
        L_0x0084:
            int r13 = r13 + r4
            goto L_0x0008
        L_0x0086:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.Entities.a(java.io.Writer, java.lang.String, int):void");
    }
}
