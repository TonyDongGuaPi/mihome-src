package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.engines;

import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.coloros.mcssdk.c.a;
import com.google.common.base.Ascii;
import com.tencent.smtt.sdk.TbsListener;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.BlockCipher;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.CipherParameters;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.DataLengthException;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.OutputLengthException;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.crypto.params.KeyParameter;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Arrays;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.bouncycastle.util.Pack;
import java.lang.reflect.Array;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;

public class AESEngine implements BlockCipher {

    /* renamed from: a  reason: collision with root package name */
    private static final byte[] f14451a = {Constants.TagName.PAY_ORDER, Constants.TagName.PRICE, 119, Constants.TagName.ELECTRONIC_USE_TIME, -14, Constants.TagName.ELECTRONIC, Constants.TagName.ELECTRONIC_END_TIME, Constants.TagName.USER_PLATFORM_ID, 48, 1, 103, Constants.TagName.USER_LOCK_TIME, -2, -41, -85, Constants.TagName.ELECTRONIC_FROZEN_FLAG, -54, -126, Constants.TagName.BUSINESS_ORDER_LOAD_TYPE, Constants.TagName.ELECTRONIC_PRICE_FAVOURABLE, -6, 89, Constants.TagName.ACTIVITY_INFO, -16, -83, -44, ScriptToolsConst.TagName.ResponseSingle, -81, Constants.TagName.PRODUCT_LIST, ScriptToolsConst.TagName.CommandMultiple, Constants.TagName.ELECTRONIC_NUMBER, Constants.TagName.STATION_ENAME, -73, -3, Constants.TagName.PLATFORM_NOTICES, Constants.TagName.QUERY_RECORD_COUNT, 54, Constants.TagName.CARD_APP_ACTIVATION_STATUS, -9, -52, 52, -91, -27, -15, Constants.TagName.ELECTRONIC_TYPE_ID, -40, 49, 21, 4, Constants.TagName.PROMOTION_MESSAGE_LIST, 35, Constants.TagName.RENT_HANDLE_DATD, 24, Constants.TagName.PREDEPOSIT_INFO, 5, Constants.TagName.PRODUCT_NAME, 7, 18, Byte.MIN_VALUE, -30, -21, 39, Constants.TagName.APP_TYPE, Constants.TagName.ELECTRONIC_TRANSFER_FLAG, 9, -125, Constants.TagName.SYSTEM_NEW_VERSION, 26, 27, Constants.TagName.ELECTRONIC_STARTTIME, Constants.TagName.PREDEPOSIT_TOTAL, ScriptToolsConst.TagName.CommandSingle, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, Constants.TagName.CARD_APP_RAMDOM, -42, Constants.TagName.APP_AID, 41, -29, Constants.TagName.CARD_FORM, -124, 83, -47, 0, -19, 32, -4, Constants.TagName.SEID, Constants.TagName.PREDEPOSIT_BLANCE, Constants.TagName.PAY_ORDER_ID, -53, Constants.TagName.STATION_INFO_LIST, ScriptToolsConst.TagName.TagApdu, 74, 76, 88, -49, -48, -17, -86, -5, Constants.TagName.TERMINAL_BACK_INFO_TYPE, 77, 51, Constants.TagName.ACTIVITY_END, Constants.TagName.TERMINAL_MODEL_NUMBER, -7, 2, Byte.MAX_VALUE, Constants.TagName.ORDER_BRIEF_INFO_LIST, ScriptToolsConst.TagName.TagExpectationAndNext, -97, -88, Constants.TagName.TERMINAL_BACK_MAIN_ID, ScriptToolsConst.TagName.ResponseMultiple, 64, Constants.TagName.URL_LIST, Constants.TagName.TEXT_NOTICE, Constants.TagName.INVOICE_TOKEN_OBJECT, ScriptToolsConst.TagName.TagSerial, -11, Constants.TagName.STATION_CONFIG_VERSION, Constants.TagName.CPLC, -38, Framer.ENTER_FRAME_PREFIX, 16, -1, -13, -46, -51, 12, 19, -20, 95, Constants.TagName.PREDEPOSIT_LIST, Constants.TagName.TERMINAL_OS_VERSION, 23, Constants.TagName.USER_PLATFORM_TYPE, Constants.TagName.OPERATION_STEP, Constants.TagName.ELECTRONIC_OUT_SERIAL, Constants.TagName.CARD_APP_VERSION, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.TERMINAL_INFO_TYPE, 25, Constants.TagName.ELECTRONIC_TYPE, Constants.TagName.MAIN_ORDER, -127, Constants.TagName.CP_NO, -36, 34, 42, Constants.TagName.SYSTEM_VERSION, Constants.TagName.ACTIVITY_DEFINITION, Constants.TagName.TERMINAL_BASEBAND_VERSION, -18, Constants.TagName.EUID, 20, -34, 94, 11, -37, -32, 50, Constants.TagName.BUSINESS_ORDER_OP_TYPE, 10, Constants.TagName.ORDER_BRIEF_INFO, 6, Constants.TagName.USER_LOGIN_FAIL_COUNT, Constants.TagName.ORDER_TRADE_STATUSES, Constants.TagName.RENT_HANDLE_TYPE, -45, -84, Constants.TagName.OPERATE_TIMING, -111, Constants.TagName.PREDEPOSIT_TYPE, -28, Constants.TagName.ELECTRONIC_STATE, -25, Constants.TagName.PROMOTION_MESSAGE_DATA, 55, Constants.TagName.PUBLISH_END_TIME, Constants.TagName.ACTIVITY_STATUS, -43, 78, -87, Constants.TagName.ELECTRONIC_LIST, Constants.TagName.QUERY_DATA_SORT_TYPE, -12, -22, Constants.TagName.ORDER_TYPE, Constants.TagName.ELECTRONIC_OUT_STATE, -82, 8, Constants.TagName.IMEI, 120, Constants.TagName.ORDER_RANGE_TYPE, Constants.TagName.SIM_SEID, 28, Constants.TagName.OPERATION_ID, Constants.TagName.PATCH_DATA, Constants.TagName.PROMOTION_MESSAGE, -24, -35, Constants.TagName.ELECTRONIC_USE_TYPE, 31, TarConstants.U, Constants.TagName.STATION_INFO, Constants.TagName.PAY_CHANNEL_MIN, Constants.TagName.PAY_CHANNEL_NAME, Constants.TagName.ELECTRONIC_ID, Constants.TagName.CARD_BUSINESS_ORDER_STATUS, Constants.TagName.BUSINESS_HANDLE_RESULT, 102, Constants.TagName.BUSINESS_ORDER_TYPE, 3, -10, 14, 97, TarConstants.R, 87, Constants.TagName.SIR, Constants.TagName.ACTIVITY_TOTAL, Constants.TagName.STATION_NAME, Ascii.GS, Constants.TagName.INVOICE_TOKEN_OBJECT_LIST, -31, -8, Constants.TagName.PRODUCT_INFO, 17, Constants.TagName.MAIN_ORDER_ID, -39, Constants.TagName.URL_TYPE, Constants.TagName.UNSOLVED_NOTICES, Constants.TagName.CITY_CODE, 30, Constants.TagName.ACTIVITY_REMAINDER, -23, -50, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.CARD_APP_BLANCE, -33, Constants.TagName.PREDEPOSIT_STATUS, ScriptToolsConst.TagName.ScriptDown, Constants.TagName.COMPANY_CODE, 13, Constants.TagName.STATION_ID, -26, Constants.TagName.INVOICE_TOKEN, Constants.TagName.DEVICE_MODEL, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.PRODUCT_CODE, 45, 15, -80, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.APP_MANAGE_OPEATE_TYPE, 22};
    private static final byte[] b = {Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, 9, Constants.TagName.PAY_ORDER_ID, -43, 48, 54, -91, ScriptToolsConst.TagName.TagSerial, Constants.TagName.STATION_ID, 64, ScriptToolsConst.TagName.ResponseMultiple, Constants.TagName.INVOICE_TOKEN_OBJECT_LIST, -127, -13, -41, -5, Constants.TagName.PRICE, -29, ScriptToolsConst.TagName.TagApdu, -126, Constants.TagName.CITY_CODE, Constants.TagName.CARD_FORM, -1, Constants.TagName.ACTIVITY_REMAINDER, 52, Constants.TagName.URL_TYPE, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.USER_PLATFORM_TYPE, -34, -23, -53, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.ELECTRONIC_USE_TIME, Constants.TagName.UNSOLVED_NOTICES, 50, Constants.TagName.OPERATION_ID, Constants.TagName.RENT_HANDLE_TYPE, 35, Constants.TagName.CARD_APP_VERSION, -18, 76, Constants.TagName.PREDEPOSIT_TYPE, 11, Constants.TagName.INVOICE_TOKEN, -6, Constants.TagName.RENT_HANDLE_DATD, 78, 8, Constants.TagName.SIM_SEID, ScriptToolsConst.TagName.ScriptDown, 102, Constants.TagName.CARD_APP_BLANCE, -39, Constants.TagName.USER_LOGIN_FAIL_COUNT, Constants.TagName.APP_TYPE, Constants.TagName.ELECTRONIC_FROZEN_FLAG, Constants.TagName.PREDEPOSIT_BLANCE, ScriptToolsConst.TagName.ResponseSingle, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.PUBLISH_END_TIME, Constants.TagName.PAY_CHANNEL_MIN, -47, Constants.TagName.ORDER_RANGE_TYPE, Constants.TagName.ELECTRONIC_NUMBER, -8, -10, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.ACTIVITY_TOTAL, Constants.TagName.DEVICE_MODEL, Constants.TagName.PRODUCT_INFO, 22, -44, ScriptToolsConst.TagName.CommandMultiple, Constants.TagName.ORDER_TRADE_STATUSES, -52, Constants.TagName.TERMINAL_INFO_TYPE, Constants.TagName.ORDER_TYPE, Constants.TagName.CPLC, Constants.TagName.TEXT_NOTICE, Constants.TagName.ELECTRONIC_LIST, Constants.TagName.ELECTRONIC_ID, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.ORDER_BRIEF_INFO_LIST, -3, -19, Constants.TagName.SIR, -38, 94, 21, Constants.TagName.TERMINAL_BASEBAND_VERSION, 87, Constants.TagName.OPERATION_STEP, Constants.TagName.ACTIVITY_STATUS, Constants.TagName.INVOICE_TOKEN_OBJECT, -124, Constants.TagName.SYSTEM_VERSION, -40, -85, 0, Constants.TagName.PREDEPOSIT_STATUS, Constants.TagName.STATION_CONFIG_VERSION, -45, 10, -9, -28, 88, 5, Constants.TagName.EUID, Constants.TagName.APP_AID, Constants.TagName.TERMINAL_MODEL_NUMBER, 6, -48, Constants.TagName.SYSTEM_NEW_VERSION, 30, Constants.TagName.URL_LIST, -54, Constants.TagName.CARD_APP_ACTIVATION_STATUS, 15, 2, Constants.TagName.STATION_NAME, -81, Constants.TagName.STATION_INFO, 3, 1, 19, Constants.TagName.PAY_CHANNEL_NAME, Constants.TagName.ELECTRONIC, Constants.TagName.BUSINESS_ORDER_OP_TYPE, -111, 17, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 103, -36, -22, Constants.TagName.PREDEPOSIT_LIST, -14, -49, -50, -16, Constants.TagName.PATCH_DATA, -26, Constants.TagName.ELECTRONIC_TYPE, Constants.TagName.PREDEPOSIT_INFO, -84, Constants.TagName.ELECTRONIC_USE_TYPE, 34, -25, -83, TarConstants.R, Constants.TagName.ACTIVITY_END, -30, -7, 55, -24, 28, Constants.TagName.ELECTRONIC_TRANSFER_FLAG, -33, Constants.TagName.ELECTRONIC_STARTTIME, Constants.TagName.ACTIVITY_INFO, -15, 26, Constants.TagName.ELECTRONIC_TYPE_ID, Ascii.GS, 41, Constants.TagName.USER_PLATFORM_ID, Constants.TagName.COMPANY_CODE, Constants.TagName.ELECTRONIC_END_TIME, -73, Constants.TagName.OPERATE_TIMING, 14, -86, 24, Constants.TagName.STATION_INFO_LIST, 27, -4, Constants.TagName.QUERY_DATA_SORT_TYPE, Constants.TagName.CARD_BUSINESS_ORDER_STATUS, TarConstants.U, Constants.TagName.PROMOTION_MESSAGE, -46, Constants.TagName.ELECTRONIC_STATE, 32, Constants.TagName.PRODUCT_NAME, -37, Constants.TagName.STATION_ENAME, -2, 120, -51, Constants.TagName.PREDEPOSIT_TOTAL, -12, 31, -35, -88, 51, Constants.TagName.ACTIVITY_DEFINITION, 7, Constants.TagName.PROMOTION_MESSAGE_LIST, 49, Constants.TagName.SEID, 18, 16, 89, 39, Byte.MIN_VALUE, -20, 95, Constants.TagName.MAIN_ORDER, Constants.TagName.TERMINAL_BACK_MAIN_ID, Byte.MAX_VALUE, -87, 25, Constants.TagName.BUSINESS_HANDLE_RESULT, 74, 13, 45, -27, Constants.TagName.ELECTRONIC_OUT_STATE, -97, Constants.TagName.PLATFORM_NOTICES, Constants.TagName.BUSINESS_ORDER_LOAD_TYPE, Constants.TagName.PRODUCT_LIST, -17, ScriptToolsConst.TagName.CommandSingle, -32, Constants.TagName.CARD_APP_RAMDOM, 77, -82, 42, -11, -80, Constants.TagName.PROMOTION_MESSAGE_DATA, -21, Constants.TagName.APP_MANAGE_OPEATE_TYPE, ScriptToolsConst.TagName.TagExpectationAndNext, -125, 83, Constants.TagName.PRODUCT_CODE, 97, 23, Constants.TagName.USER_LOCK_TIME, 4, Constants.TagName.ELECTRONIC_OUT_SERIAL, Constants.TagName.IMEI, 119, -42, Constants.TagName.QUERY_RECORD_COUNT, -31, Constants.TagName.MAIN_ORDER_ID, 20, Constants.TagName.PAY_ORDER, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Framer.ENTER_FRAME_PREFIX, 12, Constants.TagName.ELECTRONIC_PRICE_FAVOURABLE};
    private static final int[] c = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, 171, 77, 154, 47, 94, 188, 99, Opcodes.dh, 151, 53, 106, TbsListener.ErrorCode.COPY_FAIL, 179, 125, 250, 239, Opcodes.dg, 145};
    private static final int[] d = {-1520213050, -2072216328, -1720223762, -1921287178, 234025727, -1117033514, -1318096930, 1422247313, 1345335392, 50397442, -1452841010, 2099981142, 436141799, 1658312629, -424957107, -1703512340, 1170918031, -1652391393, 1086966153, -2021818886, 368769775, -346465870, -918075506, 200339707, -324162239, 1742001331, -39673249, -357585083, -1080255453, -140204973, -1770884380, 1539358875, -1028147339, 486407649, -1366060227, 1780885068, 1513502316, 1094664062, 49805301, 1338821763, 1546925160, -190470831, 887481809, 150073849, -1821281822, 1943591083, 1395732834, 1058346282, 201589768, 1388824469, 1696801606, 1589887901, 672667696, -1583966665, 251987210, -1248159185, 151455502, 907153956, -1686077413, 1038279391, 652995533, 1764173646, -843926913, -1619692054, 453576978, -1635548387, 1949051992, 773462580, 756751158, -1301385508, -296068428, -73359269, -162377052, 1295727478, 1641469623, -827083907, 2066295122, 1055122397, 1898917726, -1752923117, -179088474, 1758581177, 0, 753790401, 1612718144, 536673507, -927878791, -312779850, -1100322092, 1187761037, -641810841, 1262041458, -565556588, -733197160, -396863312, 1255133061, 1808847035, 720367557, -441800113, 385612781, -985447546, -682799718, 1429418854, -1803188975, -817543798, 284817897, 100794884, -2122350594, -263171936, 1144798328, -1163944155, -475486133, -212774494, -22830243, -1069531008, -1970303227, -1382903233, -1130521311, 1211644016, 83228145, -541279133, -1044990345, 1977277103, 1663115586, 806359072, 452984805, 250868733, 1842533055, 1288555905, 336333848, 890442534, 804056259, -513843266, -1567123659, -867941240, 957814574, 1472513171, -223893675, -2105639172, 1195195770, -1402706744, -413311558, 723065138, -1787595802, -1604296512, -1736343271, -783331426, 2145180835, 1713513028, 2116692564, -1416589253, -2088204277, -901364084, 703524551, -742868885, 1007948840, 2044649127, -497131844, 487262998, 1994120109, 1004593371, 1446130276, 1312438900, 503974420, -615954030, 168166924, 1814307912, -463709000, 1573044895, 1859376061, -273896381, -1503501628, -1466855111, -1533700815, 937747667, -1954973198, 854058965, 1137232011, 1496790894, -1217565222, -1936880383, 1691735473, -766620004, -525751991, -1267962664, -95005012, 133494003, 636152527, -1352309302, -1904575756, -374428089, 403179536, -709182865, -2005370640, 1864705354, 1915629148, 605822008, -240736681, -944458637, 1371981463, 602466507, 2094914977, -1670089496, 555687742, -582268010, -591544991, -2037675251, -2054518257, -1871679264, 1111375484, -994724495, -1436129588, -666351472, 84083462, 32962295, 302911004, -1553899070, 1597322602, -111716434, -793134743, -1853454825, 1489093017, 656219450, -1180787161, 954327513, 335083755, -1281845205, 856756514, -1150719534, 1893325225, -1987146233, -1483434957, -1231316179, 572399164, -1836611819, 552200649, 1238290055, -11184726, 2015897680, 2061492133, -1886614525, -123625127, -2138470135, 386731290, -624967835, 837215959, -968736124, -1201116976, -1019133566, -1332111063, 1999449434, 286199582, -877612933, -61582168, -692339859, 974525996};
    private static final int[] e = {1353184337, 1399144830, -1012656358, -1772214470, -882136261, -247096033, -1420232020, -1828461749, 1442459680, -160598355, -1854485368, 625738485, -52959921, -674551099, -2143013594, -1885117771, 1230680542, 1729870373, -1743852987, -507445667, 41234371, 317738113, -1550367091, -956705941, -413167869, -1784901099, -344298049, -631680363, 763608788, -752782248, 694804553, 1154009486, 1787413109, 2021232372, 1799248025, -579749593, -1236278850, 397248752, 1722556617, -1271214467, 407560035, -2110711067, 1613975959, 1165972322, -529046351, -2068943941, 480281086, -1809118983, 1483229296, 436028815, -2022908268, -1208452270, 601060267, -503166094, 1468997603, 715871590, 120122290, 63092015, -1703164538, -1526188077, -226023376, -1297760477, -1167457534, 1552029421, 723308426, -1833666137, -252573709, -1578997426, -839591323, -708967162, 526529745, -1963022652, -1655493068, -1604979806, 853641733, 1978398372, 971801355, -1427152832, 111112542, 1360031421, -108388034, 1023860118, -1375387939, 1186850381, -1249028975, 90031217, 1876166148, -15380384, 620468249, -1746289194, -868007799, 2006899047, -1119688528, -2004121337, 945494503, -605108103, 1191869601, -384875908, -920746760, 0, -2088337399, 1223502642, -1401941730, 1316117100, -67170563, 1446544655, 517320253, 658058550, 1691946762, 564550760, -783000677, 976107044, -1318647284, 266819475, -761860428, -1634624741, 1338359936, -1574904735, 1766553434, 370807324, 179999714, -450191168, 1138762300, 488053522, 185403662, -1379431438, -1180125651, -928440812, -2061897385, 1275557295, -1143105042, -44007517, -1624899081, -1124765092, -985962940, 880737115, 1982415755, -590994485, 1761406390, 1676797112, -891538985, 277177154, 1076008723, 538035844, 2099530373, -130171950, 288553390, 1839278535, 1261411869, -214912292, -330136051, -790380169, 1813426987, -1715900247, -95906799, 577038663, -997393240, 440397984, -668172970, -275762398, -951170681, -1043253031, -22885748, 906744984, -813566554, 685669029, 646887386, -1530942145, -459458004, 227702864, -1681105046, 1648787028, -1038905866, -390539120, 1593260334, -173030526, -1098883681, 2090061929, -1456614033, -1290656305, 999926984, -1484974064, 1852021992, 2075868123, 158869197, -199730834, 28809964, -1466282109, 1701746150, 2129067946, 147831841, -420997649, -644094022, -835293366, -737566742, -696471511, -1347247055, 824393514, 815048134, -1067015627, 935087732, -1496677636, -1328508704, 366520115, 1251476721, -136647615, 240176511, 804688151, -1915335306, 1303441219, 1414376140, -553347356, -474623586, 461924940, -1205916479, 2136040774, 82468509, 1563790337, 1937016826, 776014843, 1511876531, 1389550482, 861278441, 323475053, -1939744870, 2047648055, -1911228327, -1992551445, -299390514, 902390199, -303751967, 1018251130, 1507840668, 1064563285, 2043548696, -1086863501, -355600557, 1537932639, 342834655, -2032450440, -2114736182, 1053059257, 741614648, 1598071746, 1925389590, 203809468, -1958134744, 1100287487, 1895934009, -558691320, -1662733096, -1866377628, 1636092795, 1890988757, 1952214088, 1113045200};
    private static final int f = -2139062144;
    private static final int g = 2139062143;
    private static final int h = 27;
    private static final int i = -1061109568;
    private static final int j = 1061109567;
    private static final int s = 16;
    private int k;
    private int[][] l = null;
    private int m;
    private int n;
    private int o;
    private int p;
    private boolean q;
    private byte[] r;

    private static int a(int i2) {
        return (((i2 & f) >>> 7) * 27) ^ ((g & i2) << 1);
    }

    private static int a(int i2, int i3) {
        return (i2 << (-i3)) | (i2 >>> i3);
    }

    private static int b(int i2) {
        int i3 = i2 & i;
        int i4 = i3 ^ (i3 >>> 1);
        return (i4 >>> 5) ^ (((j & i2) << 2) ^ (i4 >>> 2));
    }

    public String a() {
        return a.b;
    }

    public int b() {
        return 16;
    }

    public void c() {
    }

    private static int c(int i2) {
        int a2 = a(i2, 8) ^ i2;
        int a3 = i2 ^ a(a2);
        int b2 = a2 ^ b(a3);
        return a3 ^ (b2 ^ a(b2, 16));
    }

    private static int d(int i2) {
        return (f14451a[(i2 >> 24) & 255] << 24) | (f14451a[i2 & 255] & 255) | ((f14451a[(i2 >> 8) & 255] & 255) << 8) | ((f14451a[(i2 >> 16) & 255] & 255) << 16);
    }

    private int[][] a(byte[] bArr, boolean z) {
        byte[] bArr2 = bArr;
        int length = bArr2.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i2 = length >>> 2;
        this.k = i2 + 6;
        int[][] iArr = (int[][]) Array.newInstance(int.class, new int[]{this.k + 1, 4});
        if (i2 == 4) {
            int e2 = Pack.e(bArr2, 0);
            iArr[0][0] = e2;
            int e3 = Pack.e(bArr2, 4);
            iArr[0][1] = e3;
            int e4 = Pack.e(bArr2, 8);
            iArr[0][2] = e4;
            int e5 = Pack.e(bArr2, 12);
            iArr[0][3] = e5;
            int i3 = e4;
            int i4 = e2;
            int i5 = e5;
            for (int i6 = 1; i6 <= 10; i6++) {
                i4 ^= d(a(i5, 8)) ^ c[i6 - 1];
                iArr[i6][0] = i4;
                e3 ^= i4;
                iArr[i6][1] = e3;
                i3 ^= e3;
                iArr[i6][2] = i3;
                i5 ^= i3;
                iArr[i6][3] = i5;
            }
        } else if (i2 == 6) {
            int e6 = Pack.e(bArr2, 0);
            iArr[0][0] = e6;
            int e7 = Pack.e(bArr2, 4);
            iArr[0][1] = e7;
            int e8 = Pack.e(bArr2, 8);
            iArr[0][2] = e8;
            int e9 = Pack.e(bArr2, 12);
            iArr[0][3] = e9;
            int e10 = Pack.e(bArr2, 16);
            iArr[1][0] = e10;
            int e11 = Pack.e(bArr2, 20);
            iArr[1][1] = e11;
            int d2 = e6 ^ (d(a(e11, 8)) ^ 1);
            iArr[1][2] = d2;
            int i7 = e7 ^ d2;
            iArr[1][3] = i7;
            int i8 = e8 ^ i7;
            iArr[2][0] = i8;
            int i9 = e9 ^ i8;
            iArr[2][1] = i9;
            int i10 = e10 ^ i9;
            iArr[2][2] = i10;
            int i11 = e11 ^ i10;
            iArr[2][3] = i11;
            int i12 = i10;
            int i13 = i9;
            int i14 = 2;
            int i15 = i8;
            int i16 = d2;
            int i17 = i11;
            for (int i18 = 3; i18 < 12; i18 += 3) {
                int d3 = d(a(i17, 8)) ^ i14;
                int i19 = i14 << 1;
                int i20 = i16 ^ d3;
                iArr[i18][0] = i20;
                int i21 = i7 ^ i20;
                iArr[i18][1] = i21;
                int i22 = i15 ^ i21;
                iArr[i18][2] = i22;
                int i23 = i13 ^ i22;
                iArr[i18][3] = i23;
                int i24 = i12 ^ i23;
                int i25 = i18 + 1;
                iArr[i25][0] = i24;
                int i26 = i17 ^ i24;
                iArr[i25][1] = i26;
                int d4 = d(a(i26, 8)) ^ i19;
                i14 = i19 << 1;
                i16 = i20 ^ d4;
                iArr[i25][2] = i16;
                i7 = i21 ^ i16;
                iArr[i25][3] = i7;
                i15 = i22 ^ i7;
                int i27 = i18 + 2;
                iArr[i27][0] = i15;
                i13 = i23 ^ i15;
                iArr[i27][1] = i13;
                i12 = i24 ^ i13;
                iArr[i27][2] = i12;
                i17 = i26 ^ i12;
                iArr[i27][3] = i17;
            }
            int d5 = (d(a(i17, 8)) ^ i14) ^ i16;
            iArr[12][0] = d5;
            int i28 = d5 ^ i7;
            iArr[12][1] = i28;
            int i29 = i28 ^ i15;
            iArr[12][2] = i29;
            iArr[12][3] = i29 ^ i13;
        } else if (i2 == 8) {
            int e12 = Pack.e(bArr2, 0);
            iArr[0][0] = e12;
            int e13 = Pack.e(bArr2, 4);
            iArr[0][1] = e13;
            int e14 = Pack.e(bArr2, 8);
            iArr[0][2] = e14;
            int e15 = Pack.e(bArr2, 12);
            iArr[0][3] = e15;
            int e16 = Pack.e(bArr2, 16);
            iArr[1][0] = e16;
            int e17 = Pack.e(bArr2, 20);
            iArr[1][1] = e17;
            int e18 = Pack.e(bArr2, 24);
            iArr[1][2] = e18;
            int e19 = Pack.e(bArr2, 28);
            iArr[1][3] = e19;
            int i30 = e12;
            int i31 = e18;
            int i32 = e19;
            int i33 = e17;
            int i34 = e16;
            int i35 = 1;
            for (int i36 = 2; i36 < 14; i36 += 2) {
                int d6 = d(a(i32, 8)) ^ i35;
                i35 <<= 1;
                i30 ^= d6;
                iArr[i36][0] = i30;
                e13 ^= i30;
                iArr[i36][1] = e13;
                e14 ^= e13;
                iArr[i36][2] = e14;
                e15 ^= e14;
                iArr[i36][3] = e15;
                i34 ^= d(e15);
                int i37 = i36 + 1;
                iArr[i37][0] = i34;
                i33 ^= i34;
                iArr[i37][1] = i33;
                i31 ^= i33;
                iArr[i37][2] = i31;
                i32 ^= i31;
                iArr[i37][3] = i32;
            }
            int d7 = (d(a(i32, 8)) ^ i35) ^ i30;
            iArr[14][0] = d7;
            int i38 = d7 ^ e13;
            iArr[14][1] = i38;
            int i39 = i38 ^ e14;
            iArr[14][2] = i39;
            iArr[14][3] = i39 ^ e15;
        } else {
            throw new IllegalStateException("Should never get here");
        }
        if (!z) {
            for (int i40 = 1; i40 < this.k; i40++) {
                for (int i41 = 0; i41 < 4; i41++) {
                    iArr[i40][i41] = c(iArr[i40][i41]);
                }
            }
        }
        return iArr;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.l = a(((KeyParameter) cipherParameters).a(), z);
            this.q = z;
            if (z) {
                this.r = Arrays.b(f14451a);
            } else {
                this.r = Arrays.b(b);
            }
        } else {
            throw new IllegalArgumentException("invalid parameter passed to AES init - " + cipherParameters.getClass().getName());
        }
    }

    public int a(byte[] bArr, int i2, byte[] bArr2, int i3) {
        if (this.l == null) {
            throw new IllegalStateException("AES engine not initialised");
        } else if (i2 + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i3 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        } else if (this.q) {
            a(bArr, i2);
            a(this.l);
            b(bArr2, i3);
            return 16;
        } else {
            a(bArr, i2);
            b(this.l);
            b(bArr2, i3);
            return 16;
        }
    }

    private void a(byte[] bArr, int i2) {
        int i3 = i2 + 1;
        this.m = bArr[i2] & 255;
        int i4 = i3 + 1;
        this.m |= (bArr[i3] & 255) << 8;
        int i5 = i4 + 1;
        this.m |= (bArr[i4] & 255) << 16;
        int i6 = i5 + 1;
        this.m |= bArr[i5] << 24;
        int i7 = i6 + 1;
        this.n = bArr[i6] & 255;
        int i8 = i7 + 1;
        this.n = ((bArr[i7] & 255) << 8) | this.n;
        int i9 = i8 + 1;
        this.n |= (bArr[i8] & 255) << 16;
        int i10 = i9 + 1;
        this.n |= bArr[i9] << 24;
        int i11 = i10 + 1;
        this.o = bArr[i10] & 255;
        int i12 = i11 + 1;
        this.o = ((bArr[i11] & 255) << 8) | this.o;
        int i13 = i12 + 1;
        this.o |= (bArr[i12] & 255) << 16;
        int i14 = i13 + 1;
        this.o |= bArr[i13] << 24;
        int i15 = i14 + 1;
        this.p = bArr[i14] & 255;
        int i16 = i15 + 1;
        this.p = ((bArr[i15] & 255) << 8) | this.p;
        this.p |= (bArr[i16] & 255) << 16;
        this.p = (bArr[i16 + 1] << 24) | this.p;
    }

    private void b(byte[] bArr, int i2) {
        int i3 = i2 + 1;
        bArr[i2] = (byte) this.m;
        int i4 = i3 + 1;
        bArr[i3] = (byte) (this.m >> 8);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (this.m >> 16);
        int i6 = i5 + 1;
        bArr[i5] = (byte) (this.m >> 24);
        int i7 = i6 + 1;
        bArr[i6] = (byte) this.n;
        int i8 = i7 + 1;
        bArr[i7] = (byte) (this.n >> 8);
        int i9 = i8 + 1;
        bArr[i8] = (byte) (this.n >> 16);
        int i10 = i9 + 1;
        bArr[i9] = (byte) (this.n >> 24);
        int i11 = i10 + 1;
        bArr[i10] = (byte) this.o;
        int i12 = i11 + 1;
        bArr[i11] = (byte) (this.o >> 8);
        int i13 = i12 + 1;
        bArr[i12] = (byte) (this.o >> 16);
        int i14 = i13 + 1;
        bArr[i13] = (byte) (this.o >> 24);
        int i15 = i14 + 1;
        bArr[i14] = (byte) this.p;
        int i16 = i15 + 1;
        bArr[i15] = (byte) (this.p >> 8);
        bArr[i16] = (byte) (this.p >> 16);
        bArr[i16 + 1] = (byte) (this.p >> 24);
    }

    private void a(int[][] iArr) {
        char c2 = 0;
        int i2 = this.m ^ iArr[0][0];
        int i3 = 1;
        int i4 = this.n ^ iArr[0][1];
        int i5 = this.o ^ iArr[0][2];
        int i6 = this.p ^ iArr[0][3];
        int i7 = i5;
        int i8 = i4;
        int i9 = i2;
        int i10 = 1;
        while (i10 < this.k - i3) {
            int a2 = (((a(d[(i8 >> 8) & 255], 24) ^ d[i9 & 255]) ^ a(d[(i7 >> 16) & 255], 16)) ^ a(d[(i6 >> 24) & 255], 8)) ^ iArr[i10][c2];
            int a3 = (((a(d[(i7 >> 8) & 255], 24) ^ d[i8 & 255]) ^ a(d[(i6 >> 16) & 255], 16)) ^ a(d[(i9 >> 24) & 255], 8)) ^ iArr[i10][i3];
            int a4 = (((a(d[(i6 >> 8) & 255], 24) ^ d[i7 & 255]) ^ a(d[(i9 >> 16) & 255], 16)) ^ a(d[(i8 >> 24) & 255], 8)) ^ iArr[i10][2];
            int a5 = ((a(d[(i9 >> 8) & 255], 24) ^ d[i6 & 255]) ^ a(d[(i8 >> 16) & 255], 16)) ^ a(d[(i7 >> 24) & 255], 8);
            int i11 = i10 + 1;
            int i12 = iArr[i10][3] ^ a5;
            i9 = (((d[a2 & 255] ^ a(d[(a3 >> 8) & 255], 24)) ^ a(d[(a4 >> 16) & 255], 16)) ^ a(d[(i12 >> 24) & 255], 8)) ^ iArr[i11][0];
            int a6 = (((d[a3 & 255] ^ a(d[(a4 >> 8) & 255], 24)) ^ a(d[(i12 >> 16) & 255], 16)) ^ a(d[(a2 >> 24) & 255], 8)) ^ iArr[i11][1];
            int a7 = (((d[a4 & 255] ^ a(d[(i12 >> 8) & 255], 24)) ^ a(d[(a2 >> 16) & 255], 16)) ^ a(d[(a3 >> 24) & 255], 8)) ^ iArr[i11][2];
            int a8 = ((d[i12 & 255] ^ a(d[(a2 >> 8) & 255], 24)) ^ a(d[(a3 >> 16) & 255], 16)) ^ a(d[(a4 >> 24) & 255], 8);
            int i13 = i11 + 1;
            int i14 = a8 ^ iArr[i11][3];
            i8 = a6;
            i7 = a7;
            i3 = 1;
            i6 = i14;
            i10 = i13;
            c2 = 0;
        }
        int a9 = (((d[i9 & 255] ^ a(d[(i8 >> 8) & 255], 24)) ^ a(d[(i7 >> 16) & 255], 16)) ^ a(d[(i6 >> 24) & 255], 8)) ^ iArr[i10][0];
        int a10 = (((d[i8 & 255] ^ a(d[(i7 >> 8) & 255], 24)) ^ a(d[(i6 >> 16) & 255], 16)) ^ a(d[(i9 >> 24) & 255], 8)) ^ iArr[i10][1];
        int a11 = (((d[i7 & 255] ^ a(d[(i6 >> 8) & 255], 24)) ^ a(d[(i9 >> 16) & 255], 16)) ^ a(d[(i8 >> 24) & 255], 8)) ^ iArr[i10][2];
        int a12 = ((a(d[(i9 >> 8) & 255], 24) ^ d[i6 & 255]) ^ a(d[(i8 >> 16) & 255], 16)) ^ a(d[(i7 >> 24) & 255], 8);
        int i15 = i10 + 1;
        int i16 = iArr[i10][3] ^ a12;
        this.m = ((((f14451a[a9 & 255] & 255) ^ ((f14451a[(a10 >> 8) & 255] & 255) << 8)) ^ ((this.r[(a11 >> 16) & 255] & 255) << 16)) ^ (this.r[(i16 >> 24) & 255] << 24)) ^ iArr[i15][0];
        this.n = ((((this.r[a10 & 255] & 255) ^ ((f14451a[(a11 >> 8) & 255] & 255) << 8)) ^ ((f14451a[(i16 >> 16) & 255] & 255) << 16)) ^ (this.r[(a9 >> 24) & 255] << 24)) ^ iArr[i15][1];
        this.o = ((((this.r[a11 & 255] & 255) ^ ((f14451a[(i16 >> 8) & 255] & 255) << 8)) ^ ((f14451a[(a9 >> 16) & 255] & 255) << 16)) ^ (f14451a[(a10 >> 24) & 255] << 24)) ^ iArr[i15][2];
        this.p = iArr[i15][3] ^ ((((this.r[i16 & 255] & 255) ^ ((this.r[(a9 >> 8) & 255] & 255) << 8)) ^ ((this.r[(a10 >> 16) & 255] & 255) << 16)) ^ (f14451a[(a11 >> 24) & 255] << 24));
    }

    private void b(int[][] iArr) {
        char c2 = 0;
        int i2 = this.m ^ iArr[this.k][0];
        int i3 = 1;
        int i4 = this.n ^ iArr[this.k][1];
        int i5 = this.o ^ iArr[this.k][2];
        int i6 = this.k - 1;
        int i7 = this.p ^ iArr[this.k][3];
        while (i6 > i3) {
            int a2 = (((a(e[(i7 >> 8) & 255], 24) ^ e[i2 & 255]) ^ a(e[(i5 >> 16) & 255], 16)) ^ a(e[(i4 >> 24) & 255], 8)) ^ iArr[i6][c2];
            int a3 = (((a(e[(i2 >> 8) & 255], 24) ^ e[i4 & 255]) ^ a(e[(i7 >> 16) & 255], 16)) ^ a(e[(i5 >> 24) & 255], 8)) ^ iArr[i6][i3];
            int a4 = (((a(e[(i4 >> 8) & 255], 24) ^ e[i5 & 255]) ^ a(e[(i2 >> 16) & 255], 16)) ^ a(e[(i7 >> 24) & 255], 8)) ^ iArr[i6][2];
            int a5 = a(e[(i2 >> 24) & 255], 8) ^ (a(e[(i4 >> 16) & 255], 16) ^ (a(e[(i5 >> 8) & 255], 24) ^ e[i7 & 255]));
            int i8 = i6 - 1;
            int i9 = a5 ^ iArr[i6][3];
            int a6 = (((e[a2 & 255] ^ a(e[(i9 >> 8) & 255], 24)) ^ a(e[(a4 >> 16) & 255], 16)) ^ a(e[(a3 >> 24) & 255], 8)) ^ iArr[i8][0];
            int a7 = (((e[a3 & 255] ^ a(e[(a2 >> 8) & 255], 24)) ^ a(e[(i9 >> 16) & 255], 16)) ^ a(e[(a4 >> 24) & 255], 8)) ^ iArr[i8][1];
            int a8 = (((e[a4 & 255] ^ a(e[(a3 >> 8) & 255], 24)) ^ a(e[(a2 >> 16) & 255], 16)) ^ a(e[(i9 >> 24) & 255], 8)) ^ iArr[i8][2];
            int a9 = ((e[i9 & 255] ^ a(e[(a4 >> 8) & 255], 24)) ^ a(e[(a3 >> 16) & 255], 16)) ^ a(e[(a2 >> 24) & 255], 8);
            int i10 = i8 - 1;
            int i11 = a9 ^ iArr[i8][3];
            i4 = a7;
            i3 = 1;
            i6 = i10;
            c2 = 0;
            int i12 = a8;
            i7 = i11;
            i2 = a6;
            i5 = i12;
        }
        int a10 = (((e[i2 & 255] ^ a(e[(i7 >> 8) & 255], 24)) ^ a(e[(i5 >> 16) & 255], 16)) ^ a(e[(i4 >> 24) & 255], 8)) ^ iArr[i6][0];
        int a11 = (((e[i4 & 255] ^ a(e[(i2 >> 8) & 255], 24)) ^ a(e[(i7 >> 16) & 255], 16)) ^ a(e[(i5 >> 24) & 255], 8)) ^ iArr[i6][1];
        int a12 = (((e[i5 & 255] ^ a(e[(i4 >> 8) & 255], 24)) ^ a(e[(i2 >> 16) & 255], 16)) ^ a(e[(i7 >> 24) & 255], 8)) ^ iArr[i6][2];
        int a13 = (a(e[(i2 >> 24) & 255], 8) ^ (a(e[(i4 >> 16) & 255], 16) ^ (a(e[(i5 >> 8) & 255], 24) ^ e[i7 & 255]))) ^ iArr[i6][3];
        this.m = ((((b[a10 & 255] & 255) ^ ((this.r[(a13 >> 8) & 255] & 255) << 8)) ^ ((this.r[(a12 >> 16) & 255] & 255) << 16)) ^ (b[(a11 >> 24) & 255] << 24)) ^ iArr[0][0];
        this.n = ((((this.r[a11 & 255] & 255) ^ ((this.r[(a10 >> 8) & 255] & 255) << 8)) ^ ((b[(a13 >> 16) & 255] & 255) << 16)) ^ (this.r[(a12 >> 24) & 255] << 24)) ^ iArr[0][1];
        this.o = ((((this.r[a12 & 255] & 255) ^ ((b[(a11 >> 8) & 255] & 255) << 8)) ^ ((b[(a10 >> 16) & 255] & 255) << 16)) ^ (this.r[(a13 >> 24) & 255] << 24)) ^ iArr[0][2];
        this.p = iArr[0][3] ^ ((((b[a13 & 255] & 255) ^ ((this.r[(a12 >> 8) & 255] & 255) << 8)) ^ ((this.r[(a11 >> 16) & 255] & 255) << 16)) ^ (this.r[(a10 >> 24) & 255] << 24));
    }
}
