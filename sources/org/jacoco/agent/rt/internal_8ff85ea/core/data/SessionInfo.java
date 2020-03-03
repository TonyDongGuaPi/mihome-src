package org.jacoco.agent.rt.internal_8ff85ea.core.data;

import com.taobao.weex.el.parse.Operators;

public class SessionInfo implements Comparable<SessionInfo> {

    /* renamed from: a  reason: collision with root package name */
    private final String f3615a;
    private final long b;
    private final long c;

    public SessionInfo(String str, long j, long j2) {
        if (str != null) {
            this.f3615a = str;
            this.b = j;
            this.c = j2;
            return;
        }
        throw new IllegalArgumentException();
    }

    public String a() {
        return this.f3615a;
    }

    public long b() {
        return this.b;
    }

    public long c() {
        return this.c;
    }

    /* renamed from: a */
    public int compareTo(SessionInfo sessionInfo) {
        if (this.c < sessionInfo.c) {
            return -1;
        }
        return this.c > sessionInfo.c ? 1 : 0;
    }

    public String toString() {
        return "SessionInfo[" + this.f3615a + Operators.ARRAY_END_STR;
    }
}
