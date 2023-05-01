package ma.fstf.ServeurGestionRessourcesMaterielles.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> ba5a08b4626b2bd0f571799bf0501ea11f5e5891

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Proposition {
    @Id
    @GeneratedValue
    private Integer id;
<<<<<<< HEAD
=======

>>>>>>> ba5a08b4626b2bd0f571799bf0501ea11f5e5891
    @Column
    @Enumerated(EnumType.STRING)
    private StatusPropo status;
    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;
<<<<<<< HEAD
=======




>>>>>>> ba5a08b4626b2bd0f571799bf0501ea11f5e5891
}
