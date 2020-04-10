package monprojet.classes;

public class Etudiant {
	
	private static int idMax;
	private int id;
	private String nom;
	private String prenom;
	private String sexe;
	private String filiere;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getFiliere() {
		return filiere;
	}
	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}
	
	// constructeur avec id en paramètre
	public Etudiant(int id, String nom, String prenom, String sexe, String filiere) {
		this.id = id;
		idMax++;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.filiere = filiere;
	}
	
	// constructeur avec id auto-increment
	public Etudiant(String nom, String prenom, String sexe, String filiere) {
		// l'écriture ci-dessous met idMax dans this.id PUIS ajoute 1 à idMax
		// si on avait écrit "this.id=++idMax;" ça aurait ajouté 1 à idMax PUIS ça aurait mis idMax dans this.id
		this.id=idMax++;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.filiere = filiere;
	}
	
	@Override
	public String toString() {
		return "Etudiant [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", filiere=" + filiere
				+ "]";
	}
	
	
}
