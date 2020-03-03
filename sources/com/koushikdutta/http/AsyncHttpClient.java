package com.koushikdutta.http;

import android.net.Uri;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import com.codebutler.android_websockets.WebSocketClient;
import com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;
import org.aspectj.runtime.internal.AroundClosure;

public class AsyncHttpClient {

    public interface StringCallback {
        void a(Exception exc, String str);
    }

    public interface WebSocketConnectCallback {
        void a(Exception exc, WebSocketClient webSocketClient);
    }

    public static class SocketIORequest {

        /* renamed from: a  reason: collision with root package name */
        private String f6235a;
        private String b;
        private List<BasicNameValuePair> c;

        public SocketIORequest(String str) {
            this(str, (String) null);
        }

        public SocketIORequest(String str, String str2) {
            this(str, str2, (List<BasicNameValuePair>) null);
        }

        public SocketIORequest(String str, String str2, List<BasicNameValuePair> list) {
            this.f6235a = Uri.parse(str).buildUpon().encodedPath("/socket.io/1/").build().toString();
            this.b = str2;
            this.c = list;
        }

        public String a() {
            return this.f6235a;
        }

        public String b() {
            return this.b;
        }

        public List<BasicNameValuePair> c() {
            return this.c;
        }
    }

    public void a(final SocketIORequest socketIORequest, final StringCallback stringCallback) {
        new AsyncTask<Void, Void, Void>() {

            /* renamed from: com.koushikdutta.http.AsyncHttpClient$1$AjcClosure1 */
            public class AjcClosure1 extends AroundClosure {
                public AjcClosure1(Object[] objArr) {
                    super(objArr);
                }

                public Object run(Object[] objArr) {
                    Object[] objArr2 = this.state;
                    return AnonymousClass1.a((AnonymousClass1) objArr2[0], (AndroidHttpClient) objArr2[1], (HttpUriRequest) objArr2[2]);
                }
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Void doInBackground(Void... voidArr) {
                AndroidHttpClient newInstance = AndroidHttpClient.newInstance("android-websockets-2.0");
                HttpPost httpPost = new HttpPost(socketIORequest.a());
                a(httpPost, socketIORequest.c());
                try {
                    String a2 = AsyncHttpClient.this.b(TraceNetTrafficMonitor.b().b((HttpClient) newInstance, (HttpUriRequest) httpPost).getEntity().getContent());
                    if (stringCallback != null) {
                        stringCallback.a((Exception) null, a2);
                    }
                } catch (IOException e) {
                    if (stringCallback != null) {
                        stringCallback.a(e, (String) null);
                    }
                } catch (Throwable th) {
                    newInstance.close();
                    throw th;
                }
                newInstance.close();
                return null;
            }

            static final HttpResponse a(AnonymousClass1 r0, AndroidHttpClient androidHttpClient, HttpUriRequest httpUriRequest) {
                return androidHttpClient.execute(httpUriRequest);
            }

            private void a(HttpRequest httpRequest, List<BasicNameValuePair> list) {
                if (list != null) {
                    for (BasicNameValuePair next : list) {
                        httpRequest.addHeader(next.getName(), next.getValue());
                    }
                }
            }
        }.execute(new Void[0]);
    }

    private byte[] a(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = dataInputStream.read(bArr);
            if (read == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    /* access modifiers changed from: private */
    public String b(InputStream inputStream) throws IOException {
        return new String(a(inputStream));
    }
}
