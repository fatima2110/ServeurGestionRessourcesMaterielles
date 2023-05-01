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
public class MaterielImprimenteDTO {
    private int id;
    private String enseignant;
    private  String departement;

    private String marque;
    private String code_barre;
    private  Double prix;
    private Integer Appleoffreid;
    private LocalDate datedebut;
    private  LocalDate dateFin;
    private String resolution;
    private String vitesse;
    private  String Fournissuer;
    private LocalDate  datelivraison;
    private Integer dureegarantie;


}
