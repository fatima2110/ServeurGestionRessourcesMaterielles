package ma.fstf.ServeurGestionRessourcesMaterielles.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdinateurDto {
    private int id;
    private String cpu ;
    private String ram;
    private String disque;
    private String ecran ;
    private String marque;
    private double prix;
    private int id_Prop;
}
