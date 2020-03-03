package com.liulishuo.filedownloader.connection;

import com.liulishuo.filedownloader.download.CustomComponentHolder;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RedirectHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final int f6416a = 10;
    private static final int b = 307;
    private static final int c = 308;

    private static boolean a(int i) {
        return i == 301 || i == 302 || i == 303 || i == 300 || i == 307 || i == 308;
    }

    public static FileDownloadConnection a(Map<String, List<String>> map, FileDownloadConnection fileDownloadConnection, List<String> list) throws IOException, IllegalAccessException {
        int e = fileDownloadConnection.e();
        String a2 = fileDownloadConnection.a("Location");
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (a(e)) {
            if (a2 != null) {
                if (FileDownloadLog.f6465a) {
                    FileDownloadLog.c(RedirectHandler.class, "redirect to %s with %d, %s", a2, Integer.valueOf(e), arrayList);
                }
                fileDownloadConnection.f();
                fileDownloadConnection = a(map, a2);
                arrayList.add(a2);
                fileDownloadConnection.d();
                e = fileDownloadConnection.e();
                a2 = fileDownloadConnection.a("Location");
                i++;
                if (i >= 10) {
                    throw new IllegalAccessException(FileDownloadUtils.a("redirect too many times! %s", arrayList));
                }
            } else {
                throw new IllegalAccessException(FileDownloadUtils.a("receive %d (redirect) but the location is null with response [%s]", Integer.valueOf(e), fileDownloadConnection.c()));
            }
        }
        if (list != null) {
            list.addAll(arrayList);
        }
        return fileDownloadConnection;
    }

    private static FileDownloadConnection a(Map<String, List<String>> map, String str) throws IOException {
        FileDownloadConnection a2 = CustomComponentHolder.a().a(str);
        for (Map.Entry next : map.entrySet()) {
            String str2 = (String) next.getKey();
            List<String> list = (List) next.getValue();
            if (list != null) {
                for (String a3 : list) {
                    a2.a(str2, a3);
                }
            }
        }
        return a2;
    }
}
