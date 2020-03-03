package samples;

import com.google.code.microlog4android.format.PatternFormatter;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

public class RegisterService {

    /* renamed from: a  reason: collision with root package name */
    public static final String f4253a = "_touch-remote._tcp.local.";
    private static final char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', PatternFormatter.CATEGORY_CONVERSION_CHAR, PatternFormatter.DATE_CONVERSION_CHAR, 'e', 'f'};

    public static void a(String[] strArr) {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.FINEST);
        Enumeration<String> loggerNames = LogManager.getLogManager().getLoggerNames();
        while (loggerNames.hasMoreElements()) {
            Logger logger = Logger.getLogger(loggerNames.nextElement());
            logger.addHandler(consoleHandler);
            logger.setLevel(Level.FINEST);
        }
        try {
            System.out.println("Opening JmDNS...");
            JmDNS a2 = JmDNS.a();
            System.out.println("Opened JmDNS!");
            Random random = new Random();
            int nextInt = random.nextInt(100000);
            PrintStream printStream = System.out;
            printStream.println("\nPress r and Enter, to register Itunes Remote service 'Android-'" + nextInt);
            while (true) {
                int read = System.in.read();
                if (read == -1 || ((char) read) == 'r') {
                    HashMap hashMap = new HashMap();
                    hashMap.put("DvNm", "Android-" + nextInt);
                    hashMap.put("RemV", "10000");
                    hashMap.put("DvTy", "iPod");
                    hashMap.put("RemN", "Remote");
                    hashMap.put("txtvers", "1");
                    byte[] bArr = new byte[8];
                    random.nextBytes(bArr);
                    hashMap.put("Pair", a(bArr));
                    byte[] bArr2 = new byte[20];
                    random.nextBytes(bArr2);
                    PrintStream printStream2 = System.out;
                    printStream2.println("Requesting pairing for " + a(bArr2));
                    ServiceInfo a3 = ServiceInfo.a("_touch-remote._tcp.local.", a(bArr2), 1025, 0, 0, (Map<String, ?>) hashMap);
                    a2.a(a3);
                    PrintStream printStream3 = System.out;
                    printStream3.println("\nRegistered Service as " + a3);
                    System.out.println("Press q and Enter, to quit");
                }
            }
            HashMap hashMap2 = new HashMap();
            hashMap2.put("DvNm", "Android-" + nextInt);
            hashMap2.put("RemV", "10000");
            hashMap2.put("DvTy", "iPod");
            hashMap2.put("RemN", "Remote");
            hashMap2.put("txtvers", "1");
            byte[] bArr3 = new byte[8];
            random.nextBytes(bArr3);
            hashMap2.put("Pair", a(bArr3));
            byte[] bArr22 = new byte[20];
            random.nextBytes(bArr22);
            PrintStream printStream22 = System.out;
            printStream22.println("Requesting pairing for " + a(bArr22));
            ServiceInfo a32 = ServiceInfo.a("_touch-remote._tcp.local.", a(bArr22), 1025, 0, 0, (Map<String, ?>) hashMap2);
            a2.a(a32);
            PrintStream printStream32 = System.out;
            printStream32.println("\nRegistered Service as " + a32);
            System.out.println("Press q and Enter, to quit");
            while (true) {
                int read2 = System.in.read();
                if (read2 == -1 || ((char) read2) == 'q') {
                    System.out.println("Closing JmDNS...");
                    a2.b(a32);
                    a2.e();
                    a2.close();
                    System.out.println("Done!");
                    System.exit(0);
                }
            }
            System.out.println("Closing JmDNS...");
            a2.b(a32);
            a2.e();
            a2.close();
            System.out.println("Done!");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b2 : bArr) {
            byte b3 = b2 & 255;
            sb.append(b[b3 / 16]);
            sb.append(b[b3 % 16]);
        }
        return sb.toString();
    }
}
