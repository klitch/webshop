package web.servlet.filter;

import web.wrapper.gzip.GZipServletResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.zip.GZIPOutputStream;

@WebFilter(filterName = "/GzipFilter", urlPatterns = {"/*"})
public class GzipFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (acceptsGZipEncoding(httpRequest)) {
            final GZipServletResponseWrapper gzipResponse = new GZipServletResponseWrapper(httpResponse);
            chain.doFilter(request, gzipResponse);
            gzipResponse.close();
            if (httpResponse.getContentType() != null && httpResponse.getContentType().contains("text")) {
                writeCompressed(gzipResponse.getByteArray(), gzipResponse.getCharArray(), httpResponse);
            } else {
                write(gzipResponse.getByteArray(), gzipResponse.getCharArray(), httpResponse);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    public void init(final FilterConfig filterConfig) throws ServletException {
    }

    private boolean acceptsGZipEncoding(final HttpServletRequest httpRequest) {
        final String acceptEncoding = httpRequest.getHeader("Accept-Encoding");
        return acceptEncoding != null && acceptEncoding.contains("gzip");
    }

    private void write(final byte[] byteArray, final char[] charArray, final HttpServletResponse httpResponse) throws IOException {
        if (byteArray.length != 0) {
            httpResponse.setContentLength(byteArray.length);
            httpResponse.getOutputStream().write(byteArray);
        } else {
            httpResponse.setContentLength(charArray.length);
            httpResponse.getWriter().write(charArray);
        }
    }

    private void writeCompressed(final byte[] byteArray, final char[] charArray, final HttpServletResponse httpResponse) throws IOException {
        try (final ByteArrayOutputStream byteStream = new ByteArrayOutputStream()) {
            if (charArray.length != 0) {
                try (final OutputStreamWriter streamWriter = new OutputStreamWriter(new GZIPOutputStream(byteStream), "UTF-8")) {
                    streamWriter.write(charArray);
                }
            } else {
                try (final OutputStream stream = new GZIPOutputStream(byteStream)) {
                    stream.write(byteArray);
                }
            }
            httpResponse.addHeader("Content-Encoding", "gzip");
            httpResponse.setContentLength(byteStream.size());
            byteStream.writeTo(httpResponse.getOutputStream());
        }
    }
}