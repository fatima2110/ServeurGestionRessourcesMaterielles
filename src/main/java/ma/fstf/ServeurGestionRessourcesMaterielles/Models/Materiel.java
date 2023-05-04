package ma.fstf.ServeurGestionRessourcesMaterielles.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
//sonIgnoreProperties("{appelOffre, constats, materiels_propositions}") //@JsonIgnore
@Inheritance(strategy = InheritanceType.JOINED)
public class Materiel {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String marque;
    @Column(unique = true)
    private String code_barre;
    @Column
    private Double prix;
    @Column
    private LocalDate date_livraison;
    @Column
    private Integer duree_garentie;
    @Column(columnDefinition = "boolean default false")
    private boolean verifie;
    @ManyToOne
    @JoinColumn(name = "appel_offre_id")
    private AppelOffre appelOffre;
    @ManyToOne
    @JoinColumn(name = "ensiegnant_id")
    private Ensiegnant ensiegnant;
}
