package org.greenrobot.greendao.internal;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScope;
import org.greenrobot.greendao.identityscope.IdentityScopeLong;
import org.greenrobot.greendao.identityscope.IdentityScopeObject;
import org.greenrobot.greendao.identityscope.IdentityScopeType;

public final class DaoConfig implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    public final Database f3527a;
    public final String b;
    public final Property[] c;
    public final String[] d;
    public final String[] e;
    public final String[] f;
    public final Property g;
    public final boolean h;
    public final TableStatements i;
    private IdentityScope<?, ?> j;

    public DaoConfig(Database database, Class<? extends AbstractDao<?, ?>> cls) {
        this.f3527a = database;
        try {
            Property property = null;
            this.b = (String) cls.getField("TABLENAME").get((Object) null);
            Property[] a2 = a(cls);
            this.c = a2;
            this.d = new String[a2.length];
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            Property property2 = null;
            for (int i2 = 0; i2 < a2.length; i2++) {
                Property property3 = a2[i2];
                String str = property3.e;
                this.d[i2] = str;
                if (property3.d) {
                    arrayList.add(str);
                    property2 = property3;
                } else {
                    arrayList2.add(str);
                }
            }
            this.f = (String[]) arrayList2.toArray(new String[arrayList2.size()]);
            this.e = (String[]) arrayList.toArray(new String[arrayList.size()]);
            boolean z = true;
            this.g = this.e.length == 1 ? property2 : property;
            this.i = new TableStatements(database, this.b, this.d, this.e);
            if (this.g != null) {
                Class<?> cls2 = this.g.b;
                if (!cls2.equals(Long.TYPE) && !cls2.equals(Long.class) && !cls2.equals(Integer.TYPE) && !cls2.equals(Integer.class) && !cls2.equals(Short.TYPE) && !cls2.equals(Short.class) && !cls2.equals(Byte.TYPE)) {
                    if (!cls2.equals(Byte.class)) {
                        z = false;
                    }
                }
                this.h = z;
                return;
            }
            this.h = false;
        } catch (Exception e2) {
            throw new DaoException("Could not init DAOConfig", e2);
        }
    }

    private static Property[] a(Class<? extends AbstractDao<?, ?>> cls) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
        Field[] declaredFields = Class.forName(cls.getName() + "$Properties").getDeclaredFields();
        ArrayList arrayList = new ArrayList();
        for (Field field : declaredFields) {
            if ((field.getModifiers() & 9) == 9) {
                Object obj = field.get((Object) null);
                if (obj instanceof Property) {
                    arrayList.add((Property) obj);
                }
            }
        }
        Property[] propertyArr = new Property[arrayList.size()];
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Property property = (Property) it.next();
            if (propertyArr[property.f3515a] == null) {
                propertyArr[property.f3515a] = property;
            } else {
                throw new DaoException("Duplicate property ordinals");
            }
        }
        return propertyArr;
    }

    public DaoConfig(DaoConfig daoConfig) {
        this.f3527a = daoConfig.f3527a;
        this.b = daoConfig.b;
        this.c = daoConfig.c;
        this.d = daoConfig.d;
        this.e = daoConfig.e;
        this.f = daoConfig.f;
        this.g = daoConfig.g;
        this.i = daoConfig.i;
        this.h = daoConfig.h;
    }

    /* renamed from: a */
    public DaoConfig clone() {
        return new DaoConfig(this);
    }

    public IdentityScope<?, ?> b() {
        return this.j;
    }

    public void c() {
        IdentityScope<?, ?> identityScope = this.j;
        if (identityScope != null) {
            identityScope.a();
        }
    }

    public void a(IdentityScope<?, ?> identityScope) {
        this.j = identityScope;
    }

    public void a(IdentityScopeType identityScopeType) {
        if (identityScopeType == IdentityScopeType.None) {
            this.j = null;
        } else if (identityScopeType != IdentityScopeType.Session) {
            throw new IllegalArgumentException("Unsupported type: " + identityScopeType);
        } else if (this.h) {
            this.j = new IdentityScopeLong();
        } else {
            this.j = new IdentityScopeObject();
        }
    }
}
