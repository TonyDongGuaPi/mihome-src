package com.huawei.hms.support.log.a;

import android.content.Context;
import android.util.Log;
import com.huawei.hms.support.log.c;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class a implements c {

    /* renamed from: a  reason: collision with root package name */
    private File f5895a;

    public void a(Context context, String str) {
        File externalFilesDir;
        if (context == null || str == null || str.isEmpty()) {
            Log.e("FileLogNode", "Failed to initialize the file logger, parameter error.");
            return;
        }
        if (this.f5895a == null && (externalFilesDir = context.getExternalFilesDir((String) null)) != null) {
            File file = new File(externalFilesDir, "Log");
            if (file.isDirectory() || file.mkdirs()) {
                this.f5895a = new File(file, str + ".log");
                this.f5895a.setReadable(true);
                this.f5895a.setWritable(true);
                this.f5895a.setExecutable(false, false);
                return;
            }
        }
        Log.e("FileLogNode", "Failed to initialize the file logger.");
    }

    public void a(String str, int i, String str2, String str3) {
        if (this.f5895a != null && str != null) {
            String str4 = str + 10;
            if (a(str4)) {
                b(str4);
            }
        }
    }

    private boolean a(String str) {
        if (this.f5895a.length() + ((long) str.length()) <= 524288) {
            return true;
        }
        if (this.f5895a.renameTo(new File(this.f5895a.getPath() + ".bak"))) {
            return true;
        }
        Log.w("FileLogNode", "Failed to backup the log file.");
        return false;
    }

    private void b(String str) {
        BufferedOutputStream bufferedOutputStream;
        FileOutputStream fileOutputStream;
        OutputStreamWriter outputStreamWriter;
        OutputStreamWriter outputStreamWriter2 = null;
        try {
            fileOutputStream = new FileOutputStream(this.f5895a, true);
            try {
                bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                try {
                    outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
                } catch (FileNotFoundException unused) {
                    Log.w("FileLogNode", "Exception when writing the log file.");
                    a((Closeable) outputStreamWriter2);
                    a((Closeable) bufferedOutputStream);
                    a((Closeable) fileOutputStream);
                } catch (IOException unused2) {
                    Log.w("FileLogNode", "Exception when writing the log file.");
                    a((Closeable) outputStreamWriter2);
                    a((Closeable) bufferedOutputStream);
                    a((Closeable) fileOutputStream);
                }
                try {
                    outputStreamWriter.write(str);
                    outputStreamWriter.flush();
                    a((Closeable) outputStreamWriter);
                } catch (FileNotFoundException unused3) {
                    outputStreamWriter2 = outputStreamWriter;
                    Log.w("FileLogNode", "Exception when writing the log file.");
                    a((Closeable) outputStreamWriter2);
                    a((Closeable) bufferedOutputStream);
                    a((Closeable) fileOutputStream);
                } catch (IOException unused4) {
                    outputStreamWriter2 = outputStreamWriter;
                    Log.w("FileLogNode", "Exception when writing the log file.");
                    a((Closeable) outputStreamWriter2);
                    a((Closeable) bufferedOutputStream);
                    a((Closeable) fileOutputStream);
                } catch (Throwable th) {
                    th = th;
                    outputStreamWriter2 = outputStreamWriter;
                    a((Closeable) outputStreamWriter2);
                    a((Closeable) bufferedOutputStream);
                    a((Closeable) fileOutputStream);
                    throw th;
                }
            } catch (FileNotFoundException unused5) {
                bufferedOutputStream = null;
                Log.w("FileLogNode", "Exception when writing the log file.");
                a((Closeable) outputStreamWriter2);
                a((Closeable) bufferedOutputStream);
                a((Closeable) fileOutputStream);
            } catch (IOException unused6) {
                bufferedOutputStream = null;
                Log.w("FileLogNode", "Exception when writing the log file.");
                a((Closeable) outputStreamWriter2);
                a((Closeable) bufferedOutputStream);
                a((Closeable) fileOutputStream);
            } catch (Throwable th2) {
                th = th2;
                bufferedOutputStream = null;
                a((Closeable) outputStreamWriter2);
                a((Closeable) bufferedOutputStream);
                a((Closeable) fileOutputStream);
                throw th;
            }
        } catch (FileNotFoundException unused7) {
            fileOutputStream = null;
            bufferedOutputStream = null;
            Log.w("FileLogNode", "Exception when writing the log file.");
            a((Closeable) outputStreamWriter2);
            a((Closeable) bufferedOutputStream);
            a((Closeable) fileOutputStream);
        } catch (IOException unused8) {
            fileOutputStream = null;
            bufferedOutputStream = null;
            Log.w("FileLogNode", "Exception when writing the log file.");
            a((Closeable) outputStreamWriter2);
            a((Closeable) bufferedOutputStream);
            a((Closeable) fileOutputStream);
        } catch (Throwable th3) {
            th = th3;
            a((Closeable) outputStreamWriter2);
            a((Closeable) bufferedOutputStream);
            a((Closeable) fileOutputStream);
            throw th;
        }
        a((Closeable) bufferedOutputStream);
        a((Closeable) fileOutputStream);
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                Log.w("FileLogNode", "Exception when closing the closeable.");
            }
        }
    }

    /* renamed from: com.huawei.hms.support.log.a.a$a  reason: collision with other inner class name */
    public static class C0056a implements c {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public final c f5896a;
        private c b;
        private final Executor c = Executors.newSingleThreadExecutor();

        public C0056a(c cVar) {
            this.f5896a = cVar;
        }

        public void a(Context context, String str) {
            this.c.execute(new b(this, context, str));
            if (this.b != null) {
                this.b.a(context, str);
            }
        }

        public void a(String str, int i, String str2, String str3) {
            this.c.execute(new c(this, str, i, str2, str3));
            if (this.b != null) {
                this.b.a(str, i, str2, str3);
            }
        }
    }
}
