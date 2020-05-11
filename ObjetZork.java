public class ObjetZork {
	
	public static final int MAX_OBJET_TRANSPORTABLE = 1000;
	private final String nom;
	private final int poids;
	private final boolean mangeable;

	/**
	 * Initialise un objet décrit par son non, son poids
	 * et le fait d'être mangeable ou pas
	 * @param nom   le nom de l'objet
	 * @param poids  le poids de l'objet
	 * @param mangeable  boolean qui indique si l'objet est mangeable ou pas
	 */

	public ObjetZork(String nom, int poids, boolean mangeable) {
		this.nom = nom;
		this.poids = poids;
		this.mangeable = mangeable;
	}

	/**
	 * cette méthode renseigne sur le fait qu'un objet
	 * est mangeable ou pas en retournant true s'il est
	 * et false sinon
	 * @return  boolean, true si l'objet est mangeable; false sinon
	 */

	public boolean estMangeable() {
		return this.mangeable;
	}

	/**
	 * cette méthode renseigne sur le fait qu'un objet
	 * est transportable ou pas en retournant true s'il est
	 * et false sinon
	 * @return  boolean, true si l'objet est transportable; false sinon
	 */

	public boolean estTransportable() {
		return this.poids <= MAX_OBJET_TRANSPORTABLE;
	}

	/**
	 * renvoi une chaine de caractère: le nom de l'objet
	 * @return nom de l'objet
	 */

	public String getNom() {
		return nom;
	}

	/**
	 * renvoi un entier positif: le poids de l'objet
	 * @return poids de l'objet
	 */

	public int getPoids() {
		return poids;
	}

	/**
	 * renvoi une chaine de caractère descrivant l'objet
	 * @return description de l'objet
	 */

	public String getDescription() {
		return nom + " " + poids + "g";
	}

	/**
	 * renvoie un boolean qui vaut true si l'objet passé en paramètre
	 * est égal a l'objet sur lequel on applique la méthode, false sinon
	 * @return true si l'objet passé en paramètre est égal a l'objet sur lequel on applique la méthode; false sinon
	 */

	public boolean equals(Object o){
    		if (!(o instanceof ObjetZork))
    			return false;
    		ObjetZork oz = (ObjetZork)o;
    		return nom.equals(oz.nom) && (poids == oz.poids);	
  	}
}