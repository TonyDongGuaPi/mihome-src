package miuipub.util.async;

class TaskExecutingException extends RuntimeException {
    public TaskExecutingException(Exception exc) {
        super(exc);
    }
}
