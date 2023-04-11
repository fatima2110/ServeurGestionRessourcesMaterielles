package ma.fstf.ServeurGestionRessourcesMaterielles.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BesoinChefImprimenteDto {
    private int id;
    private String nom;
    private String prenom;
    private String resolution;
    private String vitesse;
}
