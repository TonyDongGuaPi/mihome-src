package com.mijia.model.local;

import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.Utils.FileUtil;
import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import com.mijia.model.BaseFileLoadManager;
import com.mijia.player.FileNamer;
import com.xiaomi.CameraDevice;
import com.xiaomi.smarthome.device.api.Callback;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class LocalFileManager extends BaseFileLoadManager {
    /* access modifiers changed from: private */
    public List<LocalFile> i = new CopyOnWriteArrayList();

    public String f() {
        return "com.mijia.camera.LocalFileManager";
    }

    public static class LocalFile {

        /* renamed from: a  reason: collision with root package name */
        public long f8057a;
        public long b;
        public File c;
        public String d;
        public boolean e;

        public boolean equals(Object obj) {
            if (!(obj instanceof LocalFile)) {
                return false;
            }
            LocalFile localFile = (LocalFile) obj;
            return localFile.e == this.e && this.d.equals(localFile.d);
        }
    }

    public LocalFileManager(CameraDevice cameraDevice) {
        super(cameraDevice);
    }

    public List<LocalFile> g() {
        return this.i;
    }

    public List<LocalFile> h() {
        ArrayList arrayList = new ArrayList();
        for (LocalFile next : this.i) {
            if (next.d.endsWith(".mp4")) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public List<LocalFile> i() {
        ArrayList arrayList = new ArrayList();
        for (LocalFile next : this.i) {
            if (!next.d.endsWith(".mp4")) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public void a(String str) {
        LocalFile a2;
        File file = new File(str);
        if (file.exists() && (a2 = a(file)) != null) {
            this.i.add(a2);
            d();
        }
    }

    public void b(Callback<Void> callback) {
        b(callback, true);
    }

    public void b(final Callback<Void> callback, boolean z) {
        if (!this.d || z) {
            new AsyncTask<Void, Void, List<LocalFile>>() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public List<LocalFile> doInBackground(Void... voidArr) {
                    ArrayList arrayList = new ArrayList();
                    File[] listFiles = new File(FileUtil.a(LocalFileManager.this.g.getDid())).listFiles(new FilenameFilter() {
                        public boolean accept(File file, String str) {
                            return str.endsWith(".jpg") || str.endsWith(".png") || str.endsWith(".mp4");
                        }
                    });
                    if (listFiles != null) {
                        for (File a2 : listFiles) {
                            LocalFile a3 = LocalFileManager.this.a(a2);
                            if (a3 != null) {
                                arrayList.add(a3);
                            }
                        }
                    }
                    Collections.sort(arrayList, new LocalFileCompare());
                    return arrayList;
                }

                /* access modifiers changed from: protected */
                /* renamed from: a */
                public void onPostExecute(List<LocalFile> list) {
                    super.onPostExecute(list);
                    List unused = LocalFileManager.this.i = new CopyOnWriteArrayList(list);
                    callback.onSuccess(null);
                }
            }.execute((Void[]) null);
            return;
        }
        SDKLog.d(Tag.d, "mHasDataSynced");
        if (callback != null) {
            callback.onSuccess(null);
        }
    }

    /* access modifiers changed from: protected */
    public void a(final Callback<Void> callback) {
        new AsyncTask<Void, Void, List<LocalFile>>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public List<LocalFile> doInBackground(Void... voidArr) {
                ArrayList arrayList = new ArrayList();
                File[] listFiles = new File(FileUtil.a(LocalFileManager.this.g.getDid())).listFiles(new FilenameFilter() {
                    public boolean accept(File file, String str) {
                        return str.endsWith(".jpg") || str.endsWith(".png") || str.endsWith(".mp4");
                    }
                });
                if (listFiles != null) {
                    for (File a2 : listFiles) {
                        LocalFile a3 = LocalFileManager.this.a(a2);
                        if (a3 != null) {
                            arrayList.add(a3);
                        }
                    }
                }
                Collections.sort(arrayList, new LocalFileCompare());
                return arrayList;
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(List<LocalFile> list) {
                super.onPostExecute(list);
                List unused = LocalFileManager.this.i = new CopyOnWriteArrayList(list);
                if (callback != null) {
                    callback.onSuccess(null);
                }
                LocalFileManager.this.e();
            }
        }.execute((Void[]) null);
    }

    /* access modifiers changed from: private */
    public LocalFile a(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        String name = file.getName();
        if (name.endsWith(".jpg") || name.endsWith(".png")) {
            LocalFile localFile = new LocalFile();
            localFile.c = file;
            localFile.d = Uri.fromFile(file).toString();
            localFile.e = false;
            localFile.f8057a = FileNamer.a().a(name.substring(0, name.length() - 4), false);
            return localFile;
        } else if (!name.endsWith(".mp4")) {
            return null;
        } else {
            LocalFile localFile2 = new LocalFile();
            localFile2.c = file;
            localFile2.d = Uri.fromFile(file).toString();
            localFile2.e = true;
            localFile2.f8057a = FileNamer.a().a(name.substring(0, name.length() - 4), true);
            b(localFile2);
            return localFile2;
        }
    }

    private void b(LocalFile localFile) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            mediaMetadataRetriever.setDataSource(localFile.c.getAbsolutePath());
            String extractMetadata = mediaMetadataRetriever.extractMetadata(9);
            if (!TextUtils.isEmpty(extractMetadata)) {
                localFile.b = Long.valueOf(extractMetadata).longValue();
            }
        } catch (Exception e) {
            localFile.b = 0;
            e.printStackTrace();
        } catch (Throwable th) {
            mediaMetadataRetriever.release();
            throw th;
        }
        mediaMetadataRetriever.release();
    }

    public void a(final List<LocalFile> list, final Callback<Void> callback) {
        if (list == null || list.isEmpty()) {
            callback.onSuccess(null);
            return;
        }
        this.i.removeAll(list);
        new Thread(new Runnable() {
            public void run() {
                for (LocalFile localFile : list) {
                    localFile.c.delete();
                }
                callback.onSuccess(null);
            }
        }).start();
    }

    public void a(LocalFile localFile) {
        localFile.c.delete();
        this.i.remove(localFile);
        d();
    }

    public LocalFile b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (LocalFile next : this.i) {
            if (next.d.endsWith(str)) {
                return next;
            }
        }
        LocalFile a2 = a(new File(str));
        if (a2 != null) {
            this.i.add(a2);
            d();
        }
        return a2;
    }

    public LocalFile a(long j) {
        for (LocalFile next : this.i) {
            if (next.f8057a == j) {
                return next;
            }
        }
        LocalFile a2 = a(new File(FileUtil.a(j, true, this.g.getDid())));
        if (a2 != null) {
            this.i.add(a2);
            d();
        }
        return a2;
    }
}
