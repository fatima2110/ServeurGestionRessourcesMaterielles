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
public class MaterielDto {
    private String enseignant;
    private String marque;
    private String code_barre;
    private LocalDate date_livraison;
    private Integer duree_garentie;
}
