package si.um.si.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public record NarocninaDTO(Long id, int gigaPodatki, String naziv, int trenutnaPoraba, String cena) {

}
