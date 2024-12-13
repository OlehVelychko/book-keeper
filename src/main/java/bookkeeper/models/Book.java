package bookkeeper.models;

import jakarta.validation.constraints.*;

public class Book {
    private int id;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 100, message = "Title should be between 2 and 100 characters")
    private String title;

    @NotEmpty(message = "Author should not be empty")
    @Size(min = 2, max = 50, message = "Author should be between 2 and 50 characters")
    private String author;

    @Min(value = 1000, message = "Year should be a valid year (>= 1000)")
    @Max(value = 9999, message = "Year should be a valid year (<= 9999)")
    private int year;

    private Integer personId;

    public Book() {
    }

    public Book(int id, String title, String author, int year, Integer personId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.personId = personId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }
}