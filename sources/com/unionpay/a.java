package com.unionpay;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.exoplayer2.C;
import com.mi.global.bbs.utils.Constants;
import com.unionpay.utils.UPUtils;
import com.unionpay.utils.b;
import com.unionpay.utils.j;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

final class a extends BroadcastReceiver {
    a() {
    }

    public final void onReceive(Context context, Intent intent) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Constants.TitleMenu.MENU_DOWNLOAD);
        long longExtra = intent.getLongExtra(com.xiaomi.smarthome.download.DownloadManager.G, -1);
        long c = UPUtils.c(context, "id");
        if (c == longExtra) {
            Intent intent2 = new Intent("android.intent.action.VIEW");
            Uri uriForDownloadedFile = downloadManager.getUriForDownloadedFile(c);
            String str = "";
            if (uriForDownloadedFile != null) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(new File(uriForDownloadedFile.getPath()));
                    String absolutePath = context.getFilesDir().getAbsolutePath();
                    if (absolutePath != null) {
                        str = absolutePath + File.separator + UPPayAssistEx.N;
                        FileOutputStream openFileOutput = context.openFileOutput(UPPayAssistEx.N, 1);
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            openFileOutput.write(bArr, 0, read);
                        }
                        openFileOutput.close();
                        fileInputStream.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    str = uriForDownloadedFile.getPath();
                }
                try {
                    if (!UPPayAssistEx.L.equalsIgnoreCase(b.b(str))) {
                        b.c(uriForDownloadedFile.getPath());
                    } else if (!UPPayAssistEx.c(UPPayAssistEx.G)) {
                        intent2.setDataAndType(Uri.parse("file:" + str), UPPayAssistEx.S);
                        intent2.addFlags(C.ENCODING_PCM_MU_LAW);
                        context.startActivity(intent2);
                    }
                } catch (FileNotFoundException unused) {
                }
                j.b("uppay", "downloadFileUri" + uriForDownloadedFile);
            }
            context.unregisterReceiver(UPPayAssistEx.Y);
            boolean unused2 = UPPayAssistEx.M = false;
        }
    }
}
