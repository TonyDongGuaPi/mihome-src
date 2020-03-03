package com.xiaomi.mimc.example;

import com.xiaomi.msg.logger.Logger;
import com.xiaomi.msg.logger.MIMCLog;
import java.io.PrintStream;

public class LogUtils {
    public static void a() {
        MIMCLog.a((Logger) new Logger() {
            public void a(String str, String str2) {
                PrintStream printStream = System.out;
                printStream.println(MIMCLog.a(1, str) + str2);
            }

            public void a(String str, String str2, Throwable th) {
                PrintStream printStream = System.out;
                printStream.println(MIMCLog.a(1, str) + str2 + MIMCLog.a(th));
            }

            public void b(String str, String str2) {
                PrintStream printStream = System.out;
                printStream.println(MIMCLog.a(2, str) + str2);
            }

            public void b(String str, String str2, Throwable th) {
                PrintStream printStream = System.out;
                printStream.println(MIMCLog.a(2, str) + str2 + MIMCLog.a(th));
            }

            public void c(String str, String str2) {
                PrintStream printStream = System.out;
                printStream.println(MIMCLog.a(3, str) + str2);
            }

            public void c(String str, String str2, Throwable th) {
                PrintStream printStream = System.out;
                printStream.println(MIMCLog.a(3, str) + str2 + MIMCLog.a(th));
            }

            public void d(String str, String str2) {
                PrintStream printStream = System.out;
                printStream.println(MIMCLog.a(4, str) + str2);
            }

            public void d(String str, String str2, Throwable th) {
                PrintStream printStream = System.out;
                printStream.println(MIMCLog.a(4, str) + str2 + MIMCLog.a(th));
            }
        });
        MIMCLog.a(2);
        MIMCLog.b(1);
    }
}
