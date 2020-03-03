package org.jsoup;

import java.io.IOException;

public class UncheckedIOException extends Error {
    public UncheckedIOException(IOException iOException) {
        super(iOException);
    }

    public IOException ioException() {
        return (IOException) getCause();
    }
}
