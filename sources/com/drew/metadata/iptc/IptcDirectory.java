package com.drew.metadata.iptc;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import com.facebook.appevents.AppEventsConstants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class IptcDirectory extends Directory {
    public static final int A = 527;
    public static final int B = 532;
    public static final int C = 534;
    public static final int D = 537;
    public static final int E = 538;
    public static final int F = 539;
    public static final int G = 542;
    public static final int H = 547;
    public static final int I = 549;
    public static final int J = 550;
    public static final int K = 552;
    public static final int L = 554;
    public static final int M = 557;
    public static final int N = 559;
    public static final int O = 562;
    public static final int P = 567;
    public static final int Q = 572;
    public static final int R = 574;
    public static final int S = 575;
    public static final int T = 577;
    public static final int U = 582;
    public static final int V = 587;
    public static final int W = 592;
    public static final int X = 597;
    public static final int Y = 602;
    public static final int Z = 604;
    public static final int aA = 712;
    public static final int aB = 713;
    public static final int aC = 714;
    @NotNull
    protected static final HashMap<Integer, String> aD = new HashMap<>();
    public static final int aa = 607;
    public static final int ab = 612;
    public static final int ac = 613;
    public static final int ad = 615;
    public static final int ae = 617;
    public static final int af = 622;
    public static final int ag = 627;
    public static final int ah = 628;
    public static final int ai = 630;
    public static final int aj = 632;
    public static final int ak = 633;
    public static final int al = 634;
    public static final int am = 637;
    public static final int an = 642;
    public static final int ao = 643;
    public static final int ap = 647;
    public static final int aq = 662;
    public static final int ar = 663;
    public static final int as = 664;
    public static final int at = 665;
    public static final int au = 666;
    public static final int av = 696;
    public static final int aw = 697;
    public static final int ax = 698;
    public static final int ay = 699;
    public static final int az = 700;
    public static final int e = 256;
    public static final int f = 261;
    public static final int g = 276;
    public static final int h = 278;
    public static final int i = 286;
    public static final int j = 296;
    public static final int k = 306;
    public static final int l = 316;
    public static final int m = 326;
    public static final int n = 336;
    public static final int o = 346;
    public static final int p = 356;
    public static final int q = 376;
    public static final int r = 378;
    public static final int s = 512;
    public static final int t = 515;
    public static final int u = 516;
    public static final int v = 517;
    public static final int w = 519;
    public static final int x = 520;
    public static final int y = 522;
    public static final int z = 524;

    @NotNull
    public String a() {
        return "IPTC";
    }

    static {
        aD.put(256, "Enveloped Record Version");
        aD.put(261, "Destination");
        aD.put(276, "File Format");
        aD.put(278, "File Version");
        aD.put(286, "Service Identifier");
        aD.put(296, "Envelope Number");
        aD.put(306, "Product Identifier");
        aD.put(316, "Envelope Priority");
        aD.put(326, "Date Sent");
        aD.put(Integer.valueOf(n), "Time Sent");
        aD.put(Integer.valueOf(o), "Coded Character Set");
        aD.put(Integer.valueOf(p), "Unique Object Name");
        aD.put(Integer.valueOf(q), "ARM Identifier");
        aD.put(Integer.valueOf(r), "ARM Version");
        aD.put(512, "Application Record Version");
        aD.put(515, "Object Type Reference");
        aD.put(516, "Object Attribute Reference");
        aD.put(517, "Object Name");
        aD.put(519, "Edit Status");
        aD.put(520, "Editorial Update");
        aD.put(522, "Urgency");
        aD.put(524, "Subject Reference");
        aD.put(527, "Category");
        aD.put(532, "Supplemental Category(s)");
        aD.put(534, "Fixture Identifier");
        aD.put(537, "Keywords");
        aD.put(Integer.valueOf(E), "Content Location Code");
        aD.put(539, "Content Location Name");
        aD.put(542, "Release Date");
        aD.put(547, "Release Time");
        aD.put(549, "Expiration Date");
        aD.put(Integer.valueOf(J), "Expiration Time");
        aD.put(Integer.valueOf(K), "Special Instructions");
        aD.put(Integer.valueOf(L), "Action Advised");
        aD.put(Integer.valueOf(M), "Reference Service");
        aD.put(559, "Reference Date");
        aD.put(Integer.valueOf(O), "Reference Number");
        aD.put(Integer.valueOf(P), "Date Created");
        aD.put(Integer.valueOf(Q), "Time Created");
        aD.put(Integer.valueOf(R), "Digital Date Created");
        aD.put(Integer.valueOf(S), "Digital Time Created");
        aD.put(Integer.valueOf(T), "Originating Program");
        aD.put(Integer.valueOf(U), "Program Version");
        aD.put(Integer.valueOf(V), "Object Cycle");
        aD.put(Integer.valueOf(W), "By-line");
        aD.put(Integer.valueOf(X), "By-line Title");
        aD.put(602, "City");
        aD.put(Integer.valueOf(Z), "Sub-location");
        aD.put(Integer.valueOf(aa), "Province/State");
        aD.put(Integer.valueOf(ab), "Country/Primary Location Code");
        aD.put(Integer.valueOf(ac), "Country/Primary Location Name");
        aD.put(615, "Original Transmission Reference");
        aD.put(Integer.valueOf(ae), "Headline");
        aD.put(Integer.valueOf(af), "Credit");
        aD.put(Integer.valueOf(ag), "Source");
        aD.put(Integer.valueOf(ah), "Copyright Notice");
        aD.put(Integer.valueOf(ai), AppEventsConstants.EVENT_NAME_CONTACT);
        aD.put(Integer.valueOf(aj), "Caption/Abstract");
        aD.put(Integer.valueOf(ak), "Local Caption");
        aD.put(Integer.valueOf(al), "Caption Writer/Editor");
        aD.put(Integer.valueOf(am), "Rasterized Caption");
        aD.put(Integer.valueOf(an), "Image Type");
        aD.put(Integer.valueOf(ao), "Image Orientation");
        aD.put(Integer.valueOf(ap), "Language Identifier");
        aD.put(Integer.valueOf(aq), "Audio Type");
        aD.put(Integer.valueOf(ar), "Audio Sampling Rate");
        aD.put(Integer.valueOf(as), "Audio Sampling Resolution");
        aD.put(Integer.valueOf(at), "Audio Duration");
        aD.put(Integer.valueOf(au), "Audio Outcue");
        aD.put(Integer.valueOf(av), "Job Identifier");
        aD.put(Integer.valueOf(aw), "Master Document Identifier");
        aD.put(Integer.valueOf(ax), "Short Document Identifier");
        aD.put(Integer.valueOf(ay), "Unique Document Identifier");
        aD.put(700, "Owner Identifier");
        aD.put(Integer.valueOf(aA), "Object Data Preview File Format");
        aD.put(Integer.valueOf(aB), "Object Data Preview File Format Version");
        aD.put(Integer.valueOf(aC), "Object Data Preview Data");
    }

    public IptcDirectory() {
        a((TagDescriptor) new IptcDescriptor(this));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return aD;
    }

    @Nullable
    public List<String> j() {
        String[] d = d(537);
        if (d == null) {
            return null;
        }
        return Arrays.asList(d);
    }

    @Nullable
    public Date k() {
        return b(326, n);
    }

    @Nullable
    public Date l() {
        return b(542, 547);
    }

    @Nullable
    public Date m() {
        return b(549, J);
    }

    @Nullable
    public Date n() {
        return b(P, Q);
    }

    @Nullable
    public Date o() {
        return b(R, S);
    }

    @Nullable
    private Date b(int i2, int i3) {
        String s2 = s(i2);
        String s3 = s(i3);
        if (s2 == null || s3 == null) {
            return null;
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssZ");
            return simpleDateFormat.parse(s2 + s3);
        } catch (ParseException unused) {
            return null;
        }
    }
}
