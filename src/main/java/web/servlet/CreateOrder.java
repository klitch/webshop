package web.servlet;

import entity.Cart;
import entity.order.Order;
import entity.order.OrderStatus;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

@WebServlet(name = "createOrder", urlPatterns = {"/create_order"})
public class CreateOrder extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(CreateOrder.class);
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        orderService = (OrderService) getServletContext().getAttribute("orderService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");
        String card = null;
        String address = null;
        if (session.getAttribute("card") != null) {
            card = String.valueOf(session.getAttribute("card"));
        }
        if (session.getAttribute("address") != null) {
            address = String.valueOf(session.getAttribute("address"));
        }
        String paymentType = request.getParameter("payment_type");
        String shippingType = request.getParameter("shipping_type");

        Order order = new Order(UUID.randomUUID().toString(), user.getId(), address, card, paymentType, shippingType, OrderStatus.ACCEPTED, Calendar.getInstance().getTime(), cart.getAllProducts());
        if (LOG.isDebugEnabled()) {
            LOG.debug("Order created");
        }
        if (orderService.addOrder(order)) {
            cart.clean();
        }
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("orderSuccess.jsp").forward(request, response);
    }
}
