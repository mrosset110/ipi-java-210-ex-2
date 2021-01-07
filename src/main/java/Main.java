import java.util.Scanner;

public class Main {
    // exercice 1
    // Déclaration et initialisation des constantes
    static final short MAX_PTS_VIE = 100;
    static final short PTS_BOUCLIER = 25;
    static final short MAX_ATTAQUE_ENNEMI = 5;
    static final short MAX_VIE_ENNEMI = 30;
    static final short MAX_ATTAQUE_JOUEUR = 5;
    static final short REGENERATION_BOUCLIER_PAR_TOUR = 10;

    // exercice 2
    //Variables
    static String nomPersonnage;
    static short ptsDeVie;
    static short ptsBouclier;
    static short nbEnnemisTues = 0;
    static boolean bouclierActif = true;

    // exercice 3
    public static void main(String[] args) {
        initPersonnage();
        short nbPtsEnnemi = attaqueJoueur((short)10);
        System.out.println("L'ennemi a " + nbPtsEnnemi + " points de vie restant");
    }

    static void initPersonnage() {
        System.out.println("Saisir le nom de votre personnage");
        Scanner scanner = new Scanner(System.in);
        nomPersonnage = scanner.nextLine();
        ptsDeVie = MAX_PTS_VIE;
        ptsBouclier = PTS_BOUCLIER;
        System.out.println("OK " + Util.color(nomPersonnage, Color.GREEN) + " ! C'est parti !");
        scanner.close();
    }

    // exercice 4
    static boolean hasard(double pourcentageDeChance) {
        return pourcentageDeChance < Math.random();
    }

    // exercice 5
    static short nombreAuHasard(short max) {
        return (short) Math.round(Math.random() * max);
    }

    static short attaqueJoueur(short ptsDeVieEnnemi) {
        short dommages = nombreAuHasard(MAX_ATTAQUE_JOUEUR);
        System.out.println(Util.color(nomPersonnage, Color.GREEN)
                + " attaque l'" + Util.color("ennemi", Color.YELLOW) + " ! ");
        System.out.println("il lui fait perdre " + Util.color(dommages, Color.PURPLE)
                + " points de dommages");
        ptsDeVieEnnemi -= dommages;
        return ptsDeVieEnnemi;
    }
    static void afficherPersonnage(){
        System.out.print(Util.color(nomPersonnage, Color.GREEN) +
                " (" + Util.color(ptsDeVie, Color.RED));
        if(bouclierActif){
            System.out.print(" " + Util.color(ptsBouclier, Color.BLUE));
        }
        System.out.print(")");
    }
}





