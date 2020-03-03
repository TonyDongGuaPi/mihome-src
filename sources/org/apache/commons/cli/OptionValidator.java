package org.apache.commons.cli;

class OptionValidator {
    OptionValidator() {
    }

    static void a(String str) throws IllegalArgumentException {
        if (str != null) {
            int i = 0;
            if (str.length() == 1) {
                char charAt = str.charAt(0);
                if (!a(charAt)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("illegal option value '");
                    stringBuffer.append(charAt);
                    stringBuffer.append("'");
                    throw new IllegalArgumentException(stringBuffer.toString());
                }
                return;
            }
            char[] charArray = str.toCharArray();
            while (i < charArray.length) {
                if (b(charArray[i])) {
                    i++;
                } else {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("opt contains illegal character value '");
                    stringBuffer2.append(charArray[i]);
                    stringBuffer2.append("'");
                    throw new IllegalArgumentException(stringBuffer2.toString());
                }
            }
        }
    }

    private static boolean a(char c) {
        return b(c) || c == ' ' || c == '?' || c == '@';
    }

    private static boolean b(char c) {
        return Character.isJavaIdentifierPart(c);
    }
}
