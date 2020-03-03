package com.libra.sinvoice;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;
import com.libra.sinvoice.SinVoiceReceiver;
import com.libra.sinvoice.SinVoiceSender;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SinVoice implements SinVoiceReceiver.Listener, SinVoiceSender.Listener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6251a = "SinVoice";
    private static final String b = "/sinvoice_backup";
    private SinVoiceSender c;
    private SinVoiceReceiver d;
    private Context e;
    private String f = Environment.getExternalStorageDirectory().getPath();
    private Listener g;

    public interface Listener {
        void V_();

        void W_();

        void X_();

        void Y_();

        void Z_();

        void a(String str);

        void b();
    }

    static {
        System.loadLibrary("sinvoice");
    }

    public SinVoice(Context context, Listener listener) {
        this.e = context;
        this.g = listener;
        this.c = new SinVoiceSender(context, this);
        this.d = new SinVoiceReceiver(context, this);
    }

    public boolean a() {
        return this.c.g();
    }

    public boolean b() {
        return this.d.f();
    }

    public void a(boolean z) {
        this.d.a(z);
        this.c.a(z);
    }

    public void a(int i) {
        this.c.a(i);
    }

    public void b(int i) {
        c();
        this.c.b(i);
        d();
        this.d.a(i);
        b(false);
    }

    public void a(String str) {
        this.c.a(str);
    }

    public void c() {
        this.c.j();
    }

    public void b(boolean z) {
        this.d.b(z);
    }

    public void d() {
        this.d.g();
    }

    public void e() {
        c();
        d();
    }

    public void f() {
        this.c.i();
        this.d.e();
    }

    public void g() {
        if (this.g != null) {
            this.g.V_();
        }
    }

    public void h() {
        if (this.g != null) {
            this.g.b();
        }
    }

    public void i() {
        if (this.g != null) {
            this.g.Y_();
        }
    }

    public void j() {
        if (this.g != null) {
            this.g.W_();
        }
    }

    public void k() {
        if (this.g != null) {
            this.g.X_();
        }
    }

    public void b(String str) {
        if (this.g == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            this.g.Z_();
        } else {
            this.g.a(str);
        }
    }

    /* access modifiers changed from: private */
    public void m() {
        try {
            if (System.currentTimeMillis() <= new SimpleDateFormat("yyyy-MM-dd").parse("2014-03-05").getTime()) {
                return;
            }
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        new AlertDialog.Builder(this.e).setTitle(R.string.warning).setMessage(R.string.use_warn_info).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        SinVoice.this.m();
                    }
                }, 3000);
            }
        }).setCancelable(false).show();
    }

    private static String n() {
        return new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date());
    }

    private static void a(File file, File file2) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file2);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        byte[] bArr = new byte[5120];
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read != -1) {
                bufferedOutputStream.write(bArr, 0, read);
            } else {
                bufferedOutputStream.flush();
                bufferedInputStream.close();
                bufferedOutputStream.close();
                fileOutputStream.close();
                fileInputStream.close();
                return;
            }
        }
    }

    private static void b(String str, String str2) throws IOException {
        new File(str).mkdirs();
        File[] listFiles = new File(str2).listFiles();
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isFile()) {
                    a(new File(new File(str).getAbsolutePath() + File.separator + listFiles[i].getName()), listFiles[i]);
                }
                if (listFiles[i].isDirectory()) {
                    b(str + "/" + listFiles[i].getName(), str2 + "/" + listFiles[i].getName());
                }
            }
        }
    }

    public void l() {
        a(new File(this.f + b));
        Toast.makeText(this.e, "clear backup log info successful", 0).show();
    }

    private static void a(File file) {
        if (file.isFile()) {
            file.delete();
        } else if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                file.delete();
                return;
            }
            for (File a2 : listFiles) {
                a(a2);
            }
            file.delete();
        }
    }

    public void a(String str, String str2) {
        this.d.g();
        String str3 = this.f + b + "/back_" + n();
        try {
            b(str3, this.f + "/sinvoice");
            b(str3, this.f + "/sinvoice_log");
            FileOutputStream fileOutputStream = new FileOutputStream(str3 + "/text.txt");
            fileOutputStream.write(str.getBytes());
            fileOutputStream.write(str2.getBytes());
            fileOutputStream.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        Toast.makeText(this.e, "backup log info successful", 0).show();
    }
}
