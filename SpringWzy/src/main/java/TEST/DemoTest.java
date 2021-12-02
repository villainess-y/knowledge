package TEST;

import org.junit.Test;

import java.lang.reflect.Field;

public class DemoTest {

    @Test
    public void testField() throws NoSuchFieldException, IllegalAccessException {
        UserController userController = new UserController();
        Class<? extends UserController> clazz = userController.getClass();
        Field userServiceField = clazz.getDeclaredField("userService");
        userServiceField.setAccessible(true);
        UserService userService = new UserService();
        userServiceField.set(userController,userService);
        System.out.println(userController.getUserService());
    }
}
