package org.apache.commons.compress.archivers.sevenz;

import com.taobao.weex.el.parse.Operators;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class CLI {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final byte[] f3223a = new byte[8192];

    private enum Mode {
        LIST("Analysing") {
            public void takeAction(SevenZFile sevenZFile, SevenZArchiveEntry sevenZArchiveEntry) {
                System.out.print(sevenZArchiveEntry.getName());
                if (sevenZArchiveEntry.isDirectory()) {
                    System.out.print(" dir");
                } else {
                    PrintStream printStream = System.out;
                    printStream.print(" " + sevenZArchiveEntry.p() + "/" + sevenZArchiveEntry.getSize());
                }
                if (sevenZArchiveEntry.f()) {
                    PrintStream printStream2 = System.out;
                    printStream2.print(" " + sevenZArchiveEntry.a());
                } else {
                    System.out.print(" no last modified date");
                }
                if (!sevenZArchiveEntry.isDirectory()) {
                    PrintStream printStream3 = System.out;
                    printStream3.println(" " + getContentMethods(sevenZArchiveEntry));
                    return;
                }
                System.out.println("");
            }

            private String getContentMethods(SevenZArchiveEntry sevenZArchiveEntry) {
                StringBuilder sb = new StringBuilder();
                boolean z = true;
                for (SevenZMethodConfiguration sevenZMethodConfiguration : sevenZArchiveEntry.q()) {
                    if (!z) {
                        sb.append(", ");
                    }
                    z = false;
                    sb.append(sevenZMethodConfiguration.a());
                    if (sevenZMethodConfiguration.b() != null) {
                        sb.append(Operators.BRACKET_START_STR);
                        sb.append(sevenZMethodConfiguration.b());
                        sb.append(Operators.BRACKET_END_STR);
                    }
                }
                return sb.toString();
            }
        },
        EXTRACT("Extracting") {
            public void takeAction(SevenZFile sevenZFile, SevenZArchiveEntry sevenZArchiveEntry) throws IOException {
                Throwable th;
                File file = new File(sevenZArchiveEntry.getName());
                if (!sevenZArchiveEntry.isDirectory()) {
                    PrintStream printStream = System.out;
                    printStream.println("extracting to " + file);
                    File parentFile = file.getParentFile();
                    if (parentFile == null || parentFile.exists() || parentFile.mkdirs()) {
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        try {
                            long size = sevenZArchiveEntry.getSize();
                            long j = 0;
                            while (j < size) {
                                int a2 = sevenZFile.a(CLI.f3223a, 0, (int) Math.min(size - j, (long) CLI.f3223a.length));
                                if (a2 >= 1) {
                                    j += (long) a2;
                                    fileOutputStream.write(CLI.f3223a, 0, a2);
                                } else {
                                    throw new IOException("reached end of entry " + sevenZArchiveEntry.getName() + " after " + j + " bytes, expected " + size);
                                }
                            }
                            fileOutputStream.close();
                            return;
                        } catch (Throwable unused) {
                        }
                    } else {
                        throw new IOException("Cannot create " + parentFile);
                    }
                } else if (file.isDirectory() || file.mkdirs()) {
                    PrintStream printStream2 = System.out;
                    printStream2.println("created directory " + file);
                    return;
                } else {
                    throw new IOException("Cannot create directory " + file);
                }
                throw th;
            }
        };
        
        private final String message;

        public abstract void takeAction(SevenZFile sevenZFile, SevenZArchiveEntry sevenZArchiveEntry) throws IOException;

        private Mode(String str) {
            this.message = str;
        }

        public String getMessage() {
            return this.message;
        }
    }

    public static void a(String[] strArr) throws Exception {
        Throwable th;
        if (strArr.length == 0) {
            b();
            return;
        }
        Mode b = b(strArr);
        PrintStream printStream = System.out;
        printStream.println(b.getMessage() + " " + strArr[0]);
        File file = new File(strArr[0]);
        if (!file.isFile()) {
            PrintStream printStream2 = System.err;
            printStream2.println(file + " doesn't exist or is a directory");
        }
        SevenZFile sevenZFile = new SevenZFile(file);
        while (true) {
            try {
                SevenZArchiveEntry a2 = sevenZFile.a();
                if (a2 != null) {
                    b.takeAction(sevenZFile, a2);
                } else {
                    sevenZFile.close();
                    return;
                }
            } catch (Throwable unused) {
            }
        }
        throw th;
    }

    private static void b() {
        System.out.println("Parameters: archive-name [list|extract]");
    }

    private static Mode b(String[] strArr) {
        if (strArr.length < 2) {
            return Mode.LIST;
        }
        return (Mode) Enum.valueOf(Mode.class, strArr[1].toUpperCase());
    }
}
