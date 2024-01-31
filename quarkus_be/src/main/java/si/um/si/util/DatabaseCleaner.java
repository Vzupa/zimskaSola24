package si.um.si.util;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import si.um.si.dao.NarocninaRepository;

@ApplicationScoped
public class DatabaseCleaner {

    @Inject
    NarocninaRepository narocninaRepository;

    @Transactional
    public void clearDatabase() {
        narocninaRepository.deleteAll();
    }
}
