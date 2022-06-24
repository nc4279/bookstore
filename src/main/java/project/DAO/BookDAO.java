package project.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import project.entity.Book;
import project.entity.Book_;
import project.entity.Copy;
import project.entity.Copy_;
import project.model.DTO.BookForUserDTO;
import project.model.DTO.BookForWriterDTO;

@ApplicationScoped
@Transactional
public class BookDAO implements PanacheRepository<Book> {

    public List<Book> getBooksFromBookstore(int bookstoreID) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> book = cq.from(Book.class);
        Join<Book, Copy> copy = book.join(Book_.COPIES);

        cq.where(cb.equal(copy.get(Copy_.BOOKSTORE), bookstoreID));
        TypedQuery<Book> q = getEntityManager().createQuery(cq);
        return q.getResultList();

    }

    public List<BookForWriterDTO> getBooksForAuthor(int bookstoreID, String author) {

        ModelMapper modelMapper = new ModelMapper();

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
        Root<Book> book = cq.from(Book.class);
        Join<Book, Copy> copy = book.join(Book_.COPIES, JoinType.INNER);
        cq.multiselect(copy.get(Copy_.BOOK), copy.get(Copy_.COPIES), copy.get(Copy_.SOLDCOPIES));

        cq.where(cb.and(cb.equal(book.get(Book_.AUTHOR), author), cb.equal(copy.get(Copy_.BOOKSTORE), bookstoreID)));
        TypedQuery<Tuple> q = getEntityManager().createQuery(cq);

        List<BookForWriterDTO> newBooks = new ArrayList<>();
        for (Tuple iterable_element : q.getResultList()) {
            BookForUserDTO bookDTO = modelMapper.map(iterable_element.get(0), BookForUserDTO.class);
            BookForWriterDTO mybook = modelMapper.map(bookDTO, BookForWriterDTO.class);
            mybook.setCopies(Integer.parseInt(iterable_element.get(1).toString()));
            mybook.setSoldcopies(Integer.parseInt(iterable_element.get(2).toString()));
            newBooks.add(mybook);
            // Log.info(" BOOK: " + iterable_element.get(0));
            // Log.info(" COPIES: " + iterable_element.get(1));
            // Log.info(" SOLD COPIES: " + iterable_element.get(2));
        }

        // Log.info("BOOKS: "+ newBooks.size());

        return newBooks;
    }

}
