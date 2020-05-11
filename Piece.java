import java.util.Map;
import java.util.HashMap;

import java.util.ArrayList;

/**
 *  Une piece dans un jeu d'aventure. <p>
 *
 *  Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 *  texte.</p> <p>
 *
 *  Une "Piece" represente un des lieux dans lesquels se déroule l'action du
 *  jeu. Elle est reliée a au plus quatre autres "Piece" par des sorties. Les
 *  sorties sont étiquettées "nord", "est", "sud", "ouest". Pour chaque
 *  direction, la "Piece" possède une référence sur la piece voisine ou null
 *  s'il n'y a pas de sortie dans cette direction.</p>
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @version    1.2
 * @since      August 2000
 */

public class Piece {
 	private String description;
	// mémorise les sorties de cette piece.
	private Map<Direction, Piece> sorties;

	private Dessin dessin = new Dessin();

	/*------------------------------------------------------*/
	public ArrayList<ObjetZork> lesObjets = new ArrayList<ObjetZork>();	
	public ArrayList<Chien> lesChiens = new ArrayList<Chien>();

	public String getNomPiece() {
		return this.description;
	}

	public int comtientCombienDe(ObjetZork oz) {
		int res=0;
		for (int i=0; i<lesObjets.size(); i++) {
			if (lesObjets.get(i).equals(oz))
				res++;
		}
		return res;
	}

	public void ajouter(ObjetZork oz) {
		lesObjets.add(oz);
	}

	public boolean retirer(ObjetZork oz) {
		return lesObjets.remove(oz);
	}

	public boolean contient(ObjetZork oz) {
		return lesObjets.contains(oz);
	}
	public void afficherObjetsPiece() {
		if (lesObjets.size()>0) {
			System.out.println("\n\tListe des objets contenu dans cette piece:");
			for (int i=0; i<lesObjets.size(); i++) {
				System.out.println(i+1 + ": " + lesObjets.get(i).getDescription() + " ");
				
				if (lesObjets.get(i).getNom().equals("Magicien")) {
					dessin.magicien();
					System.out.println("Oooh la! Il faut que vous tuer le mechant magicien avant qu'il ne vous transforme en grenouille!");
				}

				if (lesObjets.get(i).getNom().equals("Pistolet")) {
					dessin.pistolet();
					System.out.println("Vous pouvez vous munir de cet arme");
				}

				if (lesObjets.get(i).getNom().equals("Chat")) {
					dessin.chat();
					System.out.println("Ce chat s'introduit chez vous et ne vous laisse pas dormir le soir");
					System.out.println("Faites-lui peur en liberant un chien dans cette piece et vous ne le verrez plus jamais ;)");
				}
			}
			System.out.println();
		} else {
			System.out.println("\tCette piece ne contient aucaun objet !");
		}
	}

	public void afficherChiensPiece() {
		if (lesChiens.size()>0)
			System.out.println("\tLa liste des chiens contenu dans cette piece: ");
		for (int i=0; i<lesChiens.size(); i++) {
				if(lesChiens.get(i).estMalade()){
					dessin.chien_Malade();
				}
				else {
					dessin.chien();
				}
				System.out.println(i+1 + ": " + lesChiens.get(i).getDescription() + " ");
		}
		System.out.println();
	}

	public boolean retirerChien(Chien c) {
		return lesChiens.remove(c);
	}

	public void ajouterChien(Chien c) {
		lesChiens.add(c);
	}

	public Piece(String description, ArrayList<ObjetZork> lesObjetsDansPiece) {
		this.description = description;
		sorties = new HashMap<Direction, Piece>();
		this.lesObjets = lesObjetsDansPiece;
	}

	/*------------------------------------------------------*/


	/**
	 *  Initialise une piece décrite par la chaine de caractères spécifiée.
	 *  Initialement, cette piece ne possède aucune sortie. La description fournie
	 *  est une courte phrase comme "le "COULOIR"" ou "la "CUISINE"".
	 *
	 * @param  description  Description de la piece.
	 */
	public Piece(String description) {
		this.description = description;
		sorties = new HashMap<Direction, Piece>();
	}


	/**
	 *  Définie les sorties de cette piece. A chaque direction correspond ou bien
	 *  une piece ou bien la valeur null signifiant qu'il n'y a pas de sortie dans
	 *  cette direction.
	 *
	 * @param  nord   La sortie nord
	 * @param  est    La sortie est
	 * @param  sud    La sortie sud
	 * @param  ouest  La sortie ouest
	 */
	public void setSorties(Piece nord, Piece est, Piece sud, Piece ouest) {
		if (nord != null) {
			sorties.put(Direction.NORD, nord);
		}
		if (est != null) {
			sorties.put(Direction.EST, est);
		}
		if (sud != null) {
			sorties.put(Direction.SUD, sud);
		}
		if (ouest != null) {
			sorties.put(Direction.OUEST, ouest);
		}
	}


	/**
	 *  Renvoie la description de cette piece (i.e. la description spécifiée lors
	 *  de la création de cette instance).
	 *
	 * @return    Description de cette piece
	 */
	public String descriptionCourte() {
		return description;
	}


	/**
	 *  Renvoie une description de cette piece mentionant ses sorties et
	 *  directement formatée pour affichage, de la forme: <pre>
	 *  Vous etes dans la bibliothèque.
	 *  Sorties: nord ouest</pre> Cette description utilise la chaine de caractères
	 *  renvoyée par la méthode descriptionSorties pour décrire les sorties de
	 *  cette piece.
	 *
	 * @return    Description affichable de cette piece
	 */
	public String descriptionLongue() {
		/*if (descriptionSorties().equals("Sorties: SUD"))
		{
			dessin.bas();
		}*/
		switch(descriptionSorties()) {
			case "Sorties: NORD":
				dessin.haut(); break;
			case "Sorties: EST":
				dessin.droite(); break;
			case "Sorties: SUD":
				dessin.bas(); break;
			case "Sorties: OUEST":
				dessin.gauche(); break;
			case "Sorties: NORD OUEST EST SUD":
				dessin.tousOuvert(); break;
			case "Sorties: OUEST EST":
				dessin.gauche_Droite(); break;
			case "Sorties: OUEST SUD":
				dessin.gauche_Bas(); break;
			default: 
				System.out.println("	---> Pas de dessin disponible");
		}
		if (this.getNomPiece().equals("La \"TERRASSE\"")) {
			dessin.helico();
			System.out.println("Liberez le chien malade qui est chez vous quelque part ici a la terrasse pour que l'equipe de secours prenne charge de lui!");
		}
		return "Vous etes dans " + description + ".\n" + descriptionSorties();
	}


	/**
	 *  Renvoie une description des sorties de cette piece, de la forme: <pre>
	 *  Sorties: nord ouest</pre> Cette description est utilisée dans la
	 *  description longue d'une piece.
	 *
	 * @return    Une description des sorties de cette pièce.
	 */
	public String descriptionSorties() {
		String resulString = "Sorties:";
		for (Direction sortie :  sorties.keySet()) {
			resulString += " " + sortie;
		}
		return resulString;
	}

	public String descriptionDeplacementChien(Chien c, Piece courante, Piece suivante) {
		return  c.getNom() + " passe de: <<" + courante.getNomPiece() + ">> a: <<" + suivante.getNomPiece() + ">>" + ", energie: " + c.getEnergie();
	}


	/**
	 *  Renvoie la piece atteinte lorsque l'on se déplace a partir de cette piece
	 *  dans la direction spécifiée. Si cette piece ne possède aucune sortie dans cette direction,
	 *  renvoie null.
	 *
	 * @param  direction  La direction dans laquelle on souhaite se déplacer
	 * @return            Piece atteinte lorsque l'on se déplace dans la direction
	 *      spécifiée ou null.
	 */
	public Piece pieceSuivante(Direction d) {
		return sorties.get(d);
	}
}

