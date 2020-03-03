package com.liulishuo.filedownloader.services;

import android.util.SparseArray;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.services.FileDownloadDatabase;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NoDatabaseImpl implements FileDownloadDatabase {

    /* renamed from: a  reason: collision with root package name */
    private final SparseArray<FileDownloadModel> f6457a = new SparseArray<>();
    private final SparseArray<List<ConnectionModel>> b = new SparseArray<>();

    public void a(int i, int i2) {
    }

    public void a(int i, long j) {
    }

    public void a(int i, long j, String str, String str2) {
    }

    public void a(int i, String str, long j, long j2, int i2) {
    }

    public void a(int i, Throwable th) {
    }

    public void a(int i, Throwable th, long j) {
    }

    public void c(int i, long j) {
    }

    public void e(int i) {
    }

    protected NoDatabaseImpl() {
    }

    public static Maker c() {
        return new Maker();
    }

    public FileDownloadModel a(int i) {
        return this.f6457a.get(i);
    }

    public List<ConnectionModel> b(int i) {
        ArrayList arrayList = new ArrayList();
        List list = this.b.get(i);
        if (list != null) {
            arrayList.addAll(list);
        }
        return arrayList;
    }

    public void c(int i) {
        this.b.remove(i);
    }

    public void a(ConnectionModel connectionModel) {
        int a2 = connectionModel.a();
        List list = this.b.get(a2);
        if (list == null) {
            list = new ArrayList();
            this.b.put(a2, list);
        }
        list.add(connectionModel);
    }

    public void a(int i, int i2, long j) {
        List<ConnectionModel> list = this.b.get(i);
        if (list != null) {
            for (ConnectionModel connectionModel : list) {
                if (connectionModel.b() == i2) {
                    connectionModel.b(j);
                    return;
                }
            }
        }
    }

    public void a(FileDownloadModel fileDownloadModel) {
        this.f6457a.put(fileDownloadModel.a(), fileDownloadModel);
    }

    public void b(FileDownloadModel fileDownloadModel) {
        if (fileDownloadModel == null) {
            FileDownloadLog.d(this, "update but model == null!", new Object[0]);
        } else if (a(fileDownloadModel.a()) != null) {
            this.f6457a.remove(fileDownloadModel.a());
            this.f6457a.put(fileDownloadModel.a(), fileDownloadModel);
        } else {
            a(fileDownloadModel);
        }
    }

    public boolean d(int i) {
        this.f6457a.remove(i);
        return true;
    }

    public void a() {
        this.f6457a.clear();
    }

    public void b(int i, long j) {
        d(i);
    }

    public FileDownloadDatabase.Maintainer b() {
        return new Maintainer();
    }

    class Maintainer implements FileDownloadDatabase.Maintainer {
        public void a() {
        }

        public void a(int i, FileDownloadModel fileDownloadModel) {
        }

        public void a(FileDownloadModel fileDownloadModel) {
        }

        public void b(FileDownloadModel fileDownloadModel) {
        }

        Maintainer() {
        }

        public Iterator<FileDownloadModel> iterator() {
            return new MaintainerIterator();
        }
    }

    class MaintainerIterator implements Iterator<FileDownloadModel> {
        /* renamed from: a */
        public FileDownloadModel next() {
            return null;
        }

        public boolean hasNext() {
            return false;
        }

        public void remove() {
        }

        MaintainerIterator() {
        }
    }

    public static class Maker implements FileDownloadHelper.DatabaseCustomMaker {
        public FileDownloadDatabase a() {
            return new NoDatabaseImpl();
        }
    }
}
