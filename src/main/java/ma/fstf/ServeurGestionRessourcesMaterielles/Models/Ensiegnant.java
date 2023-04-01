package ma.fstf.ServeurGestionRessourcesMaterielles.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Ensiegnant extends User{
    @Column
    private String departement;
    @Column
    private boolean status;//chef de departement ou non
    @OneToMany(mappedBy = "ensiegnant")
    private List<Materiel> materiels;

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Materiel> getMateriels() {
        return materiels;
    }

    public void setMateriels(List<Materiel> materiels) {
        this.materiels = materiels;
    }
}
