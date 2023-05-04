package ma.fstf.ServeurGestionRessourcesMaterielles.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Fournisseur{
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String nomSociete;
    @Column
    private String pass;
    @Column
    private String adresse;
    @Column
    @Value("${listeNoir:false}")
    private  boolean listeNoir;
    @Column
    private String email;
    @Column
    private String gerant;
}