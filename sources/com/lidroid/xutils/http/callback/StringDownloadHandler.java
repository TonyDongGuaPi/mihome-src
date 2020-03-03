package com.lidroid.xutils.http.callback;

import com.lidroid.xutils.util.IOUtils;
import com.lidroid.xutils.util.OtherUtils;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;

public class StringDownloadHandler {
    public String a(HttpEntity httpEntity, RequestCallBackHandler requestCallBackHandler, String str) throws IOException {
        InputStream inputStream;
        if (httpEntity == null) {
            return null;
        }
        long j = 0;
        long contentLength = httpEntity.getContentLength();
        if (requestCallBackHandler != null && !requestCallBackHandler.a(contentLength, 0, true)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            inputStream = httpEntity.getContent();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, str));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        sb.append(readLine);
                        sb.append(10);
                        j += OtherUtils.a(readLine, str);
                        if (requestCallBackHandler != null && !requestCallBackHandler.a(contentLength, j, false)) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                long j2 = j;
                if (requestCallBackHandler != null) {
                    requestCallBackHandler.a(contentLength, j2, true);
                }
                IOUtils.a((Closeable) inputStream);
                return sb.toString().trim();
            } catch (Throwable th) {
                th = th;
                IOUtils.a((Closeable) inputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            IOUtils.a((Closeable) inputStream);
            throw th;
        }
    }
}
