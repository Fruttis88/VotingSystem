package ru.grad.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.grad.model.Role;
import ru.grad.model.User;
import ru.grad.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static ru.grad.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

    @Test
    public void testSave() throws Exception{
        User newUser = new User(null, "New", "new@yandex.ru", "newPass", Collections.singleton(Role.ROLE_USER), false);
        User created = service.save(newUser);
        newUser.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(USER, ADMIN, USER2, newUser), service.getAll());
    }
    @Test(expected = DataAccessException.class)
    public void testDuplicateMailSave() throws Exception {
        service.save(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER));
    }
    @Test
    public void testDelete() throws Exception {
        service.delete(USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER2), service.getAll());
    }
    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(99);
    }
    @Test
    public void testGet() throws Exception {
        User user = service.get(ADMIN_ID);
        MATCHER.assertEquals(ADMIN, user);
    }
    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(99);
    }
    @Test
    public void testGetByEmail() throws Exception {
        User user = service.getByEmail("admin@gmail.com");
        MATCHER.assertEquals(ADMIN, user);
    }
    @Test
    public void testGetAll() throws Exception {
        Collection<User> all = service.getAll();
        MATCHER.assertCollectionEquals(Arrays.asList(USER, ADMIN, USER2), all);
    }
    @Test
    public void testUpdate() throws Exception {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        updated.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(USER_ID));
    }
    @Test
    public void testSetEnabledEquals() {
        service.enable(USER_ID, false);
        Assert.assertFalse(service.get(USER_ID).isEnabled());
        service.enable(USER_ID, true);
        Assert.assertTrue(service.get(USER_ID).isEnabled());
    }
}
