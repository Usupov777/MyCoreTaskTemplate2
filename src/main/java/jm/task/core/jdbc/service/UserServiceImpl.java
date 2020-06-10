package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    Connection connection = Util.connection;
    Statement statement = Util.statement;
    public void createUsersTable() {
        try {
            statement.executeUpdate("CREATE TABLE users (" +
                    "id int auto_increment primary key," +
                    "name varchar(20) not null," +
                    "lastname varchar(20) not null," +
                    "age int not null)");
        } catch (SQLException e) {}
    }

    public void dropUsersTable() {
        try {
            statement.executeUpdate("DROP TABLE users;");
        } catch (SQLException e) {}
    }

    public void saveUser(String name, String lastName, byte age) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Users(name, lastname, age) VALUES(?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.printf("User с именем %s добавлен в базу данных \n", name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Users WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try{
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
            while(resultSet.next()){
                User user = new User(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        try {
            statement.executeUpdate("TRUNCATE TABLE Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
