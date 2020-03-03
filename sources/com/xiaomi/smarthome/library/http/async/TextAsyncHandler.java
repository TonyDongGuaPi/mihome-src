package com.xiaomi.smarthome.library.http.async;

import com.xiaomi.smarthome.library.http.Error;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;

public abstract class TextAsyncHandler extends AsyncHandler<String, Error> {
    /* renamed from: a */
    public abstract void onSuccess(String str, Response response);

    public abstract void onFailure(Error error, Exception exc, Response response);

    public final void processResponse(Response response) {
        if (response.isSuccessful()) {
            try {
                sendSuccessMessage(response.body().string(), response);
            } catch (Exception e) {
                sendFailureMessage(new Error(response.code(), ""), e, response);
            }
        } else {
            sendFailureMessage(new Error(response.code(), ""), (Exception) null, response);
        }
    }

    public final void processFailure(Call call, IOException iOException) {
        sendFailureMessage(new Error(-1, ""), iOException, (Response) null);
    }
}
