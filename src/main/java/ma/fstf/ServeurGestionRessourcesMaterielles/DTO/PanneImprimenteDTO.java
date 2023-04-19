package ma.fstf.ServeurGestionRessourcesMaterielles.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PanneImprimenteDTO {
    private String enseignant;
    private String code_barre;
    private String marque;
    private String resolution;
    private String vitesse;
}
