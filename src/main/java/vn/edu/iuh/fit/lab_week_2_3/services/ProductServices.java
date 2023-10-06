package vn.edu.iuh.fit.lab_week_2_3.services;

import vn.edu.iuh.fit.lab_week_2_3.enums.ProductStatus;
import vn.edu.iuh.fit.lab_week_2_3.models.Product;
import vn.edu.iuh.fit.lab_week_2_3.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

public class ProductServices {
    private ProductRepository repository;
    public ProductServices(){
        repository = new ProductRepository();
    }
    public void insertProd(Product product){
        repository.insertProd(product);
    }

    public void updateStatus(long id, ProductStatus status){
        repository.updateStatus(id,status);
    }
    public Optional<Product>findProductById(long id){
        return repository.findProdById(id);
    }

    //product khong con kinh doanh
    public boolean deleteProduct(long id){
        Optional<Product> op = findProductById(id);
        if (op.isPresent()){
            Product product = op.get();
            product.setStatus(ProductStatus.TERMINATED);
            return true;
        }
        return false;
    }

    //dang kinh doanh
    public boolean activeProduct(long id){
        Optional<Product> op = findProductById(id);
        if (op.isPresent()){
            Product product = op.get();
            product.setStatus(ProductStatus.ACTIVE);

            return true;
        }
        return false;
    }

    public List<Product> getAllProduct(){
        return repository.getAllProd();
    }


}
