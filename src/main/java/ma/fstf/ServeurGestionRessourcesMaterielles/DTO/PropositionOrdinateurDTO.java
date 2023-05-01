package ma.fstf.ServeurGestionRessourcesMaterielles.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropositionOrdinateurDTO {

    private String marque;
    private String cpu;
    private String disque;
    private double prix;
    private String ecran;
    private Integer qte;
    private String ram;
}
