package vn.edu.iuh.fit.lab_week_2_3.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.lab_week_2_3.enums.ProductStatus;
import vn.edu.iuh.fit.lab_week_2_3.models.Product;

import java.util.List;
import java.util.Optional;


public class ProductRepository {
    //    @PersistenceContext
    private EntityManager em;
    private EntityTransaction trans;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public ProductRepository() {
        em = Persistence
                .createEntityManagerFactory("lab_week_2_3")
                .createEntityManager();
        trans = em.getTransaction();
    }

    public void insertProd(Product product) {
//        em.persist(product);
        try {
            trans.begin();
            em.persist(product);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            logger.error(e.getMessage());
        }
    }

    //set trang thai product
    public void setStatus(Product product, ProductStatus status){
        product.setStatus(status);
    }

    public void updateProd(Product product){
        try {
            trans.begin();
            em.merge(product);
            trans.commit();
        } catch (Exception ex){
            trans.rollback();
            logger.error(ex.getMessage());
        }
    }

    public void delPro(Product product){
        try {
            trans.begin();
            Product delprod = em.find(Product.class,product.getProduct_id());
            if(delprod != null){
                em.remove(product);
            }
            trans.commit();
        } catch (Exception ex){
            trans.rollback();
            logger.error(ex.getMessage());
        }
    }

    public Optional<Product> findProdById(long id){
        TypedQuery<Product> query = em.createQuery("select p from Product p where p.product_id=:id", Product.class);
        query.setParameter("id", id);
        Product product = query.getSingleResult();
        return product == null ? Optional.empty() : Optional.of(product);
    }

    public List<Product> getAllProd(){
        return em.createNamedQuery("Product.findAll", Product.class).setParameter(1,ProductStatus.ACTIVE).getResultList();
    }
    public Optional<Product> findByID(long id){
        Product product = em.createNamedQuery("Product.findById", Product.class).setParameter(1,id).getSingleResult();
        return product == null ? Optional.empty() : Optional.of(product);
    }

}