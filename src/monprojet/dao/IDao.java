package monprojet.dao;
import java.util.ArrayList;

// interface générique
public interface IDao <E> {
	// méthode pour ajouter un objet o de type E
	public boolean create (E o);
	public boolean delete(E o);
	public boolean update(E o);
	public E findById (int id);
	// méthode permettant de renvoyer la liste des objets de type E
	public ArrayList <E> findAll();
	
}
