package com.mishopsdk.volley;

import java.util.Collections;
import java.util.Map;

public class NetworkResponse {
    public final byte[] data;
    public final Map<String, String> headers;
    public final int networkReceivedTimeMs;
    public final int networkSendTimeMs;
    public final int networkTimeMs;
    public final long networkTimestamp;
    public final boolean notModified;
    public final int statusCode;

    public NetworkResponse(int i, byte[] bArr, Map<String, String> map, boolean z, int i2, int i3, int i4, long j) {
        this.statusCode = i;
        this.data = bArr;
        this.headers = map;
        this.notModified = z;
        this.networkTimeMs = i2;
        this.networkSendTimeMs = i3;
        this.networkReceivedTimeMs = i4;
        this.networkTimestamp = j;
    }

    public NetworkResponse(int i, byte[] bArr, Map<String, String> map, boolean z) {
        this(i, bArr, map, z, 0, 0, 0, 0);
    }

    public NetworkResponse(byte[] bArr) {
        this(200, bArr, Collections.emptyMap(), false, 0, 0, 0, 0);
    }

    public NetworkResponse(byte[] bArr, Map<String, String> map) {
        this(200, bArr, map, false, 0, 0, 0, 0);
    }
}
