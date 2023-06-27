package am.smartCode.shop.service.product.impl;

import am.smartCode.shop.exceptions.ProductNotFoundException;
import am.smartCode.shop.exceptions.ProductValidationException;
import am.smartCode.shop.model.Product;
import am.smartCode.shop.repository.product.ProductRepository;
import am.smartCode.shop.service.product.ProductService;
import am.smartCode.shop.util.constants.Message;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class ProductServiceJpaImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public void createProduct(@NotBlank @NotEmpty @NotNull String category,
                              @NotBlank @NotEmpty @NotNull String name,
                              @NotBlank @NotEmpty @NotNull String publishedDate,
                              @Positive long price) throws SQLException {
        if (!Pattern.compile("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$").matcher(publishedDate).matches()) {
            throw new ProductValidationException(Message.INVALID_DATE_FORMAT);
        }
        Product product = new Product();
        product.setCategory(category);
        product.setName(name);
        product.setPublishedDate(publishedDate);
        product.setPrice(price);
        productRepository.create(product);


    }

    @Override
    public void updateProduct(@NotNull Product product) throws SQLException {
        if (productRepository.get(product.getId()) == null) {
            throw new ProductNotFoundException(Message.PRODUCT_NOT_FOUND);
        }
        productRepository.update(product);
    }

    @Override
    public Product getProduct(@Positive long id) throws SQLException {
        if (productRepository.get(id) == null) {
            throw new ProductNotFoundException(Message.PRODUCT_NOT_FOUND);
        }
        return productRepository.get(id);
    }

    @Override
    public void deleteProduct(@Positive long id) throws SQLException {
        if (productRepository.get(id) == null) {
            throw new ProductNotFoundException(Message.PRODUCT_NOT_FOUND);
        }
        productRepository.delete(id);
    }

    @Override
    public List<Product> getAllProducts() throws SQLException {
        return productRepository.getAll();
    }

    @Override
    public List<Product> getProductsByName(@NotBlank @NotEmpty @NotNull String name) throws SQLException {
        return productRepository.findProductsByName(name);
    }
}
