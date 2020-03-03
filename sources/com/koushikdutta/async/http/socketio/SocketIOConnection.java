package com.koushikdutta.async.http.socketio;

import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import com.codebutler.android_websockets.WebSocketClient;
import com.koushikdutta.http.AsyncHttpClient;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

class SocketIOConnection {

    /* renamed from: a  reason: collision with root package name */
    AsyncHttpClient f6216a;
    int b;
    ArrayList<SocketIOClient> c = new ArrayList<>();
    WebSocketClient d;
    AsyncHttpClient.SocketIORequest e;
    Hashtable<String, Acknowledge> f = new Hashtable<>();
    int g;
    long h = 1000;
    /* access modifiers changed from: private */
    public Handler i;

    private interface SelectCallback {
        void a(SocketIOClient socketIOClient);
    }

    public SocketIOConnection(Handler handler, AsyncHttpClient asyncHttpClient, AsyncHttpClient.SocketIORequest socketIORequest) {
        this.i = handler;
        this.f6216a = asyncHttpClient;
        this.e = socketIORequest;
    }

    public boolean a() {
        return this.d != null && this.d.d();
    }

    public void a(int i2, SocketIOClient socketIOClient, String str, Acknowledge acknowledge) {
        String str2 = "";
        if (acknowledge != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            int i3 = this.g;
            this.g = i3 + 1;
            sb.append(i3);
            String sb2 = sb.toString();
            this.f.put(sb2, acknowledge);
            str2 = sb2 + "+";
        }
        this.d.a(String.format("%d:%s:%s:%s", new Object[]{Integer.valueOf(i2), str2, socketIOClient.l, str}));
    }

    public void a(SocketIOClient socketIOClient) {
        this.c.add(socketIOClient);
        this.d.a(String.format("1::%s", new Object[]{socketIOClient.l}));
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x002d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x0013  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(com.koushikdutta.async.http.socketio.SocketIOClient r6) {
        /*
            r5 = this;
            java.util.ArrayList<com.koushikdutta.async.http.socketio.SocketIOClient> r0 = r5.c
            r0.remove(r6)
            java.util.ArrayList<com.koushikdutta.async.http.socketio.SocketIOClient> r0 = r5.c
            java.util.Iterator r0 = r0.iterator()
        L_0x000b:
            boolean r1 = r0.hasNext()
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x002d
            java.lang.Object r1 = r0.next()
            com.koushikdutta.async.http.socketio.SocketIOClient r1 = (com.koushikdutta.async.http.socketio.SocketIOClient) r1
            java.lang.String r1 = r1.l
            java.lang.String r4 = r6.l
            boolean r1 = android.text.TextUtils.equals(r1, r4)
            if (r1 != 0) goto L_0x002b
            java.lang.String r1 = r6.l
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 == 0) goto L_0x000b
        L_0x002b:
            r0 = 0
            goto L_0x002e
        L_0x002d:
            r0 = 1
        L_0x002e:
            if (r0 == 0) goto L_0x0041
            com.codebutler.android_websockets.WebSocketClient r0 = r5.d
            java.lang.String r1 = "0::%s"
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.String r6 = r6.l
            r3[r2] = r6
            java.lang.String r6 = java.lang.String.format(r1, r3)
            r0.a((java.lang.String) r6)
        L_0x0041:
            java.util.ArrayList<com.koushikdutta.async.http.socketio.SocketIOClient> r6 = r5.c
            int r6 = r6.size()
            if (r6 <= 0) goto L_0x004a
            return
        L_0x004a:
            com.codebutler.android_websockets.WebSocketClient r6 = r5.d
            r6.c()
            r6 = 0
            r5.d = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.koushikdutta.async.http.socketio.SocketIOConnection.b(com.koushikdutta.async.http.socketio.SocketIOClient):void");
    }

    /* access modifiers changed from: package-private */
    public void b() {
        if (!a()) {
            this.f6216a.a(this.e, (AsyncHttpClient.StringCallback) new AsyncHttpClient.StringCallback() {
                public void a(Exception exc, String str) {
                    if (exc != null) {
                        SocketIOConnection.this.a(exc);
                        return;
                    }
                    try {
                        String[] split = str.split(":");
                        String str2 = split[0];
                        if (!"".equals(split[1])) {
                            SocketIOConnection.this.b = (Integer.parseInt(split[1]) / 2) * 1000;
                        } else {
                            SocketIOConnection.this.b = 0;
                        }
                        if (new HashSet(Arrays.asList(split[3].split(","))).contains("websocket")) {
                            String uri = Uri.parse(SocketIOConnection.this.e.a()).buildUpon().appendPath("websocket").appendPath(str2).build().toString();
                            SocketIOConnection.this.d = new WebSocketClient(URI.create(uri), new WebSocketClient.Listener() {
                                public void a(byte[] bArr) {
                                }

                                public void a(String str) {
                                    try {
                                        String[] split = str.split(":", 4);
                                        switch (Integer.parseInt(split[0])) {
                                            case 0:
                                                SocketIOConnection.this.d.c();
                                                SocketIOConnection.this.a((Exception) null);
                                                return;
                                            case 1:
                                                SocketIOConnection.this.a(split[2]);
                                                return;
                                            case 2:
                                                SocketIOConnection.this.d.a("2::");
                                                return;
                                            case 3:
                                                SocketIOConnection.this.a(split[2], split[3], SocketIOConnection.this.b(split[1]));
                                                return;
                                            case 4:
                                                SocketIOConnection.this.a(split[2], new JSONObject(split[3]), SocketIOConnection.this.b(split[1]));
                                                return;
                                            case 5:
                                                JSONObject jSONObject = new JSONObject(split[3]);
                                                SocketIOConnection.this.a(split[2], jSONObject.getString("name"), jSONObject.optJSONArray("args"), SocketIOConnection.this.b(split[1]));
                                                return;
                                            case 6:
                                                String[] split2 = split[3].split("\\+", 2);
                                                Acknowledge remove = SocketIOConnection.this.f.remove(split2[0]);
                                                if (remove != null) {
                                                    remove.a(split2.length == 2 ? new JSONArray(split2[1]) : null);
                                                    return;
                                                }
                                                return;
                                            case 7:
                                                SocketIOConnection.this.a(split[2], split[3]);
                                                return;
                                            case 8:
                                                return;
                                            default:
                                                throw new Exception("unknown code");
                                        }
                                    } catch (Exception e) {
                                        SocketIOConnection.this.d.c();
                                        SocketIOConnection.this.d = null;
                                        SocketIOConnection.this.a(e);
                                    }
                                }

                                public void a(Exception exc) {
                                    SocketIOConnection.this.a(exc);
                                }

                                public void a(int i, String str) {
                                    SocketIOConnection.this.a((Exception) new IOException(String.format("Disconnected code %d for reason %s", new Object[]{Integer.valueOf(i), str})));
                                }

                                public void a(List<Header> list) {
                                    SocketIOConnection.this.h = 1000;
                                    SocketIOConnection.this.c();
                                }
                            }, (List<BasicNameValuePair>) null);
                            SocketIOConnection.this.d.b();
                            return;
                        }
                        throw new Exception("websocket not supported");
                    } catch (Exception e) {
                        SocketIOConnection.this.a(e);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void c() {
        final WebSocketClient webSocketClient = this.d;
        new Runnable() {
            public void run() {
                if (SocketIOConnection.this.b > 0 && webSocketClient == SocketIOConnection.this.d && webSocketClient != null && webSocketClient.d()) {
                    SocketIOConnection.this.d.a("2:::");
                    SocketIOConnection.this.i.postDelayed(this, (long) SocketIOConnection.this.b);
                }
            }
        }.run();
    }

    private void a(String str, SelectCallback selectCallback) {
        Iterator<SocketIOClient> it = this.c.iterator();
        while (it.hasNext()) {
            SocketIOClient next = it.next();
            if (str == null || TextUtils.equals(next.l, str)) {
                selectCallback.a(next);
            }
        }
    }

    private void d() {
        if (this.d == null && this.c.size() != 0) {
            boolean z = false;
            Iterator<SocketIOClient> it = this.c.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().c) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (z) {
                this.i.postDelayed(new Runnable() {
                    public void run() {
                        SocketIOConnection.this.b();
                    }
                }, this.h);
                this.h *= 2;
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(final Exception exc) {
        a((String) null, (SelectCallback) new SelectCallback() {
            public void a(final SocketIOClient socketIOClient) {
                if (socketIOClient.b) {
                    socketIOClient.c = true;
                    final DisconnectCallback b2 = socketIOClient.b();
                    if (b2 != null) {
                        SocketIOConnection.this.i.post(new Runnable() {
                            public void run() {
                                b2.a(exc);
                            }
                        });
                        return;
                    }
                    return;
                }
                final ConnectCallback connectCallback = socketIOClient.e;
                if (connectCallback != null) {
                    SocketIOConnection.this.i.post(new Runnable() {
                        public void run() {
                            connectCallback.a(exc, socketIOClient);
                        }
                    });
                }
            }
        });
        d();
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        a(str, (SelectCallback) new SelectCallback() {
            public void a(SocketIOClient socketIOClient) {
                if (!socketIOClient.f()) {
                    if (!socketIOClient.b) {
                        socketIOClient.b = true;
                        ConnectCallback connectCallback = socketIOClient.e;
                        if (connectCallback != null) {
                            connectCallback.a((Exception) null, socketIOClient);
                        }
                    } else if (socketIOClient.c) {
                        socketIOClient.c = false;
                        ReconnectCallback reconnectCallback = socketIOClient.h;
                        if (reconnectCallback != null) {
                            reconnectCallback.a();
                        }
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str, final JSONObject jSONObject, final Acknowledge acknowledge) {
        a(str, (SelectCallback) new SelectCallback() {
            public void a(SocketIOClient socketIOClient) {
                final JSONCallback jSONCallback = socketIOClient.i;
                if (jSONCallback != null) {
                    SocketIOConnection.this.i.post(new Runnable() {
                        public void run() {
                            jSONCallback.a(jSONObject, acknowledge);
                        }
                    });
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str, final String str2, final Acknowledge acknowledge) {
        a(str, (SelectCallback) new SelectCallback() {
            public void a(SocketIOClient socketIOClient) {
                final StringCallback stringCallback = socketIOClient.j;
                if (stringCallback != null) {
                    SocketIOConnection.this.i.post(new Runnable() {
                        public void run() {
                            stringCallback.a(str2, acknowledge);
                        }
                    });
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str, final String str2, final JSONArray jSONArray, final Acknowledge acknowledge) {
        a(str, (SelectCallback) new SelectCallback() {
            public void a(final SocketIOClient socketIOClient) {
                SocketIOConnection.this.i.post(new Runnable() {
                    public void run() {
                        socketIOClient.a(str2, jSONArray, acknowledge);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str, final String str2) {
        a(str, (SelectCallback) new SelectCallback() {
            public void a(SocketIOClient socketIOClient) {
                final ErrorCallback errorCallback = socketIOClient.f;
                if (errorCallback != null) {
                    SocketIOConnection.this.i.post(new Runnable() {
                        public void run() {
                            errorCallback.a(str2);
                        }
                    });
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public Acknowledge b(final String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new Acknowledge() {
            public void a(JSONArray jSONArray) {
                String str = "";
                if (jSONArray != null) {
                    str = str + "+" + jSONArray.toString();
                }
                SocketIOConnection.this.d.a(String.format("6:::%s%s", new Object[]{str, str}));
            }
        };
    }
}
