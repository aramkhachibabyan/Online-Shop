package am.smartCode.shop.repository.order.impl;

import am.smartCode.shop.model.Order;
import am.smartCode.shop.repository.order.OrderRepository;
import am.smartCode.shop.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    private final Connection connection;
    @Override
    public Connection getConnection(){
        return connection;
    }

    public OrderRepositoryImpl(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
        try {
            this.connection.createStatement().executeUpdate(
                    """
                            CREATE TABLE IF NOT EXISTS orders (
                            id bigserial primary key,
                            user_id bigint not null
                            constraint fk_order_user
                            references users,
                            product_id bigint not null
                            constraint fk_order_product
                            references products,
                            count_of_product integer not null,
                            total_price integer not null
                            )
                            """);
        } catch (SQLException e) {
            System.out.println("Connection exception");
        }
    }

    @Override
    public void create(Order order) throws SQLException {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO orders (user_id,product_id,count_of_product,total_price) VALUES (?,?,?,?)"
            );
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setLong(2, order.getProductId());
            preparedStatement.setInt(3, order.getCountOfProducts());
            preparedStatement.setLong(4, order.getTotalPrice());
            preparedStatement.executeUpdate();

            preparedStatement.close();
    }

    @Override
    public Order getOrderWithOrderId(Long id) throws SQLException {
        connection.setReadOnly(true);
        Order order = new Order();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from orders WHERE id = ?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            setOrderFields(order, resultSet);
        }
        resultSet.close();
        preparedStatement.close();
        connection.setReadOnly(false);
        return order;
    }

    @Override
    public List<Order> getAllOrdersOfOnePerson(Long id) throws SQLException {
        List<Order> orders = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from orders WHERE user_id = ?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        addOrderToListFromResultSet(orders, resultSet);
        return orders;
    }

    @Override
    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * from orders");
        addOrderToListFromResultSet(orders, resultSet);
        return orders;
    }

    private void setOrderFields(Order order, ResultSet resultSet) throws SQLException {
        order.setId(resultSet.getLong("id"));
        order.setUserId(resultSet.getLong("user_id"));
        order.setProductId(resultSet.getLong("product_id"));
        order.setCountOfProducts(resultSet.getInt("count_of_product"));
        order.setTotalPrice(resultSet.getLong("total_price"));
    }

    private void addOrderToListFromResultSet(List<Order> orders, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Order order = new Order();
            setOrderFields(order, resultSet);
            orders.add(order);
        }
    }
}
