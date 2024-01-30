package si.um.si.vao;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import si.um.si.dto.NarocninaDTO;


@Entity
@Data
@NoArgsConstructor
public class Narocnina {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int gigaPodatki;
    private String naziv;
    private int trenutnaPoraba;
    private String cena;

    public Narocnina(int gigaPodatki, String naziv, int trenutnaPoraba, String cena) {
        this.gigaPodatki = gigaPodatki;
        this.naziv = naziv;
        this.trenutnaPoraba = trenutnaPoraba;
        this.cena = cena;
    }

    public Narocnina(NarocninaDTO narocninaDTO){
        setGigaPodatki(narocninaDTO.gigaPodatki());
        setNaziv(narocninaDTO.naziv());
        setTrenutnaPoraba(narocninaDTO.trenutnaPoraba());
        setCena(narocninaDTO.cena());
    }

    public void updateFrom(NarocninaDTO narocninaDTO){
        setGigaPodatki(narocninaDTO.gigaPodatki());
        setNaziv(narocninaDTO.naziv());
        setTrenutnaPoraba(narocninaDTO.trenutnaPoraba());
        setCena(narocninaDTO.cena());
    }

    public NarocninaDTO toDTO() {
        return new NarocninaDTO(id, gigaPodatki, naziv, trenutnaPoraba, cena);
    }
}
