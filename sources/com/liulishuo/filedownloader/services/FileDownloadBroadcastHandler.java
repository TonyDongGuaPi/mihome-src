package com.liulishuo.filedownloader.services;

import android.content.Intent;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

public class FileDownloadBroadcastHandler {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6453a = "filedownloader.intent.action.completed";
    public static final String b = "model";

    public static FileDownloadModel a(Intent intent) {
        if (f6453a.equals(intent.getAction())) {
            return (FileDownloadModel) intent.getParcelableExtra("model");
        }
        throw new IllegalArgumentException(FileDownloadUtils.a("can't recognize the intent with action %s, on the current version we only support action [%s]", intent.getAction(), f6453a));
    }

    public static void a(FileDownloadModel fileDownloadModel) {
        if (fileDownloadModel == null) {
            throw new IllegalArgumentException();
        } else if (fileDownloadModel.f() == -3) {
            Intent intent = new Intent(f6453a);
            intent.putExtra("model", fileDownloadModel);
            FileDownloadHelper.a().sendBroadcast(intent);
        } else {
            throw new IllegalStateException();
        }
    }
}
