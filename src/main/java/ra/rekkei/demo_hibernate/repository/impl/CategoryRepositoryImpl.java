package ra.rekkei.demo_hibernate.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.rekkei.demo_hibernate.model.entity.Category;
import ra.rekkei.demo_hibernate.repository.CategoryRepository;

import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> findAll() {
        Session session = sessionFactory.openSession();
        try{
            List list = session.createQuery("from Category").list();
            return list;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean save(Category cate) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.save(cate);
            session.getTransaction().commit();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean update(Category cate) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.update(cate);
            session.getTransaction().commit();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean delete(Integer cateId) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.delete(getCategoryById(cateId));
            session.getTransaction().commit();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public Category getCategoryById(Integer cateId) {
        Session session = sessionFactory.openSession();
        try{
            Category category = session.get(Category.class, cateId);
            return category;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
}
