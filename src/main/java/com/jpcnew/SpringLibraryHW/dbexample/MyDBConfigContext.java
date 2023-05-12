package com.jpcnew.SpringLibraryHW.dbexample;

import com.jpcnew.SpringLibraryHW.dbexample.dao.BookDAOBean;
import com.jpcnew.SpringLibraryHW.dbexample.dao.UserDao;
import com.jpcnew.SpringLibraryHW.dbexample.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

import static com.jpcnew.SpringLibraryHW.dbexample.constants.DBConstants.*;

@Configuration
//@ComponentScan
//@ComponentScan(value = {"com.jpcnew.SpringLibraryHW.dbexample.dao"})
public class MyDBConfigContext {

    private final Environment env;

    public MyDBConfigContext(Environment env) {
        this.env = env;
    }

    @Bean
    @Scope("singleton")
    public Connection newConnection() throws SQLException {
        /*return DriverManager.getConnection("jdbc:postgresql://" + DB_HOST + ":" + PORT + "/" +
                DB, USER, PASSWORD);*/
        return DriverManager.getConnection(
                Objects.requireNonNull(env.getProperty("spring.datasource.url")),
                env.getProperty("spring.datasource.username"),
                env.getProperty("spring.datasource.password")
        );
    }

    // hw
    @Bean
    @Scope("prototype")
    public UserDao userDao() throws SQLException {
        return new UserDao(newConnection());
    }

/*    @Bean
    public BookDAOBean bookDAOBean() throws SQLException {
        return new BookDAOBean(newConnection());
    }*/

}
