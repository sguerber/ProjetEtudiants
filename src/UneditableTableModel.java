// On définit cette classe héritant de DefaultTableModel pour empêcher la modification directe du tableau

import javax.swing.table.DefaultTableModel;

public class UneditableTableModel extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UneditableTableModel(Object[][] data, Object[] columnNames) {
	    super(data, columnNames);
	}
	
	// Cette fonction permet de rendre l'édition manuelle du tableau impossible
	public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	
}
