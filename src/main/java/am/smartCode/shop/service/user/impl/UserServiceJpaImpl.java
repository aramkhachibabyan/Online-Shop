package am.smartCode.shop.service.user.impl;

import am.smartCode.shop.exceptions.UserNotFoundException;
import am.smartCode.shop.exceptions.ValidationException;
import am.smartCode.shop.model.User;
import am.smartCode.shop.repository.user.UserRepository;
import am.smartCode.shop.service.user.UserService;
import am.smartCode.shop.util.constants.Message;
import am.smartCode.shop.util.encoder.MD5Encoder;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class UserServiceJpaImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void register(@NotBlank @NotEmpty @NotNull String name,
                         @NotBlank @NotEmpty @NotNull String lastname,
                         @Email @NotBlank @NotEmpty @NotNull String email,
                         @Size(min = 8) @NotBlank @NotEmpty @NotNull String password,
                         @Positive int age,
                         @PositiveOrZero long balance) throws SQLException {
        if (userRepository.get(email) != null) {
            throw new ValidationException(Message.EMAIL_IS_NOT_AVAILABLE);
        }
        User user = new User();
        user.setName(name);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPassword(MD5Encoder.encode(password));
        user.setAge(age);
        user.setBalance(balance);
        userRepository.create(user);
    }

    @Override
    public void login(@Email @NotBlank @NotEmpty @NotNull String email,
                      @Size(min = 8) @NotBlank @NotEmpty @NotNull String password) throws SQLException {
        User user = userRepository.get(email);
        if (user == null) {
            throw new UserNotFoundException(Message.USER_NOT_FOUND);
        }
        if (!Objects.equals(user.getPassword(), MD5Encoder.encode(password))) {
            throw new ValidationException(Message.INVALID_PASSWORD);
        }
    }

    @Override
    public void deleteUser(@Email @NotBlank @NotEmpty @NotNull String email,
                           @Size(min = 8) @NotBlank @NotEmpty @NotNull String password) throws SQLException {
        if (!userRepository.get(email).getPassword().equals(MD5Encoder.encode(password))) {
            throw new ValidationException(Message.INVALID_PASSWORD);
        }
        userRepository.delete(userRepository.get(email).getId());
    }

    @Override
    public void updateUser(@Email @NotBlank @NotEmpty @NotNull String email,
                           @Size(min = 8) @NotBlank @NotEmpty @NotNull String newPassword,
                           @Size(min = 8) @NotBlank @NotEmpty @NotNull String repeatPassword) throws SQLException {
        if (!Objects.equals(newPassword, repeatPassword)) {
            throw new RuntimeException("Passwords does not match");
        }
        User user = userRepository.get(email);
        user.setPassword(newPassword);
        userRepository.update(user);
    }

}

