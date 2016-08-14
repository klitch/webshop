package web.servlet;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.captcha.provider.AbstractCaptchaProvider;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Servlet implementation class Captcha
 */
@WebServlet(name = "captcha", urlPatterns = {"/captcha"})
public class CaptchaRenderer extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(CaptchaRenderer.class);

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (LOG.isTraceEnabled()) {
            LOG.trace("Captcha renderer servlet starts");
        }
        int width = 180;
        int height = 50;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = createGraphicsObject(bufferedImage, width, height);
        Random r = new Random();

        AbstractCaptchaProvider captchaProvider = (AbstractCaptchaProvider) request
                .getServletContext().getAttribute("captchaStorage");

        String cap = captchaProvider.getCaptcha(request).getCaptchaToken();

        int x = 0;
        int y;
        for (int i = 0; i < cap.toCharArray().length; i++) {
            x += 10 + (Math.abs(r.nextInt()) % 15);
            y = 20 + Math.abs(r.nextInt()) % 20;
            g2d.drawChars(cap.toCharArray(), i, 1, x, y);
        }

        g2d.dispose();
        response.setContentType("image/png");
        ImageIO.write(bufferedImage, "png", response.getOutputStream());
        if (LOG.isTraceEnabled()) {
            LOG.trace("Captcha image rendered");
        }
    }

    private Graphics2D createGraphicsObject(BufferedImage bufferedImage, int width, int height) {
        Graphics2D g2d = bufferedImage.createGraphics();

        Font font = new Font("Georgia", Font.BOLD, 18);
        g2d.setFont(font);

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        GradientPaint gp = new GradientPaint(0, 0, Color.white, 0, height / 2, Color.CYAN, true);

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(new Color(85, 85, 85));
        return g2d;
    }
}
