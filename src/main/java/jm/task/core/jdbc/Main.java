package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl usi = new UserServiceImpl();
        usi.createUsersTable();
        usi.saveUser("Vladimir","Ugryumov", (byte) 18);
        usi.saveUser("Ivan","Strunov", (byte) 19);
        usi.saveUser("David","Ovechkin", (byte) 20);
        usi.saveUser("Ignat","Vasilyev", (byte) 21);
        usi.getAllUsers().forEach(System.out::println);
        usi.cleanUsersTable();
        usi.dropUsersTable();
    }
}
