package ru.grad.service;

import ru.grad.model.User;
import ru.grad.to.UserTo;
import ru.grad.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User save(User user);

    User get(int id) throws NotFoundException;

    List<User> getAll();

    void delete(int id) throws NotFoundException;

    void update(User user);

    void update(UserTo user);

    User getByEmail(String email) throws NotFoundException;

    void enable(int id, boolean enable);
}
