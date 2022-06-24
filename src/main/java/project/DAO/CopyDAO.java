package project.DAO;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import project.entity.Copy;

@ApplicationScoped
@Transactional
public class CopyDAO implements PanacheRepository<Copy> {
    
}
