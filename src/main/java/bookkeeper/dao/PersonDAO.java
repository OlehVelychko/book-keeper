package bookkeeper.dao;

import bookkeeper.mappers.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import bookkeeper.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private static final String SELECT_ALL = "SELECT * FROM book_keeper_schema.person";
    private static final String SELECT_BY_ID = "SELECT * FROM book_keeper_schema.person WHERE id=?";
    private static final String INSERT_PERSON = "INSERT INTO book_keeper_schema.person (name, birth_year) VALUES (?, ?)";
    private static final String UPDATE_PERSON = "UPDATE book_keeper_schema.person SET name=?, birth_year=? WHERE id=?";
    private static final String DELETE_PERSON = "DELETE FROM book_keeper_schema.person WHERE id=?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query(SELECT_ALL, new PersonMapper());
    }

    public Optional<Person> show(int id) {
        return jdbcTemplate.query(SELECT_BY_ID, new PersonMapper(), new Object[]{id})
                .stream().findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update(INSERT_PERSON, person.getName(), person.getBirthYear());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update(UPDATE_PERSON, updatedPerson.getName(), updatedPerson.getBirthYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update(DELETE_PERSON, id);
    }
}