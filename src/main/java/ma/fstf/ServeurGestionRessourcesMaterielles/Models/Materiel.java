package ma.fstf.ServeurGestionRessourcesMaterielles.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Materiel {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(unique = true)
    private String code_barre;
    @Column
    private double prix;
    @Column
    private LocalDate date_livraison;
    @Column
    private int duree_garentie;
    @ManyToOne
    @JoinColumn(name = "appel_offre_id")
    private AppelOffre appelOffre;
    @OneToMany
    @JoinColumn(name = "materiel_id")
    private List<Panne> pannes;
    @ManyToOne
    @JoinColumn(name = "ensiegnant_id")
    private Ensiegnant ensiegnant;
    @OneToMany(mappedBy = "materiel")
    private List<Materiel_Proposition> materiels_propositions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode_barre() {
        return code_barre;
    }

    public void setCode_barre(String code_barre) {
        this.code_barre = code_barre;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public LocalDate getDate_livraison() {
        return date_livraison;
    }

    public void setDate_livraison(LocalDate date_livraison) {
        this.date_livraison = date_livraison;
    }

    public int getDuree_garentie() {
        return duree_garentie;
    }

    public void setDuree_garentie(int duree_garentie) {
        this.duree_garentie = duree_garentie;
    }

    public AppelOffre getAppelOffre() {
        return appelOffre;
    }

    public void setAppelOffre(AppelOffre appelOffre) {
        this.appelOffre = appelOffre;
    }

    public List<Panne> getPannes() {
        return pannes;
    }

    public void setPannes(List<Panne> pannes) {
        this.pannes = pannes;
    }

    public Ensiegnant getEnsiegnant() {
        return ensiegnant;
    }

    public void setEnsiegnant(Ensiegnant ensiegnant) {
        this.ensiegnant = ensiegnant;
    }

    public List<Materiel_Proposition> getMateriels_propositions() {
        return materiels_propositions;
    }

    public void setMateriels_propositions(List<Materiel_Proposition> materiels_propositions) {
        this.materiels_propositions = materiels_propositions;
    }
}
