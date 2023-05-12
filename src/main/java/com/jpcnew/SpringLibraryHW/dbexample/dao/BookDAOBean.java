package com.jpcnew.SpringLibraryHW.dbexample.dao;


import com.jpcnew.SpringLibraryHW.dbexample.model.Book;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookDAOBean {
    private final Connection connection;

    private final String SELECT_BOOK_BY_ID_QUERY = "select * from public.books where id = ?";

    public BookDAOBean(Connection connection) {
        this.connection = connection;
    }

    public Book findBookById(Integer bookId) throws SQLException {
        PreparedStatement selectQuery = connection.prepareStatement(SELECT_BOOK_BY_ID_QUERY);
        selectQuery.setInt(1, bookId);
        ResultSet resultSet = selectQuery.executeQuery();
        Book book = new Book();
        while (resultSet.next()) {
            book.setId(resultSet.getInt("id"));
            book.setAuthor(resultSet.getString("author"));
            book.setTitle(resultSet.getString("title"));
            System.out.println(book);
        }
        return book;
    }

    public List<Book> showInfoAboutBooks(List<String> titles) throws SQLException {
        List<Book> books = new ArrayList<>();

        PreparedStatement selectQuery = connection.prepareStatement(
                "select * from public.books where title = ?"
        );

        for (String title : titles) {
            selectQuery.setString(1, title);
            ResultSet resultSet = selectQuery.executeQuery();
            Book book = new Book();
            while (resultSet.next()) {
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
            }
            books.add(book);
        }

        return books;
    }
}

