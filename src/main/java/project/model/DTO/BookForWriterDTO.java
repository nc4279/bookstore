package project.model.DTO;

import java.util.Objects;

public class BookForWriterDTO {

    private String title;
    private String author;
    private int year;
    private double price;
    private int page;
    private int copies;
    private int soldcopies;


    public BookForWriterDTO() {
    }

    public BookForWriterDTO(String title, String author, int year, double price, int page, int copies, int soldcopies) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
        this.page = page;
        this.copies = copies;
        this.soldcopies = soldcopies;
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

    public int getCopies() {
        return this.copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public int getSoldcopies() {
        return this.soldcopies;
    }

    public void setSoldcopies(int soldcopies) {
        this.soldcopies = soldcopies;
    }

    public BookForWriterDTO title(String title) {
        setTitle(title);
        return this;
    }

    public BookForWriterDTO author(String author) {
        setAuthor(author);
        return this;
    }

    public BookForWriterDTO year(int year) {
        setYear(year);
        return this;
    }

    public BookForWriterDTO price(double price) {
        setPrice(price);
        return this;
    }

    public BookForWriterDTO page(int page) {
        setPage(page);
        return this;
    }

    public BookForWriterDTO copies(int copies) {
        setCopies(copies);
        return this;
    }

    public BookForWriterDTO soldcopies(int soldcopies) {
        setSoldcopies(soldcopies);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BookForWriterDTO)) {
            return false;
        }
        BookForWriterDTO bookForWriterDTO = (BookForWriterDTO) o;
        return Objects.equals(title, bookForWriterDTO.title) && Objects.equals(author, bookForWriterDTO.author) && year == bookForWriterDTO.year && price == bookForWriterDTO.price && page == bookForWriterDTO.page && copies == bookForWriterDTO.copies && soldcopies == bookForWriterDTO.soldcopies;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year, price, page, copies, soldcopies);
    }

    @Override
    public String toString() {
        return "{" +
            " title='" + getTitle() + "'" +
            ", author='" + getAuthor() + "'" +
            ", year='" + getYear() + "'" +
            ", price='" + getPrice() + "'" +
            ", page='" + getPage() + "'" +
            ", copies='" + getCopies() + "'" +
            ", soldcopies='" + getSoldcopies() + "'" +
            "}";
    }

}
