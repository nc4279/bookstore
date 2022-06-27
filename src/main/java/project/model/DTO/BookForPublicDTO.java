package project.model.DTO;

import java.util.Objects;

public class BookForPublicDTO {

    private String title;
    private String author;
    private int year;
    private int page;

    public BookForPublicDTO() {
    }

    public BookForPublicDTO(String title, String author, int year, int page) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.page = page;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public BookForPublicDTO title(String title) {
        setTitle(title);
        return this;
    }

    public BookForPublicDTO author(String author) {
        setAuthor(author);
        return this;
    }

    public BookForPublicDTO year(int year) {
        setYear(year);
        return this;
    }

    public BookForPublicDTO page(int page) {
        setPage(page);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BookForPublicDTO)) {
            return false;
        }
        BookForPublicDTO bookWithoutLoginDTO = (BookForPublicDTO) o;
        return Objects.equals(title, bookWithoutLoginDTO.title) && Objects.equals(author, bookWithoutLoginDTO.author)
                && year == bookWithoutLoginDTO.year && page == bookWithoutLoginDTO.page;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year, page);
    }

    @Override
    public String toString() {
        return "{" +
                " title='" + getTitle() + "'" +
                ", author='" + getAuthor() + "'" +
                ", year='" + getYear() + "'" +
                ", page='" + getPage() + "'" +
                "}";
    }

}
