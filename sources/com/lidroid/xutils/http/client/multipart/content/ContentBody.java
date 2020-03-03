package com.lidroid.xutils.http.client.multipart.content;

import com.lidroid.xutils.http.client.multipart.MultipartEntity;
import java.io.IOException;
import java.io.OutputStream;

public interface ContentBody extends ContentDescriptor {
    void a(MultipartEntity.CallBackInfo callBackInfo);

    void a(OutputStream outputStream) throws IOException;

    String d();
}
