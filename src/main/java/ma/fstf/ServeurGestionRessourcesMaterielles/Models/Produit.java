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
public class Produit {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String nom;
    @Column
    private String description;
    @Column
    private double prix;
}
