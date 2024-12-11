package dao;

import models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private static final String SELECT_ALL = "SELECT * FROM book_keeper_schema.book";
    private static final String SELECT_BY_ID = "SELECT * FROM book_keeper_schema.book WHERE id=?";
    private static final String INSERT_BOOK = "INSERT INTO book_keeper_schema.book (title, author, year, person_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_BOOK = "UPDATE book_keeper_schema.book SET title=?, author=?, year=?, person_id=? WHERE id=?";
    private static final String DELETE_BOOK = "DELETE FROM book_keeper_schema.book WHERE id=?";
    private static final String RELEASE_BOOK = "UPDATE book_keeper_schema.book SET person_id=NULL WHERE id=?";
    private static final String ASSIGN_BOOK = "UPDATE book_keeper_schema.book SET person_id=? WHERE id=?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query(SELECT_ALL, new BookMapper());
    }

    public Optional<Book> show(int id) {
        return jdbcTemplate.query(SELECT_BY_ID, new BookMapper(), new Object[]{id})
                .stream().findAny();
    }

    public void save(Book book) {
        jdbcTemplate.update(INSERT_BOOK, book.getTitle(), book.getAuthor(), book.getYear(), book.getPersonId());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update(UPDATE_BOOK, updatedBook.getTitle(), updatedBook.getAuthor(),
                updatedBook.getYear(), updatedBook.getPersonId(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update(DELETE_BOOK, id);
    }

    public void release(int id) {
        updatePersonId(id, null);
    }

    public void assign(int bookId, int personId) {
        updatePersonId(bookId, personId);
    }

    private void updatePersonId(int bookId, Integer personId) {
        jdbcTemplate.update(personId == null ? RELEASE_BOOK : ASSIGN_BOOK, personId, bookId);
    }
}