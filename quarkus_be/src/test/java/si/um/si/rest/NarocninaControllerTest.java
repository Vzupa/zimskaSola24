package si.um.si.rest;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import si.um.si.dto.NarocninaDTO;
import si.um.si.rest.NarocninaController;
import si.um.si.util.DatabaseCleaner;

import java.util.List;


@QuarkusTest
class NarocninaControllerTest {

    @Inject
    NarocninaController narocninaController;

    @Inject
    DatabaseCleaner databaseCleaner;

    @BeforeEach
    public void setup() {
        databaseCleaner.clearDatabase();
    }

    @Test
    @TestTransaction
    void testVseOsebeEndpoint() {
        NarocninaDTO newOseba = new NarocninaDTO(null, 1, "Testni", 0, "zastojn");
        Response postResponse = narocninaController.postNarocnina(newOseba);
        Assertions.assertEquals(Response.Status.CREATED.getStatusCode(), postResponse.getStatus());
        NarocninaDTO createdOseba = (NarocninaDTO) postResponse.getEntity();

        Response getResponse = narocninaController.getNarocninaById(createdOseba.id().intValue());
        Assertions.assertEquals(Response.Status.OK.getStatusCode(), getResponse.getStatus());
        NarocninaDTO fetchedOseba = (NarocninaDTO) getResponse.getEntity();
        Assertions.assertEquals(newOseba.naziv(), fetchedOseba.naziv());

        // Update the Oseba
        NarocninaDTO updatedOseba = new NarocninaDTO(createdOseba.id(), 10, "Updatedni", 5, "zastojn");
        Response putResponse = narocninaController.putNarocnina(createdOseba.id().intValue(), updatedOseba);
        Assertions.assertEquals(Response.Status.OK.getStatusCode(), putResponse.getStatus());

        // Validate the update
        Response updatedGetResponse = narocninaController.getNarocninaById(createdOseba.id().intValue());
        NarocninaDTO fetchedUpdatedOseba = (NarocninaDTO) updatedGetResponse.getEntity();
        Assertions.assertEquals("Updatedni", fetchedUpdatedOseba.naziv());

        // Delete the Oseba
        Response deleteResponse = narocninaController.deleteNarocnina(createdOseba.id().intValue());
        Assertions.assertEquals(Response.Status.OK.getStatusCode(), deleteResponse.getStatus());

        // Validate the deletion
        Response deletedGetResponse = narocninaController.getNarocninaById(createdOseba.id().intValue());
        Assertions.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), deletedGetResponse.getStatus());
    }

    @Test
    @TestTransaction
    void testVseOsebe() {
        // Create a couple of Oseba entities
        narocninaController.postNarocnina(new NarocninaDTO(null, 10, "Testni1", 5, "dva"));
        narocninaController.postNarocnina(new NarocninaDTO(null, 20, "Testni2", 10, "ena"));

        // Retrieve all Oseba entities
        List<NarocninaDTO> narocnine = narocninaController.vseNarocnine();

        // Assertions
        Assertions.assertNotNull(narocnine);
        Assertions.assertTrue(narocnine.size() >= 2);
    }

}
