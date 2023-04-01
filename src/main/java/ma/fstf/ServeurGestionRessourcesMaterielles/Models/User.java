package ma.fstf.ServeurGestionRessourcesMaterielles.Models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column
    private String login;
    @Column
    private String nom;
    @Column
    private String pass;
    @Column
    private String photo_profile;
    @Column
    private String prenom;
    @Column
    private String role;
    @Column
    private String telephone;
    @OneToMany
    @JoinColumn(name = "technicien_id")
    private List<Panne> pannes;
    @OneToMany
    @JoinColumn(name = "emetteur_id")
    private List<Message> messages_envoyes;
    @OneToMany
    @JoinColumn(name = "recepteur_id")
    private List<Message> messages_recus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Panne> getPannes() {
        return pannes;
    }

    public void setPannes(List<Panne> pannes) {
        this.pannes = pannes;
    }

    public String getLogin() {
        return login;
    }

    public String getNom() {
        return nom;
    }

    public String getPass() {
        return pass;
    }

    public String getPhoto_profile() {
        return photo_profile;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getRole() {
        return role;
    }

    public String getTelephone() {
        return telephone;
    }

    public List<Message> getMessages_envoyes() {
        return messages_envoyes;
    }

    public List<Message> getMessages_recus() {
        return messages_recus;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setPhoto_profile(String photo_profile) {
        this.photo_profile = photo_profile;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setMessages_envoyes(List<Message> messages_envoyes) {
        this.messages_envoyes = messages_envoyes;
    }

    public void setMessages_recus(List<Message> messages_recus) {
        this.messages_recus = messages_recus;
    }
}
