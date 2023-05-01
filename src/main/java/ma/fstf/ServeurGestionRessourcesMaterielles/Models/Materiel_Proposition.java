package ma.fstf.ServeurGestionRessourcesMaterielles.Models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Materiel_Proposition {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String marque;
    @Column
    private Double prix;
    @ManyToOne
    @JoinColumn(name = "materiel_id")
    private Materiel materiel;
    @ManyToOne
    @JoinColumn(name = "proposition_id")
    private Proposition proposition;
}
