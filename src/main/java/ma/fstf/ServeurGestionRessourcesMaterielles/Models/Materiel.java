package ma.fstf.ServeurGestionRessourcesMaterielles.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public class Materiel {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String code_barre;
    @Column
    private double prix;
    @Column
    private LocalDate date_livraison;
    @Column
    private int duree_garentie;
    @ManyToOne
    @JoinColumn(name = "appel_offre_id")
    private AppelOffre appelOffre;
    /*@OneToMany
    @JoinColumn(name = "materiel_id")
    private List<Constat> constats;*/
    @ManyToOne
    @JoinColumn(name = "ensiegnant_id")
    private Ensiegnant ensiegnant;
   @OneToMany(mappedBy = "materiel")
    private List<Materiel_Proposition> materiels_propositions;
}
