package com.xiaomi.zxing.oned;

import com.google.zxing.client.result.ExpandedProductParsedResult;
import com.mi.global.shop.constants.UIConstant;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.TbsMediaPlayer;
import com.tiqiaa.client.impl.TvClient;
import com.tiqiaa.constant.KeyType;
import com.xiaomi.smarthome.download.Downloads;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import java.util.ArrayList;
import java.util.List;
import org.cybergarage.http.HTTP;

final class EANManufacturerOrgSupport {

    /* renamed from: a  reason: collision with root package name */
    private final List<int[]> f1698a = new ArrayList();
    private final List<String> b = new ArrayList();

    EANManufacturerOrgSupport() {
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0017, code lost:
        r4 = r7.f1698a.get(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(java.lang.String r8) {
        /*
            r7 = this;
            r7.a()
            r0 = 0
            r1 = 3
            java.lang.String r8 = r8.substring(r0, r1)
            int r8 = java.lang.Integer.parseInt(r8)
            java.util.List<int[]> r1 = r7.f1698a
            int r1 = r1.size()
            r2 = 0
        L_0x0014:
            r3 = 0
            if (r2 >= r1) goto L_0x0039
            java.util.List<int[]> r4 = r7.f1698a
            java.lang.Object r4 = r4.get(r2)
            int[] r4 = (int[]) r4
            r5 = r4[r0]
            if (r8 >= r5) goto L_0x0024
            return r3
        L_0x0024:
            int r3 = r4.length
            r6 = 1
            if (r3 != r6) goto L_0x0029
            goto L_0x002b
        L_0x0029:
            r5 = r4[r6]
        L_0x002b:
            if (r8 > r5) goto L_0x0036
            java.util.List<java.lang.String> r8 = r7.b
            java.lang.Object r8 = r8.get(r2)
            java.lang.String r8 = (java.lang.String) r8
            return r8
        L_0x0036:
            int r2 = r2 + 1
            goto L_0x0014
        L_0x0039:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.zxing.oned.EANManufacturerOrgSupport.a(java.lang.String):java.lang.String");
    }

    private void a(int[] iArr, String str) {
        this.f1698a.add(iArr);
        this.b.add(str);
    }

    private synchronized void a() {
        if (this.f1698a.isEmpty()) {
            a(new int[]{0, 19}, "US/CA");
            a(new int[]{30, 39}, "US");
            a(new int[]{60, 139}, "US/CA");
            a(new int[]{300, 379}, ServerCompact.e);
            a(new int[]{380}, "BG");
            a(new int[]{383}, "SI");
            a(new int[]{385}, "HR");
            a(new int[]{387}, "BA");
            a(new int[]{400, 440}, "DE");
            a(new int[]{450, 459}, "JP");
            a(new int[]{460, 469}, ServerCompact.d);
            a(new int[]{471}, ServerCompact.f1531a);
            a(new int[]{474}, "EE");
            a(new int[]{475}, "LV");
            a(new int[]{476}, "AZ");
            a(new int[]{477}, "LT");
            a(new int[]{478}, "UZ");
            a(new int[]{479}, "LK");
            a(new int[]{480}, "PH");
            a(new int[]{481}, "BY");
            a(new int[]{482}, "UA");
            a(new int[]{484}, "MD");
            a(new int[]{485}, "AM");
            a(new int[]{486}, "GE");
            a(new int[]{487}, "KZ");
            a(new int[]{489}, ServerCompact.b);
            a(new int[]{Downloads.STATUS_CANCELED, Downloads.STATUS_DEVICE_NOT_FOUND_ERROR}, "JP");
            a(new int[]{500, TbsListener.ErrorCode.INFO_CODE_FILEREADER_OPENFILEREADER_FILEPATHISNULL}, ServerCompact.i);
            a(new int[]{520}, "GR");
            a(new int[]{528}, ExpandedProductParsedResult.POUND);
            a(new int[]{529}, "CY");
            a(new int[]{531}, "MK");
            a(new int[]{535}, "MT");
            a(new int[]{539}, "IE");
            a(new int[]{540, 549}, "BE/LU");
            a(new int[]{560}, "PT");
            a(new int[]{569}, "IS");
            a(new int[]{570, 579}, "DK");
            a(new int[]{590}, "PL");
            a(new int[]{594}, "RO");
            a(new int[]{599}, "HU");
            a(new int[]{600, 601}, "ZA");
            a(new int[]{603}, "GH");
            a(new int[]{608}, "BH");
            a(new int[]{609}, "MU");
            a(new int[]{611}, "MA");
            a(new int[]{613}, "DZ");
            a(new int[]{616}, "KE");
            a(new int[]{618}, "CI");
            a(new int[]{619}, "TN");
            a(new int[]{621}, "SY");
            a(new int[]{622}, "EG");
            a(new int[]{624}, "LY");
            a(new int[]{625}, "JO");
            a(new int[]{626}, "IR");
            a(new int[]{627}, "KW");
            a(new int[]{628}, "SA");
            a(new int[]{629}, "AE");
            a(new int[]{640, 649}, "FI");
            a(new int[]{UIConstant.q, 695}, "CN");
            a(new int[]{700, 709}, Constants.ae);
            a(new int[]{729}, "IL");
            a(new int[]{730, 739}, "SE");
            a(new int[]{740}, "GT");
            a(new int[]{741}, "SV");
            a(new int[]{742}, "HN");
            a(new int[]{743}, "NI");
            a(new int[]{744}, "CR");
            a(new int[]{745}, "PA");
            a(new int[]{746}, "DO");
            a(new int[]{750}, HTTP.MX);
            a(new int[]{754, 755}, "CA");
            a(new int[]{759}, "VE");
            a(new int[]{760, 769}, "CH");
            a(new int[]{770}, "CO");
            a(new int[]{773}, "UY");
            a(new int[]{775}, "PE");
            a(new int[]{777}, "BO");
            a(new int[]{779}, "AR");
            a(new int[]{780}, "CL");
            a(new int[]{784}, "PY");
            a(new int[]{785}, "PE");
            a(new int[]{786}, "EC");
            a(new int[]{789, TbsMediaPlayer.TbsMediaPlayerListener.MEDIA_INFO_BUFFERING_PERCENTAGE}, "BR");
            a(new int[]{800, KeyType.OPEN}, ServerCompact.h);
            a(new int[]{KeyType.TITLE, KeyType.RESET}, ServerCompact.f);
            a(new int[]{850}, "CU");
            a(new int[]{858}, "SK");
            a(new int[]{859}, "CZ");
            a(new int[]{860}, "YU");
            a(new int[]{865}, "MN");
            a(new int[]{867}, "KP");
            a(new int[]{868, 869}, "TR");
            a(new int[]{KeyType.AIR_WIND_DIRECT, KeyType.AIR_POWER_SAVING}, "NL");
            a(new int[]{880}, "KR");
            a(new int[]{885}, "TH");
            a(new int[]{888}, "SG");
            a(new int[]{890}, ServerCompact.c);
            a(new int[]{893}, "VN");
            a(new int[]{896}, "PK");
            a(new int[]{899}, "ID");
            a(new int[]{900, 919}, "AT");
            a(new int[]{TvClient.TV_SERVICE_LOAD_CITY_PROVIDER_MAPS_OK, 939}, "AU");
            a(new int[]{940, 949}, "AZ");
            a(new int[]{955}, "MY");
            a(new int[]{958}, "MO");
        }
    }
}
