package project.model.DAO;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import project.entity.Book;

@ApplicationScoped
@Transactional
public class BookDAO implements PanacheRepository<Book>{

}
