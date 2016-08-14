package web.wrapper.gzip;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GZipServletResponseWrapper extends HttpServletResponseWrapper {
    private ServletOutputStreamWrapper servletOutputStreamWrapper;
    private PrintWriter printWriter;
    private final CharArrayWriter charArrayWriter;

    public GZipServletResponseWrapper(final HttpServletResponse response) {
        super(response);
        charArrayWriter = new CharArrayWriter();
    }

    public void close() throws IOException {
        if (printWriter != null) {
            printWriter.close();
        }
        if (servletOutputStreamWrapper != null) {
            servletOutputStreamWrapper.close();
        }
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (printWriter != null) {
            throw new IllegalStateException("PrintWriter obtained already - cannot get OutputStream");
        }
        if (servletOutputStreamWrapper == null) {
            servletOutputStreamWrapper = new ServletOutputStreamWrapper();
        }
        return servletOutputStreamWrapper;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (servletOutputStreamWrapper != null) {
            throw new IllegalStateException("OutputStream obtained already - cannot get PrintWriter");
        }
        if (printWriter == null) {
            printWriter = new PrintWriter(charArrayWriter);
        }
        return printWriter;
    }

    public char[] getCharArray() {
        return charArrayWriter.toCharArray();
    }

    public byte[] getByteArray() {
        return servletOutputStreamWrapper == null ? new byte[0] : servletOutputStreamWrapper.getByteArray();
    }
}