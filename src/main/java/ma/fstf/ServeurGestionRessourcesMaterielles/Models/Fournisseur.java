package ma.fstf.ServeurGestionRessourcesMaterielles.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Fournisseur {
    @Id
    private String nom_societe;
    @Column
    private String adresse;
    @Column
    private String email;
    @Column
    private String gerant;
    @Column
    private String pass;
    @OneToMany
    @JoinColumn(name = "fournisseur_id")
    private List<Proposition> propositions;
    @OneToMany
    @JoinColumn(name = "fournisseur_id")
    private List<Message> messages;

    public List<Proposition> getPropositions() {
        return propositions;
    }

    public void setPropositions(List<Proposition> propositions) {
        this.propositions = propositions;
    }

    public String getNom_societe() {
        return nom_societe;
    }

    public void setNom_societe(String nom_societe) {
        this.nom_societe = nom_societe;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGerant() {
        return gerant;
    }

    public void setGerant(String gerant) {
        this.gerant = gerant;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
