package com.mishopsdk.volley;

public interface Network {
    NetworkResponse performRequest(Request<?> request) throws VolleyError;
}
