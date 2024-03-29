package si.um.si.rest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import si.um.si.dao.NarocninaRepository;
import si.um.si.dto.NarocninaDTO;
import si.um.si.vao.Narocnina;

import java.util.List;
import java.util.logging.Logger;

@Path("/narocnine")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NarocninaController {

    private static final Logger log = Logger.getLogger(NarocninaController.class.getName());
    private static String logNarocnina = "/narocnine/";
    private static String logNarocninaNi = "Narocnina ni najdena";
    private static String logNarocninaNi2 = " ;" + logNarocninaNi + "!";

    @Inject
    NarocninaRepository narocninaRepository;

    @GET
    public List<NarocninaDTO> vseNarocnine() {
        return narocninaRepository.listAll()
                .stream()
                .map(Narocnina::toDTO)
                .toList();
    }

    @GET
    @Path("/{id}")
    public Response getNarocninaById(@PathParam("id") int id) {
        Narocnina narocnina = narocninaRepository.findById((long) id);
        if (narocnina == null) {
            log.info(() -> logNarocnina + id + logNarocninaNi2);
            return Response.status(Response.Status.NOT_FOUND).entity(logNarocninaNi).build();
        }
        return Response.ok(narocnina.toDTO()).build();
    }

    @POST
    @Transactional
    public Response postNarocnina(NarocninaDTO dto) {
        Narocnina oseba = new Narocnina(dto);
        narocninaRepository.persist(oseba);
        return Response.ok(oseba.toDTO()).status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response putNarocnina(@PathParam("id") int id, NarocninaDTO dto) {
        Narocnina oseba = narocninaRepository.findById((long) id);
        if (oseba == null) {
            log.info(() -> logNarocnina + id + logNarocninaNi2);
            return Response.status(Response.Status.NOT_FOUND).entity(logNarocninaNi).build();
        }
        oseba.updateFrom(dto);
        narocninaRepository.persist(oseba);
        return Response.ok(oseba.toDTO()).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteNarocnina(@PathParam("id") int id) {
        Narocnina oseba = narocninaRepository.findById((long) id);
        if (oseba == null) {
            log.info(() -> logNarocnina + id + logNarocninaNi2);
            return Response.status(Response.Status.NOT_FOUND).entity(logNarocninaNi).build();
        }
        narocninaRepository.delete(oseba);
        return Response.ok("Narocnina izbrisana").build();
    }
}
