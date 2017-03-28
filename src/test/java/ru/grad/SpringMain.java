package ru.grad;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.grad.model.User;
import ru.grad.web.user.AdminController;

import java.util.Arrays;
import java.util.List;

import static ru.grad.TestUtil.mockAuthorize;
import static ru.grad.UserTestData.USER;


public class SpringMain {
    public static void main(String[] args) {
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.load("spring/spring-app.xml", "spring/test-dispatcher-servlet.xml");
            appCtx.refresh();

            mockAuthorize(USER);

            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminController adminUserController = appCtx.getBean(AdminController.class);

            List<User> users = adminUserController.getAll();
            System.out.println(users);

        }
    }
}
