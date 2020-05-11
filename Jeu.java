/**
 *  Classe principale du jeu "Zork". <p>
 *
 *  Zork est un jeu d'aventure très rudimentaire avec une interface en mode
 *  texte: les joueurs peuvent juste se déplacer parmi les différentes pieces.
 *  Ce jeu nécessite vraiment d'etre enrichi pour devenir intéressant!</p> <p>
 *
 *  Pour jouer a ce jeu, créer une instance de cette classe et appeler sa
 *  méthode "jouer". </p> <p>
 *
 *  Cette classe crée et initialise des instances de toutes les autres classes:
 *  elle crée toutes les pieces, crée l'analyseur syntaxique et démarre le jeu.
 *  Elle se charge aussi d'exécuter les commandes que lui renvoie l'analyseur
 *  syntaxique.</p>
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @version    1.2
 * @since      March 2000
 */

import java.util.ArrayList;
import java.util.List; 
import java.util.Random; 
import java.util.Scanner;

public class Jeu {
	private AnalyseurSyntaxique analyseurSyntaxique;
	private Piece pieceCourante;

	/* ------------------------------------------ */

	// On creer des ArrayList qui vont contenir les objets

	public ArrayList<ObjetZork> objetZorkPossibleCouloir = new ArrayList<>();
	public ArrayList<ObjetZork> objetZorkPossibleWc = new ArrayList<ObjetZork>();
	public ArrayList<ObjetZork> objetZorkPossibleCuisine = new ArrayList<ObjetZork>();
	public ArrayList<ObjetZork> objetZorkPossibleSalon = new ArrayList<ObjetZork>();
	public ArrayList<ObjetZork> objetZorkPossibleChambre = new ArrayList<ObjetZork>();
	public ArrayList<ObjetZork> objetZorkPossibleTerrasse = new ArrayList<ObjetZork>();
	public ArrayList<ObjetZork> objetZorkPossibleJardin = new ArrayList<ObjetZork>();


	public ArrayList<Chien> chienPossible = new ArrayList<Chien>();

	Joueur joueur;
	public Dessin dessin;
	/* ------------------------------------------ */


	/**
	 *  Crée le jeu et initialise la carte du jeu (i.e. les pièces).
	 */
	public Jeu() {
		creerPieces();
		analyseurSyntaxique = new AnalyseurSyntaxique();
	}

	public ArrayList<ObjetZork> getRandomZorkObjects(ArrayList<ObjetZork> list, int totalObjetsZork) 
    { 
        Random rand = new Random(); 
        ArrayList<ObjetZork> newList = new ArrayList<>(); 
        
        for (int i=0; i<totalObjetsZork; i++) { 
 
            int randomIndex = rand.nextInt(list.size()); 
  
            newList.add(list.get(randomIndex)); 
  
            list.remove(randomIndex); 
        } 
        return newList; 
    }

    // je les déclare en public pour pouvoir les évoquer a l'interieur d'autre méthode autre que creerPiece
    public Piece wc;
    public Piece chambre;
    public Piece terrasse;

	/**
	 *  Crée toutes les pieces et relie leurs sorties les unes aux autres.
	 */
	public void creerPieces() {
		Piece couloir;
		//Piece wc;
		Piece cuisine;
		Piece salon;
		//Piece chambre;
		//Piece terrasse;
		Piece jardin;

		Random rand = new Random();

		// objetZorkPossibleCouloir
		objetZorkPossibleCouloir.add(new ObjetZork("Chaise", 2500, false)); objetZorkPossibleCouloir.add(new ObjetZork("Casque", 400,false));
		objetZorkPossibleCouloir.add(new ObjetZork("Tableau", 1005,false)); objetZorkPossibleCouloir.add(new ObjetZork("Chaussures", 240,false));

		objetZorkPossibleCouloir = getRandomZorkObjects(objetZorkPossibleCouloir, rand.nextInt(objetZorkPossibleCouloir.size()));
		objetZorkPossibleCouloir.add(new ObjetZork("Tapis", 4205,false)); objetZorkPossibleCouloir.add(new ObjetZork("Tiroir", 3440,false));
		objetZorkPossibleCouloir.add(new ObjetZork("Sandales", 440,false));

		// objetZorkPossibleWc
		objetZorkPossibleWc.add(new ObjetZork("Journal", 50,false)); objetZorkPossibleWc.add(new ObjetZork("Dentifrice", 20,false));
		objetZorkPossibleWc.add(new ObjetZork("Brosse", 45,false)); objetZorkPossibleWc.add(new ObjetZork("Miroir", 1050,false));

		objetZorkPossibleWc = getRandomZorkObjects(objetZorkPossibleWc, rand.nextInt(objetZorkPossibleWc.size()));
		objetZorkPossibleWc.add(new ObjetZork("Magicien", 999999,false));

		objetZorkPossibleWc.add(new ObjetZork("Serviette", 99,false));
		
		// objetZorkPossibleCuisine
		objetZorkPossibleCuisine.add(new ObjetZork("Couteau", 140,false)); objetZorkPossibleCuisine.add(new ObjetZork("Cuillere", 10 ,false));
		objetZorkPossibleCuisine.add(new ObjetZork("Reffrigirateur", 9950, false)); objetZorkPossibleCuisine.add(new ObjetZork("Microonde", 4240, false));


		objetZorkPossibleCuisine = getRandomZorkObjects(objetZorkPossibleCuisine, rand.nextInt(objetZorkPossibleCuisine.size()));
		objetZorkPossibleCuisine.add(new ObjetZork("Viande", 100, true)); objetZorkPossibleCuisine.add(new ObjetZork("Thon", 50, true));
		objetZorkPossibleCuisine.add(new ObjetZork("Saumom", 150, true)); objetZorkPossibleCuisine.add(new ObjetZork("Frommage", 60, true));
		objetZorkPossibleCuisine.add(new ObjetZork("Foie", 30, true)); objetZorkPossibleCuisine.add(new ObjetZork("persil", 15, true));

		// objetZorkPossibleSalon
		objetZorkPossibleSalon.add(new ObjetZork("Fauteuil", 7450, false)); objetZorkPossibleSalon.add(new ObjetZork("Television", 2390, false));
		objetZorkPossibleSalon.add(new ObjetZork("Vase", 570, false)); objetZorkPossibleSalon.add(new ObjetZork("Table", 3950, false));

		objetZorkPossibleSalon = getRandomZorkObjects(objetZorkPossibleSalon, rand.nextInt(objetZorkPossibleSalon.size()));
		objetZorkPossibleSalon.add(new ObjetZork("Radio", 500, false)); objetZorkPossibleSalon.add(new ObjetZork("horloge", 570, false));
		objetZorkPossibleSalon.add(new ObjetZork("Chargeur", 170, false));

		// objetZorkPossibleChambre
		objetZorkPossibleChambre.add(new ObjetZork("Placare", 5990, false));
		objetZorkPossibleChambre.add(new ObjetZork("Livre", 200, false)); objetZorkPossibleChambre.add(new ObjetZork("Plume", 2, false));
		objetZorkPossibleChambre.add(new ObjetZork("Pc", 250, false)); objetZorkPossibleChambre.add(new ObjetZork("Souris", 90, false));

		objetZorkPossibleChambre = getRandomZorkObjects(objetZorkPossibleChambre, rand.nextInt(objetZorkPossibleSalon.size()));
		objetZorkPossibleChambre.add(new ObjetZork("Pistolet", 450, false));
		objetZorkPossibleChambre.add(new ObjetZork("Chat", 999999, false));

		objetZorkPossibleChambre.add(new ObjetZork("Portable", 120, false)); objetZorkPossibleChambre.add(new ObjetZork("Usb", 20, false));
		objetZorkPossibleChambre.add(new ObjetZork("Lit", 8900, false)); objetZorkPossibleChambre.add(new ObjetZork("Oreiller", 90, false)); 

		// objetZorkPossibleTerrasse
		objetZorkPossibleTerrasse.add(new ObjetZork("Chaise", 1250, false)); objetZorkPossibleTerrasse.add(new ObjetZork("Velo", 2550, false));
		objetZorkPossibleTerrasse.add(new ObjetZork("Trottinette", 750, false)); objetZorkPossibleTerrasse.add(new ObjetZork("Scie", 75, false));

		objetZorkPossibleTerrasse = getRandomZorkObjects(objetZorkPossibleTerrasse, rand.nextInt(objetZorkPossibleTerrasse.size()));
		objetZorkPossibleTerrasse.add(new ObjetZork("Parasol", 1750, false));
		
		// objetZorkPossibleJardin
		objetZorkPossibleJardin.add(new ObjetZork("Balancoire", 15950, false)); objetZorkPossibleJardin.add(new ObjetZork("Ballon", 170, false));
		objetZorkPossibleJardin.add(new ObjetZork("Arrosoir", 150, false)); objetZorkPossibleJardin.add(new ObjetZork("Seau", 35, false));

		objetZorkPossibleJardin = getRandomZorkObjects(objetZorkPossibleJardin, rand.nextInt(objetZorkPossibleJardin.size()));
		objetZorkPossibleJardin.add(new ObjetZork("Arbre", 20850, false)); objetZorkPossibleJardin.add(new ObjetZork("Balais", 350, false));

		// création des pieces
		couloir = new Piece("devant le salon (LE COULOIR)", objetZorkPossibleCouloir);
		wc = new Piece("les \"wc\"", objetZorkPossibleWc);

		wc.ajouterChien(new Chien("Rex", 235, 550, true));

		cuisine = new Piece("la \"CUISINE\"", objetZorkPossibleCuisine);

		cuisine.ajouterChien(new Chien("Milou", 200, 400, false));
		cuisine.ajouterChien(new Chien("Chouppette", 135, 550, false));
		
		salon = new Piece("le \"SALON\"", objetZorkPossibleSalon);
		chambre = new Piece("la \"CHAMBRE A COUCHE\"", objetZorkPossibleChambre);

		terrasse = new Piece("La \"TERRASSE\"", objetZorkPossibleTerrasse);
		jardin = new Piece("Le \"JARDIN\"", objetZorkPossibleJardin);



		// initialise les sorties des pieces
		couloir.setSorties(null, null, salon, null);
		wc.setSorties(salon, null, null, null);
		cuisine.setSorties(null, salon, null, null);
		salon.setSorties(couloir, chambre, wc, cuisine);
		chambre.setSorties(null, terrasse, null, salon);
		terrasse.setSorties(null, null, jardin, chambre);
		jardin.setSorties(terrasse, null, null, null);

		// le jeu commence couloir
		pieceCourante = couloir;

		/*-----------------------------------------------*/
		joueur = new Joueur("Player1", pieceCourante);
		/*-----------------------------------------------*/


	}
	// pour tester si le joueur a gagner ou pas encore 
	public boolean magicienVivant=true, chatDansChambre=true, chienDansTerrase=false;

	/**
	 *  Pour lancer le jeu. Boucle jusqu'a la fin du jeu.
	 */
	public void jouer() {
		afficherMsgBienvennue();

		// Entrée dans la boucle principale du jeu. Cette boucle lit
		// et exécute les commandes entrées par l'utilisateur, jusqu'a
		// ce que la commande choisie soit la commande "quitter"
		boolean termine = false;
		while (!termine) {
			Commande commande = analyseurSyntaxique.getCommande();
			termine = traiterCommande(commande);

			// teste si j'ai gagner ou pas encore
			int tmp=0, tmp2=0;
			for (int a=0; a<wc.lesObjets.size(); a++) {
				if (wc.lesObjets.get(a).getNom().equals("Magicien"))
					tmp = 1;
			}
			if (tmp==0)
					magicienVivant = false;
			for (int b=0; b<chambre.lesObjets.size(); b++) {
				if (chambre.lesObjets.get(b).getNom().equals("Chat"))
					tmp2 = 1;
			}
			if (tmp2==0)
				chatDansChambre = false;
			for (int c=0; c<terrasse.lesChiens.size(); c++) {
				if (terrasse.lesChiens.get(c).estMalade())
					chienDansTerrase = true;
			}
			
			if (!magicienVivant && !chatDansChambre && chienDansTerrase) {
				System.out.println("FELICITATIONS !! Vous avez gagne");
				System.out.println("Vous avez accompli votre mission avec success");
				System.out.println("		            ");
				System.out.println("		  \\(^_^)/   ");
				System.out.println("		    )  )    ");
				System.out.println("		   /    \\   ");
				System.out.println("");
				termine = true;
			}
		}
		System.out.println("Merci d'avoir jouer.  Au revoir.");
	}


	/**
	 *  Affiche le message d'accueil pour le joueur.
	 */
	public void afficherMsgBienvennue() {
		System.out.println();
		System.out.println("----------------------> Bienvennue dans le monde de Zork ! <----------------------");
		System.out.println("Zork est un nouveau jeu d'aventure tres interesssant.");
		System.out.println("Tapez 'aide' si vous avez besoin d'aide.");
		System.out.println();
		System.out.println(pieceCourante.descriptionLongue());
		pieceCourante.afficherObjetsPiece();
		pieceCourante.afficherChiensPiece();
	}


	/**
	 *  Exécute la commande spécifiée. Si cette commande termine le jeu, la valeur
	 *  true est renvoyée, sinon false est renvoyée
	 *
	 * @param  commande  La commande a exécuter
	 * @return           true si cette commande termine le jeu ; false sinon.
	 */
	public boolean traiterCommande(Commande commande) {
		if (commande.estInconnue()) {
			System.out.println("Je ne comprends pas ce que vous voulez...");
			return false;
		}

		String motCommande = commande.getMotCommande();
		if (motCommande.equals("aide")) {
			afficherAide();
		} else if (motCommande.equals("aller")) {
			deplacerVersAutrePiece(commande);
		} else if (motCommande.equals("quitter")) {
			if (commande.aSecondMot()) {
				System.out.println("Quitter quoi ?");
			} else {
				return true;
			}
		} /* ------------------------------------------------- */
		else if (motCommande.equals("prendre")) {
			String aPrendre = commande.getSecondMot();
			if (aPrendre!=null) {
				int numElemAPrendre;
			try {
			   numElemAPrendre = Integer.parseInt(aPrendre);
			}
			catch (NumberFormatException e)
			{
			   numElemAPrendre = 0;
			}
				if (numElemAPrendre>=1 && numElemAPrendre<=pieceCourante.lesObjets.size()) {
					if (joueur.porterObjet(pieceCourante.lesObjets.get(numElemAPrendre-1)) ) {
						pieceCourante.retirer(pieceCourante.lesObjets.get(numElemAPrendre-1));
						joueur.afficherObjetsJoueur();
					}
				}
				else
					System.out.println("Aucun objet ne correspond a votre saisi !");
			} 
			else {
				System.out.println("Prendre quoi?");
			}
		}

		else if (motCommande.equals("prendreChien")) {
			String chienPreneur = commande.getSecondMot();
			if (chienPreneur!=null) {
				int numChienPrenneur;
				try {
				   numChienPrenneur = Integer.parseInt(chienPreneur);
				}
				catch (NumberFormatException e)
				{
				   numChienPrenneur = 0;
				}
					if (numChienPrenneur>=1 && numChienPrenneur<=joueur.lesChiensJoueur.size()) {
						Scanner sc = new Scanner(System.in);
						System.out.println("Saisisser le numero de l'objet a prendre par le chien: ");
						System.out.print("-->");
						int numObjetChien = 0;
						if (sc.hasNextInt())
							numObjetChien = sc.nextInt();
						if (numObjetChien>=1 && numObjetChien<=pieceCourante.lesObjets.size()){
							if (joueur.lesChiensJoueur.get(numChienPrenneur-1).estTransportable(pieceCourante.lesObjets.get(numObjetChien-1))) {
								joueur.lesChiensJoueur.get(numChienPrenneur-1).prendreObjetChien(pieceCourante.lesObjets.get(numObjetChien-1));
								pieceCourante.retirer(pieceCourante.lesObjets.get(numObjetChien-1));
								joueur.lesChiensJoueur.get(numChienPrenneur-1).afficherObjetsChien();
							} else {
								System.out.println("Votre chien ne peut pas transporter cette objet !");
							}
						} 
						else {
							System.out.println("Aucun des objets ne correspond a votre saisi");
						}

					} else {
						System.out.println("Aucun de vos chien ne correspond a votre saisi !");
					}
			} 
			else {
				System.out.println("Votre chien doit prendre quoi?");
			}
		}

		else if (motCommande.equals("deposerChien")) {
			String chienDeposeur = commande.getSecondMot();
			if (chienDeposeur!=null) {
				int numChienDeposeur;
				try {
				   numChienDeposeur = Integer.parseInt(chienDeposeur);
				}
				catch (NumberFormatException e)
				{
				   numChienDeposeur = 0;
				}
					if (numChienDeposeur>=1 && numChienDeposeur<=joueur.lesChiensJoueur.size()) {
						Scanner sc = new Scanner(System.in);
						System.out.println("Saisisser le numero de l'objet a deposer par le chien: ");
						System.out.print("-->");
						int numObjetChien = 0;
						if (sc.hasNextInt())
							numObjetChien = sc.nextInt();
						if (numObjetChien>=1 && numObjetChien<=joueur.lesChiensJoueur.get(numChienDeposeur-1).lesObjetsChien.size()){
							pieceCourante.ajouter(joueur.lesChiensJoueur.get(numChienDeposeur-1).lesObjetsChien.get(numObjetChien-1));
							joueur.lesChiensJoueur.get(numChienDeposeur-1).deposerObjetChien(joueur.lesChiensJoueur.get(numChienDeposeur-1).lesObjetsChien.get(numObjetChien-1));
							joueur.lesChiensJoueur.get(numChienDeposeur-1).afficherObjetsChien();	
						} else if (joueur.lesChiensJoueur.get(numChienDeposeur-1).lesObjetsChien.size()==0){
							System.out.println("Votre chien n'a rien a deposer !");
						} else {
							System.out.println("Votre saisi ne correspond a aucun objet objet du chien !");
						}
					} else {
						System.out.println("Aucun de vos chien ne correspond a votre saisi !");
					}
			} 
			else {
				System.out.println("\tVotre chien doit deposer quoi?");
			}
		}

		else if (motCommande.equals("nourir")) {
			String chienANourir = commande.getSecondMot();
			if (chienANourir!=null) {
				int numChienANourir;
				try {
				   numChienANourir = Integer.parseInt(chienANourir);
				}
				catch (NumberFormatException e)
				{
				   numChienANourir = 0;
				}
					if (numChienANourir>=1 && numChienANourir<=joueur.lesChiensJoueur.size()) {
						Scanner sc = new Scanner(System.in);
						System.out.println("Saisisser le numero de l'aliment: ");
						System.out.print("-->");
						int numAlimentChien = 0;
						if (sc.hasNextInt())
							numAlimentChien = sc.nextInt();
						if (numAlimentChien>=1 && numAlimentChien<=joueur.lesObjetsJoueur.size()){
							if (joueur.lesObjetsJoueur.get(numAlimentChien-1).estMangeable()) {
								joueur.deposerObjet(joueur.lesObjetsJoueur.get(numAlimentChien-1));
								joueur.lesChiensJoueur.get(numChienANourir-1).nourir(8);
								System.out.println("Yaamii");
							} else {
								System.out.println("L'aliment choisi n'est pas mangeable !");
							}
						} else {
							System.out.println("Aucun aliment ne correspond a votre saisi");
						}
					} else {
						System.out.println("Aucun chien ne correspond a votre saisi !");
					}
			} 
			else {
				System.out.println("Nourir qui?");
			}
		}

		else if (motCommande.equals("deposer")) {
			String aDeposer = commande.getSecondMot();
			if (aDeposer!=null) {
				int numElemADeposer;
			try {
			   numElemADeposer = Integer.parseInt(aDeposer);
			}
			catch (NumberFormatException e)
			{
			   numElemADeposer = 0;
			}
				if (numElemADeposer>=1 && numElemADeposer<=joueur.lesObjetsJoueur.size()) {
					pieceCourante.ajouter(joueur.lesObjetsJoueur.get(numElemADeposer-1));
					joueur.deposerObjet(joueur.lesObjetsJoueur.get(numElemADeposer-1));
					joueur.afficherObjetsJoueur();
				}
				else
					System.out.println("Aucun objet ne correspond a votre saisi !");

			} 
			else {
				System.out.println("Deposer quoi?");
			}
		}

		else if (motCommande.equals("mesObjets")) {
				joueur.afficherObjetsJoueur();
		}

		else if (motCommande.equals("objetsChien")) {
			String chien = commande.getSecondMot();
			if (chien!=null) {
				int numChien;
			try {
			   numChien = Integer.parseInt(chien);
			}
			catch (NumberFormatException e)
			{
			   numChien = 0;
			}
				if (numChien>=1 && numChien<=joueur.lesChiensJoueur.size()) {
					joueur.lesChiensJoueur.get(numChien-1).afficherObjetsChien();
				}
				else
					System.out.println("Aucun Chien ne correspond a votre saisi !");
			} 
			else {
				System.out.println("Quel chien");
			}
		}

		else if (motCommande.equals("chiensPiece")) {
			pieceCourante.afficherChiensPiece();
		}

		else if (motCommande.equals("objetsPiece")) {
			pieceCourante.afficherObjetsPiece();
		}

		else if (motCommande.equals("adopter")) {
			String aAdopter = commande.getSecondMot();
			if (aAdopter!=null) {
				int numChienAAdopter;
			try {
			   numChienAAdopter = Integer.parseInt(aAdopter);
			}
			catch (NumberFormatException e)
			{
			   numChienAAdopter = 0;
			}
				if (numChienAAdopter>=1 && numChienAAdopter<=pieceCourante.lesChiens.size()) {
					if (joueur.adopterChien(pieceCourante.lesChiens.get(numChienAAdopter-1)) ) {
						pieceCourante.retirerChien(pieceCourante.lesChiens.get(numChienAAdopter-1));
						joueur.afficherChiensJoueur();
					}
				}
				else
					System.out.println("Aucun chien ne correspond a votre saisi !");
			} 
			else {
				System.out.println("Adopter qui");
			}
		}

		else if (motCommande.equals("liberer")) {
			String aLiberer = commande.getSecondMot();
			if (aLiberer!=null) {
				int numChienALiberer;
			try {
			   numChienALiberer = Integer.parseInt(aLiberer);
			}
			catch (NumberFormatException e)
			{
			   numChienALiberer = 0;
			}
				if (numChienALiberer>=1 && numChienALiberer<=joueur.lesChiensJoueur.size()) {
					pieceCourante.ajouterChien(joueur.lesChiensJoueur.get(numChienALiberer-1));
					joueur.libererChien(joueur.lesChiensJoueur.get(numChienALiberer-1));
					joueur.afficherChiensJoueur();

					// je retire le chat s'il est dans la piece
					for (int i=0; i<pieceCourante.lesObjets.size(); i++) {
						if (pieceCourante.lesObjets.get(i).getNom().equals("Chat")) {
							pieceCourante.retirer(pieceCourante.lesObjets.get(i));
							System.out.println("Genial ! Le chat s'est enfui a cause du chien que vous avez liberez dans cette piece");
							System.out.println("Maintenant vous pouvez dormir tranquilement");
						}
					}

				}
				else
					System.out.println("Aucun chien ne correspond a votre saisi !");

			} 
			else {
				System.out.println("liberer qui?");
			}
		}

		else if (motCommande.equals("mesChiens")) {
			joueur.afficherChiensJoueur();
		}

		else if (motCommande.equals("tuerAvec")) {
			String objet = commande.getSecondMot();
			if (objet!=null) {
				int numObjet;
			try {
			   numObjet = Integer.parseInt(objet);
			}
			catch (NumberFormatException e)
			{
			   numObjet = 0;
			}
				boolean magicienPresent = false;
				if (numObjet>=1 && numObjet<=joueur.lesObjetsJoueur.size()) {
					if (joueur.lesObjetsJoueur.get(numObjet-1).getNom().equals("Pistolet")  || joueur.lesObjetsJoueur.get(numObjet-1).getNom().equals("Couteau")) {
						for (int i=0; i<pieceCourante.lesObjets.size(); i++) {
							if (pieceCourante.lesObjets.get(i).getNom().equals("Magicien")) {
								if (joueur.lesObjetsJoueur.get(numObjet-1).getNom().equals("Pistolet"))
									System.out.println("BAAAAM! Vous avez tuer le mechant magicien avec une balle dans la tete !");
								if (joueur.lesObjetsJoueur.get(numObjet-1).getNom().equals("Couteau"))
									System.out.println("Vous avez poignarder le magicien, il est mort maintenant !");
								pieceCourante.retirer(pieceCourante.lesObjets.get(i));
								magicienPresent = true;
							}
						}
						if (!magicienPresent) 
							System.out.println("Le mechant magicien n'est pas dans cette piece !");
					}
					else
						System.out.println("Cette Objet ne peut pas tuer le magicien !");
				}
				else
					System.out.println("Aucun objet ne correspond a votre saisi !");

			} 
			else {
				System.out.println("Tuer avec quoi?");
			}
		}

			
		 /* ------------------------------------------------- */

		return false;
	}


	// implementations des commandes utilisateur:

	/**
	 *  Affichage de l'aide. Affiche notament la liste des commandes utilisables.
	 */
	public void afficherAide() {
		System.out.println("D'etranges choses se passe chez vous. Vous etes seul");
		System.out.println("Vous dessidez d'aller chercher ce qui ne vas pas");
		System.out.println();
		System.out.println("Les commandes reconnues sont:");
		analyseurSyntaxique.afficherToutesLesCommandes();
	}


	/**
	 *  Tente d'aller dans la direction spécifiée par la commande. Si la piece
	 *  courante possède une sortie dans cette direction, la piece correspondant a
	 *  cette sortie devient la piece courante, dans les autres cas affiche un
	 *  message d'erreur.
	 *
	 * @param  commande  Commande dont le second mot spécifie la direction a suivre
	 */
	public void deplacerVersAutrePiece(Commande commande) {
		if (!commande.aSecondMot()) {
			// si la commande ne contient pas de second mot, nous ne
			// savons pas ou aller..
			System.out.println("Aller ou ?");
			return;
		}

		String direction = commande.getSecondMot();
		Direction d = null;
		try {
		    d = Direction.valueOf(direction);
		  } catch (IllegalArgumentException e) {
		      System.out.println("Pour indiquer une direction entrez une des chaînes de caractères suivantes:");
		      for (Direction dok : Direction.values()) {
		          System.out.println("-> \"" + dok + "\"");
		      }
		      return;
		  }

		// Tentative d'aller dans la direction indiquée.
		Piece pieceSuivante = pieceCourante.pieceSuivante(d);

		if (pieceSuivante == null) {
			System.out.println("Il n'y a pas de porte dans cette direction!");
		} else {
			// le(s) chien(s) que j'ai doi(ven)t me suivre
			if (joueur.lesChiensJoueur.size()>0) {
				for (int i=0; i<joueur.lesChiensJoueur.size(); i++) {
					joueur.lesChiensJoueur.get(i).enleverEnergie(2);
					String msgDeplacementChien = pieceCourante.descriptionDeplacementChien(joueur.lesChiensJoueur.get(i), pieceCourante, pieceSuivante);
					System.out.println(msgDeplacementChien);
					if (joueur.lesChiensJoueur.get(i).getEnergie()<=0) {
						System.out.println("Votre chien est mort de fin !");
						System.out.println("Vous auriez du le nourir");
						System.out.println("Perdu !");
						System.out.println("		            ");
						System.out.println("		    (x_x)   ");
						System.out.println("		   /)  )\\   ");
						System.out.println("		   /    \\   ");
						System.out.println("");
						System.exit(1);
					}
				}
			}

			pieceCourante = pieceSuivante;
			System.out.println(pieceCourante.descriptionLongue());
			/*-----------------------------------------------*/
			pieceCourante.afficherObjetsPiece();
			pieceCourante.afficherChiensPiece();
			/*-----------------------------------------------*/
		}
	}
}

