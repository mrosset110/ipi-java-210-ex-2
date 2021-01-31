import java.awt.desktop.OpenURIEvent;
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
        boolean moto = hasard(0.5);
        short nbEnnemi[] = initEnnemis();
        short voiture = nbEnnemi[0];
        System.out.println("combat avec un ennemi possédant " + Util.color((voiture), Color.PURPLE) + " points de vie.");
        afficherPersonnage();
        System.out.println(" vs ennemi " + Util.color((voiture), Color.PURPLE));
        short bmw = attaque(voiture, moto);
        if (moto) {
            moto = false;
        }
        else {
            moto = true;
        }
        short audi = attaque(voiture, moto);
        afficherPersonnage();
        if (moto) {
            System.out.println(" vs ennemi " + Util.color((audi), Color.PURPLE));
        }
        else{
            System.out.println(" vs ennemi " + Util.color((bmw), Color.PURPLE ));
        }

        if (audi < 0 || bmw < 0){
            short i = 0;
            i++;
            System.out.println("l'ennemi est mort ! Au suivant !");
            System.out.println("Régénération du bouclier : +10");
            ptsBouclier += REGENERATION_BOUCLIER_PAR_TOUR;
            if (ptsBouclier < 25) {
                ptsBouclier = 25;
            }
            System.out.println("Saisisser S pour passer au combat suivant ou n'importe quoi d'autre pour fuir ...");
            Scanner scannertest = new Scanner(System.in);
            String lettre = scannertest.nextLine();
            if (lettre.equals("S")){
                    System.out.println("Ok ! Nous passons au combat suivant !");
                }else {
                System.out.println("Courage fuyons !");
                System.out.println("Vous avez tué " + i + " ennemis mais vous êtes partis lâchement avant la fin ...");
                System.exit(0);
                }


        }

    }

    static void initPersonnage() {
        System.out.println("Saisir le nom de votre personnage");
        Scanner scanner = new Scanner(System.in);
        nomPersonnage = scanner.nextLine();
        ptsDeVie = MAX_PTS_VIE;
        ptsBouclier = PTS_BOUCLIER;
        System.out.println("OK " + Util.color(nomPersonnage, Color.GREEN) + " ! C'est parti !");
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

    static void attaqueEnnemi(){
    if(ptsDeVie <= 0) {
        return;
    }

    short dommages = nombreAuHasard(MAX_ATTAQUE_ENNEMI);
    System.out.print("L'" + Util.color("ennemi", Color.YELLOW) + " attaque " +
            Util.color(nomPersonnage, Color.GREEN) +" ! ");
    System.out.print("Il lui fait " + dommages + " points de dommages ! ");
    if(bouclierActif && ptsBouclier >0) {
        short dommagesBouclier = (short) Math.min(ptsBouclier, dommages);
        System.out.print("Le bouclier perd " + Util.color(dommagesBouclier, Color.BLUE) + " points. ");
        ptsBouclier -= dommagesBouclier;
        dommages -= dommagesBouclier;
    }

    if(dommages > 0){
        ptsDeVie -= dommages;
        System.out.print(Util.color(nomPersonnage, Color.GREEN)+ " perd " +
                Util.color(dommages, Color.RED) + " points de vie ! ");
    }
        System.out.println();
    }

    static short attaque(short ennemi, boolean joueurAttaque){
        if(ennemi <= 0 || ptsDeVie <= 0){
            return ennemi;
        }
        if(joueurAttaque){
            ennemi = attaqueJoueur(ennemi);
        }
        else {
            attaqueEnnemi();
        }
        return ennemi;

    }

    static short[] initEnnemis(){
        System.out.println("Combien souhaitez-vous combattre d'ennemis ?");
        Scanner scanner = new Scanner(System.in);
        short nbEnnemis = scanner.nextShort();
        System.out.println("Génération des ennemis...");
        short[] ennemis = new short[nbEnnemis];
        for (short i = 0; i < nbEnnemis; i++){
            ennemis[i] = nombreAuHasard(MAX_VIE_ENNEMI);
            System.out.println("Ennemi numéro " + (i + 1) + " : " + Util.color(ennemis[i], Color.PURPLE));
        }
        return ennemis;
    }


}




