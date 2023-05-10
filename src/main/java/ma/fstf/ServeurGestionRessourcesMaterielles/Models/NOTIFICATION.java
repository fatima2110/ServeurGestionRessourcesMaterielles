package ma.fstf.ServeurGestionRessourcesMaterielles.Models;

public enum NOTIFICATION {
    AjoutBesoin("Vous pouvez maintenat ajouter vous besoin"),
    Accept("Votre proposition est acceptee"),
    Refus("Votre proposition est refusee"),
    Elimination,
    NewBesoin("Nouvelles besoins sont ajoutes pour generer appel d'offre");

    private String value;
    private NOTIFICATION(){}

    private NOTIFICATION(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
