package ma.fstf.ServeurGestionRessourcesMaterielles.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
@Entity
@Table
public class Panne {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column
    private String constat;
    @Column
    private LocalDate date_panne;
    @Column
    private String frequence;
    @ManyToOne
    @JoinColumn(name = "technicien_id")
    private User technicien;
    @ManyToOne
    @JoinColumn(name = "materiel_id")
    private Materiel materiel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConstat() {
        return constat;
    }

    public void setConstat(String constat) {
        this.constat = constat;
    }

    public LocalDate getDate_panne() {
        return date_panne;
    }

    public void setDate_panne(LocalDate date_panne) {
        this.date_panne = date_panne;
    }

    public String getFrequence() {
        return frequence;
    }

    public void setFrequence(String frequence) {
        this.frequence = frequence;
    }

    public User getTechnicien() {
        return technicien;
    }

    public void setTechnicien(User technicien) {
        this.technicien = technicien;
    }

    public Materiel getMateriel() {
        return materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
    }
}
