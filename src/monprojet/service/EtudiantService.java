package monprojet.service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import monprojet.classes.Etudiant;
import monprojet.dao.IDao;

public class EtudiantService implements IDao<Etudiant> {
	
	private ArrayList<Etudiant> listeEtudiants;
	
	public EtudiantService ()
	{
		listeEtudiants = new ArrayList<Etudiant>();
	}
	
	@Override
	public boolean create (Etudiant e) {
		listeEtudiants.add(e);
		return true;
	}
	
	public boolean delete (Etudiant e) {
		listeEtudiants.remove(e);
		return true;
	}
	
	public boolean update (Etudiant e) {
		Etudiant etudiantFound;
		etudiantFound = findById(e.getId());
		if (etudiantFound!=null)
		{
			listeEtudiants.set(listeEtudiants.indexOf(e), etudiantFound);
			return true;
		}
		return false;
	}
	
	public Etudiant findById (int id) {
		Iterator<Etudiant> etudiant = listeEtudiants.iterator();
		Etudiant unEtudiant;
		while (etudiant.hasNext())
		{
			unEtudiant=etudiant.next();
			if (unEtudiant.getId()==id)
			{
				return unEtudiant;
			}
		}
		return null;
	}
	
	public ArrayList<Etudiant> findAll() {
		return listeEtudiants;
	}
	
}
