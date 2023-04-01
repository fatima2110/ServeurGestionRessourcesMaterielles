package ma.fstf.ServeurGestionRessourcesMaterielles.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

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
    private String marque;
    @Column
    private String ram;

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getDisque() {
        return disque;
    }

    public void setDisque(String disque) {
        this.disque = disque;
    }

    public String getEcran() {
        return ecran;
    }

    public void setEcran(String ecran) {
        this.ecran = ecran;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }
}
