package com.xiaomi.mimc.protobuf;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mimc.protobuf.GeneratedMessageLite;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

final class MessageLiteToString {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11335a = "List";
    private static final String b = "OrBuilderList";
    private static final String c = "Bytes";

    MessageLiteToString() {
    }

    static String a(MessageLite messageLite, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        a(messageLite, sb, 0);
        return sb.toString();
    }

    private static void a(MessageLite messageLite, StringBuilder sb, int i) {
        boolean z;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet<>();
        for (Method method : messageLite.getClass().getDeclaredMethods()) {
            hashMap2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                hashMap.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String replaceFirst : treeSet) {
            String replaceFirst2 = replaceFirst.replaceFirst("get", "");
            if (replaceFirst2.endsWith(f11335a) && !replaceFirst2.endsWith(b)) {
                String str = replaceFirst2.substring(0, 1).toLowerCase() + replaceFirst2.substring(1, replaceFirst2.length() - f11335a.length());
                Method method2 = (Method) hashMap.get("get" + replaceFirst2);
                if (method2 != null) {
                    a(sb, i, a(str), GeneratedMessageLite.a(method2, (Object) messageLite, new Object[0]));
                }
            }
            if (((Method) hashMap2.get("set" + replaceFirst2)) != null) {
                if (replaceFirst2.endsWith(c)) {
                    if (hashMap.containsKey("get" + replaceFirst2.substring(0, replaceFirst2.length() - c.length()))) {
                    }
                }
                String str2 = replaceFirst2.substring(0, 1).toLowerCase() + replaceFirst2.substring(1);
                Method method3 = (Method) hashMap.get("get" + replaceFirst2);
                Method method4 = (Method) hashMap.get("has" + replaceFirst2);
                if (method3 != null) {
                    Object a2 = GeneratedMessageLite.a(method3, (Object) messageLite, new Object[0]);
                    if (method4 == null) {
                        z = !a(a2);
                    } else {
                        z = ((Boolean) GeneratedMessageLite.a(method4, (Object) messageLite, new Object[0])).booleanValue();
                    }
                    if (z) {
                        a(sb, i, a(str2), a2);
                    }
                }
            }
        }
        if (messageLite instanceof GeneratedMessageLite.ExtendableMessage) {
            Iterator<Map.Entry<GeneratedMessageLite.ExtensionDescriptor, Object>> h = ((GeneratedMessageLite.ExtendableMessage) messageLite).f11313a.h();
            while (h.hasNext()) {
                Map.Entry next = h.next();
                a(sb, i, Operators.ARRAY_START_STR + ((GeneratedMessageLite.ExtensionDescriptor) next.getKey()).a() + Operators.ARRAY_END_STR, next.getValue());
            }
        }
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) messageLite;
        if (generatedMessageLite.n != null) {
            generatedMessageLite.n.a(sb, i);
        }
    }

    private static boolean a(Object obj) {
        if (obj instanceof Boolean) {
            return !((Boolean) obj).booleanValue();
        }
        if (obj instanceof Integer) {
            if (((Integer) obj).intValue() == 0) {
                return true;
            }
            return false;
        } else if (obj instanceof Float) {
            if (((Float) obj).floatValue() == 0.0f) {
                return true;
            }
            return false;
        } else if (obj instanceof Double) {
            if (((Double) obj).doubleValue() == 0.0d) {
                return true;
            }
            return false;
        } else if (obj instanceof String) {
            return obj.equals("");
        } else {
            if (obj instanceof ByteString) {
                return obj.equals(ByteString.EMPTY);
            }
            if (obj instanceof MessageLite) {
                if (obj == ((MessageLite) obj).aa()) {
                    return true;
                }
                return false;
            } else if (!(obj instanceof Enum) || ((Enum) obj).ordinal() != 0) {
                return false;
            } else {
                return true;
            }
        }
    }

    static final void a(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            for (Object a2 : (List) obj) {
                a(sb, i, str, a2);
            }
            return;
        }
        sb.append(10);
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(' ');
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"");
            sb.append(TextFormatEscaper.a((String) obj));
            sb.append('\"');
        } else if (obj instanceof ByteString) {
            sb.append(": \"");
            sb.append(TextFormatEscaper.a((ByteString) obj));
            sb.append('\"');
        } else if (obj instanceof GeneratedMessageLite) {
            sb.append(" {");
            a((GeneratedMessageLite) obj, sb, i + 2);
            sb.append("\n");
            for (int i3 = 0; i3 < i; i3++) {
                sb.append(' ');
            }
            sb.append("}");
        } else {
            sb.append(": ");
            sb.append(obj.toString());
        }
    }

    private static final String a(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                sb.append(JSMethod.NOT_SET);
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }
}
