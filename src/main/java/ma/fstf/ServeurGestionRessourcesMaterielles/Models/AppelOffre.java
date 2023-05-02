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
public class AppelOffre {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private LocalDate date_debut;
    @Column
    private LocalDate date_fin;

}
