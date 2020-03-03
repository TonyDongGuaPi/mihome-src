package org.jacoco.agent.rt.internal_8ff85ea;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class ConfigLoader {

    /* renamed from: a  reason: collision with root package name */
    private static final String f3579a = "jacoco-agent.";
    private static final Pattern b = Pattern.compile("\\$\\{([^\\}]+)\\}");

    static Properties a(String str, Properties properties) {
        Properties properties2 = new Properties();
        b(str, properties2);
        a(properties, properties2);
        b(properties2, properties);
        return properties2;
    }

    private static void b(String str, Properties properties) {
        InputStream resourceAsStream = Offline.class.getResourceAsStream(str);
        if (resourceAsStream != null) {
            try {
                properties.load(resourceAsStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void a(Properties properties, Properties properties2) {
        for (Map.Entry entry : properties.entrySet()) {
            String obj = entry.getKey().toString();
            if (obj.startsWith(f3579a)) {
                properties2.put(obj.substring(f3579a.length()), entry.getValue());
            }
        }
    }

    private static void b(Properties properties, Properties properties2) {
        for (Map.Entry entry : properties.entrySet()) {
            String str = (String) entry.getValue();
            StringBuilder sb = new StringBuilder();
            Matcher matcher = b.matcher(str);
            int i = 0;
            while (matcher.find()) {
                sb.append(str.substring(i, matcher.start()));
                String property = properties2.getProperty(matcher.group(1));
                if (property == null) {
                    property = matcher.group(0);
                }
                sb.append(property);
                i = matcher.end();
            }
            sb.append(str.substring(i));
            entry.setValue(sb.toString());
        }
    }

    private ConfigLoader() {
    }
}
