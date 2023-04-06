package ma.fstf.ServeurGestionRessourcesMaterielles.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Ensiegnant extends User{
    @Column
    private String departement;
    @Column
    private boolean status;//chef de departement ou non
    @OneToMany(mappedBy = "ensiegnant")
    private List<Materiel> materiels;
}
