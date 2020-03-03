package com.squareup.picasso.mishop;

public interface Callback {

    public static class EmptyCallback implements Callback {
        public void onError() {
        }

        public void onSuccess() {
        }
    }

    void onError();

    void onSuccess();
}
