package ra.rekkei.demo_hibernate.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.rekkei.demo_hibernate.model.entity.Product;
import ra.rekkei.demo_hibernate.repository.ProductRepository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> findAll() {
        Session session = sessionFactory.openSession();
        try{
            List list = session.createQuery("from Product").list();
            return list;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Product getProductById(Integer proId) {
        Session session = sessionFactory.openSession();
        try{
            Product product = session.get(Product.class, proId);
            return product;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean save(Product pro) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.save(pro);
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
    public boolean update(Product pro) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.update(pro);
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
    public boolean delete(Integer proId) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.delete(getProductById(proId));
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
    public List<Product> findByName(String proName) {
        Session session = sessionFactory.openSession();
        try{
            if(proName==null || proName.isEmpty())
                proName = "%";
            else
                proName = "%"+proName+"%";
            List list = session.createQuery("from Product where proName like :proName")
                    .setParameter("proName",proName)
                    .list();
            return list;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
}
