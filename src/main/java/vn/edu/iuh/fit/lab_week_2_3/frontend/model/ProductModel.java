package vn.edu.iuh.fit.lab_week_2_3.frontend.model;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.lab_week_2_3.enums.ProductStatus;
import vn.edu.iuh.fit.lab_week_2_3.models.Product;
import vn.edu.iuh.fit.lab_week_2_3.services.ProductServices;

public class ProductModel {
    private final ProductServices services = new ProductServices();
    public void insertProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String description = req.getParameter("description");
        String manufacturer = req.getParameter("manufacturer");
        String name = req.getParameter("name");
        String status = req.getParameter("status");
        String unit = req.getParameter("unit");

        Product product = new Product(name,description,unit,manufacturer,ProductStatus.valueOf(status));
        services.insertProd(product);
        resp.sendRedirect("productListing.jsp");
    }

    public void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        long id = Long.parseLong(req.getParameter("id"));
        //tam ngung
        services.updateStatus(id,ProductStatus.IN_ACTIVE);
        resp.sendRedirect("productListing.jsp");
    }
}
