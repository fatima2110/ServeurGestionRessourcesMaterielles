package ma.fstf.ServeurGestionRessourcesMaterielles.ModelRepre;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Materiel;
import ma.fstf.ServeurGestionRessourcesMaterielles.Models.Proposition;

import javax.persistence.*;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class Matereil_Propo_Repres {

    private Integer id;
    private String marque;
    private double prix;
    private Materiel materiel;
    private Integer id_Four;

}
