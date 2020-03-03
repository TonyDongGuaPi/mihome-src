package in.cashify.otex;

import in.cashify.otex.ExchangeError;

public final class SetupError extends ExchangeError {
    public SetupError(ExchangeError.Kind kind) {
        super(kind.getDetail(), kind);
    }

    public SetupError(String str, int i) {
        super(str, i);
    }
}
