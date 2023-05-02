package ma.fstf.ServeurGestionRessourcesMaterielles.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImprimenteDto {
    private int id;
    private String resolution;
    private String vitesse;
    private String marque;
    private double prix;
    private int id_Prop;
}
