package pl.droidsonroids.relinker.elf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import pl.droidsonroids.relinker.elf.Elf;

public class Program32Header extends Elf.ProgramHeader {
    public Program32Header(ElfParser elfParser, Elf.Header header, long j) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(header.d ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = header.f + (j * ((long) header.h));
        this.c = elfParser.c(allocate, j2);
        this.d = elfParser.c(allocate, 4 + j2);
        this.e = elfParser.c(allocate, 8 + j2);
        this.f = elfParser.c(allocate, j2 + 20);
    }
}
