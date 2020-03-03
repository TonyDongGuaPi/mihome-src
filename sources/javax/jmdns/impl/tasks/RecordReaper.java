package javax.jmdns.impl.tasks;

import com.taobao.weex.el.parse.Operators;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jmdns.impl.JmDNSImpl;

public class RecordReaper extends DNSTask {

    /* renamed from: a  reason: collision with root package name */
    static Logger f2669a = Logger.getLogger(RecordReaper.class.getName());

    public RecordReaper(JmDNSImpl jmDNSImpl) {
        super(jmDNSImpl);
    }

    public String b() {
        StringBuilder sb = new StringBuilder();
        sb.append("RecordReaper(");
        sb.append(a() != null ? a().b() : "");
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }

    public void a(Timer timer) {
        if (!a().isCanceling() && !a().isCanceled()) {
            timer.schedule(this, 10000, 10000);
        }
    }

    public void run() {
        if (!a().isCanceling() && !a().isCanceled()) {
            if (f2669a.isLoggable(Level.FINEST)) {
                Logger logger = f2669a;
                logger.finest(b() + ".run() JmDNS reaping cache");
            }
            a().u();
        }
    }
}
