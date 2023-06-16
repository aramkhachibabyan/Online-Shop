package am.smartCode.shop.model;

public class Order {
    private long id;
    private long userId;
    private long productId;
    private int countOfProduct;
    private long totalPrice;

    public Order(long id, long userId, long productId, int countOfProducts, long totalPrice) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.countOfProduct = countOfProducts;
        this.totalPrice = totalPrice;
    }

    public Order() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getCountOfProducts() {
        return countOfProduct;
    }

    public void setCountOfProducts(int countOfProducts) {
        this.countOfProduct = countOfProducts;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", countOfProducts=" + countOfProduct +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
