package com.seek.biscuit;

public interface CompressListener {
    void onError(CompressException compressException);

    void onSuccess(String str);
}
