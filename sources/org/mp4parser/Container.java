package org.mp4parser;

import java.io.IOException;
import java.nio.channels.WritableByteChannel;
import java.util.List;

public interface Container {
    List<Box> a();

    <T extends Box> List<T> a(Class<T> cls);

    <T extends Box> List<T> a(Class<T> cls, boolean z);

    void a(WritableByteChannel writableByteChannel) throws IOException;

    void a(List<? extends Box> list);
}
