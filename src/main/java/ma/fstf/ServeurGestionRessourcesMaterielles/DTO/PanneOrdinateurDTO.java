package ma.fstf.ServeurGestionRessourcesMaterielles.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PanneOrdinateurDTO {
    private String enseignant;
    private String code_barre;
    private String marque;
    private String cpu;
    private String disque;
    private String ecran;
    private String ram;
}
