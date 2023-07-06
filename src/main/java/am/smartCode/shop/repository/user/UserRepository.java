package am.smartCode.shop.repository.user;

import am.smartCode.shop.*;
import am.smartCode.shop.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.StringJoiner;

public interface UserRepository {

    void create(User user);

    void update(User user);

    User get(Long id);

    User get(String email);

    List<User> getAll();

    List<User> findUsersByName(String name);

    void delete(long id);

}
