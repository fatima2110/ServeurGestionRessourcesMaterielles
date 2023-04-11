package ma.fstf.ServeurGestionRessourcesMaterielles.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.MaterielState;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaterielDto {
    private int id;
    private String enseignant;
    private String marque;
    private String code_barre;
    private LocalDate date_affectation;
    private int duree_garentie;
    private boolean enPanne;
}
