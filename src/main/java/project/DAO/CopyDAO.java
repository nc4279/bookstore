package project.DAO;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import project.entity.CompositeKey;
import project.entity.Copy;

@ApplicationScoped
@Transactional
public class CopyDAO implements PanacheRepository<Copy> {
    
    public void delete(CompositeKey key)
    {
        Copy copy = getEntityManager().merge(find("id", key).firstResult());
        getEntityManager().remove(copy);
    }

    public void merge(Copy copy)
    { 
        getEntityManager().merge(copy);
    }
}
