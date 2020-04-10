import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import monprojet.classes.Etudiant;
import monprojet.service.EtudiantService;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.ButtonGroup;

public class InterfaceGraphique extends JFrame {

	private JPanel contentPane;
	private JTextField nom;
	private JTextField prenom;
	private JTable tableEtudiants;
	UneditableTableModel model;
	private static int id;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private EtudiantService monEtudiantService;
	private Etudiant UnEtudiant;
	private String genre;
	private int idOfStudentToUpdate;
	private int idOfStudentToRemove;
	
	// Variables pour faciliter la compréhension du code lorsque l'on clique sur une ligne du tableau d'étudiants
	private String recupDataNomOfTable;
	private String recupDataPrenomOfTable;
	private String recupDataSexeOfTable;
	private Object recupDataFiliereOfTable;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceGraphique frame = new InterfaceGraphique();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfaceGraphique() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBounds(12, 13, 361, 227);
		contentPane.add(leftPanel);
		leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel form = new JPanel();
		form.setToolTipText("");
		form.setBackground(Color.WHITE);
		form.setPreferredSize(new Dimension(350, 90));
		leftPanel.add(form);
		form.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(151, 16, 0, 2);
		form.add(separator);
		
		// LES LABELS

		
		JLabel lblInfosEtudiants = new JLabel("Informations Etudiants");
		lblInfosEtudiants.setBackground(new Color(0, 191, 255));
		lblInfosEtudiants.setBounds(0, 0, 136, 16);
		form.add(lblInfosEtudiants);
				
		JLabel lblNom = new JLabel("Nom: ");
		lblNom.setBounds(0, 32, 35, 16);
		form.add(lblNom);
		
		JLabel lblSexe = new JLabel("Sexe: ");
		lblSexe.setBounds(151, 32, 37, 16);
		form.add(lblSexe);
		
		JLabel lblPrenom = new JLabel("Pr\u00E9nom: ");
		lblPrenom.setBounds(0, 61, 53, 16);
		form.add(lblPrenom);
		
		JLabel lblFiliere = new JLabel("Fili\u00E8re: ");
		lblFiliere.setBounds(151, 61, 53, 16);
		form.add(lblFiliere);
		
		// LES SELECTIONS ET CHAMPS DU FORMULAIRE D'INFORMATIONS
		
		JRadioButton sexeF = new JRadioButton("F");
		buttonGroup.add(sexeF);
		sexeF.setBounds(193, 23, 70, 25);
		form.add(sexeF);
		
		JRadioButton sexeM = new JRadioButton("M");
		buttonGroup.add(sexeM);
		sexeM.setBounds(272, 23, 70, 25);
		form.add(sexeM);
		
		JComboBox comboBoxFiliere = new JComboBox();
		comboBoxFiliere.setBounds(193, 57, 149, 25);
		comboBoxFiliere.setModel(new DefaultComboBoxModel(new String[] {"Scientifique", "Litt\u00E9raire", "Economique et Sociale"}));
		form.add(comboBoxFiliere);

		nom = new JTextField();
		nom.setBounds(63, 32, 83, 16);
		form.add(nom);
		nom.setColumns(7);
		
		prenom = new JTextField();
		prenom.setColumns(7);
		prenom.setBounds(63, 61, 83, 16);
		form.add(prenom);
		
		JPanel PanelTable = new JPanel();
		PanelTable.setBackground(Color.WHITE);
		PanelTable.setPreferredSize(new Dimension(350, 110));
		leftPanel.add(PanelTable);
		PanelTable.setLayout(null);
		
		JLabel lblListeEtudiants = new JLabel("Liste des Etudiants");
		lblListeEtudiants.setBounds(0, 0, 117, 16);
		lblListeEtudiants.setBackground(new Color(0, 191, 255));
		PanelTable.add(lblListeEtudiants);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBounds(385, 13, 107, 227);
		contentPane.add(rightPanel);
		rightPanel.setLayout(null);
		

		monEtudiantService = new EtudiantService();
		
		// Bouton pour ajouter un étudiant dans la liste
		JButton btnAdd = new JButton("Ajouter");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Si un des champs n'est pas rempli
				if ( nom.getText()==null || prenom.getText()==null || (!sexeF.isSelected() && !sexeM.isSelected()) || comboBoxFiliere.getSelectedItem()==null )
				{
					// Une popup s'affiche avec un message d'erreur
					JOptionPane.showMessageDialog(contentPane, "Veuillez remplir tous les champs d'information svp.");
				}
				else
				{
					// Sinon on ajoute les informations de l'étudiant dans le tableau tableEtudiants
					String genre;
					if ( sexeF.isSelected() )
					{
						genre="F";
					}
					else
					{
						genre="M";
					}
					model.insertRow(model.getRowCount(), new Object[] {id, nom.getText(), prenom.getText(), genre, comboBoxFiliere.getSelectedItem()});
					
					// On ajoute également l'étudiant à la liste des étudiants de monEtudiantService
					UnEtudiant = new Etudiant (nom.getText(), prenom.getText(), genre, comboBoxFiliere.getSelectedItem().toString());
					monEtudiantService.create(UnEtudiant);
					id++;
				}
					
			};

		});
		btnAdd.setBounds(0, 42, 95, 25);
		rightPanel.add(btnAdd);
		
		// Bouton qui permet de modifier un étudiant dans la liste 
		JButton btnEdit = new JButton("Modifier");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Si aucune ligne du tableau (un étudiant) n'a été sélectionnée
				if ( tableEtudiants.getSelectedRow() == -1 )
				{
					JOptionPane.showMessageDialog(contentPane, "Veuillez sélectionnez un étudiant à modifier svp.");
				}
				// Sinon on récupère les données des champs d'information et modifie l'étudiant concerné avec ces nouvelles données
				else
				{
					model.setValueAt(nom.getText(),tableEtudiants.getSelectedRow(),1);
					model.setValueAt(prenom.getText(),tableEtudiants.getSelectedRow(),2);
					if (sexeF.isSelected())
					{
						genre="F";
					}
					else
					{
						genre="M";
					}
					model.setValueAt(genre, tableEtudiants.getSelectedRow(), 3);
					model.setValueAt(comboBoxFiliere.getSelectedItem(),tableEtudiants.getSelectedRow(),4);
					
					// On récupère l'id de la ligne de tableEtudiants qu'on souhaite modifier
					idOfStudentToUpdate = (int)(model.getValueAt(tableEtudiants.getSelectedRow(), 0));
					// Puis on met à jour notre listeEtudiants de monEtudiantService
					monEtudiantService.update(monEtudiantService.findById(idOfStudentToUpdate));
				}
				
			}
		});
		btnEdit.setBounds(0, 154, 95, 25);
		rightPanel.add(btnEdit);
		
	
		// Bouton pour supprimer un étudiant de la liste
		JButton btnDelete = new JButton("Supprimer");
		btnDelete.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			// Si aucune ligne du tableau (un étudiant) n'a été sélectionnée
			if ( tableEtudiants.getSelectedRow() == -1 )
			{
				JOptionPane.showMessageDialog(contentPane, "Veuillez sélectionnez un étudiant à supprimer svp.");
			}
			// Sinon on peut supprimer l'étudiant lié à la liste du tableau sélectionnée
			else
			{
				
				// On récupère l'id de la ligne de tableEtudiants qu'on souhaite supprimer
				idOfStudentToRemove = (int)(model.getValueAt(tableEtudiants.getSelectedRow(), 0));
				// Puis on le supprime de notre listeEtudiants dans monEtudiantService
				monEtudiantService.delete(monEtudiantService.findById(idOfStudentToUpdate));
				
				// Ensuite, on supprime la ligne de l'étudiant correspondant dans tableEtudiants
				model.removeRow(tableEtudiants.getSelectedRow());
			}	
		}
		});
			
		btnDelete.setBounds(0, 99, 95, 25);
		rightPanel.add(btnDelete);
		
		
		// Instanciation de tableEtudiants
		tableEtudiants = new JTable();
		// On ajoute un MouseListener qui récupère les données de la ligne de tableEtudiants (sur laquelle on a cliqué) pour les mettre dans les champs d'information
		tableEtudiants.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				// On récupère les informations de l'étudiant concerné à partir de la ligne sélectionnée dans le tableau
				recupDataNomOfTable = String.valueOf(model.getValueAt(tableEtudiants.getSelectedRow(), 1));
				recupDataPrenomOfTable = String.valueOf(model.getValueAt(tableEtudiants.getSelectedRow(), 2));
				recupDataSexeOfTable = String.valueOf(model.getValueAt(tableEtudiants.getSelectedRow(), 3));
				recupDataFiliereOfTable = model.getValueAt(tableEtudiants.getSelectedRow(), 4);
				
				// On affiche les données dans les champs d'informations 
				nom.setText(recupDataNomOfTable);
				prenom.setText(recupDataPrenomOfTable);
				
				if ( recupDataSexeOfTable== "F")
				{
					sexeF.setSelected(true);
				}
				else
				{
					sexeM.setSelected(true);
				}
				comboBoxFiliere.setSelectedItem(recupDataFiliereOfTable);
			}
		});
		
		tableEtudiants.setBounds(0, 23, 350, 87);
		PanelTable.add(tableEtudiants);
		
		// On définit le modèle de tableEtudiants (noms des colonnes associés aux données)
		tableEtudiants.setModel(new UneditableTableModel(
				new Object[][] {
				},
				new String[] {
					"ID", "Nom", "Prénom", "Sexe","Filière"
				}
			));
		
		// On définit les tailles des différentes cases de tableEtudiants
		tableEtudiants.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableEtudiants.getColumnModel().getColumn(1).setPreferredWidth(90);
		tableEtudiants.getColumnModel().getColumn(2).setPreferredWidth(90);
		tableEtudiants.getColumnModel().getColumn(3).setPreferredWidth(10);
		tableEtudiants.setPreferredSize(new Dimension(350, 100));
		
		// On définit model comme le modèle de tableEtudiants
		model = (UneditableTableModel) tableEtudiants.getModel(); 
	}
}
