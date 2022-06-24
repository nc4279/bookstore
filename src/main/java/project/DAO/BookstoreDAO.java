package project.DAO;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import project.entity.Bookstore;

@ApplicationScoped
@Transactional
public class BookstoreDAO implements PanacheRepository<Bookstore>{
    
}
