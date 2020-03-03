package org.greenrobot.greendao.test;

import android.app.Application;
import android.app.Instrumentation;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import java.util.Random;
import org.greenrobot.greendao.DaoLog;
import org.greenrobot.greendao.DbUtils;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.StandardDatabase;

public abstract class DbTest extends AndroidTestCase {
    public static final String g = "greendao-unittest-db.temp";

    /* renamed from: a  reason: collision with root package name */
    private Application f3573a;
    protected final Random h;
    protected final boolean i;
    protected Database j;

    public DbTest() {
        this(true);
    }

    public DbTest(boolean z) {
        this.i = z;
        this.h = new Random();
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        DbTest.super.setUp();
        this.j = E();
    }

    public <T extends Application> T a(Class<T> cls) {
        assertNull("Application already created", this.f3573a);
        try {
            T newApplication = Instrumentation.newApplication(cls, getContext());
            newApplication.onCreate();
            this.f3573a = newApplication;
            return newApplication;
        } catch (Exception e) {
            throw new RuntimeException("Could not create application " + cls, e);
        }
    }

    public void C() {
        assertNotNull("Application not yet created", this.f3573a);
        this.f3573a.onTerminate();
        this.f3573a = null;
    }

    public <T extends Application> T D() {
        assertNotNull("Application not yet created", this.f3573a);
        return this.f3573a;
    }

    /* access modifiers changed from: protected */
    public Database E() {
        SQLiteDatabase sQLiteDatabase;
        if (this.i) {
            sQLiteDatabase = SQLiteDatabase.create((SQLiteDatabase.CursorFactory) null);
        } else {
            getContext().deleteDatabase(g);
            sQLiteDatabase = getContext().openOrCreateDatabase(g, 0, (SQLiteDatabase.CursorFactory) null);
        }
        return new StandardDatabase(sQLiteDatabase);
    }

    /* access modifiers changed from: protected */
    public void tearDown() throws Exception {
        if (this.f3573a != null) {
            C();
        }
        this.j.f();
        if (!this.i) {
            getContext().deleteDatabase(g);
        }
        DbTest.super.tearDown();
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        if (this.j instanceof StandardDatabase) {
            DbUtils.a(((StandardDatabase) this.j).h(), str);
            return;
        }
        DaoLog.d("Table dump unsupported for " + this.j);
    }
}
