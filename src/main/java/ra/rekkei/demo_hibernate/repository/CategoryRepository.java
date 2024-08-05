package ra.rekkei.demo_hibernate.repository;

import ra.rekkei.demo_hibernate.model.entity.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAll();
    boolean save(Category cate);
    boolean update(Category cate);
    boolean delete(Integer cateId);
    Category getCategoryById(Integer cateId);
}
