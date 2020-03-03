package com.google.android.exoplayer2.ext.okhttp;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.upstream.BaseDataSource;
import com.google.android.exoplayer2.upstream.DataSourceException;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Predicate;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpDataSource extends BaseDataSource implements HttpDataSource {
    private static final byte[] SKIP_BUFFER = new byte[4096];
    private long bytesRead;
    private long bytesSkipped;
    private long bytesToRead;
    private long bytesToSkip;
    @Nullable
    private final CacheControl cacheControl;
    private final Call.Factory callFactory;
    @Nullable
    private final Predicate<String> contentTypePredicate;
    @Nullable
    private DataSpec dataSpec;
    @Nullable
    private final HttpDataSource.RequestProperties defaultRequestProperties;
    private boolean opened;
    private final HttpDataSource.RequestProperties requestProperties;
    @Nullable
    private Response response;
    @Nullable
    private InputStream responseByteStream;
    @Nullable
    private final String userAgent;

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.okhttp");
    }

    public OkHttpDataSource(Call.Factory factory, @Nullable String str, @Nullable Predicate<String> predicate) {
        this(factory, str, predicate, (CacheControl) null, (HttpDataSource.RequestProperties) null);
    }

    public OkHttpDataSource(Call.Factory factory, @Nullable String str, @Nullable Predicate<String> predicate, @Nullable CacheControl cacheControl2, @Nullable HttpDataSource.RequestProperties requestProperties2) {
        super(true);
        this.callFactory = (Call.Factory) Assertions.checkNotNull(factory);
        this.userAgent = str;
        this.contentTypePredicate = predicate;
        this.cacheControl = cacheControl2;
        this.defaultRequestProperties = requestProperties2;
        this.requestProperties = new HttpDataSource.RequestProperties();
    }

    @Nullable
    public Uri getUri() {
        if (this.response == null) {
            return null;
        }
        return Uri.parse(this.response.request().url().toString());
    }

    public Map<String, List<String>> getResponseHeaders() {
        return this.response == null ? Collections.emptyMap() : this.response.headers().toMultimap();
    }

    public void setRequestProperty(String str, String str2) {
        Assertions.checkNotNull(str);
        Assertions.checkNotNull(str2);
        this.requestProperties.set(str, str2);
    }

    public void clearRequestProperty(String str) {
        Assertions.checkNotNull(str);
        this.requestProperties.remove(str);
    }

    public void clearAllRequestProperties() {
        this.requestProperties.clear();
    }

    public long open(DataSpec dataSpec2) throws HttpDataSource.HttpDataSourceException {
        this.dataSpec = dataSpec2;
        long j = 0;
        this.bytesRead = 0;
        this.bytesSkipped = 0;
        transferInitializing(dataSpec2);
        try {
            this.response = this.callFactory.newCall(makeRequest(dataSpec2)).execute();
            Response response2 = this.response;
            ResponseBody responseBody = (ResponseBody) Assertions.checkNotNull(response2.body());
            this.responseByteStream = responseBody.byteStream();
            int code = response2.code();
            if (!response2.isSuccessful()) {
                Map<String, List<String>> multimap = response2.headers().toMultimap();
                closeConnectionQuietly();
                HttpDataSource.InvalidResponseCodeException invalidResponseCodeException = new HttpDataSource.InvalidResponseCodeException(code, response2.message(), multimap, dataSpec2);
                if (code == 416) {
                    invalidResponseCodeException.initCause(new DataSourceException(0));
                }
                throw invalidResponseCodeException;
            }
            MediaType contentType = responseBody.contentType();
            String mediaType = contentType != null ? contentType.toString() : "";
            if (this.contentTypePredicate == null || this.contentTypePredicate.evaluate(mediaType)) {
                if (code == 200 && dataSpec2.position != 0) {
                    j = dataSpec2.position;
                }
                this.bytesToSkip = j;
                if (dataSpec2.length != -1) {
                    this.bytesToRead = dataSpec2.length;
                } else {
                    long contentLength = responseBody.contentLength();
                    this.bytesToRead = contentLength != -1 ? contentLength - this.bytesToSkip : -1;
                }
                this.opened = true;
                transferStarted(dataSpec2);
                return this.bytesToRead;
            }
            closeConnectionQuietly();
            throw new HttpDataSource.InvalidContentTypeException(mediaType, dataSpec2);
        } catch (IOException e) {
            throw new HttpDataSource.HttpDataSourceException("Unable to connect to " + dataSpec2.uri, e, dataSpec2, 1);
        }
    }

    public int read(byte[] bArr, int i, int i2) throws HttpDataSource.HttpDataSourceException {
        try {
            skipInternal();
            return readInternal(bArr, i, i2);
        } catch (IOException e) {
            throw new HttpDataSource.HttpDataSourceException(e, (DataSpec) Assertions.checkNotNull(this.dataSpec), 2);
        }
    }

    public void close() throws HttpDataSource.HttpDataSourceException {
        if (this.opened) {
            this.opened = false;
            transferEnded();
            closeConnectionQuietly();
        }
    }

    /* access modifiers changed from: protected */
    public final long bytesSkipped() {
        return this.bytesSkipped;
    }

    /* access modifiers changed from: protected */
    public final long bytesRead() {
        return this.bytesRead;
    }

    /* access modifiers changed from: protected */
    public final long bytesRemaining() {
        return this.bytesToRead == -1 ? this.bytesToRead : this.bytesToRead - this.bytesRead;
    }

    private Request makeRequest(DataSpec dataSpec2) throws HttpDataSource.HttpDataSourceException {
        long j = dataSpec2.position;
        long j2 = dataSpec2.length;
        boolean isFlagSet = dataSpec2.isFlagSet(1);
        HttpUrl parse = HttpUrl.parse(dataSpec2.uri.toString());
        if (parse != null) {
            Request.Builder url = new Request.Builder().url(parse);
            if (this.cacheControl != null) {
                url.cacheControl(this.cacheControl);
            }
            if (this.defaultRequestProperties != null) {
                for (Map.Entry next : this.defaultRequestProperties.getSnapshot().entrySet()) {
                    url.header((String) next.getKey(), (String) next.getValue());
                }
            }
            for (Map.Entry next2 : this.requestProperties.getSnapshot().entrySet()) {
                url.header((String) next2.getKey(), (String) next2.getValue());
            }
            if (!(j == 0 && j2 == -1)) {
                String str = "bytes=" + j + "-";
                if (j2 != -1) {
                    str = str + ((j + j2) - 1);
                }
                url.addHeader("Range", str);
            }
            if (this.userAgent != null) {
                url.addHeader("User-Agent", this.userAgent);
            }
            if (!isFlagSet) {
                url.addHeader("Accept-Encoding", "identity");
            }
            RequestBody requestBody = null;
            if (dataSpec2.httpBody != null) {
                requestBody = RequestBody.create((MediaType) null, dataSpec2.httpBody);
            } else if (dataSpec2.httpMethod == 2) {
                requestBody = RequestBody.create((MediaType) null, Util.EMPTY_BYTE_ARRAY);
            }
            url.method(dataSpec2.getHttpMethodString(), requestBody);
            return url.build();
        }
        throw new HttpDataSource.HttpDataSourceException("Malformed URL", dataSpec2, 1);
    }

    private void skipInternal() throws IOException {
        if (this.bytesSkipped != this.bytesToSkip) {
            while (this.bytesSkipped != this.bytesToSkip) {
                int read = ((InputStream) Util.castNonNull(this.responseByteStream)).read(SKIP_BUFFER, 0, (int) Math.min(this.bytesToSkip - this.bytesSkipped, (long) SKIP_BUFFER.length));
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedIOException();
                } else if (read != -1) {
                    this.bytesSkipped += (long) read;
                    bytesTransferred(read);
                } else {
                    throw new EOFException();
                }
            }
        }
    }

    private int readInternal(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        if (this.bytesToRead != -1) {
            long j = this.bytesToRead - this.bytesRead;
            if (j == 0) {
                return -1;
            }
            i2 = (int) Math.min((long) i2, j);
        }
        int read = ((InputStream) Util.castNonNull(this.responseByteStream)).read(bArr, i, i2);
        if (read != -1) {
            this.bytesRead += (long) read;
            bytesTransferred(read);
            return read;
        } else if (this.bytesToRead == -1) {
            return -1;
        } else {
            throw new EOFException();
        }
    }

    private void closeConnectionQuietly() {
        if (this.response != null) {
            ((ResponseBody) Assertions.checkNotNull(this.response.body())).close();
            this.response = null;
        }
        this.responseByteStream = null;
    }
}
