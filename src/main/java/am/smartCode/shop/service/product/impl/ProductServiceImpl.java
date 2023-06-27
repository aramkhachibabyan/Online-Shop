package am.smartCode.shop.service.product.impl;

import am.smartCode.shop.*;
import am.smartCode.shop.exceptions.ProductNotFoundException;
import am.smartCode.shop.exceptions.ProductValidationException;
import am.smartCode.shop.model.Product;
import am.smartCode.shop.repository.product.ProductRepository;
import am.smartCode.shop.repository.product.impl.ProductRepositoryJpaImpl;
import am.smartCode.shop.repository.product.impl.ProductRepositoryimpl;
import am.smartCode.shop.service.product.ProductService;
import am.smartCode.shop.util.constants.Message;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class ProductServiceImpl implements ProductService {
    private final ProductRepositoryimpl productRepository;

    public ProductServiceImpl(ProductRepositoryimpl productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void createProduct(String category, String name, String publishedDate, long price) throws SQLException {
        productValidation(category, name, publishedDate, price);
        Product product = new Product();
        product.setCategory(category);
        product.setName(name);
        product.setPublishedDate(publishedDate);
        product.setPrice(price);
        productRepository.create(product);

    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        if (product.getId() <= 0) {
            throw new ProductValidationException(Message.INVALID_ID);
        }
        if (productRepository.get(product.getId()) == null) {
            throw new ProductNotFoundException(Message.PRODUCT_NOT_FOUND);
        }
        productValidation(product.getCategory(), product.getName(), product.getPublishedDate(), product.getPrice());
        Connection connection = productRepository.getConnection();
        connection.setAutoCommit(false);
        try {
            productRepository.update(product);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            connection.setAutoCommit(true);
        }
    }

    @Override
    public Product getProduct(long id) throws SQLException {
        Connection connection = productRepository.getConnection();
        if (id <= 0) {
            throw new ProductValidationException(Message.INVALID_ID);
        }
        if (productRepository.get(id) == null) {
            throw new ProductNotFoundException(Message.PRODUCT_NOT_FOUND);
        }
        return productRepository.get(id);
    }

    @Override
    public void deleteProduct(long id) throws SQLException {
        Connection connection = productRepository.getConnection();
        connection.setAutoCommit(false);
        if (id <= 0) {
            throw new ProductValidationException(Message.INVALID_ID);
        }
        if (productRepository.get(id) == null){
            throw new ProductNotFoundException(Message.PRODUCT_NOT_FOUND);
        }
        try {
            productRepository.delete(id);
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            connection.setAutoCommit(false);
        }
    }

    @Override
    public List<Product> getAllProducts() throws SQLException {
        Connection connection = productRepository.getConnection();
        return productRepository.getAll();
    }

    @Override
    public List<Product> getProductsByName(String name) throws SQLException {
        Connection connection = productRepository.getConnection();
        return productRepository.findProductsByName(name);
    }

    private static void productValidation(String category, String name, String publishedDate, long price) {
        if (category == null || category.isEmpty()) {
            throw new ProductValidationException(Message.BLANK_PRODUCT_CATEGORY);
        }
        if (name == null || name.isEmpty()) {
            throw new ProductValidationException(Message.BLANK_PRODUCT_NAME);
        }
        if (!Pattern.compile("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$").matcher(publishedDate).matches()) {
            throw new ProductValidationException(Message.INVALID_DATE_FORMAT);
        }
        if (price <= 0) {
            throw new ProductValidationException(Message.INVALID_PRICE);
        }
    }
}
