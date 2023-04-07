package ma.fstf.ServeurGestionRessourcesMaterielles.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private double prix;
    @Column
    private LocalDate date_livraison;
    @Column
    private int duree_garentie;
    @ManyToOne
    @JoinColumn(name = "appel_offre_id")
    private AppelOffre appelOffre;
    @ManyToOne
    @JoinColumn(name = "ensiegnant_id")
    @JsonIgnore
    private Ensiegnant ensiegnant;
}
