package samples;

import java.util.Enumeration;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.jmdns.JmDNS;

public class OpenJmDNS {
    public static void a(String[] strArr) {
        try {
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.FINEST);
            Enumeration<String> loggerNames = LogManager.getLogManager().getLoggerNames();
            while (loggerNames.hasMoreElements()) {
                Logger logger = Logger.getLogger(loggerNames.nextElement());
                logger.addHandler(consoleHandler);
                logger.setLevel(Level.FINEST);
            }
            JmDNS a2 = JmDNS.a();
            System.out.println("Press q and Enter, to quit");
            while (true) {
                int read = System.in.read();
                if (read == -1 || ((char) read) == 'q') {
                    a2.close();
                }
            }
            a2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
