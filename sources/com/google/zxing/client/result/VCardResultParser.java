package com.google.zxing.client.result;

import com.alipay.sdk.util.i;
import com.google.zxing.Result;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class VCardResultParser extends ResultParser {
    private static final Pattern BEGIN_VCARD = Pattern.compile("BEGIN:VCARD", 2);
    private static final Pattern COMMA = Pattern.compile(",");
    private static final Pattern CR_LF_SPACE_TAB = Pattern.compile("\r\n[ \t]");
    private static final Pattern EQUALS = Pattern.compile("=");
    private static final Pattern NEWLINE_ESCAPE = Pattern.compile("\\\\[nN]");
    private static final Pattern SEMICOLON = Pattern.compile(i.b);
    private static final Pattern SEMICOLON_OR_COMMA = Pattern.compile("[;,]");
    private static final Pattern UNESCAPED_SEMICOLONS = Pattern.compile("(?<!\\\\);+");
    private static final Pattern VCARD_ESCAPES = Pattern.compile("\\\\([,;\\\\])");
    private static final Pattern VCARD_LIKE_DATE = Pattern.compile("\\d{4}-?\\d{2}-?\\d{2}");

    public AddressBookParsedResult parse(Result result) {
        String[] strArr;
        String[] strArr2;
        String massagedText = getMassagedText(result);
        Matcher matcher = BEGIN_VCARD.matcher(massagedText);
        if (!matcher.find() || matcher.start() != 0) {
            return null;
        }
        List<List<String>> matchVCardPrefixedField = matchVCardPrefixedField("FN", massagedText, true, false);
        if (matchVCardPrefixedField == null) {
            matchVCardPrefixedField = matchVCardPrefixedField("N", massagedText, true, false);
            formatNames(matchVCardPrefixedField);
        }
        List<String> matchSingleVCardPrefixedField = matchSingleVCardPrefixedField("NICKNAME", massagedText, true, false);
        if (matchSingleVCardPrefixedField == null) {
            strArr = null;
        } else {
            strArr = COMMA.split(matchSingleVCardPrefixedField.get(0));
        }
        List<List<String>> matchVCardPrefixedField2 = matchVCardPrefixedField("TEL", massagedText, true, false);
        List<List<String>> matchVCardPrefixedField3 = matchVCardPrefixedField("EMAIL", massagedText, true, false);
        List<String> matchSingleVCardPrefixedField2 = matchSingleVCardPrefixedField("NOTE", massagedText, false, false);
        List<List<String>> matchVCardPrefixedField4 = matchVCardPrefixedField("ADR", massagedText, true, true);
        List<String> matchSingleVCardPrefixedField3 = matchSingleVCardPrefixedField("ORG", massagedText, true, true);
        List<String> matchSingleVCardPrefixedField4 = matchSingleVCardPrefixedField("BDAY", massagedText, true, false);
        List<String> list = (matchSingleVCardPrefixedField4 == null || isLikeVCardDate(matchSingleVCardPrefixedField4.get(0))) ? matchSingleVCardPrefixedField4 : null;
        List<String> matchSingleVCardPrefixedField5 = matchSingleVCardPrefixedField("TITLE", massagedText, true, false);
        List<List<String>> matchVCardPrefixedField5 = matchVCardPrefixedField(DTransferConstants.f, massagedText, true, false);
        List<String> matchSingleVCardPrefixedField6 = matchSingleVCardPrefixedField("IMPP", massagedText, true, false);
        List<String> matchSingleVCardPrefixedField7 = matchSingleVCardPrefixedField("GEO", massagedText, true, false);
        if (matchSingleVCardPrefixedField7 == null) {
            strArr2 = null;
        } else {
            strArr2 = SEMICOLON_OR_COMMA.split(matchSingleVCardPrefixedField7.get(0));
        }
        return new AddressBookParsedResult(toPrimaryValues(matchVCardPrefixedField), strArr, (String) null, toPrimaryValues(matchVCardPrefixedField2), toTypes(matchVCardPrefixedField2), toPrimaryValues(matchVCardPrefixedField3), toTypes(matchVCardPrefixedField3), toPrimaryValue(matchSingleVCardPrefixedField6), toPrimaryValue(matchSingleVCardPrefixedField2), toPrimaryValues(matchVCardPrefixedField4), toTypes(matchVCardPrefixedField4), toPrimaryValue(matchSingleVCardPrefixedField3), toPrimaryValue(list), toPrimaryValue(matchSingleVCardPrefixedField5), toPrimaryValues(matchVCardPrefixedField5), (strArr2 == null || strArr2.length == 2) ? strArr2 : null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:78:0x0165  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0172  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.util.List<java.util.List<java.lang.String>> matchVCardPrefixedField(java.lang.CharSequence r16, java.lang.String r17, boolean r18, boolean r19) {
        /*
            r0 = r17
            int r1 = r17.length()
            r3 = 0
            r4 = 0
            r5 = 0
        L_0x0009:
            if (r4 >= r1) goto L_0x0182
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "(?:^|\n)"
            r6.<init>(r7)
            r7 = r16
            r6.append(r7)
            java.lang.String r8 = "(?:;([^:]*))?:"
            r6.append(r8)
            java.lang.String r6 = r6.toString()
            r8 = 2
            java.util.regex.Pattern r6 = java.util.regex.Pattern.compile(r6, r8)
            java.util.regex.Matcher r6 = r6.matcher(r0)
            if (r4 <= 0) goto L_0x002d
            int r4 = r4 + -1
        L_0x002d:
            boolean r4 = r6.find(r4)
            if (r4 == 0) goto L_0x0182
            int r4 = r6.end(r3)
            r9 = 1
            java.lang.String r6 = r6.group(r9)
            if (r6 == 0) goto L_0x0092
            java.util.regex.Pattern r10 = SEMICOLON
            java.lang.String[] r6 = r10.split(r6)
            int r10 = r6.length
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
        L_0x004a:
            if (r11 >= r10) goto L_0x0090
            r2 = r6[r11]
            if (r12 != 0) goto L_0x0055
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>(r9)
        L_0x0055:
            r12.add(r2)
            java.util.regex.Pattern r3 = EQUALS
            java.lang.String[] r2 = r3.split(r2, r8)
            int r3 = r2.length
            if (r3 <= r9) goto L_0x008b
            r3 = 0
            r8 = r2[r3]
            r2 = r2[r9]
            java.lang.String r3 = "ENCODING"
            boolean r3 = r3.equalsIgnoreCase(r8)
            if (r3 == 0) goto L_0x0078
            java.lang.String r3 = "QUOTED-PRINTABLE"
            boolean r3 = r3.equalsIgnoreCase(r2)
            if (r3 == 0) goto L_0x0078
            r13 = 1
            goto L_0x008b
        L_0x0078:
            java.lang.String r3 = "CHARSET"
            boolean r3 = r3.equalsIgnoreCase(r8)
            if (r3 == 0) goto L_0x0082
            r14 = r2
            goto L_0x008b
        L_0x0082:
            java.lang.String r3 = "VALUE"
            boolean r3 = r3.equalsIgnoreCase(r8)
            if (r3 == 0) goto L_0x008b
            r15 = r2
        L_0x008b:
            int r11 = r11 + 1
            r3 = 0
            r8 = 2
            goto L_0x004a
        L_0x0090:
            r2 = r15
            goto L_0x0096
        L_0x0092:
            r2 = 0
            r12 = 0
            r13 = 0
            r14 = 0
        L_0x0096:
            r3 = r4
        L_0x0097:
            r6 = 10
            int r3 = r0.indexOf(r6, r3)
            if (r3 < 0) goto L_0x00da
            int r6 = r17.length()
            int r6 = r6 - r9
            if (r3 >= r6) goto L_0x00bb
            int r6 = r3 + 1
            char r8 = r0.charAt(r6)
            r10 = 32
            if (r8 == r10) goto L_0x00b8
            char r6 = r0.charAt(r6)
            r8 = 9
            if (r6 != r8) goto L_0x00bb
        L_0x00b8:
            int r3 = r3 + 2
            goto L_0x0097
        L_0x00bb:
            if (r13 == 0) goto L_0x00da
            r6 = 61
            if (r3 <= 0) goto L_0x00cc
            int r8 = r3 + -1
            char r8 = r0.charAt(r8)
            if (r8 == r6) goto L_0x00ca
            goto L_0x00cc
        L_0x00ca:
            r8 = 2
            goto L_0x00d7
        L_0x00cc:
            r8 = 2
            if (r3 < r8) goto L_0x00da
            int r10 = r3 + -2
            char r10 = r0.charAt(r10)
            if (r10 != r6) goto L_0x00da
        L_0x00d7:
            int r3 = r3 + 1
            goto L_0x0097
        L_0x00da:
            if (r3 >= 0) goto L_0x00e0
            r4 = r1
        L_0x00dd:
            r3 = 0
            goto L_0x0009
        L_0x00e0:
            if (r3 <= r4) goto L_0x017c
            if (r5 != 0) goto L_0x00e9
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>(r9)
        L_0x00e9:
            if (r3 <= 0) goto L_0x00f7
            int r6 = r3 + -1
            char r6 = r0.charAt(r6)
            r8 = 13
            if (r6 != r8) goto L_0x00f7
            int r3 = r3 + -1
        L_0x00f7:
            java.lang.String r4 = r0.substring(r4, r3)
            if (r18 == 0) goto L_0x0101
            java.lang.String r4 = r4.trim()
        L_0x0101:
            if (r13 == 0) goto L_0x011a
            java.lang.String r4 = decodeQuotedPrintable(r4, r14)
            if (r19 == 0) goto L_0x0150
            java.util.regex.Pattern r8 = UNESCAPED_SEMICOLONS
            java.util.regex.Matcher r4 = r8.matcher(r4)
            java.lang.String r8 = "\n"
            java.lang.String r4 = r4.replaceAll(r8)
            java.lang.String r4 = r4.trim()
            goto L_0x0150
        L_0x011a:
            if (r19 == 0) goto L_0x012c
            java.util.regex.Pattern r8 = UNESCAPED_SEMICOLONS
            java.util.regex.Matcher r4 = r8.matcher(r4)
            java.lang.String r8 = "\n"
            java.lang.String r4 = r4.replaceAll(r8)
            java.lang.String r4 = r4.trim()
        L_0x012c:
            java.util.regex.Pattern r8 = CR_LF_SPACE_TAB
            java.util.regex.Matcher r4 = r8.matcher(r4)
            java.lang.String r8 = ""
            java.lang.String r4 = r4.replaceAll(r8)
            java.util.regex.Pattern r8 = NEWLINE_ESCAPE
            java.util.regex.Matcher r4 = r8.matcher(r4)
            java.lang.String r8 = "\n"
            java.lang.String r4 = r4.replaceAll(r8)
            java.util.regex.Pattern r8 = VCARD_ESCAPES
            java.util.regex.Matcher r4 = r8.matcher(r4)
            java.lang.String r8 = "$1"
            java.lang.String r4 = r4.replaceAll(r8)
        L_0x0150:
            java.lang.String r8 = "uri"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x0162
            java.net.URI r2 = java.net.URI.create(r4)     // Catch:{ IllegalArgumentException -> 0x0162 }
            java.lang.String r2 = r2.getSchemeSpecificPart()     // Catch:{ IllegalArgumentException -> 0x0162 }
            goto L_0x0163
        L_0x0162:
            r2 = r4
        L_0x0163:
            if (r12 != 0) goto L_0x0172
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>(r9)
            r4.add(r2)
            r5.add(r4)
            r4 = 0
            goto L_0x0179
        L_0x0172:
            r4 = 0
            r12.add(r4, r2)
            r5.add(r12)
        L_0x0179:
            int r2 = r3 + 1
            goto L_0x017f
        L_0x017c:
            r4 = 0
            int r2 = r3 + 1
        L_0x017f:
            r4 = r2
            goto L_0x00dd
        L_0x0182:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.client.result.VCardResultParser.matchVCardPrefixedField(java.lang.CharSequence, java.lang.String, boolean, boolean):java.util.List");
    }

    private static String decodeQuotedPrintable(CharSequence charSequence, String str) {
        char charAt;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i < length) {
            char charAt2 = charSequence.charAt(i);
            if (!(charAt2 == 10 || charAt2 == 13)) {
                if (charAt2 != '=') {
                    maybeAppendFragment(byteArrayOutputStream, str, sb);
                    sb.append(charAt2);
                } else if (!(i >= length - 2 || (charAt = charSequence.charAt(i + 1)) == 13 || charAt == 10)) {
                    i += 2;
                    char charAt3 = charSequence.charAt(i);
                    int parseHexDigit = parseHexDigit(charAt);
                    int parseHexDigit2 = parseHexDigit(charAt3);
                    if (parseHexDigit >= 0 && parseHexDigit2 >= 0) {
                        byteArrayOutputStream.write((parseHexDigit << 4) + parseHexDigit2);
                    }
                }
            }
            i++;
        }
        maybeAppendFragment(byteArrayOutputStream, str, sb);
        return sb.toString();
    }

    private static void maybeAppendFragment(ByteArrayOutputStream byteArrayOutputStream, String str, StringBuilder sb) {
        String str2;
        if (byteArrayOutputStream.size() > 0) {
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (str == null) {
                str2 = new String(byteArray, StandardCharsets.UTF_8);
            } else {
                try {
                    str2 = new String(byteArray, str);
                } catch (UnsupportedEncodingException unused) {
                    str2 = new String(byteArray, StandardCharsets.UTF_8);
                }
            }
            byteArrayOutputStream.reset();
            sb.append(str2);
        }
    }

    static List<String> matchSingleVCardPrefixedField(CharSequence charSequence, String str, boolean z, boolean z2) {
        List<List<String>> matchVCardPrefixedField = matchVCardPrefixedField(charSequence, str, z, z2);
        if (matchVCardPrefixedField == null || matchVCardPrefixedField.isEmpty()) {
            return null;
        }
        return matchVCardPrefixedField.get(0);
    }

    private static String toPrimaryValue(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    private static String[] toPrimaryValues(Collection<List<String>> collection) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (List<String> list : collection) {
            String str = (String) list.get(0);
            if (str != null && !str.isEmpty()) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private static String[] toTypes(Collection<List<String>> collection) {
        String str;
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (List next : collection) {
            String str2 = (String) next.get(0);
            if (str2 != null && !str2.isEmpty()) {
                int i = 1;
                while (true) {
                    if (i >= next.size()) {
                        str = null;
                        break;
                    }
                    String str3 = (String) next.get(i);
                    int indexOf = str3.indexOf(61);
                    if (indexOf < 0) {
                        str = str3;
                        break;
                    } else if ("TYPE".equalsIgnoreCase(str3.substring(0, indexOf))) {
                        str = str3.substring(indexOf + 1);
                        break;
                    } else {
                        i++;
                    }
                }
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private static boolean isLikeVCardDate(CharSequence charSequence) {
        return charSequence == null || VCARD_LIKE_DATE.matcher(charSequence).matches();
    }

    private static void formatNames(Iterable<List<String>> iterable) {
        int indexOf;
        if (iterable != null) {
            for (List next : iterable) {
                String str = (String) next.get(0);
                String[] strArr = new String[5];
                int i = 0;
                int i2 = 0;
                while (i < 4 && (indexOf = str.indexOf(59, i2)) >= 0) {
                    strArr[i] = str.substring(i2, indexOf);
                    i++;
                    i2 = indexOf + 1;
                }
                strArr[i] = str.substring(i2);
                StringBuilder sb = new StringBuilder(100);
                maybeAppendComponent(strArr, 3, sb);
                maybeAppendComponent(strArr, 1, sb);
                maybeAppendComponent(strArr, 2, sb);
                maybeAppendComponent(strArr, 0, sb);
                maybeAppendComponent(strArr, 4, sb);
                next.set(0, sb.toString().trim());
            }
        }
    }

    private static void maybeAppendComponent(String[] strArr, int i, StringBuilder sb) {
        if (strArr[i] != null && !strArr[i].isEmpty()) {
            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append(strArr[i]);
        }
    }
}
