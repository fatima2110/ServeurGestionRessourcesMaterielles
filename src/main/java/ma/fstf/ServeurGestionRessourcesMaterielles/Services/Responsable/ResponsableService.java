package ma.fstf.ServeurGestionRessourcesMaterielles.Services.Responsable;

import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.MaterielImprimenteDTO;
import ma.fstf.ServeurGestionRessourcesMaterielles.DTO.MaterielOrdinateurDTO;

import ma.fstf.ServeurGestionRessourcesMaterielles.Models.*;
import ma.fstf.ServeurGestionRessourcesMaterielles.Repositories.Responsable.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResponsableService {
    @Autowired
    private ImprimanteRepository imRepo;
    @Autowired
    private EnseignantRepository ensiegRepo;

    @Autowired
    private  OrdinateurRepository ordRepo;

    @Autowired
    AppleOffreRepo appelRepo;

   @Autowired
    private MatereilRepository matRepo;
    @Autowired
    FournisseurRepository fournRepo;
    @Autowired
    MaterielPropositionRepository matpropRepo;
    @Autowired
    PropositionRepository propoRepo;

    /////////*****************Ensiegnant de chaque Materiel***********

    public Ensiegnant getEnsignant(Integer id) {
        return ensiegRepo.getEnsiegnant(id);
    }

    //****************Recuprer Apple d'offre
    public AppelOffre getAppelOffre() {
        return appelRepo.getAppleOffre();
    }

    //***************generer Appel offre********************//
    public void genereAppel(AppelOffre app) {
        System.out.println("Apple offre genre ");
        appelRepo.save(app);
    }

    //************************Recuperer Founisseur Acepte***********************
    public Fournisseur getFounisseur(Integer id) {
        return fournRepo.getFournisseur(id);
    }


    //************************* Recuperer Les Besoins****************///
    //////////////***Imprimente**********/////////////
    public List<MaterielImprimenteDTO> getbesoinIM() {
        List<Imprimente> list = imRepo.getBesoinIM();
//        AppelOffre ap=getAppelOffre();
//        if(ap==null)
//        {
//            ap=new AppelOffre();
//        }
        System.out.println("helloimpriment pour afficher");
        System.out.println(list);

        List<MaterielImprimenteDTO> listF = new ArrayList<>();
        for (Imprimente im : list) {
            Ensiegnant e = getEnsignant(im.getId());
            if (e == null) {
                e = new Ensiegnant();
            }
            MaterielImprimenteDTO maDto = MaterielImprimenteDTO.builder()
                    .id(im.getId())
//                    .Appleoffreid(ap.getId())
                    .code_barre(im.getCode_barre())
//                    .datedebut(ap.getDate_debut())
//                    .dateFin(ap.getDate_fin())
                    .prix(im.getPrix())
                    .resolution(im.getResolution())
                    .vitesse(im.getVitesse())
                    .enseignant(e.getNom() + " " + e.getPrenom())
                    .departement(e.getDepartement())

                    .build();
            listF.add(maDto);

        }
        return listF;
    }

    //*************************Besoin Ordinateur************************/////////////////
    public List<MaterielOrdinateurDTO> getbesoinOr() {
        List<Ordinateur> list = ordRepo.getBesoinOrd();
        List<MaterielOrdinateurDTO> listF = new ArrayList<>();
        System.out.println("Ordinateur pour afficher");
        for (Ordinateur im : list) {
            Ensiegnant e = getEnsignant(im.getId());
            if (e == null) {
                e = new Ensiegnant();
            }


            MaterielOrdinateurDTO maDto = MaterielOrdinateurDTO.builder()
                    .id(im.getId())

                    .code_barre(im.getCode_barre())

                    .prix(im.getPrix())

                    .cpu(im.getCpu())
                    .ram(im.getRam())
                    .ecran(im.getEcran())
                    .disque(im.getDisque())
                    .enseignant(e.getNom() + " " + e.getPrenom())
                    .departement(e.getDepartement())

                    .build();
            listF.add(maDto);

        }
        return listF;
    }
    ///***********Generer Appele d'offre***********////
    //****************Modier id_appel_offre de materiel*************////////////////

    public void EnregisterIm(List<MaterielImprimenteDTO> list) {
        AppelOffre ap = new AppelOffre();
        ap.setId(list.get(0).getAppleoffreid());
        ap.setDate_debut(list.get(0).getDatedebut());
        ap.setDate_fin(list.get(0).getDateFin());
        this.genereAppel(ap);
        ap = appelRepo.getAppleOffre();
        System.out.println(ap);

        System.out.println("hello imprimente pour enrgistrer");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Im in boucle" + i);

            Materiel m = matRepo.findMaterielByid(list.get(i).getId());
            m.setAppelOffre(ap);
            System.out.println("test");
            System.out.println("Material:" + i + "\t" + m);
            matRepo.save(m);
        }


    }

    /********************Ordinateur************************/
    public void EnregisterOm(List<MaterielOrdinateurDTO> list) {

        AppelOffre ap = new AppelOffre();
        ap.setId(list.get(0).getAppleoffreid());
        ap.setDate_debut(list.get(0).getDatedebut());
        ap.setDate_fin(list.get(0).getDateFin());
        this.genereAppel(ap);
        ap = appelRepo.getAppleOffre();
        System.out.println(ap);

        System.out.println("hello imprimente pour enrgistrer");


        for (int i = 0; i < list.size(); i++) {
            System.out.println("Im in boucle" + i);

            Materiel m = matRepo.findMaterielByid(list.get(i).getId());
            m.setAppelOffre(ap);
            System.out.println("test");
            System.out.println("Material:" + i + "\t" + m);
            matRepo.save(m);
        }

    }
    // ******************************************Recuperer Les Materiels pour Enregistrer***************************//

    //Recuprer La marque et le prix pour chaque Materiel//////
    public Materiel_Proposition getInfo(Integer id) {

        return matpropRepo.getMaterialProposition(id);
    }

    /*****************Ordinateur******************/
    public List<MaterielOrdinateurDTO> getMatOr() {

        List<Ordinateur> list = ordRepo.getMaterialord();


        List<MaterielOrdinateurDTO> listF = new ArrayList<>();
        for (Ordinateur im : list) {

            Fournisseur f = getFounisseur(im.getId());

            Materiel_Proposition matp = getInfo(im.getId());

            MaterielOrdinateurDTO maDto = MaterielOrdinateurDTO.builder()
                    .prix(matp.getPrix())
                    .id(im.getId())
                    .cpu(im.getCpu())
                    .ram(im.getRam())
                    .ecran(im.getEcran())
                    .disque(im.getDisque())

                    .marque(matp.getMarque())
                    .Fournissuer(f.getNom_societe())
                    .build();
            listF.add(maDto);

        }
        return listF;
    }

    /**************Imprimente*****************/
    public List<MaterielImprimenteDTO> getMatIm() {
        List<Imprimente> list = imRepo.getMaterialIM();


        List<MaterielImprimenteDTO> listF = new ArrayList<>();
        for (Imprimente im : list) {

            Fournisseur f = getFounisseur(im.getId());


            Materiel_Proposition matp = getInfo(im.getId());
            MaterielImprimenteDTO maDto = MaterielImprimenteDTO.builder()
                    .prix(matp.getPrix())

                    .vitesse(im.getVitesse())
                    .resolution(im.getResolution())

                    .marque(matp.getMarque())
                    .Fournissuer(f.getNom_societe())
                    .build();
            listF.add(maDto);

        }
        return listF;
    }
    /**********************************Enregistrer Les Materiels************************************/
    /********************Ordinateur**********************************************/
    public void EnregisterMatOR(List<MaterielOrdinateurDTO> list) {


        System.out.println("on va sauvgarder ordinateur");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Im in boucle" + i);
            System.out.println("id******" + list.get(i).getId());
            Materiel m = matRepo.findMaterielByid(list.get(i).getId());
            m.setCode_barre(list.get(i).getCode_barre());

            LocalDate date = list.get(i).getDatelivraison();
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
            String text = date.format(formatters);
            LocalDate parsedDate = LocalDate.parse(text, formatters);

            m.setDate_livraison(parsedDate);
            m.setDuree_garentie(list.get(i).getDureegarantie());
            System.out.println("Material:" + i + "\t" + m);
            matRepo.save(m);
        }


    }

    public void EnregisterMatIM(List<MaterielImprimenteDTO> list) {


        System.out.println("hello imprimente pour enrgistrer");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Im in boucle" + i);
            System.out.println("id******" + list.get(i).getId());
            Materiel m = matRepo.findMaterielByid(list.get(i).getId());
            m.setCode_barre(list.get(i).getCode_barre());

            LocalDate date = list.get(i).getDatelivraison();
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
            String text = date.format(formatters);
            LocalDate parsedDate = LocalDate.parse(text, formatters);
            System.out.println("date************************" + parsedDate);
            System.out.println("date de lavrason " + list.get(i).getDatelivraison());
            m.setDate_livraison(parsedDate);
            m.setDuree_garentie(list.get(i).getDureegarantie());
            m.setPrix(list.get(i).getPrix());
            m.setMarque(list.get(i).getMarque());
            System.out.println("Material:" + i + "\t" + m);
            matRepo.save(m);
        }
    }

/***************************************Gestion des ressources**********************************************/
/*************************************************Recuprer les resssources************************************/
    /*********************************************Les imprimentes***********************************************/
    public List<MaterielImprimenteDTO> getRessourcesIm() {
        List<Imprimente> list = imRepo.getResourceIM();
        List<MaterielImprimenteDTO> listF = new ArrayList<>();
        for (Imprimente im : list) {

            Fournisseur f = getFounisseur(im.getId());
            Materiel_Proposition matp = getInfo(im.getId());
            Materiel m=matRepo.findMaterielByid(im.getId());

            MaterielImprimenteDTO maDto = MaterielImprimenteDTO.builder()
                    .prix(matp.getPrix())
                    .marque(matp.getMarque())
                    .code_barre(m.getCode_barre())
                    .dureegarantie(m.getDuree_garentie())
                    .datelivraison(m.getDate_livraison())
                    .id(im.getId())
                    .vitesse(im.getVitesse())
                    .resolution(im.getResolution())


                    .Fournissuer(f.getNom_societe())
                    .build();
            listF.add(maDto);

        }
        return listF;
    }

    /*********************************************Les Ordinateur**********************************************/
    public List<MaterielOrdinateurDTO> getRessourcesOr() {
        List<Ordinateur> list = ordRepo.getResourceOR();
        List<MaterielOrdinateurDTO> listF = new ArrayList<>();
        for (Ordinateur im : list) {

            Fournisseur f = getFounisseur(im.getId());
            Materiel_Proposition matp = getInfo(im.getId());
     Materiel m=matRepo.findMaterielByid(im.getId());

            MaterielOrdinateurDTO maDto = MaterielOrdinateurDTO.builder()
                    .prix(matp.getPrix())
                    .marque(matp.getMarque())
                    .code_barre(m.getCode_barre())
                    .dureegarantie(m.getDuree_garentie())
                    .datelivraison(m.getDate_livraison())
                    .id(im.getId())
                    .cpu(im.getCpu())
                    .ram(im.getRam())
                    .ecran(im.getEcran())
                    .disque(im.getDisque())

                    .Fournissuer(f.getNom_societe())
                    .build();
            listF.add(maDto);

        }
        return listF;
    }  /******* **********************************Suprimmer Materiel******************************************/
    /**************************************************Imprimente***********************************/
    public void suprimerIm(Integer id) {
Imprimente im=imRepo.findImprimenteByid(id);
System.out.println("Imprimente pour suprimmer");
Materiel m=matRepo.findMaterielByid(id);

        System.out.println("Material"+m);
System.out.println("Impriment"+im);
Materiel_Proposition matp=matpropRepo.findMateriel_PropositionByMateriel(m);
System.out.println("Material proposition"+matp);
//Proposition p=propoRepo.getPropostion(matp.getId());
//System.out.println("proposition"+p);




    }
    public void suprimerOr(Integer id) {
        Ordinateur im=ordRepo.findOrdinateurByid(id);
        System.out.println("Ordinateur pour suprimmer");
        Materiel m=matRepo.findMaterielByid(id);

        System.out.println("Material"+m);
        System.out.println("ordinateur"+im);
        Materiel_Proposition matp=matpropRepo.findMateriel_PropositionByMateriel(m);
        System.out.println("Material proposition"+matp);
        System.out.println("id de material"+m.getId());

      matpropRepo.delete(matp);

 ordRepo.delete(im);
matRepo.delete(m);




    }
}

