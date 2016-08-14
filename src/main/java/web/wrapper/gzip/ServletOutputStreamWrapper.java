package web.wrapper.gzip;

import javax.servlet.ServletOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ServletOutputStreamWrapper extends ServletOutputStream {
    private final ByteArrayOutputStream byteArrayOutputStream;

    public ServletOutputStreamWrapper() throws IOException {
        super();
        byteArrayOutputStream = new ByteArrayOutputStream();
    }

    @Override
    public void close() throws IOException {
        byteArrayOutputStream.close();
    }

    @Override
    public void flush() throws IOException {
        byteArrayOutputStream.flush();
    }

    @Override
    public void write(final byte b[]) throws IOException {
        byteArrayOutputStream.write(b);
    }

    @Override
    public void write(final byte b[], final int off, final int len) {
        byteArrayOutputStream.write(b, off, len);
    }

    @Override
    public void write(final int b) {
        byteArrayOutputStream.write(b);
    }

    public byte[] getByteArray() {
        return byteArrayOutputStream.toByteArray();
    }
}
