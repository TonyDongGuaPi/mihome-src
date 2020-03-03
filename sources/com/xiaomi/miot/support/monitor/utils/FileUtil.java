package com.xiaomi.miot.support.monitor.utils;

import android.net.LocalSocket;
import android.util.Log;
import android.widget.Toast;
import com.mi.global.shop.model.Tags;
import com.xiaomi.miot.support.monitor.MiotMonitorManager;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.nio.channels.FileChannel;

public class FileUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f11494a = "/";
    private static final String b = "--FileUtil--";

    public static boolean a(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        if (!str.contains(":") && !str.contains("*") && !str.contains("?") && !str.contains("\"") && !str.contains("<") && !str.contains(">") && !str.contains("|")) {
            return true;
        }
        Log.w(b, "filename can not contains:*:?\"<>|");
        return false;
    }

    public static boolean b(String str) {
        return str.contains("/") || str.contains(Tags.MiHome.TEL_SEPARATOR4);
    }

    public static String c(String str) {
        return str.substring(0, str.lastIndexOf("/"));
    }

    public static String a(String str, String str2) {
        if (str.contains("/") || str.contains(Tags.MiHome.TEL_SEPARATOR4)) {
            int lastIndexOf = str.lastIndexOf(".");
            if (lastIndexOf < 0) {
                return str + str2;
            }
            String substring = str.substring(lastIndexOf);
            if (!substring.contains("/") && !substring.contains(Tags.MiHome.TEL_SEPARATOR4)) {
                return str;
            }
            return str + str2;
        } else if (str.contains(".")) {
            return str;
        } else {
            return str + str2;
        }
    }

    public static boolean d(String str) {
        try {
            if (!new File(str).exists()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean a(File file) {
        if (file.exists()) {
            return true;
        }
        try {
            file.createNewFile();
            file.delete();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean a(File file, String str) {
        return a(new File(file, str));
    }

    public static void e(String str) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
    }

    public static boolean f(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return file.mkdirs();
        }
        return true;
    }

    public static void b(File file) {
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void a(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void a(Writer writer) {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void b(Writer writer) {
        if (writer != null) {
            try {
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void a(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void a(FileChannel fileChannel) {
        if (fileChannel != null) {
            try {
                fileChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void a(RandomAccessFile randomAccessFile) {
        if (randomAccessFile != null) {
            try {
                randomAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void a(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void a(LocalSocket localSocket) {
        if (localSocket != null) {
            try {
                localSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void g(String str) {
        Toast makeText = Toast.makeText(MiotMonitorManager.a().h(), str, 0);
        makeText.setGravity(17, 0, 0);
        makeText.show();
    }

    public static void c(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                for (File c : listFiles) {
                    c(c);
                }
            }
            file.delete();
            return;
        }
        g("文件不存在！n");
    }

    public static void a(File file, File file2) {
        FileChannel fileChannel;
        FileChannel fileChannel2;
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            if (!file2.exists()) {
                file2.createNewFile();
            }
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (IOException e) {
                e = e;
                fileChannel = null;
                fileChannel2 = null;
                try {
                    e.printStackTrace();
                    a((OutputStream) fileOutputStream2);
                    a((InputStream) fileInputStream);
                    a(fileChannel);
                    a(fileChannel2);
                } catch (Throwable th) {
                    th = th;
                    a((OutputStream) fileOutputStream2);
                    a((InputStream) fileInputStream);
                    a(fileChannel);
                    a(fileChannel2);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileChannel = null;
                fileChannel2 = null;
                a((OutputStream) fileOutputStream2);
                a((InputStream) fileInputStream);
                a(fileChannel);
                a(fileChannel2);
                throw th;
            }
            try {
                fileChannel = fileInputStream.getChannel();
            } catch (IOException e2) {
                fileChannel2 = null;
                fileOutputStream2 = fileOutputStream;
                e = e2;
                fileChannel = null;
                e.printStackTrace();
                a((OutputStream) fileOutputStream2);
                a((InputStream) fileInputStream);
                a(fileChannel);
                a(fileChannel2);
            } catch (Throwable th3) {
                fileChannel2 = null;
                fileOutputStream2 = fileOutputStream;
                th = th3;
                fileChannel = null;
                a((OutputStream) fileOutputStream2);
                a((InputStream) fileInputStream);
                a(fileChannel);
                a(fileChannel2);
                throw th;
            }
            try {
                fileChannel2 = fileOutputStream.getChannel();
                try {
                    fileChannel.transferTo(0, fileChannel.size(), fileChannel2);
                    a((OutputStream) fileOutputStream);
                } catch (IOException e3) {
                    IOException iOException = e3;
                    fileOutputStream2 = fileOutputStream;
                    e = iOException;
                    e.printStackTrace();
                    a((OutputStream) fileOutputStream2);
                    a((InputStream) fileInputStream);
                    a(fileChannel);
                    a(fileChannel2);
                } catch (Throwable th4) {
                    Throwable th5 = th4;
                    fileOutputStream2 = fileOutputStream;
                    th = th5;
                    a((OutputStream) fileOutputStream2);
                    a((InputStream) fileInputStream);
                    a(fileChannel);
                    a(fileChannel2);
                    throw th;
                }
            } catch (IOException e4) {
                fileChannel2 = null;
                fileOutputStream2 = fileOutputStream;
                e = e4;
                e.printStackTrace();
                a((OutputStream) fileOutputStream2);
                a((InputStream) fileInputStream);
                a(fileChannel);
                a(fileChannel2);
            } catch (Throwable th6) {
                fileChannel2 = null;
                fileOutputStream2 = fileOutputStream;
                th = th6;
                a((OutputStream) fileOutputStream2);
                a((InputStream) fileInputStream);
                a(fileChannel);
                a(fileChannel2);
                throw th;
            }
        } catch (IOException e5) {
            e = e5;
            fileChannel = null;
            fileInputStream = null;
            fileChannel2 = null;
            e.printStackTrace();
            a((OutputStream) fileOutputStream2);
            a((InputStream) fileInputStream);
            a(fileChannel);
            a(fileChannel2);
        } catch (Throwable th7) {
            th = th7;
            fileChannel = null;
            fileInputStream = null;
            fileChannel2 = null;
            a((OutputStream) fileOutputStream2);
            a((InputStream) fileInputStream);
            a(fileChannel);
            a(fileChannel2);
            throw th;
        }
        a((InputStream) fileInputStream);
        a(fileChannel);
        a(fileChannel2);
    }

    public static void a(InputStream inputStream, String str) {
        BufferedInputStream bufferedInputStream;
        FileOutputStream fileOutputStream = null;
        try {
            byte[] bArr = new byte[10240];
            bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(str);
                try {
                    for (int read = bufferedInputStream.read(bArr, 0, bArr.length); read != -1; read = bufferedInputStream.read(bArr, 0, bArr.length)) {
                        fileOutputStream2.write(bArr, 0, read);
                        fileOutputStream2.flush();
                    }
                    a((OutputStream) fileOutputStream2);
                } catch (Exception e) {
                    e = e;
                    fileOutputStream = fileOutputStream2;
                    try {
                        e.printStackTrace();
                        a((OutputStream) fileOutputStream);
                        a((InputStream) bufferedInputStream);
                        a(inputStream);
                    } catch (Throwable th) {
                        th = th;
                        a((OutputStream) fileOutputStream);
                        a((InputStream) bufferedInputStream);
                        a(inputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = fileOutputStream2;
                    a((OutputStream) fileOutputStream);
                    a((InputStream) bufferedInputStream);
                    a(inputStream);
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                a((OutputStream) fileOutputStream);
                a((InputStream) bufferedInputStream);
                a(inputStream);
            }
        } catch (Exception e3) {
            e = e3;
            bufferedInputStream = null;
            e.printStackTrace();
            a((OutputStream) fileOutputStream);
            a((InputStream) bufferedInputStream);
            a(inputStream);
        } catch (Throwable th3) {
            th = th3;
            bufferedInputStream = null;
            a((OutputStream) fileOutputStream);
            a((InputStream) bufferedInputStream);
            a(inputStream);
            throw th;
        }
        a((InputStream) bufferedInputStream);
        a(inputStream);
    }
}
