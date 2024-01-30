package si.um.si.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import si.um.si.vao.Narocnina;

@ApplicationScoped
public class NarocninaRepository implements PanacheRepository<Narocnina> {
}
