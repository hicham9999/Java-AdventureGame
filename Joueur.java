import java.util.ArrayList;

public class Joueur {

	private final String nomJoueur;
	private Piece pieceJoueur;
	public static final int CAPACITE_TRANSPORT_JOUEUR = 3000;

	ArrayList<ObjetZork> lesObjetsJoueur = new ArrayList<ObjetZork>();
	ArrayList<Chien> lesChiensJoueur = new ArrayList<Chien>();

	/**
	 * Initialise un joueur avec un nom et une piece dans laquelle il se situe
	 * C'est une méthode constructeur, elle ne renvoie rien
	 */

	public Joueur(String nomJoueur, Piece pieceJoueur) {
		this.nomJoueur = nomJoueur;
		this.pieceJoueur = pieceJoueur;
	}

	/**
	 * renvoie un entier naturel correspondant au poids total des
	 * objets tranporté par le joueur
	 * @return poids total des objets tranporté par le joueur
	 */

	public int poidsObjetsJoueur() {
		int res=0;
		for (int i=0; i<lesObjetsJoueur.size(); i++) {
			res += lesObjetsJoueur.get(i).getPoids();
		}
		return res;
	}

	/**
	 * renvoie un entier naturel correspondant au nombre fois que
	 * l'objet passer en paramètre apparaît parmi les objets
	 * transporter par le joueur
	 * @return nombre fois que l'objet passer en paramètre apparaît parmi les objets transporter par le joueur
	 */

	public int porteCombienDe(ObjetZork oz) {
		int res=0;
		for (int i=0; i<lesObjetsJoueur.size(); i++) {
			if (lesObjetsJoueur.get(i).equals(oz))
				res++;		
		}
		return res;	
	}

	/**
	 * renvoi un boolean qui vaut true si le joueur peut transporter 
	 * l'objet indiqué en paramètre false sinon
	 * @return true si le joueur peut transporter l'objet indiqué en paramètre; false sinon
	 */
	
	public boolean porterObjet(ObjetZork oz) {
		boolean bool;
		if ((this.poidsObjetsJoueur()+oz.getPoids()) <= CAPACITE_TRANSPORT_JOUEUR && oz.estTransportable()) {
			lesObjetsJoueur.add(oz);
			bool = true;
		} else {
			if (!oz.estTransportable())
				System.out.println("Cet objet est trop lourd pour vous!");
			else
				System.out.println("Vous ne pouvez pas prendre cette objet car vous n'avez plus de capacite");
			bool = false;
		}
		return bool;
	}

	/**
	 * renvoie un boolean qui vaut true si l'objet passé en paramètre a été
	 * transporter avec succès par le chien et false sinon
	 * @return  true si l'objet passé en paramètre a été transporter avec succès par le chien; false sinon
	 */

	public boolean porterObjetChien(ObjetZork oz) {
		boolean bool;
		if ((this.poidsObjetsJoueur()+oz.getPoids()) <= CAPACITE_TRANSPORT_JOUEUR && oz.estTransportable()) {
			lesObjetsJoueur.add(oz);
			bool = true;
		} else {
			if (!oz.estTransportable())
				System.out.println("Cet objet est trop lourd pour vous!");
			else
				System.out.println("Vous ne pouvez pas prendre cette objet car vous n'avez plus de capacite");
			bool = false;
		}
		return bool;
	}

	/**
	 * renvoi un boolean indiquant si le chien passé en paramètre
	 * a été adopter avec succès ou pas
	 * @return vrai si le chien passé en paramètre a été adopter avec succès; false sinon
	 */

	public boolean adopterChien(Chien c)  {
		return lesChiensJoueur.add(c);
	}

	/**
	 * renvoi un boolean indiquant si l'objet passé en paramètre
	 * a été deposer avec succès ou pas
	 * @return vrai si l'objet passé en paramètre a été deposer avec succès; false sinon
	 */

	public boolean deposerObjet(ObjetZork oz) {
		return lesObjetsJoueur.remove(oz);
	}

	/**
	 * renvoi un boolean indiquant si le chien passé en paramètre
	 * a été liberer avec succès ou pas
	 * @return vrai si l'objet passé en paramètre a été liberer avec succès; false sinon
	 */

	public boolean libererChien(Chien c) {
		return lesChiensJoueur.remove(c);
	}

	/**
	 * renvoi un boolean indiquant si l'objet passé en paramètre
	 * est parmi les objets que porte le joueur ou pas
	 * @return vrai si l'objet passé en paramètre est transporté par le joueur avec succès; false sinon
	 */

	public boolean parmiObjetPorte(ObjetZork oz) {
		return lesObjetsJoueur.contains(oz);
	}

	/**
	 * affiche la liste des objets transporté par le joueur
	 * cette méthode ne renvoie rien
	 */

	public void afficherObjetsJoueur() {
		System.out.println("\tLa liste des objet en votre possession : ");
		if (lesObjetsJoueur.size()>0) {
			for (int i=0; i<lesObjetsJoueur.size(); i++) {
				System.out.println(i+1 + ": " + lesObjetsJoueur.get(i).getDescription() + " ");
			}
		}else {
			System.out.println("\tVous ne disposez d'aucun objet pour l'instant !");
		}
		System.out.println();
	}

	/**
	 * affiche la liste des objets transporté par le chien
	 * cette méthode ne renvoie rien
	 */

	public void afficherChiensJoueur() {
		if (lesChiensJoueur.size()>0) {
			System.out.println("\tLa liste des chiens que vous avez adopte : ");
			for (int i=0; i<lesChiensJoueur.size(); i++) {
				System.out.println(i+1 + ": " + lesChiensJoueur.get(i).getDescription() + " ");
			}
		}else {
			System.out.println("\tVous n'adoptez aucun chien pour l'instant !");
		}
		System.out.println();
	}

}