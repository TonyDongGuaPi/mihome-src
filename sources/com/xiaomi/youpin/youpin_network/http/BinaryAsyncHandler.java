package com.xiaomi.youpin.youpin_network.http;

import com.xiaomi.youpin.network.bean.NetError;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class BinaryAsyncHandler extends AsyncHandler<byte[], NetError> {
    public abstract void a(NetError netError, Exception exc, Response response);

    public abstract void a(byte[] bArr, Response response);

    public void a(Response response) {
        if (response.isSuccessful()) {
            ResponseBody body = response.body();
            if (body == null) {
                b(new NetError(response.code(), ""), (Exception) null, response);
                return;
            }
            try {
                b(body.bytes(), response);
            } catch (Exception e) {
                b(new NetError(response.code(), ""), e, response);
            }
        } else {
            b(new NetError(response.code(), ""), (Exception) null, response);
        }
    }

    public void a(Call call, IOException iOException) {
        b(new NetError(-1, ""), iOException, (Response) null);
    }
}
