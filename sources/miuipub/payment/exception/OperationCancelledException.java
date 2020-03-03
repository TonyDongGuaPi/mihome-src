package miuipub.payment.exception;

public class OperationCancelledException extends Exception {
    public OperationCancelledException() {
    }

    public OperationCancelledException(String str) {
        super(str);
    }
}
