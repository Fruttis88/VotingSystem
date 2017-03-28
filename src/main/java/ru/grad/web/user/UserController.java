package ru.grad.web.user;

import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.grad.AuthorizedUser;
import ru.grad.model.User;
import ru.grad.service.UserService;
import ru.grad.to.UserTo;

import javax.validation.Valid;


@RestController
@RequestMapping(UserController.URL)
public class UserController extends AbstractUserController {
    static final String URL = "/api/v1/profile";

    public UserController(UserService service, MessageSource messageSource) {
        super(service, messageSource);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return super.get(AuthorizedUser.id());
    }

    @DeleteMapping
    public void delete() {
        super.delete(AuthorizedUser.id());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody UserTo userTo) {
        super.update(userTo, AuthorizedUser.id());
    }
}
