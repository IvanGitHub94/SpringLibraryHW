package com.jpcnew.SpringLibraryHW.dbexample.dao;

import com.jpcnew.SpringLibraryHW.dbexample.db.DBConnection;
import com.jpcnew.SpringLibraryHW.dbexample.model.Book;

import java.sql.*;

public class BookDaoJDBC {
    public Book findBookById(Integer bookId) {
        try (Connection connection = DBConnection.INSTANCE.getConnection())
        {
            if(connection != null) {
                System.out.println("Ура! Мы подключились к БД!");
            }
            else {
                System.out.println("БД недоступна.");
            }

            String select = "select * from public.books where id = ?";

            assert connection != null;
            PreparedStatement selectQuery = connection.prepareStatement(select);

            selectQuery.setInt(1, bookId);

            ResultSet resultSet = selectQuery.executeQuery();

            //for HW!
            //List<Book> books = new ArrayList<>(); // если бы книг было много, но сейчас один запрос по id

            Book book = new Book();
            while (resultSet.next()) {
                // возможно нужно будет сделать за циклом (1:33:00)
                book.setId(resultSet.getInt("id"));
                book.setAuthor(resultSet.getString("author"));
                book.setTitle(resultSet.getString("title"));

                //books.add(book);
                System.out.println(book);
            }
            //return book;

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return null;
    }

    /*public Book findBookByTitle() {
        return null;
    }*/
}
