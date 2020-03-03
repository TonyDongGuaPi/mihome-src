package org.greenrobot.greendao.query;

import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;

public interface WhereCondition {
    void a(StringBuilder sb, String str);

    void a(List<Object> list);

    public static abstract class AbstractCondition implements WhereCondition {

        /* renamed from: a  reason: collision with root package name */
        protected final boolean f3540a;
        protected final Object b;
        protected final Object[] c;

        public AbstractCondition() {
            this.f3540a = false;
            this.b = null;
            this.c = null;
        }

        public AbstractCondition(Object obj) {
            this.b = obj;
            this.f3540a = true;
            this.c = null;
        }

        public AbstractCondition(Object[] objArr) {
            this.b = null;
            this.f3540a = false;
            this.c = objArr;
        }

        public void a(List<Object> list) {
            if (this.f3540a) {
                list.add(this.b);
            } else if (this.c != null) {
                for (Object add : this.c) {
                    list.add(add);
                }
            }
        }
    }

    public static class PropertyCondition extends AbstractCondition {
        public final Property d;
        public final String e;

        private static Object a(Property property, Object obj) {
            if (obj != null && obj.getClass().isArray()) {
                throw new DaoException("Illegal value: found array, but simple object required");
            } else if (property.b != Date.class) {
                if (property.b == Boolean.TYPE || property.b == Boolean.class) {
                    if (obj instanceof Boolean) {
                        return Integer.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
                    }
                    if (obj instanceof Number) {
                        int intValue = ((Number) obj).intValue();
                        if (!(intValue == 0 || intValue == 1)) {
                            throw new DaoException("Illegal boolean value: numbers must be 0 or 1, but was " + obj);
                        }
                    } else if (obj instanceof String) {
                        String str = (String) obj;
                        if ("TRUE".equalsIgnoreCase(str)) {
                            return 1;
                        }
                        if ("FALSE".equalsIgnoreCase(str)) {
                            return 0;
                        }
                        throw new DaoException("Illegal boolean value: Strings must be \"TRUE\" or \"FALSE\" (case insensitive), but was " + obj);
                    }
                }
                return obj;
            } else if (obj instanceof Date) {
                return Long.valueOf(((Date) obj).getTime());
            } else {
                if (obj instanceof Long) {
                    return obj;
                }
                throw new DaoException("Illegal date value: expected java.util.Date or Long for value " + obj);
            }
        }

        private static Object[] a(Property property, Object[] objArr) {
            for (int i = 0; i < objArr.length; i++) {
                objArr[i] = a(property, objArr[i]);
            }
            return objArr;
        }

        public PropertyCondition(Property property, String str) {
            this.d = property;
            this.e = str;
        }

        public PropertyCondition(Property property, String str, Object obj) {
            super(a(property, obj));
            this.d = property;
            this.e = str;
        }

        public PropertyCondition(Property property, String str, Object[] objArr) {
            super(a(property, objArr));
            this.d = property;
            this.e = str;
        }

        public void a(StringBuilder sb, String str) {
            SqlUtils.a(sb, str, this.d).append(this.e);
        }
    }

    public static class StringCondition extends AbstractCondition {
        protected final String d;

        public StringCondition(String str) {
            this.d = str;
        }

        public StringCondition(String str, Object obj) {
            super(obj);
            this.d = str;
        }

        public StringCondition(String str, Object... objArr) {
            super(objArr);
            this.d = str;
        }

        public void a(StringBuilder sb, String str) {
            sb.append(this.d);
        }
    }
}
