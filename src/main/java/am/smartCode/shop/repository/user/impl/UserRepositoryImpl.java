package am.smartCode.shop.repository.user.impl;

import am.smartCode.shop.*;
import am.smartCode.shop.model.User;
import am.smartCode.shop.repository.user.UserRepository;
import am.smartCode.shop.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final Connection connection;

    public UserRepositoryImpl(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
        try {
            this.connection.createStatement().executeUpdate(
                    """
                            CREATE TABLE IF NOT EXISTS users (
                            id bigserial primary key,
                            name varchar(255) not null,
                            lastname varchar(255) not null,
                            username varchar(255) not null unique,
                            password varchar(255) not null,
                            age integer not null,
                            balance integer not null
                            )
                            """);
        } catch (SQLException e) {
            System.out.println("Connection exception");
        }

    }

    @Override
    public void create(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (name,lastname,username,password,age,balance) VALUES (?,?,?,?,?,?)"
            );
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getAge());
            preparedStatement.setLong(6, user.getBalance());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(User user) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users SET name = ?, lastname = ?, username = ?, password = ?, age = ?, balance = ? WHERE id = ?"
            );

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getAge());
            preparedStatement.setLong(6, user.getBalance());
            preparedStatement.setLong(7, user.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public User get(Long id) {
        try {
            User user = new User();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from users WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setUserFields(user, resultSet);
                resultSet.close();
                preparedStatement.close();
                return user;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public User get(String email) {
        try {
            User user = new User();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from users WHERE username = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                setUserFields(user, resultSet);
                resultSet.close();
                preparedStatement.close();
                return user;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public List<User> getAll() {
        try {
            List<User> usersList = new ArrayList<>();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * from users");
            addUserToListFromResultSet(usersList, resultSet);
            return usersList;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public List<User> findUsersByName(String name) {

        try {

            List<User> users = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users WHERE lower(name) LIKE lower(concat('%',?,'%'))"
            );
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            addUserToListFromResultSet(users, resultSet);
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public void delete(long id) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE from users WHERE id = ?");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public Connection getConnection() {
        return connection;
    }

    private void setUserFields(User user, ResultSet resultSet) {
        try {
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setLastname(resultSet.getString("lastname"));
            user.setEmail(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setAge(resultSet.getInt("age"));
            user.setBalance(resultSet.getLong("balance"));
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    private void addUserToListFromResultSet(List<User> usersList, ResultSet resultSet) {
        try {

            while (resultSet.next()) {
                User user = new User();
                setUserFields(user, resultSet);
                usersList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
