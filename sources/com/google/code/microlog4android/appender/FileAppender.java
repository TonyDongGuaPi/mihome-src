package com.google.code.microlog4android.appender;

import android.os.Environment;
import android.util.Log;
import com.google.code.microlog4android.Level;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class FileAppender extends AbstractAppender {
    public static final String DEFAULT_FILENAME = "microlog.txt";
    private static final String TAG = "Microlog.FileAppender";
    private boolean append = false;
    private String fileName = DEFAULT_FILENAME;
    private PrintWriter writer;

    public long getLogSize() {
        return -1;
    }

    public void open() throws IOException {
        File sDCardFile = getSDCardFile();
        this.logOpen = false;
        if (sDCardFile != null) {
            if (!sDCardFile.exists() && !sDCardFile.createNewFile()) {
                Log.e(TAG, "Unable to create new log file");
            }
            this.writer = new PrintWriter(new FileOutputStream(sDCardFile, this.append));
            this.logOpen = true;
        }
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public void close() throws IOException {
        Log.i(TAG, "Closing the FileAppender");
        if (this.writer != null) {
            this.writer.close();
        }
    }

    public void doLog(String str, String str2, long j, Level level, Object obj, Throwable th) {
        if (this.logOpen && this.formatter != null && this.writer != null) {
            this.writer.println(this.formatter.format(str, str2, j, level, obj, th));
            this.writer.flush();
            if (th != null) {
                th.printStackTrace();
            }
        } else if (this.formatter == null) {
            Log.e(TAG, "Please set a formatter.");
        }
    }

    public void setFileName(String str) {
        if (str != null) {
            this.fileName = str;
        }
    }

    public void setAppend(boolean z) {
        this.append = z;
    }

    private File getSDCardFile() {
        String externalStorageState = Environment.getExternalStorageState();
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        File file = (!externalStorageState.equals("mounted") || externalStorageDirectory == null) ? null : new File(externalStorageDirectory, this.fileName);
        if (file == null) {
            Log.e(TAG, "Unable to open log file from external storage");
        }
        return file;
    }
}
