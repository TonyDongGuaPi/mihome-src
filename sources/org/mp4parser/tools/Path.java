package org.mp4parser.tools;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mp4parser.Box;
import org.mp4parser.Container;
import org.mp4parser.ParsableBox;
import org.mp4parser.support.AbstractContainerBox;

public class Path {

    /* renamed from: a  reason: collision with root package name */
    static Pattern f4110a = Pattern.compile("(....|\\.\\.)(\\[(.*)\\])?");

    private Path() {
    }

    public static <T extends Box> T a(Box box, String str) {
        List a2 = a((Object) box, str, true);
        if (a2.isEmpty()) {
            return null;
        }
        return (Box) a2.get(0);
    }

    public static <T extends Box> T a(Container container, String str) {
        List a2 = a(container, str, true);
        if (a2.isEmpty()) {
            return null;
        }
        return (Box) a2.get(0);
    }

    public static <T extends Box> T a(AbstractContainerBox abstractContainerBox, String str) {
        List a2 = a(abstractContainerBox, str, true);
        if (a2.isEmpty()) {
            return null;
        }
        return (Box) a2.get(0);
    }

    public static <T extends Box> List<T> b(Box box, String str) {
        return a((Object) box, str, false);
    }

    public static <T extends Box> List<T> b(Container container, String str) {
        return a(container, str, false);
    }

    private static <T extends Box> List<T> a(AbstractContainerBox abstractContainerBox, String str, boolean z) {
        return a((Object) abstractContainerBox, str, z);
    }

    private static <T extends Box> List<T> a(Container container, String str, boolean z) {
        return a((Object) container, str, z);
    }

    private static <T extends Box> List<T> a(ParsableBox parsableBox, String str, boolean z) {
        return a((Object) parsableBox, str, z);
    }

    private static <T extends Box> List<T> a(Object obj, String str, boolean z) {
        String str2;
        if (str.startsWith("/")) {
            throw new RuntimeException("Cannot start at / - only relative path expression into the structure are allowed");
        } else if (str.length() != 0) {
            int i = 0;
            if (str.contains("/")) {
                str2 = str.substring(str.indexOf(47) + 1);
                str = str.substring(0, str.indexOf(47));
            } else {
                str2 = "";
            }
            Matcher matcher = f4110a.matcher(str);
            if (matcher.matches()) {
                String group = matcher.group(1);
                if ("..".equals(group)) {
                    throw new RuntimeException(".. notation no longer allowed");
                } else if (!(obj instanceof Container)) {
                    return Collections.emptyList();
                } else {
                    int parseInt = matcher.group(2) != null ? Integer.parseInt(matcher.group(3)) : -1;
                    LinkedList linkedList = new LinkedList();
                    for (Box next : ((Container) obj).a()) {
                        if (next.ae_().matches(group)) {
                            if (parseInt == -1 || parseInt == i) {
                                linkedList.addAll(a((Object) next, str2, z));
                            }
                            i++;
                        }
                        if ((z || parseInt >= 0) && !linkedList.isEmpty()) {
                            return linkedList;
                        }
                    }
                    return linkedList;
                }
            } else {
                throw new RuntimeException(String.valueOf(str) + " is invalid path.");
            }
        } else if (obj instanceof ParsableBox) {
            return Collections.singletonList((Box) obj);
        } else {
            throw new RuntimeException("Result of path expression seems to be the root container. This is not allowed!");
        }
    }

    public static boolean a(Container container, Box box, String str) {
        return b(container, str).contains(box);
    }
}
