package com.youpin.weex.app.util;

import android.content.Context;
import android.util.Log;
import java.io.IOException;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AppConfigXmlParser {

    /* renamed from: a  reason: collision with root package name */
    private static String f2541a = "AppConfigXmlParser";
    private AppPreferences b = new AppPreferences();

    private void c(XmlPullParser xmlPullParser) {
    }

    public AppPreferences a() {
        return this.b;
    }

    public synchronized void a(Context context) {
        int identifier = context.getResources().getIdentifier("app_config", "xml", context.getClass().getPackage().getName());
        if (identifier == 0 && (identifier = context.getResources().getIdentifier("app_config", "xml", context.getPackageName())) == 0) {
            Log.e(f2541a, "res/xml/app_config.xml is missing!");
        } else {
            a((XmlPullParser) context.getResources().getXml(identifier));
        }
    }

    public void a(XmlPullParser xmlPullParser) {
        int i = -1;
        while (i != 1) {
            if (i == 2) {
                b(xmlPullParser);
            } else if (i == 3) {
                c(xmlPullParser);
            }
            try {
                i = xmlPullParser.next();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void b(XmlPullParser xmlPullParser) {
        if (xmlPullParser.getName().equals("preference")) {
            this.b.a(xmlPullParser.getAttributeValue((String) null, "name").toLowerCase(Locale.ENGLISH), xmlPullParser.getAttributeValue((String) null, "value"));
        }
    }
}
