package project.model.DAO;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import project.entity.Book;
import project.entity.Book_;
import project.entity.Copy;
import project.entity.Copy_;

@ApplicationScoped
@Transactional
public class BookDAO implements PanacheRepository<Book> {

    public List<Book> getBooksFromBookstore(int bookstoreID) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> root = cq.from(Book.class);
        Join<Book, Copy> book = root.join(Book_.COPIES);
        
        cq.where(cb.equal(book.get(Copy_.BOOKSTORE), bookstoreID));
        TypedQuery<Book> q = getEntityManager().createQuery(cq);
        return q.getResultList();

    }

    public List<Object> getBooksForAuthor(int bookstoreID, String author) {
 
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Object> cq = cb.createQuery(Object.class);
        Root<Book> root = cq.from(Book.class);
        Join<Book, Copy> book = root.join(Book_.COPIES, JoinType.INNER);
        cq.multiselect(root,book);
        
        cq.where(cb.and(cb.equal(root.get(Book_.AUTHOR), author), cb.equal(book.get(Copy_.BOOKSTORE), bookstoreID)));
        TypedQuery<Object> q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }

}
