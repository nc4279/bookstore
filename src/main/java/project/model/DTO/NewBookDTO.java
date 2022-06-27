package project.model.DTO;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;

import io.smallrye.common.constraint.NotNull;

public class NewBookDTO {

    @NotEmpty
    private String title;
    @NotEmpty
    private String author;
    @NotEmpty
    private int year;
    @NotEmpty
    private int page;
    @NotNull
    private double price;
    @NotNull
    private int copies;

    public NewBookDTO() {
    }

    public NewBookDTO(String title, String author, int year, int page, double price, int copies) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.page = page;
        this.price = price;
        this.copies = copies;
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

    public int getCopies() {
        return this.copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public NewBookDTO title(String title) {
        setTitle(title);
        return this;
    }

    public NewBookDTO author(String author) {
        setAuthor(author);
        return this;
    }

    public NewBookDTO year(int year) {
        setYear(year);
        return this;
    }

    public NewBookDTO page(int page) {
        setPage(page);
        return this;
    }

    public NewBookDTO price(double price) {
        setPrice(price);
        return this;
    }

    public NewBookDTO copies(int copies) {
        setCopies(copies);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof NewBookDTO)) {
            return false;
        }
        NewBookDTO newBookDTO = (NewBookDTO) o;
        return Objects.equals(title, newBookDTO.title) && Objects.equals(author, newBookDTO.author)
                && year == newBookDTO.year && page == newBookDTO.page && price == newBookDTO.price
                && copies == newBookDTO.copies;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year, page, price, copies);
    }

    @Override
    public String toString() {
        return "{" +
                " title='" + getTitle() + "'" +
                ", author='" + getAuthor() + "'" +
                ", year='" + getYear() + "'" +
                ", page='" + getPage() + "'" +
                ", price='" + getPrice() + "'" +
                ", copies='" + getCopies() + "'" +
                "}";
    }

}
