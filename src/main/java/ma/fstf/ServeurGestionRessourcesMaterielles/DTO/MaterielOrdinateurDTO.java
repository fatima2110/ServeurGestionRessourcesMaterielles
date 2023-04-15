package ma.fstf.ServeurGestionRessourcesMaterielles.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaterielOrdinateurDTO {
    private String enseignant;
    private String cpu;
    private String disque;

    private String ecran;

    private String ram;


}
