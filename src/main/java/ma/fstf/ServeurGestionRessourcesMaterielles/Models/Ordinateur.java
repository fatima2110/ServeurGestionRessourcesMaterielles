package ma.fstf.ServeurGestionRessourcesMaterielles.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Ordinateur extends Materiel{
    @Column
    private String cpu;
    @Column
    private String disque;
    @Column
    private String ecran;
    @Column
    private String ram;
}
