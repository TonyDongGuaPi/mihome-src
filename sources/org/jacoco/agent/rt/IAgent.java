package org.jacoco.agent.rt;

import java.io.IOException;

public interface IAgent {
    String a();

    void a(String str);

    byte[] a(boolean z);

    String b();

    void b(boolean z) throws IOException;

    void c();
}
