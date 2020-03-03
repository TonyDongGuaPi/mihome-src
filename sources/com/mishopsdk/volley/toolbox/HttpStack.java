package com.mishopsdk.volley.toolbox;

import com.mishopsdk.volley.AuthFailureError;
import com.mishopsdk.volley.Request;
import java.io.IOException;
import java.util.Map;
import org.apache.http.HttpResponse;

public interface HttpStack {
    HttpResponse performRequest(Request<?> request, Map<String, String> map) throws IOException, AuthFailureError;
}
