package ma.fstf.ServeurGestionRessourcesMaterielles.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Panne {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "technicien_id")
    private User technicien;
    @ManyToOne
    @JoinColumn(name = "materiel_id")
    private Materiel materiel;
}