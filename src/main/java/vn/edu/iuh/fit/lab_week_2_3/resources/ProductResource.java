package vn.edu.iuh.fit.lab_week_2_3.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.lab_week_2_3.models.Product;
import vn.edu.iuh.fit.lab_week_2_3.services.ProductServices;

import java.util.Optional;
import java.util.logging.Logger;

@Path("/products")
public class ProductResource {
    private  ProductServices productServices;
    private  Logger logger = (Logger) LoggerFactory.getLogger(this.getClass().getName());

    public ProductResource() {
        productServices = new ProductServices();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getProd(@PathParam("id") long pid) {
        Optional<Product> proOpt = productServices.findProductById(pid);
        if (proOpt.isPresent()) {
            return Response.ok(proOpt.get()).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response insert(Product product) {
        //ResponseEntity
        productServices.insertProd(product);
        return Response.ok(product).build();
    }
}
