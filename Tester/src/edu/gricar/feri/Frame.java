package edu.gricar.feri;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JTable jTable = null;
	private JScrollPane jScrollPane = null;
	
	private JScrollPane getJScrollPane() {
		if(jScrollPane == null) {
			jScrollPane = new JScrollPane(getJTable());
		}
		return jScrollPane;
	}
	
	private JTable getJTable() {
		if(jTable == null) {
			Tester.main(null);
			Vector<String> columnName = new Vector<String>();
			columnName.add("Metoda");
			columnName.add("Funkcija");
			columnName.add("Število dimenzij");
			columnName.add("Število evaluacij");
			columnName.add("Povpreèje");
			columnName.add("Standardni odklon");
			jTable = new JTable(Tester.vector, columnName);
		}
		return jTable;
	}

	private JPanel getJContentPane() {
		if(jContentPane == null) {
			jContentPane = new JPanel(new BorderLayout());
			jContentPane.add(getJScrollPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Frame thisClass = new Frame();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	public Frame() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(1000, 800);
		this.setContentPane(getJContentPane());
		this.setTitle("Tester evolucije");
	}
}
