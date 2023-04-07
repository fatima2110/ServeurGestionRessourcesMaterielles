package ma.fstf.ServeurGestionRessourcesMaterielles.Representations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepresentationMateriel {
    private int id;
    private String materiel;
    private String code_barre;
    private Date date_affectation;

}
