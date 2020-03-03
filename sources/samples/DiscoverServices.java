package samples;

import java.io.PrintStream;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;

public class DiscoverServices {

    static class SampleListener implements ServiceListener {
        SampleListener() {
        }

        public void a(ServiceEvent serviceEvent) {
            PrintStream printStream = System.out;
            printStream.println("Service added   : " + serviceEvent.getName() + "." + serviceEvent.getType());
        }

        public void b(ServiceEvent serviceEvent) {
            PrintStream printStream = System.out;
            printStream.println("Service removed : " + serviceEvent.getName() + "." + serviceEvent.getType());
        }

        public void c(ServiceEvent serviceEvent) {
            PrintStream printStream = System.out;
            printStream.println("Service resolved: " + serviceEvent.getInfo());
        }
    }

    public static void a(String[] strArr) {
        try {
            JmDNS a2 = JmDNS.a();
            a2.a("_http._tcp.local.", (ServiceListener) new SampleListener());
            System.out.println("Press q and Enter, to quit");
            while (true) {
                int read = System.in.read();
                if (read == -1 || ((char) read) == 'q') {
                    a2.close();
                    System.out.println("Done");
                }
            }
            a2.close();
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
