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
public class Message {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String message;
    @ManyToOne
    @JoinColumn(name = "emetteur_id")
    private User emetteur;
    @ManyToOne
    @JoinColumn(name = "recepteur_id")
    private User recepteur;
    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;
}
