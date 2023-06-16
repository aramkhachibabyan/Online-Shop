package am.smartCode.shop.service.user.impl;

import am.smartCode.shop.*;
import am.smartCode.shop.model.User;
import am.smartCode.shop.repository.user.UserRepository;
import am.smartCode.shop.service.user.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(String name, String lastname, String email, String password, int age, long balance) throws SQLException {

        Connection connection = userRepository.getConnection();
        connection.setAutoCommit(false);
        try {
            registerValidation(email, password);
            User user = new User();
            user.setName(name);
            user.setLastname(lastname);
            user.setEmail(email);
            user.setPassword(password);
            user.setAge(age);
            user.setBalance(balance);
            userRepository.create(user);
            connection.commit();
            System.out.println(email + " registration successfully");
        } catch (Throwable e) {
            connection.rollback();
            connection.setAutoCommit(true);
            System.out.println("Registration failed !");
        }


    }

    @Override
    public void login(String email, String password) throws SQLException {
        registerValidation(email, password);
        User user = userRepository.get(email);
        if (user == null) {
            throw new RuntimeException("No such user please register first! ");
        }
        System.out.println(user.getName() + " Login successfully");
    }

    private void registerValidation(String email, String password) {
        if (email == null || email.isEmpty()) {
            throw new RuntimeException("Email must not be blank");
        }
        if (password == null || password.isEmpty()) {
            throw new RuntimeException("Password must not be blank");
        }
        if (password.length() <= 8) {
            throw new RuntimeException("Password can not be less then 8 symbols");
        }
        if (!Pattern.compile("^(.+)@(\\S+)$").matcher(email).matches()) {
            throw new RuntimeException("Invalid email");
        }
    }
}