package org.jacoco.agent.rt.internal_8ff85ea.core.runtime;

import com.taobao.weex.el.parse.Operators;
import java.util.regex.Pattern;

public class WildcardMatcher {

    /* renamed from: a  reason: collision with root package name */
    private final Pattern f3634a;

    public WildcardMatcher(String str) {
        String[] split = str.split("\\:");
        StringBuilder sb = new StringBuilder(str.length() * 2);
        int length = split.length;
        int i = 0;
        boolean z = false;
        while (i < length) {
            String str2 = split[i];
            if (z) {
                sb.append('|');
            }
            sb.append(Operators.BRACKET_START);
            sb.append(b(str2));
            sb.append(Operators.BRACKET_END);
            i++;
            z = true;
        }
        this.f3634a = Pattern.compile(sb.toString());
    }

    private static CharSequence b(String str) {
        StringBuilder sb = new StringBuilder(str.length() * 2);
        for (char c : str.toCharArray()) {
            if (c == '*') {
                sb.append(".*");
            } else if (c != '?') {
                sb.append(Pattern.quote(String.valueOf(c)));
            } else {
                sb.append(".?");
            }
        }
        return sb;
    }

    public boolean a(String str) {
        return this.f3634a.matcher(str).matches();
    }
}
