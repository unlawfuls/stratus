package dev.stratus.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Streams {

    private static final int BUFFER = 8192;

    private Streams() {
    }

    public static long copy(InputStream in, OutputStream out) throws IOException {
        byte[] buf = new byte[BUFFER];
        long total = 0;
        int read;
        while ((read = in.read(buf)) != -1) {
            out.write(buf, 0, read);
            total += read;
        }
        return total;
    }

    public static byte[] readAll(Path path) throws IOException {
        return Files.readAllBytes(path);
    }

    public static void writeAtomic(Path path, byte[] data) throws IOException {
        Path tmp = path.resolveSibling(path.getFileName() + ".tmp");
        Files.write(tmp, data);
        try {
            Files.move(tmp, path, java.nio.file.StandardCopyOption.ATOMIC_MOVE,
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        } catch (java.nio.file.AtomicMoveNotSupportedException e) {
            Files.move(tmp, path, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
