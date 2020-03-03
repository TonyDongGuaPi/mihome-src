package miuipub.hybrid;

import java.util.Map;

public interface HybridFeature {

    public enum Mode {
        SYNC,
        ASYNC,
        CALLBACK
    }

    Response a(Request request);

    void a(Map<String, String> map);

    Mode b(Request request);
}
