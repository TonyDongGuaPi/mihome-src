package com.xiaomi.infra.galaxy.fds.model;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AccessControlList {

    /* renamed from: a  reason: collision with root package name */
    private final Map<GrantKey, Integer> f10163a = new HashMap();

    public enum GrantType {
        USER,
        GROUP
    }

    public enum UserGroups {
        ALL_USERS,
        AUTHENTICATED_USERS
    }

    public enum Permission {
        READ(1),
        WRITE(2),
        READ_OBJECTS(4),
        SSO_WRITE(8),
        FULL_CONTROL(255);
        
        private final int value;

        private Permission(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static class GrantKey implements Comparable<GrantKey> {

        /* renamed from: a  reason: collision with root package name */
        protected final String f10165a;
        protected final GrantType b;

        public GrantKey(String str, GrantType grantType) {
            this.f10165a = str;
            this.b = grantType;
        }

        /* renamed from: a */
        public int compareTo(GrantKey grantKey) {
            int compareTo = this.f10165a.compareTo(grantKey.f10165a);
            return compareTo == 0 ? this.b.compareTo(grantKey.b) : compareTo;
        }

        public String toString() {
            return this.f10165a + ":" + this.b.name();
        }

        public static GrantKey a(String str) {
            int lastIndexOf = str.lastIndexOf(":");
            Preconditions.checkState(lastIndexOf > 0);
            return new GrantKey(str.substring(0, lastIndexOf), GrantType.valueOf(str.substring(lastIndexOf + 1)));
        }

        public int hashCode() {
            return (this.f10165a.hashCode() ^ this.b.hashCode()) + this.f10165a.length();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof GrantKey)) {
                return false;
            }
            GrantKey grantKey = (GrantKey) obj;
            if (!this.f10165a.equals(grantKey.f10165a) || !this.b.equals(grantKey.b)) {
                return false;
            }
            return true;
        }
    }

    public static class Grant {

        /* renamed from: a  reason: collision with root package name */
        private String f10164a;
        private Permission b;
        private GrantType c;

        public Grant(GrantKey grantKey, Permission permission) {
            this(grantKey.f10165a, permission, grantKey.b);
        }

        public Grant(String str, Permission permission) {
            this(str, permission, GrantType.USER);
        }

        public Grant(String str, Permission permission, GrantType grantType) {
            this.f10164a = str;
            this.b = permission;
            this.c = grantType;
        }

        public String a() {
            return this.f10164a;
        }

        public void a(String str) {
            this.f10164a = str;
        }

        public Permission b() {
            return this.b;
        }

        public void a(Permission permission) {
            this.b = permission;
        }

        public GrantType c() {
            return this.c;
        }

        public void a(GrantType grantType) {
            this.c = grantType;
        }

        public String toString() {
            return d().toString() + ":" + this.b.name();
        }

        public static Grant b(String str) {
            int lastIndexOf = str.lastIndexOf(":");
            Preconditions.checkState(lastIndexOf > 0);
            return new Grant(GrantKey.a(str.substring(0, lastIndexOf)), Permission.valueOf(str.substring(lastIndexOf + 1)));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Grant grant = (Grant) obj;
            if (this.f10164a == null ? grant.f10164a == null : this.f10164a.equals(grant.f10164a)) {
                return this.b == grant.b && this.c == grant.c;
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = (((this.f10164a != null ? this.f10164a.hashCode() : 0) * 31) + (this.b != null ? this.b.hashCode() : 0)) * 31;
            if (this.c != null) {
                i = this.c.hashCode();
            }
            return hashCode + i;
        }

        /* access modifiers changed from: protected */
        public GrantKey d() {
            return new GrantKey(this.f10164a, this.c);
        }
    }

    public void a(Grant grant) {
        GrantKey d = grant.d();
        Integer num = this.f10163a.get(d);
        if (num == null) {
            this.f10163a.put(d, Integer.valueOf(grant.b().getValue()));
            return;
        }
        this.f10163a.put(d, Integer.valueOf(grant.b().getValue() | num.intValue()));
    }

    public void a(List<Grant> list) {
        for (Grant a2 : list) {
            a(a2);
        }
    }

    public boolean a(String str, GrantType grantType, Permission permission) {
        Integer num = this.f10163a.get(new GrantKey(str, grantType));
        if (num == null) {
            return false;
        }
        if ((num.intValue() & permission.getValue()) > 0) {
            return true;
        }
        return false;
    }

    public boolean a(String str) {
        return a(str, GrantType.USER, Permission.READ);
    }

    public boolean b(String str) {
        return a(str, GrantType.USER, Permission.WRITE);
    }

    public boolean c(String str) {
        return a(str, GrantType.GROUP, Permission.READ);
    }

    public boolean d(String str) {
        return a(str, GrantType.GROUP, Permission.WRITE);
    }

    public List<Grant> a() {
        LinkedList linkedList = new LinkedList();
        for (Map.Entry next : this.f10163a.entrySet()) {
            GrantKey grantKey = (GrantKey) next.getKey();
            if (((Integer) next.getValue()).intValue() == Permission.FULL_CONTROL.getValue()) {
                linkedList.add(new Grant(grantKey, Permission.FULL_CONTROL));
            } else {
                for (Permission permission : Permission.values()) {
                    if (permission.getValue() != Permission.FULL_CONTROL.getValue() && (permission.getValue() & ((Integer) next.getValue()).intValue()) > 0) {
                        linkedList.add(new Grant(grantKey, permission));
                    }
                }
            }
        }
        return linkedList;
    }
}
