package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Alan", "Wake", (byte) 42);
        userService.saveUser("Marilyn", "Manson", (byte) 40);
        userService.saveUser("Egor", "Letov", (byte) 43);
        userService.saveUser("50", "Cent", (byte) 46);
        List<User> users = userService.getAllUsers();
        for(User user : users){
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
