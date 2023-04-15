package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    private List <User> users = null;

    private static final String sqlCommandCreateUserTable =
            "CREATE TABLE users (Id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), lastName VARCHAR(100), age INT(3))";
    private static final String sqlCommandSaveUser =
            "INSERT INTO users (name, lastName, age) VALUES( ?, ?, ?)";
    private static final String sqlCommandDeleteUser =
            "DELETE FROM users WHERE Id=?";
    private static final String sqlCommandDeleteUserTable =
            "DROP TABLE users";
    private static final String sqlCommandClearTable =
            "TRUNCATE TABLE users";
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(sqlCommandCreateUserTable);
            System.out.println("Таблица создана!");
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Таблица уже существует");
        } catch (SQLException e) {
            System.out.println("Таблица не создана!");
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(sqlCommandDeleteUserTable);
            System.out.println("Таблица удалена!");
        } catch (SQLSyntaxErrorException e) {
            System.out.println("Таблицы не существует!");
        } catch (SQLException e) {
            System.out.println("Таблица не удалена!");
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement ps = getConnection().prepareStatement(sqlCommandSaveUser)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.execute();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Пользователь не добавлен!");
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement ps = getConnection().prepareStatement(sqlCommandDeleteUser)) {
            ps.setLong(1, id);
            ps.executeUpdate();
            System.out.println("User по идексу " + id + " удален из базы данных");
        } catch (SQLException e) {
            System.out.println("Ошибка удаления!");
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        try  (Statement statement = getConnection().createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM users");
            users = new ArrayList<>();
            while (result.next()) {
                User user = new User(result.getString("name"), result.getString("lastName"), result.getByte("age"));
                user.setId(result.getLong("Id"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(sqlCommandClearTable);
            System.out.println("Таблица очищена!");
        } catch (SQLException e) {
            System.out.println("Таблица не очищена!");
            throw new RuntimeException(e);
        }
    }
}
