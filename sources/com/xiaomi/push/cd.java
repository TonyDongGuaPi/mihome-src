package com.xiaomi.push;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.xiaomi.push.service.ah;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class cd {

    /* renamed from: a  reason: collision with root package name */
    private static volatile cd f12666a;
    private Context b;
    private cc c;
    private final HashMap<String, cb> d = new HashMap<>();
    private ThreadPoolExecutor e = new ThreadPoolExecutor(1, 1, 15, TimeUnit.SECONDS, new LinkedBlockingQueue());
    /* access modifiers changed from: private */
    public final ArrayList<a> f = new ArrayList<>();

    public static abstract class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private String f12667a;
        protected String b;
        protected cb c = null;
        private WeakReference<Context> d;
        private Random e = new Random();
        private int f = 0;
        private a g;

        public a(String str) {
            this.f12667a = str;
        }

        public Object a() {
            return null;
        }

        /* access modifiers changed from: package-private */
        public void a(Context context) {
            if (this.g != null) {
                this.g.a(context, a());
            }
            b(context);
        }

        public abstract void a(Context context, SQLiteDatabase sQLiteDatabase);

        public void a(Context context, Object obj) {
            cd.a(context).a(this);
        }

        /* access modifiers changed from: package-private */
        public void a(cb cbVar, Context context) {
            this.c = cbVar;
            this.b = this.c.a();
            this.d = new WeakReference<>(context);
        }

        public void a(a aVar) {
            this.g = aVar;
        }

        public void b(Context context) {
        }

        public boolean b() {
            return this.c == null || TextUtils.isEmpty(this.b) || this.d == null;
        }

        public String c() {
            return this.f12667a;
        }

        public SQLiteDatabase d() {
            return this.c.getWritableDatabase();
        }

        public final void run() {
            Context context;
            if (this.d != null && (context = (Context) this.d.get()) != null && context.getFilesDir() != null && this.c != null && !TextUtils.isEmpty(this.f12667a)) {
                File file = new File(this.f12667a);
                v.a(context, new File(file.getParentFile(), be.b(file.getAbsolutePath())), new cf(this, context));
            }
        }
    }

    public static abstract class b<T> extends a {

        /* renamed from: a  reason: collision with root package name */
        private List<String> f12668a;
        private String d;
        private String[] e;
        private String f;
        private String g;
        private String h;
        private int i;
        private List<T> j = new ArrayList();

        public b(String str, List<String> list, String str2, String[] strArr, String str3, String str4, String str5, int i2) {
            super(str);
            this.f12668a = list;
            this.d = str2;
            this.e = strArr;
            this.f = str3;
            this.g = str4;
            this.h = str5;
            this.i = i2;
        }

        public void a(Context context, SQLiteDatabase sQLiteDatabase) {
            String[] strArr;
            this.j.clear();
            String str = null;
            if (this.f12668a == null || this.f12668a.size() <= 0) {
                strArr = null;
            } else {
                String[] strArr2 = new String[this.f12668a.size()];
                this.f12668a.toArray(strArr2);
                strArr = strArr2;
            }
            if (this.i > 0) {
                str = String.valueOf(this.i);
            }
            Cursor query = sQLiteDatabase.query(this.b, strArr, this.d, this.e, this.f, this.g, this.h, str);
            if (query != null && query.moveToFirst()) {
                do {
                    Object b = b(context, query);
                    if (b != null) {
                        this.j.add(b);
                    }
                } while (query.moveToNext());
                query.close();
            }
            a(context, this.j);
        }

        public abstract void a(Context context, List<T> list);

        public abstract T b(Context context, Cursor cursor);

        public SQLiteDatabase d() {
            return this.c.getReadableDatabase();
        }
    }

    public static class c extends a {

        /* renamed from: a  reason: collision with root package name */
        private ArrayList<a> f12669a = new ArrayList<>();

        public c(String str, ArrayList<a> arrayList) {
            super(str);
            this.f12669a.addAll(arrayList);
        }

        public final void a(Context context) {
            super.a(context);
            Iterator<a> it = this.f12669a.iterator();
            while (it.hasNext()) {
                a next = it.next();
                if (next != null) {
                    next.a(context);
                }
            }
        }

        public void a(Context context, SQLiteDatabase sQLiteDatabase) {
            Iterator<a> it = this.f12669a.iterator();
            while (it.hasNext()) {
                a next = it.next();
                if (next != null) {
                    next.a(context, sQLiteDatabase);
                }
            }
        }
    }

    public static class d extends a {

        /* renamed from: a  reason: collision with root package name */
        private String f12670a;
        protected String[] d;

        public d(String str, String str2, String[] strArr) {
            super(str);
            this.f12670a = str2;
            this.d = strArr;
        }

        public void a(Context context, SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.delete(this.b, this.f12670a, this.d);
        }
    }

    public static class e extends a {

        /* renamed from: a  reason: collision with root package name */
        private ContentValues f12671a;

        public e(String str, ContentValues contentValues) {
            super(str);
            this.f12671a = contentValues;
        }

        public void a(Context context, SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.insert(this.b, (String) null, this.f12671a);
        }
    }

    private cd(Context context) {
        this.b = context;
    }

    public static cd a(Context context) {
        if (f12666a == null) {
            synchronized (cd.class) {
                if (f12666a == null) {
                    f12666a = new cd(context);
                }
            }
        }
        return f12666a;
    }

    private void a() {
        ai.a(this.b).b(new ce(this), ah.a(this.b).a(ht.StatDataProcessFrequency.a(), 5));
    }

    private cb b(String str) {
        cb cbVar = this.d.get(str);
        if (cbVar == null) {
            synchronized (this.d) {
                if (cbVar == null) {
                    try {
                        cbVar = this.c.a(this.b, str);
                        this.d.put(str, cbVar);
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        }
        return cbVar;
    }

    public String a(String str) {
        return b(str).a();
    }

    public void a(a aVar) {
        cb cbVar;
        if (aVar != null) {
            if (this.c != null) {
                String c2 = aVar.c();
                synchronized (this.d) {
                    cbVar = this.d.get(c2);
                    if (cbVar == null) {
                        cbVar = this.c.a(this.b, c2);
                        this.d.put(c2, cbVar);
                    }
                }
                if (!this.e.isShutdown()) {
                    aVar.a(cbVar, this.b);
                    synchronized (this.f) {
                        this.f.add(aVar);
                        a();
                    }
                    return;
                }
                return;
            }
            throw new IllegalStateException("should exec init method first!");
        }
    }

    public void a(Runnable runnable) {
        if (!this.e.isShutdown()) {
            this.e.execute(runnable);
        }
    }

    public void a(ArrayList<a> arrayList) {
        if (this.c != null) {
            HashMap hashMap = new HashMap();
            if (!this.e.isShutdown()) {
                Iterator<a> it = arrayList.iterator();
                while (it.hasNext()) {
                    a next = it.next();
                    if (next.b()) {
                        next.a(b(next.c()), this.b);
                    }
                    ArrayList arrayList2 = (ArrayList) hashMap.get(next.c());
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                        hashMap.put(next.c(), arrayList2);
                    }
                    arrayList2.add(next);
                }
                for (String str : hashMap.keySet()) {
                    ArrayList arrayList3 = (ArrayList) hashMap.get(str);
                    if (arrayList3 != null && arrayList3.size() > 0) {
                        c cVar = new c(str, arrayList3);
                        cVar.a(((a) arrayList3.get(0)).c, this.b);
                        this.e.execute(cVar);
                    }
                }
                return;
            }
            return;
        }
        throw new IllegalStateException("should exec setDbHelperFactory method first!");
    }

    public void b(a aVar) {
        cb cbVar;
        if (aVar != null) {
            if (this.c != null) {
                String c2 = aVar.c();
                synchronized (this.d) {
                    cbVar = this.d.get(c2);
                    if (cbVar == null) {
                        cbVar = this.c.a(this.b, c2);
                        this.d.put(c2, cbVar);
                    }
                }
                if (!this.e.isShutdown()) {
                    aVar.a(cbVar, this.b);
                    a((Runnable) aVar);
                    return;
                }
                return;
            }
            throw new IllegalStateException("should exec init method first!");
        }
    }
}
