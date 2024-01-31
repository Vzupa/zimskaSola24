package si.um.si;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import si.um.si.dao.NarocninaRepository;
import si.um.si.vao.Narocnina;

import java.util.logging.Logger;

@ApplicationScoped
public class GreetingResource {

    private static final Logger log = Logger.getLogger(GreetingResource.class.getName());

    @Inject
    NarocninaRepository narocninaRepository;

    void onStart(@Observes StartupEvent ev) {
        log.info("The application is starting...");
        populateTestDataIfNotPresent();
    }

    @Transactional
    void populateTestDataIfNotPresent() {
        if (narocninaRepository.count() > 0) {
            log.info("populateTestData - skipped.");
            return;
        }
        log.info("populateTestData initiated.");

        Narocnina o1 = new Narocnina();
        o1.setNaziv("The Favorite");
        o1.setGigaPodatki(30);
        o1.setTrenutnaPoraba(0);
        o1.setCena("40.00€");
        narocninaRepository.persist(o1);

        Narocnina o3 = new Narocnina();
        o3.setNaziv("The mid Beast");
        o3.setGigaPodatki(10);
        o3.setTrenutnaPoraba(0);
        o3.setCena("20.00€");
        narocninaRepository.persist(o3);

        Narocnina o2 = new Narocnina();
        o2.setNaziv("The Cheapest");
        o2.setGigaPodatki(5);
        o2.setTrenutnaPoraba(0);
        o2.setCena("5.00€");
        narocninaRepository.persist(o2);
    }
}
