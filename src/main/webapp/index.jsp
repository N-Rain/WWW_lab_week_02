<%@ page import="vn.edu.iuh.fit.lab_week_2_3.repositories.EmployeeRepository" %>
<%@ page import="vn.edu.iuh.fit.lab_week_2_3.models.Employee" %>
<%@ page import="vn.edu.iuh.fit.lab_week_2_3.repositories.ProductRepository" %>
<%@ page import="vn.edu.iuh.fit.lab_week_2_3.models.Product" %>
<%@ page import="vn.edu.iuh.fit.lab_week_2_3.models.ProductImage" %>
<%@ page import="vn.edu.iuh.fit.lab_week_2_3.enums.ProductStatus" %>
<%@ page import="vn.edu.iuh.fit.lab_week_2_3.enums.EmployeeStatus" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Rest API</title>
</head>
<body>
<%
    EmployeeRepository repository = new EmployeeRepository();
    Employee emp = new Employee("teo", LocalDate.now(), "teo" + System.currentTimeMillis() + "@mail.com",
            "2349235", "12 NVB", EmployeeStatus.ACTIVE);
    repository.insertEmp(emp);

    out.print(emp);

    ProductRepository productRepository = new ProductRepository();
    Product product = new Product("xoai", "xoai ngot", "kg", "ba dua", ProductStatus.ACTIVE);

    ProductImage productImage = new ProductImage();
    productImage.setAlternative("xxx xxx");
    productImage.setPath("/zzz.jpg");
    productImage.setProduct(product);

    product.getProductImageList().add(productImage);

    productRepository.insertProd(product);

    out.print(product);

    out.print("OK");
%>
</body>
</html>