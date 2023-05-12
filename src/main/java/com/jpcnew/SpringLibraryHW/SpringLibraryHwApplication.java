package com.jpcnew.SpringLibraryHW;

import com.jpcnew.SpringLibraryHW.dbexample.dao.BookDAOBean;
import com.jpcnew.SpringLibraryHW.dbexample.MyDBConfigContext;
import com.jpcnew.SpringLibraryHW.dbexample.dao.UserDao;
import com.jpcnew.SpringLibraryHW.dbexample.db.DBConnection;
import com.jpcnew.SpringLibraryHW.dbexample.model.Book;
import com.jpcnew.SpringLibraryHW.dbexample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Date;
import java.util.List;
// создать таблицу users с полями id, name, email
//создать таблицу books с полями id, title, author
//создать таблицу bookrent с полями id, user_email, book_title
//в таблице bookrent необходимо связать полу email из таблицы users с полем title из таблицы books
//sql

@SpringBootApplication
public class SpringLibraryHwApplication implements CommandLineRunner {

	public BookDAOBean bookDAOBean;
	public UserDao userDao;

	// Инжект бина через конструктор (Финальный шаг)
	/*public SpringLibraryHwApplication(BookDAOBean bookDAOBean) {
		this.bookDAOBean = bookDAOBean;
	}*/

	//или... Инжект бина через setter (Финальный шаг)
	@Autowired
	public void setEnv(BookDAOBean bookDAOBean) {
		this.bookDAOBean = bookDAOBean;
	}

	@Autowired
	public void setEnvUser(UserDao userDao) {
		this.userDao = userDao;
	}


	//JDBC обертка от Spring
	/*@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;*/


	public static void main(String[] args) {
		SpringApplication.run(SpringLibraryHwApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Шаг 1 (Большая связность кода)
//        BookDaoJDBC bookDaoJDBC = new BookDaoJDBC();
//        bookDaoJDBC.findBookById(2);

		//Шаг 2 (Сделали BookDAOBean - добавили поле connection)
        //BookDAOBean bookDAOBean = new BookDAOBean(DBConnection.INSTANCE.getConnection());
        //bookDAOBean.findBookById(1);

		//Шаг 3 - работаем со Spring Context
        /*ApplicationContext ctx = new AnnotationConfigApplicationContext(MyDBConfigContext.class);
        BookDAOBean bookDAOBean = ctx.getBean(BookDAOBean.class);
		bookDAOBean.findBookById(1);*/

		//Шаг 4 - для MyDBConfigContext добавляем аннотацию @ComponentScan, удаляем связь на BookDAOBean
		//Добавляем аннотацию @Component для BookDAOBean (на 3 шаги был без аннотации)

		//Финальный шаг
        bookDAOBean.findBookById(3);
		userDao.findUserById(1);

		User user = new User(9,
				"Сидоров",
				"Владимир",
				Date.valueOf("1995-03-05"),
				89112222222L,
				"sidorov@mail.com");

		//userDao.addUserToDB(user);

		List<String> titles = userDao.findTitlesByEmail("gromar@mail.ru");
		for (String str : titles) {
			System.out.println(str);
		}

		System.out.println("----------------");
		List<Book> info = bookDAOBean.showInfoAboutBooks(titles);
		for (Book book : info) {
			System.out.println(book);
		}


		//JDBC обертка от Spring(зависимость spring-boot-starter-jdbc) -> Работа с темплейтом
        /*List<Book> bookList = jdbcTemplate.query("select * from books",
                ((rs, rowNum) -> new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDate("date_added")
                )));
        bookList.forEach(System.out::println);*/
	}
}
