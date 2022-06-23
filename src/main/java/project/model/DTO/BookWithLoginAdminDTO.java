package project.model.DTO;

import java.util.Objects;

public class BookWithLoginAdminDTO {

    private int id;
    private String title;
    private String author;
    private int year;
    private double price;
    private int page;


    public BookWithLoginAdminDTO() {
    }

    public BookWithLoginAdminDTO(int id, String title, String author, int year, double price, int page) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
        this.page = page;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public BookWithLoginAdminDTO id(int id) {
        setId(id);
        return this;
    }

    public BookWithLoginAdminDTO title(String title) {
        setTitle(title);
        return this;
    }

    public BookWithLoginAdminDTO author(String author) {
        setAuthor(author);
        return this;
    }

    public BookWithLoginAdminDTO year(int year) {
        setYear(year);
        return this;
    }

    public BookWithLoginAdminDTO price(double price) {
        setPrice(price);
        return this;
    }

    public BookWithLoginAdminDTO page(int page) {
        setPage(page);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BookWithLoginAdminDTO)) {
            return false;
        }
        BookWithLoginAdminDTO bookWithLoginAdminDTO = (BookWithLoginAdminDTO) o;
        return id == bookWithLoginAdminDTO.id && Objects.equals(title, bookWithLoginAdminDTO.title) && Objects.equals(author, bookWithLoginAdminDTO.author) && year == bookWithLoginAdminDTO.year && price == bookWithLoginAdminDTO.price && page == bookWithLoginAdminDTO.page;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, year, price, page);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", author='" + getAuthor() + "'" +
            ", year='" + getYear() + "'" +
            ", price='" + getPrice() + "'" +
            ", page='" + getPage() + "'" +
            "}";
    }

}
