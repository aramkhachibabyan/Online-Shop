package am.smartCode.shop.repository.product;

import am.smartCode.shop.*;
import am.smartCode.shop.model.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ProductRepository {

    Connection getConnection();
    void create(Product product) throws SQLException;

    void update(Product product) throws SQLException;

    Product get(Long id) throws SQLException;

    List<Product> getAll() throws SQLException;

    List<Product> findProductsByName(String name) throws SQLException;

    void delete(Long id) throws SQLException;
}
