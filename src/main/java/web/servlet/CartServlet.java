package web.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entity.Cart;
import entity.Product;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "cart", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    private Cart cart;

    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = (ProductService) getServletContext().getAttribute("productService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        String action = request.getParameter("action");
        String id = request.getParameter("productId");
        int productId = 0;
        if (id != null) {
            productId = Integer.parseInt(id);
        }
        PrintWriter out = response.getWriter();

        int newQuantity;
        try {
            newQuantity = Integer.parseInt(request.getParameter("newQuantity"));
        } catch (NumberFormatException e) {
            newQuantity = 1;
        }

        switch (action) {
            case "addToCart":
                addToCart(productId);
                break;
            case "removeFromCart":
                removeFromCart(productId);
                break;
            case "incrementQuantity":
                addToCart(productId);
                break;
            case "decrementQuantity":
                decrementQuantity(productId);
                break;
            case "changeQuantity":
                changeQuantity(productId, newQuantity);
                break;
            case "cleanCart":
                cart.clean();
                break;
        }

        Gson gson = new Gson();
        JsonObject myObj = new JsonObject();

        session.setAttribute("cart", cart);
        Product currentProduct = productService.getProductById(productId);
        if (cart == null) {
            myObj.addProperty("count", 0);
            myObj.addProperty("success", false);
        } else {
            myObj.addProperty("productQuantity", cart.getQuantity(currentProduct));
            if (cart.getQuantity(currentProduct) != null) {
                myObj.addProperty("productTotalPrice", currentProduct.getPrice() * cart.getQuantity(currentProduct));
            }
            myObj.addProperty("count", cart.size());
            myObj.addProperty("success", true);
            myObj.addProperty("total", cart.getTotal());
        }
        out.println(myObj.toString());
        out.close();
    }

    private void addToCart(int productId) {
        cart.add(productService.getProductById(productId));
    }

    private void removeFromCart(int productId) {
        cart.remove(productService.getProductById(productId));
    }

    private void decrementQuantity(int productId) {
        cart.decrementQuantity(productService.getProductById(productId));
    }

    private void changeQuantity(int productId, int newQuantity) {
        cart.changeQuantity(productService.getProductById(productId), newQuantity);
    }


}
