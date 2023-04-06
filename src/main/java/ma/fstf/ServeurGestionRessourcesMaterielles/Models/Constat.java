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
public class Constat {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private LocalDate date_apparition;
    @Column
    private String explication_panne;
    @Column
    private String frequence;
    @Column
    private String ordre;
    @ManyToOne
    @JoinColumn(name = "technicien_id")
    private User technicien;
    @ManyToOne
    @JoinColumn(name = "materiel_id")
    private Materiel materiel;
}
