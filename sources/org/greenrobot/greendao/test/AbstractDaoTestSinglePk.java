package org.greenrobot.greendao.test;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.support.media.ExifInterface;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.DaoLog;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;

public abstract class AbstractDaoTestSinglePk<D extends AbstractDao<T, K>, T, K> extends AbstractDaoTest<D, T, K> {
    protected Set<K> f = new HashSet();
    private Property k;

    /* access modifiers changed from: protected */
    public abstract T a(K k2);

    /* access modifiers changed from: protected */
    public abstract K f();

    public AbstractDaoTestSinglePk(Class<D> cls) {
        super(cls);
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        super.setUp();
        for (Property property : this.c.a()) {
            if (property.d) {
                if (this.k == null) {
                    this.k = property;
                } else {
                    throw new RuntimeException("Test does not work with multiple PK columns");
                }
            }
        }
        if (this.k == null) {
            throw new RuntimeException("Test does not work without a PK column");
        }
    }

    public void g() {
        Object A = A();
        Object a2 = a(A);
        this.b.insert(a2);
        assertEquals(A, this.c.a(a2));
        Object load = this.b.load(A);
        assertNotNull(load);
        assertEquals(this.c.a(a2), this.c.a(load));
    }

    public void h() {
        this.b.deleteAll();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 20; i++) {
            arrayList.add(B());
        }
        this.b.insertInTx(arrayList);
        assertEquals((long) arrayList.size(), this.b.count());
    }

    public void i() {
        this.b.deleteAll();
        assertEquals(0, this.b.count());
        this.b.insert(B());
        assertEquals(1, this.b.count());
        this.b.insert(B());
        assertEquals(2, this.b.count());
    }

    public void j() {
        Object a2 = a(A());
        this.b.insert(a2);
        try {
            this.b.insert(a2);
            fail("Inserting twice should not work");
        } catch (SQLException unused) {
        }
    }

    public void k() {
        Object B = B();
        long insert = this.b.insert(B);
        long insertOrReplace = this.b.insertOrReplace(B);
        if (this.b.getPkProperty().b == Long.class) {
            assertEquals(insert, insertOrReplace);
        }
    }

    public void l() {
        this.b.deleteAll();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < 20; i++) {
            Object B = B();
            if (i % 2 == 0) {
                arrayList.add(B);
            }
            arrayList2.add(B);
        }
        this.b.insertOrReplaceInTx(arrayList);
        this.b.insertOrReplaceInTx(arrayList2);
        assertEquals((long) arrayList2.size(), this.b.count());
    }

    public void m() {
        Object A = A();
        this.b.deleteByKey(A);
        this.b.insert(a(A));
        assertNotNull(this.b.load(A));
        this.b.deleteByKey(A);
        assertNull(this.b.load(A));
    }

    public void n() {
        ArrayList<Object> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(B());
        }
        this.b.insertInTx(arrayList);
        this.b.deleteAll();
        assertEquals(0, this.b.count());
        for (Object a2 : arrayList) {
            Object a3 = this.c.a(a2);
            assertNotNull(a3);
            assertNull(this.b.load(a3));
        }
    }

    public void o() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            arrayList.add(B());
        }
        this.b.insertInTx(arrayList);
        ArrayList<Object> arrayList2 = new ArrayList<>();
        arrayList2.add(arrayList.get(0));
        arrayList2.add(arrayList.get(3));
        arrayList2.add(arrayList.get(4));
        arrayList2.add(arrayList.get(8));
        this.b.deleteInTx(arrayList2);
        assertEquals((long) (arrayList.size() - arrayList2.size()), this.b.count());
        for (Object a2 : arrayList2) {
            Object a3 = this.c.a(a2);
            assertNotNull(a3);
            assertNull(this.b.load(a3));
        }
    }

    public void p() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            arrayList.add(B());
        }
        this.b.insertInTx(arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(this.c.a(arrayList.get(0)));
        arrayList2.add(this.c.a(arrayList.get(3)));
        arrayList2.add(this.c.a(arrayList.get(4)));
        arrayList2.add(this.c.a(arrayList.get(8)));
        this.b.deleteByKeyInTx(arrayList2);
        assertEquals((long) (arrayList.size() - arrayList2.size()), this.b.count());
        for (Object next : arrayList2) {
            assertNotNull(next);
            assertNull(this.b.load(next));
        }
    }

    public void q() {
        assertTrue(this.b.insert(B()) != this.b.insert(B()));
    }

    public void r() {
        this.b.deleteAll();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 15; i++) {
            arrayList.add(a(A()));
        }
        this.b.insertInTx(arrayList);
        assertEquals(arrayList.size(), this.b.loadAll().size());
    }

    public void s() {
        this.b.insert(B());
        Object A = A();
        this.b.insert(a(A));
        this.b.insert(B());
        List queryRaw = this.b.queryRaw("WHERE " + this.b.getPkColumns()[0] + "=?", A.toString());
        assertEquals(1, queryRaw.size());
        assertEquals(A, this.c.a(queryRaw.get(0)));
    }

    public void t() {
        this.b.deleteAll();
        Object B = B();
        this.b.insert(B);
        this.b.update(B);
        assertEquals(1, this.b.count());
    }

    public void u() {
        Object A = A();
        this.b.insert(a(A));
        Cursor a2 = a(5, "42", A);
        try {
            assertEquals(A, this.c.a(this.c.a(a2, 5)));
        } finally {
            a2.close();
        }
    }

    public void v() {
        a(10);
    }

    public void w() {
        a(0);
    }

    public void x() {
        if (z()) {
            this.b.deleteAll();
            Object a2 = a((Object) null);
            if (a2 != null) {
                this.b.save(a2);
                this.b.save(a2);
                assertEquals(1, this.b.count());
            }
        }
    }

    public void y() {
        if (z()) {
            this.b.deleteAll();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < 20; i++) {
                Object a2 = a((Object) null);
                if (i % 2 == 0) {
                    arrayList.add(a2);
                }
                arrayList2.add(a2);
            }
            this.b.saveInTx(arrayList);
            this.b.saveInTx(arrayList2);
            assertEquals((long) arrayList2.size(), this.b.count());
        }
    }

    /* access modifiers changed from: protected */
    public void a(int i) {
        Object A = A();
        this.b.insert(a(A));
        Cursor a2 = a(i, "42", A);
        try {
            assertEquals(A, this.c.b(a2, i));
        } finally {
            a2.close();
        }
    }

    /* access modifiers changed from: protected */
    public Cursor a(int i, String str, K k2) {
        StringBuilder sb = new StringBuilder("SELECT ");
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            sb.append(str);
            sb.append(",");
        }
        SqlUtils.a(sb, ExifInterface.GPS_DIRECTION_TRUE, this.b.getAllColumns()).append(" FROM ");
        sb.append('\"');
        sb.append(this.b.getTablename());
        sb.append('\"');
        sb.append(" T");
        if (k2 != null) {
            sb.append(" WHERE ");
            assertEquals(1, this.b.getPkColumns().length);
            sb.append(this.b.getPkColumns()[0]);
            sb.append("=");
            DatabaseUtils.appendValueToSql(sb, k2);
        }
        Cursor a2 = this.j.a(sb.toString(), (String[]) null);
        assertTrue(a2.moveToFirst());
        while (i2 < i) {
            try {
                assertEquals(str, a2.getString(i2));
                i2++;
            } catch (RuntimeException e) {
                a2.close();
                throw e;
            }
        }
        if (k2 != null) {
            assertEquals(1, a2.getCount());
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    public boolean z() {
        if (a((Object) null) != null) {
            return true;
        }
        DaoLog.b("Test is not available for entities with non-null keys");
        return false;
    }

    /* access modifiers changed from: protected */
    public K A() {
        for (int i = 0; i < 100000; i++) {
            K f2 = f();
            if (this.f.add(f2)) {
                return f2;
            }
        }
        throw new IllegalStateException("Could not find a new PK");
    }

    /* access modifiers changed from: protected */
    public T B() {
        return a(A());
    }
}
