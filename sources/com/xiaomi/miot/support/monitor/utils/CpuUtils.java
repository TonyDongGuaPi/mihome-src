package com.xiaomi.miot.support.monitor.utils;

import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CpuUtils {

    /* renamed from: a  reason: collision with root package name */
    public static Map<String, CpuUtils> f1489a = new HashMap();
    private static final String b = "CpuUtils";
    private static boolean c = true;
    private static double d = 0.0d;
    private static double e = 0.0d;
    private double f = 0.0d;
    private double g = 0.0d;
    private double h = 0.0d;
    private double i = 0.0d;
    private double j = 0.0d;

    public long a() {
        return (long) this.f;
    }

    public static double b() {
        RandomAccessFile randomAccessFile = null;
        double d2 = 0.0d;
        if (c) {
            c = false;
            try {
                RandomAccessFile randomAccessFile2 = new RandomAccessFile("/proc/stat", "r");
                try {
                    String[] split = randomAccessFile2.readLine().split(" ");
                    e = Double.parseDouble(split[5]);
                    d = Double.parseDouble(split[2]) + Double.parseDouble(split[3]) + Double.parseDouble(split[4]) + Double.parseDouble(split[6]) + Double.parseDouble(split[8]) + Double.parseDouble(split[7]);
                    FileUtil.a(randomAccessFile2);
                } catch (IOException e2) {
                    e = e2;
                    randomAccessFile = randomAccessFile2;
                    try {
                        e.printStackTrace();
                        FileUtil.a(randomAccessFile);
                        return d2;
                    } catch (Throwable th) {
                        th = th;
                        randomAccessFile2 = randomAccessFile;
                        FileUtil.a(randomAccessFile2);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    FileUtil.a(randomAccessFile2);
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
                e.printStackTrace();
                FileUtil.a(randomAccessFile);
                return d2;
            }
        } else {
            try {
                RandomAccessFile randomAccessFile3 = new RandomAccessFile("/proc/stat", "r");
                try {
                    String[] split2 = randomAccessFile3.readLine().split(" ");
                    double parseDouble = Double.parseDouble(split2[5]);
                    double parseDouble2 = Double.parseDouble(split2[2]) + Double.parseDouble(split2[3]) + Double.parseDouble(split2[4]) + Double.parseDouble(split2[6]) + Double.parseDouble(split2[8]) + Double.parseDouble(split2[7]);
                    double d3 = parseDouble2 + parseDouble;
                    if (0.0d != d3 - (d + e)) {
                        double a2 = DoubleUtils.a((parseDouble2 - d) * 100.0d, d3 - (d + e), 2);
                        if (a2 >= 0.0d) {
                            d2 = a2 > 100.0d ? 100.0d : a2;
                        }
                    }
                    d = parseDouble2;
                    e = parseDouble;
                    FileUtil.a(randomAccessFile3);
                } catch (IOException e4) {
                    e = e4;
                    randomAccessFile = randomAccessFile3;
                    try {
                        e.printStackTrace();
                        FileUtil.a(randomAccessFile);
                        return d2;
                    } catch (Throwable th3) {
                        th = th3;
                        randomAccessFile3 = randomAccessFile;
                        FileUtil.a(randomAccessFile3);
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    FileUtil.a(randomAccessFile3);
                    throw th;
                }
            } catch (IOException e5) {
                e = e5;
                e.printStackTrace();
                FileUtil.a(randomAccessFile);
                return d2;
            }
        }
        return d2;
    }

    public static double c() {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("/proc/stat", "r");
            String[] split = randomAccessFile.readLine().split(" ");
            double parseDouble = Double.parseDouble(split[5]);
            double parseDouble2 = Double.parseDouble(split[2]) + Double.parseDouble(split[3]) + Double.parseDouble(split[4]) + Double.parseDouble(split[6]) + Double.parseDouble(split[8]) + Double.parseDouble(split[7]);
            try {
                Thread.sleep(360);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            randomAccessFile.seek(0);
            String readLine = randomAccessFile.readLine();
            randomAccessFile.close();
            String[] split2 = readLine.split(" ");
            double parseDouble3 = Double.parseDouble(split2[5]);
            double parseDouble4 = Double.parseDouble(split2[2]) + Double.parseDouble(split2[3]) + Double.parseDouble(split2[4]) + Double.parseDouble(split2[6]) + Double.parseDouble(split2[8]) + Double.parseDouble(split2[7]);
            return DoubleUtils.a((parseDouble4 - parseDouble2) * 100.0d, (parseDouble4 + parseDouble3) - (parseDouble2 + parseDouble), 2);
        } catch (IOException e3) {
            e3.printStackTrace();
            return 0.0d;
        }
    }

    public static double d() {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("/proc/stat", "r");
            String[] split = randomAccessFile.readLine().split(" ");
            double parseDouble = Double.parseDouble(split[2]);
            double parseDouble2 = Double.parseDouble(split[4]);
            double parseDouble3 = Double.parseDouble(split[7]);
            double parseDouble4 = Double.parseDouble(split[5]);
            try {
                Thread.sleep(360);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            randomAccessFile.seek(0);
            String readLine = randomAccessFile.readLine();
            randomAccessFile.close();
            String[] split2 = readLine.split(" ");
            double parseDouble5 = Double.parseDouble(split2[2]);
            double parseDouble6 = Double.parseDouble(split2[4]);
            double d2 = parseDouble5 - parseDouble;
            double d3 = parseDouble6 - parseDouble2;
            double parseDouble7 = Double.parseDouble(split2[7]) - parseDouble3;
            return new BigDecimal((((d2 + d3) + parseDouble7) * 100.0d) / (((d2 + parseDouble7) + d3) + (Double.parseDouble(split2[5]) - parseDouble4))).setScale(2, 4).doubleValue();
        } catch (FileNotFoundException e3) {
            e3.printStackTrace();
            return 0.0d;
        } catch (IOException e4) {
            e4.printStackTrace();
            return 0.0d;
        }
    }

    public String[] a(int i2) {
        BufferedReader bufferedReader;
        IOException e2;
        String[] strArr = new String[3];
        File file = new File("/proc/" + i2 + "/stat");
        if (!file.exists() || !file.canRead()) {
            return strArr;
        }
        try {
            bufferedReader = new BufferedReader(new FileReader(file), 8192);
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    String[] split = readLine.split(" ");
                    strArr[0] = split[1];
                    strArr[1] = split[13];
                    strArr[2] = split[14];
                }
            } catch (IOException e3) {
                e2 = e3;
                e2.printStackTrace();
                FileUtil.a((Reader) bufferedReader);
                return strArr;
            }
        } catch (IOException e4) {
            IOException iOException = e4;
            bufferedReader = null;
            e2 = iOException;
            e2.printStackTrace();
            FileUtil.a((Reader) bufferedReader);
            return strArr;
        }
        FileUtil.a((Reader) bufferedReader);
        return strArr;
    }

    public String[] e() {
        String[] strArr = new String[7];
        File file = new File("/proc/stat");
        if (!file.exists() || !file.canRead()) {
            return strArr;
        }
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file), 8192);
            try {
                String readLine = bufferedReader2.readLine();
                if (readLine != null) {
                    strArr = readLine.split(" ");
                }
                bufferedReader = bufferedReader2;
            } catch (FileNotFoundException e2) {
                BufferedReader bufferedReader3 = bufferedReader2;
                e = e2;
                bufferedReader = bufferedReader3;
                e.printStackTrace();
                FileUtil.a((Reader) bufferedReader);
                return strArr;
            } catch (IOException e3) {
                BufferedReader bufferedReader4 = bufferedReader2;
                e = e3;
                bufferedReader = bufferedReader4;
                e.printStackTrace();
                FileUtil.a((Reader) bufferedReader);
                return strArr;
            }
        } catch (FileNotFoundException e4) {
            e = e4;
            e.printStackTrace();
            FileUtil.a((Reader) bufferedReader);
            return strArr;
        } catch (IOException e5) {
            e = e5;
            e.printStackTrace();
            FileUtil.a((Reader) bufferedReader);
            return strArr;
        }
        FileUtil.a((Reader) bufferedReader);
        return strArr;
    }

    public CpuUtils() {
        g();
    }

    private void g() {
        this.i = 0.0d;
        this.g = 0.0d;
        this.j = 0.0d;
        this.h = 0.0d;
    }

    public String b(int i2) {
        String str = "";
        if (i2 >= 0) {
            String[] a2 = a(i2);
            if (a2 != null) {
                this.g = Double.parseDouble(a2[1]) + Double.parseDouble(a2[2]);
            }
            String[] e2 = e();
            double d2 = 0.0d;
            if (e2 != null) {
                this.h = 0.0d;
                for (int i3 = 2; i3 < e2.length; i3++) {
                    this.h += Double.parseDouble(e2[i3]);
                }
            }
            if (this.h - this.j != 0.0d) {
                double a3 = DoubleUtils.a((this.g - this.i) * 100.0d, this.h - this.j, 2);
                if (a3 >= 0.0d) {
                    d2 = a3 > 100.0d ? 100.0d : a3;
                }
            }
            this.i = this.g;
            this.j = this.h;
            str = String.valueOf(d2) + Operators.MOD;
        }
        this.f = this.g;
        return str;
    }

    public static void f() {
        StringBuilder sb = new StringBuilder();
        try {
            Process exec = Runtime.getRuntime().exec(new String[]{"sh", "-c", "dumpsys cpuinfo"});
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    String trim = readLine.trim();
                    if (!trim.equals("")) {
                        sb.append(trim);
                        sb.append("\r\n");
                    }
                } else {
                    try {
                        exec.waitFor();
                        return;
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public static void a(String str) {
        try {
            Process exec = Runtime.getRuntime().exec(new String[]{"sh", "-c", "top -n 1"});
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    String trim = readLine.trim();
                    String[] split = trim.split(" ");
                    if (str.startsWith(split[split.length - 1].substring(0, split[split.length - 1].length() - 1))) {
                        Log.e(b, "getCpuUsageByCmd: " + trim);
                        Log.e(b, "top cpu 值: " + split[16]);
                    }
                } else {
                    try {
                        exec.waitFor();
                        return;
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
            }
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }
}
