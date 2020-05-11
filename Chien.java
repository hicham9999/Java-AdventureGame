import java.util.ArrayList;

/**
 *  <p>
 *  Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 *  texte.</p> 
 *	<p>
 *	  
 *	Cette classe permet de creer des instances de chiens
 *	capable eux aussi de transporter des objets et de 
 *  les deposer
*/

public class Chien {
	private final String nom;
	private final int maxPoidsObjet;
	private final int maxPoidsTotal;
	private int energie = 4;
	private boolean malade;
	
	public ArrayList<ObjetZork> lesObjetsChien = new ArrayList<ObjetZork>();

	/**
	 * Initialise un chien décrit par son nom, le poids max qu'il peut
	 * transporter, le poids maximal des objets qu'il peut transporter,
	 * ainsi du faite qu'il est malade ou pas
	 * @param nom   chaine de caractères correspondant au nom du chien
	 * @param maxPoidsObjet	 un entier representant le poids maximal de l'objet que peut transporter le chien
	 * @param maxPoidsObjet  un entier representant le poids maximal des objets que peut transporter le chien
	 */

	public Chien(String nom, int maxPoidsObjet, int maxPoidsTotal, boolean malade) {
		this.nom = nom;
		this.maxPoidsObjet = maxPoidsObjet;
		this.maxPoidsTotal = maxPoidsTotal;
		this.malade = malade;
	}

	/**
	 * renvoi la chaine de caractère correspondante au nom du chien
	 * @return nom du chien
	 */

	public String getNom() {
		return this.nom;
	}

	/**
	 * revoie l'entier correspondant a l'energie actuelle du chien
	 * @return energie actuelle du chien
	 */

	public int getEnergie() {
		return this.energie;
	}

	/**
	 * enleve de l'energie au chien a partir de l'entier passé en paramètre
	 * le parètre doit être un entier naturel non nul
	 * @param energieAEnlever  valeur de l'energie qu'on veut retirer
	 */

	public int enleverEnergie(int energieAEnlever) {
		this.energie -= energieAEnlever;
		return this.energie;
	}

	/**
	 * rajoute de l'energie au chien a partir de l'entier naturel non nul passé en paramètre
	 * @param energieAAjouter  valeur de l'energie a rajouter
	 */

	public int nourir(int energieAAjouter) {
		this.energie += energieAAjouter;
		return this.energie;
	}

	/**
	 * ne prend aucun paramètre, elle renvoi l'état du chien
	 * vrai s'il est malade et faux sinon
	 */

	public boolean estMalade() {
		return this.malade;
	}

	/**
	 * Calcul et retourne le poids total des objets transporté par le chien
	 * elle ne prend aucun paramètre
	 * @return poids total des objets transporté par le chien
	 */

	public int poidsObjetsChien() {
		int res=0;
		for (int i=0; i<lesObjetsChien.size(); i++)
			res += lesObjetsChien.get(i).getPoids();
		return res;
	}

	/**
	 * permet de savoir si un objet est transportable par le chien ou pas
	 * @param oz  l'objet qu'on veut savoir s'il est transportable par le chien ou pas
	 * @return true s'il est tranportable; false sinon
	 */

	public boolean estTransportable(ObjetZork oz) {
		return ( lesObjetsChien.size()<5 && (poidsObjetsChien()+oz.getPoids())<=this.maxPoidsTotal && oz.getPoids()<=this.maxPoidsObjet);
	}

	/**
	 * renvoi la description du chien en citon ses caractèristiques
	 * @return  description du chien
	 */

	public String getDescription() {
		return "\""+this.nom+"\"" + " ,poids max objet: " + this.maxPoidsObjet + " ,poids max total: " + this.maxPoidsTotal + ", son enegrie: " + this.getEnergie();
	}

	/**
	 * permet au chien de prendre un objet 
	 * @return true si l'objet a été pris; false sinon
	 */

	public boolean prendreObjetChien(ObjetZork oz) {
		return lesObjetsChien.add(oz);
	}

	/**
   	 * permet au chien de déposer un objet
   	 * @return true si l'objet a été déposer; false sinon
	 */

	public boolean deposerObjetChien(ObjetZork oz) {
		return lesObjetsChien.remove(oz);
	}

	/**
	 * affiche la liste des objets transporter par le chien
	 * cette methode ne renvoie rien
	 */

	public void afficherObjetsChien() {
		if (lesObjetsChien.size()>0) {
			System.out.println("\tVotre chien " + "\"" + this.nom + "\"" + " dispose des objets suivant: ");
			for (int i=0; i<lesObjetsChien.size(); i++) {
				System.out.println((i+1) + ": " + lesObjetsChien.get(i).getDescription());
			}
		} else {
			System.out.println("Votre chien " + "\"" + this.nom + "\"" + " Ne dispose d'AUCUN objet");
		}
	}
}