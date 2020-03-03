package com.mishopsdk.volley.toolbox;

import com.mishopsdk.volley.AuthFailureError;

public interface Authenticator {
    String getAuthToken() throws AuthFailureError;

    void invalidateAuthToken(String str);
}
