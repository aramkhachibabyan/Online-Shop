package am.smartCode.shop.repository.user;

import am.smartCode.shop.*;
import am.smartCode.shop.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.StringJoiner;

public interface UserRepository {

    void create(User user) throws SQLException;

    void update(User user) throws SQLException;

    User get(Long id) throws SQLException;
    User get(String email) throws SQLException;

    List<User> getAll() throws SQLException;

    List<User> findUsersByName(String name) throws SQLException;

    void delete(long id) throws SQLException;

}
