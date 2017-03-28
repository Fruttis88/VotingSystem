package ru.grad.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import ru.grad.model.User;
import ru.grad.service.UserService;
import ru.grad.to.UserTo;

import java.util.List;

import static ru.grad.util.ValidationUtil.checkIdConsistent;
import static ru.grad.util.ValidationUtil.checkNew;


public abstract class AbstractUserController {
    private final Logger LOG = LoggerFactory.getLogger(AbstractUserController.class);

    private final MessageSource messageSource;

    private final UserService service;

    @Autowired
    public AbstractUserController(UserService service, MessageSource messageSource) {
        this.service = service;
        this.messageSource = messageSource;
    }

    public List<User> getAll() {
        LOG.info("getMenu");
        return service.getAll();
    }

    public User get(int id) {
        LOG.info("get " + id);
        return service.get(id);
    }

    public User create(User user) {
        checkNew(user);
        LOG.info("create " + user);
        return service.save(user);
    }

    public void delete(int id) {
        LOG.info("delete " + id);
        service.delete(id);
    }

    public void update(User user, int id) {
        LOG.info("update " + user);
        checkIdConsistent(user, id);
        service.update(user);
    }

    public void update(UserTo userTo, int id) {
        LOG.info("update " + userTo);
        checkIdConsistent(userTo, id);
        service.update(userTo);
    }

    public User getByMail(String email) {
        LOG.info("getByEmail " + email);
        return service.getByEmail(email);
    }

    public void enable(int id, boolean enabled) {
        LOG.info((enabled ? "enable " : "disable ") + id);
        service.enable(id, enabled);
    }
}
