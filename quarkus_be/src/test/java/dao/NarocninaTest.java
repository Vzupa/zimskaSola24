package dao;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import si.um.si.dao.NarocninaRepository;
import si.um.si.util.DatabaseCleaner;
import si.um.si.vao.Narocnina;

import static io.smallrye.common.constraint.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class NarocninaTest {

    @Inject
    NarocninaRepository narocninaRepository;

    @Inject
    DatabaseCleaner databaseCleaner;
    
    private Narocnina narocnina;

    @BeforeEach
    @Transactional
    void beforeEach() {
        databaseCleaner.clearDatabase();

        narocnina = new Narocnina();
        narocnina.setNaziv("SUPER DUPER");
        narocnina.setCena("100c");
        narocnina.setTrenutnaPoraba(15);
        narocnina.setGigaPodatki(16);
        narocninaRepository.persist(narocnina);
    }

    @Test
    @Transactional
    void addAnotherNarocninaTest() {
        assertEquals(1, narocninaRepository.count());
        assertEquals("SUPER DUPER", narocnina.getNaziv());
        assertEquals("100c", narocnina.getCena());
        assertEquals(15, narocnina.getTrenutnaPoraba());
        assertEquals(16, narocnina.getGigaPodatki());

        Narocnina o = new Narocnina();
        o.setNaziv("Yule");
        narocninaRepository.persist(o);

        assertEquals(2, narocninaRepository.count());

        Narocnina foundNarocnina = narocninaRepository.findById(o.getId());
        assertNotNull(foundNarocnina);
        assertEquals("Yule", foundNarocnina.getNaziv());
        assertEquals(null, foundNarocnina.getCena());
    }

    @Test
    @Transactional
    void deleteNarocninaTest() {
        assertEquals(1, narocninaRepository.count());
        narocninaRepository.deleteById(narocnina.getId());
        assertEquals(0, narocninaRepository.count());
    }

}
