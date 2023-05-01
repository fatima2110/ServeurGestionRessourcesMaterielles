package ma.fstf.ServeurGestionRessourcesMaterielles.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConstatDTO {
    private Integer id_constat;
    private Integer id_technicien;
    private String code_barre;
    private LocalDate date_apparition;
    private String explication_panne;
    private String frequence;
    private String ordre;
}
