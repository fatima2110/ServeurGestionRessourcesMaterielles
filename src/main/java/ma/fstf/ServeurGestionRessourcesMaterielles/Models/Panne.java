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
public class Panne {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(columnDefinition = "boolean default false")
    private boolean treated;
    @ManyToOne
    @JoinColumn(name = "technicien_id")
    private User technicien;
    @Column
    private LocalDate datePanne;
    @ManyToOne
    @JoinColumn(name = "materiel_id")
    private Materiel materiel;
}