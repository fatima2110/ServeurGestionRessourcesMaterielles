package ma.fstf.ServeurGestionRessourcesMaterielles.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Fournisseur {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String nom_societe;
    @Column
    private String adresse;
    @Column
    private String email;
    @Column
    private String gerant;
    @Column
    private String pass;
    @OneToMany
    @JoinColumn(name = "fournisseur_id")
    private List<Proposition> propositions;
}
