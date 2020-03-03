package samples;

import java.io.IOException;
import java.io.PrintStream;

public class TestShutdownHook {
    public static void a(String[] strArr) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Shutdown Hook");
            }
        });
        while (true) {
            try {
                int read = System.in.read();
                if (read != -1) {
                    PrintStream printStream = System.out;
                    printStream.print("\"" + ((char) read));
                } else {
                    return;
                }
            } catch (IOException unused) {
                return;
            }
        }
    }
}
