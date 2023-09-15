package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Yuliy", "Boytsev",(byte)23);
        userService.saveUser("Yuliy", "Boytsev",(byte)23);
        userService.saveUser("Yuliy", "Boytsev",(byte)23);
        userService.saveUser("Yuliy", "Boytsev",(byte)23);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
