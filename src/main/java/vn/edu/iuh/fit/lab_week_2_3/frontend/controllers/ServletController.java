package vn.edu.iuh.fit.lab_week_2_3.frontend.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.lab_week_2_3.frontend.model.ProductModel;

import java.io.IOException;

@WebServlet("/controls")
public class ServletController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Object actionObject = req.getParameter("action");
            if (actionObject != null){
                String action = actionObject.toString();
                if (action.equals("product_list")){
                    resp.sendRedirect("productListing.jsp");
                } else if (action.equals("delete_product")) {
                    ProductModel productModel = new ProductModel();
                    productModel.deleteProduct(req,resp);
                }
            } else {
                resp.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
}
