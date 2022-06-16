package project.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "copy")
public class Copy {

    @EmbeddedId
    CompositeKey id;

    @ManyToOne
    @MapsId("bookstoreId")
    @JoinColumn(name = "bookstore_id")
    Bookstore bookstore;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    Book book;

    @Column(nullable = false)
    private int copies;
    @Column(nullable = false)
    private int soldcopies;

    public Copy() {
    }

    public Copy(CompositeKey id, Bookstore bookstore, Book book, int copies, int soldcopies) {
        this.id = id;
        this.bookstore = bookstore;
        this.book = book;
        this.copies = copies;
        this.soldcopies = soldcopies;
    }

    public CompositeKey getId() {
        return this.id;
    }

    public void setId(CompositeKey id) {
        this.id = id;
    }

    public Bookstore getBookstore() {
        return this.bookstore;
    }

    public void setBookstore(Bookstore bookstore) {
        this.bookstore = bookstore;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        this.book = book;
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

    public Copy id(CompositeKey id) {
        setId(id);
        return this;
    }

    public Copy bookstore(Bookstore bookstore) {
        setBookstore(bookstore);
        return this;
    }

    public Copy book(Book book) {
        setBook(book);
        return this;
    }

    public Copy copies(int copies) {
        setCopies(copies);
        return this;
    }

    public Copy soldcopies(int soldcopies) {
        setSoldcopies(soldcopies);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Copy)) {
            return false;
        }
        Copy copy = (Copy) o;
        return Objects.equals(id, copy.id) && Objects.equals(bookstore, copy.bookstore)
                && Objects.equals(book, copy.book) && copies == copy.copies && soldcopies == copy.soldcopies;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookstore, book, copies, soldcopies);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", bookstore='" + getBookstore() + "'" +
                ", book='" + getBook() + "'" +
                ", copies='" + getCopies() + "'" +
                ", soldcopies='" + getSoldcopies() + "'" +
                "}";
    }

}
