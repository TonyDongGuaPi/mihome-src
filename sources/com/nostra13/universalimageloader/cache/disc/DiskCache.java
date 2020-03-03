package com.nostra13.universalimageloader.cache.disc;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.utils.IoUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface DiskCache {
    File a();

    File a(String str);

    boolean a(String str, Bitmap bitmap) throws IOException;

    boolean a(String str, InputStream inputStream, IoUtils.CopyListener copyListener) throws IOException;

    void b();

    boolean b(String str);

    void c();
}
