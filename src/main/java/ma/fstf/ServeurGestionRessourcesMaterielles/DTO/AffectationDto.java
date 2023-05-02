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
public class AffectationDto {
    private Integer id;
    private Integer id_enseignant;
    private Integer id_materiel;
    private String departement;
    private LocalDate date_affecatation;
}
