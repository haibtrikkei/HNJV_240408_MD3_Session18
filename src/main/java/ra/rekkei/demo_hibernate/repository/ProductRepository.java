package ra.rekkei.demo_hibernate.repository;

import ra.rekkei.demo_hibernate.model.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    Product getProductById(Integer proId);
    boolean save(Product pro);
    boolean update(Product pro);
    boolean delete(Integer proId);
    List<Product> findByName(String proName);
}
