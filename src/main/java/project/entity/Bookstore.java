package project.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bookstore")
public class Bookstore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String owner;
    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "bookstore")
    Set<Copy> copies;

    public Bookstore() {
    }

    public Bookstore(String name, String owner, String address) {
        this.name = name;
        this.owner = owner;
        this.address = address;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Copy> getCopies() {
        return this.copies;
    }

    public void setCopies(Set<Copy> copies) {
        this.copies = copies;
    }

    public Bookstore id(int id) {
        setId(id);
        return this;
    }

    public Bookstore name(String name) {
        setName(name);
        return this;
    }

    public Bookstore owner(String owner) {
        setOwner(owner);
        return this;
    }

    public Bookstore address(String address) {
        setAddress(address);
        return this;
    }

    public Bookstore copies(Set<Copy> copies) {
        setCopies(copies);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Bookstore)) {
            return false;
        }
        Bookstore bookstore = (Bookstore) o;
        return id == bookstore.id && Objects.equals(name, bookstore.name) && Objects.equals(owner, bookstore.owner)
                && Objects.equals(address, bookstore.address) && Objects.equals(copies, bookstore.copies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner, address, copies);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", owner='" + getOwner() + "'" +
                ", address='" + getAddress() + "'" +
                ", copies='" + getCopies() + "'" +
                "}";
    }

}
