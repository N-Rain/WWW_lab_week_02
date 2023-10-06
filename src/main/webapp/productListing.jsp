<%@ page import="vn.edu.iuh.fit.lab_week_2_3.models.Product" %>
<%@ page import="vn.edu.iuh.fit.lab_week_2_3.services.ProductServices" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Croco
  Date: 06/10/2023
  Time: 10:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products Listing</title>
</head>
<body>
<%
    ProductServices productServices = new ProductServices();
    //product con dang kinh doanh
    List<Product> productList = productServices.getAllProduct();
%>
<table>
    <tr>
        <th>Id</th>
        <th>Description</th>
        <th>Manufacturer</th>
        <th>Name</th>
        <th>Status</th>
        <th>Unit</th>
        <th colspan="2"><a href="insertProduct.jsp">Insert</a></th>
    </tr>
    <%
        for (Product product: productList){
            long id = product.getProduct_id();
            String delete = "control?action=delete_product_id="+id;
            String edit = "controls?action=edit_product_id="+id;
    %>
    <tr>
        <td><%=id%></td>
        <td><%=product.getDescription()%></td>
        <td><%=product.getManufacturer()%></td>
        <td><%=product.getName()%></td>
        <td><%=product.getStatus()%></td>
        <td><%=product.getUnit()%></td>
        <td><a href=<%=edit%>></a></td>
        <td><a href=<%=delete%>></td>

    </tr>

    <%}%>
</table>
</body>
</html>
