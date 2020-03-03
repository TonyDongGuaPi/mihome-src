package org.xutils.http.body;

import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

public class FileBody extends InputStreamBody {

    /* renamed from: a  reason: collision with root package name */
    private File f10776a;
    private String b;

    public FileBody(File file) throws IOException {
        this(file, (String) null);
    }

    public FileBody(File file, String str) throws IOException {
        super(new FileInputStream(file));
        this.f10776a = file;
        this.b = str;
    }

    public void a(String str) {
        this.b = str;
    }

    public String a() {
        if (TextUtils.isEmpty(this.b)) {
            this.b = a(this.f10776a);
        }
        return this.b;
    }

    public static String a(File file) {
        String guessContentTypeFromName = HttpURLConnection.guessContentTypeFromName(file.getName());
        if (TextUtils.isEmpty(guessContentTypeFromName)) {
            return "application/octet-stream";
        }
        return guessContentTypeFromName.replaceFirst("\\/jpg$", "/jpeg");
    }
}
