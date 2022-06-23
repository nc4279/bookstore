package project.model.DTO;

import java.util.Objects;

public class BookWithLoginDTO {
    private String title;
    private String author;
    private int year;
    private int page;
    private double price;


    public BookWithLoginDTO() {
    }

    public BookWithLoginDTO(String title, String author, int year, int page, double price) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.page = page;
        this.price = price;
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

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public BookWithLoginDTO title(String title) {
        setTitle(title);
        return this;
    }

    public BookWithLoginDTO author(String author) {
        setAuthor(author);
        return this;
    }

    public BookWithLoginDTO year(int year) {
        setYear(year);
        return this;
    }

    public BookWithLoginDTO page(int page) {
        setPage(page);
        return this;
    }

    public BookWithLoginDTO price(double price) {
        setPrice(price);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BookWithLoginDTO)) {
            return false;
        }
        BookWithLoginDTO bookWithLoginDTO = (BookWithLoginDTO) o;
        return Objects.equals(title, bookWithLoginDTO.title) && Objects.equals(author, bookWithLoginDTO.author) && year == bookWithLoginDTO.year && page == bookWithLoginDTO.page && price == bookWithLoginDTO.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year, page, price);
    }

    @Override
    public String toString() {
        return "{" +
            " title='" + getTitle() + "'" +
            ", author='" + getAuthor() + "'" +
            ", year='" + getYear() + "'" +
            ", page='" + getPage() + "'" +
            ", price='" + getPrice() + "'" +
            "}";
    }

}
