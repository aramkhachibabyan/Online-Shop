package am.smartCode.shop.repository.product.impl;

import am.smartCode.shop.model.Product;
import am.smartCode.shop.model.User;
import am.smartCode.shop.repository.product.ProductRepository;
import am.smartCode.shop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductRepositoryJpaImpl implements ProductRepository {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    @Override
    public void create(Product product) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        session.save(product);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Product product) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        session.update(product);
        transaction.commit();
        session.close();
    }

    @Override
    public Product get(long id) throws SQLException {
        Session session = sessionFactory.openSession();
        Product product = session.get(Product.class, id);
        session.close();
        return product;
    }

    @Override
    public List<Product> getAll() throws SQLException {
        Session session = sessionFactory.openSession();
        NativeQuery<Product> nativeQuery = session.createNativeQuery(
                "SELECT * FROM products",
                Product.class
        );
        return nativeQuery.getResultList();
    }

    @Override
    public List<Product> findProductsByName(String name) throws SQLException {
        Session session = sessionFactory.openSession();
        NativeQuery<Product> nativeQuery = session.createNativeQuery(
                "SELECT * FROM products WHERE lower(name) LIKE lower(concat('%',:name,'%'));",
                Product.class
        );
        nativeQuery.setParameter("name", name);
        return nativeQuery.getResultList();
    }

    @Override
    public void delete(long id) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(get(id));
        transaction.commit();
        session.close();
    }
}
