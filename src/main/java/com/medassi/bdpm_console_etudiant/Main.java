package com.medassi.bdpm_console_etudiant;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
    private static void afficherMedicamentsASurveiller() {
        System.out.println("Médicaments à surveiller :");
        ArrayList<Medicament> lesMedicamentsSurvRenf = BDPM.getDatabase().getMedicamentsSurvRenf();
        for (Medicament m : lesMedicamentsSurvRenf) {
            System.out.println(m.denomination);
        }
    }

    /*
    Demande à l’utilisateur la saisie d’un entier après avoir afficher 
    le message passé en paramètre. 
    Retourne l’entier saisi par l’utilisateur.
     */
    private static int saisirInt(String msg) {
        System.out.println(msg);
        Scanner sc = new Scanner(System.in);
        int nb = sc.nextInt();
        return nb;
    }

    /*
    Demande à l’utilisateur la saisie d’une chaîne de caractère 
    après avoir afficher le message passé en paramètre.
    Retourne la chaîne saisie par l’utilisateur.
     */
    private static String saisirString(String msg) {
        System.out.println(msg);
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        return s;
    }

    /*
    Affiche toutes les informations 
    qui concernent le médicament passé en paramètre
     */
    private static void afficherFiche(Medicament m) {
        System.out.println(m.denomination + " (" + m.code_cis + ")");
        ArrayList<Composant> lesComposantsDeM = BDPM.getDatabase().getCompositions(m);
        for (Composant c : lesComposantsDeM) {
            System.out.println("\t- " + c.denomination_sub + " (" + c.dosage + ")");
        }
    }

    /*
    Demande à l’utilisateur d’entrer le mot clé à rechercher 
    puis va rechercher sur la base de données tous les médicaments qui correspondent. 
    Permet ensuite à l’utilisateur la sélection du médicament et affiche sa fiche.
     */
    private static void choixRechercheMotCle() {
        String motClef = saisirString("Entrer le mot clef");
        ArrayList<Medicament> ms = BDPM.getDatabase().getMedicamentsByMotClef(motClef);
        int cpt = 1;
        for (Medicament m : ms) {
            System.out.println(cpt + " -> " + m.denomination);
            cpt = cpt + 1; //cpt++
        }
        int choixMedicament = saisirInt("Entrer le numéro du médicament :");
        Medicament medicamentSel = ms.get(choixMedicament - 1);
        afficherFiche(medicamentSel);
    }

    // Demande à l’utilisateur d’entrer le code CIS à rechercher 
    // puis va rechercher sur la base de données 
    // le médicament qui correspond à ce code CIS. 
    // Affiche ensuite sa fiche.
    private static void choixRechercheCodeCis() {
        String code = saisirString("Entrer le code CIS :");
        Medicament m = BDPM.getDatabase().getMedicamentByCodeCIS(code);
        afficherFiche(m);
    }
    
    //Demande à l’utilisateur d’entrer le nom du laboratoire à rechercher
    //puis va rechercher sur la base de données les médicaments 
    //qui correspondent à ce laboratoire. 
    //Permet ensuite à l’utilisateur la sélection du médicament
    //et affiche sa fiche.
    private static void choixRechercheLabo(){
        String labo = saisirString("Entrer le laboratoire à rechercher") ;
        ArrayList<Medicament> lesMedocs = BDPM.getDatabase().getMedicamentsByLabo(labo) ;
        int cpt=1 ;
        for( Medicament unMedoc : lesMedocs ){
            System.out.println(cpt+" -> "+unMedoc.denomination);
            cpt++ ;
        }
        int num = saisirInt("Entrer le numéro du médicament :") ;
        afficherFiche(lesMedocs.get(num-1));
    }


    
    public static void main(String[] args) {
        //choixRechercheMotCle();
        //choixRechercheCodeCis();
        choixRechercheLabo();
    }
    
}
