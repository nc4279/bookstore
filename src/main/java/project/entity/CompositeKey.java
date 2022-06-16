package project.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CompositeKey implements Serializable {
    @Column(name = "bookstore_id")
    int bookstoreId;

    @Column(name = "book_id")
    int bookId;


    public CompositeKey() {
    }

    public CompositeKey(int bookstoreId, int bookId) {
        this.bookstoreId = bookstoreId;
        this.bookId = bookId;
    }

    public int getBookstoreId() {
        return this.bookstoreId;
    }

    public void setBookstoreId(int bookstoreId) {
        this.bookstoreId = bookstoreId;
    }

    public int getBookId() {
        return this.bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public CompositeKey bookstoreId(int bookstoreId) {
        setBookstoreId(bookstoreId);
        return this;
    }

    public CompositeKey bookId(int bookId) {
        setBookId(bookId);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CompositeKey)) {
            return false;
        }
        CompositeKey compositeKey = (CompositeKey) o;
        return bookstoreId == compositeKey.bookstoreId && bookId == compositeKey.bookId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookstoreId, bookId);
    }

    @Override
    public String toString() {
        return "{" +
            " bookstoreId='" + getBookstoreId() + "'" +
            ", bookId='" + getBookId() + "'" +
            "}";
    }

}