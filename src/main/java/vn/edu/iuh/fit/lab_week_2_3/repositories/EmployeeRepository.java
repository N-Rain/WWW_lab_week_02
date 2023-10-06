package vn.edu.iuh.fit.lab_week_2_3.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.iuh.fit.lab_week_2_3.enums.EmployeeStatus;
import vn.edu.iuh.fit.lab_week_2_3.models.Employee;
import vn.edu.iuh.fit.lab_week_2_3.models.Product;


import java.util.List;
import java.util.Optional;


public class EmployeeRepository {
    private EntityManager em;
    private EntityTransaction trans;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public EmployeeRepository() {
        em = Persistence
                .createEntityManagerFactory("lab_week_2_3")
                .createEntityManager();
        trans = em.getTransaction();
    }

    public void insertEmp(Employee employee) {
//        em.persist(employee);
        try {
            trans.begin();
            em.persist(employee);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            logger.error(ex.getMessage());
        }
    }

    public void setStatus(Employee employee, EmployeeStatus status) {

        employee.setStatus(status);
    }

    public void updateEmp(Employee employee) {
        try {
            trans.begin();
            em.merge(employee);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            logger.error(ex.getMessage());
        }
    }

    public void delEmp(Employee employee){
        try {
            trans.begin();
            Product delprod = em.find(Product.class,employee.getId());
            if(delprod != null){
                em.remove(employee);
            }
            trans.commit();
        } catch (Exception ex){
            trans.rollback();
            logger.error(ex.getMessage());
        }
    }

    public Optional<Employee> findbyId(long id) {
        TypedQuery<Employee> query = em.createQuery("select e from Employee e where e.id=:id", Employee.class);
        query.setParameter("id", id);
        Employee emp = query.getSingleResult();
        return emp == null ? Optional.empty() : Optional.of(emp);
    }

    public List<Employee> getAllEmp() {
        //paging if possible
        return em.createNamedQuery("Employee.findAll", Employee.class)
                .setParameter(1, EmployeeStatus.ACTIVE)
                .getResultList();
    }
    //...
}
