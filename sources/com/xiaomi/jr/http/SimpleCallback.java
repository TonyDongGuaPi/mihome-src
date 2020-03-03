package com.xiaomi.jr.http;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SimpleCallback<T> implements Callback<T> {
    public void onFailure(Call<T> call, Throwable th) {
    }

    public void onResponse(Call<T> call, Response<T> response) {
    }
}
