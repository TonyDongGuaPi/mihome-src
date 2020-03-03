package com.xiaomi.push;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.cybergarage.http.HTTP;

class cp {

    /* renamed from: a  reason: collision with root package name */
    private StringBuilder f12678a;
    private File b;

    public void a() {
        try {
            FileWriter fileWriter = new FileWriter(this.b, true);
            fileWriter.write(this.f12678a.toString());
            fileWriter.flush();
            fileWriter.close();
            this.f12678a.delete(0, this.f12678a.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void a(co coVar) {
        StringBuilder sb = this.f12678a;
        sb.append(coVar.f12677a + HTTP.TAB);
        StringBuilder sb2 = this.f12678a;
        sb2.append(coVar.b + HTTP.TAB + coVar.c);
        this.f12678a.append("\r\n");
    }
}
