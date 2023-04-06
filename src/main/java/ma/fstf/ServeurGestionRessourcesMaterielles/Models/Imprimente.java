package ma.fstf.ServeurGestionRessourcesMaterielles.Models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Imprimente extends Materiel{
    @Column
    private String marque;
    @Column
    private String resolution;
    @Column
    private double vitesse;
}
