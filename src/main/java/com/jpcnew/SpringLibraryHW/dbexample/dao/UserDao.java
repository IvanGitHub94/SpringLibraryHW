package com.jpcnew.SpringLibraryHW.dbexample.dao;

import com.jpcnew.SpringLibraryHW.dbexample.model.Book;
import com.jpcnew.SpringLibraryHW.dbexample.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@Component
public class UserDao {
    private final Connection connection;

    private final String SELECT_BOOK_BY_ID_QUERY = "select * from public.users where id = ?";

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public User findUserById(Integer userId) throws SQLException {
        PreparedStatement selectQuery = connection.prepareStatement(SELECT_BOOK_BY_ID_QUERY);
        selectQuery.setInt(1, userId);
        ResultSet resultSet = selectQuery.executeQuery();
        User user = new User();
        while (resultSet.next()) {
            user.setId(resultSet.getInt("id"));
            user.setSurname(resultSet.getString("surname"));
            user.setName(resultSet.getString("name"));
            user.setBirthDate(resultSet.getDate("date_birth"));
            user.setPhoneNumber(resultSet.getLong("phone"));
            user.setEmail(resultSet.getString("email"));
            System.out.println(user);
        }
        return user;
    }

    public void addUserToDB(User user) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (surname, name, date_birth, phone, email) VALUES (?, ?, ?, ?, ?)");

            // Задаем параметры запроса
            /*preparedStatement.setString(1, "Смирнов");
            preparedStatement.setString(2, "Иван");
            preparedStatement.setDate(3, Date.valueOf("2000-10-11"));
            preparedStatement.setLong(4, 89215555555L);
            preparedStatement.setString(5, "smirnov@mail.ru");*/

            preparedStatement.setString(1, user.getSurname());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setDate(3, user.getBirthDate());
            preparedStatement.setLong(4, user.getPhoneNumber());
            preparedStatement.setString(5, user.getEmail());

            preparedStatement.executeUpdate();

            System.out.println("User успешно добавлен в базу данных.");

        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса.");
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Ошибка закрытия ресурсов.");
                e.printStackTrace();
            }
        }
    }

    public List<String> findTitlesByEmail(String s) {
        List<String> res = new ArrayList<>();
        //SELECT title FROM table_name WHERE user_email = 'smirnov@mail.ru';

        PreparedStatement preparedStatement = null;
        try {
            /*preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (surname, name, date_birth, phone, email) VALUES (?, ?, ?, ?, ?)");*/
            preparedStatement = connection.prepareStatement(
                    "SELECT book_title FROM public.bookrent WHERE user_email = ?");


            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            //User user = new User();
            while (resultSet.next()) {
                res.add(resultSet.getString("book_title"));
            }

            //preparedStatement.executeUpdate();

            System.out.println("Запрос успешно выполнен.");

        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса.");
            e.printStackTrace();
        } /*finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Ошибка закрытия ресурсов.");
                e.printStackTrace();
            }
        }*/

        return res;
    }
}
