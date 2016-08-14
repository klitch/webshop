package web.servlet;

import entity.order.CheckoutFormBean;
import entity.order.PaymentType;
import entity.order.ShippingType;
import web.validator.CheckoutInformationValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "orderDetails", urlPatterns = {"/order_details"})
public class OrderDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("checkoutDetails.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String card = request.getParameter("card");
        String address = request.getParameter("address");
        String paymentType = request.getParameter("payment_type");
        String shippingType = request.getParameter("shipping_type");
        CheckoutFormBean bean = new CheckoutFormBean(address, card);
        CheckoutInformationValidator validator = new CheckoutInformationValidator();
        Map<String, String> errorMap = validator.validate(bean);

        if (errorMap.size() != 0) {
            for (String key : errorMap.keySet()) {
                request.setAttribute(key, errorMap.get(key));
            }
            request.getRequestDispatcher("checkoutDetails.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("card", card);
            request.getSession().setAttribute("address", address);
            request.getSession().setAttribute("paymentType", PaymentType.valueOf(paymentType.toUpperCase()).toString());
            request.getSession().setAttribute("shippingType", ShippingType.valueOf(shippingType.toUpperCase()).toString());
            response.sendRedirect("confirmation.jsp");
        }
    }
}
